<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="预警编号" prop="warningNumber">
        <el-input v-model="formData.warningNumber" placeholder="请输入预警编号" />
      </el-form-item>
      <el-form-item label="预警时间" prop="warningTime">
        <el-date-picker
          v-model="formData.warningTime"
          type="date"
          value-format="x"
          placeholder="选择预警时间"
        />
      </el-form-item>
      <el-form-item label="预警级别" prop="warningLevel">
        <el-input v-model="formData.warningLevel" placeholder="请输入预警级别" />
      </el-form-item>
      <el-form-item label="预警区域" prop="warningArea">
        <el-input v-model="formData.warningArea" placeholder="请输入预警区域" />
      </el-form-item>
      <el-form-item label="监测点位" prop="monitoringPoints">
        <el-input v-model="formData.monitoringPoints" placeholder="请输入监测点位" />
      </el-form-item>
      <el-form-item label="实时水位" prop="realTimeWaterLevel">
        <el-input v-model="formData.realTimeWaterLevel" placeholder="请输入实时水位" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { DrainageSafetyApi, DrainageSafetyVO } from '@/api/smartcity/drainagesafety'

/** 排水安全 表单 */
defineOptions({ name: 'DrainageSafetyForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  warningNumber: undefined,
  warningTime: undefined,
  warningLevel: undefined,
  warningArea: undefined,
  monitoringPoints: undefined,
  realTimeWaterLevel: undefined
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
      formData.value = await DrainageSafetyApi.getDrainageSafety(id)
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
    const data = formData.value as unknown as DrainageSafetyVO
    if (formType.value === 'create') {
      await DrainageSafetyApi.createDrainageSafety(data)
      message.success(t('common.createSuccess'))
    } else {
      await DrainageSafetyApi.updateDrainageSafety(data)
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
    warningNumber: undefined,
    warningTime: undefined,
    warningLevel: undefined,
    warningArea: undefined,
    monitoringPoints: undefined,
    realTimeWaterLevel: undefined
  }
  formRef.value?.resetFields()
}
</script>