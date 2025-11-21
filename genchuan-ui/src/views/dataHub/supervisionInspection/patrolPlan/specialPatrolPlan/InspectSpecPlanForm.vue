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
      <div class="form-row">
        <el-form-item label="计划ID" prop="specPlanId" class="form-item">
          <el-input v-model="formData.specPlanId" placeholder="请输入计划ID" />
        </el-form-item>
        <el-form-item label="计划名称" prop="specPlanName" class="form-item">
          <el-input v-model="formData.specPlanName" placeholder="请输入计划名称" />
        </el-form-item>
      </div>

      <div class="form-row">
        <el-form-item label="计划编码" prop="specPlanCode" class="form-item">
          <el-input v-model="formData.specPlanCode" placeholder="请输入计划编码" />
        </el-form-item>
        <el-form-item label="专项主题" prop="specTopic" class="form-item">
          <el-input v-model="formData.specTopic" placeholder="请输入专项主题" />
        </el-form-item>
      </div>

      <div class="form-row">
        <el-form-item label="所属区域代码" prop="areaCode" class="form-item">
          <el-input v-model="formData.areaCode" placeholder="请输入所属区域代码" />
        </el-form-item>
        <el-form-item label="所属区域名称" prop="areaName" class="form-item">
          <el-input v-model="formData.areaName" placeholder="请输入所属区域名称" />
        </el-form-item>
      </div>

      <div class="form-row">
        <el-form-item label="关联标准ID" prop="stdId" class="form-item">
          <el-input v-model="formData.stdId" placeholder="请输入关联标准ID" />
        </el-form-item>
        <el-form-item label="关联标准名称" prop="stdName" class="form-item">
          <el-input v-model="formData.stdName" placeholder="请输入关联标准名称" />
        </el-form-item>
      </div>

      <div class="form-row">
        <el-form-item label="负责人员IDs" prop="personIds" class="form-item">
          <el-input v-model="formData.personIds" placeholder="请输入负责人员IDs" />
        </el-form-item>
        <el-form-item label="负责人员Names" prop="personNames" class="form-item">
          <el-input v-model="formData.personNames" placeholder="请输入负责人员Names" />
        </el-form-item>
      </div>

      <div class="form-row">
        <el-form-item label="执行开始日期" prop="executeStartDate" class="form-item">
          <el-date-picker
            v-model="formData.executeStartDate"
            type="date"
            value-format="x"
            placeholder="选择执行开始日期"
          />
        </el-form-item>
        <el-form-item label="执行结束日期" prop="executeEndDate" class="form-item">
          <el-date-picker
            v-model="formData.executeEndDate"
            type="date"
            value-format="x"
            placeholder="选择执行结束日期"
          />
        </el-form-item>
      </div>

      <div class="form-row">
        <el-form-item label="每日执行时间" prop="dailyExecuteTime" class="form-item">
          <el-date-picker
            v-model="formData.dailyExecuteTime"
            type="date"
            value-format="x"
            placeholder="选择每日执行时间"
          />
        </el-form-item>
        <el-form-item label="计划状态" prop="planStatus" class="form-item">
          <el-radio-group v-model="formData.planStatus">
            <el-radio :value="true">启用</el-radio>
            <el-radio :value="false">禁用</el-radio>
          </el-radio-group>
        </el-form-item>
      </div>

      <div class="form-row">
        <el-form-item label="分类扩展字段1" prop="extCat1" class="form-item">
          <el-input v-model="formData.extCat1" placeholder="请输入分类扩展字段1" />
        </el-form-item>
        <el-form-item label="分类扩展字段2" prop="extCat2" class="form-item">
          <el-input v-model="formData.extCat2" placeholder="请输入分类扩展字段2" />
        </el-form-item>
      </div>

      <div class="form-row">
        <el-form-item label="通用扩展字段1" prop="extCommon1" class="form-item">
          <el-input v-model="formData.extCommon1" placeholder="请输入通用扩展字段1" />
        </el-form-item>
        <el-form-item label="通用扩展字段2" prop="extCommon2" class="form-item">
          <el-input v-model="formData.extCommon2" placeholder="请输入通用扩展字段2" />
        </el-form-item>
      </div>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  InspectSpecPlanApi,
  InspectSpecPlanVO
} from '@/api/dataHub/supervisionInspection/patrolPlan/specialPatrolPlan'

/** 专项巡查计划信息 表单 */
defineOptions({ name: 'InspectSpecPlanForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  specPlanId: undefined,
  specPlanName: undefined,
  specPlanCode: undefined,
  specTopic: undefined,
  areaCode: undefined,
  areaName: undefined,
  stdId: undefined,
  stdName: undefined,
  personIds: undefined,
  personNames: undefined,
  executeStartDate: undefined,
  executeEndDate: undefined,
  dailyExecuteTime: undefined,
  planStatus: 1, // 默认启用
  createUser: undefined,
  bizCreateTime: undefined,
  updateUser: undefined,
  bizUpdateTime: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  specPlanName: [{ required: true, message: '请输入计划名称', trigger: 'blur' }],
  specPlanCode: [{ required: true, message: '请输入计划编码', trigger: 'blur' }],
  executeStartDate: [{ required: true, message: '请选择执行开始日期', trigger: 'change' }],
  executeEndDate: [{ required: true, message: '请选择执行结束日期', trigger: 'change' }]
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
      formData.value = await InspectSpecPlanApi.getInspectSpecPlan(id)
    } finally {
      formLoading.value = false
    }
  }
}
defineExpose({ open }) // 提供 open 方法，用于打开弹窗

/** 提交表单 */
const emit = defineEmits(['success']) // 定义 success 事件
const submitForm = async () => {
  // 校验表单
  await formRef.value.validate()
  // 提交请求
  formLoading.value = true
  try {
    const data = formData.value as unknown as InspectSpecPlanVO
    if (formType.value === 'create') {
      await InspectSpecPlanApi.createInspectSpecPlan(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectSpecPlanApi.updateInspectSpecPlan(data)
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
    specPlanId: undefined,
    specPlanName: undefined,
    specPlanCode: undefined,
    specTopic: undefined,
    areaCode: undefined,
    areaName: undefined,
    stdId: undefined,
    stdName: undefined,
    personIds: undefined,
    personNames: undefined,
    executeStartDate: undefined,
    executeEndDate: undefined,
    dailyExecuteTime: undefined,
    planStatus: 1, // 默认启用
    createUser: undefined,
    bizCreateTime: undefined,
    updateUser: undefined,
    bizUpdateTime: undefined,
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

.form-row {
  display: flex;
  flex-wrap: wrap;
  margin-bottom: 15px;
}

.form-item {
  flex: 1;
  min-width: 280px;
  margin-right: 15px;
}

.form-item:last-child {
  margin-right: 0;
}

.el-form-item__label {
  font-weight: 500;
}

.el-input,
.el-date-picker,
.el-select {
  width: 100%;
}
</style>
