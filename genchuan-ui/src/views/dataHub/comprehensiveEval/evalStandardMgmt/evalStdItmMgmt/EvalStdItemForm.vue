<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="650px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="140px"
      v-loading="formLoading"
      class="p-4 bg-gray-50 rounded-lg"
    >
      <!-- 基础信息组 -->
      <el-divider content-position="left">基础信息</el-divider>
      <el-form-item label="标准项名称" prop="stdItemName">
        <el-input
          v-model="formData.stdItemName"
          placeholder="请输入标准项名称"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="标准项编码" prop="stdItemCode">
        <el-input
          v-model="formData.stdItemCode"
          placeholder="请输入标准项编码"
          class="w-full rounded-md"
        />
      </el-form-item>

      <!-- 分类信息组 -->
      <el-divider content-position="left">分类信息</el-divider>
      <el-form-item label="所属标准分类ID" prop="stdCatId">
        <el-input
          v-model="formData.stdCatId"
          placeholder="请输入所属标准分类ID"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="所属标准分类名称" prop="stdCatName">
        <el-input
          v-model="formData.stdCatName"
          placeholder="请输入所属标准分类名称"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="标准等级" prop="stdLevel">
        <el-input
          v-model="formData.stdLevel"
          placeholder="请输入标准等级"
          class="w-full rounded-md"
        />
      </el-form-item>

      <!-- 评分设置组 -->
      <el-divider content-position="left">评分设置</el-divider>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="分数下限" prop="scoreMin">
            <el-input
              v-model="formData.scoreMin"
              placeholder="请输入分数下限"
              type="number"
              class="w-full rounded-md"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分数上限" prop="scoreMax">
            <el-input
              v-model="formData.scoreMax"
              placeholder="请输入分数上限"
              type="number"
              class="w-full rounded-md"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="排序序号" prop="sortNum">
        <el-input
          v-model="formData.sortNum"
          placeholder="请输入排序序号"
          type="number"
          class="w-full rounded-md"
        />
      </el-form-item>

      <!-- 其他信息组 -->
      <el-divider content-position="left">其他信息</el-divider>
      <el-form-item label="标准描述" prop="stdDesc">
        <el-input
          v-model="formData.stdDesc"
          placeholder="请输入标准描述"
          type="textarea"
          :rows="3"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="启用状态" prop="enableStatus">
        <el-radio-group v-model="formData.enableStatus" class="radio-group">
          <el-radio value="ENABLED">启用</el-radio>
          <el-radio value="Disabled">禁用</el-radio>
        </el-radio-group>
      </el-form-item>

      <!-- 创建信息组 -->
      <el-divider content-position="left">创建信息</el-divider>
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
    </el-form>

    <template #footer>
      <el-button @click="dialogVisible = false" class="mr-2">取 消</el-button>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
import {
  EvalStdItemApi,
  EvalStdItemVO
} from '@/api/dataHub/comprehensiveEval/evalStandardMgmt/evalStdItmMgmt'

/** 标准项管理 表单 */
defineOptions({ name: 'EvalStdItemForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

// 简化表单数据，隐藏不必要字段
const formData = ref({
  id: undefined,
  stdItemName: undefined,
  stdItemCode: undefined,
  stdCatId: undefined,
  stdCatName: undefined,
  stdLevel: undefined,
  scoreMin: undefined,
  scoreMax: undefined,
  stdDesc: undefined,
  sortNum: undefined,
  enableStatus: undefined, // 默认启用
  createUserBiz: undefined,
  createTimeBiz: undefined
})

// 保留核心必填项校验
const formRules = reactive({
  stdItemName: [{ required: true, message: '标准项名称不能为空', trigger: 'blur' }],
  stdItemCode: [{ required: true, message: '标准项编码不能为空', trigger: 'blur' }],
  stdCatId: [{ required: true, message: '所属标准分类ID不能为空', trigger: 'blur' }],
  stdCatName: [{ required: true, message: '所属标准分类名称不能为空', trigger: 'blur' }],
  stdLevel: [{ required: true, message: '标准等级不能为空', trigger: 'blur' }],
  scoreMin: [
    { required: true, message: '分数下限不能为空', trigger: 'blur' },
    { type: 'number', message: '请输入有效数字', trigger: 'blur' }
  ],
  scoreMax: [
    { required: true, message: '分数上限不能为空', trigger: 'blur' },
    { type: 'number', message: '请输入有效数字', trigger: 'blur' }
  ],
  sortNum: [
    { required: true, message: '排序序号不能为空', trigger: 'blur' },
    { type: 'number', message: '请输入有效数字', trigger: 'blur' }
  ],
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
      const res = await EvalStdItemApi.getEvalStdItem(id)
      formData.value = {
        ...res,
        // 只保留需要展示的字段
        stdDesc: res.stdDesc || '',
        scoreMin: res.scoreMin || 0,
        scoreMax: res.scoreMax || 0,
        sortNum: res.sortNum || 0
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
    const data = formData.value as unknown as EvalStdItemVO
    if (formType.value === 'create') {
      await EvalStdItemApi.createEvalStdItem(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalStdItemApi.updateEvalStdItem(data)
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
    stdItemName: undefined,
    stdItemCode: undefined,
    stdCatId: undefined,
    stdCatName: undefined,
    stdLevel: undefined,
    scoreMin: undefined,
    scoreMax: undefined,
    stdDesc: undefined,
    sortNum: undefined,
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

/* 分组分隔线样式优化 */
:deep(.el-divider) {
  margin: 16px 0;
}

:deep(.el-divider__text) {
  font-weight: 500;
  color: #333;
  background-color: #fafafa;
  padding: 0 8px;
}
</style>
