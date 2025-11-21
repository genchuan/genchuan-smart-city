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
          <el-form-item label="路径ID" prop="tracePathId">
            <el-input v-model="formData.tracePathId" placeholder="请输入路径ID" />
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
          <el-form-item label="溯源时间" prop="traceTime">
            <el-date-picker
              v-model="formData.traceTime"
              type="date"
              value-format="x"
              placeholder="选择溯源时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="是否自动分析" prop="isAutoAnalysis">
            <el-select v-model="formData.isAutoAnalysis" placeholder="请选择">
              <el-option label="是" value="1" />
              <el-option label="否" value="0" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="路径节点1（直接现象）" prop="node1Content">
        <Editor v-model="formData.node1Content" height="150px" />
      </el-form-item>

      <el-form-item label="路径节点2（直接原因）" prop="node2Content">
        <Editor v-model="formData.node2Content" height="150px" />
      </el-form-item>

      <el-form-item label="路径节点3（根本原因）" prop="node3Content">
        <Editor v-model="formData.node3Content" height="150px" />
      </el-form-item>

      <el-form-item label="溯源结论" prop="traceConclusion">
        <el-input v-model="formData.traceConclusion" placeholder="请输入溯源结论" />
      </el-form-item>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="分析人ID" prop="analystUserId">
            <el-input v-model="formData.analystUserId" placeholder="请输入分析人ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分析人姓名" prop="analystUserName">
            <el-input v-model="formData.analystUserName" placeholder="请输入分析人姓名" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-collapse v-model="activeNames" class="mt-2">
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
  RiskTracePathApi,
  RiskTracePathVO
} from '@/api/dataHub/warningAlarm/riskIdentifyTrace/risktracepath'

/** 风险溯源路径 表单 */
defineOptions({ name: 'RiskTracePathForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const activeNames = ref(['1']) // 折叠面板默认状态
const formData = ref({
  id: undefined,
  tracePathId: undefined,
  earlyWarnId: undefined,
  earlyWarnName: undefined,
  traceTime: undefined,
  node1Content: undefined,
  node2Content: undefined,
  node3Content: undefined,
  traceConclusion: undefined,
  analystUserId: undefined,
  analystUserName: undefined,
  isAutoAnalysis: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  tracePathId: [{ required: true, message: '路径ID不能为空', trigger: 'blur' }],
  earlyWarnId: [{ required: true, message: '预警ID不能为空', trigger: 'blur' }],
  earlyWarnName: [{ required: true, message: '预警名称不能为空', trigger: 'blur' }],
  traceTime: [{ required: true, message: '溯源时间不能为空', trigger: 'blur' }],
  node1Content: [{ required: true, message: '路径节点1（直接现象）不能为空', trigger: 'blur' }],
  node2Content: [{ required: true, message: '路径节点2（直接原因）不能为空', trigger: 'blur' }],
  traceConclusion: [{ required: true, message: '溯源结论不能为空', trigger: 'blur' }],
  analystUserId: [{ required: true, message: '分析人ID不能为空', trigger: 'blur' }],
  analystUserName: [{ required: true, message: '分析人姓名不能为空', trigger: 'blur' }],
  isAutoAnalysis: [{ required: true, message: '是否自动分析不能为空', trigger: 'change' }]
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
      formData.value = await RiskTracePathApi.getRiskTracePath(id)
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
    const data = formData.value as unknown as RiskTracePathVO
    if (formType.value === 'create') {
      await RiskTracePathApi.createRiskTracePath(data)
      message.success(t('common.createSuccess'))
    } else {
      await RiskTracePathApi.updateRiskTracePath(data)
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
    tracePathId: undefined,
    earlyWarnId: undefined,
    earlyWarnName: undefined,
    traceTime: undefined,
    node1Content: undefined,
    node2Content: undefined,
    node3Content: undefined,
    traceConclusion: undefined,
    analystUserId: undefined,
    analystUserName: undefined,
    isAutoAnalysis: undefined,
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
  margin-bottom: 15px;
}

::v-deep .el-collapse {
  border: none;
}

::v-deep .el-collapse-item__header {
  background-color: #f0f2f5;
  border-radius: 4px;
}
</style>
