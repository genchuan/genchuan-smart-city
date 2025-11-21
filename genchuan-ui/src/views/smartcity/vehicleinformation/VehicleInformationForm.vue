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
      <el-form-item label="车辆品牌" prop="vehicleBrand">
        <el-input v-model="formData.vehicleBrand" placeholder="请输入车辆品牌" />
      </el-form-item>
      <el-form-item label="车辆型号" prop="model">
        <el-input v-model="formData.model" placeholder="请输入车辆型号" />
      </el-form-item>
      <el-form-item label="车辆类型" prop="vehicleType">
        <el-select v-model="formData.vehicleType" placeholder="请选择车辆类型">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="车架号" prop="vin">
        <el-input v-model="formData.vin" placeholder="请输入车架号" />
      </el-form-item>
      <el-form-item label="发动机号" prop="engineNo">
        <el-input v-model="formData.engineNo" placeholder="请输入发动机号" />
      </el-form-item>
      <el-form-item label="注册日期" prop="registrationDate">
        <el-date-picker
          v-model="formData.registrationDate"
          type="date"
          value-format="x"
          placeholder="选择注册日期"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="登记车主姓名" prop="registerTheName">
        <el-input v-model="formData.registerTheName" placeholder="请输入登记车主姓名" />
      </el-form-item>
      <el-form-item label="车主身份证号" prop="ownerIdNumberNumber">
        <el-input v-model="formData.ownerIdNumberNumber" placeholder="请输入车主身份证号" />
      </el-form-item>
      <el-form-item label="车主联系电话" prop="carOwnerPhoneNumber">
        <el-input v-model="formData.carOwnerPhoneNumber" placeholder="请输入车主联系电话" />
      </el-form-item>
      <el-form-item label="车辆颜色" prop="vehicleColor">
        <el-input v-model="formData.vehicleColor" placeholder="请输入车辆颜色" />
      </el-form-item>
      <el-form-item label="座位数" prop="seats">
        <el-input v-model="formData.seats" placeholder="请输入座位数" />
      </el-form-item>
      <el-form-item label="出厂日期" prop="dateOfProduction">
        <el-input v-model="formData.dateOfProduction" placeholder="请输入出厂日期" />
      </el-form-item>
      <el-form-item label="购置价格" prop="purchasePrice">
        <el-input v-model="formData.purchasePrice" placeholder="请输入购置价格" />
      </el-form-item>
      <el-form-item label="使用性质" prop="natureOfUse">
        <el-input v-model="formData.natureOfUse" placeholder="请输入使用性质" />
      </el-form-item>
      <el-form-item label="年检有效期" prop="annualInspectionPeriod">
        <el-input v-model="formData.annualInspectionPeriod" placeholder="请输入年检有效期" />
      </el-form-item>
      <el-form-item label="保险有效期" prop="periodOfInsurance">
        <el-input v-model="formData.periodOfInsurance" placeholder="请输入保险有效期" />
      </el-form-item>
      <el-form-item label="保险类型" prop="typesOfInsurance">
        <el-input v-model="formData.typesOfInsurance" placeholder="请输入保险类型" />
      </el-form-item>
      <el-form-item label="环保排放标准" prop="environmentalStandards">
        <el-input v-model="formData.environmentalStandards" placeholder="请输入环保排放标准" />
      </el-form-item>
      <el-form-item label="车辆用途" prop="vehicleUsage">
        <el-input v-model="formData.vehicleUsage" placeholder="请输入车辆用途" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { VehicleInformationApi, VehicleInformationVO } from '@/api/smartcity/vehicleinformation'

/** 车辆信息 表单 */
defineOptions({ name: 'VehicleInformationForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  licensePlateNumber: undefined,
  vehicleBrand: undefined,
  model: undefined,
  vehicleType: undefined,
  vin: undefined,
  engineNo: undefined,
  registrationDate: undefined,
  registerTheName: undefined,
  ownerIdNumberNumber: undefined,
  carOwnerPhoneNumber: undefined,
  vehicleColor: undefined,
  seats: undefined,
  dateOfProduction: undefined,
  purchasePrice: undefined,
  natureOfUse: undefined,
  annualInspectionPeriod: undefined,
  periodOfInsurance: undefined,
  typesOfInsurance: undefined,
  environmentalStandards: undefined,
  vehicleUsage: undefined
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
      formData.value = await VehicleInformationApi.getVehicleInformation(id)
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
    const data = formData.value as unknown as VehicleInformationVO
    if (formType.value === 'create') {
      await VehicleInformationApi.createVehicleInformation(data)
      message.success(t('common.createSuccess'))
    } else {
      await VehicleInformationApi.updateVehicleInformation(data)
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
    vehicleBrand: undefined,
    model: undefined,
    vehicleType: undefined,
    vin: undefined,
    engineNo: undefined,
    registrationDate: undefined,
    registerTheName: undefined,
    ownerIdNumberNumber: undefined,
    carOwnerPhoneNumber: undefined,
    vehicleColor: undefined,
    seats: undefined,
    dateOfProduction: undefined,
    purchasePrice: undefined,
    natureOfUse: undefined,
    annualInspectionPeriod: undefined,
    periodOfInsurance: undefined,
    typesOfInsurance: undefined,
    environmentalStandards: undefined,
    vehicleUsage: undefined
  }
  formRef.value?.resetFields()
}
</script>
