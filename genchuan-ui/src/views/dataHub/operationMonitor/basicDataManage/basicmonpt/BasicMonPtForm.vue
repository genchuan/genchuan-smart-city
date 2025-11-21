<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="750px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="110px"
      v-loading="formLoading"
      class="form-container"
    >
      <el-row :gutter="25">
        <el-col :span="12">
          <el-form-item label="点位名称" prop="ptName">
            <el-input v-model="formData.ptName" placeholder="请输入点位名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="点位编码" prop="ptCode">
            <el-input v-model="formData.ptCode" placeholder="请输入点位编码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="点位类型" prop="ptType">
            <el-select v-model="formData.ptType" placeholder="请选择点位类型">
              <el-option label="请选择字典生成" value="" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="点位状态" prop="ptStatus">
            <el-radio-group v-model="formData.ptStatus">
              <el-radio :label="true">正常</el-radio>
              <el-radio :label="false">异常</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属区域代码" prop="areaCode">
            <el-input v-model="formData.areaCode" placeholder="请输入所属区域代码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属区域名称" prop="areaName">
            <el-input v-model="formData.areaName" placeholder="请输入所属区域名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联设备编码" prop="deviceCode">
            <el-input v-model="formData.deviceCode" placeholder="请输入关联设备编码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联设备名称" prop="deviceName">
            <el-input v-model="formData.deviceName" placeholder="请输入关联设备名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="点位坐标X(度)" prop="ptCoordX">
            <el-input v-model.number="formData.ptCoordX" placeholder="请输入点位坐标X(度)" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="点位坐标Y(度)" prop="ptCoordY">
            <el-input v-model.number="formData.ptCoordY" placeholder="请输入点位坐标Y(度)" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="负责人ID" prop="mngrId">
            <el-input v-model.number="formData.mngrId" placeholder="请输入负责人ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="负责人姓名" prop="mngrName">
            <el-input v-model="formData.mngrName" placeholder="请输入负责人姓名" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="负责人联系方式" prop="mngrPhone">
            <el-input v-model="formData.mngrPhone" placeholder="请输入负责人联系方式" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-divider content-position="left">扩展信息</el-divider>

      <el-row :gutter="25">
        <el-col :span="12">
          <el-form-item label="分类扩展字段1" prop="extCat1">
            <el-input v-model="formData.extCat1" placeholder="请输入分类扩展字段1" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分类扩展字段2" prop="extCat2">
            <el-input v-model="formData.extCat2" placeholder="请输入分类扩展字段2" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="通用扩展字段1" prop="extCommon1">
            <el-input v-model="formData.extCommon1" placeholder="请输入通用扩展字段1" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="通用扩展字段2" prop="extCommon2">
            <el-input v-model="formData.extCommon2" placeholder="请输入通用扩展字段2" />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  BasicMonPtApi,
  BasicMonPtVO
} from '@/api/dataHub/operationMonitor/basicDataManage/basicmonpt'

/** 基础监测点位 表单 */
defineOptions({ name: 'BasicMonPtForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<BasicMonPtVO>>({
  id: undefined,
  ptId: undefined,
  ptName: undefined,
  ptCode: undefined,
  ptType: undefined,
  areaCode: undefined,
  areaName: undefined,
  deviceCode: undefined,
  deviceName: undefined,
  ptCoordX: undefined,
  ptCoordY: undefined,
  mngrId: undefined,
  mngrName: undefined,
  mngrPhone: undefined,
  ptStatus: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  ptName: [{ required: true, message: '点位名称不能为空', trigger: 'blur' }],
  ptCode: [{ required: true, message: '点位编码不能为空', trigger: 'blur' }],
  ptType: [{ required: true, message: '点位类型不能为空', trigger: 'change' }],
  areaCode: [{ required: true, message: '所属区域代码不能为空', trigger: 'blur' }],
  areaName: [{ required: true, message: '所属区域名称不能为空', trigger: 'blur' }],
  deviceCode: [{ required: true, message: '关联设备编码不能为空', trigger: 'blur' }],
  deviceName: [{ required: true, message: '关联设备名称不能为空', trigger: 'blur' }],
  ptCoordX: [{ required: true, message: '点位坐标X(度)不能为空', trigger: 'blur' }],
  ptCoordY: [{ required: true, message: '点位坐标Y(度)不能为空', trigger: 'blur' }],
  mngrName: [{ required: true, message: '负责人姓名不能为空', trigger: 'blur' }],
  mngrPhone: [{ required: true, message: '负责人联系方式不能为空', trigger: 'blur' }],
  ptStatus: [{ required: true, message: '点位状态不能为空', trigger: 'change' }]
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
      const detail = await BasicMonPtApi.getBasicMonPt(id)
      formData.value = { ...detail }
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
    const data = formData.value as BasicMonPtVO
    if (formType.value === 'create') {
      await BasicMonPtApi.createBasicMonPt(data)
      message.success(t('common.createSuccess'))
    } else {
      await BasicMonPtApi.updateBasicMonPt(data)
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
    ptId: undefined,
    ptName: undefined,
    ptCode: undefined,
    ptType: undefined,
    areaCode: undefined,
    areaName: undefined,
    deviceCode: undefined,
    deviceName: undefined,
    ptCoordX: undefined,
    ptCoordY: undefined,
    mngrId: undefined,
    mngrName: undefined,
    mngrPhone: undefined,
    ptStatus: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined
  }
  formRef.value?.resetFields()
}
</script>

<style scoped>
.form-container {
  padding: 15px 0;
}

::v-deep .el-form-item {
  margin-bottom: 18px;
}

::v-deep .el-form-item__label {
  color: #4b5563;
  font-weight: 500;
}

::v-deep .el-divider {
  margin: 15px 0;
}

::v-deep .el-divider__text {
  color: #64748b;
  font-weight: 500;
  background-color: #fff;
  padding: 0 10px;
}
</style>
