<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="700px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
      class="custom-form"
    >
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="计划名称" prop="emerPlanName" required>
            <el-input v-model="formData.emerPlanName" placeholder="请输入计划名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="计划编码" prop="emerPlanCode" required>
            <el-input v-model="formData.emerPlanCode" placeholder="请输入计划编码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="应急主题" prop="emerTopic" required>
            <el-input v-model="formData.emerTopic" placeholder="请输入应急主题" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联应急事件名称" prop="emerEvtName">
            <el-input v-model="formData.emerEvtName" placeholder="请输入关联应急事件名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属区域名称" prop="areaName">
            <el-input v-model="formData.areaName" placeholder="请输入所属区域名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联标准名称" prop="stdName">
            <el-input v-model="formData.stdName" placeholder="请输入关联标准名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="负责人员Names" prop="personNames">
            <el-input v-model="formData.personNames" placeholder="请输入负责人员Names" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="执行时间" prop="executeTime" required>
            <el-date-picker
              v-model="formData.executeTime"
              type="date"
              value-format="x"
              placeholder="选择执行时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="执行截止时间" prop="executeDeadline" required>
            <el-date-picker
              v-model="formData.executeDeadline"
              type="date"
              value-format="x"
              placeholder="选择执行截止时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="计划状态" prop="planStatus" required>
            <el-radio-group v-model="formData.planStatus">
              <el-radio :value="true">启用</el-radio>
              <el-radio :value="false">禁用</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="扩展信息">
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item prop="extCat1">
                  <el-input v-model="formData.extCat1" placeholder="分类扩展字段1" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item prop="extCat2">
                  <el-input v-model="formData.extCat2" placeholder="分类扩展字段2" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item prop="extCommon1">
                  <el-input v-model="formData.extCommon1" placeholder="通用扩展字段1" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item prop="extCommon2">
                  <el-input v-model="formData.extCommon2" placeholder="通用扩展字段2" />
                </el-form-item>
              </el-col>
            </el-row>
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
  InspectEmerPlanApi,
  InspectEmerPlanVO
} from '@/api/dataHub/supervisionInspection/patrolPlan/emergencyPatrolPlan'

/** 应急巡查计划信息 表单 */
defineOptions({ name: 'InspectEmerPlanForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<InspectEmerPlanVO>>({
  id: undefined,
  emerPlanId: undefined,
  emerPlanName: undefined,
  emerPlanCode: undefined,
  emerTopic: undefined,
  emerEvtId: undefined,
  emerEvtName: undefined,
  areaCode: undefined,
  areaName: undefined,
  stdId: undefined,
  stdName: undefined,
  personIds: undefined,
  personNames: undefined,
  executeTime: undefined,
  executeDeadline: undefined,
  planStatus: '1',
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
  emerPlanName: [{ required: true, message: '请输入计划名称', trigger: 'blur' }],
  emerPlanCode: [{ required: true, message: '请输入计划编码', trigger: 'blur' }],
  emerTopic: [{ required: true, message: '请输入应急主题', trigger: 'blur' }],
  executeTime: [{ required: true, message: '请选择执行时间', trigger: 'change' }],
  executeDeadline: [{ required: true, message: '请选择执行截止时间', trigger: 'change' }],
  planStatus: [{ required: true, message: '请选择计划状态', trigger: 'change' }]
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
      formData.value = await InspectEmerPlanApi.getInspectEmerPlan(id)
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
    const data = formData.value as unknown as InspectEmerPlanVO
    if (formType.value === 'create') {
      await InspectEmerPlanApi.createInspectEmerPlan(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectEmerPlanApi.updateInspectEmerPlan(data)
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
    emerPlanId: undefined,
    emerPlanName: undefined,
    emerPlanCode: undefined,
    emerTopic: undefined,
    emerEvtId: undefined,
    emerEvtName: undefined,
    areaCode: undefined,
    areaName: undefined,
    stdId: undefined,
    stdName: undefined,
    personIds: undefined,
    personNames: undefined,
    executeTime: undefined,
    executeDeadline: undefined,
    planStatus: '1',
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
.custom-form {
  padding: 15px;
}

::v-deep .el-form-item__label {
  font-weight: 500;
}

::v-deep .el-row {
  margin-bottom: 10px;
}

::v-deep .el-col:last-child .el-form-item {
  margin-bottom: 0;
}
</style>
