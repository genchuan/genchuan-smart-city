<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" :width="700">
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
          <el-form-item label="监测部件大类ID" prop="monCompMajorId">
            <el-input v-model="formData.monCompMajorId" placeholder="请输入监测部件大类ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="监测部件大类名称" prop="monCompMajorName">
            <el-input v-model="formData.monCompMajorName" placeholder="请输入监测部件大类名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="监测部件小类ID" prop="monCompMinorId">
            <el-input v-model="formData.monCompMinorId" placeholder="请输入监测部件小类ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="监测部件小类名称" prop="monCompMinorName">
            <el-input v-model="formData.monCompMinorName" placeholder="请输入监测部件小类名称" />
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
          <el-form-item label="设备总数" prop="totalDeviceCount">
            <el-input v-model.number="formData.totalDeviceCount" placeholder="请输入设备总数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="设备在线率(%)" prop="deviceOnlineRate">
            <el-input v-model.number="formData.deviceOnlineRate" placeholder="请输入设备在线率" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="数据上传总数" prop="totalUploadCount">
            <el-input v-model.number="formData.totalUploadCount" placeholder="请输入数据上传总数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="数据上传达标率(%)" prop="uploadQualifiedRate">
            <el-input
              v-model.number="formData.uploadQualifiedRate"
              placeholder="请输入数据上传达标率"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="故障事件数" prop="faultEvtCount">
            <el-input v-model.number="formData.faultEvtCount" placeholder="请输入故障事件数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="校准次数" prop="calibrateCount">
            <el-input v-model.number="formData.calibrateCount" placeholder="请输入校准次数" />
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
  DomMonCompMonRptApi,
  DomMonCompMonRptVO
} from '@/api/dataHub/operationMonitor/divMonitorReport/dommoncompmonrpt'

/** 监测部件分域监测报表 表单 */
defineOptions({ name: 'DomMonCompMonRptForm' })

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
  monCompMajorId: undefined,
  monCompMajorName: undefined,
  monCompMinorId: undefined,
  monCompMinorName: undefined,
  domId: undefined,
  domName: undefined,
  totalDeviceCount: undefined,
  deviceOnlineRate: undefined,
  totalUploadCount: undefined,
  uploadQualifiedRate: undefined,
  faultEvtCount: undefined,
  calibrateCount: undefined,
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
  monCompMajorId: [{ required: true, message: '监测部件大类ID不能为空', trigger: 'blur' }],
  monCompMajorName: [{ required: true, message: '监测部件大类名称不能为空', trigger: 'blur' }],
  monCompMinorId: [{ required: true, message: '监测部件小类ID不能为空', trigger: 'blur' }],
  monCompMinorName: [{ required: true, message: '监测部件小类名称不能为空', trigger: 'blur' }],
  domId: [{ required: true, message: '分域ID不能为空', trigger: 'blur' }],
  domName: [{ required: true, message: '分域名称不能为空', trigger: 'blur' }],
  totalDeviceCount: [{ required: true, message: '设备总数不能为空', trigger: 'blur' }],
  deviceOnlineRate: [{ required: true, message: '设备在线率不能为空', trigger: 'blur' }],
  totalUploadCount: [{ required: true, message: '数据上传总数不能为空', trigger: 'blur' }],
  uploadQualifiedRate: [{ required: true, message: '数据上传达标率不能为空', trigger: 'blur' }],
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
      formData.value = await DomMonCompMonRptApi.getDomMonCompMonRpt(id)
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
    const data = formData.value as unknown as DomMonCompMonRptVO
    if (formType.value === 'create') {
      await DomMonCompMonRptApi.createDomMonCompMonRpt(data)
      message.success(t('common.createSuccess'))
    } else {
      await DomMonCompMonRptApi.updateDomMonCompMonRpt(data)
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
    monCompMajorId: undefined,
    monCompMajorName: undefined,
    monCompMinorId: undefined,
    monCompMinorName: undefined,
    domId: undefined,
    domName: undefined,
    totalDeviceCount: undefined,
    deviceOnlineRate: undefined,
    totalUploadCount: undefined,
    uploadQualifiedRate: undefined,
    faultEvtCount: undefined,
    calibrateCount: undefined,
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
  border-radius: 4px;
}

::v-deep .el-form-item__label {
  font-weight: 500;
  color: #4e5969;
}
</style>
