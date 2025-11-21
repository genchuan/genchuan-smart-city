<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form ref="formRef" :model="formData" label-width="100px" v-loading="formLoading">
      <el-form-item label="管理网格编码">
        <el-input v-model="formData.mgGridCode" disabled />
      </el-form-item>
      <el-form-item label="面积(m²)">
        <el-input v-model="formData.area" disabled />
      </el-form-item>
      <el-form-item label="所含单元网格">
        <el-input v-model="formData.unitGridList" disabled />
      </el-form-item>
      <el-form-item label="终止时间">
        <el-date-picker v-model="formData.endTime" type="date" value-format="YYYY-MM-DD" />
      </el-form-item>
      <el-form-item label="网格员">
        <el-select
          v-model="formData.gridUserId"
          placeholder="选择网格员"
          filterable
          clearable
          class="w-full"
        >
          <el-option v-for="user in mockUsers" :key="user.id" :label="user.name" :value="user.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="备注">
        <el-input v-model="formData.remark" type="textarea" rows="2" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="dialogVisible = false">取消</el-button>
      <el-button type="primary" @click="submitForm">保存</el-button>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { MngGridAttrApi, MngGridAttrVO } from '@/api/dataHub/gridManagement/gridDataManage/mnggridattr'

defineOptions({ name: 'MngGridAttrForm' })
const message = useMessage()

const dialogVisible = ref(false)
const dialogTitle = ref('')
const formLoading = ref(false)
const formType = ref('')
const formRef = ref()

const mockUsers = [
  { id: 'U001', name: '张三' },
  { id: 'U002', name: '李四' },
  { id: 'U003', name: '王五' },
]

const formData = ref<Partial<MngGridAttrVO>>({})
const open = async (type: string, id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = type === 'create' ? '新增属性' : '编辑属性'
  formType.value = type
  formData.value = {}
  if (id) {
    formLoading.value = true
    try {
      formData.value = await MngGridAttrApi.getMngGridAttr(id)
    } finally {
      formLoading.value = false
    }
  }
}
defineExpose({ open })

const emit = defineEmits(['success'])
const submitForm = async () => {
  formLoading.value = true
  try {
    await MngGridAttrApi.updateMngGridAttr(formData.value as MngGridAttrVO)
    message.success('保存成功')
    dialogVisible.value = false
    emit('success')
  } finally {
    formLoading.value = false
  }
}
</script>
