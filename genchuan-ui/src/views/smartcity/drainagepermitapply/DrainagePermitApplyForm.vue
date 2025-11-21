<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="180px"
      v-loading="formLoading"
    >
      <el-form-item label="申请编号" prop="applyNo">
        <el-input v-model="formData.applyNo" placeholder="请输入申请编号" />
      </el-form-item>
      <el-form-item label="排水户名称" prop="userName">
        <el-input v-model="formData.userName" placeholder="请输入排水户名称" />
      </el-form-item>
      <el-form-item label="排水水质检测报告文件" prop="waterQualityReport">
        <UploadFile v-model="formData.waterQualityReport" />
      </el-form-item>
      <el-form-item label="日均排水量（吨）" prop="dailyDrainage">
        <el-input v-model="formData.dailyDrainage" placeholder="请输入日均排水量（吨）" />
      </el-form-item>
      <el-form-item label="重点排污单位证明文件路径" prop="pollutionProof">
        <el-input v-model="formData.pollutionProof" placeholder="请输入重点排污单位证明文件路径" />
      </el-form-item>
      <el-form-item label="历史违规记录" prop="violationHistory">
        <el-input v-model="formData.violationHistory" placeholder="请输入历史违规记录" />
      </el-form-item>
      <el-form-item label="申请状态" prop="applyStatus">
        <el-select v-model="formData.applyStatus" placeholder="请选择申请状态">
          <el-option
            v-for="dict in getStrDictOptions(DICT_TYPE.CRM_AUDIT_STATUS)"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="审核人" prop="approver">
        <el-input v-model="formData.approver" placeholder="请输入审核人" />
      </el-form-item>
      <el-form-item label="审核时间" prop="approveTime">
        <el-date-picker
          v-model="formData.approveTime"
          type="date"
          value-format="x"
          placeholder="选择审核时间"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="审核意见" prop="approveComment">
        <el-input v-model="formData.approveComment" placeholder="请输入审核意见" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { getStrDictOptions, DICT_TYPE } from '@/utils/dict'
import { DrainagePermitApplyApi, DrainagePermitApplyVO } from '@/api/smartcity/drainagepermitapply'

/** 排水许可证申请 表单 */
defineOptions({ name: 'DrainagePermitApplyForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  applyNo: undefined,
  userName: undefined,
  waterQualityReport: undefined,
  dailyDrainage: undefined,
  pollutionProof: undefined,
  violationHistory: undefined,
  applyStatus: undefined,
  approver: undefined,
  approveTime: undefined,
  approveComment: undefined,
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
      formData.value = await DrainagePermitApplyApi.getDrainagePermitApply(id)
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
    const data = formData.value as unknown as DrainagePermitApplyVO
    if (formType.value === 'create') {
      await DrainagePermitApplyApi.createDrainagePermitApply(data)
      message.success(t('common.createSuccess'))
    } else {
      await DrainagePermitApplyApi.updateDrainagePermitApply(data)
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
    applyNo: undefined,
    userName: undefined,
    waterQualityReport: undefined,
    dailyDrainage: undefined,
    pollutionProof: undefined,
    violationHistory: undefined,
    applyStatus: undefined,
    approver: undefined,
    approveTime: undefined,
    approveComment: undefined,
  }
  formRef.value?.resetFields()
}
</script>
