<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="150px"
      v-loading="formLoading"
    >
      <el-form-item label="巡查人员" prop="inspector">
        <el-input v-model="formData.inspector" placeholder="请输入巡查人员" />
      </el-form-item>
      <el-form-item label="巡查开始时间" prop="cycleStartTime">
        <el-date-picker
          v-model="formData.cycleStartTime"
          type="datetime"
          value-format="x"
          placeholder="请选择巡查开始时间"
        />
      </el-form-item>
      <el-form-item label="巡查结束时间" prop="cycleEndTime">
        <el-date-picker
          v-model="formData.cycleEndTime"
          type="datetime"
          value-format="x"
          placeholder="请选择巡查结束时间"
        />
      </el-form-item>
      <el-form-item label="完成巡查任务数" prop="completedTaskCount">
        <el-input v-model.number="formData.completedTaskCount" placeholder="请输入完成巡查任务数" type="number" />
      </el-form-item>
      <el-form-item label="巡查总里程（km）" prop="totalMileageKm">
        <el-input v-model.number="formData.totalMileageKm" placeholder="请输入巡查总里程" type="number" step="0.1" />
      </el-form-item>
      <el-form-item label="巡查总时长（h）" prop="totalDurationH">
        <el-input v-model.number="formData.totalDurationH" placeholder="请输入巡查总时长" type="number" step="0.1" />
      </el-form-item>
      <el-form-item label="发现问题总数" prop="foundProblemCount">
        <el-input v-model.number="formData.foundProblemCount" placeholder="请输入发现问题总数" type="number" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { InspectionAnalysisApi, InspectionAnalysisVO } from '@/api/smartcity/inspectionanalysis'

/** 巡查分析 表单 */
defineOptions({ name: 'InspectionAnalysisForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
// 表单数据
const formData = ref({
  id: undefined,
  inspector: undefined,
  cycleStartTime: undefined,
  cycleEndTime: undefined,
  completedTaskCount: undefined,
  totalMileageKm: undefined,
  totalDurationH: undefined,
  foundProblemCount: undefined
})
// 表单校验规则：针对核心必填字段设置校验
const formRules = reactive({
  inspector: [{ required: true, message: '请输入巡查人员', trigger: 'blur' }],
  cycleStartTime: [{ required: true, message: '请选择巡查开始时间', trigger: 'change' }],
  cycleEndTime: [{ required: true, message: '请选择巡查结束时间', trigger: 'change' }]
})
const formRef = ref() // 表单 ref

/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = t('action.' + type)
  formType.value = type
  resetForm()
  // 修改时，加载巡查分析详情数据
  if (id) {
    formLoading.value = true
    try {
      formData.value = await InspectionAnalysisApi.getInspectionAnalysis(id)
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
    const submitData = { ...formData.value }
    if (formType.value === 'create') {
      await InspectionAnalysisApi.createInspectionAnalysis(submitData as unknown as InspectionAnalysisVO)
      message.success(t('common.createSuccess'))
    } else {
      await InspectionAnalysisApi.updateInspectionAnalysis(submitData as unknown as InspectionAnalysisVO)
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
    inspector: undefined,
    cycleStartTime: undefined,
    cycleEndTime: undefined,
    completedTaskCount: undefined,
    totalMileageKm: undefined,
    totalDurationH: undefined,
    foundProblemCount: undefined
  }
  formRef.value?.resetFields()
}
</script>
