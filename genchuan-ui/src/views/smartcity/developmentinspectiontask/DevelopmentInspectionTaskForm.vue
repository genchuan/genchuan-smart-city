<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="巡查计划" prop="plan">
        <el-input v-model="formData.plan" placeholder="请输入巡查计划" class="common-Width100" />
      </el-form-item>
      <el-form-item label="巡查任务" prop="task">
        <el-input v-model="formData.task" placeholder="请输入巡查任务" class="common-Width100" />
      </el-form-item>
      <el-form-item label="巡查记录" prop="record">
        <el-input v-model="formData.record" placeholder="请输入巡查记录" class="common-Width100" />
      </el-form-item>
      <el-form-item label="巡查时间" prop="patrolTime">
        <el-date-picker
          v-model="formData.patrolTime"
          type="date"
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
      <el-form-item label="发现的问题" prop="foundProblems">
        <el-input v-model="formData.foundProblems" placeholder="请输入发现的问题" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { DevelopmentInspectionTaskApi, DevelopmentInspectionTaskVO } from '@/api/smartcity/developmentinspectiontask'

/** 巡查任务管理 表单 */
defineOptions({ name: 'DevelopmentInspectionTaskForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  plan: undefined,
  task: undefined,
  record: undefined,
  patrolTime: undefined,
  region: undefined,
  staff: undefined,
  foundProblems: undefined
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
      const detail = await DevelopmentInspectionTaskApi.getDevelopmentInspectionTask(id)
      // 处理巡查时间回显：确保时间戳为数字类型（数据库存储的是时间戳）
      formData.value = {
        ...detail,
        // 转换时间戳为数字（兼容字符串格式的时间戳）
        patrolTime: detail.patrolTime ? Number(detail.patrolTime) : undefined
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
    const data = formData.value as unknown as DevelopmentInspectionTaskVO
    if (formType.value === 'create') {
      await DevelopmentInspectionTaskApi.createDevelopmentInspectionTask(data)
      message.success(t('common.createSuccess'))
    } else {
      await DevelopmentInspectionTaskApi.updateDevelopmentInspectionTask(data)
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
    plan: undefined,
    task: undefined,
    record: undefined,
    patrolTime: undefined,
    region: undefined,
    staff: undefined,
    foundProblems: undefined
  }
  formRef.value?.resetFields()
}
</script>
