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
          <el-form-item label="关联ID" prop="deviceRelMonCompId">
            <el-input v-model="formData.deviceRelMonCompId" placeholder="请输入关联ID" />
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
          <el-form-item label="监测部件ID" prop="monCompId">
            <el-input v-model="formData.monCompId" placeholder="请输入监测部件ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="监测部件名称" prop="monCompName">
            <el-input v-model="formData.monCompName" placeholder="请输入监测部件名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="监测部件编码" prop="monCompCode">
            <el-input v-model="formData.monCompCode" placeholder="请输入监测部件编码" />
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
              <el-radio value="1">已关联</el-radio>
              <el-radio value="0">未关联</el-radio>
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
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  DeviceMonCompApi,
  DeviceMonCompVO
} from '@/api/dataHub/deviceManage/deviceOpManage/devicemoncomp'

/** 设备关联监测部件 表单 */
defineOptions({ name: 'DeviceMonCompForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<DeviceMonCompVO>>({
  id: undefined,
  deviceRelMonCompId: undefined,
  deviceId: undefined,
  deviceName: undefined,
  deviceCode: undefined,
  monCompId: undefined,
  monCompName: undefined,
  monCompCode: undefined,
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
  monCompId: [{ required: true, message: '监测部件ID不能为空', trigger: 'blur' }],
  monCompName: [{ required: true, message: '监测部件名称不能为空', trigger: 'blur' }],
  monCompCode: [{ required: true, message: '监测部件编码不能为空', trigger: 'blur' }],
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
      const data = await DeviceMonCompApi.getDeviceMonComp(id)
      formData.value = {
        ...data,
        relTime: data.relTime ? new Date(data.relTime).getTime() : undefined,
        adjustTime: data.adjustTime ? new Date(data.adjustTime).getTime() : undefined
      }
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
    const data = {
      ...formData.value,
      relTime: formData.value.relTime ? new Date(Number(formData.value.relTime)) : undefined,
      adjustTime: formData.value.adjustTime
        ? new Date(Number(formData.value.adjustTime))
        : undefined
    } as DeviceMonCompVO

    if (formType.value === 'create') {
      await DeviceMonCompApi.createDeviceMonComp(data)
      message.success(t('common.createSuccess'))
    } else {
      await DeviceMonCompApi.updateDeviceMonComp(data)
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
    deviceRelMonCompId: undefined,
    deviceId: undefined,
    deviceName: undefined,
    deviceCode: undefined,
    monCompId: undefined,
    monCompName: undefined,
    monCompCode: undefined,
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
  padding: 10px 0;
}

::v-deep .el-form-item {
  margin-bottom: 15px;
}

::v-deep .el-input__wrapper {
  border-radius: 4px;
}

::v-deep .el-date-editor {
  width: 100%;
}
</style>
