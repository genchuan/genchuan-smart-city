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
          <el-form-item label="整改任务ID" prop="rectifyTaskId">
            <el-input v-model="formData.rectifyTaskId" placeholder="请输入整改任务ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="整改任务编码" prop="rectifyTaskCode">
            <el-input v-model="formData.rectifyTaskCode" placeholder="请输入整改任务编码" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="关联问题名称" prop="problemName">
            <el-input v-model="formData.problemName" placeholder="请输入关联问题名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="责任部门名称" prop="deptName">
            <el-input v-model="formData.deptName" placeholder="请输入责任部门名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="整改负责人姓名" prop="leaderUserName">
            <el-input v-model="formData.leaderUserName" placeholder="请输入整改负责人姓名" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="整改任务状态" prop="rectifyStatus">
            <el-select v-model="formData.rectifyStatus" placeholder="请选择整改任务状态">
              <el-option label="未开始" value="未开始" />
              <el-option label="进行中" value="进行中" />
              <el-option label="已完成" value="已完成" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="整改时限（小时）" prop="rectifyDeadlineHour">
            <el-input
              v-model.number="formData.rectifyDeadlineHour"
              placeholder="请输入整改时限（小时）"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="整改截止时间" prop="rectifyDeadlineTime">
            <el-date-picker
              v-model="formData.rectifyDeadlineTime"
              type="datetime"
              value-format="x"
              placeholder="选择整改截止时间"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="剩余时间（分钟）" prop="remainTimeMin">
            <el-input
              v-model.number="formData.remainTimeMin"
              placeholder="请输入剩余时间（分钟）"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="是否超期" prop="isOverdue">
            <el-radio-group v-model="formData.isOverdue">
              <el-radio :value="true">是</el-radio>
              <el-radio :value="false">否</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="最新进展描述" prop="latestProgressDesc">
        <el-input
          v-model="formData.latestProgressDesc"
          placeholder="请输入最新进展描述"
          type="textarea"
          rows="3"
        />
      </el-form-item>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="最新进展时间" prop="latestProgressTime">
            <el-date-picker
              v-model="formData.latestProgressTime"
              type="datetime"
              value-format="x"
              placeholder="选择最新进展时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="最新进展照片URL" prop="latestProgressPhoto">
            <el-input v-model="formData.latestProgressPhoto" placeholder="请输入最新进展照片URL" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="催办次数" prop="remindCount">
            <el-input v-model.number="formData.remindCount" placeholder="请输入催办次数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="最后催办时间" prop="lastRemindTime">
            <el-date-picker
              v-model="formData.lastRemindTime"
              type="datetime"
              value-format="x"
              placeholder="选择最后催办时间"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="派发时间" prop="dispatchTime">
        <el-date-picker
          v-model="formData.dispatchTime"
          type="datetime"
          value-format="x"
          placeholder="选择派发时间"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  InspectRectifyTrackApi,
  InspectRectifyTrackVO
} from '@/api/dataHub/supervisionInspection/issueCorrectionDisposal/correctionProgressTracking'

/** 巡查巡检整改进度跟踪视图 表单 */
defineOptions({ name: 'InspectRectifyTrackForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<InspectRectifyTrackVO>>({
  id: undefined,
  rectifyTaskId: undefined,
  rectifyTaskCode: undefined,
  problemName: undefined,
  deptName: undefined,
  leaderUserName: undefined,
  rectifyStatus: undefined,
  rectifyDeadlineHour: undefined,
  rectifyDeadlineTime: undefined,
  remainTimeMin: undefined,
  isOverdue: false,
  latestProgressTime: undefined,
  latestProgressDesc: undefined,
  latestProgressPhoto: undefined,
  remindCount: 0,
  lastRemindTime: undefined,
  dispatchTime: undefined
})
const formRules = reactive({
  rectifyTaskId: [{ required: true, message: '整改任务ID不能为空', trigger: 'blur' }],
  rectifyTaskCode: [{ required: true, message: '整改任务编码不能为空', trigger: 'blur' }],
  problemName: [{ required: true, message: '关联问题名称不能为空', trigger: 'blur' }],
  deptName: [{ required: true, message: '责任部门名称不能为空', trigger: 'blur' }],
  leaderUserName: [{ required: true, message: '整改负责人姓名不能为空', trigger: 'blur' }],
  rectifyStatus: [{ required: true, message: '整改任务状态不能为空', trigger: 'change' }],
  rectifyDeadlineHour: [{ required: true, message: '整改时限（小时）不能为空', trigger: 'blur' }],
  rectifyDeadlineTime: [{ required: true, message: '整改截止时间不能为空', trigger: 'change' }],
  isOverdue: [{ required: true, message: '是否超期不能为空', trigger: 'change' }],
  remindCount: [{ required: true, message: '催办次数不能为空', trigger: 'blur' }],
  dispatchTime: [{ required: true, message: '派发时间不能为空', trigger: 'change' }]
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
      const data = await InspectRectifyTrackApi.getInspectRectifyTrack(id)
      formData.value = { ...data }
      // 转换时间格式为时间戳
      if (formData.value.rectifyDeadlineTime) {
        formData.value.rectifyDeadlineTime = new Date(formData.value.rectifyDeadlineTime).getTime()
      }
      if (formData.value.latestProgressTime) {
        formData.value.latestProgressTime = new Date(formData.value.latestProgressTime).getTime()
      }
      if (formData.value.lastRemindTime) {
        formData.value.lastRemindTime = new Date(formData.value.lastRemindTime).getTime()
      }
      if (formData.value.dispatchTime) {
        formData.value.dispatchTime = new Date(formData.value.dispatchTime).getTime()
      }
    } finally {
      formLoading.value = false
    }
  } else {
    // 新增时默认值
    formData.value.isOverdue = false
    formData.value.remindCount = 0
    formData.value.dispatchTime = Date.now()
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
    const data = { ...formData.value } as unknown as InspectRectifyTrackVO
    // 转换时间格式为Date
    if (data.rectifyDeadlineTime) {
      data.rectifyDeadlineTime = new Date(Number(data.rectifyDeadlineTime))
    }
    if (data.latestProgressTime) {
      data.latestProgressTime = new Date(Number(data.latestProgressTime))
    }
    if (data.lastRemindTime) {
      data.lastRemindTime = new Date(Number(data.lastRemindTime))
    }
    if (data.dispatchTime) {
      data.dispatchTime = new Date(Number(data.dispatchTime))
    }

    if (formType.value === 'create') {
      await InspectRectifyTrackApi.createInspectRectifyTrack(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectRectifyTrackApi.updateInspectRectifyTrack(data)
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
    rectifyTaskId: undefined,
    rectifyTaskCode: undefined,
    problemName: undefined,
    deptName: undefined,
    leaderUserName: undefined,
    rectifyStatus: undefined,
    rectifyDeadlineHour: undefined,
    rectifyDeadlineTime: undefined,
    remainTimeMin: undefined,
    isOverdue: false,
    latestProgressTime: undefined,
    latestProgressDesc: undefined,
    latestProgressPhoto: undefined,
    remindCount: 0,
    lastRemindTime: undefined,
    dispatchTime: undefined
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

::v-deep .el-input__wrapper,
::v-deep .el-select__wrapper,
::v-deep .el-date-editor .el-input__wrapper {
  border-radius: 4px;
}

::v-deep .el-textarea__wrapper {
  border-radius: 4px;
  resize: none;
}
</style>
