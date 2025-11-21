<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="1000px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
      class="form-container"
    >
      <div class="form-grid">
        <el-form-item label="档案ID" prop="ledgerArchId">
          <el-input v-model="formData.ledgerArchId" placeholder="请输入档案ID" class="!w-300px" />
        </el-form-item>
        <el-form-item label="档案编码" prop="ledgerArchCode">
          <el-input
            v-model="formData.ledgerArchCode"
            placeholder="请输入档案编码"
            class="!w-300px"
          />
        </el-form-item>
        <el-form-item label="档案名称" prop="ledgerArchName">
          <el-input
            v-model="formData.ledgerArchName"
            placeholder="请输入档案名称"
            class="!w-300px"
          />
        </el-form-item>
        <el-form-item label="所属区域代码" prop="areaCode">
          <el-input v-model="formData.areaCode" placeholder="请输入所属区域代码" class="!w-300px" />
        </el-form-item>
        <el-form-item label="所属区域名称" prop="areaName">
          <el-input v-model="formData.areaName" placeholder="请输入所属区域名称" class="!w-300px" />
        </el-form-item>
        <el-form-item label="台账周期" prop="ledgerCycle">
          <el-input
            v-model="formData.ledgerCycle"
            placeholder="请输入台账周期（如：2024年第二季度）"
            class="!w-300px"
          />
        </el-form-item>
        <el-form-item label="核心指标JSON" prop="coreIdxJson">
          <el-input
            v-model="formData.coreIdxJson"
            placeholder="请输入核心指标JSON字符串"
            type="textarea"
            :rows="3"
            class="!w-300px"
          />
        </el-form-item>
        <el-form-item label="包含档案类型" prop="includeArchTypes">
          <el-input
            v-model="formData.includeArchTypes"
            placeholder="请输入包含档案类型（用逗号分隔）"
            class="!w-300px"
          />
        </el-form-item>
        <el-form-item label="台账附件URL" prop="ledgerAttachUrl">
          <el-input
            v-model="formData.ledgerAttachUrl"
            placeholder="请输入台账附件URL"
            class="!w-300px"
          />
        </el-form-item>
        <el-form-item label="归档人" prop="ledgerArchUser">
          <el-input v-model="formData.ledgerArchUser" placeholder="请输入归档人" class="!w-300px" />
        </el-form-item>
        <el-form-item label="归档时间" prop="ledgerArchTime">
          <el-date-picker
            v-model="formData.ledgerArchTime"
            type="date"
            value-format="x"
            placeholder="选择归档时间"
            class="!w-300px"
          />
        </el-form-item>
        <el-form-item label="档案状态" prop="ledgerArchStatus">
          <el-select
            v-model="formData.ledgerArchStatus"
            placeholder="请选择档案状态"
            class="!w-300px"
          >
            <el-option label="待归档" value="待归档" />
            <el-option label="已归档" value="已归档" />
          </el-select>
        </el-form-item>
        <el-form-item label="销毁时间" prop="ledgerDestroyTime">
          <el-date-picker
            v-model="formData.ledgerDestroyTime"
            type="date"
            value-format="x"
            placeholder="选择销毁时间（非必填）"
            class="!w-300px"
          />
        </el-form-item>
        <el-form-item label="归档说明" prop="ledgerArchDesc">
          <el-input
            v-model="formData.ledgerArchDesc"
            placeholder="请输入归档说明"
            type="textarea"
            :rows="3"
            class="!w-300px"
          />
        </el-form-item>
      </div>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading" class="!mr-4"
        >确 定
      </el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  InspectLedgerArchApi,
  InspectLedgerArchVO
} from '@/api/dataHub/supervisionInspection/patrolArchiveManagement/accountBookArchive'

/** 巡查巡检台账档案 表单 */
defineOptions({ name: 'InspectLedgerArchForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改

const formData = ref({
  id: undefined,
  ledgerArchId: undefined,
  ledgerArchCode: undefined,
  ledgerArchName: undefined,
  areaCode: undefined,
  areaName: undefined,
  ledgerCycle: undefined,
  coreIdxJson: undefined,
  includeArchTypes: undefined,
  ledgerAttachUrl: undefined,
  ledgerArchUser: undefined,
  ledgerArchTime: undefined,
  ledgerArchStatus: undefined,
  ledgerDestroyTime: undefined,
  ledgerArchDesc: undefined
})

const formRules = reactive({
  ledgerArchId: [{ required: true, message: '档案ID不能为空', trigger: 'blur' }],
  ledgerArchCode: [{ required: true, message: '档案编码不能为空', trigger: 'blur' }],
  ledgerArchName: [{ required: true, message: '档案名称不能为空', trigger: 'blur' }],
  areaCode: [{ required: true, message: '所属区域代码不能为空', trigger: 'blur' }],
  areaName: [{ required: true, message: '所属区域名称不能为空', trigger: 'blur' }],
  ledgerCycle: [{ required: true, message: '台账周期不能为空', trigger: 'blur' }],
  coreIdxJson: [{ required: true, message: '核心指标JSON不能为空', trigger: 'blur' }],
  includeArchTypes: [{ required: true, message: '包含档案类型不能为空', trigger: 'blur' }],
  ledgerAttachUrl: [{ required: true, message: '台账附件URL不能为空', trigger: 'blur' }],
  ledgerArchUser: [{ required: true, message: '归档人不能为空', trigger: 'blur' }],
  ledgerArchTime: [{ required: true, message: '归档时间不能为空', trigger: 'blur' }],
  ledgerArchStatus: [{ required: true, message: '档案状态不能为空', trigger: 'blur' }]
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
      formData.value = await InspectLedgerArchApi.getInspectLedgerArch(id)
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
    const data = formData.value as unknown as InspectLedgerArchVO
    if (formType.value === 'create') {
      await InspectLedgerArchApi.createInspectLedgerArch(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectLedgerArchApi.updateInspectLedgerArch(data)
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
    ledgerArchId: undefined,
    ledgerArchCode: undefined,
    ledgerArchName: undefined,
    areaCode: undefined,
    areaName: undefined,
    ledgerCycle: undefined,
    coreIdxJson: undefined,
    includeArchTypes: undefined,
    ledgerAttachUrl: undefined,
    ledgerArchUser: undefined,
    ledgerArchTime: undefined,
    ledgerArchStatus: undefined,
    ledgerDestroyTime: undefined,
    ledgerArchDesc: undefined
  }
  formRef.value?.resetFields()
}
</script>
<style scoped>
.form-container {
  padding: 16px;
}

.form-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

::v-deep .el-form-item {
  margin-bottom: 0;
}

::v-deep .el-input,
::v-deep .el-select,
::v-deep .el-date-picker {
  border-radius: 6px;
}

::v-deep .el-form-item__label {
  font-weight: 500;
  color: #333;
}

::v-deep .el-textarea {
  resize: none;
}
</style>
