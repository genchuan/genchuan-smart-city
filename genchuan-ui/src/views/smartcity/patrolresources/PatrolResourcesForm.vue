<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="资源名称" prop="resourceName">
        <el-input v-model="formData.resourceName" placeholder="请输入资源名称" />
      </el-form-item>
      <el-form-item label="资源编号" prop="resourceNumber">
        <el-input v-model="formData.resourceNumber" placeholder="请输入资源编号" />
      </el-form-item>
      <el-form-item label="资源类型" prop="resourceType">
        <el-select v-model="formData.resourceType" placeholder="请选择资源类型">
          <el-option label="建筑设施" value="building_facility" />
          <el-option label="市政设施" value="municipal_facility" />
          <el-option label="安防设施" value="security_facility" />
          <el-option label="消防设施" value="fire_facility" />
          <el-option label="电气设备" value="electrical_equipment" />
          <el-option label="特种设备" value="special_equipment" />
          <el-option label="生产设备" value="production_equipment" />
          <el-option label="环保设备" value="environmental_equipment" />
          <el-option label="电力线路" value="power_line" />
          <el-option label="给排水管线" value="water_pipeline" />
          <el-option label="燃气管线" value="gas_pipeline" />
          <el-option label="通信线路" value="communication_line" />
          <el-option label="巡查区域" value="patrol_area" />
          <el-option label="重点区域" value="key_area" />
          <el-option label="应急物资" value="emergency_materials" />
          <el-option label="工具器材" value="tools_equipment" />
        </el-select>
      </el-form-item>
      <el-form-item label="地理位置" prop="geographicLocation">
        <el-input v-model="formData.geographicLocation" placeholder="请输入地理位置" />
      </el-form-item>
      <el-form-item label="区域划分" prop="regionalDivision">
        <el-input v-model="formData.regionalDivision" placeholder="请输入区域划分" />
      </el-form-item>
      <el-form-item label="规格型号" prop="specificationAndModel">
        <el-input v-model="formData.specificationAndModel" placeholder="请输入规格型号" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { PatrolResourcesApi, PatrolResourcesVO } from '@/api/smartcity/patrolresources'

/** 巡查资源 表单 */
defineOptions({ name: 'PatrolResourcesForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  resourceName: undefined,
  resourceNumber: undefined,
  resourceType: undefined,
  geographicLocation: undefined,
  regionalDivision: undefined,
  specificationAndModel: undefined
})
const formRules = reactive({})
const formRef = ref() // 表单 ref

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
      formData.value = await PatrolResourcesApi.getPatrolResources(id)
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
    const data = formData.value as unknown as PatrolResourcesVO
    if (formType.value === 'create') {
      await PatrolResourcesApi.createPatrolResources(data)
      message.success(t('common.createSuccess'))
    } else {
      await PatrolResourcesApi.updatePatrolResources(data)
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
    resourceName: undefined,
    resourceNumber: undefined,
    resourceType: undefined,
    geographicLocation: undefined,
    regionalDivision: undefined,
    specificationAndModel: undefined
  }
  formRef.value?.resetFields()
}
</script>
