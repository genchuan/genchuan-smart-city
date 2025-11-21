<template>
  <el-dialog
    v-model="visible"
    title="导出设置"
    :width="500"
    :destroy-on-close="true"
  >
    <el-form
      ref="formRef"
      :model="form"
      :rules="rules"
      label-width="100px"
    >
      <el-form-item label="导出格式" prop="format">
        <el-radio-group v-model="form.format">
          <el-radio label="excel">Excel</el-radio>
          <el-radio label="csv">CSV</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="导出字段">
        <el-checkbox-group v-model="form.exportFields">
          <el-checkbox
            v-for="field in exportFieldOptions"
            :key="field.value"
            :label="field.value"
            :checked="field.checked"
          >
            {{ field.label }}
          </el-checkbox>
        </el-checkbox-group>
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="handleConfirm">确认导出</el-button>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'

const visible = ref(false)
const formRef = ref()

// 导出字段选项
const exportFieldOptions = ref([
  { label: '告警编号', value: 'alertCode', checked: true },
  { label: '关联对象名称', value: 'relatedObjectName', checked: true },
  { label: '预警领域', value: 'warningField', checked: true },
  { label: '预警类型', value: 'warningType', checked: true },
  { label: '预警等级', value: 'warningLevel', checked: true },
  { label: '预警状态', value: 'warningStatus', checked: true },
  { label: '触发原因', value: 'triggerReason', checked: false },
  { label: '触发时间', value: 'triggerTime', checked: true },
  { label: '要求完成时间', value: 'requiredCompleteTime', checked: true },
  { label: '派发部门', value: 'dispatchDepartment', checked: true },
  { label: '责任人', value: 'responsiblePerson', checked: true },
  { label: '处置时长', value: 'disposalDuration', checked: true }
])

// 表单数据
const form = reactive({
  format: 'excel',
  exportFields: exportFieldOptions.value
    .filter(field => field.checked)
    .map(field => field.value)
})

// 验证规则
const rules = reactive({
  format: [{ required: true, message: '请选择导出格式', trigger: 'change' }],
  exportFields: [{ required: true, message: '请至少选择一个导出字段', trigger: 'change' }]
})

// 打开弹窗
const open = () => {
  visible.value = true
}

// 确认导出
const handleConfirm = async () => {
  try {
    await formRef.value.validate()
    visible.value = false
    emit('confirm', form.exportFields, form.format)
  } catch (error) {
    console.error('验证失败', error)
  }
}

const emit = defineEmits(['confirm'])

defineExpose({
  open
})
</script>
