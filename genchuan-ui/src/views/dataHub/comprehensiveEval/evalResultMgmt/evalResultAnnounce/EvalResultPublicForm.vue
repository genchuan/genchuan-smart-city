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
      <el-form-item label="关联审核ID" prop="resultAuditId">
        <el-input
          v-model="formData.resultAuditId"
          placeholder="请输入关联审核ID"
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
      <el-form-item label="评价等级" prop="evalLevel">
        <el-input
          v-model="formData.evalLevel"
          placeholder="请输入评价等级"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="评价得分" prop="evalScore">
        <el-input
          v-model="formData.evalScore"
          placeholder="请输入评价得分"
          type="number"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="公示范围" prop="publicScope">
        <el-input
          v-model="formData.publicScope"
          placeholder="请输入公示范围"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="公示开始时间" prop="publicStartTime">
        <el-date-picker
          v-model="formData.publicStartTime"
          type="datetime"
          value-format="x"
          placeholder="选择公示开始时间"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="公示结束时间" prop="publicEndTime">
        <el-date-picker
          v-model="formData.publicEndTime"
          type="datetime"
          value-format="x"
          placeholder="选择公示结束时间"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="公示状态" prop="publicStatus">
        <el-select
          v-model="formData.publicStatus"
          placeholder="请选择公示状态"
          class="w-full rounded-md"
        >
          <el-option label="进行中" value="RUNNING" />
          <el-option label="已完成" value="COMPLETED" />
        </el-select>
      </el-form-item>
      <el-form-item label="公示链接" prop="publicUrl">
        <el-input
          v-model="formData.publicUrl"
          placeholder="请输入公示链接"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="异议数量" prop="objectCount">
        <el-input
          v-model="formData.objectCount"
          placeholder="请输入异议数量"
          type="number"
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
  EvalResultPublicApi,
  EvalResultPublicVO
} from '@/api/dataHub/comprehensiveEval/evalResultMgmt/evalResultAnnounce'

/** 结果公示 表单 */
defineOptions({ name: 'EvalResultPublicForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

// 简化表单数据，隐藏不必要字段
const formData = ref({
  id: undefined,
  resultAuditId: undefined,
  evalTaskId: undefined,
  evalTaskName: undefined,
  evalObjectId: undefined,
  evalObjectName: undefined,
  evalLevel: undefined,
  evalScore: undefined,
  publicScope: undefined,
  publicStartTime: undefined,
  publicEndTime: undefined,
  publicStatus: undefined,
  publicUrl: undefined,
  objectCount: undefined,
  createUserBiz: undefined,
  createTimeBiz: undefined
})

// 保留核心必填项校验
const formRules = reactive({
  resultAuditId: [{ required: true, message: '关联审核ID不能为空', trigger: 'blur' }],
  evalTaskId: [{ required: true, message: '关联任务ID不能为空', trigger: 'blur' }],
  evalTaskName: [{ required: true, message: '关联任务名称不能为空', trigger: 'blur' }],
  evalObjectId: [{ required: true, message: '评价对象ID不能为空', trigger: 'blur' }],
  evalObjectName: [{ required: true, message: '评价对象名称不能为空', trigger: 'blur' }],
  evalLevel: [{ required: true, message: '评价等级不能为空', trigger: 'blur' }],
  evalScore: [
    { required: true, message: '评价得分不能为空', trigger: 'blur' },
    { type: 'number', message: '请输入有效数字', trigger: 'blur' }
  ],
  publicScope: [{ required: true, message: '公示范围不能为空', trigger: 'blur' }],
  publicStartTime: [{ required: true, message: '公示开始时间不能为空', trigger: 'change' }],
  publicEndTime: [{ required: true, message: '公示结束时间不能为空', trigger: 'change' }],
  publicStatus: [{ required: true, message: '公示状态不能为空', trigger: 'change' }],
  objectCount: [
    { required: true, message: '异议数量不能为空', trigger: 'blur' },
    { type: 'number', message: '请输入有效数字', trigger: 'blur' }
  ],
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
      const res = await EvalResultPublicApi.getEvalResultPublic(id)
      formData.value = {
        ...res,
        // 只保留需要展示的字段
        publicUrl: res.publicUrl || '',
        evalScore: res.evalScore || 0,
        objectCount: res.objectCount || 0
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
    const data = formData.value as unknown as EvalResultPublicVO
    if (formType.value === 'create') {
      await EvalResultPublicApi.createEvalResultPublic(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalResultPublicApi.updateEvalResultPublic(data)
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
    resultAuditId: undefined,
    evalTaskId: undefined,
    evalTaskName: undefined,
    evalObjectId: undefined,
    evalObjectName: undefined,
    evalLevel: undefined,
    evalScore: undefined,
    publicScope: undefined,
    publicStartTime: undefined,
    publicEndTime: undefined,
    publicStatus: undefined,
    publicUrl: undefined,
    objectCount: undefined,
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
