<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="指南ID" prop="systemFunctionGuideId">
        <el-input v-model="formData.systemFunctionGuideId" placeholder="请输入指南ID" />
      </el-form-item>
      <el-form-item label="指南标题" prop="guideTitle">
        <el-input v-model="formData.guideTitle" placeholder="请输入指南标题" />
      </el-form-item>
      <el-form-item label="关联功能ID" prop="relatedFunctionId">
        <el-input v-model="formData.relatedFunctionId" placeholder="请输入关联功能ID" />
      </el-form-item>
      <el-form-item label="关联功能名称" prop="relatedFunctionName">
        <el-input v-model="formData.relatedFunctionName" placeholder="请输入关联功能名称" />
      </el-form-item>
      <el-form-item label="功能用途说明" prop="functionPurpose">
        <el-input v-model="formData.functionPurpose" placeholder="请输入功能用途说明" />
      </el-form-item>
      <el-form-item label="操作步骤" prop="operSteps">
        <el-input v-model="formData.operSteps" placeholder="请输入操作步骤" />
      </el-form-item>
      <el-form-item label="常见问题及解决方案" prop="commonProblems">
        <el-input v-model="formData.commonProblems" placeholder="请输入常见问题及解决方案" />
      </el-form-item>
      <el-form-item label="适用角色" prop="applicableRole">
        <el-input v-model="formData.applicableRole" placeholder="请输入适用角色" />
      </el-form-item>
      <el-form-item label="指南附件ID" prop="guideAttachId">
        <el-input v-model="formData.guideAttachId" placeholder="请输入指南附件ID" />
      </el-form-item>
      <el-form-item label="指南附件路径" prop="guideAttachPath">
        <el-input v-model="formData.guideAttachPath" placeholder="请输入指南附件路径" />
      </el-form-item>
      <el-form-item label="指南状态" prop="guideStatus">
        <el-radio-group v-model="formData.guideStatus">
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
import { SystemFunctionGuideApi, SystemFunctionGuideVO } from '@/api/dataHub/businessGuide/systemfunctionguide'

/** 系统功能指南 表单 */
defineOptions({ name: 'SystemFunctionGuideForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  systemFunctionGuideId: undefined,
  guideTitle: undefined,
  relatedFunctionId: undefined,
  relatedFunctionName: undefined,
  functionPurpose: undefined,
  operSteps: undefined,
  commonProblems: undefined,
  applicableRole: undefined,
  guideAttachId: undefined,
  guideAttachPath: undefined,
  guideStatus: undefined,
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
  systemFunctionGuideId: [{ required: true, message: '指南ID不能为空', trigger: 'blur' }],
  guideTitle: [{ required: true, message: '指南标题不能为空', trigger: 'blur' }],
  relatedFunctionId: [{ required: true, message: '关联功能ID不能为空', trigger: 'blur' }],
  relatedFunctionName: [{ required: true, message: '关联功能名称不能为空', trigger: 'blur' }],
  operSteps: [{ required: true, message: '操作步骤不能为空', trigger: 'blur' }],
  applicableRole: [{ required: true, message: '适用角色不能为空', trigger: 'blur' }],
  guideStatus: [{ required: true, message: '指南状态不能为空', trigger: 'blur' }],
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
      formData.value = await SystemFunctionGuideApi.getSystemFunctionGuide(id)
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
    const data = formData.value as unknown as SystemFunctionGuideVO
    if (formType.value === 'create') {
      await SystemFunctionGuideApi.createSystemFunctionGuide(data)
      message.success(t('common.createSuccess'))
    } else {
      await SystemFunctionGuideApi.updateSystemFunctionGuide(data)
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
    systemFunctionGuideId: undefined,
    guideTitle: undefined,
    relatedFunctionId: undefined,
    relatedFunctionName: undefined,
    functionPurpose: undefined,
    operSteps: undefined,
    commonProblems: undefined,
    applicableRole: undefined,
    guideAttachId: undefined,
    guideAttachPath: undefined,
    guideStatus: undefined,
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
