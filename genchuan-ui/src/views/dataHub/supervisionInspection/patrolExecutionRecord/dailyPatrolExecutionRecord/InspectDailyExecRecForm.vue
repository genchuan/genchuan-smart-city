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
          <el-form-item label="记录ID" prop="dailyExecId">
            <el-input v-model="formData.dailyExecId" placeholder="请输入记录ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="执行记录编码" prop="dailyExecCode">
            <el-input v-model="formData.dailyExecCode" placeholder="请输入执行记录编码" />
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
          <el-form-item label="关联标准ID" prop="stdId">
            <el-input v-model="formData.stdId" placeholder="请输入关联标准ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联设备编码" prop="deviceCode">
            <el-input v-model="formData.deviceCode" placeholder="请输入关联设备编码" />
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

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="执行评分" prop="execScore">
            <el-input v-model="formData.execScore" placeholder="请输入执行评分" type="number" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="检查项执行情况" prop="ckItemExec">
            <el-input v-model="formData.ckItemExec" placeholder="请输入检查项执行情况" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="24">
          <el-form-item label="执行备注" prop="execRemark">
            <el-input
              v-model="formData.execRemark"
              placeholder="请输入执行备注"
              type="textarea"
              rows="3"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20" class="mt-10">
        <el-col :span="12">
          <el-form-item label="执行地点坐标X" prop="execLocX">
            <el-input v-model="formData.execLocX" placeholder="请输入执行地点坐标X" type="number" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="执行地点坐标Y" prop="execLocY">
            <el-input v-model="formData.execLocY" placeholder="请输入执行地点坐标Y" type="number" />
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 扩展字段使用折叠面板隐藏 -->
      <el-collapse class="mt-10">
        <el-collapse-item title="扩展字段设置">
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
        </el-collapse-item>
      </el-collapse>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  InspectDailyExecRecApi,
  InspectDailyExecRecVO
} from '@/api/dataHub/supervisionInspection/patrolExecutionRecord/dailyPatrolExecutionRecord'

/** 日常巡查执行记录 表单 */
defineOptions({ name: 'InspectDailyExecRecForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  dailyExecId: undefined,
  dailyExecCode: undefined,
  taskId: undefined,
  taskCode: undefined,
  personId: undefined,
  personName: undefined,
  stdId: undefined,
  execStartTime: undefined,
  execCompleteTime: undefined,
  ckItemExec: undefined,
  execScore: undefined,
  execRemark: undefined,
  deviceCode: undefined,
  execLocX: undefined,
  execLocY: undefined,
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
      formData.value = await InspectDailyExecRecApi.getInspectDailyExecRec(id)
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
    const data = formData.value as unknown as InspectDailyExecRecVO
    if (formType.value === 'create') {
      await InspectDailyExecRecApi.createInspectDailyExecRec(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectDailyExecRecApi.updateInspectDailyExecRec(data)
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
    dailyExecId: undefined,
    dailyExecCode: undefined,
    taskId: undefined,
    taskCode: undefined,
    personId: undefined,
    personName: undefined,
    stdId: undefined,
    execStartTime: undefined,
    execCompleteTime: undefined,
    ckItemExec: undefined,
    execScore: undefined,
    execRemark: undefined,
    deviceCode: undefined,
    execLocX: undefined,
    execLocY: undefined,
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
  color: #4e5969;
}

::v-deep .el-collapse-item__content {
  padding-top: 15px !important;
}

.mt-10 {
  margin-top: 10px;
}
</style>
