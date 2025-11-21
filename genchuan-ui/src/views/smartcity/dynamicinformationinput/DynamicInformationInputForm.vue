<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="标题" prop="title">
        <el-input v-model="formData.title" placeholder="请输入标题" />
      </el-form-item>
      <el-form-item label="发布时间" prop="releaseTime">
        <el-date-picker
          v-model="formData.releaseTime"
          type="date"
          value-format="x"
          placeholder="选择发布时间"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="信息类别" prop="publishingSubject">
        <el-select v-model="formData.publishingSubject" placeholder="请选择信息类别">
          <el-option
            v-for="item in publishingSubjectOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
        <!--<el-input v-model="formData.publishingSubject" placeholder="请输入发布主体" />-->
      </el-form-item>
      <el-form-item label="内容概述" prop="contentOverview">
        <el-input v-model="formData.contentOverview" placeholder="请输入内容概述" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { DynamicInformationInputApi, DynamicInformationInputVO } from '@/api/smartcity/dynamicinformationinput'
import { DynamicInformationClassificationApi } from '@/api/smartcity/dynamicinformationclassification'

/** 动态信息录入 表单 */
defineOptions({ name: 'DynamicInformationInputForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  title: undefined,
  releaseTime: undefined,
  publishingSubject: undefined,
  contentOverview: undefined
})
const formRules = reactive({
})
const formRef = ref() // 表单 ref

let publishingSubjectOptions = ref([]);//

const initData = async () => {
  let queryParams={
    pageNo:1,
    pageSize:100
  }
  const data = await DynamicInformationClassificationApi.getDynamicInformationClassificationPage(queryParams);
  publishingSubjectOptions.value = data.list.map(item => ({
    label: item.messageSubject,
    value:item.id
  }));
}

/** 初始化 **/
onMounted(() => {
  initData()
})


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
      formData.value = await DynamicInformationInputApi.getDynamicInformationInput(id)
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
    const data = formData.value as unknown as DynamicInformationInputVO
    if (formType.value === 'create') {
      await DynamicInformationInputApi.createDynamicInformationInput(data)
      message.success(t('common.createSuccess'))
    } else {
      await DynamicInformationInputApi.updateDynamicInformationInput(data)
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
    title: undefined,
    releaseTime: undefined,
    publishingSubject: undefined,
    contentOverview: undefined
  }
  formRef.value?.resetFields()
}
</script>
