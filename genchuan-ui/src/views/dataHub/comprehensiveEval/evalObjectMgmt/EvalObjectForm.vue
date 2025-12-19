<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="700px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="140px"
      v-loading="formLoading"
      class="p-4 bg-gray-50 rounded-lg"
    >
      <!-- 基础信息区域 -->
      <el-form-item label="评价对象名称" prop="evalObjectName">
        <el-input
          v-model="formData.evalObjectName"
          placeholder="请输入评价对象名称"
          class="rounded-md"
        />
      </el-form-item>

      <el-form-item label="对象编码" prop="objectCode">
        <el-input v-model="formData.objectCode" placeholder="请输入对象编码" class="rounded-md" />
      </el-form-item>

      <!-- 行政区划信息行 -->
      <el-row :gutter="20" class="mb-4">
        <el-col :span="12">
          <el-form-item label="所属行政区划代码" prop="regionCode" class="no-label">
            <el-input
              v-model="formData.regionCode"
              placeholder="请输入行政区划代码"
              class="rounded-md"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属行政区划名称" prop="regionName" class="no-label">
            <el-input
              v-model="formData.regionName"
              placeholder="请输入行政区划名称"
              class="rounded-md"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 关联信息行 -->
      <el-row :gutter="20" class="mb-4">
        <el-col :span="12">
          <el-form-item label="关联网格名称" prop="gridName" class="no-label">
            <el-input
              v-model="formData.gridName"
              placeholder="请输入关联网格名称"
              class="rounded-md"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联部门名称" prop="deptName" class="no-label">
            <el-input
              v-model="formData.deptName"
              placeholder="请输入关联部门名称"
              class="rounded-md"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 负责人信息行 -->
      <el-row :gutter="20" class="mb-4">
        <el-col :span="12">
          <el-form-item label="负责人姓名" prop="leaderName" class="no-label">
            <el-input
              v-model="formData.leaderName"
              placeholder="请输入负责人姓名"
              class="rounded-md"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="负责人联系方式" prop="leaderContact" class="no-label">
            <el-input
              v-model="formData.leaderContact"
              placeholder="请输入负责人联系方式"
              class="rounded-md"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="对象状态" prop="objectStatus">
        <el-radio-group v-model="formData.objectStatus" class="radio-group">
          <el-radio value="ENABLED">正常</el-radio>
          <el-radio value="Disabled">异常</el-radio>
        </el-radio-group>
      </el-form-item>

      <!-- 创建信息行 -->
      <el-row :gutter="20" class="mb-4">
        <el-col :span="12">
          <el-form-item label="创建人(业务)" prop="createUserBiz" class="no-label">
            <el-input
              v-model="formData.createUserBiz"
              placeholder="请输入创建人(业务)"
              class="rounded-md"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="创建时间(业务)" prop="createTimeBiz" class="no-label">
            <el-date-picker
              v-model="formData.createTimeBiz"
              type="date"
              value-format="x"
              placeholder="选择创建时间(业务)"
              class="rounded-md"
            />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <template #footer>
      <el-button @click="dialogVisible = false" class="mr-2">取 消</el-button>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
import { EvalObjectApi, EvalObjectVO } from '@/api/dataHub/comprehensiveEval/evalObjectMgmt'

/** 评价对象管理 表单 */
defineOptions({ name: 'EvalObjectForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

// 简化表单数据，隐藏不必要字段
const formData = ref({
  id: undefined,
  evalObjectName: undefined,
  objectCode: undefined,
  regionCode: undefined,
  regionName: undefined,
  gridName: undefined,
  deptName: undefined,
  leaderName: undefined,
  leaderContact: undefined,
  objectStatus: '1', // 默认正常
  createUserBiz: undefined,
  createTimeBiz: undefined
})

// 保留核心必填项校验
const formRules = reactive({
  evalObjectName: [{ required: true, message: '评价对象名称不能为空', trigger: 'blur' }],
  objectCode: [{ required: true, message: '对象编码不能为空', trigger: 'blur' }],
  regionCode: [{ required: true, message: '所属行政区划代码不能为空', trigger: 'blur' }],
  regionName: [{ required: true, message: '所属行政区划名称不能为空', trigger: 'blur' }],
  leaderName: [{ required: true, message: '负责人姓名不能为空', trigger: 'blur' }],
  leaderContact: [{ required: true, message: '负责人联系方式不能为空', trigger: 'blur' }],
  objectStatus: [{ required: true, message: '对象状态不能为空', trigger: 'change' }],
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
      const res = await EvalObjectApi.getEvalObject(id)
      formData.value = {
        ...res,
        // 只保留需要展示的字段
        gridName: res.gridName || '',
        deptName: res.deptName || ''
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
    const data = formData.value as unknown as EvalObjectVO
    if (formType.value === 'create') {
      await EvalObjectApi.createEvalObject(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalObjectApi.updateEvalObject(data)
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
    evalObjectName: undefined,
    objectCode: undefined,
    regionCode: undefined,
    regionName: undefined,
    gridName: undefined,
    deptName: undefined,
    leaderName: undefined,
    leaderContact: undefined,
    objectStatus: '1',
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
.el-radio-group {
  --el-input-bg-color: #fff;
  --el-input-border-color: #e4e7ed;
  --el-input-hover-border-color: #409eff;
}

.radio-group {
  display: flex;
  align-items: center;
  gap: 16px;
}

.el-dialog__body {
  padding: 16px 24px;
  max-height: 70vh;
  overflow-y: auto;
}

.el-form-item {
  margin-bottom: 20px;
}

/* 移除行内项目的标签样式 */
.no-label .el-form-item__label {
  display: none;
}

/* 调整行内表单项的底部边距 */
.el-row .el-form-item {
  margin-bottom: 0;
}
</style>
