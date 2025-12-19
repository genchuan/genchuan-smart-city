<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="访谈ID " prop="interviewId">
        <el-input v-model="formData.interviewId" placeholder="请输入访谈ID " />
      </el-form-item>
      <el-form-item label="访谈主题 " prop="interviewTopic">
        <el-input v-model="formData.interviewTopic" placeholder="请输入访谈主题 " />
      </el-form-item>
      <el-form-item label="访谈单位编码 " prop="deptCode">
        <el-input v-model="formData.deptCode" placeholder="请输入访谈单位编码 " />
      </el-form-item>
      <el-form-item label="访谈单位名称 " prop="deptName">
        <el-input v-model="formData.deptName" placeholder="请输入访谈单位名称 " />
      </el-form-item>
      <el-form-item label="嘉宾信息 " prop="guestInfo">
        <el-input v-model="formData.guestInfo" placeholder="请输入嘉宾信息 " />
      </el-form-item>
      <el-form-item label="访谈时间 " prop="interviewTime">
        <el-date-picker
          v-model="formData.interviewTime"
          type="date"
          value-format="x"
          placeholder="选择访谈时间 "
        />
      </el-form-item>
      <el-form-item label="访谈时长 " prop="interviewEndure">
        <el-input v-model="formData.interviewEndure" placeholder="请输入访谈时长 " />
      </el-form-item>
      <el-form-item label="访谈状态 " prop="interviewStatus">
        <el-radio-group v-model="formData.interviewStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="提问总数 " prop="questionCount">
        <el-input v-model="formData.questionCount" placeholder="请输入提问总数 " />
      </el-form-item>
      <el-form-item label="回复总数 " prop="replyCount">
        <el-input v-model="formData.replyCount" placeholder="请输入回复总数 " />
      </el-form-item>
      <el-form-item label="实录内容 " prop="recContent">
        <Editor v-model="formData.recContent" height="150px" />
      </el-form-item>
      <el-form-item label="实录发布时间 " prop="recPublishTime">
        <el-date-picker
          v-model="formData.recPublishTime"
          type="date"
          value-format="x"
          placeholder="选择实录发布时间 "
        />
      </el-form-item>
      <el-form-item label="发布人ID " prop="publishUserId">
        <el-input v-model="formData.publishUserId" placeholder="请输入发布人ID " />
      </el-form-item>
      <el-form-item label="发布人姓名 " prop="publishUserName">
        <el-input v-model="formData.publishUserName" placeholder="请输入发布人姓名 " />
      </el-form-item>
      <el-form-item label="备注，补充说明 " prop="remark">
        <el-input v-model="formData.remark" placeholder="请输入备注，补充说明 " />
      </el-form-item>
      <el-form-item label="分类扩展字段1   " prop="extCat1">
        <el-input v-model="formData.extCat1" placeholder="请输入分类扩展字段1   " />
      </el-form-item>
      <el-form-item label="分类扩展字段2   " prop="extCat2">
        <el-input v-model="formData.extCat2" placeholder="请输入分类扩展字段2   " />
      </el-form-item>
      <el-form-item label="通用扩展字段1 " prop="extCommon1">
        <el-input v-model="formData.extCommon1" placeholder="请输入通用扩展字段1 " />
      </el-form-item>
      <el-form-item label="通用扩展字段2  " prop="extCommon2">
        <el-input v-model="formData.extCommon2" placeholder="请输入通用扩展字段2  " />
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
import { PublicOnlineInterviewApi, PublicOnlineInterviewVO } from '@/api/dataHub/publicService/publiconlineinterview'

/** 在线访谈 表单 */
defineOptions({ name: 'PublicOnlineInterviewForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  interviewId: undefined,
  interviewTopic: undefined,
  deptCode: undefined,
  deptName: undefined,
  guestInfo: undefined,
  interviewTime: undefined,
  interviewEndure: undefined,
  interviewStatus: undefined,
  questionCount: undefined,
  replyCount: undefined,
  recContent: undefined,
  recPublishTime: undefined,
  publishUserId: undefined,
  publishUserName: undefined,
  remark: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  interviewId: [{ required: true, message: '访谈ID 不能为空', trigger: 'blur' }],
  interviewTopic: [{ required: true, message: '访谈主题 不能为空', trigger: 'blur' }],
  deptCode: [{ required: true, message: '访谈单位编码 不能为空', trigger: 'blur' }],
  deptName: [{ required: true, message: '访谈单位名称 不能为空', trigger: 'blur' }],
  guestInfo: [{ required: true, message: '嘉宾信息 不能为空', trigger: 'blur' }],
  interviewTime: [{ required: true, message: '访谈时间 不能为空', trigger: 'blur' }],
  interviewEndure: [{ required: true, message: '访谈时长 不能为空', trigger: 'blur' }],
  interviewStatus: [{ required: true, message: '访谈状态 不能为空', trigger: 'blur' }],
  questionCount: [{ required: true, message: '提问总数 不能为空', trigger: 'blur' }],
  replyCount: [{ required: true, message: '回复总数 不能为空', trigger: 'blur' }],
  publishUserId: [{ required: true, message: '发布人ID 不能为空', trigger: 'blur' }],
  publishUserName: [{ required: true, message: '发布人姓名 不能为空', trigger: 'blur' }],
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
      formData.value = await PublicOnlineInterviewApi.getPublicOnlineInterview(id)
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
    const data = formData.value as unknown as PublicOnlineInterviewVO
    if (formType.value === 'create') {
      await PublicOnlineInterviewApi.createPublicOnlineInterview(data)
      message.success(t('common.createSuccess'))
    } else {
      await PublicOnlineInterviewApi.updatePublicOnlineInterview(data)
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
    interviewId: undefined,
    interviewTopic: undefined,
    deptCode: undefined,
    deptName: undefined,
    guestInfo: undefined,
    interviewTime: undefined,
    interviewEndure: undefined,
    interviewStatus: undefined,
    questionCount: undefined,
    replyCount: undefined,
    recContent: undefined,
    recPublishTime: undefined,
    publishUserId: undefined,
    publishUserName: undefined,
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
