<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="计划名称" prop="planName">
        <el-input v-model="formData.planName" placeholder="请输入计划名称" />
      </el-form-item>
      <el-form-item label="巡查区域" prop="patrolArea">
        <el-input v-model="formData.patrolArea" placeholder="请输入巡查区域" />
      </el-form-item>
      <el-form-item label="巡查周期" prop="inspectionCycle">
        <el-input v-model="formData.inspectionCycle" placeholder="请输入巡查周期" />
      </el-form-item>
      <el-form-item label="计划开始时间" prop="scheduledStartTime">
        <el-date-picker
          v-model="formData.scheduledStartTime"
          type="date"
          value-format="x"
          placeholder="选择计划开始时间"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="计划结束时间" prop="plannedEndTime">
        <el-date-picker
          v-model="formData.plannedEndTime"
          type="date"
          value-format="x"
          placeholder="选择计划结束时间"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="巡查人员安排" prop="arrangementOfPatrolPersonnel">
        <el-input v-model="formData.arrangementOfPatrolPersonnel" placeholder="请输入巡查人员安排" />
      </el-form-item>
      <el-form-item label="巡查区域" prop="inspectionContent">
        <el-input v-model="formData.inspectionContent" type="textarea" placeholder="请输入巡查区域" />
      </el-form-item>
      <el-form-item label="巡查标准" prop="inspectionStandards">
        <el-input v-model="formData.inspectionStandards" placeholder="请输入巡查标准" />
      </el-form-item>
      <!--<el-form-item label="应急处置预案" prop="emergencyResponsePlan">-->
      <!--  <el-input v-model="formData.emergencyResponsePlan" placeholder="请输入应急处置预案" />-->
      <!--</el-form-item>-->
      <el-form-item label="备注" prop="notes">
        <el-input v-model="formData.notes" placeholder="请输入备注" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { InspectionPlanManagementApi, InspectionPlanManagementVO } from '@/api/smartcity/inspectionplanmanagement'
import {dateFormatter2} from "@/utils/formatTime";

/** 巡查计划管理 表单 */
defineOptions({ name: 'InspectionPlanManagementForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  planName: undefined,
  patrolArea: undefined,
  inspectionCycle: undefined,
  scheduledStartTime: undefined,
  plannedEndTime: undefined,
  arrangementOfPatrolPersonnel: undefined,
  inspectionContent: undefined,
  inspectionStandards: undefined,
  emergencyResponsePlan: undefined,
  notes: undefined
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
      formData.value = await InspectionPlanManagementApi.getInspectionPlanManagement(id)
      formData.value.scheduledStartTime=formData.value.scheduledStartTime?Number(formData.value.scheduledStartTime):'';
      formData.value.plannedEndTime=formData.value.plannedEndTime?Number(formData.value.plannedEndTime):'';
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
    const data = formData.value as unknown as InspectionPlanManagementVO
    if (formType.value === 'create') {
      await InspectionPlanManagementApi.createInspectionPlanManagement(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectionPlanManagementApi.updateInspectionPlanManagement(data)
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
    planName: undefined,
    patrolArea: undefined,
    inspectionCycle: undefined,
    scheduledStartTime: undefined,
    plannedEndTime: undefined,
    arrangementOfPatrolPersonnel: undefined,
    inspectionContent: undefined,
    inspectionStandards: undefined,
    emergencyResponsePlan: undefined,
    notes: undefined
  }
  formRef.value?.resetFields()
}
</script>
