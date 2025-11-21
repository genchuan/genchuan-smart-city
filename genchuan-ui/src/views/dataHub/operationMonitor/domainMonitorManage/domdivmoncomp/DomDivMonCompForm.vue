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
      <el-form-item label="分域ID" prop="domId" required>
        <el-input v-model="formData.domId" placeholder="请输入分域ID" />
      </el-form-item>
      <el-form-item label="分域名称" prop="domName" required>
        <el-input v-model="formData.domName" placeholder="请输入分域名称" />
      </el-form-item>
      <el-form-item label="分极编码" prop="domCode" required>
        <el-input v-model="formData.domCode" placeholder="请输入分极编码" />
      </el-form-item>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="监测部件大类ID" prop="monCompMajorId">
            <el-input v-model="formData.monCompMajorId" placeholder="请输入监测部件大类ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="监测部件大类名称" prop="monCompMajorName">
            <el-input v-model="formData.monCompMajorName" placeholder="请输入监测部件大类名称" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="监测部件小类ID" prop="monCompMinorId">
            <el-input v-model="formData.monCompMinorId" placeholder="请输入监测部件小类ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="监测部件小类名称" prop="monCompMinorName">
            <el-input v-model="formData.monCompMinorName" placeholder="请输入监测部件小类名称" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="关联点位数量(个)" prop="relPtCount">
            <el-input v-model.number="formData.relPtCount" placeholder="请输入关联点位数量" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联设备数量(个)" prop="relDeviceCount">
            <el-input v-model.number="formData.relDeviceCount" placeholder="请输入关联设备数量" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="设备在线率(%)" prop="deviceOnlineRate">
        <el-input v-model.number="formData.deviceOnlineRate" placeholder="请输入设备在线率" />
      </el-form-item>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="维护部门代码" prop="deptCode">
            <el-input v-model="formData.deptCode" placeholder="请输入维护部门代码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="维护部门名称" prop="deptName">
            <el-input v-model="formData.deptName" placeholder="请输入维护部门名称" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="分域状态" prop="domStatus">
        <el-radio-group v-model="formData.domStatus">
          <el-radio :label="true" border>启用</el-radio>
          <el-radio :label="false" border>停用</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="创建时间(业务)" prop="createTimeBiz">
            <el-date-picker
              v-model="formData.createTimeBiz"
              type="date"
              value-format="x"
              placeholder="选择创建时间(业务)"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="更新时间(业务)" prop="updateTimeBiz">
            <el-date-picker
              v-model="formData.updateTimeBiz"
              type="date"
              value-format="x"
              placeholder="选择更新时间(业务)"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-collapse v-model="activeNames" border>
        <el-collapse-item name="1" title="扩展字段">
          <el-row :gutter="20">
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
          </el-row>
          <el-row :gutter="20">
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
        </el-collapse-item>
      </el-collapse>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  DomDivMonCompApi,
  DomDivMonCompVO
} from '@/api/dataHub/operationMonitor/domainMonitorManage/domdivmoncomp'

/** 监测部件分域 表单 */
defineOptions({ name: 'DomDivMonCompForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const activeNames = ref(['1']) // 折叠面板默认状态
const formData = ref<Partial<DomDivMonCompVO>>({
  id: undefined,
  domId: undefined,
  domName: undefined,
  domCode: undefined,
  monCompMajorId: undefined,
  monCompMajorName: undefined,
  monCompMinorId: undefined,
  monCompMinorName: undefined,
  relPtCount: undefined,
  relDeviceCount: undefined,
  deviceOnlineRate: undefined,
  deptCode: undefined,
  deptName: undefined,
  domStatus: true,
  createTimeBiz: undefined,
  updateTimeBiz: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  domId: [{ required: true, message: '请输入分域ID', trigger: 'blur' }],
  domName: [{ required: true, message: '请输入分域名称', trigger: 'blur' }],
  domCode: [{ required: true, message: '请输入分极编码', trigger: 'blur' }]
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
      const res = await DomDivMonCompApi.getDomDivMonComp(id)
      formData.value = { ...res }
    } finally {
      formLoading.value = false
    }
  }
}
defineExpose({ open }) // 提供 open 方法，用于打开弹窗

/** 提交表单 */
const emit = defineEmits(['success']) // 定义 success 事件
const submitForm = async () => {
  // 校验表单
  await formRef.value.validate()
  // 提交请求
  formLoading.value = true
  try {
    const data = formData.value as unknown as DomDivMonCompVO
    if (formType.value === 'create') {
      await DomDivMonCompApi.createDomDivMonComp(data)
      message.success(t('common.createSuccess'))
    } else {
      await DomDivMonCompApi.updateDomDivMonComp(data)
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
    monCompMajorId: undefined,
    monCompMajorName: undefined,
    monCompMinorId: undefined,
    monCompMinorName: undefined,
    relPtCount: undefined,
    relDeviceCount: undefined,
    deviceOnlineRate: undefined,
    deptCode: undefined,
    deptName: undefined,
    domStatus: true,
    createTimeBiz: undefined,
    updateTimeBiz: undefined,
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
  margin-bottom: 16px;
}

::v-deep .el-collapse-item__content {
  padding-bottom: 10px !important;
}
</style>
