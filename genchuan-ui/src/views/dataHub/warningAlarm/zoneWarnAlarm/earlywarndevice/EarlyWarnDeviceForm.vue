<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="600px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
      class="form-container"
    >
      <el-form-item label="统计ID" prop="warnDeviceStatId">
        <el-input v-model="formData.warnDeviceStatId" placeholder="请输入统计ID" />
      </el-form-item>
      <el-form-item label="统计周期" prop="statCycle">
        <el-input v-model="formData.statCycle" placeholder="请输入统计周期" />
      </el-form-item>
      <el-form-item label="设备大类ID" prop="deviceMajorId">
        <el-input v-model="formData.deviceMajorId" placeholder="请输入设备大类ID" />
      </el-form-item>
      <el-form-item label="设备大类名称" prop="deviceMajorName">
        <el-input v-model="formData.deviceMajorName" placeholder="请输入设备大类名称" />
      </el-form-item>
      <el-form-item label="设备型号ID" prop="deviceModelId">
        <el-input v-model="formData.deviceModelId" placeholder="请输入设备型号ID" />
      </el-form-item>
      <el-form-item label="设备型号名称" prop="deviceModelName">
        <el-input v-model="formData.deviceModelName" placeholder="请输入设备型号名称" />
      </el-form-item>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="预警总数" prop="totalWarnCount">
            <el-input v-model.number="formData.totalWarnCount" placeholder="请输入预警总数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="涉及设备数" prop="deviceCount">
            <el-input v-model.number="formData.deviceCount" placeholder="请输入涉及设备数" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="在线修复数" prop="onlineRepairCount">
            <el-input v-model.number="formData.onlineRepairCount" placeholder="请输入在线修复数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="在线修复率" prop="onlineRepairRate">
            <el-input v-model.number="formData.onlineRepairRate" placeholder="请输入在线修复率" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="主要故障类型" prop="mainFaultType">
        <el-select v-model="formData.mainFaultType" placeholder="请选择主要故障类型">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="统计人" prop="statUserId">
            <el-input v-model="formData.statUserId" placeholder="请输入统计人" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="统计时间" prop="statTime">
            <el-date-picker
              v-model="formData.statTime"
              type="datetime"
              value-format="x"
              placeholder="选择统计时间"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-collapse v-model="expandKeys">
        <el-collapse-item title="扩展字段" name="expand">
          <el-row :gutter="20">
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
        </el-collapse-item>
      </el-collapse>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  EarlyWarnDeviceApi,
  EarlyWarnDeviceVO
} from '@/api/dataHub/warningAlarm/zoneWarnAlarm/earlywarndevice'

/** 按设备分域预警告警统计 表单 */
defineOptions({ name: 'EarlyWarnDeviceForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const expandKeys = ref<string[]>([]) // 控制扩展字段折叠面板
const formData = ref({
  id: undefined,
  warnDeviceStatId: undefined,
  statCycle: undefined,
  deviceMajorId: undefined,
  deviceMajorName: undefined,
  deviceModelId: undefined,
  deviceModelName: undefined,
  totalWarnCount: undefined,
  mainFaultType: undefined,
  deviceCount: undefined,
  onlineRepairCount: undefined,
  onlineRepairRate: undefined,
  statUserId: undefined,
  statTime: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  warnDeviceStatId: [{ required: true, message: '统计ID不能为空', trigger: 'blur' }],
  statCycle: [{ required: true, message: '统计周期不能为空', trigger: 'blur' }],
  deviceMajorId: [{ required: true, message: '设备大类ID不能为空', trigger: 'blur' }],
  deviceMajorName: [{ required: true, message: '设备大类名称不能为空', trigger: 'blur' }],
  deviceModelId: [{ required: true, message: '设备型号ID不能为空', trigger: 'blur' }],
  deviceModelName: [{ required: true, message: '设备型号名称不能为空', trigger: 'blur' }],
  totalWarnCount: [{ required: true, message: '预警总数不能为空', trigger: 'blur' }],
  deviceCount: [{ required: true, message: '涉及设备数不能为空', trigger: 'blur' }],
  statUserId: [{ required: true, message: '统计人不能为空', trigger: 'blur' }],
  statTime: [{ required: true, message: '统计时间不能为空', trigger: 'blur' }]
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
      formData.value = await EarlyWarnDeviceApi.getEarlyWarnDevice(id)
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
    const data = formData.value as unknown as EarlyWarnDeviceVO
    if (formType.value === 'create') {
      await EarlyWarnDeviceApi.createEarlyWarnDevice(data)
      message.success(t('common.createSuccess'))
    } else {
      await EarlyWarnDeviceApi.updateEarlyWarnDevice(data)
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
    warnDeviceStatId: undefined,
    statCycle: undefined,
    deviceMajorId: undefined,
    deviceMajorName: undefined,
    deviceModelId: undefined,
    deviceModelName: undefined,
    totalWarnCount: undefined,
    mainFaultType: undefined,
    deviceCount: undefined,
    onlineRepairCount: undefined,
    onlineRepairRate: undefined,
    statUserId: undefined,
    statTime: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined
  }
  formRef.value?.resetFields()
  expandKeys.value = []
}
</script>

<style scoped>
.form-container {
  padding: 15px 0;
}

::v-deep .el-form-item {
  margin-bottom: 18px;
}

::v-deep .el-collapse-item__content {
  padding-bottom: 10px !important;
}

::v-deep .el-collapse {
  margin-top: 10px;
  border: 1px solid #f0f2f5;
  border-radius: 4px;
}
</style>
