<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="600px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="130px"
      v-loading="formLoading"
      class="form-container"
    >
      <el-form-item label="分域ID" prop="domId">
        <el-input v-model="formData.domId" placeholder="请输入分域ID" />
      </el-form-item>
      <el-form-item label="分域名称" prop="domName">
        <el-input v-model="formData.domName" placeholder="请输入分域名称" />
      </el-form-item>
      <el-form-item label="分域编码" prop="domCode">
        <el-input v-model="formData.domCode" placeholder="请输入分域编码" />
      </el-form-item>
      <el-form-item label="行政区域级别" prop="adminLevel">
        <el-input v-model="formData.adminLevel" placeholder="请输入行政区域级别" />
      </el-form-item>
      <el-form-item label="行政区域代码" prop="adminCode">
        <el-input v-model="formData.adminCode" placeholder="请输入行政区域代码" />
      </el-form-item>
      <el-form-item label="行政区域名称" prop="adminName">
        <el-input v-model="formData.adminName" placeholder="请输入行政区域名称" />
      </el-form-item>
      <el-form-item label="关联点位数量(个)" prop="relPtCount">
        <el-input v-model.number="formData.relPtCount" placeholder="请输入关联点位数量(个)" />
      </el-form-item>
      <el-form-item label="关联设备数量(个)" prop="relDeviceCount">
        <el-input v-model.number="formData.relDeviceCount" placeholder="请输入关联设备数量(个)" />
      </el-form-item>
      <el-form-item label="分域负责人ID" prop="mngrId">
        <el-input v-model="formData.mngrId" placeholder="请输入分域负责人ID" />
      </el-form-item>
      <el-form-item label="分域负责人姓名" prop="mngrName">
        <el-input v-model="formData.mngrName" placeholder="请输入分域负责人姓名" />
      </el-form-item>
      <el-form-item label="分域状态" prop="domStatus">
        <el-radio-group v-model="formData.domStatus">
          <el-radio :label="true" border>启用</el-radio>
          <el-radio :label="false" border>禁用</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="分类扩展字段1" prop="extCat1">
        <el-input v-model="formData.extCat1" placeholder="请输入分类扩展字段1" />
      </el-form-item>
      <el-form-item label="分类扩展字段2" prop="extCat2">
        <el-input v-model="formData.extCat2" placeholder="请输入分类扩展字段2" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  DomDivAdminApi,
  DomDivAdminVO
} from '@/api/dataHub/operationMonitor/domainMonitorManage/domdivadmin'

/** 行政区划分域 表单 */
defineOptions({ name: 'DomDivAdminForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<DomDivAdminVO>>({
  id: undefined,
  domId: undefined,
  domName: undefined,
  domCode: undefined,
  adminLevel: undefined,
  adminCode: undefined,
  adminName: undefined,
  relPtCount: undefined,
  relDeviceCount: undefined,
  mngrId: undefined,
  mngrName: undefined,
  domStatus: true,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  domName: [{ required: true, message: '分域名称不能为空', trigger: 'blur' }],
  domCode: [{ required: true, message: '分域编码不能为空', trigger: 'blur' }],
  adminLevel: [{ required: true, message: '行政区域级别不能为空', trigger: 'blur' }],
  adminCode: [{ required: true, message: '行政区域代码不能为空', trigger: 'blur' }],
  adminName: [{ required: true, message: '行政区域名称不能为空', trigger: 'blur' }],
  mngrId: [{ required: true, message: '分域负责人ID不能为空', trigger: 'blur' }],
  mngrName: [{ required: true, message: '分域负责人姓名不能为空', trigger: 'blur' }],
  domStatus: [{ required: true, message: '分域状态不能为空', trigger: 'change' }]
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
      const data = await DomDivAdminApi.getDomDivAdmin(id)
      formData.value = { ...data }
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
    const data = formData.value as unknown as DomDivAdminVO
    if (formType.value === 'create') {
      await DomDivAdminApi.createDomDivAdmin(data)
      message.success(t('common.createSuccess'))
    } else {
      await DomDivAdminApi.updateDomDivAdmin(data)
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
    adminLevel: undefined,
    adminCode: undefined,
    adminName: undefined,
    relPtCount: undefined,
    relDeviceCount: undefined,
    mngrId: undefined,
    mngrName: undefined,
    domStatus: true,
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
  padding: 15px;
}

::v-deep .el-form-item {
  margin-bottom: 18px;
}

::v-deep .el-radio-group {
  display: flex;
  gap: 20px;
}

::v-deep .el-dialog__body {
  padding: 15px 20px;
  max-height: 60vh;
  overflow-y: auto;
}
</style>
