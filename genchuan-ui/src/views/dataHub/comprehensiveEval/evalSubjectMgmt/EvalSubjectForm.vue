<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="800px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
      class="p-4 bg-gray-50 rounded-lg"
    >
      <el-row :gutter="20">
        <!-- 第一列 -->
        <el-col :span="12">
          <el-form-item label="评价主体名称" prop="evalSubjectName">
            <el-input
              v-model="formData.evalSubjectName"
              placeholder="请输入评价主体名称"
              class="rounded-md"
            />
          </el-form-item>
          <el-form-item label="主体编码" prop="subjectCode">
            <el-input
              v-model="formData.subjectCode"
              placeholder="请输入主体编码"
              class="rounded-md"
            />
          </el-form-item>
          <el-form-item label="主体类型" prop="subjectType">
            <el-select
              v-model="formData.subjectType"
              placeholder="请选择主体类型"
              class="rounded-md"
            >
              <el-option label="请选择字典生成" value="" />
            </el-select>
          </el-form-item>
          <el-form-item label="所属部门名称" prop="deptName">
            <el-input
              v-model="formData.deptName"
              placeholder="请输入所属部门名称"
              class="rounded-md"
            />
          </el-form-item>
          <el-form-item label="成员姓名" prop="memberNames">
            <el-input
              v-model="formData.memberNames"
              placeholder="请输入成员姓名（多个用逗号分隔）"
              class="rounded-md"
            />
          </el-form-item>
        </el-col>

        <!-- 第二列 -->
        <el-col :span="12">
          <el-form-item label="联系人" prop="contactPerson">
            <el-input
              v-model="formData.contactPerson"
              placeholder="请输入联系人"
              class="rounded-md"
            />
          </el-form-item>
          <el-form-item label="联系电话" prop="contactPhone">
            <el-input
              v-model="formData.contactPhone"
              placeholder="请输入联系电话"
              class="rounded-md"
            />
          </el-form-item>
          <el-form-item label="主体状态" prop="subjectStatus">
            <el-radio-group v-model="formData.subjectStatus" class="radio-group">
              <el-radio value="ENABLED">正常</el-radio>
              <el-radio value="Disabled">异常</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="创建人(业务)" prop="createUserBiz">
            <el-input
              v-model="formData.createUserBiz"
              placeholder="请输入创建人(业务)"
              class="rounded-md"
            />
          </el-form-item>
          <el-form-item label="创建时间(业务)" prop="createTimeBiz">
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
import { EvalSubjectApi, EvalSubjectVO } from '@/api/dataHub/comprehensiveEval/evalSubjectMgmt'

/** 评价主体管理 表单 */
defineOptions({ name: 'EvalSubjectForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

// 简化表单数据，隐藏不必要字段
const formData = ref({
  id: undefined,
  evalSubjectName: undefined,
  subjectCode: undefined,
  subjectType: undefined,
  deptName: undefined,
  memberNames: undefined,
  contactPerson: undefined,
  contactPhone: undefined,
  subjectStatus: undefined, // 默认正常
  createUserBiz: undefined,
  createTimeBiz: undefined
})

// 保留核心必填项校验
const formRules = reactive({
  evalSubjectName: [{ required: true, message: '评价主体名称不能为空', trigger: 'blur' }],
  subjectCode: [{ required: true, message: '主体编码不能为空', trigger: 'blur' }],
  subjectType: [{ required: true, message: '主体类型不能为空', trigger: 'change' }],
  contactPerson: [{ required: true, message: '联系人不能为空', trigger: 'blur' }],
  contactPhone: [{ required: true, message: '联系电话不能为空', trigger: 'blur' }],
  subjectStatus: [{ required: true, message: '主体状态不能为空', trigger: 'change' }],
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
      const res = await EvalSubjectApi.getEvalSubject(id)
      formData.value = {
        ...res,
        // 只保留需要展示的字段
        deptName: res.deptName || '',
        memberNames: res.memberNames || ''
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
    const data = formData.value as unknown as EvalSubjectVO
    if (formType.value === 'create') {
      await EvalSubjectApi.createEvalSubject(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvalSubjectApi.updateEvalSubject(data)
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
    evalSubjectName: undefined,
    subjectCode: undefined,
    subjectType: undefined,
    deptName: undefined,
    memberNames: undefined,
    contactPerson: undefined,
    contactPhone: undefined,
    subjectStatus: undefined,
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
}

.el-dialog__body {
  padding: 16px 24px;
}

.el-form-item {
  margin-bottom: 20px;
}
</style>
