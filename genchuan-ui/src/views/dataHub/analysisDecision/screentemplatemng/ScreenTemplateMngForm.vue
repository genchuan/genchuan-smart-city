<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="模板ID" prop="screenTemplateId">
        <el-input v-model="formData.screenTemplateId" placeholder="请输入模板ID" />
      </el-form-item>
      <el-form-item label="模板名称" prop="templateName">
        <el-input v-model="formData.templateName" placeholder="请输入模板名称" />
      </el-form-item>
      <el-form-item label="大屏布局" prop="screenLayout">
        <el-input v-model="formData.screenLayout" placeholder="请输入大屏布局" />
      </el-form-item>
      <el-form-item label="数据来源配置" prop="dataSourceCfg">
        <el-input v-model="formData.dataSourceCfg" placeholder="请输入数据来源配置" />
      </el-form-item>
      <el-form-item label="组件配置" prop="componentCfg">
        <el-input v-model="formData.componentCfg" placeholder="请输入组件配置" />
      </el-form-item>
      <el-form-item label="刷新频率" prop="refreshFreq">
        <el-input v-model="formData.refreshFreq" placeholder="请输入刷新频率" />
      </el-form-item>
      <el-form-item label="模板状态" prop="templateStatus">
        <el-radio-group v-model="formData.templateStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="创建人" prop="createUser">
        <el-input v-model="formData.createUser" placeholder="请输入创建人" />
      </el-form-item>
      <el-form-item label="更新人" prop="updateUser">
        <el-input v-model="formData.updateUser" placeholder="请输入更新人" />
      </el-form-item>
      <el-form-item label="模板预览图" prop="templatePreview">
        <el-input v-model="formData.templatePreview" placeholder="请输入模板预览图" />
      </el-form-item>
      <el-form-item label="分类扩展字段1" prop="extCat1">
        <el-input v-model="formData.extCat1" placeholder="请输入分类扩展字段1" />
      </el-form-item>
      <el-form-item label="分类扩展字段2挥中心/展厅）" prop="extCat2">
        <el-input v-model="formData.extCat2" placeholder="请输入分类扩展字段2挥中心/展厅）" />
      </el-form-item>
      <el-form-item label="通用扩展字段1" prop="extCommon1">
        <el-input v-model="formData.extCommon1" placeholder="请输入通用扩展字段1" />
      </el-form-item>
      <el-form-item label="通用扩展字段2" prop="extCommon2">
        <el-input v-model="formData.extCommon2" placeholder="请输入通用扩展字段2" />
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
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { ScreenTemplateMngApi, ScreenTemplateMngVO } from '@/api/dataHub/analysisDecision/screentemplatemng'

/** 大屏模板管理 表单 */
defineOptions({ name: 'ScreenTemplateMngForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  screenTemplateId: undefined,
  templateName: undefined,
  screenLayout: undefined,
  dataSourceCfg: undefined,
  componentCfg: undefined,
  refreshFreq: undefined,
  templateStatus: undefined,
  createUser: undefined,
  updateUser: undefined,
  templatePreview: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  screenTemplateId: [{ required: true, message: '模板ID不能为空', trigger: 'blur' }],
  templateName: [{ required: true, message: '模板名称不能为空', trigger: 'blur' }],
  screenLayout: [{ required: true, message: '大屏布局不能为空', trigger: 'blur' }],
  dataSourceCfg: [{ required: true, message: '数据来源配置不能为空', trigger: 'blur' }],
  componentCfg: [{ required: true, message: '组件配置不能为空', trigger: 'blur' }],
  refreshFreq: [{ required: true, message: '刷新频率不能为空', trigger: 'blur' }],
  templateStatus: [{ required: true, message: '模板状态不能为空', trigger: 'blur' }],
  createUser: [{ required: true, message: '创建人不能为空', trigger: 'blur' }],
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
      formData.value = await ScreenTemplateMngApi.getScreenTemplateMng(id)
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
    const data = formData.value as unknown as ScreenTemplateMngVO
    if (formType.value === 'create') {
      await ScreenTemplateMngApi.createScreenTemplateMng(data)
      message.success(t('common.createSuccess'))
    } else {
      await ScreenTemplateMngApi.updateScreenTemplateMng(data)
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
    screenTemplateId: undefined,
    templateName: undefined,
    screenLayout: undefined,
    dataSourceCfg: undefined,
    componentCfg: undefined,
    refreshFreq: undefined,
    templateStatus: undefined,
    createUser: undefined,
    updateUser: undefined,
    templatePreview: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined,
  }
  formRef.value?.resetFields()
}
</script>
