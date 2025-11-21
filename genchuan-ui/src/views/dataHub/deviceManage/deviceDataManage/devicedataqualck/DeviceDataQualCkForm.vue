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
          <el-form-item label="质量检查ID" prop="qualCkId">
            <el-input v-model="formData.qualCkId" placeholder="请输入质量检查ID" />
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
          <el-form-item label="检查批次" prop="ckBatch">
            <el-input v-model="formData.ckBatch" placeholder="请输入检查批次" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="检查时间" prop="ckTime">
            <el-date-picker
              v-model="formData.ckTime"
              type="datetime"
              value-format="x"
              placeholder="选择检查时间"
              class="w-full"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="检查项" prop="ckItem">
            <el-input v-model="formData.ckItem" placeholder="请输入检查项" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="检查结果" prop="ckResult">
            <el-input v-model="formData.ckResult" placeholder="请输入检查结果" />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="错误描述" prop="errorDesc">
            <el-input
              v-model="formData.errorDesc"
              placeholder="请输入错误描述"
              type="textarea"
              rows="3"
            />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="整改建议" prop="rectifySugg">
            <el-input
              v-model="formData.rectifySugg"
              placeholder="请输入整改建议"
              type="textarea"
              rows="3"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="检查人" prop="ckUser">
            <el-input v-model="formData.ckUser" placeholder="请输入检查人" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="整改状态" prop="rectifyStatus">
            <el-radio-group v-model="formData.rectifyStatus">
              <el-radio value="0" class="mr-4">未整改</el-radio>
              <el-radio value="1">已整改</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="整改时间" prop="rectifyTime">
            <el-date-picker
              v-model="formData.rectifyTime"
              type="datetime"
              value-format="x"
              placeholder="选择整改时间"
              class="w-full"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="整改人" prop="rectifyUser">
            <el-input v-model="formData.rectifyUser" placeholder="请输入整改人" />
          </el-form-item>
        </el-col>
        <!-- 折叠面板显示扩展字段 -->
        <el-col :span="24">
          <el-collapse>
            <el-collapse-item title="扩展字段">
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
  DeviceDataQualCkApi,
  DeviceDataQualCkVO
} from '@/api/dataHub/deviceManage/deviceDataManage/devicedataqualck'

/** 设备数据质量检查 表单 */
defineOptions({ name: 'DeviceDataQualCkForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<DeviceDataQualCkVO>>({
  id: undefined,
  qualCkId: undefined,
  deviceId: undefined,
  deviceName: undefined,
  deviceCode: undefined,
  ckBatch: undefined,
  ckTime: undefined,
  ckItem: undefined,
  ckResult: undefined,
  errorDesc: undefined,
  rectifySugg: undefined,
  ckUser: undefined,
  rectifyStatus: '0', // 默认未整改
  rectifyTime: undefined,
  rectifyUser: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  deviceId: [{ required: true, message: '设备ID不能为空', trigger: 'blur' }],
  deviceName: [{ required: true, message: '设备名称不能为空', trigger: 'blur' }],
  deviceCode: [{ required: true, message: '设备编码不能为空', trigger: 'blur' }],
  ckBatch: [{ required: true, message: '检查批次不能为空', trigger: 'blur' }],
  ckTime: [{ required: true, message: '检查时间不能为空', trigger: 'blur' }],
  ckItem: [{ required: true, message: '检查项不能为空', trigger: 'blur' }],
  ckResult: [{ required: true, message: '检查结果不能为空', trigger: 'blur' }],
  ckUser: [{ required: true, message: '检查人不能为空', trigger: 'blur' }],
  rectifyStatus: [{ required: true, message: '整改状态不能为空', trigger: 'change' }]
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
      const data = await DeviceDataQualCkApi.getDeviceDataQualCk(id)
      // 转换时间格式为时间戳
      if (data.ckTime) data.ckTime = new Date(data.ckTime).getTime()
      if (data.rectifyTime) data.rectifyTime = new Date(data.rectifyTime).getTime()
      formData.value = data
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
    const data = formData.value as unknown as DeviceDataQualCkVO
    if (formType.value === 'create') {
      await DeviceDataQualCkApi.createDeviceDataQualCk(data)
      message.success(t('common.createSuccess'))
    } else {
      await DeviceDataQualCkApi.updateDeviceDataQualCk(data)
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
    qualCkId: undefined,
    deviceId: undefined,
    deviceName: undefined,
    deviceCode: undefined,
    ckBatch: undefined,
    ckTime: undefined,
    ckItem: undefined,
    ckResult: undefined,
    errorDesc: undefined,
    rectifySugg: undefined,
    ckUser: undefined,
    rectifyStatus: '0',
    rectifyTime: undefined,
    rectifyUser: undefined,
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

::v-deep .el-form-item {
  margin-bottom: 15px;
}

::v-deep .el-collapse {
  margin-top: 10px;
  border: 1px solid #f0f0f0;
  border-radius: 4px;
}

::v-deep .el-collapse-item__header {
  background-color: #f9fafb;
}
</style>
