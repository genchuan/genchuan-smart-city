<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="资料ID" prop="trainMatId">
        <el-input v-model="formData.trainMatId" placeholder="请输入资料ID" />
      </el-form-item>
      <el-form-item label="资料名称" prop="matName">
        <el-input v-model="formData.matName" placeholder="请输入资料名称" />
      </el-form-item>
      <el-form-item label="培训类型代码" prop="trainTypeCode">
        <el-input v-model="formData.trainTypeCode" placeholder="请输入培训类型代码" />
      </el-form-item>
      <el-form-item label="培训类型名称" prop="trainTypeName">
        <el-input v-model="formData.trainTypeName" placeholder="请输入培训类型名称" />
      </el-form-item>
      <el-form-item label="培训对象" prop="trainObject">
        <el-input v-model="formData.trainObject" placeholder="请输入培训对象" />
      </el-form-item>
      <el-form-item label="培训时长" prop="trainEndure">
        <el-input v-model="formData.trainEndure" placeholder="请输入培训时长" />
      </el-form-item>
      <el-form-item label="资料格式" prop="matFormat">
        <el-input v-model="formData.matFormat" placeholder="请输入资料格式" />
      </el-form-item>
      <el-form-item label="资料大小" prop="matSize">
        <el-input v-model="formData.matSize" placeholder="请输入资料大小" />
      </el-form-item>
      <el-form-item label="资料摘要" prop="matAbstract">
        <el-input v-model="formData.matAbstract" placeholder="请输入资料摘要" />
      </el-form-item>
      <el-form-item label="资料文件ID" prop="matFileId">
        <el-input v-model="formData.matFileId" placeholder="请输入资料文件ID" />
      </el-form-item>
      <el-form-item label="资料文件路径" prop="matFilePath">
        <el-input v-model="formData.matFilePath" placeholder="请输入资料文件路径" />
      </el-form-item>
      <el-form-item label="资料状态" prop="matStatus">
        <el-radio-group v-model="formData.matStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="下载次数" prop="downloadCount">
        <el-input v-model="formData.downloadCount" placeholder="请输入下载次数" />
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
import { TrainMatApi, TrainMatVO } from '@/api/dataHub/businessGuide/trainmat'

/** 培训资料 表单 */
defineOptions({ name: 'TrainMatForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  trainMatId: undefined,
  matName: undefined,
  trainTypeCode: undefined,
  trainTypeName: undefined,
  trainObject: undefined,
  trainEndure: undefined,
  matFormat: undefined,
  matSize: undefined,
  matAbstract: undefined,
  matFileId: undefined,
  matFilePath: undefined,
  matStatus: undefined,
  downloadCount: undefined,
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
  trainMatId: [{ required: true, message: '资料ID不能为空', trigger: 'blur' }],
  matName: [{ required: true, message: '资料名称不能为空', trigger: 'blur' }],
  trainTypeCode: [{ required: true, message: '培训类型代码不能为空', trigger: 'blur' }],
  trainTypeName: [{ required: true, message: '培训类型名称不能为空', trigger: 'blur' }],
  trainObject: [{ required: true, message: '培训对象不能为空', trigger: 'blur' }],
  trainEndure: [{ required: true, message: '培训时长不能为空', trigger: 'blur' }],
  matFormat: [{ required: true, message: '资料格式不能为空', trigger: 'blur' }],
  matSize: [{ required: true, message: '资料大小不能为空', trigger: 'blur' }],
  matFileId: [{ required: true, message: '资料文件ID不能为空', trigger: 'blur' }],
  matFilePath: [{ required: true, message: '资料文件路径不能为空', trigger: 'blur' }],
  matStatus: [{ required: true, message: '资料状态不能为空', trigger: 'blur' }],
  downloadCount: [{ required: true, message: '下载次数不能为空', trigger: 'blur' }],
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
      formData.value = await TrainMatApi.getTrainMat(id)
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
    const data = formData.value as unknown as TrainMatVO
    if (formType.value === 'create') {
      await TrainMatApi.createTrainMat(data)
      message.success(t('common.createSuccess'))
    } else {
      await TrainMatApi.updateTrainMat(data)
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
    trainMatId: undefined,
    matName: undefined,
    trainTypeCode: undefined,
    trainTypeName: undefined,
    trainObject: undefined,
    trainEndure: undefined,
    matFormat: undefined,
    matSize: undefined,
    matAbstract: undefined,
    matFileId: undefined,
    matFilePath: undefined,
    matStatus: undefined,
    downloadCount: undefined,
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
