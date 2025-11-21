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
          <el-form-item label="计划ID" prop="dailyPlanId">
            <el-input v-model="formData.dailyPlanId" placeholder="请输入计划ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="计划名称" prop="dailyPlanName" required>
            <el-input v-model="formData.dailyPlanName" placeholder="请输入计划名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="计划编码" prop="dailyPlanCode" required>
            <el-input v-model="formData.dailyPlanCode" placeholder="请输入计划编码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属区域代码" prop="areaCode">
            <el-input v-model="formData.areaCode" placeholder="请输入所属区域代码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属区域名称" prop="areaName" required>
            <el-input v-model="formData.areaName" placeholder="请输入所属区域名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联路线ID" prop="routeId">
            <el-input v-model="formData.routeId" placeholder="请输入关联路线ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联路线名称" prop="routeName">
            <el-input v-model="formData.routeName" placeholder="请输入关联路线名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联标准ID" prop="stdId">
            <el-input v-model="formData.stdId" placeholder="请输入关联标准ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联标准名称" prop="stdName">
            <el-input v-model="formData.stdName" placeholder="请输入关联标准名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="负责人员ID" prop="personId">
            <el-input v-model="formData.personId" placeholder="请输入负责人员ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="负责人员姓名" prop="personName" required>
            <el-input v-model="formData.personName" placeholder="请输入负责人员姓名" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="计划周期" prop="planCycle" required>
            <el-input v-model="formData.planCycle" placeholder="请输入计划周期" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="执行开始时间" prop="executeStartTime" required>
            <el-date-picker
              v-model="formData.executeStartTime"
              type="datetime"
              value-format="x"
              placeholder="选择执行开始时间"
            />
            <!--            生成代码采用如下格式处理-->
            <!--            <el-date-picker-->
            <!--              v-model="formData.executeStartTime"-->
            <!--              type="date"-->
            <!--              value-format="x"-->
            <!--              placeholder="选择执行开始时间"-->
            <!--            />-->
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="执行结束时间" prop="executeEndTime" required>
            <el-date-picker
              v-model="formData.executeEndTime"
              type="datetime"
              value-format="x"
              placeholder="选择执行结束时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="开始日期" prop="startDate">
            <el-date-picker
              v-model="formData.startDate"
              type="date"
              value-format="x"
              placeholder="选择开始日期"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="结束日期" prop="endDate">
            <el-date-picker
              v-model="formData.endDate"
              type="date"
              value-format="x"
              placeholder="选择结束日期"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="计划状态" prop="planStatus">
            <el-radio-group v-model="formData.planStatus">
              <el-radio :value="true">启用</el-radio>
              <el-radio :value="false">禁用</el-radio>
            </el-radio-group>
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
  InspectDailyPlanApi,
  InspectDailyPlanVO
} from '@/api/dataHub/supervisionInspection/patrolPlan/dailyPatrolPlan'

/** 日常巡查计划信息 表单 */
defineOptions({ name: 'InspectDailyPlanForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<InspectDailyPlanVO>>({
  id: undefined,
  dailyPlanId: undefined,
  dailyPlanName: undefined,
  dailyPlanCode: undefined,
  areaCode: undefined,
  areaName: undefined,
  routeId: undefined,
  routeName: undefined,
  stdId: undefined,
  stdName: undefined,
  personId: undefined,
  personName: undefined,
  planCycle: undefined,
  executeStartTime: undefined,
  executeEndTime: undefined,
  planStatus: true,
  startDate: undefined,
  endDate: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  dailyPlanName: [{ required: true, message: '请输入计划名称', trigger: 'blur' }],
  dailyPlanCode: [{ required: true, message: '请输入计划编码', trigger: 'blur' }],
  areaName: [{ required: true, message: '请输入所属区域名称', trigger: 'blur' }],
  personName: [{ required: true, message: '请输入负责人员姓名', trigger: 'blur' }],
  planCycle: [{ required: true, message: '请输入计划周期', trigger: 'blur' }],
  executeStartTime: [{ required: true, message: '请选择执行开始时间', trigger: 'change' }],
  executeEndTime: [{ required: true, message: '请选择执行结束时间', trigger: 'change' }]
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
      const res = await InspectDailyPlanApi.getInspectDailyPlan(id)
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
    const data = formData.value as InspectDailyPlanVO
    if (formType.value === 'create') {
      await InspectDailyPlanApi.createInspectDailyPlan(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectDailyPlanApi.updateInspectDailyPlan(data)
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
    dailyPlanId: undefined,
    dailyPlanName: undefined,
    dailyPlanCode: undefined,
    areaCode: undefined,
    areaName: undefined,
    routeId: undefined,
    routeName: undefined,
    stdId: undefined,
    stdName: undefined,
    personId: undefined,
    personName: undefined,
    planCycle: undefined,
    executeStartTime: undefined,
    executeEndTime: undefined,
    planStatus: true,
    startDate: undefined,
    endDate: undefined,
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
  padding: 15px 0;
}

.el-form-item {
  margin-bottom: 15px;
}

.el-row {
  margin-bottom: 5px;
}
</style>
