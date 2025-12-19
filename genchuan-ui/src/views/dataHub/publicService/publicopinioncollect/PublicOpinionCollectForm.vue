<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="征集ID " prop="collectId">
        <el-input v-model="formData.collectId" placeholder="请输入征集ID " />
      </el-form-item>
      <el-form-item label="征集主题 " prop="collectTopic">
        <el-input v-model="formData.collectTopic" placeholder="请输入征集主题 " />
      </el-form-item>
      <el-form-item label="征集单位编码 " prop="deptCode">
        <el-input v-model="formData.deptCode" placeholder="请输入征集单位编码 " />
      </el-form-item>
      <el-form-item label="征集单位名称 " prop="deptName">
        <el-input v-model="formData.deptName" placeholder="请输入征集单位名称 " />
      </el-form-item>
      <el-form-item label="征集范围编码 " prop="areaCode">
        <el-input v-model="formData.areaCode" placeholder="请输入征集范围编码 " />
      </el-form-item>
      <el-form-item label="征集范围名称 " prop="areaName">
        <el-input v-model="formData.areaName" placeholder="请输入征集范围名称 " />
      </el-form-item>
      <el-form-item label="征集内容 " prop="collectContent">
        <Editor v-model="formData.collectContent" height="150px" />
      </el-form-item>
      <el-form-item label="开始时间 " prop="startTime">
        <el-date-picker
          v-model="formData.startTime"
          type="date"
          value-format="x"
          placeholder="选择开始时间 "
        />
      </el-form-item>
      <el-form-item label="结束时间 " prop="endTime">
        <el-date-picker
          v-model="formData.endTime"
          type="date"
          value-format="x"
          placeholder="选择结束时间 "
        />
      </el-form-item>
      <el-form-item label="征集状态 " prop="collectStatus">
        <el-radio-group v-model="formData.collectStatus">
          <el-radio value="1">未开始</el-radio>
          <el-radio value="2">征集中</el-radio>
          <el-radio value="3">已结束</el-radio>
          <el-radio value="4">结果公示</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="结果公示内容 " prop="resultContent">
        <Editor v-model="formData.resultContent" height="150px" />
      </el-form-item>
      <el-form-item label="公示时间 " prop="publishResultTime">
        <el-date-picker
          v-model="formData.publishResultTime"
          type="date"
          value-format="x"
          placeholder="选择公示时间 "
        />
      </el-form-item>
      <el-form-item label="发布人ID " prop="publishUserId">
        <el-input v-model="formData.publishUserId" placeholder="请输入发布人ID " />
      </el-form-item>
      <el-form-item label="发布时间 " prop="publishTime">
        <el-date-picker
          v-model="formData.publishTime"
          type="date"
          value-format="x"
          placeholder="选择发布时间 "
        />
      </el-form-item>
      <el-form-item label="备注，补充说明 " prop="remark">
        <el-input v-model="formData.remark" placeholder="请输入备注，补充说明 " />
      </el-form-item>
      <el-form-item label="分类扩展字段1 " prop="extCat1">
        <el-input v-model="formData.extCat1" placeholder="请输入分类扩展字段1 " />
      </el-form-item>
      <el-form-item label="分类扩展字段2 " prop="extCat2">
        <el-input v-model="formData.extCat2" placeholder="请输入分类扩展字段2 " />
      </el-form-item>
      <el-form-item label="通用扩展字段1  " prop="extCommon1">
        <el-input v-model="formData.extCommon1" placeholder="请输入通用扩展字段1  " />
      </el-form-item>
      <el-form-item label="通用扩展字段2 " prop="extCommon2">
        <el-input v-model="formData.extCommon2" placeholder="请输入通用扩展字段2 " />
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
import { PublicOpinionCollectApi, PublicOpinionCollectVO } from '@/api/dataHub/publicService/publicopinioncollect'

/** 民意征集 表单 */
defineOptions({ name: 'PublicOpinionCollectForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  collectId: undefined,
  collectTopic: undefined,
  deptCode: undefined,
  deptName: undefined,
  areaCode: undefined,
  areaName: undefined,
  collectContent: undefined,
  startTime: undefined,
  endTime: undefined,
  collectStatus: undefined,
  resultContent: undefined,
  publishResultTime: undefined,
  publishUserId: undefined,
  publishTime: undefined,
  remark: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  collectId: [{ required: true, message: '征集ID 不能为空', trigger: 'blur' }],
  collectTopic: [{ required: true, message: '征集主题 不能为空', trigger: 'blur' }],
  deptCode: [{ required: true, message: '征集单位编码 不能为空', trigger: 'blur' }],
  deptName: [{ required: true, message: '征集单位名称 不能为空', trigger: 'blur' }],
  areaCode: [{ required: true, message: '征集范围编码 不能为空', trigger: 'blur' }],
  areaName: [{ required: true, message: '征集范围名称 不能为空', trigger: 'blur' }],
  collectContent: [{ required: true, message: '征集内容 不能为空', trigger: 'blur' }],
  startTime: [{ required: true, message: '开始时间 不能为空', trigger: 'blur' }],
  endTime: [{ required: true, message: '结束时间 不能为空', trigger: 'blur' }],
  collectStatus: [{ required: true, message: '征集状态 不能为空', trigger: 'blur' }],
  publishUserId: [{ required: true, message: '发布人ID 不能为空', trigger: 'blur' }],
  publishTime: [{ required: true, message: '发布时间 不能为空', trigger: 'blur' }],
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
      formData.value = await PublicOpinionCollectApi.getPublicOpinionCollect(id)
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
    const data = formData.value as unknown as PublicOpinionCollectVO
    if (formType.value === 'create') {
      await PublicOpinionCollectApi.createPublicOpinionCollect(data)
      message.success(t('common.createSuccess'))
    } else {
      await PublicOpinionCollectApi.updatePublicOpinionCollect(data)
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
    collectId: undefined,
    collectTopic: undefined,
    deptCode: undefined,
    deptName: undefined,
    areaCode: undefined,
    areaName: undefined,
    collectContent: undefined,
    startTime: undefined,
    endTime: undefined,
    collectStatus: undefined,
    resultContent: undefined,
    publishResultTime: undefined,
    publishUserId: undefined,
    publishTime: undefined,
    remark: undefined,
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
