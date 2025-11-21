<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="配置ID" prop="flowCfgId">
        <el-input v-model="formData.flowCfgId" placeholder="请输入配置ID" />
      </el-form-item>
      <el-form-item label="配置名称" prop="flowCfgName">
        <el-input v-model="formData.flowCfgName" placeholder="请输入配置名称" />
      </el-form-item>
      <el-form-item label="流程步骤" prop="flowSteps">
        <el-input v-model="formData.flowSteps" placeholder="请输入流程步骤" />
      </el-form-item>
      <el-form-item label="创建人账号" prop="createUser">
        <el-input v-model="formData.createUser" placeholder="请输入创建人账号" />
      </el-form-item>
      <el-form-item label="更新人账号" prop="updateUser">
        <el-input v-model="formData.updateUser" placeholder="请输入更新人账号" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { CoopFlowCfgApi, CoopFlowCfgVO } from '@/api/dataHub/commandAndCoordination/coopflowcfg'

/** 联动流程配置 表单 */
defineOptions({ name: 'CoopFlowCfgForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  flowCfgId: undefined,
  flowCfgName: undefined,
  flowSteps: undefined,
  createUser: undefined,
  updateUser: undefined,
})
const formRules = reactive({
  flowCfgId: [{ required: true, message: '配置ID不能为空', trigger: 'blur' }],
  flowCfgName: [{ required: true, message: '配置名称不能为空', trigger: 'blur' }],
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
      formData.value = await CoopFlowCfgApi.getCoopFlowCfg(id)
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
    const data = formData.value as unknown as CoopFlowCfgVO
    if (formType.value === 'create') {
      await CoopFlowCfgApi.createCoopFlowCfg(data)
      message.success(t('common.createSuccess'))
    } else {
      await CoopFlowCfgApi.updateCoopFlowCfg(data)
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
    flowCfgId: undefined,
    flowCfgName: undefined,
    flowSteps: undefined,
    createUser: undefined,
    updateUser: undefined,
  }
  formRef.value?.resetFields()
}
</script>
