<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="所属行业" prop="sector">
        <el-input v-model="formData.sector" placeholder="请输入所属行业" />
      </el-form-item>
      <el-form-item label="应用场景" prop="applicationScenarios">
        <el-input v-model="formData.applicationScenarios" placeholder="请输入应用场景" />
      </el-form-item>
      <el-form-item label="经验性质" prop="empiricalNature">
        <el-input v-model="formData.empiricalNature" placeholder="请输入经验性质" />
      </el-form-item>
      <el-form-item label="适用对象" prop="applicableObjects">
        <el-input v-model="formData.applicableObjects" placeholder="请输入适用对象" />
      </el-form-item>
      <el-form-item label="来源渠道" prop="sourceChannel">
        <el-input v-model="formData.sourceChannel" placeholder="请输入来源渠道" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { ClassificationOfExperienceInformationApi, ClassificationOfExperienceInformationVO } from '@/api/smartcity/classificationofexperienceinformation'

/** 经验信息分类 表单 */
defineOptions({ name: 'ClassificationOfExperienceInformationForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  sector: undefined,
  applicationScenarios: undefined,
  empiricalNature: undefined,
  applicableObjects: undefined,
  sourceChannel: undefined
})
const formRules = reactive({
})
const formRef = ref() // 表单 ref

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
      formData.value = await ClassificationOfExperienceInformationApi.getClassificationOfExperienceInformation(id)
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
    const data = formData.value as unknown as ClassificationOfExperienceInformationVO
    if (formType.value === 'create') {
      await ClassificationOfExperienceInformationApi.createClassificationOfExperienceInformation(data)
      message.success(t('common.createSuccess'))
    } else {
      await ClassificationOfExperienceInformationApi.updateClassificationOfExperienceInformation(data)
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
    sector: undefined,
    applicationScenarios: undefined,
    empiricalNature: undefined,
    applicableObjects: undefined,
    sourceChannel: undefined
  }
  formRef.value?.resetFields()
}
</script>
