<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="600px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
      class="statistics-form"
    >
      <el-form-item label="考核日期" prop="inspectionDate">
        <el-date-picker
          v-model="formData.inspectionDate"
          type="date"
          value-format="x"
          placeholder="选择考核日期"
          class="full-width"
        />
      </el-form-item>
      <el-form-item label="场所类型" prop="areaType">
        <el-input v-model="formData.areaType" placeholder="请输入场所类型" class="full-width" />
      </el-form-item>
      <el-form-item label="区域/地点名称" prop="areaName">
        <el-input
          v-model="formData.areaName"
          placeholder="请输入区域/地点名称"
          class="full-width"
        />
      </el-form-item>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="该场所总得分" prop="totalScore">
            <el-input
              v-model="formData.totalScore"
              placeholder="请输入该场所总得分"
              class="full-width"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="该类型满分" prop="maxScore">
            <el-input
              v-model="formData.maxScore"
              placeholder="请输入该类型满分"
              class="full-width"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="权重" prop="weight">
            <el-input v-model="formData.weight" placeholder="请输入权重" class="full-width" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="最终得分" prop="scoreWeighted">
            <el-input
              v-model="formData.scoreWeighted"
              placeholder="请输入最终得分"
              class="full-width"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="样本数" prop="sampleCount">
        <el-input v-model="formData.sampleCount" placeholder="请输入样本数" class="full-width" />
      </el-form-item>
      <el-form-item label="考核状态" prop="inspectionStatus">
        <el-select
          v-model="formData.inspectionStatus"
          placeholder="请选择考核状态"
          class="full-width"
        >
          <el-option label="未开始" value="未开始" />
          <el-option label="进行中" value="进行中" />
          <el-option label="已完成" value="已完成" />
        </el-select>
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
  InspectionStatisticsApi,
  InspectionStatisticsVO
} from '@/api/dataHub/comprehensiveEval/inspectionstatistics'

/** 环卫考核统计结果 表单 */
defineOptions({ name: 'InspectionStatisticsForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  inspectionDate: undefined,
  areaType: undefined,
  areaName: undefined,
  totalScore: undefined,
  maxScore: undefined,
  weight: undefined,
  scoreWeighted: undefined,
  sampleCount: undefined,
  inspectionStatus: undefined
})
const formRules = reactive({
  inspectionDate: [{ required: true, message: '考核日期不能为空', trigger: 'blur' }],
  areaType: [{ required: true, message: '场所类型不能为空', trigger: 'blur' }],
  areaName: [{ required: true, message: '区域/地点名称不能为空', trigger: 'blur' }],
  totalScore: [{ required: true, message: '该场所总得分不能为空', trigger: 'blur' }],
  maxScore: [{ required: true, message: '该类型满分不能为空', trigger: 'blur' }],
  weight: [{ required: true, message: '权重不能为空', trigger: 'blur' }],
  scoreWeighted: [{ required: true, message: '最终得分不能为空', trigger: 'blur' }],
  sampleCount: [{ required: true, message: '样本数不能为空', trigger: 'blur' }],
  inspectionStatus: [{ required: true, message: '考核状态不能为空', trigger: 'blur' }]
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
      formData.value = await InspectionStatisticsApi.getInspectionStatistics(id)
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
    const data = formData.value as unknown as InspectionStatisticsVO
    if (formType.value === 'create') {
      await InspectionStatisticsApi.createInspectionStatistics(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectionStatisticsApi.updateInspectionStatistics(data)
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
    inspectionDate: undefined,
    areaType: undefined,
    areaName: undefined,
    totalScore: undefined,
    maxScore: undefined,
    weight: undefined,
    scoreWeighted: undefined,
    sampleCount: undefined,
    inspectionStatus: undefined
  }
  formRef.value?.resetFields()
}
</script>

<style scoped>
.statistics-form {
  padding: 15px;
}

.full-width {
  width: 100%;
}

::v-deep .el-form-item {
  margin-bottom: 18px;
}
</style>
