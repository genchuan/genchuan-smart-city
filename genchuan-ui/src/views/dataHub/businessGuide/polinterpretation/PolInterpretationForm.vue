<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="解读ID" prop="polInterpretationId">
        <el-input v-model="formData.polInterpretationId" placeholder="请输入解读ID" />
      </el-form-item>
      <el-form-item label="关联政策类型" prop="relatedPolType">
        <el-select v-model="formData.relatedPolType" placeholder="请选择关联政策类型">
          <el-option label="请选择字典生成" value="1" />
        </el-select>
      </el-form-item>
      <el-form-item label="关联政策ID" prop="relatedPolId">
        <el-input v-model="formData.relatedPolId" placeholder="请输入关联政策ID" />
      </el-form-item>
      <el-form-item label="关联政策标题" prop="relatedPolTitle">
        <el-input v-model="formData.relatedPolTitle" placeholder="请输入关联政策标题" />
      </el-form-item>
      <el-form-item label="解读标题" prop="interpretationTitle">
        <el-input v-model="formData.interpretationTitle" placeholder="请输入解读标题" />
      </el-form-item>
      <el-form-item label="解读详细内容" prop="interpretationContent">
        <Editor v-model="formData.interpretationContent" height="150px" />
      </el-form-item>
      <el-form-item label="解读人ID" prop="interpreterId">
        <el-input v-model="formData.interpreterId" placeholder="请输入解读人ID" />
      </el-form-item>
      <el-form-item label="解读人姓名" prop="interpreterName">
        <el-input v-model="formData.interpreterName" placeholder="请输入解读人姓名" />
      </el-form-item>
      <el-form-item label="解读时间" prop="interpretationTime">
        <el-date-picker
          v-model="formData.interpretationTime"
          type="date"
          value-format="x"
          placeholder="选择解读时间"
        />
      </el-form-item>
      <el-form-item label="解读状态" prop="interpretationStatus">
        <el-radio-group v-model="formData.interpretationStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="附件ID" prop="attachId">
        <el-input v-model="formData.attachId" placeholder="请输入附件ID" />
      </el-form-item>
      <el-form-item label="附件路径" prop="attachPath">
        <el-input v-model="formData.attachPath" placeholder="请输入附件路径" />
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
import { PolInterpretationApi, PolInterpretationVO } from '@/api/dataHub/businessGuide/polinterpretation'

/** 政策解读信息 表单 */
defineOptions({ name: 'PolInterpretationForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  polInterpretationId: undefined,
  relatedPolType: undefined,
  relatedPolId: undefined,
  relatedPolTitle: undefined,
  interpretationTitle: undefined,
  interpretationContent: undefined,
  interpreterId: undefined,
  interpreterName: undefined,
  interpretationTime: undefined,
  interpretationStatus: undefined,
  attachId: undefined,
  attachPath: undefined,
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
  polInterpretationId: [{ required: true, message: '解读ID不能为空', trigger: 'blur' }],
  relatedPolType: [{ required: true, message: '关联政策类型不能为空', trigger: 'change' }],
  relatedPolId: [{ required: true, message: '关联政策ID不能为空', trigger: 'blur' }],
  relatedPolTitle: [{ required: true, message: '关联政策标题不能为空', trigger: 'blur' }],
  interpretationTitle: [{ required: true, message: '解读标题不能为空', trigger: 'blur' }],
  interpretationContent: [{ required: true, message: '解读详细内容不能为空', trigger: 'blur' }],
  interpreterId: [{ required: true, message: '解读人ID不能为空', trigger: 'blur' }],
  interpreterName: [{ required: true, message: '解读人姓名不能为空', trigger: 'blur' }],
  interpretationTime: [{ required: true, message: '解读时间不能为空', trigger: 'blur' }],
  interpretationStatus: [{ required: true, message: '解读状态不能为空', trigger: 'blur' }],
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
      formData.value = await PolInterpretationApi.getPolInterpretation(id)
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
    const data = formData.value as unknown as PolInterpretationVO
    if (formType.value === 'create') {
      await PolInterpretationApi.createPolInterpretation(data)
      message.success(t('common.createSuccess'))
    } else {
      await PolInterpretationApi.updatePolInterpretation(data)
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
    polInterpretationId: undefined,
    relatedPolType: undefined,
    relatedPolId: undefined,
    relatedPolTitle: undefined,
    interpretationTitle: undefined,
    interpretationContent: undefined,
    interpreterId: undefined,
    interpreterName: undefined,
    interpretationTime: undefined,
    interpretationStatus: undefined,
    attachId: undefined,
    attachPath: undefined,
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
