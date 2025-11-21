<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="680px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="150px"
      v-loading="formLoading"
      class="form-container"
    >
      <el-form-item label="报表ID" prop="rptId">
        <el-input v-model="formData.rptId" placeholder="请输入报表ID" />
      </el-form-item>
      <el-form-item label="统计周期" prop="statCycle">
        <el-input v-model="formData.statCycle" placeholder="请输入统计周期" />
      </el-form-item>
      <el-form-item label="统计周期名称" prop="statCycleName">
        <el-input v-model="formData.statCycleName" placeholder="请输入统计周期名称" />
      </el-form-item>
      <el-form-item label="行政区划代码" prop="adminCode">
        <el-input v-model="formData.adminCode" placeholder="请输入行政区划代码" />
      </el-form-item>
      <el-form-item label="行政区划名称" prop="adminName">
        <el-input v-model="formData.adminName" placeholder="请输入行政区划名称" />
      </el-form-item>
      <el-form-item label="点位类型" prop="ptType">
        <el-input v-model="formData.ptType" placeholder="请输入点位类型" />
      </el-form-item>

      <!-- 使用grid布局优化表单布局 -->
      <div class="grid grid-cols-2 gap-4">
        <el-form-item label="点位总数(个)" prop="totalPtCount">
          <el-input v-model.number="formData.totalPtCount" placeholder="请输入点位总数(个)" />
        </el-form-item>
        <el-form-item label="启用点位数量(个)" prop="enablePtCount">
          <el-input v-model.number="formData.enablePtCount" placeholder="请输入启用点位数量(个)" />
        </el-form-item>
        <el-form-item label="停用点位数量(个)" prop="disablePtCount">
          <el-input v-model.number="formData.disablePtCount" placeholder="请输入停用点位数量(个)" />
        </el-form-item>
        <el-form-item label="新增点位数量(个)" prop="newPtCount">
          <el-input v-model.number="formData.newPtCount" placeholder="请输入新增点位数量(个)" />
        </el-form-item>
      </div>

      <el-form-item label="关联设备总数(个)" prop="relDeviceCount">
        <el-input v-model.number="formData.relDeviceCount" placeholder="请输入关联设备总数(个)" />
      </el-form-item>
      <el-form-item label="报表生成时间" prop="rptCreateTime">
        <el-date-picker
          v-model="formData.rptCreateTime"
          type="date"
          value-format="x"
          placeholder="选择报表生成时间"
        />
      </el-form-item>
      <el-form-item label="报表生成人" prop="rptCreateUser">
        <el-input v-model="formData.rptCreateUser" placeholder="请输入报表生成人" />
      </el-form-item>
      <el-form-item label="报表备注" prop="rptRemark">
        <el-input
          v-model="formData.rptRemark"
          placeholder="请输入报表备注"
          type="textarea"
          rows="3"
        />
      </el-form-item>

      <div class="grid grid-cols-2 gap-4 mt-2">
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
  BasicMonIdxRptApi,
  BasicMonIdxRptVO
} from '@/api/dataHub/operationMonitor/basicMonitorReport/basicmonidxrpt'

/** 基础监测指标报 表单 */
defineOptions({ name: 'BasicMonIdxRptForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  rptId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  adminCode: undefined,
  adminName: undefined,
  ptType: undefined,
  totalPtCount: undefined,
  enablePtCount: undefined,
  disablePtCount: undefined,
  newPtCount: undefined,
  relDeviceCount: undefined,
  rptCreateTime: undefined,
  rptCreateUser: undefined,
  rptRemark: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  statCycle: [{ required: true, message: '统计周期不能为空', trigger: 'blur' }],
  statCycleName: [{ required: true, message: '统计周期名称不能为空', trigger: 'blur' }],
  adminCode: [{ required: true, message: '行政区划代码不能为空', trigger: 'blur' }],
  adminName: [{ required: true, message: '行政区划名称不能为空', trigger: 'blur' }],
  ptType: [{ required: true, message: '点位类型不能为空', trigger: 'blur' }],
  totalPtCount: [{ required: true, message: '点位总数(个)不能为空', trigger: 'blur' }],
  enablePtCount: [{ required: true, message: '启用点位数量(个)不能为空', trigger: 'blur' }],
  disablePtCount: [{ required: true, message: '停用点位数量(个)不能为空', trigger: 'blur' }],
  rptCreateTime: [{ required: true, message: '报表生成时间不能为空', trigger: 'blur' }],
  rptCreateUser: [{ required: true, message: '报表生成人不能为空', trigger: 'blur' }]
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
      formData.value = await BasicMonIdxRptApi.getBasicMonIdxRpt(id)
    } finally {
      formLoading.value = false
    }
  }
}
defineExpose({ open }) // 提供 open 方法，用于打开弹窗

/** 提交表单 */
const emit = defineEmits(['success']) // 定义 success 事件
const submitForm = async () => {
  // 校验表单
  await formRef.value.validate()
  // 提交请求
  formLoading.value = true
  try {
    const data = formData.value as unknown as BasicMonIdxRptVO
    if (formType.value === 'create') {
      await BasicMonIdxRptApi.createBasicMonIdxRpt(data)
      message.success(t('common.createSuccess'))
    } else {
      await BasicMonIdxRptApi.updateBasicMonIdxRpt(data)
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
    rptId: undefined,
    statCycle: undefined,
    statCycleName: undefined,
    adminCode: undefined,
    adminName: undefined,
    ptType: undefined,
    totalPtCount: undefined,
    enablePtCount: undefined,
    disablePtCount: undefined,
    newPtCount: undefined,
    relDeviceCount: undefined,
    rptCreateTime: undefined,
    rptCreateUser: undefined,
    rptRemark: undefined,
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
  padding: 15px 0;
}

::v-deep .el-form-item {
  margin-bottom: 15px;
}

::v-deep .el-input__wrapper {
  border-radius: 6px;
}

::v-deep .el-textarea__wrapper {
  border-radius: 6px;
}
</style>
