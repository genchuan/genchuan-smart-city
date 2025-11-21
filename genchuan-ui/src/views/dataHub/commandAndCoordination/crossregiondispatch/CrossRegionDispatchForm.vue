<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="协调ID" prop="coordinateId">
        <el-input v-model="formData.coordinateId" placeholder="请输入协调ID" />
      </el-form-item>
      <el-form-item label="协调编号" prop="coordinateNo">
        <el-input v-model="formData.coordinateNo" placeholder="请输入协调编号" />
      </el-form-item>
      <el-form-item label="申请单位ID" prop="applyUnitId">
        <el-input v-model="formData.applyUnitId" placeholder="请输入申请单位ID" />
      </el-form-item>
      <el-form-item label="申请单位名称" prop="applyUnitName">
        <el-input v-model="formData.applyUnitName" placeholder="请输入申请单位名称" />
      </el-form-item>
      <el-form-item label="协作单位ID" prop="cooperUnitId">
        <el-input v-model="formData.cooperUnitId" placeholder="请输入协作单位ID" />
      </el-form-item>
      <el-form-item label="协作单位名称" prop="cooperUnitName">
        <el-input v-model="formData.cooperUnitName" placeholder="请输入协作单位名称" />
      </el-form-item>
      <el-form-item label="申请理由" prop="applyReason">
        <el-input v-model="formData.applyReason" placeholder="请输入申请理由" />
      </el-form-item>
      <el-form-item label="所需资源类型" prop="resType">
        <el-select v-model="formData.resType" placeholder="请选择所需资源类型">
          <el-option label="应急物资" value="1" />
          <el-option label="救援队伍" value="2" />
          <el-option label="救援车辆" value="3" />
          <el-option label="救援设备" value="4" />
        </el-select>
      </el-form-item>
      <el-form-item label="所需资源名称" prop="resName">
        <el-input v-model="formData.resName" placeholder="请输入所需资源名称" />
      </el-form-item>
      <el-form-item label="所需数量" prop="requireQuantity">
        <el-input v-model="formData.requireQuantity" placeholder="请输入所需数量" />
      </el-form-item>
      <el-form-item label="协作区域" prop="cooperRegion">
        <el-input v-model="formData.cooperRegion" placeholder="请输入协作区域" />
      </el-form-item>
      <el-form-item label="申请状态" prop="applyStatus">
        <el-radio-group v-model="formData.applyStatus">
          <el-radio value="1">待审核</el-radio>
          <el-radio value="2">已通过</el-radio>
          <el-radio value="3">已驳回</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="申请人ID" prop="applyUserId">
        <el-input v-model="formData.applyUserId" placeholder="请输入申请人ID" />
      </el-form-item>
      <el-form-item label="申请人姓名" prop="applyUserName">
        <el-input v-model="formData.applyUserName" placeholder="请输入申请人姓名" />
      </el-form-item>
      <el-form-item label="申请时间" prop="applyTime">
        <el-date-picker
          v-model="formData.applyTime"
          type="date"
          value-format="x"
          placeholder="选择申请时间"
        />
      </el-form-item>
      <el-form-item label="审核人ID" prop="auditUserId">
        <el-input v-model="formData.auditUserId" placeholder="请输入审核人ID" />
      </el-form-item>
      <el-form-item label="审核人姓名" prop="auditUserName">
        <el-input v-model="formData.auditUserName" placeholder="请输入审核人姓名" />
      </el-form-item>
      <el-form-item label="审核时间" prop="auditTime">
        <el-date-picker
          v-model="formData.auditTime"
          type="date"
          value-format="x"
          placeholder="选择审核时间"
        />
      </el-form-item>
      <el-form-item label="审核意见" prop="auditOpinion">
        <el-input v-model="formData.auditOpinion" placeholder="请输入审核意见" />
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
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { CrossRegionDispatchApi, CrossRegionDispatchVO } from '@/api/dataHub/commandAndCoordination/crossregiondispatch'

/** 跨域调度协调 表单 */
defineOptions({ name: 'CrossRegionDispatchForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  coordinateId: undefined,
  coordinateNo: undefined,
  applyUnitId: undefined,
  applyUnitName: undefined,
  cooperUnitId: undefined,
  cooperUnitName: undefined,
  applyReason: undefined,
  resType: undefined,
  resName: undefined,
  requireQuantity: undefined,
  cooperRegion: undefined,
  applyStatus: undefined,
  applyUserId: undefined,
  applyUserName: undefined,
  applyTime: undefined,
  auditUserId: undefined,
  auditUserName: undefined,
  auditTime: undefined,
  auditOpinion: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
})
const formRules = reactive({
  coordinateId: [{ required: true, message: '协调ID不能为空', trigger: 'blur' }],
  coordinateNo: [{ required: true, message: '协调编号不能为空', trigger: 'blur' }],
  applyUnitId: [{ required: true, message: '申请单位ID不能为空', trigger: 'blur' }],
  applyUnitName: [{ required: true, message: '申请单位名称不能为空', trigger: 'blur' }],
  cooperUnitId: [{ required: true, message: '协作单位ID不能为空', trigger: 'blur' }],
  cooperUnitName: [{ required: true, message: '协作单位名称不能为空', trigger: 'blur' }],
  applyReason: [{ required: true, message: '申请理由不能为空', trigger: 'blur' }],
  resType: [{ required: true, message: '所需资源类型不能为空', trigger: 'change' }],
  resName: [{ required: true, message: '所需资源名称不能为空', trigger: 'blur' }],
  requireQuantity: [{ required: true, message: '所需数量不能为空', trigger: 'blur' }],
  cooperRegion: [{ required: true, message: '协作区域不能为空', trigger: 'blur' }],
  applyUserId: [{ required: true, message: '申请人ID不能为空', trigger: 'blur' }],
  applyUserName: [{ required: true, message: '申请人姓名不能为空', trigger: 'blur' }],
  applyTime: [{ required: true, message: '申请时间不能为空', trigger: 'blur' }],
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
      formData.value = await CrossRegionDispatchApi.getCrossRegionDispatch(id)
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
    const data = formData.value as unknown as CrossRegionDispatchVO
    if (formType.value === 'create') {
      await CrossRegionDispatchApi.createCrossRegionDispatch(data)
      message.success(t('common.createSuccess'))
    } else {
      await CrossRegionDispatchApi.updateCrossRegionDispatch(data)
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
    coordinateId: undefined,
    coordinateNo: undefined,
    applyUnitId: undefined,
    applyUnitName: undefined,
    cooperUnitId: undefined,
    cooperUnitName: undefined,
    applyReason: undefined,
    resType: undefined,
    resName: undefined,
    requireQuantity: undefined,
    cooperRegion: undefined,
    applyStatus: undefined,
    applyUserId: undefined,
    applyUserName: undefined,
    applyTime: undefined,
    auditUserId: undefined,
    auditUserName: undefined,
    auditTime: undefined,
    auditOpinion: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined,
  }
  formRef.value?.resetFields()
}
</script>
