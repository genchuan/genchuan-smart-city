<template>
  <Dialog :title="dialogTitle" v-model="internalVisible" width="800px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      class="form-container"
      v-loading="formLoading"
    >
      <el-row :gutter="20">
        <!-- 基础信息组 -->
        <el-col :span="24" class="mb-4">
          <div class="form-group-title bg-blue-50 text-blue-700 px-3 py-2 rounded-md font-medium"
            >基础信息
          </div>
        </el-col>
        <el-col :span="12">
          <el-form-item label="复核ID" prop="dailyReviewId">
            <el-input
              v-model="formData.dailyReviewId"
              placeholder="请输入复核ID"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="复核编码" prop="dailyReviewCode">
            <el-input
              v-model="formData.dailyReviewCode"
              placeholder="请输入复核编码"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联记录ID" prop="dailyExecId">
            <el-input
              v-model="formData.dailyExecId"
              placeholder="请输入关联日常记录ID"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联记录编码" prop="dailyExecCode">
            <el-input
              v-model="formData.dailyExecCode"
              placeholder="请输入关联日常记录编码"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>

        <!-- 巡查信息组 -->
        <el-col :span="24" class="mb-4 mt-6">
          <div class="form-group-title bg-pink-50 text-pink-700 px-3 py-2 rounded-md font-medium"
            >巡查信息
          </div>
        </el-col>
        <el-col :span="12">
          <el-form-item label="巡查人员ID" prop="execUserId">
            <el-input
              v-model="formData.execUserId"
              placeholder="请输入巡查人员ID"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="巡查人员姓名" prop="execUserName">
            <el-input
              v-model="formData.execUserName"
              placeholder="请输入巡查人员姓名"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="巡查区域名称" prop="execAreaName">
            <el-input
              v-model="formData.execAreaName"
              placeholder="请输入巡查区域名称"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>

        <!-- 复核信息组 -->
        <el-col :span="24" class="mb-4 mt-6">
          <div class="form-group-title bg-green-50 text-green-700 px-3 py-2 rounded-md font-medium"
            >复核信息
          </div>
        </el-col>
        <el-col :span="24">
          <el-form-item label="复核内容" prop="reviewContent">
            <Editor
              v-model="formData.reviewContent"
              height="180px"
              class="rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="复核结果" prop="reviewResult">
            <el-input
              v-model="formData.reviewResult"
              placeholder="请输入复核结果（如：合格/待改进）"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="复核人" prop="reviewUser">
            <el-input
              v-model="formData.reviewUser"
              placeholder="请输入复核人"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="复核时间" prop="reviewTime">
            <el-date-picker
              v-model="formData.reviewTime"
              type="date"
              value-format="x"
              placeholder="选择复核时间"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="复核依据" prop="reviewBasis">
            <el-input
              v-model="formData.reviewBasis"
              placeholder="请输入复核依据"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="复核意见" prop="reviewOpinion">
            <el-input
              v-model="formData.reviewOpinion"
              placeholder="请输入复核意见"
              type="textarea"
              :rows="3"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>

        <!-- 整改要求组 -->
        <el-col :span="24" class="mb-4 mt-6">
          <div
            class="form-group-title bg-yellow-50 text-yellow-700 px-3 py-2 rounded-md font-medium"
            >整改要求
          </div>
        </el-col>
        <el-col :span="24">
          <el-form-item label="整改要求" prop="rectifyRequirement">
            <el-input
              v-model="formData.rectifyRequirement"
              placeholder="请输入整改要求"
              type="textarea"
              :rows="3"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <template #footer>
      <el-button @click="internalVisible = false" class="mr-2">取 消</el-button>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  InspectDailyReviewApi,
  InspectDailyReviewVO
} from '@/api/dataHub/supervisionInspection/patrolResultReview/dailyPatrolResultReview'

