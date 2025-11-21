<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="车牌号" prop="licensePlateNumber">
        <el-input v-model="formData.licensePlateNumber" placeholder="请输入车牌号" />
      </el-form-item>
      <el-form-item label="入场时间" prop="admissionTime">
        <el-date-picker
          v-model="formData.admissionTime"
          type="date"
          value-format="x"
          placeholder="选择入场时间"
        />
      </el-form-item>
      <el-form-item label="出场时间" prop="exitTime">
        <el-date-picker
          v-model="formData.exitTime"
          type="date"
          value-format="x"
          placeholder="选择出场时间"
        />
      </el-form-item>
      <el-form-item label="停车时长" prop="parkingDuration">
        <el-input v-model="formData.parkingDuration" placeholder="请输入停车时长" />
      </el-form-item>
      <el-form-item label="收费金额" prop="feeAmount">
        <el-input v-model="formData.feeAmount" placeholder="请输入收费金额" />
      </el-form-item>
      <el-form-item label="缴费方式" prop="paymentMethod">
        <el-input v-model="formData.paymentMethod" placeholder="请输入缴费方式" />
      </el-form-item>
      <el-form-item label="实收金额" prop="actualReceivedAmount">
        <el-input v-model="formData.actualReceivedAmount" placeholder="请输入实收金额" />
      </el-form-item>
      <el-form-item label="车位编号" prop="parkingSpaceNumber">
        <el-input v-model="formData.parkingSpaceNumber" placeholder="请输入车位编号" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { ParkingFeeApi, ParkingFeeVO } from '@/api/smartcity/parkingfee'

/** 停车收费管理 表单 */
defineOptions({ name: 'ParkingFeeForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  licensePlateNumber: undefined,
  admissionTime: undefined,
  exitTime: undefined,
  parkingDuration: undefined,
  feeAmount: undefined,
  paymentMethod: undefined,
  actualReceivedAmount: undefined,
  parkingSpaceNumber: undefined
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
      formData.value = await ParkingFeeApi.getParkingFee(id)
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
    const data = formData.value as unknown as ParkingFeeVO
    if (formType.value === 'create') {
      await ParkingFeeApi.createParkingFee(data)
      message.success(t('common.createSuccess'))
    } else {
      await ParkingFeeApi.updateParkingFee(data)
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
    licensePlateNumber: undefined,
    admissionTime: undefined,
    exitTime: undefined,
    parkingDuration: undefined,
    feeAmount: undefined,
    paymentMethod: undefined,
    actualReceivedAmount: undefined,
    parkingSpaceNumber: undefined
  }
  formRef.value?.resetFields()
}
</script>