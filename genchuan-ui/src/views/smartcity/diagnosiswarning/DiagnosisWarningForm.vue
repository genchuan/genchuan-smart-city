<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="故障设备名称" prop="nameFaultyEquipment">
        <el-input v-model="formData.nameFaultyEquipment" placeholder="请输入故障设备名称" />
      </el-form-item>
      <el-form-item label="故障设备编号" prop="faultEquipmentNumber">
        <el-input v-model="formData.faultEquipmentNumber" placeholder="请输入故障设备编号" />
      </el-form-item>
      <el-form-item label="故障发生时间" prop="timeFailure">
        <el-input v-model="formData.timeFailure" placeholder="请输入故障发生时间" />
      </el-form-item>
      <el-form-item label="故障现象描述" prop="descriptionSymptoms">
        <el-input v-model="formData.descriptionSymptoms" placeholder="请输入故障现象描述" />
      </el-form-item>
      <el-form-item label="故障可能原因分析" prop="analysisPossibleMalfunction">
        <el-input v-model="formData.analysisPossibleMalfunction" placeholder="请输入故障可能原因分析" />
      </el-form-item>
      <el-form-item label="诊断方法与依据" prop="diagnosticMethodsBasis">
        <el-input v-model="formData.diagnosticMethodsBasis" placeholder="请输入诊断方法与依据" />
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
      <el-form-item label="预警信息内容" prop="warningInformationContent">
        <el-input v-model="formData.warningInformationContent" placeholder="请输入预警信息内容" />
      </el-form-item>
      <el-form-item label="专家评估意见" prop="expertEvaluationOpinions">
        <el-input v-model="formData.expertEvaluationOpinions" placeholder="请输入专家评估意见" />
      </el-form-item>
      <el-form-item label="系统恢复时间" prop="systemRecoveryTime">
        <el-date-picker
          v-model="formData.systemRecoveryTime"
          type="date"
          value-format="x"
          placeholder="选择系统恢复时间"
        />
      </el-form-item>
      <el-form-item label="故障对业务的影响程度" prop="theDegreeBusiness">
        <el-input v-model="formData.theDegreeBusiness" placeholder="请输入故障对业务的影响程度" />
      </el-form-item>
      <el-form-item label="后续预防措施" prop="subsequentPreventiveMeasures">
        <el-input v-model="formData.subsequentPreventiveMeasures" placeholder="请输入后续预防措施" />
      </el-form-item>
      <el-form-item label="备用设备投入情况" prop="backupDeploymentStatus">
        <el-radio-group v-model="formData.backupDeploymentStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { DiagnosisWarningApi, DiagnosisWarningVO } from '@/api/smartcity/diagnosiswarning'

/** 故障诊断和预警 表单 */
defineOptions({ name: 'DiagnosisWarningForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  nameFaultyEquipment: undefined,
  faultEquipmentNumber: undefined,
  timeFailure: undefined,
  descriptionSymptoms: undefined,
  analysisPossibleMalfunction: undefined,
  diagnosticMethodsBasis: undefined,
  warningLevel: undefined,
  warningTime: undefined,
  warningInformationContent: undefined,
  expertEvaluationOpinions: undefined,
  systemRecoveryTime: undefined,
  theDegreeBusiness: undefined,
  subsequentPreventiveMeasures: undefined,
  backupDeploymentStatus: undefined
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
      formData.value = await DiagnosisWarningApi.getDiagnosisWarning(id)
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
    const data = formData.value as unknown as DiagnosisWarningVO
    if (formType.value === 'create') {
      await DiagnosisWarningApi.createDiagnosisWarning(data)
      message.success(t('common.createSuccess'))
    } else {
      await DiagnosisWarningApi.updateDiagnosisWarning(data)
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
    nameFaultyEquipment: undefined,
    faultEquipmentNumber: undefined,
    timeFailure: undefined,
    descriptionSymptoms: undefined,
    analysisPossibleMalfunction: undefined,
    diagnosticMethodsBasis: undefined,
    warningLevel: undefined,
    warningTime: undefined,
    warningInformationContent: undefined,
    expertEvaluationOpinions: undefined,
    systemRecoveryTime: undefined,
    theDegreeBusiness: undefined,
    subsequentPreventiveMeasures: undefined,
    backupDeploymentStatus: undefined
  }
  formRef.value?.resetFields()
}
</script>