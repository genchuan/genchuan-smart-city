<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="700px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
      class="form-container"
    >
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="设备分类ID" prop="deviceCatId">
            <el-input v-model="formData.deviceCatId" placeholder="请输入设备分类ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="设备分类名称" prop="deviceCatName">
            <el-input v-model="formData.deviceCatName" placeholder="请输入设备分类名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="属性名称" prop="attrName">
            <el-input v-model="formData.attrName" placeholder="请输入属性名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="属性代码" prop="attrCode">
            <el-input v-model="formData.attrCode" placeholder="请输入属性代码" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="数据类型" prop="dataType">
            <el-input v-model="formData.dataType" placeholder="请输入数据类型" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="字段长度" prop="fieldLength">
            <el-input v-model.number="formData.fieldLength" placeholder="请输入字段长度" type="number" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="是否必选" prop="isRequired">
            <el-select v-model="formData.isRequired" placeholder="请选择是否必选">
              <el-option label="是" value="1" />
              <el-option label="否" value="0" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="计量单位" prop="unit">
            <el-input v-model="formData.unit" placeholder="请输入计量单位" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="值域范围" prop="valueRange">
            <el-input v-model="formData.valueRange" placeholder="请输入值域范围" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="默认值" prop="defaultValue">
            <el-input v-model="formData.defaultValue" placeholder="请输入默认值" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="24">
          <el-form-item label="属性说明" prop="attrDesc">
            <el-input v-model="formData.attrDesc" placeholder="请输入属性说明" type="textarea"  />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="启用状态" prop="enableStatus">
            <el-radio-group v-model="formData.enableStatus">
              <el-radio value="1" class="radio-enabled">启用</el-radio>
              <el-radio value="0" class="radio-disabled">禁用</el-radio>
            </el-radio-group>
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
import { DeviceAttrRuleApi, DeviceAttrRuleVO } from '@/api/dataHub/deviceManage/deviceRule/deviceattrrule'

/** 设备属性规则配置 表单 */
defineOptions({ name: 'DeviceAttrRuleForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  attrRuleId: undefined,
  deviceCatId: undefined,
  deviceCatName: undefined,
  attrName: undefined,
  attrCode: undefined,
  dataType: undefined,
  fieldLength: undefined,
  isRequired: undefined,
  unit: undefined,
  valueRange: undefined,
  defaultValue: undefined,
  attrDesc: undefined,
  enableStatus: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
})
const formRules = reactive({
  deviceCatId: [{ required: true, message: '设备分类ID不能为空', trigger: 'blur' }],
  deviceCatName: [{ required: true, message: '设备分类名称不能为空', trigger: 'blur' }],
  attrName: [{ required: true, message: '属性名称不能为空', trigger: 'blur' }],
  attrCode: [{ required: true, message: '属性代码不能为空', trigger: 'blur' }],
  dataType: [{ required: true, message: '数据类型不能为空', trigger: 'blur' }],
  isRequired: [{ required: true, message: '是否必选不能为空', trigger: 'change' }],
  enableStatus: [{ required: true, message: '启用状态不能为空', trigger: 'change' }],
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
      formData.value = await DeviceAttrRuleApi.getDeviceAttrRule(id)
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
    const data = formData.value as unknown as DeviceAttrRuleVO
    if (formType.value === 'create') {
      await DeviceAttrRuleApi.createDeviceAttrRule(data)
      message.success(t('common.createSuccess'))
    } else {
      await DeviceAttrRuleApi.updateDeviceAttrRule(data)
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
    attrRuleId: undefined,
    deviceCatId: undefined,
    deviceCatName: undefined,
    attrName: undefined,
    attrCode: undefined,
    dataType: undefined,
    fieldLength: undefined,
    isRequired: undefined,
    unit: undefined,
    valueRange: undefined,
    defaultValue: undefined,
    attrDesc: undefined,
    enableStatus: undefined,
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

<style scoped>
.form-container {
  padding: 15px;
  background-color: #f9fafb;
  border-radius: 8px;
}

.el-row {
  margin-bottom: 15px;
}

.radio-enabled {
  color: #00b42a;
  margin-right: 20px;
}

.radio-disabled {
  color: #f53f3f;
}
</style>
