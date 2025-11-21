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
      <el-form-item label="处理类型" prop="disposalType">
        <el-select
          v-model="formData.disposalType"
          placeholder="请选择处理类型"
          clearable
          class="common-Width100"
        >
          <el-option value="administrative_penalty" label="行政处罚" />
          <el-option value="rectification_notice" label="整改通知" />
          <el-option value="no_penalty" label="不予处罚" />
          <el-option value="other_disposal" label="其他处理" />
        </el-select>
      </el-form-item>
      <el-form-item label="处理部门" prop="disposalDepartment">
        <el-input v-model="formData.disposalDepartment" placeholder="请输入处理部门" />
      </el-form-item>
      <el-form-item label="处理人" prop="disposalPerson">
        <el-input v-model="formData.disposalPerson" placeholder="请输入处理人" />
      </el-form-item>
      <el-form-item label="处理开始时间" prop="disposalStartTime">
        <el-date-picker
          v-model="formData.disposalStartTime"
          type="date"
          value-format="x"
          placeholder="选择处理开始时间"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="处理结束时间" prop="disposalEndTime">
        <el-date-picker
          v-model="formData.disposalEndTime"
          type="date"
          value-format="x"
          placeholder="选择处理结束时间"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="处理依据" prop="disposalBasis">
        <el-input v-model="formData.disposalBasis" placeholder="请输入处理依据" />
      </el-form-item>
      <el-form-item label="处理内容" prop="disposalContent">
        <el-input v-model="formData.disposalContent" type="textarea" placeholder="请输入处理内容" />
      </el-form-item>
      <el-form-item label="处理结果" prop="disposalResult">
        <el-input v-model="formData.disposalResult" placeholder="请输入处理结果" />
      </el-form-item>
      <el-form-item label="处罚金额" prop="penaltyAmount">
        <el-input v-model="formData.penaltyAmount" placeholder="请输入处罚金额" />
      </el-form-item>
      <el-form-item label="处罚类型" prop="penaltyType">
        <el-input v-model="formData.penaltyType" placeholder="请输入处罚类型" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { CaseDisposalApi, CaseDisposalVO } from '@/api/smartcity/casedisposal'

/** 案件处理 表单 */
defineOptions({ name: 'CaseDisposalForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  caseId: undefined,
  disposalType: undefined,
  disposalDepartment: undefined,
  disposalPerson: undefined,
  disposalStartTime: undefined,
  disposalEndTime: undefined,
  disposalBasis: undefined,
  disposalContent: undefined,
  disposalResult: undefined,
  penaltyAmount: undefined,
  penaltyType: undefined,
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
      formData.value = await CaseDisposalApi.getCaseDisposal(id)
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
    const data = formData.value as unknown as CaseDisposalVO
    if (formType.value === 'create') {
      await CaseDisposalApi.createCaseDisposal(data)
      message.success(t('common.createSuccess'))
    } else {
      await CaseDisposalApi.updateCaseDisposal(data)
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
    disposalType: undefined,
    disposalDepartment: undefined,
    disposalPerson: undefined,
    disposalStartTime: undefined,
    disposalEndTime: undefined,
    disposalBasis: undefined,
    disposalContent: undefined,
    disposalResult: undefined,
    penaltyAmount: undefined,
    penaltyType: undefined,
  }
  formRef.value?.resetFields()
}
</script>
