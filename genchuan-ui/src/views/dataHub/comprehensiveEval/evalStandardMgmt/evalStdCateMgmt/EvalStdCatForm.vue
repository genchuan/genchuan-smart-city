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
      <!-- 标准分类名称 -->
      <el-form-item label="标准分类名称" prop="stdCatName">
        <el-input
          v-model="formData.stdCatName"
          placeholder="请输入标准分类名称"
          class="w-full rounded-md"
        />
      </el-form-item>

      <!-- 分类编码 -->
      <el-form-item label="分类编码" prop="stdCatCode">
        <el-input
          v-model="formData.stdCatCode"
          placeholder="请输入分类编码"
          class="w-full rounded-md"
        />
      </el-form-item>

      <!-- 适用指标体系ID -->
      <el-form-item label="适用指标体系ID" prop="idxSystemId">
        <el-input v-model="formData.idxSystemId" placeholder="请输入ID" class="w-full rounded-md" />
      </el-form-item>

      <!-- 适用指标体系名称 -->
      <el-form-item label="适用指标体系名称" prop="idxSystemName">
        <el-input
          v-model="formData.idxSystemName"
          placeholder="请输入名称"
          class="w-full rounded-md"
        />
      </el-form-item>

      <!-- 分类描述 -->
      <el-form-item label="分类描述" prop="catDesc">
        <el-input
          v-model="formData.catDesc"
          placeholder="请输入分类描述"
          type="textarea"
          :rows="3"
          class="w-full rounded-md"
        />
      </el-form-item>

      <!-- 启用状态 -->
      <el-form-item label="启用状态" prop="enableStatus">
        <el-radio-group v-model="formData.enableStatus" class="radio-group">
          <el-radio value="ENABLED">启用</el-radio>
          <el-radio value="Disabled">禁用</el-radio>
        </el-radio-group>
      </el-form-item>

      <!-- 创建人(业务) - 单独一行 -->
      <el-form-item label="创建人(业务)" prop="createUserBiz">
        <el-input
          v-model="formData.createUserBiz"
          placeholder="请输入创建人"
          class="w-full rounded-md"
        />
      </el-form-item>

      <!-- 创建时间(业务) - 单独一行 -->
      <el-form-item label="创建时间(业务)" prop="createTimeBiz">
        <el-date-picker
          v-model="formData.createTimeBiz"
          type="date"
          value-format="x"
          placeholder="选择时间"
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
  EvalStdCatApi,
  EvalStdCatVO
} from '@/api/dataHub/comprehensiveEval/evalStandardMgmt/evalStdCateMgmt'

/** 标准分类管理 表单 */
defineOptions({ name: 'EvalStdCatForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

// 表单数据
const formData = ref({
  id: undefined,
  stdCatName: undefined,
  stdCatCode: undefined,
  idxSystemId: undefined,
  idxSystemName: undefined,
  catDesc: undefined,
  enableStatus: undefined,
  createUserBiz: undefined,
  createTimeBiz: undefined
})

// 表单校验规则
const formRules = reactive({
  stdCatName: [{ required: true, message: '标准分类名称不能为空', trigger: 'blur' }],
  stdCatCode: [{ required: true, message: '分类编码不能为空', trigger: 'blur' }],
  idxSystemId: [{ required: true, message: '适用指标体系ID不能为空', trigger: 'blur' }],
  idxSystemName: [{ required: true, message: '适用指标体系名称不能为空', trigger: 'blur' }],
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
      const res = await EvalStdCatApi.getEvalStdCat(id)
      formData.value = {
        ...res,
        catDesc: res.catDesc || ''
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
    const data = formData.value as unknown as EvalStdCatVO
    if (formType.value === 'create') {
      await EvalStdCatApi.createEvalStdCat(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalStdCatApi.updateEvalStdCat(data)
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
    stdCatName: undefined,
    stdCatCode: undefined,
    idxSystemId: undefined,
    idxSystemName: undefined,
    catDesc: undefined,
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

/* 确保标签右对齐且宽度一致 */
:deep(.el-form-item__label) {
  text-align: right !important;
  padding-right: 12px !important;
}
</style>
