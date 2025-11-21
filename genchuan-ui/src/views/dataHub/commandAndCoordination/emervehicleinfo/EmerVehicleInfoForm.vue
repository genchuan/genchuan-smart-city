<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="车辆ID" prop="vehicleId">
        <el-input v-model="formData.vehicleId" placeholder="请输入车辆ID" />
      </el-form-item>
      <el-form-item label="车辆编码" prop="vehicleCode">
        <el-input v-model="formData.vehicleCode" placeholder="请输入车辆编码" />
      </el-form-item>
      <el-form-item label="车辆名称" prop="vehicleName">
        <el-input v-model="formData.vehicleName" placeholder="请输入车辆名称" />
      </el-form-item>
      <el-form-item label="分类ID" prop="catId">
        <el-input v-model="formData.catId" placeholder="请输入分类ID" />
      </el-form-item>
      <el-form-item label="分类名称" prop="catName">
        <el-input v-model="formData.catName" placeholder="请输入分类名称" />
      </el-form-item>
      <el-form-item label="适用区域代码" prop="applyRegionCode">
        <el-input v-model="formData.applyRegionCode" placeholder="请输入适用区域代码" />
      </el-form-item>
      <el-form-item label="适用区域名称" prop="applyRegionName">
        <el-input v-model="formData.applyRegionName" placeholder="请输入适用区域名称" />
      </el-form-item>
      <el-form-item label="车辆型号" prop="vehicleModel">
        <el-input v-model="formData.vehicleModel" placeholder="请输入车辆型号" />
      </el-form-item>
      <el-form-item label="车辆数量" prop="vehicleCount">
        <el-input v-model="formData.vehicleCount" placeholder="请输入车辆数量" />
      </el-form-item>
      <el-form-item label="可用数量" prop="availableVehicleCount">
        <el-input v-model="formData.availableVehicleCount" placeholder="请输入可用数量" />
      </el-form-item>
      <el-form-item label="车辆状态" prop="vehicleStatus">
        <el-radio-group v-model="formData.vehicleStatus">
          <el-radio value="1">正常</el-radio>
          <el-radio value="0">异常</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="创建人" prop="createUser">
        <el-input v-model="formData.createUser" placeholder="请输入创建人" />
      </el-form-item>
      <el-form-item label="更新人" prop="updateUser">
        <el-input v-model="formData.updateUser" placeholder="请输入更新人" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { EmerVehicleInfoApi, EmerVehicleInfoVO } from '@/api/dataHub/commandAndCoordination/emervehicleinfo'

/** 救援车辆信息 表单 */
defineOptions({ name: 'EmerVehicleInfoForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  vehicleId: undefined,
  vehicleCode: undefined,
  vehicleName: undefined,
  catId: undefined,
  catName: undefined,
  applyRegionCode: undefined,
  applyRegionName: undefined,
  vehicleModel: undefined,
  vehicleCount: undefined,
  availableVehicleCount: undefined,
  vehicleStatus: undefined,
  createUser: undefined,
  updateUser: undefined,
})
const formRules = reactive({
  vehicleId: [{ required: true, message: '车辆ID不能为空', trigger: 'blur' }],
  vehicleCode: [{ required: true, message: '车辆编码不能为空', trigger: 'blur' }],
  vehicleName: [{ required: true, message: '车辆名称不能为空', trigger: 'blur' }],
  catId: [{ required: true, message: '分类ID不能为空', trigger: 'blur' }],
  catName: [{ required: true, message: '分类名称不能为空', trigger: 'blur' }],
  applyRegionCode: [{ required: true, message: '适用区域代码不能为空', trigger: 'blur' }],
  applyRegionName: [{ required: true, message: '适用区域名称不能为空', trigger: 'blur' }],
  vehicleCount: [{ required: true, message: '车辆数量不能为空', trigger: 'blur' }],
  availableVehicleCount: [{ required: true, message: '可用数量不能为空', trigger: 'blur' }],
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
      formData.value = await EmerVehicleInfoApi.getEmerVehicleInfo(id)
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
    const data = formData.value as unknown as EmerVehicleInfoVO
    if (formType.value === 'create') {
      await EmerVehicleInfoApi.createEmerVehicleInfo(data)
      message.success(t('common.createSuccess'))
    } else {
      await EmerVehicleInfoApi.updateEmerVehicleInfo(data)
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
    vehicleId: undefined,
    vehicleCode: undefined,
    vehicleName: undefined,
    catId: undefined,
    catName: undefined,
    applyRegionCode: undefined,
    applyRegionName: undefined,
    vehicleModel: undefined,
    vehicleCount: undefined,
    availableVehicleCount: undefined,
    vehicleStatus: undefined,
    createUser: undefined,
    updateUser: undefined,
  }
  formRef.value?.resetFields()
}
</script>
