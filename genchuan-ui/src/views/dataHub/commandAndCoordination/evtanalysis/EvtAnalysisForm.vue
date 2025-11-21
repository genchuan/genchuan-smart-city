<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="研判ID" prop="analysisId">
        <el-input v-model="formData.analysisId" placeholder="请输入研判ID" />
      </el-form-item>
      <el-form-item label="事件编码" prop="evtCode">
        <el-input v-model="formData.evtCode" placeholder="请输入事件编码" />
      </el-form-item>
      <el-form-item label="事件名称" prop="evtName">
        <el-input v-model="formData.evtName" placeholder="请输入事件名称" />
      </el-form-item>
      <el-form-item label="研判人ID" prop="analystId">
        <el-input v-model="formData.analystId" placeholder="请输入研判人ID" />
      </el-form-item>
      <el-form-item label="研判人姓名" prop="analystName">
        <el-input v-model="formData.analystName" placeholder="请输入研判人姓名" />
      </el-form-item>
      <el-form-item label="研判时间" prop="analysisTime">
        <el-date-picker
          v-model="formData.analysisTime"
          type="date"
          value-format="x"
          placeholder="选择研判时间"
        />
      </el-form-item>
      <el-form-item label="事件原因" prop="evtReason">
        <el-input v-model="formData.evtReason" placeholder="请输入事件原因" />
      </el-form-item>
      <el-form-item label="影响范围" prop="impactRange">
        <el-input v-model="formData.impactRange" placeholder="请输入影响范围" />
      </el-form-item>
      <el-form-item label="紧急程度" prop="urgencyLevel">
        <el-input v-model="formData.urgencyLevel" placeholder="请输入紧急程度" />
      </el-form-item>
      <el-form-item label="研判结论" prop="conclusion">
        <el-input v-model="formData.conclusion" placeholder="请输入研判结论" />
      </el-form-item>
      <el-form-item label="关联监测数据ID" prop="relMonDataId">
        <el-input v-model="formData.relMonDataId" placeholder="请输入关联监测数据ID" />
      </el-form-item>
      <el-form-item label="监测数据摘要" prop="monDataAbstract">
        <el-input v-model="formData.monDataAbstract" placeholder="请输入监测数据摘要" />
      </el-form-item>
      <el-form-item label="是否需要分拨" prop="needAllocate">
        <el-input v-model="formData.needAllocate" placeholder="请输入是否需要分拨" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { EvtAnalysisApi, EvtAnalysisVO } from '@/api/dataHub/commandAndCoordination/evtanalysis'

/** 事件研判分析 表单 */
defineOptions({ name: 'EvtAnalysisForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  analysisId: undefined,
  evtCode: undefined,
  evtName: undefined,
  analystId: undefined,
  analystName: undefined,
  analysisTime: undefined,
  evtReason: undefined,
  impactRange: undefined,
  urgencyLevel: undefined,
  conclusion: undefined,
  relMonDataId: undefined,
  monDataAbstract: undefined,
  needAllocate: undefined,
})
const formRules = reactive({
  analysisId: [{ required: true, message: '研判ID不能为空', trigger: 'blur' }],
  evtCode: [{ required: true, message: '事件编码不能为空', trigger: 'blur' }],
  evtName: [{ required: true, message: '事件名称不能为空', trigger: 'blur' }],
  analystId: [{ required: true, message: '研判人ID不能为空', trigger: 'blur' }],
  analystName: [{ required: true, message: '研判人姓名不能为空', trigger: 'blur' }],
  evtReason: [{ required: true, message: '事件原因不能为空', trigger: 'blur' }],
  impactRange: [{ required: true, message: '影响范围不能为空', trigger: 'blur' }],
  urgencyLevel: [{ required: true, message: '紧急程度不能为空', trigger: 'blur' }],
  conclusion: [{ required: true, message: '研判结论不能为空', trigger: 'blur' }],
  needAllocate: [{ required: true, message: '是否需要分拨不能为空', trigger: 'blur' }],
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
      formData.value = await EvtAnalysisApi.getEvtAnalysis(id)
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
    const data = formData.value as unknown as EvtAnalysisVO
    if (formType.value === 'create') {
      await EvtAnalysisApi.createEvtAnalysis(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvtAnalysisApi.updateEvtAnalysis(data)
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
    analysisId: undefined,
    evtCode: undefined,
    evtName: undefined,
    analystId: undefined,
    analystName: undefined,
    analysisTime: undefined,
    evtReason: undefined,
    impactRange: undefined,
    urgencyLevel: undefined,
    conclusion: undefined,
    relMonDataId: undefined,
    monDataAbstract: undefined,
    needAllocate: undefined,
  }
  formRef.value?.resetFields()
}
</script>
