<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="事件编号" prop="eventNumber">
        <el-input v-model="formData.eventNumber" placeholder="请输入事件编号" />
      </el-form-item>
      <el-form-item label="事件发生时间" prop="eventOccurrenceTime">
        <el-date-picker
          v-model="formData.eventOccurrenceTime"
          type="date"
          value-format="x"
          placeholder="选择事件发生时间"
        />
      </el-form-item>
      <el-form-item label="事件发生地点" prop="locationOfTheIncident">
        <el-input v-model="formData.locationOfTheIncident" placeholder="请输入事件发生地点" />
      </el-form-item>
      <el-form-item label="事件描述" prop="eventDescription">
        <Editor v-model="formData.eventDescription" height="150px" />
      </el-form-item>
      <el-form-item label="发现人" prop="discoverer">
        <el-input v-model="formData.discoverer" placeholder="请输入发现人" />
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
import { EventListApi, EventListVO } from '@/api/smartcity/eventlist'

/** 事件列表 表单 */
defineOptions({ name: 'EventListForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  eventNumber: undefined,
  eventOccurrenceTime: undefined,
  locationOfTheIncident: undefined,
  eventDescription: undefined,
  discoverer: undefined,
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
      formData.value = await EventListApi.getEventList(id)
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
    const data = formData.value as unknown as EventListVO
    if (formType.value === 'create') {
      await EventListApi.createEventList(data)
      message.success(t('common.createSuccess'))
    } else {
      await EventListApi.updateEventList(data)
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
    eventNumber: undefined,
    eventOccurrenceTime: undefined,
    locationOfTheIncident: undefined,
    eventDescription: undefined,
    discoverer: undefined,
    notes: undefined
  }
  formRef.value?.resetFields()
}
</script>