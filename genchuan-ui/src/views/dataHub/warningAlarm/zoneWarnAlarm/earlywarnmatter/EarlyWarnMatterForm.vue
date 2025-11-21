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
      <el-form-item label="统计ID" prop="warnMngMatterStatId">
        <el-input v-model="formData.warnMngMatterStatId" placeholder="请输入统计ID" />
      </el-form-item>
      <el-form-item label="统计周期" prop="statCycle" required>
        <el-input v-model="formData.statCycle" placeholder="请输入统计周期" />
      </el-form-item>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="事项大类ID" prop="matterMajorId" required>
            <el-input v-model="formData.matterMajorId" placeholder="请输入事项大类ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="事项大类名称" prop="matterMajorName" required>
            <el-input v-model="formData.matterMajorName" placeholder="请输入事项大类名称" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="事项小类ID" prop="matterMinorId" required>
            <el-input v-model="formData.matterMinorId" placeholder="请输入事项小类ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="事项小类名称" prop="matterMinorName" required>
            <el-input v-model="formData.matterMinorName" placeholder="请输入事项小类名称" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="10">
          <el-form-item label="预警总数" prop="totalWarnCount" required>
            <el-input
              v-model.number="formData.totalWarnCount"
              placeholder="请输入预警总数"
              type="number"
            />
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="已办结数" prop="completedCount" required>
            <el-input
              v-model.number="formData.completedCount"
              placeholder="请输入已办结数"
              type="number"
            />
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="超时未处置数" prop="overtimeCount" required>
            <el-input
              v-model.number="formData.overtimeCount"
              placeholder="请输入超时未处置数"
              type="number"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="处置率(%)" prop="handleRate" required>
        <el-input v-model.number="formData.handleRate" placeholder="请输入处置率" type="number" />
      </el-form-item>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="主要处置部门代码" prop="mainDeptCode" required>
            <el-input v-model="formData.mainDeptCode" placeholder="请输入主要处置部门代码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="主要处置部门名称" prop="mainDeptName" required>
            <el-input v-model="formData.mainDeptName" placeholder="请输入主要处置部门名称" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="统计人" prop="statUserId">
        <el-input v-model="formData.statUserId" placeholder="请输入统计人" />
      </el-form-item>
      <el-form-item label="统计时间" prop="statTime">
        <el-date-picker
          v-model="formData.statTime"
          type="datetime"
          value-format="x"
          placeholder="选择统计时间"
        />
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
  EarlyWarnMatterApi,
  EarlyWarnMatterVO
} from '@/api/dataHub/warningAlarm/zoneWarnAlarm/earlywarnmatter'

/** 按管理事项预警告警统计 表单 */
defineOptions({ name: 'EarlyWarnMatterForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<EarlyWarnMatterVO>>({
  id: undefined,
  warnMngMatterStatId: undefined,
  statCycle: undefined,
  matterMajorId: undefined,
  matterMajorName: undefined,
  matterMinorId: undefined,
  matterMinorName: undefined,
  totalWarnCount: undefined,
  completedCount: undefined,
  overtimeCount: undefined,
  handleRate: undefined,
  mainDeptCode: undefined,
  mainDeptName: undefined,
  statUserId: undefined,
  statTime: undefined
})
const formRules = reactive({
  statCycle: [{ required: true, message: '请输入统计周期', trigger: 'blur' }],
  matterMajorId: [{ required: true, message: '请输入事项大类ID', trigger: 'blur' }],
  matterMajorName: [{ required: true, message: '请输入事项大类名称', trigger: 'blur' }],
  matterMinorId: [{ required: true, message: '请输入事项小类ID', trigger: 'blur' }],
  matterMinorName: [{ required: true, message: '请输入事项小类名称', trigger: 'blur' }],
  totalWarnCount: [{ required: true, message: '请输入预警总数', trigger: 'blur', type: 'number' }],
  completedCount: [{ required: true, message: '请输入已办结数', trigger: 'blur', type: 'number' }],
  overtimeCount: [
    { required: true, message: '请输入超时未处置数', trigger: 'blur', type: 'number' }
  ],
  handleRate: [{ required: true, message: '请输入处置率', trigger: 'blur', type: 'number' }],
  mainDeptCode: [{ required: true, message: '请输入主要处置部门代码', trigger: 'blur' }],
  mainDeptName: [{ required: true, message: '请输入主要处置部门名称', trigger: 'blur' }]
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
      formData.value = await EarlyWarnMatterApi.getEarlyWarnMatter(id)
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
    const data = formData.value as EarlyWarnMatterVO
    if (formType.value === 'create') {
      await EarlyWarnMatterApi.createEarlyWarnMatter(data)
      message.success(t('common.createSuccess'))
    } else {
      await EarlyWarnMatterApi.updateEarlyWarnMatter(data)
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
    warnMngMatterStatId: undefined,
    statCycle: undefined,
    matterMajorId: undefined,
    matterMajorName: undefined,
    matterMinorId: undefined,
    matterMinorName: undefined,
    totalWarnCount: undefined,
    completedCount: undefined,
    overtimeCount: undefined,
    handleRate: undefined,
    mainDeptCode: undefined,
    mainDeptName: undefined,
    statUserId: undefined,
    statTime: undefined
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
  color: #555;
  font-weight: 500;
}

::v-deep .el-input__wrapper {
  border-radius: 4px;
}
</style>
