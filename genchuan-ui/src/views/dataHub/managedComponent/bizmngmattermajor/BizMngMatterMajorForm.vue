<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="管理事项大类ID" prop="mngMatterMajorId">
        <el-input v-model="formData.mngMatterMajorId" placeholder="请输入管理事项大类ID" />
      </el-form-item>
      <el-form-item label="大类代码" prop="matterMajorCode">
        <el-input v-model="formData.matterMajorCode" placeholder="请输入大类代码" />
      </el-form-item>
      <el-form-item label="大类名称" prop="matterMajorName">
        <el-input v-model="formData.matterMajorName" placeholder="请输入大类名称" />
      </el-form-item>
      <el-form-item label="大类说明" prop="matterMajorDesc">
        <el-input v-model="formData.matterMajorDesc" placeholder="请输入大类说明" />
      </el-form-item>
      <el-form-item label="排序序号" prop="sortNum">
        <el-input v-model="formData.sortNum" placeholder="请输入排序序号" />
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
import { BizMngMatterMajorApi, BizMngMatterMajorVO } from '@/api/dataHub/managedComponent/bizmngmattermajor'

/** 管理事项大类 表单 */
defineOptions({ name: 'BizMngMatterMajorForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  mngMatterMajorId: undefined,
  matterMajorCode: undefined,
  matterMajorName: undefined,
  matterMajorDesc: undefined,
  sortNum: undefined,
  enableStatus: undefined,
  createUser: undefined,
  updateUser: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined
})
const formRules = reactive({
  mngMatterMajorId: [{ required: true, message: '管理事项大类ID不能为空', trigger: 'blur' }],
  matterMajorCode: [{ required: true, message: '大类代码不能为空', trigger: 'blur' }],
  matterMajorName: [{ required: true, message: '大类名称不能为空', trigger: 'blur' }],
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
      formData.value = await BizMngMatterMajorApi.getBizMngMatterMajor(id)
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
    const data = formData.value as unknown as BizMngMatterMajorVO
    if (formType.value === 'create') {
      await BizMngMatterMajorApi.createBizMngMatterMajor(data)
      message.success(t('common.createSuccess'))
    } else {
      await BizMngMatterMajorApi.updateBizMngMatterMajor(data)
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
    mngMatterMajorId: undefined,
    matterMajorCode: undefined,
    matterMajorName: undefined,
    matterMajorDesc: undefined,
    sortNum: undefined,
    enableStatus: undefined,
    createUser: undefined,
    updateUser: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined
  }
  formRef.value?.resetFields()
}
</script>
