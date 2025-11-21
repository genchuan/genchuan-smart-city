<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="制定机关" prop="developingAgencies">
        <el-input v-model="formData.developingAgencies" placeholder="请输入制定机关"/>
      </el-form-item>
      <el-form-item label="政策法规名称" prop="nameOfPolicyAndRegulation">
        <el-input v-model="formData.nameOfPolicyAndRegulation" placeholder="请输入政策法规名称"/>
      </el-form-item>
      <el-form-item label="文号" prop="documentNumber">
        <el-input v-model="formData.documentNumber" placeholder="请输入文号"/>
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
      <el-form-item label="生效日期" prop="effectiveDate">
        <el-date-picker
          v-model="formData.effectiveDate"
          type="date"
          value-format="x"
          placeholder="选择生效日期"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="失效日期" prop="expiringDate">
        <el-date-picker
          v-model="formData.expiringDate"
          type="date"
          value-format="x"
          placeholder="选择失效日期"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="法规类别" prop="regulatoryCategory">
        <el-select v-model="formData.regulatoryCategory" placeholder="请选择法规类别">
          <el-option
            v-for="item in regulatoryCategoryOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
        <!--<el-input v-model="formData.regulatoryCategory" placeholder="请输入法规类别" />-->
      </el-form-item>
      <el-form-item label="所属领域" prop="isArea">
        <el-input v-model="formData.isArea" placeholder="请输入所属领域"/>
      </el-form-item>
      <el-form-item label="适用范围" prop="scopeOfApplication">
        <el-input v-model="formData.scopeOfApplication" placeholder="请输入适用范围"/>
      </el-form-item>
      <el-form-item label="正文内容" prop="mainContent">
        <el-input v-model="formData.mainContent" type="textarea" placeholder="请输入正文内容"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  PolicyAndRegulationInputApi,
  PolicyAndRegulationInputVO
} from '@/api/smartcity/policyandregulationinput'
import {
  ClassificationOfPoliciesAndRegulationsApi
} from '@/api/smartcity/classificationofpoliciesandregulations'

/** 政策法规录入 表单 */
defineOptions({name: 'PolicyAndRegulationInputForm'})

const {t} = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
let regulatoryCategoryOptions = ref([]);//法规分类

const initData = async () => {
  let queryParams={
    pageNo:1,
    pageSize:100
  }
  const data = await ClassificationOfPoliciesAndRegulationsApi.getClassificationOfPoliciesAndRegulationsPage(queryParams);
  regulatoryCategoryOptions.value = data.list.map(item => ({
    label: item.isArea,
    value:item.id
  }));
}

/** 初始化 **/
onMounted(() => {
  initData()
})


const formData = ref({
  id: undefined,
  developingAgencies: undefined,
  nameOfPolicyAndRegulation: undefined,
  documentNumber: undefined,
  releaseDate: undefined,
  effectiveDate: undefined,
  expiringDate: undefined,
  // regulatoryCategory: undefined,
  isArea: undefined,
  scopeOfApplication: undefined,
  mainContent: undefined
})
const formRules = reactive({})
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
      formData.value = await PolicyAndRegulationInputApi.getPolicyAndRegulationInput(id)
    } finally {
      formLoading.value = false
    }
  }
}
defineExpose({open}) // 提供 open 方法，用于打开弹窗

/** 提交表单 */
const emit = defineEmits(['success']) // 定义 success 事件，用于操作成功后的回调
const submitForm = async () => {
  // 校验表单
  await formRef.value.validate()
  // 提交请求
  formLoading.value = true
  try {
    const data = formData.value as unknown as PolicyAndRegulationInputVO
    if (formType.value === 'create') {
      await PolicyAndRegulationInputApi.createPolicyAndRegulationInput(data)
      message.success(t('common.createSuccess'))
    } else {
      await PolicyAndRegulationInputApi.updatePolicyAndRegulationInput(data)
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
    developingAgencies: undefined,
    nameOfPolicyAndRegulation: undefined,
    documentNumber: undefined,
    releaseDate: undefined,
    effectiveDate: undefined,
    expiringDate: undefined,
    regulatoryCategory: undefined,
    isArea: undefined,
    scopeOfApplication: undefined,
    mainContent: undefined
  }
  formRef.value?.resetFields()
}
</script>
