<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="响应ID" prop="respId">
        <el-input v-model="formData.respId" placeholder="请输入响应ID" />
      </el-form-item>
      <el-form-item label="响应编号" prop="respNo">
        <el-input v-model="formData.respNo" placeholder="请输入响应编号" />
      </el-form-item>
      <el-form-item label="关联预案ID" prop="planId">
        <el-input v-model="formData.planId" placeholder="请输入关联预案ID" />
      </el-form-item>
      <el-form-item label="预案名称" prop="planName">
        <el-input v-model="formData.planName" placeholder="请输入预案名称" />
      </el-form-item>
      <el-form-item label="预案版本" prop="planVersion">
        <el-input v-model="formData.planVersion" placeholder="请输入预案版本" />
      </el-form-item>
      <el-form-item label="关联事件编码" prop="evtCode">
        <el-input v-model="formData.evtCode" placeholder="请输入关联事件编码" />
      </el-form-item>
      <el-form-item label="事件名称" prop="evtName">
        <el-input v-model="formData.evtName" placeholder="请输入事件名称" />
      </el-form-item>
      <el-form-item label="启动原因" prop="launchReason">
        <el-input v-model="formData.launchReason" placeholder="请输入启动原因" />
      </el-form-item>
      <el-form-item label="响应等级" prop="respLevel">
        <el-input v-model="formData.respLevel" placeholder="请输入响应等级" />
      </el-form-item>
      <el-form-item label="启动人ID" prop="launchUserId">
        <el-input v-model="formData.launchUserId" placeholder="请输入启动人ID" />
      </el-form-item>
      <el-form-item label="启动人姓名" prop="launchUserName">
        <el-input v-model="formData.launchUserName" placeholder="请输入启动人姓名" />
      </el-form-item>
      <el-form-item label="启动时间" prop="launchTime">
        <el-date-picker
          v-model="formData.launchTime"
          type="date"
          value-format="x"
          placeholder="选择启动时间"
        />
      </el-form-item>
      <el-form-item label="响应状态" prop="respStatus">
        <el-radio-group v-model="formData.respStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="终止原因" prop="terminateReason">
        <el-input v-model="formData.terminateReason" placeholder="请输入终止原因" />
      </el-form-item>
      <el-form-item label="终止时间" prop="terminateTime">
        <el-date-picker
          v-model="formData.terminateTime"
          type="date"
          value-format="x"
          placeholder="选择终止时间"
        />
      </el-form-item>
      <el-form-item label="需同步的联动单位ID，多个用逗号分隔" prop="syncUnitIds">
        <el-input v-model="formData.syncUnitIds" placeholder="请输入需同步的联动单位ID，多个用逗号分隔" />
      </el-form-item>
      <el-form-item label="与单位IDs同步" prop="syncUnitNames">
        <el-input v-model="formData.syncUnitNames" placeholder="请输入与单位IDs同步" />
      </el-form-item>
      <el-form-item label="预留，如“响应指挥长”" prop="extCat1">
        <el-input v-model="formData.extCat1" placeholder="请输入预留，如“响应指挥长”" />
      </el-form-item>
      <el-form-item label="预留，如“响应指挥长”" prop="extCat2">
        <el-input v-model="formData.extCat2" placeholder="请输入预留，如“响应指挥长”" />
      </el-form-item>
      <el-form-item label="预留，存储响应部署方案路径" prop="extCommon1">
        <el-input v-model="formData.extCommon1" placeholder="请输入预留，存储响应部署方案路径" />
      </el-form-item>
      <el-form-item label="预留，存储响应部署方案路径" prop="extCommon2">
        <el-input v-model="formData.extCommon2" placeholder="请输入预留，存储响应部署方案路径" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { PlanLaunchRespApi, PlanLaunchRespVO } from '@/api/dataHub/commandAndCoordination/planlaunchresp'

/** 预案启动响应 表单 */
defineOptions({ name: 'PlanLaunchRespForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  respId: undefined,
  respNo: undefined,
  planId: undefined,
  planName: undefined,
  planVersion: undefined,
  evtCode: undefined,
  evtName: undefined,
  launchReason: undefined,
  respLevel: undefined,
  launchUserId: undefined,
  launchUserName: undefined,
  launchTime: undefined,
  respStatus: undefined,
  terminateReason: undefined,
  terminateTime: undefined,
  syncUnitIds: undefined,
  syncUnitNames: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
})
const formRules = reactive({
  respId: [{ required: true, message: '响应ID不能为空', trigger: 'blur' }],
  respNo: [{ required: true, message: '响应编号不能为空', trigger: 'blur' }],
  planId: [{ required: true, message: '关联预案ID不能为空', trigger: 'blur' }],
  planName: [{ required: true, message: '预案名称不能为空', trigger: 'blur' }],
  planVersion: [{ required: true, message: '预案版本不能为空', trigger: 'blur' }],
  launchReason: [{ required: true, message: '启动原因不能为空', trigger: 'blur' }],
  respLevel: [{ required: true, message: '响应等级不能为空', trigger: 'blur' }],
  launchUserId: [{ required: true, message: '启动人ID不能为空', trigger: 'blur' }],
  launchUserName: [{ required: true, message: '启动人姓名不能为空', trigger: 'blur' }],
  launchTime: [{ required: true, message: '启动时间不能为空', trigger: 'blur' }],
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
      formData.value = await PlanLaunchRespApi.getPlanLaunchResp(id)
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
    const data = formData.value as unknown as PlanLaunchRespVO
    if (formType.value === 'create') {
      await PlanLaunchRespApi.createPlanLaunchResp(data)
      message.success(t('common.createSuccess'))
    } else {
      await PlanLaunchRespApi.updatePlanLaunchResp(data)
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
    respId: undefined,
    respNo: undefined,
    planId: undefined,
    planName: undefined,
    planVersion: undefined,
    evtCode: undefined,
    evtName: undefined,
    launchReason: undefined,
    respLevel: undefined,
    launchUserId: undefined,
    launchUserName: undefined,
    launchTime: undefined,
    respStatus: undefined,
    terminateReason: undefined,
    terminateTime: undefined,
    syncUnitIds: undefined,
    syncUnitNames: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined,
  }
  formRef.value?.resetFields()
}
</script>
