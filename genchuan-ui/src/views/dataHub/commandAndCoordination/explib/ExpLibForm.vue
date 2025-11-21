<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="140px"
      v-loading="formLoading"
    >
      <el-form-item label="经验ID" prop="expId">
        <el-input v-model="formData.expId" placeholder="请输入经验ID" />
      </el-form-item>
      <el-form-item label="经验编号" prop="expNo">
        <el-input v-model="formData.expNo" placeholder="请输入经验编号" />
      </el-form-item>
      <el-form-item label="经验标题" prop="expTitle">
        <el-input v-model="formData.expTitle" placeholder="请输入经验标题" />
      </el-form-item>
      <el-form-item label="关联事件类型ID" prop="evtTypeId">
        <el-input v-model="formData.evtTypeId" placeholder="请输入关联事件类型ID" />
      </el-form-item>
      <el-form-item label="关联事件类型名称" prop="evtTypeName">
        <el-input v-model="formData.evtTypeName" placeholder="请输入关联事件类型名称" />
      </el-form-item>
      <el-form-item label="经验类型" prop="expType">
        <el-select v-model="formData.expType" placeholder="请选择经验类型">
          <el-option label="请选择字典生成" value="1" />
        </el-select>
      </el-form-item>
      <el-form-item label="经验内容" prop="expContent">
        <Editor v-model="formData.expContent" height="150px" />
      </el-form-item>
      <el-form-item label="关联评估ID" prop="evalId">
        <el-input v-model="formData.evalId" placeholder="请输入关联评估ID" />
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
import { ExpLibApi, ExpLibVO } from '@/api/dataHub/commandAndCoordination/explib'

/** 经验库 表单 */
defineOptions({ name: 'ExpLibForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  expId: undefined,
  expNo: undefined,
  expTitle: undefined,
  evtTypeId: undefined,
  evtTypeName: undefined,
  expType: undefined,
  expContent: undefined,
  evalId: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  expId: [{ required: true, message: '经验ID不能为空', trigger: 'blur' }],
  expNo: [{ required: true, message: '经验编号不能为空', trigger: 'blur' }],
  expTitle: [{ required: true, message: '经验标题不能为空', trigger: 'blur' }],
  evtTypeId: [{ required: true, message: '关联事件类型ID不能为空', trigger: 'blur' }],
  evtTypeName: [{ required: true, message: '关联事件类型名称不能为空', trigger: 'blur' }],
  expType: [{ required: true, message: '经验类型不能为空', trigger: 'change' }],
  expContent: [{ required: true, message: '经验内容不能为空', trigger: 'blur' }],
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
      formData.value = await ExpLibApi.getExpLib(id)
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
    const data = formData.value as unknown as ExpLibVO
    if (formType.value === 'create') {
      await ExpLibApi.createExpLib(data)
      message.success(t('common.createSuccess'))
    } else {
      await ExpLibApi.updateExpLib(data)
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
    expId: undefined,
    expNo: undefined,
    expTitle: undefined,
    evtTypeId: undefined,
    evtTypeName: undefined,
    expType: undefined,
    expContent: undefined,
    evalId: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined,
  }
  formRef.value?.resetFields()
}
</script>
