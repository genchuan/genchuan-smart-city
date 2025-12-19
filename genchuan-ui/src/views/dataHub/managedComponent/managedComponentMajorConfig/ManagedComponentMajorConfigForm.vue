<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="大类ID" prop="majorId">
        <el-input v-model="formData.majorId" placeholder="请输入大类ID" />
      </el-form-item>
      <el-form-item label="大类代码" prop="majorCode">
        <el-input v-model="formData.majorCode" placeholder="请输入大类代码" />
      </el-form-item>
      <el-form-item label="大类名称" prop="majorName">
        <el-input v-model="formData.majorName" placeholder="请输入大类名称" />
      </el-form-item>
      <el-form-item label="大类说明" prop="majorDesc">
        <el-input v-model="formData.majorDesc" placeholder="请输入大类说明" />
      </el-form-item>
      <el-form-item label="排序序号" prop="sortNum">
        <el-input v-model="formData.sortNum" placeholder="请输入排序序号" />
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
      <el-form-item label="扩展字段2" prop="extField2">
        <el-input v-model="formData.extField2" placeholder="请输入扩展字段2" />
      </el-form-item>
      <el-form-item label="扩展字段3" prop="extField3">
        <el-input v-model="formData.extField3" placeholder="请输入扩展字段3" />
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
import { ManagedComponentMajorConfigApi, ManagedComponentMajorConfigVO } from '@/api/dataHub/managedComponent/managedcomponentmajorconfig'

/** 管理部件大类配置表 表单 */
defineOptions({ name: 'ManagedComponentMajorConfigForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  majorId: undefined,
  majorCode: undefined,
  majorName: undefined,
  majorDesc: undefined,
  sortNum: undefined,
  createUser: undefined,
  updateUser: undefined,
  extField1: undefined,
  extField2: undefined,
  extField3: undefined,
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
      formData.value = await ManagedComponentMajorConfigApi.getManagedComponentMajorConfig(id)
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
    const data = formData.value as unknown as ManagedComponentMajorConfigVO
    if (formType.value === 'create') {
      await ManagedComponentMajorConfigApi.createManagedComponentMajorConfig(data)
      message.success(t('common.createSuccess'))
    } else {
      await ManagedComponentMajorConfigApi.updateManagedComponentMajorConfig(data)
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
    majorId: undefined,
    majorCode: undefined,
    majorName: undefined,
    majorDesc: undefined,
    sortNum: undefined,
    createUser: undefined,
    updateUser: undefined,
    extField1: undefined,
    extField2: undefined,
    extField3: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined
  }
  formRef.value?.resetFields()
}
</script>
