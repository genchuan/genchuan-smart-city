<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="跟踪ID" prop="trackId">
        <el-input v-model="formData.trackId" placeholder="请输入跟踪ID" />
      </el-form-item>
      <el-form-item label="工单ID" prop="woId">
        <el-input v-model="formData.woId" placeholder="请输入工单ID" />
      </el-form-item>
      <el-form-item label="处置状态" prop="handleStatus">
        <el-radio-group v-model="formData.handleStatus">
          <el-radio value="1">已完成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="当前处置节点" prop="currentNode">
        <el-input v-model="formData.currentNode" placeholder="请输入当前处置节点" />
      </el-form-item>
      <el-form-item label="处置人员" prop="handlerName">
        <el-input v-model="formData.handlerName" placeholder="请输入处置人员" />
      </el-form-item>
      <el-form-item label="处置开始时间" prop="startTime">
        <el-date-picker
          v-model="formData.startTime"
          type="date"
          value-format="x"
          placeholder="选择处置开始时间"
        />
      </el-form-item>
      <el-form-item label="预计完成时间" prop="estCompleteTime">
        <el-date-picker
          v-model="formData.estCompleteTime"
          type="date"
          value-format="x"
          placeholder="选择预计完成时间"
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
import { EvtDisposalTrackApi, EvtDisposalTrackVO } from '@/api/dataHub/commandAndCoordination/evtdisposaltrack'

/** 事件处置跟踪 表单 */
defineOptions({ name: 'EvtDisposalTrackForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  trackId: undefined,
  woId: undefined,
  handleStatus: undefined,
  currentNode: undefined,
  handlerName: undefined,
  startTime: undefined,
  estCompleteTime: undefined,
})
const formRules = reactive({
  trackId: [{ required: true, message: '跟踪ID不能为空', trigger: 'blur' }],
  woId: [{ required: true, message: '工单ID不能为空', trigger: 'blur' }],
  handleStatus: [{ required: true, message: '处置状态不能为空', trigger: 'blur' }],
  currentNode: [{ required: true, message: '当前处置节点不能为空', trigger: 'blur' }],
  handlerName: [{ required: true, message: '处置人员不能为空', trigger: 'blur' }],
  startTime: [{ required: true, message: '处置开始时间不能为空', trigger: 'blur' }],
  estCompleteTime: [{ required: true, message: '预计完成时间不能为空', trigger: 'blur' }],
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
      formData.value = await EvtDisposalTrackApi.getEvtDisposalTrack(id)
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
    const data = formData.value as unknown as EvtDisposalTrackVO
    if (formType.value === 'create') {
      await EvtDisposalTrackApi.createEvtDisposalTrack(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvtDisposalTrackApi.updateEvtDisposalTrack(data)
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
    trackId: undefined,
    woId: undefined,
    handleStatus: undefined,
    currentNode: undefined,
    handlerName: undefined,
    startTime: undefined,
    estCompleteTime: undefined,
  }
  formRef.value?.resetFields()
}
</script>
