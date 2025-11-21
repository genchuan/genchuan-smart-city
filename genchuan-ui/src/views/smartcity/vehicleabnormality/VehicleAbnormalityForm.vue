<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="车辆编号" prop="vehicleNumber">
        <el-input v-model="formData.vehicleNumber" placeholder="请输入车辆编号" />
      </el-form-item>
      <el-form-item label="异常发生时间" prop="abnormalOccurrenceTime">
        <el-date-picker
          v-model="formData.abnormalOccurrenceTime"
          type="date"
          value-format="x"
          placeholder="选择异常发生时间"
        />
      </el-form-item>
      <el-form-item label="异常地点" prop="abnormalLocation">
        <el-input v-model="formData.abnormalLocation" placeholder="请输入异常地点" />
      </el-form-item>
      <el-form-item label="异常描述" prop="abnormalDescription">
        <el-input v-model="formData.abnormalDescription" placeholder="请输入异常描述" />
      </el-form-item>
      <el-form-item label="车辆状态" prop="vehicleStatus">
        <el-radio-group v-model="formData.vehicleStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="影响程度" prop="degreeOfImpact">
        <el-input v-model="formData.degreeOfImpact" placeholder="请输入影响程度" />
      </el-form-item>
      <el-form-item label="处理措施" prop="handlingMeasures">
        <el-input v-model="formData.handlingMeasures" placeholder="请输入处理措施" />
      </el-form-item>
      <el-form-item label="处理时间" prop="processingTime">
        <el-date-picker
          v-model="formData.processingTime"
          type="date"
          value-format="x"
          placeholder="选择处理时间"
        />
      </el-form-item>
      <el-form-item label="处理人员" prop="processingPersonnel">
        <el-input v-model="formData.processingPersonnel" placeholder="请输入处理人员" />
      </el-form-item>
      <el-form-item label="维修费用" prop="maintenanceCosts">
        <el-input v-model="formData.maintenanceCosts" placeholder="请输入维修费用" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { VehicleAbnormalityApi, VehicleAbnormalityVO } from '@/api/smartcity/vehicleabnormality'

/** 车辆异常 表单 */
defineOptions({ name: 'VehicleAbnormalityForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  vehicleNumber: undefined,
  abnormalOccurrenceTime: undefined,
  abnormalLocation: undefined,
  abnormalDescription: undefined,
  vehicleStatus: undefined,
  degreeOfImpact: undefined,
  handlingMeasures: undefined,
  processingTime: undefined,
  processingPersonnel: undefined,
  maintenanceCosts: undefined
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
      formData.value = await VehicleAbnormalityApi.getVehicleAbnormality(id)
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
    const data = formData.value as unknown as VehicleAbnormalityVO
    if (formType.value === 'create') {
      await VehicleAbnormalityApi.createVehicleAbnormality(data)
      message.success(t('common.createSuccess'))
    } else {
      await VehicleAbnormalityApi.updateVehicleAbnormality(data)
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
    vehicleNumber: undefined,
    abnormalOccurrenceTime: undefined,
    abnormalLocation: undefined,
    abnormalDescription: undefined,
    vehicleStatus: undefined,
    degreeOfImpact: undefined,
    handlingMeasures: undefined,
    processingTime: undefined,
    processingPersonnel: undefined,
    maintenanceCosts: undefined
  }
  formRef.value?.resetFields()
}
</script>