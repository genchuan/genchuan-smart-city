<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="名称" prop="name">
        <el-input v-model="formData.name" placeholder="请输入名称" />
      </el-form-item>
      <el-form-item label="位置" prop="position">
        <el-input v-model="formData.position" placeholder="请输入位置" />
      </el-form-item>
      <el-form-item label="联系电话" prop="contactNumber">
        <el-input v-model="formData.contactNumber" placeholder="请输入联系电话" />
      </el-form-item>
      <el-form-item label="车位编号" prop="parkingSpaceNumber">
        <el-input v-model="formData.parkingSpaceNumber" placeholder="请输入车位编号" />
      </el-form-item>
      <el-form-item label="监控设备数量" prop="numberDevices">
        <el-input v-model="formData.numberDevices" placeholder="请输入监控设备数量" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { ParkingLotInformationApi, ParkingLotInformationVO } from '@/api/smartcity/parkinglotinformation'

/** 停车场信息管理 表单 */
defineOptions({ name: 'ParkingLotInformationForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  name: undefined,
  position: undefined,
  contactNumber: undefined,
  parkingSpaceNumber: undefined,
  numberDevices: undefined
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
      formData.value = await ParkingLotInformationApi.getParkingLotInformation(id)
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
    const data = formData.value as unknown as ParkingLotInformationVO
    if (formType.value === 'create') {
      await ParkingLotInformationApi.createParkingLotInformation(data)
      message.success(t('common.createSuccess'))
    } else {
      await ParkingLotInformationApi.updateParkingLotInformation(data)
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
    name: undefined,
    position: undefined,
    contactNumber: undefined,
    parkingSpaceNumber: undefined,
    numberDevices: undefined
  }
  formRef.value?.resetFields()
}
</script>