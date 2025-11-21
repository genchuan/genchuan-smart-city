<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="归档ID" prop="archId">
        <el-input v-model="formData.archId" placeholder="请输入归档ID" />
      </el-form-item>
      <el-form-item label="工单ID" prop="woId">
        <el-input v-model="formData.woId" placeholder="请输入工单ID" />
      </el-form-item>
      <el-form-item label="事件编码" prop="evtCode">
        <el-input v-model="formData.evtCode" placeholder="请输入事件编码" />
      </el-form-item>
      <el-form-item label="办结时间" prop="completeTime">
        <el-date-picker
          v-model="formData.completeTime"
          type="date"
          value-format="x"
          placeholder="选择办结时间"
        />
      </el-form-item>
      <el-form-item label="办结说明" prop="completeDesc">
        <el-input v-model="formData.completeDesc" placeholder="请输入办结说明" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { EvtArchApi, EvtArchVO } from '@/api/dataHub/commandAndCoordination/evtarch'

/** 事件办结归档 表单 */
defineOptions({ name: 'EvtArchForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  archId: undefined,
  woId: undefined,
  evtCode: undefined,
  completeTime: undefined,
  completeDesc: undefined,
})
const formRules = reactive({
  archId: [{ required: true, message: '归档ID不能为空', trigger: 'blur' }],
  woId: [{ required: true, message: '工单ID不能为空', trigger: 'blur' }],
  evtCode: [{ required: true, message: '事件编码不能为空', trigger: 'blur' }],
  completeTime: [{ required: true, message: '办结时间不能为空', trigger: 'blur' }],
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
      formData.value = await EvtArchApi.getEvtArch(id)
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
    const data = formData.value as unknown as EvtArchVO
    if (formType.value === 'create') {
      await EvtArchApi.createEvtArch(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvtArchApi.updateEvtArch(data)
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
    archId: undefined,
    woId: undefined,
    evtCode: undefined,
    completeTime: undefined,
    completeDesc: undefined,
  }
  formRef.value?.resetFields()
}
</script>
