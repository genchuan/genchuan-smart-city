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
          <el-form-item label="记录ID" prop="emerExecId">
            <el-input v-model="formData.emerExecId" placeholder="请输入记录ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="记录编码" prop="emerExecCode">
            <el-input v-model="formData.emerExecCode" placeholder="请输入记录编码" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="关联应急计划ID" prop="emerPlanId">
            <el-input v-model="formData.emerPlanId" placeholder="请输入关联应急计划ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联应急计划名称" prop="emerPlanName">
            <el-input v-model="formData.emerPlanName" placeholder="请输入关联应急计划名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="关联应急事件ID" prop="emerEvtId">
            <el-input v-model="formData.emerEvtId" placeholder="请输入关联应急事件ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联应急事件名称" prop="emerEvtName">
            <el-input v-model="formData.emerEvtName" placeholder="请输入关联应急事件名称" />
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
          <el-form-item label="执行人员ID" prop="personId">
            <el-input v-model="formData.personId" placeholder="请输入执行人员ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="执行人员姓名" prop="personName">
            <el-input v-model="formData.personName" placeholder="请输入执行人员姓名" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
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
      </el-row>

      <el-form-item label="应急检查项明细" prop="emerCkDetail">
        <el-input
          v-model="formData.emerCkDetail"
          placeholder="请输入应急检查项明细"
          type="textarea"
          rows="3"
        />
      </el-form-item>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="紧急级别" prop="urgencyLevel">
            <el-select v-model="formData.urgencyLevel" placeholder="请选择紧急级别">
              <el-option label="一般" value="一般" />
              <el-option label="较紧急" value="较紧急" />
              <el-option label="紧急" value="紧急" />
              <el-option label="非常紧急" value="非常紧急" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="是否已推送指挥中心" prop="isPushedCmd">
            <el-radio-group v-model="formData.isPushedCmd">
              <el-radio :value="true">是</el-radio>
              <el-radio :value="false">否</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="应急问题描述" prop="emerProblemDesc">
        <el-input
          v-model="formData.emerProblemDesc"
          placeholder="请输入应急问题描述"
          type="textarea"
          rows="3"
        />
      </el-form-item>

      <el-form-item label="处置建议" prop="disposalSugg">
        <el-input
          v-model="formData.disposalSugg"
          placeholder="请输入处置建议"
          type="textarea"
          rows="3"
        />
      </el-form-item>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="执行设备编码" prop="deviceCode">
            <el-input v-model="formData.deviceCode" placeholder="请输入执行设备编码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="推送时间" prop="pushTime">
            <el-date-picker
              v-model="formData.pushTime"
              type="datetime"
              value-format="x"
              placeholder="选择推送时间"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="应急地点坐标X" prop="emerLocX">
            <el-input v-model="formData.emerLocX" placeholder="请输入应急地点坐标X" type="number" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="应急地点坐标Y" prop="emerLocY">
            <el-input v-model="formData.emerLocY" placeholder="请输入应急地点坐标Y" type="number" />
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

      <!-- 隐藏扩展字段，如需使用可取消注释 -->
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
  InspectEmerExecRecApi,
  InspectEmerExecRecVO
} from '@/api/dataHub/supervisionInspection/patrolExecutionRecord/emergencyPatrolExecutionRecord'

/** 应急巡查执行记录 表单 */
defineOptions({ name: 'InspectEmerExecRecForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<InspectEmerExecRecVO>>({
  id: undefined,
  emerExecId: undefined,
  emerExecCode: undefined,
  emerPlanId: undefined,
  emerPlanName: undefined,
  emerEvtId: undefined,
  emerEvtName: undefined,
  taskId: undefined,
  taskCode: undefined,
  personId: undefined,
  personName: undefined,
  execStartTime: undefined,
  execCompleteTime: undefined,
  emerCkDetail: undefined,
  urgencyLevel: undefined,
  emerProblemDesc: undefined,
  disposalSugg: undefined,
  deviceCode: undefined,
  emerLocX: undefined,
  emerLocY: undefined,
  isPushedCmd: undefined,
  pushTime: undefined,
  bizCreateUser: undefined,
  bizCreateTime: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  emerExecId: [{ required: true, message: '请输入记录ID', trigger: 'blur' }],
  emerPlanName: [{ required: true, message: '请输入关联应急计划名称', trigger: 'blur' }],
  personName: [{ required: true, message: '请输入执行人员姓名', trigger: 'blur' }],
  execStartTime: [{ required: true, message: '请选择执行开始时间', trigger: 'change' }]
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
      const data = await InspectEmerExecRecApi.getInspectEmerExecRec(id)
      formData.value = { ...data }
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
    const data = formData.value as unknown as InspectEmerExecRecVO
    if (formType.value === 'create') {
      await InspectEmerExecRecApi.createInspectEmerExecRec(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectEmerExecRecApi.updateInspectEmerExecRec(data)
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
    emerExecId: undefined,
    emerExecCode: undefined,
    emerPlanId: undefined,
    emerPlanName: undefined,
    emerEvtId: undefined,
    emerEvtName: undefined,
    taskId: undefined,
    taskCode: undefined,
    personId: undefined,
    personName: undefined,
    execStartTime: undefined,
    execCompleteTime: undefined,
    emerCkDetail: undefined,
    urgencyLevel: undefined,
    emerProblemDesc: undefined,
    disposalSugg: undefined,
    deviceCode: undefined,
    emerLocX: undefined,
    emerLocY: undefined,
    isPushedCmd: undefined,
    pushTime: undefined,
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
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
}

::v-deep .el-form-item {
  margin-bottom: 16px;
}

::v-deep .el-input__wrapper,
::v-deep .el-select__wrapper,
::v-deep .el-date-editor .el-input__wrapper {
  border-radius: 4px;
}

::v-deep .el-textarea__wrapper {
  border-radius: 4px;
}
</style>
