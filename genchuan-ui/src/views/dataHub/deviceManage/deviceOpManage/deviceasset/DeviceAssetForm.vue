<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="600px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
      class="custom-form"
    >
      <el-form-item label="设备ID" prop="deviceId">
        <el-input v-model="formData.deviceId" placeholder="请输入设备ID" />
      </el-form-item>
      <el-form-item label="设备名称" prop="deviceName">
        <el-input v-model="formData.deviceName" placeholder="请输入设备名称" />
      </el-form-item>
      <el-form-item label="设备编码" prop="deviceCode">
        <el-input v-model="formData.deviceCode" placeholder="请输入设备编码" />
      </el-form-item>
      <el-form-item label="资产ID" prop="assetId">
        <el-input v-model="formData.assetId" placeholder="请输入资产ID" />
      </el-form-item>
      <el-form-item label="资产名称" prop="assetName">
        <el-input v-model="formData.assetName" placeholder="请输入资产名称" />
      </el-form-item>
      <el-form-item label="资产编码" prop="assetCode">
        <el-input v-model="formData.assetCode" placeholder="请输入资产编码" />
      </el-form-item>
      <el-form-item label="关联时间" prop="relTime">
        <el-date-picker
          v-model="formData.relTime"
          type="datetime"
          value-format="x"
          placeholder="选择关联时间"
        />
      </el-form-item>
      <el-form-item label="关联人" prop="relUser">
        <el-input v-model="formData.relUser" placeholder="请输入关联人" />
      </el-form-item>
      <el-form-item label="关联状态" prop="relStatus">
        <el-radio-group v-model="formData.relStatus">
          <el-radio value="1" label="已关联" />
          <el-radio value="0" label="未关联" />
        </el-radio-group>
      </el-form-item>
      <el-form-item label="调整时间" prop="adjustTime">
        <el-date-picker
          v-model="formData.adjustTime"
          type="datetime"
          value-format="x"
          placeholder="选择调整时间"
        />
      </el-form-item>
      <el-form-item label="调整人" prop="adjustUser">
        <el-input v-model="formData.adjustUser" placeholder="请输入调整人" />
      </el-form-item>
      <el-form-item label="调整原因" prop="adjustReason">
        <el-input v-model="formData.adjustReason" placeholder="请输入调整原因" type="textarea" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  DeviceAssetApi,
  DeviceAssetVO
} from '@/api/dataHub/deviceManage/deviceOpManage/deviceasset'

/** 设备关联资产 表单 */
defineOptions({ name: 'DeviceAssetForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  deviceRelAssetId: undefined,
  deviceId: undefined,
  deviceName: undefined,
  deviceCode: undefined,
  assetId: undefined,
  assetName: undefined,
  assetCode: undefined,
  relTime: undefined,
  relUser: undefined,
  relStatus: undefined,
  adjustTime: undefined,
  adjustUser: undefined,
  adjustReason: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  deviceId: [{ required: true, message: '设备ID不能为空', trigger: 'blur' }],
  deviceName: [{ required: true, message: '设备名称不能为空', trigger: 'blur' }],
  deviceCode: [{ required: true, message: '设备编码不能为空', trigger: 'blur' }],
  assetId: [{ required: true, message: '资产ID不能为空', trigger: 'blur' }],
  assetName: [{ required: true, message: '资产名称不能为空', trigger: 'blur' }],
  assetCode: [{ required: true, message: '资产编码不能为空', trigger: 'blur' }],
  relTime: [{ required: true, message: '关联时间不能为空', trigger: 'blur' }],
  relUser: [{ required: true, message: '关联人不能为空', trigger: 'blur' }],
  relStatus: [{ required: true, message: '关联状态不能为空', trigger: 'blur' }]
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
      formData.value = await DeviceAssetApi.getDeviceAsset(id)
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
    const data = formData.value as unknown as DeviceAssetVO
    if (formType.value === 'create') {
      await DeviceAssetApi.createDeviceAsset(data)
      message.success(t('common.createSuccess'))
    } else {
      await DeviceAssetApi.updateDeviceAsset(data)
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
    deviceRelAssetId: undefined,
    deviceId: undefined,
    deviceName: undefined,
    deviceCode: undefined,
    assetId: undefined,
    assetName: undefined,
    assetCode: undefined,
    relTime: undefined,
    relUser: undefined,
    relStatus: undefined,
    adjustTime: undefined,
    adjustUser: undefined,
    adjustReason: undefined,
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
  padding: 15px;
  background-color: #fff;
  border-radius: 8px;
}

::v-deep .el-form-item {
  margin-bottom: 18px;
}

::v-deep .el-form-item__label {
  color: #4b5563;
  font-weight: 500;
}

::v-deep .el-input__wrapper,
::v-deep .el-textarea__wrapper,
::v-deep .el-date-editor .el-input__wrapper {
  border-radius: 6px;
}
</style>
