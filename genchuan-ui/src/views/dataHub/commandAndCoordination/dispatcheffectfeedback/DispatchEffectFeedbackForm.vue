<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="反馈ID" prop="feedbackId">
        <el-input v-model="formData.feedbackId" placeholder="请输入反馈ID" />
      </el-form-item>
      <el-form-item label="关联指令ID" prop="commandId">
        <el-input v-model="formData.commandId" placeholder="请输入关联指令ID" />
      </el-form-item>
      <el-form-item label="指令编号" prop="commandNo">
        <el-input v-model="formData.commandNo" placeholder="请输入指令编号" />
      </el-form-item>
      <el-form-item label="执行单位ID" prop="execUnitId">
        <el-input v-model="formData.execUnitId" placeholder="请输入执行单位ID" />
      </el-form-item>
      <el-form-item label="执行单位名称" prop="execUnitName">
        <el-input v-model="formData.execUnitName" placeholder="请输入执行单位名称" />
      </el-form-item>
      <el-form-item label="执行效果描述" prop="effectDesc">
        <el-input v-model="formData.effectDesc" placeholder="请输入执行效果描述" />
      </el-form-item>
      <el-form-item label="反馈附件路径" prop="feedbackAttachPath">
        <el-input v-model="formData.feedbackAttachPath" placeholder="请输入反馈附件路径" />
      </el-form-item>
      <el-form-item label="反馈人ID" prop="feedbackUserId">
        <el-input v-model="formData.feedbackUserId" placeholder="请输入反馈人ID" />
      </el-form-item>
      <el-form-item label="反馈人姓名" prop="feedbackUserName">
        <el-input v-model="formData.feedbackUserName" placeholder="请输入反馈人姓名" />
      </el-form-item>
      <el-form-item label="反馈时间" prop="feedbackTime">
        <el-date-picker
          v-model="formData.feedbackTime"
          type="date"
          value-format="x"
          placeholder="选择反馈时间"
        />
      </el-form-item>
      <el-form-item label="评估结果" prop="evalResult">
        <el-input v-model="formData.evalResult" placeholder="请输入评估结果" />
      </el-form-item>
      <el-form-item label="评估说明" prop="evalDesc">
        <el-input v-model="formData.evalDesc" placeholder="请输入评估说明" />
      </el-form-item>
      <el-form-item label="评估人ID" prop="evalUserId">
        <el-input v-model="formData.evalUserId" placeholder="请输入评估人ID" />
      </el-form-item>
      <el-form-item label="评估时间" prop="evalTime">
        <el-date-picker
          v-model="formData.evalTime"
          type="date"
          value-format="x"
          placeholder="选择评估时间"
        />
      </el-form-item>
      <el-form-item label="分类扩展字段1" prop="extCat1">
        <el-input v-model="formData.extCat1" placeholder="请输入分类扩展字段1" />
      </el-form-item>
      <el-form-item label="分类扩展字段2" prop="extCat2">
        <el-input v-model="formData.extCat2" placeholder="请输入分类扩展字段2" />
      </el-form-item>
      <el-form-item label="通用扩展字段1" prop="extCommon1">
        <el-input v-model="formData.extCommon1" placeholder="请输入通用扩展字段1" />
      </el-form-item>
      <el-form-item label="通用扩展字段2" prop="extCommon2">
        <el-input v-model="formData.extCommon2" placeholder="请输入通用扩展字段2" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { DispatchEffectFeedbackApi, DispatchEffectFeedbackVO } from '@/api/dataHub/commandAndCoordination/dispatcheffectfeedback'

/** 调度效果反馈 表单 */
defineOptions({ name: 'DispatchEffectFeedbackForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  feedbackId: undefined,
  commandId: undefined,
  commandNo: undefined,
  execUnitId: undefined,
  execUnitName: undefined,
  effectDesc: undefined,
  feedbackAttachPath: undefined,
  feedbackUserId: undefined,
  feedbackUserName: undefined,
  feedbackTime: undefined,
  evalResult: undefined,
  evalDesc: undefined,
  evalUserId: undefined,
  evalTime: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
})
const formRules = reactive({
  feedbackId: [{ required: true, message: '反馈ID不能为空', trigger: 'blur' }],
  commandId: [{ required: true, message: '关联指令ID不能为空', trigger: 'blur' }],
  commandNo: [{ required: true, message: '指令编号不能为空', trigger: 'blur' }],
  execUnitId: [{ required: true, message: '执行单位ID不能为空', trigger: 'blur' }],
  execUnitName: [{ required: true, message: '执行单位名称不能为空', trigger: 'blur' }],
  effectDesc: [{ required: true, message: '执行效果描述不能为空', trigger: 'blur' }],
  feedbackUserId: [{ required: true, message: '反馈人ID不能为空', trigger: 'blur' }],
  feedbackUserName: [{ required: true, message: '反馈人姓名不能为空', trigger: 'blur' }],
  feedbackTime: [{ required: true, message: '反馈时间不能为空', trigger: 'blur' }],
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
      formData.value = await DispatchEffectFeedbackApi.getDispatchEffectFeedback(id)
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
    const data = formData.value as unknown as DispatchEffectFeedbackVO
    if (formType.value === 'create') {
      await DispatchEffectFeedbackApi.createDispatchEffectFeedback(data)
      message.success(t('common.createSuccess'))
    } else {
      await DispatchEffectFeedbackApi.updateDispatchEffectFeedback(data)
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
    commandId: undefined,
    commandNo: undefined,
    execUnitId: undefined,
    execUnitName: undefined,
    effectDesc: undefined,
    feedbackAttachPath: undefined,
    feedbackUserId: undefined,
    feedbackUserName: undefined,
    feedbackTime: undefined,
    evalResult: undefined,
    evalDesc: undefined,
    evalUserId: undefined,
    evalTime: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined,
  }
  formRef.value?.resetFields()
}
</script>
