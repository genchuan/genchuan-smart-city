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
      <el-form-item label="如“2025年09月”" prop="statCycleName">
        <el-input v-model="formData.statCycleName" placeholder="请输入如“2025年09月”" />
      </el-form-item>
      <el-form-item label="统计区域的行政区划代码" prop="regionCode">
        <el-input v-model="formData.regionCode" placeholder="请输入统计区域的行政区划代码" />
      </el-form-item>
      <el-form-item label="与行政区划代码关联" prop="regionName">
        <el-input v-model="formData.regionName" placeholder="请输入与行政区划代码关联" />
      </el-form-item>
      <el-form-item label="关联监测事件分类配置表的大类ID" prop="evtMajorId">
        <el-input v-model="formData.evtMajorId" placeholder="请输入关联监测事件分类配置表的大类ID" />
      </el-form-item>
      <el-form-item label="与事件大类ID关联" prop="evtMajorName">
        <el-input v-model="formData.evtMajorName" placeholder="请输入与事件大类ID关联" />
      </el-form-item>
      <el-form-item label="关联监测事件分类配置表的小类ID" prop="evtMinorId">
        <el-input v-model="formData.evtMinorId" placeholder="请输入关联监测事件分类配置表的小类ID" />
      </el-form-item>
      <el-form-item label="与事件小类ID关联" prop="evtMinorName">
        <el-input v-model="formData.evtMinorName" placeholder="请输入与事件小类ID关联" />
      </el-form-item>
      <el-form-item label="事件处置部门统一社会信用代码" prop="deptCode">
        <el-input v-model="formData.deptCode" placeholder="请输入事件处置部门统一社会信用代码" />
      </el-form-item>
      <el-form-item label="与处置部门代码关联" prop="deptName">
        <el-input v-model="formData.deptName" placeholder="请输入与处置部门代码关联" />
      </el-form-item>
      <el-form-item label="统计周期内该维度下事件上报总数" prop="totalRptCount">
        <el-input v-model="formData.totalRptCount" placeholder="请输入统计周期内该维度下事件上报总数" />
      </el-form-item>
      <el-form-item label="状态为“待处置”的事件数量" prop="pendCount">
        <el-input v-model="formData.pendCount" placeholder="请输入状态为“待处置”的事件数量" />
      </el-form-item>
      <el-form-item label="状态为“处置中”的事件数量" prop="handlCount">
        <el-input v-model="formData.handlCount" placeholder="请输入状态为“处置中”的事件数量" />
      </el-form-item>
      <el-form-item label="状态为“已办结”的事件数量" prop="completedCount">
        <el-input v-model="formData.completedCount" placeholder="请输入状态为“已办结”的事件数量" />
      </el-form-item>
      <el-form-item label="状态为“已驳回”的事件数量" prop="rejectedCount">
        <el-input v-model="formData.rejectedCount" placeholder="请输入状态为“已驳回”的事件数量" />
      </el-form-item>
      <el-form-item label="事件等级为“一级”的数量" prop="level1Count">
        <el-input v-model="formData.level1Count" placeholder="请输入事件等级为“一级”的数量" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { MonEvtRptApi, MonEvtRptVO } from '@/api/dataHub/managedComponent/monevtrpt'

/** 监测事件统计报 表单 */
defineOptions({ name: 'MonEvtRptForm' })

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
  evtMajorId: undefined,
  evtMajorName: undefined,
  evtMinorId: undefined,
  evtMinorName: undefined,
  deptCode: undefined,
  deptName: undefined,
  totalRptCount: undefined,
  pendCount: undefined,
  handlCount: undefined,
  completedCount: undefined,
  rejectedCount: undefined,
  level1Count: undefined,
})
const formRules = reactive({
  statId: [{ required: true, message: '唯一编码不能为空', trigger: 'blur' }],
  statCycle: [{ required: true, message: '统计周期类型不能为空', trigger: 'blur' }],
  statCycleName: [{ required: true, message: '如“2025年09月”不能为空', trigger: 'blur' }],
  regionCode: [{ required: true, message: '统计区域的行政区划代码不能为空', trigger: 'blur' }],
  regionName: [{ required: true, message: '与行政区划代码关联不能为空', trigger: 'blur' }],
  evtMajorId: [{ required: true, message: '关联监测事件分类配置表的大类ID不能为空', trigger: 'blur' }],
  evtMajorName: [{ required: true, message: '与事件大类ID关联不能为空', trigger: 'blur' }],
  totalRptCount: [{ required: true, message: '统计周期内该维度下事件上报总数不能为空', trigger: 'blur' }],
  pendCount: [{ required: true, message: '状态为“待处置”的事件数量不能为空', trigger: 'blur' }],
  handlCount: [{ required: true, message: '状态为“处置中”的事件数量不能为空', trigger: 'blur' }],
  completedCount: [{ required: true, message: '状态为“已办结”的事件数量不能为空', trigger: 'blur' }],
  rejectedCount: [{ required: true, message: '状态为“已驳回”的事件数量不能为空', trigger: 'blur' }],
  level1Count: [{ required: true, message: '事件等级为“一级”的数量不能为空', trigger: 'blur' }],
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
      formData.value = await MonEvtRptApi.getMonEvtRpt(id)
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
    const data = formData.value as unknown as MonEvtRptVO
    if (formType.value === 'create') {
      await MonEvtRptApi.createMonEvtRpt(data)
      message.success(t('common.createSuccess'))
    } else {
      await MonEvtRptApi.updateMonEvtRpt(data)
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
    evtMajorId: undefined,
    evtMajorName: undefined,
    evtMinorId: undefined,
    evtMinorName: undefined,
    deptCode: undefined,
    deptName: undefined,
    totalRptCount: undefined,
    pendCount: undefined,
    handlCount: undefined,
    completedCount: undefined,
    rejectedCount: undefined,
    level1Count: undefined,
  }
  formRef.value?.resetFields()
}
</script>
