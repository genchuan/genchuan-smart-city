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
          <el-form-item label="统计ID" prop="problemStatId" required>
            <el-input v-model="formData.problemStatId" placeholder="请输入统计ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="统计周期" prop="statCycle" required>
            <el-input v-model="formData.statCycle" placeholder="请输入统计周期" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="周期名称" prop="statCycleName" required>
            <el-input v-model="formData.statCycleName" placeholder="请输入统计周期名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属区域代码" prop="areaCode" required>
            <el-input v-model="formData.areaCode" placeholder="请输入所属区域代码" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="所属区域名称" prop="areaName" required>
            <el-input v-model="formData.areaName" placeholder="请输入所属区域名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="问题类型ID" prop="problemTypeId" required>
            <el-input v-model="formData.problemTypeId" placeholder="请输入问题类型ID" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="问题类型名称" prop="problemTypeName" required>
            <el-input v-model="formData.problemTypeName" placeholder="请输入问题类型名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="问题集中区域" prop="concentratedArea">
            <el-input v-model="formData.concentratedArea" placeholder="请输入问题集中区域" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="8">
          <el-form-item label="上报问题数" prop="rptProblemCount" required>
            <el-input v-model.number="formData.rptProblemCount" placeholder="请输入上报问题数" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="一级问题数" prop="level1ProblemCount" required>
            <el-input v-model.number="formData.level1ProblemCount" placeholder="请输入一级问题数" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="整改完成数" prop="rectifyCompleteCount" required>
            <el-input
              v-model.number="formData.rectifyCompleteCount"
              placeholder="请输入整改完成数"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="整改率(%)" prop="rectifyRate" required>
            <el-input v-model.number="formData.rectifyRate" placeholder="请输入整改率" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="统计人" prop="statUser" required>
            <el-input v-model="formData.statUser" placeholder="请输入统计人" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="统计时间" prop="statTime" required>
            <el-date-picker
              v-model="formData.statTime"
              type="datetime"
              value-format="x"
              placeholder="选择统计时间"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="24">
          <el-form-item label="未整改原因" prop="unrectifiedReason">
            <el-input
              v-model="formData.unrectifiedReason"
              placeholder="请输入未整改原因"
              type="textarea"
              rows="3"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20" class="mt-4">
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
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  InspectProblemRptApi,
  InspectProblemRptVO
} from '@/api/dataHub/supervisionInspection/patrolStatisticalReport/issueStatisticalReport'

/** 巡查巡检问题统计 表单 */
defineOptions({ name: 'InspectProblemRptForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<InspectProblemRptVO>>({
  id: undefined,
  problemStatId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  areaCode: undefined,
  areaName: undefined,
  problemTypeId: undefined,
  problemTypeName: undefined,
  rptProblemCount: 0,
  level1ProblemCount: 0,
  rectifyCompleteCount: 0,
  rectifyRate: 0,
  unrectifiedReason: undefined,
  concentratedArea: undefined,
  statUser: undefined,
  statTime: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  problemStatId: [{ required: true, message: '统计ID不能为空', trigger: 'blur' }],
  statCycle: [{ required: true, message: '统计周期不能为空', trigger: 'blur' }],
  statCycleName: [{ required: true, message: '统计周期名称不能为空', trigger: 'blur' }],
  areaCode: [{ required: true, message: '所属区域代码不能为空', trigger: 'blur' }],
  areaName: [{ required: true, message: '所属区域名称不能为空', trigger: 'blur' }],
  problemTypeId: [{ required: true, message: '问题类型ID不能为空', trigger: 'blur' }],
  problemTypeName: [{ required: true, message: '问题类型名称不能为空', trigger: 'blur' }],
  rptProblemCount: [{ required: true, message: '上报问题数不能为空', trigger: 'blur' }],
  level1ProblemCount: [{ required: true, message: '一级问题数不能为空', trigger: 'blur' }],
  rectifyCompleteCount: [{ required: true, message: '整改完成数不能为空', trigger: 'blur' }],
  rectifyRate: [{ required: true, message: '整改率不能为空', trigger: 'blur' }],
  statUser: [{ required: true, message: '统计人不能为空', trigger: 'blur' }],
  statTime: [{ required: true, message: '统计时间不能为空', trigger: 'change' }]
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
      const data = await InspectProblemRptApi.getInspectProblemRpt(id)
      formData.value = {
        ...data,
        // 转换时间为时间戳
        statTime: data.statTime ? new Date(data.statTime).getTime() : undefined
      }
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
    const data = {
      ...formData.value,
      // 转换时间戳为Date对象
      statTime: formData.value.statTime ? new Date(formData.value.statTime) : undefined
    } as InspectProblemRptVO

    if (formType.value === 'create') {
      await InspectProblemRptApi.createInspectProblemRpt(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectProblemRptApi.updateInspectProblemRpt(data)
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
    problemStatId: undefined,
    statCycle: undefined,
    statCycleName: undefined,
    areaCode: undefined,
    areaName: undefined,
    problemTypeId: undefined,
    problemTypeName: undefined,
    rptProblemCount: 0,
    level1ProblemCount: 0,
    rectifyCompleteCount: 0,
    rectifyRate: 0,
    unrectifiedReason: undefined,
    concentratedArea: undefined,
    statUser: undefined,
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
  margin-bottom: 15px;
}

::v-deep .el-form-item__label {
  font-weight: 500;
  color: #4e5969;
}

::v-deep .el-row {
  margin-bottom: 5px;
}
</style>
