<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="动态ID" prop="domesticDynamicId">
        <el-input v-model="formData.domesticDynamicId" placeholder="请输入动态ID" />
      </el-form-item>
      <el-form-item label="动态标题" prop="dynamicTitle">
        <el-input v-model="formData.dynamicTitle" placeholder="请输入动态标题" />
      </el-form-item>
      <el-form-item label="动态标签" prop="dynamicTag">
        <el-input v-model="formData.dynamicTag" placeholder="请输入动态标签" />
      </el-form-item>
      <el-form-item label="动态来源" prop="dynamicSource">
        <el-input v-model="formData.dynamicSource" placeholder="请输入动态来源" />
      </el-form-item>
      <el-form-item label="发布时间" prop="releaseTime">
        <el-date-picker
          v-model="formData.releaseTime"
          type="date"
          value-format="x"
          placeholder="选择发布时间"
        />
      </el-form-item>
      <el-form-item label="动态详细内容" prop="dynamicDetail">
        <el-input v-model="formData.dynamicDetail" placeholder="请输入动态详细内容" />
      </el-form-item>
      <el-form-item label="封面图片ID" prop="coverImgId">
        <el-input v-model="formData.coverImgId" placeholder="请输入封面图片ID" />
      </el-form-item>
      <el-form-item label="封面图片路径" prop="coverImgPath">
        <el-input v-model="formData.coverImgPath" placeholder="请输入封面图片路径" />
      </el-form-item>
      <el-form-item label="动态状态" prop="dynamicStatus">
        <el-radio-group v-model="formData.dynamicStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
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
import { DomesticDynamicApi, DomesticDynamicVO } from '@/api/dataHub/businessGuide/domesticdynamic'

/** 国内行业动态 表单 */
defineOptions({ name: 'DomesticDynamicForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  domesticDynamicId: undefined,
  dynamicTitle: undefined,
  dynamicTag: undefined,
  dynamicSource: undefined,
  releaseTime: undefined,
  dynamicDetail: undefined,
  coverImgId: undefined,
  coverImgPath: undefined,
  dynamicStatus: undefined,
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
  domesticDynamicId: [{ required: true, message: '动态ID不能为空', trigger: 'blur' }],
  dynamicTitle: [{ required: true, message: '动态标题不能为空', trigger: 'blur' }],
  dynamicTag: [{ required: true, message: '动态标签不能为空', trigger: 'blur' }],
  dynamicSource: [{ required: true, message: '动态来源不能为空', trigger: 'blur' }],
  releaseTime: [{ required: true, message: '发布时间不能为空', trigger: 'blur' }],
  dynamicDetail: [{ required: true, message: '动态详细内容不能为空', trigger: 'blur' }],
  dynamicStatus: [{ required: true, message: '动态状态不能为空', trigger: 'blur' }],
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
      formData.value = await DomesticDynamicApi.getDomesticDynamic(id)
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
    const data = formData.value as unknown as DomesticDynamicVO
    if (formType.value === 'create') {
      await DomesticDynamicApi.createDomesticDynamic(data)
      message.success(t('common.createSuccess'))
    } else {
      await DomesticDynamicApi.updateDomesticDynamic(data)
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
    domesticDynamicId: undefined,
    dynamicTitle: undefined,
    dynamicTag: undefined,
    dynamicSource: undefined,
    releaseTime: undefined,
    dynamicDetail: undefined,
    coverImgId: undefined,
    coverImgPath: undefined,
    dynamicStatus: undefined,
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
