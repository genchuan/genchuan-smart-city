<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="扩展配置ID" prop="extId">
        <el-input v-model="formData.extId" placeholder="请输入扩展配置ID" />
      </el-form-item>
      <el-form-item label="部件ID" prop="compId">
        <el-input v-model="formData.compId" placeholder="请输入部件ID" />
      </el-form-item>
      <el-form-item label="扩展键" prop="extKey">
        <el-input v-model="formData.extKey" placeholder="请输入扩展键" />
      </el-form-item>
      <el-form-item label="扩展值" prop="extValue">
        <el-input v-model="formData.extValue" placeholder="请输入扩展值" />
      </el-form-item>
      <el-form-item label="系统创建时间" prop="createTimeSys">
        <el-date-picker
          v-model="formData.createTimeSys"
          type="date"
          value-format="x"
          placeholder="选择系统创建时间"
        />
      </el-form-item>
      <el-form-item label="系统更新时间" prop="updateTimeSys">
        <el-date-picker
          v-model="formData.updateTimeSys"
          type="date"
          value-format="x"
          placeholder="选择系统更新时间"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { MonCompExtApi, MonCompExtVO } from '@/api/dataHub/managedComponent/moncompext'

/** 监测部件扩展配置 表单 */
defineOptions({ name: 'MonCompExtForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  extId: undefined,
  compId: undefined,
  extKey: undefined,
  extValue: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  extId: [{ required: true, message: '扩展配置ID不能为空', trigger: 'blur' }],
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
      formData.value = await MonCompExtApi.getMonCompExt(id)
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
    const data = formData.value as unknown as MonCompExtVO
    if (formType.value === 'create') {
      await MonCompExtApi.createMonCompExt(data)
      message.success(t('common.createSuccess'))
    } else {
      await MonCompExtApi.updateMonCompExt(data)
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
    extId: undefined,
    compId: undefined,
    extKey: undefined,
    extValue: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined,
  }
  formRef.value?.resetFields()
}
</script>
