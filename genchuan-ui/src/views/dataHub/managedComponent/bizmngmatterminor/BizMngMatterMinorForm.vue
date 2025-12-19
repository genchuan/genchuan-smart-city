<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="管理事项小类ID" prop="mngMatterMinorId">
        <el-input v-model="formData.mngMatterMinorId" placeholder="请输入管理事项小类ID" />
      </el-form-item>
      <el-form-item label="所属大类ID" prop="parentMajorId">
        <el-input v-model="formData.parentMajorId" placeholder="请输入所属大类ID" />
      </el-form-item>
      <el-form-item label="所属大类名称" prop="parentMajorName">
        <el-input v-model="formData.parentMajorName" placeholder="请输入所属大类名称" />
      </el-form-item>
      <el-form-item label="小类代码" prop="matterMinorCode">
        <el-input v-model="formData.matterMinorCode" placeholder="请输入小类代码" />
      </el-form-item>
      <el-form-item label="小类名称" prop="matterMinorName">
        <el-input v-model="formData.matterMinorName" placeholder="请输入小类名称" />
      </el-form-item>
      <el-form-item label="小类说明" prop="matterMinorDesc">
        <el-input v-model="formData.matterMinorDesc" placeholder="请输入小类说明" />
      </el-form-item>
      <el-form-item label="主管部门代码" prop="deptCode">
        <el-input v-model="formData.deptCode" placeholder="请输入主管部门代码" />
      </el-form-item>
      <el-form-item label="主管部门名称" prop="deptName">
        <el-input v-model="formData.deptName" placeholder="请输入主管部门名称" />
      </el-form-item>
      <el-form-item label="是否扩展类" prop="isExt">
        <el-input v-model="formData.isExt" placeholder="请输入是否扩展类" />
      </el-form-item>
      <el-form-item label="启用状态" prop="enableStatus">
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
import { BizMngMatterMinorApi, BizMngMatterMinorVO } from '@/api/dataHub/managedComponent/bizmngmatterminor'

/** 管理事项小类 表单 */
defineOptions({ name: 'BizMngMatterMinorForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  mngMatterMinorId: undefined,
  parentMajorId: undefined,
  parentMajorName: undefined,
  matterMinorCode: undefined,
  matterMinorName: undefined,
  matterMinorDesc: undefined,
  deptCode: undefined,
  deptName: undefined,
  isExt: undefined,
  enableStatus: undefined,
  createUser: undefined,
  updateUser: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined
})
const formRules = reactive({
  mngMatterMinorId: [{ required: true, message: '管理事项小类ID不能为空', trigger: 'blur' }],
  parentMajorId: [{ required: true, message: '所属大类ID不能为空', trigger: 'blur' }],
  parentMajorName: [{ required: true, message: '所属大类名称不能为空', trigger: 'blur' }],
  matterMinorCode: [{ required: true, message: '小类代码不能为空', trigger: 'blur' }],
  matterMinorName: [{ required: true, message: '小类名称不能为空', trigger: 'blur' }],
  deptCode: [{ required: true, message: '主管部门代码不能为空', trigger: 'blur' }],
  deptName: [{ required: true, message: '主管部门名称不能为空', trigger: 'blur' }],
  isExt: [{ required: true, message: '是否扩展类不能为空', trigger: 'blur' }],
  enableStatus: [{ required: true, message: '启用状态不能为空', trigger: 'blur' }],
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
      formData.value = await BizMngMatterMinorApi.getBizMngMatterMinor(id)
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
    const data = formData.value as unknown as BizMngMatterMinorVO
    if (formType.value === 'create') {
      await BizMngMatterMinorApi.createBizMngMatterMinor(data)
      message.success(t('common.createSuccess'))
    } else {
      await BizMngMatterMinorApi.updateBizMngMatterMinor(data)
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
    mngMatterMinorId: undefined,
    parentMajorId: undefined,
    parentMajorName: undefined,
    matterMinorCode: undefined,
    matterMinorName: undefined,
    matterMinorDesc: undefined,
    deptCode: undefined,
    deptName: undefined,
    isExt: undefined,
    enableStatus: undefined,
    createUser: undefined,
    updateUser: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined
  }
  formRef.value?.resetFields()
}
</script>
