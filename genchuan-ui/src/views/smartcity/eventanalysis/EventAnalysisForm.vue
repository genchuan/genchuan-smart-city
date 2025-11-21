<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="关联分析编号" prop="analysisNumber">
        <el-input v-model="formData.analysisNumber" placeholder="请输入关联分析编号" />
      </el-form-item>
      <el-form-item label="主事件编号" prop="mainEventNumber">
        <el-input v-model="formData.mainEventNumber" placeholder="请输入主事件编号" />
      </el-form-item>
      <el-form-item label="主事件名称" prop="mainEventName">
        <el-input v-model="formData.mainEventName" placeholder="请输入主事件名称" />
      </el-form-item>
      <el-form-item label="关联事件编号" prop="relatedEventNumber">
        <el-input v-model="formData.relatedEventNumber" placeholder="请输入关联事件编号" />
      </el-form-item>
      <el-form-item label="关联事件名称" prop="relatedEventName">
        <el-input v-model="formData.relatedEventName" placeholder="请输入关联事件名称" />
      </el-form-item>
      <el-form-item label="关联类型" prop="associationType">
        <el-select v-model="formData.associationType" placeholder="请选择关联类型">
          <el-option label="直接因果" value="direct_causal" />
          <el-option label="间接因果" value="indirect_causal" />
          <el-option label="同期发生" value="same_period" />
          <el-option label="时序依赖" value="time_dependent" />
          <el-option label="同部件关联" value="same_component" />
          <el-option label="上下游关联" value="upstream_downstream" />
          <el-option label="同系统关联" value="same_system" />
          <el-option label="衍生关联" value="derivative" />
          <el-option label="耦合关联" value="coupling" />
        </el-select>
      </el-form-item>
      <el-form-item label="关联强度" prop="associationStrength">
        <el-input v-model="formData.associationStrength" placeholder="请输入关联强度" />
      </el-form-item>
      <el-form-item label="分析时间" prop="analysisTime">
        <el-date-picker
          v-model="formData.analysisTime"
          type="datetime"
          value-format="x"
          placeholder="选择分析时间"
        />
      </el-form-item>
      <el-form-item label="分析人员" prop="analysts">
        <el-input v-model="formData.analysts" placeholder="请输入分析人员" />
      </el-form-item>
      <el-form-item label="分析结论" prop="conclusion">
        <el-input v-model="formData.conclusion" placeholder="请输入分析结论" />
      </el-form-item>
      <el-form-item label="关联证据" prop="relatedEvidence">
        <el-input v-model="formData.relatedEvidence" placeholder="请输入关联证据" />
      </el-form-item>
      <el-form-item label="建议措施" prop="recommendedMeasure">
        <el-input v-model="formData.recommendedMeasure" placeholder="请输入建议措施" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { EventAnalysisApi, EventAnalysisVO } from '@/api/smartcity/eventanalysis'

/** 事件关联分析 表单 */
defineOptions({ name: 'EventAnalysisForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  analysisNumber: undefined,
  mainEventNumber: undefined,
  mainEventName: undefined,
  relatedEventNumber: undefined,
  relatedEventName: undefined,
  associationType: undefined,
  associationStrength: undefined,
  analysisTime: undefined,
  analysts: undefined,
  conclusion: undefined,
  relatedEvidence: undefined,
  recommendedMeasure: undefined
})
const formRules = reactive({})
const formRef = ref() // 表单 ref

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
      formData.value = await EventAnalysisApi.getEventAnalysis(id)
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
    const data = formData.value as unknown as EventAnalysisVO
    if (formType.value === 'create') {
      await EventAnalysisApi.createEventAnalysis(data)
      message.success(t('common.createSuccess'))
    } else {
      await EventAnalysisApi.updateEventAnalysis(data)
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
    analysisNumber: undefined,
    mainEventNumber: undefined,
    mainEventName: undefined,
    relatedEventNumber: undefined,
    relatedEventName: undefined,
    associationType: undefined,
    associationStrength: undefined,
    analysisTime: undefined,
    analysts: undefined,
    conclusion: undefined,
    relatedEvidence: undefined,
    recommendedMeasure: undefined
  }
  formRef.value?.resetFields()
}
</script>
