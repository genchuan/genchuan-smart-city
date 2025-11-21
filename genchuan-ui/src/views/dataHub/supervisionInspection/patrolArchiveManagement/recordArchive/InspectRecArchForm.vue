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
          <el-form-item label="档案ID" prop="archId">
            <el-input v-model="formData.archId" placeholder="请输入档案ID" class="!rounded-md" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="档案编码" prop="archCode">
            <el-input
              v-model="formData.archCode"
              placeholder="请输入档案编码"
              class="!rounded-md"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="档案名称" prop="archName">
        <el-input v-model="formData.archName" placeholder="请输入档案名称" class="!rounded-md" />
      </el-form-item>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="档案类型" prop="archType">
            <el-select v-model="formData.archType" placeholder="请选择档案类型" class="!rounded-md">
              <el-option label="请选择字典生成" value="" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="档案状态" prop="archStatus">
            <el-radio-group v-model="formData.archStatus">
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

      <!-- 归档信息行 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="归档周期" prop="archCycle">
            <el-input
              v-model="formData.archCycle"
              placeholder="请输入归档周期"
              class="!rounded-md"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="包含记录数" prop="includeRecCount">
            <el-input
              v-model="formData.includeRecCount"
              type="number"
              placeholder="请输入包含记录数"
              class="!rounded-md"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 时间信息行 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="归档时间" prop="archTime">
            <el-date-picker
              v-model="formData.archTime"
              type="date"
              value-format="x"
              placeholder="选择归档时间"
              class="!rounded-md"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="销毁时间" prop="destroyTime">
            <el-date-picker
              v-model="formData.destroyTime"
              type="date"
              value-format="x"
              placeholder="选择销毁时间（可选）"
              class="!rounded-md"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="档案附件URLs" prop="archAttachUrls">
        <el-input
          v-model="formData.archAttachUrls"
          placeholder="请输入档案附件URLs（多个用逗号分隔）"
          class="!rounded-md"
        />
      </el-form-item>

      <el-form-item label="归档说明" prop="archDesc">
        <el-input
          v-model="formData.archDesc"
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
  InspectRecArchApi,
  InspectRecArchVO
} from '@/api/dataHub/supervisionInspection/patrolArchiveManagement/recordArchive'

/** 巡查巡检记录档案 表单 */
defineOptions({ name: 'InspectRecArchForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

const formData = ref<Partial<InspectRecArchVO>>({
  id: undefined,
  archId: undefined,
  archCode: undefined,
  archName: undefined,
  archType: undefined,
  areaCode: undefined,
  areaName: undefined,
  archCycle: undefined,
  includeRecCount: undefined,
  archAttachUrls: undefined,
  archUser: undefined,
  archTime: undefined,
  archStatus: '1', // 默认正常状态
  destroyTime: undefined,
  archDesc: undefined
  // 隐藏扩展字段（由系统自动处理或无需手动输入）
})

const formRules = reactive({
  archId: [{ required: true, message: '请输入档案ID', trigger: 'blur' }],
  archCode: [{ required: true, message: '请输入档案编码', trigger: 'blur' }],
  archName: [{ required: true, message: '请输入档案名称', trigger: 'blur' }],
  archType: [{ required: true, message: '请选择档案类型', trigger: 'change' }],
  areaCode: [{ required: true, message: '请输入所属区域代码', trigger: 'blur' }],
  areaName: [{ required: true, message: '请输入所属区域名称', trigger: 'blur' }],
  archCycle: [{ required: true, message: '请输入归档周期', trigger: 'blur' }],
  includeRecCount: [
    { required: true, message: '请输入包含记录数', trigger: 'blur', type: 'number' }
  ],
  archUser: [{ required: true, message: '请输入归档人', trigger: 'blur' }],
  archTime: [{ required: true, message: '请选择归档时间', trigger: 'change' }],
  archStatus: [{ required: true, message: '请选择档案状态', trigger: 'change' }]
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
      const res = await InspectRecArchApi.getInspectRecArch(id)
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
    const data = formData.value as InspectRecArchVO
    if (formType.value === 'create') {
      await InspectRecArchApi.createInspectRecArch(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectRecArchApi.updateInspectRecArch(data)
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
    archId: undefined,
    archCode: undefined,
    archName: undefined,
    archType: undefined,
    areaCode: undefined,
    areaName: undefined,
    archCycle: undefined,
    includeRecCount: undefined,
    archAttachUrls: undefined,
    archUser: undefined,
    archTime: undefined,
    archStatus: '1',
    destroyTime: undefined,
    archDesc: undefined
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
  border-color: #94a3b8 !important;
  box-shadow: 0 0 0 2px rgba(148, 163, 184, 0.2) !important;
}
</style>
