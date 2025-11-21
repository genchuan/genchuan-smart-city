<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="分拨ID" prop="allocateId">
        <el-input v-model="formData.allocateId" placeholder="请输入分拨ID" />
      </el-form-item>
      <el-form-item label="事件编码" prop="evtCode">
        <el-input v-model="formData.evtCode" placeholder="请输入事件编码" />
      </el-form-item>
      <el-form-item label="事件类型ID" prop="evtTypeId">
        <el-input v-model="formData.evtTypeId" placeholder="请输入事件类型ID" />
      </el-form-item>
      <el-form-item label="事件类型名称" prop="evtTypeName">
        <el-input v-model="formData.evtTypeName" placeholder="请输入事件类型名称" />
      </el-form-item>
      <el-form-item label="紧急程度" prop="urgencyLevel">
        <el-input v-model="formData.urgencyLevel" placeholder="请输入紧急程度" />
      </el-form-item>
      <el-form-item label="分拨部门代码" prop="allocateDeptCode">
        <el-input v-model="formData.allocateDeptCode" placeholder="请输入分拨部门代码" />
      </el-form-item>
      <el-form-item label="分拨部门名称" prop="allocateDeptName">
        <el-input v-model="formData.allocateDeptName" placeholder="请输入分拨部门名称" />
      </el-form-item>
      <el-form-item label="分拨人ID" prop="allocatorId">
        <el-input v-model="formData.allocatorId" placeholder="请输入分拨人ID" />
      </el-form-item>
      <el-form-item label="分拨人姓名" prop="allocatorName">
        <el-input v-model="formData.allocatorName" placeholder="请输入分拨人姓名" />
      </el-form-item>
      <el-form-item label="分拨时间" prop="allocateTime">
        <el-date-picker
          v-model="formData.allocateTime"
          type="date"
          value-format="x"
          placeholder="选择分拨时间"
        />
      </el-form-item>
      <el-form-item label="分拨说明" prop="allocateDesc">
        <el-input v-model="formData.allocateDesc" placeholder="请输入分拨说明" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { EvtClassifyAllocateApi, EvtClassifyAllocateVO } from '@/api/dataHub/commandAndCoordination/evtclassifyallocate'

/** 事件分级分拨 表单 */
defineOptions({ name: 'EvtClassifyAllocateForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  allocateId: undefined,
  evtCode: undefined,
  evtTypeId: undefined,
  evtTypeName: undefined,
  urgencyLevel: undefined,
  allocateDeptCode: undefined,
  allocateDeptName: undefined,
  allocatorId: undefined,
  allocatorName: undefined,
  allocateTime: undefined,
  allocateDesc: undefined,
})
const formRules = reactive({
  allocateId: [{ required: true, message: '分拨ID不能为空', trigger: 'blur' }],
  evtCode: [{ required: true, message: '事件编码不能为空', trigger: 'blur' }],
  evtTypeId: [{ required: true, message: '事件类型ID不能为空', trigger: 'blur' }],
  evtTypeName: [{ required: true, message: '事件类型名称不能为空', trigger: 'blur' }],
  urgencyLevel: [{ required: true, message: '紧急程度不能为空', trigger: 'blur' }],
  allocateDeptCode: [{ required: true, message: '分拨部门代码不能为空', trigger: 'blur' }],
  allocateDeptName: [{ required: true, message: '分拨部门名称不能为空', trigger: 'blur' }],
  allocatorId: [{ required: true, message: '分拨人ID不能为空', trigger: 'blur' }],
  allocatorName: [{ required: true, message: '分拨人姓名不能为空', trigger: 'blur' }],
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
      formData.value = await EvtClassifyAllocateApi.getEvtClassifyAllocate(id)
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
    const data = formData.value as unknown as EvtClassifyAllocateVO
    if (formType.value === 'create') {
      await EvtClassifyAllocateApi.createEvtClassifyAllocate(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvtClassifyAllocateApi.updateEvtClassifyAllocate(data)
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
    allocateId: undefined,
    evtCode: undefined,
    evtTypeId: undefined,
    evtTypeName: undefined,
    urgencyLevel: undefined,
    allocateDeptCode: undefined,
    allocateDeptName: undefined,
    allocatorId: undefined,
    allocatorName: undefined,
    allocateTime: undefined,
    allocateDesc: undefined,
  }
  formRef.value?.resetFields()
}
</script>
