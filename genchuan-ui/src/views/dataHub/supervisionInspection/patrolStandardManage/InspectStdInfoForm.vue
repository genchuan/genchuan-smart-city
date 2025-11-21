<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="600px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
      class="form-container"
    >
      <el-form-item label="标准ID" prop="stdId" required>
        <el-input v-model="formData.stdId" placeholder="请输入标准ID" />
      </el-form-item>
      <el-form-item label="标准名称" prop="stdName" required>
        <el-input v-model="formData.stdName" placeholder="请输入标准名称" />
      </el-form-item>
      <el-form-item label="标准编码" prop="stdCode" required>
        <el-input v-model="formData.stdCode" placeholder="请输入标准编码" />
      </el-form-item>
      <el-form-item label="标准类型" prop="stdType" required>
        <el-select v-model="formData.stdType" placeholder="请选择标准类型">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>

      <div class="form-section">
        <h4 class="section-title">区域信息</h4>
        <el-form-item label="适用区域代码" prop="areaCode">
          <el-input v-model="formData.areaCode" placeholder="请输入适用区域代码" />
        </el-form-item>
        <el-form-item label="适用区域名称" prop="areaName">
          <el-input v-model="formData.areaName" placeholder="请输入适用区域名称" />
        </el-form-item>
      </div>

      <div class="form-section">
        <h4 class="section-title">检查标准</h4>
        <el-form-item label="检查项列表" prop="ckItemList">
          <el-input v-model="formData.ckItemList" placeholder="请输入检查项列表" />
        </el-form-item>
        <el-form-item label="评分规则" prop="scoreRule">
          <el-input v-model="formData.scoreRule" placeholder="请输入评分规则" />
        </el-form-item>
        <el-form-item label="合格阈值" prop="qualifiedThreshold">
          <el-input
            v-model.number="formData.qualifiedThreshold"
            placeholder="请输入合格阈值"
            type="number"
          />
        </el-form-item>
      </div>

      <div class="form-section">
        <h4 class="section-title">状态与时间</h4>
        <el-form-item label="标准状态" prop="stdStatus">
          <el-radio-group v-model="formData.stdStatus">
            <el-radio :label="true">启用</el-radio>
            <el-radio :label="false">禁用</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="生效时间" prop="effectiveTime">
          <el-date-picker
            v-model="formData.effectiveTime"
            type="datetime"
            value-format="x"
            placeholder="选择生效时间"
          />
        </el-form-item>
        <el-form-item label="失效时间" prop="expireTime">
          <el-date-picker
            v-model="formData.expireTime"
            type="datetime"
            value-format="x"
            placeholder="选择失效时间"
          />
        </el-form-item>
      </div>

      <div class="form-section" v-if="formType === 'update'">
        <h4 class="section-title">系统信息</h4>
        <el-form-item label="创建人" prop="createUser">
          <el-input v-model="formData.createUser" disabled />
        </el-form-item>
        <el-form-item label="创建时间" prop="bizCreateTime">
          <el-date-picker
            v-model="formData.bizCreateTime"
            type="date"
            value-format="x"
            placeholder="选择创建时间"
            disabled
          />
        </el-form-item>
        <el-form-item label="更新人" prop="updateUser">
          <el-input v-model="formData.updateUser" disabled />
        </el-form-item>
      </div>

      <div class="form-section">
        <h4 class="section-title">扩展信息</h4>
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
      </div>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  InspectStdInfoApi,
  InspectStdInfoVO
} from '@/api/dataHub/supervisionInspection/patrolStandardManage'

/** 巡查巡检标准信息 表单 */
defineOptions({ name: 'InspectStdInfoForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<InspectStdInfoVO>>({
  id: undefined,
  stdId: undefined,
  stdName: undefined,
  stdCode: undefined,
  stdType: undefined,
  areaCode: undefined,
  areaName: undefined,
  ckItemList: undefined,
  scoreRule: undefined,
  qualifiedThreshold: undefined,
  stdStatus: true,
  effectiveTime: undefined,
  expireTime: undefined,
  createUser: undefined,
  bizCreateTime: undefined,
  updateUser: undefined,
  bizUpdateTime: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})

// 格式化日期
const formatDate = (val: any) => {
  return val ? new Date(Number(val)).toLocaleString() : '-'
}

const formRules = reactive({
  stdId: [{ required: true, message: '请输入标准ID', trigger: 'blur' }],
  stdName: [{ required: true, message: '请输入标准名称', trigger: 'blur' }],
  stdCode: [{ required: true, message: '请输入标准编码', trigger: 'blur' }],
  stdType: [{ required: true, message: '请选择标准类型', trigger: 'change' }],
  qualifiedThreshold: [
    { required: true, message: '请输入合格阈值', trigger: 'blur' },
    { type: 'number', message: '请输入数字', trigger: 'blur' }
  ],
  effectiveTime: [{ required: true, message: '请选择生效时间', trigger: 'change' }]
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
      const data = await InspectStdInfoApi.getInspectStdInfo(id)
      // 转换时间格式
      if (data.effectiveTime) data.effectiveTime = new Date(data.effectiveTime).getTime()
      if (data.expireTime) data.expireTime = new Date(data.expireTime).getTime()
      if (data.bizCreateTime) data.bizCreateTime = new Date(data.bizCreateTime).getTime()
      if (data.bizUpdateTime) data.bizUpdateTime = new Date(data.bizUpdateTime).getTime()
      formData.value = data
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
    const data = { ...formData.value } as InspectStdInfoVO
    // 转换时间格式
    if (data.effectiveTime) data.effectiveTime = new Date(Number(data.effectiveTime))
    if (data.expireTime) data.expireTime = new Date(Number(data.expireTime))

    if (formType.value === 'create') {
      await InspectStdInfoApi.createInspectStdInfo(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectStdInfoApi.updateInspectStdInfo(data)
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
    stdId: undefined,
    stdName: undefined,
    stdCode: undefined,
    stdType: undefined,
    areaCode: undefined,
    areaName: undefined,
    ckItemList: undefined,
    scoreRule: undefined,
    qualifiedThreshold: undefined,
    stdStatus: true,
    effectiveTime: undefined,
    expireTime: undefined,
    createUser: undefined,
    bizCreateTime: undefined,
    updateUser: undefined,
    bizUpdateTime: undefined,
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
  padding: 15px;
}

.form-section {
  margin: 15px 0;
  padding: 10px;
  background-color: #f9fafb;
  border-radius: 6px;
}

.section-title {
  margin: 0 0 15px 0;
  padding-left: 5px;
  font-size: 14px;
  color: #666;
  border-left: 3px solid #409eff;
}

::v-deep .el-form-item {
  margin-bottom: 15px;
}

::v-deep .el-form-item__label {
  color: #555;
  font-weight: 500;
}

::v-deep .el-dialog__body {
  padding: 15px 20px;
  max-height: 70vh;
  overflow-y: auto;
}
</style>
