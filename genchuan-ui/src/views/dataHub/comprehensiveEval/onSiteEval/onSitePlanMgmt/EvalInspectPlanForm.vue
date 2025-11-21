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
      <!-- 纵向布局：确保所有输入控件对齐，提升用户体验 -->
      <el-form-item label="计划名称" prop="inspectPlanName">
        <el-input
          v-model="formData.inspectPlanName"
          placeholder="请输入计划名称"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="计划编码" prop="inspectPlanCode">
        <el-input
          v-model="formData.inspectPlanCode"
          placeholder="请输入计划编码"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="关联任务ID" prop="evalTaskId">
        <el-input
          v-model="formData.evalTaskId"
          placeholder="请输入关联任务ID"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="关联任务名称" prop="evalTaskName">
        <el-input
          v-model="formData.evalTaskName"
          placeholder="请输入关联任务名称"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="考察对象ID" prop="evalObjectId">
        <el-input
          v-model="formData.evalObjectId"
          placeholder="请输入考察对象ID"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="考察对象名称" prop="evalObjectName">
        <el-input
          v-model="formData.evalObjectName"
          placeholder="请输入考察对象名称"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="考察人员ID" prop="inspectorId">
        <el-input
          v-model="formData.inspectorId"
          placeholder="请输入考察人员ID"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="考察人员姓名" prop="inspectorName">
        <el-input
          v-model="formData.inspectorName"
          placeholder="请输入考察人员姓名"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="考察时间" prop="inspectTime">
        <el-date-picker
          v-model="formData.inspectTime"
          type="datetime"
          value-format="x"
          placeholder="选择考察时间"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="考察方式" prop="inspectMethod">
        <el-input
          v-model="formData.inspectMethod"
          placeholder="请输入考察方式"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="考察内容" prop="inspectContent">
        <Editor v-model="formData.inspectContent" height="150px" class="w-full" />
      </el-form-item>
      <el-form-item label="计划状态" prop="planStatus">
        <el-select
          v-model="formData.planStatus"
          placeholder="请选择计划状态"
          class="w-full rounded-md"
        >
          <el-option label="计划中" value="PLANNED" />
          <el-option label="已完成" value="COMPLETED" />
          <el-option label="进行中" value="RUNNING" />
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
    </el-form>

    <template #footer>
      <el-button @click="dialogVisible = false" class="mr-2">取 消</el-button>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
import {
  EvalInspectPlanApi,
  EvalInspectPlanVO
} from '@/api/dataHub/comprehensiveEval/onSiteEval/onSitePlanMgmt'

/** 考察计划管理 表单 */
defineOptions({ name: 'EvalInspectPlanForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

// 简化表单数据，隐藏不必要字段
const formData = ref({
  id: undefined,
  inspectPlanName: undefined,
  inspectPlanCode: undefined,
  evalTaskId: undefined,
  evalTaskName: undefined,
  evalObjectId: undefined,
  evalObjectName: undefined,
  inspectorId: undefined,
  inspectorName: undefined,
  inspectTime: undefined,
  inspectMethod: undefined,
  inspectContent: undefined,
  planStatus: undefined,
  createUserBiz: undefined,
  createTimeBiz: undefined
})

// 保留核心必填项校验
const formRules = reactive({
  inspectPlanName: [{ required: true, message: '计划名称不能为空', trigger: 'blur' }],
  inspectPlanCode: [{ required: true, message: '计划编码不能为空', trigger: 'blur' }],
  evalTaskId: [{ required: true, message: '关联任务ID不能为空', trigger: 'blur' }],
  evalTaskName: [{ required: true, message: '关联任务名称不能为空', trigger: 'blur' }],
  evalObjectId: [{ required: true, message: '考察对象ID不能为空', trigger: 'blur' }],
  evalObjectName: [{ required: true, message: '考察对象名称不能为空', trigger: 'blur' }],
  inspectorId: [{ required: true, message: '考察人员ID不能为空', trigger: 'blur' }],
  inspectorName: [{ required: true, message: '考察人员姓名不能为空', trigger: 'blur' }],
  inspectTime: [{ required: true, message: '考察时间不能为空', trigger: 'change' }],
  inspectMethod: [{ required: true, message: '考察方式不能为空', trigger: 'blur' }],
  inspectContent: [{ required: true, message: '考察内容不能为空', trigger: 'blur' }],
  planStatus: [{ required: true, message: '计划状态不能为空', trigger: 'change' }],
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
      const res = await EvalInspectPlanApi.getEvalInspectPlan(id)
      formData.value = {
        ...res,
        // 只保留需要展示的字段
        inspectContent: res.inspectContent || ''
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
    const data = formData.value as unknown as EvalInspectPlanVO
    if (formType.value === 'create') {
      await EvalInspectPlanApi.createEvalInspectPlan(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalInspectPlanApi.updateEvalInspectPlan(data)
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
    inspectPlanName: undefined,
    inspectPlanCode: undefined,
    evalTaskId: undefined,
    evalTaskName: undefined,
    evalObjectId: undefined,
    evalObjectName: undefined,
    inspectorId: undefined,
    inspectorName: undefined,
    inspectTime: undefined,
    inspectMethod: undefined,
    inspectContent: undefined,
    planStatus: undefined,
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
.el-editor {
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

/* 编辑器宽度适配 */
:deep(.el-editor) {
  width: 100% !important;
}
</style>
