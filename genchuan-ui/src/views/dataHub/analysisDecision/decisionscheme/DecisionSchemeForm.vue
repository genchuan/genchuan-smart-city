<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="方案ID" prop="decisionSchemeId">
        <el-input v-model="formData.decisionSchemeId" placeholder="请输入方案ID" />
      </el-form-item>
      <el-form-item label="方案名称" prop="schemeName">
        <el-input v-model="formData.schemeName" placeholder="请输入方案名称" />
      </el-form-item>
      <el-form-item label="关联分析ID" prop="relAnalysisId">
        <el-input v-model="formData.relAnalysisId" placeholder="请输入关联分析ID" />
      </el-form-item>
      <el-form-item label="关联分析类型" prop="relAnalysisType">
        <el-select v-model="formData.relAnalysisType" placeholder="请选择关联分析类型">
          <el-option label="请选择字典生成" value="1" />
        </el-select>
      </el-form-item>
      <el-form-item label="方案目标" prop="schemeGoal">
        <el-input v-model="formData.schemeGoal" placeholder="请输入方案目标" />
      </el-form-item>
      <el-form-item label="实施措施" prop="implementationMeasures">
        <el-input v-model="formData.implementationMeasures" placeholder="请输入实施措施" />
      </el-form-item>
      <el-form-item label="资源需求" prop="resDemand">
        <el-input v-model="formData.resDemand" placeholder="请输入资源需求" />
      </el-form-item>
      <el-form-item label="预期效果" prop="expectedEffect">
        <el-input v-model="formData.expectedEffect" placeholder="请输入预期效果" />
      </el-form-item>
      <el-form-item label="方案状态" prop="schemeStatus">
        <el-radio-group v-model="formData.schemeStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="申请人ID" prop="applyUserId">
        <el-input v-model="formData.applyUserId" placeholder="请输入申请人ID" />
      </el-form-item>
      <el-form-item label="申请人姓名" prop="applyUserName">
        <el-input v-model="formData.applyUserName" placeholder="请输入申请人姓名" />
      </el-form-item>
      <el-form-item label="申请时间" prop="applyTime">
        <el-date-picker
          v-model="formData.applyTime"
          type="date"
          value-format="x"
          placeholder="选择申请时间"
        />
      </el-form-item>
      <el-form-item label="审核人ID" prop="auditUserId">
        <el-input v-model="formData.auditUserId" placeholder="请输入审核人ID" />
      </el-form-item>
      <el-form-item label="审核人姓名" prop="auditUserName">
        <el-input v-model="formData.auditUserName" placeholder="请输入审核人姓名" />
      </el-form-item>
      <el-form-item label="审核时间" prop="auditTime">
        <el-date-picker
          v-model="formData.auditTime"
          type="date"
          value-format="x"
          placeholder="选择审核时间"
        />
      </el-form-item>
      <el-form-item label="审核意见" prop="auditOpinion">
        <el-input v-model="formData.auditOpinion" placeholder="请输入审核意见" />
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
import { DecisionSchemeApi, DecisionSchemeVO } from '@/api/dataHub/analysisDecision/decisionscheme'

/** 决策方案生成 表单 */
defineOptions({ name: 'DecisionSchemeForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  decisionSchemeId: undefined,
  schemeName: undefined,
  relAnalysisId: undefined,
  relAnalysisType: undefined,
  schemeGoal: undefined,
  implementationMeasures: undefined,
  resDemand: undefined,
  expectedEffect: undefined,
  schemeStatus: undefined,
  applyUserId: undefined,
  applyUserName: undefined,
  applyTime: undefined,
  auditUserId: undefined,
  auditUserName: undefined,
  auditTime: undefined,
  auditOpinion: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  decisionSchemeId: [{ required: true, message: '方案ID不能为空', trigger: 'blur' }],
  schemeName: [{ required: true, message: '方案名称不能为空', trigger: 'blur' }],
  relAnalysisId: [{ required: true, message: '关联分析ID不能为空', trigger: 'blur' }],
  relAnalysisType: [{ required: true, message: '关联分析类型不能为空', trigger: 'change' }],
  schemeGoal: [{ required: true, message: '方案目标不能为空', trigger: 'blur' }],
  implementationMeasures: [{ required: true, message: '实施措施不能为空', trigger: 'blur' }],
  resDemand: [{ required: true, message: '资源需求不能为空', trigger: 'blur' }],
  expectedEffect: [{ required: true, message: '预期效果不能为空', trigger: 'blur' }],
  schemeStatus: [{ required: true, message: '方案状态不能为空', trigger: 'blur' }],
  applyUserId: [{ required: true, message: '申请人ID不能为空', trigger: 'blur' }],
  applyUserName: [{ required: true, message: '申请人姓名，与申请人ID同步，用户信息表（sys_user）不能为空', trigger: 'blur' }],
  applyTime: [{ required: true, message: '申请时间不能为空', trigger: 'blur' }],
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
      formData.value = await DecisionSchemeApi.getDecisionScheme(id)
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
    const data = formData.value as unknown as DecisionSchemeVO
    if (formType.value === 'create') {
      await DecisionSchemeApi.createDecisionScheme(data)
      message.success(t('common.createSuccess'))
    } else {
      await DecisionSchemeApi.updateDecisionScheme(data)
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
    decisionSchemeId: undefined,
    schemeName: undefined,
    relAnalysisId: undefined,
    relAnalysisType: undefined,
    schemeGoal: undefined,
    implementationMeasures: undefined,
    resDemand: undefined,
    expectedEffect: undefined,
    schemeStatus: undefined,
    applyUserId: undefined,
    applyUserName: undefined,
    applyTime: undefined,
    auditUserId: undefined,
    auditUserName: undefined,
    auditTime: undefined,
    auditOpinion: undefined,
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
