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
      <el-form-item label="关联任务ID" prop="evalTaskId">
        <el-input
          v-model="formData.evalTaskId"
          placeholder="请输入关联任务ID"
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
      <el-form-item label="复核人员ID" prop="reviewUserId">
        <el-input
          v-model="formData.reviewUserId"
          placeholder="请输入复核人员ID"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="复核人员姓名" prop="reviewUserName">
        <el-input
          v-model="formData.reviewUserName"
          placeholder="请输入复核人员姓名"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="复核意见" prop="reviewOpinion">
        <el-input
          v-model="formData.reviewOpinion"
          placeholder="请输入复核意见"
          type="textarea"
          :rows="3"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="核查材料" prop="reviewMats">
        <el-input
          v-model="formData.reviewMats"
          placeholder="请输入核查材料地址"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="复核状态" prop="reviewStatus">
        <el-select
          v-model="formData.reviewStatus"
          placeholder="请选择复核状态"
          class="w-full rounded-md"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="分配时间" prop="assignTime">
        <el-date-picker
          v-model="formData.assignTime"
          type="datetime"
          value-format="x"
          placeholder="选择分配时间"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="复核完成时间" prop="reviewCompleteTime">
        <el-date-picker
          v-model="formData.reviewCompleteTime"
          type="datetime"
          value-format="x"
          placeholder="选择复核完成时间"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="分配人ID" prop="assignUserId">
        <el-input
          v-model="formData.assignUserId"
          placeholder="请输入分配人ID"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="分配人姓名" prop="assignUserName">
        <el-input
          v-model="formData.assignUserName"
          placeholder="请输入分配人姓名"
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
  EvalReviewApi,
  EvalReviewVO
} from '@/api/dataHub/comprehensiveEval/evalAppealReview/evalReviewHandling'

/** 复核办理 表单 */
defineOptions({ name: 'EvalReviewForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

// 简化表单数据，隐藏不必要字段
const formData = ref({
  id: undefined,
  appealId: undefined,
  appealCode: undefined,
  evalTaskId: undefined,
  evalObjectId: undefined,
  evalObjectName: undefined,
  reviewUserId: undefined,
  reviewUserName: undefined,
  reviewOpinion: undefined,
  reviewMats: undefined,
  reviewStatus: undefined,
  assignTime: undefined,
  reviewCompleteTime: undefined,
  assignUserId: undefined,
  assignUserName: undefined
})

// 保留核心必填项校验
const formRules = reactive({
  appealId: [{ required: true, message: '关联申诉ID不能为空', trigger: 'blur' }],
  appealCode: [{ required: true, message: '申诉编号不能为空', trigger: 'blur' }],
  evalTaskId: [{ required: true, message: '关联任务ID不能为空', trigger: 'blur' }],
  evalObjectId: [{ required: true, message: '评价对象ID不能为空', trigger: 'blur' }],
  evalObjectName: [{ required: true, message: '评价对象名称不能为空', trigger: 'blur' }],
  reviewUserId: [{ required: true, message: '复核人员ID不能为空', trigger: 'blur' }],
  reviewUserName: [{ required: true, message: '复核人员姓名不能为空', trigger: 'blur' }],
  reviewOpinion: [{ required: true, message: '复核意见不能为空', trigger: 'blur' }],
  reviewStatus: [{ required: true, message: '复核状态不能为空', trigger: 'change' }],
  assignTime: [{ required: true, message: '分配时间不能为空', trigger: 'change' }],
  assignUserId: [{ required: true, message: '分配人ID不能为空', trigger: 'blur' }],
  assignUserName: [{ required: true, message: '分配人姓名不能为空', trigger: 'blur' }]
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
      const res = await EvalReviewApi.getEvalReview(id)
      formData.value = {
        ...res,
        // 只保留需要展示的字段
        reviewOpinion: res.reviewOpinion || '',
        reviewMats: res.reviewMats || ''
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
    const data = formData.value as unknown as EvalReviewVO
    if (formType.value === 'create') {
      await EvalReviewApi.createEvalReview(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalReviewApi.updateEvalReview(data)
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
    appealId: undefined,
    appealCode: undefined,
    evalTaskId: undefined,
    evalObjectId: undefined,
    evalObjectName: undefined,
    reviewUserId: undefined,
    reviewUserName: undefined,
    reviewOpinion: undefined,
    reviewMats: undefined,
    reviewStatus: undefined,
    assignTime: undefined,
    reviewCompleteTime: undefined,
    assignUserId: undefined,
    assignUserName: undefined
  }
  formRef.value?.resetFields()
}
</script>

<style scoped>
.el-form {
  background-color: #fafafa;
}

.el-input,
.el-date-picker,
.el-select {
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
