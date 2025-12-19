<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="咨询ID" prop="consultId">
        <el-input v-model="formData.consultId" placeholder="请输入咨询ID" />
      </el-form-item>
      <el-form-item label="咨询单号" prop="consultNo">
        <el-input v-model="formData.consultNo" placeholder="请输入咨询单号" />
      </el-form-item>
      <el-form-item label="咨询类型编码" prop="consultTypeCode">
        <el-input v-model="formData.consultTypeCode" placeholder="请输入咨询类型编码" />
      </el-form-item>
      <el-form-item label="咨询类型名称" prop="consultTypeName">
        <el-input v-model="formData.consultTypeName" placeholder="请输入咨询类型名称" />
      </el-form-item>
      <el-form-item label="咨询内容" prop="consultContent">
        <Editor v-model="formData.consultContent" height="150px" />
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
      <el-form-item label="回复内容" prop="replyContent">
        <Editor v-model="formData.replyContent" height="150px" />
      </el-form-item>
      <el-form-item label="咨询状态" prop="consultStatus">
        <el-radio-group v-model="formData.consultStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="提交时间" prop="submitTime">
        <el-date-picker
          v-model="formData.submitTime"
          type="date"
          value-format="x"
          placeholder="选择提交时间"
        />
      </el-form-item>
      <el-form-item label="回复时间" prop="replyTime">
        <el-date-picker
          v-model="formData.replyTime"
          type="date"
          value-format="x"
          placeholder="选择回复时间"
        />
      </el-form-item>
      <el-form-item label="备注，补充说明" prop="remark">
        <el-input v-model="formData.remark" placeholder="请输入备注，补充说明" />
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
import { PublicConsultationApi, PublicConsultationVO } from '@/api/dataHub/publicService/publicconsultation'

/** 咨询建议 表单 */
defineOptions({ name: 'PublicConsultationForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  consultId: undefined,
  consultNo: undefined,
  consultTypeCode: undefined,
  consultTypeName: undefined,
  consultContent: undefined,
  contactName: undefined,
  contactPhone: undefined,
  acceptDeptCode: undefined,
  acceptDeptName: undefined,
  replyContent: undefined,
  consultStatus: undefined,
  submitTime: undefined,
  replyTime: undefined,
  remark: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  consultId: [{ required: true, message: '咨询ID不能为空', trigger: 'blur' }],
  consultNo: [{ required: true, message: '咨询单号不能为空', trigger: 'blur' }],
  consultTypeCode: [{ required: true, message: '咨询类型编码不能为空', trigger: 'blur' }],
  consultTypeName: [{ required: true, message: '咨询类型名称不能为空', trigger: 'blur' }],
  consultContent: [{ required: true, message: '咨询内容不能为空', trigger: 'blur' }],
  contactName: [{ required: true, message: '联系人姓名不能为空', trigger: 'blur' }],
  contactPhone: [{ required: true, message: '联系人电话不能为空', trigger: 'blur' }],
  consultStatus: [{ required: true, message: '咨询状态不能为空', trigger: 'blur' }],
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
      formData.value = await PublicConsultationApi.getPublicConsultation(id)
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
    const data = formData.value as unknown as PublicConsultationVO
    if (formType.value === 'create') {
      await PublicConsultationApi.createPublicConsultation(data)
      message.success(t('common.createSuccess'))
    } else {
      await PublicConsultationApi.updatePublicConsultation(data)
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
    consultId: undefined,
    consultNo: undefined,
    consultTypeCode: undefined,
    consultTypeName: undefined,
    consultContent: undefined,
    contactName: undefined,
    contactPhone: undefined,
    acceptDeptCode: undefined,
    acceptDeptName: undefined,
    replyContent: undefined,
    consultStatus: undefined,
    submitTime: undefined,
    replyTime: undefined,
    remark: undefined,
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
