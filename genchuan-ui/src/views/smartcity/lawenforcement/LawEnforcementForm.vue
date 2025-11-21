<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="执法案件编号" prop="lawEnforcementNumber">
        <el-input v-model="formData.lawEnforcementNumber" placeholder="请输入执法案件编号" />
      </el-form-item>
      <el-form-item label="案件发生地点" prop="locationTheCase">
        <el-input v-model="formData.locationTheCase" placeholder="请输入案件发生地点" />
      </el-form-item>
      <el-form-item label="发生时间" prop="occurrenceTime">
        <el-date-picker
          v-model="formData.occurrenceTime"
          type="date"
          value-format="x"
          placeholder="选择发生时间"
        />
      </el-form-item>
      <el-form-item label="涉事主体信息" prop="informationInvolved">
        <el-input v-model="formData.informationInvolved" placeholder="请输入涉事主体信息" />
      </el-form-item>
      <el-form-item label="案件详情描述" prop="caseDetailsDescription">
        <Editor v-model="formData.caseDetailsDescription" height="150px" />
      </el-form-item>
      <el-form-item label="案件处理时长" prop="durationOfCaseHandling">
        <el-input v-model="formData.durationOfCaseHandling" placeholder="请输入案件处理时长" />
      </el-form-item>
      <el-form-item label="投诉举报次数" prop="numberComplaintsReports">
        <el-input v-model="formData.numberComplaintsReports" placeholder="请输入投诉举报次数" />
      </el-form-item>
      <el-form-item label="投诉内容" prop="complaintContent">
        <Editor v-model="formData.complaintContent" height="150px" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { LawEnforcementApi, LawEnforcementVO } from '@/api/smartcity/lawenforcement'

/** 城市管理执法 表单 */
defineOptions({ name: 'LawEnforcementForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  lawEnforcementNumber: undefined,
  locationTheCase: undefined,
  occurrenceTime: undefined,
  informationInvolved: undefined,
  caseDetailsDescription: undefined,
  durationOfCaseHandling: undefined,
  numberComplaintsReports: undefined,
  complaintContent: undefined
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
      formData.value = await LawEnforcementApi.getLawEnforcement(id)
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
    const data = formData.value as unknown as LawEnforcementVO
    if (formType.value === 'create') {
      await LawEnforcementApi.createLawEnforcement(data)
      message.success(t('common.createSuccess'))
    } else {
      await LawEnforcementApi.updateLawEnforcement(data)
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
    lawEnforcementNumber: undefined,
    locationTheCase: undefined,
    occurrenceTime: undefined,
    informationInvolved: undefined,
    caseDetailsDescription: undefined,
    durationOfCaseHandling: undefined,
    numberComplaintsReports: undefined,
    complaintContent: undefined
  }
  formRef.value?.resetFields()
}
</script>