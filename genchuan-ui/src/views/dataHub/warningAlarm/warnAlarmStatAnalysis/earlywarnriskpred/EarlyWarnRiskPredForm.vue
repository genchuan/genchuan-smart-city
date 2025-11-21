<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="700px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="130px"
      v-loading="formLoading"
      class="form-container"
    >
      <!-- 栅格布局：分两列优化布局 -->
      <el-row :gutter="24">
        <el-col :span="12">
          <el-form-item label="预测ID" prop="trendForecastId">
            <el-input v-model="formData.trendForecastId" placeholder="请输入预测ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预测对象" prop="forecastObject">
            <el-input v-model="formData.forecastObject" placeholder="请输入预测对象" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预测对象值" prop="forecastObjectValue">
            <el-input v-model="formData.forecastObjectValue" placeholder="请输入预测对象值" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="历史数据周期" prop="historyDataCycle">
            <el-input v-model="formData.historyDataCycle" placeholder="请输入历史数据周期" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="历史数据范围" prop="historyDataRange">
            <el-input v-model="formData.historyDataRange" placeholder="请输入历史数据范围" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预测周期" prop="forecastCycle">
            <el-input v-model="formData.forecastCycle" placeholder="请输入预测周期" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预测时间范围" prop="forecastTimeRange">
            <el-input v-model="formData.forecastTimeRange" placeholder="请输入预测时间范围" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预测指标" prop="forecastIndicators">
            <el-input v-model="formData.forecastIndicators" placeholder="请输入预测指标" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预测模型" prop="forecastModel">
            <el-input v-model="formData.forecastModel" placeholder="请输入预测模型" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预测准确率(%)" prop="forecastAccuracy">
            <el-input
              v-model="formData.forecastAccuracy"
              placeholder="请输入预测准确率"
              type="number"
              step="0.01"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预测人ID" prop="forecastUserId">
            <el-input v-model="formData.forecastUserId" placeholder="请输入预测人ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预测人姓名" prop="forecastUserName">
            <el-input v-model="formData.forecastUserName" placeholder="请输入预测人姓名" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预测时间" prop="forecastTime">
            <el-date-picker
              v-model="formData.forecastTime"
              type="datetime"
              value-format="x"
              placeholder="选择预测时间"
              class="!w-full"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 扩展字段：折叠面板隐藏 -->
      <el-collapse v-model="activeCollapse" class="mt-4">
        <el-collapse-item name="extFields" title="扩展字段（可选）">
          <el-row :gutter="24">
            <el-col :span="12">
              <el-form-item label="分类扩展字段1" prop="extCat1">
                <el-input v-model="formData.extCat1" placeholder="请输入分类扩展字段1" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="分类扩展字段2" prop="extCat2">
                <el-input v-model="formData.extCat2" placeholder="请输入分类扩展字段2" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="通用扩展字段1" prop="extCommon1">
                <el-input v-model="formData.extCommon1" placeholder="请输入通用扩展字段1" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="通用扩展字段2" prop="extCommon2">
                <el-input v-model="formData.extCommon2" placeholder="请输入通用扩展字段2" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-collapse-item>
      </el-collapse>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
import {
  EarlyWarnRiskPredApi,
  EarlyWarnRiskPredVO
} from '@/api/dataHub/warningAlarm/warnAlarmStatAnalysis/earlywarnriskpred'

/** 预警告警风险趋势预测 表单 */
defineOptions({ name: 'EarlyWarnRiskPredForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const activeCollapse = ref<string[]>([]) // 折叠面板状态
const formData = ref<Partial<EarlyWarnRiskPredVO>>({
  id: undefined,
  trendForecastId: undefined,
  forecastObject: undefined,
  forecastObjectValue: undefined,
  historyDataCycle: undefined,
  historyDataRange: undefined,
  forecastCycle: undefined,
  forecastTimeRange: undefined,
  forecastIndicators: undefined,
  forecastModel: undefined,
  forecastAccuracy: undefined,
  forecastUserId: undefined,
  forecastUserName: undefined,
  forecastTime: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})

// 表单校验规则
const formRules = reactive({
  trendForecastId: [{ required: true, message: '预测ID不能为空', trigger: 'blur' }],
  forecastObject: [{ required: true, message: '预测对象不能为空', trigger: 'blur' }],
  forecastCycle: [{ required: true, message: '预测周期不能为空', trigger: 'blur' }],
  forecastModel: [{ required: true, message: '预测模型不能为空', trigger: 'blur' }],
  forecastAccuracy: [{ required: true, message: '预测准确率不能为空', trigger: 'blur' }],
  forecastUserId: [{ required: true, message: '预测人ID不能为空', trigger: 'blur' }],
  forecastUserName: [{ required: true, message: '预测人姓名不能为空', trigger: 'blur' }],
  forecastTime: [{ required: true, message: '预测时间不能为空', trigger: 'blur' }]
})

const formRef = ref() // 表单 Ref

/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = t('action.' + type)
  formType.value = type
  resetForm()

  // 修改时加载数据
  if (id) {
    formLoading.value = true
    try {
      const res = await EarlyWarnRiskPredApi.getEarlyWarnRiskPred(id)
      formData.value = { ...res }
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
    const submitData = formData.value as EarlyWarnRiskPredVO
    if (formType.value === 'create') {
      await EarlyWarnRiskPredApi.createEarlyWarnRiskPred(submitData)
      message.success(t('common.createSuccess'))
    } else {
      await EarlyWarnRiskPredApi.updateEarlyWarnRiskPred(submitData)
      message.success(t('common.updateSuccess'))
    }
    dialogVisible.value = false
    emit('success') // 通知父组件刷新列表
  } finally {
    formLoading.value = false
  }
}

/** 重置表单 */
const resetForm = () => {
  formData.value = {
    id: undefined,
    trendForecastId: undefined,
    forecastObject: undefined,
    forecastObjectValue: undefined,
    historyDataCycle: undefined,
    historyDataRange: undefined,
    forecastCycle: undefined,
    forecastTimeRange: undefined,
    forecastIndicators: undefined,
    forecastModel: undefined,
    forecastAccuracy: undefined,
    forecastUserId: undefined,
    forecastUserName: undefined,
    forecastTime: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined
  }
  formRef.value?.resetFields()
  activeCollapse.value = []
}
</script>

<style scoped>
.form-container {
  padding: 8px 0;
}

::v-deep .el-form-item {
  margin-bottom: 16px;
}

::v-deep .el-collapse {
  border: 1px solid #f0f2f5;
  border-radius: 6px;
  overflow: hidden;
}

::v-deep .el-collapse-item__header {
  background-color: #f9fafb;
}
</style>
