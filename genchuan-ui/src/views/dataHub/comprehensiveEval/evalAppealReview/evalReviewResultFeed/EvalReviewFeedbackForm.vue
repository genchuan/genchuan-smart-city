<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="800px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="140px"
      v-loading="formLoading"
      class="p-4 bg-gray-50 rounded-lg"
    >
      <!-- 纵向布局：确保输入控件对齐，符合用户使用习惯 -->
      <el-form-item label="关联复核ID" prop="reviewId">
        <el-input
          v-model="formData.reviewId"
          placeholder="请输入关联复核ID"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="关联申诉ID" prop="appealId">
        <el-input
          v-model="formData.appealId"
          placeholder="请输入关联申诉ID"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="申诉编号" prop="appealCode">
        <el-input
          v-model="formData.appealCode"
          placeholder="请输入申诉编号"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="评价对象ID" prop="evalObjectId">
        <el-input
          v-model="formData.evalObjectId"
          placeholder="请输入评价对象ID"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="评价对象名称" prop="evalObjectName">
        <el-input
          v-model="formData.evalObjectName"
          placeholder="请输入评价对象名称"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="原评价等级" prop="originalEvalLevel">
        <el-input
          v-model="formData.originalEvalLevel"
          placeholder="请输入原评价等级"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="原评价得分" prop="originalEvalScore">
        <el-input
          v-model="formData.originalEvalScore"
          placeholder="请输入原评价得分"
          type="number"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="最终复核结果" prop="finalReviewResult">
        <el-input
          v-model="formData.finalReviewResult"
          placeholder="请输入最终复核结果"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="修正后等级" prop="revisedLevel">
        <el-input
          v-model="formData.revisedLevel"
          placeholder="请输入修正后等级"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="修正后得分" prop="revisedScore">
        <el-input
          v-model="formData.revisedScore"
          placeholder="请输入修正后得分"
          type="number"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="反馈意见" prop="feedbackOpinion">
        <el-input
          v-model="formData.feedbackOpinion"
          placeholder="请输入反馈意见"
          type="textarea"
          :rows="3"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="反馈时间" prop="feedbackTime">
        <el-date-picker
          v-model="formData.feedbackTime"
          type="datetime"
          value-format="x"
          placeholder="选择反馈时间"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="反馈方式" prop="feedbackMethod">
        <el-input
          v-model="formData.feedbackMethod"
          placeholder="请输入反馈方式"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="创建人(业务)" prop="createUserBiz">
        <el-input
          v-model="formData.createUserBiz"
          placeholder="请输入创建人(业务)"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="创建时间(业务)" prop="createTimeBiz">
        <el-date-picker
          v-model="formData.createTimeBiz"
          type="date"
          value-format="x"
          placeholder="选择创建时间(业务)"
          class="w-full rounded-md"
        />
      </el-form-item>
    </el-form>

    <template #footer>
      <el-button @click="dialogVisible = false" class="mr-2">取 消</el-button>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
import {
  EvalReviewFeedbackApi,
  EvalReviewFeedbackVO
} from '@/api/dataHub/comprehensiveEval/evalAppealReview/evalReviewResultFeed'

