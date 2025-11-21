<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="区域编号" prop="areaNumber">
        <el-input v-model="formData.areaNumber" placeholder="请输入区域编号" />
      </el-form-item>
      <el-form-item label="区域名称" prop="areaName">
        <el-input v-model="formData.areaName" placeholder="请输入区域名称" />
      </el-form-item>
      <el-form-item label="区域位置" prop="regionalLocation">
        <el-input v-model="formData.regionalLocation" placeholder="请输入区域位置" />
      </el-form-item>
      <el-form-item label="区域面积" prop="regionalArea">
        <el-input v-model="formData.regionalArea" placeholder="请输入区域面积" />
      </el-form-item>
      <el-form-item label="人员容量" prop="personnelCapacity">
        <el-input v-model="formData.personnelCapacity" placeholder="请输入人员容量" />
      </el-form-item>
      <el-form-item label="环境风险因素" prop="environmentalFactors">
        <el-input v-model="formData.environmentalFactors" placeholder="请输入环境风险因素" />
      </el-form-item>
      <el-form-item label="防护措施要求" prop="requirementsMeasures">
        <el-input v-model="formData.requirementsMeasures" placeholder="请输入防护措施要求" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { WorkAreaApi, WorkAreaVO } from '@/api/smartcity/workarea'

/** 作业区域 表单 */
defineOptions({ name: 'WorkAreaForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  areaNumber: undefined,
  areaName: undefined,
  regionalLocation: undefined,
  regionalArea: undefined,
  personnelCapacity: undefined,
  environmentalFactors: undefined,
  requirementsMeasures: undefined
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
      formData.value = await WorkAreaApi.getWorkArea(id)
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
    const data = formData.value as unknown as WorkAreaVO
    if (formType.value === 'create') {
      await WorkAreaApi.createWorkArea(data)
      message.success(t('common.createSuccess'))
    } else {
      await WorkAreaApi.updateWorkArea(data)
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
    areaNumber: undefined,
    areaName: undefined,
    regionalLocation: undefined,
    regionalArea: undefined,
    personnelCapacity: undefined,
    environmentalFactors: undefined,
    requirementsMeasures: undefined
  }
  formRef.value?.resetFields()
}
</script>