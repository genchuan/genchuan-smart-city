<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="案件ID" prop="caseId">
        <el-input v-model="formData.caseId" placeholder="请输入案件ID" />
      </el-form-item>
      <el-form-item label="调查负责人" prop="investigationLeader">
        <el-input v-model="formData.investigationLeader" placeholder="请输入调查负责人" />
      </el-form-item>
      <el-form-item label="调查组成员" prop="investigationTeam">
        <el-input v-model="formData.investigationTeam" placeholder="请输入调查组成员" />
      </el-form-item>
      <el-form-item label="调查开始时间" prop="investigationStartTime">
        <el-date-picker
          v-model="formData.investigationStartTime"
          type="date"
          value-format="x"
          placeholder="选择调查开始时间"
          class="common-Width100"
        />
      </el-form-item>

      <el-form-item label="调查结束时间" prop="investigationEndTime">
        <el-date-picker
          v-model="formData.investigationEndTime"
          type="date"
          value-format="x"
          placeholder="选择调查结束时间"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="现场勘查情况" prop="investigationDesc">
        <el-input v-model="formData.investigationDesc" placeholder="请输入现场勘查情况" />
      </el-form-item>
      <el-form-item label="证据情况描述" prop="evidenceDesc">
        <el-input v-model="formData.evidenceDesc" placeholder="请输入证据情况描述" />
      </el-form-item>
      <el-form-item label="证人证言描述" prop="testimonyDesc">
        <el-input v-model="formData.testimonyDesc" placeholder="请输入证人证言描述" />
      </el-form-item>
      <el-form-item label="调查结果" prop="investigationResult">
        <el-input v-model="formData.investigationResult" placeholder="请输入调查结果" />
      </el-form-item>
      <el-form-item label="处理建议" prop="treatmentSuggestion">
        <el-input v-model="formData.treatmentSuggestion" placeholder="请输入处理建议" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { CaseInvestigationApi, CaseInvestigationVO } from '@/api/smartcity/caseinvestigation'

/** 案件调查 表单 */
defineOptions({ name: 'CaseInvestigationForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  caseId: undefined,
  investigationLeader: undefined,
  investigationTeam: undefined,
  investigationStartTime: undefined,
  investigationEndTime: undefined,
  investigationDesc: undefined,
  evidenceDesc: undefined,
  testimonyDesc: undefined,
  investigationResult: undefined,
  treatmentSuggestion: undefined,
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
      formData.value = await CaseInvestigationApi.getCaseInvestigation(id)
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
    const data = formData.value as unknown as CaseInvestigationVO
    if (formType.value === 'create') {
      await CaseInvestigationApi.createCaseInvestigation(data)
      message.success(t('common.createSuccess'))
    } else {
      await CaseInvestigationApi.updateCaseInvestigation(data)
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
    caseId: undefined,
    investigationLeader: undefined,
    investigationTeam: undefined,
    investigationStartTime: undefined,
    investigationEndTime: undefined,
    investigationDesc: undefined,
    evidenceDesc: undefined,
    testimonyDesc: undefined,
    investigationResult: undefined,
    treatmentSuggestion: undefined,
  }
  formRef.value?.resetFields()
}
</script>
