<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="650px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="150px"
      v-loading="formLoading"
      class="form-container"
    >
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="分域ID" prop="domId">
            <el-input v-model="formData.domId" placeholder="请输入分域ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分域名称" prop="domName">
            <el-input v-model="formData.domName" placeholder="请输入分域名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分域编码" prop="domCode">
            <el-input v-model="formData.domCode" placeholder="请输入分域编码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="网格类型" prop="gridType">
            <el-select v-model="formData.gridType" placeholder="请选择网格类型">
              <el-option label="单元网格" value="单元网格" />
              <el-option label="管理网格" value="管理网格" />
              <el-option label="评价网格" value="评价网格" />
              <el-option label="拓展网格" value="拓展网格" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="网格ID" prop="gridId">
            <el-input v-model="formData.gridId" placeholder="请输入网格ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="网格编码" prop="gridCode">
            <el-input v-model="formData.gridCode" placeholder="请输入网格编码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="网格名称" prop="gridName">
            <el-input v-model="formData.gridName" placeholder="请输入网格名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属行政区域代码" prop="adminCode">
            <el-input v-model="formData.adminCode" placeholder="请输入所属行政区域代码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属行政区域名称" prop="adminName">
            <el-input v-model="formData.adminName" placeholder="请输入所属行政区域名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联点位数量(个)" prop="relPtCount">
            <el-input v-model.number="formData.relPtCount" placeholder="请输入关联点位数量(个)" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联设备数量(个)" prop="relDeviceCount">
            <el-input
              v-model.number="formData.relDeviceCount"
              placeholder="请输入关联设备数量(个)"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="网格员ID" prop="gridUserId">
            <el-input v-model.number="formData.gridUserId" placeholder="请输入网格员ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="网格员姓名" prop="gridUserName">
            <el-input v-model="formData.gridUserName" placeholder="请输入网格员姓名" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分域状态" prop="domStatus">
            <el-radio-group v-model="formData.domStatus">
              <el-radio :label="true">启用</el-radio>
              <el-radio :label="false">禁用</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="创建时间" prop="createTimeSys">
            <el-date-picker
              v-model="formData.createTimeSys"
              type="datetime"
              value-format="x"
              placeholder="选择创建时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="更新时间" prop="updateTimeSys">
            <el-date-picker
              v-model="formData.updateTimeSys"
              type="datetime"
              value-format="x"
              placeholder="选择更新时间"
            />
          </el-form-item>
        </el-col>
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
  DomDivGridApi,
  DomDivGridVO
} from '@/api/dataHub/operationMonitor/domainMonitorManage/domdivgrid'

/** 网格分域 表单 */
defineOptions({ name: 'DomDivGridForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<DomDivGridVO>>({
  id: undefined,
  domId: undefined,
  domName: undefined,
  domCode: undefined,
  gridType: undefined,
  gridId: undefined,
  gridCode: undefined,
  gridName: undefined,
  adminCode: undefined,
  adminName: undefined,
  relPtCount: undefined,
  relDeviceCount: undefined,
  gridUserId: undefined,
  gridUserName: undefined,
  domStatus: true,
  createTimeSys: undefined,
  updateTimeSys: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  domName: [{ required: true, message: '分域名称不能为空', trigger: 'blur' }],
  domCode: [{ required: true, message: '分域编码不能为空', trigger: 'blur' }],
  gridType: [{ required: true, message: '网格类型不能为空', trigger: 'change' }],
  gridId: [{ required: true, message: '网格ID不能为空', trigger: 'blur' }],
  gridCode: [{ required: true, message: '网格编码不能为空', trigger: 'blur' }],
  gridName: [{ required: true, message: '网格名称不能为空', trigger: 'blur' }],
  adminCode: [{ required: true, message: '所属行政区域代码不能为空', trigger: 'blur' }],
  adminName: [{ required: true, message: '所属行政区域名称不能为空', trigger: 'blur' }],
  gridUserId: [{ required: true, message: '网格员ID不能为空', trigger: 'blur' }],
  gridUserName: [{ required: true, message: '网格员姓名不能为空', trigger: 'blur' }],
  domStatus: [{ required: true, message: '分域状态不能为空', trigger: 'change' }],
  createTimeSys: [{ required: true, message: '创建时间不能为空', trigger: 'change' }]
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
      const data = await DomDivGridApi.getDomDivGrid(id)
      formData.value = {
        ...data,
        createTimeSys: data.createTimeSys ? new Date(data.createTimeSys).getTime() : undefined,
        updateTimeSys: data.updateTimeSys ? new Date(data.updateTimeSys).getTime() : undefined
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
    const data = {
      ...formData.value,
      createTimeSys: formData.value.createTimeSys
        ? new Date(formData.value.createTimeSys as number).toISOString()
        : undefined,
      updateTimeSys: formData.value.updateTimeSys
        ? new Date(formData.value.updateTimeSys as number).toISOString()
        : undefined
    } as DomDivGridVO

    if (formType.value === 'create') {
      await DomDivGridApi.createDomDivGrid(data)
      message.success(t('common.createSuccess'))
    } else {
      await DomDivGridApi.updateDomDivGrid(data)
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
    domId: undefined,
    domName: undefined,
    domCode: undefined,
    gridType: undefined,
    gridId: undefined,
    gridCode: undefined,
    gridName: undefined,
    adminCode: undefined,
    adminName: undefined,
    relPtCount: undefined,
    relDeviceCount: undefined,
    gridUserId: undefined,
    gridUserName: undefined,
    domStatus: true,
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
  padding: 10px 0;
}

::v-deep .el-form-item {
  margin-bottom: 15px;
}

::v-deep .el-dialog__body {
  padding: 20px;
  max-height: 70vh;
  overflow-y: auto;
}
</style>
