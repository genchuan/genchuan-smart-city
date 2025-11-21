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
      <!-- 纵向布局：确保输入控件对齐，符合用户使用习惯 -->
      <el-form-item label="报告名称" prop="evalRptName">
        <el-input
          v-model="formData.evalRptName"
          placeholder="请输入报告名称"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="报告编码" prop="evalRptCode">
        <el-input
          v-model="formData.evalRptCode"
          placeholder="请输入报告编码"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="关联模板ID" prop="rptTemplateId">
        <el-input
          v-model="formData.rptTemplateId"
          placeholder="请输入关联模板ID"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="关联模板名称" prop="rptTemplateName">
        <el-input
          v-model="formData.rptTemplateName"
          placeholder="请输入关联模板名称"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="关联存档ID" prop="resultArchId">
        <el-input
          v-model="formData.resultArchId"
          placeholder="请输入关联存档ID"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="评价对象ID" prop="evalObjectId">
        <el-input
          v-model="formData.evalObjectId"
          placeholder="请输入评价对象ID"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="评价对象名称" prop="evalObjectName">
        <el-input
          v-model="formData.evalObjectName"
          placeholder="请输入评价对象名称"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="报告文件路径" prop="rptFilePath">
        <el-input
          v-model="formData.rptFilePath"
          placeholder="请输入报告文件路径"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="报告状态" prop="rptStatus">
        <el-select
          v-model="formData.rptStatus"
          placeholder="请选择报告状态"
          class="w-full rounded-md"
        >
          <el-option label="已生成" value="GENERATED" />
          <el-option label="处理中" value="PENDING" />
        </el-select>
      </el-form-item>
      <el-form-item label="生成时间" prop="generateTime">
        <el-date-picker
          v-model="formData.generateTime"
          type="datetime"
          value-format="x"
          placeholder="选择生成时间"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="补充内容" prop="suppleContent">
        <Editor v-model="formData.suppleContent" height="150px" class="w-full rounded-md" />
      </el-form-item>
      <el-form-item label="生成人" prop="generateUser">
        <el-input
          v-model="formData.generateUser"
          placeholder="请输入生成人"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="创建时间(业务)" prop="createTimeBiz">
        <el-date-picker
          v-model="formData.createTimeBiz"
          type="date"
          value-format="x"
          placeholder="选择创建时间(业务)"
          class="w-full rounded-md"
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
import {
  EvalRptApi,
  EvalRptVO
} from '@/api/dataHub/comprehensiveEval/evalReportMgmt/evalReportAutoGen'

/** 评价报告 表单 */
defineOptions({ name: 'EvalRptForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

// 简化表单数据，隐藏不必要字段
const formData = ref({
  id: undefined,
  evalRptName: undefined,
  evalRptCode: undefined,
  rptTemplateId: undefined,
  rptTemplateName: undefined,
  resultArchId: undefined,
  evalObjectId: undefined,
  evalObjectName: undefined,
  rptFilePath: undefined,
  rptStatus: undefined,
  generateTime: undefined,
  suppleContent: undefined,
  generateUser: undefined,
  createTimeBiz: undefined
})

// 保留核心必填项校验
const formRules = reactive({
  evalRptName: [{ required: true, message: '报告名称不能为空', trigger: 'blur' }],
  evalRptCode: [{ required: true, message: '报告编码不能为空', trigger: 'blur' }],
  rptTemplateId: [{ required: true, message: '关联模板ID不能为空', trigger: 'blur' }],
  rptTemplateName: [{ required: true, message: '关联模板名称不能为空', trigger: 'blur' }],
  resultArchId: [{ required: true, message: '关联存档ID不能为空', trigger: 'blur' }],
  evalObjectId: [{ required: true, message: '评价对象ID不能为空', trigger: 'blur' }],
  evalObjectName: [{ required: true, message: '评价对象名称不能为空', trigger: 'blur' }],
  rptFilePath: [{ required: true, message: '报告文件路径不能为空', trigger: 'blur' }],
  rptStatus: [{ required: true, message: '报告状态不能为空', trigger: 'change' }],
  generateUser: [{ required: true, message: '生成人不能为空', trigger: 'blur' }],
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
      const res = await EvalRptApi.getEvalRpt(id)
      formData.value = {
        ...res,
        // 只保留需要展示的字段
        suppleContent: res.suppleContent || '',
        rptFilePath: res.rptFilePath || ''
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
    const data = formData.value as unknown as EvalRptVO
    if (formType.value === 'create') {
      await EvalRptApi.createEvalRpt(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalRptApi.updateEvalRpt(data)
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
    evalRptName: undefined,
    evalRptCode: undefined,
    rptTemplateId: undefined,
    rptTemplateName: undefined,
    resultArchId: undefined,
    evalObjectId: undefined,
    evalObjectName: undefined,
    rptFilePath: undefined,
    rptStatus: undefined,
    generateTime: undefined,
    suppleContent: undefined,
    generateUser: undefined,
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
}

.el-form-item {
  margin-bottom: 20px;
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

/* 日期选择器宽度适配 */
:deep(.el-date-editor--datetime) {
  width: 100% !important;
}

/* 编辑器宽度适配 */
:deep(.w-e-container) {
  width: 100% !important;
}
</style>
