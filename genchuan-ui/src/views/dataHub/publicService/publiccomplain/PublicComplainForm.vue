<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="投诉ID" prop="complainId">
        <el-input v-model="formData.complainId" placeholder="请输入投诉ID" />
      </el-form-item>
      <el-form-item label="投诉单号" prop="complainNo">
        <el-input v-model="formData.complainNo" placeholder="请输入投诉单号" />
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
      <el-form-item label="事发地址" prop="incidentAddr">
        <el-input v-model="formData.incidentAddr" placeholder="请输入事发地址" />
      </el-form-item>
      <el-form-item label="投诉内容" prop="complainContent">
        <Editor v-model="formData.complainContent" height="150px" />
      </el-form-item>
      <el-form-item label="上传图片路径" prop="imgPath">
        <el-input v-model="formData.imgPath" placeholder="请输入上传图片路径" />
      </el-form-item>
      <el-form-item label="联系人姓名" prop="contactName">
        <el-input v-model="formData.contactName" placeholder="请输入联系人姓名" />
      </el-form-item>
      <el-form-item label="联系人电话" prop="contactPhone">
        <el-input v-model="formData.contactPhone" placeholder="请输入联系人电话" />
      </el-form-item>
      <el-form-item label="受理部门编码" prop="acceptDeptCode">
        <el-input v-model="formData.acceptDeptCode" placeholder="请输入受理部门编码" />
      </el-form-item>
      <el-form-item label="受理部门名称" prop="acceptDeptName">
        <el-input v-model="formData.acceptDeptName" placeholder="请输入受理部门名称" />
      </el-form-item>
      <el-form-item label="处置状态" prop="handleStatus">
        <el-radio-group v-model="formData.handleStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="处置结果" prop="handleResult">
        <el-input v-model="formData.handleResult" placeholder="请输入处置结果" />
      </el-form-item>
      <el-form-item label="提交时间" prop="submitTime">
        <el-date-picker
          v-model="formData.submitTime"
          type="date"
          value-format="x"
          placeholder="选择提交时间"
        />
      </el-form-item>
      <el-form-item label="受理时间" prop="acceptTime">
        <el-date-picker
          v-model="formData.acceptTime"
          type="date"
          value-format="x"
          placeholder="选择受理时间"
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
import { PublicComplainApi, PublicComplainVO } from '@/api/dataHub/publicService/publiccomplain'

/** 投诉举报 表单 */
defineOptions({ name: 'PublicComplainForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  complainId: undefined,
  complainNo: undefined,
  appealTypeCode: undefined,
  appealTypeName: undefined,
  areaCode: undefined,
  areaName: undefined,
  incidentAddr: undefined,
  complainContent: undefined,
  imgPath: undefined,
  contactName: undefined,
  contactPhone: undefined,
  acceptDeptCode: undefined,
  acceptDeptName: undefined,
  handleStatus: undefined,
  handleResult: undefined,
  submitTime: undefined,
  acceptTime: undefined,
  finishTime: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  complainId: [{ required: true, message: '投诉ID不能为空', trigger: 'blur' }],
  complainNo: [{ required: true, message: '投诉单号不能为空', trigger: 'blur' }],
  appealTypeCode: [{ required: true, message: '诉求类型编码不能为空', trigger: 'blur' }],
  appealTypeName: [{ required: true, message: '诉求类型名称不能为空', trigger: 'blur' }],
  areaCode: [{ required: true, message: '事发区域编码不能为空', trigger: 'blur' }],
  areaName: [{ required: true, message: '事发区域名称不能为空', trigger: 'blur' }],
  incidentAddr: [{ required: true, message: '事发地址不能为空', trigger: 'blur' }],
  complainContent: [{ required: true, message: '投诉内容不能为空', trigger: 'blur' }],
  contactName: [{ required: true, message: '联系人姓名不能为空', trigger: 'blur' }],
  contactPhone: [{ required: true, message: '联系人电话不能为空', trigger: 'blur' }],
  handleStatus: [{ required: true, message: '处置状态不能为空', trigger: 'blur' }],
  submitTime: [{ required: true, message: '提交时间不能为空', trigger: 'blur' }],
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
      formData.value = await PublicComplainApi.getPublicComplain(id)
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
    const data = formData.value as unknown as PublicComplainVO
    if (formType.value === 'create') {
      await PublicComplainApi.createPublicComplain(data)
      message.success(t('common.createSuccess'))
    } else {
      await PublicComplainApi.updatePublicComplain(data)
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
    complainId: undefined,
    complainNo: undefined,
    appealTypeCode: undefined,
    appealTypeName: undefined,
    areaCode: undefined,
    areaName: undefined,
    incidentAddr: undefined,
    complainContent: undefined,
    imgPath: undefined,
    contactName: undefined,
    contactPhone: undefined,
    acceptDeptCode: undefined,
    acceptDeptName: undefined,
    handleStatus: undefined,
    handleResult: undefined,
    submitTime: undefined,
    acceptTime: undefined,
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
