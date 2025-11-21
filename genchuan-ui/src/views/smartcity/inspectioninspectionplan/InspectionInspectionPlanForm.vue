<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="巡查项目" prop="inspectionProject">
        <el-input v-model="formData.inspectionProject" placeholder="请输入巡查项目" />
      </el-form-item>
      <el-form-item label="巡查地点" prop="patrolLocation">
        <el-input v-model="formData.patrolLocation" placeholder="请输入巡查地点" />
      </el-form-item>
      <el-form-item label="巡查周期" prop="inspectionCycle">
        <el-input v-model="formData.inspectionCycle" placeholder="请输入巡查周期" />
      </el-form-item>
      <el-form-item label="巡查时间" prop="patrolTime">
        <el-date-picker
          v-model="formData.patrolTime"
          type="datetime"
          value-format="x"
          placeholder="选择巡查时间"
        />
      </el-form-item>
      <el-form-item label="巡查方式" prop="inspectionMethod">
        <el-input v-model="formData.inspectionMethod" placeholder="请输入巡查方式" />
      </el-form-item>
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
import { InspectionInspectionPlanApi, InspectionInspectionPlanVO } from '@/api/smartcity/inspectioninspectionplan'

/** 巡查计划 表单 */
defineOptions({ name: 'InspectionInspectionPlanForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  inspectionProject: undefined,
  patrolLocation: undefined,
  inspectionCycle: undefined,
  patrolTime: undefined,
  inspectionMethod: undefined,
  notes: undefined
})
const formRules = reactive({
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
      formData.value = await InspectionInspectionPlanApi.getInspectionInspectionPlan(id)
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
    const data = formData.value as unknown as InspectionInspectionPlanVO
    if (formType.value === 'create') {
      await InspectionInspectionPlanApi.createInspectionInspectionPlan(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectionInspectionPlanApi.updateInspectionInspectionPlan(data)
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
    inspectionProject: undefined,
    patrolLocation: undefined,
    inspectionCycle: undefined,
    patrolTime: undefined,
    inspectionMethod: undefined,
    notes: undefined
  }
  formRef.value?.resetFields()
}
</script>
