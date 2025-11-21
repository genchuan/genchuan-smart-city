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
      <el-row :gutter="20">
        <el-col :span="24">
          <el-form-item label="传播链ID" prop="propagateChainId">
            <el-input v-model="formData.propagateChainId" placeholder="请输入传播链ID" />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="传播链编号" prop="propagateChainNo">
            <el-input v-model="formData.propagateChainNo" placeholder="请输入传播链编号" />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="源头预警ID" prop="sourceWarnId">
            <el-input v-model="formData.sourceWarnId" placeholder="请输入源头预警ID" />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="源头预警名称" prop="sourceWarnName">
            <el-input v-model="formData.sourceWarnName" placeholder="请输入源头预警名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="传播节点序号" prop="nodeSeq">
            <el-input v-model.number="formData.nodeSeq" placeholder="请输入传播节点序号" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="节点类型" prop="nodeType">
            <el-select v-model="formData.nodeType" placeholder="请选择节点类型">
              <el-option label="请选择字典生成" value="" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="发生时间" prop="occurTime">
            <el-date-picker
              v-model="formData.occurTime"
              type="datetime"
              value-format="x"
              placeholder="选择发生时间"
              style="width: 100%"
            />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="传播节点内容" prop="nodeContent">
            <Editor v-model="formData.nodeContent" height="150px" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联设备ID" prop="deviceId">
            <el-input v-model="formData.deviceId" placeholder="请输入关联设备ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联设备名称" prop="deviceName">
            <el-input v-model="formData.deviceName" placeholder="请输入关联设备名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="记录人ID" prop="recUserId">
            <el-input v-model="formData.recUserId" placeholder="请输入记录人ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="记录人姓名" prop="recUserName">
            <el-input v-model="formData.recUserName" placeholder="请输入记录人姓名" />
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
  RiskPropagateChainApi,
  RiskPropagateChainVO
} from '@/api/dataHub/warningAlarm/riskIdentifyTrace/riskpropagatechain'

/** 风险传播链 表单 */
defineOptions({ name: 'RiskPropagateChainForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<RiskPropagateChainVO>>({
  id: undefined,
  propagateChainId: undefined,
  propagateChainNo: undefined,
  sourceWarnId: undefined,
  sourceWarnName: undefined,
  nodeSeq: undefined,
  nodeContent: undefined,
  nodeType: undefined,
  occurTime: undefined,
  impactRange: undefined,
  deviceId: undefined,
  deviceName: undefined,
  recUserId: undefined,
  recUserName: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  propagateChainId: [{ required: true, message: '传播链ID不能为空', trigger: 'blur' }],
  propagateChainNo: [{ required: true, message: '传播链编号不能为空', trigger: 'blur' }],
  sourceWarnId: [{ required: true, message: '源头预警ID不能为空', trigger: 'blur' }],
  sourceWarnName: [{ required: true, message: '源头预警名称不能为空', trigger: 'blur' }],
  nodeSeq: [{ required: true, message: '传播节点序号不能为空', trigger: 'blur' }],
  nodeContent: [{ required: true, message: '传播节点内容不能为空', trigger: 'blur' }],
  nodeType: [{ required: true, message: '节点类型不能为空', trigger: 'change' }],
  occurTime: [{ required: true, message: '发生时间不能为空', trigger: 'blur' }],
  recUserId: [{ required: true, message: '记录人ID不能为空', trigger: 'blur' }],
  recUserName: [{ required: true, message: '记录人姓名不能为空', trigger: 'blur' }]
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
      formData.value = await RiskPropagateChainApi.getRiskPropagateChain(id)
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
    const data = formData.value as unknown as RiskPropagateChainVO
    if (formType.value === 'create') {
      await RiskPropagateChainApi.createRiskPropagateChain(data)
      message.success(t('common.createSuccess'))
    } else {
      await RiskPropagateChainApi.updateRiskPropagateChain(data)
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
    propagateChainId: undefined,
    propagateChainNo: undefined,
    sourceWarnId: undefined,
    sourceWarnName: undefined,
    nodeSeq: undefined,
    nodeContent: undefined,
    nodeType: undefined,
    occurTime: undefined,
    impactRange: undefined,
    deviceId: undefined,
    deviceName: undefined,
    recUserId: undefined,
    recUserName: undefined,
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

::v-deep .el-input__wrapper,
::v-deep .el-select__wrapper,
::v-deep .el-date-picker__input-wrapper {
  border-radius: 4px;
}

::v-deep .el-form-item__label {
  color: #4e5969;
  font-weight: 500;
}
</style>
