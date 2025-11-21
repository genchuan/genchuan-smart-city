<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="650px">
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
          <el-form-item label="网格ID" prop="gridId">
            <el-input v-model="formData.gridId" placeholder="请输入网格ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="网格名称" prop="gridName">
            <el-input v-model="formData.gridName" placeholder="请输入网格名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="网格编码" prop="gridCode">
            <el-input v-model="formData.gridCode" placeholder="请输入网格编码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联时间" prop="relTime">
            <el-date-picker
              v-model="formData.relTime"
              type="datetime"
              value-format="x"
              placeholder="选择关联时间"
              class="w-full"
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
              <el-radio :label="'1'" border>已关联</el-radio>
              <el-radio :label="'0'" border>未关联</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="调整时间" prop="adjustTime">
            <el-date-picker
              v-model="formData.adjustTime"
              type="datetime"
              value-format="x"
              placeholder="选择调整时间"
              class="w-full"
            />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="调整原因" prop="adjustReason">
            <el-input
              v-model="formData.adjustReason"
              placeholder="请输入调整原因"
              type="textarea"
            />
          </el-form-item>
        </el-col>
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
      </el-row>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { DeviceGridApi, DeviceGridVO } from '@/api/dataHub/deviceManage/deviceOpManage/devicegrid'

/** 设备关联网格 表单 */
defineOptions({ name: 'DeviceGridForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  deviceRelGridId: undefined,
  deviceId: undefined,
  deviceName: undefined,
  deviceCode: undefined,
  gridId: undefined,
  gridName: undefined,
  gridCode: undefined,
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
  gridId: [{ required: true, message: '网格ID不能为空', trigger: 'blur' }],
  gridName: [{ required: true, message: '网格名称不能为空', trigger: 'blur' }],
  gridCode: [{ required: true, message: '网格编码不能为空', trigger: 'blur' }],
  relTime: [{ required: true, message: '关联时间不能为空', trigger: 'blur' }],
  relUser: [{ required: true, message: '关联人不能为空', trigger: 'blur' }],
  relStatus: [{ required: true, message: '关联状态不能为空', trigger: 'change' }]
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
      formData.value = await DeviceGridApi.getDeviceGrid(id)
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
    const data = formData.value as unknown as DeviceGridVO
    if (formType.value === 'create') {
      await DeviceGridApi.createDeviceGrid(data)
      message.success(t('common.createSuccess'))
    } else {
      await DeviceGridApi.updateDeviceGrid(data)
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
    deviceRelGridId: undefined,
    deviceId: undefined,
    deviceName: undefined,
    deviceCode: undefined,
    gridId: undefined,
    gridName: undefined,
    gridCode: undefined,
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
  background-color: #f9fafb;
  border-radius: 8px;
}

::v-deep .el-form-item {
  margin-bottom: 15px;
}

::v-deep .el-form-item__label {
  font-weight: 500;
  color: #4e5969;
}

::v-deep .el-radio-group {
  display: flex;
  gap: 15px;
}
</style>
