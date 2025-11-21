<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="指南名称" prop="guideName">
        <el-input v-model="formData.guideName" placeholder="请输入指南名称" />
      </el-form-item>
      <el-form-item label="适用范围" prop="scopeOfApplication">
        <el-input v-model="formData.scopeOfApplication" placeholder="请输入适用范围" />
      </el-form-item>
      <el-form-item label="发布单位" prop="publishingUnit">
        <el-input v-model="formData.publishingUnit" placeholder="请输入发布单位" />
      </el-form-item>
      <el-form-item label="发布日期" prop="releaseDate">
        <el-date-picker
          v-model="formData.releaseDate"
          type="date"
          value-format="x"
          placeholder="选择发布日期"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="更新日期" prop="updateDate">
        <el-date-picker
          v-model="formData.updateDate"
          type="date"
          value-format="x"
          placeholder="选择更新日期"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="主要内容概述" prop="mainContentOverview">
        <el-input v-model="formData.mainContentOverview" placeholder="请输入主要内容概述" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { GuideInformationInputApi, GuideInformationInputVO } from '@/api/smartcity/guideinformationinput'

/** 指南信息录入 表单 */
defineOptions({ name: 'GuideInformationInputForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  guideName: undefined,
  scopeOfApplication: undefined,
  publishingUnit: undefined,
  releaseDate: undefined,
  updateDate: undefined,
  mainContentOverview: undefined
})
const formRules = reactive({
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
      formData.value = await GuideInformationInputApi.getGuideInformationInput(id);
      formData.value.releaseDate=formData.value.releaseDate?Number(formData.value.releaseDate):'';
      formData.value.updateDate=formData.value.updateDate?Number(formData.value.updateDate):'';
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
    const data = formData.value as unknown as GuideInformationInputVO
    if (formType.value === 'create') {
      await GuideInformationInputApi.createGuideInformationInput(data)
      message.success(t('common.createSuccess'))
    } else {
      await GuideInformationInputApi.updateGuideInformationInput(data)
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
    guideName: undefined,
    scopeOfApplication: undefined,
    publishingUnit: undefined,
    releaseDate: undefined,
    updateDate: undefined,
    mainContentOverview: undefined
  }
  formRef.value?.resetFields()
}
</script>
