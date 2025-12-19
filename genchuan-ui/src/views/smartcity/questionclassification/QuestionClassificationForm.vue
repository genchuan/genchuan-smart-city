<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="所属领域" prop="isArea">
        <el-input v-model="formData.isArea" placeholder="请输入所属领域" />
      </el-form-item>
      <el-form-item label="问题类型" prop="questionType" label-width="100px">
        <el-select
          v-model="formData.questionType"
          placeholder="请选择问题类型"
          clearable
          style="width: 100%"
        >
          <el-option label="系统操作类" value="systemOperation" />
          <el-option label="现场实操类" value="onSiteOperation" />
          <el-option label="流程管理类" value="processManagement" />
          <el-option label="资源需求类" value="resourceRequirement" />
          <el-option label="政策咨询类" value="policyConsultation" />
          <el-option label="其他问题" value="other" />
        </el-select>
      </el-form-item>
      <el-form-item label="紧急程度" prop="urgency">
        <el-input v-model="formData.urgency" placeholder="请输入紧急程度" />
      </el-form-item>
      <el-form-item label="涉及主体" prop="involvingTheSubject">
        <el-input v-model="formData.involvingTheSubject" placeholder="请输入涉及主体" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { QuestionClassificationApi, QuestionClassificationVO } from '@/api/smartcity/questionclassification'

/** 问题录入 表单 */
defineOptions({ name: 'QuestionClassificationForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  isArea: undefined,
  questionType: undefined,
  urgency: undefined,
  involvingTheSubject: undefined
})
const formRules = reactive({
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
      formData.value = await QuestionClassificationApi.getQuestionClassification(id)
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
    const data = formData.value as unknown as QuestionClassificationVO
    if (formType.value === 'create') {
      await QuestionClassificationApi.createQuestionClassification(data)
      message.success(t('common.createSuccess'))
    } else {
      await QuestionClassificationApi.updateQuestionClassification(data)
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
    isArea: undefined,
    questionType: undefined,
    urgency: undefined,
    involvingTheSubject: undefined
  }
  formRef.value?.resetFields()
}
</script>
