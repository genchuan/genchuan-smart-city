<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="编码ID" prop="mgCodeId">
        <el-input v-model="formData.mgCodeId" placeholder="请输入编码ID" />
      </el-form-item>
      <el-form-item label="管理网格标识码" prop="mgGridCode">
        <el-input v-model="formData.mgGridCode" placeholder="请输入管理网格标识码" />
      </el-form-item>
      <el-form-item label="所属管理网格ID" prop="mngGridId">
        <el-input v-model="formData.mngGridId" placeholder="请输入所属管理网格ID" />
      </el-form-item>
      <el-form-item label="行政区划完整代码" prop="areaFullCode">
        <el-input v-model="formData.areaFullCode" placeholder="请输入行政区划完整代码" />
      </el-form-item>
      <el-form-item label="顺序码" prop="seqCode">
        <el-input v-model="formData.seqCode" placeholder="请输入顺序码" />
      </el-form-item>
      <el-form-item label="编码生成时间" prop="generateTime">
        <el-date-picker
          v-model="formData.generateTime"
          type="date"
          value-format="x"
          placeholder="选择编码生成时间"
        />
      </el-form-item>
      <el-form-item label="编码状态" prop="codeStatus">
        <el-input v-model="formData.codeStatus" placeholder="请输入编码状态" />
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input v-model="formData.remark" placeholder="请输入备注" />
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
import { MngGridCodeApi, MngGridCodeVO } from '@/api/dataHub/gridManagement/gridCode/mnggridcode'

/** 管理网格编码 表单 */
defineOptions({ name: 'MngGridCodeForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  mgCodeId: undefined,
  mgGridCode: undefined,
  mngGridId: undefined,
  areaFullCode: undefined,
  seqCode: undefined,
  generateTime: undefined,
  codeStatus: undefined,
  remark: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
})
const formRules = reactive({
  mgGridCode: [{ required: true, message: '管理网格标识码不能为空', trigger: 'blur' }],
  mngGridId: [{ required: true, message: '所属管理网格ID不能为空', trigger: 'blur' }],
  areaFullCode: [{ required: true, message: '行政区划完整代码不能为空', trigger: 'blur' }],
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
      formData.value = await MngGridCodeApi.getMngGridCode(id)
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
    const data = formData.value as unknown as MngGridCodeVO
    if (formType.value === 'create') {
      await MngGridCodeApi.createMngGridCode(data)
      message.success(t('common.createSuccess'))
    } else {
      await MngGridCodeApi.updateMngGridCode(data)
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
    mgCodeId: undefined,
    mgGridCode: undefined,
    mngGridId: undefined,
    areaFullCode: undefined,
    seqCode: undefined,
    generateTime: undefined,
    codeStatus: undefined,
    remark: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined,
  }
  formRef.value?.resetFields()
}
</script>
