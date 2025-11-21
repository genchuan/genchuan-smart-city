<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" :width="800">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
      class="custom-form"
    >
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="任务ID" prop="taskId">
            <el-input v-model="formData.taskId" placeholder="请输入任务ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="任务编码" prop="taskCode">
            <el-input v-model="formData.taskCode" placeholder="请输入任务编码" />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="任务名称" prop="taskName" required>
            <el-input v-model="formData.taskName" placeholder="请输入任务名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="任务类型" prop="taskType">
            <el-select v-model="formData.taskType" placeholder="请选择任务类型">
              <el-option label="请选择字典生成" value="" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="任务状态" prop="taskStatus">
            <el-radio-group v-model="formData.taskStatus">
              <el-radio value="1">已完成</el-radio>
              <el-radio value="0">未完成</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="负责人员ID" prop="personId">
            <el-input v-model="formData.personId" placeholder="请输入负责人员ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="负责人员姓名" prop="personName" required>
            <el-input v-model="formData.personName" placeholder="请输入负责人员姓名" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属区域代码" prop="areaCode">
            <el-input v-model="formData.areaCode" placeholder="请输入所属区域代码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属区域名称" prop="areaName">
            <el-input v-model="formData.areaName" placeholder="请输入所属区域名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="开始时间" prop="startTime">
            <el-date-picker
              v-model="formData.startTime"
              type="date"
              value-format="x"
              placeholder="选择开始时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="截止时间" prop="deadlineTime" required>
            <el-date-picker
              v-model="formData.deadlineTime"
              type="date"
              value-format="x"
              placeholder="选择截止时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="完成时间" prop="completeTime">
            <el-date-picker
              v-model="formData.completeTime"
              type="date"
              value-format="x"
              placeholder="选择完成时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="检查项总数" prop="totalCkItem">
            <el-input v-model.number="formData.totalCkItem" placeholder="请输入检查项总数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="已完成检查项" prop="completedCkItem">
            <el-input v-model.number="formData.completedCkItem" placeholder="请输入已完成检查项" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="上传照片数" prop="uploadPhotoCount">
            <el-input v-model.number="formData.uploadPhotoCount" placeholder="请输入上传照片数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="是否超时" prop="isOvertime">
            <el-radio-group v-model="formData.isOvertime">
              <el-radio :label="1">是</el-radio>
              <el-radio :label="0">否</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="催办次数" prop="remindCount">
            <el-input v-model.number="formData.remindCount" placeholder="请输入催办次数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="最后催办时间" prop="lastRemindTime">
            <el-date-picker
              v-model="formData.lastRemindTime"
              type="date"
              value-format="x"
              placeholder="选择最后催办时间"
            />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  InspectTaskTrackApi,
  InspectTaskTrackVO
} from '@/api/dataHub/supervisionInspection/patrolTaskAssignment/taskTracking'

/** 巡查巡检任务跟踪 表单 */
defineOptions({ name: 'InspectTaskTrackForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<InspectTaskTrackVO>>({
  id: undefined,
  taskId: undefined,
  taskCode: undefined,
  taskName: undefined,
  taskType: undefined,
  personId: undefined,
  personName: undefined,
  areaCode: undefined,
  areaName: undefined,
  taskStatus: undefined,
  startTime: undefined,
  completeTime: undefined,
  deadlineTime: undefined,
  totalCkItem: undefined,
  completedCkItem: undefined,
  uploadPhotoCount: undefined,
  isOvertime: undefined,
  remindCount: undefined,
  lastRemindTime: undefined
})
const formRules = reactive({
  taskName: [{ required: true, message: '请输入任务名称', trigger: 'blur' }],
  personName: [{ required: true, message: '请输入负责人员姓名', trigger: 'blur' }],
  deadlineTime: [{ required: true, message: '请选择截止时间', trigger: 'change' }]
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
      const data = await InspectTaskTrackApi.getInspectTaskTrack(id)
      formData.value = { ...data }
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
    const data = formData.value as unknown as InspectTaskTrackVO
    if (formType.value === 'create') {
      await InspectTaskTrackApi.createInspectTaskTrack(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectTaskTrackApi.updateInspectTaskTrack(data)
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
    taskId: undefined,
    taskCode: undefined,
    taskName: undefined,
    taskType: undefined,
    personId: undefined,
    personName: undefined,
    areaCode: undefined,
    areaName: undefined,
    taskStatus: undefined,
    startTime: undefined,
    completeTime: undefined,
    deadlineTime: undefined,
    totalCkItem: undefined,
    completedCkItem: undefined,
    uploadPhotoCount: undefined,
    isOvertime: undefined,
    remindCount: undefined,
    lastRemindTime: undefined
  }
  formRef.value?.resetFields()
}
</script>

<style scoped>
.custom-form {
  padding: 16px;
  background-color: #fff;
}

::v-deep .el-form-item {
  margin-bottom: 16px;
}

::v-deep .el-form-item__label {
  font-weight: 500;
  color: #606266;
}

::v-deep .el-input,
::v-deep .el-select,
::v-deep .el-date-picker {
  width: 100%;
}
</style>
