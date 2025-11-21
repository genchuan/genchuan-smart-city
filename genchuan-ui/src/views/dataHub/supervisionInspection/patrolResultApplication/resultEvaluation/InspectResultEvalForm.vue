<template>
  <Dialog :title="dialogTitle" v-model="internalVisible" width="900px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="150px"
      class="form-container"
      v-loading="formLoading"
    >
      <el-row :gutter="20">
        <!-- 基础信息组 -->
        <el-col :span="24" class="mb-4">
          <div class="form-group-title bg-blue-50 text-blue-700 px-3 py-2 rounded-md font-medium"
            >基础信息
          </div>
        </el-col>
        <el-col :span="12">
          <el-form-item label="评估ID" prop="evalId">
            <el-input
              v-model="formData.evalId"
              placeholder="请输入评估ID"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="评估编码" prop="evalCode">
            <el-input
              v-model="formData.evalCode"
              placeholder="请输入评估编码"
              class="!w-full rounded-md border-gray-200"
              :disabled="formType === 'update'"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="评估周期" prop="evalCycle">
            <el-input
              v-model="formData.evalCycle"
              placeholder="请输入评估周期（如：2024Q1）"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="评估周期名称" prop="evalCycleName">
            <el-input
              v-model="formData.evalCycleName"
              placeholder="请输入评估周期名称（如：2024年第一季度）"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属区域代码" prop="areaCode">
            <el-input
              v-model="formData.areaCode"
              placeholder="请输入所属区域代码"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属区域名称" prop="areaName">
            <el-input
              v-model="formData.areaName"
              placeholder="请输入所属区域名称"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>

        <!-- 评估数据组 -->
        <el-col :span="24" class="mb-4 mt-6">
          <div class="form-group-title bg-green-50 text-green-700 px-3 py-2 rounded-md font-medium"
            >评估数据
          </div>
        </el-col>
        <el-col :span="8">
          <el-form-item label="日常巡查合格率" prop="dailyQualifiedRate">
            <el-input
              v-model="formData.dailyQualifiedRate"
              placeholder="请输入百分比（如：98.5）"
              type="number"
              step="0.1"
              min="0"
              max="100"
              class="!w-full rounded-md border-gray-200"
            />
            <div class="text-xs text-gray-500 mt-1">注：输入0-100之间的数值，保留1位小数</div>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="专项巡查整改率" prop="specRectifyRate">
            <el-input
              v-model="formData.specRectifyRate"
              placeholder="请输入百分比（如：95.0）"
              type="number"
              step="0.1"
              min="0"
              max="100"
              class="!w-full rounded-md border-gray-200"
            />
            <div class="text-xs text-gray-500 mt-1">注：输入0-100之间的数值，保留1位小数</div>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="一级问题处置及时率" prop="level1TimelyRate">
            <el-input
              v-model="formData.level1TimelyRate"
              placeholder="请输入百分比（如：100.0）"
              type="number"
              step="0.1"
              min="0"
              max="100"
              class="!w-full rounded-md border-gray-200"
            />
            <div class="text-xs text-gray-500 mt-1">注：输入0-100之间的数值，保留1位小数</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="问题集中区域" prop="problemConcentratedArea">
            <el-input
              v-model="formData.problemConcentratedArea"
              placeholder="请输入问题集中区域"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="主要问题类型" prop="mainProblemType">
            <el-select
              v-model="formData.mainProblemType"
              placeholder="请选择主要问题类型"
              class="!w-full rounded-md border-gray-200"
            >
              <el-option label="请选择字典生成" value="" />
            </el-select>
          </el-form-item>
        </el-col>

        <!-- 评估配置组 -->
        <el-col :span="24" class="mb-4 mt-6">
          <div
            class="form-group-title bg-purple-50 text-purple-700 px-3 py-2 rounded-md font-medium"
            >评估配置
          </div>
        </el-col>
        <el-col :span="24">
          <el-form-item label="评估意见" prop="evalOpinion">
            <el-input
              v-model="formData.evalOpinion"
              placeholder="请输入评估意见"
              type="textarea"
              :rows="3"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="评估人" prop="evalUser">
            <el-input
              v-model="formData.evalUser"
              placeholder="请输入评估人"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="评估时间" prop="evalTime">
            <el-date-picker
              v-model="formData.evalTime"
              type="date"
              value-format="x"
              placeholder="选择评估时间"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="评估报告URL" prop="evalRptUrl">
            <el-input
              v-model="formData.evalRptUrl"
              placeholder="请输入评估报告URL"
              class="!w-full rounded-md border-gray-200"
            />
          </el-form-item>
        </el-col>

        <!-- 隐藏系统自动生成字段和扩展字段 -->
        <el-form-item label="创建人" prop="createUser" v-if="false">
          <el-input v-model="formData.createUser" />
        </el-form-item>
        <el-form-item label="创建时间" prop="bizCreateTime" v-if="false">
          <el-date-picker v-model="formData.bizCreateTime" type="date" value-format="x" />
        </el-form-item>
        <el-form-item label="分类扩展字段1" prop="extCat1" v-if="false">
          <el-input v-model="formData.extCat1" />
        </el-form-item>
        <el-form-item label="分类扩展字段2" prop="extCat2" v-if="false">
          <el-input v-model="formData.extCat2" />
        </el-form-item>
        <el-form-item label="通用扩展字段1" prop="extCommon1" v-if="false">
          <el-input v-model="formData.extCommon1" />
        </el-form-item>
        <el-form-item label="通用扩展字段2" prop="extCommon2" v-if="false">
          <el-input v-model="formData.extCommon2" />
        </el-form-item>
      </el-row>
    </el-form>
    <template #footer>
      <el-button @click="internalVisible = false" class="mr-2">取 消</el-button>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  InspectResultEvalApi,
  InspectResultEvalVO
} from '@/api/dataHub/supervisionInspection/patrolResultApplication/resultEvaluation'
import { nextTick } from 'vue'