/** 巡查巡检日常结果复核 表单 */
defineOptions({ name: 'InspectDailyReviewForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const props = defineProps<{
  modelValue: boolean
}>()
const emit = defineEmits(['update:modelValue', 'success'])

const internalVisible = computed({
  get: () => props.modelValue,
  set: (val) => emit('update:modelValue', val)
})

const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<InspectDailyReviewVO>>({
  id: undefined,
  dailyReviewId: undefined,
  dailyReviewCode: undefined,
  dailyExecId: undefined,
  dailyExecCode: undefined,
  execUserId: undefined,
  execUserName: undefined,
  execAreaName: undefined,
  reviewContent: undefined,
  reviewResult: undefined,
  reviewOpinion: undefined,
  reviewBasis: undefined,
  reviewUser: undefined,
  reviewTime: undefined,
  rectifyRequirement: undefined
})
const formRules = reactive({
  dailyReviewCode: [{ required: true, message: '请输入复核编码', trigger: 'blur' }],
  dailyExecCode: [{ required: true, message: '请输入关联日常记录编码', trigger: 'blur' }],
  execUserName: [{ required: true, message: '请输入巡查人员姓名', trigger: 'blur' }],
  reviewContent: [{ required: true, message: '请输入复核内容', trigger: 'blur' }],
  reviewResult: [{ required: true, message: '请输入复核结果', trigger: 'blur' }],
  reviewUser: [{ required: true, message: '请输入复核人', trigger: 'blur' }],
  reviewTime: [{ required: true, message: '请选择复核时间', trigger: 'change' }]
})
const formRef = ref() // 表单 Ref

/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  internalVisible.value = true
  dialogTitle.value = t('action.' + type)
  formType.value = type
  resetForm()

  // 修改时，设置数据
  if (id) {
    formLoading.value = true
    try {
      const res = await InspectDailyReviewApi.getInspectDailyReview(id)
      formData.value = res
    } finally {
      formLoading.value = false
    }
  }
}

defineExpose({ open }) // 提供 open 方法

/** 提交表单 */
const submitForm = async () => {
  // 校验表单
  await formRef.value.validate()
  // 提交请求
  formLoading.value = true
  try {
    const data = formData.value as InspectDailyReviewVO
    if (formType.value === 'create') {
      await InspectDailyReviewApi.createInspectDailyReview(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectDailyReviewApi.updateInspectDailyReview(data)
      message.success(t('common.updateSuccess'))
    }
    internalVisible.value = false
    emit('success')
  } finally {
    formLoading.value = false
  }
}

/** 重置表单 */
const resetForm = () => {
  formData.value = {
    id: undefined,
    dailyReviewId: undefined,
    dailyReviewCode: undefined,
    dailyExecId: undefined,
    dailyExecCode: undefined,
    execUserId: undefined,
    execUserName: undefined,
    execAreaName: undefined,
    reviewContent: undefined,
    reviewResult: undefined,
    reviewOpinion: undefined,
    reviewBasis: undefined,
    reviewUser: undefined,
    reviewTime: undefined,
    rectifyRequirement: undefined
  }
  formRef.value?.resetFields()
}
</script>
<style scoped>
.form-container {
  padding: 10px;
}

.form-group-title {
  margin-bottom: 15px;
  border-left: 3px solid currentColor;
}

::v-deep(.el-input__wrapper),
::v-deep(.el-textarea__wrapper),
::v-deep(.el-date-picker__wrapper) {
  transition: all 0.3s ease;
}

::v-deep(.el-input__wrapper):hover,
::v-deep(.el-textarea__wrapper):hover,
::v-deep(.el-date-picker__wrapper):hover {
  border-color: #409eff;
  box-shadow: 0 0 0 2px rgba(64, 158, 255, 0.1);
}

::v-deep(.el-dialog__body) {
  padding: 20px;
}

::v-deep(.el-form-item) {
  margin-bottom: 16px;
}
</style>
