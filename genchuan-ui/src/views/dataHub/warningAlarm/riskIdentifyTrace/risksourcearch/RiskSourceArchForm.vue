<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="700px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
      class="form-container"
    >
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="风险源ID" prop="riskSourceId">
            <el-input v-model="formData.riskSourceId" placeholder="请输入风险源ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="风险源编号" prop="riskSourceNo">
            <el-input v-model="formData.riskSourceNo" placeholder="请输入风险源编号" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="24">
          <el-form-item label="风险源名称" prop="riskSourceName">
            <el-input v-model="formData.riskSourceName" placeholder="请输入风险源名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="风险源类型" prop="riskSourceType">
            <el-select v-model="formData.riskSourceType" placeholder="请选择风险源类型">
              <el-option label="请选择字典生成" value="" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="风险等级" prop="riskLevel">
            <el-input v-model="formData.riskLevel" placeholder="请输入风险等级" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="所在行政区划代码" prop="regionCode">
            <el-input v-model="formData.regionCode" placeholder="请输入所在行政区划代码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所在行政区划名称" prop="regionName">
            <el-input v-model="formData.regionName" placeholder="请输入所在行政区划名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="24">
          <el-form-item label="所在位置描述" prop="locationDesc">
            <el-input
              v-model="formData.locationDesc"
              placeholder="请输入所在位置描述"
              type="textarea"
              :rows="3"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="关联资产ID" prop="assetId">
            <el-input v-model="formData.assetId" placeholder="请输入关联资产ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联资产名称" prop="assetName">
            <el-input v-model="formData.assetName" placeholder="请输入关联资产名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="责任部门ID" prop="deptId">
            <el-input v-model="formData.deptId" placeholder="请输入责任部门ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="责任部门名称" prop="deptName">
            <el-input v-model="formData.deptName" placeholder="请输入责任部门名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="历史预警次数" prop="historyWarnCount">
            <el-input
              v-model="formData.historyWarnCount"
              placeholder="请输入历史预警次数"
              type="number"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20" class="extension-fields">
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
  RiskSourceArchApi,
  RiskSourceArchVO
} from '@/api/dataHub/warningAlarm/riskIdentifyTrace/risksourcearch'

/** 风险源档案 表单 */
defineOptions({ name: 'RiskSourceArchForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  riskSourceId: undefined,
  riskSourceNo: undefined,
  riskSourceName: undefined,
  riskSourceType: undefined,
  riskLevel: undefined,
  regionCode: undefined,
  regionName: undefined,
  locationDesc: undefined,
  assetId: undefined,
  assetName: undefined,
  deptId: undefined,
  deptName: undefined,
  historyWarnCount: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  riskSourceId: [{ required: true, message: '风险源ID不能为空', trigger: 'blur' }],
  riskSourceNo: [{ required: true, message: '风险源编号不能为空', trigger: 'blur' }],
  riskSourceName: [{ required: true, message: '风险源名称不能为空', trigger: 'blur' }],
  riskSourceType: [{ required: true, message: '风险源类型不能为空', trigger: 'change' }],
  riskLevel: [{ required: true, message: '风险等级不能为空', trigger: 'blur' }],
  regionCode: [{ required: true, message: '所在行政区划代码不能为空', trigger: 'blur' }],
  regionName: [{ required: true, message: '所在行政区划名称不能为空', trigger: 'blur' }],
  locationDesc: [{ required: true, message: '所在位置描述不能为空', trigger: 'blur' }],
  deptId: [{ required: true, message: '责任部门ID不能为空', trigger: 'blur' }],
  deptName: [{ required: true, message: '责任部门名称不能为空', trigger: 'blur' }],
  historyWarnCount: [{ required: true, message: '历史预警次数不能为空', trigger: 'blur' }]
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
      formData.value = await RiskSourceArchApi.getRiskSourceArch(id)
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
    const data = formData.value as unknown as RiskSourceArchVO
    if (formType.value === 'create') {
      await RiskSourceArchApi.createRiskSourceArch(data)
      message.success(t('common.createSuccess'))
    } else {
      await RiskSourceArchApi.updateRiskSourceArch(data)
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
    riskSourceId: undefined,
    riskSourceNo: undefined,
    riskSourceName: undefined,
    riskSourceType: undefined,
    riskLevel: undefined,
    regionCode: undefined,
    regionName: undefined,
    locationDesc: undefined,
    assetId: undefined,
    assetName: undefined,
    deptId: undefined,
    deptName: undefined,
    historyWarnCount: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined
  }
  formRef.value?.resetFields()
}
</script>

<style scoped>
.form-container {
  padding: 10px 0;
}

.el-row {
  margin-bottom: 15px;
}

.extension-fields {
  margin-top: 20px;
  padding-top: 15px;
  border-top: 1px dashed #e5e7eb;
}

.el-textarea__inner {
  resize: none;
}
</style>
