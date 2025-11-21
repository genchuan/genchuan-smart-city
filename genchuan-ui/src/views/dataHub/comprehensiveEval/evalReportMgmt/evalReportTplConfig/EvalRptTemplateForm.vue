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
      <el-form-item label="模板名称" prop="rptTemplateName">
        <el-input
          v-model="formData.rptTemplateName"
          placeholder="请输入模板名称"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="模板编码" prop="rptTemplateCode">
        <el-input
          v-model="formData.rptTemplateCode"
          placeholder="请输入模板编码"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="适用任务类型" prop="applyTaskType">
        <el-select
          v-model="formData.applyTaskType"
          placeholder="请选择适用任务类型"
          class="w-full rounded-md"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="模板文件路径" prop="templateFilePath">
        <el-input
          v-model="formData.templateFilePath"
          placeholder="请输入模板文件路径"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="报告结构" prop="rptStructure">
        <el-input
          v-model="formData.rptStructure"
          placeholder="请输入报告结构（例：标题-正文-结论）"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="动态字段映射" prop="dynamicFieldMap">
        <el-input
          v-model="formData.dynamicFieldMap"
          placeholder="请输入动态字段映射（JSON格式）"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="固定内容" prop="fixedContent">
        <Editor v-model="formData.fixedContent" height="150px" class="w-full rounded-md" />
      </el-form-item>
      <el-form-item label="模板版本" prop="templateVersion">
        <el-input
          v-model="formData.templateVersion"
          placeholder="请输入模板版本（例：V1.0.0）"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="启用状态" prop="enableStatus">
        <el-select
          v-model="formData.enableStatus"
          placeholder="请选择启用状态"
          class="w-full rounded-md"
        >
          <el-option label="启用" value="ENABLED" />
          <el-option label="禁用" value="DISABLED" />
        </el-select>
      </el-form-item>
      <el-form-item label="创建人(业务)" prop="createUserBiz">
        <el-input
          v-model="formData.createUserBiz"
          placeholder="请输入创建人(业务)"
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
      <el-form-item label="更新人(业务)" prop="updateUserBiz">
        <el-input
          v-model="formData.updateUserBiz"
          placeholder="请输入更新人(业务)"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="更新时间(业务)" prop="updateTimeBiz">
        <el-date-picker
          v-model="formData.updateTimeBiz"
          type="date"
          value-format="x"
          placeholder="选择更新时间(业务)"
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
  EvalRptTemplateApi,
  EvalRptTemplateVO
} from '@/api/dataHub/comprehensiveEval/evalReportMgmt/evalReportTplConfig'

/** 报告模板配置 表单 */
defineOptions({ name: 'EvalRptTemplateForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

// 简化表单数据，隐藏不必要字段
const formData = ref({
  id: undefined,
  rptTemplateName: undefined,
  rptTemplateCode: undefined,
  applyTaskType: undefined,
  templateFilePath: undefined,
  rptStructure: undefined,
  dynamicFieldMap: undefined,
  fixedContent: undefined,
  templateVersion: undefined,
  enableStatus: undefined,
  createUserBiz: undefined,
  createTimeBiz: undefined,
  updateUserBiz: undefined,
  updateTimeBiz: undefined
})

// 保留核心必填项校验
const formRules = reactive({
  rptTemplateName: [{ required: true, message: '模板名称不能为空', trigger: 'blur' }],
  rptTemplateCode: [{ required: true, message: '模板编码不能为空', trigger: 'blur' }],
  applyTaskType: [{ required: true, message: '适用任务类型不能为空', trigger: 'change' }],
  templateFilePath: [{ required: true, message: '模板文件路径不能为空', trigger: 'blur' }],
  rptStructure: [{ required: true, message: '报告结构不能为空', trigger: 'blur' }],
  dynamicFieldMap: [{ required: true, message: '动态字段映射不能为空', trigger: 'blur' }],
  templateVersion: [{ required: true, message: '模板版本不能为空', trigger: 'blur' }],
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
      const res = await EvalRptTemplateApi.getEvalRptTemplate(id)
      formData.value = {
        ...res,
        // 只保留需要展示的字段
        fixedContent: res.fixedContent || '',
        dynamicFieldMap: res.dynamicFieldMap || ''
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
    const data = formData.value as unknown as EvalRptTemplateVO
    if (formType.value === 'create') {
      await EvalRptTemplateApi.createEvalRptTemplate(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalRptTemplateApi.updateEvalRptTemplate(data)
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
    rptTemplateName: undefined,
    rptTemplateCode: undefined,
    applyTaskType: undefined,
    templateFilePath: undefined,
    rptStructure: undefined,
    dynamicFieldMap: undefined,
    fixedContent: undefined,
    templateVersion: undefined,
    enableStatus: undefined,
    createUserBiz: undefined,
    createTimeBiz: undefined,
    updateUserBiz: undefined,
    updateTimeBiz: undefined
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
:deep(.el-date-editor--date) {
  width: 100% !important;
}

/* 编辑器宽度适配 */
:deep(.w-e-container) {
  width: 100% !important;
}
</style>
