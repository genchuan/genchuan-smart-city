<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" :width="700">
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
          <el-form-item label="统计周期" prop="statCycle">
            <el-input v-model="formData.statCycle" placeholder="请输入统计周期" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="统计周期名称" prop="statCycleName">
            <el-input v-model="formData.statCycleName" placeholder="请输入统计周期名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="行政区划代码" prop="adminCode">
            <el-input v-model="formData.adminCode" placeholder="请输入行政区划代码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="行政区划名称" prop="adminName">
            <el-input v-model="formData.adminName" placeholder="请输入行政区划名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="指标ID" prop="idxId">
            <el-input v-model="formData.idxId" placeholder="请输入指标ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="指标名称" prop="idxName">
            <el-input v-model="formData.idxName" placeholder="请输入指标名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="指标单位" prop="idxUnit">
            <el-input v-model="formData.idxUnit" placeholder="请输入指标单位" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="指标重要等级" prop="idxLevel">
            <el-select v-model="formData.idxLevel" placeholder="请选择指标重要等级">
              <el-option label="高" value="高" />
              <el-option label="中" value="中" />
              <el-option label="低" value="低" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="指标平均值" prop="idxAvg">
            <el-input v-model="formData.idxAvg" placeholder="请输入指标平均值" type="number" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="指标最大值" prop="idxMax">
            <el-input v-model="formData.idxMax" placeholder="请输入指标最大值" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="超标次数" prop="exceedCount">
            <el-input v-model="formData.exceedCount" placeholder="请输入超标次数" type="number" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="环比改善率(%)" prop="momImproveRate">
            <el-input
              v-model="formData.momImproveRate"
              placeholder="请输入环比改善率"
              type="number"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="报表生成时间" prop="rptCreateTime">
            <el-date-picker
              v-model="formData.rptCreateTime"
              type="date"
              value-format="x"
              placeholder="选择报表生成时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="报表生成人" prop="rptCreateUser">
            <el-input v-model="formData.rptCreateUser" placeholder="请输入报表生成人" />
          </el-form-item>
        </el-col>
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
  KeyIdxSummaryRptApi,
  KeyIdxSummaryRptVO
} from '@/api/dataHub/operationMonitor/compStatAnalysisReport/keyidxsummaryrpt'

/** 关键指标汇总报表 表单 */
defineOptions({ name: 'KeyIdxSummaryRptForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  rptId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  adminCode: undefined,
  adminName: undefined,
  idxId: undefined,
  idxName: undefined,
  idxUnit: undefined,
  idxLevel: undefined,
  idxAvg: undefined,
  idxMax: undefined,
  exceedCount: undefined,
  momImproveRate: undefined,
  rptCreateTime: undefined,
  rptCreateUser: undefined,
  rptRemark: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  rptId: [{ required: true, message: '请输入报表ID', trigger: 'blur' }],
  statCycle: [{ required: true, message: '请输入统计周期', trigger: 'blur' }],
  idxName: [{ required: true, message: '请输入指标名称', trigger: 'blur' }],
  adminName: [{ required: true, message: '请输入行政区划名称', trigger: 'blur' }]
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
      formData.value = await KeyIdxSummaryRptApi.getKeyIdxSummaryRpt(id)
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
    const data = formData.value as unknown as KeyIdxSummaryRptVO
    if (formType.value === 'create') {
      await KeyIdxSummaryRptApi.createKeyIdxSummaryRpt(data)
      message.success(t('common.createSuccess'))
    } else {
      await KeyIdxSummaryRptApi.updateKeyIdxSummaryRpt(data)
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
    statCycle: undefined,
    statCycleName: undefined,
    adminCode: undefined,
    adminName: undefined,
    idxId: undefined,
    idxName: undefined,
    idxUnit: undefined,
    idxLevel: undefined,
    idxAvg: undefined,
    idxMax: undefined,
    exceedCount: undefined,
    momImproveRate: undefined,
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

.el-row {
  margin-bottom: 10px;
}

.el-form-item {
  margin-bottom: 15px;
}

.el-form-item__label {
  font-weight: 500;
}

.el-textarea__inner {
  resize: vertical;
}
</style>
