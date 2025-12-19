<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="140px"
      v-loading="formLoading"
    >
      <el-form-item label="地块名称" prop="plotName">
        <el-input v-model="formData.plotName" placeholder="请输入地块名称" />
      </el-form-item>
      <el-form-item label="地块描述" prop="plotDescription">
        <el-input v-model="formData.plotDescription" type="textarea" placeholder="请输入地块描述" />
      </el-form-item>
      <el-form-item label="归属信息" prop="ownership">
        <el-input v-model="formData.ownership" placeholder="请输入归属信息" />
      </el-form-item>
      <el-form-item label="地块面积" prop="acreage">
        <el-input v-model="formData.acreage" placeholder="请输入地块面积" />
      </el-form-item>
      <!--<el-form-item label="纬度" prop="latitude">-->
      <!--  <el-input v-model="formData.latitude" placeholder="请输入纬度" />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="经度" prop="longitude">-->
      <!--  <el-input v-model="formData.longitude" placeholder="请输入经度" />-->
      <!--</el-form-item>-->
      <el-form-item label="绿化类型" prop="greeningType">
        <el-select v-model="formData.greeningType" placeholder="请选择绿化类型" clearable style="width: 100%">
          <el-option label="纯乔木绿化" value="pureArbor" />
          <el-option label="纯灌木绿化" value="pureShrub" />
          <el-option label="纯地被/草坪绿化" value="pureGroundcover" />
          <el-option label="乔灌混合绿化" value="arborShrubMixed" />
          <el-option label="乔灌草混合绿化" value="arborShrubGrassMixed" />
          <el-option label="水生绿化" value="aquatic" />
        </el-select>
      </el-form-item>
      <el-form-item label="上次养护日期" prop="lastMaintenanceDate">
        <el-date-picker
          v-model="formData.lastMaintenanceDate"
          type="date"
          value-format="x"
          placeholder="选择上次养护日期"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="下次养护计划日期" prop="nextMaintenanceDate">
        <el-date-picker
          v-model="formData.nextMaintenanceDate"
          type="date"
          value-format="x"
          placeholder="选择下次养护计划日期"
          class="common-Width100"
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
import { MaintenancePlotApi, MaintenancePlotVO } from '@/api/smartcity/maintenanceplot'

/** 养护地块 表单 */
defineOptions({ name: 'MaintenancePlotForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  plotName: undefined,
  plotDescription: undefined,
  ownership: undefined,
  acreage: undefined,
  latitude: undefined,
  longitude: undefined,
  greeningType: undefined,
  lastMaintenanceDate: undefined,
  nextMaintenanceDate: undefined
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
      formData.value = await MaintenancePlotApi.getMaintenancePlot(id)
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
    const data = formData.value as unknown as MaintenancePlotVO
    if (formType.value === 'create') {
      await MaintenancePlotApi.createMaintenancePlot(data)
      message.success(t('common.createSuccess'))
    } else {
      await MaintenancePlotApi.updateMaintenancePlot(data)
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
    plotName: undefined,
    plotDescription: undefined,
    ownership: undefined,
    acreage: undefined,
    latitude: undefined,
    longitude: undefined,
    greeningType: undefined,
    lastMaintenanceDate: undefined,
    nextMaintenanceDate: undefined
  }
  formRef.value?.resetFields()
}
</script>
