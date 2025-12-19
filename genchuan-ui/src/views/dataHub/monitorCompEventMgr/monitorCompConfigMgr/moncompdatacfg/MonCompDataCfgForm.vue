<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="配置ID" prop="monCompDataId">
        <el-input v-model="formData.monCompDataId" placeholder="请输入配置ID" />
      </el-form-item>
      <el-form-item label="部件分类ID" prop="compCatId">
        <el-input v-model="formData.compCatId" placeholder="请输入部件分类ID" />
      </el-form-item>
      <el-form-item label="部件分类名称" prop="compCatName">
        <el-input v-model="formData.compCatName" placeholder="请输入部件分类名称" />
      </el-form-item>
      <el-form-item label="数据类型" prop="dataType">
        <el-select v-model="formData.dataType" placeholder="请选择数据类型">
          <el-option label="请选择字典生成" value="1" />
        </el-select>
      </el-form-item>
      <el-form-item label="坐标系类型" prop="coordSystem">
        <el-input v-model="formData.coordSystem" placeholder="请输入坐标系类型" />
      </el-form-item>
      <el-form-item label="定位精度级别" prop="accuracyLevel">
        <el-input v-model="formData.accuracyLevel" placeholder="请输入定位精度级别" />
      </el-form-item>
      <el-form-item label="高程基准" prop="elevationDatum">
        <el-input v-model="formData.elevationDatum" placeholder="请输入高程基准" />
      </el-form-item>
      <el-form-item label="字段名称" prop="fieldName">
        <el-input v-model="formData.fieldName" placeholder="请输入字段名称" />
      </el-form-item>
      <el-form-item label="字段代码" prop="fieldCode">
        <el-input v-model="formData.fieldCode" placeholder="请输入字段代码" />
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
import { MonCompDataCfgApi, MonCompDataCfgVO } from '@/api/dataHub/monitorCompEventMgr/moncompdatacfg'

/** 监测部件数据配置 表单 */
defineOptions({ name: 'MonCompDataCfgForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  monCompDataId: undefined,
  compCatId: undefined,
  compCatName: undefined,
  dataType: undefined,
  coordSystem: undefined,
  accuracyLevel: undefined,
  elevationDatum: undefined,
  fieldName: undefined,
  fieldCode: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined
})
const formRules = reactive({
  monCompDataId: [{ required: true, message: '配置ID不能为空', trigger: 'blur' }],
  compCatId: [{ required: true, message: '部件分类ID不能为空', trigger: 'blur' }],
  compCatName: [{ required: true, message: '部件分类名称不能为空', trigger: 'blur' }],
  dataType: [{ required: true, message: '数据类型不能为空', trigger: 'change' }]
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
      formData.value = await MonCompDataCfgApi.getMonCompDataCfg(id)
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
    const data = formData.value as unknown as MonCompDataCfgVO
    if (formType.value === 'create') {
      await MonCompDataCfgApi.createMonCompDataCfg(data)
      message.success(t('common.createSuccess'))
    } else {
      await MonCompDataCfgApi.updateMonCompDataCfg(data)
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
    monCompDataId: undefined,
    compCatId: undefined,
    compCatName: undefined,
    dataType: undefined,
    coordSystem: undefined,
    accuracyLevel: undefined,
    elevationDatum: undefined,
    fieldName: undefined,
    fieldCode: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined
  }
  formRef.value?.resetFields()
}
</script>
