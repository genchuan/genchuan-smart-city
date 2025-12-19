<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="720px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
    >
      <el-form-item label="图层ID" prop="layerId">
        <el-input v-model="formData.layerId" placeholder="请输入图层ID" />
      </el-form-item>

      <el-form-item label="图层名称" prop="layerName">
        <el-input v-model="formData.layerName" placeholder="请输入图层名称" />
      </el-form-item>

      <el-form-item label="网格类型" prop="gridType">
        <el-select
          v-model="formData.gridType"
          placeholder="请选择网格类型"
          @change="onGridOrScaleChange"
        >
          <el-option v-for="g in gridTypeOptions" :key="g" :label="g" :value="g" />
        </el-select>
      </el-form-item>

      <el-form-item label="比例尺" prop="scale">
        <el-select
          v-model="formData.scale"
          placeholder="请选择比例尺"
          @change="onGridOrScaleChange"
        >
          <el-option v-for="s in scaleOptions" :key="s" :label="s" :value="s" />
        </el-select>
      </el-form-item>

      <el-form-item label="边界线样式" prop="boundaryStyleId">
        <el-select v-model="formData.boundaryStyleId" placeholder="请选择边界线样式">
          <el-option v-for="s in boundaryStyles" :key="s.id" :label="s.name" :value="s.id" />
        </el-select>
      </el-form-item>

      <el-form-item label="注记样式" prop="annotateStyleId">
        <el-select v-model="formData.annotateStyleId" placeholder="请选择注记样式">
          <el-option v-for="s in annotateStyles" :key="s.id" :label="s.name" :value="s.id" />
        </el-select>
      </el-form-item>

      <el-form-item label="显示状态" prop="displayStatus">
        <el-switch v-model="displaySwitch" active-text="显示" inactive-text="隐藏" />
      </el-form-item>

      <el-form-item label="图层顺序" prop="layerWo">
        <div class="flex items-center">
          <el-input-number v-model="formData.layerWo" :min="1" :max="100" />
          <div class="ml-8px text-sm text-gray-500">（1 - 100，数值越小越先渲染）</div>
        </div>
      </el-form-item>

      <el-form-item label="通用扩展字段1" prop="extCommon1">
        <el-input v-model="formData.extCommon1" placeholder="请输入通用扩展字段1" />
      </el-form-item>

      <el-form-item label="创建人" prop="createUserId">
        <el-input v-model="formData.createUserId" placeholder="请输入创建人" />
      </el-form-item>

      <el-form-item label="备注" prop="remark">
        <el-input v-model="formData.remark" placeholder="请输入备注" />
      </el-form-item>
    </el-form>

    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
import { ref, reactive, watch } from 'vue'
import {
  GridTopicLayerApi,
  GridTopicLayerVO
} from '@/api/dataHub/gridManagement/gridDiagramManage/gridtopiclayer'

defineOptions({ name: 'GridTopicLayerForm' })
const message = useMessage()

const dialogVisible = ref(false)
const dialogTitle = ref('')
const formLoading = ref(false)
const formType = ref('')

const gridTypeOptions = ['单元', '管理', '评价']
const scaleOptions = ['1:1000', '1:5000', '1:10000']
const boundaryStyles = ref<{ id: string; name: string }[]>([])
const annotateStyles = ref<{ id: string; name: string }[]>([])

const formRef = ref()
const displaySwitch = ref(true)
const formData = reactive<Partial<GridTopicLayerVO>>({
  displayStatus: '1',
  layerWo: 10
})

watch(displaySwitch, (v) => (formData.displayStatus = v ? '1' : '0'))

const formRules = reactive({
  layerName: [{ required: true, message: '图层名称不能为空', trigger: 'blur' }],
  gridType: [{ required: true, message: '网格类型不能为空', trigger: 'change' }],
  scale: [{ required: true, message: '比例尺不能为空', trigger: 'change' }]
})

/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = type === 'create' ? '新增网格专题图层' : '编辑网格专题图层'
  formType.value = type
  resetForm()

  if (id) {
    formLoading.value = true
    try {
      const data = await GridTopicLayerApi.getGridTopicLayer(id)
      Object.assign(formData, data)
      displaySwitch.value = formData.displayStatus === '1'
      await loadStyles(formData.gridType, formData.scale)
    } finally {
      formLoading.value = false
    }
  } else {
    await loadStyles()
  }
}
defineExpose({ open })

/** 网格类型或比例尺变化时触发 */
const onGridOrScaleChange = async () => {
  await loadStyles(formData.gridType, formData.scale)
}

/** 加载样式 */
const loadStyles = async (gridType?: string, scale?: string) => {
  boundaryStyles.value = []
  annotateStyles.value = []
  await new Promise((r) => setTimeout(r, 150))
  // 模拟样式加载逻辑，可按项目实际接口替换
  if (gridType === '单元') {
    boundaryStyles.value = [{ id: 'b-unit-1', name: '单元红边' }]
  } else if (gridType === '管理') {
    boundaryStyles.value = [{ id: 'b-mng-1', name: '管理蓝边' }]
  } else if (gridType === '评价') {
    boundaryStyles.value = [{ id: 'b-eval-1', name: `评价边界 (${scale || '默认'})` }]
  } else {
    boundaryStyles.value = [{ id: 'b-default-1', name: '默认边界' }]
  }
  annotateStyles.value = [{ id: 'a-default-1', name: '默认注记' }]
}

const emit = defineEmits(['success'])
const submitForm = async () => {
  await formRef.value.validate()
  formLoading.value = true
  try {
    const payload = formData as GridTopicLayerVO
    if (formType.value === 'create') {
      await GridTopicLayerApi.createGridTopicLayer(payload)
      message.success('创建成功')
    } else {
      await GridTopicLayerApi.updateGridTopicLayer(payload)
      message.success('修改成功')
    }
    dialogVisible.value = false
    emit('success')
  } finally {
    formLoading.value = false
  }
}

const resetForm = () => {
  formRef.value?.resetFields()
  Object.assign(formData, {
    id: undefined,
    layerId: undefined,
    layerName: undefined,
    gridType: undefined,
    scale: undefined,
    boundaryStyleId: undefined,
    annotateStyleId: undefined,
    displayStatus: '1',
    layerWo: 10,
    createUserId: undefined,
    remark: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined
  })
  displaySwitch.value = true
}
</script>
