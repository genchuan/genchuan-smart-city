<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="700px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="110px"
      v-loading="formLoading"
      class="custom-form"
    >
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="事件ID" prop="evtId">
            <el-input v-model="formData.evtId" placeholder="请输入事件ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="事件编码" prop="evtCode">
            <el-input v-model="formData.evtCode" placeholder="请输入事件编码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="事件类型" prop="evtType">
            <el-select v-model="formData.evtType" placeholder="请选择事件类型">
              <el-option label="请选择字典生成" value="" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联点位ID" prop="relPtId">
            <el-input v-model="formData.relPtId" placeholder="请输入关联点位ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联点位名称" prop="relPtName">
            <el-input v-model="formData.relPtName" placeholder="请输入关联点位名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联设备编码" prop="relDeviceCode">
            <el-input v-model="formData.relDeviceCode" placeholder="请输入关联设备编码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联设备名称" prop="relDeviceName">
            <el-input v-model="formData.relDeviceName" placeholder="请输入关联设备名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="事件数值" prop="evtValue">
            <el-input v-model="formData.evtValue" placeholder="请输入事件数值" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="事发时间" prop="evtTime">
            <el-date-picker
              v-model="formData.evtTime"
              type="datetime"
              value-format="x"
              placeholder="选择事发时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="处置状态" prop="dealStatus">
            <el-radio-group v-model="formData.dealStatus">
              <el-radio value="1">已处理</el-radio>
              <el-radio value="0">未处理</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="派单部门名称" prop="deptName">
            <el-input v-model="formData.deptName" placeholder="请输入派单部门名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="处置人姓名" prop="dealUserName">
            <el-input v-model="formData.dealUserName" placeholder="请输入处置人姓名" />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="处置结果" prop="dealResult">
            <el-input v-model="formData.dealResult" placeholder="请输入处置结果" type="textarea" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="处置时间" prop="dealTime">
            <el-date-picker
              v-model="formData.dealTime"
              type="datetime"
              value-format="x"
              placeholder="选择处置时间"
            />
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
  BasicMonEvtApi,
  BasicMonEvtVO
} from '@/api/dataHub/operationMonitor/basicDataManage/basicmonevt'

/** 基础监测事件 表单 */
defineOptions({ name: 'BasicMonEvtForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<BasicMonEvtVO>>({
  id: undefined,
  evtId: undefined,
  evtCode: undefined,
  evtType: undefined,
  relPtId: undefined,
  relPtName: undefined,
  relIdxId: undefined,
  relIdxName: undefined,
  relDeviceCode: undefined,
  relDeviceName: undefined,
  evtValue: undefined,
  evtTime: undefined,
  dealStatus: undefined,
  deptCode: undefined,
  deptName: undefined,
  dealUserId: undefined,
  dealUserName: undefined,
  dealResult: undefined,
  dealTime: undefined,
  createTimeSys: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  evtCode: [{ required: true, message: '事件编码不能为空', trigger: 'blur' }],
  evtType: [{ required: true, message: '事件类型不能为空', trigger: 'change' }],
  relPtId: [{ required: true, message: '关联点位ID不能为空', trigger: 'blur' }],
  relPtName: [{ required: true, message: '关联点位名称不能为空', trigger: 'blur' }],
  relDeviceCode: [{ required: true, message: '关联设备编码不能为空', trigger: 'blur' }],
  relDeviceName: [{ required: true, message: '关联设备名称不能为空', trigger: 'blur' }],
  evtTime: [{ required: true, message: '事发时间不能为空', trigger: 'blur' }],
  dealStatus: [{ required: true, message: '处置状态不能为空', trigger: 'change' }],
  createTimeSys: [{ required: true, message: '创建时间不能为空', trigger: 'blur' }]
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
      formData.value = await BasicMonEvtApi.getBasicMonEvt(id)
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
    const data = formData.value as unknown as BasicMonEvtVO
    if (formType.value === 'create') {
      await BasicMonEvtApi.createBasicMonEvt(data)
      message.success(t('common.createSuccess'))
    } else {
      await BasicMonEvtApi.updateBasicMonEvt(data)
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
    evtId: undefined,
    evtCode: undefined,
    evtType: undefined,
    relPtId: undefined,
    relPtName: undefined,
    relIdxId: undefined,
    relIdxName: undefined,
    relDeviceCode: undefined,
    relDeviceName: undefined,
    evtValue: undefined,
    evtTime: undefined,
    dealStatus: undefined,
    deptCode: undefined,
    deptName: undefined,
    dealUserId: undefined,
    dealUserName: undefined,
    dealResult: undefined,
    dealTime: undefined,
    createTimeSys: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined
  }
  formRef.value?.resetFields()
}
</script>

<style scoped>
.custom-form {
  background-color: #fff;
  padding: 15px;
  border-radius: 6px;
}

::v-deep .el-form-item {
  margin-bottom: 15px;
}

::v-deep .el-form-item__label {
  color: #4e5969;
  font-weight: 500;
}

::v-deep .el-input__wrapper,
::v-deep .el-select__wrapper,
::v-deep .el-date-picker__input-wrapper {
  border-radius: 4px;
}
</style>
