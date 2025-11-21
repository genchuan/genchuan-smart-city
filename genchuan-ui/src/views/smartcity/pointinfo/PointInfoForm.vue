<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="点位编号" prop="pointNumber">
        <el-input v-model="formData.pointNumber" placeholder="请输入点位编号" />
      </el-form-item>
      <el-form-item label="点位名称" prop="pointName">
        <el-input v-model="formData.pointName" placeholder="请输入点位名称" />
      </el-form-item>
      <el-form-item label="点位类型" prop="pointType">
        <el-input v-model="formData.pointType" placeholder="请输入点位类型" />
      </el-form-item>
      <el-form-item label="所属区域" prop="belongingArea">
        <el-input v-model="formData.belongingArea" placeholder="请输入所属区域" />
      </el-form-item>
      <el-form-item label="经纬度坐标" prop="latitudeLongitudeCoordinate">
        <el-input v-model="formData.latitudeLongitudeCoordinate" placeholder="请输入经纬度坐标" />
      </el-form-item>
      <el-form-item label="海拔高度" prop="altitude">
        <el-input v-model="formData.altitude" placeholder="请输入海拔高度" />
      </el-form-item>
      <el-form-item label="设备信息" prop="deviceInfo">
        <el-input v-model="formData.deviceInfo" placeholder="请输入设备信息" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { PointInfoApi, PointInfoVO } from '@/api/smartcity/pointinfo'

/** 监测点位 表单 */
defineOptions({ name: 'PointInfoForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  pointNumber: undefined,
  pointName: undefined,
  pointType: undefined,
  belongingArea: undefined,
  latitudeLongitudeCoordinate: undefined,
  altitude: undefined,
  deviceInfo: undefined
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
      formData.value = await PointInfoApi.getPointInfo(id)
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
    const data = formData.value as unknown as PointInfoVO
    if (formType.value === 'create') {
      await PointInfoApi.createPointInfo(data)
      message.success(t('common.createSuccess'))
    } else {
      await PointInfoApi.updatePointInfo(data)
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
    pointNumber: undefined,
    pointName: undefined,
    pointType: undefined,
    belongingArea: undefined,
    latitudeLongitudeCoordinate: undefined,
    altitude: undefined,
    deviceInfo: undefined
  }
  formRef.value?.resetFields()
}
</script>