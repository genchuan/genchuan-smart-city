<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
    >
      <el-form-item label="姓名" prop="fullName">
        <el-input v-model="formData.fullName" placeholder="请输入姓名" />
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-input v-model="formData.gender" placeholder="请输入性别" />
      </el-form-item>
      <el-form-item label="身份证号" prop="idNumber">
        <el-input v-model="formData.idNumber" placeholder="请输入身份证号" />
      </el-form-item>
      <el-form-item label="联系方式" prop="contactInformation">
        <el-input v-model="formData.contactInformation" placeholder="请输入联系方式" />
      </el-form-item>
      <el-form-item label="所属执法部门" prop="belongingLawDepartment">
        <el-input v-model="formData.belongingLawDepartment" placeholder="请输入所属执法部门" />
      </el-form-item>
      <el-form-item label="职务" prop="position">
        <el-input v-model="formData.position" placeholder="请输入职务" />
      </el-form-item>
      <el-form-item label="执法证编号" prop="lawEnforcementNumber">
        <el-input v-model="formData.lawEnforcementNumber" placeholder="请输入执法证编号" />
      </el-form-item>
      <el-form-item label="执法证有效期" prop="validityCertificate">
        <el-date-picker
          v-model="formData.validityCertificate"
          type="date"
          value-format="x"
          placeholder="选择执法证有效期"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="入职时间" prop="entryTime">
        <el-date-picker
          v-model="formData.entryTime"
          type="date"
          value-format="x"
          placeholder="选择入职时间"
          class="common-Width100"
        />
      </el-form-item>
      <!--<el-form-item label="政治面貌" prop="politicalStatus">-->
      <!--  <el-radio-group v-model="formData.politicalStatus">-->
      <!--    <el-radio value="1">请选择字典生成</el-radio>-->
      <!--  </el-radio-group>-->
      <!--</el-form-item>-->
      <el-form-item label="学历" prop="educationalBackground">
        <el-input v-model="formData.educationalBackground" placeholder="请输入学历" />
      </el-form-item>
      <el-form-item label="专业" prop="major">
        <el-input v-model="formData.major" placeholder="请输入专业" />
      </el-form-item>
      <el-form-item label="培训经历" prop="trainingExperience">
        <el-input v-model="formData.trainingExperience" placeholder="请输入培训经历" />
      </el-form-item>
      <el-form-item label="考核成绩" prop="resultOfExamination">
        <el-input v-model="formData.resultOfExamination" placeholder="请输入考核成绩" />
      </el-form-item>
      <el-form-item label="奖惩记录" prop="rewardsRecord">
        <el-input v-model="formData.rewardsRecord" placeholder="请输入奖惩记录" />
      </el-form-item>
      <el-form-item label="违规违纪情况" prop="violationRegulations">
        <el-input v-model="formData.violationRegulations" placeholder="请输入违规违纪情况" />
      </el-form-item>
      <el-form-item label="负责区域" prop="responsibleArea">
        <el-input v-model="formData.responsibleArea" placeholder="请输入负责区域" />
      </el-form-item>
      <!--<el-form-item label="执法类型" prop="lawEnforcementType">-->
      <!--  <el-select v-model="formData.lawEnforcementType" placeholder="请选择执法类型">-->
      <!--    <el-option label="请选择字典生成" value="" />-->
      <!--  </el-select>-->
      <!--</el-form-item>-->
      <el-form-item label="紧急联系人姓名" prop="emergencyContactName">
        <el-input v-model="formData.emergencyContactName" placeholder="请输入紧急联系人姓名" />
      </el-form-item>
      <el-form-item label="紧急联系人电话" prop="emergencyContactPhone">
        <el-input v-model="formData.emergencyContactPhone" placeholder="请输入紧急联系人电话" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { OfLawEnforcementPersonnelApi, OfLawEnforcementPersonnelVO } from '@/api/smartcity/oflawenforcementpersonnel'

/** 执法人员管理 表单 */
defineOptions({ name: 'OfLawEnforcementPersonnelForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  fullName: undefined,
  gender: undefined,
  idNumber: undefined,
  contactInformation: undefined,
  belongingLawDepartment: undefined,
  position: undefined,
  lawEnforcementNumber: undefined,
  validityCertificate: undefined,
  entryTime: undefined,
  politicalStatus: undefined,
  educationalBackground: undefined,
  major: undefined,
  trainingExperience: undefined,
  resultOfExamination: undefined,
  rewardsRecord: undefined,
  violationRegulations: undefined,
  responsibleArea: undefined,
  lawEnforcementType: undefined,
  emergencyContactName: undefined,
  emergencyContactPhone: undefined
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
      formData.value = await OfLawEnforcementPersonnelApi.getOfLawEnforcementPersonnel(id)
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
    const data = formData.value as unknown as OfLawEnforcementPersonnelVO
    if (formType.value === 'create') {
      await OfLawEnforcementPersonnelApi.createOfLawEnforcementPersonnel(data)
      message.success(t('common.createSuccess'))
    } else {
      await OfLawEnforcementPersonnelApi.updateOfLawEnforcementPersonnel(data)
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
    fullName: undefined,
    gender: undefined,
    idNumber: undefined,
    contactInformation: undefined,
    belongingLawDepartment: undefined,
    position: undefined,
    lawEnforcementNumber: undefined,
    validityCertificate: undefined,
    entryTime: undefined,
    politicalStatus: undefined,
    educationalBackground: undefined,
    major: undefined,
    trainingExperience: undefined,
    resultOfExamination: undefined,
    rewardsRecord: undefined,
    violationRegulations: undefined,
    responsibleArea: undefined,
    lawEnforcementType: undefined,
    emergencyContactName: undefined,
    emergencyContactPhone: undefined
  }
  formRef.value?.resetFields()
}
</script>
