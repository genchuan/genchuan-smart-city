<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="法规ID" prop="localRegulationId">
        <el-input v-model="formData.localRegulationId" placeholder="请输入法规ID" />
      </el-form-item>
      <el-form-item label="法规标题" prop="regulationTitle">
        <el-input v-model="formData.regulationTitle" placeholder="请输入法规标题" />
      </el-form-item>
      <el-form-item label="法规文号" prop="regulationDocNo">
        <el-input v-model="formData.regulationDocNo" placeholder="请输入法规文号" />
      </el-form-item>
      <el-form-item label="行政区划代码" prop="regionCode">
        <el-input v-model="formData.regionCode" placeholder="请输入行政区划代码" />
      </el-form-item>
      <el-form-item label="行政区划名称" prop="regionName">
        <el-input v-model="formData.regionName" placeholder="请输入行政区划名称" />
      </el-form-item>
      <el-form-item label="发布部门代码" prop="issueDeptCode">
        <el-input v-model="formData.issueDeptCode" placeholder="请输入发布部门代码" />
      </el-form-item>
      <el-form-item label="发布部门名称" prop="issueDeptName">
        <el-input v-model="formData.issueDeptName" placeholder="请输入发布部门名称" />
      </el-form-item>
      <el-form-item label="发布时间" prop="issueTime">
        <el-date-picker
          v-model="formData.issueTime"
          type="date"
          value-format="x"
          placeholder="选择发布时间"
        />
      </el-form-item>
      <el-form-item label="实施时间" prop="implementTime">
        <el-date-picker
          v-model="formData.implementTime"
          type="date"
          value-format="x"
          placeholder="选择实施时间"
        />
      </el-form-item>
      <el-form-item label="法规文件ID" prop="regulationFileId">
        <el-input v-model="formData.regulationFileId" placeholder="请输入法规文件ID" />
      </el-form-item>
      <el-form-item label="法规文件路径" prop="regulationFilePath">
        <el-input v-model="formData.regulationFilePath" placeholder="请输入法规文件路径" />
      </el-form-item>
      <el-form-item label="法规状态" prop="regulationStatus">
        <el-radio-group v-model="formData.regulationStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="法规摘要" prop="regulationSummary">
        <el-input v-model="formData.regulationSummary" placeholder="请输入法规摘要" />
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
import { LocalRegulationApi, LocalRegulationVO } from '@/api/dataHub/businessGuide/localregulation'

/** 地方法规信息 表单 */
defineOptions({ name: 'LocalRegulationForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  localRegulationId: undefined,
  regulationTitle: undefined,
  regulationDocNo: undefined,
  regionCode: undefined,
  regionName: undefined,
  issueDeptCode: undefined,
  issueDeptName: undefined,
  issueTime: undefined,
  implementTime: undefined,
  regulationFileId: undefined,
  regulationFilePath: undefined,
  regulationStatus: undefined,
  regulationSummary: undefined,
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
  localRegulationId: [{ required: true, message: '法规ID不能为空', trigger: 'blur' }],
  regulationTitle: [{ required: true, message: '法规标题不能为空', trigger: 'blur' }],
  regionCode: [{ required: true, message: '行政区划代码不能为空', trigger: 'blur' }],
  regionName: [{ required: true, message: '行政区划名称不能为空', trigger: 'blur' }],
  issueDeptCode: [{ required: true, message: '发布部门代码不能为空', trigger: 'blur' }],
  issueDeptName: [{ required: true, message: '发布部门名称不能为空', trigger: 'blur' }],
  issueTime: [{ required: true, message: '发布时间不能为空', trigger: 'blur' }],
  regulationFileId: [{ required: true, message: '法规文件ID不能为空', trigger: 'blur' }],
  regulationFilePath: [{ required: true, message: '法规文件路径不能为空', trigger: 'blur' }],
  regulationStatus: [{ required: true, message: '法规状态不能为空', trigger: 'blur' }],
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
      formData.value = await LocalRegulationApi.getLocalRegulation(id)
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
    const data = formData.value as unknown as LocalRegulationVO
    if (formType.value === 'create') {
      await LocalRegulationApi.createLocalRegulation(data)
      message.success(t('common.createSuccess'))
    } else {
      await LocalRegulationApi.updateLocalRegulation(data)
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
    localRegulationId: undefined,
    regulationTitle: undefined,
    regulationDocNo: undefined,
    regionCode: undefined,
    regionName: undefined,
    issueDeptCode: undefined,
    issueDeptName: undefined,
    issueTime: undefined,
    implementTime: undefined,
    regulationFileId: undefined,
    regulationFilePath: undefined,
    regulationStatus: undefined,
    regulationSummary: undefined,
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
