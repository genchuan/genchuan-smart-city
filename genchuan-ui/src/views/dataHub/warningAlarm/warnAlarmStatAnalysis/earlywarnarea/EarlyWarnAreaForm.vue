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
      <!-- 栅格布局：分两列，优化空间利用 -->
      <el-row :gutter="24">
        <el-col :span="12">
          <el-form-item label="统计ID" prop="regionStatId">
            <el-input v-model="formData.regionStatId" placeholder="请输入统计ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="统计周期" prop="statCycle">
            <el-input v-model="formData.statCycle" placeholder="请输入统计周期" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="行政区划级别" prop="adminLevel">
            <el-input v-model="formData.adminLevel" placeholder="请输入行政区划级别" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="行政区划代码" prop="adminCode">
            <el-input v-model="formData.adminCode" placeholder="请输入行政区划代码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="行政区划名称" prop="adminName">
            <el-input v-model="formData.adminName" placeholder="请输入行政区划名称" />
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
          <el-form-item label="严重等级数" prop="criticalCount">
            <el-input
              v-model="formData.criticalCount"
              placeholder="请输入严重等级数"
              type="number"
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
          <el-form-item label="处置率" prop="handleRate">
            <el-input
              v-model="formData.handleRate"
              placeholder="请输入处置率"
              type="number"
              step="0.01"
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

      <!-- 扩展字段：折叠面板隐藏，按需展开 -->
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
  EarlyWarnAreaApi,
  EarlyWarnAreaVO
} from '@/api/dataHub/warningAlarm/warnAlarmStatAnalysis/earlywarnarea'

/** 预警告警区域维度统计 表单 */
defineOptions({ name: 'EarlyWarnAreaForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const activeCollapse = ref<string[]>([]) // 折叠面板状态
const formData = ref<Partial<EarlyWarnAreaVO>>({
  id: undefined,
  regionStatId: undefined,
  statCycle: undefined,
  adminLevel: undefined,
  adminCode: undefined,
  adminName: undefined,
  totalWarnCount: undefined,
  criticalCount: undefined,
  completedCount: undefined,
  handleRate: undefined,
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
  regionStatId: [{ required: true, message: '统计ID不能为空', trigger: 'blur' }],
  statCycle: [{ required: true, message: '统计周期不能为空', trigger: 'blur' }],
  adminLevel: [{ required: true, message: '行政区划级别不能为空', trigger: 'blur' }],
  adminCode: [{ required: true, message: '行政区划代码不能为空', trigger: 'blur' }],
  adminName: [{ required: true, message: '行政区划名称不能为空', trigger: 'blur' }],
  totalWarnCount: [{ required: true, message: '预警总数不能为空', trigger: 'blur' }],
  criticalCount: [{ required: true, message: '严重等级数不能为空', trigger: 'blur' }],
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
      const res = await EarlyWarnAreaApi.getEarlyWarnArea(id)
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
    const submitData = formData.value as EarlyWarnAreaVO
    if (formType.value === 'create') {
      await EarlyWarnAreaApi.createEarlyWarnArea(submitData)
      message.success(t('common.createSuccess'))
    } else {
      await EarlyWarnAreaApi.updateEarlyWarnArea(submitData)
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
    regionStatId: undefined,
    statCycle: undefined,
    adminLevel: undefined,
    adminCode: undefined,
    adminName: undefined,
    totalWarnCount: undefined,
    criticalCount: undefined,
    completedCount: undefined,
    handleRate: undefined,
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
