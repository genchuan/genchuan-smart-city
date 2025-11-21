<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="物资ID" prop="matId">
        <el-input v-model="formData.matId" placeholder="请输入物资ID" />
      </el-form-item>
      <el-form-item label="物资编码" prop="matCode">
        <el-input v-model="formData.matCode" placeholder="请输入物资编码" />
      </el-form-item>
      <el-form-item label="物资名称" prop="matName">
        <el-input v-model="formData.matName" placeholder="请输入物资名称" />
      </el-form-item>
      <el-form-item label="分类ID" prop="catId">
        <el-input v-model="formData.catId" placeholder="请输入分类ID" />
      </el-form-item>
      <el-form-item label="分类名称" prop="catName">
        <el-input v-model="formData.catName" placeholder="请输入分类名称" />
      </el-form-item>
      <el-form-item label="适用区域代码" prop="applyRegionCode">
        <el-input v-model="formData.applyRegionCode" placeholder="请输入适用区域代码" />
      </el-form-item>
      <el-form-item label="适用区域名称" prop="applyRegionName">
        <el-input v-model="formData.applyRegionName" placeholder="请输入适用区域名称" />
      </el-form-item>
      <el-form-item label="物资规格" prop="matSpec">
        <el-input v-model="formData.matSpec" placeholder="请输入物资规格" />
      </el-form-item>
      <el-form-item label="物资单位" prop="matUnit">
        <el-input v-model="formData.matUnit" placeholder="请输入物资单位" />
      </el-form-item>
      <el-form-item label="总数量" prop="totalQuantity">
        <el-input v-model="formData.totalQuantity" placeholder="请输入总数量" />
      </el-form-item>
      <el-form-item label="可用数量" prop="availableQuantity">
        <el-input v-model="formData.availableQuantity" placeholder="请输入可用数量" />
      </el-form-item>
      <el-form-item label="物资状态" prop="matStatus">
        <el-select v-model="formData.matStatus" placeholder="请选择物资状态">
          <el-option label="正常" value="1" />
          <el-option label="异常" value="0" />
        </el-select>
      </el-form-item>
      <el-form-item label="创建人" prop="createUser">
        <el-input v-model="formData.createUser" placeholder="请输入创建人" />
      </el-form-item>
      <el-form-item label="更新人" prop="updateUser">
        <el-input v-model="formData.updateUser" placeholder="请输入更新人" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { EmerMatInfoApi, EmerMatInfoVO } from '@/api/dataHub/commandAndCoordination/emermatinfo'

/** 应急物资信息 表单 */
defineOptions({ name: 'EmerMatInfoForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  matId: undefined,
  matCode: undefined,
  matName: undefined,
  catId: undefined,
  catName: undefined,
  applyRegionCode: undefined,
  applyRegionName: undefined,
  matSpec: undefined,
  matUnit: undefined,
  totalQuantity: undefined,
  availableQuantity: undefined,
  matStatus: undefined,
  createUser: undefined,
  updateUser: undefined,
})
const formRules = reactive({
  matId: [{ required: true, message: '物资ID不能为空', trigger: 'blur' }],
  matCode: [{ required: true, message: '物资编码不能为空', trigger: 'blur' }],
  matName: [{ required: true, message: '物资名称不能为空', trigger: 'blur' }],
  catId: [{ required: true, message: '分类ID不能为空', trigger: 'blur' }],
  catName: [{ required: true, message: '分类名称不能为空', trigger: 'blur' }],
  applyRegionCode: [{ required: true, message: '适用区域代码不能为空', trigger: 'blur' }],
  applyRegionName: [{ required: true, message: '适用区域名称不能为空', trigger: 'blur' }],
  totalQuantity: [{ required: true, message: '总数量不能为空', trigger: 'blur' }],
  availableQuantity: [{ required: true, message: '可用数量不能为空', trigger: 'blur' }],
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
      formData.value = await EmerMatInfoApi.getEmerMatInfo(id)
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
    const data = formData.value as unknown as EmerMatInfoVO
    if (formType.value === 'create') {
      await EmerMatInfoApi.createEmerMatInfo(data)
      message.success(t('common.createSuccess'))
    } else {
      await EmerMatInfoApi.updateEmerMatInfo(data)
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
    matId: undefined,
    matCode: undefined,
    matName: undefined,
    catId: undefined,
    catName: undefined,
    applyRegionCode: undefined,
    applyRegionName: undefined,
    matSpec: undefined,
    matUnit: undefined,
    totalQuantity: undefined,
    availableQuantity: undefined,
    matStatus: undefined,
    createUser: undefined,
    updateUser: undefined,
  }
  formRef.value?.resetFields()
}
</script>
