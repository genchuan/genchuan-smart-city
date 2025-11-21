<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="事件ID" prop="monEvtId">
        <el-input v-model="formData.monEvtId" placeholder="请输入事件ID" />
      </el-form-item>
      <el-form-item label="事件标识码" prop="evtCode">
        <el-input v-model="formData.evtCode" placeholder="请输入事件标识码" />
      </el-form-item>
      <el-form-item label="事件名称" prop="evtName">
        <el-input v-model="formData.evtName" placeholder="请输入事件名称" />
      </el-form-item>
      <el-form-item label="事件分类ID" prop="evtCatId">
        <el-input v-model="formData.evtCatId" placeholder="请输入事件分类ID" />
      </el-form-item>
      <el-form-item label="关联部件ID" prop="relCompId">
        <el-input v-model="formData.relCompId" placeholder="请输入关联部件ID" />
      </el-form-item>
      <el-form-item label="关联部件名称" prop="relCompName">
        <el-input v-model="formData.relCompName" placeholder="请输入关联部件名称" />
      </el-form-item>
      <el-form-item label="事发位置" prop="incidentPos">
        <el-input v-model="formData.incidentPos" placeholder="请输入事发位置" />
      </el-form-item>
      <el-form-item label="事发坐标X" prop="incidentX">
        <el-input v-model="formData.incidentX" placeholder="请输入事发坐标X" />
      </el-form-item>
      <el-form-item label="事发坐标Y" prop="incidentY">
        <el-input v-model="formData.incidentY" placeholder="请输入事发坐标Y" />
      </el-form-item>
      <el-form-item label="事件等级" prop="evtLevel">
        <el-input v-model="formData.evtLevel" placeholder="请输入事件等级" />
      </el-form-item>
      <el-form-item label="处置状态" prop="handleStatus">
        <el-radio-group v-model="formData.handleStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
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
import { MonEvtInfoApi, MonEvtInfoVO } from '@/api/dataHub/managedComponent/monevtinfo'

/** 监测事件信息 表单 */
defineOptions({ name: 'MonEvtInfoForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  monEvtId: undefined,
  evtCode: undefined,
  evtName: undefined,
  evtCatId: undefined,
  relCompId: undefined,
  relCompName: undefined,
  incidentPos: undefined,
  incidentX: undefined,
  incidentY: undefined,
  evtLevel: undefined,
  handleStatus: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  monEvtId: [{ required: true, message: '事件ID不能为空', trigger: 'blur' }],
  evtCode: [{ required: true, message: '事件标识码不能为空', trigger: 'blur' }],
  evtName: [{ required: true, message: '事件名称不能为空', trigger: 'blur' }],
  evtCatId: [{ required: true, message: '事件分类ID不能为空', trigger: 'blur' }],
  relCompId: [{ required: true, message: '关联部件ID不能为空', trigger: 'blur' }],
  relCompName: [{ required: true, message: '关联部件名称不能为空', trigger: 'blur' }],
  incidentPos: [{ required: true, message: '事发位置不能为空', trigger: 'blur' }],
  incidentX: [{ required: true, message: '事发坐标X不能为空', trigger: 'blur' }],
  incidentY: [{ required: true, message: '事发坐标Y不能为空', trigger: 'blur' }],
  evtLevel: [{ required: true, message: '事件等级不能为空', trigger: 'blur' }],
  handleStatus: [{ required: true, message: '处置状态不能为空', trigger: 'blur' }],
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
      formData.value = await MonEvtInfoApi.getMonEvtInfo(id)
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
    const data = formData.value as unknown as MonEvtInfoVO
    if (formType.value === 'create') {
      await MonEvtInfoApi.createMonEvtInfo(data)
      message.success(t('common.createSuccess'))
    } else {
      await MonEvtInfoApi.updateMonEvtInfo(data)
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
    monEvtId: undefined,
    evtCode: undefined,
    evtName: undefined,
    evtCatId: undefined,
    relCompId: undefined,
    relCompName: undefined,
    incidentPos: undefined,
    incidentX: undefined,
    incidentY: undefined,
    evtLevel: undefined,
    handleStatus: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined,
  }
  formRef.value?.resetFields()
}
</script>
