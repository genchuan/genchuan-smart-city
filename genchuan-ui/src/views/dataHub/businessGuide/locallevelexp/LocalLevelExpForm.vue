<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="经验ID" prop="localLevelExpId">
        <el-input v-model="formData.localLevelExpId" placeholder="请输入经验ID" />
      </el-form-item>
      <el-form-item label="经验标题" prop="expTitle">
        <el-input v-model="formData.expTitle" placeholder="请输入经验标题" />
      </el-form-item>
      <el-form-item label="行政区划代码" prop="regionCode">
        <el-input v-model="formData.regionCode" placeholder="请输入行政区划代码" />
      </el-form-item>
      <el-form-item label="行政区划名称" prop="regionName">
        <el-input v-model="formData.regionName" placeholder="请输入行政区划名称" />
      </el-form-item>
      <el-form-item label="关联部门代码" prop="deptCode">
        <el-input v-model="formData.deptCode" placeholder="请输入关联部门代码" />
      </el-form-item>
      <el-form-item label="关联部门名称" prop="deptName">
        <el-input v-model="formData.deptName" placeholder="请输入关联部门名称" />
      </el-form-item>
      <el-form-item label="经验详细内容" prop="expContent">
        <Editor v-model="formData.expContent" height="150px" />
      </el-form-item>
      <el-form-item label="经验实施步骤" prop="implementationSteps">
        <el-input v-model="formData.implementationSteps" placeholder="请输入经验实施步骤" />
      </el-form-item>
      <el-form-item label="实施成果" prop="achievementData">
        <el-input v-model="formData.achievementData" placeholder="请输入实施成果" />
      </el-form-item>
      <el-form-item label="附件ID" prop="attachId">
        <el-input v-model="formData.attachId" placeholder="请输入附件ID" />
      </el-form-item>
      <el-form-item label="附件存储路径" prop="attachPath">
        <el-input v-model="formData.attachPath" placeholder="请输入附件存储路径" />
      </el-form-item>
      <el-form-item label="发布状态" prop="publishStatus">
        <el-radio-group v-model="formData.publishStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="分享次数" prop="shareCount">
        <el-input v-model="formData.shareCount" placeholder="请输入分享次数" />
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
import { LocalLevelExpApi, LocalLevelExpVO } from '@/api/dataHub/businessGuide/locallevelexp'

/** 本级经验分享 表单 */
defineOptions({ name: 'LocalLevelExpForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  localLevelExpId: undefined,
  expTitle: undefined,
  regionCode: undefined,
  regionName: undefined,
  deptCode: undefined,
  deptName: undefined,
  expContent: undefined,
  implementationSteps: undefined,
  achievementData: undefined,
  attachId: undefined,
  attachPath: undefined,
  publishStatus: undefined,
  shareCount: undefined,
  createUser: undefined,
  updateUser: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  localLevelExpId: [{ required: true, message: '经验ID不能为空', trigger: 'blur' }],
  expTitle: [{ required: true, message: '经验标题不能为空', trigger: 'blur' }],
  regionCode: [{ required: true, message: '行政区划代码不能为空', trigger: 'blur' }],
  regionName: [{ required: true, message: '行政区划名称不能为空', trigger: 'blur' }],
  deptCode: [{ required: true, message: '关联部门代码不能为空', trigger: 'blur' }],
  deptName: [{ required: true, message: '关联部门名称不能为空', trigger: 'blur' }],
  expContent: [{ required: true, message: '经验详细内容不能为空', trigger: 'blur' }],
  implementationSteps: [{ required: true, message: '经验实施步骤不能为空', trigger: 'blur' }],
  publishStatus: [{ required: true, message: '发布状态不能为空', trigger: 'blur' }],
  shareCount: [{ required: true, message: '分享次数不能为空', trigger: 'blur' }],
  createUser: [{ required: true, message: '创建人不能为空', trigger: 'blur' }],
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
      formData.value = await LocalLevelExpApi.getLocalLevelExp(id)
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
    const data = formData.value as unknown as LocalLevelExpVO
    if (formType.value === 'create') {
      await LocalLevelExpApi.createLocalLevelExp(data)
      message.success(t('common.createSuccess'))
    } else {
      await LocalLevelExpApi.updateLocalLevelExp(data)
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
    localLevelExpId: undefined,
    expTitle: undefined,
    regionCode: undefined,
    regionName: undefined,
    deptCode: undefined,
    deptName: undefined,
    expContent: undefined,
    implementationSteps: undefined,
    achievementData: undefined,
    attachId: undefined,
    attachPath: undefined,
    publishStatus: undefined,
    shareCount: undefined,
    createUser: undefined,
    updateUser: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined,
  }
  formRef.value?.resetFields()
}
</script>
