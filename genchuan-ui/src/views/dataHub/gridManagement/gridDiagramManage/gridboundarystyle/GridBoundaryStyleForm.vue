<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="500px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="样式ID" prop="styleId">
        <el-input
          v-model="formData.styleId"
          placeholder="请输入样式ID"
          :disabled="formType === 'update'"
        />
      </el-form-item>
      <el-form-item label="比例尺" prop="scale">
        <el-input
          v-model="formData.scale"
          placeholder="请输入比例尺"
          :disabled="formType === 'update'"
        />
      </el-form-item>
      <el-form-item label="网格类型" prop="gridType">
        <el-input
          v-model="formData.gridType"
          placeholder="请输入网格类型"
          :disabled="formType === 'update'"
        />
      </el-form-item>
      <el-form-item label="线宽(mm)" prop="lineWidth">
        <el-input v-model="formData.lineWidth" placeholder="请输入线宽(mm)" />
      </el-form-item>
    </el-form>

    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import {
  GridBoundaryStyleApi,
  GridBoundaryStyleVO
} from '@/api/dataHub/gridManagement/gridDiagramManage/gridboundarystyle'

const dialogVisible = ref(false)
const dialogTitle = ref('')
const formLoading = ref(false)
const formType = ref<'create' | 'update'>('create')
const formData = reactive<Partial<GridBoundaryStyleVO>>({})

const formRef = ref()
const emit = defineEmits(['success'])
const message = useMessage()

const formRules = reactive({
  scale: [{ required: true, message: '比例尺不能为空', trigger: 'blur' }],
  gridType: [{ required: true, message: '网格类型不能为空', trigger: 'blur' }],
  lineWidth: [{ required: true, message: '线宽不能为空', trigger: 'blur' }]
})

// 打开表单
const open = async (type: 'create' | 'update', id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = type === 'create' ? '新增样式' : '编辑样式'
  formType.value = type
  resetForm()

  if (id) {
    formLoading.value = true
    try {
      const data = await GridBoundaryStyleApi.getGridBoundaryStyle(id)
      Object.assign(formData, data)
    } finally {
      formLoading.value = false
    }
  }
}
defineExpose({ open })

// 重置表单
const resetForm = () => {
  formData.styleId = ''
  formData.scale = ''
  formData.gridType = ''
  formData.lineWidth = undefined
  formRef.value?.resetFields()
}

// 提交表单
const submitForm = async () => {
  await formRef.value.validate()
  formLoading.value = true
  try {
    const data = formData as GridBoundaryStyleVO
    if (formType.value === 'create') {
      await GridBoundaryStyleApi.createGridBoundaryStyle(data)
      message.success('新增成功')
    } else {
      await GridBoundaryStyleApi.updateGridBoundaryStyle(data)
      message.success('更新成功')
    }
    dialogVisible.value = false
    emit('success')
  } finally {
    formLoading.value = false
  }
}
</script>
