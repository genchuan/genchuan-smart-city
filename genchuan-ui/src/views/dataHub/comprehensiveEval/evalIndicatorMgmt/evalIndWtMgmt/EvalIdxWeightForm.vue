<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="600px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="140px"
      v-loading="formLoading"
      class="p-4 bg-gray-50 rounded-lg"
    >
      <!-- 采用单列布局，通过栅格系统确保整齐对齐 -->
      <el-row :gutter="20">
        <el-col :span="24">
          <el-form-item label="所属指标体系ID" prop="idxSystemId">
            <el-input
              v-model="formData.idxSystemId"
              placeholder="请输入所属指标体系ID"
              class="rounded-md"
            />
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="所属指标体系名称" prop="idxSystemName">
            <el-input
              v-model="formData.idxSystemName"
              placeholder="请输入所属指标体系名称"
              class="rounded-md"
            />
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="权重类型" prop="weightType">
            <el-select
              v-model="formData.weightType"
              placeholder="请选择权重类型"
              class="rounded-md"
            >
              <el-option label="请选择字典生成" value="" />
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="关联ID" prop="relateId">
            <el-input v-model="formData.relateId" placeholder="请输入关联ID" class="rounded-md" />
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="关联名称" prop="relateName">
            <el-input
              v-model="formData.relateName"
              placeholder="请输入关联名称"
              class="rounded-md"
            />
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="权重值(%)" prop="weightValue">
            <el-input
              v-model="formData.weightValue"
              placeholder="请输入权重值(%)"
              type="number"
              min="0"
              max="100"
              class="rounded-md"
            />
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="权重说明" prop="weightDesc">
            <el-input
              v-model="formData.weightDesc"
              placeholder="请输入权重说明"
              type="textarea"
              :rows="3"
              class="rounded-md"
            />
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="创建人(业务)" prop="createUserBiz">
            <el-input
              v-model="formData.createUserBiz"
              placeholder="请输入创建人(业务)"
              class="rounded-md"
            />
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="创建时间(业务)" prop="createTimeBiz">
            <el-date-picker
              v-model="formData.createTimeBiz"
              type="date"
              value-format="x"
              placeholder="选择创建时间(业务)"
              class="rounded-md"
            />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <template #footer>
      <el-button @click="dialogVisible = false" class="mr-2">取 消</el-button>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
import {
  EvalIdxWeightApi,
  EvalIdxWeightVO
} from '@/api/dataHub/comprehensiveEval/evalIndicatorMgmt/evalIndWtMgmt'

/** 指标权重管理 表单 */
defineOptions({ name: 'EvalIdxWeightForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

// 简化表单数据，隐藏不必要字段
const formData = ref({
  id: undefined,
  idxSystemId: undefined,
  idxSystemName: undefined,
  weightType: undefined,
  relateId: undefined,
  relateName: undefined,
  weightValue: undefined,
  weightDesc: undefined,
  createUserBiz: undefined,
  createTimeBiz: undefined
})

// 保留核心必填项校验
const formRules = reactive({
  idxSystemId: [{ required: true, message: '所属指标体系ID不能为空', trigger: 'blur' }],
  idxSystemName: [{ required: true, message: '所属指标体系名称不能为空', trigger: 'blur' }],
  weightType: [{ required: true, message: '权重类型不能为空', trigger: 'change' }],
  relateId: [{ required: true, message: '关联ID不能为空', trigger: 'blur' }],
  relateName: [{ required: true, message: '关联名称不能为空', trigger: 'blur' }],
  weightValue: [
    { required: true, message: '权重值(%)不能为空', trigger: 'blur' },
    { type: 'number', message: '请输入有效数字', trigger: 'blur' },
    { min: 0, max: 100, message: '权重值需在0-100之间', trigger: 'blur' }
  ],
  createUserBiz: [{ required: true, message: '创建人(业务)不能为空', trigger: 'blur' }],
  createTimeBiz: [{ required: true, message: '创建时间(业务)不能为空', trigger: 'change' }]
})

const formRef = ref() // 表单 Ref

/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = t('action.' + type)
  formType.value = type
  resetForm()

  // 修改时，设置数据
  if (id) {
    formLoading.value = true
    try {
      const res = await EvalIdxWeightApi.getEvalIdxWeight(id)
      formData.value = {
        ...res,
        // 只保留需要展示的字段
        weightDesc: res.weightDesc || '',
        weightValue: res.weightValue || 0
      }
    } finally {
      formLoading.value = false
    }
  }
}

defineExpose({ open }) // 提供 open 方法

/** 提交表单 */
const emit = defineEmits(['success'])
const submitForm = async () => {
  // 校验表单
  await formRef.value.validate()
  // 提交请求
  formLoading.value = true
  try {
    const data = formData.value as unknown as EvalIdxWeightVO
    if (formType.value === 'create') {
      await EvalIdxWeightApi.createEvalIdxWeight(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalIdxWeightApi.updateEvalIdxWeight(data)
      message.success(t('common.updateSuccess'))
    }
    dialogVisible.value = false
    emit('success')
  } finally {
    formLoading.value = false
  }
}

/** 重置表单 */
const resetForm = () => {
  formData.value = {
    id: undefined,
    idxSystemId: undefined,
    idxSystemName: undefined,
    weightType: undefined,
    relateId: undefined,
    relateName: undefined,
    weightValue: undefined,
    weightDesc: undefined,
    createUserBiz: undefined,
    createTimeBiz: undefined
  }
  formRef.value?.resetFields()
}
</script>

<style scoped>
.el-form {
  background-color: #fafafa;
}

.el-input,
.el-date-picker,
.el-select {
  --el-input-bg-color: #fff;
  --el-input-border-color: #e4e7ed;
  --el-input-hover-border-color: #409eff;
  width: 100%;
}

.el-dialog__body {
  padding: 16px 24px;
  max-height: 70vh;
  overflow-y: auto;
}

.el-form-item {
  margin-bottom: 16px;
}

.el-textarea {
  resize: none;
}
</style>
