<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="150px"
      v-loading="formLoading"
    >
      <el-form-item label="企业名称" prop="entName">
        <el-input v-model="formData.entName" placeholder="请输入企业名称" />
      </el-form-item>
      <el-form-item label="统一社会信用代码" prop="creditCode">
        <el-input v-model="formData.creditCode" placeholder="请输入统一社会信用代码" />
      </el-form-item>
      <el-form-item label="法定代表人" prop="legalPerson">
        <el-input v-model="formData.legalPerson" placeholder="请输入法定代表人" />
      </el-form-item>
      <el-form-item label="注册地址" prop="regAddress">
        <el-input v-model="formData.regAddress" placeholder="请输入注册地址" />
      </el-form-item>
      <el-form-item label="经营范围" prop="businessScope">
        <el-input v-model="formData.businessScope" placeholder="请输入经营范围" />
      </el-form-item>
      <!-- 行业类型选择框 -->
      <el-form-item label="行业类型" prop="industryType">
        <el-select v-model="formData.industryType" placeholder="请选择行业类型">
          <el-option value="manufacturing" label="制造业" />
          <el-option value="construction" label="建筑业" />
          <el-option value="transportation" label="交通运输业" />
          <el-option value="logistics" label="物流业" />
          <el-option value="retail" label="零售业" />
          <el-option value="finance" label="金融业" />
          <el-option value="it_service" label="IT服务业" />
          <el-option value="education" label="教育行业" />
          <el-option value="medical_health" label="医疗卫生业" />
          <el-option value="hotel_catering" label="酒店餐饮业" />
          <el-option value="cultural_tourism" label="文化旅游业" />
          <el-option value="energy" label="能源行业" />
          <el-option value="environmental_protection" label="环保行业" />
          <el-option value="agriculture" label="农业" />
          <el-option value="real_estate" label="房地产业" />
          <el-option value="public_service" label="公共服务业" />
        </el-select>
      </el-form-item>
      <!-- 风险等级选择框 -->
      <el-form-item label="风险等级" prop="riskLevel">
        <el-select v-model="formData.riskLevel" placeholder="请选择风险等级">
          <el-option value="level0" label="未评定" />
          <el-option value="level1" label="低风险" />
          <el-option value="level2" label="中低风险" />
          <el-option value="level3" label="中风险" />
          <el-option value="level4" label="中高风险" />
          <el-option value="level5" label="高风险" />
        </el-select>
      </el-form-item>
      <el-form-item label="联系人" prop="contactPerson">
        <el-input v-model="formData.contactPerson" placeholder="请输入联系人" />
      </el-form-item>
      <el-form-item label="联系电话" prop="contactPhone">
        <el-input v-model="formData.contactPhone" placeholder="请输入联系电话" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { InspectionObjectApi, InspectionObjectVO } from '@/api/smartcity/inspectionobject'

/** 双随机行政检查 表单 */
defineOptions({ name: 'InspectionObjectForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  entName: undefined,
  creditCode: undefined,
  legalPerson: undefined,
  regAddress: undefined,
  businessScope: undefined,
  industryType: undefined,
  riskLevel: undefined,
  contactPerson: undefined,
  contactPhone: undefined
})
const formRules = reactive({
  industryType: [{ required: true, message: '请选择行业类型', trigger: 'change' }],
  riskLevel: [{ required: true, message: '请选择风险等级', trigger: 'change' }]
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
      formData.value = await InspectionObjectApi.getInspectionObject(id)
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
    const data = formData.value as unknown as InspectionObjectVO
    if (formType.value === 'create') {
      await InspectionObjectApi.createInspectionObject(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectionObjectApi.updateInspectionObject(data)
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
    entName: undefined,
    creditCode: undefined,
    legalPerson: undefined,
    regAddress: undefined,
    businessScope: undefined,
    industryType: undefined,
    riskLevel: undefined,
    contactPerson: undefined,
    contactPhone: undefined
  }
  formRef.value?.resetFields()
}
</script>
