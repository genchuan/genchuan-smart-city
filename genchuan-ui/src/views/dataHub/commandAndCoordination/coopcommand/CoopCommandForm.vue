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
      <el-form-item label="指令名称" prop="commandName">
        <el-input v-model="formData.commandName" placeholder="请输入指令名称" />
      </el-form-item>
      <el-form-item label="关联流程ID" prop="flowId">
        <el-input v-model="formData.flowId" placeholder="请输入关联流程ID" />
      </el-form-item>
      <el-form-item label="关联事件编码" prop="evtCode">
        <el-input v-model="formData.evtCode" placeholder="请输入关联事件编码" />
      </el-form-item>
      <el-form-item label="联动单位ID" prop="unitId">
        <el-input v-model="formData.unitId" placeholder="请输入联动单位ID" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { CoopCommandApi, CoopCommandVO } from '@/api/dataHub/commandAndCoordination/coopcommand'

/** 联动指令 表单 */
defineOptions({ name: 'CoopCommandForm' })

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
  commandName: undefined,
  flowId: undefined,
  evtCode: undefined,
  unitId: undefined,
})
const formRules = reactive({
  commandId: [{ required: true, message: '指令ID不能为空', trigger: 'blur' }],
  commandNo: [{ required: true, message: '指令编号不能为空', trigger: 'blur' }],
  commandName: [{ required: true, message: '指令名称不能为空', trigger: 'blur' }],
  unitId: [{ required: true, message: '联动单位ID不能为空', trigger: 'blur' }],
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
      formData.value = await CoopCommandApi.getCoopCommand(id)
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
    const data = formData.value as unknown as CoopCommandVO
    if (formType.value === 'create') {
      await CoopCommandApi.createCoopCommand(data)
      message.success(t('common.createSuccess'))
    } else {
      await CoopCommandApi.updateCoopCommand(data)
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
    commandName: undefined,
    flowId: undefined,
    evtCode: undefined,
    unitId: undefined,
  }
  formRef.value?.resetFields()
}
</script>
