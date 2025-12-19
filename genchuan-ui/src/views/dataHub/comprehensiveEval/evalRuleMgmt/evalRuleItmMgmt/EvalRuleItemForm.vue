<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="600px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="140px"
      v-loading="formLoading"
      class="p-4 bg-gray-50 rounded-lg"
    >
      <!-- 基础信息区域 -->
      <el-form-item label="规则项名称" prop="ruleItemName">
        <el-input
          v-model="formData.ruleItemName"
          placeholder="请输入规则项名称"
          class="w-full rounded-md"
        />
      </el-form-item>

      <el-form-item label="规则项编码" prop="ruleItemCode">
        <el-input
          v-model="formData.ruleItemCode"
          placeholder="请输入规则项编码"
          class="w-full rounded-md"
        />
      </el-form-item>

      <!-- 分类信息区域 -->
      <el-form-item label="所属规则分类ID" prop="ruleCatId">
        <el-input
          v-model="formData.ruleCatId"
          placeholder="请输入所属规则分类ID"
          class="w-full rounded-md"
        />
      </el-form-item>

      <el-form-item label="所属规则分类名称" prop="ruleCatName">
        <el-input
          v-model="formData.ruleCatName"
          placeholder="请输入所属规则分类名称"
          class="w-full rounded-md"
        />
      </el-form-item>

      <!-- 指标关联区域 -->
      <el-form-item label="关联指标项ID" prop="idxItemId">
        <el-input
          v-model="formData.idxItemId"
          placeholder="请输入关联指标项ID"
          class="w-full rounded-md"
        />
      </el-form-item>

      <el-form-item label="关联指标项名称" prop="idxItemName">
        <el-input
          v-model="formData.idxItemName"
          placeholder="请输入关联指标项名称"
          class="w-full rounded-md"
        />
      </el-form-item>

      <!-- 规则配置区域 -->
      <el-form-item label="规则类型" prop="ruleType">
        <el-select
          v-model="formData.ruleType"
          placeholder="请选择规则类型"
          class="w-full rounded-md"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>

      <el-form-item label="满分值" prop="fullScore">
        <el-input
          v-model="formData.fullScore"
          placeholder="请输入满分值"
          type="number"
          class="w-full rounded-md"
        />
      </el-form-item>

      <el-form-item label="评分逻辑" prop="scoreLogic">
        <el-input
          v-model="formData.scoreLogic"
          placeholder="请输入评分逻辑"
          class="w-full rounded-md"
        />
      </el-form-item>

      <el-form-item label="规则描述" prop="ruleDesc">
        <el-input
          v-model="formData.ruleDesc"
          placeholder="请输入规则描述"
          type="textarea"
          :rows="3"
          class="w-full rounded-md"
        />
      </el-form-item>

      <!-- 状态与创建信息区域 -->
      <el-form-item label="启用状态" prop="enableStatus">
        <el-radio-group v-model="formData.enableStatus" class="radio-group">
          <el-radio value="ENABLED">启用</el-radio>
          <el-radio value="Disabled">禁用</el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="创建人" prop="createUser">
        <el-input
          v-model="formData.createUser"
          placeholder="请输入创建人"
          class="w-full rounded-md"
        />
      </el-form-item>

      <el-form-item label="创建时间" prop="createTimeSys">
        <el-date-picker
          v-model="formData.createTimeSys"
          type="date"
          value-format="x"
          placeholder="选择创建时间"
          class="w-full rounded-md"
        />
      </el-form-item>
    </el-form>

    <template #footer>
      <el-button @click="dialogVisible = false" class="mr-2">取 消</el-button>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
import {
  EvalRuleItemApi,
  EvalRuleItemVO
} from '@/api/dataHub/comprehensiveEval/evalRuleMgmt/evalRuleItmMgmt'

