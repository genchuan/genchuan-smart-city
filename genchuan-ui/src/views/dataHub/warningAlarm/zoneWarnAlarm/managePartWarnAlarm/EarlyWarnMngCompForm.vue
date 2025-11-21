<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="600px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="130px"
      v-loading="formLoading"
      class="form-container"
    >
      <el-form-item label="统计ID" prop="warnMngCompStatId">
        <el-input v-model="formData.warnMngCompStatId" placeholder="请输入统计ID" />
      </el-form-item>
      <el-form-item label="统计周期" prop="statCycle">
        <el-input v-model="formData.statCycle" placeholder="请输入统计周期" />
      </el-form-item>
      <el-form-item label="部件大类ID" prop="compMajorId">
        <el-input v-model="formData.compMajorId" placeholder="请输入部件大类ID" />
      </el-form-item>
      <el-form-item label="部件大类名称" prop="compMajorName">
        <el-input v-model="formData.compMajorName" placeholder="请输入部件大类名称" />
      </el-form-item>
      <el-form-item label="部件小类ID" prop="compMinorId">
        <el-input v-model="formData.compMinorId" placeholder="请输入部件小类ID" />
      </el-form-item>
      <el-form-item label="部件小类名称" prop="compMinorName">
        <el-input v-model="formData.compMinorName" placeholder="请输入部件小类名称" />
      </el-form-item>
      <el-form-item label="预警总数" prop="totalWarnCount">
        <el-input v-model.number="formData.totalWarnCount" placeholder="请输入预警总数" />
      </el-form-item>
      <el-form-item label="主要故障类型" prop="mainFaultType">
        <el-select v-model="formData.mainFaultType" placeholder="请选择主要故障类型">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="主要分布区域代码" prop="mainRegionCode">
        <el-input v-model="formData.mainRegionCode" placeholder="请输入主要分布区域代码" />
      </el-form-item>
      <el-form-item label="主要分布区域名称" prop="mainRegionName">
        <el-input v-model="formData.mainRegionName" placeholder="请输入主要分布区域名称" />
      </el-form-item>
      <el-form-item label="已办结数" prop="completedCount">
        <el-input v-model.number="formData.completedCount" placeholder="请输入已办结数" />
      </el-form-item>
      <el-form-item label="处置率" prop="handleRate">
        <el-input v-model.number="formData.handleRate" placeholder="请输入处置率" />
      </el-form-item>
      <el-form-item label="统计人" prop="statUserId">
        <el-input v-model="formData.statUserId" placeholder="请输入统计人" />
      </el-form-item>
      <el-form-item label="统计时间" prop="statTime">
        <el-date-picker
          v-model="formData.statTime"
          type="date"
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
  EarlyWarnMngCompApi,
  EarlyWarnMngCompVO
} from '@/api/dataHub/warningAlarm/zoneWarnAlarm/managePartWarnAlarm'

/** 按管理部件预警告警统计 表单 */
defineOptions({ name: 'EarlyWarnMngCompForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<EarlyWarnMngCompVO>>({
  id: undefined,
  warnMngCompStatId: undefined,
  statCycle: undefined,
  compMajorId: undefined,
  compMajorName: undefined,
  compMinorId: undefined,
  compMinorName: undefined,
  totalWarnCount: undefined,
  mainFaultType: undefined,
  mainRegionCode: undefined,
  mainRegionName: undefined,
  completedCount: undefined,
  handleRate: undefined,
  statUserId: undefined,
  statTime: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({})
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
      formData.value = await EarlyWarnMngCompApi.getEarlyWarnMngComp(id)
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
    const data = formData.value as unknown as EarlyWarnMngCompVO
    if (formType.value === 'create') {
      await EarlyWarnMngCompApi.createEarlyWarnMngComp(data)
      message.success(t('common.createSuccess'))
    } else {
      await EarlyWarnMngCompApi.updateEarlyWarnMngComp(data)
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
    warnMngCompStatId: undefined,
    statCycle: undefined,
    compMajorId: undefined,
    compMajorName: undefined,
    compMinorId: undefined,
    compMinorName: undefined,
    totalWarnCount: undefined,
    mainFaultType: undefined,
    mainRegionCode: undefined,
    mainRegionName: undefined,
    completedCount: undefined,
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
  background-color: #fff;
  border-radius: 8px;
}

::v-deep .el-form-item {
  margin-bottom: 18px;
}

::v-deep .el-form-item__label {
  font-weight: 500;
  color: #4e5969;
}

::v-deep .el-input__wrapper {
  border-radius: 6px;
}

::v-deep .el-dialog__header {
  background-color: #f9fafb;
  padding: 15px 20px;
  border-bottom: 1px solid #eee;
}

::v-deep .el-dialog__title {
  font-size: 16px;
  font-weight: 500;
}

::v-deep .el-dialog__footer {
  padding: 15px 20px;
  border-top: 1px solid #eee;
}
</style>
