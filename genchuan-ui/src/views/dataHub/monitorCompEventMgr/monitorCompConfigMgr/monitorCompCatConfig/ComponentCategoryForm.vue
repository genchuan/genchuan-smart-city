<template>
  <el-dialog
    v-model="visible"
    :title="dialogTitle"
    width="600px"
    :close-on-click-modal="false"
    destroy-on-close
  >
    <el-form :model="formData" :rules="rules" ref="formRef" label-width="120px">
      <el-form-item label="分类层级" prop="category_level">
        <el-select v-model="formData.category_level" placeholder="请选择层级" :disabled="isUpdate">
          <el-option label="大类" :value="1" />
          <el-option label="中类" :value="2" />
          <el-option label="小类" :value="3" />
        </el-select>
      </el-form-item>

      <el-form-item v-if="formData.category_level > 1" label="父类" prop="parent_category_id">
        <el-select
          v-model="formData.parent_category_id"
          placeholder="请选择父类"
          :disabled="isUpdate"
        >
          <el-option
            v-for="item in parentOptions"
            :key="item.category_id"
            :label="item.category_name"
            :value="item.category_id"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="分类代码" prop="category_code">
        <el-input
          v-model="formData.category_code"
          placeholder="请输入分类代码"
          :disabled="isUpdate"
        />
      </el-form-item>

      <el-form-item label="分类名称" prop="category_name">
        <el-input v-model="formData.category_name" placeholder="请输入分类名称" />
      </el-form-item>

      <el-form-item label="分类说明" prop="category_desc">
        <el-input v-model="formData.category_desc" type="textarea" placeholder="请输入分类说明" />
      </el-form-item>

      <el-form-item label="启用状态" prop="enable_status">
        <el-switch v-model="formData.enable_status" :active-value="1" :inactive-value="0" />
      </el-form-item>
    </el-form>

    <template #footer>
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="submitForm">保存</el-button>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
const emits = defineEmits(['success'])
const visible = ref(false)
const dialogTitle = ref('')
const formRef = ref()

/** 是否为编辑状态 */
const isUpdate = ref(false)

/** 模拟父类选项 */
const parentOptions = ref([
  { category_id: 1, category_name: '市政设施' },
  { category_id: 2, category_name: '燃气' }
])

/** 表单数据 */
const formData = reactive({
  category_id: undefined,
  parent_category_id: undefined,
  category_level: 1,
  category_code: '',
  category_name: '',
  category_desc: '',
  enable_status: 1
})

/** 校验规则 */
const rules = {
  category_level: [{ required: true, message: '请选择层级', trigger: 'change' }],
  category_code: [{ required: true, message: '请输入分类代码', trigger: 'blur' }],
  category_name: [{ required: true, message: '请输入分类名称', trigger: 'blur' }]
}

/** 打开表单 */
const open = (type: string, row?: any) => {
  visible.value = true
  isUpdate.value = type === 'update'
  dialogTitle.value = type === 'create' ? '新增分类' : '修改分类'
  Object.assign(formData, {
    category_id: row?.category_id,
    parent_category_id: row?.parent_category_id || undefined,
    category_level: row?.category_level || 1,
    category_code: row?.category_code || '',
    category_name: row?.category_name || '',
    category_desc: row?.category_desc || '',
    enable_status: row?.enable_status ?? 1
  })
}

/** 提交表单 */
const submitForm = () => {
  formRef.value.validate((valid: boolean) => {
    if (!valid) return
    // TODO: 对接接口逻辑
    ElMessage.success(isUpdate.value ? '修改成功' : '新增成功')
    visible.value = false
    emits('success')
  })
}

defineExpose({ open })
</script>
