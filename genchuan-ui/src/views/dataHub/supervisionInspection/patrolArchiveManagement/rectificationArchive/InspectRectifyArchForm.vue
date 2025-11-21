<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="800px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
      class="space-y-6"
    >
      <!-- 基础信息行 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="档案ID" prop="rectifyArchId">
            <el-input
              v-model="formData.rectifyArchId"
              placeholder="请输入档案ID"
              class="!rounded-md"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="档案编码" prop="rectifyArchCode">
            <el-input
              v-model="formData.rectifyArchCode"
              placeholder="请输入档案编码"
              class="!rounded-md"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="档案名称" prop="rectifyArchName">
        <el-input
          v-model="formData.rectifyArchName"
          placeholder="请输入档案名称"
          class="!rounded-md"
        />
      </el-form-item>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="关联问题类型" prop="problemTypeName">
            <el-input
              v-model="formData.problemTypeName"
              placeholder="请输入关联问题类型"
              class="!rounded-md"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="档案状态" prop="rectifyArchStatus">
            <el-radio-group v-model="formData.rectifyArchStatus">
              <el-radio value="已归档">已归档</el-radio>
              <el-radio value="待归档">待归档</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 区域信息行 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="所属区域代码" prop="areaCode">
            <el-input
              v-model="formData.areaCode"
              placeholder="请输入所属区域代码"
              class="!rounded-md"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属区域名称" prop="areaName">
            <el-input
              v-model="formData.areaName"
              placeholder="请输入所属区域名称"
              class="!rounded-md"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 整改信息行 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="归档周期" prop="rectifyArchCycle">
            <el-input
              v-model="formData.rectifyArchCycle"
              placeholder="请输入归档周期"
              class="!rounded-md"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="整改任务数" prop="includeTaskCount">
            <el-input
              v-model="formData.includeTaskCount"
              type="number"
              placeholder="请输入包含整改任务数"
              class="!rounded-md"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 时间与完成率行 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="归档时间" prop="rectifyArchTime">
            <el-date-picker
              v-model="formData.rectifyArchTime"
              type="date"
              value-format="x"
              placeholder="选择归档时间"
              class="!rounded-md"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="整改完成率(%)" prop="rectifyCompleteRate">
            <el-input
              v-model="formData.rectifyCompleteRate"
              type="number"
              min="0"
              max="100"
              placeholder="请输入整改完成率"
              class="!rounded-md"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="档案附件URLs" prop="rectifyAttachUrls">
        <el-input
          v-model="formData.rectifyAttachUrls"
          placeholder="请输入档案附件URLs（多个用逗号分隔）"
          class="!rounded-md"
        />
      </el-form-item>

      <el-form-item label="归档说明" prop="rectifyArchDesc">
        <el-input
          v-model="formData.rectifyArchDesc"
          placeholder="请输入归档说明"
          type="textarea"
          :rows="3"
          class="!rounded-md"
        />
      </el-form-item>

      <el-form-item label="销毁时间" prop="rectifyDestroyTime">
        <el-date-picker
          v-model="formData.rectifyDestroyTime"
          type="date"
          value-format="x"
          placeholder="选择销毁时间（可选）"
          class="!rounded-md"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="dialogVisible = false" class="!mr-5px">取 消</el-button>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  InspectRectifyArchApi,
  InspectRectifyArchVO
} from '@/api/dataHub/supervisionInspection/patrolArchiveManagement/rectificationArchive'

/** 巡查巡检整改档案 表单 */
defineOptions({ name: 'InspectRectifyArchForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

const formData = ref<Partial<InspectRectifyArchVO>>({
  id: undefined,
  rectifyArchId: undefined,
  rectifyArchCode: undefined,
  rectifyArchName: undefined,
  areaCode: undefined,
  areaName: undefined,
  rectifyArchCycle: undefined,
  problemTypeName: undefined,
  includeTaskCount: undefined,
  rectifyCompleteRate: undefined,
  rectifyAttachUrls: undefined,
  rectifyArchUser: undefined,
  rectifyArchTime: undefined,
  rectifyArchStatus: '1', // 默认正常状态
  rectifyDestroyTime: undefined,
  rectifyArchDesc: undefined
  // 隐藏扩展字段（由系统自动处理）
})

const formRules = reactive({
  rectifyArchId: [{ required: true, message: '请输入档案ID', trigger: 'blur' }],
  rectifyArchCode: [{ required: true, message: '请输入档案编码', trigger: 'blur' }],
  rectifyArchName: [{ required: true, message: '请输入档案名称', trigger: 'blur' }],
  areaCode: [{ required: true, message: '请输入所属区域代码', trigger: 'blur' }],
  areaName: [{ required: true, message: '请输入所属区域名称', trigger: 'blur' }],
  rectifyArchCycle: [{ required: true, message: '请输入归档周期', trigger: 'blur' }],
  problemTypeName: [{ required: true, message: '请输入关联问题类型', trigger: 'blur' }],
  includeTaskCount: [
    { required: true, message: '请输入包含整改任务数', trigger: 'blur', type: 'number' }
  ],
  rectifyCompleteRate: [
    { required: true, message: '请输入整改完成率', trigger: 'blur', type: 'number' },
    { min: 0, max: 100, message: '完成率范围为0-100', trigger: 'change' }
  ],
  rectifyArchUser: [{ required: true, message: '请输入归档人', trigger: 'blur' }],
  rectifyArchTime: [{ required: true, message: '请选择归档时间', trigger: 'change' }],
  rectifyArchStatus: [{ required: true, message: '请选择档案状态', trigger: 'change' }]
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
      const res = await InspectRectifyArchApi.getInspectRectifyArch(id)
      formData.value = { ...res }
    } finally {
      formLoading.value = false
    }
  }
}

defineExpose({ open }) // 提供 open 方法，用于打开弹窗

/** 提交表单 */
const emit = defineEmits(['success']) // 定义 success 事件
const submitForm = async () => {
  // 校验表单
  await formRef.value.validate()
  // 提交请求
  formLoading.value = true
  try {
    const data = formData.value as InspectRectifyArchVO
    if (formType.value === 'create') {
      await InspectRectifyArchApi.createInspectRectifyArch(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectRectifyArchApi.updateInspectRectifyArch(data)
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
    rectifyArchId: undefined,
    rectifyArchCode: undefined,
    rectifyArchName: undefined,
    areaCode: undefined,
    areaName: undefined,
    rectifyArchCycle: undefined,
    problemTypeName: undefined,
    includeTaskCount: undefined,
    rectifyCompleteRate: undefined,
    rectifyAttachUrls: undefined,
    rectifyArchUser: undefined,
    rectifyArchTime: undefined,
    rectifyArchStatus: '1',
    rectifyDestroyTime: undefined,
    rectifyArchDesc: undefined
  }
  formRef.value?.resetFields()
}
</script>
<style scoped>
.space-y-6 {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.el-form-item__label {
  font-weight: 500 !important;
  color: #4b5563 !important;
}

.el-input,
.el-select,
.el-date-picker,
.el-textarea {
  border-color: #e5e7eb !important;
}

.el-input:focus-within,
.el-select:focus-within,
.el-date-picker:focus-within,
.el-textarea:focus-within {
  border-color: #f472b6 !important;
  box-shadow: 0 0 0 2px rgba(244, 114, 182, 0.2) !important;
}
</style>
