<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="600px">
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
          <el-form-item label="统计ID" prop="catStatId">
            <el-input v-model="formData.catStatId" placeholder="请输入统计ID" />
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
          <el-form-item label="设备中类ID" prop="deviceMidId">
            <el-input v-model="formData.deviceMidId" placeholder="请输入设备中类ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="设备中类名称" prop="deviceMidName">
            <el-input v-model="formData.deviceMidName" placeholder="请输入设备中类名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="设备小类ID" prop="deviceMinorId">
            <el-input v-model="formData.deviceMinorId" placeholder="请输入设备小类ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="设备小类名称" prop="deviceMinorName">
            <el-input v-model="formData.deviceMinorName" placeholder="请输入设备小类名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="设备总数(台)" prop="totalDeviceCount">
            <el-input v-model.number="formData.totalDeviceCount" placeholder="请输入设备总数(台)" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="在线设备数(台)" prop="onlineDeviceCount">
            <el-input
              v-model.number="formData.onlineDeviceCount"
              placeholder="请输入在线设备数(台)"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="离线设备数(台)" prop="offlineDeviceCount">
            <el-input
              v-model.number="formData.offlineDeviceCount"
              placeholder="请输入离线设备数(台)"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="故障设备数(台)" prop="faultDeviceCount">
            <el-input
              v-model.number="formData.faultDeviceCount"
              placeholder="请输入故障设备数(台)"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="新增设备数(台)" prop="newDeviceCount">
            <el-input v-model.number="formData.newDeviceCount" placeholder="请输入新增设备数(台)" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="报废设备数(台)" prop="scrappedDeviceCount">
            <el-input
              v-model.number="formData.scrappedDeviceCount"
              placeholder="请输入报废设备数(台)"
            />
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

        <!-- 折叠面板展示扩展字段 -->
        <el-col :span="24">
          <el-collapse>
            <el-collapse-item title="扩展字段">
              <el-row :gutter="20">
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
  DeviceCatRptApi,
  DeviceCatRptVO
} from '@/api/dataHub/deviceManage/deviceStatisticsReport/devicecatrpt'

/** 设备分类统计 表单 */
defineOptions({ name: 'DeviceCatRptForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  catStatId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  regionCode: undefined,
  regionName: undefined,
  deviceMajorId: undefined,
  deviceMajorName: undefined,
  deviceMidId: undefined,
  deviceMidName: undefined,
  deviceMinorId: undefined,
  deviceMinorName: undefined,
  totalDeviceCount: undefined,
  onlineDeviceCount: undefined,
  offlineDeviceCount: undefined,
  faultDeviceCount: undefined,
  newDeviceCount: undefined,
  scrappedDeviceCount: undefined,
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
  totalDeviceCount: [{ required: true, message: '设备总数(台)不能为空', trigger: 'blur' }],
  onlineDeviceCount: [{ required: true, message: '在线设备数(台)不能为空', trigger: 'blur' }],
  offlineDeviceCount: [{ required: true, message: '离线设备数(台)不能为空', trigger: 'blur' }],
  faultDeviceCount: [{ required: true, message: '故障设备数(台)不能为空', trigger: 'blur' }],
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
      formData.value = await DeviceCatRptApi.getDeviceCatRpt(id)
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
    const data = formData.value as unknown as DeviceCatRptVO
    if (formType.value === 'create') {
      await DeviceCatRptApi.createDeviceCatRpt(data)
      message.success(t('common.createSuccess'))
    } else {
      await DeviceCatRptApi.updateDeviceCatRpt(data)
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
    catStatId: undefined,
    statCycle: undefined,
    statCycleName: undefined,
    regionCode: undefined,
    regionName: undefined,
    deviceMajorId: undefined,
    deviceMajorName: undefined,
    deviceMidId: undefined,
    deviceMidName: undefined,
    deviceMinorId: undefined,
    deviceMinorName: undefined,
    totalDeviceCount: undefined,
    onlineDeviceCount: undefined,
    offlineDeviceCount: undefined,
    faultDeviceCount: undefined,
    newDeviceCount: undefined,
    scrappedDeviceCount: undefined,
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
  padding: 10px 0;
}

::v-deep .el-form-item {
  margin-bottom: 15px;
}

::v-deep .el-collapse {
  margin-top: 10px;
}

::v-deep .el-collapse-item__content {
  padding-bottom: 5px !important;
}
</style>
