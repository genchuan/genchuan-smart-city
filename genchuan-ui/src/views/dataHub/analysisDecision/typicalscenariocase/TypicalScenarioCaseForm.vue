<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="案例ID" prop="typicalCaseId">
        <el-input v-model="formData.typicalCaseId" placeholder="请输入案例ID" />
      </el-form-item>
      <el-form-item label="案例名称" prop="caseName">
        <el-input v-model="formData.caseName" placeholder="请输入案例名称" />
      </el-form-item>
      <el-form-item label="关联方案ID" prop="relSchemeId">
        <el-input v-model="formData.relSchemeId" placeholder="请输入关联方案ID" />
      </el-form-item>
      <el-form-item label="关联评估ID" prop="relEvalId">
        <el-input v-model="formData.relEvalId" placeholder="请输入关联评估ID" />
      </el-form-item>
      <el-form-item label="案例标签" prop="caseTags">
        <el-input v-model="formData.caseTags" placeholder="请输入案例标签" />
      </el-form-item>
      <el-form-item label="适用场景" prop="applicableScenario">
        <el-input v-model="formData.applicableScenario" placeholder="请输入适用场景" />
      </el-form-item>
      <el-form-item label="核心措施" prop="coreMeasures">
        <el-input v-model="formData.coreMeasures" placeholder="请输入核心措施" />
      </el-form-item>
      <el-form-item label="资源投入" prop="resourceInput">
        <el-input v-model="formData.resourceInput" placeholder="请输入资源投入" />
      </el-form-item>
      <el-form-item label="实施效果" prop="implementationEffect">
        <el-input v-model="formData.implementationEffect" placeholder="请输入实施效果" />
      </el-form-item>
      <el-form-item label="评估等级" prop="evalGrade">
        <el-input v-model="formData.evalGrade" placeholder="请输入评估等级" />
      </el-form-item>
      <el-form-item label="成功经验" prop="successExp">
        <el-input v-model="formData.successExp" placeholder="请输入成功经验" />
      </el-form-item>
      <el-form-item label="改进建议" prop="improveSuggs">
        <el-input v-model="formData.improveSuggs" placeholder="请输入改进建议" />
      </el-form-item>
      <el-form-item label="归档人" prop="archUser">
        <el-input v-model="formData.archUser" placeholder="请输入归档人" />
      </el-form-item>
      <el-form-item label="归档时间" prop="archTime">
        <el-date-picker
          v-model="formData.archTime"
          type="date"
          value-format="x"
          placeholder="选择归档时间"
        />
      </el-form-item>
      <el-form-item label="案例状态" prop="caseStatus">
        <el-radio-group v-model="formData.caseStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
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
import { TypicalScenarioCaseApi, TypicalScenarioCaseVO } from '@/api/dataHub/analysisDecision/typicalscenariocase'

/** 典型场景案例库 表单 */
defineOptions({ name: 'TypicalScenarioCaseForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  typicalCaseId: undefined,
  caseName: undefined,
  relSchemeId: undefined,
  relEvalId: undefined,
  caseTags: undefined,
  applicableScenario: undefined,
  coreMeasures: undefined,
  resourceInput: undefined,
  implementationEffect: undefined,
  evalGrade: undefined,
  successExp: undefined,
  improveSuggs: undefined,
  archUser: undefined,
  archTime: undefined,
  caseStatus: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  typicalCaseId: [{ required: true, message: '案例ID不能为空', trigger: 'blur' }],
  caseName: [{ required: true, message: '案例名称不能为空', trigger: 'blur' }],
  relSchemeId: [{ required: true, message: '关联方案ID不能为空', trigger: 'blur' }],
  relEvalId: [{ required: true, message: '关联评估ID不能为空', trigger: 'blur' }],
  caseTags: [{ required: true, message: '案例标签不能为空', trigger: 'blur' }],
  applicableScenario: [{ required: true, message: '适用场景不能为空', trigger: 'blur' }],
  coreMeasures: [{ required: true, message: '核心措施不能为空', trigger: 'blur' }],
  resourceInput: [{ required: true, message: '资源投入不能为空', trigger: 'blur' }],
  implementationEffect: [{ required: true, message: '实施效果不能为空', trigger: 'blur' }],
  evalGrade: [{ required: true, message: '评估等级不能为空', trigger: 'blur' }],
  archUser: [{ required: true, message: '归档人不能为空', trigger: 'blur' }],
  archTime: [{ required: true, message: '归档时间不能为空', trigger: 'blur' }],
  caseStatus: [{ required: true, message: '案例状态不能为空', trigger: 'blur' }],
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
      formData.value = await TypicalScenarioCaseApi.getTypicalScenarioCase(id)
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
    const data = formData.value as unknown as TypicalScenarioCaseVO
    if (formType.value === 'create') {
      await TypicalScenarioCaseApi.createTypicalScenarioCase(data)
      message.success(t('common.createSuccess'))
    } else {
      await TypicalScenarioCaseApi.updateTypicalScenarioCase(data)
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
    typicalCaseId: undefined,
    caseName: undefined,
    relSchemeId: undefined,
    relEvalId: undefined,
    caseTags: undefined,
    applicableScenario: undefined,
    coreMeasures: undefined,
    resourceInput: undefined,
    implementationEffect: undefined,
    evalGrade: undefined,
    successExp: undefined,
    improveSuggs: undefined,
    archUser: undefined,
    archTime: undefined,
    caseStatus: undefined,
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
