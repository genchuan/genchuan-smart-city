<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="750px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
      class="custom-form"
    >
      <!-- 第一行：基础资产信息 -->
      <el-row :gutter="20" class="form-row">
        <el-col :span="12">
          <el-form-item label="资产ID" prop="assetId">
            <el-input v-model="formData.assetId" placeholder="请输入资产ID" clearable />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="资产名称" prop="assetName">
            <el-input v-model="formData.assetName" placeholder="请输入资产名称" clearable />
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第二行：资产类型与档案 -->
      <el-row :gutter="20" class="form-row">
        <el-col :span="12">
          <el-form-item label="资产类型" prop="assetType">
            <el-select v-model="formData.assetType" placeholder="请选择资产类型" clearable>
              <el-option label="请选择字典生成" value="" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="资产标签" prop="assetLabel">
            <el-input v-model="formData.assetLabel" placeholder="请输入资产标签" clearable />
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第三行：外部ID与版本号 -->
      <el-row :gutter="20" class="form-row">
        <el-col :span="12">
          <el-form-item label="外部ID" prop="externalId">
            <el-input v-model="formData.externalId" placeholder="请输入外部ID" clearable />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="版本号" prop="version">
            <el-input v-model="formData.version" placeholder="请输入版本号" clearable type="number" />
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第四行：资产档案信息 -->
      <el-row :gutter="20" class="form-row">
        <el-col :span="12">
          <el-form-item label="资产档案ID" prop="assetProfileId">
            <el-input v-model="formData.assetProfileId" placeholder="请输入资产档案ID" clearable />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="资产档案名称" prop="assetProfileName">
            <el-input v-model="formData.assetProfileName" placeholder="请输入资产档案名称" clearable />
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第五行：客户信息 -->
      <el-row :gutter="20" class="form-row">
        <el-col :span="12">
          <el-form-item label="客户ID" prop="customerId">
            <el-input v-model="formData.customerId" placeholder="请输入客户ID" clearable />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="客户标题" prop="customerTitle">
            <el-input v-model="formData.customerTitle" placeholder="请输入客户标题" clearable />
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第六行：客户公开状态与系统租户ID -->
      <el-row :gutter="20" class="form-row">
        <el-col :span="12">
          <el-form-item label="客户是否公开" prop="customerIsPublic">
            <el-select v-model="formData.customerIsPublic" placeholder="请选择客户是否公开" clearable>
              <el-option label="是" value="true" />
              <el-option label="否" value="false" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="系统租户ID" prop="tenantIdSys">
            <el-input v-model="formData.tenantIdSys" placeholder="请输入系统租户ID" clearable type="number" />
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第七行：创建时间戳（时间戳转日期选择） -->
      <el-row :gutter="20" class="form-row">
        <el-col :span="24">
          <el-form-item label="创建时间戳" prop="createdTime">
            <el-date-picker
              v-model="formData.createdTime"
              type="datetime"
              value-format="x"
              placeholder="选择创建时间"
              class="w-full"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第八行：JSON类型字段（textarea输入） -->
      <el-row :gutter="20" class="form-row">
        <el-col :span="24">
          <el-form-item label="附加信息" prop="additionalInfo">
            <el-input
              v-model="formData.additionalInfo"
              placeholder="请输入JSON格式附加信息"
              type="textarea"
              rows="3"
              clearable
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20" class="form-row">
        <el-col :span="24">
          <el-form-item label="属性列表" prop="attributes">
            <el-input
              v-model="formData.attributes"
              placeholder="请输入JSON格式属性列表"
              type="textarea"
              rows="3"
              clearable
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20" class="form-row">
        <el-col :span="24">
          <el-form-item label="关联设备列表" prop="contextDevices">
            <el-input
              v-model="formData.contextDevices"
              placeholder="请输入JSON格式关联设备列表"
              type="textarea"
              rows="3"
              clearable
            />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { AssetInfoApi, AssetInfoVO } from '@/api/dataHub/assetManagement/assetinfo'