/** 规则项管理 表单 */
defineOptions({ name: 'EvalRuleItemForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

// 简化表单数据，隐藏不必要字段
const formData = ref({
  id: undefined,
  ruleItemName: undefined,
  ruleItemCode: undefined,
  ruleCatId: undefined,
  ruleCatName: undefined,
  idxItemId: undefined,
  idxItemName: undefined,
  scoreLogic: undefined,
  fullScore: undefined,
  ruleType: undefined,
  ruleDesc: undefined,
  enableStatus: undefined, // 默认启用
  createUser: undefined,
  createTimeSys: undefined
})

// 保留核心必填项校验
const formRules = reactive({
  ruleItemName: [{ required: true, message: '规则项名称不能为空', trigger: 'blur' }],
  ruleItemCode: [{ required: true, message: '规则项编码不能为空', trigger: 'blur' }],
  ruleCatId: [{ required: true, message: '所属规则分类ID不能为空', trigger: 'blur' }],
  ruleCatName: [{ required: true, message: '所属规则分类名称不能为空', trigger: 'blur' }],
  idxItemId: [{ required: true, message: '关联指标项ID不能为空', trigger: 'blur' }],
  idxItemName: [{ required: true, message: '关联指标项名称不能为空', trigger: 'blur' }],
  scoreLogic: [{ required: true, message: '评分逻辑不能为空', trigger: 'blur' }],
  fullScore: [
    { required: true, message: '满分值不能为空', trigger: 'blur' },
    { type: 'number', message: '请输入有效数字', trigger: 'blur' }
  ],
  ruleType: [{ required: true, message: '规则类型不能为空', trigger: 'change' }],
  enableStatus: [{ required: true, message: '启用状态不能为空', trigger: 'change' }],
  createUser: [{ required: true, message: '创建人不能为空', trigger: 'blur' }],
  createTimeSys: [{ required: true, message: '创建时间不能为空', trigger: 'change' }]
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
      const res = await EvalRuleItemApi.getEvalRuleItem(id)
      formData.value = {
        ...res,
        // 只保留需要展示的字段
        ruleDesc: res.ruleDesc || '',
        fullScore: res.fullScore || 0
      }
    } finally {
      formLoading.value = false
    }
  }
}

defineExpose({ open }) // 提供 open 方法

/** 提交表单 */
const emit = defineEmits(['success'])
const submitForm = async () => {
  // 校验表单
  await formRef.value.validate()
  // 提交请求
  formLoading.value = true
  try {
    const data = formData.value as unknown as EvalRuleItemVO
    if (formType.value === 'create') {
      await EvalRuleItemApi.createEvalRuleItem(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalRuleItemApi.updateEvalRuleItem(data)
      message.success(t('common.updateSuccess'))
    }
    dialogVisible.value = false
    emit('success')
  } finally {
    formLoading.value = false
  }
}

/** 重置表单 */
const resetForm = () => {
  formData.value = {
    id: undefined,
    ruleItemName: undefined,
    ruleItemCode: undefined,
    ruleCatId: undefined,
    ruleCatName: undefined,
    idxItemId: undefined,
    idxItemName: undefined,
    scoreLogic: undefined,
    fullScore: undefined,
    ruleType: undefined,
    ruleDesc: undefined,
    enableStatus: undefined,
    createUser: undefined,
    createTimeSys: undefined
  }
  formRef.value?.resetFields()
}
</script>

<style scoped>
.el-form {
  background-color: #fafafa;
}

.el-input,
.el-date-picker,
.el-select,
.el-radio-group {
  --el-input-bg-color: #fff;
  --el-input-border-color: #e4e7ed;
  --el-input-hover-border-color: #409eff;
  width: 100%;
}

.radio-group {
  display: flex;
  align-items: center;
  gap: 16px;
  padding: 5px 0;
}

.el-dialog__body {
  padding: 16px 24px;
  max-height: 70vh;
  overflow-y: auto;
}

.el-form-item {
  margin-bottom: 16px;
}

.el-textarea {
  resize: none;
}

/* 确保输入控件高度一致 */
:deep(.el-input__wrapper),
:deep(.el-date-editor) {
  height: 32px;
  line-height: 32px;
}

:deep(.el-date-editor .el-input__wrapper) {
  height: auto;
}
</style>
