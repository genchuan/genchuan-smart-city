<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="900px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="130px"
      v-loading="formLoading"
      class="p-4 bg-gray-50 rounded-lg"
    >
      <el-row :gutter="20">
        <!-- 第一列 -->
        <el-col :span="12">
          <el-form-item label="任务名称" prop="evalTaskName">
            <el-input
              v-model="formData.evalTaskName"
              placeholder="请输入任务名称"
              class="w-full rounded-md"
            />
          </el-form-item>
          <el-form-item label="任务编码" prop="evalTaskCode">
            <el-input
              v-model="formData.evalTaskCode"
              placeholder="请输入任务编码"
              class="w-full rounded-md"
            />
          </el-form-item>
          <el-form-item label="关联模板ID" prop="taskTemplateId">
            <el-input
              v-model="formData.taskTemplateId"
              placeholder="请输入关联模板ID"
              class="w-full rounded-md"
            />
          </el-form-item>
          <el-form-item label="关联模板名称" prop="taskTemplateName">
            <el-input
              v-model="formData.taskTemplateName"
              placeholder="请输入关联模板名称"
              class="w-full rounded-md"
            />
          </el-form-item>
          <el-form-item label="评价对象范围" prop="objectScope">
            <el-input
              v-model="formData.objectScope"
              placeholder="请输入评价对象范围"
              class="w-full rounded-md"
            />
          </el-form-item>
          <el-form-item label="关联对象IDs" prop="relateObjectIds">
            <el-input
              v-model="formData.relateObjectIds"
              placeholder="请输入关联对象IDs"
              class="w-full rounded-md"
            />
          </el-form-item>
        </el-col>

        <!-- 第二列 -->
        <el-col :span="12">
          <el-form-item label="关联对象Names" prop="relateObjectNames">
            <el-input
              v-model="formData.relateObjectNames"
              placeholder="请输入关联对象Names"
              class="w-full rounded-md"
            />
          </el-form-item>
          <el-form-item label="任务开始时间" prop="taskStartTime">
            <el-date-picker
              v-model="formData.taskStartTime"
              type="datetime"
              value-format="x"
              placeholder="选择任务开始时间"
              class="w-full rounded-md"
            />
          </el-form-item>
          <el-form-item label="任务结束时间" prop="taskEndTime">
            <el-date-picker
              v-model="formData.taskEndTime"
              type="datetime"
              value-format="x"
              placeholder="选择任务结束时间"
              class="w-full rounded-md"
            />
          </el-form-item>
          <el-form-item label="任务状态" prop="taskStatus">
            <el-select
              v-model="formData.taskStatus"
              placeholder="请选择任务状态"
              class="w-full rounded-md"
            >
              <el-option label="RUNNING" value="RUNNING" />
            </el-select>
          </el-form-item>
          <el-form-item label="数据采集方式" prop="dataCollectMethod">
            <el-input
              v-model="formData.dataCollectMethod"
              placeholder="请输入数据采集方式"
              class="w-full rounded-md"
            />
          </el-form-item>
          <el-form-item label="任务描述" prop="taskDesc">
            <el-input
              v-model="formData.taskDesc"
              placeholder="请输入任务描述"
              type="textarea"
              :rows="3"
              class="w-full rounded-md"
            />
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
  EvalTaskApi,
  EvalTaskVO
} from '@/api/dataHub/comprehensiveEval/evalTaskMgmt/evalTaskOpsMgmt'

/** 任务管理 表单 */
defineOptions({ name: 'EvalTaskForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

// 简化表单数据，隐藏不必要字段
const formData = ref({
  id: undefined,
  evalTaskName: undefined,
  evalTaskCode: undefined,
  taskTemplateId: undefined,
  taskTemplateName: undefined,
  objectScope: undefined,
  relateObjectIds: undefined,
  relateObjectNames: undefined,
  taskStartTime: undefined,
  taskEndTime: undefined,
  taskStatus: undefined,
  dataCollectMethod: undefined,
  taskDesc: undefined,
  createUserBiz: undefined,
  createTimeBiz: undefined
})

// 保留核心必填项校验
const formRules = reactive({
  evalTaskName: [{ required: true, message: '任务名称不能为空', trigger: 'blur' }],
  evalTaskCode: [{ required: true, message: '任务编码不能为空', trigger: 'blur' }],
  taskTemplateId: [{ required: true, message: '关联模板ID不能为空', trigger: 'blur' }],
  taskTemplateName: [{ required: true, message: '关联模板名称不能为空', trigger: 'blur' }],
  objectScope: [{ required: true, message: '评价对象范围不能为空', trigger: 'blur' }],
  taskStartTime: [{ required: true, message: '任务开始时间不能为空', trigger: 'change' }],
  taskEndTime: [{ required: true, message: '任务结束时间不能为空', trigger: 'change' }],
  taskStatus: [{ required: true, message: '任务状态不能为空', trigger: 'change' }],
  dataCollectMethod: [{ required: true, message: '数据采集方式不能为空', trigger: 'blur' }],
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
      const res = await EvalTaskApi.getEvalTask(id)
      formData.value = {
        ...res,
        // 只保留需要展示的字段
        taskDesc: res.taskDesc || '',
        relateObjectIds: res.relateObjectIds || '',
        relateObjectNames: res.relateObjectNames || ''
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
    const data = formData.value as unknown as EvalTaskVO
    if (formType.value === 'create') {
      await EvalTaskApi.createEvalTask(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalTaskApi.updateEvalTask(data)
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
    evalTaskName: undefined,
    evalTaskCode: undefined,
    taskTemplateId: undefined,
    taskTemplateName: undefined,
    objectScope: undefined,
    relateObjectIds: undefined,
    relateObjectNames: undefined,
    taskStartTime: undefined,
    taskEndTime: undefined,
    taskStatus: undefined,
    dataCollectMethod: undefined,
    taskDesc: undefined,
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

/* 日期选择器宽度适配 */
:deep(.el-date-editor--datetime) {
  width: 100% !important;
}
</style>
