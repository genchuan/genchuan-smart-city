<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="800px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="140px"
      v-loading="formLoading"
      class="p-4 bg-gray-50 rounded-lg"
    >
      <!-- 纵向布局：确保输入控件对齐，符合用户使用习惯 -->
      <el-form-item label="分析名称" prop="resultAnalysisName">
        <el-input
          v-model="formData.resultAnalysisName"
          placeholder="请输入分析名称"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="分析编码" prop="resultAnalysisCode">
        <el-input
          v-model="formData.resultAnalysisCode"
          placeholder="请输入分析编码"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="关联任务ID" prop="evalTaskId">
        <el-input
          v-model="formData.evalTaskId"
          placeholder="请输入关联任务ID"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="关联任务名称" prop="evalTaskName">
        <el-input
          v-model="formData.evalTaskName"
          placeholder="请输入关联任务名称"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="分析维度" prop="analysisDimension">
        <el-input
          v-model="formData.analysisDimension"
          placeholder="请输入分析维度"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="行政区划代码" prop="regionCode">
        <el-input
          v-model="formData.regionCode"
          placeholder="请输入行政区划代码"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="行政区划名称" prop="regionName">
        <el-input
          v-model="formData.regionName"
          placeholder="请输入行政区划名称"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="指标项ID" prop="idxItemId">
        <el-input
          v-model="formData.idxItemId"
          placeholder="请输入指标项ID"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="指标项名称" prop="idxItemName">
        <el-input
          v-model="formData.idxItemName"
          placeholder="请输入指标项名称"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="时间范围" prop="timeRange">
        <el-input
          v-model="formData.timeRange"
          placeholder="请输入时间范围（例：2024-01至2024-12）"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="分析结果" prop="analysisResult">
        <el-input
          v-model="formData.analysisResult"
          placeholder="请输入分析结果"
          type="textarea"
          :rows="3"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="图表类型" prop="chartType">
        <el-select
          v-model="formData.chartType"
          placeholder="请选择图表类型"
          class="w-full rounded-md"
        >
          <el-option label="BAR" value="BAR" />
          <el-option label="PIE" value="PIE" />
          <el-option label="LINE" value="LINE" />
          <el-option label="RADAR" value="RADAR" />
          <el-option label="SCATTER" value="SCATTER" />
        </el-select>
      </el-form-item>
      <el-form-item label="生成时间" prop="generateTime">
        <el-date-picker
          v-model="formData.generateTime"
          type="datetime"
          value-format="x"
          placeholder="选择生成时间"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="生成人" prop="generateUser">
        <el-input
          v-model="formData.generateUser"
          placeholder="请输入生成人"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="创建时间(业务)" prop="createTimeBiz">
        <el-date-picker
          v-model="formData.createTimeBiz"
          type="date"
          value-format="x"
          placeholder="选择创建时间(业务)"
          class="w-full rounded-md"
        />
      </el-form-item>
    </el-form>

    <template #footer>
      <el-button @click="dialogVisible = false" class="mr-2">取 消</el-button>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
import {
  EvalResultAnalysisApi,
  EvalResultAnalysisVO
} from '@/api/dataHub/comprehensiveEval/evalResultApply/evalResultAna'

/** 评价结果分析 表单 */
defineOptions({ name: 'EvalResultAnalysisForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

// 简化表单数据，隐藏不必要字段
const formData = ref({
  id: undefined,
  resultAnalysisName: undefined,
  resultAnalysisCode: undefined,
  evalTaskId: undefined,
  evalTaskName: undefined,
  analysisDimension: undefined,
  regionCode: undefined,
  regionName: undefined,
  idxItemId: undefined,
  idxItemName: undefined,
  timeRange: undefined,
  analysisResult: undefined,
  chartType: undefined,
  generateTime: undefined,
  generateUser: undefined,
  createTimeBiz: undefined
})

// 保留核心必填项校验
const formRules = reactive({
  resultAnalysisName: [{ required: true, message: '分析名称不能为空', trigger: 'blur' }],
  resultAnalysisCode: [{ required: true, message: '分析编码不能为空', trigger: 'blur' }],
  evalTaskId: [{ required: true, message: '关联任务ID不能为空', trigger: 'blur' }],
  evalTaskName: [{ required: true, message: '关联任务名称不能为空', trigger: 'blur' }],
  analysisDimension: [{ required: true, message: '分析维度不能为空', trigger: 'blur' }],
  analysisResult: [{ required: true, message: '分析结果不能为空', trigger: 'blur' }],
  chartType: [{ required: true, message: '图表类型不能为空', trigger: 'change' }],
  generateTime: [{ required: true, message: '生成时间不能为空', trigger: 'change' }],
  generateUser: [{ required: true, message: '生成人不能为空', trigger: 'blur' }],
  createTimeBiz: [{ required: true, message: '创建时间(业务)不能为空', trigger: 'change' }]
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
      const res = await EvalResultAnalysisApi.getEvalResultAnalysis(id)
      formData.value = {
        ...res,
        // 只保留需要展示的字段
        analysisResult: res.analysisResult || '',
        timeRange: res.timeRange || ''
      }
    } finally {
      formLoading.value = false
    }
  }
}

defineExpose({ open }) // 提供 open 方法

/** 提交表单 */
const emit = defineEmits(['success'])
const submitForm = async () => {
  // 校验表单
  await formRef.value.validate()
  // 提交请求
  formLoading.value = true
  try {
    const data = formData.value as unknown as EvalResultAnalysisVO
    if (formType.value === 'create') {
      await EvalResultAnalysisApi.createEvalResultAnalysis(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalResultAnalysisApi.updateEvalResultAnalysis(data)
      message.success(t('common.updateSuccess'))
    }
    dialogVisible.value = false
    emit('success')
  } finally {
    formLoading.value = false
  }
}

/** 重置表单 */
const resetForm = () => {
  formData.value = {
    id: undefined,
    resultAnalysisName: undefined,
    resultAnalysisCode: undefined,
    evalTaskId: undefined,
    evalTaskName: undefined,
    analysisDimension: undefined,
    regionCode: undefined,
    regionName: undefined,
    idxItemId: undefined,
    idxItemName: undefined,
    timeRange: undefined,
    analysisResult: undefined,
    chartType: undefined,
    generateTime: undefined,
    generateUser: undefined,
    createTimeBiz: undefined
  }
  formRef.value?.resetFields()
}
</script>

<style scoped>
.el-form {
  background-color: #fafafa;
}

.el-input,
.el-date-picker,
.el-select {
  --el-input-bg-color: #fff;
  --el-input-border-color: #e4e7ed;
  --el-input-hover-border-color: #409eff;
  width: 100%;
}

.el-dialog__body {
  padding: 16px 24px;
}

.el-form-item {
  margin-bottom: 20px;
}

.el-textarea {
  resize: none;
}

/* 确保输入控件高度一致 */
:deep(.el-input__wrapper),
:deep(.el-date-editor) {
  height: 32px;
  line-height: 32px;
}

:deep(.el-date-editor .el-input__wrapper) {
  height: auto;
}

/* 日期选择器宽度适配 */
:deep(.el-date-editor--datetime) {
  width: 100% !important;
}
</style>
