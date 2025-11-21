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
      <el-form-item label="任务描述" prop="taskDescription">
        <el-input v-model="formData.taskDescription" placeholder="请输入任务描述" />
      </el-form-item>
      <el-form-item label="开始时间" prop="startingTimeA">
        <el-date-picker
          v-model="formData.startingTimeA"
          type="datetime"
          value-format="x"
          placeholder="选择开始时间"
        />
      </el-form-item>
      <el-form-item label="结束时间" prop="endTimeB">
        <el-date-picker
          v-model="formData.endTimeB"
          type="datetime"
          value-format="x"
          placeholder="选择结束时间"
        />
      </el-form-item>
      <el-form-item label="检查项目" prop="inspectionItems">
        <el-input v-model="formData.inspectionItems" placeholder="请输入检查项目" />
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
import { InspectionTaskAApi, InspectionTaskAVO } from '@/api/smartcity/inspectiontaska'

/** 巡查任务 表单 */
defineOptions({ name: 'InspectionTaskAForm' })

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
  taskDescription: undefined,
  startingTimeA: undefined,
  endTimeB: undefined,
  inspectionItems: undefined,
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
      const res = await InspectionTaskAApi.getInspectionTaskA(id)
      formData.value = {
        ...res,
        // 将字符串时间戳转为数字
        startingTimeA: res.startingTimeA ? Number(res.startingTimeA) : undefined,
        endTimeB: res.endTimeB ? Number(res.endTimeB) : undefined
      }
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
    const data = formData.value as unknown as InspectionTaskAVO
    if (formType.value === 'create') {
      await InspectionTaskAApi.createInspectionTaskA(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectionTaskAApi.updateInspectionTaskA(data)
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
    taskDescription: undefined,
    startingTimeA: undefined,
    endTimeB: undefined,
    inspectionItems: undefined,
    notes: undefined
  }
  formRef.value?.resetFields()
}
</script>
