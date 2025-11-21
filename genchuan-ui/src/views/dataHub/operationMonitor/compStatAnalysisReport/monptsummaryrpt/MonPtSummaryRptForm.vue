<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="600px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="150px"
      v-loading="formLoading"
      class="form-container"
    >
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="报表ID" prop="rptId" required>
            <el-input v-model="formData.rptId" placeholder="请输入报表ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="统计周期" prop="statCycle" required>
            <el-input v-model="formData.statCycle" placeholder="请输入统计周期" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="统计周期名称" prop="statCycleName" required>
            <el-input v-model="formData.statCycleName" placeholder="请输入统计周期名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="行政区划代码" prop="adminCode" required>
            <el-input v-model="formData.adminCode" placeholder="请输入行政区划代码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="行政区划名称" prop="adminName" required>
            <el-input v-model="formData.adminName" placeholder="请输入行政区划名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="点位总数量" prop="totalPtCount" required>
            <el-input v-model.number="formData.totalPtCount" placeholder="请输入点位总数量" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="空气质量监测点位数" prop="airPtCount">
            <el-input v-model.number="formData.airPtCount" placeholder="请输入空气质量监测点位数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="交通流量监测点位数" prop="trafPtCount">
            <el-input
              v-model.number="formData.trafPtCount"
              placeholder="请输入交通流量监测点位数"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="井盖状态监测点位数" prop="manholePtCount">
            <el-input
              v-model.number="formData.manholePtCount"
              placeholder="请输入井盖状态监测点位数"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="其他类型点位数" prop="otherPtCount">
            <el-input v-model.number="formData.otherPtCount" placeholder="请输入其他类型点位数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="新增点位数量" prop="newPtCount">
            <el-input v-model.number="formData.newPtCount" placeholder="请输入新增点位数量" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="停用点位数量" prop="disablePtCount">
            <el-input v-model.number="formData.disablePtCount" placeholder="请输入停用点位数量" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="点位覆盖率(%)" prop="ptCoverageRate">
            <el-input v-model.number="formData.ptCoverageRate" placeholder="请输入点位覆盖率" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="报表生成时间" prop="rptCreateTime" required>
            <el-date-picker
              v-model="formData.rptCreateTime"
              type="datetime"
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
  MonPtSummaryRptApi,
  MonPtSummaryRptVO
} from '@/api/dataHub/operationMonitor/compStatAnalysisReport/monptsummaryrpt'

/** 监测点位汇总报表 表单 */
defineOptions({ name: 'MonPtSummaryRptForm' })

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
  totalPtCount: undefined,
  airPtCount: undefined,
  trafPtCount: undefined,
  manholePtCount: undefined,
  otherPtCount: undefined,
  newPtCount: undefined,
  disablePtCount: undefined,
  ptCoverageRate: undefined,
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
  statCycleName: [{ required: true, message: '请输入统计周期名称', trigger: 'blur' }],
  adminCode: [{ required: true, message: '请输入行政区划代码', trigger: 'blur' }],
  adminName: [{ required: true, message: '请输入行政区划名称', trigger: 'blur' }],
  totalPtCount: [{ required: true, message: '请输入点位总数量', trigger: 'blur' }],
  rptCreateTime: [{ required: true, message: '请选择报表生成时间', trigger: 'change' }]
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
      formData.value = await MonPtSummaryRptApi.getMonPtSummaryRpt(id)
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
    const data = formData.value as unknown as MonPtSummaryRptVO
    if (formType.value === 'create') {
      await MonPtSummaryRptApi.createMonPtSummaryRpt(data)
      message.success(t('common.createSuccess'))
    } else {
      await MonPtSummaryRptApi.updateMonPtSummaryRpt(data)
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
    totalPtCount: undefined,
    airPtCount: undefined,
    trafPtCount: undefined,
    manholePtCount: undefined,
    otherPtCount: undefined,
    newPtCount: undefined,
    disablePtCount: undefined,
    ptCoverageRate: undefined,
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

::v-deep .el-form-item {
  margin-bottom: 15px;
}

::v-deep .el-form-item__label {
  font-weight: 500;
}
</style>
