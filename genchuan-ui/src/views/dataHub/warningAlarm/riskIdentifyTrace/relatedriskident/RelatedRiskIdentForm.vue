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
          <el-form-item label="识别ID" prop="identId">
            <el-input v-model="formData.identId" placeholder="请输入识别ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="触发预警ID" prop="triggerWarnId">
            <el-input v-model="formData.triggerWarnId" placeholder="请输入触发预警ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="触发预警名称" prop="triggerWarnName">
            <el-input v-model="formData.triggerWarnName" placeholder="请输入触发预警名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="触发预警类型" prop="triggerWarnType">
            <el-select v-model="formData.triggerWarnType" placeholder="请选择触发预警类型">
              <el-option label="请选择字典生成" value="" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联风险名称" prop="relatedRiskName">
            <el-input v-model="formData.relatedRiskName" placeholder="请输入关联风险名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联风险类型" prop="relatedRiskType">
            <el-select v-model="formData.relatedRiskType" placeholder="请选择关联风险类型">
              <el-option label="请选择字典生成" value="" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="关联依据" prop="relatedBasis">
            <el-input
              v-model="formData.relatedBasis"
              placeholder="请输入关联依据"
              type="textarea"
              rows="3"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="风险等级" prop="riskLevel">
            <el-input v-model="formData.riskLevel" placeholder="请输入风险等级" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="识别时间" prop="identTime">
            <el-date-picker
              v-model="formData.identTime"
              type="datetime"
              value-format="x"
              placeholder="选择识别时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="识别方式" prop="identMethod">
            <el-input v-model="formData.identMethod" placeholder="请输入识别方式" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="识别用户ID" prop="identifyUserId">
            <el-input v-model="formData.identifyUserId" placeholder="请输入识别用户ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="识别用户姓名" prop="identifyUserName">
            <el-input v-model="formData.identifyUserName" placeholder="请输入识别用户姓名" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="是否已处置" prop="isHandled">
            <el-select v-model="formData.isHandled" placeholder="请选择是否已处置">
              <el-option label="是" value="1" />
              <el-option label="否" value="0" />
            </el-select>
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
  RelatedRiskIdentApi,
  RelatedRiskIdentVO
} from '@/api/dataHub/warningAlarm/riskIdentifyTrace/relatedriskident'

/** 关联风险识别 表单 */
defineOptions({ name: 'RelatedRiskIdentForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  identId: undefined,
  triggerWarnId: undefined,
  triggerWarnName: undefined,
  triggerWarnType: undefined,
  relatedRiskName: undefined,
  relatedRiskType: undefined,
  relatedBasis: undefined,
  riskLevel: undefined,
  identTime: undefined,
  identMethod: undefined,
  identifyUserId: undefined,
  identifyUserName: undefined,
  isHandled: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  identId: [{ required: true, message: '识别ID不能为空', trigger: 'blur' }],
  triggerWarnId: [{ required: true, message: '触发预警ID不能为空', trigger: 'blur' }],
  triggerWarnName: [{ required: true, message: '触发预警名称不能为空', trigger: 'blur' }],
  triggerWarnType: [{ required: true, message: '触发预警类型不能为空', trigger: 'change' }],
  relatedRiskName: [{ required: true, message: '关联风险名称不能为空', trigger: 'blur' }],
  relatedRiskType: [{ required: true, message: '关联风险类型不能为空', trigger: 'change' }],
  relatedBasis: [{ required: true, message: '关联依据不能为空', trigger: 'blur' }],
  riskLevel: [{ required: true, message: '风险等级不能为空', trigger: 'blur' }],
  identTime: [{ required: true, message: '识别时间不能为空', trigger: 'blur' }],
  identMethod: [{ required: true, message: '识别方式不能为空', trigger: 'blur' }],
  identifyUserId: [{ required: true, message: '识别用户ID不能为空', trigger: 'blur' }],
  identifyUserName: [{ required: true, message: '识别用户姓名不能为空', trigger: 'blur' }],
  isHandled: [{ required: true, message: '是否已处置不能为空', trigger: 'change' }]
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
      formData.value = await RelatedRiskIdentApi.getRelatedRiskIdent(id)
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
    const data = formData.value as unknown as RelatedRiskIdentVO
    if (formType.value === 'create') {
      await RelatedRiskIdentApi.createRelatedRiskIdent(data)
      message.success(t('common.createSuccess'))
    } else {
      await RelatedRiskIdentApi.updateRelatedRiskIdent(data)
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
    identId: undefined,
    triggerWarnId: undefined,
    triggerWarnName: undefined,
    triggerWarnType: undefined,
    relatedRiskName: undefined,
    relatedRiskType: undefined,
    relatedBasis: undefined,
    riskLevel: undefined,
    identTime: undefined,
    identMethod: undefined,
    identifyUserId: undefined,
    identifyUserName: undefined,
    isHandled: undefined,
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

::v-deep .el-form-item__label {
  font-weight: 500;
  color: #4e5969;
}

::v-deep .el-textarea__inner {
  resize: vertical;
}
</style>
