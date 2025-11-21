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
        <!-- 基础信息组 -->
        <el-col :span="24" class="mb-4">
          <div class="form-group-title bg-blue-50 text-blue-700 px-3 py-2 rounded-md font-medium"
            >基础信息
          </div>
        </el-col>
        <el-col :span="12">
          <el-form-item label="公示ID" prop="publicId">
            <el-input
              v-model="formData.publicId"
              placeholder="请输入公示ID"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="公示编码" prop="publicCode">
            <el-input
              v-model="formData.publicCode"
              placeholder="请输入公示编码"
              class="!w-full rounded-md border-gray-200"
              :disabled="formType === 'update'"
            />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="公示标题" prop="publicTitle">
            <el-input
              v-model="formData.publicTitle"
              placeholder="请输入公示标题"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="公示类型" prop="publicType">
            <el-select
              v-model="formData.publicType"
              placeholder="请选择公示类型"
              class="!w-full rounded-md border-gray-200"
            >
              <el-option label="请选择字典生成" value="" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="公示状态" prop="publicStatus">
            <el-select
              v-model="formData.publicStatus"
              placeholder="请选择公示状态"
              class="!w-full rounded-md border-gray-200"
            >
              <el-option label="请选择字典生成" value="" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属区域代码" prop="areaCode">
            <el-input
              v-model="formData.areaCode"
              placeholder="请输入所属区域代码"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属区域名称" prop="areaName">
            <el-input
              v-model="formData.areaName"
              placeholder="请输入所属区域名称"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>

        <!-- 公示内容组 -->
        <el-col :span="24" class="mb-4 mt-6">
          <div class="form-group-title bg-pink-50 text-pink-700 px-3 py-2 rounded-md font-medium"
            >公示内容
          </div>
        </el-col>
        <el-col :span="24">
          <el-form-item label="公示内容" prop="publicContent">
            <Editor
              v-model="formData.publicContent"
              height="200px"
              class="rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="公示附件URL" prop="publicAttachUrl">
            <el-input
              v-model="formData.publicAttachUrl"
              placeholder="请输入公示附件URL（多个用逗号分隔）"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="公示说明" prop="publicDesc">
            <el-input
              v-model="formData.publicDesc"
              placeholder="请输入公示说明"
              type="textarea"
              :rows="2"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>

        <!-- 公示时间配置组 -->
        <el-col :span="24" class="mb-4 mt-6">
          <div class="form-group-title bg-green-50 text-green-700 px-3 py-2 rounded-md font-medium"
            >公示时间配置
          </div>
        </el-col>
        <el-col :span="12">
          <el-form-item label="公示开始时间" prop="publicStartTime">
            <el-date-picker
              v-model="formData.publicStartTime"
              type="date"
              value-format="x"
              placeholder="选择公示开始时间"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="公示结束时间" prop="publicEndTime">
            <el-date-picker
              v-model="formData.publicEndTime"
              type="date"
              value-format="x"
              placeholder="选择公示结束时间"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>

        <!-- 隐藏系统自动生成字段和扩展字段 -->
        <el-form-item label="创建人" prop="createUser" v-if="false">
          <el-input v-model="formData.createUser" />
        </el-form-item>
        <el-form-item label="创建时间" prop="bizCreateTime" v-if="false">
          <el-date-picker v-model="formData.bizCreateTime" type="date" value-format="x" />
        </el-form-item>
        <el-form-item label="更新人" prop="updateUser" v-if="false">
          <el-input v-model="formData.updateUser" />
        </el-form-item>
        <el-form-item label="更新时间" prop="bizUpdateTime" v-if="false">
          <el-date-picker v-model="formData.bizUpdateTime" type="date" value-format="x" />
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
  InspectResultPublicApi,
  InspectResultPublicVO
} from '@/api/dataHub/supervisionInspection/patrolResultApplication/resultAnnouncement'
import { nextTick } from 'vue'

/** 巡查巡检结果公示 表单 */
defineOptions({ name: 'InspectResultPublicForm' })
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
const formData = ref<Partial<InspectResultPublicVO>>({
  id: undefined,
  publicId: undefined,
  publicCode: undefined,
  publicTitle: undefined,
  publicType: undefined,
  areaCode: undefined,
  areaName: undefined,
  publicContent: undefined,
  publicAttachUrl: undefined,
  publicStartTime: undefined,
  publicEndTime: undefined,
  publicStatus: undefined,
  publicDesc: undefined,
  createUser: undefined, // 隐藏，系统自动填充
  bizCreateTime: undefined, // 隐藏，系统自动填充
  updateUser: undefined, // 隐藏，系统自动填充
  bizUpdateTime: undefined, // 隐藏，系统自动填充
  extCat1: undefined, // 隐藏扩展字段
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
// 表单校验规则
const formRules = reactive({
  publicCode: [{ required: true, message: '请输入公示编码', trigger: 'blur' }],
  publicTitle: [{ required: true, message: '请输入公示标题', trigger: 'blur' }],
  publicType: [{ required: true, message: '请选择公示类型', trigger: 'change' }],
  publicStatus: [{ required: true, message: '请选择公示状态', trigger: 'change' }],
  areaName: [{ required: true, message: '请输入所属区域名称', trigger: 'blur' }],
  publicContent: [{ required: true, message: '请输入公示内容', trigger: 'blur' }],
  publicStartTime: [{ required: true, message: '请选择公示开始时间', trigger: 'change' }],
  publicEndTime: [{ required: true, message: '请选择公示结束时间', trigger: 'change' }]
})
const formRef = ref() // 表单 Ref

/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  formType.value = type
  dialogTitle.value = t('action.' + type)
  resetForm()
  // 先显示弹窗再加载数据（优化用户体验）
  internalVisible.value = true

  // 修改时加载数据
  if (id) {
    formLoading.value = true
    try {
      const res = await InspectResultPublicApi.getInspectResultPublic(id)
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
    const data = formData.value as InspectResultPublicVO
    if (formType.value === 'create') {
      await InspectResultPublicApi.createInspectResultPublic(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectResultPublicApi.updateInspectResultPublic(data)
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
    publicId: undefined,
    publicCode: undefined,
    publicTitle: undefined,
    publicType: undefined,
    areaCode: undefined,
    areaName: undefined,
    publicContent: undefined,
    publicAttachUrl: undefined,
    publicStartTime: undefined,
    publicEndTime: undefined,
    publicStatus: undefined,
    publicDesc: undefined
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
