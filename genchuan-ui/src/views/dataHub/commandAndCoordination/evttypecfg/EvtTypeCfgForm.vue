<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="类型ID" prop="evtTypeId">
        <el-input v-model="formData.evtTypeId" placeholder="请输入类型ID" />
      </el-form-item>
      <el-form-item label="父类型ID" prop="parentTypeId">
        <el-input v-model="formData.parentTypeId" placeholder="请输入父类型ID" />
      </el-form-item>
      <el-form-item label="类型层级" prop="typeLevel">
        <el-input v-model="formData.typeLevel" placeholder="请输入类型层级" />
      </el-form-item>
      <el-form-item label="类型编码" prop="typeCode">
        <el-input v-model="formData.typeCode" placeholder="请输入类型编码" />
      </el-form-item>
      <el-form-item label="类型名称" prop="typeName">
        <el-input v-model="formData.typeName" placeholder="请输入类型名称" />
      </el-form-item>
      <el-form-item label="类型说明" prop="typeDesc">
        <el-input v-model="formData.typeDesc" placeholder="请输入类型说明" />
      </el-form-item>
      <el-form-item label="所属区域代码" prop="regionCode">
        <el-input v-model="formData.regionCode" placeholder="请输入所属区域代码" />
      </el-form-item>
      <el-form-item label="所属区域名称" prop="regionName">
        <el-input v-model="formData.regionName" placeholder="请输入所属区域名称" />
      </el-form-item>
      <el-form-item label="启用状态" prop="enableStatus">
        <el-radio-group v-model="formData.enableStatus">
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
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { EvtTypeCfgApi, EvtTypeCfgVO } from '@/api/dataHub/commandAndCoordination/evttypecfg'

/** 事件类型配置 表单 */
defineOptions({ name: 'EvtTypeCfgForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  evtTypeId: undefined,
  parentTypeId: undefined,
  typeLevel: undefined,
  typeCode: undefined,
  typeName: undefined,
  typeDesc: undefined,
  regionCode: undefined,
  regionName: undefined,
  enableStatus: undefined,
  createUser: undefined,
  updateUser: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
})
const formRules = reactive({
  evtTypeId: [{ required: true, message: '类型ID不能为空', trigger: 'blur' }],
  parentTypeId: [{ required: true, message: '父类型ID不能为空', trigger: 'blur' }],
  typeLevel: [{ required: true, message: '类型层级不能为空', trigger: 'blur' }],
  typeCode: [{ required: true, message: '类型编码不能为空', trigger: 'blur' }],
  typeName: [{ required: true, message: '类型名称不能为空', trigger: 'blur' }],
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
      formData.value = await EvtTypeCfgApi.getEvtTypeCfg(id)
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
    const data = formData.value as unknown as EvtTypeCfgVO
    if (formType.value === 'create') {
      await EvtTypeCfgApi.createEvtTypeCfg(data)
      message.success(t('common.createSuccess'))
    } else {
      await EvtTypeCfgApi.updateEvtTypeCfg(data)
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
    evtTypeId: undefined,
    parentTypeId: undefined,
    typeLevel: undefined,
    typeCode: undefined,
    typeName: undefined,
    typeDesc: undefined,
    regionCode: undefined,
    regionName: undefined,
    enableStatus: undefined,
    createUser: undefined,
    updateUser: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined,
  }
  formRef.value?.resetFields()
}
</script>
