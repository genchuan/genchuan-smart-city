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
      <el-form-item label="遥测数据ID" prop="telemetryDataId">
        <el-input v-model="formData.telemetryDataId" placeholder="请输入遥测数据ID" />
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
      <el-form-item label="遥测项名称" prop="telemetryItemName">
        <el-input v-model="formData.telemetryItemName" placeholder="请输入遥测项名称" />
      </el-form-item>
      <el-form-item label="遥测项代码" prop="telemetryItemCode">
        <el-input v-model="formData.telemetryItemCode" placeholder="请输入遥测项代码" />
      </el-form-item>
      <el-form-item label="遥测值" prop="telemetryValue">
        <el-input v-model="formData.telemetryValue" placeholder="请输入遥测值" />
      </el-form-item>
      <el-form-item label="数据类型" prop="dataType">
        <el-input v-model="formData.dataType" placeholder="请输入数据类型" />
      </el-form-item>
      <el-form-item label="采集时间" prop="collectTime">
        <el-date-picker
          v-model="formData.collectTime"
          type="datetime"
          value-format="x"
          placeholder="选择采集时间"
          class="w-full"
        />
      </el-form-item>
      <el-form-item label="数据来源" prop="dataSource">
        <el-input v-model="formData.dataSource" placeholder="请输入数据来源" />
      </el-form-item>
      <el-form-item label="存储周期(天)" prop="storageCycle">
        <el-input v-model.number="formData.storageCycle" placeholder="请输入存储周期(天)" />
      </el-form-item>
      <el-form-item label="过期状态" prop="expireStatus">
        <el-radio-group v-model="formData.expireStatus">
          <el-radio value="0" class="mr-4">未过期</el-radio>
          <el-radio value="1">已过期</el-radio>
        </el-radio-group>
      </el-form-item>

      <!-- 折叠面板展示扩展字段 -->
      <el-collapse v-model="activeNames" class="mt-4">
        <el-collapse-item name="1" title="扩展字段">
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
  DeviceTelemetryDataApi,
  DeviceTelemetryDataVO
} from '@/api/dataHub/deviceManage/deviceDataManage/devicetelemetrydata'

/** 设备遥测数据 表单 */
defineOptions({ name: 'DeviceTelemetryDataForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const activeNames = ref(['1']) // 折叠面板状态
const formData = ref({
  id: undefined,
  telemetryDataId: undefined,
  deviceId: undefined,
  deviceName: undefined,
  deviceCode: undefined,
  telemetryItemName: undefined,
  telemetryItemCode: undefined,
  telemetryValue: undefined,
  dataType: undefined,
  collectTime: undefined,
  dataSource: undefined,
  storageCycle: undefined,
  expireStatus: '0',
  createTimeSys: undefined,
  updateTimeSys: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  deviceId: [{ required: true, message: '设备ID不能为空', trigger: 'blur' }],
  deviceName: [{ required: true, message: '设备名称不能为空', trigger: 'blur' }],
  deviceCode: [{ required: true, message: '设备编码不能为空', trigger: 'blur' }],
  telemetryItemName: [{ required: true, message: '遥测项名称不能为空', trigger: 'blur' }],
  telemetryItemCode: [{ required: true, message: '遥测项代码不能为空', trigger: 'blur' }],
  telemetryValue: [{ required: true, message: '遥测值不能为空', trigger: 'blur' }],
  dataType: [{ required: true, message: '数据类型不能为空', trigger: 'blur' }],
  collectTime: [{ required: true, message: '采集时间不能为空', trigger: 'blur' }],
  dataSource: [{ required: true, message: '数据来源不能为空', trigger: 'blur' }],
  storageCycle: [{ required: true, message: '存储周期(天)不能为空', trigger: 'blur' }],
  expireStatus: [{ required: true, message: '过期状态不能为空', trigger: 'blur' }]
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
      formData.value = await DeviceTelemetryDataApi.getDeviceTelemetryData(id)
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
    const data = formData.value as unknown as DeviceTelemetryDataVO
    if (formType.value === 'create') {
      await DeviceTelemetryDataApi.createDeviceTelemetryData(data)
      message.success(t('common.createSuccess'))
    } else {
      await DeviceTelemetryDataApi.updateDeviceTelemetryData(data)
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
    telemetryDataId: undefined,
    deviceId: undefined,
    deviceName: undefined,
    deviceCode: undefined,
    telemetryItemName: undefined,
    telemetryItemCode: undefined,
    telemetryValue: undefined,
    dataType: undefined,
    collectTime: undefined,
    dataSource: undefined,
    storageCycle: undefined,
    expireStatus: '0',
    createTimeSys: undefined,
    updateTimeSys: undefined,
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

::v-deep .el-form-item {
  margin-bottom: 18px;
}

::v-deep .el-collapse {
  border: none;
}

::v-deep .el-collapse-item__content {
  padding-bottom: 0;
}
</style>
