<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="桥梁名称" prop="bridgeName">
        <el-input v-model="formData.bridgeName" placeholder="请输入桥梁名称" />
      </el-form-item>
      <el-form-item label="桥梁编号" prop="bridgeNumber">
        <el-input v-model="formData.bridgeNumber" placeholder="请输入桥梁编号" />
      </el-form-item>
      <el-form-item label="桥梁位置" prop="bridgeLocation">
        <el-input v-model="formData.bridgeLocation" placeholder="请输入桥梁位置" />
      </el-form-item>
      <el-form-item label="桥梁类型" prop="bridgeType">
        <el-select v-model="formData.bridgeType" placeholder="请选择桥梁类型">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="建造时间" prop="constructionTime">
        <el-date-picker
          v-model="formData.constructionTime"
          type="date"
          value-format="x"
          placeholder="选择建造时间"
        />
      </el-form-item>
      <el-form-item label="设计荷载" prop="designLoad">
        <el-input v-model="formData.designLoad" placeholder="请输入设计荷载" />
      </el-form-item>
      <el-form-item label="结构部件状况" prop="conditionStructuralComponents">
        <el-input v-model="formData.conditionStructuralComponents" placeholder="请输入结构部件状况" />
      </el-form-item>
      <el-form-item label="基础沉降数据" prop="basicSettlementData">
        <el-input v-model="formData.basicSettlementData" placeholder="请输入基础沉降数据" />
      </el-form-item>
      <el-form-item label="索力" prop="cableForce">
        <el-input v-model="formData.cableForce" placeholder="请输入索力" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { BridgeSafetyApi, BridgeSafetyVO } from '@/api/smartcity/bridgesafety'

/** 桥梁安全 表单 */
defineOptions({ name: 'BridgeSafetyForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  bridgeName: undefined,
  bridgeNumber: undefined,
  bridgeLocation: undefined,
  bridgeType: undefined,
  constructionTime: undefined,
  designLoad: undefined,
  conditionStructuralComponents: undefined,
  basicSettlementData: undefined,
  cableForce: undefined
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
      formData.value = await BridgeSafetyApi.getBridgeSafety(id)
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
    const data = formData.value as unknown as BridgeSafetyVO
    if (formType.value === 'create') {
      await BridgeSafetyApi.createBridgeSafety(data)
      message.success(t('common.createSuccess'))
    } else {
      await BridgeSafetyApi.updateBridgeSafety(data)
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
    bridgeName: undefined,
    bridgeNumber: undefined,
    bridgeLocation: undefined,
    bridgeType: undefined,
    constructionTime: undefined,
    designLoad: undefined,
    conditionStructuralComponents: undefined,
    basicSettlementData: undefined,
    cableForce: undefined
  }
  formRef.value?.resetFields()
}
</script>