<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="方案ID" prop="planId">
        <el-input v-model="formData.planId" placeholder="请输入方案ID" />
      </el-form-item>
      <el-form-item label="方案编号" prop="planNo">
        <el-input v-model="formData.planNo" placeholder="请输入方案编号" />
      </el-form-item>
      <el-form-item label="关联响应ID" prop="respId">
        <el-input v-model="formData.respId" placeholder="请输入关联响应ID" />
      </el-form-item>
      <el-form-item label="响应编号" prop="respNo">
        <el-input v-model="formData.respNo" placeholder="请输入响应编号" />
      </el-form-item>
      <el-form-item label="方案名称" prop="planName">
        <el-input v-model="formData.planName" placeholder="请输入方案名称" />
      </el-form-item>
      <el-form-item label="调度需求描述" prop="demandDesc">
        <el-input v-model="formData.demandDesc" placeholder="请输入调度需求描述" />
      </el-form-item>
      <el-form-item label="资源类型" prop="resType">
        <el-select v-model="formData.resType" placeholder="请选择资源类型">
          <el-option label="请选择字典生成" value="1" />
        </el-select>
      </el-form-item>
      <el-form-item label="资源ID" prop="resId">
        <el-input v-model="formData.resId" placeholder="请输入资源ID" />
      </el-form-item>
      <el-form-item label="资源名称" prop="resName">
        <el-input v-model="formData.resName" placeholder="请输入资源名称" />
      </el-form-item>
      <el-form-item label="调度数量" prop="dispatchQuantity">
        <el-input v-model="formData.dispatchQuantity" placeholder="请输入调度数量" />
      </el-form-item>
      <el-form-item label="调配目的地" prop="dispatchDest">
        <el-input v-model="formData.dispatchDest" placeholder="请输入调配目的地" />
      </el-form-item>
      <el-form-item label="目的地坐标X" prop="destCoordX">
        <el-input v-model="formData.destCoordX" placeholder="请输入目的地坐标X" />
      </el-form-item>
      <el-form-item label="目的地坐标Y" prop="destCoordY">
        <el-input v-model="formData.destCoordY" placeholder="请输入目的地坐标Y" />
      </el-form-item>
      <el-form-item label="负责人ID" prop="chargerId">
        <el-input v-model="formData.chargerId" placeholder="请输入负责人ID" />
      </el-form-item>
      <el-form-item label="负责人姓名" prop="chargerName">
        <el-input v-model="formData.chargerName" placeholder="请输入负责人姓名" />
      </el-form-item>
      <el-form-item label="要求完成时间" prop="requireCompleteTime">
        <el-date-picker
          v-model="formData.requireCompleteTime"
          type="date"
          value-format="x"
          placeholder="选择要求完成时间"
        />
      </el-form-item>
      <el-form-item label="方案状态" prop="planStatus">
        <el-radio-group v-model="formData.planStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="创建人" prop="createUser">
        <el-input v-model="formData.createUser" placeholder="请输入创建人" />
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
import { DispatchPlanApi, DispatchPlanVO } from '@/api/dataHub/commandAndCoordination/dispatchplan'

/** 调度方案 表单 */
defineOptions({ name: 'DispatchPlanForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  planId: undefined,
  planNo: undefined,
  respId: undefined,
  respNo: undefined,
  planName: undefined,
  demandDesc: undefined,
  resType: undefined,
  resId: undefined,
  resName: undefined,
  dispatchQuantity: undefined,
  dispatchDest: undefined,
  destCoordX: undefined,
  destCoordY: undefined,
  chargerId: undefined,
  chargerName: undefined,
  requireCompleteTime: undefined,
  planStatus: undefined,
  createUser: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
})
const formRules = reactive({
  planId: [{ required: true, message: '方案ID不能为空', trigger: 'blur' }],
  planNo: [{ required: true, message: '方案编号不能为空', trigger: 'blur' }],
  respId: [{ required: true, message: '关联响应ID不能为空', trigger: 'blur' }],
  respNo: [{ required: true, message: '响应编号不能为空', trigger: 'blur' }],
  planName: [{ required: true, message: '方案名称不能为空', trigger: 'blur' }],
  demandDesc: [{ required: true, message: '调度需求描述不能为空', trigger: 'blur' }],
  resType: [{ required: true, message: '资源类型不能为空', trigger: 'change' }],
  resId: [{ required: true, message: '资源ID不能为空', trigger: 'blur' }],
  resName: [{ required: true, message: '资源名称不能为空', trigger: 'blur' }],
  dispatchQuantity: [{ required: true, message: '调度数量不能为空', trigger: 'blur' }],
  dispatchDest: [{ required: true, message: '调配目的地不能为空', trigger: 'blur' }],
  chargerId: [{ required: true, message: '负责人ID不能为空', trigger: 'blur' }],
  chargerName: [{ required: true, message: '负责人姓名不能为空', trigger: 'blur' }],
  requireCompleteTime: [{ required: true, message: '要求完成时间不能为空', trigger: 'blur' }],
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
      formData.value = await DispatchPlanApi.getDispatchPlan(id)
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
    const data = formData.value as unknown as DispatchPlanVO
    if (formType.value === 'create') {
      await DispatchPlanApi.createDispatchPlan(data)
      message.success(t('common.createSuccess'))
    } else {
      await DispatchPlanApi.updateDispatchPlan(data)
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
    planId: undefined,
    planNo: undefined,
    respId: undefined,
    respNo: undefined,
    planName: undefined,
    demandDesc: undefined,
    resType: undefined,
    resId: undefined,
    resName: undefined,
    dispatchQuantity: undefined,
    dispatchDest: undefined,
    destCoordX: undefined,
    destCoordY: undefined,
    chargerId: undefined,
    chargerName: undefined,
    requireCompleteTime: undefined,
    planStatus: undefined,
    createUser: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined,
  }
  formRef.value?.resetFields()
}
</script>
