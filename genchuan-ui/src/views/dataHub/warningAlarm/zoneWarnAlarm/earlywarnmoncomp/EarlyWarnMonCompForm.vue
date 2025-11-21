<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="650px">
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
          <el-form-item label="统计ID" prop="warnMonCompStatId">
            <el-input v-model="formData.warnMonCompStatId" placeholder="请输入统计ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="统计周期" prop="statCycle">
            <el-input v-model="formData.statCycle" placeholder="请输入统计周期" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="部件大类ID" prop="monMajorId">
            <el-input v-model="formData.monMajorId" placeholder="请输入部件大类ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="部件大类名称" prop="monMajorName">
            <el-input v-model="formData.monMajorName" placeholder="请输入部件大类名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="部件小类ID" prop="monMinorId">
            <el-input v-model="formData.monMinorId" placeholder="请输入部件小类ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="部件小类名称" prop="monMinorName">
            <el-input v-model="formData.monMinorName" placeholder="请输入部件小类名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="预警总数" prop="totalWarnCount">
            <el-input v-model.number="formData.totalWarnCount" placeholder="请输入预警总数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="主要触发原因" prop="mainTriggerReason">
            <el-input v-model="formData.mainTriggerReason" placeholder="请输入主要触发原因" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="涉及设备数" prop="deviceCount">
            <el-input v-model.number="formData.deviceCount" placeholder="请输入涉及设备数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="已恢复数" prop="recoveredCount">
            <el-input v-model.number="formData.recoveredCount" placeholder="请输入已恢复数" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="恢复率" prop="recoveryRate">
            <el-input v-model.number="formData.recoveryRate" placeholder="请输入恢复率" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="统计人" prop="statUserId">
            <el-input v-model="formData.statUserId" placeholder="请输入统计人" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="统计时间" prop="statTime">
            <el-date-picker
              v-model="formData.statTime"
              type="date"
              value-format="x"
              placeholder="选择统计时间"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-collapse v-model="activeNames" class="mt-4">
        <el-collapse-item title="扩展字段" name="1">
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
  EarlyWarnMonCompApi,
  EarlyWarnMonCompVO
} from '@/api/dataHub/warningAlarm/zoneWarnAlarm/earlywarnmoncomp'

/** 按监测部件预警告警统计 表单 */
defineOptions({ name: 'EarlyWarnMonCompForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const activeNames = ref(['1']) // 折叠面板默认状态
const formData = ref({
  id: undefined,
  warnMonCompStatId: undefined,
  statCycle: undefined,
  monMajorId: undefined,
  monMajorName: undefined,
  monMinorId: undefined,
  monMinorName: undefined,
  totalWarnCount: undefined,
  mainTriggerReason: undefined,
  deviceCount: undefined,
  recoveredCount: undefined,
  recoveryRate: undefined,
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
      formData.value = await EarlyWarnMonCompApi.getEarlyWarnMonComp(id)
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
    const data = formData.value as unknown as EarlyWarnMonCompVO
    if (formType.value === 'create') {
      await EarlyWarnMonCompApi.createEarlyWarnMonComp(data)
      message.success(t('common.createSuccess'))
    } else {
      await EarlyWarnMonCompApi.updateEarlyWarnMonComp(data)
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
    warnMonCompStatId: undefined,
    statCycle: undefined,
    monMajorId: undefined,
    monMajorName: undefined,
    monMinorId: undefined,
    monMinorName: undefined,
    totalWarnCount: undefined,
    mainTriggerReason: undefined,
    deviceCount: undefined,
    recoveredCount: undefined,
    recoveryRate: undefined,
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
}

::v-deep .el-form-item__label {
  color: #4e5969;
  font-weight: 500;
}

::v-deep .el-collapse-item__header {
  background-color: #f5f7fa;
  border-radius: 4px;
}

::v-deep .el-collapse-item__content {
  padding-top: 15px;
}
</style>
