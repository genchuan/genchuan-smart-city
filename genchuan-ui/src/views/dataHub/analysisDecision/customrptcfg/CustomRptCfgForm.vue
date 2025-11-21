<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="配置ID" prop="customRptCfgId">
        <el-input v-model="formData.customRptCfgId" placeholder="请输入配置ID" />
      </el-form-item>
      <el-form-item label="报表模板名称" prop="rptTemplateName">
        <el-input v-model="formData.rptTemplateName" placeholder="请输入报表模板名称" />
      </el-form-item>
      <el-form-item label="报表类型" prop="rptType">
        <el-select v-model="formData.rptType" placeholder="请选择报表类型">
          <el-option label="请选择字典生成" value="1" />
        </el-select>
      </el-form-item>
      <el-form-item label="数据来源表" prop="dataSourceTable">
        <el-input v-model="formData.dataSourceTable" placeholder="请输入数据来源表" />
      </el-form-item>
      <el-form-item label="统计维度配置" prop="statDimensionCfg">
        <el-input v-model="formData.statDimensionCfg" placeholder="请输入统计维度配置" />
      </el-form-item>
      <el-form-item label="统计指标配置" prop="statIdxCfg">
        <el-input v-model="formData.statIdxCfg" placeholder="请输入统计指标配置" />
      </el-form-item>
      <el-form-item label="展示样式配置" prop="displayStyleCfg">
        <el-input v-model="formData.displayStyleCfg" placeholder="请输入展示样式配置" />
      </el-form-item>
      <el-form-item label="筛选条件配置" prop="filterCfg">
        <el-input v-model="formData.filterCfg" placeholder="请输入筛选条件配置" />
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
import { CustomRptCfgApi, CustomRptCfgVO } from '@/api/dataHub/analysisDecision/customrptcfg'

/** 自定义报表配置 表单 */
defineOptions({ name: 'CustomRptCfgForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  customRptCfgId: undefined,
  rptTemplateName: undefined,
  rptType: undefined,
  dataSourceTable: undefined,
  statDimensionCfg: undefined,
  statIdxCfg: undefined,
  displayStyleCfg: undefined,
  filterCfg: undefined,
  templateStatus: undefined,
  createUser: undefined,
  updateUser: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  customRptCfgId: [{ required: true, message: '配置ID不能为空', trigger: 'blur' }],
  rptTemplateName: [{ required: true, message: '报表模板名称不能为空', trigger: 'blur' }],
  rptType: [{ required: true, message: '报表类型不能为空', trigger: 'change' }],
  dataSourceTable: [{ required: true, message: '数据来源表不能为空', trigger: 'blur' }],
  statDimensionCfg: [{ required: true, message: '统计维度配置不能为空', trigger: 'blur' }],
  statIdxCfg: [{ required: true, message: '统计指标配置不能为空', trigger: 'blur' }],
  displayStyleCfg: [{ required: true, message: '展示样式配置不能为空', trigger: 'blur' }],
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
      formData.value = await CustomRptCfgApi.getCustomRptCfg(id)
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
    const data = formData.value as unknown as CustomRptCfgVO
    if (formType.value === 'create') {
      await CustomRptCfgApi.createCustomRptCfg(data)
      message.success(t('common.createSuccess'))
    } else {
      await CustomRptCfgApi.updateCustomRptCfg(data)
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
    customRptCfgId: undefined,
    rptTemplateName: undefined,
    rptType: undefined,
    dataSourceTable: undefined,
    statDimensionCfg: undefined,
    statIdxCfg: undefined,
    displayStyleCfg: undefined,
    filterCfg: undefined,
    templateStatus: undefined,
    createUser: undefined,
    updateUser: undefined,
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
