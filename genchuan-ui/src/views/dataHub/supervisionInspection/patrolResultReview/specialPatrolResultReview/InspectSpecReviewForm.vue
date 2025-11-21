<template>
  <Dialog :title="dialogTitle" v-model="internalVisible" width="850px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="140px"
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
          <el-form-item label="复核ID" prop="specReviewId">
            <el-input
              v-model="formData.specReviewId"
              placeholder="请输入复核ID"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="复核编码" prop="specReviewCode">
            <el-input
              v-model="formData.specReviewCode"
              placeholder="请输入复核编码"
              class="!w-full rounded-md border-gray-200"
              :disabled="formType === 'update'"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联专项记录ID" prop="specExecId">
            <el-input
              v-model="formData.specExecId"
              placeholder="请输入关联专项记录ID"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联专项记录编码" prop="specExecCode">
            <el-input
              v-model="formData.specExecCode"
              placeholder="请输入关联专项记录编码"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联专项计划ID" prop="specPlanId">
            <el-input
              v-model="formData.specPlanId"
              placeholder="请输入关联专项计划ID"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联专项计划名称" prop="specPlanName">
            <el-input
              v-model="formData.specPlanName"
              placeholder="请输入关联专项计划名称"
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
          <el-form-item label="专项主题" prop="specTopic">
            <el-input
              v-model="formData.specTopic"
              placeholder="请输入专项主题"
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
          <el-form-item label="复核重点" prop="reviewFocus">
            <el-input
              v-model="formData.reviewFocus"
              placeholder="请输入复核重点"
              type="textarea"
              :rows="2"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="复核结果" prop="reviewResult">
            <el-input
              v-model="formData.reviewResult"
              placeholder="请输入复核结果"
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
          <el-form-item label="重查要求" prop="reckRequirement">
            <el-input
              v-model="formData.reckRequirement"
              placeholder="请输入重查要求"
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
  InspectSpecReviewApi,
  InspectSpecReviewVO
} from '@/api/dataHub/supervisionInspection/patrolResultReview/specialPatrolResultReview'
import { nextTick } from 'vue'

/** 巡查巡检专项结果复核 表单 */
defineOptions({ name: 'InspectSpecReviewForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

// 接收父组件v-model绑定
const props = defineProps<{
  modelValue: boolean
}>()
const emit = defineEmits(['update:modelValue', 'success'])

// 内部维护弹窗状态，规避直接修改props
const internalVisible = computed({
  get: () => props.modelValue,
  set: (val) => emit('update:modelValue', val)
})

const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<InspectSpecReviewVO>>({
  id: undefined,
  specReviewId: undefined,
  specReviewCode: undefined,
  specExecId: undefined,
  specExecCode: undefined,
  specPlanId: undefined,
  specPlanName: undefined,
  execUserId: undefined,
  execUserName: undefined,
  specTopic: undefined,
  reviewFocus: undefined,
  reviewResult: undefined,
  reviewOpinion: undefined,
  reviewUser: undefined,
  reviewTime: undefined,
  reckRequirement: undefined,
  createUser: undefined, // 隐藏，由系统自动填充
  createTimeBiz: undefined // 隐藏，由系统自动填充
})
// 表单校验规则
const formRules = reactive({
  specReviewCode: [{ required: true, message: '请输入复核编码', trigger: 'blur' }],
  specExecCode: [{ required: true, message: '请输入关联专项记录编码', trigger: 'blur' }],
  specPlanName: [{ required: true, message: '请输入关联专项计划名称', trigger: 'blur' }],
  execUserName: [{ required: true, message: '请输入巡查人员姓名', trigger: 'blur' }],
  specTopic: [{ required: true, message: '请输入专项主题', trigger: 'blur' }],
  reviewFocus: [{ required: true, message: '请输入复核重点', trigger: 'blur' }],
  reviewResult: [{ required: true, message: '请输入复核结果', trigger: 'blur' }],
  reviewUser: [{ required: true, message: '请输入复核人', trigger: 'blur' }],
  reviewTime: [{ required: true, message: '请选择复核时间', trigger: 'change' }]
})
const formRef = ref() // 表单 Ref

/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  formType.value = type
  dialogTitle.value = t('action.' + type)
  resetForm()

  // 先显示弹窗再加载数据（优化用户体验）
  internalVisible.value = true
  await nextTick() // 等待弹窗DOM渲染完成

  // 修改时加载数据
  if (id) {
    formLoading.value = true
    try {
      const res = await InspectSpecReviewApi.getInspectSpecReview(id)
      formData.value = res
    } catch (error) {
      message.error('数据加载失败，请重试')
      internalVisible.value = false
    } finally {
      formLoading.value = false
    }
  }
}

// 暴露open方法给父组件
defineExpose({ open })

/** 提交表单 */
const submitForm = async () => {
  // 校验表单
  await formRef.value.validate()
  formLoading.value = true
  try {
    const data = formData.value as InspectSpecReviewVO
    if (formType.value === 'create') {
      await InspectSpecReviewApi.createInspectSpecReview(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectSpecReviewApi.updateInspectSpecReview(data)
      message.success(t('common.updateSuccess'))
    }
    internalVisible.value = false
    emit('success') // 通知父组件刷新列表
  } catch (error) {
    message.error('操作失败，请重试')
  } finally {
    formLoading.value = false
  }
}

/** 重置表单 */
const resetForm = () => {
  formData.value = {
    id: undefined,
    specReviewId: undefined,
    specReviewCode: undefined,
    specExecId: undefined,
    specExecCode: undefined,
    specPlanId: undefined,
    specPlanName: undefined,
    execUserId: undefined,
    execUserName: undefined,
    specTopic: undefined,
    reviewFocus: undefined,
    reviewResult: undefined,
    reviewOpinion: undefined,
    reviewUser: undefined,
    reviewTime: undefined,
    reckRequirement: undefined
  }
  nextTick(() => {
    formRef.value?.resetFields()
  })
}
</script>
<style scoped>
.form-container {
  padding: 10px;
}

.form-group-title {
  margin-bottom: 15px;
  border-left: 3px solid currentColor;
  transition: all 0.2s ease;
}

.form-group-title:hover {
  opacity: 0.9;
}

/* 输入框 hover 效果 */
::v-deep(.el-input__wrapper),
::v-deep(.el-textarea__wrapper),
::v-deep(.el-date-picker__wrapper) {
  transition: all 0.3s ease;
  border-color: #e5e7eb;
}

::v-deep(.el-input__wrapper):hover,
::v-deep(.el-textarea__wrapper):hover,
::v-deep(.el-date-picker__wrapper):hover {
  border-color: #409eff;
  box-shadow: 0 0 0 2px rgba(64, 158, 255, 0.1);
}

/* 弹窗样式优化 */
::v-deep(.el-dialog__body) {
  padding: 20px;
  background-color: #fafafa;
}

::v-deep(.el-form-item) {
  margin-bottom: 16px;
}
</style>
