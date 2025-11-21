<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="设施名称" prop="facilityName">
        <el-input v-model="formData.facilityName" placeholder="请输入设施名称" />
      </el-form-item>
      <el-form-item label="设施位置" prop="facilityLocation">
        <el-input v-model="formData.facilityLocation" placeholder="请输入设施位置" />
      </el-form-item>
      <el-form-item label="数量" prop="quantity">
        <el-input v-model="formData.quantity" placeholder="请输入数量" />
      </el-form-item>
      <el-form-item label="建设时间" prop="constructionTime">
        <el-date-picker
          v-model="formData.constructionTime"
          type="date"
          value-format="x"
          placeholder="选择建设时间"
        />
      </el-form-item>
      <el-form-item label="使用年限" prop="serviceLife">
        <el-input v-model="formData.serviceLife" placeholder="请输入使用年限" />
      </el-form-item>
      <el-form-item label="处理能力" prop="processingCapacity">
        <el-input v-model="formData.processingCapacity" placeholder="请输入处理能力" />
      </el-form-item>
      <el-form-item label="维护需求" prop="maintenanceRequirements">
        <el-input v-model="formData.maintenanceRequirements" placeholder="请输入维护需求" />
      </el-form-item>
      <el-form-item label="年度预算金额" prop="annualBudgetAmount">
        <el-input v-model="formData.annualBudgetAmount" placeholder="请输入年度预算金额" />
      </el-form-item>
      <el-form-item label="成本效益分析" prop="costBenefitAnalysis">
        <el-input v-model="formData.costBenefitAnalysis" placeholder="请输入成本效益分析" />
      </el-form-item>
      <el-form-item label="对周边环境影响评估" prop="assessmentSurEnvironment">
        <el-input v-model="formData.assessmentSurEnvironment" placeholder="请输入对周边环境影响评估" />
      </el-form-item>
      <el-form-item label="节能减排指标" prop="energyConservationTargets">
        <el-input v-model="formData.energyConservationTargets" placeholder="请输入节能减排指标" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { EnvironmentalSanitationApi, EnvironmentalSanitationVO } from '@/api/smartcity/environmentalsanitation'

/** 市容环卫 表单 */
defineOptions({ name: 'EnvironmentalSanitationForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  facilityName: undefined,
  facilityLocation: undefined,
  quantity: undefined,
  constructionTime: undefined,
  serviceLife: undefined,
  processingCapacity: undefined,
  maintenanceRequirements: undefined,
  annualBudgetAmount: undefined,
  costBenefitAnalysis: undefined,
  assessmentSurEnvironment: undefined,
  energyConservationTargets: undefined
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
      formData.value = await EnvironmentalSanitationApi.getEnvironmentalSanitation(id)
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
    const data = formData.value as unknown as EnvironmentalSanitationVO
    if (formType.value === 'create') {
      await EnvironmentalSanitationApi.createEnvironmentalSanitation(data)
      message.success(t('common.createSuccess'))
    } else {
      await EnvironmentalSanitationApi.updateEnvironmentalSanitation(data)
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
    facilityName: undefined,
    facilityLocation: undefined,
    quantity: undefined,
    constructionTime: undefined,
    serviceLife: undefined,
    processingCapacity: undefined,
    maintenanceRequirements: undefined,
    annualBudgetAmount: undefined,
    costBenefitAnalysis: undefined,
    assessmentSurEnvironment: undefined,
    energyConservationTargets: undefined
  }
  formRef.value?.resetFields()
}
</script>