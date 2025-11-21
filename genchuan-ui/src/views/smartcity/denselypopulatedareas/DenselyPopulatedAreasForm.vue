<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="所名称" prop="theName">
        <el-input v-model="formData.theName" placeholder="请输入所名称" />
      </el-form-item>
      <el-form-item label="场所类型" prop="typeOfVenue">
        <el-input v-model="formData.typeOfVenue" placeholder="请输入场所类型" />
      </el-form-item>
      <el-form-item label="地址" prop="address">
        <el-input v-model="formData.address" placeholder="请输入地址" />
      </el-form-item>
      <el-form-item label="实时人数" prop="realTimeNumberOfPeople">
        <el-input v-model="formData.realTimeNumberOfPeople" placeholder="请输入实时人数" />
      </el-form-item>
      <el-form-item label="人员密度" prop="occupantDensity">
        <el-input v-model="formData.occupantDensity" placeholder="请输入人员密度" />
      </el-form-item>
      <el-form-item label="人员流动速度" prop="personnelTurnoverSpeed">
        <el-input v-model="formData.personnelTurnoverSpeed" placeholder="请输入人员流动速度" />
      </el-form-item>
      <el-form-item label="人员分布区域" prop="personnelDistributionArea">
        <el-input v-model="formData.personnelDistributionArea" placeholder="请输入人员分布区域" />
      </el-form-item>
      <el-form-item label="预警级别" prop="warningLevel">
        <el-input v-model="formData.warningLevel" placeholder="请输入预警级别" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { DenselyPopulatedAreasApi, DenselyPopulatedAreasVO } from '@/api/smartcity/denselypopulatedareas'

/** 人员密集场所安全 表单 */
defineOptions({ name: 'DenselyPopulatedAreasForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  theName: undefined,
  typeOfVenue: undefined,
  address: undefined,
  realTimeNumberOfPeople: undefined,
  occupantDensity: undefined,
  personnelTurnoverSpeed: undefined,
  personnelDistributionArea: undefined,
  warningLevel: undefined
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
      formData.value = await DenselyPopulatedAreasApi.getDenselyPopulatedAreas(id)
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
    const data = formData.value as unknown as DenselyPopulatedAreasVO
    if (formType.value === 'create') {
      await DenselyPopulatedAreasApi.createDenselyPopulatedAreas(data)
      message.success(t('common.createSuccess'))
    } else {
      await DenselyPopulatedAreasApi.updateDenselyPopulatedAreas(data)
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
    theName: undefined,
    typeOfVenue: undefined,
    address: undefined,
    realTimeNumberOfPeople: undefined,
    occupantDensity: undefined,
    personnelTurnoverSpeed: undefined,
    personnelDistributionArea: undefined,
    warningLevel: undefined
  }
  formRef.value?.resetFields()
}
</script>