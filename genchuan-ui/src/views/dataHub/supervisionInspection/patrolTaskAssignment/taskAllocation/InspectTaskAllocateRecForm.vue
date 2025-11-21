<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="700px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="140px"
      v-loading="formLoading"
      class="custom-form"
    >
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="分配ID" prop="allocateId">
            <el-input v-model="formData.allocateId" placeholder="请输入分配ID" />
          </el-form-item>
        </el-col>
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
        <el-col :span="12">
          <el-form-item label="任务类型" prop="taskType">
            <el-select v-model="formData.taskType" placeholder="请选择任务类型">
              <el-option label="请选择字典生成" value="" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="原负责人员ID" prop="oldPersonId">
            <el-input v-model="formData.oldPersonId" placeholder="请输入原负责人员ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="原负责人员姓名" prop="oldPersonName">
            <el-input v-model="formData.oldPersonName" placeholder="请输入原负责人员姓名" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="新负责人员ID" prop="newPersonId">
            <el-input v-model="formData.newPersonId" placeholder="请输入新负责人员ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="新负责人员姓名" prop="newPersonName">
            <el-input v-model="formData.newPersonName" placeholder="请输入新负责人员姓名" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分配人" prop="allocateUser">
            <el-input v-model="formData.allocateUser" placeholder="请输入分配人" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分配时间" prop="allocateTime">
            <el-date-picker
              v-model="formData.allocateTime"
              type="date"
              value-format="x"
              placeholder="选择分配时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="分配原因" prop="allocateReason">
            <el-input
              v-model="formData.allocateReason"
              placeholder="请输入分配原因"
              type="textarea"
              rows="3"
            />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="任务状态变更" prop="taskStatusChange">
            <el-input
              v-model="formData.taskStatusChange"
              placeholder="请输入任务状态变更"
              type="textarea"
              rows="3"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分类扩展字段1" prop="extCat1">
            <el-input v-model="formData.extCat1" placeholder="请输入分类扩展字段1" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分类扩展字段2" prop="extCat2">
            <el-input v-model="formData.extCat2" placeholder="请输入分类扩展字段2" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="通用扩展字段1" prop="extCommon1">
            <el-input v-model="formData.extCommon1" placeholder="请输入通用扩展字段1" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="通用扩展字段2" prop="extCommon2">
            <el-input v-model="formData.extCommon2" placeholder="请输入通用扩展字段2" />
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
  InspectTaskAllocateRecApi,
  InspectTaskAllocateRecVO
} from '@/api/dataHub/supervisionInspection/patrolTaskAssignment/taskAllocation'

/** 巡查巡检任务分配记录 表单 */
defineOptions({ name: 'InspectTaskAllocateRecForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  allocateId: undefined,
  taskId: undefined,
  taskCode: undefined,
  oldPersonId: undefined,
  oldPersonName: undefined,
  newPersonId: undefined,
  newPersonName: undefined,
  allocateReason: undefined,
  allocateTime: undefined,
  allocateUser: undefined,
  taskStatusChange: undefined,
  taskType: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  allocateId: [{ required: true, message: '分配ID不能为空', trigger: 'blur' }],
  taskId: [{ required: true, message: '任务ID不能为空', trigger: 'blur' }],
  taskCode: [{ required: true, message: '任务编码不能为空', trigger: 'blur' }],
  newPersonId: [{ required: true, message: '新负责人员ID不能为空', trigger: 'blur' }],
  newPersonName: [{ required: true, message: '新负责人员姓名不能为空', trigger: 'blur' }],
  allocateReason: [{ required: true, message: '分配原因不能为空', trigger: 'blur' }],
  allocateTime: [{ required: true, message: '分配时间不能为空', trigger: 'blur' }],
  allocateUser: [{ required: true, message: '分配人不能为空', trigger: 'blur' }],
  taskStatusChange: [{ required: true, message: '任务状态变更不能为空', trigger: 'blur' }],
  taskType: [{ required: true, message: '任务类型不能为空', trigger: 'change' }]
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
      formData.value = await InspectTaskAllocateRecApi.getInspectTaskAllocateRec(id)
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
    const data = formData.value as unknown as InspectTaskAllocateRecVO
    if (formType.value === 'create') {
      await InspectTaskAllocateRecApi.createInspectTaskAllocateRec(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectTaskAllocateRecApi.updateInspectTaskAllocateRec(data)
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
    allocateId: undefined,
    taskId: undefined,
    taskCode: undefined,
    oldPersonId: undefined,
    oldPersonName: undefined,
    newPersonId: undefined,
    newPersonName: undefined,
    allocateReason: undefined,
    allocateTime: undefined,
    allocateUser: undefined,
    taskStatusChange: undefined,
    taskType: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined
  }
  formRef.value?.resetFields()
}
</script>

<style scoped>
.custom-form {
  padding: 15px;
}

::v-deep .el-form-item__label {
  font-weight: 500;
}

::v-deep .el-textarea__inner {
  resize: none;
}
</style>
