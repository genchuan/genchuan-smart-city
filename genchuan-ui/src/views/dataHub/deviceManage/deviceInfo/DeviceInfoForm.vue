<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" :width="700">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
      class="custom-form"
    >
      <el-row :gutter="20">
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
          <el-form-item label="设备类型" prop="deviceType">
            <el-select v-model="formData.deviceType" placeholder="请选择设备类型">
              <el-option label="请选择字典生成" value="" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="设备型号" prop="deviceModel">
            <el-input v-model="formData.deviceModel" placeholder="请输入设备型号" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="设备规格" prop="deviceSpec">
            <el-input v-model="formData.deviceSpec" placeholder="请输入设备规格" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="生产厂商" prop="manufacturer">
            <el-input v-model="formData.manufacturer" placeholder="请输入生产厂商" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="生产日期" prop="productionDate">
            <el-date-picker
              v-model="formData.productionDate"
              type="date"
              value-format="x"
              placeholder="选择生产日期"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="采购日期" prop="purchaseDate">
            <el-date-picker
              v-model="formData.purchaseDate"
              type="date"
              value-format="x"
              placeholder="选择采购日期"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="安装日期" prop="installDate">
            <el-date-picker
              v-model="formData.installDate"
              type="date"
              value-format="x"
              placeholder="选择安装日期"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="质保期（月）" prop="warrantyPeriod">
            <el-input v-model="formData.warrantyPeriod" placeholder="请输入质保期（月）" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="设备状态" prop="deviceStatus">
            <el-radio-group v-model="formData.deviceStatus">
              <el-radio value="1">请选择字典生成</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <!-- 只保留常用字段，其他字段可折叠或通过滚动查看 -->
        <el-col :span="24">
          <el-collapse>
            <el-collapse-item title="更多信息">
              <el-row :gutter="20">
                <el-col :span="12">
                  <el-form-item label="运行状态" prop="runStatus">
                    <el-radio-group v-model="formData.runStatus">
                      <el-radio value="1">请选择字典生成</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="最后在线时间" prop="lastOnlineTime">
                    <el-date-picker
                      v-model="formData.lastOnlineTime"
                      type="date"
                      value-format="x"
                      placeholder="选择最后在线时间"
                    />
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="IP地址" prop="ipAddress">
                    <el-input v-model="formData.ipAddress" placeholder="请输入IP地址" />
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="MAC地址" prop="macAddress">
                    <el-input v-model="formData.macAddress" placeholder="请输入MAC地址" />
                  </el-form-item>
                </el-col>
              </el-row>
            </el-collapse-item>
          </el-collapse>
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
import { DeviceInfoApi, DeviceInfoVO } from '@/api/dataHub/deviceManage/deviceinfo'

/** 设备信息 表单 */
defineOptions({ name: 'DeviceInfoForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  deviceId: undefined,
  deviceName: undefined,
  deviceCode: undefined,
  deviceType: undefined,
  deviceModel: undefined,
  deviceSpec: undefined,
  manufacturer: undefined,
  productionDate: undefined,
  purchaseDate: undefined,
  installDate: undefined,
  warrantyPeriod: undefined,
  deviceStatus: undefined,
  runStatus: undefined,
  lastOnlineTime: undefined,
  ipAddress: undefined,
  macAddress: undefined
  // 其他字段保持不变
})
const formRules = reactive({
  deviceId: [{ required: true, message: '设备ID不能为空', trigger: 'blur' }],
  deviceName: [{ required: true, message: '设备名称不能为空', trigger: 'blur' }],
  deviceCode: [{ required: true, message: '设备编码不能为空', trigger: 'blur' }],
  deviceType: [{ required: true, message: '设备类型不能为空', trigger: 'change' }],
  deviceStatus: [{ required: true, message: '设备状态不能为空', trigger: 'blur' }]
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
      formData.value = await DeviceInfoApi.getDeviceInfo(id)
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
    const data = formData.value as unknown as DeviceInfoVO
    if (formType.value === 'create') {
      await DeviceInfoApi.createDeviceInfo(data)
      message.success(t('common.createSuccess'))
    } else {
      await DeviceInfoApi.updateDeviceInfo(data)
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
    deviceId: undefined,
    deviceName: undefined,
    deviceCode: undefined,
    deviceType: undefined,
    deviceModel: undefined,
    deviceSpec: undefined,
    manufacturer: undefined,
    productionDate: undefined,
    purchaseDate: undefined,
    installDate: undefined,
    warrantyPeriod: undefined,
    deviceStatus: undefined,
    runStatus: undefined,
    lastOnlineTime: undefined,
    ipAddress: undefined,
    macAddress: undefined
    // 其他字段保持不变
  }
  formRef.value?.resetFields()
}
</script>

<style scoped>
.custom-form {
  background-color: #f9fafb;
  padding: 20px;
  border-radius: 8px;
}

.el-collapse {
  margin-top: 10px;
  border: 1px solid #e5e7eb;
  border-radius: 4px;
}

.el-collapse-item__content {
  padding-bottom: 15px !important;
}
</style>
