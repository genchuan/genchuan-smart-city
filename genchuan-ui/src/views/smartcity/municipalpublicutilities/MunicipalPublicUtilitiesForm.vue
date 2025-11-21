<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="市政设施名称" prop="nameMunicipalFacilities">
        <el-input v-model="formData.nameMunicipalFacilities" placeholder="请输入市政设施名称" />
      </el-form-item>
      <el-form-item label="设施类型" prop="facilityType">
        <el-select v-model="formData.facilityType" placeholder="请选择设施类型">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="设施位置" prop="facilityLocation">
        <el-input v-model="formData.facilityLocation" placeholder="请输入设施位置" />
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
      <el-form-item label="承载能力" prop="bearingCapacity">
        <el-input v-model="formData.bearingCapacity" placeholder="请输入承载能力" />
      </el-form-item>
      <el-form-item label="服务范围" prop="serviceScope">
        <el-input v-model="formData.serviceScope" placeholder="请输入服务范围" />
      </el-form-item>
      <el-form-item label="服务人口" prop="servicePopulation">
        <el-input v-model="formData.servicePopulation" placeholder="请输入服务人口" />
      </el-form-item>
      <el-form-item label="资源配置" prop="resourceAllocation">
        <el-input v-model="formData.resourceAllocation" placeholder="请输入资源配置" />
      </el-form-item>
      <el-form-item label="资金投入" prop="capitalInvestment">
        <el-input v-model="formData.capitalInvestment" placeholder="请输入资金投入" />
      </el-form-item>
      <el-form-item label="预算安排" prop="budgetArrangement">
        <el-input v-model="formData.budgetArrangement" placeholder="请输入预算安排" />
      </el-form-item>
      <el-form-item label="效益评估" prop="benefitsAssessment">
        <el-input v-model="formData.benefitsAssessment" placeholder="请输入效益评估" />
      </el-form-item>
      <el-form-item label="政策法规依据" prop="policyRegulatoryBasis">
        <el-input v-model="formData.policyRegulatoryBasis" placeholder="请输入政策法规依据" />
      </el-form-item>
      <el-form-item label="行业标准规范" prop="industryStandardSpecifications">
        <el-input v-model="formData.industryStandardSpecifications" placeholder="请输入行业标准规范" />
      </el-form-item>
      <el-form-item label="安全隐患" prop="potentialSafetyHazard">
        <el-input v-model="formData.potentialSafetyHazard" placeholder="请输入安全隐患" />
      </el-form-item>
      <el-form-item label="风险评估" prop="riskAssessment">
        <el-input v-model="formData.riskAssessment" placeholder="请输入风险评估" />
      </el-form-item>
      <el-form-item label="应急处置预案" prop="emergencyResponsePlan">
        <el-input v-model="formData.emergencyResponsePlan" placeholder="请输入应急处置预案" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { MunicipalPublicUtilitiesApi, MunicipalPublicUtilitiesVO } from '@/api/smartcity/municipalpublicutilities'

/** 市政公用 表单 */
defineOptions({ name: 'MunicipalPublicUtilitiesForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  nameMunicipalFacilities: undefined,
  facilityType: undefined,
  facilityLocation: undefined,
  constructionTime: undefined,
  serviceLife: undefined,
  bearingCapacity: undefined,
  serviceScope: undefined,
  servicePopulation: undefined,
  resourceAllocation: undefined,
  capitalInvestment: undefined,
  budgetArrangement: undefined,
  benefitsAssessment: undefined,
  policyRegulatoryBasis: undefined,
  industryStandardSpecifications: undefined,
  potentialSafetyHazard: undefined,
  riskAssessment: undefined,
  emergencyResponsePlan: undefined
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
      formData.value = await MunicipalPublicUtilitiesApi.getMunicipalPublicUtilities(id)
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
    const data = formData.value as unknown as MunicipalPublicUtilitiesVO
    if (formType.value === 'create') {
      await MunicipalPublicUtilitiesApi.createMunicipalPublicUtilities(data)
      message.success(t('common.createSuccess'))
    } else {
      await MunicipalPublicUtilitiesApi.updateMunicipalPublicUtilities(data)
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
    nameMunicipalFacilities: undefined,
    facilityType: undefined,
    facilityLocation: undefined,
    constructionTime: undefined,
    serviceLife: undefined,
    bearingCapacity: undefined,
    serviceScope: undefined,
    servicePopulation: undefined,
    resourceAllocation: undefined,
    capitalInvestment: undefined,
    budgetArrangement: undefined,
    benefitsAssessment: undefined,
    policyRegulatoryBasis: undefined,
    industryStandardSpecifications: undefined,
    potentialSafetyHazard: undefined,
    riskAssessment: undefined,
    emergencyResponsePlan: undefined
  }
  formRef.value?.resetFields()
}
</script>