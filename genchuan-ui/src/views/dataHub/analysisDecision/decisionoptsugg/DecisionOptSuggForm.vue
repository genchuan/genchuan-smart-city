<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="建议ID" prop="optSuggId">
        <el-input v-model="formData.optSuggId" placeholder="请输入建议ID" />
      </el-form-item>
      <el-form-item label="关联评估ID" prop="relEvalId">
        <el-input v-model="formData.relEvalId" placeholder="请输入关联评估ID" />
      </el-form-item>
      <el-form-item label="关联方案ID" prop="relSchemeId">
        <el-input v-model="formData.relSchemeId" placeholder="请输入关联方案ID" />
      </el-form-item>
      <el-form-item label="关联方案名称" prop="relSchemeName">
        <el-input v-model="formData.relSchemeName" placeholder="请输入关联方案名称" />
      </el-form-item>
      <el-form-item label="建议类型" prop="suggType">
        <el-select v-model="formData.suggType" placeholder="请选择建议类型">
          <el-option label="请选择字典生成" value="1" />
        </el-select>
      </el-form-item>
      <el-form-item label="建议内容" prop="suggContent">
        <Editor v-model="formData.suggContent" height="150px" />
      </el-form-item>
      <el-form-item label="参考案例ID" prop="refCaseId">
        <el-input v-model="formData.refCaseId" placeholder="请输入参考案例ID" />
      </el-form-item>
      <el-form-item label="参考案例名称" prop="refCaseName">
        <el-input v-model="formData.refCaseName" placeholder="请输入参考案例名称" />
      </el-form-item>
      <el-form-item label="建议优先级" prop="suggPriority">
        <el-input v-model="formData.suggPriority" placeholder="请输入建议优先级" />
      </el-form-item>
      <el-form-item label="建议状态" prop="suggStatus">
        <el-radio-group v-model="formData.suggStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="提出人" prop="proposer">
        <el-input v-model="formData.proposer" placeholder="请输入提出人" />
      </el-form-item>
      <el-form-item label="提出人姓名" prop="proposerName">
        <el-input v-model="formData.proposerName" placeholder="请输入提出人姓名" />
      </el-form-item>
      <el-form-item label="提出时间" prop="proposeTime">
        <el-date-picker
          v-model="formData.proposeTime"
          type="date"
          value-format="x"
          placeholder="选择提出时间"
        />
      </el-form-item>
      <el-form-item label="处理人" prop="handler">
        <el-input v-model="formData.handler" placeholder="请输入处理人" />
      </el-form-item>
      <el-form-item label="处理时间" prop="handleTime">
        <el-date-picker
          v-model="formData.handleTime"
          type="date"
          value-format="x"
          placeholder="选择处理时间"
        />
      </el-form-item>
      <el-form-item label="处理意见" prop="handleOpinion">
        <el-input v-model="formData.handleOpinion" placeholder="请输入处理意见" />
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
import { DecisionOptSuggApi, DecisionOptSuggVO } from '@/api/dataHub/analysisDecision/decisionoptsugg'

/** 决策优化建议 表单 */
defineOptions({ name: 'DecisionOptSuggForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  optSuggId: undefined,
  relEvalId: undefined,
  relSchemeId: undefined,
  relSchemeName: undefined,
  suggType: undefined,
  suggContent: undefined,
  refCaseId: undefined,
  refCaseName: undefined,
  suggPriority: undefined,
  suggStatus: undefined,
  proposer: undefined,
  proposerName: undefined,
  proposeTime: undefined,
  handler: undefined,
  handleTime: undefined,
  handleOpinion: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  optSuggId: [{ required: true, message: '建议ID不能为空', trigger: 'blur' }],
  relEvalId: [{ required: true, message: '关联评估ID不能为空', trigger: 'blur' }],
  relSchemeId: [{ required: true, message: '关联方案ID不能为空', trigger: 'blur' }],
  relSchemeName: [{ required: true, message: '关联方案名称不能为空', trigger: 'blur' }],
  suggType: [{ required: true, message: '建议类型不能为空', trigger: 'change' }],
  suggContent: [{ required: true, message: '建议内容不能为空', trigger: 'blur' }],
  suggPriority: [{ required: true, message: '建议优先级不能为空', trigger: 'blur' }],
  suggStatus: [{ required: true, message: '建议状态不能为空', trigger: 'blur' }],
  proposer: [{ required: true, message: '提出人不能为空', trigger: 'blur' }],
  proposerName: [{ required: true, message: '提出人姓名不能为空', trigger: 'blur' }],
  proposeTime: [{ required: true, message: '提出时间不能为空', trigger: 'blur' }],
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
      formData.value = await DecisionOptSuggApi.getDecisionOptSugg(id)
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
    const data = formData.value as unknown as DecisionOptSuggVO
    if (formType.value === 'create') {
      await DecisionOptSuggApi.createDecisionOptSugg(data)
      message.success(t('common.createSuccess'))
    } else {
      await DecisionOptSuggApi.updateDecisionOptSugg(data)
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
    optSuggId: undefined,
    relEvalId: undefined,
    relSchemeId: undefined,
    relSchemeName: undefined,
    suggType: undefined,
    suggContent: undefined,
    refCaseId: undefined,
    refCaseName: undefined,
    suggPriority: undefined,
    suggStatus: undefined,
    proposer: undefined,
    proposerName: undefined,
    proposeTime: undefined,
    handler: undefined,
    handleTime: undefined,
    handleOpinion: undefined,
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
