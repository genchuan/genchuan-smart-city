<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="随手拍ID" prop="snapShotId">
        <el-input v-model="formData.snapShotId" placeholder="请输入随手拍ID" />
      </el-form-item>
      <el-form-item label="记录单号" prop="snapNo">
        <el-input v-model="formData.snapNo" placeholder="请输入记录单号" />
      </el-form-item>
      <el-form-item label="问题类型编码" prop="problemTypeCode">
        <el-input v-model="formData.problemTypeCode" placeholder="请输入问题类型编码" />
      </el-form-item>
      <el-form-item label="问题类型名称" prop="problemTypeName">
        <el-input v-model="formData.problemTypeName" placeholder="请输入问题类型名称" />
      </el-form-item>
      <el-form-item label="事发坐标X" prop="coordX">
        <el-input v-model="formData.coordX" placeholder="请输入事发坐标X" />
      </el-form-item>
      <el-form-item label="事发坐标Y" prop="coordY">
        <el-input v-model="formData.coordY" placeholder="请输入事发坐标Y" />
      </el-form-item>
      <el-form-item label="事发区域编码" prop="areaCode">
        <el-input v-model="formData.areaCode" placeholder="请输入事发区域编码" />
      </el-form-item>
      <el-form-item label="事发区域名称" prop="areaName">
        <el-input v-model="formData.areaName" placeholder="请输入事发区域名称" />
      </el-form-item>
      <el-form-item label="问题描述" prop="problemDesc">
        <el-input v-model="formData.problemDesc" placeholder="请输入问题描述" />
      </el-form-item>
      <el-form-item label="媒体路径" prop="mediaPath">
        <el-input v-model="formData.mediaPath" placeholder="请输入媒体路径" />
      </el-form-item>
      <el-form-item label="媒体类型" prop="mediaType">
        <el-select v-model="formData.mediaType" placeholder="请选择媒体类型">
          <el-option label="请选择字典生成" value="1" />
        </el-select>
      </el-form-item>
      <el-form-item label="上报人姓名" prop="rptName">
        <el-input v-model="formData.rptName" placeholder="请输入上报人姓名" />
      </el-form-item>
      <el-form-item label="上报人电话" prop="rptPhone">
        <el-input v-model="formData.rptPhone" placeholder="请输入上报人电话" />
      </el-form-item>
      <el-form-item label="处置状态" prop="handleStatus">
        <el-radio-group v-model="formData.handleStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="处置结果" prop="handleResult">
        <el-input v-model="formData.handleResult" placeholder="请输入处置结果" />
      </el-form-item>
      <el-form-item label="上报时间" prop="rptTime">
        <el-date-picker
          v-model="formData.rptTime"
          type="date"
          value-format="x"
          placeholder="选择上报时间"
        />
      </el-form-item>
      <el-form-item label="办结时间" prop="finishTime">
        <el-date-picker
          v-model="formData.finishTime"
          type="date"
          value-format="x"
          placeholder="选择办结时间"
        />
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
import { PublicSnapShotApi, PublicSnapShotVO } from '@/api/dataHub/publicService/publicsnapshot'

/** 随手拍数据 表单 */
defineOptions({ name: 'PublicSnapShotForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  snapShotId: undefined,
  snapNo: undefined,
  problemTypeCode: undefined,
  problemTypeName: undefined,
  coordX: undefined,
  coordY: undefined,
  areaCode: undefined,
  areaName: undefined,
  problemDesc: undefined,
  mediaPath: undefined,
  mediaType: undefined,
  rptName: undefined,
  rptPhone: undefined,
  handleStatus: undefined,
  handleResult: undefined,
  rptTime: undefined,
  finishTime: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  snapShotId: [{ required: true, message: '随手拍ID不能为空', trigger: 'blur' }],
  snapNo: [{ required: true, message: '记录单号不能为空', trigger: 'blur' }],
  problemTypeCode: [{ required: true, message: '问题类型编码不能为空', trigger: 'blur' }],
  problemTypeName: [{ required: true, message: '问题类型名称不能为空', trigger: 'blur' }],
  coordX: [{ required: true, message: '事发坐标X不能为空', trigger: 'blur' }],
  coordY: [{ required: true, message: '事发坐标Y不能为空', trigger: 'blur' }],
  areaCode: [{ required: true, message: '事发区域编码不能为空', trigger: 'blur' }],
  areaName: [{ required: true, message: '事发区域名称不能为空', trigger: 'blur' }],
  mediaPath: [{ required: true, message: '媒体路径不能为空', trigger: 'blur' }],
  mediaType: [{ required: true, message: '媒体类型不能为空', trigger: 'change' }],
  rptName: [{ required: true, message: '上报人姓名不能为空', trigger: 'blur' }],
  rptPhone: [{ required: true, message: '上报人电话不能为空', trigger: 'blur' }],
  handleStatus: [{ required: true, message: '处置状态不能为空', trigger: 'blur' }],
  rptTime: [{ required: true, message: '上报时间不能为空', trigger: 'blur' }],
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
      formData.value = await PublicSnapShotApi.getPublicSnapShot(id)
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
    const data = formData.value as unknown as PublicSnapShotVO
    if (formType.value === 'create') {
      await PublicSnapShotApi.createPublicSnapShot(data)
      message.success(t('common.createSuccess'))
    } else {
      await PublicSnapShotApi.updatePublicSnapShot(data)
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
    snapShotId: undefined,
    snapNo: undefined,
    problemTypeCode: undefined,
    problemTypeName: undefined,
    coordX: undefined,
    coordY: undefined,
    areaCode: undefined,
    areaName: undefined,
    problemDesc: undefined,
    mediaPath: undefined,
    mediaType: undefined,
    rptName: undefined,
    rptPhone: undefined,
    handleStatus: undefined,
    handleResult: undefined,
    rptTime: undefined,
    finishTime: undefined,
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
