<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="风险类型ID" prop="riskTypeId">
        <el-input v-model="formData.riskTypeId" placeholder="请输入风险类型ID" />
      </el-form-item>
      <el-form-item label="风险类型名称" prop="riskTypeName">
        <el-input v-model="formData.riskTypeName" placeholder="请输入风险类型名称" />
      </el-form-item>
      <el-form-item label="风险编码" prop="riskCode">
        <el-input v-model="formData.riskCode" placeholder="请输入风险编码" />
      </el-form-item>
      <el-form-item label="所属分域ID" prop="domainId">
        <el-input v-model="formData.domainId" placeholder="请输入所属分域ID" />
      </el-form-item>
      <el-form-item label="所属分域名称" prop="domainName">
        <el-input v-model="formData.domainName" placeholder="请输入所属分域名称" />
      </el-form-item>
      <el-form-item label="风险描述" prop="riskDesc">
        <el-input v-model="formData.riskDesc" placeholder="请输入风险描述" />
      </el-form-item>
      <el-form-item label="应对措施" prop="responseMeasures">
        <el-input v-model="formData.responseMeasures" placeholder="请输入应对措施" />
      </el-form-item>
      <el-form-item label="启用状态" prop="enableStatus">
        <el-radio-group v-model="formData.enableStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { RiskTypeApi, RiskTypeVO } from '@/api/dataHub/earlyWarningAlarm/risktype'

/** 风险类型库管理 表单 */
defineOptions({ name: 'RiskTypeForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  riskTypeId: undefined,
  riskTypeName: undefined,
  riskCode: undefined,
  domainId: undefined,
  domainName: undefined,
  riskDesc: undefined,
  responseMeasures: undefined,
  enableStatus: undefined,
})
const formRules = reactive({
  riskTypeId: [{ required: true, message: '风险类型ID不能为空', trigger: 'blur' }],
  riskTypeName: [{ required: true, message: '风险类型名称不能为空', trigger: 'blur' }],
  riskCode: [{ required: true, message: '风险编码不能为空', trigger: 'blur' }],
  domainId: [{ required: true, message: '所属分域ID不能为空', trigger: 'blur' }],
  enableStatus: [{ required: true, message: '启用状态不能为空', trigger: 'blur' }],
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
      formData.value = await RiskTypeApi.getRiskType(id)
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
    const data = formData.value as unknown as RiskTypeVO
    if (formType.value === 'create') {
      await RiskTypeApi.createRiskType(data)
      message.success(t('common.createSuccess'))
    } else {
      await RiskTypeApi.updateRiskType(data)
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
    riskTypeId: undefined,
    riskTypeName: undefined,
    riskCode: undefined,
    domainId: undefined,
    domainName: undefined,
    riskDesc: undefined,
    responseMeasures: undefined,
    enableStatus: undefined,
  }
  formRef.value?.resetFields()
}
</script>
