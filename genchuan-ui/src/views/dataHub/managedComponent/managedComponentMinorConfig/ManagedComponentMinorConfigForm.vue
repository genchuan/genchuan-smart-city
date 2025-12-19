<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="小类ID" prop="minorId">
        <el-input v-model="formData.minorId" placeholder="请输入小类ID" />
      </el-form-item>
      <el-form-item label="关联的大类ID" prop="majorId">
        <el-input v-model="formData.majorId" placeholder="请输入关联的大类ID" />
      </el-form-item>
      <el-form-item label="小类代码" prop="minorCode">
        <el-input v-model="formData.minorCode" placeholder="请输入小类代码" />
      </el-form-item>
      <el-form-item label="小类名称" prop="minorName">
        <el-input v-model="formData.minorName" placeholder="请输入小类名称" />
      </el-form-item>
      <el-form-item label="小类说明" prop="minorDesc">
        <el-input v-model="formData.minorDesc" placeholder="请输入小类说明" />
      </el-form-item>
      <el-form-item label="主管部门统一社会信用代码" prop="deptCode">
        <el-input v-model="formData.deptCode" placeholder="请输入主管部门统一社会信用代码" />
      </el-form-item>
      <el-form-item label="主管部门全称" prop="deptName">
        <el-input v-model="formData.deptName" placeholder="请输入主管部门全称" />
      </el-form-item>
      <el-form-item label="是否扩展类" prop="isExtend">
        <el-input v-model="formData.isExtend" placeholder="请输入是否扩展类" />
      </el-form-item>
      <el-form-item label="创建人ID" prop="createUser">
        <el-input v-model="formData.createUser" placeholder="请输入创建人ID" />
      </el-form-item>
      <el-form-item label="更新人ID" prop="updateUser">
        <el-input v-model="formData.updateUser" placeholder="请输入更新人ID" />
      </el-form-item>
      <el-form-item label="扩展字段1" prop="extField1">
        <el-input v-model="formData.extField1" placeholder="请输入扩展字段1" />
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
import { ManagedComponentMinorConfigApi, ManagedComponentMinorConfigVO } from '@/api/dataHub/managedComponent/managedcomponentminorconfig'

/** 管理部件小类配置 表单 */
defineOptions({ name: 'ManagedComponentMinorConfigForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  minorId: undefined,
  majorId: undefined,
  minorCode: undefined,
  minorName: undefined,
  minorDesc: undefined,
  deptCode: undefined,
  deptName: undefined,
  isExtend: undefined,
  createUser: undefined,
  updateUser: undefined,
  extField1: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined
})
const formRules = reactive({
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
      formData.value = await ManagedComponentMinorConfigApi.getManagedComponentMinorConfig(id)
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
    const data = formData.value as unknown as ManagedComponentMinorConfigVO
    if (formType.value === 'create') {
      await ManagedComponentMinorConfigApi.createManagedComponentMinorConfig(data)
      message.success(t('common.createSuccess'))
    } else {
      await ManagedComponentMinorConfigApi.updateManagedComponentMinorConfig(data)
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
    minorId: undefined,
    majorId: undefined,
    minorCode: undefined,
    minorName: undefined,
    minorDesc: undefined,
    deptCode: undefined,
    deptName: undefined,
    isExtend: undefined,
    createUser: undefined,
    updateUser: undefined,
    extField1: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined
  }
  formRef.value?.resetFields()
}
</script>
