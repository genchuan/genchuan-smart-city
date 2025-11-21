<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="扩展网格ID" prop="extGridId">
        <el-input v-model="formData.extGridId" placeholder="请输入扩展网格ID" />
      </el-form-item>
      <el-form-item label="扩展网格名称" prop="extGridName">
        <el-input v-model="formData.extGridName" placeholder="请输入扩展网格名称" />
      </el-form-item>
      <el-form-item label="扩展网格类型" prop="extType">
<!--        <el-select v-model="formData.extType" placeholder="请选择扩展网格类型">-->
<!--          <el-option label="请选择字典生成" value="" />-->
<!--        </el-select>-->
        <el-input v-model="formData.extType" placeholder="请输入扩展网格类型" />
      </el-form-item>
      <el-form-item label="所含基础网格IDs" prop="includedBasicIds">
        <el-input v-model="formData.includedBasicIds" placeholder="请输入所含基础网格IDs" />
      </el-form-item>
      <el-form-item label="基础网格类型" prop="basicGridType">
        <el-select v-model="formData.basicGridType" placeholder="请选择基础网格类型">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="面积(m²)" prop="area">
        <el-input v-model="formData.area" placeholder="请输入面积(m²)" />
      </el-form-item>
      <el-form-item label="边界坐标" prop="boundaryCoords">
        <el-input v-model="formData.boundaryCoords" placeholder="请输入边界坐标" />
      </el-form-item>
      <el-form-item label="申请原因" prop="applyReason">
        <el-input v-model="formData.applyReason" placeholder="请输入申请原因" />
      </el-form-item>
      <el-form-item label="审核状态" prop="auditStatus">
        <el-input v-model="formData.auditStatus" placeholder="请输入审核状态" />
      </el-form-item>
      <el-form-item label="申请人ID" prop="applyUserId">
        <el-input v-model="formData.applyUserId" placeholder="请输入申请人ID" />
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
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { ExtGridDivApi, ExtGridDivVO } from '@/api/dataHub/gridManagement/divideConfig/extgriddiv'

/** 扩展网格划分 表单 */
defineOptions({ name: 'ExtGridDivForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  extGridId: undefined,
  extGridName: undefined,
  extType: undefined,
  includedBasicIds: undefined,
  basicGridType: undefined,
  area: undefined,
  boundaryCoords: undefined,
  applyReason: undefined,
  auditStatus: undefined,
  applyUserId: undefined,
  applyTime: undefined,
  auditUserId: undefined,
  auditTime: undefined,
  auditOpinion: undefined,
  extCat1: undefined,
  extCat2: undefined,
})
const formRules = reactive({
  extGridId:[{ required: true, message: '扩展网格id不能为空', trigger: 'blur' }],
  extGridName: [{ required: true, message: '扩展网格名称不能为空', trigger: 'blur' }],
  extType: [{ required: true, message: '扩展网格类型不能为空', trigger: 'change' }],
  includedBasicIds: [{ required: true, message: '所含基础网格IDs不能为空', trigger: 'blur' }],
  basicGridType: [{ required: true, message: '基础网格类型不能为空', trigger: 'change' }],
  boundaryCoords: [{ required: true, message: '边界坐标不能为空', trigger: 'blur' }],
  applyReason: [{ required: true, message: '申请原因不能为空', trigger: 'blur' }],
  auditStatus: [{ required: true, message: '审核状态不能为空', trigger: 'blur' }],
  applyUserId: [{ required: true, message: '申请人ID不能为空', trigger: 'blur' }],
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
      formData.value = await ExtGridDivApi.getExtGridDiv(id)
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
    const data = formData.value as unknown as ExtGridDivVO
    if (formType.value === 'create') {
      await ExtGridDivApi.createExtGridDiv(data)
      message.success(t('common.createSuccess'))
    } else {
      await ExtGridDivApi.updateExtGridDiv(data)
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
    extGridId: undefined,
    extGridName: undefined,
    extType: undefined,
    includedBasicIds: undefined,
    basicGridType: undefined,
    area: undefined,
    boundaryCoords: undefined,
    applyReason: undefined,
    auditStatus: undefined,
    applyUserId: undefined,
    applyTime: undefined,
    auditUserId: undefined,
    auditTime: undefined,
    auditOpinion: undefined,
    extCat1: undefined,
    extCat2: undefined,
  }
  formRef.value?.resetFields()
}
</script>
