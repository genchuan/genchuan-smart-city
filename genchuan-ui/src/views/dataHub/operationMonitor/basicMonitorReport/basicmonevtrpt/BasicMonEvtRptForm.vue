<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="650px">
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
          <el-form-item label="事件类型" prop="evtType" required>
            <el-input v-model="formData.evtType" placeholder="请输入事件类型" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="事件总数" prop="totalEvtCount" required>
            <el-input v-model.number="formData.totalEvtCount" placeholder="请输入事件总数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="待处置事件数" prop="pendEvtCount" required>
            <el-input v-model.number="formData.pendEvtCount" placeholder="请输入待处置事件数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="处置中事件数" prop="handlEvtCount" required>
            <el-input v-model.number="formData.handlEvtCount" placeholder="请输入处置中事件数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="已办结事件数" prop="completedEvtCount" required>
            <el-input
              v-model.number="formData.completedEvtCount"
              placeholder="请输入已办结事件数"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="事件办结率(%)" prop="evtCompleteRate" required>
            <el-input v-model.number="formData.evtCompleteRate" placeholder="请输入事件办结率(%)" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="平均处置时长(分钟)" prop="avgDealEndure">
            <el-input
              v-model.number="formData.avgDealEndure"
              placeholder="请输入平均处置时长(分钟)"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="报表生成时间" prop="rptCreateTime" required>
            <el-date-picker
              v-model="formData.rptCreateTime"
              type="date"
              value-format="x"
              placeholder="选择报表生成时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="报表生成人" prop="rptCreateUser" required>
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

        <!-- 扩展字段默认隐藏，可根据需要显示 -->
        <el-col :span="24" class="expand-fields">
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
  BasicMonEvtRptApi,
  BasicMonEvtRptVO
} from '@/api/dataHub/operationMonitor/basicMonitorReport/basicmonevtrpt'

/** 基础监测事件报 表单 */
defineOptions({ name: 'BasicMonEvtRptForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<BasicMonEvtRptVO>>({
  id: undefined,
  rptId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  adminCode: undefined,
  adminName: undefined,
  evtType: undefined,
  totalEvtCount: undefined,
  pendEvtCount: undefined,
  handlEvtCount: undefined,
  completedEvtCount: undefined,
  evtCompleteRate: undefined,
  avgDealEndure: undefined,
  rptCreateTime: undefined,
  rptCreateUser: undefined,
  rptRemark: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  statCycle: [{ required: true, message: '统计周期不能为空', trigger: 'blur' }],
  statCycleName: [{ required: true, message: '统计周期名称不能为空', trigger: 'blur' }],
  adminCode: [{ required: true, message: '行政区划代码不能为空', trigger: 'blur' }],
  adminName: [{ required: true, message: '行政区划名称不能为空', trigger: 'blur' }],
  evtType: [{ required: true, message: '事件类型不能为空', trigger: 'blur' }],
  totalEvtCount: [{ required: true, message: '事件总数不能为空', trigger: 'blur' }],
  pendEvtCount: [{ required: true, message: '待处置事件数不能为空', trigger: 'blur' }],
  handlEvtCount: [{ required: true, message: '处置中事件数不能为空', trigger: 'blur' }],
  completedEvtCount: [{ required: true, message: '已办结事件数不能为空', trigger: 'blur' }],
  evtCompleteRate: [{ required: true, message: '事件办结率(%)不能为空', trigger: 'blur' }],
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
      formData.value = await BasicMonEvtRptApi.getBasicMonEvtRpt(id)
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
    const data = formData.value as unknown as BasicMonEvtRptVO
    if (formType.value === 'create') {
      await BasicMonEvtRptApi.createBasicMonEvtRpt(data)
      message.success(t('common.createSuccess'))
    } else {
      await BasicMonEvtRptApi.updateBasicMonEvtRpt(data)
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
    evtType: undefined,
    totalEvtCount: undefined,
    pendEvtCount: undefined,
    handlEvtCount: undefined,
    completedEvtCount: undefined,
    evtCompleteRate: undefined,
    avgDealEndure: undefined,
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

.expand-fields {
  margin-top: 10px;
}

::v-deep .el-collapse-item__content {
  padding-top: 15px !important;
}
</style>
