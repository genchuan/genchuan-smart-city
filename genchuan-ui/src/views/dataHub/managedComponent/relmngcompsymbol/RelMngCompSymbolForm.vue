<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="关联ID" prop="mngCompSymbolId">
        <el-input v-model="formData.mngCompSymbolId" placeholder="请输入关联ID" />
      </el-form-item>
      <el-form-item label="所属小类ID" prop="minorId">
        <el-input v-model="formData.minorId" placeholder="请输入所属小类ID" />
      </el-form-item>
      <el-form-item label="所属小类名称" prop="minorName">
        <el-input v-model="formData.minorName" placeholder="请输入所属小类名称" />
      </el-form-item>
      <el-form-item label="图示ID" prop="symbolId">
        <el-input v-model="formData.symbolId" placeholder="请输入图示ID" />
      </el-form-item>
      <el-form-item label="图示名称" prop="symbolName">
        <el-input v-model="formData.symbolName" placeholder="请输入图示名称" />
      </el-form-item>
      <el-form-item label="图示路径" prop="symbolPath">
        <el-input v-model="formData.symbolPath" placeholder="请输入图示路径" />
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
import { RelMngCompSymbolApi, RelMngCompSymbolVO } from '@/api/dataHub/managedComponent/relmngcompsymbol'

/** 管理部件图示关联 表单 */
defineOptions({ name: 'RelMngCompSymbolForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  mngCompSymbolId: undefined,
  minorId: undefined,
  minorName: undefined,
  symbolId: undefined,
  symbolName: undefined,
  symbolPath: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined
})
const formRules = reactive({
  mngCompSymbolId: [{ required: true, message: '关联ID不能为空', trigger: 'blur' }],
  minorId: [{ required: true, message: '所属小类ID不能为空', trigger: 'blur' }],
  minorName: [{ required: true, message: '所属小类名称不能为空', trigger: 'blur' }],
  symbolId: [{ required: true, message: '图示ID不能为空', trigger: 'blur' }],
  symbolName: [{ required: true, message: '图示名称不能为空', trigger: 'blur' }],
  symbolPath: [{ required: true, message: '图示路径不能为空', trigger: 'blur' }]
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
      formData.value = await RelMngCompSymbolApi.getRelMngCompSymbol(id)
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
    const data = formData.value as unknown as RelMngCompSymbolVO
    if (formType.value === 'create') {
      await RelMngCompSymbolApi.createRelMngCompSymbol(data)
      message.success(t('common.createSuccess'))
    } else {
      await RelMngCompSymbolApi.updateRelMngCompSymbol(data)
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
    mngCompSymbolId: undefined,
    minorId: undefined,
    minorName: undefined,
    symbolId: undefined,
    symbolName: undefined,
    symbolPath: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined
  }
  formRef.value?.resetFields()
}
</script>
