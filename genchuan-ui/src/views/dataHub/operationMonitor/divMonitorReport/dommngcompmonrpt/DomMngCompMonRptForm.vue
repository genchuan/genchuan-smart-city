<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="700px">
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
          <el-form-item label="管理部件大类ID" prop="mngCompMajorId">
            <el-input v-model="formData.mngCompMajorId" placeholder="请输入管理部件大类ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="管理部件大类名称" prop="mngCompMajorName">
            <el-input v-model="formData.mngCompMajorName" placeholder="请输入管理部件大类名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="管理部件小类ID" prop="mngCompMinorId">
            <el-input v-model="formData.mngCompMinorId" placeholder="请输入管理部件小类ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="管理部件小类名称" prop="mngCompMinorName">
            <el-input v-model="formData.mngCompMinorName" placeholder="请输入管理部件小类名称" />
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
          <el-form-item label="关联点位数量" prop="relPtCount">
            <el-input
              v-model="formData.relPtCount"
              placeholder="请输入关联点位数量"
              type="number"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联设备数量" prop="relDeviceCount">
            <el-input
              v-model="formData.relDeviceCount"
              placeholder="请输入关联设备数量"
              type="number"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="设备在线率(%)" prop="deviceOnlineRate">
            <el-input
              v-model="formData.deviceOnlineRate"
              placeholder="请输入设备在线率"
              type="number"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="部件故障事件数" prop="compFaultEvtCount">
            <el-input
              v-model="formData.compFaultEvtCount"
              placeholder="请输入部件故障事件数"
              type="number"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="故障修复率(%)" prop="faultRepairRate">
            <el-input
              v-model="formData.faultRepairRate"
              placeholder="请输入故障修复率"
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
  DomMngCompMonRptApi,
  DomMngCompMonRptVO
} from '@/api/dataHub/operationMonitor/divMonitorReport/dommngcompmonrpt'

/** 管理部件分域监测报表 表单 */
defineOptions({ name: 'DomMngCompMonRptForm' })

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
  mngCompMajorId: undefined,
  mngCompMajorName: undefined,
  mngCompMinorId: undefined,
  mngCompMinorName: undefined,
  domId: undefined,
  domName: undefined,
  relPtCount: undefined,
  relDeviceCount: undefined,
  deviceOnlineRate: undefined,
  compFaultEvtCount: undefined,
  faultRepairRate: undefined,
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
  mngCompMajorId: [{ required: true, message: '管理部件大类ID不能为空', trigger: 'blur' }],
  mngCompMajorName: [{ required: true, message: '管理部件大类名称不能为空', trigger: 'blur' }],
  mngCompMinorId: [{ required: true, message: '管理部件小类ID不能为空', trigger: 'blur' }],
  mngCompMinorName: [{ required: true, message: '管理部件小类名称不能为空', trigger: 'blur' }],
  domId: [{ required: true, message: '分域ID不能为空', trigger: 'blur' }],
  domName: [{ required: true, message: '分域名称不能为空', trigger: 'blur' }],
  relPtCount: [{ required: true, message: '关联点位数量不能为空', trigger: 'blur' }],
  relDeviceCount: [{ required: true, message: '关联设备数量不能为空', trigger: 'blur' }],
  deviceOnlineRate: [{ required: true, message: '设备在线率不能为空', trigger: 'blur' }],
  compFaultEvtCount: [{ required: true, message: '部件故障事件数不能为空', trigger: 'blur' }],
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
      formData.value = await DomMngCompMonRptApi.getDomMngCompMonRpt(id)
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
    const data = formData.value as unknown as DomMngCompMonRptVO
    if (formType.value === 'create') {
      await DomMngCompMonRptApi.createDomMngCompMonRpt(data)
      message.success(t('common.createSuccess'))
    } else {
      await DomMngCompMonRptApi.updateDomMngCompMonRpt(data)
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
    mngCompMajorId: undefined,
    mngCompMajorName: undefined,
    mngCompMinorId: undefined,
    mngCompMinorName: undefined,
    domId: undefined,
    domName: undefined,
    relPtCount: undefined,
    relDeviceCount: undefined,
    deviceOnlineRate: undefined,
    compFaultEvtCount: undefined,
    faultRepairRate: undefined,
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

::v-deep .el-collapse {
  margin-top: 10px;
  border: 1px solid #f0f0f0;
  border-radius: 4px;
}

::v-deep .el-collapse-item__content {
  padding-bottom: 15px;
}
</style>
