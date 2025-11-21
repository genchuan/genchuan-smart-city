<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="700px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
      class="custom-form"
    >
      <el-form-item label="指标名称" prop="idxName">
        <el-input v-model="formData.idxName" placeholder="请输入指标名称" />
      </el-form-item>
      <el-form-item label="指标编码" prop="idxCode">
        <el-input v-model="formData.idxCode" placeholder="请输入指标编码" />
      </el-form-item>
      <el-form-item label="指标单位" prop="idxUnit">
        <el-input v-model="formData.idxUnit" placeholder="请输入指标单位" />
      </el-form-item>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="数据类型" prop="dataType">
            <el-select v-model="formData.dataType" placeholder="请选择数据类型">
              <el-option label="请选择字典生成" value="" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="数据长度" prop="dataLength">
            <el-input v-model="formData.dataLength" placeholder="请输入数据长度" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="关联点位类型" prop="relPtType">
        <el-select v-model="formData.relPtType" placeholder="请选择关联点位类型">
          <el-option label="水质监测" value="水质监测" />
          <el-option label="空气质量监测" value="空气质量监测" />
          <el-option label="市政设施监测" value="市政设施监测" />
          <el-option label="安防监测" value="安防监测" />
        </el-select>
      </el-form-item>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="预警阈值上限" prop="warnThresholdMax">
            <el-input v-model="formData.warnThresholdMax" placeholder="请输入预警阈值上限" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预警阈值下限" prop="warnThresholdMin">
            <el-input v-model="formData.warnThresholdMin" placeholder="请输入预警阈值下限" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="指标说明" prop="idxDesc">
        <el-input v-model="formData.idxDesc" placeholder="请输入指标说明" type="textarea" />
      </el-form-item>
      <el-form-item label="指标状态" prop="idxStatus">
        <el-radio-group v-model="formData.idxStatus">
          <el-radio :label="true">启用</el-radio>
          <el-radio :label="false">禁用</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="分类扩展字段1" prop="extCat1">
            <el-input v-model="formData.extCat1" placeholder="请输入分类扩展字段1" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分类扩展字段2" prop="extCat2">
            <el-input v-model="formData.extCat2" placeholder="请输入分类扩展字段2" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="通用扩展字段1" prop="extCommon1">
            <el-input v-model="formData.extCommon1" placeholder="请输入通用扩展字段1" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="通用扩展字段2" prop="extCommon2">
            <el-input v-model="formData.extCommon2" placeholder="请输入通用扩展字段2" />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  BasicMonIdxApi,
  BasicMonIdxVO
} from '@/api/dataHub/operationMonitor/basicDataManage/basicmonidx'

/** 基础监测指标 表单 */
defineOptions({ name: 'BasicMonIdxForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  idxId: undefined,
  idxName: undefined,
  idxCode: undefined,
  idxUnit: undefined,
  dataType: undefined,
  dataLength: undefined,
  relPtType: undefined,
  warnThresholdMax: undefined,
  warnThresholdMin: undefined,
  idxDesc: undefined,
  idxStatus: true, // 默认启用
  createTimeSys: undefined,
  updateTimeSys: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  idxName: [{ required: true, message: '指标名称不能为空', trigger: 'blur' }],
  idxCode: [{ required: true, message: '指标编码不能为空', trigger: 'blur' }],
  idxUnit: [{ required: true, message: '指标单位不能为空', trigger: 'blur' }],
  dataType: [{ required: true, message: '数据类型不能为空', trigger: 'change' }],
  dataLength: [{ required: true, message: '数据长度不能为空', trigger: 'blur' }],
  relPtType: [{ required: true, message: '关联点位类型不能为空', trigger: 'change' }],
  idxStatus: [{ required: true, message: '指标状态不能为空', trigger: 'change' }]
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
      formData.value = await BasicMonIdxApi.getBasicMonIdx(id)
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
    const data = formData.value as unknown as BasicMonIdxVO
    if (formType.value === 'create') {
      await BasicMonIdxApi.createBasicMonIdx(data)
      message.success(t('common.createSuccess'))
    } else {
      await BasicMonIdxApi.updateBasicMonIdx(data)
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
    idxId: undefined,
    idxName: undefined,
    idxCode: undefined,
    idxUnit: undefined,
    dataType: undefined,
    dataLength: undefined,
    relPtType: undefined,
    warnThresholdMax: undefined,
    warnThresholdMin: undefined,
    idxDesc: undefined,
    idxStatus: true, // 默认启用
    createTimeSys: undefined,
    updateTimeSys: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined
  }
  formRef.value?.resetFields()
}
</script>

<style scoped>
.custom-form {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
}

.el-form-item {
  margin-bottom: 16px;
}

.el-form-item__label {
  color: #666;
  font-weight: 500;
}

.el-input,
.el-select,
.el-radio-group {
  width: 100%;
}

.el-textarea {
  width: 100%;
}

.el-dialog__footer {
  padding: 16px 24px;
  border-top: 1px solid #f0f0f0;
  margin-top: 10px;
}
</style>
