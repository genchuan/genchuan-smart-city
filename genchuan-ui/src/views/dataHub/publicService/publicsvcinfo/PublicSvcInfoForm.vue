<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="服务信息ID" prop="svcInfoId">
        <el-input v-model="formData.svcInfoId" placeholder="请输入服务信息ID" />
      </el-form-item>
      <el-form-item label="服务标题" prop="svcTitle">
        <el-input v-model="formData.svcTitle" placeholder="请输入服务标题" />
      </el-form-item>
      <el-form-item label="服务类型编码" prop="svcTypeCode">
        <el-input v-model="formData.svcTypeCode" placeholder="请输入服务类型编码" />
      </el-form-item>
      <el-form-item label="服务类型名称" prop="svcTypeName">
        <el-input v-model="formData.svcTypeName" placeholder="请输入服务类型名称" />
      </el-form-item>
      <el-form-item label="服务内容" prop="svcContent">
        <Editor v-model="formData.svcContent" height="150px" />
      </el-form-item>
      <el-form-item label="服务提供单位" prop="svcProvider">
        <el-input v-model="formData.svcProvider" placeholder="请输入服务提供单位" />
      </el-form-item>
      <el-form-item label="联系电话" prop="contactPhone">
        <el-input v-model="formData.contactPhone" placeholder="请输入联系电话" />
      </el-form-item>
      <el-form-item label="生效时间" prop="effectiveTime">
        <el-date-picker
          v-model="formData.effectiveTime"
          type="date"
          value-format="x"
          placeholder="选择生效时间"
        />
      </el-form-item>
      <el-form-item label="失效时间" prop="expireTime">
        <el-date-picker
          v-model="formData.expireTime"
          type="date"
          value-format="x"
          placeholder="选择失效时间"
        />
      </el-form-item>
      <el-form-item label="发布人ID" prop="publishUserId">
        <el-input v-model="formData.publishUserId" placeholder="请输入发布人ID" />
      </el-form-item>
      <el-form-item label="发布时间" prop="publishTime">
        <el-date-picker
          v-model="formData.publishTime"
          type="date"
          value-format="x"
          placeholder="选择发布时间"
        />
      </el-form-item>
      <el-form-item label="信息状态" prop="infoStatus">
        <el-radio-group v-model="formData.infoStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
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
import { PublicSvcInfoApi, PublicSvcInfoVO } from '@/api/dataHub/publicService/publicsvcinfo'

/** 服务信息发布 表单 */
defineOptions({ name: 'PublicSvcInfoForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  svcInfoId: undefined,
  svcTitle: undefined,
  svcTypeCode: undefined,
  svcTypeName: undefined,
  svcContent: undefined,
  svcProvider: undefined,
  contactPhone: undefined,
  effectiveTime: undefined,
  expireTime: undefined,
  publishUserId: undefined,
  publishTime: undefined,
  infoStatus: undefined,
  remark: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  svcInfoId: [{ required: true, message: '服务信息ID不能为空', trigger: 'blur' }],
  svcTitle: [{ required: true, message: '服务标题不能为空', trigger: 'blur' }],
  svcTypeCode: [{ required: true, message: '服务类型编码不能为空', trigger: 'blur' }],
  svcTypeName: [{ required: true, message: '服务类型名称不能为空', trigger: 'blur' }],
  svcContent: [{ required: true, message: '服务内容不能为空', trigger: 'blur' }],
  svcProvider: [{ required: true, message: '服务提供单位不能为空', trigger: 'blur' }],
  effectiveTime: [{ required: true, message: '生效时间不能为空', trigger: 'blur' }],
  expireTime: [{ required: true, message: '失效时间不能为空', trigger: 'blur' }],
  publishUserId: [{ required: true, message: '发布人ID不能为空', trigger: 'blur' }],
  publishTime: [{ required: true, message: '发布时间不能为空', trigger: 'blur' }],
  infoStatus: [{ required: true, message: '信息状态不能为空', trigger: 'blur' }],
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
      formData.value = await PublicSvcInfoApi.getPublicSvcInfo(id)
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
    const data = formData.value as unknown as PublicSvcInfoVO
    if (formType.value === 'create') {
      await PublicSvcInfoApi.createPublicSvcInfo(data)
      message.success(t('common.createSuccess'))
    } else {
      await PublicSvcInfoApi.updatePublicSvcInfo(data)
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
    svcInfoId: undefined,
    svcTitle: undefined,
    svcTypeCode: undefined,
    svcTypeName: undefined,
    svcContent: undefined,
    svcProvider: undefined,
    contactPhone: undefined,
    effectiveTime: undefined,
    expireTime: undefined,
    publishUserId: undefined,
    publishTime: undefined,
    infoStatus: undefined,
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
