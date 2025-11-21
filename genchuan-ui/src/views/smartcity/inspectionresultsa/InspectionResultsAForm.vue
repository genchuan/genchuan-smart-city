<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="编号" prop="number">
        <el-input v-model="formData.number" placeholder="请输入编号" />
      </el-form-item>
      <el-form-item label="巡查人员" prop="patrolPersonnel">
        <el-input v-model="formData.patrolPersonnel" placeholder="请输入巡查人员" />
      </el-form-item>
      <el-form-item label="巡查时间" prop="patrolTime">
        <el-date-picker
          v-model="formData.patrolTime"
          type="datetime"
          value-format="x"
          placeholder="选择巡查时间"
        />
      </el-form-item>
      <el-form-item label="巡查地点" prop="patrolLocation">
        <el-input v-model="formData.patrolLocation" placeholder="请输入巡查地点" />
      </el-form-item>
      <el-form-item label="资源名称" prop="resourceName">
        <el-input v-model="formData.resourceName" placeholder="请输入资源名称" />
      </el-form-item>
      <el-form-item label="资源编号" prop="resourceNumber">
        <el-input v-model="formData.resourceNumber" placeholder="请输入资源编号" />
      </el-form-item>
      <el-form-item label="检查项目" prop="inspectionItems">
        <el-input v-model="formData.inspectionItems" placeholder="请输入检查项目" />
      </el-form-item>
      <el-form-item label="检查结果" prop="inspectionResults">
        <el-input v-model="formData.inspectionResults" placeholder="请输入检查结果" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { InspectionResultsAApi, InspectionResultsAVO } from '@/api/smartcity/inspectionresultsa'

/** 巡查结果 表单 */
defineOptions({ name: 'InspectionResultsAForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  number: undefined,
  patrolPersonnel: undefined,
  patrolTime: undefined,
  patrolLocation: undefined,
  resourceName: undefined,
  resourceNumber: undefined,
  inspectionItems: undefined,
  inspectionResults: undefined
})
const formRules = reactive({
})
const formRef = ref() // 表单 ref

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
      formData.value = await InspectionResultsAApi.getInspectionResultsA(id)
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
    const data = formData.value as unknown as InspectionResultsAVO
    if (formType.value === 'create') {
      await InspectionResultsAApi.createInspectionResultsA(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectionResultsAApi.updateInspectionResultsA(data)
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
    number: undefined,
    patrolPersonnel: undefined,
    patrolTime: undefined,
    patrolLocation: undefined,
    resourceName: undefined,
    resourceNumber: undefined,
    inspectionItems: undefined,
    inspectionResults: undefined
  }
  formRef.value?.resetFields()
}
</script>
