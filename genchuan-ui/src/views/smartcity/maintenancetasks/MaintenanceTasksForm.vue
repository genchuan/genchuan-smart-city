<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="任务编号" prop="taskNumber">
        <el-input v-model="formData.taskNumber" placeholder="请输入任务编号" />
      </el-form-item>
      <el-form-item label="任务名称" prop="task">
        <el-input v-model="formData.task" placeholder="请输入任务名称" />
      </el-form-item>
      <el-form-item label="养护地块" prop="maintainTheLandParcel">
        <el-input v-model="formData.maintainTheLandParcel" placeholder="请输入养护地块" />
      </el-form-item>
      <el-form-item label="养护人员" prop="maintenancePersonnel">
        <el-input v-model="formData.maintenancePersonnel" placeholder="请输入养护人员" />
      </el-form-item>
      <el-form-item label="任务开始时间" prop="taskStartTime">
        <el-date-picker
          v-model="formData.taskStartTime"
          type="date"
          value-format="x"
          placeholder="选择任务开始时间"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="任务结束时间" prop="taskEndTime">
        <el-date-picker
          v-model="formData.taskEndTime"
          type="date"
          value-format="x"
          placeholder="选择任务结束时间"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="任务内容" prop="taskContent">
        <el-input v-model="formData.taskContent" type="textarea" placeholder="请输入任务内容" />
      </el-form-item>
      <el-form-item label="所需工具" prop="requiredTools">
        <el-input v-model="formData.requiredTools" placeholder="请输入所需工具" />
      </el-form-item>
      <!--<el-form-item label="所需材料" prop="requiredMaterials">-->
      <!--  <el-input v-model="formData.requiredMaterials" placeholder="请输入所需材料" />-->
      <!--</el-form-item>-->
      <el-form-item label="任务优先级" prop="taskPriority">
        <el-input v-model="formData.taskPriority" placeholder="请输入任务优先级" />
      </el-form-item>
      <!--<el-form-item label="任务状态" prop="status">-->
      <!--  <el-radio-group v-model="formData.status">-->
      <!--    <el-radio value="1">请选择字典生成</el-radio>-->
      <!--  </el-radio-group>-->
      <!--</el-form-item>-->
      <el-form-item label="完成情况说明" prop="completionStatusDescription">
        <el-input v-model="formData.completionStatusDescription" type="textarea" placeholder="请输入完成情况说明" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { MaintenanceTasksApi, MaintenanceTasksVO } from '@/api/smartcity/maintenancetasks'

/** 养护任务 表单 */
defineOptions({ name: 'MaintenanceTasksForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  taskNumber: undefined,
  task: undefined,
  maintainTheLandParcel: undefined,
  maintenancePersonnel: undefined,
  taskStartTime: undefined,
  taskEndTime: undefined,
  taskContent: undefined,
  requiredTools: undefined,
  requiredMaterials: undefined,
  taskPriority: undefined,
  status: undefined,
  completionStatusDescription: undefined
})
const formRules = reactive({
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
      formData.value = await MaintenanceTasksApi.getMaintenanceTasks(id)
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
    const data = formData.value as unknown as MaintenanceTasksVO
    if (formType.value === 'create') {
      await MaintenanceTasksApi.createMaintenanceTasks(data)
      message.success(t('common.createSuccess'))
    } else {
      await MaintenanceTasksApi.updateMaintenanceTasks(data)
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
    taskNumber: undefined,
    task: undefined,
    maintainTheLandParcel: undefined,
    maintenancePersonnel: undefined,
    taskStartTime: undefined,
    taskEndTime: undefined,
    taskContent: undefined,
    requiredTools: undefined,
    requiredMaterials: undefined,
    taskPriority: undefined,
    status: undefined,
    completionStatusDescription: undefined
  }
  formRef.value?.resetFields()
}
</script>
