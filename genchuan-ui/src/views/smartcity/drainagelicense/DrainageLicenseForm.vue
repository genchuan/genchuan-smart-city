<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
    >
      <el-form-item label="许可证编号" prop="licenseNo">
        <el-input v-model="formData.licenseNo" placeholder="请输入许可证编号" />
      </el-form-item>
      <el-form-item label="有效期开始日期" prop="startDate">
        <el-date-picker
          v-model="formData.startDate"
          type="date"
          value-format="x"
          placeholder="选择有效期开始日期"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="有效期结束日期" prop="endDate">
        <el-date-picker
          v-model="formData.endDate"
          type="date"
          value-format="x"
          placeholder="选择有效期结束日期"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="许可排水类型" prop="drainageType">
        <el-select v-model="formData.drainageType" placeholder="请选择许可排水类型">
          <el-option
            v-for="dict in getStrDictOptions(DICT_TYPE.SM_DRAINAGE_TYPE)"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="审批单位" prop="approvalUnit">
        <el-input v-model="formData.approvalUnit" placeholder="请输入审批单位" />
      </el-form-item>
      <el-form-item label="状态" prop="licenseStatus">
        <el-select v-model="formData.licenseStatus" placeholder="请选择状态">
          <el-option
            v-for="dict in getStrDictOptions(DICT_TYPE.CRM_AUDIT_STATUS)"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { getStrDictOptions, DICT_TYPE } from '@/utils/dict'
import { DrainageLicenseApi, DrainageLicenseVO } from '@/api/smartcity/drainagelicense'

/** 排水电子许可证信息 表单 */
defineOptions({ name: 'DrainageLicenseForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  licenseNo: undefined,
  startDate: undefined,
  endDate: undefined,
  drainageType: undefined,
  approvalUnit: undefined,
  licenseStatus: undefined
})
const formRules = reactive({
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
      formData.value = await DrainageLicenseApi.getDrainageLicense(id)
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
    const data = formData.value as unknown as DrainageLicenseVO
    if (formType.value === 'create') {
      await DrainageLicenseApi.createDrainageLicense(data)
      message.success(t('common.createSuccess'))
    } else {
      await DrainageLicenseApi.updateDrainageLicense(data)
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
    licenseNo: undefined,
    startDate: undefined,
    endDate: undefined,
    drainageType: undefined,
    approvalUnit: undefined,
    licenseStatus: undefined
  }
  formRef.value?.resetFields()
}
</script>
