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
      <el-form-item label="设备ID" prop="deviceId">
        <el-input v-model="formData.deviceId" placeholder="请输入设备ID" />
      </el-form-item>
      <el-form-item label="设备名称" prop="deviceName">
        <el-input v-model="formData.deviceName" placeholder="请输入设备名称" />
      </el-form-item>
      <el-form-item label="设备编码" prop="deviceCode">
        <el-input v-model="formData.deviceCode" placeholder="请输入设备编码" />
      </el-form-item>
      <el-form-item label="属性名称" prop="attrName">
        <el-input v-model="formData.attrName" placeholder="请输入属性名称" />
      </el-form-item>
      <el-form-item label="属性代码" prop="attrCode">
        <el-input v-model="formData.attrCode" placeholder="请输入属性代码" />
      </el-form-item>
      <el-form-item label="属性值" prop="attrValue">
        <el-input v-model="formData.attrValue" placeholder="请输入属性值" />
      </el-form-item>
      <el-form-item label="数据类型" prop="dataType">
        <el-input v-model="formData.dataType" placeholder="请输入数据类型" />
      </el-form-item>
      <el-form-item label="是否必选" prop="isRequired">
        <el-select v-model="formData.isRequired" placeholder="请选择是否必选">
          <el-option label="是" value="是" />
          <el-option label="否" value="否" />
        </el-select>
      </el-form-item>
      <el-form-item label="属性说明" prop="attrDesc">
        <el-input
          v-model="formData.attrDesc"
          placeholder="请输入属性说明"
          type="textarea"
          rows="3"
        />
      </el-form-item>
      <el-form-item label="配置时间" prop="cfgTime">
        <el-date-picker
          v-model="formData.cfgTime"
          type="datetime"
          value-format="x"
          placeholder="选择配置时间"
        />
      </el-form-item>
      <el-form-item label="配置人" prop="cfgUser">
        <el-input v-model="formData.cfgUser" placeholder="请输入配置人" />
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
  DeviceClientAttrApi,
  DeviceClientAttrVO
} from '@/api/dataHub/deviceManage/deviceDataManage/deviceclientattr'

/** 设备客户端属性配置 表单 */
defineOptions({ name: 'DeviceClientAttrForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  clientAttrCfgId: undefined,
  deviceId: undefined,
  deviceName: undefined,
  deviceCode: undefined,
  attrName: undefined,
  attrCode: undefined,
  attrValue: undefined,
  dataType: undefined,
  isRequired: undefined,
  attrDesc: undefined,
  cfgTime: undefined,
  cfgUser: undefined,
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
  attrName: [{ required: true, message: '属性名称不能为空', trigger: 'blur' }],
  attrCode: [{ required: true, message: '属性代码不能为空', trigger: 'blur' }],
  attrValue: [{ required: true, message: '属性值不能为空', trigger: 'blur' }],
  dataType: [{ required: true, message: '数据类型不能为空', trigger: 'blur' }],
  isRequired: [{ required: true, message: '是否必选不能为空', trigger: 'change' }],
  cfgTime: [{ required: true, message: '配置时间不能为空', trigger: 'change' }],
  cfgUser: [{ required: true, message: '配置人不能为空', trigger: 'blur' }]
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
      formData.value = await DeviceClientAttrApi.getDeviceClientAttr(id)
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
    const data = formData.value as unknown as DeviceClientAttrVO
    if (formType.value === 'create') {
      await DeviceClientAttrApi.createDeviceClientAttr(data)
      message.success(t('common.createSuccess'))
    } else {
      await DeviceClientAttrApi.updateDeviceClientAttr(data)
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
    clientAttrCfgId: undefined,
    deviceId: undefined,
    deviceName: undefined,
    deviceCode: undefined,
    attrName: undefined,
    attrCode: undefined,
    attrValue: undefined,
    dataType: undefined,
    isRequired: undefined,
    attrDesc: undefined,
    cfgTime: undefined,
    cfgUser: undefined,
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
  padding: 15px 0;
}

::v-deep .el-form-item {
  margin-bottom: 18px;
}

::v-deep .el-form-item__label {
  font-weight: 500;
  color: #4b5563;
}

::v-deep .el-input__wrapper {
  border-radius: 6px;
}
</style>
