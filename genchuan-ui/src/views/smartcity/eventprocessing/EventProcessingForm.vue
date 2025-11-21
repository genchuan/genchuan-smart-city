<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="事件处理编号" prop="eventHandlingNumber">
        <el-input v-model="formData.eventHandlingNumber" placeholder="请输入事件处理编号" />
      </el-form-item>
      <!--<el-form-item label="关联事件上报编号" prop="relatedEventReportingId">-->
      <!--  <el-input v-model="formData.relatedEventReportingId" placeholder="请输入关联事件上报编号" />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="处理部门" prop="processingDepartment">-->
      <!--  <el-input v-model="formData.processingDepartment" placeholder="请输入处理部门" />-->
      <!--</el-form-item>-->
      <el-form-item label="处理人员" prop="processingPersonnel">
        <el-input v-model="formData.processingPersonnel" placeholder="请输入处理人员" />
      </el-form-item>
      <el-form-item label="接收时间" prop="receptionTime">
        <el-date-picker
          v-model="formData.receptionTime"
          type="datetime"
          value-format="x"
          placeholder="选择接收时间"
          class="common-Width100"
        />
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
import { EventProcessingApi, EventProcessingVO } from '@/api/smartcity/eventprocessing'

/** 事件处理 表单 */
defineOptions({ name: 'EventProcessingForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  eventHandlingNumber: undefined,
  relatedEventReportingId: undefined,
  processingDepartment: undefined,
  processingPersonnel: undefined,
  receptionTime: undefined,
  handlingMeasures: undefined
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
      const res = await EventProcessingApi.getEventProcessing(id)
      formData.value = {
        ...res,
        // 将字符串时间戳转为数字
        receptionTime: res.receptionTime ? Number(res.receptionTime) : undefined
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
    const data = formData.value as unknown as EventProcessingVO
    if (formType.value === 'create') {
      await EventProcessingApi.createEventProcessing(data)
      message.success(t('common.createSuccess'))
    } else {
      await EventProcessingApi.updateEventProcessing(data)
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
    eventHandlingNumber: undefined,
    relatedEventReportingId: undefined,
    processingDepartment: undefined,
    processingPersonnel: undefined,
    receptionTime: undefined,
    handlingMeasures: undefined
  }
  formRef.value?.resetFields()
}
</script>
