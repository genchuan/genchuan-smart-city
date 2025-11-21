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
            <el-input
              v-model.number="formData.totalDeviceCount"
              placeholder="请输入设备总数"
              type="number"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="设备在线率(%)" prop="deviceOnlineRate">
            <el-input
              v-model.number="formData.deviceOnlineRate"
              placeholder="请输入设备在线率"
              type="number"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="数据上传总频次" prop="totalUploadFreq">
            <el-input
              v-model.number="formData.totalUploadFreq"
              placeholder="请输入数据上传总频次"
              type="number"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="数据上传达标率(%)" prop="uploadQualifiedRate">
            <el-input
              v-model.number="formData.uploadQualifiedRate"
              placeholder="请输入数据上传达标率"
              type="number"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="设备异常次数" prop="deviceAbnCount">
            <el-input
              v-model.number="formData.deviceAbnCount"
              placeholder="请输入设备异常次数"
              type="number"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="固件更新设备数" prop="firmwareUpdatedCount">
            <el-input
              v-model.number="formData.firmwareUpdatedCount"
              placeholder="请输入固件更新设备数"
              type="number"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="固件更新率(%)" prop="firmwareUpdateRate">
            <el-input
              v-model.number="formData.firmwareUpdateRate"
              placeholder="请输入固件更新率"
              type="number"
            />
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
            <el-input v-model.number="formData.rptCreateUser" placeholder="请输入报表生成人" />
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
  DomDeviceMonRptApi,
  DomDeviceMonRptVO
} from '@/api/dataHub/operationMonitor/divMonitorReport/domdevicemonrpt'

/** 设备分域监测报表 表单 */
defineOptions({ name: 'DomDeviceMonRptForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<DomDeviceMonRptVO>>({
  id: undefined,
  rptId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  adminCode: undefined,
  adminName: undefined,
  deviceMajorId: undefined,
  deviceMajorName: undefined,
  deviceMinorId: undefined,
  deviceMinorName: undefined,
  domId: undefined,
  domName: undefined,
  totalDeviceCount: undefined,
  deviceOnlineRate: undefined,
  totalUploadFreq: undefined,
  uploadQualifiedRate: undefined,
  deviceAbnCount: undefined,
  firmwareUpdatedCount: undefined,
  firmwareUpdateRate: undefined,
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
  adminName: [{ required: true, message: '请输入行政区划名称', trigger: 'blur' }],
  deviceMajorName: [{ required: true, message: '请输入设备大类名称', trigger: 'blur' }],
  domName: [{ required: true, message: '请输入分域名称', trigger: 'blur' }],
  totalDeviceCount: [{ required: true, type: 'number', message: '请输入设备总数', trigger: 'blur' }]
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
      const data = await DomDeviceMonRptApi.getDomDeviceMonRpt(id)
      formData.value = { ...data }
      // 转换时间格式为时间戳
      if (formData.value.rptCreateTime) {
        formData.value.rptCreateTime = new Date(formData.value.rptCreateTime).getTime()
      }
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
    const data = formData.value as unknown as DomDeviceMonRptVO
    if (formType.value === 'create') {
      await DomDeviceMonRptApi.createDomDeviceMonRpt(data)
      message.success(t('common.createSuccess'))
    } else {
      await DomDeviceMonRptApi.updateDomDeviceMonRpt(data)
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
    deviceMajorId: undefined,
    deviceMajorName: undefined,
    deviceMinorId: undefined,
    deviceMinorName: undefined,
    domId: undefined,
    domName: undefined,
    totalDeviceCount: undefined,
    deviceOnlineRate: undefined,
    totalUploadFreq: undefined,
    uploadQualifiedRate: undefined,
    deviceAbnCount: undefined,
    firmwareUpdatedCount: undefined,
    firmwareUpdateRate: undefined,
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

::v-deep .el-form-item__label {
  font-weight: 500;
  color: #666;
}

::v-deep .el-collapse-item__header {
  background-color: #f5f7fa;
  border-radius: 4px;
}

::v-deep .el-collapse {
  border: 1px solid #eee;
  border-radius: 4px;
  overflow: hidden;
}
</style>
