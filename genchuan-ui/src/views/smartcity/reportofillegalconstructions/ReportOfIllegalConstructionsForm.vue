<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
    >
      <el-form-item label="上报编号" prop="reportNumber">
        <el-input v-model="formData.reportNumber" placeholder="请输入上报编号" />
      </el-form-item>
      <el-form-item label="上报人" prop="reportperson">
        <el-input v-model="formData.reportperson" placeholder="请输入上报人" />
      </el-form-item>
      <el-form-item label="上报人联系方式" prop="contactReporter">
        <el-input v-model="formData.contactReporter" placeholder="请输入上报人联系方式" />
      </el-form-item>
      <el-form-item label="上报时间" prop="reportTime">
        <el-date-picker
          v-model="formData.reportTime"
          type="datetime"
          value-format="x"
          placeholder="选择上报时间"
        />
      </el-form-item>
      <el-form-item label="违建详细地址" prop="detailedAddressConstruction">
        <el-input v-model="formData.detailedAddressConstruction" placeholder="请输入违建详细地址" />
      </el-form-item>
      <el-form-item label="违建所在区域" prop="constructionIsLocated">
        <el-input v-model="formData.constructionIsLocated" placeholder="请输入违建所在区域" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { ReportOfIllegalConstructionsApi, ReportOfIllegalConstructionsVO } from '@/api/smartcity/reportofillegalconstructions'

/** 违建上报 表单 */
defineOptions({ name: 'ReportOfIllegalConstructionsForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  reportNumber: undefined,
  reportperson: undefined,
  contactReporter: undefined,
  reportTime: undefined,
  detailedAddressConstruction: undefined,
  constructionIsLocated: undefined
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
      formData.value = await ReportOfIllegalConstructionsApi.getReportOfIllegalConstructions(id)
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
    const data = formData.value as unknown as ReportOfIllegalConstructionsVO
    if (formType.value === 'create') {
      await ReportOfIllegalConstructionsApi.createReportOfIllegalConstructions(data)
      message.success(t('common.createSuccess'))
    } else {
      await ReportOfIllegalConstructionsApi.updateReportOfIllegalConstructions(data)
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
    reportNumber: undefined,
    reportperson: undefined,
    contactReporter: undefined,
    reportTime: undefined,
    detailedAddressConstruction: undefined,
    constructionIsLocated: undefined
  }
  formRef.value?.resetFields()
}
</script>
