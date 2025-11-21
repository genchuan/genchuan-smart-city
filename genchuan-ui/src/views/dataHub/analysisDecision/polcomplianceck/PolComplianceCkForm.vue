<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="校验ID" prop="complianceCkId">
        <el-input v-model="formData.complianceCkId" placeholder="请输入校验ID" />
      </el-form-item>
      <el-form-item label="关联方案ID" prop="relSchemeId">
        <el-input v-model="formData.relSchemeId" placeholder="请输入关联方案ID" />
      </el-form-item>
      <el-form-item label="关联方案名称" prop="relSchemeName">
        <el-input v-model="formData.relSchemeName" placeholder="请输入关联方案名称" />
      </el-form-item>
      <el-form-item label="校验政策IDs" prop="ckPolIds">
        <el-input v-model="formData.ckPolIds" placeholder="请输入校验政策IDs" />
      </el-form-item>
      <el-form-item label="校验政策名称" prop="ckPolNames">
        <el-input v-model="formData.ckPolNames" placeholder="请输入校验政策名称" />
      </el-form-item>
      <el-form-item label="校验状态" prop="ckStatus">
        <el-radio-group v-model="formData.ckStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="风险等级" prop="riskLevel">
        <el-input v-model="formData.riskLevel" placeholder="请输入风险等级" />
      </el-form-item>
      <el-form-item label="风险点数量" prop="riskPtCount">
        <el-input v-model="formData.riskPtCount" placeholder="请输入风险点数量" />
      </el-form-item>
      <el-form-item label="合规率" prop="complianceRate">
        <el-input v-model="formData.complianceRate" placeholder="请输入合规率" />
      </el-form-item>
      <el-form-item label="校验结论" prop="ckConclusion">
        <el-input v-model="formData.ckConclusion" placeholder="请输入校验结论" />
      </el-form-item>
      <el-form-item label="校验人" prop="ckUser">
        <el-input v-model="formData.ckUser" placeholder="请输入校验人" />
      </el-form-item>
      <el-form-item label="校验时间" prop="ckTime">
        <el-date-picker
          v-model="formData.ckTime"
          type="date"
          value-format="x"
          placeholder="选择校验时间"
        />
      </el-form-item>
      <el-form-item label="整改建议" prop="rectificationSugg">
        <el-input v-model="formData.rectificationSugg" placeholder="请输入整改建议" />
      </el-form-item>
      <el-form-item label="复核状态" prop="reviewStatus">
        <el-radio-group v-model="formData.reviewStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="复核人" prop="reviewUser">
        <el-input v-model="formData.reviewUser" placeholder="请输入复核人" />
      </el-form-item>
      <el-form-item label="复核时间" prop="reviewTime">
        <el-date-picker
          v-model="formData.reviewTime"
          type="date"
          value-format="x"
          placeholder="选择复核时间"
        />
      </el-form-item>
      <el-form-item label="分类扩展字段1" prop="extCat1">
        <el-input v-model="formData.extCat1" placeholder="请输入分类扩展字段1" />
      </el-form-item>
      <el-form-item label="分类扩展字段2" prop="extCat2">
        <el-input v-model="formData.extCat2" placeholder="请输入分类扩展字段2" />
      </el-form-item>
      <el-form-item label="通用扩展字段1" prop="extCommon1">
        <el-input v-model="formData.extCommon1" placeholder="请输入通用扩展字段1" />
      </el-form-item>
      <el-form-item label="通用扩展字段2" prop="extCommon2">
        <el-input v-model="formData.extCommon2" placeholder="请输入通用扩展字段2" />
      </el-form-item>
      <el-form-item label="系统创建时间" prop="createTimeSys">
        <el-date-picker
          v-model="formData.createTimeSys"
          type="date"
          value-format="x"
          placeholder="选择系统创建时间"
        />
      </el-form-item>
      <el-form-item label="系统更新时间" prop="updateTimeSys">
        <el-date-picker
          v-model="formData.updateTimeSys"
          type="date"
          value-format="x"
          placeholder="选择系统更新时间"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { PolComplianceCkApi, PolComplianceCkVO } from '@/api/dataHub/analysisDecision/polcomplianceck'

/** 政策合规校验 表单 */
defineOptions({ name: 'PolComplianceCkForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  complianceCkId: undefined,
  relSchemeId: undefined,
  relSchemeName: undefined,
  ckPolIds: undefined,
  ckPolNames: undefined,
  ckStatus: undefined,
  riskLevel: undefined,
  riskPtCount: undefined,
  complianceRate: undefined,
  ckConclusion: undefined,
  ckUser: undefined,
  ckTime: undefined,
  rectificationSugg: undefined,
  reviewStatus: undefined,
  reviewUser: undefined,
  reviewTime: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  complianceCkId: [{ required: true, message: '校验ID不能为空', trigger: 'blur' }],
  relSchemeId: [{ required: true, message: '关联方案ID不能为空', trigger: 'blur' }],
  relSchemeName: [{ required: true, message: '关联方案名称不能为空', trigger: 'blur' }],
  ckPolIds: [{ required: true, message: '校验政策IDs不能为空', trigger: 'blur' }],
  ckPolNames: [{ required: true, message: '校验政策名称不能为空', trigger: 'blur' }],
  ckStatus: [{ required: true, message: '校验状态不能为空', trigger: 'blur' }],
  ckUser: [{ required: true, message: '校验人不能为空', trigger: 'blur' }],
  ckTime: [{ required: true, message: '校验时间不能为空', trigger: 'blur' }],
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
      formData.value = await PolComplianceCkApi.getPolComplianceCk(id)
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
    const data = formData.value as unknown as PolComplianceCkVO
    if (formType.value === 'create') {
      await PolComplianceCkApi.createPolComplianceCk(data)
      message.success(t('common.createSuccess'))
    } else {
      await PolComplianceCkApi.updatePolComplianceCk(data)
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
    complianceCkId: undefined,
    relSchemeId: undefined,
    relSchemeName: undefined,
    ckPolIds: undefined,
    ckPolNames: undefined,
    ckStatus: undefined,
    riskLevel: undefined,
    riskPtCount: undefined,
    complianceRate: undefined,
    ckConclusion: undefined,
    ckUser: undefined,
    ckTime: undefined,
    rectificationSugg: undefined,
    reviewStatus: undefined,
    reviewUser: undefined,
    reviewTime: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined,
  }
  formRef.value?.resetFields()
}
</script>
