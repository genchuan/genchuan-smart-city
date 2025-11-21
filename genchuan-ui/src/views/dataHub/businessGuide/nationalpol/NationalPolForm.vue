<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="政策ID" prop="nationalPolId">
        <el-input v-model="formData.nationalPolId" placeholder="请输入政策ID" />
      </el-form-item>
      <el-form-item label="政策标题" prop="polTitle">
        <el-input v-model="formData.polTitle" placeholder="请输入政策标题" />
      </el-form-item>
      <el-form-item label="政策文号" prop="polDocNo">
        <el-input v-model="formData.polDocNo" placeholder="请输入政策文号" />
      </el-form-item>
      <el-form-item label="发布部门代码" prop="issueDeptCode">
        <el-input v-model="formData.issueDeptCode" placeholder="请输入发布部门代码" />
      </el-form-item>
      <el-form-item label="发布部门名称" prop="issueDeptName">
        <el-input v-model="formData.issueDeptName" placeholder="请输入发布部门名称" />
      </el-form-item>
      <el-form-item label="发布时间" prop="issueTime">
        <el-date-picker
          v-model="formData.issueTime"
          type="date"
          value-format="x"
          placeholder="选择发布时间"
        />
      </el-form-item>
      <el-form-item label="实施时间" prop="implementTime">
        <el-date-picker
          v-model="formData.implementTime"
          type="date"
          value-format="x"
          placeholder="选择实施时间"
        />
      </el-form-item>
      <el-form-item label="政策文件ID" prop="polFileId">
        <el-input v-model="formData.polFileId" placeholder="请输入政策文件ID" />
      </el-form-item>
      <el-form-item label="政策文件路径" prop="polFilePath">
        <el-input v-model="formData.polFilePath" placeholder="请输入政策文件路径" />
      </el-form-item>
      <el-form-item label="政策状态" prop="polStatus">
        <el-radio-group v-model="formData.polStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="政策摘要" prop="polSummary">
        <el-input v-model="formData.polSummary" placeholder="请输入政策摘要" />
      </el-form-item>
      <el-form-item label="创建人" prop="createUser">
        <el-input v-model="formData.createUser" placeholder="请输入创建人" />
      </el-form-item>
      <el-form-item label="更新人" prop="updateUser">
        <el-input v-model="formData.updateUser" placeholder="请输入更新人" />
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
import { NationalPolApi, NationalPolVO } from '@/api/dataHub/businessGuide/nationalpol'

/** 国家政策信息 表单 */
defineOptions({ name: 'NationalPolForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  nationalPolId: undefined,
  polTitle: undefined,
  polDocNo: undefined,
  issueDeptCode: undefined,
  issueDeptName: undefined,
  issueTime: undefined,
  implementTime: undefined,
  polFileId: undefined,
  polFilePath: undefined,
  polStatus: undefined,
  polSummary: undefined,
  createUser: undefined,
  updateUser: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined
})
const formRules = reactive({
  nationalPolId: [{ required: true, message: '政策ID不能为空', trigger: 'blur' }],
  polTitle: [{ required: true, message: '政策标题不能为空', trigger: 'blur' }],
  issueDeptCode: [{ required: true, message: '发布部门代码不能为空', trigger: 'blur' }],
  issueDeptName: [{ required: true, message: '发布部门名称不能为空', trigger: 'blur' }],
  issueTime: [{ required: true, message: '发布时间不能为空', trigger: 'blur' }],
  polFileId: [{ required: true, message: '政策文件ID不能为空', trigger: 'blur' }],
  polFilePath: [{ required: true, message: '政策文件路径不能为空', trigger: 'blur' }],
  polStatus: [{ required: true, message: '政策状态不能为空', trigger: 'blur' }],
  createUser: [{ required: true, message: '创建人不能为空', trigger: 'blur' }]
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
      formData.value = await NationalPolApi.getNationalPol(id)
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
    const data = formData.value as unknown as NationalPolVO
    if (formType.value === 'create') {
      await NationalPolApi.createNationalPol(data)
      message.success(t('common.createSuccess'))
    } else {
      await NationalPolApi.updateNationalPol(data)
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
    nationalPolId: undefined,
    polTitle: undefined,
    polDocNo: undefined,
    issueDeptCode: undefined,
    issueDeptName: undefined,
    issueTime: undefined,
    implementTime: undefined,
    polFileId: undefined,
    polFilePath: undefined,
    polStatus: undefined,
    polSummary: undefined,
    createUser: undefined,
    updateUser: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined
  }
  formRef.value?.resetFields()
}
</script>
