<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="指南ID" prop="generalOperGuideId">
        <el-input v-model="formData.generalOperGuideId" placeholder="请输入指南ID" />
      </el-form-item>
      <el-form-item label="指南标题" prop="guideTitle">
        <el-input v-model="formData.guideTitle" placeholder="请输入指南标题" />
      </el-form-item>
      <el-form-item label="操作场景" prop="operScenario">
        <el-input v-model="formData.operScenario" placeholder="请输入操作场景" />
      </el-form-item>
      <el-form-item label="操作步骤" prop="operSteps">
        <el-input v-model="formData.operSteps" placeholder="请输入操作步骤" />
      </el-form-item>
      <el-form-item label="适用角色" prop="applicableRole">
        <el-input v-model="formData.applicableRole" placeholder="请输入适用角色" />
      </el-form-item>
      <el-form-item label="版本号" prop="version">
        <el-input v-model="formData.version" placeholder="请输入版本号" />
      </el-form-item>
      <el-form-item label="生效时间" prop="effectiveTime">
        <el-date-picker
          v-model="formData.effectiveTime"
          type="date"
          value-format="x"
          placeholder="选择生效时间"
        />
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
import { GeneralOperGuideApi, GeneralOperGuideVO } from '@/api/dataHub/businessGuide/generaloperguide'

/** 通用操作指南 表单 */
defineOptions({ name: 'GeneralOperGuideForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  generalOperGuideId: undefined,
  guideTitle: undefined,
  operScenario: undefined,
  operSteps: undefined,
  applicableRole: undefined,
  version: undefined,
  effectiveTime: undefined,
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
  generalOperGuideId: [{ required: true, message: '指南ID不能为空', trigger: 'blur' }],
  guideTitle: [{ required: true, message: '指南标题不能为空', trigger: 'blur' }],
  operScenario: [{ required: true, message: '操作场景不能为空', trigger: 'blur' }],
  operSteps: [{ required: true, message: '操作步骤不能为空', trigger: 'blur' }],
  applicableRole: [{ required: true, message: '适用角色不能为空', trigger: 'blur' }],
  version: [{ required: true, message: '版本号不能为空', trigger: 'blur' }],
  effectiveTime: [{ required: true, message: '生效时间不能为空', trigger: 'blur' }],
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
      formData.value = await GeneralOperGuideApi.getGeneralOperGuide(id)
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
    const data = formData.value as unknown as GeneralOperGuideVO
    if (formType.value === 'create') {
      await GeneralOperGuideApi.createGeneralOperGuide(data)
      message.success(t('common.createSuccess'))
    } else {
      await GeneralOperGuideApi.updateGeneralOperGuide(data)
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
    generalOperGuideId: undefined,
    guideTitle: undefined,
    operScenario: undefined,
    operSteps: undefined,
    applicableRole: undefined,
    version: undefined,
    effectiveTime: undefined,
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
