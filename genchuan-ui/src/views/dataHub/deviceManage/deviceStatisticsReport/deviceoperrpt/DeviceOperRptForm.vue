<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="650px">
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
          <el-form-item label="统计ID" prop="operStatId">
            <el-input v-model="formData.operStatId" placeholder="请输入统计ID" />
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
          <el-form-item label="设备大类ID" prop="deviceMajorId">
            <el-input v-model="formData.deviceMajorId" placeholder="请输入设备大类ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="设备大类名称" prop="deviceMajorName">
            <el-input v-model="formData.deviceMajorName" placeholder="请输入设备大类名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="设备总数量(台)" prop="totalDeviceCount">
            <el-input
              v-model.number="formData.totalDeviceCount"
              placeholder="请输入设备总数量(台)"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="平均在线设备数(台)" prop="avgOnlineCount">
            <el-input
              v-model.number="formData.avgOnlineCount"
              placeholder="请输入平均在线设备数(台)"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="设备在线率(%)" prop="deviceOnlineRate">
            <el-input
              v-model.number="formData.deviceOnlineRate"
              placeholder="请输入设备在线率(%)"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="总离线时长(分钟)" prop="totalOfflineEndure">
            <el-input
              v-model.number="formData.totalOfflineEndure"
              placeholder="请输入总离线时长(分钟)"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="平均离线时长(分钟)" prop="avgOfflineEndure">
            <el-input
              v-model.number="formData.avgOfflineEndure"
              placeholder="请输入平均离线时长(分钟)"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="一般预警数(条)" prop="generalAlertCount">
            <el-input
              v-model.number="formData.generalAlertCount"
              placeholder="请输入一般预警数(条)"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="重要预警数(条)" prop="importantAlertCount">
            <el-input
              v-model.number="formData.importantAlertCount"
              placeholder="请输入重要预警数(条)"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="紧急预警数(条)" prop="urgentAlertCount">
            <el-input
              v-model.number="formData.urgentAlertCount"
              placeholder="请输入紧急预警数(条)"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="已处理预警数(条)" prop="handledAlertCount">
            <el-input
              v-model.number="formData.handledAlertCount"
              placeholder="请输入已处理预警数(条)"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预警处置率(%)" prop="alertHandleRate">
            <el-input v-model.number="formData.alertHandleRate" placeholder="请输入预警处置率(%)" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="统计时间" prop="statTime">
            <el-date-picker
              v-model="formData.statTime"
              type="date"
              value-format="x"
              placeholder="选择统计时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="统计人" prop="statUser">
            <el-input v-model="formData.statUser" placeholder="请输入统计人" />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="报表备注" prop="rptRemark">
            <el-input v-model="formData.rptRemark" placeholder="请输入报表备注" type="textarea" />
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
  DeviceOperRptApi,
  DeviceOperRptVO
} from '@/api/dataHub/deviceManage/deviceStatisticsReport/deviceoperrpt'

/** 设备运行状态统计 表单 */
defineOptions({ name: 'DeviceOperRptForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  operStatId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  regionCode: undefined,
  regionName: undefined,
  deviceMajorId: undefined,
  deviceMajorName: undefined,
  totalDeviceCount: undefined,
  avgOnlineCount: undefined,
  deviceOnlineRate: undefined,
  totalOfflineEndure: undefined,
  avgOfflineEndure: undefined,
  generalAlertCount: undefined,
  importantAlertCount: undefined,
  urgentAlertCount: undefined,
  handledAlertCount: undefined,
  alertHandleRate: undefined,
  statTime: undefined,
  statUser: undefined,
  rptRemark: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  statCycle: [{ required: true, message: '统计周期不能为空', trigger: 'blur' }],
  statCycleName: [{ required: true, message: '统计周期名称不能为空', trigger: 'blur' }],
  regionCode: [{ required: true, message: '行政区划代码不能为空', trigger: 'blur' }],
  regionName: [{ required: true, message: '行政区划名称不能为空', trigger: 'blur' }],
  deviceMajorId: [{ required: true, message: '设备大类ID不能为空', trigger: 'blur' }],
  deviceMajorName: [{ required: true, message: '设备大类名称不能为空', trigger: 'blur' }],
  totalDeviceCount: [{ required: true, message: '设备总数量(台)不能为空', trigger: 'blur' }],
  avgOnlineCount: [{ required: true, message: '平均在线设备数(台)不能为空', trigger: 'blur' }],
  deviceOnlineRate: [{ required: true, message: '设备在线率(%)不能为空', trigger: 'blur' }],
  totalOfflineEndure: [{ required: true, message: '总离线时长(分钟)不能为空', trigger: 'blur' }],
  avgOfflineEndure: [{ required: true, message: '平均离线时长(分钟)不能为空', trigger: 'blur' }],
  generalAlertCount: [{ required: true, message: '一般预警数(条)不能为空', trigger: 'blur' }],
  importantAlertCount: [{ required: true, message: '重要预警数(条)不能为空', trigger: 'blur' }],
  urgentAlertCount: [{ required: true, message: '紧急预警数(条)不能为空', trigger: 'blur' }],
  handledAlertCount: [{ required: true, message: '已处理预警数(条)不能为空', trigger: 'blur' }],
  alertHandleRate: [{ required: true, message: '预警处置率(%)不能为空', trigger: 'blur' }],
  statTime: [{ required: true, message: '统计时间不能为空', trigger: 'blur' }],
  statUser: [{ required: true, message: '统计人不能为空', trigger: 'blur' }]
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
      formData.value = await DeviceOperRptApi.getDeviceOperRpt(id)
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
    const data = formData.value as unknown as DeviceOperRptVO
    if (formType.value === 'create') {
      await DeviceOperRptApi.createDeviceOperRpt(data)
      message.success(t('common.createSuccess'))
    } else {
      await DeviceOperRptApi.updateDeviceOperRpt(data)
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
    operStatId: undefined,
    statCycle: undefined,
    statCycleName: undefined,
    regionCode: undefined,
    regionName: undefined,
    deviceMajorId: undefined,
    deviceMajorName: undefined,
    totalDeviceCount: undefined,
    avgOnlineCount: undefined,
    deviceOnlineRate: undefined,
    totalOfflineEndure: undefined,
    avgOfflineEndure: undefined,
    generalAlertCount: undefined,
    importantAlertCount: undefined,
    urgentAlertCount: undefined,
    handledAlertCount: undefined,
    alertHandleRate: undefined,
    statTime: undefined,
    statUser: undefined,
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
  background-color: #f9fafb;
  border-radius: 8px;
}

::v-deep .el-form-item {
  margin-bottom: 15px;
}

::v-deep .el-input__wrapper {
  border-radius: 6px;
}

::v-deep .el-textarea__inner {
  border-radius: 6px;
}
</style>
