<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="统筹ID" prop="overallId">
        <el-input v-model="formData.overallId" placeholder="请输入统筹ID" />
      </el-form-item>
      <el-form-item label="任务编号" prop="taskNo">
        <el-input v-model="formData.taskNo" placeholder="请输入任务编号" />
      </el-form-item>
      <el-form-item label="任务名称" prop="taskName">
        <el-input v-model="formData.taskName" placeholder="请输入任务名称" />
      </el-form-item>
      <el-form-item label="任务类型" prop="taskType">
        <el-select v-model="formData.taskType" placeholder="请选择任务类型">
          <el-option label="请选择字典生成" value="1" />
        </el-select>
      </el-form-item>
      <el-form-item label="关联方案ID" prop="planId">
        <el-input v-model="formData.planId" placeholder="请输入关联方案ID" />
      </el-form-item>
      <el-form-item label="关联指令ID" prop="commandId">
        <el-input v-model="formData.commandId" placeholder="请输入关联指令ID" />
      </el-form-item>
      <el-form-item label="关联跨域ID" prop="crossId">
        <el-input v-model="formData.crossId" placeholder="请输入关联跨域ID" />
      </el-form-item>
      <el-form-item label="任务状态" prop="taskStatus">
        <el-radio-group v-model="formData.taskStatus">
          <el-radio value="1">待执行</el-radio>
          <el-radio value="2">执行中</el-radio>
          <el-radio value="3">已完成</el-radio>
          <el-radio value="4">已取消</el-radio>
          <el-radio value="5">超时</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="优先级" prop="priority">
        <el-input v-model="formData.priority" placeholder="请输入优先级" />
      </el-form-item>
      <el-form-item label="任务负责人账号" prop="chargerId">
        <el-input v-model="formData.chargerId" placeholder="请输入任务负责人账号" />
      </el-form-item>
      <el-form-item label="任务负责人姓名" prop="chargerName">
        <el-input v-model="formData.chargerName" placeholder="请输入任务负责人姓名" />
      </el-form-item>
      <el-form-item label="计划完成时间" prop="planCompleteTime">
        <el-date-picker
          v-model="formData.planCompleteTime"
          type="date"
          value-format="x"
          placeholder="选择计划完成时间"
        />
      </el-form-item>
      <el-form-item label="实际完成时间" prop="actualCompleteTime">
        <el-date-picker
          v-model="formData.actualCompleteTime"
          type="date"
          value-format="x"
          placeholder="选择实际完成时间"
        />
      </el-form-item>
      <el-form-item label="延迟时长" prop="delayEndure">
        <el-input v-model="formData.delayEndure" placeholder="请输入延迟时长" />
      </el-form-item>
      <el-form-item label="分类扩展字段1" prop="extCat1">
        <el-input v-model="formData.extCat1" placeholder="请输入分类扩展字段1" />
      </el-form-item>
      <el-form-item label="分类扩展字段2" prop="extCat2">
        <el-input v-model="formData.extCat2" placeholder="请输入分类扩展字段2" />
      </el-form-item>
      <el-form-item label="通用扩展字段1" prop="extCommon1">
        <el-input v-model="formData.extCommon1" placeholder="请输入通用扩展字段1" />
      </el-form-item>
      <el-form-item label="通用扩展字段2" prop="extCommon2">
        <el-input v-model="formData.extCommon2" placeholder="请输入通用扩展字段2" />
      </el-form-item>
      <el-form-item label="系统创建时间" prop="createTimeSys">
        <el-date-picker
          v-model="formData.createTimeSys"
          type="date"
          value-format="x"
          placeholder="选择系统创建时间"
        />
      </el-form-item>
      <el-form-item label="系统更新时间" prop="updateTimeSys">
        <el-date-picker
          v-model="formData.updateTimeSys"
          type="date"
          value-format="x"
          placeholder="选择系统更新时间"
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
import { TaskOverallMngApi, TaskOverallMngVO } from '@/api/dataHub/commandAndCoordination/taskoverallmng'

/** 任务统筹管理 表单 */
defineOptions({ name: 'TaskOverallMngForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  overallId: undefined,
  taskNo: undefined,
  taskName: undefined,
  taskType: undefined,
  planId: undefined,
  commandId: undefined,
  crossId: undefined,
  taskStatus: undefined,
  priority: undefined,
  chargerId: undefined,
  chargerName: undefined,
  planCompleteTime: undefined,
  actualCompleteTime: undefined,
  delayEndure: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  overallId: [{ required: true, message: '统筹ID不能为空', trigger: 'blur' }],
  taskNo: [{ required: true, message: '任务编号不能为空', trigger: 'blur' }],
  taskName: [{ required: true, message: '任务名称不能为空', trigger: 'blur' }],
  taskType: [{ required: true, message: '任务类型不能为空', trigger: 'change' }],
  taskStatus: [{ required: true, message: '任务状态不能为空', trigger: 'blur' }],
  priority: [{ required: true, message: '优先级不能为空', trigger: 'blur' }],
  chargerId: [{ required: true, message: '任务负责人账号不能为空', trigger: 'blur' }],
  chargerName: [{ required: true, message: '任务负责人姓名不能为空', trigger: 'blur' }],
  planCompleteTime: [{ required: true, message: '计划完成时间不能为空', trigger: 'blur' }],
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
      formData.value = await TaskOverallMngApi.getTaskOverallMng(id)
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
    const data = formData.value as unknown as TaskOverallMngVO
    if (formType.value === 'create') {
      await TaskOverallMngApi.createTaskOverallMng(data)
      message.success(t('common.createSuccess'))
    } else {
      await TaskOverallMngApi.updateTaskOverallMng(data)
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
    overallId: undefined,
    taskNo: undefined,
    taskName: undefined,
    taskType: undefined,
    planId: undefined,
    commandId: undefined,
    crossId: undefined,
    taskStatus: undefined,
    priority: undefined,
    chargerId: undefined,
    chargerName: undefined,
    planCompleteTime: undefined,
    actualCompleteTime: undefined,
    delayEndure: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined,
  }
  formRef.value?.resetFields()
}
</script>
