<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="指南ID" prop="specBusinessGuideId">
        <el-input v-model="formData.specBusinessGuideId" placeholder="请输入指南ID" />
      </el-form-item>
      <el-form-item label="指南标题" prop="guideTitle">
        <el-input v-model="formData.guideTitle" placeholder="请输入指南标题" />
      </el-form-item>
      <el-form-item label="业务领域代码" prop="businessFieldCode">
        <el-input v-model="formData.businessFieldCode" placeholder="请输入业务领域代码" />
      </el-form-item>
      <el-form-item label="业务领域名称" prop="businessFieldName">
        <el-input v-model="formData.businessFieldName" placeholder="请输入业务领域名称" />
      </el-form-item>
      <el-form-item label="关联业务表名" prop="relatedTableName">
        <el-input v-model="formData.relatedTableName" placeholder="请输入关联业务表名" />
      </el-form-item>
      <el-form-item label="关联业务表编码" prop="relatedTableCode">
        <el-input v-model="formData.relatedTableCode" placeholder="请输入关联业务表编码" />
      </el-form-item>
      <el-form-item label="业务流程说明" prop="businessProcessDesc">
        <el-input v-model="formData.businessProcessDesc" placeholder="请输入业务流程说明" />
      </el-form-item>
      <el-form-item label="操作步骤" prop="operSteps">
        <el-input v-model="formData.operSteps" placeholder="请输入操作步骤" />
      </el-form-item>
      <el-form-item label="业务数据录入规范" prop="dataSpec">
        <el-input v-model="formData.dataSpec" placeholder="请输入业务数据录入规范" />
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
import { SpecBusinessGuideApi, SpecBusinessGuideVO } from '@/api/dataHub/businessGuide/specbusinessguide'

/** 专项业务指南 表单 */
defineOptions({ name: 'SpecBusinessGuideForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  specBusinessGuideId: undefined,
  guideTitle: undefined,
  businessFieldCode: undefined,
  businessFieldName: undefined,
  relatedTableName: undefined,
  relatedTableCode: undefined,
  businessProcessDesc: undefined,
  operSteps: undefined,
  dataSpec: undefined,
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
  specBusinessGuideId: [{ required: true, message: '指南ID不能为空', trigger: 'blur' }],
  guideTitle: [{ required: true, message: '指南标题不能为空', trigger: 'blur' }],
  businessFieldCode: [{ required: true, message: '业务领域代码不能为空', trigger: 'blur' }],
  businessFieldName: [{ required: true, message: '业务领域名称不能为空', trigger: 'blur' }],
  relatedTableName: [{ required: true, message: '关联业务表名不能为空', trigger: 'blur' }],
  relatedTableCode: [{ required: true, message: '关联业务表编码不能为空', trigger: 'blur' }],
  businessProcessDesc: [{ required: true, message: '业务流程说明不能为空', trigger: 'blur' }],
  operSteps: [{ required: true, message: '操作步骤不能为空', trigger: 'blur' }],
  dataSpec: [{ required: true, message: '业务数据录入规范不能为空', trigger: 'blur' }],
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
      formData.value = await SpecBusinessGuideApi.getSpecBusinessGuide(id)
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
    const data = formData.value as unknown as SpecBusinessGuideVO
    if (formType.value === 'create') {
      await SpecBusinessGuideApi.createSpecBusinessGuide(data)
      message.success(t('common.createSuccess'))
    } else {
      await SpecBusinessGuideApi.updateSpecBusinessGuide(data)
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
    specBusinessGuideId: undefined,
    guideTitle: undefined,
    businessFieldCode: undefined,
    businessFieldName: undefined,
    relatedTableName: undefined,
    relatedTableCode: undefined,
    businessProcessDesc: undefined,
    operSteps: undefined,
    dataSpec: undefined,
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
