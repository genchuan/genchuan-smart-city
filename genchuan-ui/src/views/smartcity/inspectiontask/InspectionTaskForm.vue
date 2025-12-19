<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="150px"
      v-loading="formLoading"
    >
      <el-form-item label="任务ID" prop="taskId">
        <el-input v-model="formData.taskId" placeholder="请输入任务ID" />
      </el-form-item>
      <el-form-item label="任务名称" prop="taskName">
        <el-input v-model="formData.taskName" placeholder="请输入任务名称" />
      </el-form-item>
      <el-form-item label="发起部门" prop="initiatingDepartment">
        <el-input v-model="formData.initiatingDepartment" placeholder="请输入发起部门" />
      </el-form-item>
      <el-form-item label="计划执行时间" prop="plannedExecutionTime">
        <el-date-picker
          v-model="formData.plannedExecutionTime"
          type="datetime"
          value-format="x"
          placeholder="请选择计划执行时间"
        />
      </el-form-item>
      <el-form-item label="截止时间" prop="deadline">
        <el-date-picker
          v-model="formData.deadline"
          type="datetime"
          value-format="x"
          placeholder="请选择截止时间"
        />
      </el-form-item>
      <!-- 所属行业选择框 -->
      <el-form-item label="所属行业" prop="sector">
        <el-select v-model="formData.sector" placeholder="请选择所属行业">
          <el-option value="manufacturing" label="制造业" />
          <el-option value="construction" label="建筑业" />
          <el-option value="transportation" label="交通运输业" />
          <el-option value="logistics" label="物流业" />
          <el-option value="retail" label="零售业" />
          <el-option value="finance" label="金融业" />
          <el-option value="it_service" label="IT服务业" />
          <el-option value="education" label="教育行业" />
          <el-option value="medical_health" label="医疗卫生业" />
          <el-option value="hotel_catering" label="酒店餐饮业" />
          <el-option value="cultural_tourism" label="文化旅游业" />
          <el-option value="energy" label="能源行业" />
          <el-option value="environmental_protection" label="环保行业" />
          <el-option value="agriculture" label="农业" />
          <el-option value="real_estate" label="房地产业" />
          <el-option value="public_service" label="公共服务业" />
        </el-select>
      </el-form-item>
      <el-form-item label="检查对象数量" prop="numberObjects">
        <el-input v-model="formData.numberObjects" placeholder="请输入检查对象数量" />
      </el-form-item>
      <el-form-item label="执法人员数量" prop="numberPersonnel">
        <el-input v-model="formData.numberPersonnel" placeholder="请输入执法人员数量" />
      </el-form-item>
      <el-form-item label="检查事项" prop="inspectionItems">
        <el-input v-model="formData.inspectionItems" placeholder="请输入检查事项" />
      </el-form-item>
      <!-- 完成情况选择框 -->
      <el-form-item label="完成情况" prop="completionStatus">
        <el-select v-model="formData.completionStatus" placeholder="请选择完成情况">
          <el-option value="not_started" label="未开始" />
          <el-option value="in_progress" label="进行中" />
          <el-option value="completed" label="已完成" />
          <el-option value="overdue" label="已逾期" />
        </el-select>
      </el-form-item>
      <el-form-item label="负责人" prop="personInCharge">
        <el-input v-model="formData.personInCharge" placeholder="请输入负责人" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { InspectionTaskApi, InspectionTaskVO } from '@/api/smartcity/inspectiontask'

/** 检查任务管理 表单 */
defineOptions({ name: 'InspectionTaskForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  taskId: undefined,
  taskName: undefined,
  initiatingDepartment: undefined,
  plannedExecutionTime: undefined,
  deadline: undefined,
  sector: undefined,
  numberObjects: undefined,
  numberPersonnel: undefined,
  inspectionItems: undefined,
  completionStatus: undefined,
  personInCharge: undefined
})
const formRules = reactive({
  taskId: [{ required: true, message: '请输入任务ID', trigger: 'blur' }],
  taskName: [{ required: true, message: '请输入任务名称', trigger: 'blur' }]
})
const formRef = ref() // 表单 ref

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
      formData.value = await InspectionTaskApi.getInspectionTask(id)
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
    const data = formData.value as unknown as InspectionTaskVO
    if (formType.value === 'create') {
      await InspectionTaskApi.createInspectionTask(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectionTaskApi.updateInspectionTask(data)
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
    taskName: undefined,
    initiatingDepartment: undefined,
    plannedExecutionTime: undefined,
    deadline: undefined,
    sector: undefined,
    numberObjects: undefined,
    numberPersonnel: undefined,
    inspectionItems: undefined,
    completionStatus: undefined,
    personInCharge: undefined
  }
  formRef.value?.resetFields()
}
</script>
