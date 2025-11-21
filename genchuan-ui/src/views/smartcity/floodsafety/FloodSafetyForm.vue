<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="监测点名称" prop="monitoringPointName">
        <el-input v-model="formData.monitoringPointName" placeholder="请输入监测点名称" />
      </el-form-item>
      <el-form-item label="监测点位置" prop="locationOfMonitoringPoints">
        <el-input v-model="formData.locationOfMonitoringPoints" placeholder="请输入监测点位置" />
      </el-form-item>
      <el-form-item label="积水深度" prop="depthOfAccumulatedWater">
        <el-input v-model="formData.depthOfAccumulatedWater" placeholder="请输入积水深度" />
      </el-form-item>
      <el-form-item label="积水面积" prop="catchmentArea">
        <el-input v-model="formData.catchmentArea" placeholder="请输入积水面积" />
      </el-form-item>
      <el-form-item label="水流速度" prop="waterFlowVelocity">
        <el-input v-model="formData.waterFlowVelocity" placeholder="请输入水流速度" />
      </el-form-item>
      <el-form-item label="水位变化趋势" prop="trendOfWaterLevelChanges">
        <el-input v-model="formData.trendOfWaterLevelChanges" placeholder="请输入水位变化趋势" />
      </el-form-item>
      <el-form-item label="降雨量" prop="rainfall">
        <el-input v-model="formData.rainfall" placeholder="请输入降雨量" />
      </el-form-item>
      <el-form-item label="降雨强度" prop="rainfallIntensity">
        <el-input v-model="formData.rainfallIntensity" placeholder="请输入降雨强度" />
      </el-form-item>
      <el-form-item label="管道堵塞情况" prop="pipelineBlockageSituation">
        <el-input v-model="formData.pipelineBlockageSituation" placeholder="请输入管道堵塞情况" />
      </el-form-item>
      <el-form-item label="泵站运行状态" prop="pumpStationOperationStatus">
        <el-radio-group v-model="formData.pumpStationOperationStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="设备故障信息" prop="equipmentFaultInformation">
        <el-input v-model="formData.equipmentFaultInformation" placeholder="请输入设备故障信息" />
      </el-form-item>
      <el-form-item label="预警级别" prop="warningLevel">
        <el-input v-model="formData.warningLevel" placeholder="请输入预警级别" />
      </el-form-item>
      <el-form-item label="预警时间
" prop="warningTime">
        <el-date-picker
          v-model="formData.warningTime"
          type="date"
          value-format="x"
          placeholder="选择预警时间
"
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
import { FloodSafetyApi, FloodSafetyVO } from '@/api/smartcity/floodsafety'

/** 内涝安全 表单 */
defineOptions({ name: 'FloodSafetyForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  monitoringPointName: undefined,
  locationOfMonitoringPoints: undefined,
  depthOfAccumulatedWater: undefined,
  catchmentArea: undefined,
  waterFlowVelocity: undefined,
  trendOfWaterLevelChanges: undefined,
  rainfall: undefined,
  rainfallIntensity: undefined,
  pipelineBlockageSituation: undefined,
  pumpStationOperationStatus: undefined,
  equipmentFaultInformation: undefined,
  warningLevel: undefined,
  warningTime: undefined
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
      formData.value = await FloodSafetyApi.getFloodSafety(id)
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
    const data = formData.value as unknown as FloodSafetyVO
    if (formType.value === 'create') {
      await FloodSafetyApi.createFloodSafety(data)
      message.success(t('common.createSuccess'))
    } else {
      await FloodSafetyApi.updateFloodSafety(data)
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
    monitoringPointName: undefined,
    locationOfMonitoringPoints: undefined,
    depthOfAccumulatedWater: undefined,
    catchmentArea: undefined,
    waterFlowVelocity: undefined,
    trendOfWaterLevelChanges: undefined,
    rainfall: undefined,
    rainfallIntensity: undefined,
    pipelineBlockageSituation: undefined,
    pumpStationOperationStatus: undefined,
    equipmentFaultInformation: undefined,
    warningLevel: undefined,
    warningTime: undefined
  }
  formRef.value?.resetFields()
}
</script>