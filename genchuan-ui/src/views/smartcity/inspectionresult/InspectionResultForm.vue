<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="150px"
      v-loading="formLoading"
    >
      <el-form-item label="检查结果编号" prop="inspectionNumber">
        <el-input v-model="formData.inspectionNumber" placeholder="请输入检查结果编号" />
      </el-form-item>
      <el-form-item label="检查任务编号" prop="taskNumber">
        <el-input v-model="formData.taskNumber" placeholder="请输入检查任务编号" />
      </el-form-item>
      <el-form-item label="检查内容" prop="inspectionContent">
        <el-input v-model="formData.inspectionContent" placeholder="请输入检查内容" />
      </el-form-item>
      <el-form-item label="检查结果" prop="inspectionResult">
        <el-input v-model="formData.inspectionResult" placeholder="请输入检查结果" maxlength="18" />
      </el-form-item>
      <el-form-item label="检查时间" prop="inspectionTime">
        <el-date-picker
          v-model="formData.inspectionTime"
          type="datetime"
          value-format="x"
          placeholder="请选择检查时间"
        />
      </el-form-item>
      <el-form-item label="检查地点" prop="inspectionLocation">
        <el-input v-model="formData.inspectionLocation" placeholder="请输入检查地点" />
      </el-form-item>
      <el-form-item label="处理意见" prop="disposalOpinion">
        <el-input v-model="formData.disposalOpinion" placeholder="请输入处理意见" />
      </el-form-item>
      <el-form-item label="执法人员编号" prop="officerNumber">
        <el-input v-model="formData.officerNumber" placeholder="多个编号用逗号分隔" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { InspectionResultApi, InspectionResultVO } from '@/api/smartcity/inspectionresult'

/** 检查结果管理 表单 */
defineOptions({ name: 'InspectionResultForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
// 表单数据：完全匹配检查结果实体字段
const formData = ref({
  id: undefined,
  inspectionNumber: undefined,
  taskNumber: undefined,
  inspectionContent: undefined,
  inspectionResult: undefined,
  inspectionTime: undefined,
  inspectionLocation: undefined,
  disposalOpinion: undefined,
  officerNumber: undefined
})
// 表单校验规则：针对核心必填字段设置校验
const formRules = reactive({
  inspectionNumber: [{ required: true, message: '请输入检查结果编号', trigger: 'blur' }],
  taskNumber: [{ required: true, message: '请输入检查任务编号', trigger: 'blur' }],
})
const formRef = ref() // 表单 ref

/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = t('action.' + type)
  formType.value = type
  resetForm()
  // 修改时，加载检查结果详情数据
  if (id) {
    formLoading.value = true
    try {
      formData.value = await InspectionResultApi.getInspectionResult(id)
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
    const submitData = { ...formData.value }
    if (formType.value === 'create') {
      await InspectionResultApi.createInspectionResult(submitData as unknown as InspectionResultVO)
      message.success(t('common.createSuccess'))
    } else {
      await InspectionResultApi.updateInspectionResult(submitData as unknown as InspectionResultVO)
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
    inspectionNumber: undefined,
    taskNumber: undefined,
    inspectionContent: undefined,
    inspectionResult: undefined,
    inspectionTime: undefined,
    inspectionLocation: undefined,
    disposalOpinion: undefined,
    officerNumber: undefined
  }
  formRef.value?.resetFields()
}
</script>
