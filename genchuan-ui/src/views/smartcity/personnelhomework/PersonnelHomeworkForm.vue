<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="作业编号" prop="jobNumber">
        <el-input v-model="formData.jobNumber" placeholder="请输入作业编号" />
      </el-form-item>
      <el-form-item label="作业人员编号" prop="operatorId">
        <el-input v-model="formData.operatorId" placeholder="请输入作业人员编号" />
      </el-form-item>
      <el-form-item label="作业区域编号" prop="assignmentAreaNumber">
        <el-input v-model="formData.assignmentAreaNumber" placeholder="请输入作业区域编号" />
      </el-form-item>
      <el-form-item label="作业时间" prop="operationTime">
        <el-date-picker
          v-model="formData.operationTime"
          type="date"
          value-format="x"
          placeholder="选择作业时间"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="作业内容" prop="homeworkContent">
        <el-input v-model="formData.homeworkContent" type="textarea" placeholder="请输入作业内容" />
      </el-form-item>
      <el-form-item label="作业方式" prop="operationMode">
        <el-input v-model="formData.operationMode" placeholder="请输入作业方式" />
      </el-form-item>
      <el-form-item label="备注" prop="notes">
        <el-input v-model="formData.notes" placeholder="请输入备注" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { PersonnelHomeworkApi, PersonnelHomeworkVO } from '@/api/smartcity/personnelhomework'

/** 人员作业 表单 */
defineOptions({ name: 'PersonnelHomeworkForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  jobNumber: undefined,
  operatorId: undefined,
  assignmentAreaNumber: undefined,
  operationTime: undefined,
  homeworkContent: undefined,
  operationMode: undefined,
  notes: undefined
})
const formRules = reactive({
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
      formData.value = await PersonnelHomeworkApi.getPersonnelHomework(id)
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
    const data = formData.value as unknown as PersonnelHomeworkVO
    if (formType.value === 'create') {
      await PersonnelHomeworkApi.createPersonnelHomework(data)
      message.success(t('common.createSuccess'))
    } else {
      await PersonnelHomeworkApi.updatePersonnelHomework(data)
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
    jobNumber: undefined,
    operatorId: undefined,
    assignmentAreaNumber: undefined,
    operationTime: undefined,
    homeworkContent: undefined,
    operationMode: undefined,
    notes: undefined
  }
  formRef.value?.resetFields()
}
</script>
