<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
    >
      <el-form-item label="监督编号" prop="supervisionId">
        <el-input v-model="formData.supervisionId" placeholder="请输入监督编号" />
      </el-form-item>
      <el-form-item label="执法事件编号" prop="eventNumber">
        <el-input v-model="formData.eventNumber" placeholder="请输入执法事件编号" />
      </el-form-item>
      <el-form-item label="执法人员" prop="officials">
        <el-input v-model="formData.officials" placeholder="请输入执法人员" />
      </el-form-item>
      <el-form-item label="监督人员" prop="personnel">
        <el-input v-model="formData.personnel" placeholder="请输入监督人员" />
      </el-form-item>
      <el-form-item label="监督时间" prop="time">
        <el-date-picker
          v-model="formData.time"
          type="date"
          value-format="x"
          placeholder="选择监督时间"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="监督类型" prop="method">
        <el-select
          v-model="formData.method"
          placeholder="请选择监督类型"
          clearable
        >
          <el-option value="internal_recorder" label="执法记录仪核查" />
          <el-option value="internal_replay" label="执法过程回放" />
          <el-option value="internal_document" label="执法文书审查" />
          <el-option value="internal_case_review" label="执法案件评查" />
          <el-option value="internal_performance" label="执法绩效考核" />
          <el-option value="external_complaint" label="投诉举报核查" />
          <el-option value="external_satisfaction" label="满意度调查" />
          <el-option value="external_information" label="信息公开检查" />
        </el-select>
      </el-form-item>
      <el-form-item label="监督证据" prop="integrityCollection">
        <el-select
          v-model="formData.integrityCollection"
          placeholder="请选择监督证据类型"
          clearable
          @change="handleEvidenceChange"
        >
          <el-option value="recorder_video" label="执法记录仪视频" />
          <el-option value="law_document" label="执法文书" />
          <el-option value="complaint_evidence" label="投诉举报证据" />
          <el-option value="satisfaction_survey" label="满意度调查问卷" />
          <el-option value="information_public" label="信息公开材料" />
          <el-option value="no_evidence" label="无监督证据" />
        </el-select>
        <el-input
          v-if="formData.integrityCollection && formData.integrityCollection !== 'no_evidence'"
          v-model="formData.evidenceDetail"
          placeholder="请补充证据详情（如设备编号、上传状态等）"
          class="mt-10px"
        />
      </el-form-item>
      <el-form-item label="群众满意度" prop="satisfactionInvolved">
        <el-input v-model="formData.satisfactionInvolved" placeholder="请输入当事人满意度" />
      </el-form-item>
      <el-form-item label="监督结果" prop="resultEvaluation">
        <el-select
          v-model="formData.resultEvaluation"
          placeholder="请选择监督结果"
          clearable
          @change="handleResultChange"
        >
          <el-option value="qualified" label="合格" />
          <el-option value="minor_violation" label="轻微违规" />
          <el-option value="serious_violation" label="严重违规" />
          <el-option value="rectified" label="已完成整改" />
          <el-option value="no_need_rectify" label="无需整改" />
        </el-select>
        <el-input
          v-if="formData.resultEvaluation === 'minor_violation' || formData.resultEvaluation === 'serious_violation'"
          v-model="formData.rectifyRequirements"
          placeholder="请输入整改要求（如：3日内补正执法文书）"
          class="mt-10px"
        />
      </el-form-item>
      <el-form-item label="监督问题描述" prop="violationDescription">
        <el-input v-model="formData.violationDescription" placeholder="请输入违规行为描述" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { LawEnforcementSupervisionApi, LawEnforcementSupervisionVO } from '@/api/smartcity/lawenforcementsupervision'

/** 执法监督 表单 */
defineOptions({ name: 'LawEnforcementSupervisionForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  supervisionId: undefined,
  eventNumber: undefined,
  officials: undefined,
  personnel: undefined,
  time: undefined,
  method: undefined,
  integrityCollection: undefined,
  satisfactionInvolved: undefined,
  resultEvaluation: undefined,
  violationDescription: undefined
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
      formData.value = await LawEnforcementSupervisionApi.getLawEnforcementSupervision(id)
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
    const data = formData.value as unknown as LawEnforcementSupervisionVO
    if (formType.value === 'create') {
      await LawEnforcementSupervisionApi.createLawEnforcementSupervision(data)
      message.success(t('common.createSuccess'))
    } else {
      await LawEnforcementSupervisionApi.updateLawEnforcementSupervision(data)
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
    supervisionId: undefined,
    eventNumber: undefined,
    officials: undefined,
    personnel: undefined,
    time: undefined,
    method: undefined,
    integrityCollection: undefined,
    satisfactionInvolved: undefined,
    resultEvaluation: undefined,
    violationDescription: undefined
  }
  formRef.value?.resetFields()
}
</script>
