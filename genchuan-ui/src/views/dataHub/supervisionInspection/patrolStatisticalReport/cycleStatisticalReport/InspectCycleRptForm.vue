<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="600px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="140px"
      v-loading="formLoading"
      class="form-container"
    >
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="统计ID" prop="cycleStatId">
            <el-input v-model="formData.cycleStatId" placeholder="请输入统计ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="统计维度" prop="statDimension" required>
            <el-input v-model="formData.statDimension" placeholder="请输入统计维度" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="所属区域代码" prop="areaCode" required>
            <el-input v-model="formData.areaCode" placeholder="请输入所属区域代码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属区域名称" prop="areaName" required>
            <el-input v-model="formData.areaName" placeholder="请输入所属区域名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="周期列表" prop="cycleList" required>
            <el-input v-model="formData.cycleList" placeholder="请输入周期列表（逗号分隔）" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="巡查次数列表" prop="inspectCountList" required>
            <el-input
              v-model="formData.inspectCountList"
              placeholder="请输入巡查次数列表（逗号分隔）"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="问题数列表" prop="problemCountList" required>
            <el-input
              v-model="formData.problemCountList"
              placeholder="请输入问题数列表（逗号分隔）"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="整改率列表" prop="rectifyRateList" required>
            <el-input
              v-model="formData.rectifyRateList"
              placeholder="请输入整改率列表（逗号分隔）"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="平均问题发现率列表" prop="avgFoundRateList">
            <el-input
              v-model="formData.avgFoundRateList"
              placeholder="请输入平均问题发现率列表（逗号分隔）"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="统计人" prop="statUser" required>
            <el-input v-model="formData.statUser" placeholder="请输入统计人" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="统计时间(业务)" prop="statTimeBiz" required>
            <el-date-picker
              v-model="formData.statTimeBiz"
              type="date"
              value-format="x"
              placeholder="选择统计时间"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20" class="mt-4">
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
      </el-row>

      <el-row :gutter="20">
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
  InspectCycleRptApi,
  InspectCycleRptVO
} from '@/api/dataHub/supervisionInspection/patrolStatisticalReport/cycleStatisticalReport'

/** 巡查巡检周期统计 表单 */
defineOptions({ name: 'InspectCycleRptForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<InspectCycleRptVO>>({
  id: undefined,
  cycleStatId: undefined,
  statDimension: undefined,
  areaCode: undefined,
  areaName: undefined,
  cycleList: undefined,
  inspectCountList: undefined,
  problemCountList: undefined,
  rectifyRateList: undefined,
  avgFoundRateList: undefined,
  statUser: undefined,
  statTimeBiz: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  statDimension: [{ required: true, message: '统计维度不能为空', trigger: 'blur' }],
  areaCode: [{ required: true, message: '所属区域代码不能为空', trigger: 'blur' }],
  areaName: [{ required: true, message: '所属区域名称不能为空', trigger: 'blur' }],
  cycleList: [{ required: true, message: '周期列表不能为空', trigger: 'blur' }],
  inspectCountList: [{ required: true, message: '巡查次数列表不能为空', trigger: 'blur' }],
  problemCountList: [{ required: true, message: '问题数列表不能为空', trigger: 'blur' }],
  rectifyRateList: [{ required: true, message: '整改率列表不能为空', trigger: 'blur' }],
  statUser: [{ required: true, message: '统计人不能为空', trigger: 'blur' }],
  statTimeBiz: [{ required: true, message: '统计时间不能为空', trigger: 'change' }]
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
      const data = await InspectCycleRptApi.getInspectCycleRpt(id)
      formData.value = {
        ...data,
        // 转换时间为时间戳
        statTimeBiz: data.statTimeBiz ? new Date(data.statTimeBiz).getTime() : undefined
      }
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
    const data = {
      ...formData.value,
      // 转换时间戳为Date对象
      statTimeBiz: formData.value.statTimeBiz ? new Date(formData.value.statTimeBiz) : undefined
    } as InspectCycleRptVO

    if (formType.value === 'create') {
      await InspectCycleRptApi.createInspectCycleRpt(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectCycleRptApi.updateInspectCycleRpt(data)
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
    cycleStatId: undefined,
    statDimension: undefined,
    areaCode: undefined,
    areaName: undefined,
    cycleList: undefined,
    inspectCountList: undefined,
    problemCountList: undefined,
    rectifyRateList: undefined,
    avgFoundRateList: undefined,
    statUser: undefined,
    statTimeBiz: undefined,
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

::v-deep .el-form-item {
  margin-bottom: 15px;
}

::v-deep .el-form-item__label {
  font-weight: 500;
  color: #4e5969;
}

::v-deep .el-row {
  margin-bottom: 5px;
}
</style>
