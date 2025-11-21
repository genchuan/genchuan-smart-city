<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="600px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
      class="form-container"
    >
      <el-form-item label="状态ID" prop="realTimeStatusId">
        <el-input v-model="formData.realTimeStatusId" placeholder="请输入状态ID" />
      </el-form-item>
      <el-form-item label="设备ID" prop="deviceId">
        <el-input v-model="formData.deviceId" placeholder="请输入设备ID" />
      </el-form-item>
      <el-form-item label="设备名称" prop="deviceName">
        <el-input v-model="formData.deviceName" placeholder="请输入设备名称" />
      </el-form-item>
      <el-form-item label="设备编码" prop="deviceCode">
        <el-input v-model="formData.deviceCode" placeholder="请输入设备编码" />
      </el-form-item>
      <el-form-item label="运行状态" prop="runStatus">
        <el-input v-model="formData.runStatus" placeholder="请输入运行状态" />
      </el-form-item>
      <el-form-item label="最近上报时间" prop="lastRptTime">
        <el-date-picker
          v-model="formData.lastRptTime"
          type="datetime"
          value-format="x"
          placeholder="选择最近上报时间"
          class="w-full"
        />
      </el-form-item>
      <el-form-item label="实时参数1" prop="realTimeParam1">
        <el-input v-model="formData.realTimeParam1" placeholder="请输入实时参数1" />
      </el-form-item>
      <el-form-item label="实时参数2" prop="realTimeParam2">
        <el-input v-model="formData.realTimeParam2" placeholder="请输入实时参数2" />
      </el-form-item>
      <el-form-item label="状态更新时间" prop="statusUpdateTime">
        <el-date-picker
          v-model="formData.statusUpdateTime"
          type="datetime"
          value-format="x"
          placeholder="选择状态更新时间"
          class="w-full"
        />
      </el-form-item>

      <el-collapse v-model="activeNames" class="mt-4">
        <el-collapse-item name="1" title="扩展信息">
          <el-form-item label="所属行政区划代码" prop="regionCode">
            <el-input v-model="formData.regionCode" placeholder="请输入所属行政区划代码" />
          </el-form-item>
          <el-form-item label="所属行政区划名称" prop="regionName">
            <el-input v-model="formData.regionName" placeholder="请输入所属行政区划名称" />
          </el-form-item>
          <el-form-item label="所属网格ID" prop="gridId">
            <el-input v-model="formData.gridId" placeholder="请输入所属网格ID" />
          </el-form-item>
          <el-form-item label="所属网格名称" prop="gridName">
            <el-input v-model="formData.gridName" placeholder="请输入所属网格名称" />
          </el-form-item>
          <el-form-item label="分类扩展字段1" prop="extCat1">
            <el-input v-model="formData.extCat1" placeholder="请输入分类扩展字段1" />
          </el-form-item>
          <el-form-item label="分类扩展字段2" prop="extCat2">
            <el-input v-model="formData.extCat2" placeholder="请输入分类扩展字段2" />
          </el-form-item>
          <el-form-item label="通用扩展字段1" prop="extCommon1">
            <el-input v-model="formData.extCommon1" placeholder="请输入通用扩展字段1" />
          </el-form-item>
          <el-form-item label="通用扩展字段2" prop="extCommon2">
            <el-input v-model="formData.extCommon2" placeholder="请输入通用扩展字段2" />
          </el-form-item>
        </el-collapse-item>
      </el-collapse>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  DeviceRealTimeStatusApi,
  DeviceRealTimeStatusVO
} from '@/api/dataHub/deviceManage/daviceStatusMonitor/devicerealtimestatus'

/** 设备实时状态 表单 */
defineOptions({ name: 'DeviceRealTimeStatusForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const activeNames = ref(['1']) // 折叠面板默认状态
const formData = ref({
  id: undefined,
  realTimeStatusId: undefined,
  deviceId: undefined,
  deviceName: undefined,
  deviceCode: undefined,
  runStatus: undefined,
  lastRptTime: undefined,
  realTimeParam1: undefined,
  realTimeParam2: undefined,
  statusUpdateTime: undefined,
  regionCode: undefined,
  regionName: undefined,
  gridId: undefined,
  gridName: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  deviceId: [{ required: true, message: '设备ID不能为空', trigger: 'blur' }],
  deviceName: [{ required: true, message: '设备名称不能为空', trigger: 'blur' }],
  deviceCode: [{ required: true, message: '设备编码不能为空', trigger: 'blur' }],
  runStatus: [{ required: true, message: '运行状态不能为空', trigger: 'blur' }],
  lastRptTime: [{ required: true, message: '最近上报时间不能为空', trigger: 'blur' }],
  statusUpdateTime: [{ required: true, message: '状态更新时间不能为空', trigger: 'blur' }]
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
      formData.value = await DeviceRealTimeStatusApi.getDeviceRealTimeStatus(id)
    } finally {
      formLoading.value = false
    }
  }
}
defineExpose({ open }) // 提供 open 方法，用于打开弹窗

/** 提交表单 */
const emit = defineEmits(['success']) // 定义 success 事件
const submitForm = async () => {
  // 校验表单
  await formRef.value.validate()
  // 提交请求
  formLoading.value = true
  try {
    const data = formData.value as unknown as DeviceRealTimeStatusVO
    if (formType.value === 'create') {
      await DeviceRealTimeStatusApi.createDeviceRealTimeStatus(data)
      message.success(t('common.createSuccess'))
    } else {
      await DeviceRealTimeStatusApi.updateDeviceRealTimeStatus(data)
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
    realTimeStatusId: undefined,
    deviceId: undefined,
    deviceName: undefined,
    deviceCode: undefined,
    runStatus: undefined,
    lastRptTime: undefined,
    realTimeParam1: undefined,
    realTimeParam2: undefined,
    statusUpdateTime: undefined,
    regionCode: undefined,
    regionName: undefined,
    gridId: undefined,
    gridName: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined
  }
  formRef.value?.resetFields()
}
</script>

<style scoped>
.form-container {
  padding: 10px 0;
}

::v-deep .el-collapse-item__content {
  padding-bottom: 0 !important;
}
</style>
