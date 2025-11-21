<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="违建编号" prop="buildingNumber">
        <el-input v-model="formData.buildingNumber" placeholder="请输入违建编号" />
      </el-form-item>
      <el-form-item label="违建地址" prop="buildingAddress">
        <el-input v-model="formData.buildingAddress" placeholder="请输入违建地址" />
      </el-form-item>
      <el-form-item label="违建面积" prop="constructionArea">
        <el-input v-model="formData.constructionArea" placeholder="请输入违建面积" />
      </el-form-item>
      <el-form-item label="违建结构类型" prop="typesStructures">
        <el-input v-model="formData.typesStructures" placeholder="请输入违建结构类型" />
      </el-form-item>
      <el-form-item label="违建层数" prop="numberFloors">
        <el-input v-model="formData.numberFloors" placeholder="请输入违建层数" />
      </el-form-item>
      <el-form-item label="违建用途" prop="unauthorizedBuildings">
        <el-input v-model="formData.unauthorizedBuildings" placeholder="请输入违建用途" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { BasicIllegalApi, BasicIllegalVO } from '@/api/smartcity/basicillegal'

/** 违建基本信息 表单 */
defineOptions({ name: 'BasicIllegalForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  buildingNumber: undefined,
  buildingAddress: undefined,
  constructionArea: undefined,
  typesStructures: undefined,
  numberFloors: undefined,
  unauthorizedBuildings: undefined
})
const formRules = reactive({
})
const formRef = ref() // 表单 ref

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
      formData.value = await BasicIllegalApi.getBasicIllegal(id)
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
    const data = formData.value as unknown as BasicIllegalVO
    if (formType.value === 'create') {
      await BasicIllegalApi.createBasicIllegal(data)
      message.success(t('common.createSuccess'))
    } else {
      await BasicIllegalApi.updateBasicIllegal(data)
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
    buildingNumber: undefined,
    buildingAddress: undefined,
    constructionArea: undefined,
    typesStructures: undefined,
    numberFloors: undefined,
    unauthorizedBuildings: undefined
  }
  formRef.value?.resetFields()
}
</script>
