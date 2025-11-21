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
      <el-form-item label="上报数据ID" prop="platformRptDataId">
        <el-input
          v-model="formData.platformRptDataId"
          placeholder="请输入上报数据ID"
          class="!w-full"
        />
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
      <el-form-item label="评价对象ID" prop="evalObjectId">
        <el-input v-model="formData.evalObjectId" placeholder="请输入评价对象ID" class="!w-full" />
      </el-form-item>
      <el-form-item label="评价对象名称" prop="evalObjectName">
        <el-input
          v-model="formData.evalObjectName"
          placeholder="请输入评价对象名称"
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
      <el-form-item label="数据值" prop="dataValue">
        <el-input v-model="formData.dataValue" placeholder="请输入数据值" class="!w-full" />
      </el-form-item>
      <el-form-item label="数据单位" prop="dataUnit">
        <el-input
          v-model="formData.dataUnit"
          placeholder="请输入数据单位（如：个、kg）"
          class="!w-full"
        />
      </el-form-item>
      <el-form-item label="上报时间" prop="rptTime">
        <el-date-picker
          v-model="formData.rptTime"
          type="datetime"
          value-format="x"
          placeholder="选择上报时间"
          class="!w-full"
        />
      </el-form-item>
      <el-form-item label="上报人ID" prop="rptUserId">
        <el-input v-model="formData.rptUserId" placeholder="请输入上报人ID" class="!w-full" />
      </el-form-item>
      <el-form-item label="上报人姓名" prop="rptUserName">
        <el-input v-model="formData.rptUserName" placeholder="请输入上报人姓名" class="!w-full" />
      </el-form-item>
      <el-form-item label="数据状态" prop="dataStatus">
        <el-radio-group v-model="formData.dataStatus" class="w-full">
          <el-radio value="VERIFIED">已验证</el-radio>
          <!--          <el-radio value="0">未验证</el-radio>-->
        </el-radio-group>
      </el-form-item>
      <el-form-item label="校验失败原因" prop="verifyFailReason">
        <el-input
          v-model="formData.verifyFailReason"
          placeholder="数据无效时填写"
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
  EvalPlatformRptDataApi,
  EvalPlatformRptDataVO
} from '@/api/dataHub/comprehensiveEval/dataCollectionMgmt/platReportDataUpload'

/** 平台上报数据 表单 */
defineOptions({ name: 'EvalPlatformRptDataForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改

const formData = ref({
  id: undefined,
  platformRptDataId: undefined,
  evalTaskId: undefined,
  evalTaskName: undefined,
  evalObjectId: undefined,
  evalObjectName: undefined,
  idxItemId: undefined,
  idxItemName: undefined,
  dataValue: undefined,
  dataUnit: undefined,
  rptTime: undefined,
  rptUserId: undefined,
  rptUserName: undefined,
  dataStatus: undefined,
  verifyFailReason: undefined
})

const formRules = reactive({
  evalTaskId: [{ required: true, message: '关联任务ID不能为空', trigger: 'blur' }],
  evalTaskName: [{ required: true, message: '关联任务名称不能为空', trigger: 'blur' }],
  evalObjectId: [{ required: true, message: '评价对象ID不能为空', trigger: 'blur' }],
  evalObjectName: [{ required: true, message: '评价对象名称不能为空', trigger: 'blur' }],
  idxItemId: [{ required: true, message: '关联指标项ID不能为空', trigger: 'blur' }],
  idxItemName: [{ required: true, message: '关联指标项名称不能为空', trigger: 'blur' }],
  dataValue: [{ required: true, message: '数据值不能为空', trigger: 'blur' }],
  rptTime: [{ required: true, message: '上报时间不能为空', trigger: 'blur' }],
  rptUserId: [{ required: true, message: '上报人ID不能为空', trigger: 'blur' }],
  rptUserName: [{ required: true, message: '上报人姓名不能为空', trigger: 'blur' }],
  dataStatus: [{ required: true, message: '数据状态不能为空', trigger: 'blur' }]
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
      formData.value = await EvalPlatformRptDataApi.getEvalPlatformRptData(id)
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
    const data = formData.value as unknown as EvalPlatformRptDataVO
    if (formType.value === 'create') {
      await EvalPlatformRptDataApi.createEvalPlatformRptData(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalPlatformRptDataApi.updateEvalPlatformRptData(data)
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
    platformRptDataId: undefined,
    evalTaskId: undefined,
    evalTaskName: undefined,
    evalObjectId: undefined,
    evalObjectName: undefined,
    idxItemId: undefined,
    idxItemName: undefined,
    dataValue: undefined,
    dataUnit: undefined,
    rptTime: undefined,
    rptUserId: undefined,
    rptUserName: undefined,
    dataStatus: undefined,
    verifyFailReason: undefined
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
.el-date-picker,
.el-radio-group {
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
