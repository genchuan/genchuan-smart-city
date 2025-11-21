<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="案件ID" prop="caseId">
        <el-input v-model="formData.caseId" placeholder="请输入案件ID" />
      </el-form-item>
      <el-form-item label="结案原因" prop="closureReason">
        <el-input v-model="formData.closureReason" placeholder="请输入结案原因" />
      </el-form-item>
      <el-form-item label="结案部门" prop="closureDepartment">
        <el-input v-model="formData.closureDepartment" placeholder="请输入结案部门" />
      </el-form-item>
      <el-form-item label="结案人" prop="closurePerson">
        <el-input v-model="formData.closurePerson" placeholder="请输入结案人" />
      </el-form-item>
      <el-form-item label="结案时间" prop="closureTime">
        <el-date-picker
          v-model="formData.closureTime"
          type="date"
          value-format="x"
          placeholder="选择结案时间"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="审批人" prop="approvalPerson">
        <el-input v-model="formData.approvalPerson" placeholder="请输入审批人" />
      </el-form-item>
      <el-form-item label="审批时间" prop="approvalTime">
        <el-date-picker
          v-model="formData.approvalTime"
          type="date"
          value-format="x"
          placeholder="选择审批时间"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="审批意见" prop="approvalOpinion">
        <el-input v-model="formData.approvalOpinion" placeholder="请输入审批意见" />
      </el-form-item>
      <el-form-item label="归档编号" prop="archiveNumber">
        <el-input v-model="formData.archiveNumber" placeholder="请输入归档编号" />
      </el-form-item>
      <el-form-item label="归档位置" prop="archiveLocation">
        <el-input v-model="formData.archiveLocation" placeholder="请输入归档位置" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { CaseClosureApi, CaseClosureVO } from '@/api/smartcity/caseclosure'

/** 案件结案 表单 */
defineOptions({ name: 'CaseClosureForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  caseId: undefined,
  closureReason: undefined,
  closureDepartment: undefined,
  closurePerson: undefined,
  closureTime: undefined,
  approvalPerson: undefined,
  approvalTime: undefined,
  approvalOpinion: undefined,
  archiveNumber: undefined,
  archiveLocation: undefined,
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
      formData.value = await CaseClosureApi.getCaseClosure(id)
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
    const data = formData.value as unknown as CaseClosureVO
    if (formType.value === 'create') {
      await CaseClosureApi.createCaseClosure(data)
      message.success(t('common.createSuccess'))
    } else {
      await CaseClosureApi.updateCaseClosure(data)
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
    caseId: undefined,
    closureReason: undefined,
    closureDepartment: undefined,
    closurePerson: undefined,
    closureTime: undefined,
    approvalPerson: undefined,
    approvalTime: undefined,
    approvalOpinion: undefined,
    archiveNumber: undefined,
    archiveLocation: undefined,
  }
  formRef.value?.resetFields()
}
</script>
