<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
      class="bg-white rounded-lg p-4"
    >
      <el-form-item label="注记ID" prop="annotateId">
        <el-input
          v-model="formData.annotateId"
          placeholder="请输入注记ID"
          :disabled="isEditMode"
          class="!mb-2"
        />
      </el-form-item>
      <el-form-item label="比例尺" prop="scale">
        <el-select
          v-model="formData.scale"
          placeholder="请选择比例尺"
          @change="handleScaleChange"
          class="!mb-2"
        >
          <el-option label="1:500" value="1:500" />
          <el-option label="1:1000" value="1:1000" />
          <el-option label="1:2000" value="1:2000" />
        </el-select>
      </el-form-item>
      <el-form-item label="网格类型" prop="gridType">
        <el-select v-model="formData.gridType" placeholder="请选择网格类型" class="!mb-2">
          <el-option label="单元网格" value="UNIT_GRID" />
          <el-option label="管理网格" value="MANAGE_GRID" />
          <el-option label="评价网格" value="EVAL_GRID" />
        </el-select>
      </el-form-item>
      <el-form-item label="字高(mm)" prop="fontHeight">
        <el-input
          v-model="formData.fontHeight"
          placeholder="请输入字高(mm)"
          type="number"
          min="0"
          class="!mb-2"
        />
        <div class="text-gray-500 text-sm">
          参考值：1:500对应5mm，1:1000对应4mm，1:2000对应3mm
        </div>
      </el-form-item>
      <el-form-item label="字型" prop="fontType">
        <el-input v-model="formData.fontType" placeholder="请输入字型" disabled class="!mb-2" />
      </el-form-item>
      <!-- 颜色字段固定为品红色，禁用且默认赋值 -->
      <el-form-item label="颜色C值(%)" prop="colorC">
        <el-input v-model="formData.colorC" disabled class="!mb-2" />
      </el-form-item>
      <el-form-item label="颜色M值(%)" prop="colorM">
        <el-input v-model="formData.colorM" disabled class="!mb-2" />
      </el-form-item>
      <el-form-item label="颜色Y值(%)" prop="colorY">
        <el-input v-model="formData.colorY" disabled class="!mb-2" />
      </el-form-item>
      <el-form-item label="颜色K值(%)" prop="colorK">
        <el-input v-model="formData.colorK" disabled class="!mb-2" />
      </el-form-item>
      <el-form-item label="创建人" prop="createUserId">
        <el-input v-model="formData.createUserId" placeholder="请输入创建人" class="!mb-2" />
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input v-model="formData.remark" placeholder="请输入备注" type="textarea" rows="3" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
import {
  GridCodeAnnotateApi,
  GridCodeAnnotateVO
} from '@/api/dataHub/gridManagement/gridDiagramManage/gridcodeannotate'

defineOptions({ name: 'GridCodeAnnotateForm' })

const { t } = useI18n()
const message = useMessage()
const dialogVisible = ref(false)
const dialogTitle = ref('')
const formLoading = ref(false)
const formType = ref('')
const isEditMode = ref(false)

// 比例尺对应默认字高
const scale2FontHeight = {
  '1:500': 5,
  '1:1000': 4,
  '1:2000': 3
}

const formData = ref<GridCodeAnnotateVO>({
  id: undefined,
  annotateId: undefined,
  scale: undefined,
  gridType: undefined,
  fontHeight: undefined,
  fontType: '正等线体', // 固定字型
  colorC: 0, // 品红色C值
  colorM: 100, // 品红色M值
  colorY: 0, // 品红色Y值
  colorK: 0, // 品红色K值
  createUserId: undefined,
  remark: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
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
  createUserId: [{ required: true, message: '创建人不能为空', trigger: 'blur' }]
})

const formRef = ref()

// 比例尺变更联动字高默认值
const handleScaleChange = (val: string) => {
  if (!isEditMode.value) {
    formData.value.fontHeight = scale2FontHeight[val]
  }
}

// 打开弹窗
const open = async (type: string, id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = t('action.' + type)
  formType.value = type
  resetForm()

  if (id) {
    isEditMode.value = true
    formLoading.value = true
    try {
      const res = await GridCodeAnnotateApi.getGridCodeAnnotate(id)
      formData.value = {
        ...res,
        fontType: res.fontType || '正等线体', // 兜底字型
        colorC: res.colorC || 0,
        colorM: res.colorM || 100,
        colorY: res.colorY || 0,
        colorK: res.colorK || 0
      }
    } finally {
      formLoading.value = false
    }
  } else {
    isEditMode.value = false
    // 初始化颜色和字型
    formData.value.fontType = '正等线体'
    formData.value.colorC = 0
    formData.value.colorM = 100
    formData.value.colorY = 0
    formData.value.colorK = 0
  }
}
defineExpose({ open })

// 提交表单
const emit = defineEmits(['success'])
const submitForm = async () => {
  await formRef.value.validate()
  formLoading.value = true
  try {
    const data = formData.value as GridCodeAnnotateVO
    if (formType.value === 'create') {
      await GridCodeAnnotateApi.createGridCodeAnnotate(data)
      message.success(t('common.createSuccess'))
    } else {
      await GridCodeAnnotateApi.updateGridCodeAnnotate(data)
      message.success(t('common.updateSuccess'))
    }
    dialogVisible.value = false
    emit('success')
  } finally {
    formLoading.value = false
  }
}

// 重置表单
const resetForm = () => {
  formData.value = {
    id: undefined,
    annotateId: undefined,
    scale: undefined,
    gridType: undefined,
    fontHeight: undefined,
    fontType: '正等线体',
    colorC: 0,
    colorM: 100,
    colorY: 0,
    colorK: 0,
    createUserId: undefined,
    remark: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined
  }
  formRef.value?.resetFields()
}
</script>

<style scoped>
.el-form {
  background: #fdfdfd;
  border-radius: 8px;
}
</style>
