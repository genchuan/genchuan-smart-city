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
      <!-- 采用单列布局，避免横向对齐问题 -->
      <el-form-item label="指标项名称" prop="idxItemName">
        <el-input
          v-model="formData.idxItemName"
          placeholder="请输入指标项名称"
          class="rounded-md"
          style="width: 100%"
        />
      </el-form-item>

      <el-form-item label="指标项编码" prop="idxItemCode">
        <el-input
          v-model="formData.idxItemCode"
          placeholder="请输入指标项编码"
          class="rounded-md"
          style="width: 100%"
        />
      </el-form-item>

      <!-- 指标分类信息行 -->
      <div class="form-row">
        <el-form-item label="所属指标分类ID" prop="idxCatId" class="form-item-inline">
          <el-input
            v-model="formData.idxCatId"
            placeholder="请输入分类ID"
            class="rounded-md"
            style="width: 100%"
          />
        </el-form-item>

        <el-form-item label="所属指标分类名称" prop="idxCatName" class="form-item-inline">
          <el-input
            v-model="formData.idxCatName"
            placeholder="请输入分类名称"
            class="rounded-md"
            style="width: 100%"
          />
        </el-form-item>
      </div>

      <el-form-item label="指标类型" prop="idxType">
        <el-select
          v-model="formData.idxType"
          placeholder="请选择指标类型"
          class="rounded-md"
          style="width: 100%"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>

      <el-form-item label="计算方式" prop="calcMethod">
        <el-input
          v-model="formData.calcMethod"
          placeholder="请输入计算方式"
          class="rounded-md"
          style="width: 100%"
        />
      </el-form-item>

      <!-- 数据来源信息行 -->
      <div class="form-row">
        <el-form-item label="数据来源表" prop="dataSourceTable" class="form-item-inline">
          <el-input
            v-model="formData.dataSourceTable"
            placeholder="请输入来源表"
            class="rounded-md"
            style="width: 100%"
          />
        </el-form-item>

        <el-form-item label="数据来源字段" prop="dataSourceField" class="form-item-inline">
          <el-input
            v-model="formData.dataSourceField"
            placeholder="请输入来源字段"
            class="rounded-md"
            style="width: 100%"
          />
        </el-form-item>
      </div>

      <el-form-item label="达标阈值" prop="qualifiedThreshold">
        <el-input
          v-model="formData.qualifiedThreshold"
          placeholder="请输入达标阈值"
          class="rounded-md"
          style="width: 100%"
        />
      </el-form-item>

      <el-form-item label="指标描述" prop="idxDesc">
        <el-input
          v-model="formData.idxDesc"
          placeholder="请输入指标描述"
          type="textarea"
          :rows="3"
          class="rounded-md"
          style="width: 100%"
        />
      </el-form-item>

      <el-form-item label="启用状态" prop="enableStatus">
        <el-radio-group v-model="formData.enableStatus" class="radio-group">
          <el-radio value="ENABLED">启用</el-radio>
          <el-radio value="Disabled">禁用</el-radio>
        </el-radio-group>
      </el-form-item>

      <!-- 创建信息行 -->
      <div class="form-row">
        <el-form-item label="创建人(业务)" prop="createUserBiz" class="form-item-inline">
          <el-input
            v-model="formData.createUserBiz"
            placeholder="请输入创建人"
            class="rounded-md"
            style="width: 100%"
          />
        </el-form-item>

        <el-form-item label="创建时间(业务)" prop="createTimeBiz" class="form-item-inline">
          <el-date-picker
            v-model="formData.createTimeBiz"
            type="date"
            value-format="x"
            placeholder="选择创建时间"
            class="rounded-md"
            style="width: 100%"
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
  EvalIdxItemApi,
  EvalIdxItemVO
} from '@/api/dataHub/comprehensiveEval/evalIndicatorMgmt/evalIndItmMgmt'

/** 指标项管理 表单 */
defineOptions({ name: 'EvalIdxItemForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

// 简化表单数据，隐藏不必要字段
const formData = ref({
  id: undefined,
  idxItemName: undefined,
  idxItemCode: undefined,
  idxCatId: undefined,
  idxCatName: undefined,
  idxType: undefined,
  calcMethod: undefined,
  dataSourceTable: undefined,
  dataSourceField: undefined,
  qualifiedThreshold: undefined,
  idxDesc: undefined,
  enableStatus: undefined, // 默认启用
  createUserBiz: undefined,
  createTimeBiz: undefined
})

// 保留核心必填项校验
const formRules = reactive({
  idxItemName: [{ required: true, message: '指标项名称不能为空', trigger: 'blur' }],
  idxItemCode: [{ required: true, message: '指标项编码不能为空', trigger: 'blur' }],
  idxCatId: [{ required: true, message: '所属指标分类ID不能为空', trigger: 'blur' }],
  idxCatName: [{ required: true, message: '所属指标分类名称不能为空', trigger: 'blur' }],
  idxType: [{ required: true, message: '指标类型不能为空', trigger: 'change' }],
  calcMethod: [{ required: true, message: '计算方式不能为空', trigger: 'blur' }],
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
      const res = await EvalIdxItemApi.getEvalIdxItem(id)
      formData.value = {
        ...res,
        // 只保留需要展示的字段
        idxDesc: res.idxDesc || '',
        dataSourceTable: res.dataSourceTable || '',
        dataSourceField: res.dataSourceField || '',
        qualifiedThreshold: res.qualifiedThreshold || ''
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
    const data = formData.value as unknown as EvalIdxItemVO
    if (formType.value === 'create') {
      await EvalIdxItemApi.createEvalIdxItem(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalIdxItemApi.updateEvalIdxItem(data)
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
    idxItemName: undefined,
    idxItemCode: undefined,
    idxCatId: undefined,
    idxCatName: undefined,
    idxType: undefined,
    calcMethod: undefined,
    dataSourceTable: undefined,
    dataSourceField: undefined,
    qualifiedThreshold: undefined,
    idxDesc: undefined,
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
}

.radio-group {
  display: flex;
  align-items: center;
  gap: 20px;
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

/* 行内表单样式 */
.form-row {
  display: flex;
  gap: 16px;
  width: 100%;
  margin-bottom: 16px;
}

.form-item-inline {
  flex: 1;
  margin-bottom: 0;
}

/* 调整弹窗滚动条样式 */
.el-dialog__body::-webkit-scrollbar {
  width: 6px;
}

.el-dialog__body::-webkit-scrollbar-thumb {
  background-color: #ddd;
  border-radius: 3px;
}
</style>
