<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="执法公示编号" prop="publicityNumber">
        <el-input v-model="formData.publicityNumber" placeholder="请输入执法公示编号" />
      </el-form-item>
      <el-form-item label="公示标题" prop="publicityTitle">
        <el-input v-model="formData.publicityTitle" placeholder="请输入公示标题" />
      </el-form-item>
      <el-form-item label="公示内容" prop="publicityContent">
        <el-input v-model="formData.publicityContent" type="textarea" placeholder="请输入公示内容" />
      </el-form-item>
      <el-form-item label="公示类型" prop="publicityType">
        <el-select v-model="formData.publicityType" placeholder="请选择公示类型">
          <el-option value="行政处罚公示" label="行政处罚公示" />
          <el-option value="行政许可公示" label="行政许可公示" />
          <el-option value="行政强制公示" label="行政强制公示" />
          <el-option value="执法依据公示" label="执法依据公示" />
          <el-option value="执法流程公示" label="执法流程公示" />
          <el-option value="执法监督公示" label="执法监督公示" />
        </el-select>
      </el-form-item>
      <el-form-item label="执法部门" prop="enforcement">
        <el-input v-model="formData.enforcement" placeholder="请输入执法部门" />
      </el-form-item>
      <el-form-item label="执法地点" prop="enforcementLocation">
        <el-input v-model="formData.enforcementLocation" placeholder="请输入执法地点" />
      </el-form-item>
      <el-form-item label="执法依据" prop="enforcementBasis">
        <el-input v-model="formData.enforcementBasis" placeholder="请输入执法依据" />
      </el-form-item>
      <el-form-item label="执法结果" prop="enforcementResults">
        <el-input v-model="formData.enforcementResults" placeholder="请输入执法结果" />
      </el-form-item>
      <el-form-item label="公示开始时间" prop="announcementStartTime">
        <el-date-picker
          v-model="formData.announcementStartTime"
          type="date"
          value-format="x"
          placeholder="选择公示开始时间"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="公示截止时间" prop="announcementDeadline">
        <el-date-picker
          v-model="formData.announcementDeadline"
          type="date"
          value-format="x"
          placeholder="选择公示截止时间"
          class="common-Width100"
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
import { EnforcementPublicityApi, EnforcementPublicityVO } from '@/api/smartcity/enforcementpublicity'

/** 执法公示 表单 */
defineOptions({ name: 'EnforcementPublicityForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  publicityNumber: undefined,
  publicityTitle: undefined,
  publicityContent: undefined,
  publicityType: undefined,
  enforcement: undefined,
  enforcementLocation: undefined,
  enforcementBasis: undefined,
  enforcementResults: undefined,
  announcementStartTime: undefined,
  announcementDeadline: undefined
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
      formData.value = await EnforcementPublicityApi.getEnforcementPublicity(id)
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
    const data = formData.value as unknown as EnforcementPublicityVO
    if (formType.value === 'create') {
      await EnforcementPublicityApi.createEnforcementPublicity(data)
      message.success(t('common.createSuccess'))
    } else {
      await EnforcementPublicityApi.updateEnforcementPublicity(data)
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
    publicityNumber: undefined,
    publicityTitle: undefined,
    publicityContent: undefined,
    publicityType: undefined,
    enforcement: undefined,
    enforcementLocation: undefined,
    enforcementBasis: undefined,
    enforcementResults: undefined,
    announcementStartTime: undefined,
    announcementDeadline: undefined
  }
  formRef.value?.resetFields()
}
</script>
