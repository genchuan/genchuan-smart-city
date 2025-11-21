<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="650px">
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
          <el-form-item label="评估ID" prop="evalId">
            <el-input v-model="formData.evalId" placeholder="请输入评估ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预警ID" prop="earlyWarnId">
            <el-input v-model="formData.earlyWarnId" placeholder="请输入预警ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="派单ID" prop="dispatchId">
            <el-input v-model="formData.dispatchId" placeholder="请输入派单ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="评估人ID" prop="evalUserId">
            <el-input v-model="formData.evalUserId" placeholder="请输入评估人ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="评估人姓名" prop="evalUserName">
            <el-input v-model="formData.evalUserName" placeholder="请输入评估人姓名" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="问题解决度得分" prop="problemSolveScore">
            <el-input
              v-model="formData.problemSolveScore"
              placeholder="请输入问题解决度得分"
              type="number"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="响应及时性得分" prop="timelinessScore">
            <el-input
              v-model="formData.timelinessScore"
              placeholder="请输入响应及时性得分"
              type="number"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="现场规范性得分" prop="stdizationScore">
            <el-input
              v-model="formData.stdizationScore"
              placeholder="请输入现场规范性得分"
              type="number"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="综合得分" prop="comprehensiveScore">
            <el-input
              v-model="formData.comprehensiveScore"
              placeholder="请输入综合得分"
              type="number"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="评估等级" prop="evalLevel">
            <el-input v-model="formData.evalLevel" placeholder="请输入评估等级" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="评估时间" prop="evalTime">
            <el-date-picker
              v-model="formData.evalTime"
              type="datetime"
              value-format="x"
              placeholder="选择评估时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="是否二次处置" prop="isSecondHandle">
            <el-select v-model="formData.isSecondHandle" placeholder="请选择是否二次处置">
              <el-option label="是" value="1" />
              <el-option label="否" value="0" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="评估备注" prop="evalRemark">
            <el-input
              v-model="formData.evalRemark"
              placeholder="请输入评估备注"
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
  EarlyWarnHandleEvalApi,
  EarlyWarnHandleEvalVO
} from '@/api/dataHub/warningAlarm/warnAlarmRespDisposal/disposalresulteval'

/** 预警告警处置评估 表单 */
defineOptions({ name: 'EarlyWarnHandleEvalForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<EarlyWarnHandleEvalVO>>({
  id: undefined,
  evalId: undefined,
  earlyWarnId: undefined,
  dispatchId: undefined,
  evalUserId: undefined,
  evalUserName: undefined,
  problemSolveScore: undefined,
  timelinessScore: undefined,
  stdizationScore: undefined,
  comprehensiveScore: undefined,
  evalLevel: undefined,
  evalTime: undefined,
  evalRemark: undefined,
  isSecondHandle: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  evalId: [{ required: true, message: '评估ID不能为空', trigger: 'blur' }],
  earlyWarnId: [{ required: true, message: '预警ID不能为空', trigger: 'blur' }],
  dispatchId: [{ required: true, message: '派单ID不能为空', trigger: 'blur' }],
  evalUserId: [{ required: true, message: '评估人ID不能为空', trigger: 'blur' }],
  evalUserName: [{ required: true, message: '评估人姓名不能为空', trigger: 'blur' }],
  problemSolveScore: [{ required: true, message: '问题解决度得分不能为空', trigger: 'blur' }],
  timelinessScore: [{ required: true, message: '响应及时性得分不能为空', trigger: 'blur' }],
  stdizationScore: [{ required: true, message: '现场规范性得分不能为空', trigger: 'blur' }],
  comprehensiveScore: [{ required: true, message: '综合得分不能为空', trigger: 'blur' }],
  evalLevel: [{ required: true, message: '评估等级不能为空', trigger: 'blur' }],
  evalTime: [{ required: true, message: '评估时间不能为空', trigger: 'blur' }],
  isSecondHandle: [{ required: true, message: '是否二次处置不能为空', trigger: 'blur' }]
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
      formData.value = await EarlyWarnHandleEvalApi.getEarlyWarnHandleEval(id)
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
    const data = formData.value as unknown as EarlyWarnHandleEvalVO
    if (formType.value === 'create') {
      await EarlyWarnHandleEvalApi.createEarlyWarnHandleEval(data)
      message.success(t('common.createSuccess'))
    } else {
      await EarlyWarnHandleEvalApi.updateEarlyWarnHandleEval(data)
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
    evalId: undefined,
    earlyWarnId: undefined,
    dispatchId: undefined,
    evalUserId: undefined,
    evalUserName: undefined,
    problemSolveScore: undefined,
    timelinessScore: undefined,
    stdizationScore: undefined,
    comprehensiveScore: undefined,
    evalLevel: undefined,
    evalTime: undefined,
    evalRemark: undefined,
    isSecondHandle: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined
  }
  formRef.value?.resetFields()
}
</script>

<style scoped>
.custom-form {
  background-color: #fff;
  padding: 15px;
  border-radius: 8px;
}

::v-deep .el-form-item {
  margin-bottom: 15px;
}

::v-deep .el-form-item__label {
  font-weight: 500;
  color: #4e5969;
}

::v-deep .el-input__wrapper,
::v-deep .el-select__wrapper,
::v-deep .el-textarea__wrapper {
  border-radius: 6px;
}
</style>
