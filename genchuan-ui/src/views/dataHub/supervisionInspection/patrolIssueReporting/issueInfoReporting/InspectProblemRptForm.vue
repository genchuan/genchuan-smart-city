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
          <el-form-item label="问题ID" prop="problemId">
            <el-input v-model="formData.problemId" placeholder="请输入问题ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="问题编码" prop="problemCode">
            <el-input v-model="formData.problemCode" placeholder="请输入问题编码" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="24">
          <el-form-item label="问题名称" prop="problemName">
            <el-input v-model="formData.problemName" placeholder="请输入问题名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="问题类型ID" prop="matterTypeId">
            <el-input v-model="formData.matterTypeId" placeholder="请输入问题类型ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="问题类型名称" prop="matterTypeName">
            <el-input v-model="formData.matterTypeName" placeholder="请输入问题类型名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="关联任务ID" prop="taskId">
            <el-input v-model="formData.taskId" placeholder="请输入关联任务ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联任务编码" prop="taskCode">
            <el-input v-model="formData.taskCode" placeholder="请输入关联任务编码" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="上报人员ID" prop="rptUserId">
            <el-input v-model="formData.rptUserId" placeholder="请输入上报人员ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="上报人员姓名" prop="rptUserName">
            <el-input v-model="formData.rptUserName" placeholder="请输入上报人员姓名" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="所属网格ID" prop="gridId">
            <el-input v-model="formData.gridId" placeholder="请输入所属网格ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属网格名称" prop="gridName">
            <el-input v-model="formData.gridName" placeholder="请输入所属网格名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="问题位置坐标X" prop="problemLocX">
            <el-input v-model="formData.problemLocX" placeholder="请输入问题位置坐标X" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="问题位置坐标Y" prop="problemLocY">
            <el-input v-model="formData.problemLocY" placeholder="请输入问题位置坐标Y" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="24">
          <el-form-item label="问题位置描述" prop="problemLocDesc">
            <el-input v-model="formData.problemLocDesc" placeholder="请输入问题位置描述" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="24">
          <el-form-item label="问题描述" prop="problemDesc">
            <el-input
              v-model="formData.problemDesc"
              placeholder="请输入问题描述"
              type="textarea"
              rows="3"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="24">
          <el-form-item label="问题照片URLs" prop="problemPhotoUrls">
            <el-input
              v-model="formData.problemPhotoUrls"
              placeholder="请输入问题照片URLs，多个用逗号分隔"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="上报时间" prop="rptTime">
            <el-date-picker
              v-model="formData.rptTime"
              type="datetime"
              value-format="x"
              placeholder="选择上报时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="问题状态" prop="problemStatus">
            <el-radio-group v-model="formData.problemStatus">
              <el-radio value="1">待处理</el-radio>
              <el-radio value="2">处理中</el-radio>
              <el-radio value="3">已解决</el-radio>
              <el-radio value="4">已驳回</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="24">
          <el-form-item label="驳回原因" prop="rejectReason">
            <el-input
              v-model="formData.rejectReason"
              placeholder="请输入驳回原因"
              type="textarea"
              rows="2"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="创建人" prop="bizCreateUser">
            <el-input v-model="formData.bizCreateUser" placeholder="请输入创建人" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="创建时间" prop="bizCreateTime">
            <el-date-picker
              v-model="formData.bizCreateTime"
              type="datetime"
              value-format="x"
              placeholder="选择创建时间"
            />
          </el-form-item>
        </el-col>
      </el-row>

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
      </el-row>

      <el-row :gutter="20">
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
  InspectProblemRptApi,
  InspectProblemRptVO
} from '@/api/dataHub/supervisionInspection/patrolIssueReporting/issueInfoReporting'

/** 巡查巡检问题上报记录 表单 */
defineOptions({ name: 'InspectProblemRptForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  problemId: undefined,
  problemCode: undefined,
  problemName: undefined,
  matterTypeId: undefined,
  matterTypeName: undefined,
  taskId: undefined,
  taskCode: undefined,
  rptUserId: undefined,
  rptUserName: undefined,
  gridId: undefined,
  gridName: undefined,
  problemLocX: undefined,
  problemLocY: undefined,
  problemLocDesc: undefined,
  problemDesc: undefined,
  problemPhotoUrls: undefined,
  rptTime: undefined,
  problemStatus: undefined,
  rejectReason: undefined,
  bizCreateUser: undefined,
  bizCreateTime: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  problemName: [{ required: true, message: '请输入问题名称', trigger: 'blur' }],
  problemStatus: [{ required: true, message: '请选择问题状态', trigger: 'change' }],
  rptTime: [{ required: true, message: '请选择上报时间', trigger: 'change' }]
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
      formData.value = await InspectProblemRptApi.getInspectProblemRpt(id)
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
    const data = formData.value as unknown as InspectProblemRptVO
    if (formType.value === 'create') {
      await InspectProblemRptApi.createInspectProblemRpt(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectProblemRptApi.updateInspectProblemRpt(data)
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
    problemId: undefined,
    problemCode: undefined,
    problemName: undefined,
    matterTypeId: undefined,
    matterTypeName: undefined,
    taskId: undefined,
    taskCode: undefined,
    rptUserId: undefined,
    rptUserName: undefined,
    gridId: undefined,
    gridName: undefined,
    problemLocX: undefined,
    problemLocY: undefined,
    problemLocDesc: undefined,
    problemDesc: undefined,
    problemPhotoUrls: undefined,
    rptTime: undefined,
    problemStatus: undefined,
    rejectReason: undefined,
    bizCreateUser: undefined,
    bizCreateTime: undefined,
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
}

::v-deep .el-form-item {
  margin-bottom: 15px;
}

::v-deep .el-dialog__body {
  padding: 15px 20px;
  max-height: 70vh;
  overflow-y: auto;
}
</style>
