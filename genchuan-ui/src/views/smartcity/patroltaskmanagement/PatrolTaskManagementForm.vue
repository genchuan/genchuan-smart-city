<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="任务名称" prop="task">
        <el-input v-model="formData.task" placeholder="请输入任务名称" />
      </el-form-item>
      <!--<el-form-item label="所属计划" prop="belongingPlan">-->
      <!--  <el-input v-model="formData.belongingPlan" placeholder="请输入所属计划" />-->
      <!--</el-form-item>-->
      <el-form-item label="巡查区域" prop="patrolArea">
        <el-input v-model="formData.patrolArea" placeholder="请输入巡查区域" />
      </el-form-item>
      <el-form-item label="巡查时间" prop="patrolTime">
        <el-date-picker
          v-model="formData.patrolTime"
          type="date"
          value-format="x"
          placeholder="选择巡查时间"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="执行人员" prop="executive">
        <el-input v-model="formData.executive" placeholder="请输入执行人员" />
      </el-form-item>
      <el-form-item label="预计时长" prop="expectedDuration">
        <el-input v-model="formData.expectedDuration" placeholder="请输入预计时长" />
      </el-form-item>
      <el-form-item label="任务描述" prop="taskDescription">
        <el-input v-model="formData.taskDescription"  placeholder="请输入任务描述"  />
      </el-form-item>
      <el-form-item label="巡查重点" prop="keyInspectionPoints">
        <el-input v-model="formData.keyInspectionPoints" placeholder="请输入巡查重点" />
      </el-form-item>
      <el-form-item label="携带设备清单" prop="listOfCarryingEquipment">
        <el-input v-model="formData.listOfCarryingEquipment" placeholder="请输入携带设备清单" />
      </el-form-item>
      <el-form-item label="完成情况说明" prop="completionStatusDescription">
        <el-input v-model="formData.completionStatusDescription" placeholder="请输入完成情况说明" />
      </el-form-item>
      <el-form-item label="异常情况记录" prop="abnormalSituationRecord">
        <el-input v-model="formData.abnormalSituationRecord" placeholder="请输入异常情况记录" />
      </el-form-item>
      <el-form-item label="处理措施" prop="handlingMeasures">
        <el-input v-model="formData.handlingMeasures" placeholder="请输入处理措施" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { PatrolTaskManagementApi, PatrolTaskManagementVO } from '@/api/smartcity/patroltaskmanagement'

/** 巡査任务管理 表单 */
defineOptions({ name: 'PatrolTaskManagementForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  task: undefined,
  belongingPlan: undefined,
  patrolArea: undefined,
  patrolTime: undefined,
  executive: undefined,
  expectedDuration: undefined,
  taskDescription: undefined,
  keyInspectionPoints: undefined,
  listOfCarryingEquipment: undefined,
  completionStatusDescription: undefined,
  abnormalSituationRecord: undefined,
  handlingMeasures: undefined
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
      formData.value = await PatrolTaskManagementApi.getPatrolTaskManagement(id);
      formData.value.patrolTime=formData.value.patrolTime?Number(formData.value.patrolTime):''
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
    const data = formData.value as unknown as PatrolTaskManagementVO
    if (formType.value === 'create') {
      await PatrolTaskManagementApi.createPatrolTaskManagement(data)
      message.success(t('common.createSuccess'))
    } else {
      await PatrolTaskManagementApi.updatePatrolTaskManagement(data)
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
    task: undefined,
    belongingPlan: undefined,
    patrolArea: undefined,
    patrolTime: undefined,
    executive: undefined,
    expectedDuration: undefined,
    taskDescription: undefined,
    keyInspectionPoints: undefined,
    listOfCarryingEquipment: undefined,
    completionStatusDescription: undefined,
    abnormalSituationRecord: undefined,
    handlingMeasures: undefined
  }
  formRef.value?.resetFields()
}
</script>
