<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="规范ID" prop="processExecStdId">
        <el-input v-model="formData.processExecStdId" placeholder="请输入规范ID" />
      </el-form-item>
      <el-form-item label="规范名称" prop="stdName">
        <el-input v-model="formData.stdName" placeholder="请输入规范名称" />
      </el-form-item>
      <el-form-item label="关联流程ID" prop="relatedProcessId">
        <el-input v-model="formData.relatedProcessId" placeholder="请输入关联流程ID" />
      </el-form-item>
      <el-form-item label="关联流程名称" prop="relatedProcessName">
        <el-input v-model="formData.relatedProcessName" placeholder="请输入关联流程名称" />
      </el-form-item>
      <el-form-item label="流程步骤" prop="processSteps">
        <el-input v-model="formData.processSteps" placeholder="请输入流程步骤" />
      </el-form-item>
      <el-form-item label="责任部门代码" prop="deptCode">
        <el-input v-model="formData.deptCode" placeholder="请输入责任部门代码" />
      </el-form-item>
      <el-form-item label="责任部门名称" prop="deptName">
        <el-input v-model="formData.deptName" placeholder="请输入责任部门名称" />
      </el-form-item>
      <el-form-item label="时限要求" prop="timeLimit">
        <el-input v-model="formData.timeLimit" placeholder="请输入时限要求" />
      </el-form-item>
      <el-form-item label="执行要求" prop="execRequirement">
        <el-input v-model="formData.execRequirement" placeholder="请输入执行要求" />
      </el-form-item>
      <el-form-item label="规范文件ID" prop="stdFileId">
        <el-input v-model="formData.stdFileId" placeholder="请输入规范文件ID" />
      </el-form-item>
      <el-form-item label="规范文件路径" prop="stdFilePath">
        <el-input v-model="formData.stdFilePath" placeholder="请输入规范文件路径" />
      </el-form-item>
      <el-form-item label="规范状态" prop="stdStatus">
        <el-radio-group v-model="formData.stdStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="创建人" prop="createUser">
        <el-input v-model="formData.createUser" placeholder="请输入创建人" />
      </el-form-item>
      <el-form-item label="更新人" prop="updateUser">
        <el-input v-model="formData.updateUser" placeholder="请输入更新人" />
      </el-form-item>
      <el-form-item label="分类扩展字段1" prop="extCat1">
        <el-input v-model="formData.extCat1" placeholder="请输入分类扩展字段1" />
      </el-form-item>
      <el-form-item label="分类扩展字段2" prop="extCat2">
        <el-input v-model="formData.extCat2" placeholder="请输入分类扩展字段2" />
      </el-form-item>
      <el-form-item label="通用扩展字段1" prop="extCommon1">
        <el-input v-model="formData.extCommon1" placeholder="请输入通用扩展字段1" />
      </el-form-item>
      <el-form-item label="通用扩展字段2" prop="extCommon2">
        <el-input v-model="formData.extCommon2" placeholder="请输入通用扩展字段2" />
      </el-form-item>
      <el-form-item label="系统创建时间" prop="createTimeSys">
        <el-date-picker
          v-model="formData.createTimeSys"
          type="date"
          value-format="x"
          placeholder="选择系统创建时间"
        />
      </el-form-item>
      <el-form-item label="系统更新时间" prop="updateTimeSys">
        <el-date-picker
          v-model="formData.updateTimeSys"
          type="date"
          value-format="x"
          placeholder="选择系统更新时间"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { ProcessExecStdApi, ProcessExecStdVO } from '@/api/dataHub/businessGuide/processexecstd'

/** 流程执行规范 表单 */
defineOptions({ name: 'ProcessExecStdForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  processExecStdId: undefined,
  stdName: undefined,
  relatedProcessId: undefined,
  relatedProcessName: undefined,
  processSteps: undefined,
  deptCode: undefined,
  deptName: undefined,
  timeLimit: undefined,
  execRequirement: undefined,
  stdFileId: undefined,
  stdFilePath: undefined,
  stdStatus: undefined,
  createUser: undefined,
  updateUser: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined
})
const formRules = reactive({
  processExecStdId: [{ required: true, message: '规范ID不能为空', trigger: 'blur' }],
  stdName: [{ required: true, message: '规范名称不能为空', trigger: 'blur' }],
  relatedProcessId: [{ required: true, message: '关联流程ID不能为空', trigger: 'blur' }],
  relatedProcessName: [{ required: true, message: '关联流程名称不能为空', trigger: 'blur' }],
  processSteps: [{ required: true, message: '流程步骤不能为空', trigger: 'blur' }],
  deptCode: [{ required: true, message: '责任部门代码不能为空', trigger: 'blur' }],
  deptName: [{ required: true, message: '责任部门名称不能为空', trigger: 'blur' }],
  stdStatus: [{ required: true, message: '规范状态不能为空', trigger: 'blur' }],
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
      formData.value = await ProcessExecStdApi.getProcessExecStd(id)
    } finally {
      formLoading.value = false
    }
  }
}
defineExpose({ open }) // 提供 open 方法，用于打开弹窗

/** 提交表单 */
const emit = defineEmits(['success']) // 定义 success 事件，用于操作成功后的回调
const submitForm = async () => {
  // 校验表单
  await formRef.value.validate()
  // 提交请求
  formLoading.value = true
  try {
    const data = formData.value as unknown as ProcessExecStdVO
    if (formType.value === 'create') {
      await ProcessExecStdApi.createProcessExecStd(data)
      message.success(t('common.createSuccess'))
    } else {
      await ProcessExecStdApi.updateProcessExecStd(data)
      message.success(t('common.updateSuccess'))
    }
    dialogVisible.value = false
    // 发送操作成功的事件
    emit('success')
  } finally {
    formLoading.value = false
  }
}

/** 重置表单 */
const resetForm = () => {
  formData.value = {
    id: undefined,
    processExecStdId: undefined,
    stdName: undefined,
    relatedProcessId: undefined,
    relatedProcessName: undefined,
    processSteps: undefined,
    deptCode: undefined,
    deptName: undefined,
    timeLimit: undefined,
    execRequirement: undefined,
    stdFileId: undefined,
    stdFilePath: undefined,
    stdStatus: undefined,
    createUser: undefined,
    updateUser: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined
  }
  formRef.value?.resetFields()
}
</script>
