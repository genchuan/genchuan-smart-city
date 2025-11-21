<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="预警ID（UUID）" prop="alarmId">
        <el-input v-model="formData.alarmId" placeholder="请输入预警ID（UUID）" />
      </el-form-item>
      <el-form-item label="预警编号（AL+分域编码+8位流水号）" prop="alarmCode">
        <el-input v-model="formData.alarmCode" placeholder="请输入预警编号（AL+分域编码+8位流水号）" />
      </el-form-item>
      <el-form-item label="风险类型ID" prop="riskTypeId">
        <el-input v-model="formData.riskTypeId" placeholder="请输入风险类型ID" />
      </el-form-item>
      <el-form-item label="风险类型名称" prop="riskTypeName">
        <el-input v-model="formData.riskTypeName" placeholder="请输入风险类型名称" />
      </el-form-item>
      <el-form-item label="预警等级（一般/较大/重大/特别重大）" prop="alarmLevel">
        <el-input v-model="formData.alarmLevel" placeholder="请输入预警等级（一般/较大/重大/特别重大）" />
      </el-form-item>
      <el-form-item label="所属分域ID" prop="domainId">
        <el-input v-model="formData.domainId" placeholder="请输入所属分域ID" />
      </el-form-item>
      <el-form-item label="所属分域名称" prop="domainName">
        <el-input v-model="formData.domainName" placeholder="请输入所属分域名称" />
      </el-form-item>
      <el-form-item label="发生区域" prop="occurRegion">
        <el-input v-model="formData.occurRegion" placeholder="请输入发生区域" />
      </el-form-item>
      <el-form-item label="GPS坐标（经度,纬度）" prop="gpsCoordinate">
        <el-input v-model="formData.gpsCoordinate" placeholder="请输入GPS坐标（经度,纬度）" />
      </el-form-item>
      <el-form-item label="触发时间" prop="triggerTime">
        <el-date-picker
          v-model="formData.triggerTime"
          type="date"
          value-format="x"
          placeholder="选择触发时间"
        />
      </el-form-item>
      <el-form-item label="预警状态（待处置/处置中/已完成/已解除）" prop="alarmStatus">
<!--        <el-radio-group v-model="formData.alarmStatus">-->
<!--          <el-radio value="1">请选择字典生成</el-radio>-->
<!--        </el-radio-group>-->
        <el-input v-model="formData.alarmStatus" placeholder="请输入预警状态" />
      </el-form-item>
      <el-form-item label="触发原因" prop="triggerReason">
        <el-input v-model="formData.triggerReason" placeholder="请输入触发原因" />
      </el-form-item>
      <el-form-item label="关联指标ID" prop="indicatorId">
        <el-input v-model="formData.indicatorId" placeholder="请输入关联指标ID" />
      </el-form-item>
      <el-form-item label="处置责任人ID" prop="handlerId">
        <el-input v-model="formData.handlerId" placeholder="请输入处置责任人ID" />
      </el-form-item>
      <el-form-item label="处置责任人姓名" prop="handlerName">
        <el-input v-model="formData.handlerName" placeholder="请输入处置责任人姓名" />
      </el-form-item>
      <el-form-item label="最后更新时间" prop="lastUpdateTime">
        <el-date-picker
          v-model="formData.lastUpdateTime"
          type="date"
          value-format="x"
          placeholder="选择最后更新时间"
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
import { AlarmListApi, AlarmListVO } from '@/api/dataHub/earlyWarningAlarm/alarmlist'

/** 预警告警列 表单 */
defineOptions({ name: 'AlarmListForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  alarmId: undefined,
  alarmCode: undefined,
  riskTypeId: undefined,
  riskTypeName: undefined,
  alarmLevel: undefined,
  domainId: undefined,
  domainName: undefined,
  occurRegion: undefined,
  gpsCoordinate: undefined,
  triggerTime: undefined,
  alarmStatus: undefined,
  triggerReason: undefined,
  indicatorId: undefined,
  handlerId: undefined,
  handlerName: undefined,
  lastUpdateTime: undefined,
})
const formRules = reactive({
  alarmId: [{ required: true, message: '预警ID（UUID）不能为空', trigger: 'blur' }],
  alarmCode: [{ required: true, message: '预警编号（AL+分域编码+8位流水号）不能为空', trigger: 'blur' }],
  riskTypeId: [{ required: true, message: '风险类型ID不能为空', trigger: 'blur' }],
  alarmLevel: [{ required: true, message: '预警等级（一般/较大/重大/特别重大）不能为空', trigger: 'blur' }],
  domainId: [{ required: true, message: '所属分域ID不能为空', trigger: 'blur' }],
  occurRegion: [{ required: true, message: '发生区域不能为空', trigger: 'blur' }],
  triggerTime: [{ required: true, message: '触发时间不能为空', trigger: 'blur' }],
  alarmStatus: [{ required: true, message: '预警状态（待处置/处置中/已完成/已解除）不能为空', trigger: 'blur' }],
  triggerReason: [{ required: true, message: '触发原因不能为空', trigger: 'blur' }],
  lastUpdateTime: [{ required: true, message: '最后更新时间不能为空', trigger: 'blur' }],
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
      formData.value = await AlarmListApi.getAlarmList(id)
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
    const data = formData.value as unknown as AlarmListVO
    if (formType.value === 'create') {
      await AlarmListApi.createAlarmList(data)
      message.success(t('common.createSuccess'))
    } else {
      await AlarmListApi.updateAlarmList(data)
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
    alarmId: undefined,
    alarmCode: undefined,
    riskTypeId: undefined,
    riskTypeName: undefined,
    alarmLevel: undefined,
    domainId: undefined,
    domainName: undefined,
    occurRegion: undefined,
    gpsCoordinate: undefined,
    triggerTime: undefined,
    alarmStatus: undefined,
    triggerReason: undefined,
    indicatorId: undefined,
    handlerId: undefined,
    handlerName: undefined,
    lastUpdateTime: undefined,
  }
  formRef.value?.resetFields()
}
</script>
