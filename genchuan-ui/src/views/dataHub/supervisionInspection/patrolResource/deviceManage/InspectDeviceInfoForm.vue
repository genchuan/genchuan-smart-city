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
          <el-form-item label="设备ID" prop="deviceId">
            <el-input v-model="formData.deviceId" placeholder="请输入设备ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="设备编码" prop="deviceCode">
            <el-input v-model="formData.deviceCode" placeholder="请输入设备编码" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="设备名称" prop="deviceName" required>
            <el-input v-model="formData.deviceName" placeholder="请输入设备名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="设备类型" prop="deviceType">
            <el-select v-model="formData.deviceType" placeholder="请选择设备类型">
              <el-option label="请选择字典生成" value="" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="所属区域代码" prop="areaCode">
            <el-input v-model="formData.areaCode" placeholder="请输入所属区域代码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属区域名称" prop="areaName">
            <el-input v-model="formData.areaName" placeholder="请输入所属区域名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="所属网格ID" prop="gridId">
            <el-input v-model="formData.gridId" placeholder="请输入所属网格ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属网格名称" prop="gridName">
            <el-input v-model="formData.gridName" placeholder="请输入所属网格名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="关联人员ID" prop="personId">
            <el-input v-model="formData.personId" placeholder="请输入关联人员ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联人员姓名" prop="personName">
            <el-input v-model="formData.personName" placeholder="请输入关联人员姓名" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="设备状态" prop="deviceStatus">
            <el-radio-group v-model="formData.deviceStatus">
              <el-radio value="正常">正常</el-radio>
              <el-radio value="异常">异常</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="安装时间" prop="installTime">
            <el-date-picker
              v-model="formData.installTime"
              type="date"
              value-format="x"
              placeholder="选择安装时间"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="保修截止时间" prop="warrantyEndTime">
            <el-date-picker
              v-model="formData.warrantyEndTime"
              type="date"
              value-format="x"
              placeholder="选择保修截止时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分类扩展字段1" prop="extCat1">
            <el-input v-model="formData.extCat1" placeholder="请输入分类扩展字段1" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
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
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="通用扩展字段2" prop="extCommon2">
            <el-input v-model="formData.extCommon2" placeholder="请输入通用扩展字段2" />
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
  InspectDeviceInfoApi,
  InspectDeviceInfoVO
} from '@/api/dataHub/supervisionInspection/patrolResource/deviceManage'

/** 巡查巡检设备信息 表单 */
defineOptions({ name: 'InspectDeviceInfoForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<InspectDeviceInfoVO>>({
  id: undefined,
  deviceId: undefined,
  deviceCode: undefined,
  deviceName: undefined,
  deviceType: undefined,
  areaCode: undefined,
  areaName: undefined,
  gridId: undefined,
  gridName: undefined,
  personId: undefined,
  personName: undefined,
  deviceStatus: undefined,
  installTime: undefined,
  warrantyEndTime: undefined,
  createUser: undefined,
  bizCreateTime: undefined,
  updateUser: undefined,
  bizUpdateTime: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  deviceName: [{ required: true, message: '请输入设备名称', trigger: 'blur' }]
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
      formData.value = await InspectDeviceInfoApi.getInspectDeviceInfo(id)
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
    const data = formData.value as unknown as InspectDeviceInfoVO
    if (formType.value === 'create') {
      await InspectDeviceInfoApi.createInspectDeviceInfo(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectDeviceInfoApi.updateInspectDeviceInfo(data)
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
    deviceCode: undefined,
    deviceName: undefined,
    deviceType: undefined,
    areaCode: undefined,
    areaName: undefined,
    gridId: undefined,
    gridName: undefined,
    personId: undefined,
    personName: undefined,
    deviceStatus: undefined,
    installTime: undefined,
    warrantyEndTime: undefined,
    createUser: undefined,
    bizCreateTime: undefined,
    updateUser: undefined,
    bizUpdateTime: undefined,
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
  background-color: #f9fafb;
  padding: 20px;
  border-radius: 8px;
}

::v-deep .el-form-item__label {
  font-weight: 500;
  color: #4e5969;
}

::v-deep .el-form-item {
  margin-bottom: 16px;
}
</style>
