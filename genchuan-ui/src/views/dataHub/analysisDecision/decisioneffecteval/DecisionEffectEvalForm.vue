<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="评估ID" prop="effectEvalId">
        <el-input v-model="formData.effectEvalId" placeholder="请输入评估ID" />
      </el-form-item>
      <el-form-item label="关联方案ID" prop="relSchemeId">
        <el-input v-model="formData.relSchemeId" placeholder="请输入关联方案ID" />
      </el-form-item>
      <el-form-item label="关联方案名称" prop="relSchemeName">
        <el-input v-model="formData.relSchemeName" placeholder="请输入关联方案名称" />
      </el-form-item>
      <el-form-item label="实施周期" prop="implementationCycle">
        <el-input v-model="formData.implementationCycle" placeholder="请输入实施周期" />
      </el-form-item>
      <el-form-item label="预期效果" prop="expectedEffect">
        <el-input v-model="formData.expectedEffect" placeholder="请输入预期效果" />
      </el-form-item>
      <el-form-item label="实际效果" prop="actualEffect">
        <el-input v-model="formData.actualEffect" placeholder="请输入实际效果" />
      </el-form-item>
      <el-form-item label="效果达成率" prop="effectAchievementRate">
        <el-input v-model="formData.effectAchievementRate" placeholder="请输入效果达成率" />
      </el-form-item>
      <el-form-item label="资源总投入" prop="resourceTotalInput">
        <el-input v-model="formData.resourceTotalInput" placeholder="请输入资源总投入" />
      </el-form-item>
      <el-form-item label="投入回报率" prop="inputReturnRate">
        <el-input v-model="formData.inputReturnRate" placeholder="请输入投入回报率" />
      </el-form-item>
      <el-form-item label="用户满意度" prop="userSatisfy">
        <el-input v-model="formData.userSatisfy" placeholder="请输入用户满意度" />
      </el-form-item>
      <el-form-item label="评估等级" prop="evalGrade">
        <el-input v-model="formData.evalGrade" placeholder="请输入评估等级" />
      </el-form-item>
      <el-form-item label="成功经验" prop="successExp">
        <el-input v-model="formData.successExp" placeholder="请输入成功经验" />
      </el-form-item>
      <el-form-item label="改进点" prop="improvePts">
        <el-input v-model="formData.improvePts" placeholder="请输入改进点" />
      </el-form-item>
      <el-form-item label="评估人" prop="evalUser">
        <el-input v-model="formData.evalUser" placeholder="请输入评估人" />
      </el-form-item>
      <el-form-item label="评估时间" prop="evalTime">
        <el-date-picker
          v-model="formData.evalTime"
          type="date"
          value-format="x"
          placeholder="选择评估时间"
        />
      </el-form-item>
      <el-form-item label="评估报告附件" prop="evalRptAttach">
        <el-input v-model="formData.evalRptAttach" placeholder="请输入评估报告附件" />
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
import { DecisionEffectEvalApi, DecisionEffectEvalVO } from '@/api/dataHub/analysisDecision/decisioneffecteval'

/** 决策效果评估 表单 */
defineOptions({ name: 'DecisionEffectEvalForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  effectEvalId: undefined,
  relSchemeId: undefined,
  relSchemeName: undefined,
  implementationCycle: undefined,
  expectedEffect: undefined,
  actualEffect: undefined,
  effectAchievementRate: undefined,
  resourceTotalInput: undefined,
  inputReturnRate: undefined,
  userSatisfy: undefined,
  evalGrade: undefined,
  successExp: undefined,
  improvePts: undefined,
  evalUser: undefined,
  evalTime: undefined,
  evalRptAttach: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  effectEvalId: [{ required: true, message: '评估ID不能为空', trigger: 'blur' }],
  relSchemeId: [{ required: true, message: '关联方案ID不能为空', trigger: 'blur' }],
  relSchemeName: [{ required: true, message: '关联方案名称不能为空', trigger: 'blur' }],
  implementationCycle: [{ required: true, message: '实施周期不能为空', trigger: 'blur' }],
  expectedEffect: [{ required: true, message: '预期效果不能为空', trigger: 'blur' }],
  actualEffect: [{ required: true, message: '实际效果不能为空', trigger: 'blur' }],
  effectAchievementRate: [{ required: true, message: '效果达成率不能为空', trigger: 'blur' }],
  resourceTotalInput: [{ required: true, message: '资源总投入不能为空', trigger: 'blur' }],
  evalGrade: [{ required: true, message: '评估等级不能为空', trigger: 'blur' }],
  evalUser: [{ required: true, message: '评估人不能为空', trigger: 'blur' }],
  evalTime: [{ required: true, message: '评估时间不能为空', trigger: 'blur' }],
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
      formData.value = await DecisionEffectEvalApi.getDecisionEffectEval(id)
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
    const data = formData.value as unknown as DecisionEffectEvalVO
    if (formType.value === 'create') {
      await DecisionEffectEvalApi.createDecisionEffectEval(data)
      message.success(t('common.createSuccess'))
    } else {
      await DecisionEffectEvalApi.updateDecisionEffectEval(data)
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
    effectEvalId: undefined,
    relSchemeId: undefined,
    relSchemeName: undefined,
    implementationCycle: undefined,
    expectedEffect: undefined,
    actualEffect: undefined,
    effectAchievementRate: undefined,
    resourceTotalInput: undefined,
    inputReturnRate: undefined,
    userSatisfy: undefined,
    evalGrade: undefined,
    successExp: undefined,
    improvePts: undefined,
    evalUser: undefined,
    evalTime: undefined,
    evalRptAttach: undefined,
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
