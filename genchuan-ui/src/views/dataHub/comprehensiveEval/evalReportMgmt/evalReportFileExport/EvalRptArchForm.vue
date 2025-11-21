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
      <el-form-item label="归档编号" prop="rptArchCode">
        <el-input
          v-model="formData.rptArchCode"
          placeholder="请输入归档编号"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="关联报告ID" prop="evalRptId">
        <el-input
          v-model="formData.evalRptId"
          placeholder="请输入关联报告ID"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="报告名称" prop="evalRptName">
        <el-input
          v-model="formData.evalRptName"
          placeholder="请输入报告名称"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="评价对象ID" prop="evalObjectId">
        <el-input
          v-model="formData.evalObjectId"
          placeholder="请输入评价对象ID"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="评价对象名称" prop="evalObjectName">
        <el-input
          v-model="formData.evalObjectName"
          placeholder="请输入评价对象名称"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="归档位置" prop="archLocation">
        <el-input
          v-model="formData.archLocation"
          placeholder="请输入归档位置（路径/存储标识）"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="归档方式" prop="archMethod">
        <el-input
          v-model="formData.archMethod"
          placeholder="请输入归档方式（本地/云端等）"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="报告版本" prop="rptVersion">
        <el-input
          v-model="formData.rptVersion"
          placeholder="请输入报告版本（例：V1.0.0）"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="归档状态" prop="archStatus">
        <el-select
          v-model="formData.archStatus"
          placeholder="请选择归档状态"
          class="w-full rounded-md"
        >
          <el-option label="成功" value="SUCCESS" />
          <el-option label="失败" value="FAILED" />
        </el-select>
      </el-form-item>
      <el-form-item label="归档时间" prop="archTime">
        <el-date-picker
          v-model="formData.archTime"
          type="datetime"
          value-format="x"
          placeholder="选择归档时间"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="失败原因" prop="failReason">
        <el-input
          v-model="formData.failReason"
          placeholder="归档失败时填写原因"
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
  EvalRptArchApi,
  EvalRptArchVO
} from '@/api/dataHub/comprehensiveEval/evalReportMgmt/evalReportFileExport'

/** 报告归档导出 表单 */
defineOptions({ name: 'EvalRptArchForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

// 简化表单数据，隐藏不必要字段
const formData = ref({
  id: undefined,
  rptArchCode: undefined,
  evalRptId: undefined,
  evalRptName: undefined,
  evalObjectId: undefined,
  evalObjectName: undefined,
  archLocation: undefined,
  archTime: undefined,
  archMethod: undefined,
  rptVersion: undefined,
  archStatus: undefined,
  failReason: undefined,
  createUserBiz: undefined,
  createTimeBiz: undefined
})

// 保留核心必填项校验
const formRules = reactive({
  rptArchCode: [{ required: true, message: '归档编号不能为空', trigger: 'blur' }],
  evalRptId: [{ required: true, message: '关联报告ID不能为空', trigger: 'blur' }],
  evalRptName: [{ required: true, message: '报告名称不能为空', trigger: 'blur' }],
  evalObjectId: [{ required: true, message: '评价对象ID不能为空', trigger: 'blur' }],
  evalObjectName: [{ required: true, message: '评价对象名称不能为空', trigger: 'blur' }],
  archLocation: [{ required: true, message: '归档位置不能为空', trigger: 'blur' }],
  archMethod: [{ required: true, message: '归档方式不能为空', trigger: 'blur' }],
  rptVersion: [{ required: true, message: '报告版本不能为空', trigger: 'blur' }],
  archStatus: [{ required: true, message: '归档状态不能为空', trigger: 'change' }],
  archTime: [{ required: true, message: '归档时间不能为空', trigger: 'change' }],
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
      const res = await EvalRptArchApi.getEvalRptArch(id)
      formData.value = {
        ...res,
        // 只保留需要展示的字段
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
    const data = formData.value as unknown as EvalRptArchVO
    if (formType.value === 'create') {
      await EvalRptArchApi.createEvalRptArch(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalRptArchApi.updateEvalRptArch(data)
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
    rptArchCode: undefined,
    evalRptId: undefined,
    evalRptName: undefined,
    evalObjectId: undefined,
    evalObjectName: undefined,
    archLocation: undefined,
    archTime: undefined,
    archMethod: undefined,
    rptVersion: undefined,
    archStatus: undefined,
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
