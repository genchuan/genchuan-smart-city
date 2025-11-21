<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="60%">
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
          <el-form-item label="资产大类ID" prop="assetMajorId">
            <el-input v-model="formData.assetMajorId" placeholder="请输入资产大类ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="资产大类名称" prop="assetMajorName">
            <el-input v-model="formData.assetMajorName" placeholder="请输入资产大类名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="资产小类ID" prop="assetMinorId">
            <el-input v-model="formData.assetMinorId" placeholder="请输入资产小类ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="资产小类名称" prop="assetMinorName">
            <el-input v-model="formData.assetMinorName" placeholder="请输入资产小类名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分域ID" prop="domId">
            <el-input v-model="formData.domId" placeholder="请输入分域ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分域名称" prop="domName">
            <el-input v-model="formData.domName" placeholder="请输入分域名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="资产总数" prop="totalAssetCount">
            <el-input
              v-model="formData.totalAssetCount"
              placeholder="请输入资产总数"
              type="number"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="在用资产数" prop="inUseAssetCount">
            <el-input
              v-model="formData.inUseAssetCount"
              placeholder="请输入在用资产数"
              type="number"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="资产在用率" prop="assetInUseRate">
            <el-input
              v-model="formData.assetInUseRate"
              placeholder="请输入资产在用率"
              type="number"
              step="0.01"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="维护总次数" prop="totalMntCount">
            <el-input
              v-model="formData.totalMntCount"
              placeholder="请输入维护总次数"
              type="number"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="平均资产寿命" prop="avgAssetLife">
            <el-input
              v-model="formData.avgAssetLife"
              placeholder="请输入平均资产寿命"
              type="number"
              step="0.01"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="资产故障事件数" prop="assetFaultEvtCount">
            <el-input
              v-model="formData.assetFaultEvtCount"
              placeholder="请输入资产故障事件数"
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
  DomAssetMonRptApi,
  DomAssetMonRptVO
} from '@/api/dataHub/operationMonitor/divMonitorReport/domassetmonrpt'

/** 资产分域监测报表 表单 */
defineOptions({ name: 'DomAssetMonRptForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<DomAssetMonRptVO>>({
  id: undefined,
  rptId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  adminCode: undefined,
  adminName: undefined,
  assetMajorId: undefined,
  assetMajorName: undefined,
  assetMinorId: undefined,
  assetMinorName: undefined,
  domId: undefined,
  domName: undefined,
  totalAssetCount: undefined,
  inUseAssetCount: undefined,
  assetInUseRate: undefined,
  totalMntCount: undefined,
  avgAssetLife: undefined,
  assetFaultEvtCount: undefined,
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
      formData.value = await DomAssetMonRptApi.getDomAssetMonRpt(id)
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
    const data = formData.value as unknown as DomAssetMonRptVO
    if (formType.value === 'create') {
      await DomAssetMonRptApi.createDomAssetMonRpt(data)
      message.success(t('common.createSuccess'))
    } else {
      await DomAssetMonRptApi.updateDomAssetMonRpt(data)
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
    assetMajorId: undefined,
    assetMajorName: undefined,
    assetMinorId: undefined,
    assetMinorName: undefined,
    domId: undefined,
    domName: undefined,
    totalAssetCount: undefined,
    inUseAssetCount: undefined,
    assetInUseRate: undefined,
    totalMntCount: undefined,
    avgAssetLife: undefined,
    assetFaultEvtCount: undefined,
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
  background-color: #f9fafb;
  padding: 20px;
  border-radius: 8px;
}

::v-deep .el-form-item {
  margin-bottom: 15px;
}

::v-deep .el-form-item__label {
  font-weight: 500;
  color: #4b5563;
}

::v-deep .el-input,
::v-deep .el-date-picker {
  border-radius: 4px;
}
</style>
