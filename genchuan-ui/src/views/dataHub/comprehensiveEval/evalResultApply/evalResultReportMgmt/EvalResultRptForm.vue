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
      <el-form-item label="报表名称" prop="resultRptName">
        <el-input
          v-model="formData.resultRptName"
          placeholder="请输入报表名称"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="报表编码" prop="resultRptCode">
        <el-input
          v-model="formData.resultRptCode"
          placeholder="请输入报表编码"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="关联任务ID" prop="evalTaskId">
        <el-input
          v-model="formData.evalTaskId"
          placeholder="请输入关联任务ID"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="关联任务名称" prop="evalTaskName">
        <el-input
          v-model="formData.evalTaskName"
          placeholder="请输入关联任务名称"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="报表类型" prop="rptType">
        <el-select
          v-model="formData.rptType"
          placeholder="请选择报表类型"
          class="w-full rounded-md"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="统计维度" prop="statDimension">
        <el-input
          v-model="formData.statDimension"
          placeholder="请输入统计维度"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="行政区划代码" prop="regionCode">
        <el-input
          v-model="formData.regionCode"
          placeholder="请输入行政区划代码"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="行政区划名称" prop="regionName">
        <el-input
          v-model="formData.regionName"
          placeholder="请输入行政区划名称"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="报表字段" prop="rptFields">
        <el-input
          v-model="formData.rptFields"
          placeholder="请输入报表字段（多个用逗号分隔）"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="生成时间" prop="generateTime">
        <el-date-picker
          v-model="formData.generateTime"
          type="datetime"
          value-format="x"
          placeholder="选择生成时间"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="报表状态" prop="rptStatus">
        <el-select
          v-model="formData.rptStatus"
          placeholder="请选择报表状态"
          class="w-full rounded-md"
        >
          <el-option label="已生成" value="GENERATED" />
        </el-select>
      </el-form-item>
      <el-form-item label="文件路径" prop="filePath">
        <el-input
          v-model="formData.filePath"
          placeholder="请输入文件路径"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="生成人" prop="generateUser">
        <el-input
          v-model="formData.generateUser"
          placeholder="请输入生成人"
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
  EvalResultRptApi,
  EvalResultRptVO
} from '@/api/dataHub/comprehensiveEval/evalResultApply/evalResultReportMgmt'

/** 结果报表管理 表单 */
defineOptions({ name: 'EvalResultRptForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

// 简化表单数据，隐藏不必要字段
const formData = ref({
  id: undefined,
  resultRptName: undefined,
  resultRptCode: undefined,
  evalTaskId: undefined,
  evalTaskName: undefined,
  rptType: undefined,
  statDimension: undefined,
  regionCode: undefined,
  regionName: undefined,
  rptFields: undefined,
  generateTime: undefined,
  rptStatus: undefined,
  filePath: undefined,
  generateUser: undefined,
  createTimeBiz: undefined
})

// 保留核心必填项校验
const formRules = reactive({
  resultRptName: [{ required: true, message: '报表名称不能为空', trigger: 'blur' }],
  resultRptCode: [{ required: true, message: '报表编码不能为空', trigger: 'blur' }],
  evalTaskId: [{ required: true, message: '关联任务ID不能为空', trigger: 'blur' }],
  evalTaskName: [{ required: true, message: '关联任务名称不能为空', trigger: 'blur' }],
  rptType: [{ required: true, message: '报表类型不能为空', trigger: 'change' }],
  statDimension: [{ required: true, message: '统计维度不能为空', trigger: 'blur' }],
  rptFields: [{ required: true, message: '报表字段不能为空', trigger: 'blur' }],
  generateTime: [{ required: true, message: '生成时间不能为空', trigger: 'change' }],
  rptStatus: [{ required: true, message: '报表状态不能为空', trigger: 'change' }],
  generateUser: [{ required: true, message: '生成人不能为空', trigger: 'blur' }],
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
      const res = await EvalResultRptApi.getEvalResultRpt(id)
      formData.value = {
        ...res,
        // 只保留需要展示的字段
        rptFields: res.rptFields || '',
        filePath: res.filePath || ''
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
    const data = formData.value as unknown as EvalResultRptVO
    if (formType.value === 'create') {
      await EvalResultRptApi.createEvalResultRpt(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalResultRptApi.updateEvalResultRpt(data)
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
    resultRptName: undefined,
    resultRptCode: undefined,
    evalTaskId: undefined,
    evalTaskName: undefined,
    rptType: undefined,
    statDimension: undefined,
    regionCode: undefined,
    regionName: undefined,
    rptFields: undefined,
    generateTime: undefined,
    rptStatus: undefined,
    filePath: undefined,
    generateUser: undefined,
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
