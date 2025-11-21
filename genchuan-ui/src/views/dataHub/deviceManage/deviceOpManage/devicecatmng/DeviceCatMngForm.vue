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
      <el-form-item label="设备ID" prop="deviceId">
        <el-input v-model="formData.deviceId" placeholder="请输入设备ID" />
      </el-form-item>
      <el-form-item label="设备名称" prop="deviceName">
        <el-input v-model="formData.deviceName" placeholder="请输入设备名称" />
      </el-form-item>
      <el-form-item label="设备编码" prop="deviceCode">
        <el-input v-model="formData.deviceCode" placeholder="请输入设备编码" />
      </el-form-item>

      <div class="form-section">
        <h3 class="section-title">设备分类信息</h3>
        <el-form-item label="设备大类ID" prop="deviceMajorId">
          <el-input v-model="formData.deviceMajorId" placeholder="请输入设备大类ID" />
        </el-form-item>
        <el-form-item label="设备大类名称" prop="deviceMajorName">
          <el-input v-model="formData.deviceMajorName" placeholder="请输入设备大类名称" />
        </el-form-item>
        <el-form-item label="设备中类ID" prop="deviceMidId">
          <el-input v-model="formData.deviceMidId" placeholder="请输入设备中类ID" />
        </el-form-item>
        <el-form-item label="设备中类名称" prop="deviceMidName">
          <el-input v-model="formData.deviceMidName" placeholder="请输入设备中类名称" />
        </el-form-item>
        <el-form-item label="设备小类ID" prop="deviceMinorId">
          <el-input v-model="formData.deviceMinorId" placeholder="请输入设备小类ID" />
        </el-form-item>
        <el-form-item label="设备小类名称" prop="deviceMinorName">
          <el-input v-model="formData.deviceMinorName" placeholder="请输入设备小类名称" />
        </el-form-item>
      </div>

      <div class="form-section">
        <h3 class="section-title">分类操作信息</h3>
        <el-form-item label="分类时间" prop="catTime">
          <el-date-picker
            v-model="formData.catTime"
            type="datetime"
            value-format="x"
            placeholder="选择分类时间"
          />
        </el-form-item>
        <el-form-item label="分类人" prop="catUser">
          <el-input v-model="formData.catUser" placeholder="请输入分类人" />
        </el-form-item>
        <el-form-item label="分类状态" prop="catStatus">
          <el-radio-group v-model="formData.catStatus">
            <el-radio value="1">已分类</el-radio>
            <el-radio value="0">未分类</el-radio>
          </el-radio-group>
        </el-form-item>
      </div>

      <div class="form-section">
        <h3 class="section-title">调整信息</h3>
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
      </div>

      <div class="form-section">
        <h3 class="section-title">扩展信息</h3>
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
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  DeviceCatMngApi,
  DeviceCatMngVO
} from '@/api/dataHub/deviceManage/deviceOpManage/devicecatmng'

/** 设备分类管理 表单 */
defineOptions({ name: 'DeviceCatMngForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  catMngId: undefined,
  deviceId: undefined,
  deviceName: undefined,
  deviceCode: undefined,
  deviceMajorId: undefined,
  deviceMajorName: undefined,
  deviceMidId: undefined,
  deviceMidName: undefined,
  deviceMinorId: undefined,
  deviceMinorName: undefined,
  catTime: undefined,
  catUser: undefined,
  catStatus: undefined,
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
  deviceMajorId: [{ required: true, message: '设备大类ID不能为空', trigger: 'blur' }],
  deviceMajorName: [{ required: true, message: '设备大类名称不能为空', trigger: 'blur' }],
  deviceMidId: [{ required: true, message: '设备中类ID不能为空', trigger: 'blur' }],
  deviceMidName: [{ required: true, message: '设备中类名称不能为空', trigger: 'blur' }],
  deviceMinorId: [{ required: true, message: '设备小类ID不能为空', trigger: 'blur' }],
  deviceMinorName: [{ required: true, message: '设备小类名称不能为空', trigger: 'blur' }],
  catTime: [{ required: true, message: '分类时间不能为空', trigger: 'blur' }],
  catUser: [{ required: true, message: '分类人不能为空', trigger: 'blur' }],
  catStatus: [{ required: true, message: '分类状态不能为空', trigger: 'blur' }]
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
      formData.value = await DeviceCatMngApi.getDeviceCatMng(id)
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
    const data = formData.value as unknown as DeviceCatMngVO
    if (formType.value === 'create') {
      await DeviceCatMngApi.createDeviceCatMng(data)
      message.success(t('common.createSuccess'))
    } else {
      await DeviceCatMngApi.updateDeviceCatMng(data)
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
    catMngId: undefined,
    deviceId: undefined,
    deviceName: undefined,
    deviceCode: undefined,
    deviceMajorId: undefined,
    deviceMajorName: undefined,
    deviceMidId: undefined,
    deviceMidName: undefined,
    deviceMinorId: undefined,
    deviceMinorName: undefined,
    catTime: undefined,
    catUser: undefined,
    catStatus: undefined,
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

.form-section {
  margin: 15px 0;
  padding: 10px;
  background-color: #fff;
  border-radius: 6px;
  border-left: 3px solid #409eff;
}

.section-title {
  margin: 0 0 15px 0;
  padding-left: 5px;
  font-size: 14px;
  color: #409eff;
  border-bottom: 1px solid #f0f2f5;
  padding-bottom: 8px;
}

::v-deep .el-form-item {
  margin-bottom: 15px;
}

::v-deep .el-form-item__label {
  font-weight: 500;
  color: #4e5969;
}
</style>
