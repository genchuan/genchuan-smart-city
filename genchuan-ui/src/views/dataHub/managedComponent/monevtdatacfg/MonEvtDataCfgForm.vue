<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="配置ID" prop="monEvtDataId">
        <el-input v-model="formData.monEvtDataId" placeholder="请输入配置ID" />
      </el-form-item>
      <el-form-item label="事件分类ID" prop="evtCatId">
        <el-input v-model="formData.evtCatId" placeholder="请输入事件分类ID" />
      </el-form-item>
      <el-form-item label="事件分类名称" prop="evtCatName">
        <el-input v-model="formData.evtCatName" placeholder="请输入事件分类名称" />
      </el-form-item>
      <el-form-item label="字段名称" prop="fieldName">
        <el-input v-model="formData.fieldName" placeholder="请输入字段名称" />
      </el-form-item>
      <el-form-item label="字段代码" prop="fieldCode">
        <el-input v-model="formData.fieldCode" placeholder="请输入字段代码" />
      </el-form-item>
      <el-form-item label="字段类型" prop="fieldType">
        <el-select v-model="formData.fieldType" placeholder="请选择字段类型">
          <el-option label="请选择字典生成" value="1" />
        </el-select>
      </el-form-item>
      <el-form-item label="字段长度" prop="fieldLength">
        <el-input v-model="formData.fieldLength" placeholder="请输入字段长度" />
      </el-form-item>
      <el-form-item label="约束条件" prop="constraintType">
        <el-select v-model="formData.constraintType" placeholder="请选择约束条件">
          <el-option label="请选择字典生成" value="1" />
        </el-select>
      </el-form-item>
      <el-form-item label="值域范围" prop="valueRange">
        <el-input v-model="formData.valueRange" placeholder="请输入值域范围" />
      </el-form-item>
      <el-form-item label="字段说明" prop="fieldDesc">
        <el-input v-model="formData.fieldDesc" placeholder="请输入字段说明" />
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
import { MonEvtDataCfgApi, MonEvtDataCfgVO } from '@/api/dataHub/managedComponent/monevtdatacfg'

/** 监测事件数据配置 表单 */
defineOptions({ name: 'MonEvtDataCfgForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  monEvtDataId: undefined,
  evtCatId: undefined,
  evtCatName: undefined,
  fieldName: undefined,
  fieldCode: undefined,
  fieldType: undefined,
  fieldLength: undefined,
  constraintType: undefined,
  valueRange: undefined,
  fieldDesc: undefined,
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
  monEvtDataId: [{ required: true, message: '配置ID不能为空', trigger: 'blur' }],
  evtCatId: [{ required: true, message: '事件分类ID不能为空', trigger: 'blur' }],
  evtCatName: [{ required: true, message: '事件分类名称不能为空', trigger: 'blur' }],
  fieldName: [{ required: true, message: '字段名称不能为空', trigger: 'blur' }],
  fieldCode: [{ required: true, message: '字段代码不能为空', trigger: 'blur' }],
  fieldType: [{ required: true, message: '字段类型不能为空', trigger: 'change' }],
  fieldLength: [{ required: true, message: '字段长度不能为空', trigger: 'blur' }],
  constraintType: [{ required: true, message: '约束条件不能为空', trigger: 'change' }],
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
      formData.value = await MonEvtDataCfgApi.getMonEvtDataCfg(id)
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
    const data = formData.value as unknown as MonEvtDataCfgVO
    if (formType.value === 'create') {
      await MonEvtDataCfgApi.createMonEvtDataCfg(data)
      message.success(t('common.createSuccess'))
    } else {
      await MonEvtDataCfgApi.updateMonEvtDataCfg(data)
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
    monEvtDataId: undefined,
    evtCatId: undefined,
    evtCatName: undefined,
    fieldName: undefined,
    fieldCode: undefined,
    fieldType: undefined,
    fieldLength: undefined,
    constraintType: undefined,
    valueRange: undefined,
    fieldDesc: undefined,
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
