<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="统计ID" prop="statMngMatterId">
        <el-input v-model="formData.statMngMatterId" placeholder="请输入统计ID" />
      </el-form-item>
      <el-form-item label="统计周期" prop="statCycle">
        <el-input v-model="formData.statCycle" placeholder="请输入统计周期" />
      </el-form-item>
      <el-form-item label="统计周期名称" prop="statCycleName">
        <el-input v-model="formData.statCycleName" placeholder="请输入统计周期名称" />
      </el-form-item>
      <el-form-item label="行政区划代码" prop="areaCode">
        <el-input v-model="formData.areaCode" placeholder="请输入行政区划代码" />
      </el-form-item>
      <el-form-item label="行政区划名称" prop="areaName">
        <el-input v-model="formData.areaName" placeholder="请输入行政区划名称" />
      </el-form-item>
      <el-form-item label="事项大类ID" prop="matterMajorId">
        <el-input v-model="formData.matterMajorId" placeholder="请输入事项大类ID" />
      </el-form-item>
      <el-form-item label="事项大类名称" prop="matterMajorName">
        <el-input v-model="formData.matterMajorName" placeholder="请输入事项大类名称" />
      </el-form-item>
      <el-form-item label="事项小类ID" prop="matterMinorId">
        <el-input v-model="formData.matterMinorId" placeholder="请输入事项小类ID" />
      </el-form-item>
      <el-form-item label="事项小类名称" prop="matterMinorName">
        <el-input v-model="formData.matterMinorName" placeholder="请输入事项小类名称" />
      </el-form-item>
      <el-form-item label="处置部门代码" prop="deptCode">
        <el-input v-model="formData.deptCode" placeholder="请输入处置部门代码" />
      </el-form-item>
      <el-form-item label="处置部门名称" prop="deptName">
        <el-input v-model="formData.deptName" placeholder="请输入处置部门名称" />
      </el-form-item>
      <el-form-item label="上报总数" prop="totalRptCount">
        <el-input v-model="formData.totalRptCount" placeholder="请输入上报总数" />
      </el-form-item>
      <el-form-item label="待处置数" prop="pendCount">
        <el-input v-model="formData.pendCount" placeholder="请输入待处置数" />
      </el-form-item>
      <el-form-item label="处置中数" prop="handlCount">
        <el-input v-model="formData.handlCount" placeholder="请输入处置中数" />
      </el-form-item>
      <el-form-item label="已办结数" prop="completedCount">
        <el-input v-model="formData.completedCount" placeholder="请输入已办结数" />
      </el-form-item>
      <el-form-item label="已驳回数" prop="rejectedCount">
        <el-input v-model="formData.rejectedCount" placeholder="请输入已驳回数" />
      </el-form-item>
      <el-form-item label="办结率" prop="completeRate">
        <el-input v-model="formData.completeRate" placeholder="请输入办结率" />
      </el-form-item>
      <el-form-item label="平均处置时长" prop="avgHandleEndure">
        <el-input v-model="formData.avgHandleEndure" placeholder="请输入平均处置时长" />
      </el-form-item>
      <el-form-item label="系统创建时间" prop="createTimeSys">
        <el-date-picker
          v-model="formData.createTimeSys"
          type="date"
          value-format="x"
          placeholder="选择系统创建时间"
        />
      </el-form-item>
      <el-form-item label="系统更新时间" prop="updateTimeSys">
        <el-date-picker
          v-model="formData.updateTimeSys"
          type="date"
          value-format="x"
          placeholder="选择系统更新时间"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { MngMatterApi, MngMatterVO } from '@/api/dataHub/managedComponent/mngmatter'

/** 管理事项统计 表单 */
defineOptions({ name: 'MngMatterForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  statMngMatterId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  areaCode: undefined,
  areaName: undefined,
  matterMajorId: undefined,
  matterMajorName: undefined,
  matterMinorId: undefined,
  matterMinorName: undefined,
  deptCode: undefined,
  deptName: undefined,
  totalRptCount: undefined,
  pendCount: undefined,
  handlCount: undefined,
  completedCount: undefined,
  rejectedCount: undefined,
  completeRate: undefined,
  avgHandleEndure: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  statMngMatterId: [{ required: true, message: '统计ID不能为空', trigger: 'blur' }],
  statCycle: [{ required: true, message: '统计周期不能为空', trigger: 'blur' }],
  statCycleName: [{ required: true, message: '统计周期名称不能为空', trigger: 'blur' }],
  areaCode: [{ required: true, message: '行政区划代码不能为空', trigger: 'blur' }],
  areaName: [{ required: true, message: '行政区划名称不能为空', trigger: 'blur' }],
  matterMajorId: [{ required: true, message: '事项大类ID不能为空', trigger: 'blur' }],
  matterMajorName: [{ required: true, message: '事项大类名称不能为空', trigger: 'blur' }],
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
      formData.value = await MngMatterApi.getMngMatter(id)
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
    const data = formData.value as unknown as MngMatterVO
    if (formType.value === 'create') {
      await MngMatterApi.createMngMatter(data)
      message.success(t('common.createSuccess'))
    } else {
      await MngMatterApi.updateMngMatter(data)
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
    statMngMatterId: undefined,
    statCycle: undefined,
    statCycleName: undefined,
    areaCode: undefined,
    areaName: undefined,
    matterMajorId: undefined,
    matterMajorName: undefined,
    matterMinorId: undefined,
    matterMinorName: undefined,
    deptCode: undefined,
    deptName: undefined,
    totalRptCount: undefined,
    pendCount: undefined,
    handlCount: undefined,
    completedCount: undefined,
    rejectedCount: undefined,
    completeRate: undefined,
    avgHandleEndure: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined,
  }
  formRef.value?.resetFields()
}
</script>