/** 资产信息 表单 */
defineOptions({ name: 'AssetInfoForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单类型：create/update
const formData = ref<Partial<AssetInfoVO>>({
  id: undefined,
  assetId: undefined,
  assetName: undefined,
  assetType: undefined,
  assetLabel: undefined,
  externalId: undefined,
  version: undefined,
  assetProfileId: undefined,
  assetProfileName: undefined,
  customerId: undefined,
  customerTitle: undefined,
  customerIsPublic: undefined,
  tenantIdSys: undefined,
  createdTime: undefined,
  additionalInfo: undefined,
  attributes: undefined,
  contextDevices: undefined,
  // 隐藏系统内部字段，不展示在表单
  entityType: undefined,
  tenantEntityType: undefined,
  customerEntityType: undefined,
  assetProfileEntityType: undefined,
})
const formRules = reactive({
  assetId: [{ required: true, message: '资产ID不能为空', trigger: 'blur' }],
  assetName: [{ required: true, message: '资产名称不能为空', trigger: 'blur' }],
  assetType: [{ required: true, message: '资产类型不能为空', trigger: 'change' }],
  assetProfileId: [{ required: true, message: '资产档案ID不能为空', trigger: 'blur' }],
  assetProfileName: [{ required: true, message: '资产档案名称不能为空', trigger: 'blur' }],
  version: [{ required: true, message: '版本号不能为空', trigger: 'blur' }, { type: 'number', message: '版本号必须为数字', trigger: 'blur' }],
  tenantIdSys: [{ required: true, message: '系统租户ID不能为空', trigger: 'blur' }, { type: 'number', message: '系统租户ID必须为数字', trigger: 'blur' }],
  createdTime: [{ required: true, message: '创建时间不能为空', trigger: 'blur' }],
  customerIsPublic: [{ required: true, message: '客户是否公开不能为空', trigger: 'change' }],
})
const formRef = ref() // 表单Ref

/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = t(`action.${type}`)
  formType.value = type
  resetForm()

  // 编辑时加载数据
  if (id) {
    formLoading.value = true
    try {
      const res = await AssetInfoApi.getAssetInfo(id)
      // JSON字段转字符串（便于textarea显示）
      formData.value = {
        ...res,
        additionalInfo: res.additionalInfo ? JSON.stringify(res.additionalInfo, null, 2) : undefined,
        attributes: res.attributes ? JSON.stringify(res.attributes, null, 2) : undefined,
        contextDevices: res.contextDevices ? JSON.stringify(res.contextDevices, null, 2) : undefined,
        // 时间戳转毫秒（适配日期选择器）
        createdTime: res.createdTime ? Number(res.createdTime) : undefined,
      }
    } finally {
      formLoading.value = false
    }
  }
}
defineExpose({ open }) // 暴露open方法

/** 提交表单 */
const emit = defineEmits(['success'])
const submitForm = async () => {
  await formRef.value.validate()
  formLoading.value = true

  try {
    // 处理JSON字段（字符串转对象）
    const submitData: AssetInfoVO = {
      ...formData.value as AssetInfoVO,
      additionalInfo: formData.value.additionalInfo ? JSON.parse(formData.value.additionalInfo) : undefined,
      attributes: formData.value.attributes ? JSON.parse(formData.value.attributes) : undefined,
      contextDevices: formData.value.contextDevices ? JSON.parse(formData.value.contextDevices) : undefined,
      // 布尔值处理（select返回字符串，转布尔）
      customerIsPublic: formData.value.customerIsPublic === 'true',
    } as AssetInfoVO

    if (formType.value === 'create') {
      await AssetInfoApi.createAssetInfo(submitData)
      message.success(t('common.createSuccess'))
    } else {
      await AssetInfoApi.updateAssetInfo(submitData)
      message.success(t('common.updateSuccess'))
    }

    dialogVisible.value = false
    emit('success')
  } catch (err: any) {
    message.error(err.message || '操作失败')
  } finally {
    formLoading.value = false
  }
}

/** 重置表单 */
const resetForm = () => {
  formData.value = {
    id: undefined,
    assetId: undefined,
    assetName: undefined,
    assetType: undefined,
    assetLabel: undefined,
    externalId: undefined,
    version: undefined,
    assetProfileId: undefined,
    assetProfileName: undefined,
    customerId: undefined,
    customerTitle: undefined,
    customerIsPublic: undefined,
    tenantIdSys: undefined,
    createdTime: undefined,
    additionalInfo: undefined,
    attributes: undefined,
    contextDevices: undefined,
    entityType: undefined,
    tenantEntityType: undefined,
    customerEntityType: undefined,
    assetProfileEntityType: undefined,
  }
  formRef.value?.resetFields()
}
</script>

<style scoped>
.custom-form {
  padding: 15px;
  background-color: #f9fafb;
  border-radius: 8px;
}

.form-row {
  margin-bottom: 12px;
}

::v-deep .el-form-item__label {
  color: #4b5563;
  font-weight: 500;
}

::v-deep .el-input__wrapper,
::v-deep .el-select__wrapper,
::v-deep .el-date-editor .el-input__wrapper {
  border-radius: 6px;
}

::v-deep .el-textarea__wrapper {
  border-radius: 6px;
}
</style>
