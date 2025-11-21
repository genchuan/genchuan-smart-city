<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="700px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
      class="form-container"
    >
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="报表ID" prop="rptId">
            <el-input v-model="formData.rptId" placeholder="请输入报表ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分析维度类型" prop="trendDimType">
            <el-select v-model="formData.trendDimType" placeholder="请选择分析维度类型">
              <el-option label="请选择字典生成" value="" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="分析维度ID" prop="trendDimId">
            <el-input v-model="formData.trendDimId" placeholder="请输入分析维度ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分析维度名称" prop="trendDimName">
            <el-input v-model="formData.trendDimName" placeholder="请输入分析维度名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="时间粒度" prop="timeGranularity">
            <el-input v-model="formData.timeGranularity" placeholder="请输入时间粒度" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="趋势类型" prop="trendType">
            <el-select v-model="formData.trendType" placeholder="请选择趋势类型">
              <el-option label="请选择字典生成" value="" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="起始时间" prop="startTime">
            <el-date-picker
              v-model="formData.startTime"
              type="date"
              value-format="x"
              placeholder="选择起始时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="结束时间" prop="endTime">
            <el-date-picker
              v-model="formData.endTime"
              type="date"
              value-format="x"
              placeholder="选择结束时间"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="周期1标识" prop="cycle1Label">
            <el-input v-model="formData.cycle1Label" placeholder="请输入周期1标识" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="周期1数值" prop="cycle1Value">
            <el-input v-model="formData.cycle1Value" placeholder="请输入周期1数值" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="周期2标识" prop="cycle2Label">
            <el-input v-model="formData.cycle2Label" placeholder="请输入周期2标识" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="周期2数值" prop="cycle2Value">
            <el-input v-model="formData.cycle2Value" placeholder="请输入周期2数值" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="整体变化率" prop="overallChangeRate">
            <el-input v-model="formData.overallChangeRate" placeholder="请输入整体变化率" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="报表生成人" prop="rptCreateUser">
            <el-input v-model="formData.rptCreateUser" placeholder="请输入报表生成人" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="24">
          <el-form-item label="报表备注" prop="rptRemark">
            <el-input
              v-model="formData.rptRemark"
              placeholder="请输入报表备注"
              type="textarea"
              rows="3"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20" class="extension-fields">
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
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  TrendAnalysisRptApi,
  TrendAnalysisRptVO
} from '@/api/dataHub/operationMonitor/compStatAnalysisReport/trendanalysisrpt'

/** 综合趋势分析报表 表单 */
defineOptions({ name: 'TrendAnalysisRptForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  rptId: undefined,
  trendDimType: undefined,
  trendDimId: undefined,
  trendDimName: undefined,
  timeGranularity: undefined,
  startTime: undefined,
  endTime: undefined,
  cycle1Label: undefined,
  cycle1Value: undefined,
  cycle2Label: undefined,
  cycle2Value: undefined,
  overallChangeRate: undefined,
  trendType: undefined,
  rptCreateTime: undefined,
  rptCreateUser: undefined,
  rptRemark: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({})
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
      formData.value = await TrendAnalysisRptApi.getTrendAnalysisRpt(id)
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
    const data = formData.value as unknown as TrendAnalysisRptVO
    if (formType.value === 'create') {
      await TrendAnalysisRptApi.createTrendAnalysisRpt(data)
      message.success(t('common.createSuccess'))
    } else {
      await TrendAnalysisRptApi.updateTrendAnalysisRpt(data)
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
    trendDimType: undefined,
    trendDimId: undefined,
    trendDimName: undefined,
    timeGranularity: undefined,
    startTime: undefined,
    endTime: undefined,
    cycle1Label: undefined,
    cycle1Value: undefined,
    cycle2Label: undefined,
    cycle2Value: undefined,
    overallChangeRate: undefined,
    trendType: undefined,
    rptCreateTime: undefined,
    rptCreateUser: undefined,
    rptRemark: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined
  }
  formRef.value?.resetFields()
}
</script>

<style scoped>
.form-container {
  padding: 15px;
}

.el-row {
  margin-bottom: 15px;
}

.extension-fields {
  background-color: #f9fafb;
  padding: 15px;
  border-radius: 6px;
  margin-top: 10px;
}

::v-deep .el-form-item__label {
  color: #4e5969;
  font-weight: 500;
}

::v-deep .el-input__wrapper {
  border-radius: 4px;
}
</style>
