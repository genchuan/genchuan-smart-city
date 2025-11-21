<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="编号" prop="number">
        <el-input v-model="formData.number" placeholder="请输入编号" />
      </el-form-item>
      <el-form-item label="巡查巡检时间" prop="inspectionAndPatrolTime">
        <el-date-picker
          v-model="formData.inspectionAndPatrolTime"
          type="date"
          value-format="x"
          placeholder="选择巡查巡检时间"
        />
      </el-form-item>
      <el-form-item label="巡查巡检人员" prop="patrolPersonnel">
        <el-input v-model="formData.patrolPersonnel" placeholder="请输入巡查巡检人员" />
      </el-form-item>
      <el-form-item label="巡查巡检区域" prop="patrolInspectionArea">
        <el-input v-model="formData.patrolInspectionArea" placeholder="请输入巡查巡检区域" />
      </el-form-item>
      <el-form-item label="巡查巡检内容" prop="inspectionAndPatrolContent">
        <el-input v-model="formData.inspectionAndPatrolContent" type="textarea" placeholder="请输入巡查巡检内容" />
      </el-form-item>
      <el-form-item label="异常情况描述" prop="abnormalSituationDescription">
        <el-input v-model="formData.abnormalSituationDescription" type="textarea" placeholder="请输入异常情况描述" />
      </el-form-item>
      <el-form-item label="备注" prop="notes">
        <el-input v-model="formData.notes" placeholder="请输入备注" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { InspectionAndPatrolCategoryApi, InspectionAndPatrolCategoryVO } from '@/api/smartcity/inspectionandpatrolcategory'

/** 巡查巡检类 表单 */
defineOptions({ name: 'InspectionAndPatrolCategoryForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  number: undefined,
  inspectionAndPatrolTime: undefined,
  patrolPersonnel: undefined,
  patrolInspectionArea: undefined,
  inspectionAndPatrolContent: undefined,
  abnormalSituationDescription: undefined,
  notes: undefined
})
const formRules = reactive({
  inspectionAndPatrolTime: [{ required: true, message: '巡查巡检时间不能为空', trigger: 'blur' }]
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
      formData.value = await InspectionAndPatrolCategoryApi.getInspectionAndPatrolCategory(id)
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
    const data = formData.value as unknown as InspectionAndPatrolCategoryVO
    if (formType.value === 'create') {
      await InspectionAndPatrolCategoryApi.createInspectionAndPatrolCategory(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectionAndPatrolCategoryApi.updateInspectionAndPatrolCategory(data)
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
    number: undefined,
    inspectionAndPatrolTime: undefined,
    patrolPersonnel: undefined,
    patrolInspectionArea: undefined,
    inspectionAndPatrolContent: undefined,
    abnormalSituationDescription: undefined,
    notes: undefined
  }
  formRef.value?.resetFields()
}
</script>