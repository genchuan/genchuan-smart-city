<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="800px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="140px"
      v-loading="formLoading"
      class="p-4 bg-gray-50 rounded-lg"
    >
      <!-- 纵向布局：确保输入控件对齐，符合用户使用习惯 -->
      <el-form-item label="对接编号" prop="resultDockCode">
        <el-input
          v-model="formData.resultDockCode"
          placeholder="请输入对接编号"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="关联存档ID" prop="resultArchId">
        <el-input
          v-model="formData.resultArchId"
          placeholder="请输入关联存档ID"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="外部系统ID" prop="externalSystemId">
        <el-input
          v-model="formData.externalSystemId"
          placeholder="请输入外部系统ID"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="外部系统名称" prop="externalSystemName">
        <el-input
          v-model="formData.externalSystemName"
          placeholder="请输入外部系统名称"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="对接方式" prop="dockMethod">
        <el-input
          v-model="formData.dockMethod"
          placeholder="请输入对接方式"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="对接频率" prop="dockFrequency">
        <el-input
          v-model="formData.dockFrequency"
          placeholder="请输入对接频率"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="数据映射规则ID" prop="mapRuleId">
        <el-input
          v-model="formData.mapRuleId"
          placeholder="请输入数据映射规则ID"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="对接状态" prop="dockStatus">
        <el-select
          v-model="formData.dockStatus"
          placeholder="请选择对接状态"
          class="w-full rounded-md"
        >
          <el-option label="成功" value="SUCCESS" />
          <el-option label="失败" value="FAILED" />
          <el-option label="对接中" value="PENDING" />
        </el-select>
      </el-form-item>
      <el-form-item label="对接时间" prop="dockTime">
        <el-date-picker
          v-model="formData.dockTime"
          type="datetime"
          value-format="x"
          placeholder="选择对接时间"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="对接数据" prop="dockData">
        <el-input
          v-model="formData.dockData"
          placeholder="请输入对接数据"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="失败原因" prop="failReason">
        <el-input
          v-model="formData.failReason"
          placeholder="请输入失败原因"
          class="w-full rounded-md"
        />
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
    </el-form>

    <template #footer>
      <el-button @click="dialogVisible = false" class="mr-2">取 消</el-button>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
import {
  EvalResultSystemDockApi,
  EvalResultSystemDockVO
} from '@/api/dataHub/comprehensiveEval/evalResultApply/evalResultSysInt'

/** 结果系统对接 表单 */
defineOptions({ name: 'EvalResultSystemDockForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

// 简化表单数据，隐藏不必要字段
const formData = ref({
  id: undefined,
  resultDockCode: undefined,
  resultArchId: undefined,
  externalSystemId: undefined,
  externalSystemName: undefined,
  dockMethod: undefined,
  dockFrequency: undefined,
  mapRuleId: undefined,
  dockStatus: undefined,
  dockTime: undefined,
  dockData: undefined,
  failReason: undefined,
  createUserBiz: undefined,
  createTimeBiz: undefined
})

// 保留核心必填项校验
const formRules = reactive({
  resultDockCode: [{ required: true, message: '对接编号不能为空', trigger: 'blur' }],
  resultArchId: [{ required: true, message: '关联存档ID不能为空', trigger: 'blur' }],
  externalSystemId: [{ required: true, message: '外部系统ID不能为空', trigger: 'blur' }],
  externalSystemName: [{ required: true, message: '外部系统名称不能为空', trigger: 'blur' }],
  dockMethod: [{ required: true, message: '对接方式不能为空', trigger: 'blur' }],
  dockFrequency: [{ required: true, message: '对接频率不能为空', trigger: 'blur' }],
  mapRuleId: [{ required: true, message: '数据映射规则ID不能为空', trigger: 'blur' }],
  dockStatus: [{ required: true, message: '对接状态不能为空', trigger: 'change' }],
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
      const res = await EvalResultSystemDockApi.getEvalResultSystemDock(id)
      formData.value = {
        ...res,
        // 只保留需要展示的字段
        dockData: res.dockData || '',
        failReason: res.failReason || ''
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
    const data = formData.value as unknown as EvalResultSystemDockVO
    if (formType.value === 'create') {
      await EvalResultSystemDockApi.createEvalResultSystemDock(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalResultSystemDockApi.updateEvalResultSystemDock(data)
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
    resultDockCode: undefined,
    resultArchId: undefined,
    externalSystemId: undefined,
    externalSystemName: undefined,
    dockMethod: undefined,
    dockFrequency: undefined,
    mapRuleId: undefined,
    dockStatus: undefined,
    dockTime: undefined,
    dockData: undefined,
    failReason: undefined,
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
.el-select {
  --el-input-bg-color: #fff;
  --el-input-border-color: #e4e7ed;
  --el-input-hover-border-color: #409eff;
  width: 100%;
}

.el-dialog__body {
  padding: 16px 24px;
}

.el-form-item {
  margin-bottom: 20px;
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

/* 日期选择器宽度适配 */
:deep(.el-date-editor--datetime) {
  width: 100% !important;
}
</style>
