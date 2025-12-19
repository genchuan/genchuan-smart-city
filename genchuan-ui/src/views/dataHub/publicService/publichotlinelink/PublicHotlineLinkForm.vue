<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="热线ID" prop="hotlineId">
        <el-input v-model="formData.hotlineId" placeholder="请输入热线ID" />
      </el-form-item>
      <el-form-item label="热线单号" prop="hotlineNo">
        <el-input v-model="formData.hotlineNo" placeholder="请输入热线单号" />
      </el-form-item>
      <el-form-item label="平台单号" prop="platformNo">
        <el-input v-model="formData.platformNo" placeholder="请输入平台单号" />
      </el-form-item>
      <el-form-item label="诉求类型编码" prop="appealTypeCode">
        <el-input v-model="formData.appealTypeCode" placeholder="请输入诉求类型编码" />
      </el-form-item>
      <el-form-item label="诉求类型名称" prop="appealTypeName">
        <el-input v-model="formData.appealTypeName" placeholder="请输入诉求类型名称" />
      </el-form-item>
      <el-form-item label="事发区域编码" prop="areaCode">
        <el-input v-model="formData.areaCode" placeholder="请输入事发区域编码" />
      </el-form-item>
      <el-form-item label="事发区域名称" prop="areaName">
        <el-input v-model="formData.areaName" placeholder="请输入事发区域名称" />
      </el-form-item>
      <el-form-item label="诉求内容" prop="appealContent">
        <Editor v-model="formData.appealContent" height="150px" />
      </el-form-item>
      <el-form-item label="联系人姓名" prop="contactName">
        <el-input v-model="formData.contactName" placeholder="请输入联系人姓名" />
      </el-form-item>
      <el-form-item label="联系人电话" prop="contactPhone">
        <el-input v-model="formData.contactPhone" placeholder="请输入联系人电话" />
      </el-form-item>
      <el-form-item label="热线来源" prop="hotlineSource">
        <el-input v-model="formData.hotlineSource" placeholder="请输入热线来源" />
      </el-form-item>
      <el-form-item label="同步状态" prop="syncStatus">
        <el-radio-group v-model="formData.syncStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="处置状态" prop="handleStatus">
        <el-radio-group v-model="formData.handleStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="受理部门编码" prop="acceptDeptCode">
        <el-input v-model="formData.acceptDeptCode" placeholder="请输入受理部门编码" />
      </el-form-item>
      <el-form-item label="受理部门名称" prop="acceptDeptName">
        <el-input v-model="formData.acceptDeptName" placeholder="请输入受理部门名称" />
      </el-form-item>
      <el-form-item label="处置结果" prop="handleResult">
        <el-input v-model="formData.handleResult" placeholder="请输入处置结果" />
      </el-form-item>
      <el-form-item label="同步时间" prop="syncTime">
        <el-date-picker
          v-model="formData.syncTime"
          type="date"
          value-format="x"
          placeholder="选择同步时间"
        />
      </el-form-item>
      <el-form-item label="办结时间" prop="finishTime">
        <el-date-picker
          v-model="formData.finishTime"
          type="date"
          value-format="x"
          placeholder="选择办结时间"
        />
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
import { PublicHotlineLinkApi, PublicHotlineLinkVO } from '@/api/dataHub/publicService/publichotlinelink'

/** 热线对接 表单 */
defineOptions({ name: 'PublicHotlineLinkForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  hotlineId: undefined,
  hotlineNo: undefined,
  platformNo: undefined,
  appealTypeCode: undefined,
  appealTypeName: undefined,
  areaCode: undefined,
  areaName: undefined,
  appealContent: undefined,
  contactName: undefined,
  contactPhone: undefined,
  hotlineSource: undefined,
  syncStatus: undefined,
  handleStatus: undefined,
  acceptDeptCode: undefined,
  acceptDeptName: undefined,
  handleResult: undefined,
  syncTime: undefined,
  finishTime: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  hotlineId: [{ required: true, message: '热线ID不能为空', trigger: 'blur' }],
  hotlineNo: [{ required: true, message: '热线单号不能为空', trigger: 'blur' }],
  platformNo: [{ required: true, message: '平台单号不能为空', trigger: 'blur' }],
  appealTypeCode: [{ required: true, message: '诉求类型编码不能为空', trigger: 'blur' }],
  appealTypeName: [{ required: true, message: '诉求类型名称不能为空', trigger: 'blur' }],
  areaCode: [{ required: true, message: '事发区域编码不能为空', trigger: 'blur' }],
  areaName: [{ required: true, message: '事发区域名称不能为空', trigger: 'blur' }],
  appealContent: [{ required: true, message: '诉求内容不能为空', trigger: 'blur' }],
  contactName: [{ required: true, message: '联系人姓名不能为空', trigger: 'blur' }],
  contactPhone: [{ required: true, message: '联系人电话不能为空', trigger: 'blur' }],
  hotlineSource: [{ required: true, message: '热线来源不能为空', trigger: 'blur' }],
  syncStatus: [{ required: true, message: '同步状态不能为空', trigger: 'blur' }],
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
      formData.value = await PublicHotlineLinkApi.getPublicHotlineLink(id)
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
    const data = formData.value as unknown as PublicHotlineLinkVO
    if (formType.value === 'create') {
      await PublicHotlineLinkApi.createPublicHotlineLink(data)
      message.success(t('common.createSuccess'))
    } else {
      await PublicHotlineLinkApi.updatePublicHotlineLink(data)
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
    hotlineId: undefined,
    hotlineNo: undefined,
    platformNo: undefined,
    appealTypeCode: undefined,
    appealTypeName: undefined,
    areaCode: undefined,
    areaName: undefined,
    appealContent: undefined,
    contactName: undefined,
    contactPhone: undefined,
    hotlineSource: undefined,
    syncStatus: undefined,
    handleStatus: undefined,
    acceptDeptCode: undefined,
    acceptDeptName: undefined,
    handleResult: undefined,
    syncTime: undefined,
    finishTime: undefined,
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
