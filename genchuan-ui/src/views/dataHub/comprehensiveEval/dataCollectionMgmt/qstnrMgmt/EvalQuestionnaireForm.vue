<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="700px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
      class="form-container"
    >
      <el-form-item label="问卷ID" prop="questionnaireId">
        <el-input v-model="formData.questionnaireId" placeholder="请输入问卷ID" class="!w-full" />
      </el-form-item>
      <el-form-item label="问卷名称" prop="questionnaireName">
        <el-input
          v-model="formData.questionnaireName"
          placeholder="请输入问卷名称"
          class="!w-full"
        />
      </el-form-item>
      <el-form-item label="问卷编码" prop="questionnaireCode">
        <el-input
          v-model="formData.questionnaireCode"
          placeholder="请输入问卷编码"
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
      <el-form-item label="调查对象范围" prop="targetScope">
        <el-input v-model="formData.targetScope" placeholder="请输入调查对象范围" class="!w-full" />
      </el-form-item>
      <el-form-item label="发放方式" prop="releaseMethod">
        <el-input
          v-model="formData.releaseMethod"
          placeholder="请输入发放方式（如：线上、线下）"
          class="!w-full"
        />
      </el-form-item>
      <el-form-item label="问卷链接" prop="questionnaireUrl">
        <el-input
          v-model="formData.questionnaireUrl"
          placeholder="请输入问卷链接"
          class="!w-full"
        />
      </el-form-item>
      <el-form-item label="问卷二维码" prop="questionnaireQrcode">
        <el-input
          v-model="formData.questionnaireQrcode"
          placeholder="请输入问卷二维码地址"
          class="!w-full"
        />
      </el-form-item>
      <el-form-item label="开始时间" prop="startTime">
        <el-date-picker
          v-model="formData.startTime"
          type="datetime"
          value-format="x"
          placeholder="选择开始时间"
          class="!w-full"
        />
      </el-form-item>
      <el-form-item label="结束时间" prop="endTime">
        <el-date-picker
          v-model="formData.endTime"
          type="datetime"
          value-format="x"
          placeholder="选择结束时间"
          class="!w-full"
        />
      </el-form-item>
      <el-form-item label="问卷状态" prop="questionnaireStatus">
        <el-radio-group v-model="formData.questionnaireStatus" class="w-full">
          <el-radio value="COMPLETED">已完成</el-radio>
          <el-radio value="RUNNING">进行中</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="填写人数" prop="fillCount">
        <el-input
          v-model="formData.fillCount"
          placeholder="请输入填写人数"
          type="number"
          class="!w-full"
        />
      </el-form-item>
      <el-form-item label="问卷内容" prop="questionnaireContent">
        <Editor v-model="formData.questionnaireContent" height="200px" class="!w-full" />
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
  EvalQuestionnaireApi,
  EvalQuestionnaireVO
} from '@/api/dataHub/comprehensiveEval/dataCollectionMgmt/qstnrMgmt'

/** 问卷调查管理 表单 */
defineOptions({ name: 'EvalQuestionnaireForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改

const formData = ref({
  id: undefined,
  questionnaireId: undefined,
  questionnaireName: undefined,
  questionnaireCode: undefined,
  evalTaskId: undefined,
  evalTaskName: undefined,
  idxItemId: undefined,
  idxItemName: undefined,
  targetScope: undefined,
  releaseMethod: undefined,
  questionnaireUrl: undefined,
  questionnaireQrcode: undefined,
  startTime: undefined,
  endTime: undefined,
  questionnaireStatus: undefined,
  fillCount: undefined,
  questionnaireContent: undefined,
  createUserBiz: undefined,
  createTimeBiz: undefined
})

const formRules = reactive({
  questionnaireName: [{ required: true, message: '问卷名称不能为空', trigger: 'blur' }],
  questionnaireCode: [{ required: true, message: '问卷编码不能为空', trigger: 'blur' }],
  evalTaskId: [{ required: true, message: '关联任务ID不能为空', trigger: 'blur' }],
  evalTaskName: [{ required: true, message: '关联任务名称不能为空', trigger: 'blur' }],
  targetScope: [{ required: true, message: '调查对象范围不能为空', trigger: 'blur' }],
  releaseMethod: [{ required: true, message: '发放方式不能为空', trigger: 'blur' }],
  startTime: [{ required: true, message: '开始时间不能为空', trigger: 'blur' }],
  endTime: [{ required: true, message: '结束时间不能为空', trigger: 'blur' }],
  questionnaireStatus: [{ required: true, message: '问卷状态不能为空', trigger: 'blur' }],
  fillCount: [{ required: true, message: '填写人数不能为空', trigger: 'blur', type: 'number' }],
  questionnaireContent: [{ required: true, message: '问卷内容不能为空', trigger: 'blur' }],
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
      formData.value = await EvalQuestionnaireApi.getEvalQuestionnaire(id)
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
    const data = formData.value as unknown as EvalQuestionnaireVO
    if (formType.value === 'create') {
      await EvalQuestionnaireApi.createEvalQuestionnaire(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalQuestionnaireApi.updateEvalQuestionnaire(data)
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
    questionnaireId: undefined,
    questionnaireName: undefined,
    questionnaireCode: undefined,
    evalTaskId: undefined,
    evalTaskName: undefined,
    idxItemId: undefined,
    idxItemName: undefined,
    targetScope: undefined,
    releaseMethod: undefined,
    questionnaireUrl: undefined,
    questionnaireQrcode: undefined,
    startTime: undefined,
    endTime: undefined,
    questionnaireStatus: undefined,
    fillCount: undefined,
    questionnaireContent: undefined,
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
.el-date-picker,
.el-radio-group,
.editor-container {
  border-radius: 6px !important;
}

.el-dialog__body {
  padding: 20px !important;
  max-height: 70vh;
  overflow-y: auto;
}

.el-dialog__header {
  background-color: #f8fafc;
  border-bottom: 1px solid #e2e8f0;
  margin: -20px -20px 0;
  padding: 16px 20px;
}

/* 适配编辑器样式 */
.editor-container {
  border: 1px solid #dcdfe6;
}
</style>
