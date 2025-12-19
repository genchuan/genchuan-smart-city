<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="调度ID" prop="resScheduleId">
        <el-input v-model="formData.resScheduleId" placeholder="请输入调度ID" />
      </el-form-item>
      <el-form-item label="关联方案ID" prop="relSchemeId">
        <el-input v-model="formData.relSchemeId" placeholder="请输入关联方案ID" />
      </el-form-item>
      <el-form-item label="关联方案名称" prop="relSchemeName">
        <el-input v-model="formData.relSchemeName" placeholder="请输入关联方案名称" />
      </el-form-item>
      <el-form-item label="资源需求明细" prop="resDemandDetail">
        <el-input v-model="formData.resDemandDetail" placeholder="请输入资源需求明细" />
      </el-form-item>
      <el-form-item label="匹配资源明细" prop="matchedResDetail">
        <el-input v-model="formData.matchedResDetail" placeholder="请输入匹配资源明细" />
      </el-form-item>
      <el-form-item label="调度状态" prop="scheduleStatus">
        <el-radio-group v-model="formData.scheduleStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="匹配失败原因" prop="matchFailReason">
        <el-input v-model="formData.matchFailReason" placeholder="请输入匹配失败原因" />
      </el-form-item>
      <el-form-item label="调度工单ID" prop="scheduleWoId">
        <el-input v-model="formData.scheduleWoId" placeholder="请输入调度工单ID" />
      </el-form-item>
      <el-form-item label="调度人" prop="scheduleUser">
        <el-input v-model="formData.scheduleUser" placeholder="请输入调度人" />
      </el-form-item>
      <el-form-item label="调度时间" prop="scheduleTime">
        <el-date-picker
          v-model="formData.scheduleTime"
          type="date"
          value-format="x"
          placeholder="选择调度时间"
        />
      </el-form-item>
      <el-form-item label="完成时间" prop="completeTime">
        <el-date-picker
          v-model="formData.completeTime"
          type="date"
          value-format="x"
          placeholder="选择完成时间"
        />
      </el-form-item>
      <el-form-item label="资源负责人" prop="resMngr">
        <el-input v-model="formData.resMngr" placeholder="请输入资源负责人" />
      </el-form-item>
      <el-form-item label="资源负责人姓名" prop="resMngrName">
        <el-input v-model="formData.resMngrName" placeholder="请输入资源负责人姓名" />
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
import { ResMatchScheduleApi, ResMatchScheduleVO } from '@/api/dataHub/analysisDecision/resmatchschedule'

/** 资源匹配调度 表单 */
defineOptions({ name: 'ResMatchScheduleForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  resScheduleId: undefined,
  relSchemeId: undefined,
  relSchemeName: undefined,
  resDemandDetail: undefined,
  matchedResDetail: undefined,
  scheduleStatus: undefined,
  matchFailReason: undefined,
  scheduleWoId: undefined,
  scheduleUser: undefined,
  scheduleTime: undefined,
  completeTime: undefined,
  resMngr: undefined,
  resMngrName: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined
})
const formRules = reactive({
  resScheduleId: [{ required: true, message: '调度ID不能为空', trigger: 'blur' }],
  relSchemeId: [{ required: true, message: '关联方案ID不能为空', trigger: 'blur' }],
  relSchemeName: [{ required: true, message: '关联方案名称不能为空', trigger: 'blur' }],
  resDemandDetail: [{ required: true, message: '资源需求明细不能为空', trigger: 'blur' }],
  scheduleStatus: [{ required: true, message: '调度状态不能为空', trigger: 'blur' }],
  scheduleUser: [{ required: true, message: '调度人不能为空', trigger: 'blur' }],
  scheduleTime: [{ required: true, message: '调度时间不能为空', trigger: 'blur' }]
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
      formData.value = await ResMatchScheduleApi.getResMatchSchedule(id)
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
    const data = formData.value as unknown as ResMatchScheduleVO
    if (formType.value === 'create') {
      await ResMatchScheduleApi.createResMatchSchedule(data)
      message.success(t('common.createSuccess'))
    } else {
      await ResMatchScheduleApi.updateResMatchSchedule(data)
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
    resScheduleId: undefined,
    relSchemeId: undefined,
    relSchemeName: undefined,
    resDemandDetail: undefined,
    matchedResDetail: undefined,
    scheduleStatus: undefined,
    matchFailReason: undefined,
    scheduleWoId: undefined,
    scheduleUser: undefined,
    scheduleTime: undefined,
    completeTime: undefined,
    resMngr: undefined,
    resMngrName: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined
  }
  formRef.value?.resetFields()
}
</script>
