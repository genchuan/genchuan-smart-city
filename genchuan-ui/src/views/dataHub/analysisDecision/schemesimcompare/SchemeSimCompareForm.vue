<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="对比ID" prop="simCompareId">
        <el-input v-model="formData.simCompareId" placeholder="请输入对比ID" />
      </el-form-item>
      <el-form-item label="对比名称" prop="compareName">
        <el-input v-model="formData.compareName" placeholder="请输入对比名称" />
      </el-form-item>
      <el-form-item label="参与方案IDs" prop="participateSchemeIds">
        <el-input v-model="formData.participateSchemeIds" placeholder="请输入参与方案IDs" />
      </el-form-item>
      <el-form-item label="参与方案名称" prop="participateSchemeNames">
        <el-input v-model="formData.participateSchemeNames" placeholder="请输入参与方案名称" />
      </el-form-item>
      <el-form-item label="资源投入权重" prop="resInputWeight">
        <el-input v-model="formData.resInputWeight" placeholder="请输入资源投入权重" />
      </el-form-item>
      <el-form-item label="预期效果权重" prop="expectedEffectWeight">
        <el-input v-model="formData.expectedEffectWeight" placeholder="请输入预期效果权重" />
      </el-form-item>
      <el-form-item label="实施周期权重" prop="implementationCycleWeight">
        <el-input v-model="formData.implementationCycleWeight" placeholder="请输入实施周期权重" />
      </el-form-item>
      <el-form-item label="方案1_ID" prop="scheme1Id">
        <el-input v-model="formData.scheme1Id" placeholder="请输入方案1_ID" />
      </el-form-item>
      <el-form-item label="方案1_资源投入得分" prop="scheme1ResScore">
        <el-input v-model="formData.scheme1ResScore" placeholder="请输入方案1_资源投入得分" />
      </el-form-item>
      <el-form-item label="方案1_预期效果得分" prop="scheme1EffectScore">
        <el-input v-model="formData.scheme1EffectScore" placeholder="请输入方案1_预期效果得分" />
      </el-form-item>
      <el-form-item label="方案1_周期得分" prop="scheme1CycleScore">
        <el-input v-model="formData.scheme1CycleScore" placeholder="请输入方案1_周期得分" />
      </el-form-item>
      <el-form-item label="方案1_综合得分" prop="scheme1CompositeScore">
        <el-input v-model="formData.scheme1CompositeScore" placeholder="请输入方案1_综合得分" />
      </el-form-item>
      <el-form-item label="方案2_ID" prop="scheme2Id">
        <el-input v-model="formData.scheme2Id" placeholder="请输入方案2_ID" />
      </el-form-item>
      <el-form-item label="方案2_资源投入得分" prop="scheme2ResScore">
        <el-input v-model="formData.scheme2ResScore" placeholder="请输入方案2_资源投入得分" />
      </el-form-item>
      <el-form-item label="方案2_预期效果得分" prop="scheme2EffectScore">
        <el-input v-model="formData.scheme2EffectScore" placeholder="请输入方案2_预期效果得分" />
      </el-form-item>
      <el-form-item label="方案2_周期得分" prop="scheme2CycleScore">
        <el-input v-model="formData.scheme2CycleScore" placeholder="请输入方案2_周期得分" />
      </el-form-item>
      <el-form-item label="方案2_综合得分" prop="scheme2CompositeScore">
        <el-input v-model="formData.scheme2CompositeScore" placeholder="请输入方案2_综合得分" />
      </el-form-item>
      <el-form-item label="对比结论" prop="compareConclusion">
        <el-input v-model="formData.compareConclusion" placeholder="请输入对比结论" />
      </el-form-item>
      <el-form-item label="对比人" prop="compareUser">
        <el-input v-model="formData.compareUser" placeholder="请输入对比人" />
      </el-form-item>
      <el-form-item label="对比时间" prop="compareTime">
        <el-date-picker
          v-model="formData.compareTime"
          type="date"
          value-format="x"
          placeholder="选择对比时间"
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
import { SchemeSimCompareApi, SchemeSimCompareVO } from '@/api/dataHub/analysisDecision/schemesimcompare'

