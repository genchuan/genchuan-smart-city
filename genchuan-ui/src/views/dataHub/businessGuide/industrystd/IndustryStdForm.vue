<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="规范ID" prop="industryStdId">
        <el-input v-model="formData.industryStdId" placeholder="请输入规范ID" />
      </el-form-item>
      <el-form-item label="规范标题" prop="stdTitle">
        <el-input v-model="formData.stdTitle" placeholder="请输入规范标题" />
      </el-form-item>
      <el-form-item label="行业分类代码" prop="industryTypeCode">
        <el-input v-model="formData.industryTypeCode" placeholder="请输入行业分类代码" />
      </el-form-item>
      <el-form-item label="行业分类名称" prop="industryTypeName">
        <el-input v-model="formData.industryTypeName" placeholder="请输入行业分类名称" />
      </el-form-item>
      <el-form-item label="规范编号" prop="stdNo">
        <el-input v-model="formData.stdNo" placeholder="请输入规范编号" />
      </el-form-item>
      <el-form-item label="发布机构" prop="issueOrg">
        <el-input v-model="formData.issueOrg" placeholder="请输入发布机构" />
      </el-form-item>
      <el-form-item label="发布时间" prop="issueTime">
        <el-date-picker
          v-model="formData.issueTime"
          type="date"
          value-format="x"
          placeholder="选择发布时间"
        />
      </el-form-item>
      <el-form-item label="规范文件ID" prop="stdFileId">
        <el-input v-model="formData.stdFileId" placeholder="请输入规范文件ID" />
      </el-form-item>
      <el-form-item label="规范文件路径" prop="stdFilePath">
        <el-input v-model="formData.stdFilePath" placeholder="请输入规范文件路径" />
      </el-form-item>
      <el-form-item label="规范状态" prop="stdStatus">
        <el-radio-group v-model="formData.stdStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="核心条款" prop="coreClause">
        <el-input v-model="formData.coreClause" placeholder="请输入核心条款" />
      </el-form-item>
      <el-form-item label="创建人" prop="createUser">
        <el-input v-model="formData.createUser" placeholder="请输入创建人" />
      </el-form-item>
      <el-form-item label="更新人" prop="updateUser">
        <el-input v-model="formData.updateUser" placeholder="请输入更新人" />
      </el-form-item>
      <el-form-item label="分类扩展字段1" prop="extCat1">
        <el-input v-model="formData.extCat1" placeholder="请输入分类扩展字段1" />
      </el-form-item>
      <el-form-item label="分类扩展字段2" prop="extCat2">
        <el-input v-model="formData.extCat2" placeholder="请输入分类扩展字段2" />
      </el-form-item>
      <el-form-item label="通用扩展字段1" prop="extCommon1">
        <el-input v-model="formData.extCommon1" placeholder="请输入通用扩展字段1" />
      </el-form-item>
      <el-form-item label="通用扩展字段2" prop="extCommon2">
        <el-input v-model="formData.extCommon2" placeholder="请输入通用扩展字段2" />
      </el-form-item>
      <el-form-item label="系统创建时间" prop="createTimeSys">
        <el-date-picker
          v-model="formData.createTimeSys"
          type="date"
          value-format="x"
          placeholder="选择系统创建时间"
        />
      </el-form-item>
      <el-form-item label="系统更新时间" prop="updateTimeSys">
        <el-date-picker
          v-model="formData.updateTimeSys"
          type="date"
          value-format="x"
          placeholder="选择系统更新时间"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { IndustryStdApi, IndustryStdVO } from '@/api/dataHub/businessGuide/industrystd'

/** 行业规范信息 表单 */
defineOptions({ name: 'IndustryStdForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  industryStdId: undefined,
  stdTitle: undefined,
  industryTypeCode: undefined,
  industryTypeName: undefined,
  stdNo: undefined,
  issueOrg: undefined,
  issueTime: undefined,
  stdFileId: undefined,
  stdFilePath: undefined,
  stdStatus: undefined,
  coreClause: undefined,
  createUser: undefined,
  updateUser: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined
})
const formRules = reactive({
  industryStdId: [{ required: true, message: '规范ID不能为空', trigger: 'blur' }],
  stdTitle: [{ required: true, message: '规范标题不能为空', trigger: 'blur' }],
  industryTypeCode: [{ required: true, message: '行业分类代码不能为空', trigger: 'blur' }],
  industryTypeName: [{ required: true, message: '行业分类名称不能为空', trigger: 'blur' }],
  issueOrg: [{ required: true, message: '发布机构不能为空', trigger: 'blur' }],
  issueTime: [{ required: true, message: '发布时间不能为空', trigger: 'blur' }],
  stdFileId: [{ required: true, message: '规范文件ID不能为空', trigger: 'blur' }],
  stdFilePath: [{ required: true, message: '规范文件路径不能为空', trigger: 'blur' }],
  stdStatus: [{ required: true, message: '规范状态不能为空', trigger: 'blur' }],
  createUser: [{ required: true, message: '创建人不能为空', trigger: 'blur' }]
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
      formData.value = await IndustryStdApi.getIndustryStd(id)
    } finally {
      formLoading.value = false
    }
  }
}
defineExpose({ open }) // 提供 open 方法，用于打开弹窗

/** 提交表单 */
const emit = defineEmits(['success']) // 定义 success 事件，用于操作成功后的回调
const submitForm = async () => {
  // 校验表单
  await formRef.value.validate()
  // 提交请求
  formLoading.value = true
  try {
    const data = formData.value as unknown as IndustryStdVO
    if (formType.value === 'create') {
      await IndustryStdApi.createIndustryStd(data)
      message.success(t('common.createSuccess'))
    } else {
      await IndustryStdApi.updateIndustryStd(data)
      message.success(t('common.updateSuccess'))
    }
    dialogVisible.value = false
    // 发送操作成功的事件
    emit('success')
  } finally {
    formLoading.value = false
  }
}

/** 重置表单 */
const resetForm = () => {
  formData.value = {
    id: undefined,
    industryStdId: undefined,
    stdTitle: undefined,
    industryTypeCode: undefined,
    industryTypeName: undefined,
    stdNo: undefined,
    issueOrg: undefined,
    issueTime: undefined,
    stdFileId: undefined,
    stdFilePath: undefined,
    stdStatus: undefined,
    coreClause: undefined,
    createUser: undefined,
    updateUser: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined
  }
  formRef.value?.resetFields()
}
</script>
