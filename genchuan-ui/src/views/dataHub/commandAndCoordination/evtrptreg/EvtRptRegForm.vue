<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="接报ID" prop="rptId">
        <el-input v-model="formData.rptId" placeholder="请输入接报ID" />
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
      <el-form-item label="事发区域代码" prop="incidentRegionCode">
        <el-input v-model="formData.incidentRegionCode" placeholder="请输入事发区域代码" />
      </el-form-item>
      <el-form-item label="事发区域名称" prop="incidentRegionName">
        <el-input v-model="formData.incidentRegionName" placeholder="请输入事发区域名称" />
      </el-form-item>
      <el-form-item label="事发位置" prop="incidentLocation">
        <el-input v-model="formData.incidentLocation" placeholder="请输入事发位置" />
      </el-form-item>
      <el-form-item label="事发坐标X" prop="incidentCoordX">
        <el-input v-model="formData.incidentCoordX" placeholder="请输入事发坐标X" />
      </el-form-item>
      <el-form-item label="事发坐标Y" prop="incidentCoordY">
        <el-input v-model="formData.incidentCoordY" placeholder="请输入事发坐标Y" />
      </el-form-item>
      <el-form-item label="事件描述" prop="evtDesc">
        <el-input v-model="formData.evtDesc" placeholder="请输入事件描述" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { EvtRptRegApi, EvtRptRegVO } from '@/api/dataHub/commandAndCoordination/evtrptreg'

/** 事件接报登记 表单 */
defineOptions({ name: 'EvtRptRegForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  rptId: undefined,
  evtCode: undefined,
  evtTypeId: undefined,
  evtTypeName: undefined,
  incidentRegionCode: undefined,
  incidentRegionName: undefined,
  incidentLocation: undefined,
  incidentCoordX: undefined,
  incidentCoordY: undefined,
  evtDesc: undefined,
})
const formRules = reactive({
  rptId: [{ required: true, message: '接报ID不能为空', trigger: 'blur' }],
  evtCode: [{ required: true, message: '事件编码不能为空', trigger: 'blur' }],
  evtTypeId: [{ required: true, message: '事件类型ID不能为空', trigger: 'blur' }],
  evtTypeName: [{ required: true, message: '事件类型名称不能为空', trigger: 'blur' }],
  incidentRegionCode: [{ required: true, message: '事发区域代码不能为空', trigger: 'blur' }],
  incidentRegionName: [{ required: true, message: '事发区域名称不能为空', trigger: 'blur' }],
  incidentLocation: [{ required: true, message: '事发位置不能为空', trigger: 'blur' }],
  evtDesc: [{ required: true, message: '事件描述不能为空', trigger: 'blur' }],
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
      formData.value = await EvtRptRegApi.getEvtRptReg(id)
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
    const data = formData.value as unknown as EvtRptRegVO
    if (formType.value === 'create') {
      await EvtRptRegApi.createEvtRptReg(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvtRptRegApi.updateEvtRptReg(data)
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
    rptId: undefined,
    evtCode: undefined,
    evtTypeId: undefined,
    evtTypeName: undefined,
    incidentRegionCode: undefined,
    incidentRegionName: undefined,
    incidentLocation: undefined,
    incidentCoordX: undefined,
    incidentCoordY: undefined,
    evtDesc: undefined,
  }
  formRef.value?.resetFields()
}
</script>
