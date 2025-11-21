<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="700px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
      class="custom-form"
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
          <el-form-item label="网格类型" prop="gridType">
            <el-select v-model="formData.gridType" placeholder="请选择网格类型">
              <el-option label="请选择字典生成" value="" />
            </el-select>
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
          <el-form-item label="网格编码" prop="gridCode">
            <el-input v-model="formData.gridCode" placeholder="请输入网格编码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="网格员姓名" prop="gridUserName">
            <el-input v-model="formData.gridUserName" placeholder="请输入网格员姓名" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="监测点位数量" prop="monPtCount">
            <el-input v-model.number="formData.monPtCount" placeholder="请输入监测点位数量" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="设备在线率(%)" prop="deviceOnlineRate">
            <el-input v-model.number="formData.deviceOnlineRate" placeholder="请输入设备在线率" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="事件总数" prop="evtTotalCount">
            <el-input v-model.number="formData.evtTotalCount" placeholder="请输入事件总数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="事件办结率(%)" prop="evtCompleteRate">
            <el-input v-model.number="formData.evtCompleteRate" placeholder="请输入事件办结率" />
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
  DomGridMonRptApi,
  DomGridMonRptVO
} from '@/api/dataHub/operationMonitor/divMonitorReport/domgridmonrpt'

/** 网格分域监测报表 表单 */
defineOptions({ name: 'DomGridMonRptForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<DomGridMonRptVO>>({
  id: undefined,
  rptId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  adminCode: undefined,
  adminName: undefined,
  gridType: undefined,
  domId: undefined,
  domName: undefined,
  gridCode: undefined,
  gridUserName: undefined,
  monPtCount: undefined,
  deviceOnlineRate: undefined,
  evtTotalCount: undefined,
  evtCompleteRate: undefined,
  rptCreateTime: undefined,
  rptCreateUser: undefined,
  rptRemark: undefined
})
const formRules = reactive({
  rptId: [{ required: true, message: '报表ID不能为空', trigger: 'blur' }],
  statCycle: [{ required: true, message: '统计周期不能为空', trigger: 'blur' }],
  statCycleName: [{ required: true, message: '统计周期名称不能为空', trigger: 'blur' }],
  adminCode: [{ required: true, message: '行政区划代码不能为空', trigger: 'blur' }],
  adminName: [{ required: true, message: '行政区划名称不能为空', trigger: 'blur' }],
  gridType: [{ required: true, message: '网格类型不能为空', trigger: 'change' }],
  domId: [{ required: true, message: '分域ID不能为空', trigger: 'blur' }],
  domName: [{ required: true, message: '分域名称不能为空', trigger: 'blur' }],
  gridCode: [{ required: true, message: '网格编码不能为空', trigger: 'blur' }],
  gridUserName: [{ required: true, message: '网格员姓名不能为空', trigger: 'blur' }],
  monPtCount: [
    { required: true, message: '监测点位数量不能为空', trigger: 'blur' },
    { type: 'number', message: '请输入数字', trigger: 'blur' }
  ],
  deviceOnlineRate: [
    { required: true, message: '设备在线率不能为空', trigger: 'blur' },
    { type: 'number', message: '请输入数字', trigger: 'blur' }
  ],
  evtTotalCount: [
    { required: true, message: '事件总数不能为空', trigger: 'blur' },
    { type: 'number', message: '请输入数字', trigger: 'blur' }
  ],
  evtCompleteRate: [
    { required: true, message: '事件办结率不能为空', trigger: 'blur' },
    { type: 'number', message: '请输入数字', trigger: 'blur' }
  ],
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
      formData.value = await DomGridMonRptApi.getDomGridMonRpt(id)
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
    const data = formData.value as unknown as DomGridMonRptVO
    if (formType.value === 'create') {
      await DomGridMonRptApi.createDomGridMonRpt(data)
      message.success(t('common.createSuccess'))
    } else {
      await DomGridMonRptApi.updateDomGridMonRpt(data)
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
    gridType: undefined,
    domId: undefined,
    domName: undefined,
    gridCode: undefined,
    gridUserName: undefined,
    monPtCount: undefined,
    deviceOnlineRate: undefined,
    evtTotalCount: undefined,
    evtCompleteRate: undefined,
    rptCreateTime: undefined,
    rptCreateUser: undefined,
    rptRemark: undefined
  }
  formRef.value?.resetFields()
}
</script>

<style scoped>
.custom-form {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
}

::v-deep .el-form-item__label {
  color: #4b5563;
  font-weight: 500;
}

::v-deep .el-input__wrapper {
  border-radius: 6px;
}

::v-deep .el-select__wrapper {
  border-radius: 6px;
}
</style>
