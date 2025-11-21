<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="700px">
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
          <el-form-item label="空间数据ID" prop="deviceSpatialId">
            <el-input v-model="formData.deviceSpatialId" placeholder="请输入空间数据ID" />
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
          <el-form-item label="坐标系类型" prop="coordSystem">
            <el-input v-model="formData.coordSystem" placeholder="请输入坐标系类型" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="空间数据格式" prop="spatialDataFormat">
            <el-input v-model="formData.spatialDataFormat" placeholder="请输入空间数据格式" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="坐标X(度)" prop="coordX">
            <el-input v-model.number="formData.coordX" placeholder="请输入坐标X(度)" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="坐标Y(度)" prop="coordY">
            <el-input v-model.number="formData.coordY" placeholder="请输入坐标Y(度)" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="高程(米)" prop="elevation">
            <el-input v-model.number="formData.elevation" placeholder="请输入高程(米)" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="定位精度(米)" prop="positionAccuracy">
            <el-input v-model.number="formData.positionAccuracy" placeholder="请输入定位精度(米)" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="数据来源" prop="dataSource">
            <el-input v-model="formData.dataSource" placeholder="请输入数据来源" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="录入人" prop="inputUser">
            <el-input v-model="formData.inputUser" placeholder="请输入录入人" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="录入时间" prop="inputTime">
            <el-date-picker
              v-model="formData.inputTime"
              type="datetime"
              value-format="x"
              placeholder="选择录入时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="更新人" prop="updateUser">
            <el-input v-model="formData.updateUser" placeholder="请输入更新人" />
          </el-form-item>
        </el-col>

        <!-- 扩展字段使用折叠面板 -->
        <el-col :span="24">
          <el-collapse v-model="activeNames" class="mt-2">
            <el-collapse-item name="1" title="扩展字段">
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
import {
  DeviceSpatialDataApi,
  DeviceSpatialDataVO
} from '@/api/dataHub/deviceManage/deviceDataManage/devicespatialdata'

/** 设备空间数据 表单 */
defineOptions({ name: 'DeviceSpatialDataForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const activeNames = ref(['1']) // 折叠面板默认展开项

const formData = ref<Partial<DeviceSpatialDataVO>>({
  id: undefined,
  deviceSpatialId: undefined,
  deviceId: undefined,
  deviceName: undefined,
  deviceCode: undefined,
  coordSystem: undefined,
  coordX: undefined,
  coordY: undefined,
  elevation: undefined,
  positionAccuracy: undefined,
  spatialDataFormat: undefined,
  dataSource: undefined,
  inputTime: undefined,
  inputUser: undefined,
  updateTimeSys: undefined,
  updateUser: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})

const formRules = reactive({
  deviceId: [{ required: true, message: '设备ID不能为空', trigger: 'blur' }],
  deviceName: [{ required: true, message: '设备名称不能为空', trigger: 'blur' }],
  deviceCode: [{ required: true, message: '设备编码不能为空', trigger: 'blur' }],
  coordSystem: [{ required: true, message: '坐标系类型不能为空', trigger: 'blur' }],
  coordX: [{ required: true, message: '坐标X(度)不能为空', trigger: 'blur' }],
  coordY: [{ required: true, message: '坐标Y(度)不能为空', trigger: 'blur' }],
  positionAccuracy: [{ required: true, message: '定位精度(米)不能为空', trigger: 'blur' }],
  spatialDataFormat: [{ required: true, message: '空间数据格式不能为空', trigger: 'blur' }],
  inputTime: [{ required: true, message: '录入时间不能为空', trigger: 'blur' }],
  inputUser: [{ required: true, message: '录入人不能为空', trigger: 'blur' }]
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
      formData.value = await DeviceSpatialDataApi.getDeviceSpatialData(id)
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
    const data = formData.value as unknown as DeviceSpatialDataVO
    if (formType.value === 'create') {
      await DeviceSpatialDataApi.createDeviceSpatialData(data)
      message.success(t('common.createSuccess'))
    } else {
      await DeviceSpatialDataApi.updateDeviceSpatialData(data)
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
    deviceSpatialId: undefined,
    deviceId: undefined,
    deviceName: undefined,
    deviceCode: undefined,
    coordSystem: undefined,
    coordX: undefined,
    coordY: undefined,
    elevation: undefined,
    positionAccuracy: undefined,
    spatialDataFormat: undefined,
    dataSource: undefined,
    inputTime: undefined,
    inputUser: undefined,
    updateTimeSys: undefined,
    updateUser: undefined,
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
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
}

::v-deep .el-form-item__label {
  font-weight: 500;
  color: #555;
}

::v-deep .el-collapse {
  margin-top: 10px;
  border: 1px solid #f0f0f0;
  border-radius: 4px;
}

::v-deep .el-collapse-item__header {
  background-color: #f9fafb;
  border-bottom: 1px solid #f0f0f0;
}
</style>
