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
          <el-form-item label="反馈ID" prop="feedbackId">
            <el-input v-model="formData.feedbackId" placeholder="请输入反馈ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="反馈编码" prop="feedbackCode">
            <el-input v-model="formData.feedbackCode" placeholder="请输入反馈编码" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="关联督办任务ID" prop="supvTaskId">
            <el-input v-model="formData.supvTaskId" placeholder="请输入关联督办任务ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联督办任务编码" prop="supvTaskCode">
            <el-input v-model="formData.supvTaskCode" placeholder="请输入关联督办任务编码" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="关联问题名称" prop="problemName">
        <el-input v-model="formData.problemName" placeholder="请输入关联问题名称" />
      </el-form-item>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="督办结果" prop="supvResult">
            <el-select v-model="formData.supvResult" placeholder="请选择督办结果">
              <el-option label="已完成" value="已完成" />
              <el-option label="进行中" value="进行中" />
              <el-option label="部分完成" value="部分完成" />
              <!--              <el-option label="未通过" value="未通过" />-->
              <!--              <el-option label="已逾期" value="已逾期" />-->
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联整改验收结果" prop="rectifyAcceptResult">
            <el-select v-model="formData.rectifyAcceptResult" placeholder="请选择关联整改验收结果">
              <el-option label="待改进" value="待改进" />
              <el-option label="待定" value="待定" />
              <el-option label="合格" value="合格" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="督办结果说明" prop="supvResultDesc">
        <el-input
          v-model="formData.supvResultDesc"
          placeholder="请输入督办结果说明"
          type="textarea"
          rows="3"
        />
      </el-form-item>

      <el-form-item label="督办反馈意见" prop="supvFeedbackOpinion">
        <el-input
          v-model="formData.supvFeedbackOpinion"
          placeholder="请输入督办反馈意见"
          type="textarea"
          rows="3"
        />
      </el-form-item>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="反馈人" prop="feedbackUser">
            <el-input v-model="formData.feedbackUser" placeholder="请输入反馈人" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="反馈时间" prop="feedbackTime">
            <el-date-picker
              v-model="formData.feedbackTime"
              type="datetime"
              value-format="x"
              placeholder="选择反馈时间"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="督办任务状态变更" prop="supvStatusChange">
        <el-input
          v-model="formData.supvStatusChange"
          placeholder="请输入督办任务状态变更（例：进行中->已完成）"
        />
      </el-form-item>

      <el-collapse v-model="activeNames" class="mt-4">
        <el-collapse-item name="1" title="扩展字段">
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
  InspectSupvFeedbackApi,
  InspectSupvFeedbackVO
} from '@/api/dataHub/supervisionInspection/keyTaskOversight/keyTaskOversightResultFeedback'

/** 巡查巡检督办结果反馈 表单 */
defineOptions({ name: 'InspectSupvFeedbackForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const activeNames = ref(['1']) // 折叠面板默认状态
const formData = ref<Partial<InspectSupvFeedbackVO>>({
  id: undefined,
  feedbackId: undefined,
  feedbackCode: undefined,
  supvTaskId: undefined,
  supvTaskCode: undefined,
  problemName: undefined,
  supvResult: undefined,
  supvResultDesc: undefined,
  supvFeedbackOpinion: undefined,
  rectifyAcceptResult: undefined,
  feedbackUser: undefined,
  feedbackTime: undefined,
  supvStatusChange: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  feedbackId: [{ required: true, message: '反馈ID不能为空', trigger: 'blur' }],
  feedbackCode: [{ required: true, message: '反馈编码不能为空', trigger: 'blur' }],
  supvTaskId: [{ required: true, message: '关联督办任务ID不能为空', trigger: 'blur' }],
  supvTaskCode: [{ required: true, message: '关联督办任务编码不能为空', trigger: 'blur' }],
  problemName: [{ required: true, message: '关联问题名称不能为空', trigger: 'blur' }],
  supvResult: [{ required: true, message: '督办结果不能为空', trigger: 'change' }],
  feedbackUser: [{ required: true, message: '反馈人不能为空', trigger: 'blur' }],
  feedbackTime: [{ required: true, message: '反馈时间不能为空', trigger: 'change' }]
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
      const data = await InspectSupvFeedbackApi.getInspectSupvFeedback(id)
      formData.value = { ...data }
      // 转换时间格式为时间戳
      if (formData.value.feedbackTime) {
        formData.value.feedbackTime = new Date(formData.value.feedbackTime).getTime()
      }
    } finally {
      formLoading.value = false
    }
  } else {
    // 新增时默认值
    formData.value.feedbackTime = Date.now()
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
    const data = { ...formData.value } as unknown as InspectSupvFeedbackVO
    // 转换时间格式为Date
    if (data.feedbackTime) {
      data.feedbackTime = new Date(Number(data.feedbackTime))
    }

    if (formType.value === 'create') {
      await InspectSupvFeedbackApi.createInspectSupvFeedback(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectSupvFeedbackApi.updateInspectSupvFeedback(data)
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
    feedbackId: undefined,
    feedbackCode: undefined,
    supvTaskId: undefined,
    supvTaskCode: undefined,
    problemName: undefined,
    supvResult: undefined,
    supvResultDesc: undefined,
    supvFeedbackOpinion: undefined,
    rectifyAcceptResult: undefined,
    feedbackUser: undefined,
    feedbackTime: Date.now(),
    supvStatusChange: undefined,
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

::v-deep .el-collapse {
  border: 1px solid #e5e7eb;
  border-radius: 4px;
}

::v-deep .el-collapse-item__header {
  background-color: #f9fafb;
}

::v-deep .el-textarea__wrapper {
  resize: none;
}
</style>
