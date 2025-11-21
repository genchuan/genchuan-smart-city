<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
    >
      <el-form-item label="注记ID" prop="annotateId">
        <el-input v-model="formData.annotateId" placeholder="请输入注记ID" :disabled="isEditMode" />
      </el-form-item>
      <el-form-item label="比例尺" prop="scale">
        <el-input v-model="formData.scale" placeholder="请输入比例尺" :disabled="isEditMode" />
      </el-form-item>
      <el-form-item label="网格类型" prop="gridType">
        <el-input v-model="formData.gridType" placeholder="请输入网格类型" :disabled="isEditMode" />
      </el-form-item>
      <el-form-item label="字高(mm)" prop="fontHeight">
        <el-input v-model="formData.fontHeight" placeholder="请输入字高(mm)" />
      </el-form-item>
      <el-form-item label="字型" prop="fontType">
        <el-input v-model="formData.fontType" placeholder="请输入字型" :disabled="isEditMode" />
      </el-form-item>
      <el-form-item label="颜色C值(%)" prop="colorC">
        <el-input v-model="formData.colorC" placeholder="请输入颜色C值(%)" :disabled="isEditMode" />
      </el-form-item>
      <el-form-item label="颜色M值(%)" prop="colorM">
        <el-input v-model="formData.colorM" placeholder="请输入颜色M值(%)" :disabled="isEditMode" />
      </el-form-item>
      <el-form-item label="颜色Y值(%)" prop="colorY">
        <el-input v-model="formData.colorY" placeholder="请输入颜色Y值(%)" :disabled="isEditMode" />
      </el-form-item>
      <el-form-item label="颜色K值(%)" prop="colorK">
        <el-input v-model="formData.colorK" placeholder="请输入颜色K值(%)" :disabled="isEditMode" />
      </el-form-item>
      <el-form-item label="创建人" prop="createUserId">
        <el-input v-model="formData.createUserId" placeholder="请输入创建人" :disabled="isEditMode" />
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input v-model="formData.remark" placeholder="请输入备注" :disabled="isEditMode" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
import { GridCodeAnnotateApi, GridCodeAnnotateVO } from '@/api/dataHub/gridManagement/gridDiagramManage/gridcodeannotate'

/** 网格标识码注记配置 表单 */
defineOptions({ name: 'GridCodeAnnotateForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  annotateId: undefined,
  scale: undefined,
  gridType: undefined,
  fontHeight: undefined,
  fontType: undefined,
  colorC: undefined,
  colorM: undefined,
  colorY: undefined,
  colorK: undefined,
  createUserId: undefined,
  remark: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
})

const formRules = reactive({
  scale: [{ required: true, message: '比例尺不能为空', trigger: 'blur' }],
  gridType: [{ required: true, message: '网格类型不能为空', trigger: 'blur' }],
  fontHeight: [{ required: true, message: '字高(mm)不能为空', trigger: 'blur' }],
  fontType: [{ required: true, message: '字型不能为空', trigger: 'blur' }],
  colorC: [{ required: true, message: '颜色C值(%)不能为空', trigger: 'blur' }],
  colorM: [{ required: true, message: '颜色M值(%)不能为空', trigger: 'blur' }],
  colorY: [{ required: true, message: '颜色Y值(%)不能为空', trigger: 'blur' }],
  colorK: [{ required: true, message: '颜色K值(%)不能为空', trigger: 'blur' }],
  createUserId: [{ required: true, message: '创建人不能为空', trigger: 'blur' }],
})

const formRef = ref() // 表单 Ref

// 编辑模式标志
const isEditMode = ref(false)

/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = t('action.' + type)
  formType.value = type
  resetForm()
  // 修改时，设置数据，并标记为编辑模式
  if (id) {
    isEditMode.value = true
    formLoading.value = true
    try {
      formData.value = await GridCodeAnnotateApi.getGridCodeAnnotate(id)
    } finally {
      formLoading.value = false
    }
  } else {
    isEditMode.value = false // 新增模式
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
    const data = formData.value as unknown as GridCodeAnnotateVO
    if (formType.value === 'create') {
      await GridCodeAnnotateApi.createGridCodeAnnotate(data)
      message.success(t('common.createSuccess'))
    } else {
      await GridCodeAnnotateApi.updateGridCodeAnnotate(data)
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
    annotateId: undefined,
    scale: undefined,
    gridType: undefined,
    fontHeight: undefined,
    fontType: undefined,
    colorC: undefined,
    colorM: undefined,
    colorY: undefined,
    colorK: undefined,
    createUserId: undefined,
    remark: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined,
  }
  formRef.value?.resetFields()
}
</script>
