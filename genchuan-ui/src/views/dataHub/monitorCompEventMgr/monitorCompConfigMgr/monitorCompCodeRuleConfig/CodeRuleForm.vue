<template>
  <el-dialog
    :title="isEdit ? '修改标识码规则' : '新增标识码规则'"
    :model-value="visible"
    width="520px"
    @close="$emit('close')"
  >
    <el-form :model="form" :rules="rules" ref="formRef" label-width="130px">
      <el-form-item label="规则名称" prop="ruleName">
        <el-input v-model="form.ruleName" placeholder="请输入规则名称" />
      </el-form-item>

      <el-form-item label="各段代码位数">
        <el-input v-model="segmentLength" disabled />
      </el-form-item>

      <el-form-item label="顺序码生成规则" prop="seqGenRule">
        <el-input
          v-model="form.seqGenRule"
          type="textarea"
          :rows="3"
          placeholder="请输入规则描述"
        />
      </el-form-item>
    </el-form>

    <template #footer>
      <el-button @click="$emit('close')">取消</el-button>
      <el-button type="primary" @click="submitForm">确定</el-button>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, reactive, watch } from 'vue'
import { ElMessage } from 'element-plus'

const props = defineProps({
  visible: Boolean,
  isEdit: Boolean,
  formData: Object,
})
const emit = defineEmits(['close', 'success'])

const formRef = ref()
const form = reactive({
  ruleName: '',
  seqGenRule: '同一行政区划+小类下按录入顺序递增',
})

const segmentLength = ref('行政区划6位 + 大类2位 + 中类2位 + 小类2位 + 顺序码6位')

const rules = {
  ruleName: [{ required: true, message: '请输入规则名称', trigger: 'blur' }],
  seqGenRule: [{ required: true, message: '请输入顺序码生成规则', trigger: 'blur' }],
}

watch(
  () => props.formData,
  (val) => {
    if (props.isEdit && val) {
      form.ruleName = val.ruleName
      form.seqGenRule = val.seqGenRule
    } else {
      form.ruleName = ''
      form.seqGenRule = '同一行政区划+小类下按录入顺序递增'
    }
  },
  { immediate: true }
)

const submitForm = () => {
  formRef.value.validate((valid) => {
    if (!valid) return
    ElMessage.success(props.isEdit ? '修改成功' : '新增成功')
    emit('success')
  })
}
</script>
