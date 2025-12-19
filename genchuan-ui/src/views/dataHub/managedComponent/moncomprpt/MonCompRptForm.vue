<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="250px"
      v-loading="formLoading"
    >
      <el-form-item label="唯一编码" prop="statId">
        <el-input v-model="formData.statId" placeholder="请输入唯一编码" />
      </el-form-item>
      <el-form-item label="统计周期类型" prop="statCycle">
        <el-input v-model="formData.statCycle" placeholder="请输入统计周期类型" />
      </el-form-item>
      <el-form-item label="统计周期的中文描述" prop="statCycleName">
        <el-input v-model="formData.statCycleName" placeholder="请输入统计周期的中文描述" />
      </el-form-item>
      <el-form-item label="行政区划代码" prop="regionCode">
        <el-input v-model="formData.regionCode" placeholder="请输入行政区划代码" />
      </el-form-item>
      <el-form-item label="与行政区划代码关联" prop="regionName">
        <el-input v-model="formData.regionName" placeholder="请输入与行政区划代码关联" />
      </el-form-item>
      <el-form-item label="关联监测部件分类配置表的大类ID" prop="compMajorId">
        <el-input v-model="formData.compMajorId" placeholder="请输入关联监测部件分类配置表的大类ID" />
      </el-form-item>
      <el-form-item label="与部件大类ID关联" prop="compMajorName">
        <el-input v-model="formData.compMajorName" placeholder="请输入与部件大类ID关联" />
      </el-form-item>
      <el-form-item label="关联监测部件分类配置表的小类ID" prop="compMinorId">
        <el-input v-model="formData.compMinorId" placeholder="请输入关联监测部件分类配置表的小类ID" />
      </el-form-item>
      <el-form-item label="与部件小类ID关联，自动同步名称" prop="compMinorName">
        <el-input v-model="formData.compMinorName" placeholder="请输入与部件小类ID关联" />
      </el-form-item>
      <el-form-item label="该维度下监测部件总数量" prop="totalCompCount">
        <el-input v-model="formData.totalCompCount" placeholder="请输入该维度下监测部件总数量" />
      </el-form-item>
      <el-form-item label="状态为“正常”的部件数量" prop="normalCompCount">
        <el-input v-model="formData.normalCompCount" placeholder="请输入状态为“正常”的部件数量" />
      </el-form-item>
      <el-form-item label="状态为“异常”的部件数量" prop="abnCompCount">
        <el-input v-model="formData.abnCompCount" placeholder="请输入状态为“异常”的部件数量" />
      </el-form-item>
      <el-form-item label="状态为“维护”的部件数量" prop="mntCompCount">
        <el-input v-model="formData.mntCompCount" placeholder="请输入状态为“维护”的部件数量" />
      </el-form-item>
      <el-form-item label="状态为“废弃”的部件数量" prop="discardCompCount">
        <el-input v-model="formData.discardCompCount" placeholder="请输入状态为“废弃”的部件数量" />
      </el-form-item>
      <el-form-item label="统计周期内新增的部件数量" prop="newCompCount">
        <el-input v-model="formData.newCompCount" placeholder="请输入统计周期内新增的部件数量" />
      </el-form-item>
      <el-form-item label="统计周期内更新的部件数量" prop="updateCompCount">
        <el-input v-model="formData.updateCompCount" placeholder="请输入统计周期内更新的部件数量" />
      </el-form-item>
      <el-form-item label="生成报表的用户ID" prop="statUser">
        <el-input v-model="formData.statUser" placeholder="请输入生成报表的用户ID" />
      </el-form-item>
      <el-form-item label="报表生成时间" prop="statTime">
        <el-date-picker
          v-model="formData.statTime"
          type="date"
          value-format="x"
          placeholder="选择报表生成时间"
        />
      </el-form-item>
      <el-form-item label="报表说明" prop="rptRemark">
        <el-input v-model="formData.rptRemark" placeholder="请输入报表说明" />
      </el-form-item>
      <el-form-item label="预留字段" prop="extCat1">
        <el-input v-model="formData.extCat1" placeholder="请输入预留字段" />
      </el-form-item>
      <el-form-item label="预留字段" prop="extCat2">
        <el-input v-model="formData.extCat2" placeholder="请输入预留字段" />
      </el-form-item>
      <el-form-item label="预留通用字段" prop="extCommon1">
        <el-input v-model="formData.extCommon1" placeholder="请输入预留通用字段" />
      </el-form-item>
      <el-form-item label="预留通用字段" prop="extCommon2">
        <el-input v-model="formData.extCommon2" placeholder="请输入预留通用字段" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { MonCompRptApi, MonCompRptVO } from '@/api/dataHub/managedComponent/moncomprpt'

