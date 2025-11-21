<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="适用领域" prop="applications">
        <el-input v-model="formData.applications" placeholder="请输入适用领域" />
      </el-form-item>
      <el-form-item label="应用场景" prop="applicationScenarios">
        <el-input v-model="formData.applicationScenarios" placeholder="请输入应用场景" />
      </el-form-item>
      <el-form-item label="受众群体" prop="targetAudience">
        <el-input v-model="formData.targetAudience" placeholder="请输入受众群体" />
      </el-form-item>
      <el-form-item label="指南性质" prop="natureOfTheGuide">
        <el-input v-model="formData.natureOfTheGuide" placeholder="请输入指南性质" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { ClassificationOfGuideInformationApi, ClassificationOfGuideInformationVO } from '@/api/smartcity/classificationofguideinformation'

/** 指南信息分类 表单 */
defineOptions({ name: 'ClassificationOfGuideInformationForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  applications: undefined,
  applicationScenarios: undefined,
  targetAudience: undefined,
  natureOfTheGuide: undefined
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
      formData.value = await ClassificationOfGuideInformationApi.getClassificationOfGuideInformation(id)
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
    const data = formData.value as unknown as ClassificationOfGuideInformationVO
    if (formType.value === 'create') {
      await ClassificationOfGuideInformationApi.createClassificationOfGuideInformation(data)
      message.success(t('common.createSuccess'))
    } else {
      await ClassificationOfGuideInformationApi.updateClassificationOfGuideInformation(data)
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
    applications: undefined,
    applicationScenarios: undefined,
    targetAudience: undefined,
    natureOfTheGuide: undefined
  }
  formRef.value?.resetFields()
}
</script>