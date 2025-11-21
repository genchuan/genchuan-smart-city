<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="案件ID" prop="caseId">
        <el-input v-model="formData.caseId" placeholder="请输入案件ID" />
      </el-form-item>
      <el-form-item label="文书类型" prop="documentType">
        <el-input v-model="formData.documentType" placeholder="请输入文书类型" />
      </el-form-item>
      <el-form-item label="文书编号" prop="documentCode">
        <el-input v-model="formData.documentCode" placeholder="请输入文书编号" />
      </el-form-item>
      <el-form-item label="文书标题" prop="documentTitle">
        <el-input v-model="formData.documentTitle" placeholder="请输入文书标题" />
      </el-form-item>
      <el-form-item label="文书内容" prop="documentContent">
        <el-input v-model="formData.documentContent" type="textarea" placeholder="请输入文书内容" />
      </el-form-item>
      <el-form-item label="创建人" prop="documentCreator">
        <el-input v-model="formData.documentCreator" placeholder="请输入创建人" />
      </el-form-item>
      <el-form-item label="审批人" prop="approver">
        <el-input v-model="formData.approver" placeholder="请输入审批人" />
      </el-form-item>
      <el-form-item label="审批时间" prop="approvalTime">
        <el-date-picker
          v-model="formData.approvalTime"
          type="date"
          value-format="x"
          placeholder="选择审批时间"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="审批状态" prop="approvalStatus">
        <el-input v-model="formData.approvalStatus" placeholder="请输入审批状态" />
      </el-form-item>
      <el-form-item label="签署人" prop="signatory">
        <el-input v-model="formData.signatory" placeholder="请输入签署人" />
      </el-form-item>
      <el-form-item label="签署时间" prop="signTime">
        <el-date-picker
          v-model="formData.signTime"
          type="date"
          value-format="x"
          placeholder="选择签署时间"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="盖章状态" prop="sealStatus">
        <el-input v-model="formData.sealStatus" placeholder="请输入盖章状态" />
      </el-form-item>
      <el-form-item label="盖章时间" prop="sealTime">
        <el-date-picker
          v-model="formData.sealTime"
          type="date"
          value-format="x"
          placeholder="选择盖章时间"
          class="common-Width100"
        />
      </el-form-item>
<!--      <el-form-item label="打印状态" prop="printStatus">
        <el-input v-model="formData.printStatus" placeholder="请输入打印状态" />
      </el-form-item>
      <el-form-item label="打印次数" prop="printTimes">
        <el-input v-model="formData.printTimes" placeholder="请输入打印次数" />
      </el-form-item>-->
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { LawDocumentApi, LawDocumentVO } from '@/api/smartcity/lawdocument'

/** 执法文书 表单 */
defineOptions({ name: 'LawDocumentForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  caseId: undefined,
  documentType: undefined,
  documentCode: undefined,
  documentTitle: undefined,
  documentContent: undefined,
  documentCreator: undefined,
  approver: undefined,
  approvalTime: undefined,
  approvalStatus: undefined,
  signatory: undefined,
  signTime: undefined,
  sealStatus: undefined,
  sealTime: undefined,
  printStatus: undefined,
  printTimes: undefined,
})
const formRules = reactive({
})
const formRef = ref() // 表单 ref

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
      formData.value = await LawDocumentApi.getLawDocument(id)
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
    const data = formData.value as unknown as LawDocumentVO
    if (formType.value === 'create') {
      await LawDocumentApi.createLawDocument(data)
      message.success(t('common.createSuccess'))
    } else {
      await LawDocumentApi.updateLawDocument(data)
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
    caseId: undefined,
    documentType: undefined,
    documentCode: undefined,
    documentTitle: undefined,
    documentContent: undefined,
    documentCreator: undefined,
    approver: undefined,
    approvalTime: undefined,
    approvalStatus: undefined,
    signatory: undefined,
    signTime: undefined,
    sealStatus: undefined,
    sealTime: undefined,
    printStatus: undefined,
    printTimes: undefined,
  }
  formRef.value?.resetFields()
}
</script>
