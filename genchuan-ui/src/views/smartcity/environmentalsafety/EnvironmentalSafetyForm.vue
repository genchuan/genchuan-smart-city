<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="设施名称" prop="facilityName">
        <el-input v-model="formData.facilityName" placeholder="请输入设施名称" />
      </el-form-item>
      <el-form-item label="设施类型" prop="facilityType">
        <el-select v-model="formData.facilityType" placeholder="请选择设施类型">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="设施位置" prop="facilityLocation">
        <el-input v-model="formData.facilityLocation" placeholder="请输入设施位置" />
      </el-form-item>
      <el-form-item label="设施编号" prop="facilityNumber">
        <el-input v-model="formData.facilityNumber" placeholder="请输入设施编号" />
      </el-form-item>
      <el-form-item label="运行状态" prop="runningState">
        <el-input v-model="formData.runningState" placeholder="请输入运行状态" />
      </el-form-item>
      <el-form-item label="故障代码" prop="faultCode">
        <el-input v-model="formData.faultCode" placeholder="请输入故障代码" />
      </el-form-item>
      <el-form-item label="维修记录" prop="maintenanceRecord">
        <el-input v-model="formData.maintenanceRecord" placeholder="请输入维修记录" />
      </el-form-item>
      <el-form-item label="维修时间" prop="maintenanceTime">
        <el-date-picker
          v-model="formData.maintenanceTime"
          type="date"
          value-format="x"
          placeholder="选择维修时间"
        />
      </el-form-item>
      <el-form-item label="异味浓度" prop="odorConcentration">
        <el-input v-model="formData.odorConcentration" placeholder="请输入异味浓度" />
      </el-form-item>
      <el-form-item label="有害气体含量" prop="harmfulGasContent">
        <el-input v-model="formData.harmfulGasContent" type="textarea" placeholder="请输入有害气体含量" />
      </el-form-item>
      <el-form-item label="消毒记录" prop="disinfectionRecord">
        <el-input v-model="formData.disinfectionRecord" placeholder="请输入消毒记录" />
      </el-form-item>
      <el-form-item label="蝇虫密度" prop="flyAndInsectDensity">
        <el-input v-model="formData.flyAndInsectDensity" placeholder="请输入蝇虫密度" />
      </el-form-item>
      <el-form-item label="设施使用频率" prop="facilityUsageFrequency">
        <el-input v-model="formData.facilityUsageFrequency" placeholder="请输入设施使用频率" />
      </el-form-item>
      <el-form-item label="投诉记录" prop="complaintRecord">
        <el-input v-model="formData.complaintRecord" placeholder="请输入投诉记录" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { EnvironmentalSafetyApi, EnvironmentalSafetyVO } from '@/api/smartcity/environmentalsafety'

/** 环卫设施安全 表单 */
defineOptions({ name: 'EnvironmentalSafetyForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  facilityName: undefined,
  facilityType: undefined,
  facilityLocation: undefined,
  facilityNumber: undefined,
  runningState: undefined,
  faultCode: undefined,
  maintenanceRecord: undefined,
  maintenanceTime: undefined,
  odorConcentration: undefined,
  harmfulGasContent: undefined,
  disinfectionRecord: undefined,
  flyAndInsectDensity: undefined,
  facilityUsageFrequency: undefined,
  complaintRecord: undefined
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
      formData.value = await EnvironmentalSafetyApi.getEnvironmentalSafety(id)
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
    const data = formData.value as unknown as EnvironmentalSafetyVO
    if (formType.value === 'create') {
      await EnvironmentalSafetyApi.createEnvironmentalSafety(data)
      message.success(t('common.createSuccess'))
    } else {
      await EnvironmentalSafetyApi.updateEnvironmentalSafety(data)
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
    facilityName: undefined,
    facilityType: undefined,
    facilityLocation: undefined,
    facilityNumber: undefined,
    runningState: undefined,
    faultCode: undefined,
    maintenanceRecord: undefined,
    maintenanceTime: undefined,
    odorConcentration: undefined,
    harmfulGasContent: undefined,
    disinfectionRecord: undefined,
    flyAndInsectDensity: undefined,
    facilityUsageFrequency: undefined,
    complaintRecord: undefined
  }
  formRef.value?.resetFields()
}
</script>