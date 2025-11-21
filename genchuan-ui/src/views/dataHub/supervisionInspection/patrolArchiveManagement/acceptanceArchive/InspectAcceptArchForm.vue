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
          <el-form-item label="档案ID" prop="acceptArchId">
            <el-input
              v-model="formData.acceptArchId"
              placeholder="请输入档案ID"
              class="!rounded-md"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="档案编码" prop="acceptArchCode">
            <el-input
              v-model="formData.acceptArchCode"
              placeholder="请输入档案编码"
              class="!rounded-md"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="档案名称" prop="acceptArchName">
        <el-input
          v-model="formData.acceptArchName"
          placeholder="请输入档案名称"
          class="!rounded-md"
        />
      </el-form-item>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="档案状态" prop="acceptArchStatus">
            <el-radio-group v-model="formData.acceptArchStatus">
              <el-radio value="已归档">已归档</el-radio>
              <el-radio value="待归档">待归档</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="归档周期" prop="acceptArchCycle">
            <el-input
              v-model="formData.acceptArchCycle"
              placeholder="请输入归档周期"
              class="!rounded-md"
            />
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

      <!-- 验收信息行 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="验收记录数" prop="includeAcceptCount">
            <el-input
              v-model="formData.includeAcceptCount"
              type="number"
              placeholder="请输入包含验收记录数"
              class="!rounded-md"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="验收合格率(%)" prop="acceptQualifiedRate">
            <el-input
              v-model="formData.acceptQualifiedRate"
              type="number"
              min="0"
              max="100"
              placeholder="请输入验收合格率"
              class="!rounded-md"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 时间信息行 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="归档时间" prop="acceptArchTime">
            <el-date-picker
              v-model="formData.acceptArchTime"
              type="date"
              value-format="x"
              placeholder="选择归档时间"
              class="!rounded-md"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="销毁时间" prop="acceptDestroyTime">
            <el-date-picker
              v-model="formData.acceptDestroyTime"
              type="date"
              value-format="x"
              placeholder="选择销毁时间（可选）"
              class="!rounded-md"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="档案附件URLs" prop="acceptAttachUrls">
        <el-input
          v-model="formData.acceptAttachUrls"
          placeholder="请输入档案附件URLs（多个用逗号分隔）"
          class="!rounded-md"
        />
      </el-form-item>

      <el-form-item label="归档说明" prop="acceptArchDesc">
        <el-input
          v-model="formData.acceptArchDesc"
          placeholder="请输入归档说明"
          type="textarea"
          :rows="3"
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
  InspectAcceptArchApi,
  InspectAcceptArchVO
} from '@/api/dataHub/supervisionInspection/patrolArchiveManagement/acceptanceArchive'

/** 巡查巡检验收档案 表单 */
defineOptions({ name: 'InspectAcceptArchForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

const formData = ref<Partial<InspectAcceptArchVO>>({
  id: undefined,
  acceptArchId: undefined,
  acceptArchCode: undefined,
  acceptArchName: undefined,
  areaCode: undefined,
  areaName: undefined,
  acceptArchCycle: undefined,
  includeAcceptCount: undefined,
  acceptQualifiedRate: undefined,
  acceptAttachUrls: undefined,
  acceptArchUser: undefined,
  acceptArchTime: undefined,
  acceptArchStatus: '1', // 默认正常状态
  acceptDestroyTime: undefined,
  acceptArchDesc: undefined
  // 隐藏扩展字段（由系统自动处理）
})

const formRules = reactive({
  acceptArchId: [{ required: true, message: '请输入档案ID', trigger: 'blur' }],
  acceptArchCode: [{ required: true, message: '请输入档案编码', trigger: 'blur' }],
  acceptArchName: [{ required: true, message: '请输入档案名称', trigger: 'blur' }],
  areaCode: [{ required: true, message: '请输入所属区域代码', trigger: 'blur' }],
  areaName: [{ required: true, message: '请输入所属区域名称', trigger: 'blur' }],
  acceptArchCycle: [{ required: true, message: '请输入归档周期', trigger: 'blur' }],
  includeAcceptCount: [
    { required: true, message: '请输入包含验收记录数', trigger: 'blur', type: 'number' }
  ],
  acceptQualifiedRate: [
    { required: true, message: '请输入验收合格率', trigger: 'blur', type: 'number' }
    // { min: 0, max: 100, message: '合格率范围为0-100', trigger: 'change' }
  ],
  acceptArchUser: [{ required: true, message: '请输入归档人', trigger: 'blur' }],
  acceptArchTime: [{ required: true, message: '请选择归档时间', trigger: 'change' }],
  acceptArchStatus: [{ required: true, message: '请选择档案状态', trigger: 'change' }]
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
      const res = await InspectAcceptArchApi.getInspectAcceptArch(id)
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
    const data = formData.value as InspectAcceptArchVO
    if (formType.value === 'create') {
      await InspectAcceptArchApi.createInspectAcceptArch(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectAcceptArchApi.updateInspectAcceptArch(data)
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
    acceptArchId: undefined,
    acceptArchCode: undefined,
    acceptArchName: undefined,
    areaCode: undefined,
    areaName: undefined,
    acceptArchCycle: undefined,
    includeAcceptCount: undefined,
    acceptQualifiedRate: undefined,
    acceptAttachUrls: undefined,
    acceptArchUser: undefined,
    acceptArchTime: undefined,
    acceptArchStatus: undefined,
    acceptDestroyTime: undefined,
    acceptArchDesc: undefined
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
  border-color: #60a5fa !important;
  box-shadow: 0 0 0 2px rgba(96, 165, 250, 0.2) !important;
}
</style>
