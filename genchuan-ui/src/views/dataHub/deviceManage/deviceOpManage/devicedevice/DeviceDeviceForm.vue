<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="700px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
      class="form-container"
    >
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="关联ID" prop="deviceRelDeviceId">
            <el-input v-model="formData.deviceRelDeviceId" placeholder="请输入关联ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联类型" prop="relType">
            <el-input v-model="formData.relType" placeholder="请输入关联类型" />
          </el-form-item>
        </el-col>
      </el-row>

      <div class="form-section">
        <h3 class="section-title bg-blue-50 text-blue-600">主设备信息</h3>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="主设备ID" prop="mainDeviceId">
              <el-input v-model="formData.mainDeviceId" placeholder="请输入主设备ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="主设备名称" prop="mainDeviceName">
              <el-input v-model="formData.mainDeviceName" placeholder="请输入主设备名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="主设备编码" prop="mainDeviceCode">
              <el-input v-model="formData.mainDeviceCode" placeholder="请输入主设备编码" />
            </el-form-item>
          </el-col>
        </el-row>
      </div>

      <div class="form-section">
        <h3 class="section-title bg-purple-50 text-purple-600">从设备信息</h3>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="从设备ID" prop="slaveDeviceId">
              <el-input v-model="formData.slaveDeviceId" placeholder="请输入从设备ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="从设备名称" prop="slaveDeviceName">
              <el-input v-model="formData.slaveDeviceName" placeholder="请输入从设备名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="从设备编码" prop="slaveDeviceCode">
              <el-input v-model="formData.slaveDeviceCode" placeholder="请输入从设备编码" />
            </el-form-item>
          </el-col>
        </el-row>
      </div>

      <div class="form-section">
        <h3 class="section-title bg-green-50 text-green-600">关联信息</h3>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="关联时间" prop="relTime">
              <el-date-picker
                v-model="formData.relTime"
                type="date"
                value-format="x"
                placeholder="选择关联时间"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="关联人" prop="relUser">
              <el-input v-model="formData.relUser" placeholder="请输入关联人" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="关联状态" prop="relStatus">
              <el-radio-group v-model="formData.relStatus">
                <el-radio value="1">已关联</el-radio>
                <el-radio value="0">未关联</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
      </div>

      <div class="form-section">
        <h3 class="section-title bg-yellow-50 text-yellow-600">调整信息</h3>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="调整时间" prop="adjustTime">
              <el-date-picker
                v-model="formData.adjustTime"
                type="date"
                value-format="x"
                placeholder="选择调整时间"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="调整人" prop="adjustUser">
              <el-input v-model="formData.adjustUser" placeholder="请输入调整人" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="调整原因" prop="adjustReason">
              <el-input
                v-model="formData.adjustReason"
                placeholder="请输入调整原因"
                type="textarea"
                rows="3"
              />
            </el-form-item>
          </el-col>
        </el-row>
      </div>

      <div class="form-section">
        <h3 class="section-title bg-gray-50 text-gray-600">扩展信息</h3>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="分类扩展字段1" prop="extCat1">
              <el-input v-model="formData.extCat1" placeholder="请输入分类扩展字段1" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="分类扩展字段2" prop="extCat2">
              <el-input v-model="formData.extCat2" placeholder="请输入分类扩展字段2" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="通用扩展字段1" prop="extCommon1">
              <el-input v-model="formData.extCommon1" placeholder="请输入通用扩展字段1" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="通用扩展字段2" prop="extCommon2">
              <el-input v-model="formData.extCommon2" placeholder="请输入通用扩展字段2" />
            </el-form-item>
          </el-col>
        </el-row>
      </div>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  DeviceDeviceApi,
  DeviceDeviceVO
} from '@/api/dataHub/deviceManage/deviceOpManage/devicedevice'

/** 设备关联设备 表单 */
defineOptions({ name: 'DeviceDeviceForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  deviceRelDeviceId: undefined,
  mainDeviceId: undefined,
  mainDeviceName: undefined,
  mainDeviceCode: undefined,
  slaveDeviceId: undefined,
  slaveDeviceName: undefined,
  slaveDeviceCode: undefined,
  relType: undefined,
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
  mainDeviceId: [{ required: true, message: '主设备ID不能为空', trigger: 'blur' }],
  mainDeviceName: [{ required: true, message: '主设备名称不能为空', trigger: 'blur' }],
  mainDeviceCode: [{ required: true, message: '主设备编码不能为空', trigger: 'blur' }],
  slaveDeviceId: [{ required: true, message: '从设备ID不能为空', trigger: 'blur' }],
  slaveDeviceName: [{ required: true, message: '从设备名称不能为空', trigger: 'blur' }],
  slaveDeviceCode: [{ required: true, message: '从设备编码不能为空', trigger: 'blur' }],
  relType: [{ required: true, message: '关联类型不能为空', trigger: 'blur' }],
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
      formData.value = await DeviceDeviceApi.getDeviceDevice(id)
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
    const data = formData.value as unknown as DeviceDeviceVO
    if (formType.value === 'create') {
      await DeviceDeviceApi.createDeviceDevice(data)
      message.success(t('common.createSuccess'))
    } else {
      await DeviceDeviceApi.updateDeviceDevice(data)
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
    deviceRelDeviceId: undefined,
    mainDeviceId: undefined,
    mainDeviceName: undefined,
    mainDeviceCode: undefined,
    slaveDeviceId: undefined,
    slaveDeviceName: undefined,
    slaveDeviceCode: undefined,
    relType: undefined,
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
.form-container {
  padding: 15px;
}

.form-section {
  margin: 15px 0;
  padding: 10px;
  border-radius: 6px;
  background-color: #f9fafb;
}

.section-title {
  margin: -10px -10px 15px;
  padding: 8px 15px;
  font-size: 14px;
  font-weight: 500;
  border-radius: 6px 6px 0 0;
}

::v-deep .el-form-item {
  margin-bottom: 15px;
}

::v-deep .el-form-item__label {
  font-weight: 400;
}

::v-deep .el-input__wrapper,
::v-deep .el-select__wrapper,
::v-deep .el-date-editor .el-input__wrapper {
  border-radius: 4px;
}
</style>
