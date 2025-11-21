<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="60%">
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
          <el-form-item label="监测事件大类ID" prop="monEvtMajorId">
            <el-input v-model="formData.monEvtMajorId" placeholder="请输入监测事件大类ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="监测事件大类名称" prop="monEvtMajorName">
            <el-input v-model="formData.monEvtMajorName" placeholder="请输入监测事件大类名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="监测事件小类ID" prop="monEvtMinorId">
            <el-input v-model="formData.monEvtMinorId" placeholder="请输入监测事件小类ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="监测事件小类名称" prop="monEvtMinorName">
            <el-input v-model="formData.monEvtMinorName" placeholder="请输入监测事件小类名称" />
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
          <el-form-item label="事件总数" prop="totalEvtCount">
            <el-input v-model.number="formData.totalEvtCount" placeholder="请输入事件总数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="高发区域代码" prop="highIncidenceCode">
            <el-input v-model="formData.highIncidenceCode" placeholder="请输入高发区域代码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="高发区域名称" prop="highIncidenceName">
            <el-input v-model="formData.highIncidenceName" placeholder="请输入高发区域名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="高发区域事件占比" prop="highIncidenceRatio">
            <el-input
              v-model.number="formData.highIncidenceRatio"
              placeholder="请输入高发区域事件占比"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="事件办结率" prop="evtCompleteRate">
            <el-input v-model.number="formData.evtCompleteRate" placeholder="请输入事件办结率" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="平均响应时长" prop="avgRespEndure">
            <el-input v-model.number="formData.avgRespEndure" placeholder="请输入平均响应时长" />
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
  DomMonEvtMonRptApi,
  DomMonEvtMonRptVO
} from '@/api/dataHub/operationMonitor/divMonitorReport/dommonevtmonrpt'

/** 监测事件分域监测报表 表单 */
defineOptions({ name: 'DomMonEvtMonRptForm' })

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
  monEvtMajorId: undefined,
  monEvtMajorName: undefined,
  monEvtMinorId: undefined,
  monEvtMinorName: undefined,
  domId: undefined,
  domName: undefined,
  totalEvtCount: undefined,
  highIncidenceCode: undefined,
  highIncidenceName: undefined,
  highIncidenceRatio: undefined,
  evtCompleteRate: undefined,
  avgRespEndure: undefined,
  rptCreateTime: undefined,
  rptCreateUser: undefined,
  rptRemark: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  rptId: [{ required: true, message: '报表ID不能为空', trigger: 'blur' }],
  statCycle: [{ required: true, message: '统计周期不能为空', trigger: 'blur' }],
  statCycleName: [{ required: true, message: '统计周期名称不能为空', trigger: 'blur' }],
  adminCode: [{ required: true, message: '行政区划代码不能为空', trigger: 'blur' }],
  adminName: [{ required: true, message: '行政区划名称不能为空', trigger: 'blur' }],
  monEvtMajorId: [{ required: true, message: '监测事件大类ID不能为空', trigger: 'blur' }],
  monEvtMajorName: [{ required: true, message: '监测事件大类名称不能为空', trigger: 'blur' }],
  monEvtMinorId: [{ required: true, message: '监测事件小类ID不能为空', trigger: 'blur' }],
  monEvtMinorName: [{ required: true, message: '监测事件小类名称不能为空', trigger: 'blur' }],
  domId: [{ required: true, message: '分域ID不能为空', trigger: 'blur' }],
  domName: [{ required: true, message: '分域名称不能为空', trigger: 'blur' }],
  totalEvtCount: [{ required: true, message: '事件总数不能为空', trigger: 'blur' }],
  evtCompleteRate: [{ required: true, message: '事件办结率不能为空', trigger: 'blur' }],
  rptCreateTime: [{ required: true, message: '报表生成时间不能为空', trigger: 'blur' }],
  rptCreateUser: [{ required: true, message: '报表生成人不能为空', trigger: 'blur' }]
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
      formData.value = await DomMonEvtMonRptApi.getDomMonEvtMonRpt(id)
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
    const data = formData.value as unknown as DomMonEvtMonRptVO
    if (formType.value === 'create') {
      await DomMonEvtMonRptApi.createDomMonEvtMonRpt(data)
      message.success(t('common.createSuccess'))
    } else {
      await DomMonEvtMonRptApi.updateDomMonEvtMonRpt(data)
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
    monEvtMajorId: undefined,
    monEvtMajorName: undefined,
    monEvtMinorId: undefined,
    monEvtMinorName: undefined,
    domId: undefined,
    domName: undefined,
    totalEvtCount: undefined,
    highIncidenceCode: undefined,
    highIncidenceName: undefined,
    highIncidenceRatio: undefined,
    evtCompleteRate: undefined,
    avgRespEndure: undefined,
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
  background-color: #fff;
  border-radius: 8px;
}

::v-deep .el-form-item {
  margin-bottom: 15px;
}

::v-deep .el-input__wrapper {
  border-radius: 6px;
}
</style>