/** 监测部件统计报 表单 */
defineOptions({ name: 'MonCompRptForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  statId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  regionCode: undefined,
  regionName: undefined,
  compMajorId: undefined,
  compMajorName: undefined,
  compMinorId: undefined,
  compMinorName: undefined,
  totalCompCount: undefined,
  normalCompCount: undefined,
  abnCompCount: undefined,
  mntCompCount: undefined,
  discardCompCount: undefined,
  newCompCount: undefined,
  updateCompCount: undefined,
  statUser: undefined,
  statTime: undefined,
  rptRemark: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
})
const formRules = reactive({
  statId: [{ required: true, message: '唯一编码不能为空', trigger: 'blur' }],
  statCycle: [{ required: true, message: '统计周期类型不能为空', trigger: 'blur' }],
  statCycleName: [{ required: true, message: '统计周期的中文描述不能为空', trigger: 'blur' }],
  regionCode: [{ required: true, message: '行政区划代码不能为空', trigger: 'blur' }],
  regionName: [{ required: true, message: '与行政区划代码关联，自动同步区域名称不能为空', trigger: 'blur' }],
  compMajorId: [{ required: true, message: '关联监测部件分类配置表的大类ID不能为空', trigger: 'blur' }],
  compMajorName: [{ required: true, message: '与部件大类ID关联，自动同步名称不能为空', trigger: 'blur' }],
  totalCompCount: [{ required: true, message: '该维度下监测部件总数量不能为空', trigger: 'blur' }],
  normalCompCount: [{ required: true, message: '状态为“正常”的部件数量不能为空', trigger: 'blur' }],
  abnCompCount: [{ required: true, message: '状态为“异常”的部件数量不能为空', trigger: 'blur' }],
  mntCompCount: [{ required: true, message: '状态为“维护”的部件数量不能为空', trigger: 'blur' }],
  discardCompCount: [{ required: true, message: '状态为“废弃”的部件数量不能为空', trigger: 'blur' }],
  statUser: [{ required: true, message: '生成报表的用户ID不能为空', trigger: 'blur' }],
  statTime: [{ required: true, message: '报表生成时间', trigger: 'blur' }],
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
      formData.value = await MonCompRptApi.getMonCompRpt(id)
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
    const data = formData.value as unknown as MonCompRptVO
    if (formType.value === 'create') {
      await MonCompRptApi.createMonCompRpt(data)
      message.success(t('common.createSuccess'))
    } else {
      await MonCompRptApi.updateMonCompRpt(data)
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
    statId: undefined,
    statCycle: undefined,
    statCycleName: undefined,
    regionCode: undefined,
    regionName: undefined,
    compMajorId: undefined,
    compMajorName: undefined,
    compMinorId: undefined,
    compMinorName: undefined,
    totalCompCount: undefined,
    normalCompCount: undefined,
    abnCompCount: undefined,
    mntCompCount: undefined,
    discardCompCount: undefined,
    newCompCount: undefined,
    updateCompCount: undefined,
    statUser: undefined,
    statTime: undefined,
    rptRemark: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined,
  }
  formRef.value?.resetFields()
}
</script>
