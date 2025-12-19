<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="扩展ID" prop="mngCompExtId">
        <el-input v-model="formData.mngCompExtId" placeholder="请输入扩展ID" />
      </el-form-item>
      <el-form-item label="归属大类ID" prop="majorId">
        <el-input v-model="formData.majorId" placeholder="请输入归属大类ID" />
      </el-form-item>
      <el-form-item label="归属大类名称" prop="majorName">
        <el-input v-model="formData.majorName" placeholder="请输入归属大类名称" />
      </el-form-item>
      <el-form-item label="扩展小类代码" prop="extMinorCode">
        <el-input v-model="formData.extMinorCode" placeholder="请输入扩展小类代码" />
      </el-form-item>
      <el-form-item label="扩展小类名称" prop="extMinorName">
        <el-input v-model="formData.extMinorName" placeholder="请输入扩展小类名称" />
      </el-form-item>
      <el-form-item label="扩展小类说明" prop="extMinorDesc">
        <el-input v-model="formData.extMinorDesc" placeholder="请输入扩展小类说明" />
      </el-form-item>
      <el-form-item label="建议主管部门代码" prop="suggestDeptCode">
        <el-input v-model="formData.suggestDeptCode" placeholder="请输入建议主管部门代码" />
      </el-form-item>
      <el-form-item label="建议主管部门名称" prop="suggestDeptName">
        <el-input v-model="formData.suggestDeptName" placeholder="请输入建议主管部门名称" />
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
import { BizMngCompExtApi, BizMngCompExtVO } from '@/api/dataHub/managedComponent/bizmngcompext'

/** 管理部件扩展管理部件配置 表单 */
defineOptions({ name: 'BizMngCompExtForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  mngCompExtId: undefined,
  majorId: undefined,
  majorName: undefined,
  extMinorCode: undefined,
  extMinorName: undefined,
  extMinorDesc: undefined,
  suggestDeptCode: undefined,
  suggestDeptName: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined
})
const formRules = reactive({
  mngCompExtId: [{ required: true, message: '扩展ID不能为空', trigger: 'blur' }],
  majorId: [{ required: true, message: '归属大类ID不能为空', trigger: 'blur' }],
  majorName: [{ required: true, message: '归属大类名称不能为空', trigger: 'blur' }],
  extMinorCode: [{ required: true, message: '扩展小类代码不能为空', trigger: 'blur' }],
  extMinorName: [{ required: true, message: '扩展小类名称不能为空', trigger: 'blur' }],
  extMinorDesc: [{ required: true, message: '扩展小类说明不能为空', trigger: 'blur' }]
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
      formData.value = await BizMngCompExtApi.getBizMngCompExt(id)
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
    const data = formData.value as unknown as BizMngCompExtVO
    if (formType.value === 'create') {
      await BizMngCompExtApi.createBizMngCompExt(data)
      message.success(t('common.createSuccess'))
    } else {
      await BizMngCompExtApi.updateBizMngCompExt(data)
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
    mngCompExtId: undefined,
    majorId: undefined,
    majorName: undefined,
    extMinorCode: undefined,
    extMinorName: undefined,
    extMinorDesc: undefined,
    suggestDeptCode: undefined,
    suggestDeptName: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined
  }
  formRef.value?.resetFields()
}
</script>
