<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="回溯ID" prop="traceId">
        <el-input v-model="formData.traceId" placeholder="请输入回溯ID" />
      </el-form-item>
      <el-form-item label="事件编码" prop="evtCode">
        <el-input v-model="formData.evtCode" placeholder="请输入事件编码" />
      </el-form-item>
      <el-form-item label="事件名称" prop="evtName">
        <el-input v-model="formData.evtName" placeholder="请输入事件名称" />
      </el-form-item>
      <el-form-item label="事件类型名称" prop="evtTypeName">
        <el-input v-model="formData.evtTypeName" placeholder="请输入事件类型名称" />
      </el-form-item>
      <el-form-item label="接报环节" prop="rptLink">
        <el-input v-model="formData.rptLink" placeholder="请输入接报环节" />
      </el-form-item>
      <el-form-item label="研判环节" prop="analysisLink">
        <el-input v-model="formData.analysisLink" placeholder="请输入研判环节" />
      </el-form-item>
      <el-form-item label="分拨环节" prop="allocateLink">
        <el-input v-model="formData.allocateLink" placeholder="请输入分拨环节" />
      </el-form-item>
      <el-form-item label="工单环节" prop="woLink">
        <el-input v-model="formData.woLink" placeholder="请输入工单环节" />
      </el-form-item>
      <el-form-item label="调度环节" prop="dispatchLink">
        <el-input v-model="formData.dispatchLink" placeholder="请输入调度环节" />
      </el-form-item>
      <el-form-item label="回溯人ID" prop="traceUserId">
        <el-input v-model="formData.traceUserId" placeholder="请输入回溯人ID" />
      </el-form-item>
      <el-form-item label="回溯人姓名" prop="traceUserName">
        <el-input v-model="formData.traceUserName" placeholder="请输入回溯人姓名" />
      </el-form-item>
      <el-form-item label="回溯时间" prop="traceTime">
        <el-date-picker
          v-model="formData.traceTime"
          type="date"
          value-format="x"
          placeholder="选择回溯时间"
        />
      </el-form-item>
      <el-form-item label="回溯报告路径" prop="rptPath">
        <el-input v-model="formData.rptPath" placeholder="请输入回溯报告路径" />
      </el-form-item>
      <el-form-item label="分类扩展字段1" prop="extCat1">
        <el-input v-model="formData.extCat1" placeholder="请输入分类扩展字段1" />
      </el-form-item>
      <el-form-item label="分类扩展字段2" prop="extCat2">
        <el-input v-model="formData.extCat2" placeholder="请输入分类扩展字段2" />
      </el-form-item>
      <el-form-item label="通用扩展字段1" prop="extCommon1">
        <el-input v-model="formData.extCommon1" placeholder="请输入通用扩展字段1" />
      </el-form-item>
      <el-form-item label="通用扩展字段2" prop="extCommon2">
        <el-input v-model="formData.extCommon2" placeholder="请输入通用扩展字段2" />
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
import { EvtProcessTraceApi, EvtProcessTraceVO } from '@/api/dataHub/commandAndCoordination/evtprocesstrace'

/** 事件过程回溯 表单 */
defineOptions({ name: 'EvtProcessTraceForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  traceId: undefined,
  evtCode: undefined,
  evtName: undefined,
  evtTypeName: undefined,
  rptLink: undefined,
  analysisLink: undefined,
  allocateLink: undefined,
  woLink: undefined,
  dispatchLink: undefined,
  traceUserId: undefined,
  traceUserName: undefined,
  traceTime: undefined,
  rptPath: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  traceId: [{ required: true, message: '回溯ID不能为空', trigger: 'blur' }],
  evtCode: [{ required: true, message: '事件编码不能为空', trigger: 'blur' }],
  evtName: [{ required: true, message: '事件名称不能为空', trigger: 'blur' }],
  evtTypeName: [{ required: true, message: '事件类型名称不能为空', trigger: 'blur' }],
  rptLink: [{ required: true, message: '接报环节不能为空', trigger: 'blur' }],
  analysisLink: [{ required: true, message: '研判环节不能为空', trigger: 'blur' }],
  allocateLink: [{ required: true, message: '分拨环节不能为空', trigger: 'blur' }],
  woLink: [{ required: true, message: '工单环节不能为空', trigger: 'blur' }],
  traceUserId: [{ required: true, message: '回溯人ID不能为空', trigger: 'blur' }],
  traceUserName: [{ required: true, message: '回溯人姓名不能为空', trigger: 'blur' }],
  traceTime: [{ required: true, message: '回溯时间不能为空', trigger: 'blur' }],
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
      formData.value = await EvtProcessTraceApi.getEvtProcessTrace(id)
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
    const data = formData.value as unknown as EvtProcessTraceVO
    if (formType.value === 'create') {
      await EvtProcessTraceApi.createEvtProcessTrace(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvtProcessTraceApi.updateEvtProcessTrace(data)
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
    traceId: undefined,
    evtCode: undefined,
    evtName: undefined,
    evtTypeName: undefined,
    rptLink: undefined,
    analysisLink: undefined,
    allocateLink: undefined,
    woLink: undefined,
    dispatchLink: undefined,
    traceUserId: undefined,
    traceUserName: undefined,
    traceTime: undefined,
    rptPath: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined,
  }
  formRef.value?.resetFields()
}
</script>
