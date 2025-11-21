<template>
  <Dialog :title="dialogTitle" v-model="internalVisible" width="900px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      class="form-container"
      v-loading="formLoading"
    >
      <el-row :gutter="20">
        <!-- 反馈基础信息组 -->
        <el-col :span="24" class="mb-4">
          <div class="form-group-title bg-blue-50 text-blue-700 px-3 py-2 rounded-md font-medium"
            >反馈基础信息
          </div>
        </el-col>
        <el-col :span="12">
          <el-form-item label="反馈ID" prop="feedbackId">
            <el-input
              v-model="formData.feedbackId"
              placeholder="请输入反馈ID"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="反馈编码" prop="feedbackCode">
            <el-input
              v-model="formData.feedbackCode"
              placeholder="请输入反馈编码"
              class="!w-full rounded-md border-gray-200"
              :disabled="formType === 'update'"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联公示ID" prop="publicId">
            <el-input
              v-model="formData.publicId"
              placeholder="请输入关联公示ID"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联公示标题" prop="publicTitle">
            <el-input
              v-model="formData.publicTitle"
              placeholder="请输入关联公示标题"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="反馈人姓名" prop="feedbackUserName">
            <el-input
              v-model="formData.feedbackUserName"
              placeholder="请输入反馈人姓名"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="反馈人手机号" prop="feedbackUserPhone">
            <el-input
              v-model="formData.feedbackUserPhone"
              placeholder="请输入反馈人手机号"
              type="tel"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>

        <!-- 反馈内容组 -->
        <el-col :span="24" class="mb-4 mt-6">
          <div class="form-group-title bg-pink-50 text-pink-700 px-3 py-2 rounded-md font-medium"
            >反馈内容
          </div>
        </el-col>
        <el-col :span="24">
          <el-form-item label="反馈内容" prop="feedbackContent">
            <Editor
              v-model="formData.feedbackContent"
              height="200px"
              class="rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="反馈照片URLs" prop="feedbackPhotoUrls">
            <el-input
              v-model="formData.feedbackPhotoUrls"
              placeholder="请输入反馈照片URLs（多个用逗号分隔）"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="反馈时间" prop="feedbackTime">
            <el-date-picker
              v-model="formData.feedbackTime"
              type="date"
              value-format="x"
              placeholder="选择反馈时间"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="反馈状态" prop="feedbackStatus">
            <el-select
              v-model="formData.feedbackStatus"
              placeholder="请选择反馈状态"
              class="!w-full rounded-md border-gray-200"
            >
              <el-option label="待处理" value="待处理" />
              <el-option label="处理中" value="处理中" />
              <el-option label="已处理" value="已处理" />
              <!--              <el-option label="已驳回" value="已驳回" />-->
            </el-select>
          </el-form-item>
        </el-col>

        <!-- 核实信息组 -->
        <el-col :span="24" class="mb-4 mt-6">
          <div class="form-group-title bg-green-50 text-green-700 px-3 py-2 rounded-md font-medium"
            >核实信息
          </div>
        </el-col>
        <el-col :span="12">
          <el-form-item label="核实人员ID" prop="verifyUserId">
            <el-input
              v-model="formData.verifyUserId"
              placeholder="请输入核实人员ID"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="核实人员姓名" prop="verifyUserName">
            <el-input
              v-model="formData.verifyUserName"
              placeholder="请输入核实人员姓名"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="核实结果" prop="verifyResult">
            <el-select
              v-model="formData.verifyResult"
              placeholder="请选择核实结果"
              class="!w-full rounded-md border-gray-200"
            >
              <el-option label="属实" value="属实" />
              <el-option label="部分属实" value="部分属实" />
              <!--              <el-option label="不属实" value="不属实" />-->
              <!--              <el-option label="待核实" value="待核实" />-->
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联问题ID" prop="problemId">
            <el-input
              v-model="formData.problemId"
              placeholder="请输入关联问题ID"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="核实意见" prop="verifyOpinion">
            <el-input
              v-model="formData.verifyOpinion"
              placeholder="请输入核实意见"
              type="textarea"
              :rows="2"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>

        <!-- 处理信息组 -->
        <el-col :span="24" class="mb-4 mt-6">
          <div
            class="form-group-title bg-purple-50 text-purple-700 px-3 py-2 rounded-md font-medium"
            >处理信息
          </div>
        </el-col>
        <el-col :span="12">
          <el-form-item label="处理人" prop="handleUser">
            <el-input
              v-model="formData.handleUser"
              placeholder="请输入处理人"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="处理时间" prop="handleTime">
            <el-date-picker
              v-model="formData.handleTime"
              type="date"
              value-format="x"
              placeholder="选择处理时间"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="处理结果" prop="handleResult">
            <el-input
              v-model="formData.handleResult"
              placeholder="请输入处理结果"
              type="textarea"
              :rows="2"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>

        <!-- 隐藏扩展字段 -->
        <el-form-item label="分类扩展字段1" prop="extCat1" v-if="false">
          <el-input v-model="formData.extCat1" />
        </el-form-item>
        <el-form-item label="分类扩展字段2" prop="extCat2" v-if="false">
          <el-input v-model="formData.extCat2" />
        </el-form-item>
        <el-form-item label="通用扩展字段1" prop="extCommon1" v-if="false">
          <el-input v-model="formData.extCommon1" />
        </el-form-item>
        <el-form-item label="通用扩展字段2" prop="extCommon2" v-if="false">
          <el-input v-model="formData.extCommon2" />
        </el-form-item>
      </el-row>
    </el-form>
    <template #footer>
      <el-button @click="internalVisible = false" class="mr-2">取 消</el-button>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  InspectResultFeedbackApi,
  InspectResultFeedbackVO
} from '@/api/dataHub/supervisionInspection/patrolResultApplication/resultFeedback'
import { nextTick } from 'vue'

