<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="绿地名称" prop="greenSpaceName">
        <el-input v-model="formData.greenSpaceName" placeholder="请输入绿地名称" />
      </el-form-item>
      <el-form-item label="绿地类型" prop="greenbeltType">
        <el-select v-model="formData.greenbeltType" placeholder="请选择绿地类型">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="位置" prop="position">
        <el-input v-model="formData.position" placeholder="请输入位置" />
      </el-form-item>
      <el-form-item label="面积" prop="area">
        <el-input v-model="formData.area" placeholder="请输入面积" />
      </el-form-item>
      <el-form-item label="绿地率" prop="greeningRate">
        <el-input v-model="formData.greeningRate" placeholder="请输入绿地率" />
      </el-form-item>
      <el-form-item label="绿化覆盖率" prop="greenCoverageRate">
        <el-input v-model="formData.greenCoverageRate" placeholder="请输入绿化覆盖率" />
      </el-form-item>
      <el-form-item label="植物名称" prop="plantName">
        <el-input v-model="formData.plantName" placeholder="请输入植物名称" />
      </el-form-item>
      <el-form-item label="科属" prop="familyAndGenus">
        <el-input v-model="formData.familyAndGenus" placeholder="请输入科属" />
      </el-form-item>
      <el-form-item label="数量" prop="quantity">
        <el-input v-model="formData.quantity" placeholder="请输入数量" />
      </el-form-item>
      <el-form-item label="种植密度" prop="plantingDensity">
        <el-input v-model="formData.plantingDensity" placeholder="请输入种植密度" />
      </el-form-item>
      <el-form-item label="项目总投资" prop="totalInvestmentOfTheProject">
        <el-input v-model="formData.totalInvestmentOfTheProject" placeholder="请输入项目总投资" />
      </el-form-item>
      <el-form-item label="土地成本" prop="landCost">
        <el-input v-model="formData.landCost" placeholder="请输入土地成本" />
      </el-form-item>
      <el-form-item label="苗木采购成本" prop="seedlingProcurementCost">
        <el-input v-model="formData.seedlingProcurementCost" placeholder="请输入苗木采购成本" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { LandscapingApi, LandscapingVO } from '@/api/smartcity/landscaping'

/** 园林绿化 表单 */
defineOptions({ name: 'LandscapingForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  greenSpaceName: undefined,
  greenbeltType: undefined,
  position: undefined,
  area: undefined,
  greeningRate: undefined,
  greenCoverageRate: undefined,
  plantName: undefined,
  familyAndGenus: undefined,
  quantity: undefined,
  plantingDensity: undefined,
  totalInvestmentOfTheProject: undefined,
  landCost: undefined,
  seedlingProcurementCost: undefined
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
      formData.value = await LandscapingApi.getLandscaping(id)
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
    const data = formData.value as unknown as LandscapingVO
    if (formType.value === 'create') {
      await LandscapingApi.createLandscaping(data)
      message.success(t('common.createSuccess'))
    } else {
      await LandscapingApi.updateLandscaping(data)
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
    greenSpaceName: undefined,
    greenbeltType: undefined,
    position: undefined,
    area: undefined,
    greeningRate: undefined,
    greenCoverageRate: undefined,
    plantName: undefined,
    familyAndGenus: undefined,
    quantity: undefined,
    plantingDensity: undefined,
    totalInvestmentOfTheProject: undefined,
    landCost: undefined,
    seedlingProcurementCost: undefined
  }
  formRef.value?.resetFields()
}
</script>