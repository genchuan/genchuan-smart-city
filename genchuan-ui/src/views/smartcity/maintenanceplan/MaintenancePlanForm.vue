<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="计划编号" prop="planNumber">
        <el-input v-model="formData.planNumber" placeholder="请输入计划编号" />
      </el-form-item>
      <el-form-item label="计划名称" prop="planName">
        <el-input v-model="formData.planName" placeholder="请输入计划名称" />
      </el-form-item>
      <el-form-item label="计划制定日期" prop="planFormulationDate">
        <el-date-picker
          v-model="formData.planFormulationDate"
          type="date"
          value-format="x"
          placeholder="选择计划制定日期"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="计划开始日期" prop="startDate">
        <el-date-picker
          v-model="formData.startDate"
          type="date"
          value-format="x"
          placeholder="选择计划开始日期"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="计划结束日期" prop="plannedEndDate">
        <el-date-picker
          v-model="formData.plannedEndDate"
          type="date"
          value-format="x"
          placeholder="选择计划结束日期"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="负责养护人员" prop="maintenancePersonnel">
        <el-input v-model="formData.maintenancePersonnel" placeholder="请输入负责养护人员" />
      </el-form-item>
      <el-form-item label="养护地块" prop="maintainParcel">
        <el-input v-model="formData.maintainParcel" placeholder="请输入养护地块" />
      </el-form-item>
      <el-form-item label="养护内容" prop="maintenanceContent">
        <el-input v-model="formData.maintenanceContent" type="textarea" placeholder="请输入养护内容" />
      </el-form-item>
      <el-form-item label="养护频率" prop="maintenanceFrequency">
        <el-input v-model="formData.maintenanceFrequency" placeholder="请输入养护频率" />
      </el-form-item>
      <el-form-item label="养护资源需求" prop="maintenanceDemand">
        <el-input v-model="formData.maintenanceDemand" placeholder="请输入养护资源需求" />
      </el-form-item>
      <!--<el-form-item label="计划状态" prop="planStatus">-->
      <!--  <el-radio-group v-model="formData.planStatus">-->
      <!--    <el-radio value="1">请选择字典生成</el-radio>-->
      <!--  </el-radio-group>-->
      <!--</el-form-item>-->
      <el-form-item label="计划预算" prop="plannedBudget">
        <el-input v-model="formData.plannedBudget" placeholder="请输入计划预算" />
      </el-form-item>
      <!--<el-form-item label="审批人" prop="approver">-->
      <!--  <el-input v-model="formData.approver" placeholder="请输入审批人" />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="审批意见" prop="approvalOpinion">-->
      <!--  <el-input v-model="formData.approvalOpinion" placeholder="请输入审批意见" />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="审批日期" prop="approvalDate">-->
      <!--  <el-date-picker-->
      <!--    v-model="formData.approvalDate"-->
      <!--    type="date"-->
      <!--    value-format="x"-->
      <!--    placeholder="选择审批日期"-->
      <!--  />-->
      <!--</el-form-item>-->
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { MaintenancePlanApi, MaintenancePlanVO } from '@/api/smartcity/maintenanceplan'

/** 养护计划 表单 */
defineOptions({ name: 'MaintenancePlanForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  planNumber: undefined,
  planName: undefined,
  planFormulationDate: undefined,
  startDate: undefined,
  plannedEndDate: undefined,
  maintenancePersonnel: undefined,
  maintainParcel: undefined,
  maintenanceContent: undefined,
  maintenanceFrequency: undefined,
  maintenanceDemand: undefined,
  planStatus: undefined,
  plannedBudget: undefined,
  approver: undefined,
  approvalOpinion: undefined,
  approvalDate: undefined
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
      formData.value = await MaintenancePlanApi.getMaintenancePlan(id)
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
    const data = formData.value as unknown as MaintenancePlanVO
    if (formType.value === 'create') {
      await MaintenancePlanApi.createMaintenancePlan(data)
      message.success(t('common.createSuccess'))
    } else {
      await MaintenancePlanApi.updateMaintenancePlan(data)
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
    planNumber: undefined,
    planName: undefined,
    planFormulationDate: undefined,
    startDate: undefined,
    plannedEndDate: undefined,
    maintenancePersonnel: undefined,
    maintainParcel: undefined,
    maintenanceContent: undefined,
    maintenanceFrequency: undefined,
    maintenanceDemand: undefined,
    planStatus: undefined,
    plannedBudget: undefined,
    approver: undefined,
    approvalOpinion: undefined,
    approvalDate: undefined
  }
  formRef.value?.resetFields()
}
</script>
