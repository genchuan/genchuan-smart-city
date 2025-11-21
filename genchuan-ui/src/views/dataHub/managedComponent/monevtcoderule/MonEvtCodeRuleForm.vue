<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="规则ID" prop="monEvtRuleId">
        <el-input v-model="formData.monEvtRuleId" placeholder="请输入规则ID" />
      </el-form-item>
      <el-form-item label="规则名称" prop="ruleName">
        <el-input v-model="formData.ruleName" placeholder="请输入规则名称" />
      </el-form-item>
      <el-form-item label="行政代码位数" prop="adminCodeLen">
        <el-input v-model="formData.adminCodeLen" placeholder="请输入行政代码位数" />
      </el-form-item>
      <el-form-item label="大类代码位数" prop="majorCodeLen">
        <el-input v-model="formData.majorCodeLen" placeholder="请输入大类代码位数" />
      </el-form-item>
      <el-form-item label="中类代码位数" prop="midCodeLen">
        <el-input v-model="formData.midCodeLen" placeholder="请输入中类代码位数" />
      </el-form-item>
      <el-form-item label="小类代码位数" prop="minorCodeLen">
        <el-input v-model="formData.minorCodeLen" placeholder="请输入小类代码位数" />
      </el-form-item>
      <el-form-item label="顺序码位数" prop="seqCodeLen">
        <el-input v-model="formData.seqCodeLen" placeholder="请输入顺序码位数" />
      </el-form-item>
      <el-form-item label="顺序码生成规则" prop="seqGenRule">
        <el-input v-model="formData.seqGenRule" placeholder="请输入顺序码生成规则" />
      </el-form-item>
      <el-form-item label="启用状态：1（启用）/0（禁用）" prop="enableStatus">
        <el-radio-group v-model="formData.enableStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="创建人" prop="createUser">
        <el-input v-model="formData.createUser" placeholder="请输入创建人" />
      </el-form-item>
      <el-form-item label="更新人" prop="updateUser">
        <el-input v-model="formData.updateUser" placeholder="请输入更新人" />
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
import { MonEvtCodeRuleApi, MonEvtCodeRuleVO } from '@/api/dataHub/managedComponent/monevtcoderule'

/** 监测事件标识码规则 表单 */
defineOptions({ name: 'MonEvtCodeRuleForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  monEvtRuleId: undefined,
  ruleName: undefined,
  adminCodeLen: undefined,
  majorCodeLen: undefined,
  midCodeLen: undefined,
  minorCodeLen: undefined,
  seqCodeLen: undefined,
  seqGenRule: undefined,
  enableStatus: undefined,
  createUser: undefined,
  updateUser: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  monEvtRuleId: [{ required: true, message: '规则ID不能为空', trigger: 'blur' }],
  ruleName: [{ required: true, message: '规则名称不能为空', trigger: 'blur' }],
  adminCodeLen: [{ required: true, message: '行政代码位数不能为空', trigger: 'blur' }],
  majorCodeLen: [{ required: true, message: '大类代码位数不能为空', trigger: 'blur' }],
  midCodeLen: [{ required: true, message: '中类代码位数不能为空', trigger: 'blur' }],
  minorCodeLen: [{ required: true, message: '小类代码位数不能为空', trigger: 'blur' }],
  seqCodeLen: [{ required: true, message: '顺序码位数不能为空', trigger: 'blur' }],
  seqGenRule: [{ required: true, message: '顺序码生成规则不能为空', trigger: 'blur' }],
  enableStatus: [{ required: true, message: '启用状态：1（启用）/0（禁用）不能为空', trigger: 'blur' }],
  createUser: [{ required: true, message: '创建人不能为空', trigger: 'blur' }],
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
      formData.value = await MonEvtCodeRuleApi.getMonEvtCodeRule(id)
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
    const data = formData.value as unknown as MonEvtCodeRuleVO
    if (formType.value === 'create') {
      await MonEvtCodeRuleApi.createMonEvtCodeRule(data)
      message.success(t('common.createSuccess'))
    } else {
      await MonEvtCodeRuleApi.updateMonEvtCodeRule(data)
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
    monEvtRuleId: undefined,
    ruleName: undefined,
    adminCodeLen: undefined,
    majorCodeLen: undefined,
    midCodeLen: undefined,
    minorCodeLen: undefined,
    seqCodeLen: undefined,
    seqGenRule: undefined,
    enableStatus: undefined,
    createUser: undefined,
    updateUser: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined,
  }
  formRef.value?.resetFields()
}
</script>
