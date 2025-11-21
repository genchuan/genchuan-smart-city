<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="600px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
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

      <div class="form-grid">
        <el-form-item label="管理部件大类ID" prop="compMajorId">
          <el-input v-model="formData.compMajorId" placeholder="请输入管理部件大类ID" />
        </el-form-item>
        <el-form-item label="管理部件大类名称" prop="compMajorName">
          <el-input v-model="formData.compMajorName" placeholder="请输入管理部件大类名称" />
        </el-form-item>
      </div>

      <div class="form-grid">
        <el-form-item label="管理部件小类ID" prop="compMinorId">
          <el-input v-model="formData.compMinorId" placeholder="请输入管理部件小类ID" />
        </el-form-item>
        <el-form-item label="管理部件小类名称" prop="compMinorName">
          <el-input v-model="formData.compMinorName" placeholder="请输入管理部件小类名称" />
        </el-form-item>
      </div>

      <div class="form-grid">
        <el-form-item label="关联点位数量(个)" prop="relPtCount">
          <el-input v-model.number="formData.relPtCount" placeholder="请输入关联点位数量" />
        </el-form-item>
        <el-form-item label="关联设备数量(个)" prop="relDeviceCount">
          <el-input v-model.number="formData.relDeviceCount" placeholder="请输入关联设备数量" />
        </el-form-item>
      </div>

      <div class="form-grid">
        <el-form-item label="主管部门代码" prop="deptCode">
          <el-input v-model="formData.deptCode" placeholder="请输入主管部门代码" />
        </el-form-item>
        <el-form-item label="主管部门名称" prop="deptName">
          <el-input v-model="formData.deptName" placeholder="请输入主管部门名称" />
        </el-form-item>
      </div>

      <el-form-item label="分域状态" prop="domStatus">
        <el-radio-group v-model="formData.domStatus">
          <el-radio :label="true" class="status-radio">启用</el-radio>
          <el-radio :label="false" class="status-radio">禁用</el-radio>
        </el-radio-group>
      </el-form-item>

      <div class="form-grid">
        <el-form-item label="分类扩展字段1" prop="extCat1">
          <el-input v-model="formData.extCat1" placeholder="请输入分类扩展字段1" />
        </el-form-item>
        <el-form-item label="分类扩展字段2" prop="extCat2">
          <el-input v-model="formData.extCat2" placeholder="请输入分类扩展字段2" />
        </el-form-item>
      </div>

      <div class="form-grid">
        <el-form-item label="通用扩展字段1" prop="extCommon1">
          <el-input v-model="formData.extCommon1" placeholder="请输入通用扩展字段1" />
        </el-form-item>
        <el-form-item label="通用扩展字段2" prop="extCommon2">
          <el-input v-model="formData.extCommon2" placeholder="请输入通用扩展字段2" />
        </el-form-item>
      </div>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  DomDivMngCompApi,
  DomDivMngCompVO
} from '@/api/dataHub/operationMonitor/domainMonitorManage/domdivmngcomp'

/** 管理部件分域 表单 */
defineOptions({ name: 'DomDivMngCompForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<DomDivMngCompVO>>({
  id: undefined,
  domId: undefined,
  domName: undefined,
  domCode: undefined,
  compMajorId: undefined,
  compMajorName: undefined,
  compMinorId: undefined,
  compMinorName: undefined,
  relPtCount: 0,
  relDeviceCount: 0,
  deptCode: undefined,
  deptName: undefined,
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
  compMajorId: [{ required: true, message: '管理部件大类ID不能为空', trigger: 'blur' }],
  compMajorName: [{ required: true, message: '管理部件大类名称不能为空', trigger: 'blur' }],
  compMinorId: [{ required: true, message: '管理部件小类ID不能为空', trigger: 'blur' }],
  compMinorName: [{ required: true, message: '管理部件小类名称不能为空', trigger: 'blur' }],
  deptCode: [{ required: true, message: '主管部门代码不能为空', trigger: 'blur' }],
  deptName: [{ required: true, message: '主管部门名称不能为空', trigger: 'blur' }],
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
      const detail = await DomDivMngCompApi.getDomDivMngComp(id)
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
    const data = formData.value as unknown as DomDivMngCompVO
    // 处理时间字段
    if (formType.value === 'create') {
      data.createTimeSys = new Date()
      data.updateTimeSys = new Date()
      await DomDivMngCompApi.createDomDivMngComp(data)
      message.success(t('common.createSuccess'))
    } else {
      data.updateTimeSys = new Date()
      await DomDivMngCompApi.updateDomDivMngComp(data)
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
    compMajorId: undefined,
    compMajorName: undefined,
    compMinorId: undefined,
    compMinorName: undefined,
    relPtCount: 0,
    relDeviceCount: 0,
    deptCode: undefined,
    deptName: undefined,
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

.form-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 16px;
}

.status-radio {
  margin-right: 20px;
}

::v-deep .el-form-item {
  margin-bottom: 16px;
}
</style>
