<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" :width="800">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
      class="custom-form"
    >
      <!-- 分两列布局 -->
      <div class="form-row">
        <el-form-item label="统计ID" prop="unitStatId" class="form-item">
          <el-input v-model="formData.unitStatId" placeholder="请输入统计ID" />
        </el-form-item>
        <el-form-item label="统计周期" prop="statCycle" class="form-item required">
          <el-input v-model="formData.statCycle" placeholder="请输入统计周期" />
        </el-form-item>
      </div>

      <div class="form-row">
        <el-form-item label="统计周期名称" prop="statCycleName" class="form-item required">
          <el-input v-model="formData.statCycleName" placeholder="请输入统计周期名称" />
        </el-form-item>
        <el-form-item label="行政区划代码" prop="regionCode" class="form-item required">
          <el-input v-model="formData.regionCode" placeholder="请输入行政区划代码" />
        </el-form-item>
      </div>

      <div class="form-row">
        <el-form-item label="行政区划名称" prop="regionName" class="form-item required">
          <el-input v-model="formData.regionName" placeholder="请输入行政区划名称" />
        </el-form-item>
        <el-form-item label="行政区划级别" prop="regionLevel" class="form-item required">
          <el-input v-model="formData.regionLevel" placeholder="请输入行政区划级别" />
        </el-form-item>
      </div>

      <div class="form-row">
        <el-form-item label="比例尺" prop="scale" class="form-item">
          <el-input v-model="formData.scale" placeholder="请输入比例尺" />
        </el-form-item>
        <el-form-item label="网格总数(个)" prop="totalCount" class="form-item required">
          <el-input v-model="formData.totalCount" placeholder="请输入网格总数(个)" />
        </el-form-item>
      </div>

      <div class="form-row">
        <el-form-item label="在用网格数(个)" prop="inUseCount" class="form-item required">
          <el-input v-model="formData.inUseCount" placeholder="请输入在用网格数(个)" />
        </el-form-item>
        <el-form-item label="停用网格数(个)" prop="stoppedCount" class="form-item required">
          <el-input v-model="formData.stoppedCount" placeholder="请输入停用网格数(个)" />
        </el-form-item>
      </div>

      <div class="form-row">
        <el-form-item label="总面积(m²)" prop="totalArea" class="form-item required">
          <el-input v-model="formData.totalArea" placeholder="请输入总面积(m²)" />
        </el-form-item>
        <el-form-item label="平均面积(m²)" prop="avgArea" class="form-item required">
          <el-input v-model="formData.avgArea" placeholder="请输入平均面积(m²)" />
        </el-form-item>
      </div>

      <div class="form-row">
        <el-form-item label="新增网格数(个)" prop="newCount" class="form-item">
          <el-input v-model="formData.newCount" placeholder="请输入新增网格数(个)" />
        </el-form-item>
        <el-form-item label="更新网格数(个)" prop="updateCount" class="form-item">
          <el-input v-model="formData.updateCount" placeholder="请输入更新网格数(个)" />
        </el-form-item>
      </div>

      <div class="form-row">
        <el-form-item label="统计人" prop="statUserId" class="form-item required">
          <el-input v-model="formData.statUserId" placeholder="请输入统计人" />
        </el-form-item>
        <el-form-item label="统计时间" prop="statTime" class="form-item required">
          <el-date-picker
            v-model="formData.statTime"
            type="date"
            value-format="x"
            placeholder="选择统计时间"
          />
        </el-form-item>
      </div>

      <div class="form-row">
        <el-form-item label="报表备注" prop="rptRemark" class="form-item full-width">
          <el-input v-model="formData.rptRemark" placeholder="请输入报表备注" type="textarea"  />
        </el-form-item>
      </div>

      <!-- 折叠面板显示扩展字段 -->
      <el-collapse v-model="activeNames" class="expand-fields">
        <el-collapse-item title="扩展字段" name="1">
          <div class="form-row">
            <el-form-item label="分类扩展字段1" prop="extCat1" class="form-item">
              <el-input v-model="formData.extCat1" placeholder="请输入分类扩展字段1" />
            </el-form-item>
            <el-form-item label="分类扩展字段2" prop="extCat2" class="form-item">
              <el-input v-model="formData.extCat2" placeholder="请输入分类扩展字段2" />
            </el-form-item>
          </div>

          <div class="form-row">
            <el-form-item label="通用扩展字段1" prop="extCommon1" class="form-item">
              <el-input v-model="formData.extCommon1" placeholder="请输入通用扩展字段1" />
            </el-form-item>
            <el-form-item label="通用扩展字段2" prop="extCommon2" class="form-item">
              <el-input v-model="formData.extCommon2" placeholder="请输入通用扩展字段2" />
            </el-form-item>
          </div>
        </el-collapse-item>
      </el-collapse>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { UnitGridRptApi, UnitGridRptVO } from '@/api/dataHub/gridManagement/gridStatReport/unitgridrpt'

