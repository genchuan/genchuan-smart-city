<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="600px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
    >
      <el-form-item label="面积(m²)" prop="area">
        <el-input v-model="formData.area" disabled />
      </el-form-item>

      <el-form-item label="初始时间" prop="initTime">
        <el-date-picker v-model="formData.initTime" type="date" disabled />
      </el-form-item>

      <el-form-item label="终止时间" prop="endTime">
        <el-date-picker
          v-model="formData.endTime"
          type="date"
          placeholder="选择终止时间"
          :disabled-date="(date) => date < new Date(formData.initTime || 0)"
        />
      </el-form-item>

      <el-form-item label="备注" prop="remark">
        <el-input
          v-model="formData.remark"
          placeholder="请输入备注"
          type="textarea"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button type="primary" @click="submitForm" :loading="formLoading">保存</el-button>
      <el-button @click="dialogVisible = false">取消</el-button>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
import { UnitGridAttrApi, UnitGridAttrVO } from '@/api/dataHub/gridManagement/gridDataManage/unitgridattr'

defineOptions({ name: 'UnitGridAttrForm' })
const message = useMessage()
const dialogVisible = ref(false)
const dialogTitle = ref('')
const formLoading = ref(false)
const formType = ref('')
const formRef = ref()

const formData = ref<Partial<UnitGridAttrVO>>({
  id: undefined,
  area: 0,
  initTime: undefined,
  endTime: undefined,
  remark: '',
})

const formRules = reactive({
  endTime: [
    {
      validator: (_, value, callback) => {
        if (value && new Date(value) < new Date(formData.value.initTime!)) {
          callback(new Error('终止时间不能早于初始时间'))
        } else callback()
      },
      trigger: 'change',
    },
  ],
})

const open = async (type: string, id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = type === 'create' ? '新增属性（系统自动同步）' : '编辑终止时间与备注'
  formType.value = type
  resetForm()
  if (id) {
    formLoading.value = true
    try {
      formData.value = await UnitGridAttrApi.getUnitGridAttr(id)
    } finally {
      formLoading.value = false
    }
  }
}
defineExpose({ open })

const emit = defineEmits(['success'])
const submitForm = async () => {
  await formRef.value.validate()
  formLoading.value = true
  try {
    await UnitGridAttrApi.updateUnitGridAttr(formData.value as UnitGridAttrVO)
    message.success('保存成功')
    dialogVisible.value = false
    emit('success')
  } finally {
    formLoading.value = false
  }
}

const resetForm = () => {
  formData.value = {
    id: undefined,
    area: 0,
    initTime: undefined,
    endTime: undefined,
    remark: '',
  }
  formRef.value?.resetFields()
}
</script>
