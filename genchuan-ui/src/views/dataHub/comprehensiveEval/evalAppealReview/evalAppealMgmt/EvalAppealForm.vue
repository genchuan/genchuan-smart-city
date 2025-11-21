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
      <el-form-item label="申诉编号" prop="appealCode">
        <el-input
          v-model="formData.appealCode"
          placeholder="请输入申诉编号"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="关联公示ID" prop="resultPublicId">
        <el-input
          v-model="formData.resultPublicId"
          placeholder="请输入关联公示ID"
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
      <el-form-item label="申诉人ID" prop="appealUserId">
        <el-input
          v-model="formData.appealUserId"
          placeholder="请输入申诉人ID"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="申诉人姓名" prop="appealUserName">
        <el-input
          v-model="formData.appealUserName"
          placeholder="请输入申诉人姓名"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="申诉理由" prop="appealReason">
        <el-input
          v-model="formData.appealReason"
          placeholder="请输入申诉理由"
          type="textarea"
          :rows="3"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="证明材料" prop="proofMats">
        <el-input
          v-model="formData.proofMats"
          placeholder="请输入证明材料地址"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="申诉状态" prop="appealStatus">
        <el-select
          v-model="formData.appealStatus"
          placeholder="请选择申诉状态"
          class="w-full rounded-md"
        >
          <el-option label="已提交" value="SUBMITTED" />
          <el-option label="处理中" value="PROCESSING" />
        </el-select>
      </el-form-item>
      <el-form-item label="提交时间" prop="submitTime">
        <el-date-picker
          v-model="formData.submitTime"
          type="datetime"
          value-format="x"
          placeholder="选择提交时间"
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
  EvalAppealApi,
  EvalAppealVO
} from '@/api/dataHub/comprehensiveEval/evalAppealReview/evalAppealMgmt'

/** 申诉管理 表单 */
defineOptions({ name: 'EvalAppealForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

// 简化表单数据，隐藏不必要字段
const formData = ref({
  id: undefined,
  appealCode: undefined,
  resultPublicId: undefined,
  evalTaskId: undefined,
  evalObjectId: undefined,
  evalObjectName: undefined,
  appealUserId: undefined,
  appealUserName: undefined,
  appealReason: undefined,
  proofMats: undefined,
  appealStatus: undefined,
  submitTime: undefined,
  createUserBiz: undefined,
  createTimeBiz: undefined
})

// 保留核心必填项校验
const formRules = reactive({
  appealCode: [{ required: true, message: '申诉编号不能为空', trigger: 'blur' }],
  resultPublicId: [{ required: true, message: '关联公示ID不能为空', trigger: 'blur' }],
  evalTaskId: [{ required: true, message: '关联任务ID不能为空', trigger: 'blur' }],
  evalObjectId: [{ required: true, message: '评价对象ID不能为空', trigger: 'blur' }],
  evalObjectName: [{ required: true, message: '评价对象名称不能为空', trigger: 'blur' }],
  appealUserId: [{ required: true, message: '申诉人ID不能为空', trigger: 'blur' }],
  appealUserName: [{ required: true, message: '申诉人姓名不能为空', trigger: 'blur' }],
  appealReason: [{ required: true, message: '申诉理由不能为空', trigger: 'blur' }],
  appealStatus: [{ required: true, message: '申诉状态不能为空', trigger: 'change' }],
  submitTime: [{ required: true, message: '提交时间不能为空', trigger: 'change' }],
  createUserBiz: [{ required: true, message: '创建人(业务)不能为空', trigger: 'blur' }],
  createTimeBiz: [{ required: true, message: '创建时间(业务)不能为空', trigger: 'change' }]
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
      const res = await EvalAppealApi.getEvalAppeal(id)
      formData.value = {
        ...res,
        // 只保留需要展示的字段
        appealReason: res.appealReason || '',
        proofMats: res.proofMats || ''
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
    const data = formData.value as unknown as EvalAppealVO
    if (formType.value === 'create') {
      await EvalAppealApi.createEvalAppeal(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalAppealApi.updateEvalAppeal(data)
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
    appealCode: undefined,
    resultPublicId: undefined,
    evalTaskId: undefined,
    evalObjectId: undefined,
    evalObjectName: undefined,
    appealUserId: undefined,
    appealUserName: undefined,
    appealReason: undefined,
    proofMats: undefined,
    appealStatus: undefined,
    submitTime: undefined,
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
