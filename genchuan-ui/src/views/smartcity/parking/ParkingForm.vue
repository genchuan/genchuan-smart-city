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
      <el-form-item label="手机号码" prop="phoneNumber">
        <el-input v-model="formData.phoneNumber" placeholder="请输入手机号码" />
      </el-form-item>
      <!--<el-form-item label="电子邮箱" prop="eMail">-->
      <!--  <el-input v-model="formData.eMail" placeholder="请输入电子邮箱" />-->
      <!--</el-form-item>-->
      <el-form-item label="身份证号" prop="idNumber">
        <el-input v-model="formData.idNumber" placeholder="请输入身份证号" />
      </el-form-item>
      <el-form-item label="注册时间" prop="registrationTime">
        <el-date-picker
          v-model="formData.registrationTime"
          type="date"
          value-format="x"
          placeholder="选择注册时间"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="用户类型" prop="customerType">
        <el-input v-model="formData.customerType" placeholder="请输入用户类型" />
      </el-form-item>
      <el-form-item label="用户等级" prop="userLevel">
        <el-input v-model="formData.userLevel" placeholder="请输入用户等级" />
      </el-form-item>
      <el-form-item label="积分" prop="points">
        <el-input v-model="formData.points" placeholder="请输入积分" />
      </el-form-item>
      <el-form-item label="余额" prop="balance">
        <el-input v-model="formData.balance" placeholder="请输入余额" />
      </el-form-item>
      <el-form-item label="地址" prop="address">
        <el-input v-model="formData.address" placeholder="请输入地址" />
      </el-form-item>
      <el-form-item label="服务评价" prop="serviceEvaluation">
        <el-input v-model="formData.serviceEvaluation" placeholder="请输入服务评价" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { ParkingApi, ParkingVO } from '@/api/smartcity/parking'

/** 停车管理用户管理与服务 表单 */
defineOptions({ name: 'ParkingForm' })

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
  phoneNumber: undefined,
  eMail: undefined,
  idNumber: undefined,
  registrationTime: undefined,
  customerType: undefined,
  userLevel: undefined,
  points: undefined,
  balance: undefined,
  address: undefined,
  serviceEvaluation: undefined
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
      formData.value = await ParkingApi.getParking(id)
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
    const data = formData.value as unknown as ParkingVO
    if (formType.value === 'create') {
      await ParkingApi.createParking(data)
      message.success(t('common.createSuccess'))
    } else {
      await ParkingApi.updateParking(data)
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
    phoneNumber: undefined,
    eMail: undefined,
    idNumber: undefined,
    registrationTime: undefined,
    customerType: undefined,
    userLevel: undefined,
    points: undefined,
    balance: undefined,
    address: undefined,
    serviceEvaluation: undefined
  }
  formRef.value?.resetFields()
}
</script>