/** 巡查巡检结果反馈 表单 */
defineOptions({ name: 'InspectResultFeedbackForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

// 接收父组件v-model绑定，规避直接修改props
const props = defineProps<{
  modelValue: boolean
}>()
const emit = defineEmits(['update:modelValue', 'success'])

const internalVisible = computed({
  get: () => props.modelValue,
  set: (val) => emit('update:modelValue', val)
})

const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<InspectResultFeedbackVO>>({
  id: undefined,
  feedbackId: undefined,
  feedbackCode: undefined,
  publicId: undefined,
  publicTitle: undefined,
  feedbackUserName: undefined,
  feedbackUserPhone: undefined,
  feedbackContent: undefined,
  feedbackPhotoUrls: undefined,
  feedbackTime: undefined,
  feedbackStatus: undefined,
  verifyUserId: undefined,
  verifyUserName: undefined,
  verifyResult: undefined,
  verifyOpinion: undefined,
  problemId: undefined,
  handleResult: undefined,
  handleUser: undefined,
  handleTime: undefined,
  extCat1: undefined, // 隐藏扩展字段
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
// 表单校验规则
const formRules = reactive({
  feedbackCode: [{ required: true, message: '请输入反馈编码', trigger: 'blur' }],
  publicTitle: [{ required: true, message: '请输入关联公示标题', trigger: 'blur' }],
  feedbackUserName: [{ required: true, message: '请输入反馈人姓名', trigger: 'blur' }],
  feedbackUserPhone: [
    { required: true, message: '请输入反馈人手机号', trigger: 'blur' },
    { pattern: /^1[3-9]\d{9}$/, message: '请输入正确的手机号格式', trigger: 'blur' }
  ],
  feedbackContent: [{ required: true, message: '请输入反馈内容', trigger: 'blur' }],
  feedbackTime: [{ required: true, message: '请选择反馈时间', trigger: 'change' }],
  feedbackStatus: [{ required: true, message: '请选择反馈状态', trigger: 'change' }],
  verifyUserName: [{ required: true, message: '请输入核实人员姓名', trigger: 'blur' }],
  verifyResult: [{ required: true, message: '请选择核实结果', trigger: 'change' }]
})
const formRef = ref() // 表单 Ref

/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  formType.value = type
  dialogTitle.value = t('action.' + type)
  resetForm()
  // 先显示弹窗再重置（确保DOM已渲染）
  internalVisible.value = true

  // 修改时加载数据
  if (id) {
    formLoading.value = true
    try {
      const res = await InspectResultFeedbackApi.getInspectResultFeedback(id)
      formData.value = res
    } catch (error) {
      message.error('数据加载失败，请重试')
      internalVisible.value = false
    } finally {
      formLoading.value = false
    }
  }
}

// 暴露open方法给父组件
defineExpose({ open })

/** 提交表单 */
const submitForm = async () => {
  // 校验表单
  await formRef.value.validate()
  formLoading.value = true
  try {
    const data = formData.value as InspectResultFeedbackVO
    if (formType.value === 'create') {
      await InspectResultFeedbackApi.createInspectResultFeedback(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectResultFeedbackApi.updateInspectResultFeedback(data)
      message.success(t('common.updateSuccess'))
    }
    internalVisible.value = false
    emit('success') // 通知父组件刷新列表
  } catch (error) {
    message.error('操作失败，请重试')
  } finally {
    formLoading.value = false
  }
}

/** 重置表单（确保所有字段清空） */
const resetForm = () => {
  formData.value = {
    id: undefined,
    feedbackId: undefined,
    feedbackCode: undefined,
    publicId: undefined,
    publicTitle: undefined,
    feedbackUserName: undefined,
    feedbackUserPhone: undefined,
    feedbackContent: undefined,
    feedbackPhotoUrls: undefined,
    feedbackTime: undefined,
    feedbackStatus: undefined,
    verifyUserId: undefined,
    verifyUserName: undefined,
    verifyResult: undefined,
    verifyOpinion: undefined,
    problemId: undefined,
    handleResult: undefined,
    handleUser: undefined,
    handleTime: undefined
  }
  nextTick(() => {
    formRef.value?.resetFields()
  })
}
</script>
<style scoped>
.form-container {
  padding: 10px;
}

.form-group-title {
  margin-bottom: 15px;
  border-left: 3px solid currentColor;
  transition: all 0.2s ease;
}

.form-group-title:hover {
  opacity: 0.9;
}

/* 输入框 hover 效果，避免content覆盖 */
::v-deep(.el-input__wrapper),
::v-deep(.el-textarea__wrapper),
::v-deep(.el-select__wrapper),
::v-deep(.el-date-picker__wrapper) {
  transition: all 0.3s ease;
  border-color: #e5e7eb;
}

::v-deep(.el-input__wrapper):hover,
::v-deep(.el-textarea__wrapper):hover,
::v-deep(.el-select__wrapper):hover,
::v-deep(.el-date-picker__wrapper):hover {
  border-color: #409eff;
  box-shadow: 0 0 0 2px rgba(64, 158, 255, 0.1);
}

/* 弹窗样式优化 */
::v-deep(.el-dialog__body) {
  padding: 20px;
  background-color: #fafafa;
}

::v-deep(.el-form-item) {
  margin-bottom: 16px;
}
</style>
