<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="700px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="130px"
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
          <el-form-item label="应用场景大类ID" prop="appSceneMajorId" required>
            <el-input v-model="formData.appSceneMajorId" placeholder="请输入应用场景大类ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="应用场景大类名称" prop="appSceneMajorName" required>
            <el-input v-model="formData.appSceneMajorName" placeholder="请输入应用场景大类名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="应用场景中类ID" prop="appSceneMidId">
            <el-input v-model="formData.appSceneMidId" placeholder="请输入应用场景中类ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="应用场景中类名称" prop="appSceneMidName">
            <el-input v-model="formData.appSceneMidName" placeholder="请输入应用场景中类名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分域ID" prop="domId" required>
            <el-input v-model="formData.domId" placeholder="请输入分域ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分域名称" prop="domName" required>
            <el-input v-model="formData.domName" placeholder="请输入分域名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联设备总数" prop="totalDeviceCount" required>
            <el-input v-model.number="formData.totalDeviceCount" placeholder="请输入关联设备总数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="设备在线率(%)" prop="deviceOnlineRate">
            <el-input v-model.number="formData.deviceOnlineRate" placeholder="请输入设备在线率" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="场景事件总数" prop="sceneEvtCount">
            <el-input v-model.number="formData.sceneEvtCount" placeholder="请输入场景事件总数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="事件处置率(%)" prop="evtHandleRate">
            <el-input v-model.number="formData.evtHandleRate" placeholder="请输入事件处置率" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="资源调度次数" prop="resDispatchCount">
            <el-input v-model.number="formData.resDispatchCount" placeholder="请输入资源调度次数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="场景健康度(%)" prop="sceneHealthRate">
            <el-input v-model.number="formData.sceneHealthRate" placeholder="请输入场景健康度" />
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
  DomAppSceneMonRptApi,
  DomAppSceneMonRptVO
} from '@/api/dataHub/operationMonitor/divMonitorReport/domappscenemonrpt'

/** 应用场景分域监测报表 表单 */
defineOptions({ name: 'DomAppSceneMonRptForm' })

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
  appSceneMajorId: undefined,
  appSceneMajorName: undefined,
  appSceneMidId: undefined,
  appSceneMidName: undefined,
  appSceneMinorId: undefined,
  appSceneMinorName: undefined,
  domId: undefined,
  domName: undefined,
  totalDeviceCount: undefined,
  deviceOnlineRate: undefined,
  sceneEvtCount: undefined,
  evtHandleRate: undefined,
  resDispatchCount: undefined,
  sceneHealthRate: undefined,
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
  appSceneMajorId: [{ required: true, message: '请输入应用场景大类ID', trigger: 'blur' }],
  appSceneMajorName: [{ required: true, message: '请输入应用场景大类名称', trigger: 'blur' }],
  domId: [{ required: true, message: '请输入分域ID', trigger: 'blur' }],
  domName: [{ required: true, message: '请输入分域名称', trigger: 'blur' }],
  totalDeviceCount: [
    { required: true, message: '请输入关联设备总数', trigger: 'blur', type: 'number' }
  ],
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
      formData.value = await DomAppSceneMonRptApi.getDomAppSceneMonRpt(id)
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
    const data = formData.value as unknown as DomAppSceneMonRptVO
    if (formType.value === 'create') {
      await DomAppSceneMonRptApi.createDomAppSceneMonRpt(data)
      message.success(t('common.createSuccess'))
    } else {
      await DomAppSceneMonRptApi.updateDomAppSceneMonRpt(data)
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
    appSceneMajorId: undefined,
    appSceneMajorName: undefined,
    appSceneMidId: undefined,
    appSceneMidName: undefined,
    appSceneMinorId: undefined,
    appSceneMinorName: undefined,
    domId: undefined,
    domName: undefined,
    totalDeviceCount: undefined,
    deviceOnlineRate: undefined,
    sceneEvtCount: undefined,
    evtHandleRate: undefined,
    resDispatchCount: undefined,
    sceneHealthRate: undefined,
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

.el-collapse {
  margin-top: 10px;
  border: 1px solid #f0f2f5;
  border-radius: 4px;
}

.el-collapse-item__content {
  padding-bottom: 15px;
}
</style>
