<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="姓名" prop="fullName">
        <el-input v-model="formData.fullName" placeholder="请输入姓名" />
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-input v-model="formData.gender" placeholder="请输入性别" />
      </el-form-item>
      <el-form-item label="出生日期" prop="dateOfBirth" >
        <el-date-picker
          v-model="formData.dateOfBirth"
          type="date"
          value-format="x"
          placeholder="选择出生日期"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="联系方式" prop="contactInformation">
        <el-input v-model="formData.contactInformation" placeholder="请输入联系方式" />
      </el-form-item>
      <!--<el-form-item label="身份证号" prop="idNumber">-->
      <!--  <el-input v-model="formData.idNumber" placeholder="请输入身份证号" />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="所属部门" prop="department">-->
      <!--  <el-input v-model="formData.department" placeholder="请输入所属部门" />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="岗位名称" prop="position">-->
      <!--  <el-input v-model="formData.position" placeholder="请输入岗位名称" />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="入职时间" prop="entryTime">-->
      <!--  <el-date-picker-->
      <!--    v-model="formData.entryTime"-->
      <!--    type="date"-->
      <!--    value-format="x"-->
      <!--    placeholder="选择入职时间"-->
      <!--  />-->
      <!--</el-form-item>-->
      <el-form-item label="巡查区域" prop="patrolArea">
        <el-input v-model="formData.patrolArea" placeholder="请输入巡查区域" />
      </el-form-item>
      <el-form-item label="巡查路线" prop="patrolRoute">
        <el-input v-model="formData.patrolRoute" placeholder="请输入巡查路线" />
      </el-form-item>
      <el-form-item label="巡查班次" prop="inspectionShift">
        <el-input v-model="formData.inspectionShift" placeholder="请输入巡查班次" />
      </el-form-item>
      <!--<el-form-item label="资质证书编号" prop="qualificationCertificateNumber">-->
      <!--  <el-input v-model="formData.qualificationCertificateNumber" placeholder="请输入资质证书编号" />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="证书有效期" prop="certificateValidityPeriod">-->
      <!--  <el-input v-model="formData.certificateValidityPeriod" placeholder="请输入证书有效期" />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="培训记录" prop="trainingRecords">-->
      <!--  <el-input v-model="formData.trainingRecords" placeholder="请输入培训记录" />-->
      <!--</el-form-item>-->
      <el-form-item label="巡查设备编号" prop="inspectionEquipmentNumber">
        <el-input v-model="formData.inspectionEquipmentNumber" placeholder="请输入巡查设备编号" />
      </el-form-item>
      <!--<el-form-item label="违规记录" prop="violationRecord">-->
      <!--  <el-input v-model="formData.violationRecord" placeholder="请输入违规记录" />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="奖惩情况" prop="rewardAndPunishmentSituation">-->
      <!--  <el-input v-model="formData.rewardAndPunishmentSituation" placeholder="请输入奖惩情况" />-->
      <!--</el-form-item>-->
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { ManagementOfPatrolPersonnelApi, ManagementOfPatrolPersonnelVO } from '@/api/smartcity/managementofpatrolpersonnel'

/** 巡査人员管理 表单 */
defineOptions({ name: 'ManagementOfPatrolPersonnelForm' })

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
  dateOfBirth: undefined,
  contactInformation: undefined,
  idNumber: undefined,
  department: undefined,
  position: undefined,
  entryTime: undefined,
  patrolArea: undefined,
  patrolRoute: undefined,
  inspectionShift: undefined,
  qualificationCertificateNumber: undefined,
  certificateValidityPeriod: undefined,
  trainingRecords: undefined,
  inspectionEquipmentNumber: undefined,
  violationRecord: undefined,
  rewardAndPunishmentSituation: undefined
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
      formData.value = await ManagementOfPatrolPersonnelApi.getManagementOfPatrolPersonnel(id)
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
    const data = formData.value as unknown as ManagementOfPatrolPersonnelVO
    if (formType.value === 'create') {
      await ManagementOfPatrolPersonnelApi.createManagementOfPatrolPersonnel(data)
      message.success(t('common.createSuccess'))
    } else {
      await ManagementOfPatrolPersonnelApi.updateManagementOfPatrolPersonnel(data)
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
    dateOfBirth: undefined,
    contactInformation: undefined,
    idNumber: undefined,
    department: undefined,
    position: undefined,
    entryTime: undefined,
    patrolArea: undefined,
    patrolRoute: undefined,
    inspectionShift: undefined,
    qualificationCertificateNumber: undefined,
    certificateValidityPeriod: undefined,
    trainingRecords: undefined,
    inspectionEquipmentNumber: undefined,
    violationRecord: undefined,
    rewardAndPunishmentSituation: undefined
  }
  formRef.value?.resetFields()
}
</script>
