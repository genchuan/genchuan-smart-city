<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="650px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
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
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="分域编码" prop="domCode">
            <el-input v-model="formData.domCode" placeholder="请输入分域编码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分域状态" prop="domStatus">
            <el-radio-group v-model="formData.domStatus">
              <el-radio :label="true" class="radio-success">启用</el-radio>
              <el-radio :label="false" class="radio-danger">停用</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="应用场景大类ID" prop="appSceneMajorId">
            <el-input v-model="formData.appSceneMajorId" placeholder="请输入应用场景大类ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="应用场景大类名称" prop="appSceneMajorName">
            <el-input v-model="formData.appSceneMajorName" placeholder="请输入应用场景大类名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="应用场景小类ID" prop="appSceneMinorId">
            <el-input v-model="formData.appSceneMinorId" placeholder="请输入应用场景小类ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="应用场景小类名称" prop="appSceneMinorName">
            <el-input v-model="formData.appSceneMinorName" placeholder="请输入应用场景小类名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="8">
          <el-form-item label="关联点位数量(个)" prop="relPtCount">
            <el-input v-model.number="formData.relPtCount" placeholder="请输入数量" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="关联设备数量(个)" prop="relDeviceCount">
            <el-input v-model.number="formData.relDeviceCount" placeholder="请输入数量" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="关联事件数量(件)" prop="relEvtCount">
            <el-input v-model.number="formData.relEvtCount" placeholder="请输入数量" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="场景负责人ID" prop="sceneMngrId">
            <el-input v-model="formData.sceneMngrId" placeholder="请输入场景负责人ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="场景负责人姓名" prop="sceneMngrName">
            <el-input v-model="formData.sceneMngrName" placeholder="请输入场景负责人姓名" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="主管部门代码" prop="deptCode">
            <el-input v-model="formData.deptCode" placeholder="请输入主管部门代码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="主管部门名称" prop="deptName">
            <el-input v-model="formData.deptName" placeholder="请输入主管部门名称" />
          </el-form-item>
        </el-col>
      </el-row>

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

      <el-row :gutter="20" class="mt-2">
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
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  DomDivAppSceneApi,
  DomDivAppSceneVO
} from '@/api/dataHub/operationMonitor/domainMonitorManage/domdivappscene'

/** 应用场景分域 表单 */
defineOptions({ name: 'DomDivAppSceneForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  domId: undefined,
  domName: undefined,
  domCode: undefined,
  appSceneMajorId: undefined,
  appSceneMajorName: undefined,
  appSceneMinorId: undefined,
  appSceneMinorName: undefined,
  relPtCount: undefined,
  relDeviceCount: undefined,
  relEvtCount: undefined,
  sceneMngrId: undefined,
  sceneMngrName: undefined,
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
  domName: [{ required: true, message: '分域名称不能为空', trigger: 'blur' }],
  domCode: [{ required: true, message: '分域编码不能为空', trigger: 'blur' }],
  appSceneMajorId: [{ required: true, message: '应用场景大类ID不能为空', trigger: 'blur' }],
  appSceneMajorName: [{ required: true, message: '应用场景大类名称不能为空', trigger: 'blur' }],
  appSceneMinorId: [{ required: true, message: '应用场景小类ID不能为空', trigger: 'blur' }],
  appSceneMinorName: [{ required: true, message: '应用场景小类名称不能为空', trigger: 'blur' }],
  sceneMngrId: [{ required: true, message: '场景负责人ID不能为空', trigger: 'blur' }],
  sceneMngrName: [{ required: true, message: '场景负责人姓名不能为空', trigger: 'blur' }],
  deptCode: [{ required: true, message: '主管部门代码不能为空', trigger: 'blur' }],
  deptName: [{ required: true, message: '主管部门名称不能为空', trigger: 'blur' }],
  domStatus: [{ required: true, message: '分域状态不能为空', trigger: 'change' }],
  createTimeBiz: [{ required: true, message: '创建时间(业务)不能为空', trigger: 'change' }],
  updateTimeBiz: [{ required: true, message: '更新时间(业务)不能为空', trigger: 'change' }]
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
      formData.value = await DomDivAppSceneApi.getDomDivAppScene(id)
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
    const data = formData.value as unknown as DomDivAppSceneVO
    if (formType.value === 'create') {
      await DomDivAppSceneApi.createDomDivAppScene(data)
      message.success(t('common.createSuccess'))
    } else {
      await DomDivAppSceneApi.updateDomDivAppScene(data)
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
    appSceneMajorId: undefined,
    appSceneMajorName: undefined,
    appSceneMinorId: undefined,
    appSceneMinorName: undefined,
    relPtCount: undefined,
    relDeviceCount: undefined,
    relEvtCount: undefined,
    sceneMngrId: undefined,
    sceneMngrName: undefined,
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
  padding: 15px;
  background-color: #f9fafb;
  border-radius: 8px;
}

.el-row {
  margin-bottom: 15px;
}

.radio-success {
  color: #4caf50;
}

.radio-danger {
  color: #f44336;
}
</style>
