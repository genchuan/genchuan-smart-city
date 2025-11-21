<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="140px"
      v-loading="formLoading"
    >
      <el-form-item label="统计周期" prop="statisticalCycle">
        <el-input v-model="formData.statisticalCycle" placeholder="请输入统计周期" />
      </el-form-item>
      <el-form-item label="事件类型" prop="eventType">
        <el-select v-model="formData.eventType" placeholder="请选择事件类型">
          <el-option label="故障事件" value="fault" />
          <el-option label="维护事件" value="maintenance" />
          <el-option label="异常运行事件" value="abnormal_operation" />
          <el-option label="预警事件" value="early_warning" />
          <el-option label="更换事件" value="replacement" />
          <el-option label="巡检发现事件" value="inspection_found" />
          <el-option label="报废事件" value="scrap" />
          <el-option label="其他事件" value="other" />
        </el-select>
      </el-form-item>
      <el-form-item label="事件数量" prop="numberOfEvents">
        <el-input v-model="formData.numberOfEvents" placeholder="请输入事件数量" />
      </el-form-item>
      <el-form-item label="发生地点" prop="place">
        <el-input v-model="formData.place" placeholder="请输入发生地点" />
      </el-form-item>
      <el-form-item label="高发时段" prop="highIncidencePeriod">
        <el-input v-model="formData.highIncidencePeriod" placeholder="请输入高发时段" />
      </el-form-item>
      <el-form-item label="平均处理时长" prop="aht">
        <el-input v-model="formData.aht" placeholder="请输入平均处理时长" />
      </el-form-item>
      <el-form-item label="处理成功率" prop="processingSuccessRate">
        <el-input v-model="formData.processingSuccessRate" placeholder="请输入处理成功率" />
      </el-form-item>
      <el-form-item label="严重程度分布" prop="severityDistribution">
        <el-input v-model="formData.severityDistribution" placeholder="请输入严重程度分布" />
      </el-form-item>
      <el-form-item label="涉及部门" prop="involvedDepartments">
        <el-input v-model="formData.involvedDepartments" placeholder="请输入涉及部门" />
      </el-form-item>
      <el-form-item label="责任主体" prop="responsibleParty">
        <el-input v-model="formData.responsibleParty" placeholder="请输入责任主体" />
      </el-form-item>
      <el-form-item label="处理方式占比" prop="proportionOfProcessing">
        <el-input v-model="formData.proportionOfProcessing" placeholder="请输入处理方式占比" />
      </el-form-item>
      <el-form-item label="资源消耗统计" prop="resourceConsumption">
        <el-input v-model="formData.resourceConsumption" placeholder="请输入资源消耗统计" />
      </el-form-item>
      <el-form-item label="不同渠道上报占比" prop="proportionOfReporting">
        <el-input v-model="formData.proportionOfReporting" placeholder="请输入不同渠道上报占比" />
      </el-form-item>
      <el-form-item label="重复事件数量" prop="numberOfRepeatedEvents">
        <el-input v-model="formData.numberOfRepeatedEvents" placeholder="请输入重复事件数量" />
      </el-form-item>
      <el-form-item label="时间序列趋势" prop="timeSeriesTrend">
        <el-input v-model="formData.timeSeriesTrend" placeholder="请输入时间序列趋势" />
      </el-form-item>
      <el-form-item label="关联部件故障次数" prop="numberOfRelated">
        <el-input v-model="formData.numberOfRelated" placeholder="请输入关联部件故障次数" />
      </el-form-item>
      <el-form-item label="公众反馈满意度评分" prop="publicFeedbackSatisfaction">
        <el-input
          v-model="formData.publicFeedbackSatisfaction"
          placeholder="请输入公众反馈满意度评分"
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
import {
  EventStatisticalAnalysisApi,
  EventStatisticalAnalysisVO
} from '@/api/smartcity/eventstatisticalanalysis'

/** 事件统计分析 表单 */
defineOptions({ name: 'EventStatisticalAnalysisForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  statisticalCycle: undefined,
  eventType: undefined,
  numberOfEvents: undefined,
  place: undefined,
  highIncidencePeriod: undefined,
  aht: undefined,
  processingSuccessRate: undefined,
  severityDistribution: undefined,
  involvedDepartments: undefined,
  responsibleParty: undefined,
  proportionOfProcessing: undefined,
  resourceConsumption: undefined,
  proportionOfReporting: undefined,
  numberOfRepeatedEvents: undefined,
  timeSeriesTrend: undefined,
  numberOfRelated: undefined,
  publicFeedbackSatisfaction: undefined
})
const formRules = reactive({})
const formRef = ref() // 表单 ref

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
      formData.value = await EventStatisticalAnalysisApi.getEventStatisticalAnalysis(id)
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
    const data = formData.value as unknown as EventStatisticalAnalysisVO
    if (formType.value === 'create') {
      await EventStatisticalAnalysisApi.createEventStatisticalAnalysis(data)
      message.success(t('common.createSuccess'))
    } else {
      await EventStatisticalAnalysisApi.updateEventStatisticalAnalysis(data)
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
    statisticalCycle: undefined,
    eventType: undefined,
    numberOfEvents: undefined,
    place: undefined,
    highIncidencePeriod: undefined,
    aht: undefined,
    processingSuccessRate: undefined,
    severityDistribution: undefined,
    involvedDepartments: undefined,
    responsibleParty: undefined,
    proportionOfProcessing: undefined,
    resourceConsumption: undefined,
    proportionOfReporting: undefined,
    numberOfRepeatedEvents: undefined,
    timeSeriesTrend: undefined,
    numberOfRelated: undefined,
    publicFeedbackSatisfaction: undefined
  }
  formRef.value?.resetFields()
}
</script>
