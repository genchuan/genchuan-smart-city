<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="600px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="130px"
      v-loading="formLoading"
      class="p-4 bg-gray-50 rounded-lg"
    >
      <!-- 基本信息组 -->
      <div class="form-group">
        <h3 class="group-title">基本信息</h3>
        <el-form-item label="否决项名称" prop="vetoRuleName">
          <el-input
            v-model="formData.vetoRuleName"
            placeholder="请输入否决项名称"
            class="w-full rounded-md"
          />
        </el-form-item>
        <el-form-item label="否决项编码" prop="vetoRuleCode">
          <el-input
            v-model="formData.vetoRuleCode"
            placeholder="请输入否决项编码"
            class="w-full rounded-md"
          />
        </el-form-item>
        <el-form-item label="适用对象类型" prop="applyObjectType">
          <el-select
            v-model="formData.applyObjectType"
            placeholder="请选择适用对象类型"
            class="w-full rounded-md"
          >
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
      </div>

      <!-- 指标信息组 -->
      <div class="form-group">
        <h3 class="group-title">指标信息</h3>
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
        <el-form-item label="指标阈值" prop="idxThreshold">
          <el-input
            v-model="formData.idxThreshold"
            placeholder="请输入指标阈值"
            class="w-full rounded-md"
          />
        </el-form-item>
      </div>

      <!-- 规则信息组 -->
      <div class="form-group">
        <h3 class="group-title">规则信息</h3>
        <el-form-item label="否决条件" prop="vetoCondition">
          <el-input
            v-model="formData.vetoCondition"
            placeholder="请输入否决条件"
            class="w-full rounded-md"
          />
        </el-form-item>
        <el-form-item label="生效周期" prop="validCycle">
          <el-input
            v-model="formData.validCycle"
            placeholder="请输入生效周期"
            class="w-full rounded-md"
          />
        </el-form-item>
        <el-form-item label="否决结果" prop="vetoResult">
          <el-input
            v-model="formData.vetoResult"
            placeholder="请输入否决结果"
            class="w-full rounded-md"
          />
        </el-form-item>
        <el-form-item label="否决描述" prop="vetoDesc">
          <el-input
            v-model="formData.vetoDesc"
            placeholder="请输入否决描述"
            type="textarea"
            :rows="3"
            class="w-full rounded-md"
          />
        </el-form-item>
      </div>

      <!-- 状态与创建信息组 -->
      <div class="form-group">
        <h3 class="group-title">状态与创建信息</h3>
        <el-form-item label="启用状态" prop="enableStatus">
          <el-radio-group v-model="formData.enableStatus" class="radio-group">
            <el-radio value="ENABLED">启用</el-radio>
            <el-radio value="Disabled">禁用</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="创建人(业务)" prop="createUserBiz">
          <el-input
            v-model="formData.createUserBiz"
            placeholder="请输入创建人(业务)"
            class="w-full rounded-md"
          />
        </el-form-item>
        <el-form-item label="创建时间(业务)" prop="createTimeBiz">
          <el-date-picker
            v-model="formData.createTimeBiz"
            type="date"
            value-format="x"
            placeholder="选择创建时间(业务)"
            class="w-full rounded-md"
          />
        </el-form-item>
      </div>
    </el-form>

    <template #footer>
      <el-button @click="dialogVisible = false" class="mr-2">取 消</el-button>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
import {
  EvalVetoRuleApi,
  EvalVetoRuleVO
} from '@/api/dataHub/comprehensiveEval/evalRuleMgmt/evalVetoRuleMgmt'

/** 否决项规则管理 表单 */
defineOptions({ name: 'EvalVetoRuleForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

// 简化表单数据，隐藏不必要字段
const formData = ref({
  id: undefined,
  vetoRuleName: undefined,
  vetoRuleCode: undefined,
  applyObjectType: undefined,
  vetoCondition: undefined,
  idxItemId: undefined,
  idxItemName: undefined,
  idxThreshold: undefined,
  validCycle: undefined,
  vetoResult: undefined,
  vetoDesc: undefined,
  enableStatus: undefined, // 默认启用
  createUserBiz: undefined,
  createTimeBiz: undefined
})

// 保留核心必填项校验
const formRules = reactive({
  vetoRuleName: [{ required: true, message: '否决项名称不能为空', trigger: 'blur' }],
  vetoRuleCode: [{ required: true, message: '否决项编码不能为空', trigger: 'blur' }],
  applyObjectType: [{ required: true, message: '适用对象类型不能为空', trigger: 'change' }],
  vetoCondition: [{ required: true, message: '否决条件不能为空', trigger: 'blur' }],
  validCycle: [{ required: true, message: '生效周期不能为空', trigger: 'blur' }],
  vetoResult: [{ required: true, message: '否决结果不能为空', trigger: 'blur' }],
  enableStatus: [{ required: true, message: '启用状态不能为空', trigger: 'change' }],
  createUserBiz: [{ required: true, message: '创建人(业务)不能为空', trigger: 'blur' }],
  createTimeBiz: [{ required: true, message: '创建时间(业务)不能为空', trigger: 'change' }]
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
      const res = await EvalVetoRuleApi.getEvalVetoRule(id)
      formData.value = {
        ...res,
        // 只保留需要展示的字段
        vetoDesc: res.vetoDesc || '',
        idxThreshold: res.idxThreshold || ''
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
    const data = formData.value as unknown as EvalVetoRuleVO
    if (formType.value === 'create') {
      await EvalVetoRuleApi.createEvalVetoRule(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalVetoRuleApi.updateEvalVetoRule(data)
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
    vetoRuleName: undefined,
    vetoRuleCode: undefined,
    applyObjectType: undefined,
    vetoCondition: undefined,
    idxItemId: undefined,
    idxItemName: undefined,
    idxThreshold: undefined,
    validCycle: undefined,
    vetoResult: undefined,
    vetoDesc: undefined,
    enableStatus: undefined,
    createUserBiz: undefined,
    createTimeBiz: undefined
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
  margin-bottom: 20px;
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

/* 表单分组样式 */
.form-group {
  margin-bottom: 24px;
  padding-bottom: 16px;
  border-bottom: 1px solid #eee;
}

.form-group:last-child {
  border-bottom: none;
  margin-bottom: 0;
  padding-bottom: 0;
}

.group-title {
  margin-bottom: 16px;
  padding-left: 4px;
  font-size: 15px;
  font-weight: 500;
  color: #1f2329;
  border-left: 3px solid #409eff;
}
</style>
