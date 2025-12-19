<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="知识库ID " prop="aiSvcId">
        <el-input v-model="formData.aiSvcId" placeholder="请输入知识库ID " />
      </el-form-item>
      <el-form-item label="问题类型编码 " prop="questionTypeCode">
        <el-input v-model="formData.questionTypeCode" placeholder="请输入问题类型编码 " />
      </el-form-item>
      <el-form-item label="问题类型名称 " prop="questionTypeName">
        <el-input v-model="formData.questionTypeName" placeholder="请输入问题类型名称 " />
      </el-form-item>
      <el-form-item label="问题关键词 " prop="questionKeyword">
        <el-input v-model="formData.questionKeyword" placeholder="请输入问题关键词 " />
      </el-form-item>
      <el-form-item label="标准问题 " prop="stdQuestion">
        <el-input v-model="formData.stdQuestion" placeholder="请输入标准问题 " />
      </el-form-item>
      <el-form-item label="标准答案 " prop="stdAnswer">
        <el-input v-model="formData.stdAnswer" placeholder="请输入标准答案 " />
      </el-form-item>
      <el-form-item label="附件路径 " prop="attachPath">
        <el-input v-model="formData.attachPath" placeholder="请输入附件路径 " />
      </el-form-item>
      <el-form-item label="使用次数 " prop="useCount">
        <el-input v-model="formData.useCount" placeholder="请输入使用次数 " />
      </el-form-item>
      <el-form-item label="更新人ID " prop="updateUserId">
        <el-input v-model="formData.updateUserId" placeholder="请输入更新人ID " />
      </el-form-item>
      <el-form-item label="更新人姓名 " prop="updateUserName">
        <el-input v-model="formData.updateUserName" placeholder="请输入更新人姓名 " />
      </el-form-item>
      <el-form-item label="启用状态 " prop="enableStatus">
        <el-radio-group v-model="formData.enableStatus">
          <el-radio value="1">启用</el-radio>
          <el-radio value="0">禁用</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="备注，补充说明 " prop="remark">
        <el-input v-model="formData.remark" placeholder="请输入备注，补充说明 " />
      </el-form-item>
      <el-form-item label="分类扩展字段1" prop="extCat1">
        <el-input v-model="formData.extCat1" placeholder="请输入分类扩展字段1" />
      </el-form-item>
      <el-form-item label="分类扩展字段2 " prop="extCat2">
        <el-input v-model="formData.extCat2" placeholder="请输入分类扩展字段2 " />
      </el-form-item>
      <el-form-item label="通用扩展字段1 " prop="extCommon1">
        <el-input v-model="formData.extCommon1" placeholder="请输入通用扩展字段1 " />
      </el-form-item>
      <el-form-item label="通用扩展字段2 " prop="extCommon2">
        <el-input v-model="formData.extCommon2" placeholder="请输入通用扩展字段2 " />
      </el-form-item>
      <el-form-item label="系统创建时间" prop="createTimeSys">
        <el-date-picker
          v-model="formData.createTimeSys"
          type="date"
          value-format="x"
          placeholder="选择系统创建时间"
        />
      </el-form-item>
      <el-form-item label="系统更新时间" prop="updateTimeSys">
        <el-date-picker
          v-model="formData.updateTimeSys"
          type="date"
          value-format="x"
          placeholder="选择系统更新时间"
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
import { PublicAiSvcApi, PublicAiSvcVO } from '@/api/dataHub/publicService/publicaisvc'

/** 智能客服知识库 表单 */
defineOptions({ name: 'PublicAiSvcForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  aiSvcId: undefined,
  questionTypeCode: undefined,
  questionTypeName: undefined,
  questionKeyword: undefined,
  stdQuestion: undefined,
  stdAnswer: undefined,
  attachPath: undefined,
  useCount: undefined,
  updateUserId: undefined,
  updateUserName: undefined,
  enableStatus: undefined,
  remark: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  aiSvcId: [{ required: true, message: '知识库ID 不能为空', trigger: 'blur' }],
  questionTypeCode: [{ required: true, message: '问题类型编码 不能为空', trigger: 'blur' }],
  questionTypeName: [{ required: true, message: '问题类型名称 不能为空', trigger: 'blur' }],
  questionKeyword: [{ required: true, message: '问题关键词 不能为空', trigger: 'blur' }],
  stdQuestion: [{ required: true, message: '标准问题 不能为空', trigger: 'blur' }],
  stdAnswer: [{ required: true, message: '标准答案 不能为空', trigger: 'blur' }],
  useCount: [{ required: true, message: '使用次数 不能为空', trigger: 'blur' }],
  updateUserId: [{ required: true, message: '更新人ID 不能为空', trigger: 'blur' }],
  updateUserName: [{ required: true, message: '更新人姓名 不能为空', trigger: 'blur' }],
  enableStatus: [{ required: true, message: '启用状态 不能为空', trigger: 'blur' }],
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
      formData.value = await PublicAiSvcApi.getPublicAiSvc(id)
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
    const data = formData.value as unknown as PublicAiSvcVO
    if (formType.value === 'create') {
      await PublicAiSvcApi.createPublicAiSvc(data)
      message.success(t('common.createSuccess'))
    } else {
      await PublicAiSvcApi.updatePublicAiSvc(data)
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
    aiSvcId: undefined,
    questionTypeCode: undefined,
    questionTypeName: undefined,
    questionKeyword: undefined,
    stdQuestion: undefined,
    stdAnswer: undefined,
    attachPath: undefined,
    useCount: undefined,
    updateUserId: undefined,
    updateUserName: undefined,
    enableStatus: undefined,
    remark: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined,
  }
  formRef.value?.resetFields()
}
</script>
