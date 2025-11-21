<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="巡查区域" prop="patrolArea">
        <el-input v-model="formData.patrolArea" placeholder="请输入巡查区域" />
      </el-form-item>
      <el-form-item label="巡查人员" prop="patrolPersonnel">
        <el-input v-model="formData.patrolPersonnel" placeholder="请输入巡查人员" />
      </el-form-item>
      <el-form-item label="巡查任务完成率" prop="completionInspectionTasks">
        <el-input v-model="formData.completionInspectionTasks" placeholder="请输入巡查任务完成率" />
      </el-form-item>
      <el-form-item label="平均巡查时长" prop="averagePatrolDuration">
        <el-input v-model="formData.averagePatrolDuration" placeholder="请输入平均巡查时长" />
      </el-form-item>
      <el-form-item label="问题发现数量" prop="numberProblemDiscoveries">
        <el-input v-model="formData.numberProblemDiscoveries" placeholder="请输入问题发现数量" />
      </el-form-item>
      <el-form-item label="不同类型问题分布" prop="distributionProblems">
        <el-input v-model="formData.distributionProblems" placeholder="请输入不同类型问题分布" />
      </el-form-item>
      <el-form-item label="问题解决率" prop="problemSolvingRate">
        <el-input v-model="formData.problemSolvingRate" placeholder="请输入问题解决率" />
      </el-form-item>
      <el-form-item label="重复问题发生率" prop="repetitiveProblemRate">
        <el-input v-model="formData.repetitiveProblemRate" placeholder="请输入重复问题发生率" />
      </el-form-item>
      <el-form-item label="风险等级评估" prop="riskLevelAssessment">
        <el-input v-model="formData.riskLevelAssessment" placeholder="请输入风险等级评估" />
      </el-form-item>
      <el-form-item label="建议与改进措施" prop="suggestionsMeasures">
        <el-input v-model="formData.suggestionsMeasures" placeholder="请输入建议与改进措施" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { InspectionStatisticsApi, InspectionStatisticsVO } from '@/api/smartcity/inspectionstatistics'

/** 巡查分析统计 表单 */
defineOptions({ name: 'InspectionStatisticsForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  patrolArea: undefined,
  patrolPersonnel: undefined,
  completionInspectionTasks: undefined,
  averagePatrolDuration: undefined,
  numberProblemDiscoveries: undefined,
  distributionProblems: undefined,
  problemSolvingRate: undefined,
  repetitiveProblemRate: undefined,
  riskLevelAssessment: undefined,
  suggestionsMeasures: undefined
})
const formRules = reactive({
})
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
      formData.value = await InspectionStatisticsApi.getInspectionStatistics(id)
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
    const data = formData.value as unknown as InspectionStatisticsVO
    if (formType.value === 'create') {
      await InspectionStatisticsApi.createInspectionStatistics(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectionStatisticsApi.updateInspectionStatistics(data)
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
    patrolArea: undefined,
    patrolPersonnel: undefined,
    completionInspectionTasks: undefined,
    averagePatrolDuration: undefined,
    numberProblemDiscoveries: undefined,
    distributionProblems: undefined,
    problemSolvingRate: undefined,
    repetitiveProblemRate: undefined,
    riskLevelAssessment: undefined,
    suggestionsMeasures: undefined
  }
  formRef.value?.resetFields()
}
</script>
