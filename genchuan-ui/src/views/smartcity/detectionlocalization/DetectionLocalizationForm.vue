<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="设备名称" prop="equipmentName">
        <el-input v-model="formData.equipmentName" placeholder="请输入设备名称" />
      </el-form-item>
      <el-form-item label="设备编号" prop="equipmentNumber">
        <el-input v-model="formData.equipmentNumber" placeholder="请输入设备编号" />
      </el-form-item>
      <el-form-item label="设备位置" prop="deviceLocation">
        <el-input v-model="formData.deviceLocation" placeholder="请输入设备位置" />
      </el-form-item>
      <el-form-item label="故障代码" prop="faultCode">
        <el-input v-model="formData.faultCode" placeholder="请输入故障代码" />
      </el-form-item>
      <el-form-item label="故障现象" prop="faultPhenomenon">
        <el-input v-model="formData.faultPhenomenon" placeholder="请输入故障现象" />
      </el-form-item>
      <el-form-item label="故障时间" prop="mtbf">
        <el-date-picker
          v-model="formData.mtbf"
          type="date"
          value-format="x"
          placeholder="选择故障时间"
        />
      </el-form-item>
      <el-form-item label="故障类型" prop="faultType">
        <el-select v-model="formData.faultType" placeholder="请选择故障类型">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="传感器数据" prop="sensorData">
        <el-input v-model="formData.sensorData" placeholder="请输入传感器数据" />
      </el-form-item>
      <el-form-item label="故障影响范围" prop="scopeOfFaultImpact">
        <el-input v-model="formData.scopeOfFaultImpact" placeholder="请输入故障影响范围" />
      </el-form-item>
      <el-form-item label="故障严重程度" prop="severityOfMalfunction">
        <el-input v-model="formData.severityOfMalfunction" placeholder="请输入故障严重程度" />
      </el-form-item>
      <el-form-item label="定位信息" prop="locateInformation">
        <el-input v-model="formData.locateInformation" placeholder="请输入定位信息" />
      </el-form-item>
      <el-form-item label="人工确认情况" prop="manuallyConfirmTheSituation">
        <el-input v-model="formData.manuallyConfirmTheSituation" placeholder="请输入人工确认情况" />
      </el-form-item>
      <el-form-item label="维修时间" prop="maintenanceTime">
        <el-date-picker
          v-model="formData.maintenanceTime"
          type="date"
          value-format="x"
          placeholder="选择维修时间"
        />
      </el-form-item>
      <el-form-item label="维修结果" prop="repairResults">
        <el-input v-model="formData.repairResults" placeholder="请输入维修结果" />
      </el-form-item>
      <el-form-item label="恢复运行时间" prop="restoreRunningTime">
        <el-date-picker
          v-model="formData.restoreRunningTime"
          type="date"
          value-format="x"
          placeholder="选择恢复运行时间"
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
import { DetectionLocalizationApi, DetectionLocalizationVO } from '@/api/smartcity/detectionlocalization'

/** 故障检测与定位 表单 */
defineOptions({ name: 'DetectionLocalizationForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  equipmentName: undefined,
  equipmentNumber: undefined,
  deviceLocation: undefined,
  faultCode: undefined,
  faultPhenomenon: undefined,
  mtbf: undefined,
  faultType: undefined,
  sensorData: undefined,
  scopeOfFaultImpact: undefined,
  severityOfMalfunction: undefined,
  locateInformation: undefined,
  manuallyConfirmTheSituation: undefined,
  maintenanceTime: undefined,
  repairResults: undefined,
  restoreRunningTime: undefined
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
      formData.value = await DetectionLocalizationApi.getDetectionLocalization(id)
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
    const data = formData.value as unknown as DetectionLocalizationVO
    if (formType.value === 'create') {
      await DetectionLocalizationApi.createDetectionLocalization(data)
      message.success(t('common.createSuccess'))
    } else {
      await DetectionLocalizationApi.updateDetectionLocalization(data)
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
    equipmentName: undefined,
    equipmentNumber: undefined,
    deviceLocation: undefined,
    faultCode: undefined,
    faultPhenomenon: undefined,
    mtbf: undefined,
    faultType: undefined,
    sensorData: undefined,
    scopeOfFaultImpact: undefined,
    severityOfMalfunction: undefined,
    locateInformation: undefined,
    manuallyConfirmTheSituation: undefined,
    maintenanceTime: undefined,
    repairResults: undefined,
    restoreRunningTime: undefined
  }
  formRef.value?.resetFields()
}
</script>