<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="250px"
      v-loading="formLoading"
    >
      <el-form-item label="统计ID" prop="statMngCompId">
        <el-input v-model="formData.statMngCompId" placeholder="请输入统计ID" />
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
      <el-form-item label="部件大类ID" prop="compMajorId">
        <el-input v-model="formData.compMajorId" placeholder="请输入部件大类ID" />
      </el-form-item>
      <el-form-item label="部件大类名称" prop="compMajorName">
        <el-input v-model="formData.compMajorName" placeholder="请输入部件大类名称" />
      </el-form-item>
      <el-form-item label="部件小类ID" prop="compMinorId">
        <el-input v-model="formData.compMinorId" placeholder="请输入部件小类ID" />
      </el-form-item>
      <el-form-item label="部件小类名称" prop="compMinorName">
        <el-input v-model="formData.compMinorName" placeholder="请输入部件小类名称" />
      </el-form-item>
      <el-form-item label="部件总数" prop="totalCompCount">
        <el-input v-model="formData.totalCompCount" placeholder="请输入部件总数" />
      </el-form-item>
      <el-form-item label="完好部件数" prop="normalCompCount">
        <el-input v-model="formData.normalCompCount" placeholder="请输入完好部件数" />
      </el-form-item>
      <el-form-item label="破损部件数" prop="damagedCompCount">
        <el-input v-model="formData.damagedCompCount" placeholder="请输入破损部件数" />
      </el-form-item>
      <el-form-item label="丢失部件数" prop="lostCompCount">
        <el-input v-model="formData.lostCompCount" placeholder="请输入丢失部件数" />
      </el-form-item>
      <el-form-item label="废弃部件数" prop="discardedCompCount">
        <el-input v-model="formData.discardedCompCount" placeholder="请输入废弃部件数" />
      </el-form-item>
      <el-form-item label="新增部件数" prop="newCompCount">
        <el-input v-model="formData.newCompCount" placeholder="请输入新增部件数" />
      </el-form-item>
      <el-form-item label="更新部件数" prop="updateCompCount">
        <el-input v-model="formData.updateCompCount" placeholder="请输入更新部件数" />
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
import { MngCompApi, MngCompVO } from '@/api/dataHub/managedComponent/mngcomp'

/** 管理部件统计 表单 */
defineOptions({ name: 'MngCompForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  statMngCompId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  areaCode: undefined,
  areaName: undefined,
  compMajorId: undefined,
  compMajorName: undefined,
  compMinorId: undefined,
  compMinorName: undefined,
  totalCompCount: undefined,
  normalCompCount: undefined,
  damagedCompCount: undefined,
  lostCompCount: undefined,
  discardedCompCount: undefined,
  newCompCount: undefined,
  updateCompCount: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  statMngCompId: [{ required: true, message: '统计ID不能为空', trigger: 'blur' }],
  statCycle: [{ required: true, message: '统计周期不能为空', trigger: 'blur' }],
  statCycleName: [{ required: true, message: '统计周期名称不能为空', trigger: 'blur' }],
  areaCode: [{ required: true, message: '行政区划代码不能为空', trigger: 'blur' }],
  areaName: [{ required: true, message: '行政区划名称不能为空', trigger: 'blur' }],
  compMajorId: [{ required: true, message: '部件大类ID不能为空', trigger: 'blur' }],
  compMajorName: [{ required: true, message: '部件大类名称不能为空', trigger: 'blur' }],
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
      formData.value = await MngCompApi.getMngComp(id)
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
    const data = formData.value as unknown as MngCompVO
    if (formType.value === 'create') {
      await MngCompApi.createMngComp(data)
      message.success(t('common.createSuccess'))
    } else {
      await MngCompApi.updateMngComp(data)
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
    statMngCompId: undefined,
    statCycle: undefined,
    statCycleName: undefined,
    areaCode: undefined,
    areaName: undefined,
    compMajorId: undefined,
    compMajorName: undefined,
    compMinorId: undefined,
    compMinorName: undefined,
    totalCompCount: undefined,
    normalCompCount: undefined,
    damagedCompCount: undefined,
    lostCompCount: undefined,
    discardedCompCount: undefined,
    newCompCount: undefined,
    updateCompCount: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined,
  }
  formRef.value?.resetFields()
}
</script>
