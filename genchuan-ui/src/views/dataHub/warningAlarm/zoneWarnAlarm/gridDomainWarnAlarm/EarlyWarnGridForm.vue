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
      <el-form-item label="统计ID" prop="warnGridStatId" required>
        <el-input v-model="formData.warnGridStatId" placeholder="请输入统计ID" />
      </el-form-item>
      <el-form-item label="统计周期" prop="statCycle" required>
        <el-input v-model="formData.statCycle" placeholder="请输入统计周期" />
      </el-form-item>
      <el-form-item label="网格类型" prop="gridType" required>
        <el-select v-model="formData.gridType" placeholder="请选择网格类型">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="网格ID" prop="gridId" required>
        <el-input v-model="formData.gridId" placeholder="请输入网格ID" />
      </el-form-item>
      <el-form-item label="网格编码" prop="gridCode" required>
        <el-input v-model="formData.gridCode" placeholder="请输入网格编码" />
      </el-form-item>
      <el-form-item label="网格名称" prop="gridName" required>
        <el-input v-model="formData.gridName" placeholder="请输入网格名称" />
      </el-form-item>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="所属街道代码" prop="streetCode">
            <el-input v-model="formData.streetCode" placeholder="请输入街道代码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属街道名称" prop="streetName">
            <el-input v-model="formData.streetName" placeholder="请输入街道名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="10">
          <el-form-item label="预警总数" prop="totalWarnCount" required>
            <el-input v-model.number="formData.totalWarnCount" placeholder="请输入预警总数" />
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="已办结数" prop="completedCount" required>
            <el-input v-model.number="formData.completedCount" placeholder="请输入已办结数" />
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="待处置数" prop="pendCount" required>
            <el-input v-model.number="formData.pendCount" placeholder="请输入待处置数" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="主要预警类型" prop="mainWarnType">
            <el-select v-model="formData.mainWarnType" placeholder="请选择主要预警类型">
              <el-option label="请选择字典生成" value="" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="处置率(%)" prop="handleRate">
            <el-input v-model.number="formData.handleRate" placeholder="请输入处置率" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="统计人" prop="statUserId">
            <el-input v-model="formData.statUserId" placeholder="请输入统计人" />
          </el-form-item>
        </el-col>
        <el-col :span="16">
          <el-form-item label="统计时间" prop="statTime">
            <el-date-picker
              v-model="formData.statTime"
              type="datetime"
              value-format="x"
              placeholder="选择统计时间"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="分类扩展字段1" prop="extCat1">
        <el-input v-model="formData.extCat1" placeholder="请输入分类扩展字段1" />
      </el-form-item>
      <el-form-item label="分类扩展字段2" prop="extCat2">
        <el-input v-model="formData.extCat2" placeholder="请输入分类扩展字段2" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  EarlyWarnGridApi,
  EarlyWarnGridVO
} from '@/api/dataHub/warningAlarm/zoneWarnAlarm/gridDomainWarnAlarm'

/** 按网格分域预警告警统计 表单 */
defineOptions({ name: 'EarlyWarnGridForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  warnGridStatId: undefined,
  statCycle: undefined,
  gridType: undefined,
  gridId: undefined,
  gridCode: undefined,
  gridName: undefined,
  streetCode: undefined,
  streetName: undefined,
  totalWarnCount: undefined,
  completedCount: undefined,
  pendCount: undefined,
  mainWarnType: undefined,
  handleRate: undefined,
  statUserId: undefined,
  statTime: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  warnGridStatId: [{ required: true, message: '请输入统计ID', trigger: 'blur' }],
  statCycle: [{ required: true, message: '请输入统计周期', trigger: 'blur' }],
  gridType: [{ required: true, message: '请选择网格类型', trigger: 'change' }],
  gridId: [{ required: true, message: '请输入网格ID', trigger: 'blur' }],
  gridCode: [{ required: true, message: '请输入网格编码', trigger: 'blur' }],
  gridName: [{ required: true, message: '请输入网格名称', trigger: 'blur' }],
  totalWarnCount: [{ required: true, message: '请输入预警总数', trigger: 'blur', type: 'number' }],
  completedCount: [{ required: true, message: '请输入已办结数', trigger: 'blur', type: 'number' }],
  pendCount: [{ required: true, message: '请输入待处置数', trigger: 'blur', type: 'number' }]
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
      formData.value = await EarlyWarnGridApi.getEarlyWarnGrid(id)
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
    const data = formData.value as unknown as EarlyWarnGridVO
    if (formType.value === 'create') {
      await EarlyWarnGridApi.createEarlyWarnGrid(data)
      message.success(t('common.createSuccess'))
    } else {
      await EarlyWarnGridApi.updateEarlyWarnGrid(data)
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
    warnGridStatId: undefined,
    statCycle: undefined,
    gridType: undefined,
    gridId: undefined,
    gridCode: undefined,
    gridName: undefined,
    streetCode: undefined,
    streetName: undefined,
    totalWarnCount: undefined,
    completedCount: undefined,
    pendCount: undefined,
    mainWarnType: undefined,
    handleRate: undefined,
    statUserId: undefined,
    statTime: undefined,
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
}

::v-deep .el-form-item {
  margin-bottom: 18px;
}

::v-deep .el-form-item__label {
  font-weight: 500;
  color: #4e5969;
}

::v-deep .el-dialog__body {
  padding: 15px 20px;
}
</style>
