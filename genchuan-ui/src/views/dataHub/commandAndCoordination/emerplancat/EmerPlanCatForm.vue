<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="分类ID" prop="planCatId">
        <el-input v-model="formData.planCatId" placeholder="请输入分类ID" />
      </el-form-item>
      <el-form-item label="父分类ID" prop="parentCatId">
        <el-input v-model="formData.parentCatId" placeholder="请输入父分类ID" />
      </el-form-item>
      <el-form-item label="分类层级" prop="catLevel">
        <el-input v-model="formData.catLevel" placeholder="请输入分类层级" />
      </el-form-item>
      <el-form-item label="分类编码" prop="catCode">
        <el-input v-model="formData.catCode" placeholder="请输入分类编码" />
      </el-form-item>
      <el-form-item label="分类名称" prop="catName">
        <el-input v-model="formData.catName" placeholder="请输入分类名称" />
      </el-form-item>
      <el-form-item label="分类说明" prop="catDesc">
        <el-input v-model="formData.catDesc" placeholder="请输入分类说明" />
      </el-form-item>
      <el-form-item label="适用区域代码" prop="applyRegionCode">
        <el-input v-model="formData.applyRegionCode" placeholder="请输入适用区域代码" />
      </el-form-item>
      <el-form-item label="适用区域名称" prop="applyRegionName">
        <el-input v-model="formData.applyRegionName" placeholder="请输入适用区域名称" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { EmerPlanCatApi, EmerPlanCatVO } from '@/api/dataHub/commandAndCoordination/emerplancat'

/** 预案分类配置 表单 */
defineOptions({ name: 'EmerPlanCatForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  planCatId: undefined,
  parentCatId: undefined,
  catLevel: undefined,
  catCode: undefined,
  catName: undefined,
  catDesc: undefined,
  applyRegionCode: undefined,
  applyRegionName: undefined,
})
const formRules = reactive({
  planCatId: [{ required: true, message: '分类ID不能为空', trigger: 'blur' }],
  parentCatId: [{ required: true, message: '父分类ID不能为空', trigger: 'blur' }],
  catLevel: [{ required: true, message: '分类层级不能为空', trigger: 'blur' }],
  catCode: [{ required: true, message: '分类编码不能为空', trigger: 'blur' }],
  catName: [{ required: true, message: '分类名称不能为空', trigger: 'blur' }],
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
      formData.value = await EmerPlanCatApi.getEmerPlanCat(id)
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
    const data = formData.value as unknown as EmerPlanCatVO
    if (formType.value === 'create') {
      await EmerPlanCatApi.createEmerPlanCat(data)
      message.success(t('common.createSuccess'))
    } else {
      await EmerPlanCatApi.updateEmerPlanCat(data)
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
    planCatId: undefined,
    parentCatId: undefined,
    catLevel: undefined,
    catCode: undefined,
    catName: undefined,
    catDesc: undefined,
    applyRegionCode: undefined,
    applyRegionName: undefined,
  }
  formRef.value?.resetFields()
}
</script>
