<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="案件编号" prop="caseCode">
        <el-input v-model="formData.caseCode" placeholder="请输入案件编号" />
      </el-form-item>
      <el-form-item label="案件名称" prop="caseName">
        <el-input v-model="formData.caseName" placeholder="请输入案件名称" />
      </el-form-item>
      <el-form-item label="案件类型" prop="caseType">
        <el-input v-model="formData.caseType" placeholder="请输入案件类型" />
      </el-form-item>
      <el-form-item label="案件来源" prop="caseSource">
        <el-select v-model="formData.caseSource" placeholder="请选择案件来源" clearable>
          <el-option value="telephone" label="电话举报" />
          <el-option value="network" label="网络举报" />
          <el-option value="wechat" label="微信举报" />
          <el-option value="citizen_photo" label="市民随手拍" />
          <el-option value="online_monitor" label="在线监测系统自动上报" />
          <el-option value="on-site_inspection" label="现场巡查发现" />
          <el-option value="other_department" label="其他部门移交" />
          <el-option value="letter_report" label="来信举报" />
        </el-select>
      </el-form-item>
      <el-form-item label="案件时间" prop="caseTime">
        <el-date-picker
          v-model="formData.caseTime"
          type="date"
          value-format="x"
          placeholder="选择案件时间"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="案件地点" prop="caseLocation">
        <el-input v-model="formData.caseLocation" placeholder="请输入案件地点" />
      </el-form-item>
      <el-form-item label="报案单位" prop="reportUnit">
        <el-input v-model="formData.reportUnit" placeholder="请输入报案单位" />
      </el-form-item>
      <el-form-item label="当事人信息" prop="reportPerson">
        <el-input v-model="formData.reportPerson" placeholder="请输入当事人信息" />
      </el-form-item>
      <el-form-item label="联系电话" prop="reportPhone">
        <el-input v-model="formData.reportPhone" placeholder="请输入联系电话" />
      </el-form-item>
      <el-form-item label="案件描述" prop="caseDesc">
        <el-input v-model="formData.caseDesc" placeholder="请输入案件描述" />
      </el-form-item>
      <el-form-item label="立案状态" prop="caseStatus">
        <el-select v-model="formData.caseStatus" placeholder="请选择立案状态" clearable>
          <el-option value="pending_filing" label="待立案" />
          <el-option value="filed" label="已立案" />
          <el-option value="rejected_filing" label="不予立案" />
          <el-option value="rejected_review" label="驳回重审" />
        </el-select>
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { CaseAcceptanceApi, CaseAcceptanceVO } from '@/api/smartcity/caseacceptance'

/** 案件受理 表单 */
defineOptions({ name: 'CaseAcceptanceForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  caseCode: undefined,
  caseName: undefined,
  caseType: undefined,
  caseSource: undefined,
  caseTime: undefined,
  caseLocation: undefined,
  reportUnit: undefined,
  reportPerson: undefined,
  reportPhone: undefined,
  caseDesc: undefined,
  caseStatus: undefined
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
      formData.value = await CaseAcceptanceApi.getCaseAcceptance(id)
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
    const data = formData.value as unknown as CaseAcceptanceVO
    if (formType.value === 'create') {
      await CaseAcceptanceApi.createCaseAcceptance(data)
      message.success(t('common.createSuccess'))
    } else {
      await CaseAcceptanceApi.updateCaseAcceptance(data)
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
    caseCode: undefined,
    caseName: undefined,
    caseType: undefined,
    caseSource: undefined,
    caseTime: undefined,
    caseLocation: undefined,
    reportUnit: undefined,
    reportPerson: undefined,
    reportPhone: undefined,
    caseDesc: undefined,
    caseStatus: undefined
  }
  formRef.value?.resetFields()
}
</script>
