<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="统计ID" prop="statAnalysisId">
        <el-input v-model="formData.statAnalysisId" placeholder="请输入统计ID" />
      </el-form-item>
      <el-form-item label="统计周期" prop="statCycle">
        <el-input v-model="formData.statCycle" placeholder="请输入统计周期" />
      </el-form-item>
      <el-form-item label="统计周期名称" prop="statCycleName">
        <el-input v-model="formData.statCycleName" placeholder="请输入统计周期名称" />
      </el-form-item>
      <el-form-item label="系统创建时间" prop="createTimeSys">
        <el-date-picker
          v-model="formData.createTimeSys"
          type="date"
          value-format="x"
          placeholder="选择系统创建时间"
        />
      </el-form-item>
      <el-form-item label="系统更新时间" prop="updateTimeSys">
        <el-date-picker
          v-model="formData.updateTimeSys"
          type="date"
          value-format="x"
          placeholder="选择系统更新时间"
        />
      </el-form-item>
      <el-form-item label="分类扩展字段1" prop="extCat1">
        <el-input v-model="formData.extCat1" placeholder="请输入分类扩展字段1" />
      </el-form-item>
      <el-form-item label="分类扩展字段2" prop="extCat2">
        <el-input v-model="formData.extCat2" placeholder="请输入分类扩展字段2" />
      </el-form-item>
      <el-form-item label="通用扩展字段1" prop="extCommon1">
        <el-input v-model="formData.extCommon1" placeholder="请输入通用扩展字段1" />
      </el-form-item>
      <el-form-item label="通用扩展字段2" prop="extCommon2">
        <el-input v-model="formData.extCommon2" placeholder="请输入通用扩展字段2" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { AnalysisAppSceneApi, AnalysisAppSceneVO } from '@/api/dataHub/analysisDecision/analysisappscene'

/** 按应用场景分析研判统计 表单 */
defineOptions({ name: 'AnalysisAppSceneForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  statAnalysisId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
})
const formRules = reactive({
  statAnalysisId: [{ required: true, message: '统计ID不能为空', trigger: 'blur' }],
  statCycle: [{ required: true, message: '统计周期不能为空', trigger: 'blur' }],
  statCycleName: [{ required: true, message: '统计周期名称不能为空', trigger: 'blur' }],
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
      formData.value = await AnalysisAppSceneApi.getAnalysisAppScene(id)
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
    const data = formData.value as unknown as AnalysisAppSceneVO
    if (formType.value === 'create') {
      await AnalysisAppSceneApi.createAnalysisAppScene(data)
      message.success(t('common.createSuccess'))
    } else {
      await AnalysisAppSceneApi.updateAnalysisAppScene(data)
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
    statAnalysisId: undefined,
    statCycle: undefined,
    statCycleName: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined,
  }
  formRef.value?.resetFields()
}
</script>
