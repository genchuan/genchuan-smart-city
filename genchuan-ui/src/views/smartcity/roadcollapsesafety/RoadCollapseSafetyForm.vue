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
      <el-form-item label="监测点位置" prop="locationMonitoringPoints">
        <el-input v-model="formData.locationMonitoringPoints" placeholder="请输入监测点位置" />
      </el-form-item>
      <el-form-item label="路面状况" prop="roadCondition">
        <el-input v-model="formData.roadCondition" placeholder="请输入路面状况" />
      </el-form-item>
      <el-form-item label="地下空洞信息" prop="undergroundCavityInformation">
        <el-input v-model="formData.undergroundCavityInformation" placeholder="请输入地下空洞信息" />
      </el-form-item>
      <el-form-item label="土壤湿度" prop="soilMoisture">
        <el-input v-model="formData.soilMoisture" placeholder="请输入土壤湿度" />
      </el-form-item>
      <el-form-item label="土壤位移数据" prop="soilDisplacementData">
        <el-input v-model="formData.soilDisplacementData" placeholder="请输入土壤位移数据" />
      </el-form-item>
      <el-form-item label="周边建筑物沉降情况" prop="settlementSurroundingBuildings">
        <el-input v-model="formData.settlementSurroundingBuildings" placeholder="请输入周边建筑物沉降情况" />
      </el-form-item>
      <el-form-item label="地下水位变化" prop="changesGroundwaterLevel">
        <el-input v-model="formData.changesGroundwaterLevel" placeholder="请输入地下水位变化" />
      </el-form-item>
      <el-form-item label="降雨情况" prop="rainfallSituation">
        <el-input v-model="formData.rainfallSituation" placeholder="请输入降雨情况" />
      </el-form-item>
      <el-form-item label="交通流量" prop="trafficFlow">
        <el-input v-model="formData.trafficFlow" placeholder="请输入交通流量" />
      </el-form-item>
      <el-form-item label="重型车辆通行情况" prop="trafficHeavyVehicles">
        <el-input v-model="formData.trafficHeavyVehicles" placeholder="请输入重型车辆通行情况" />
      </el-form-item>
      <el-form-item label="预警级别" prop="warningLevel">
        <el-input v-model="formData.warningLevel" placeholder="请输入预警级别" />
      </el-form-item>
      <el-form-item label="预警时间" prop="warningTime">
        <el-date-picker
          v-model="formData.warningTime"
          type="date"
          value-format="x"
          placeholder="选择预警时间"
        />
      </el-form-item>
      <el-form-item label="专家评估意见" prop="expertEvaluationOpinions">
        <el-input v-model="formData.expertEvaluationOpinions" placeholder="请输入专家评估意见" />
      </el-form-item>
      <el-form-item label="历史塌陷记录" prop="historicalCollapseRecords">
        <el-input v-model="formData.historicalCollapseRecords" placeholder="请输入历史塌陷记录" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { RoadCollapseSafetyApi, RoadCollapseSafetyVO } from '@/api/smartcity/roadcollapsesafety'

/** 路面塌陷安全 表单 */
defineOptions({ name: 'RoadCollapseSafetyForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  monitoringPointName: undefined,
  locationMonitoringPoints: undefined,
  roadCondition: undefined,
  undergroundCavityInformation: undefined,
  soilMoisture: undefined,
  soilDisplacementData: undefined,
  settlementSurroundingBuildings: undefined,
  changesGroundwaterLevel: undefined,
  rainfallSituation: undefined,
  trafficFlow: undefined,
  trafficHeavyVehicles: undefined,
  warningLevel: undefined,
  warningTime: undefined,
  expertEvaluationOpinions: undefined,
  historicalCollapseRecords: undefined
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
      formData.value = await RoadCollapseSafetyApi.getRoadCollapseSafety(id)
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
    const data = formData.value as unknown as RoadCollapseSafetyVO
    if (formType.value === 'create') {
      await RoadCollapseSafetyApi.createRoadCollapseSafety(data)
      message.success(t('common.createSuccess'))
    } else {
      await RoadCollapseSafetyApi.updateRoadCollapseSafety(data)
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
    locationMonitoringPoints: undefined,
    roadCondition: undefined,
    undergroundCavityInformation: undefined,
    soilMoisture: undefined,
    soilDisplacementData: undefined,
    settlementSurroundingBuildings: undefined,
    changesGroundwaterLevel: undefined,
    rainfallSituation: undefined,
    trafficFlow: undefined,
    trafficHeavyVehicles: undefined,
    warningLevel: undefined,
    warningTime: undefined,
    expertEvaluationOpinions: undefined,
    historicalCollapseRecords: undefined
  }
  formRef.value?.resetFields()
}
</script>