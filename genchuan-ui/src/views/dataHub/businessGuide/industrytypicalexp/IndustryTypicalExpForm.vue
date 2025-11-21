<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="经验ID" prop="industryTypicalExpId">
        <el-input v-model="formData.industryTypicalExpId" placeholder="请输入经验ID" />
      </el-form-item>
      <el-form-item label="经验标题" prop="expTitle">
        <el-input v-model="formData.expTitle" placeholder="请输入经验标题" />
      </el-form-item>
      <el-form-item label="行业领域代码" prop="industryFieldCode">
        <el-input v-model="formData.industryFieldCode" placeholder="请输入行业领域代码" />
      </el-form-item>
      <el-form-item label="行业领域名称" prop="industryFieldName">
        <el-input v-model="formData.industryFieldName" placeholder="请输入行业领域名称" />
      </el-form-item>
      <el-form-item label="案例单位" prop="caseOrg">
        <el-input v-model="formData.caseOrg" placeholder="请输入案例单位" />
      </el-form-item>
      <el-form-item label="经验核心亮点" prop="expHighlights">
        <el-input v-model="formData.expHighlights" placeholder="请输入经验核心亮点" />
      </el-form-item>
      <el-form-item label="经验实施效果" prop="implementationEffect">
        <el-input v-model="formData.implementationEffect" placeholder="请输入经验实施效果" />
      </el-form-item>
      <el-form-item label="附件ID" prop="attachId">
        <el-input v-model="formData.attachId" placeholder="请输入附件ID" />
      </el-form-item>
      <el-form-item label="附件存储路径" prop="attachPath">
        <el-input v-model="formData.attachPath" placeholder="请输入附件存储路径" />
      </el-form-item>
      <el-form-item label="发布状态" prop="publishStatus">
        <el-radio-group v-model="formData.publishStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="点赞次数" prop="likeCount">
        <el-input v-model="formData.likeCount" placeholder="请输入点赞次数" />
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
import { IndustryTypicalExpApi, IndustryTypicalExpVO } from '@/api/dataHub/businessGuide/industrytypicalexp'

/** 行业典型经验 表单 */
defineOptions({ name: 'IndustryTypicalExpForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  industryTypicalExpId: undefined,
  expTitle: undefined,
  industryFieldCode: undefined,
  industryFieldName: undefined,
  caseOrg: undefined,
  expHighlights: undefined,
  implementationEffect: undefined,
  attachId: undefined,
  attachPath: undefined,
  publishStatus: undefined,
  likeCount: undefined,
  createUser: undefined,
  updateUser: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  industryTypicalExpId: [{ required: true, message: '经验ID不能为空', trigger: 'blur' }],
  expTitle: [{ required: true, message: '经验标题不能为空', trigger: 'blur' }],
  industryFieldCode: [{ required: true, message: '行业领域代码不能为空', trigger: 'blur' }],
  industryFieldName: [{ required: true, message: '行业领域名称不能为空', trigger: 'blur' }],
  caseOrg: [{ required: true, message: '案例单位不能为空', trigger: 'blur' }],
  expHighlights: [{ required: true, message: '经验核心亮点不能为空', trigger: 'blur' }],
  implementationEffect: [{ required: true, message: '经验实施效果不能为空', trigger: 'blur' }],
  publishStatus: [{ required: true, message: '发布状态不能为空', trigger: 'blur' }],
  likeCount: [{ required: true, message: '点赞次数不能为空', trigger: 'blur' }],
  createUser: [{ required: true, message: '创建人不能为空', trigger: 'blur' }],
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
      formData.value = await IndustryTypicalExpApi.getIndustryTypicalExp(id)
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
    const data = formData.value as unknown as IndustryTypicalExpVO
    if (formType.value === 'create') {
      await IndustryTypicalExpApi.createIndustryTypicalExp(data)
      message.success(t('common.createSuccess'))
    } else {
      await IndustryTypicalExpApi.updateIndustryTypicalExp(data)
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
    industryTypicalExpId: undefined,
    expTitle: undefined,
    industryFieldCode: undefined,
    industryFieldName: undefined,
    caseOrg: undefined,
    expHighlights: undefined,
    implementationEffect: undefined,
    attachId: undefined,
    attachPath: undefined,
    publishStatus: undefined,
    likeCount: undefined,
    createUser: undefined,
    updateUser: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined,
  }
  formRef.value?.resetFields()
}
</script>
