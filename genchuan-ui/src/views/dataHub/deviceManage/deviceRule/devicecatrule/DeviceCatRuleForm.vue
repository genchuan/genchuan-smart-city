<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="700px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
      class="custom-form"
    >
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="分类规则ID" prop="catRuleId">
            <el-input v-model="formData.catRuleId" placeholder="请输入分类规则ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="父类规则ID" prop="parentCatRuleId">
            <el-input v-model="formData.parentCatRuleId" placeholder="请输入父类规则ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分类层级" prop="catLevel">
            <el-input v-model="formData.catLevel" placeholder="请输入分类层级" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分类代码" prop="catCode">
            <el-input v-model="formData.catCode" placeholder="请输入分类代码" />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="分类名称" prop="catName">
            <el-input v-model="formData.catName" placeholder="请输入分类名称" />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="分类说明" prop="catDesc">
            <el-input
              v-model="formData.catDesc"
              placeholder="请输入分类说明"
              type="textarea"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="启用状态" prop="enableStatus">
            <el-select v-model="formData.enableStatus" placeholder="请选择启用状态">
              <el-option label="启用" value="启用" />
              <el-option label="禁用" value="禁用" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="创建时间" prop="createTimeSys">
            <el-date-picker
              v-model="formData.createTimeSys"
              type="date"
              value-format="x"
              placeholder="选择创建时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分类扩展字段1" prop="extCat1">
            <el-input v-model="formData.extCat1" placeholder="请输入分类扩展字段1" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分类扩展字段2" prop="extCat2">
            <el-input v-model="formData.extCat2" placeholder="请输入分类扩展字段2" />
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
import { DeviceCatRuleApi, DeviceCatRuleVO } from '@/api/dataHub/deviceManage/deviceRule/devicecatrule'

/** 设备分类规则配置 表单 */
defineOptions({ name: 'DeviceCatRuleForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<DeviceCatRuleVO>>({
  id: undefined,
  catRuleId: undefined,
  parentCatRuleId: undefined,
  catLevel: undefined,
  catCode: undefined,
  catName: undefined,
  catDesc: undefined,
  enableStatus: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
})
const formRules = reactive({
  parentCatRuleId: [{ required: true, message: '父类规则ID不能为空', trigger: 'blur' }],
  catLevel: [{ required: true, message: '分类层级不能为空', trigger: 'blur' }],
  catCode: [{ required: true, message: '分类代码不能为空', trigger: 'blur' }],
  catName: [{ required: true, message: '分类名称不能为空', trigger: 'blur' }],
  enableStatus: [{ required: true, message: '启用状态不能为空', trigger: 'change' }],
  createTimeSys: [{ required: true, message: '创建时间不能为空', trigger: 'change' }],
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
      const data = await DeviceCatRuleApi.getDeviceCatRule(id)
      formData.value = { ...data }
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
    const data = formData.value as unknown as DeviceCatRuleVO
    if (formType.value === 'create') {
      await DeviceCatRuleApi.createDeviceCatRule(data)
      message.success(t('common.createSuccess'))
    } else {
      await DeviceCatRuleApi.updateDeviceCatRule(data)
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
    catRuleId: undefined,
    parentCatRuleId: undefined,
    catLevel: undefined,
    catCode: undefined,
    catName: undefined,
    catDesc: undefined,
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
.custom-form {
  background-color: #f9fafb;
  padding: 20px;
  border-radius: 8px;
}

.el-form-item {
  margin-bottom: 16px;
}

::v-deep .el-form-item__label {
  font-weight: 500;
  color: #4b5563;
}

::v-deep .el-input__wrapper,
::v-deep .el-select__wrapper,
::v-deep .el-textarea__wrapper {
  border-radius: 6px;
}
</style>
