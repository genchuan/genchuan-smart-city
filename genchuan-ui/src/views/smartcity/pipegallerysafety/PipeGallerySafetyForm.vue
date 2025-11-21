<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="管廊名称" prop="pipeGalleryName">
        <el-input v-model="formData.pipeGalleryName" placeholder="请输入管廊名称" />
      </el-form-item>
      <el-form-item label="管廊位置" prop="locationGallery">
        <el-input v-model="formData.locationGallery" placeholder="请输入管廊位置" />
      </el-form-item>
      <el-form-item label="管廊编号" prop="pipeGalleryNumber">
        <el-input v-model="formData.pipeGalleryNumber" placeholder="请输入管廊编号" />
      </el-form-item>
      <el-form-item label="入廊管线类型" prop="typePipeline">
        <el-input v-model="formData.typePipeline" placeholder="请输入入廊管线类型" />
      </el-form-item>
      <el-form-item label="入廊管线数量" prop="numberOfEntrancePipelines">
        <el-input v-model="formData.numberOfEntrancePipelines" placeholder="请输入入廊管线数量" />
      </el-form-item>
      <el-form-item label="管廊结构安全状况" prop="safetyStatusStructure">
        <el-input v-model="formData.safetyStatusStructure" placeholder="请输入管廊结构安全状况" />
      </el-form-item>
      <el-form-item label="管廊渗漏水情况" prop="leakagePipeGallery">
        <el-input v-model="formData.leakagePipeGallery" placeholder="请输入管廊渗漏水情况" />
      </el-form-item>
      <el-form-item label="温湿度" prop="temperatureAndHumidity">
        <el-input v-model="formData.temperatureAndHumidity" placeholder="请输入温湿度" />
      </el-form-item>
      <el-form-item label="有害气体浓度" prop="harmfulGasConcentration">
        <el-input v-model="formData.harmfulGasConcentration" placeholder="请输入有害气体浓度" />
      </el-form-item>
      <el-form-item label="氧气含量" prop="oxygenContent">
        <el-input v-model="formData.oxygenContent" type="textarea" placeholder="请输入氧气含量" />
      </el-form-item>
      <el-form-item label="通风设备运行状态" prop="operationVentilationEquipment">
        <el-input v-model="formData.operationVentilationEquipment" placeholder="请输入通风设备运行状态" />
      </el-form-item>
      <el-form-item label="预警级别" prop="warningLevel">
        <el-input v-model="formData.warningLevel" placeholder="请输入预警级别" />
      </el-form-item>
      <el-form-item label="维修记录" prop="maintenanceRecord">
        <el-input v-model="formData.maintenanceRecord" placeholder="请输入维修记录" />
      </el-form-item>
      <el-form-item label="维修时间" prop="maintenanceTime">
        <el-date-picker
          v-model="formData.maintenanceTime"
          type="date"
          value-format="x"
          placeholder="选择维修时间"
        />
      </el-form-item>
      <el-form-item label="应急物资储备情况" prop="emergencyMaterialSituation">
        <el-input v-model="formData.emergencyMaterialSituation" placeholder="请输入应急物资储备情况" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { PipeGallerySafetyApi, PipeGallerySafetyVO } from '@/api/smartcity/pipegallerysafety'

/** 管廊安全 表单 */
defineOptions({ name: 'PipeGallerySafetyForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  pipeGalleryName: undefined,
  locationGallery: undefined,
  pipeGalleryNumber: undefined,
  typePipeline: undefined,
  numberOfEntrancePipelines: undefined,
  safetyStatusStructure: undefined,
  leakagePipeGallery: undefined,
  temperatureAndHumidity: undefined,
  harmfulGasConcentration: undefined,
  oxygenContent: undefined,
  operationVentilationEquipment: undefined,
  warningLevel: undefined,
  maintenanceRecord: undefined,
  maintenanceTime: undefined,
  emergencyMaterialSituation: undefined
})
const formRules = reactive({
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
      formData.value = await PipeGallerySafetyApi.getPipeGallerySafety(id)
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
    const data = formData.value as unknown as PipeGallerySafetyVO
    if (formType.value === 'create') {
      await PipeGallerySafetyApi.createPipeGallerySafety(data)
      message.success(t('common.createSuccess'))
    } else {
      await PipeGallerySafetyApi.updatePipeGallerySafety(data)
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
    pipeGalleryName: undefined,
    locationGallery: undefined,
    pipeGalleryNumber: undefined,
    typePipeline: undefined,
    numberOfEntrancePipelines: undefined,
    safetyStatusStructure: undefined,
    leakagePipeGallery: undefined,
    temperatureAndHumidity: undefined,
    harmfulGasConcentration: undefined,
    oxygenContent: undefined,
    operationVentilationEquipment: undefined,
    warningLevel: undefined,
    maintenanceRecord: undefined,
    maintenanceTime: undefined,
    emergencyMaterialSituation: undefined
  }
  formRef.value?.resetFields()
}
</script>