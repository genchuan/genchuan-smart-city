<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="巡查时间" prop="patrolTime">
        <el-date-picker
          v-model="formData.patrolTime"
          type="datetime"
          value-format="x"
          placeholder="选择巡查时间"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="区域" prop="region">
        <el-input v-model="formData.region" placeholder="请输入区域" />
      </el-form-item>
      <el-form-item label="人员" prop="staff">
        <el-input v-model="formData.staff" placeholder="请输入人员" />
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
  DevelopmentInspectionPlanApi,
  DevelopmentInspectionPlanVO
} from '@/api/smartcity/developmentinspectionplan'

/** 巡查计划管理 表单 */
defineOptions({ name: 'DevelopmentInspectionPlanForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  patrolTime: undefined,
  region: undefined,
  staff: undefined
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
      const res = await DevelopmentInspectionPlanApi.getDevelopmentInspectionPlan(id)
      formData.value = {
        ...res,
        // 将字符串时间戳转为数字
        patrolTime: res.patrolTime ? Number(res.patrolTime) : undefined
      }
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
    const data = formData.value as unknown as DevelopmentInspectionPlanVO
    if (formType.value === 'create') {
      await DevelopmentInspectionPlanApi.createDevelopmentInspectionPlan(data)
      message.success(t('common.createSuccess'))
    } else {
      await DevelopmentInspectionPlanApi.updateDevelopmentInspectionPlan(data)
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
    patrolTime: undefined,
    region: undefined,
    staff: undefined
  }
  formRef.value?.resetFields()
}
</script>
