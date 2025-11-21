<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="法规类别" prop="regulatoryCategory">
        <el-input v-model="formData.regulatoryCategory" placeholder="请输入法规类别" />
      </el-form-item>
      <el-form-item label="所属领域" prop="isArea">
        <el-input v-model="formData.isArea" placeholder="请输入所属领域" />
      </el-form-item>
<!--      <el-form-item label="主题" prop="theme">
        <el-input v-model="formData.theme" placeholder="请输入主题" />
      </el-form-item>
      <el-form-item label="适用对象" prop="applicableObjects">
        <el-input v-model="formData.applicableObjects" placeholder="请输入适用对象" />
      </el-form-item>
      <el-form-item label="效力层级" prop="levelOfEffectiveness">
        <el-input v-model="formData.levelOfEffectiveness" placeholder="请输入效力层级" />
      </el-form-item>
      <el-form-item label="发布部门" prop="departmentOfPublication">
        <el-input v-model="formData.departmentOfPublication" placeholder="请输入发布部门" />
      </el-form-item>
      <el-form-item label="实施时间" prop="implementationTime">
        <el-date-picker
          v-model="formData.implementationTime"
          type="date"
          value-format="x"
          placeholder="选择实施时间"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="废止时间 " prop="revocatoryDate">
        <el-date-picker
          v-model="formData.revocatoryDate"
          type="date"
          value-format="x"
          placeholder="选择废止时间 "
          class="common-Width100"
        />
      </el-form-item>-->
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { ClassificationOfPoliciesAndRegulationsApi, ClassificationOfPoliciesAndRegulationsVO } from '@/api/smartcity/classificationofpoliciesandregulations'

/** 政策法规分类 表单 */
defineOptions({ name: 'ClassificationOfPoliciesAndRegulationsForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  regulatoryCategory: undefined,
  isArea: undefined,
  theme: undefined,
  applicableObjects: undefined,
  levelOfEffectiveness: undefined,
  departmentOfPublication: undefined,
  implementationTime: undefined,
  revocatoryDate: undefined
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
      formData.value = await ClassificationOfPoliciesAndRegulationsApi.getClassificationOfPoliciesAndRegulations(id)
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
    const data = formData.value as unknown as ClassificationOfPoliciesAndRegulationsVO
    if (formType.value === 'create') {
      await ClassificationOfPoliciesAndRegulationsApi.createClassificationOfPoliciesAndRegulations(data)
      message.success(t('common.createSuccess'))
    } else {
      await ClassificationOfPoliciesAndRegulationsApi.updateClassificationOfPoliciesAndRegulations(data)
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
    regulatoryCategory: undefined,
    isArea: undefined,
    theme: undefined,
    applicableObjects: undefined,
    levelOfEffectiveness: undefined,
    departmentOfPublication: undefined,
    implementationTime: undefined,
    revocatoryDate: undefined
  }
  formRef.value?.resetFields()
}
</script>