/** 方案模拟对比统计 表单 */
defineOptions({ name: 'SchemeSimCompareForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  simCompareId: undefined,
  compareName: undefined,
  participateSchemeIds: undefined,
  participateSchemeNames: undefined,
  resInputWeight: undefined,
  expectedEffectWeight: undefined,
  implementationCycleWeight: undefined,
  scheme1Id: undefined,
  scheme1ResScore: undefined,
  scheme1EffectScore: undefined,
  scheme1CycleScore: undefined,
  scheme1CompositeScore: undefined,
  scheme2Id: undefined,
  scheme2ResScore: undefined,
  scheme2EffectScore: undefined,
  scheme2CycleScore: undefined,
  scheme2CompositeScore: undefined,
  compareConclusion: undefined,
  compareUser: undefined,
  compareTime: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  simCompareId: [{ required: true, message: '对比ID不能为空', trigger: 'blur' }],
  compareName: [{ required: true, message: '对比名称不能为空', trigger: 'blur' }],
  participateSchemeIds: [{ required: true, message: '参与方案IDs不能为空', trigger: 'blur' }],
  participateSchemeNames: [{ required: true, message: '参与方案名称不能为空', trigger: 'blur' }],
  resInputWeight: [{ required: true, message: '资源投入权重不能为空', trigger: 'blur' }],
  expectedEffectWeight: [{ required: true, message: '预期效果权重不能为空', trigger: 'blur' }],
  implementationCycleWeight: [{ required: true, message: '实施周期权重不能为空', trigger: 'blur' }],
  scheme1Id: [{ required: true, message: '方案1_ID不能为空', trigger: 'blur' }],
  scheme1ResScore: [{ required: true, message: '方案1_资源投入得分不能为空', trigger: 'blur' }],
  scheme1EffectScore: [{ required: true, message: '方案1_预期效果得分不能为空', trigger: 'blur' }],
  scheme1CycleScore: [{ required: true, message: '方案1_周期得分不能为空', trigger: 'blur' }],
  scheme1CompositeScore: [{ required: true, message: '方案1_综合得分不能为空', trigger: 'blur' }],
  scheme2Id: [{ required: true, message: '方案2_ID不能为空', trigger: 'blur' }],
  scheme2ResScore: [{ required: true, message: '方案2_资源投入得分不能为空', trigger: 'blur' }],
  scheme2EffectScore: [{ required: true, message: '方案2_预期效果得分不能为空', trigger: 'blur' }],
  scheme2CycleScore: [{ required: true, message: '方案2_周期得分不能为空', trigger: 'blur' }],
  scheme2CompositeScore: [{ required: true, message: '方案2_综合得分不能为空', trigger: 'blur' }],
  compareUser: [{ required: true, message: '对比人不能为空', trigger: 'blur' }],
  compareTime: [{ required: true, message: '对比时间不能为空', trigger: 'blur' }],
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
      formData.value = await SchemeSimCompareApi.getSchemeSimCompare(id)
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
    const data = formData.value as unknown as SchemeSimCompareVO
    if (formType.value === 'create') {
      await SchemeSimCompareApi.createSchemeSimCompare(data)
      message.success(t('common.createSuccess'))
    } else {
      await SchemeSimCompareApi.updateSchemeSimCompare(data)
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
    simCompareId: undefined,
    compareName: undefined,
    participateSchemeIds: undefined,
    participateSchemeNames: undefined,
    resInputWeight: undefined,
    expectedEffectWeight: undefined,
    implementationCycleWeight: undefined,
    scheme1Id: undefined,
    scheme1ResScore: undefined,
    scheme1EffectScore: undefined,
    scheme1CycleScore: undefined,
    scheme1CompositeScore: undefined,
    scheme2Id: undefined,
    scheme2ResScore: undefined,
    scheme2EffectScore: undefined,
    scheme2CycleScore: undefined,
    scheme2CompositeScore: undefined,
    compareConclusion: undefined,
    compareUser: undefined,
    compareTime: undefined,
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
