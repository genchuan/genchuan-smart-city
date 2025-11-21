<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="养护人员编号" prop="maintenancePersonnelNumber">
        <el-input v-model="formData.maintenancePersonnelNumber" placeholder="请输入养护人员编号" />
      </el-form-item>
      <el-form-item label="养护任务编号" prop="maintenanceTaskNumber">
        <el-input v-model="formData.maintenanceTaskNumber" placeholder="请输入养护任务编号" />
      </el-form-item>
      <el-form-item label="考核周期" prop="assessmentCycle">
        <el-input v-model="formData.assessmentCycle" placeholder="请输入考核周期" />
      </el-form-item>
      <el-form-item label="考核得分" prop="assessmentScore">
        <el-input v-model="formData.assessmentScore" placeholder="请输入考核得分" />
      </el-form-item>
      <el-form-item label="考核等级" prop="assessmentLevel">
        <el-input v-model="formData.assessmentLevel" placeholder="请输入考核等级" />
      </el-form-item>
      <el-form-item label="评价意见" prop="evaluationOpinion">
        <el-input v-model="formData.evaluationOpinion" placeholder="请输入评价意见" />
      </el-form-item>
      <el-form-item label="改进建议" prop="improvementSuggestions">
        <el-input v-model="formData.improvementSuggestions" placeholder="请输入改进建议" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { AssessmentAndEvaluationApi, AssessmentAndEvaluationVO } from '@/api/smartcity/assessmentandevaluation'

/** 养护考核评价 表单 */
defineOptions({ name: 'AssessmentAndEvaluationForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  maintenancePersonnelNumber: undefined,
  maintenanceTaskNumber: undefined,
  assessmentCycle: undefined,
  assessmentScore: undefined,
  assessmentLevel: undefined,
  evaluationOpinion: undefined,
  improvementSuggestions: undefined
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
      formData.value = await AssessmentAndEvaluationApi.getAssessmentAndEvaluation(id)
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
    const data = formData.value as unknown as AssessmentAndEvaluationVO
    if (formType.value === 'create') {
      await AssessmentAndEvaluationApi.createAssessmentAndEvaluation(data)
      message.success(t('common.createSuccess'))
    } else {
      await AssessmentAndEvaluationApi.updateAssessmentAndEvaluation(data)
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
    maintenancePersonnelNumber: undefined,
    maintenanceTaskNumber: undefined,
    assessmentCycle: undefined,
    assessmentScore: undefined,
    assessmentLevel: undefined,
    evaluationOpinion: undefined,
    improvementSuggestions: undefined
  }
  formRef.value?.resetFields()
}
</script>