<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="600px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
      class="form-container"
    >
      <el-form-item label="关联规则ID" prop="relRuleId">
        <el-input v-model="formData.relRuleId" placeholder="请输入关联规则ID" />
      </el-form-item>
      <el-form-item label="设备分类ID" prop="deviceCatId">
        <el-input v-model="formData.deviceCatId" placeholder="请输入设备分类ID" />
      </el-form-item>
      <el-form-item label="设备分类名称" prop="deviceCatName">
        <el-input v-model="formData.deviceCatName" placeholder="请输入设备分类名称" />
      </el-form-item>
      <el-form-item label="关联对象类型" prop="relObjType">
        <el-input v-model="formData.relObjType" placeholder="请输入关联对象类型" />
      </el-form-item>
      <el-form-item label="关联对象ID" prop="relObjId">
        <el-input v-model="formData.relObjId" placeholder="请输入关联对象ID" />
      </el-form-item>
      <el-form-item label="关联对象名称" prop="relObjName">
        <el-input v-model="formData.relObjName" placeholder="请输入关联对象名称" />
      </el-form-item>
      <el-form-item label="关联必选标识" prop="isRequired">
        <el-radio-group v-model="formData.isRequired">
          <el-radio label="1">必填</el-radio>
          <el-radio label="0">可选</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="关联校验规则" prop="relCkRule">
        <el-input v-model="formData.relCkRule" placeholder="请输入关联校验规则" />
      </el-form-item>
      <el-form-item label="启用状态" prop="enableStatus">
        <el-radio-group v-model="formData.enableStatus">
          <el-radio label="1">启用</el-radio>
          <el-radio label="0">禁用</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="分类扩展字段1" prop="extCat1">
        <el-input v-model="formData.extCat1" placeholder="请输入分类扩展字段1" />
      </el-form-item>
      <el-form-item label="分类扩展字段2" prop="extCat2">
        <el-input v-model="formData.extCat2" placeholder="请输入分类扩展字段2" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { DeviceRelRuleApi, DeviceRelRuleVO } from '@/api/dataHub/deviceManage/deviceRule/devicerelrule'

/** 设备关联规则配置 表单 */
defineOptions({ name: 'DeviceRelRuleForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  relRuleId: undefined,
  deviceCatId: undefined,
  deviceCatName: undefined,
  relObjType: undefined,
  relObjId: undefined,
  relObjName: undefined,
  isRequired: '0',
  relCkRule: undefined,
  enableStatus: '1',
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
  relObjType: [{ required: true, message: '关联对象类型不能为空', trigger: 'blur' }],
  relObjId: [{ required: true, message: '关联对象ID不能为空', trigger: 'blur' }],
  relObjName: [{ required: true, message: '关联对象名称不能为空', trigger: 'blur' }],
  isRequired: [{ required: true, message: '关联必选标识不能为空', trigger: 'change' }],
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
      formData.value = await DeviceRelRuleApi.getDeviceRelRule(id)
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
    const data = formData.value as unknown as DeviceRelRuleVO
    if (formType.value === 'create') {
      await DeviceRelRuleApi.createDeviceRelRule(data)
      message.success(t('common.createSuccess'))
    } else {
      await DeviceRelRuleApi.updateDeviceRelRule(data)
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
    relRuleId: undefined,
    deviceCatId: undefined,
    deviceCatName: undefined,
    relObjType: undefined,
    relObjId: undefined,
    relObjName: undefined,
    isRequired: '0',
    relCkRule: undefined,
    enableStatus: '1',
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
  background-color: #fff;
  border-radius: 8px;
}

::v-deep .el-form-item {
  margin-bottom: 18px;
}

::v-deep .el-form-item__label {
  color: #4b5563;
  font-weight: 500;
}

::v-deep .el-dialog__body {
  padding: 20px;
}
</style>