/** 巡查巡检结果评估 表单 */
defineOptions({ name: 'InspectResultEvalForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

// 接收父组件v-model绑定，规避直接修改props
const props = defineProps<{
  modelValue: boolean
}>()
const emit = defineEmits(['update:modelValue', 'success'])

const internalVisible = computed({
  get: () => props.modelValue,
  set: (val) => emit('update:modelValue', val)
})

const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<InspectResultEvalVO>>({
  id: undefined,
  evalId: undefined,
  evalCode: undefined,
  evalCycle: undefined,
  evalCycleName: undefined,
  areaCode: undefined,
  areaName: undefined,
  dailyQualifiedRate: undefined,
  specRectifyRate: undefined,
  level1TimelyRate: undefined,
  problemConcentratedArea: undefined,
  mainProblemType: undefined,
  evalOpinion: undefined,
  evalUser: undefined,
  evalTime: undefined,
  evalRptUrl: undefined,
  createUser: undefined, // 隐藏，系统自动填充
  bizCreateTime: undefined, // 隐藏，系统自动填充
  extCat1: undefined, // 隐藏扩展字段
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
// 表单校验规则（针对数值字段增加范围校验）
const formRules = reactive({
  evalCode: [{ required: true, message: '请输入评估编码', trigger: 'blur' }],
  evalCycleName: [{ required: true, message: '请输入评估周期名称', trigger: 'blur' }],
  areaName: [{ required: true, message: '请输入所属区域名称', trigger: 'blur' }],
  dailyQualifiedRate: [
    { required: true, message: '请输入日常巡查合格率', trigger: 'blur' },
    { type: 'number', min: 0, max: 100, message: '请输入0-100之间的数值', trigger: 'change' }
  ],
  specRectifyRate: [
    { required: true, message: '请输入专项巡查整改率', trigger: 'blur' },
    { type: 'number', min: 0, max: 100, message: '请输入0-100之间的数值', trigger: 'change' }
  ],
  level1TimelyRate: [
    { required: true, message: '请输入一级问题处置及时率', trigger: 'blur' },
    { type: 'number', min: 0, max: 100, message: '请输入0-100之间的数值', trigger: 'change' }
  ],
  evalUser: [{ required: true, message: '请输入评估人', trigger: 'blur' }],
  evalTime: [{ required: true, message: '请选择评估时间', trigger: 'change' }]
})
const formRef = ref() // 表单 Ref

/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  formType.value = type
  dialogTitle.value = t('action.' + type)
  resetForm()
  // 先显示弹窗再重置（确保DOM已渲染）
  internalVisible.value = true

  // 修改时加载数据
  if (id) {
    formLoading.value = true
    try {
      const res = await InspectResultEvalApi.getInspectResultEval(id)
      formData.value = res
    } catch (error) {
      message.error('数据加载失败，请重试')
      internalVisible.value = false
    } finally {
      formLoading.value = false
    }
  }
}

// 暴露open方法给父组件
defineExpose({ open })

/** 提交表单 */
const submitForm = async () => {
  // 校验表单
  await formRef.value.validate()
  formLoading.value = true
  try {
    const data = formData.value as InspectResultEvalVO
    // 处理数值字段（确保为number类型）
    data.dailyQualifiedRate = Number(data.dailyQualifiedRate)
    data.specRectifyRate = Number(data.specRectifyRate)
    data.level1TimelyRate = Number(data.level1TimelyRate)

    if (formType.value === 'create') {
      await InspectResultEvalApi.createInspectResultEval(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectResultEvalApi.updateInspectResultEval(data)
      message.success(t('common.updateSuccess'))
    }
    internalVisible.value = false
    emit('success') // 通知父组件刷新列表
  } catch (error) {
    message.error('操作失败，请重试')
  } finally {
    formLoading.value = false
  }
}

/** 重置表单（确保所有字段清空） */
const resetForm = () => {
  formData.value = {
    id: undefined,
    evalId: undefined,
    evalCode: undefined,
    evalCycle: undefined,
    evalCycleName: undefined,
    areaCode: undefined,
    areaName: undefined,
    dailyQualifiedRate: undefined,
    specRectifyRate: undefined,
    level1TimelyRate: undefined,
    problemConcentratedArea: undefined,
    mainProblemType: undefined,
    evalOpinion: undefined,
    evalUser: undefined,
    evalTime: undefined,
    evalRptUrl: undefined
  }
  nextTick(() => {
    formRef.value?.resetFields()
  })
}
</script>
<style scoped>
.form-container {
  padding: 10px;
}

.form-group-title {
  margin-bottom: 15px;
  border-left: 3px solid currentColor;
  transition: all 0.2s ease;
}

.form-group-title:hover {
  opacity: 0.9;
}

/* 输入框 hover 效果，避免content覆盖 */
::v-deep(.el-input__wrapper),
::v-deep(.el-textarea__wrapper),
::v-deep(.el-select__wrapper),
::v-deep(.el-date-picker__wrapper) {
  transition: all 0.3s ease;
  border-color: #e5e7eb;
}

::v-deep(.el-input__wrapper):hover,
::v-deep(.el-textarea__wrapper):hover,
::v-deep(.el-select__wrapper):hover,
::v-deep(.el-date-picker__wrapper):hover {
  border-color: #409eff;
  box-shadow: 0 0 0 2px rgba(64, 158, 255, 0.1);
}

/* 弹窗样式优化 */
::v-deep(.el-dialog__body) {
  padding: 20px;
  background-color: #fafafa;
}

::v-deep(.el-form-item) {
  margin-bottom: 16px;
}

/* 数值输入框提示文本样式 */
::v-deep(.el-form-item__help) {
  color: #9ca3af;
  font-size: 12px;
}
</style>
