<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="预案ID" prop="planId">
        <el-input v-model="formData.planId" placeholder="请输入预案ID" />
      </el-form-item>
      <el-form-item label="预案编号" prop="planNo">
        <el-input v-model="formData.planNo" placeholder="请输入预案编号" />
      </el-form-item>
      <el-form-item label="预案标准名称" prop="planName">
        <el-input v-model="formData.planName" placeholder="请输入预案标准名称" />
      </el-form-item>
      <el-form-item label="分类ID" prop="catId">
        <el-input v-model="formData.catId" placeholder="请输入分类ID" />
      </el-form-item>
      <el-form-item label="分类名称" prop="catName">
        <el-input v-model="formData.catName" placeholder="请输入分类名称" />
      </el-form-item>
      <el-form-item label="适用区域代码" prop="applyRegionCode">
        <el-input v-model="formData.applyRegionCode" placeholder="请输入适用区域代码" />
      </el-form-item>
      <el-form-item label="适用区域名称" prop="applyRegionName">
        <el-input v-model="formData.applyRegionName" placeholder="请输入适用区域名称" />
      </el-form-item>
      <el-form-item label="预案版本" prop="planVersion">
        <el-input v-model="formData.planVersion" placeholder="请输入预案版本" />
      </el-form-item>
      <el-form-item label="生效时间" prop="effectiveTime">
        <el-date-picker
          v-model="formData.effectiveTime"
          type="date"
          value-format="x"
          placeholder="选择生效时间"
        />
      </el-form-item>
      <el-form-item label="失效时间" prop="expireTime">
        <el-date-picker
          v-model="formData.expireTime"
          type="date"
          value-format="x"
          placeholder="选择失效时间"
        />
      </el-form-item>
      <el-form-item label="预案文档路径" prop="planDocPath">
        <el-input v-model="formData.planDocPath" placeholder="请输入预案文档路径" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { EmerPlanLibApi, EmerPlanLibVO } from '@/api/dataHub/commandAndCoordination/emerplanlib'

/** 预案库 表单 */
defineOptions({ name: 'EmerPlanLibForm' })

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
  planName: undefined,
  catId: undefined,
  catName: undefined,
  applyRegionCode: undefined,
  applyRegionName: undefined,
  planVersion: undefined,
  effectiveTime: undefined,
  expireTime: undefined,
  planDocPath: undefined,
})
const formRules = reactive({
  planId: [{ required: true, message: '预案ID不能为空', trigger: 'blur' }],
  planNo: [{ required: true, message: '预案编号不能为空', trigger: 'blur' }],
  planName: [{ required: true, message: '预案标准名称不能为空', trigger: 'blur' }],
  catId: [{ required: true, message: '分类ID不能为空', trigger: 'blur' }],
  catName: [{ required: true, message: '分类名称不能为空', trigger: 'blur' }],
  applyRegionCode: [{ required: true, message: '适用区域代码不能为空', trigger: 'blur' }],
  applyRegionName: [{ required: true, message: '适用区域名称不能为空', trigger: 'blur' }],
  planVersion: [{ required: true, message: '预案版本不能为空', trigger: 'blur' }],
  effectiveTime: [{ required: true, message: '生效时间不能为空', trigger: 'blur' }],
  planDocPath: [{ required: true, message: '预案文档路径不能为空', trigger: 'blur' }],
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
      formData.value = await EmerPlanLibApi.getEmerPlanLib(id)
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
    const data = formData.value as unknown as EmerPlanLibVO
    if (formType.value === 'create') {
      await EmerPlanLibApi.createEmerPlanLib(data)
      message.success(t('common.createSuccess'))
    } else {
      await EmerPlanLibApi.updateEmerPlanLib(data)
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
    planName: undefined,
    catId: undefined,
    catName: undefined,
    applyRegionCode: undefined,
    applyRegionName: undefined,
    planVersion: undefined,
    effectiveTime: undefined,
    expireTime: undefined,
    planDocPath: undefined,
  }
  formRef.value?.resetFields()
}
</script>
