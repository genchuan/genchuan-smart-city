<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="报警时间" prop="time">
        <el-date-picker
          v-model="formData.time"
          type="date"
          value-format="x"
          placeholder="选择报警时间"
        />
      </el-form-item>
      <el-form-item label="报警来源" prop="alarmSource">
        <el-input v-model="formData.alarmSource" placeholder="请输入报警来源" />
      </el-form-item>
      <el-form-item label="风险等级" prop="riskLevel">
        <el-input v-model="formData.riskLevel" placeholder="请输入风险等级" />
      </el-form-item>
      <el-form-item label="报警描述" prop="alarmDescription">
        <el-input v-model="formData.alarmDescription" type="textarea" placeholder="请输入报警描述" />
      </el-form-item>
      <el-form-item label="涉及区域" prop="involvingRegions">
        <el-input v-model="formData.involvingRegions" placeholder="请输入涉及区域" />
      </el-form-item>
      <el-form-item label="处置措施" prop="disposalMeasures">
        <el-input v-model="formData.disposalMeasures" placeholder="请输入处置措施" />
      </el-form-item>
      <el-form-item label="处置结果" prop="disposalResults">
        <el-input v-model="formData.disposalResults" placeholder="请输入处置结果" />
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
import { AlarmHandlingCategoryApi, AlarmHandlingCategoryVO } from '@/api/smartcity/alarmhandlingcategory'

/** 报警处置类 表单 */
defineOptions({ name: 'AlarmHandlingCategoryForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  time: undefined,
  alarmSource: undefined,
  riskLevel: undefined,
  alarmDescription: undefined,
  involvingRegions: undefined,
  disposalMeasures: undefined,
  disposalResults: undefined,
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
      formData.value = await AlarmHandlingCategoryApi.getAlarmHandlingCategory(id)
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
    const data = formData.value as unknown as AlarmHandlingCategoryVO
    if (formType.value === 'create') {
      await AlarmHandlingCategoryApi.createAlarmHandlingCategory(data)
      message.success(t('common.createSuccess'))
    } else {
      await AlarmHandlingCategoryApi.updateAlarmHandlingCategory(data)
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
    time: undefined,
    alarmSource: undefined,
    riskLevel: undefined,
    alarmDescription: undefined,
    involvingRegions: undefined,
    disposalMeasures: undefined,
    disposalResults: undefined,
    notes: undefined
  }
  formRef.value?.resetFields()
}
</script>