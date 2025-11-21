<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="评估ID" prop="evalId">
        <el-input v-model="formData.evalId" placeholder="请输入评估ID" />
      </el-form-item>
      <el-form-item label="关联回溯ID" prop="traceId">
        <el-input v-model="formData.traceId" placeholder="请输入关联回溯ID" />
      </el-form-item>
      <el-form-item label="事件编码" prop="evtCode">
        <el-input v-model="formData.evtCode" placeholder="请输入事件编码" />
      </el-form-item>
      <el-form-item label="事件名称" prop="evtName">
        <el-input v-model="formData.evtName" placeholder="请输入事件名称" />
      </el-form-item>
      <el-form-item label="处置效率得分" prop="efficacyScore">
        <el-input v-model="formData.efficacyScore" placeholder="请输入处置效率得分" />
      </el-form-item>
      <el-form-item label="处置效率说明" prop="efficacyDesc">
        <el-input v-model="formData.efficacyDesc" placeholder="请输入处置效率说明" />
      </el-form-item>
      <el-form-item label="资源利用得分" prop="resScore">
        <el-input v-model="formData.resScore" placeholder="请输入资源利用得分" />
      </el-form-item>
      <el-form-item label="资源利用说明" prop="resDesc">
        <el-input v-model="formData.resDesc" placeholder="请输入资源利用说明" />
      </el-form-item>
      <el-form-item label="效果达成得分" prop="effectScore">
        <el-input v-model="formData.effectScore" placeholder="请输入效果达成得分" />
      </el-form-item>
      <el-form-item label="效果达成说明" prop="effectDesc">
        <el-input v-model="formData.effectDesc" placeholder="请输入效果达成说明" />
      </el-form-item>
      <el-form-item label="综合得分" prop="comprehensiveScore">
        <el-input v-model="formData.comprehensiveScore" placeholder="请输入综合得分" />
      </el-form-item>
      <el-form-item label="改进建议" prop="improveSugg">
        <el-input v-model="formData.improveSugg" placeholder="请输入改进建议" />
      </el-form-item>
      <el-form-item label="评估人ID" prop="evalUserId">
        <el-input v-model="formData.evalUserId" placeholder="请输入评估人ID" />
      </el-form-item>
      <el-form-item label="评估人姓名" prop="evalUserName">
        <el-input v-model="formData.evalUserName" placeholder="请输入评估人姓名" />
      </el-form-item>
      <el-form-item label="评估时间" prop="evalTime">
        <el-date-picker
          v-model="formData.evalTime"
          type="date"
          value-format="x"
          placeholder="选择评估时间"
        />
      </el-form-item>
      <el-form-item label="评估报告路径" prop="rptPath">
        <el-input v-model="formData.rptPath" placeholder="请输入评估报告路径" />
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
import { DisposalResultEvalApi, DisposalResultEvalVO } from '@/api/dataHub/commandAndCoordination/disposalresulteval'

/** 处置结果评估 表单 */
defineOptions({ name: 'DisposalResultEvalForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  evalId: undefined,
  traceId: undefined,
  evtCode: undefined,
  evtName: undefined,
  efficacyScore: undefined,
  efficacyDesc: undefined,
  resScore: undefined,
  resDesc: undefined,
  effectScore: undefined,
  effectDesc: undefined,
  comprehensiveScore: undefined,
  improveSugg: undefined,
  evalUserId: undefined,
  evalUserName: undefined,
  evalTime: undefined,
  rptPath: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  evalId: [{ required: true, message: '评估ID不能为空', trigger: 'blur' }],
  traceId: [{ required: true, message: '关联回溯ID不能为空', trigger: 'blur' }],
  evtCode: [{ required: true, message: '事件编码不能为空', trigger: 'blur' }],
  evtName: [{ required: true, message: '事件名称不能为空', trigger: 'blur' }],
  efficacyScore: [{ required: true, message: '处置效率得分不能为空', trigger: 'blur' }],
  resScore: [{ required: true, message: '资源利用得分不能为空', trigger: 'blur' }],
  effectScore: [{ required: true, message: '效果达成得分不能为空', trigger: 'blur' }],
  comprehensiveScore: [{ required: true, message: '综合得分不能为空', trigger: 'blur' }],
  evalUserId: [{ required: true, message: '评估人ID不能为空', trigger: 'blur' }],
  evalUserName: [{ required: true, message: '评估人姓名不能为空', trigger: 'blur' }],
  evalTime: [{ required: true, message: '评估时间不能为空', trigger: 'blur' }],
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
      formData.value = await DisposalResultEvalApi.getDisposalResultEval(id)
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
    const data = formData.value as unknown as DisposalResultEvalVO
    if (formType.value === 'create') {
      await DisposalResultEvalApi.createDisposalResultEval(data)
      message.success(t('common.createSuccess'))
    } else {
      await DisposalResultEvalApi.updateDisposalResultEval(data)
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
    evalId: undefined,
    traceId: undefined,
    evtCode: undefined,
    evtName: undefined,
    efficacyScore: undefined,
    efficacyDesc: undefined,
    resScore: undefined,
    resDesc: undefined,
    effectScore: undefined,
    effectDesc: undefined,
    comprehensiveScore: undefined,
    improveSugg: undefined,
    evalUserId: undefined,
    evalUserName: undefined,
    evalTime: undefined,
    rptPath: undefined,
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
