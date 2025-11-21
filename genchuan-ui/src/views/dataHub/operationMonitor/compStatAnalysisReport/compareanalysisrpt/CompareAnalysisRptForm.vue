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
          <el-form-item label="对比维度类型" prop="compareDimType">
            <el-select v-model="formData.compareDimType" placeholder="请选择对比维度类型">
              <el-option label="请选择字典生成" value="" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="统计周期" prop="statCycle">
            <el-input v-model="formData.statCycle" placeholder="请输入统计周期" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="统计周期名称" prop="statCycleName">
            <el-input v-model="formData.statCycleName" placeholder="请输入统计周期名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="统计指标" prop="statIdx">
        <el-input v-model="formData.statIdx" placeholder="请输入统计指标" />
      </el-form-item>

      <el-divider content-position="left">对比对象1信息</el-divider>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="对比对象1ID" prop="compareObj1Id">
            <el-input v-model="formData.compareObj1Id" placeholder="请输入对比对象1ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="对比对象1名称" prop="compareObj1Name">
            <el-input v-model="formData.compareObj1Name" placeholder="请输入对比对象1名称" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="对比对象1数值" prop="compareObj1Value">
        <el-input
          v-model="formData.compareObj1Value"
          placeholder="请输入对比对象1数值"
          type="number"
        />
      </el-form-item>

      <el-divider content-position="left">对比对象2信息</el-divider>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="对比对象2ID" prop="compareObj2Id">
            <el-input v-model="formData.compareObj2Id" placeholder="请输入对比对象2ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="对比对象2名称" prop="compareObj2Name">
            <el-input v-model="formData.compareObj2Name" placeholder="请输入对比对象2名称" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="对比对象2数值" prop="compareObj2Value">
        <el-input
          v-model="formData.compareObj2Value"
          placeholder="请输入对比对象2数值"
          type="number"
        />
      </el-form-item>

      <el-divider content-position="left">统计分析结果</el-divider>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="平均值" prop="avgValue">
            <el-input v-model="formData.avgValue" placeholder="请输入平均值" type="number" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="最大差异值" prop="maxDiffValue">
            <el-input
              v-model="formData.maxDiffValue"
              placeholder="请输入最大差异值"
              type="number"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="最大差异率" prop="maxDiffRate">
            <el-input v-model="formData.maxDiffRate" placeholder="请输入最大差异率" type="number" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="报表生成时间" prop="rptCreateTime">
            <el-date-picker
              v-model="formData.rptCreateTime"
              type="datetime"
              value-format="x"
              placeholder="选择报表生成时间"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="报表备注" prop="rptRemark">
        <el-input
          v-model="formData.rptRemark"
          placeholder="请输入报表备注"
          type="textarea"
          rows="3"
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
import {
  CompareAnalysisRptApi,
  CompareAnalysisRptVO
} from '@/api/dataHub/operationMonitor/compStatAnalysisReport/compareanalysisrpt'

/** 综合对比分析报表 表单 */
defineOptions({ name: 'CompareAnalysisRptForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<CompareAnalysisRptVO>>({
  id: undefined,
  rptId: undefined,
  compareDimType: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  statIdx: undefined,
  compareObj1Id: undefined,
  compareObj1Name: undefined,
  compareObj1Value: undefined,
  compareObj2Id: undefined,
  compareObj2Name: undefined,
  compareObj2Value: undefined,
  avgValue: undefined,
  maxObjId: undefined,
  maxObjName: undefined,
  maxValue: undefined,
  minObjId: undefined,
  minObjName: undefined,
  minValue: undefined,
  maxDiffValue: undefined,
  maxDiffRate: undefined,
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
      const data = await CompareAnalysisRptApi.getCompareAnalysisRpt(id)
      formData.value = { ...data }
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
    const data = formData.value as unknown as CompareAnalysisRptVO
    if (formType.value === 'create') {
      await CompareAnalysisRptApi.createCompareAnalysisRpt(data)
      message.success(t('common.createSuccess'))
    } else {
      await CompareAnalysisRptApi.updateCompareAnalysisRpt(data)
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
    compareDimType: undefined,
    statCycle: undefined,
    statCycleName: undefined,
    statIdx: undefined,
    compareObj1Id: undefined,
    compareObj1Name: undefined,
    compareObj1Value: undefined,
    compareObj2Id: undefined,
    compareObj2Name: undefined,
    compareObj2Value: undefined,
    avgValue: undefined,
    maxObjId: undefined,
    maxObjName: undefined,
    maxValue: undefined,
    minObjId: undefined,
    minObjName: undefined,
    minValue: undefined,
    maxDiffValue: undefined,
    maxDiffRate: undefined,
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
  padding: 10px 0;
}

::v-deep .el-divider__text {
  color: #666;
  font-weight: 500;
  background-color: #f5f7fa;
  padding: 0 10px;
}
</style>
