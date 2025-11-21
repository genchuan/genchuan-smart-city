<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="部件编号" prop="partNumber">
        <el-input v-model="formData.partNumber" placeholder="请输入部件编号" />
      </el-form-item>
      <el-form-item label="部件名称" prop="componentName">
        <el-input v-model="formData.componentName" placeholder="请输入部件名称" />
      </el-form-item>
      <el-form-item label="部件类型" prop="partType">
        <el-select v-model="formData.partType" placeholder="请选择部件类型">
          <el-option
            v-for="dict in getStrDictOptions(DICT_TYPE.SM_PART_TYPE)"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="所属区域" prop="belongingRegion">
        <el-input v-model="formData.belongingRegion" placeholder="请输入所属区域" />
      </el-form-item>
      <el-form-item label="安装位置" prop="installationPosition">
        <el-input v-model="formData.installationPosition" placeholder="请输入安装位置" />
      </el-form-item>
      <el-form-item label="经纬度坐标" prop="latitudeLongitude">
        <el-input v-model="formData.latitudeLongitude" placeholder="请输入经纬度坐标" />
      </el-form-item>
      <el-form-item label="建设日期" prop="constructionDate">
        <el-date-picker
          v-model="formData.constructionDate"
          type="date"
          value-format="x"
          placeholder="选择建设日期"
        />
      </el-form-item>
      <el-form-item label="管理部门" prop="administrativeDepartment">
        <el-input v-model="formData.administrativeDepartment" placeholder="请输入管理部门" />
      </el-form-item>
      <el-form-item label="维护单位" prop="maintenanceUnit">
        <el-input v-model="formData.maintenanceUnit" placeholder="请输入维护单位" />
      </el-form-item>
      <el-form-item label="联系电话" prop="contactNumber">
        <el-input v-model="formData.contactNumber" placeholder="请输入联系电话" />
      </el-form-item>
      <el-form-item label="部件状态" prop="componentStatus">
        <el-select v-model="formData.componentStatus" placeholder="请选择部件状态">
          <el-option
            v-for="dict in getStrDictOptions(DICT_TYPE.SM_STATE)"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="设备图片" prop="specificationAndModel">
        <UploadImg v-model="formData.specificationAndModel" />
      </el-form-item> -->
      <el-form-item label="使用寿命" prop="serviceLife">
        <el-input v-model="formData.serviceLife" placeholder="请输入使用寿命" />
      </el-form-item>
      <el-form-item label="关联事件记录" prop="relatedEventRecords">
        <el-input v-model="formData.relatedEventRecords" placeholder="请输入关联事件记录" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { getStrDictOptions, DICT_TYPE } from '@/utils/dict'
import { ComponentInformationApi, ComponentInformationVO } from '@/api/smartcity/componentinformation'

/** 部件信息 表单 */
defineOptions({ name: 'ComponentInformationForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  partNumber: undefined,
  componentName: undefined,
  partType: undefined,
  belongingRegion: undefined,
  installationPosition: undefined,
  latitudeLongitude: undefined,
  constructionDate: undefined,
  administrativeDepartment: undefined,
  maintenanceUnit: undefined,
  contactNumber: undefined,
  componentStatus: undefined,
  specificationAndModel: undefined,
  serviceLife: undefined,
  relatedEventRecords: undefined
})
const formRules = reactive({
})
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
      const res = await ComponentInformationApi.getComponentInformation(id)
      formData.value = {
        ...res,
        // 将字符串时间戳转为数字
        constructionDate: res.constructionDate ? Number(res.constructionDate) : undefined
      }
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
    const data = formData.value as unknown as ComponentInformationVO
    if (formType.value === 'create') {
      await ComponentInformationApi.createComponentInformation(data)
      message.success(t('common.createSuccess'))
    } else {
      await ComponentInformationApi.updateComponentInformation(data)
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
    partNumber: undefined,
    componentName: undefined,
    partType: undefined,
    belongingRegion: undefined,
    installationPosition: undefined,
    latitudeLongitude: undefined,
    constructionDate: undefined,
    administrativeDepartment: undefined,
    maintenanceUnit: undefined,
    contactNumber: undefined,
    componentStatus: undefined,
    specificationAndModel: undefined,
    serviceLife: undefined,
    relatedEventRecords: undefined
  }
  formRef.value?.resetFields()
}
</script>
