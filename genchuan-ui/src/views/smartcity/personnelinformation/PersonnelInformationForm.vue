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
      <el-form-item label="出生日期" prop="dateOfBirth">
        <el-date-picker
          v-model="formData.dateOfBirth"
          type="date"
          value-format="x"
          placeholder="选择出生日期"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="身份证号" prop="idNumber">
        <el-input v-model="formData.idNumber" placeholder="请输入身份证号" />
      </el-form-item>
      <el-form-item label="民族" prop="nationality">
        <el-input v-model="formData.nationality" placeholder="请输入民族" />
      </el-form-item>
      <el-form-item label="户籍地址" prop="registeredResidenceAddress">
        <el-input v-model="formData.registeredResidenceAddress" placeholder="请输入户籍地址" />
      </el-form-item>
      <el-form-item label="现居住地址" prop="currentResidentialAddress">
        <el-input v-model="formData.currentResidentialAddress" placeholder="请输入现居住地址" />
      </el-form-item>
      <el-form-item label="联系电话" prop="contactNumber">
        <el-input v-model="formData.contactNumber" placeholder="请输入联系电话" />
      </el-form-item>
      <el-form-item label="电子邮箱" prop="eMail">
        <el-input v-model="formData.eMail" placeholder="请输入电子邮箱" />
      </el-form-item>
      <!--<el-form-item label="婚姻状况" prop="maritalStatus">-->
      <!--  <el-radio-group v-model="formData.maritalStatus">-->
      <!--    <el-radio value="1">请选择字典生成</el-radio>-->
      <!--  </el-radio-group>-->
      <!--</el-form-item>-->
      <el-form-item label="学历" prop="educationalBackground">
        <el-input v-model="formData.educationalBackground" placeholder="请输入学历" />
      </el-form-item>
      <el-form-item label="毕业院校" prop="almaMater">
        <el-input v-model="formData.almaMater" placeholder="请输入毕业院校" />
      </el-form-item>
      <el-form-item label="专业" prop="major">
        <el-input v-model="formData.major" placeholder="请输入专业" />
      </el-form-item>
      <el-form-item label="工作单位" prop="workUnit">
        <el-input v-model="formData.workUnit" placeholder="请输入工作单位" />
      </el-form-item>
      <el-form-item label="职业" prop="career">
        <el-input v-model="formData.career" placeholder="请输入职业" />
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
      <el-form-item label="离职时间（若已离职）" prop="resignationTime">
        <el-date-picker
          v-model="formData.resignationTime"
          type="date"
          value-format="x"
          placeholder="选择离职时间（若已离职）"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="紧急联系人姓名" prop="emergencyContactName">
        <el-input v-model="formData.emergencyContactName" placeholder="请输入紧急联系人姓名" />
      </el-form-item>
      <el-form-item label="紧急联系人电话" prop="emergencyContactPhone">
        <el-input v-model="formData.emergencyContactPhone" placeholder="请输入紧急联系人电话" />
      </el-form-item>
      <el-form-item label="紧急联系人关系" prop="emergencyContactRelationship">
        <el-input v-model="formData.emergencyContactRelationship" placeholder="请输入紧急联系人关系" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { PersonnelInformationApi, PersonnelInformationVO } from '@/api/smartcity/personnelinformation'

/** 人员信息 表单 */
defineOptions({ name: 'PersonnelInformationForm' })

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
  idNumber: undefined,
  nationality: undefined,
  registeredResidenceAddress: undefined,
  currentResidentialAddress: undefined,
  contactNumber: undefined,
  eMail: undefined,
  maritalStatus: undefined,
  educationalBackground: undefined,
  almaMater: undefined,
  major: undefined,
  workUnit: undefined,
  career: undefined,
  entryTime: undefined,
  resignationTime: undefined,
  emergencyContactName: undefined,
  emergencyContactPhone: undefined,
  emergencyContactRelationship: undefined
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
      formData.value = await PersonnelInformationApi.getPersonnelInformation(id)
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
    const data = formData.value as unknown as PersonnelInformationVO
    if (formType.value === 'create') {
      await PersonnelInformationApi.createPersonnelInformation(data)
      message.success(t('common.createSuccess'))
    } else {
      await PersonnelInformationApi.updatePersonnelInformation(data)
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
    idNumber: undefined,
    nationality: undefined,
    registeredResidenceAddress: undefined,
    currentResidentialAddress: undefined,
    contactNumber: undefined,
    eMail: undefined,
    maritalStatus: undefined,
    educationalBackground: undefined,
    almaMater: undefined,
    major: undefined,
    workUnit: undefined,
    career: undefined,
    entryTime: undefined,
    resignationTime: undefined,
    emergencyContactName: undefined,
    emergencyContactPhone: undefined,
    emergencyContactRelationship: undefined
  }
  formRef.value?.resetFields()
}
</script>
