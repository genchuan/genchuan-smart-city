<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" :width="700">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
      class="custom-form"
    >
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="解除ID" prop="releaseId">
            <el-input v-model="formData.releaseId" placeholder="请输入解除ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预警ID" prop="earlyWarnId">
            <el-input v-model="formData.earlyWarnId" placeholder="请输入预警ID" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="预警名称" prop="earlyWarnName">
        <el-input v-model="formData.earlyWarnName" placeholder="请输入预警名称" />
      </el-form-item>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="解除申请人ID" prop="applyUserId">
            <el-input v-model="formData.applyUserId" placeholder="请输入解除申请人ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="解除申请人姓名" prop="applyUserName">
            <el-input v-model="formData.applyUserName" placeholder="请输入解除申请人姓名" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="解除审核人ID" prop="auditUserId">
            <el-input v-model="formData.auditUserId" placeholder="请输入解除审核人ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="解除审核人姓名" prop="auditUserName">
            <el-input v-model="formData.auditUserName" placeholder="请输入解除审核人姓名" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="解除状态" prop="releaseStatus">
            <el-radio-group v-model="formData.releaseStatus">
              <el-radio value="APPROVED">通过</el-radio>
              <el-radio value="PENDING">驳回</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="是否复发" prop="isRecurrence">
            <el-radio-group v-model="formData.isRecurrence">
              <el-radio value="1">是</el-radio>
              <el-radio value="0">否</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="申请时间" prop="applyTime">
            <el-date-picker
              v-model="formData.applyTime"
              type="datetime"
              value-format="x"
              placeholder="选择申请时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="审核时间" prop="auditTime">
            <el-date-picker
              v-model="formData.auditTime"
              type="datetime"
              value-format="x"
              placeholder="选择审核时间"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="解除原因" prop="releaseReason">
        <el-input
          v-model="formData.releaseReason"
          placeholder="请输入解除原因"
          type="textarea"
          rows="3"
        />
      </el-form-item>

      <el-form-item label="驳回原因" prop="rejectReason">
        <el-input
          v-model="formData.rejectReason"
          placeholder="请输入驳回原因"
          type="textarea"
          rows="3"
        />
      </el-form-item>

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
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  EarlyWarnReleaseApi,
  EarlyWarnReleaseVO
} from '@/api/dataHub/warningAlarm/warnAlarmRespDisposal/earlywarnrelease'

/** 预警告警解除 表单 */
defineOptions({ name: 'EarlyWarnReleaseForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  releaseId: undefined,
  earlyWarnId: undefined,
  earlyWarnName: undefined,
  applyUserId: undefined,
  applyUserName: undefined,
  auditUserId: undefined,
  auditUserName: undefined,
  releaseStatus: undefined,
  releaseReason: undefined,
  applyTime: undefined,
  auditTime: undefined,
  rejectReason: undefined,
  isRecurrence: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  releaseId: [{ required: true, message: '解除ID不能为空', trigger: 'blur' }],
  earlyWarnId: [{ required: true, message: '预警ID不能为空', trigger: 'blur' }],
  earlyWarnName: [{ required: true, message: '预警名称不能为空', trigger: 'blur' }],
  applyUserId: [{ required: true, message: '解除申请人ID不能为空', trigger: 'blur' }],
  applyUserName: [{ required: true, message: '解除申请人姓名不能为空', trigger: 'blur' }],
  auditUserId: [{ required: true, message: '解除审核人ID不能为空', trigger: 'blur' }],
  auditUserName: [{ required: true, message: '解除审核人姓名不能为空', trigger: 'blur' }],
  releaseStatus: [{ required: true, message: '解除状态不能为空', trigger: 'blur' }],
  releaseReason: [{ required: true, message: '解除原因不能为空', trigger: 'blur' }],
  applyTime: [{ required: true, message: '申请时间不能为空', trigger: 'blur' }],
  isRecurrence: [{ required: true, message: '是否复发不能为空', trigger: 'blur' }]
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
      formData.value = await EarlyWarnReleaseApi.getEarlyWarnRelease(id)
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
    const data = formData.value as unknown as EarlyWarnReleaseVO
    if (formType.value === 'create') {
      await EarlyWarnReleaseApi.createEarlyWarnRelease(data)
      message.success(t('common.createSuccess'))
    } else {
      await EarlyWarnReleaseApi.updateEarlyWarnRelease(data)
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
    releaseId: undefined,
    earlyWarnId: undefined,
    earlyWarnName: undefined,
    applyUserId: undefined,
    applyUserName: undefined,
    auditUserId: undefined,
    auditUserName: undefined,
    releaseStatus: undefined,
    releaseReason: undefined,
    applyTime: undefined,
    auditTime: undefined,
    rejectReason: undefined,
    isRecurrence: undefined,
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
  padding: 15px 0;
}

.el-row {
  margin-bottom: 15px;
}

.el-row:last-child {
  margin-bottom: 0;
}

.el-textarea__inner {
  resize: vertical;
}
</style>
