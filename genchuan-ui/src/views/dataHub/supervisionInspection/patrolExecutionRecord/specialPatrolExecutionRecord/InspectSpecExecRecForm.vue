<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="700px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="140px"
      v-loading="formLoading"
      class="form-container"
    >
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="记录ID" prop="specExecId">
            <el-input v-model="formData.specExecId" placeholder="请输入记录ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="记录编码" prop="specExecCode">
            <el-input v-model="formData.specExecCode" placeholder="请输入记录编码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联专项计划ID" prop="specPlanId">
            <el-input v-model="formData.specPlanId" placeholder="请输入关联专项计划ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联专项计划名称" prop="specPlanName">
            <el-input v-model="formData.specPlanName" placeholder="请输入关联专项计划名称" />
          </el-form-item>
        </el-col>
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
        <el-col :span="12">
          <el-form-item label="执行人员ID" prop="personId">
            <el-input v-model="formData.personId" placeholder="请输入执行人员ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="执行人员姓名" prop="personName">
            <el-input v-model="formData.personName" placeholder="请输入执行人员姓名" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联标准ID" prop="stdId">
            <el-input v-model="formData.stdId" placeholder="请输入关联标准ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联标准名称" prop="stdName">
            <el-input v-model="formData.stdName" placeholder="请输入关联标准名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="执行开始时间" prop="execStartTime">
            <el-date-picker
              v-model="formData.execStartTime"
              type="datetime"
              value-format="x"
              placeholder="选择执行开始时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="执行完成时间" prop="execCompleteTime">
            <el-date-picker
              v-model="formData.execCompleteTime"
              type="datetime"
              value-format="x"
              placeholder="选择执行完成时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="不合格项数量" prop="unqualifiedCount">
            <el-input v-model.number="formData.unqualifiedCount" placeholder="请输入不合格项数量" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="执行评分" prop="execScore">
            <el-input v-model.number="formData.execScore" placeholder="请输入执行评分" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="执行设备编码" prop="deviceCode">
            <el-input v-model="formData.deviceCode" placeholder="请输入执行设备编码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="执行地点坐标X" prop="execLocX">
            <el-input v-model.number="formData.execLocX" placeholder="请输入执行地点坐标X" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="执行地点坐标Y" prop="execLocY">
            <el-input v-model.number="formData.execLocY" placeholder="请输入执行地点坐标Y" />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="检查项执行明细" prop="ckItemDetail">
            <el-input
              v-model="formData.ckItemDetail"
              placeholder="请输入检查项执行明细"
              type="textarea"
              rows="3"
            />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="不合格项详情" prop="unqualifiedDetail">
            <el-input
              v-model="formData.unqualifiedDetail"
              placeholder="请输入不合格项详情"
              type="textarea"
              rows="3"
            />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="专项执行备注" prop="specExecRemark">
            <el-input
              v-model="formData.specExecRemark"
              placeholder="请输入专项执行备注"
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
  InspectSpecExecRecApi,
  InspectSpecExecRecVO
} from '@/api/dataHub/supervisionInspection/patrolExecutionRecord/specialPatrolExecutionRecord'

/** 专项巡查执行记录 表单 */
defineOptions({ name: 'InspectSpecExecRecForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  specExecId: undefined,
  specExecCode: undefined,
  specPlanId: undefined,
  specPlanName: undefined,
  taskId: undefined,
  taskCode: undefined,
  personId: undefined,
  personName: undefined,
  stdId: undefined,
  stdName: undefined,
  execStartTime: undefined,
  execCompleteTime: undefined,
  ckItemDetail: undefined,
  unqualifiedCount: undefined,
  unqualifiedDetail: undefined,
  execScore: undefined,
  specExecRemark: undefined,
  deviceCode: undefined,
  execLocX: undefined,
  execLocY: undefined,
  bizCreateUser: undefined,
  bizCreateTime: undefined,
  bizUpdateUser: undefined,
  bizUpdateTime: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({})
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
      formData.value = await InspectSpecExecRecApi.getInspectSpecExecRec(id)
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
    const data = formData.value as unknown as InspectSpecExecRecVO
    if (formType.value === 'create') {
      await InspectSpecExecRecApi.createInspectSpecExecRec(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectSpecExecRecApi.updateInspectSpecExecRec(data)
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
    specExecId: undefined,
    specExecCode: undefined,
    specPlanId: undefined,
    specPlanName: undefined,
    taskId: undefined,
    taskCode: undefined,
    personId: undefined,
    personName: undefined,
    stdId: undefined,
    stdName: undefined,
    execStartTime: undefined,
    execCompleteTime: undefined,
    ckItemDetail: undefined,
    unqualifiedCount: undefined,
    unqualifiedDetail: undefined,
    execScore: undefined,
    specExecRemark: undefined,
    deviceCode: undefined,
    execLocX: undefined,
    execLocY: undefined,
    bizCreateUser: undefined,
    bizCreateTime: undefined,
    bizUpdateUser: undefined,
    bizUpdateTime: undefined,
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
</style>
