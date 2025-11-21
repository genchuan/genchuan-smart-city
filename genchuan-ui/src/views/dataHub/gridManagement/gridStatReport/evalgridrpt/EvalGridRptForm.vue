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
          <el-form-item label="统计ID" prop="evalStatId">
            <el-input v-model="formData.evalStatId" placeholder="请输入统计ID" />
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
          <el-form-item label="行政区划代码" prop="regionCode">
            <el-input v-model="formData.regionCode" placeholder="请输入行政区划代码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="行政区划名称" prop="regionName">
            <el-input v-model="formData.regionName" placeholder="请输入行政区划名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="评价网格类型(A/B/C)" prop="gridType">
            <el-select v-model="formData.gridType" placeholder="请选择评价网格类型">
              <el-option label="A" value="A" />
              <el-option label="B" value="B" />
              <el-option label="C" value="C" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联评价指标ID" prop="idxId">
            <el-input v-model="formData.idxId" placeholder="请输入关联评价指标ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联评价指标名称" prop="idxName">
            <el-input v-model="formData.idxName" placeholder="请输入关联评价指标名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="网格总数(个)" prop="totalCount">
            <el-input v-model.number="formData.totalCount" placeholder="请输入网格总数(个)" type="number" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="总面积(m²)" prop="totalArea">
            <el-input v-model.number="formData.totalArea" placeholder="请输入总面积(m²)" type="number" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="平均所含管理数(个)" prop="avgMgCount">
            <el-input v-model.number="formData.avgMgCount" placeholder="请输入平均所含管理数(个)" type="number" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="总所含管理网格数(个)" prop="totalMgCount">
            <el-input v-model.number="formData.totalMgCount" placeholder="请输入总所含管理网格数(个)" type="number" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="类型占比(%)" prop="typeRatio">
            <el-input v-model.number="formData.typeRatio" placeholder="请输入类型占比(%)" type="number" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="新增网格数(个)" prop="newCount">
            <el-input v-model.number="formData.newCount" placeholder="请输入新增网格数(个)" type="number" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="统计人" prop="statUserId">
            <el-input v-model="formData.statUserId" placeholder="请输入统计人" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="统计时间" prop="statTime">
            <el-date-picker
              v-model="formData.statTime"
              type="datetime"
              value-format="x"
              placeholder="选择统计时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="报表备注" prop="rptRemark">
            <el-input v-model="formData.rptRemark" placeholder="请输入报表备注" type="textarea"  />
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
import { EvalGridRptApi, EvalGridRptVO } from '@/api/dataHub/gridManagement/gridStatReport/evalgridrpt'

/** 评价网格统计 表单 */
defineOptions({ name: 'EvalGridRptForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  evalStatId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  regionCode: undefined,
  regionName: undefined,
  gridType: undefined,
  idxId: undefined,
  idxName: undefined,
  totalCount: undefined,
  totalArea: undefined,
  avgMgCount: undefined,
  totalMgCount: undefined,
  typeRatio: undefined,
  newCount: undefined,
  statUserId: undefined,
  statTime: undefined,
  rptRemark: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
})
const formRules = reactive({
  statCycle: [{ required: true, message: '统计周期不能为空', trigger: 'blur' }],
  statCycleName: [{ required: true, message: '统计周期名称不能为空', trigger: 'blur' }],
  regionCode: [{ required: true, message: '行政区划代码不能为空', trigger: 'blur' }],
  regionName: [{ required: true, message: '行政区划名称不能为空', trigger: 'blur' }],
  gridType: [{ required: true, message: '评价网格类型(A/B/C)不能为空', trigger: 'blur' }],
  totalCount: [{ required: true, message: '网格总数(个)不能为空', trigger: 'blur' }],
  totalArea: [{ required: true, message: '总面积(m²)不能为空', trigger: 'blur' }],
  avgMgCount: [{ required: true, message: '平均所含管理数(个)不能为空', trigger: 'blur' }],
  totalMgCount: [{ required: true, message: '总所含管理网格数(个)不能为空', trigger: 'blur' }],
  typeRatio: [{ required: true, message: '类型占比(%)不能为空', trigger: 'blur' }],
  statUserId: [{ required: true, message: '统计人不能为空', trigger: 'blur' }],
  statTime: [{ required: true, message: '统计时间不能为空', trigger: 'blur' }],
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
      formData.value = await EvalGridRptApi.getEvalGridRpt(id)
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
    const data = formData.value as unknown as EvalGridRptVO
    if (formType.value === 'create') {
      await EvalGridRptApi.createEvalGridRpt(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalGridRptApi.updateEvalGridRpt(data)
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
    evalStatId: undefined,
    statCycle: undefined,
    statCycleName: undefined,
    regionCode: undefined,
    regionName: undefined,
    gridType: undefined,
    idxId: undefined,
    idxName: undefined,
    totalCount: undefined,
    totalArea: undefined,
    avgMgCount: undefined,
    totalMgCount: undefined,
    typeRatio: undefined,
    newCount: undefined,
    statUserId: undefined,
    statTime: undefined,
    rptRemark: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined,
  }
  formRef.value?.resetFields()
}
</script>

<style scoped>
.form-container {
  background-color: #f9fafb;
  padding: 20px;
  border-radius: 8px;
}

::v-deep .el-form-item {
  margin-bottom: 15px;
}

::v-deep .el-dialog__body {
  padding: 15px 20px !important;
}
</style>
