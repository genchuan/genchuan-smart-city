<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="通知ID" prop="noticeId">
        <el-input v-model="formData.noticeId" placeholder="请输入通知ID" />
      </el-form-item>
      <el-form-item label="通知标题" prop="noticeTitle">
        <el-input v-model="formData.noticeTitle" placeholder="请输入通知标题" />
      </el-form-item>
      <el-form-item label="通知内容" prop="noticeContent">
        <Editor v-model="formData.noticeContent" height="150px" />
      </el-form-item>
      <el-form-item label="发布部门编码" prop="deptCode">
        <el-input v-model="formData.deptCode" placeholder="请输入发布部门编码" />
      </el-form-item>
      <el-form-item label="发布部门名称" prop="deptName">
        <el-input v-model="formData.deptName" placeholder="请输入发布部门名称" />
      </el-form-item>
      <el-form-item label="发布人ID" prop="publishUserId">
        <el-input v-model="formData.publishUserId" placeholder="请输入发布人ID" />
      </el-form-item>
      <el-form-item label="发布人姓名" prop="publishUserName">
        <el-input v-model="formData.publishUserName" placeholder="请输入发布人姓名" />
      </el-form-item>
      <el-form-item label="发布时间" prop="publishTime">
        <el-date-picker
          v-model="formData.publishTime"
          type="date"
          value-format="x"
          placeholder="选择发布时间"
        />
      </el-form-item>
      <el-form-item label="通知状态" prop="noticeStatus">
        <el-radio-group v-model="formData.noticeStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
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
      <el-form-item label="附件路径" prop="attachPath">
        <el-input v-model="formData.attachPath" placeholder="请输入附件路径" />
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
import { PublicNoticeApi, PublicNoticeVO } from '@/api/dataHub/publicService/publicnotice'

/** 通知公告发布 表单 */
defineOptions({ name: 'PublicNoticeForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  noticeId: undefined,
  noticeTitle: undefined,
  noticeContent: undefined,
  deptCode: undefined,
  deptName: undefined,
  publishUserId: undefined,
  publishUserName: undefined,
  publishTime: undefined,
  noticeStatus: undefined,
  effectiveTime: undefined,
  expireTime: undefined,
  attachPath: undefined,
  remark: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  noticeId: [{ required: true, message: '通知ID不能为空', trigger: 'blur' }],
  noticeTitle: [{ required: true, message: '通知标题不能为空', trigger: 'blur' }],
  noticeContent: [{ required: true, message: '通知内容不能为空', trigger: 'blur' }],
  deptCode: [{ required: true, message: '发布部门编码不能为空', trigger: 'blur' }],
  deptName: [{ required: true, message: '发布部门名称不能为空', trigger: 'blur' }],
  publishUserId: [{ required: true, message: '发布人ID不能为空', trigger: 'blur' }],
  publishUserName: [{ required: true, message: '发布人姓名不能为空', trigger: 'blur' }],
  publishTime: [{ required: true, message: '发布时间不能为空', trigger: 'blur' }],
  noticeStatus: [{ required: true, message: '通知状态不能为空', trigger: 'blur' }],
  effectiveTime: [{ required: true, message: '生效时间不能为空', trigger: 'blur' }],
  expireTime: [{ required: true, message: '失效时间不能为空', trigger: 'blur' }],
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
      formData.value = await PublicNoticeApi.getPublicNotice(id)
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
    const data = formData.value as unknown as PublicNoticeVO
    if (formType.value === 'create') {
      await PublicNoticeApi.createPublicNotice(data)
      message.success(t('common.createSuccess'))
    } else {
      await PublicNoticeApi.updatePublicNotice(data)
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
    noticeId: undefined,
    noticeTitle: undefined,
    noticeContent: undefined,
    deptCode: undefined,
    deptName: undefined,
    publishUserId: undefined,
    publishUserName: undefined,
    publishTime: undefined,
    noticeStatus: undefined,
    effectiveTime: undefined,
    expireTime: undefined,
    attachPath: undefined,
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
