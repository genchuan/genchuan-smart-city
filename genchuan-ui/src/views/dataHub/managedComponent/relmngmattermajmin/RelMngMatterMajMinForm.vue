<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="290px"
      v-loading="formLoading"
    >
      <el-form-item label="关联ID">
        <el-input v-model="formData.mngMatterMajorMinorId" placeholder="请输入关联ID" />
      </el-form-item>
      <el-form-item label="关联管理事项大类ID" prop="majorId">
        <el-input v-model="formData.majorId" placeholder="请输入关联管理事项大类ID" />
      </el-form-item>
      <el-form-item label="关联管理事项大类名称" prop="majorName">
        <el-input v-model="formData.majorName" placeholder="请输入关联管理事项大类名称" />
      </el-form-item>
      <el-form-item label="关联管理事项小类ID" prop="minorId">
        <el-input v-model="formData.minorId" placeholder="请输入关联管理事项小类ID" />
      </el-form-item>
      <el-form-item label="关联管理事项小类名称" prop="minorName">
        <el-input v-model="formData.minorName" placeholder="请输入关联管理事项小类名称" />
      </el-form-item>
      <el-form-item label="关联状态，1（有效）/0（无效）" prop="relStatus">
        <el-radio-group v-model="formData.relStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
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
import { RelMngMatterMajMinApi, RelMngMatterMajMinVO } from '@/api/dataHub/managedComponent/relmngmattermajmin'

/** 管理事项大小类关联 表单 */
defineOptions({ name: 'RelMngMatterMajMinForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  mngMatterMajorMinorId: undefined,
  majorId: undefined,
  majorName: undefined,
  minorId: undefined,
  minorName: undefined,
  relStatus: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  mngMatterMajorMinorId: [{ required: true, message: '关联ID', trigger: 'blur' }],
  majorId: [{ required: true, message: '关联管理事项大类ID', trigger: 'blur' }],
  majorName: [{ required: true, message: '关联管理事项大类名称', trigger: 'blur' }],
  minorId: [{ required: true, message: '关联管理事项小类ID', trigger: 'blur' }],
  minorName: [{ required: true, message: '关联管理事项小类名称', trigger: 'blur' }],
  relStatus: [{ required: true, message: '关联状态', trigger: 'blur' }],
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
      formData.value = await RelMngMatterMajMinApi.getRelMngMatterMajMin(id)
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
    const data = formData.value as unknown as RelMngMatterMajMinVO
    if (formType.value === 'create') {
      await RelMngMatterMajMinApi.createRelMngMatterMajMin(data)
      message.success(t('common.createSuccess'))
    } else {
      await RelMngMatterMajMinApi.updateRelMngMatterMajMin(data)
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
    mngMatterMajorMinorId: undefined,
    majorId: undefined,
    majorName: undefined,
    minorId: undefined,
    minorName: undefined,
    relStatus: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined,
  }
  formRef.value?.resetFields()
}
</script>
