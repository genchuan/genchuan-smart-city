<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="600px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
      class="p-4 bg-gray-50 rounded-lg"
    >
      <!-- 基础信息区域 -->
      <el-form-item label="指标体系名称" prop="idxSystemName">
        <el-input
          v-model="formData.idxSystemName"
          placeholder="请输入指标体系名称"
          class="rounded-md"
        />
      </el-form-item>

      <el-form-item label="体系编码" prop="systemCode">
        <el-input v-model="formData.systemCode" placeholder="请输入体系编码" class="rounded-md" />
      </el-form-item>

      <el-form-item label="适用对象类型" prop="applyObjectType">
        <el-select
          v-model="formData.applyObjectType"
          placeholder="请选择适用对象类型"
          class="rounded-md"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>

      <el-form-item label="体系版本" prop="systemVersion">
        <el-input
          v-model="formData.systemVersion"
          placeholder="请输入体系版本"
          class="rounded-md"
        />
      </el-form-item>

      <!-- 描述信息区域 -->
      <el-form-item label="体系描述" prop="systemDesc">
        <el-input
          v-model="formData.systemDesc"
          placeholder="请输入体系描述"
          type="textarea"
          :rows="3"
          class="rounded-md"
        />
      </el-form-item>

      <!-- 状态与创建信息区域 -->
      <el-row :gutter="20" class="mt-2">
        <el-col :span="12">
          <el-form-item label="启用状态" prop="enableStatus">
            <el-radio-group v-model="formData.enableStatus" class="radio-group">
              <el-radio value="ENABLED">启用</el-radio>
              <el-radio value="Disabled">禁用</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="创建人(业务)" prop="createUserBiz">
            <el-input
              v-model="formData.createUserBiz"
              placeholder="请输入创建人(业务)"
              class="rounded-md"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="创建时间(业务)" prop="createTimeBiz">
        <el-date-picker
          v-model="formData.createTimeBiz"
          type="date"
          value-format="x"
          placeholder="选择创建时间(业务)"
          class="rounded-md"
        />
      </el-form-item>
    </el-form>

    <template #footer>
      <el-button @click="dialogVisible = false" class="mr-2">取 消</el-button>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
// 脚本部分保持不变
import {
  EvalIdxSystemApi,
  EvalIdxSystemVO
} from '@/api/dataHub/comprehensiveEval/evalIndicatorMgmt/evalIndSysMgmt'

/** 指标体系管理 表单 */
defineOptions({ name: 'EvalIdxSystemForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

// 简化表单数据，隐藏不必要字段
const formData = ref({
  id: undefined,
  idxSystemName: undefined,
  systemCode: undefined,
  applyObjectType: undefined,
  systemVersion: undefined,
  systemDesc: undefined,
  enableStatus: undefined, // 默认启用
  createUserBiz: undefined,
  createTimeBiz: undefined
})

// 保留核心必填项校验
const formRules = reactive({
  idxSystemName: [{ required: true, message: '指标体系名称不能为空', trigger: 'blur' }],
  systemCode: [{ required: true, message: '体系编码不能为空', trigger: 'blur' }],
  applyObjectType: [{ required: true, message: '适用对象类型不能为空', trigger: 'change' }],
  systemVersion: [{ required: true, message: '体系版本不能为空', trigger: 'blur' }],
  enableStatus: [{ required: true, message: '启用状态不能为空', trigger: 'change' }],
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
      const res = await EvalIdxSystemApi.getEvalIdxSystem(id)
      formData.value = {
        ...res,
        // 只保留需要展示的字段
        systemDesc: res.systemDesc || ''
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
    const data = formData.value as unknown as EvalIdxSystemVO
    if (formType.value === 'create') {
      await EvalIdxSystemApi.createEvalIdxSystem(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalIdxSystemApi.updateEvalIdxSystem(data)
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
    idxSystemName: undefined,
    systemCode: undefined,
    applyObjectType: undefined,
    systemVersion: undefined,
    systemDesc: undefined,
    enableStatus: undefined,
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
.el-select,
.el-radio-group {
  --el-input-bg-color: #fff;
  --el-input-border-color: #e4e7ed;
  --el-input-hover-border-color: #409eff;
}

.radio-group {
  display: flex;
  align-items: center;
  gap: 16px;
}

.el-dialog__body {
  padding: 16px 24px;
}

.el-form-item {
  margin-bottom: 20px;
}

.el-textarea {
  resize: none;
}
</style>
