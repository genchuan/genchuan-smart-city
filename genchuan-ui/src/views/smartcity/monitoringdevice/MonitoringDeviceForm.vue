<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="设备编号" prop="deviceNumber">
        <el-input v-model="formData.deviceNumber" placeholder="请输入设备编号" />
      </el-form-item>
      <el-form-item label="设备名称" prop="deviceName">
        <el-input v-model="formData.deviceName" placeholder="请输入设备名称" />
      </el-form-item>
      <el-form-item label="设备类型" prop="deviceType">
        <el-input v-model="formData.deviceType" placeholder="请输入设备类型" />
      </el-form-item>
      <el-form-item label="生产厂家" prop="manufacturer">
        <el-input v-model="formData.manufacturer" placeholder="请输入生产厂家" />
      </el-form-item>
      <el-form-item label="维护记录" prop="maintenanceRecord">
        <el-input v-model="formData.maintenanceRecord" placeholder="请输入维护记录" />
      </el-form-item>
      <el-form-item label="安装时间" prop="createTime">
        <el-date-picker
          v-model="formData.createTime"
          type="date"
          value-format="x"
          placeholder="选择安装时间"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { MonitoringDeviceApi, MonitoringDeviceVO } from '@/api/smartcity/monitoringdevice'

/** 监测设备 表单 */
defineOptions({ name: 'MonitoringDeviceForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  deviceNumber: undefined,
  deviceName: undefined,
  deviceType: undefined,
  manufacturer: undefined,
  maintenanceRecord: undefined,
  createTime: undefined
})
const formRules = reactive({
  createTime: [{ required: true, message: '安装时间不能为空', trigger: 'blur' }]
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
      formData.value = await MonitoringDeviceApi.getMonitoringDevice(id)
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
    const data = formData.value as unknown as MonitoringDeviceVO
    if (formType.value === 'create') {
      await MonitoringDeviceApi.createMonitoringDevice(data)
      message.success(t('common.createSuccess'))
    } else {
      await MonitoringDeviceApi.updateMonitoringDevice(data)
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
    deviceNumber: undefined,
    deviceName: undefined,
    deviceType: undefined,
    manufacturer: undefined,
    maintenanceRecord: undefined,
    createTime: undefined
  }
  formRef.value?.resetFields()
}
</script>