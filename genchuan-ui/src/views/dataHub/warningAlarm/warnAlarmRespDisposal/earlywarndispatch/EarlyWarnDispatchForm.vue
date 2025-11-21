<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="700px">
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
          <el-form-item label="派单ID" prop="dispatchId">
            <el-input v-model="formData.dispatchId" placeholder="请输入派单ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="派单编号" prop="dispatchNo">
            <el-input v-model="formData.dispatchNo" placeholder="请输入派单编号" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="预警ID" prop="earlyWarnId">
            <el-input v-model="formData.earlyWarnId" placeholder="请输入预警ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预警名称" prop="earlyWarnName">
            <el-input v-model="formData.earlyWarnName" placeholder="请输入预警名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="派单类型" prop="dispatchType">
            <el-select v-model="formData.dispatchType" placeholder="请选择派单类型">
              <el-option label="请选择字典生成" value="" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <!-- 修改派单状态选择器 -->
          <el-form-item label="派单状态" prop="dispatchStatus">
            <el-radio-group v-model="formData.dispatchStatus">
              <el-radio label="RECEIVED">已接收</el-radio>
              <el-radio label="HANDLING">处理中</el-radio>
              <el-radio label="COMPLETED">已完成</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="接收部门ID" prop="receiveDeptId">
            <el-input v-model="formData.receiveDeptId" placeholder="请输入接收部门ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="接收部门名称" prop="receiveDeptName">
            <el-input v-model="formData.receiveDeptName" placeholder="请输入接收部门名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="处置人ID" prop="handleUserId">
            <el-input v-model="formData.handleUserId" placeholder="请输入处置人ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="处置人姓名" prop="handleUserName">
            <el-input v-model="formData.handleUserName" placeholder="请输入处置人姓名" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="处置时限" prop="handleLimit">
            <el-input v-model="formData.handleLimit" placeholder="请输入处置时限" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="派单时间" prop="dispatchTime">
            <el-date-picker
              v-model="formData.dispatchTime"
              type="datetime"
              value-format="x"
              placeholder="选择派单时间"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="派单人ID" prop="dispatchUserId">
            <el-input v-model="formData.dispatchUserId" placeholder="请输入派单人ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="派单人姓名" prop="dispatchUserName">
            <el-input v-model="formData.dispatchUserName" placeholder="请输入派单人姓名" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="24">
          <el-form-item label="拒单原因" prop="rejectReason">
            <el-input
              v-model="formData.rejectReason"
              placeholder="请输入拒单原因"
              type="textarea"
              rows="3"
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
  EarlyWarnDispatchApi,
  EarlyWarnDispatchVO
} from '@/api/dataHub/warningAlarm/warnAlarmRespDisposal/earlywarndispatch'

/** 预警告警派单 表单 */
defineOptions({ name: 'EarlyWarnDispatchForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  dispatchId: undefined,
  dispatchNo: undefined,
  earlyWarnId: undefined,
  earlyWarnName: undefined,
  dispatchType: undefined,
  receiveDeptId: undefined,
  receiveDeptName: undefined,
  handleUserId: undefined,
  handleUserName: undefined,
  dispatchStatus: undefined,
  handleLimit: undefined,
  dispatchTime: undefined,
  receiveTime: undefined,
  rejectReason: undefined,
  dispatchUserId: undefined,
  dispatchUserName: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  dispatchId: [{ required: true, message: '派单ID不能为空', trigger: 'blur' }],
  dispatchNo: [{ required: true, message: '派单编号不能为空', trigger: 'blur' }],
  earlyWarnId: [{ required: true, message: '预警ID不能为空', trigger: 'blur' }],
  earlyWarnName: [{ required: true, message: '预警名称不能为空', trigger: 'blur' }],
  dispatchType: [{ required: true, message: '派单类型不能为空', trigger: 'change' }],
  receiveDeptId: [{ required: true, message: '接收部门ID不能为空', trigger: 'blur' }],
  receiveDeptName: [{ required: true, message: '接收部门名称不能为空', trigger: 'blur' }],
  handleUserId: [{ required: true, message: '处置人ID不能为空', trigger: 'blur' }],
  handleUserName: [{ required: true, message: '处置人姓名不能为空', trigger: 'blur' }],
  dispatchStatus: [{ required: true, message: '派单状态不能为空', trigger: 'change' }],
  handleLimit: [{ required: true, message: '处置时限不能为空', trigger: 'blur' }],
  dispatchTime: [{ required: true, message: '派单时间不能为空', trigger: 'blur' }],
  dispatchUserId: [{ required: true, message: '派单人ID不能为空', trigger: 'blur' }],
  dispatchUserName: [{ required: true, message: '派单人姓名不能为空', trigger: 'blur' }]
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
      formData.value = await EarlyWarnDispatchApi.getEarlyWarnDispatch(id)
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
    const data = formData.value as unknown as EarlyWarnDispatchVO
    if (formType.value === 'create') {
      await EarlyWarnDispatchApi.createEarlyWarnDispatch(data)
      message.success(t('common.createSuccess'))
    } else {
      await EarlyWarnDispatchApi.updateEarlyWarnDispatch(data)
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
    dispatchId: undefined,
    dispatchNo: undefined,
    earlyWarnId: undefined,
    earlyWarnName: undefined,
    dispatchType: undefined,
    receiveDeptId: undefined,
    receiveDeptName: undefined,
    handleUserId: undefined,
    handleUserName: undefined,
    dispatchStatus: undefined,
    handleLimit: undefined,
    dispatchTime: undefined,
    receiveTime: undefined,
    rejectReason: undefined,
    dispatchUserId: undefined,
    dispatchUserName: undefined,
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
  background-color: #f9fafb;
  padding: 20px;
  border-radius: 8px;
}

.el-row {
  margin-bottom: 15px;
}

.el-form-item__label {
  font-weight: 500;
  color: #4e5969;
}

::v-deep .el-form-item__content {
  line-height: 1;
}
</style>
