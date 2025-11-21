<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="机构名称" prop="institutionName">
        <el-input v-model="formData.institutionName" placeholder="请输入机构名称" />
      </el-form-item>
      <el-form-item label="统一社会信用代码" prop="unifiedSocialCreditCode">
        <el-input v-model="formData.unifiedSocialCreditCode" placeholder="请输入统一社会信用代码" />
      </el-form-item>
      <el-form-item label="成立时间" prop="establishmentTime">
        <el-date-picker
          v-model="formData.establishmentTime"
          type="date"
          value-format="x"
          placeholder="选择成立时间"
        />
      </el-form-item>
      <el-form-item label="机构性质" prop="natureOfInstitution">
        <el-input v-model="formData.natureOfInstitution" placeholder="请输入机构性质" />
      </el-form-item>
      <el-form-item label="经营范围" prop="natureOfBusiness">
        <el-input v-model="formData.natureOfBusiness" placeholder="请输入经营范围" />
      </el-form-item>
      <el-form-item label="注册资本" prop="registeredCapital">
        <el-input v-model="formData.registeredCapital" placeholder="请输入注册资本" />
      </el-form-item>
      <el-form-item label="法定代表人" prop="legalRepresentative">
        <el-input v-model="formData.legalRepresentative" placeholder="请输入法定代表人" />
      </el-form-item>
      <el-form-item label="注册地址" prop="companyRegisteredAddress">
        <el-input v-model="formData.companyRegisteredAddress" placeholder="请输入注册地址" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { InstitutionalInformationInputApi, InstitutionalInformationInputVO } from '@/api/smartcity/institutionalinformationinput'

/** 机构信息录入 表单 */
defineOptions({ name: 'InstitutionalInformationInputForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  institutionName: undefined,
  unifiedSocialCreditCode: undefined,
  establishmentTime: undefined,
  natureOfInstitution: undefined,
  natureOfBusiness: undefined,
  registeredCapital: undefined,
  legalRepresentative: undefined,
  companyRegisteredAddress: undefined
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
      formData.value = await InstitutionalInformationInputApi.getInstitutionalInformationInput(id)
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
    const data = formData.value as unknown as InstitutionalInformationInputVO
    if (formType.value === 'create') {
      await InstitutionalInformationInputApi.createInstitutionalInformationInput(data)
      message.success(t('common.createSuccess'))
    } else {
      await InstitutionalInformationInputApi.updateInstitutionalInformationInput(data)
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
    institutionName: undefined,
    unifiedSocialCreditCode: undefined,
    establishmentTime: undefined,
    natureOfInstitution: undefined,
    natureOfBusiness: undefined,
    registeredCapital: undefined,
    legalRepresentative: undefined,
    companyRegisteredAddress: undefined
  }
  formRef.value?.resetFields()
}
</script>