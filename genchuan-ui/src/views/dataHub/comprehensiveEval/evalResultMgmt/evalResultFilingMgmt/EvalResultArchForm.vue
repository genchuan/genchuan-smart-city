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
      <el-form-item label="存档编号" prop="archCode">
        <el-input
          v-model="formData.archCode"
          placeholder="请输入存档编号"
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
      <el-form-item label="关联任务名称" prop="evalTaskName">
        <el-input
          v-model="formData.evalTaskName"
          placeholder="请输入关联任务名称"
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
      <el-form-item label="最终评价等级" prop="finalEvalLevel">
        <el-input
          v-model="formData.finalEvalLevel"
          placeholder="请输入最终评价等级"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="最终评价得分" prop="finalEvalScore">
        <el-input
          v-model="formData.finalEvalScore"
          placeholder="请输入最终评价得分"
          type="number"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="存档原因" prop="archReason">
        <el-input
          v-model="formData.archReason"
          placeholder="请输入存档原因"
          type="textarea"
          :rows="3"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="存档时间" prop="archTime">
        <el-date-picker
          v-model="formData.archTime"
          type="datetime"
          value-format="x"
          placeholder="选择存档时间"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="存档附件" prop="archAttachs">
        <el-input
          v-model="formData.archAttachs"
          placeholder="请输入存档附件地址"
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
  EvalResultArchApi,
  EvalResultArchVO
} from '@/api/dataHub/comprehensiveEval/evalResultMgmt/evalResultFilingMgmt'

/** 结果存档管理 表单 */
defineOptions({ name: 'EvalResultArchForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

// 简化表单数据，隐藏不必要字段
const formData = ref({
  id: undefined,
  archCode: undefined,
  evalTaskId: undefined,
  evalTaskName: undefined,
  evalObjectId: undefined,
  evalObjectName: undefined,
  finalEvalLevel: undefined,
  finalEvalScore: undefined,
  archReason: undefined,
  archTime: undefined,
  archAttachs: undefined,
  createUserBiz: undefined,
  createTimeBiz: undefined
})

// 保留核心必填项校验
const formRules = reactive({
  archCode: [{ required: true, message: '存档编号不能为空', trigger: 'blur' }],
  evalTaskId: [{ required: true, message: '关联任务ID不能为空', trigger: 'blur' }],
  evalTaskName: [{ required: true, message: '关联任务名称不能为空', trigger: 'blur' }],
  evalObjectId: [{ required: true, message: '评价对象ID不能为空', trigger: 'blur' }],
  evalObjectName: [{ required: true, message: '评价对象名称不能为空', trigger: 'blur' }],
  finalEvalLevel: [{ required: true, message: '最终评价等级不能为空', trigger: 'blur' }],
  finalEvalScore: [
    { required: true, message: '最终评价得分不能为空', trigger: 'blur' },
    { type: 'number', message: '请输入有效数字', trigger: 'blur' }
  ],
  archReason: [{ required: true, message: '存档原因不能为空', trigger: 'blur' }],
  archTime: [{ required: true, message: '存档时间不能为空', trigger: 'change' }],
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
      const res = await EvalResultArchApi.getEvalResultArch(id)
      formData.value = {
        ...res,
        // 只保留需要展示的字段
        archReason: res.archReason || '',
        archAttachs: res.archAttachs || '',
        finalEvalScore: res.finalEvalScore || 0
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
    const data = formData.value as unknown as EvalResultArchVO
    if (formType.value === 'create') {
      await EvalResultArchApi.createEvalResultArch(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalResultArchApi.updateEvalResultArch(data)
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
    archCode: undefined,
    evalTaskId: undefined,
    evalTaskName: undefined,
    evalObjectId: undefined,
    evalObjectName: undefined,
    finalEvalLevel: undefined,
    finalEvalScore: undefined,
    archReason: undefined,
    archTime: undefined,
    archAttachs: undefined,
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
