<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="人员编号" prop="personnelId">
        <el-input v-model="formData.personnelId" placeholder="请输入人员编号" />
      </el-form-item>
      <el-form-item label="人员姓名" prop="personnelName">
        <el-input v-model="formData.personnelName" placeholder="请输入人员姓名" />
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-input v-model="formData.gender" placeholder="请输入性别" />
      </el-form-item>
      <el-form-item label="联系方式" prop="contactInformation">
        <el-input v-model="formData.contactInformation" placeholder="请输入联系方式" />
      </el-form-item>
      <el-form-item label="身份证号" prop="idNumber">
        <el-input v-model="formData.idNumber" placeholder="请输入身份证号" />
      </el-form-item>
      <el-form-item label="养护地块" prop="maintainTheLandParcel">
        <el-input v-model="formData.maintainTheLandParcel" placeholder="请输入养护地块" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { MaintenancePersonnelApi, MaintenancePersonnelVO } from '@/api/smartcity/maintenancepersonnel'

/** 养护人员 表单 */
defineOptions({ name: 'MaintenancePersonnelForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  personnelId: undefined,
  personnelName: undefined,
  gender: undefined,
  contactInformation: undefined,
  idNumber: undefined,
  maintainTheLandParcel: undefined
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
      formData.value = await MaintenancePersonnelApi.getMaintenancePersonnel(id)
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
    const data = formData.value as unknown as MaintenancePersonnelVO
    if (formType.value === 'create') {
      await MaintenancePersonnelApi.createMaintenancePersonnel(data)
      message.success(t('common.createSuccess'))
    } else {
      await MaintenancePersonnelApi.updateMaintenancePersonnel(data)
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
    personnelId: undefined,
    personnelName: undefined,
    gender: undefined,
    contactInformation: undefined,
    idNumber: undefined,
    maintainTheLandParcel: undefined
  }
  formRef.value?.resetFields()
}
</script>
