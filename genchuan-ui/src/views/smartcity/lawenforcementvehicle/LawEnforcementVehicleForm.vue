<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
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
      <el-form-item label="车辆颜色" prop="vehicleColor">
        <el-input v-model="formData.vehicleColor" placeholder="请输入车辆颜色" />
      </el-form-item>
      <el-form-item label="车架号" prop="vin">
        <el-input v-model="formData.vin" placeholder="请输入车架号" />
      </el-form-item>
      <el-form-item label="发动机号" prop="engineNo">
        <el-input v-model="formData.engineNo" placeholder="请输入发动机号" />
      </el-form-item>
      <el-form-item label="购置时间" prop="purchaseTime">
        <el-date-picker
          v-model="formData.purchaseTime"
          type="date"
          value-format="x"
          placeholder="选择购置时间"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="登记注册日期" prop="registrationDate">
        <el-date-picker
          v-model="formData.registrationDate"
          type="date"
          value-format="x"
          placeholder="选择登记注册日期"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="所属执法部门" prop="belongingDepartment">
        <el-input v-model="formData.belongingDepartment" placeholder="请输入所属执法部门" />
      </el-form-item>
      <el-form-item label="使用性质" prop="natureOfUse">
        <el-input v-model="formData.natureOfUse" placeholder="请输入使用性质" />
      </el-form-item>
      <el-form-item label="车辆用途" prop="vehicleUsage">
        <el-input v-model="formData.vehicleUsage" placeholder="请输入车辆用途" />
      </el-form-item>
      <!--<el-form-item label="车辆状态" prop="vehicleStatus">-->
      <!--  <el-radio-group v-model="formData.vehicleStatus">-->
      <!--    <el-radio value="1">请选择字典生成</el-radio>-->
      <!--  </el-radio-group>-->
      <!--</el-form-item>-->
      <el-form-item label="年检到期日期" prop="annualInspectionDate">
        <el-date-picker
          v-model="formData.annualInspectionDate"
          type="date"
          value-format="x"
          placeholder="选择年检到期日期"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="保险截止日期" prop="insuranceDeadline">
        <el-date-picker
          v-model="formData.insuranceDeadline"
          type="date"
          value-format="x"
          placeholder="选择保险截止日期"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="保险类型" prop="typesOfInsurance">
        <el-input v-model="formData.typesOfInsurance" placeholder="请输入保险类型" />
      </el-form-item>
      <el-form-item label="驾驶员姓名" prop="driverName">
        <el-input v-model="formData.driverName" placeholder="请输入驾驶员姓名" />
      </el-form-item>
      <el-form-item label="驾驶员联系方式" prop="driverContactInformation">
        <el-input v-model="formData.driverContactInformation" placeholder="请输入驾驶员联系方式" />
      </el-form-item>
      <el-form-item label="行驶里程" prop="mileage">
        <el-input v-model="formData.mileage" placeholder="请输入行驶里程" />
      </el-form-item>
      <el-form-item label="维修记录" prop="maintenanceRecord">
        <el-input v-model="formData.maintenanceRecord" placeholder="请输入维修记录" />
      </el-form-item>
      <el-form-item label="保养记录" prop="maintenanceRecords">
        <el-input v-model="formData.maintenanceRecords" placeholder="请输入保养记录" />
      </el-form-item>
      <el-form-item label="加油记录" prop="refuelingRecord">
        <el-input v-model="formData.refuelingRecord" placeholder="请输入加油记录" />
      </el-form-item>
      <el-form-item label="违章记录" prop="violationRecords">
        <el-input v-model="formData.violationRecords" placeholder="请输入违章记录" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { LawEnforcementVehicleApi, LawEnforcementVehicleVO } from '@/api/smartcity/lawenforcementvehicle'

/** 执法车辆管理 表单 */
defineOptions({ name: 'LawEnforcementVehicleForm' })

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
  vehicleColor: undefined,
  vin: undefined,
  engineNo: undefined,
  purchaseTime: undefined,
  registrationDate: undefined,
  belongingDepartment: undefined,
  natureOfUse: undefined,
  vehicleUsage: undefined,
  vehicleStatus: undefined,
  annualInspectionDate: undefined,
  insuranceDeadline: undefined,
  typesOfInsurance: undefined,
  driverName: undefined,
  driverContactInformation: undefined,
  mileage: undefined,
  maintenanceRecord: undefined,
  maintenanceRecords: undefined,
  refuelingRecord: undefined,
  violationRecords: undefined
})
const formRules = reactive({
  vehicleColor: [{ required: true, message: '车辆颜色不能为空', trigger: 'blur' }],
  vin: [{ required: true, message: '车架号不能为空', trigger: 'blur' }],
  engineNo: [{ required: true, message: '发动机号不能为空', trigger: 'blur' }],
  purchaseTime: [{ required: true, message: '购置时间不能为空', trigger: 'blur' }],
  registrationDate: [{ required: true, message: '登记注册日期不能为空', trigger: 'blur' }],
  belongingDepartment: [{ required: true, message: '所属执法部门不能为空', trigger: 'blur' }],
  natureOfUse: [{ required: true, message: '使用性质不能为空', trigger: 'blur' }],
  vehicleUsage: [{ required: true, message: '车辆用途不能为空', trigger: 'blur' }],
  vehicleStatus: [{ required: true, message: '车辆状态不能为空', trigger: 'blur' }],
  annualInspectionDate: [{ required: true, message: '年检到期日期不能为空', trigger: 'blur' }],
  insuranceDeadline: [{ required: true, message: '保险截止日期不能为空', trigger: 'blur' }],
  typesOfInsurance: [{ required: true, message: '保险类型不能为空', trigger: 'blur' }],
  driverName: [{ required: true, message: '驾驶员姓名不能为空', trigger: 'blur' }],
  driverContactInformation: [{ required: true, message: '驾驶员联系方式不能为空', trigger: 'blur' }],
  mileage: [{ required: true, message: '行驶里程不能为空', trigger: 'blur' }],
  maintenanceRecord: [{ required: true, message: '维修记录不能为空', trigger: 'blur' }],
  maintenanceRecords: [{ required: true, message: '保养记录不能为空', trigger: 'blur' }],
  refuelingRecord: [{ required: true, message: '加油记录不能为空', trigger: 'blur' }],
  violationRecords: [{ required: true, message: '违章记录不能为空', trigger: 'blur' }]
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
      formData.value = await LawEnforcementVehicleApi.getLawEnforcementVehicle(id)
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
    const data = formData.value as unknown as LawEnforcementVehicleVO
    if (formType.value === 'create') {
      await LawEnforcementVehicleApi.createLawEnforcementVehicle(data)
      message.success(t('common.createSuccess'))
    } else {
      await LawEnforcementVehicleApi.updateLawEnforcementVehicle(data)
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
    vehicleColor: undefined,
    vin: undefined,
    engineNo: undefined,
    purchaseTime: undefined,
    registrationDate: undefined,
    belongingDepartment: undefined,
    natureOfUse: undefined,
    vehicleUsage: undefined,
    vehicleStatus: undefined,
    annualInspectionDate: undefined,
    insuranceDeadline: undefined,
    typesOfInsurance: undefined,
    driverName: undefined,
    driverContactInformation: undefined,
    mileage: undefined,
    maintenanceRecord: undefined,
    maintenanceRecords: undefined,
    refuelingRecord: undefined,
    violationRecords: undefined
  }
  formRef.value?.resetFields()
}
</script>
