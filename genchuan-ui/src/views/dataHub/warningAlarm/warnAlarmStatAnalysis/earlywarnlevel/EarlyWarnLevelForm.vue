<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="700px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="130px"
      v-loading="formLoading"
      class="form-container"
    >
      <!-- 栅格布局：分两列优化布局 -->
      <el-row :gutter="24">
        <el-col :span="12">
          <el-form-item label="统计ID" prop="levelStatId">
            <el-input v-model="formData.levelStatId" placeholder="请输入统计ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="统计周期" prop="statCycle">
            <el-input v-model="formData.statCycle" placeholder="请输入统计周期" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预警等级" prop="warnLevel">
            <el-input v-model="formData.warnLevel" placeholder="请输入预警等级" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预警等级编码" prop="warnLevelCode">
            <el-input v-model="formData.warnLevelCode" placeholder="请输入预警等级编码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预警总数" prop="totalWarnCount">
            <el-input
              v-model="formData.totalWarnCount"
              placeholder="请输入预警总数"
              type="number"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="占比(%)" prop="proportion">
            <el-input
              v-model="formData.proportion"
              placeholder="请输入占比"
              type="number"
              step="0.01"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="已办结数" prop="completedCount">
            <el-input
              v-model="formData.completedCount"
              placeholder="请输入已办结数"
              type="number"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="处置率(%)" prop="handleRate">
            <el-input
              v-model="formData.handleRate"
              placeholder="请输入处置率"
              type="number"
              step="0.01"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="平均处置时长" prop="avgHandleEndure">
            <el-input
              v-model="formData.avgHandleEndure"
              placeholder="请输入平均处置时长"
              type="number"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="主要预警类型" prop="mainWarnType">
            <el-select v-model="formData.mainWarnType" placeholder="请选择主要预警类型">
              <el-option label="请选择字典生成" value="" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="统计人" prop="statUserId">
            <el-input v-model="formData.statUserId" placeholder="请输入统计人" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="统计时间" prop="statTime">
            <el-date-picker
              v-model="formData.statTime"
              type="datetime"
              value-format="x"
              placeholder="选择统计时间"
              class="!w-full"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 扩展字段：折叠面板隐藏 -->
      <el-collapse v-model="activeCollapse" class="mt-4">
        <el-collapse-item name="extFields" title="扩展字段（可选）">
          <el-row :gutter="24">
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
  EarlyWarnLevelApi,
  EarlyWarnLevelVO
} from '@/api/dataHub/warningAlarm/warnAlarmStatAnalysis/earlywarnlevel'

/** 预警告警等级维度统计 表单 */
defineOptions({ name: 'EarlyWarnLevelForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const activeCollapse = ref<string[]>([]) // 折叠面板状态
const formData = ref<Partial<EarlyWarnLevelVO>>({
  id: undefined,
  levelStatId: undefined,
  statCycle: undefined,
  warnLevel: undefined,
  warnLevelCode: undefined,
  totalWarnCount: undefined,
  proportion: undefined,
  completedCount: undefined,
  handleRate: undefined,
  avgHandleEndure: undefined,
  mainWarnType: undefined,
  statUserId: undefined,
  statTime: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})

// 表单校验规则
const formRules = reactive({
  levelStatId: [{ required: true, message: '统计ID不能为空', trigger: 'blur' }],
  statCycle: [{ required: true, message: '统计周期不能为空', trigger: 'blur' }],
  warnLevel: [{ required: true, message: '预警等级不能为空', trigger: 'blur' }],
  warnLevelCode: [{ required: true, message: '预警等级编码不能为空', trigger: 'blur' }],
  totalWarnCount: [{ required: true, message: '预警总数不能为空', trigger: 'blur' }],
  completedCount: [{ required: true, message: '已办结数不能为空', trigger: 'blur' }],
  handleRate: [{ required: true, message: '处置率不能为空', trigger: 'blur' }],
  statUserId: [{ required: true, message: '统计人不能为空', trigger: 'blur' }],
  statTime: [{ required: true, message: '统计时间不能为空', trigger: 'blur' }]
})

const formRef = ref() // 表单 Ref

/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = t('action.' + type)
  formType.value = type
  resetForm()

  // 修改时加载数据
  if (id) {
    formLoading.value = true
    try {
      const res = await EarlyWarnLevelApi.getEarlyWarnLevel(id)
      formData.value = { ...res }
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
    const submitData = formData.value as EarlyWarnLevelVO
    if (formType.value === 'create') {
      await EarlyWarnLevelApi.createEarlyWarnLevel(submitData)
      message.success(t('common.createSuccess'))
    } else {
      await EarlyWarnLevelApi.updateEarlyWarnLevel(submitData)
      message.success(t('common.updateSuccess'))
    }
    dialogVisible.value = false
    emit('success') // 通知父组件刷新列表
  } finally {
    formLoading.value = false
  }
}

/** 重置表单 */
const resetForm = () => {
  formData.value = {
    id: undefined,
    levelStatId: undefined,
    statCycle: undefined,
    warnLevel: undefined,
    warnLevelCode: undefined,
    totalWarnCount: undefined,
    proportion: undefined,
    completedCount: undefined,
    handleRate: undefined,
    avgHandleEndure: undefined,
    mainWarnType: undefined,
    statUserId: undefined,
    statTime: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined
  }
  formRef.value?.resetFields()
  activeCollapse.value = []
}
</script>

<style scoped>
.form-container {
  padding: 8px 0;
}

::v-deep .el-form-item {
  margin-bottom: 16px;
}

::v-deep .el-collapse {
  border: 1px solid #f0f2f5;
  border-radius: 6px;
  overflow: hidden;
}

::v-deep .el-collapse-item__header {
  background-color: #f9fafb;
}
</style>
