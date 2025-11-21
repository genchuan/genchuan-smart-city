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
      <el-form-item label="跟踪ID" prop="handleTraceId">
        <el-input v-model="formData.handleTraceId" placeholder="请输入跟踪ID" />
      </el-form-item>
      <el-form-item label="派单ID" prop="dispatchId">
        <el-input v-model="formData.dispatchId" placeholder="请输入派单ID" />
      </el-form-item>
      <el-form-item label="派单编号" prop="dispatchNo">
        <el-input v-model="formData.dispatchNo" placeholder="请输入派单编号" />
      </el-form-item>
      <el-form-item label="预警ID" prop="earlyWarnId">
        <el-input v-model="formData.earlyWarnId" placeholder="请输入预警ID" />
      </el-form-item>
      <el-form-item label="处置进度" prop="handleProgress">
        <el-input v-model="formData.handleProgress" placeholder="请输入处置进度" />
      </el-form-item>
      <el-form-item label="进度描述" prop="progressDesc">
        <el-input
          v-model="formData.progressDesc"
          placeholder="请输入进度描述"
          type="textarea"
          rows="3"
        />
      </el-form-item>
      <el-form-item label="操作人ID" prop="operUserId">
        <el-input v-model="formData.operUserId" placeholder="请输入操作人ID" />
      </el-form-item>
      <el-form-item label="操作人姓名" prop="operUserName">
        <el-input v-model="formData.operUserName" placeholder="请输入操作人姓名" />
      </el-form-item>
      <el-form-item label="操作时间" prop="operTime">
        <el-date-picker
          v-model="formData.operTime"
          type="datetime"
          value-format="x"
          placeholder="选择操作时间"
          class="w-full"
        />
      </el-form-item>
      <el-form-item label="是否关键节点" prop="isKeyNode">
        <el-select v-model="formData.isKeyNode" placeholder="请选择是否关键节点">
          <el-option label="是" value="1" />
          <el-option label="否" value="0" />
        </el-select>
      </el-form-item>
      <el-form-item label="附件URL" prop="attachUrl">
        <el-input v-model="formData.attachUrl" placeholder="请输入附件URL" />
      </el-form-item>

      <el-collapse v-model="activeNames" class="mt-4">
        <el-collapse-item name="1" title="扩展字段（可选）">
          <el-form-item label="分类扩展字段1" prop="extCat1">
            <el-input v-model="formData.extCat1" placeholder="请输入分类扩展字段1" />
          </el-form-item>
          <el-form-item label="分类扩展字段2" prop="extCat2">
            <el-input v-model="formData.extCat2" placeholder="请输入分类扩展字段2" />
          </el-form-item>
          <el-form-item label="通用扩展字段1" prop="extCommon1">
            <el-input v-model="formData.extCommon1" placeholder="请输入通用扩展字段1" />
          </el-form-item>
          <el-form-item label="通用扩展字段2" prop="extCommon2">
            <el-input v-model="formData.extCommon2" placeholder="请输入通用扩展字段2" />
          </el-form-item>
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
  EarlyWarnHandleTraceApi,
  EarlyWarnHandleTraceVO
} from '@/api/dataHub/warningAlarm/warnAlarmRespDisposal/earlywarnhandletrace'

/** 预警告警处置跟踪 表单 */
defineOptions({ name: 'EarlyWarnHandleTraceForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const activeNames = ref(['1']) // 折叠面板状态
const formData = ref({
  id: undefined,
  handleTraceId: undefined,
  dispatchId: undefined,
  dispatchNo: undefined,
  earlyWarnId: undefined,
  handleProgress: undefined,
  progressDesc: undefined,
  operUserId: undefined,
  operUserName: undefined,
  operTime: undefined,
  attachUrl: undefined,
  isKeyNode: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  handleTraceId: [{ required: true, message: '跟踪ID不能为空', trigger: 'blur' }],
  dispatchId: [{ required: true, message: '派单ID不能为空', trigger: 'blur' }],
  dispatchNo: [{ required: true, message: '派单编号不能为空', trigger: 'blur' }],
  earlyWarnId: [{ required: true, message: '预警ID不能为空', trigger: 'blur' }],
  handleProgress: [{ required: true, message: '处置进度不能为空', trigger: 'blur' }],
  operUserId: [{ required: true, message: '操作人ID不能为空', trigger: 'blur' }],
  operUserName: [{ required: true, message: '操作人姓名不能为空', trigger: 'blur' }],
  operTime: [{ required: true, message: '操作时间不能为空', trigger: 'blur' }],
  isKeyNode: [{ required: true, message: '是否关键节点不能为空', trigger: 'blur' }]
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
      formData.value = await EarlyWarnHandleTraceApi.getEarlyWarnHandleTrace(id)
      // 转换时间格式
      if (formData.value.operTime) {
        formData.value.operTime = new Date(formData.value.operTime).getTime()
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
    const data = formData.value as unknown as EarlyWarnHandleTraceVO
    // 转换时间格式
    if (data.operTime) {
      data.operTime = new Date(Number(data.operTime))
    }

    if (formType.value === 'create') {
      await EarlyWarnHandleTraceApi.createEarlyWarnHandleTrace(data)
      message.success(t('common.createSuccess'))
    } else {
      await EarlyWarnHandleTraceApi.updateEarlyWarnHandleTrace(data)
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
    handleTraceId: undefined,
    dispatchId: undefined,
    dispatchNo: undefined,
    earlyWarnId: undefined,
    handleProgress: undefined,
    progressDesc: undefined,
    operUserId: undefined,
    operUserName: undefined,
    operTime: undefined,
    attachUrl: undefined,
    isKeyNode: undefined,
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

::v-deep .el-form-item {
  margin-bottom: 18px;
}

::v-deep .el-collapse {
  border: none;
}

::v-deep .el-collapse-item__content {
  padding-bottom: 0;
  background-color: #fff;
  border-radius: 4px;
  margin-top: 5px;
}
</style>
