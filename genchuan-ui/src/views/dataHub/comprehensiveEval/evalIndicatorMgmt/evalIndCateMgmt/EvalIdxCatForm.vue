<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="800px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="140px"
      v-loading="formLoading"
      class="p-4 bg-gray-50 rounded-lg"
    >
      <!-- 基础信息分组 -->
      <div class="form-group">
        <h3 class="group-title">基础信息</h3>
        <el-row :gutter="20" class="form-row">
          <el-col :span="12">
            <el-form-item label="指标分类名称" prop="idxCatName">
              <el-input
                v-model="formData.idxCatName"
                placeholder="请输入指标分类名称"
                class="rounded-md"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="分类编码" prop="catCode">
              <el-input
                v-model="formData.catCode"
                placeholder="请输入分类编码"
                class="rounded-md"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20" class="form-row">
          <el-col :span="12">
            <el-form-item label="所属指标体系ID" prop="idxSystemId">
              <el-input
                v-model="formData.idxSystemId"
                placeholder="请输入所属指标体系ID"
                class="rounded-md"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="所属指标体系名称" prop="idxSystemName">
              <el-input
                v-model="formData.idxSystemName"
                placeholder="请输入所属指标体系名称"
                class="rounded-md"
              />
            </el-form-item>
          </el-col>
        </el-row>
      </div>

      <!-- 配置信息分组 -->
      <div class="form-group">
        <h3 class="group-title">配置信息</h3>
        <el-row :gutter="20" class="form-row">
          <el-col :span="12">
            <el-form-item label="排序序号" prop="sortNum">
              <el-input
                v-model="formData.sortNum"
                placeholder="请输入排序序号"
                type="number"
                class="rounded-md"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="启用状态" prop="enableStatus">
              <el-radio-group v-model="formData.enableStatus" class="radio-group">
                <el-radio value="ENABLED">启用</el-radio>
                <el-radio value="Disabled">禁用</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20" class="form-row">
          <el-col :span="24">
            <el-form-item label="分类描述" prop="catDesc">
              <el-input
                v-model="formData.catDesc"
                placeholder="请输入分类描述"
                type="textarea"
                :rows="3"
                class="rounded-md"
              />
            </el-form-item>
          </el-col>
        </el-row>
      </div>

      <!-- 业务信息分组 -->
      <div class="form-group">
        <h3 class="group-title">业务信息</h3>
        <el-row :gutter="20" class="form-row">
          <el-col :span="12">
            <el-form-item label="创建人(业务)" prop="createUserBiz">
              <el-input
                v-model="formData.createUserBiz"
                placeholder="请输入创建人(业务)"
                class="rounded-md"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
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
      </div>
    </el-form>

    <template #footer>
      <el-button @click="dialogVisible = false" class="mr-2">取 消</el-button>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
import {
  EvalIdxCatApi,
  EvalIdxCatVO
} from '@/api/dataHub/comprehensiveEval/evalIndicatorMgmt/evalIndCateMgmt'

/** 指标分类管理 表单 */
defineOptions({ name: 'EvalIdxCatForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

// 简化表单数据，隐藏不必要字段
const formData = ref({
  id: undefined,
  idxCatName: undefined,
  catCode: undefined,
  idxSystemId: undefined,
  idxSystemName: undefined,
  sortNum: undefined,
  catDesc: undefined,
  enableStatus: undefined,
  createUserBiz: undefined,
  createTimeBiz: undefined
})

// 保留核心必填项校验
const formRules = reactive({
  idxCatName: [{ required: true, message: '指标分类名称不能为空', trigger: 'blur' }],
  catCode: [{ required: true, message: '分类编码不能为空', trigger: 'blur' }],
  idxSystemId: [{ required: true, message: '所属指标体系ID不能为空', trigger: 'blur' }],
  idxSystemName: [{ required: true, message: '所属指标体系名称不能为空', trigger: 'blur' }],
  sortNum: [
    { required: true, message: '排序序号不能为空', trigger: 'blur' },
    {
      type: 'number',
      message: '请输入数字',
      trigger: 'blur'
    }
  ],
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
      const res = await EvalIdxCatApi.getEvalIdxCat(id)
      formData.value = {
        ...res,
        // 只保留需要展示的字段
        catDesc: res.catDesc || '',
        sortNum: res.sortNum || 0
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
    const data = formData.value as unknown as EvalIdxCatVO
    if (formType.value === 'create') {
      await EvalIdxCatApi.createEvalIdxCat(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalIdxCatApi.updateEvalIdxCat(data)
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
    idxCatName: undefined,
    catCode: undefined,
    idxSystemId: undefined,
    idxSystemName: undefined,
    sortNum: undefined,
    catDesc: undefined,
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

/* 分组样式 */
.form-group {
  margin-bottom: 24px;
  padding: 16px;
  border: 1px solid #eee;
  border-radius: 6px;
  background-color: #fff;
}

.group-title {
  margin: 0 0 16px 0;
  padding-bottom: 8px;
  border-bottom: 1px solid #f0f0f0;
  font-size: 16px;
  color: #1f2329;
  font-weight: 500;
}

.form-row {
  margin-bottom: 8px;
}
</style>
