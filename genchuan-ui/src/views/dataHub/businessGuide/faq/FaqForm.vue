<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="FAQ ID" prop="faqId">
        <el-input v-model="formData.faqId" placeholder="请输入FAQ ID" />
      </el-form-item>
      <el-form-item label="问题类型代码" prop="questionTypeCode">
        <el-input v-model="formData.questionTypeCode" placeholder="请输入问题类型代码" />
      </el-form-item>
      <el-form-item label="问题类型名称" prop="questionTypeName">
        <el-input v-model="formData.questionTypeName" placeholder="请输入问题类型名称" />
      </el-form-item>
      <el-form-item label="问题描述" prop="questionDesc">
        <el-input v-model="formData.questionDesc" placeholder="请输入问题描述" />
      </el-form-item>
      <el-form-item label="解答方案" prop="answerSolution">
        <el-input v-model="formData.answerSolution" placeholder="请输入解答方案" />
      </el-form-item>
      <el-form-item label="关联指南ID" prop="relatedGuideId">
        <el-input v-model="formData.relatedGuideId" placeholder="请输入关联指南ID" />
      </el-form-item>
      <el-form-item label="关联指南名称" prop="relatedGuideName">
        <el-input v-model="formData.relatedGuideName" placeholder="请输入关联指南名称" />
      </el-form-item>
      <el-form-item label="排序序号" prop="sortNum">
        <el-input v-model="formData.sortNum" placeholder="请输入排序序号" />
      </el-form-item>
      <el-form-item label="是否置顶" prop="isTop">
        <el-input v-model="formData.isTop" placeholder="请输入是否置顶" />
      </el-form-item>
      <el-form-item label="查看次数" prop="viewCount">
        <el-input v-model="formData.viewCount" placeholder="请输入查看次数" />
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
import { FaqApi, FaqVO } from '@/api/dataHub/businessGuide/faq'

/** 常见问题解答 表单 */
defineOptions({ name: 'FaqForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  faqId: undefined,
  questionTypeCode: undefined,
  questionTypeName: undefined,
  questionDesc: undefined,
  answerSolution: undefined,
  relatedGuideId: undefined,
  relatedGuideName: undefined,
  sortNum: undefined,
  isTop: undefined,
  viewCount: undefined,
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
  faqId: [{ required: true, message: 'FAQ ID不能为空', trigger: 'blur' }],
  questionTypeCode: [{ required: true, message: '问题类型代码不能为空', trigger: 'blur' }],
  questionTypeName: [{ required: true, message: '问题类型名称不能为空', trigger: 'blur' }],
  questionDesc: [{ required: true, message: '问题描述不能为空', trigger: 'blur' }],
  answerSolution: [{ required: true, message: '解答方案不能为空', trigger: 'blur' }],
  sortNum: [{ required: true, message: '排序序号不能为空', trigger: 'blur' }],
  isTop: [{ required: true, message: '是否置顶不能为空', trigger: 'blur' }],
  viewCount: [{ required: true, message: '查看次数不能为空', trigger: 'blur' }],
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
      formData.value = await FaqApi.getFaq(id)
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
    const data = formData.value as unknown as FaqVO
    if (formType.value === 'create') {
      await FaqApi.createFaq(data)
      message.success(t('common.createSuccess'))
    } else {
      await FaqApi.updateFaq(data)
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
    faqId: undefined,
    questionTypeCode: undefined,
    questionTypeName: undefined,
    questionDesc: undefined,
    answerSolution: undefined,
    relatedGuideId: undefined,
    relatedGuideName: undefined,
    sortNum: undefined,
    isTop: undefined,
    viewCount: undefined,
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
