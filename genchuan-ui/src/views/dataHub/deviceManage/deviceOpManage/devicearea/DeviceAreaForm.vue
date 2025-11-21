<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="800px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
      class="custom-form"
    >
      <!-- 分两列布局 -->
      <div class="form-grid">
        <el-form-item label="设备ID" prop="deviceId">
          <el-input v-model="formData.deviceId" placeholder="请输入设备ID" />
        </el-form-item>
        <el-form-item label="设备名称" prop="deviceName">
          <el-input v-model="formData.deviceName" placeholder="请输入设备名称" />
        </el-form-item>
        <el-form-item label="设备编码" prop="deviceCode">
          <el-input v-model="formData.deviceCode" placeholder="请输入设备编码" />
        </el-form-item>
        <el-form-item label="关联ID" prop="deviceRelRegionId">
          <el-input v-model="formData.deviceRelRegionId" placeholder="请输入关联ID" />
        </el-form-item>
        <el-form-item label="行政区划代码" prop="regionCode">
          <el-input v-model="formData.regionCode" placeholder="请输入行政区划代码" />
        </el-form-item>
        <el-form-item label="行政区划名称" prop="regionName">
          <el-input v-model="formData.regionName" placeholder="请输入行政区划名称" />
        </el-form-item>
        <el-form-item label="行政区划级别" prop="regionLevel">
          <el-input v-model="formData.regionLevel" placeholder="请输入行政区划级别" />
        </el-form-item>
        <el-form-item label="关联时间" prop="relTime">
          <el-date-picker
            v-model="formData.relTime"
            type="date"
            value-format="x"
            placeholder="选择关联时间"
          />
        </el-form-item>
        <el-form-item label="关联人" prop="relUser">
          <el-input v-model="formData.relUser" placeholder="请输入关联人" />
        </el-form-item>
        <el-form-item label="关联状态" prop="relStatus">
          <el-radio-group v-model="formData.relStatus">
            <el-radio value="1">已关联</el-radio>
            <el-radio value="0">未关联</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="调整时间" prop="adjustTime">
          <el-date-picker
            v-model="formData.adjustTime"
            type="date"
            value-format="x"
            placeholder="选择调整时间"
          />
        </el-form-item>
        <el-form-item label="调整人" prop="adjustUser">
          <el-input v-model="formData.adjustUser" placeholder="请输入调整人" />
        </el-form-item>
      </div>

      <!-- 单独一行的字段 -->
      <el-form-item label="调整原因" prop="adjustReason">
        <el-input v-model="formData.adjustReason" placeholder="请输入调整原因" type="textarea" />
      </el-form-item>

      <!-- 扩展字段折叠面板 -->
      <el-collapse v-model="activeNames" class="mt-4">
        <el-collapse-item title="扩展字段" name="1">
          <div class="form-grid">
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
          </div>
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
import { DeviceAreaApi, DeviceAreaVO } from '@/api/dataHub/deviceManage/deviceOpManage/devicearea'

/** 设备关联行政区划 表单 */
defineOptions({ name: 'DeviceAreaForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const activeNames = ref([]) // 折叠面板默认状态

const formData = ref({
  id: undefined,
  deviceRelRegionId: undefined,
  deviceId: undefined,
  deviceName: undefined,
  deviceCode: undefined,
  regionCode: undefined,
  regionName: undefined,
  regionLevel: undefined,
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
  regionCode: [{ required: true, message: '行政区划代码不能为空', trigger: 'blur' }],
  regionName: [{ required: true, message: '行政区划名称不能为空', trigger: 'blur' }],
  regionLevel: [{ required: true, message: '行政区划级别不能为空', trigger: 'blur' }],
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
      formData.value = await DeviceAreaApi.getDeviceArea(id)
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
    const data = formData.value as unknown as DeviceAreaVO
    if (formType.value === 'create') {
      await DeviceAreaApi.createDeviceArea(data)
      message.success(t('common.createSuccess'))
    } else {
      await DeviceAreaApi.updateDeviceArea(data)
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
    deviceRelRegionId: undefined,
    deviceId: undefined,
    deviceName: undefined,
    deviceCode: undefined,
    regionCode: undefined,
    regionName: undefined,
    regionLevel: undefined,
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
  padding: 15px 0;
}

.form-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 15px;
}

::v-deep .el-form-item__label {
  color: #666;
  font-weight: 500;
}

::v-deep .el-collapse {
  border: 1px solid #e5e7eb;
  border-radius: 4px;
}

::v-deep .el-collapse-item__header {
  background-color: #f9fafb;
  color: #4b5563;
}

::v-deep .el-collapse-item__content {
  padding: 15px;
  background-color: #fff;
}
</style>
