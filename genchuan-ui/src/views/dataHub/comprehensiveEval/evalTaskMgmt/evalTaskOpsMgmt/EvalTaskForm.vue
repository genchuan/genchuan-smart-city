<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="850px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="140px"
      v-loading="formLoading"
      class="p-4 bg-gray-50 rounded-lg"
    >
      <!-- 基础信息组 -->
      <div class="form-group">
        <h3 class="group-title">基础信息</h3>
        <el-row :gutter="24">
          <el-col :span="12">
            <el-form-item label="模板名称" prop="taskTemplateName">
              <el-input
                v-model="formData.taskTemplateName"
                placeholder="请输入模板名称"
                class="w-full rounded-md"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="模板编码" prop="taskTemplateCode">
              <el-input
                v-model="formData.taskTemplateCode"
                placeholder="请输入模板编码"
                class="w-full rounded-md"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="适用对象类型" prop="applyObjectType">
              <el-select
                v-model="formData.applyObjectType"
                placeholder="请选择适用对象类型"
                class="w-full rounded-md"
              >
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="模板描述" prop="templateDesc">
              <el-input
                v-model="formData.templateDesc"
                placeholder="请输入模板描述"
                type="textarea"
                :rows="3"
                class="w-full rounded-md"
              />
            </el-form-item>
          </el-col>
        </el-row>
      </div>

      <!-- 关联信息组 -->
      <div class="form-group">
        <h3 class="group-title">关联信息</h3>
        <el-row :gutter="24">
          <el-col :span="12">
            <el-form-item label="关联指标体系ID" prop="idxSystemId">
              <el-input
                v-model="formData.idxSystemId"
                placeholder="请输入关联指标体系ID"
                class="w-full rounded-md"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="关联指标体系名称" prop="idxSystemName">
              <el-input
                v-model="formData.idxSystemName"
                placeholder="请输入关联指标体系名称"
                class="w-full rounded-md"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="关联评价主体ID" prop="evalSubjectId">
              <el-input
                v-model="formData.evalSubjectId"
                placeholder="请输入关联评价主体ID"
                class="w-full rounded-md"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="关联评价主体名称" prop="evalSubjectName">
              <el-input
                v-model="formData.evalSubjectName"
                placeholder="请输入关联评价主体名称"
                class="w-full rounded-md"
              />
            </el-form-item>
          </el-col>
        </el-row>
      </div>

      <!-- 周期设置组 -->
      <div class="form-group">
        <h3 class="group-title">周期设置</h3>
        <el-row :gutter="24">
          <el-col :span="12">
            <el-form-item label="任务周期" prop="taskCycle">
              <el-input
                v-model="formData.taskCycle"
                placeholder="请输入任务周期"
                class="w-full rounded-md"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="自定义周期" prop="customCycle">
              <el-input
                v-model="formData.customCycle"
                placeholder="请输入自定义周期"
                class="w-full rounded-md"
              />
            </el-form-item>
          </el-col>
        </el-row>
      </div>

      <!-- 状态与创建信息组 -->
      <div class="form-group">
        <h3 class="group-title">状态与创建信息</h3>
        <el-row :gutter="24">
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
                class="w-full rounded-md"
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
                class="w-full rounded-md"
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
  EvalTaskTemplateApi,
  EvalTaskTemplateVO
} from '@/api/dataHub/comprehensiveEval/evalTaskMgmt/evalTaskTplMgmt'

/** 任务模板管理 表单 */
defineOptions({ name: 'EvalTaskTemplateForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

// 简化表单数据，隐藏不必要字段
const formData = ref({
  id: undefined,
  taskTemplateName: undefined,
  taskTemplateCode: undefined,
  applyObjectType: undefined,
  idxSystemId: undefined,
  idxSystemName: undefined,
  evalSubjectId: undefined,
  evalSubjectName: undefined,
  taskCycle: undefined,
  customCycle: undefined,
  templateDesc: undefined,
  enableStatus: undefined, // 默认启用
  createUserBiz: undefined,
  createTimeBiz: undefined
})

// 保留核心必填项校验
const formRules = reactive({
  taskTemplateName: [{ required: true, message: '模板名称不能为空', trigger: 'blur' }],
  taskTemplateCode: [{ required: true, message: '模板编码不能为空', trigger: 'blur' }],
  applyObjectType: [{ required: true, message: '适用对象类型不能为空', trigger: 'change' }],
  idxSystemId: [{ required: true, message: '关联指标体系ID不能为空', trigger: 'blur' }],
  idxSystemName: [{ required: true, message: '关联指标体系名称不能为空', trigger: 'blur' }],
  evalSubjectId: [{ required: true, message: '关联评价主体ID不能为空', trigger: 'blur' }],
  evalSubjectName: [{ required: true, message: '关联评价主体名称不能为空', trigger: 'blur' }],
  taskCycle: [{ required: true, message: '任务周期不能为空', trigger: 'blur' }],
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
      const res = await EvalTaskTemplateApi.getEvalTaskTemplate(id)
      formData.value = {
        ...res,
        // 只保留需要展示的字段
        templateDesc: res.templateDesc || '',
        customCycle: res.customCycle || ''
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
    const data = formData.value as unknown as EvalTaskTemplateVO
    if (formType.value === 'create') {
      await EvalTaskTemplateApi.createEvalTaskTemplate(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalTaskTemplateApi.updateEvalTaskTemplate(data)
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
    taskTemplateName: undefined,
    taskTemplateCode: undefined,
    applyObjectType: undefined,
    idxSystemId: undefined,
    idxSystemName: undefined,
    evalSubjectId: undefined,
    evalSubjectName: undefined,
    taskCycle: undefined,
    customCycle: undefined,
    templateDesc: undefined,
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
  width: 100%;
}

.radio-group {
  display: flex;
  align-items: center;
  gap: 16px;
  padding: 5px 0;
}

.el-dialog__body {
  padding: 20px 24px;
  max-height: 70vh;
  overflow-y: auto;
}

.el-form-item {
  margin-bottom: 18px;
}

.el-textarea {
  resize: none;
}

/* 确保输入控件高度一致 */
:deep(.el-input__wrapper),
:deep(.el-date-editor) {
  height: 34px;
  line-height: 34px;
}

:deep(.el-date-editor .el-input__wrapper) {
  height: auto;
}

/* 分组样式优化 */
.form-group {
  margin-bottom: 28px;
  padding: 15px 10px;
  background-color: #fff;
  border-radius: 6px;
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);
}

.form-group:last-child {
  margin-bottom: 0;
}

.group-title {
  margin-bottom: 18px;
  padding-left: 8px;
  font-size: 15px;
  font-weight: 500;
  color: #1f2329;
  border-left: 3px solid #409eff;
}

/* 响应式调整 */
@media (max-width: 768px) {
  .el-row {
    --el-row-gutter: 12px;
  }

  .el-col {
    --el-col-span: 24 !important;
  }

  .form-group {
    padding: 10px 5px;
    margin-bottom: 20px;
  }
}
</style>
