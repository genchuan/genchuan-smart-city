<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="指令ID" prop="commandId">
        <el-input v-model="formData.commandId" placeholder="请输入指令ID" />
      </el-form-item>
      <el-form-item label="指令编号" prop="commandNo">
        <el-input v-model="formData.commandNo" placeholder="请输入指令编号" />
      </el-form-item>
      <el-form-item label="关联方案ID" prop="planId">
        <el-input v-model="formData.planId" placeholder="请输入关联方案ID" />
      </el-form-item>
      <el-form-item label="方案编号" prop="planNo">
        <el-input v-model="formData.planNo" placeholder="请输入方案编号" />
      </el-form-item>
      <el-form-item label="执行单位ID" prop="execUnitId">
        <el-input v-model="formData.execUnitId" placeholder="请输入执行单位ID" />
      </el-form-item>
      <el-form-item label="执行单位名称" prop="execUnitName">
        <el-input v-model="formData.execUnitName" placeholder="请输入执行单位名称" />
      </el-form-item>
      <el-form-item label="任务内容" prop="taskContent">
        <Editor v-model="formData.taskContent" height="150px" />
      </el-form-item>
      <el-form-item label="执行要求" prop="execRequirement">
        <el-input v-model="formData.execRequirement" placeholder="请输入执行要求" />
      </el-form-item>
      <el-form-item label="指令状态" prop="commandStatus">
        <el-radio-group v-model="formData.commandStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="下发人ID" prop="issueUserId">
        <el-input v-model="formData.issueUserId" placeholder="请输入下发人ID" />
      </el-form-item>
      <el-form-item label="下发人姓名" prop="issueUserName">
        <el-input v-model="formData.issueUserName" placeholder="请输入下发人姓名" />
      </el-form-item>
      <el-form-item label="下发时间" prop="issueTime">
        <el-date-picker
          v-model="formData.issueTime"
          type="date"
          value-format="x"
          placeholder="选择下发时间"
        />
      </el-form-item>
      <el-form-item label="接收人ID" prop="receiveUserId">
        <el-input v-model="formData.receiveUserId" placeholder="请输入接收人ID" />
      </el-form-item>
      <el-form-item label="接收人姓名" prop="receiveUserName">
        <el-input v-model="formData.receiveUserName" placeholder="请输入接收人姓名" />
      </el-form-item>
      <el-form-item label="接收时间" prop="receiveTime">
        <el-date-picker
          v-model="formData.receiveTime"
          type="date"
          value-format="x"
          placeholder="选择接收时间"
        />
      </el-form-item>
      <el-form-item label="执行反馈" prop="execFeedback">
        <el-input v-model="formData.execFeedback" placeholder="请输入执行反馈" />
      </el-form-item>
      <el-form-item label="完成时间" prop="completeTime">
        <el-date-picker
          v-model="formData.completeTime"
          type="date"
          value-format="x"
          placeholder="选择完成时间"
        />
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
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { DispatchCommandApi, DispatchCommandVO } from '@/api/dataHub/commandAndCoordination/dispatchcommand'

/** 调度指令 表单 */
defineOptions({ name: 'DispatchCommandForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  commandId: undefined,
  commandNo: undefined,
  planId: undefined,
  planNo: undefined,
  execUnitId: undefined,
  execUnitName: undefined,
  taskContent: undefined,
  execRequirement: undefined,
  commandStatus: undefined,
  issueUserId: undefined,
  issueUserName: undefined,
  issueTime: undefined,
  receiveUserId: undefined,
  receiveUserName: undefined,
  receiveTime: undefined,
  execFeedback: undefined,
  completeTime: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
})
const formRules = reactive({
  commandId: [{ required: true, message: '指令ID不能为空', trigger: 'blur' }],
  commandNo: [{ required: true, message: '指令编号不能为空', trigger: 'blur' }],
  planId: [{ required: true, message: '关联方案ID不能为空', trigger: 'blur' }],
  planNo: [{ required: true, message: '方案编号不能为空', trigger: 'blur' }],
  execUnitId: [{ required: true, message: '执行单位ID不能为空', trigger: 'blur' }],
  execUnitName: [{ required: true, message: '执行单位名称不能为空', trigger: 'blur' }],
  taskContent: [{ required: true, message: '任务内容不能为空', trigger: 'blur' }],
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
      formData.value = await DispatchCommandApi.getDispatchCommand(id)
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
    const data = formData.value as unknown as DispatchCommandVO
    if (formType.value === 'create') {
      await DispatchCommandApi.createDispatchCommand(data)
      message.success(t('common.createSuccess'))
    } else {
      await DispatchCommandApi.updateDispatchCommand(data)
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
    commandId: undefined,
    commandNo: undefined,
    planId: undefined,
    planNo: undefined,
    execUnitId: undefined,
    execUnitName: undefined,
    taskContent: undefined,
    execRequirement: undefined,
    commandStatus: undefined,
    issueUserId: undefined,
    issueUserName: undefined,
    issueTime: undefined,
    receiveUserId: undefined,
    receiveUserName: undefined,
    receiveTime: undefined,
    execFeedback: undefined,
    completeTime: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined,
  }
  formRef.value?.resetFields()
}
</script>
