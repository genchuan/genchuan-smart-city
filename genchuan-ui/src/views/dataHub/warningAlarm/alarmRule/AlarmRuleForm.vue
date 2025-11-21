<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="700px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="150px"
      v-loading="formLoading"
      class="form-container"
    >
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="规则名称" prop="ruleName">
            <el-input v-model="formData.ruleName" placeholder="请输入规则名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联对象类型" prop="relatedObjectType">
            <el-select v-model="formData.relatedObjectType" placeholder="请选择关联对象类型">
              <el-option label="请选择字典生成" value="" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="关联对象范围" prop="relatedObjectScope">
            <el-input v-model="formData.relatedObjectScope" placeholder="请输入关联对象范围" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联对象分类ID" prop="relatedObjectCategoryId">
            <el-input
              v-model="formData.relatedObjectCategoryId"
              placeholder="请输入关联对象分类ID"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="24">
          <el-form-item label="关联对象ID列表" prop="relatedObjectIdList">
            <el-input
              v-model="formData.relatedObjectIdList"
              placeholder="请输入关联对象ID列表，多个用逗号分隔"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="24">
          <el-form-item label="预警触发条件" prop="triggerCondition">
            <el-input v-model="formData.triggerCondition" placeholder="请输入预警触发条件" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="预警指标编码" prop="warningIndicatorCode">
            <el-input v-model="formData.warningIndicatorCode" placeholder="请输入预警指标编码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预警等级(1-5)" prop="earlyWarningLevel">
            <el-input
              v-model.number="formData.earlyWarningLevel"
              placeholder="请输入预警等级(1-5)"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="预警阈值上限" prop="warningThresholdUpper">
            <el-input
              v-model.number="formData.warningThresholdUpper"
              placeholder="请输入预警阈值上限"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预警阈值下限" prop="warningThresholdLower">
            <el-input
              v-model.number="formData.warningThresholdLower"
              placeholder="请输入预警阈值下限"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="触发时长(分钟)" prop="triggerDuration">
            <el-input
              v-model.number="formData.triggerDuration"
              placeholder="请输入触发时长(分钟)"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="状态判定值" prop="statusDetermineValue">
            <el-input v-model="formData.statusDetermineValue" placeholder="请输入状态判定值" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="触发频率(次/小时)" prop="triggerFrequency">
            <el-input
              v-model.number="formData.triggerFrequency"
              placeholder="请输入触发频率(次/小时)"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="重复触发间隔(分钟)" prop="repeatTriggerInterval">
            <el-input
              v-model.number="formData.repeatTriggerInterval"
              placeholder="请输入重复触发间隔(分钟)"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="启用状态" prop="enableStatus">
            <el-radio-group v-model="formData.enableStatus">
              <el-radio :label="1">启用</el-radio>
              <el-radio :label="0">禁用</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="24">
          <el-form-item label="规则描述" prop="ruleDescription">
            <el-input
              v-model="formData.ruleDescription"
              type="textarea"
              rows="4"
              placeholder="请输入规则描述"
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
import { AlarmRuleApi, AlarmRuleVO } from '@/api/dataHub/warningAlarm/warnAlarmRuleMgr'

/** 预警告警规则配置 表单 */
defineOptions({ name: 'AlarmRuleForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<AlarmRuleVO>>({
  id: undefined,
  ruleName: undefined,
  relatedObjectType: undefined,
  relatedObjectScope: undefined,
  relatedObjectCategoryId: undefined,
  relatedObjectIdList: undefined,
  triggerCondition: undefined,
  warningIndicatorCode: undefined,
  warningThresholdUpper: undefined,
  warningThresholdLower: undefined,
  statusDetermineValue: undefined,
  triggerDuration: undefined,
  earlyWarningLevel: undefined,
  triggerFrequency: undefined,
  repeatTriggerInterval: undefined,
  enableStatus: 1, // 默认启用
  ruleDescription: undefined
})
const formRules = reactive({
  ruleName: [{ required: true, message: '规则名称不能为空', trigger: 'blur' }],
  earlyWarningLevel: [
    { required: true, message: '预警等级不能为空', trigger: 'blur' },
    { type: 'number', min: 1, max: 5, message: '预警等级必须在1-5之间', trigger: 'blur' }
  ],
  enableStatus: [{ required: true, message: '请选择启用状态', trigger: 'change' }]
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
      formData.value = await AlarmRuleApi.getAlarmRule(id)
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
    const data = formData.value as unknown as AlarmRuleVO
    if (formType.value === 'create') {
      await AlarmRuleApi.createAlarmRule(data)
      message.success(t('common.createSuccess'))
    } else {
      await AlarmRuleApi.updateAlarmRule(data)
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
    ruleName: undefined,
    relatedObjectType: undefined,
    relatedObjectScope: undefined,
    relatedObjectCategoryId: undefined,
    relatedObjectIdList: undefined,
    triggerCondition: undefined,
    warningIndicatorCode: undefined,
    warningThresholdUpper: undefined,
    warningThresholdLower: undefined,
    statusDetermineValue: undefined,
    triggerDuration: undefined,
    earlyWarningLevel: undefined,
    triggerFrequency: undefined,
    repeatTriggerInterval: undefined,
    enableStatus: 1,
    ruleDescription: undefined
  }
  formRef.value?.resetFields()
}
</script>

<style scoped>
.form-container {
  padding: 15px;
}

::v-deep .el-form-item {
  margin-bottom: 15px;
}

::v-deep .el-form-item__label {
  font-weight: 500;
}

::v-deep .el-dialog__body {
  padding: 15px 20px;
  max-height: 70vh;
  overflow-y: auto;
}
</style>