/** 复核结果反馈 表单 */
defineOptions({ name: 'EvalReviewFeedbackForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

// 简化表单数据，隐藏不必要字段
const formData = ref({
  id: undefined,
  reviewId: undefined,
  appealId: undefined,
  appealCode: undefined,
  evalObjectId: undefined,
  evalObjectName: undefined,
  originalEvalLevel: undefined,
  originalEvalScore: undefined,
  finalReviewResult: undefined,
  revisedLevel: undefined,
  revisedScore: undefined,
  feedbackOpinion: undefined,
  feedbackTime: undefined,
  feedbackMethod: undefined,
  createUserBiz: undefined,
  createTimeBiz: undefined
})

// 保留核心必填项校验
const formRules = reactive({
  reviewId: [{ required: true, message: '关联复核ID不能为空', trigger: 'blur' }],
  appealId: [{ required: true, message: '关联申诉ID不能为空', trigger: 'blur' }],
  appealCode: [{ required: true, message: '申诉编号不能为空', trigger: 'blur' }],
  evalObjectId: [{ required: true, message: '评价对象ID不能为空', trigger: 'blur' }],
  evalObjectName: [{ required: true, message: '评价对象名称不能为空', trigger: 'blur' }],
  originalEvalLevel: [{ required: true, message: '原评价等级不能为空', trigger: 'blur' }],
  originalEvalScore: [
    { required: true, message: '原评价得分不能为空', trigger: 'blur' },
    { type: 'number', message: '请输入有效数字', trigger: 'blur' }
  ],
  finalReviewResult: [{ required: true, message: '最终复核结果不能为空', trigger: 'blur' }],
  feedbackOpinion: [{ required: true, message: '反馈意见不能为空', trigger: 'blur' }],
  feedbackTime: [{ required: true, message: '反馈时间不能为空', trigger: 'change' }],
  feedbackMethod: [{ required: true, message: '反馈方式不能为空', trigger: 'blur' }],
  createUserBiz: [{ required: true, message: '创建人(业务)不能为空', trigger: 'blur' }],
  createTimeBiz: [{ required: true, message: '创建时间(业务)不能为空', trigger: 'change' }],
  revisedScore: [
    { required: true, message: '修正后得分不能为空', trigger: 'blur' },
    { type: 'number', message: '请输入有效数字', trigger: 'blur' }
  ]
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
      const res = await EvalReviewFeedbackApi.getEvalReviewFeedback(id)
      formData.value = {
        ...res,
        // 只保留需要展示的字段
        feedbackOpinion: res.feedbackOpinion || '',
        originalEvalScore: res.originalEvalScore || 0,
        revisedScore: res.revisedScore || 0
      }
    } finally {
      formLoading.value = false
    }
  }
}

defineExpose({ open }) // 提供 open 方法

/** 提交表单 */
const emit = defineEmits(['success'])
const submitForm = async () => {
  // 校验表单
  await formRef.value.validate()
  // 提交请求
  formLoading.value = true
  try {
    const data = formData.value as unknown as EvalReviewFeedbackVO
    if (formType.value === 'create') {
      await EvalReviewFeedbackApi.createEvalReviewFeedback(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalReviewFeedbackApi.updateEvalReviewFeedback(data)
      message.success(t('common.updateSuccess'))
    }
    dialogVisible.value = false
    emit('success')
  } finally {
    formLoading.value = false
  }
}

/** 重置表单 */
const resetForm = () => {
  formData.value = {
    id: undefined,
    reviewId: undefined,
    appealId: undefined,
    appealCode: undefined,
    evalObjectId: undefined,
    evalObjectName: undefined,
    originalEvalLevel: undefined,
    originalEvalScore: undefined,
    finalReviewResult: undefined,
    revisedLevel: undefined,
    revisedScore: undefined,
    feedbackOpinion: undefined,
    feedbackTime: undefined,
    feedbackMethod: undefined,
    createUserBiz: undefined,
    createTimeBiz: undefined
  }
  formRef.value?.resetFields()
}
</script>

<style scoped>
.el-form {
  background-color: #fafafa;
}

.el-input,
.el-date-picker {
  --el-input-bg-color: #fff;
  --el-input-border-color: #e4e7ed;
  --el-input-hover-border-color: #409eff;
  width: 100%;
}

.el-dialog__body {
  padding: 16px 24px;
}

.el-form-item {
  margin-bottom: 20px;
}

.el-textarea {
  resize: none;
}

/* 确保输入控件高度一致 */
:deep(.el-input__wrapper),
:deep(.el-date-editor) {
  height: 32px;
  line-height: 32px;
}

:deep(.el-date-editor .el-input__wrapper) {
  height: auto;
}

/* 日期选择器宽度适配 */
:deep(.el-date-editor--datetime) {
  width: 100% !important;
}
</style>
