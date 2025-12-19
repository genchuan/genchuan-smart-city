<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="跟踪ID" prop="execTrackId">
        <el-input v-model="formData.execTrackId" placeholder="请输入跟踪ID" />
      </el-form-item>
      <el-form-item label="关联方案ID" prop="relSchemeId">
        <el-input v-model="formData.relSchemeId" placeholder="请输入关联方案ID" />
      </el-form-item>
      <el-form-item label="关联方案名称" prop="relSchemeName">
        <el-input v-model="formData.relSchemeName" placeholder="请输入关联方案名称" />
      </el-form-item>
      <el-form-item label="计划实施周期" prop="plannedExecCycle">
        <el-input v-model="formData.plannedExecCycle" placeholder="请输入计划实施周期" />
      </el-form-item>
      <el-form-item label="已实施天数" prop="executedDays">
        <el-input v-model="formData.executedDays" placeholder="请输入已实施天数" />
      </el-form-item>
      <el-form-item label="实施进度" prop="execProgress">
        <el-input v-model="formData.execProgress" placeholder="请输入实施进度" />
      </el-form-item>
      <el-form-item label="预期效果指标" prop="expectedEffectIdx">
        <el-input v-model="formData.expectedEffectIdx" placeholder="请输入预期效果指标" />
      </el-form-item>
      <el-form-item label="实际效果指标" prop="actualEffectIdx">
        <el-input v-model="formData.actualEffectIdx" placeholder="请输入实际效果指标" />
      </el-form-item>
      <el-form-item label="效果达成率" prop="effectAchievementRate">
        <el-input v-model="formData.effectAchievementRate" placeholder="请输入效果达成率" />
      </el-form-item>
      <el-form-item label="未达标项" prop="unmetItems">
        <el-input v-model="formData.unmetItems" placeholder="请输入未达标项" />
      </el-form-item>
      <el-form-item label="跟踪状态" prop="trackStatus">
        <el-radio-group v-model="formData.trackStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="跟踪人" prop="trackUser">
        <el-input v-model="formData.trackUser" placeholder="请输入跟踪人" />
      </el-form-item>
      <el-form-item label="跟踪时间" prop="trackTime">
        <el-date-picker
          v-model="formData.trackTime"
          type="date"
          value-format="x"
          placeholder="选择跟踪时间"
        />
      </el-form-item>
      <el-form-item label="上次跟踪时间" prop="lastTrackTime">
        <el-date-picker
          v-model="formData.lastTrackTime"
          type="date"
          value-format="x"
          placeholder="选择上次跟踪时间"
        />
      </el-form-item>
      <el-form-item label="整改建议" prop="rectificationSugg">
        <el-input v-model="formData.rectificationSugg" placeholder="请输入整改建议" />
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
import { DecisionExecTrackApi, DecisionExecTrackVO } from '@/api/dataHub/analysisDecision/decisionexectrack'

/** 决策执行跟踪 表单 */
defineOptions({ name: 'DecisionExecTrackForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  execTrackId: undefined,
  relSchemeId: undefined,
  relSchemeName: undefined,
  plannedExecCycle: undefined,
  executedDays: undefined,
  execProgress: undefined,
  expectedEffectIdx: undefined,
  actualEffectIdx: undefined,
  effectAchievementRate: undefined,
  unmetItems: undefined,
  trackStatus: undefined,
  trackUser: undefined,
  trackTime: undefined,
  lastTrackTime: undefined,
  rectificationSugg: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  execTrackId: [{ required: true, message: '跟踪ID不能为空', trigger: 'blur' }],
  relSchemeId: [{ required: true, message: '关联方案ID不能为空', trigger: 'blur' }],
  relSchemeName: [{ required: true, message: '关联方案名称不能为空', trigger: 'blur' }],
  plannedExecCycle: [{ required: true, message: '计划实施周期不能为空', trigger: 'blur' }],
  executedDays: [{ required: true, message: '已实施天数不能为空', trigger: 'blur' }],
  execProgress: [{ required: true, message: '实施进度不能为空', trigger: 'blur' }],
  expectedEffectIdx: [{ required: true, message: '预期效果指标不能为空', trigger: 'blur' }],
  trackStatus: [{ required: true, message: '跟踪状态不能为空', trigger: 'blur' }],
  trackUser: [{ required: true, message: '跟踪人不能为空', trigger: 'blur' }],
  trackTime: [{ required: true, message: '跟踪时间不能为空', trigger: 'blur' }],
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
      formData.value = await DecisionExecTrackApi.getDecisionExecTrack(id)
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
    const data = formData.value as unknown as DecisionExecTrackVO
    if (formType.value === 'create') {
      await DecisionExecTrackApi.createDecisionExecTrack(data)
      message.success(t('common.createSuccess'))
    } else {
      await DecisionExecTrackApi.updateDecisionExecTrack(data)
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
    execTrackId: undefined,
    relSchemeId: undefined,
    relSchemeName: undefined,
    plannedExecCycle: undefined,
    executedDays: undefined,
    execProgress: undefined,
    expectedEffectIdx: undefined,
    actualEffectIdx: undefined,
    effectAchievementRate: undefined,
    unmetItems: undefined,
    trackStatus: undefined,
    trackUser: undefined,
    trackTime: undefined,
    lastTrackTime: undefined,
    rectificationSugg: undefined,
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
