<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" :width="700">
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
          <el-form-item label="统计ID" prop="mgStatId">
            <el-input v-model="formData.mgStatId" placeholder="请输入统计ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="统计周期" prop="statCycle" class="required">
            <el-input v-model="formData.statCycle" placeholder="请输入统计周期" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="统计周期名称" prop="statCycleName" class="required">
            <el-input v-model="formData.statCycleName" placeholder="请输入统计周期名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="行政区划代码" prop="regionCode" class="required">
            <el-input v-model="formData.regionCode" placeholder="请输入行政区划代码" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="行政区划名称" prop="regionName" class="required">
            <el-input v-model="formData.regionName" placeholder="请输入行政区划名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="网格员ID" prop="gridUserId">
            <el-input v-model="formData.gridUserId" placeholder="请输入网格员ID" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="网格员姓名" prop="gridUserName">
            <el-input v-model="formData.gridUserName" placeholder="请输入网格员姓名" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所含单元数范围" prop="unitCountRange">
            <el-input v-model="formData.unitCountRange" placeholder="请输入所含单元数范围" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="网格总数(个)" prop="totalCount" class="required">
            <el-input v-model.number="formData.totalCount" placeholder="请输入网格总数(个)" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="总面积(m²)" prop="totalArea" class="required">
            <el-input v-model.number="formData.totalArea" placeholder="请输入总面积(m²)" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="平均所含单元数(个)" prop="avgUnitCount" class="required">
            <el-input v-model.number="formData.avgUnitCount" placeholder="请输入平均所含单元数(个)" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="总所含单元网格数(个)" prop="totalUnitCount" class="required">
            <el-input v-model.number="formData.totalUnitCount" placeholder="请输入总所含单元网格数(个)" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="网格员覆盖数(个)" prop="gridUserCoverCount">
            <el-input v-model.number="formData.gridUserCoverCount" placeholder="请输入网格员覆盖数(个)" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="新增网格数(个)" prop="newCount">
            <el-input v-model.number="formData.newCount" placeholder="请输入新增网格数(个)" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="统计人" prop="statUserId" class="required">
            <el-input v-model="formData.statUserId" placeholder="请输入统计人" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="统计时间" prop="statTime" class="required">
            <el-date-picker
              v-model="formData.statTime"
              type="date"
              value-format="x"
              placeholder="选择统计时间"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="24">
          <el-form-item label="报表备注" prop="rptRemark">
            <el-input
              v-model="formData.rptRemark"
              placeholder="请输入报表备注"
              type="textarea"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
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
import { MngGridRptApi, MngGridRptVO } from '@/api/dataHub/gridManagement/gridStatReport/mnggridrpt'

/** 管理网格统计 表单 */
defineOptions({ name: 'MngGridRptForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  mgStatId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  regionCode: undefined,
  regionName: undefined,
  gridUserId: undefined,
  gridUserName: undefined,
  unitCountRange: undefined,
  totalCount: undefined,
  totalArea: undefined,
  avgUnitCount: undefined,
  totalUnitCount: undefined,
  gridUserCoverCount: undefined,
  newCount: undefined,
  statUserId: undefined,
  statTime: undefined,
  rptRemark: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
})
const formRules = reactive({
  statCycle: [{ required: true, message: '统计周期不能为空', trigger: 'blur' }],
  statCycleName: [{ required: true, message: '统计周期名称不能为空', trigger: 'blur' }],
  regionCode: [{ required: true, message: '行政区划代码不能为空', trigger: 'blur' }],
  regionName: [{ required: true, message: '行政区划名称不能为空', trigger: 'blur' }],
  totalCount: [{ required: true, message: '网格总数(个)不能为空', trigger: 'blur' }],
  totalArea: [{ required: true, message: '总面积(m²)不能为空', trigger: 'blur' }],
  avgUnitCount: [{ required: true, message: '平均所含单元数(个)不能为空', trigger: 'blur' }],
  totalUnitCount: [{ required: true, message: '总所含单元网格数(个)不能为空', trigger: 'blur' }],
  statUserId: [{ required: true, message: '统计人不能为空', trigger: 'blur' }],
  statTime: [{ required: true, message: '统计时间不能为空', trigger: 'blur' }],
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
      formData.value = await MngGridRptApi.getMngGridRpt(id)
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
    const data = formData.value as unknown as MngGridRptVO
    if (formType.value === 'create') {
      await MngGridRptApi.createMngGridRpt(data)
      message.success(t('common.createSuccess'))
    } else {
      await MngGridRptApi.updateMngGridRpt(data)
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
    mgStatId: undefined,
    statCycle: undefined,
    statCycleName: undefined,
    regionCode: undefined,
    regionName: undefined,
    gridUserId: undefined,
    gridUserName: undefined,
    unitCountRange: undefined,
    totalCount: undefined,
    totalArea: undefined,
    avgUnitCount: undefined,
    totalUnitCount: undefined,
    gridUserCoverCount: undefined,
    newCount: undefined,
    statUserId: undefined,
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

<style scoped>
.form-container {
  padding: 10px 0;
}

.required .el-form-item__label::after {
  content: '*';
  color: #f5222d;
  margin-left: 4px;
}

.el-row {
  margin-bottom: 15px;
}

.el-col {
  margin-bottom: 10px;
}

::v-deep .el-form-item {
  margin-bottom: 10px;
}

::v-deep .el-textarea__inner {
  resize: vertical;
}
</style>
