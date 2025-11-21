<template>
  <Dialog :title="dialogTitle" v-model="internalVisible" width="850px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="130px"
      class="form-container"
      v-loading="formLoading"
    >
      <el-row :gutter="20">
        <!-- 基础信息组 -->
        <el-col :span="24" class="mb-4">
          <div class="form-group-title bg-blue-50 text-blue-700 px-3 py-2 rounded-md font-medium"
            >基础信息
          </div>
        </el-col>
        <el-col :span="12">
          <el-form-item label="复核ID" prop="rectifyReviewId">
            <el-input
              v-model="formData.rectifyReviewId"
              placeholder="请输入复核ID"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="复核编码" prop="rectifyReviewCode">
            <el-input
              v-model="formData.rectifyReviewCode"
              placeholder="请输入复核编码"
              class="!w-full rounded-md border-gray-200"
              :disabled="formType === 'update'"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联验收ID" prop="acceptId">
            <el-input
              v-model="formData.acceptId"
              placeholder="请输入关联验收ID"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联验收编码" prop="acceptCode">
            <el-input
              v-model="formData.acceptCode"
              placeholder="请输入关联验收编码"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联整改任务ID" prop="rectifyTaskId">
            <el-input
              v-model="formData.rectifyTaskId"
              placeholder="请输入关联整改任务ID"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联问题名称" prop="problemName">
            <el-input
              v-model="formData.problemName"
              placeholder="请输入关联问题名称"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>

        <!-- 责任信息组 -->
        <el-col :span="24" class="mb-4 mt-6">
          <div class="form-group-title bg-pink-50 text-pink-700 px-3 py-2 rounded-md font-medium"
            >责任信息
          </div>
        </el-col>
        <el-col :span="12">
          <el-form-item label="责任部门名称" prop="deptName">
            <el-input
              v-model="formData.deptName"
              placeholder="请输入责任部门名称"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="整改负责人姓名" prop="leaderUserName">
            <el-input
              v-model="formData.leaderUserName"
              placeholder="请输入整改负责人姓名"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>

        <!-- 复核信息组 -->
        <el-col :span="24" class="mb-4 mt-6">
          <div class="form-group-title bg-green-50 text-green-700 px-3 py-2 rounded-md font-medium"
            >复核信息
          </div>
        </el-col>
        <el-col :span="12">
          <el-form-item label="复核方式" prop="reviewMethod">
            <el-input
              v-model="formData.reviewMethod"
              placeholder="请输入复核方式（如：现场复核/线上复核）"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="复核照片URLs" prop="reviewPhotoUrls">
            <el-input
              v-model="formData.reviewPhotoUrls"
              placeholder="请输入复核照片URLs（多个用逗号分隔）"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="复核结果" prop="reviewResult">
            <el-input
              v-model="formData.reviewResult"
              placeholder="请输入复核结果"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="复核人" prop="reviewUser">
            <el-input
              v-model="formData.reviewUser"
              placeholder="请输入复核人"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="复核时间" prop="reviewTime">
            <el-date-picker
              v-model="formData.reviewTime"
              type="date"
              value-format="x"
              placeholder="选择复核时间"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="复核意见" prop="reviewOpinion">
            <el-input
              v-model="formData.reviewOpinion"
              placeholder="请输入复核意见"
              type="textarea"
              :rows="3"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>

        <!-- 隐藏扩展字段和创建人（系统自动生成） -->
        <el-form-item label="创建人" prop="createUser" v-if="false">
          <el-input v-model="formData.createUser" />
        </el-form-item>
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
  InspectRectifyReviewApi,
  InspectRectifyReviewVO
} from '@/api/dataHub/supervisionInspection/patrolResultReview/rectificationResultReview'
import { nextTick } from 'vue'

/** 巡查巡检整改结果复核 表单 */
defineOptions({ name: 'InspectRectifyReviewForm' })
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
const formData = ref<Partial<InspectRectifyReviewVO>>({
  id: undefined,
  rectifyReviewId: undefined,
  rectifyReviewCode: undefined,
  acceptId: undefined,
  acceptCode: undefined,
  rectifyTaskId: undefined,
  problemName: undefined,
  deptName: undefined,
  leaderUserName: undefined,
  reviewMethod: undefined,
  reviewPhotoUrls: undefined,
  reviewResult: undefined,
  reviewOpinion: undefined,
  reviewUser: undefined,
  reviewTime: undefined,
  createUser: undefined, // 隐藏，系统自动填充
  extCat1: undefined, // 隐藏扩展字段
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
// 表单校验规则
const formRules = reactive({
  rectifyReviewCode: [{ required: true, message: '请输入复核编码', trigger: 'blur' }],
  acceptCode: [{ required: true, message: '请输入关联验收编码', trigger: 'blur' }],
  problemName: [{ required: true, message: '请输入关联问题名称', trigger: 'blur' }],
  deptName: [{ required: true, message: '请输入责任部门名称', trigger: 'blur' }],
  leaderUserName: [{ required: true, message: '请输入整改负责人姓名', trigger: 'blur' }],
  reviewMethod: [{ required: true, message: '请输入复核方式', trigger: 'blur' }],
  reviewResult: [{ required: true, message: '请输入复核结果', trigger: 'blur' }],
  reviewUser: [{ required: true, message: '请输入复核人', trigger: 'blur' }],
  reviewTime: [{ required: true, message: '请选择复核时间', trigger: 'change' }]
})
const formRef = ref() // 表单 Ref

/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  formType.value = type
  dialogTitle.value = t('action.' + type)
  resetForm()

  // 先显示弹窗再加载数据（优化用户体验）
  internalVisible.value = true
  await nextTick() // 等待弹窗DOM渲染完成

  // 修改时加载数据
  if (id) {
    formLoading.value = true
    try {
      const res = await InspectRectifyReviewApi.getInspectRectifyReview(id)
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
    const data = formData.value as InspectRectifyReviewVO
    if (formType.value === 'create') {
      await InspectRectifyReviewApi.createInspectRectifyReview(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectRectifyReviewApi.updateInspectRectifyReview(data)
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

/** 重置表单 */
const resetForm = () => {
  formData.value = {
    id: undefined,
    rectifyReviewId: undefined,
    rectifyReviewCode: undefined,
    acceptId: undefined,
    acceptCode: undefined,
    rectifyTaskId: undefined,
    problemName: undefined,
    deptName: undefined,
    leaderUserName: undefined,
    reviewMethod: undefined,
    reviewPhotoUrls: undefined,
    reviewResult: undefined,
    reviewOpinion: undefined,
    reviewUser: undefined,
    reviewTime: undefined
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
::v-deep(.el-date-picker__wrapper) {
  transition: all 0.3s ease;
  border-color: #e5e7eb;
}

::v-deep(.el-input__wrapper):hover,
::v-deep(.el-textarea__wrapper):hover,
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
