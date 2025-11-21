<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="水位超限预警" prop="waterLevelOverlimitWarning">
        <el-input v-model="formData.waterLevelOverlimitWarning" placeholder="请输入水位超限预警" />
      </el-form-item>
      <el-form-item label="流量异常预警" prop="flowAbnormalityWarning">
        <el-input v-model="formData.flowAbnormalityWarning" placeholder="请输入流量异常预警" />
      </el-form-item>
      <el-form-item label="水质超标预警" prop="waterQualityExceedanceWarning">
        <el-input v-model="formData.waterQualityExceedanceWarning" placeholder="请输入水质超标预警" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { MonitoringRuleApi, MonitoringRuleVO } from '@/api/smartcity/monitoringrule'

/** 监测项规则设置 表单 */
defineOptions({ name: 'MonitoringRuleForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  waterLevelOverlimitWarning: undefined,
  flowAbnormalityWarning: undefined,
  waterQualityExceedanceWarning: undefined
})
const formRules = reactive({
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
      formData.value = await MonitoringRuleApi.getMonitoringRule(id)
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
    const data = formData.value as unknown as MonitoringRuleVO
    if (formType.value === 'create') {
      await MonitoringRuleApi.createMonitoringRule(data)
      message.success(t('common.createSuccess'))
    } else {
      await MonitoringRuleApi.updateMonitoringRule(data)
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
    waterLevelOverlimitWarning: undefined,
    flowAbnormalityWarning: undefined,
    waterQualityExceedanceWarning: undefined
  }
  formRef.value?.resetFields()
}
</script>