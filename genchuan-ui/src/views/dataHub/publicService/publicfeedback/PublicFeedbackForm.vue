<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="反馈ID " prop="feedbackId">
        <el-input v-model="formData.feedbackId" placeholder="请输入反馈ID " />
      </el-form-item>
      <el-form-item label="反馈单号 " prop="feedbackNo">
        <el-input v-model="formData.feedbackNo" placeholder="请输入反馈单号 " />
      </el-form-item>
      <el-form-item label="反馈类型编码 " prop="feedbackTypeCode">
        <el-input v-model="formData.feedbackTypeCode" placeholder="请输入反馈类型编码 " />
      </el-form-item>
      <el-form-item label="反馈类型名称 " prop="feedbackTypeName">
        <el-input v-model="formData.feedbackTypeName" placeholder="请输入反馈类型名称 " />
      </el-form-item>
      <el-form-item label="反馈内容 " prop="feedbackContent">
        <el-input v-model="formData.feedbackContent" placeholder="请输入反馈内容 " height="150px" />
      </el-form-item>
      <el-form-item label="上传图片路径 " prop="imgPath">
        <el-input v-model="formData.imgPath" placeholder="请输入上传图片路径 " />
      </el-form-item>
      <el-form-item label="反馈人ID " prop="feedbackUserId">
        <el-input v-model="formData.feedbackUserId" placeholder="请输入反馈人ID " />
      </el-form-item>
      <el-form-item label="反馈人姓名" prop="feedbackUserName">
        <el-input v-model="formData.feedbackUserName" placeholder="请输入反馈人姓名" />
      </el-form-item>
      <el-form-item label="反馈人电话 " prop="feedbackUserPhone">
        <el-input v-model="formData.feedbackUserPhone" placeholder="请输入反馈人电话 " />
      </el-form-item>
      <el-form-item label="评估状态 " prop="evalStatus">
        <el-radio-group v-model="formData.evalStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="评估意见 " prop="evalOpinion">
        <el-input v-model="formData.evalOpinion" placeholder="请输入评估意见 " />
      </el-form-item>
      <el-form-item label="评估人ID " prop="evalUserId">
        <el-input v-model="formData.evalUserId" placeholder="请输入评估人ID " />
      </el-form-item>
      <el-form-item label="评估时间" prop="evalTime">
        <el-date-picker
          v-model="formData.evalTime"
          type="date"
          value-format="x"
          placeholder="选择评估时间"
        />
      </el-form-item>
      <el-form-item label="反馈时间 " prop="feedbackTime">
        <el-date-picker
          v-model="formData.feedbackTime"
          type="date"
          value-format="x"
          placeholder="选择反馈时间 "
        />
      </el-form-item>
      <el-form-item label="备注，补充说明 " prop="remark">
        <el-input v-model="formData.remark" placeholder="请输入备注，补充说明 " />
      </el-form-item>
      <el-form-item label="分类扩展字段1 " prop="extCat1">
        <el-input v-model="formData.extCat1" placeholder="请输入分类扩展字段1 " />
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
import { PublicFeedbackApi, PublicFeedbackVO } from '@/api/dataHub/publicService/publicfeedback'

/** 意见反馈 表单 */
defineOptions({ name: 'PublicFeedbackForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  feedbackId: undefined,
  feedbackNo: undefined,
  feedbackTypeCode: undefined,
  feedbackTypeName: undefined,
  feedbackContent: undefined,
  imgPath: undefined,
  feedbackUserId: undefined,
  feedbackUserName: undefined,
  feedbackUserPhone: undefined,
  evalStatus: undefined,
  evalOpinion: undefined,
  evalUserId: undefined,
  evalTime: undefined,
  feedbackTime: undefined,
  remark: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  feedbackId: [{ required: true, message: '反馈ID 不能为空', trigger: 'blur' }],
  feedbackNo: [{ required: true, message: '反馈单号 不能为空', trigger: 'blur' }],
  feedbackTypeCode: [{ required: true, message: '反馈类型编码 不能为空', trigger: 'blur' }],
  feedbackTypeName: [{ required: true, message: '反馈类型名称 不能为空', trigger: 'blur' }],
  feedbackContent: [{ required: true, message: '反馈内容 不能为空', trigger: 'blur' }],
  feedbackUserName: [{ required: true, message: '反馈人姓名不能为空', trigger: 'blur' }],
  evalStatus: [{ required: true, message: '评估状态 不能为空', trigger: 'blur' }],
  feedbackTime: [{ required: true, message: '反馈时间 不能为空', trigger: 'blur' }],
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
      formData.value = await PublicFeedbackApi.getPublicFeedback(id)
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
    const data = formData.value as unknown as PublicFeedbackVO
    if (formType.value === 'create') {
      await PublicFeedbackApi.createPublicFeedback(data)
      message.success(t('common.createSuccess'))
    } else {
      await PublicFeedbackApi.updatePublicFeedback(data)
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
    feedbackId: undefined,
    feedbackNo: undefined,
    feedbackTypeCode: undefined,
    feedbackTypeName: undefined,
    feedbackContent: undefined,
    imgPath: undefined,
    feedbackUserId: undefined,
    feedbackUserName: undefined,
    feedbackUserPhone: undefined,
    evalStatus: undefined,
    evalOpinion: undefined,
    evalUserId: undefined,
    evalTime: undefined,
    feedbackTime: undefined,
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
