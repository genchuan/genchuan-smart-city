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
      <el-form-item label="关联计划ID" prop="inspectPlanId">
        <el-input
          v-model="formData.inspectPlanId"
          placeholder="请输入关联计划ID"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="关联计划名称" prop="inspectPlanName">
        <el-input
          v-model="formData.inspectPlanName"
          placeholder="请输入关联计划名称"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="考察对象ID" prop="evalObjectId">
        <el-input
          v-model="formData.evalObjectId"
          placeholder="请输入考察对象ID"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="考察对象名称" prop="evalObjectName">
        <el-input
          v-model="formData.evalObjectName"
          placeholder="请输入考察对象名称"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="关联指标项ID" prop="idxItemId">
        <el-input
          v-model="formData.idxItemId"
          placeholder="请输入关联指标项ID"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="关联指标项名称" prop="idxItemName">
        <el-input
          v-model="formData.idxItemName"
          placeholder="请输入关联指标项名称"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="考察得分" prop="inspectScore">
        <el-input
          v-model="formData.inspectScore"
          placeholder="请输入考察得分"
          type="number"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="考察等级" prop="inspectLevel">
        <el-input
          v-model="formData.inspectLevel"
          placeholder="请输入考察等级"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="问题描述" prop="problemDesc">
        <el-input
          v-model="formData.problemDesc"
          placeholder="请输入问题描述"
          type="textarea"
          :rows="3"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="现场照片" prop="onSitePhotos">
        <el-input
          v-model="formData.onSitePhotos"
          placeholder="请输入现场照片地址"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="考察时间" prop="inspectTime">
        <el-date-picker
          v-model="formData.inspectTime"
          type="datetime"
          value-format="x"
          placeholder="选择考察时间"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="考察人员ID" prop="inspectorId">
        <el-input
          v-model="formData.inspectorId"
          placeholder="请输入考察人员ID"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="考察人员姓名" prop="inspectorName">
        <el-input
          v-model="formData.inspectorName"
          placeholder="请输入考察人员姓名"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="记录状态" prop="recStatus">
        <el-select
          v-model="formData.recStatus"
          placeholder="请选择记录状态"
          class="w-full rounded-md"
        >
          <el-option label="已审计" value="AUDITED" />
          <el-option label="待定" value="PENDING" />
        </el-select>
      </el-form-item>
      <el-form-item label="审核人ID" prop="auditorId">
        <el-input
          v-model="formData.auditorId"
          placeholder="请输入审核人ID"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="审核人姓名" prop="auditorName">
        <el-input
          v-model="formData.auditorName"
          placeholder="请输入审核人姓名"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="审核意见" prop="auditOpinion">
        <el-input
          v-model="formData.auditOpinion"
          placeholder="请输入审核意见"
          type="textarea"
          :rows="2"
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
  EvalInspectRecApi,
  EvalInspectRecVO
} from '@/api/dataHub/comprehensiveEval/onSiteEval/onSiteRecDataMgmt'

/** 考察记录数据 表单 */
defineOptions({ name: 'EvalInspectRecForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

// 简化表单数据，隐藏不必要字段
const formData = ref({
  id: undefined,
  inspectPlanId: undefined,
  inspectPlanName: undefined,
  evalObjectId: undefined,
  evalObjectName: undefined,
  idxItemId: undefined,
  idxItemName: undefined,
  inspectScore: undefined,
  inspectLevel: undefined,
  problemDesc: undefined,
  onSitePhotos: undefined,
  inspectTime: undefined,
  inspectorId: undefined,
  inspectorName: undefined,
  recStatus: undefined,
  auditorId: undefined,
  auditorName: undefined,
  auditOpinion: undefined,
  createUserBiz: undefined,
  createTimeBiz: undefined
})

// 保留核心必填项校验
const formRules = reactive({
  inspectPlanId: [{ required: true, message: '关联计划ID不能为空', trigger: 'blur' }],
  inspectPlanName: [{ required: true, message: '关联计划名称不能为空', trigger: 'blur' }],
  evalObjectId: [{ required: true, message: '考察对象ID不能为空', trigger: 'blur' }],
  evalObjectName: [{ required: true, message: '考察对象名称不能为空', trigger: 'blur' }],
  idxItemId: [{ required: true, message: '关联指标项ID不能为空', trigger: 'blur' }],
  idxItemName: [{ required: true, message: '关联指标项名称不能为空', trigger: 'blur' }],
  inspectTime: [{ required: true, message: '考察时间不能为空', trigger: 'change' }],
  inspectorId: [{ required: true, message: '考察人员ID不能为空', trigger: 'blur' }],
  inspectorName: [{ required: true, message: '考察人员姓名不能为空', trigger: 'blur' }],
  recStatus: [{ required: true, message: '记录状态不能为空', trigger: 'change' }],
  createUserBiz: [{ required: true, message: '创建人(业务)不能为空', trigger: 'blur' }],
  createTimeBiz: [{ required: true, message: '创建时间(业务)不能为空', trigger: 'change' }],
  inspectScore: [
    { required: true, message: '考察得分不能为空', trigger: 'blur' },
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
      const res = await EvalInspectRecApi.getEvalInspectRec(id)
      formData.value = {
        ...res,
        // 只保留需要展示的字段
        problemDesc: res.problemDesc || '',
        onSitePhotos: res.onSitePhotos || '',
        auditOpinion: res.auditOpinion || '',
        inspectScore: res.inspectScore || 0
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
    const data = formData.value as unknown as EvalInspectRecVO
    if (formType.value === 'create') {
      await EvalInspectRecApi.createEvalInspectRec(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalInspectRecApi.updateEvalInspectRec(data)
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
    inspectPlanId: undefined,
    inspectPlanName: undefined,
    evalObjectId: undefined,
    evalObjectName: undefined,
    idxItemId: undefined,
    idxItemName: undefined,
    inspectScore: undefined,
    inspectLevel: undefined,
    problemDesc: undefined,
    onSitePhotos: undefined,
    inspectTime: undefined,
    inspectorId: undefined,
    inspectorName: undefined,
    recStatus: undefined,
    auditorId: undefined,
    auditorName: undefined,
    auditOpinion: undefined,
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