/** 单元网格统计 表单 */
defineOptions({ name: 'UnitGridRptForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  unitStatId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  regionCode: undefined,
  regionName: undefined,
  regionLevel: undefined,
  scale: undefined,
  totalCount: undefined,
  inUseCount: undefined,
  stoppedCount: undefined,
  totalArea: undefined,
  avgArea: undefined,
  newCount: undefined,
  updateCount: undefined,
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
  regionLevel: [{ required: true, message: '行政区划级别不能为空', trigger: 'blur' }],
  totalCount: [{ required: true, message: '网格总数(个)不能为空', trigger: 'blur' }],
  inUseCount: [{ required: true, message: '在用网格数(个)不能为空', trigger: 'blur' }],
  stoppedCount: [{ required: true, message: '停用网格数(个)不能为空', trigger: 'blur' }],
  totalArea: [{ required: true, message: '总面积(m²)不能为空', trigger: 'blur' }],
  avgArea: [{ required: true, message: '平均面积(m²)不能为空', trigger: 'blur' }],
  statUserId: [{ required: true, message: '统计人不能为空', trigger: 'blur' }],
  statTime: [{ required: true, message: '统计时间不能为空', trigger: 'blur' }],
})
const formRef = ref() // 表单 Ref
const activeNames = ref(['1']) // 折叠面板默认展开状态

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
      formData.value = await UnitGridRptApi.getUnitGridRpt(id)
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
    const data = formData.value as unknown as UnitGridRptVO
    if (formType.value === 'create') {
      await UnitGridRptApi.createUnitGridRpt(data)
      message.success(t('common.createSuccess'))
    } else {
      await UnitGridRptApi.updateUnitGridRpt(data)
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
    unitStatId: undefined,
    statCycle: undefined,
    statCycleName: undefined,
    regionCode: undefined,
    regionName: undefined,
    regionLevel: undefined,
    scale: undefined,
    totalCount: undefined,
    inUseCount: undefined,
    stoppedCount: undefined,
    totalArea: undefined,
    avgArea: undefined,
    newCount: undefined,
    updateCount: undefined,
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
.custom-form {
  padding: 15px;
}

.form-row {
  display: flex;
  flex-wrap: wrap;
  margin-bottom: 15px;
}

.form-item {
  flex: 1;
  min-width: 300px;
  margin-right: 15px;
}

.form-item:last-child {
  margin-right: 0;
}

.full-width {
  flex: 100% !important;
  margin-right: 0 !important;
}

.required .el-form-item__label::after {
  content: '*';
  color: #f56c6c;
  margin-left: 4px;
}

.expand-fields {
  margin-top: 10px;
  border-top: 1px dashed #e5e7eb;
  padding-top: 10px;
}

.el-collapse-item__content {
  padding-top: 15px !important;
}
</style>
