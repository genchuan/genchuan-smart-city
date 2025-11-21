<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="600px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="130px"
      v-loading="formLoading"
      class="form-container"
    >
      <el-form-item label="数据ID" prop="realTimeDataId">
        <el-input v-model="formData.realTimeDataId" placeholder="请输入数据ID" class="!w-full" />
      </el-form-item>
      <el-form-item label="关联任务ID" prop="evalTaskId">
        <el-input v-model="formData.evalTaskId" placeholder="请输入关联任务ID" class="!w-full" />
      </el-form-item>
      <el-form-item label="关联任务名称" prop="evalTaskName">
        <el-input
          v-model="formData.evalTaskName"
          placeholder="请输入关联任务名称"
          class="!w-full"
        />
      </el-form-item>
      <el-form-item label="关联指标项ID" prop="idxItemId">
        <el-input v-model="formData.idxItemId" placeholder="请输入关联指标项ID" class="!w-full" />
      </el-form-item>
      <el-form-item label="关联指标项名称" prop="idxItemName">
        <el-input
          v-model="formData.idxItemName"
          placeholder="请输入关联指标项名称"
          class="!w-full"
        />
      </el-form-item>
      <el-form-item label="设备ID" prop="deviceId">
        <el-input v-model="formData.deviceId" placeholder="请输入设备ID" class="!w-full" />
      </el-form-item>
      <el-form-item label="设备名称" prop="deviceName">
        <el-input v-model="formData.deviceName" placeholder="请输入设备名称" class="!w-full" />
      </el-form-item>
      <el-form-item label="数据值" prop="dataValue">
        <el-input v-model="formData.dataValue" placeholder="请输入数据值" class="!w-full" />
      </el-form-item>
      <el-form-item label="数据时间" prop="dataTime">
        <el-date-picker
          v-model="formData.dataTime"
          type="datetime"
          value-format="x"
          placeholder="选择数据时间"
          class="!w-full"
        />
      </el-form-item>
      <el-form-item label="接入状态" prop="accessStatus">
        <el-radio-group v-model="formData.accessStatus" class="w-full">
          <el-radio value="SUCCESS">成功</el-radio>
          <el-radio value="FAIL">失败</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="失败原因" prop="failReason">
        <el-input v-model="formData.failReason" placeholder="接入失败时填写" class="!w-full" />
      </el-form-item>
      <el-form-item label="同步频率" prop="syncFrequency">
        <el-input
          v-model="formData.syncFrequency"
          placeholder="请输入同步频率（秒）"
          class="!w-full"
        />
      </el-form-item>
      <el-form-item label="创建人(业务)" prop="createUserBiz">
        <el-input v-model="formData.createUserBiz" placeholder="请输入创建人" class="!w-full" />
      </el-form-item>
      <el-form-item label="创建时间(业务)" prop="createTimeBiz">
        <el-date-picker
          v-model="formData.createTimeBiz"
          type="datetime"
          value-format="x"
          placeholder="选择创建时间"
          class="!w-full"
        />
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
  EvalRealTimeDataApi,
  EvalRealTimeDataVO
} from '@/api/dataHub/comprehensiveEval/dataCollectionMgmt/rtMonDataAccess'

/** 实时监测数据接入 表单 */
defineOptions({ name: 'EvalRealTimeDataForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改

const formData = ref({
  id: undefined,
  realTimeDataId: undefined,
  evalTaskId: undefined,
  evalTaskName: undefined,
  idxItemId: undefined,
  idxItemName: undefined,
  deviceId: undefined,
  deviceName: undefined,
  dataValue: undefined,
  dataTime: undefined,
  accessStatus: undefined,
  failReason: undefined,
  syncFrequency: undefined,
  createUserBiz: undefined,
  createTimeBiz: undefined
})

const formRules = reactive({
  evalTaskId: [{ required: true, message: '关联任务ID不能为空', trigger: 'blur' }],
  evalTaskName: [{ required: true, message: '关联任务名称不能为空', trigger: 'blur' }],
  idxItemId: [{ required: true, message: '关联指标项ID不能为空', trigger: 'blur' }],
  idxItemName: [{ required: true, message: '关联指标项名称不能为空', trigger: 'blur' }],
  deviceId: [{ required: true, message: '设备ID不能为空', trigger: 'blur' }],
  deviceName: [{ required: true, message: '设备名称不能为空', trigger: 'blur' }],
  dataValue: [{ required: true, message: '数据值不能为空', trigger: 'blur' }],
  dataTime: [{ required: true, message: '数据时间不能为空', trigger: 'blur' }],
  accessStatus: [{ required: true, message: '接入状态不能为空', trigger: 'blur' }],
  syncFrequency: [{ required: true, message: '同步频率不能为空', trigger: 'blur' }],
  createUserBiz: [{ required: true, message: '创建人(业务)不能为空', trigger: 'blur' }],
  createTimeBiz: [{ required: true, message: '创建时间(业务)不能为空', trigger: 'blur' }]
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
      formData.value = await EvalRealTimeDataApi.getEvalRealTimeData(id)
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
    const data = formData.value as unknown as EvalRealTimeDataVO
    if (formType.value === 'create') {
      await EvalRealTimeDataApi.createEvalRealTimeData(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalRealTimeDataApi.updateEvalRealTimeData(data)
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
    realTimeDataId: undefined,
    evalTaskId: undefined,
    evalTaskName: undefined,
    idxItemId: undefined,
    idxItemName: undefined,
    deviceId: undefined,
    deviceName: undefined,
    dataValue: undefined,
    dataTime: undefined,
    accessStatus: undefined,
    failReason: undefined,
    syncFrequency: undefined,
    createUserBiz: undefined,
    createTimeBiz: undefined
  }
  formRef.value?.resetFields()
}
</script>
<style scoped>
.form-container {
  display: flex;
  flex-direction: column;
  gap: 16px;
  padding: 10px;
}

.el-form-item {
  margin-bottom: 0;
}

.el-input,
.el-date-picker {
  border-radius: 6px !important;
}

.el-dialog__body {
  padding: 20px !important;
}

.el-dialog__header {
  background-color: #f8fafc;
  border-bottom: 1px solid #e2e8f0;
  margin: -20px -20px 0;
  padding: 16px 20px;
}
</style>
