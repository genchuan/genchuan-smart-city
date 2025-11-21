<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="650px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="150px"
      v-loading="formLoading"
      class="custom-form"
    >
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="预警ID" prop="offlineAlertId">
            <el-input v-model="formData.offlineAlertId" placeholder="请输入预警ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="设备ID" prop="deviceId">
            <el-input v-model="formData.deviceId" placeholder="请输入设备ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="设备名称" prop="deviceName">
            <el-input v-model="formData.deviceName" placeholder="请输入设备名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="设备编码" prop="deviceCode">
            <el-input v-model="formData.deviceCode" placeholder="请输入设备编码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="离线开始时间" prop="offlineStartTime">
            <el-date-picker
              v-model="formData.offlineStartTime"
              type="datetime"
              value-format="x"
              placeholder="选择离线开始时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="离线时长(分钟)" prop="offlineEndure">
            <el-input
              v-model="formData.offlineEndure"
              placeholder="请输入离线时长(分钟)"
              type="number"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预警级别" prop="alertLevel">
            <el-input v-model="formData.alertLevel" placeholder="请输入预警级别" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预警接收人ID" prop="alertUserId">
            <el-input v-model="formData.alertUserId" placeholder="请输入预警接收人ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预警接收人姓名" prop="alertUserName">
            <el-input v-model="formData.alertUserName" placeholder="请输入预警接收人姓名" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预警推送时间" prop="alertPushTime">
            <el-date-picker
              v-model="formData.alertPushTime"
              type="datetime"
              value-format="x"
              placeholder="选择预警推送时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预警状态" prop="alertStatus">
            <el-radio-group v-model="formData.alertStatus">
              <el-radio value="处理中">处理中</el-radio>
              <el-radio value="已处理">已处理</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="处理人姓名" prop="handleUserName">
            <el-input v-model="formData.handleUserName" placeholder="请输入处理人姓名" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="处理时间" prop="handleTime">
            <el-date-picker
              v-model="formData.handleTime"
              type="datetime"
              value-format="x"
              placeholder="选择处理时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="处理结果" prop="handleResult">
            <el-input
              v-model="formData.handleResult"
              placeholder="请输入处理结果"
              type="textarea"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属行政区划名称" prop="regionName">
            <el-input v-model="formData.regionName" placeholder="请输入所属行政区划名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属行政区划代码" prop="regionCode">
            <el-input v-model="formData.regionCode" placeholder="请输入所属行政区划代码" />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  DeviceOfflineAlertApi,
  DeviceOfflineAlertVO
} from '@/api/dataHub/deviceManage/daviceStatusMonitor/deviceofflinealert'

/** 设备离线预警 表单 */
defineOptions({ name: 'DeviceOfflineAlertForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<DeviceOfflineAlertVO>>({
  id: undefined,
  offlineAlertId: undefined,
  deviceId: undefined,
  deviceName: undefined,
  deviceCode: undefined,
  offlineStartTime: undefined,
  offlineEndure: undefined,
  alertLevel: undefined,
  alertUserId: undefined,
  alertUserName: undefined,
  alertPushTime: undefined,
  alertStatus: undefined,
  handleUserId: undefined,
  handleUserName: undefined,
  handleTime: undefined,
  handleResult: undefined,
  regionCode: undefined,
  regionName: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  deviceId: [{ required: true, message: '设备ID不能为空', trigger: 'blur' }],
  deviceName: [{ required: true, message: '设备名称不能为空', trigger: 'blur' }],
  deviceCode: [{ required: true, message: '设备编码不能为空', trigger: 'blur' }],
  offlineStartTime: [{ required: true, message: '离线开始时间不能为空', trigger: 'blur' }],
  offlineEndure: [{ required: true, message: '离线时长(分钟)不能为空', trigger: 'blur' }],
  alertLevel: [{ required: true, message: '预警级别不能为空', trigger: 'blur' }],
  alertUserId: [{ required: true, message: '预警接收人ID不能为空', trigger: 'blur' }],
  alertUserName: [{ required: true, message: '预警接收人姓名不能为空', trigger: 'blur' }],
  alertPushTime: [{ required: true, message: '预警推送时间不能为空', trigger: 'blur' }],
  alertStatus: [{ required: true, message: '预警状态不能为空', trigger: 'blur' }],
  regionCode: [{ required: true, message: '所属行政区划代码不能为空', trigger: 'blur' }],
  regionName: [{ required: true, message: '所属行政区划名称不能为空', trigger: 'blur' }]
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
      formData.value = await DeviceOfflineAlertApi.getDeviceOfflineAlert(id)
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
    const data = formData.value as unknown as DeviceOfflineAlertVO
    if (formType.value === 'create') {
      await DeviceOfflineAlertApi.createDeviceOfflineAlert(data)
      message.success(t('common.createSuccess'))
    } else {
      await DeviceOfflineAlertApi.updateDeviceOfflineAlert(data)
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
    offlineAlertId: undefined,
    deviceId: undefined,
    deviceName: undefined,
    deviceCode: undefined,
    offlineStartTime: undefined,
    offlineEndure: undefined,
    alertLevel: undefined,
    alertUserId: undefined,
    alertUserName: undefined,
    alertPushTime: undefined,
    alertStatus: undefined,
    handleUserId: undefined,
    handleUserName: undefined,
    handleTime: undefined,
    handleResult: undefined,
    regionCode: undefined,
    regionName: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined
  }
  formRef.value?.resetFields()
}
</script>

<style scoped>
.custom-form {
  background-color: #fff;
  padding: 15px;
  border-radius: 8px;
}

::v-deep .el-form-item__label {
  font-weight: 500;
  color: #4b5563;
}

::v-deep .el-form-item {
  margin-bottom: 15px;
}
</style>
