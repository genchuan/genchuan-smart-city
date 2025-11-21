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
      <el-row :gutter="20">
        <!-- 第一列 -->
        <el-col :span="12">
          <el-form-item label="规则分类名称" prop="ruleCatName">
            <el-input
              v-model="formData.ruleCatName"
              placeholder="请输入规则分类名称"
              class="w-full rounded-md"
            />
          </el-form-item>
          <el-form-item label="分类编码" prop="ruleCatCode">
            <el-input
              v-model="formData.ruleCatCode"
              placeholder="请输入分类编码"
              class="w-full rounded-md"
            />
          </el-form-item>
          <el-form-item label="适用指标体系ID" prop="idxSystemId">
            <el-input
              v-model="formData.idxSystemId"
              placeholder="请输入适用指标体系ID"
              class="w-full rounded-md"
            />
          </el-form-item>
          <el-form-item label="适用指标体系名称" prop="idxSystemName">
            <el-input
              v-model="formData.idxSystemName"
              placeholder="请输入适用指标体系名称"
              class="w-full rounded-md"
            />
          </el-form-item>
        </el-col>

        <!-- 第二列 -->
        <el-col :span="12">
          <el-form-item label="分类描述" prop="catDesc">
            <el-input
              v-model="formData.catDesc"
              placeholder="请输入分类描述"
              type="textarea"
              :rows="3"
              class="w-full rounded-md"
            />
          </el-form-item>
          <el-form-item label="启用状态" prop="enableStatus">
            <el-radio-group v-model="formData.enableStatus" class="radio-group">
              <el-radio value="ENABLED">启用</el-radio>
              <el-radio value="Disabled">禁用</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="创建人" prop="createUser">
            <el-input
              v-model="formData.createUser"
              placeholder="请输入创建人"
              class="w-full rounded-md"
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
  EvalRuleCatApi,
  EvalRuleCatVO
} from '@/api/dataHub/comprehensiveEval/evalRuleMgmt/evalRuleCateMgmt'

/** 规则分类管理 表单 */
defineOptions({ name: 'EvalRuleCatForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

// 简化表单数据，隐藏不必要字段
const formData = ref({
  id: undefined,
  ruleCatName: undefined,
  ruleCatCode: undefined,
  idxSystemId: undefined,
  idxSystemName: undefined,
  catDesc: undefined,
  enableStatus: undefined,
  createUser: undefined
})

// 保留核心必填项校验
const formRules = reactive({
  ruleCatName: [{ required: true, message: '规则分类名称不能为空', trigger: 'blur' }],
  ruleCatCode: [{ required: true, message: '分类编码不能为空', trigger: 'blur' }],
  idxSystemId: [{ required: true, message: '适用指标体系ID不能为空', trigger: 'blur' }],
  idxSystemName: [{ required: true, message: '适用指标体系名称不能为空', trigger: 'blur' }],
  enableStatus: [{ required: true, message: '启用状态不能为空', trigger: 'change' }],
  createUser: [{ required: true, message: '创建人不能为空', trigger: 'blur' }]
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
      const res = await EvalRuleCatApi.getEvalRuleCat(id)
      formData.value = {
        ...res,
        // 只保留需要展示的字段
        catDesc: res.catDesc || ''
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
    const data = formData.value as unknown as EvalRuleCatVO
    if (formType.value === 'create') {
      await EvalRuleCatApi.createEvalRuleCat(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalRuleCatApi.updateEvalRuleCat(data)
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
    ruleCatName: undefined,
    ruleCatCode: undefined,
    idxSystemId: undefined,
    idxSystemName: undefined,
    catDesc: undefined,
    enableStatus: undefined,
    createUser: undefined
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
  width: 100%;
}

.radio-group {
  display: flex;
  align-items: center;
  gap: 16px;
  padding: 5px 0;
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

/* 确保输入控件高度一致 */
:deep(.el-input__wrapper),
:deep(.el-date-editor) {
  height: 32px;
  line-height: 32px;
}

:deep(.el-date-editor .el-input__wrapper) {
  height: auto;
}
</style>
