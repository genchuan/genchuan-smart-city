<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="650px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
      class="custom-form"
    >
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="统计ID" prop="warnRegionStatId">
            <el-input v-model="formData.warnRegionStatId" placeholder="请输入统计ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="统计周期" prop="statCycle" required>
            <el-input v-model="formData.statCycle" placeholder="请输入统计周期" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="行政区划代码" prop="regionCode" required>
            <el-input v-model="formData.regionCode" placeholder="请输入行政区划代码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="行政区划名称" prop="regionName" required>
            <el-input v-model="formData.regionName" placeholder="请输入行政区划名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="行政区划级别" prop="regionLevel" required>
            <el-input v-model="formData.regionLevel" placeholder="请输入行政区划级别" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预警总数" prop="totalWarnCount">
            <el-input v-model.number="formData.totalWarnCount" placeholder="请输入预警总数" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="一般预警数" prop="normalLevelCount">
            <el-input v-model.number="formData.normalLevelCount" placeholder="请输入一般预警数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="较重预警数" prop="heavyLevelCount">
            <el-input v-model.number="formData.heavyLevelCount" placeholder="请输入较重预警数" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="严重预警数" prop="seriousLevelCount">
            <el-input v-model.number="formData.seriousLevelCount" placeholder="请输入严重预警数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="特别严重预警数" prop="criticalLevelCount">
            <el-input
              v-model.number="formData.criticalLevelCount"
              placeholder="请输入特别严重预警数"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="已办结数" prop="completedCount">
            <el-input v-model.number="formData.completedCount" placeholder="请输入已办结数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="待处置数" prop="pendCount">
            <el-input v-model.number="formData.pendCount" placeholder="请输入待处置数" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="处置率(%)" prop="handleRate">
            <el-input v-model.number="formData.handleRate" placeholder="请输入处置率(%)" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="统计人" prop="statUserId">
            <el-input v-model="formData.statUserId" placeholder="请输入统计人" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="统计时间" prop="statTime">
            <el-date-picker
              v-model="formData.statTime"
              type="date"
              value-format="x"
              placeholder="选择统计时间"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-collapse v-model="activeNames" class="mt-4">
        <el-collapse-item name="1" title="扩展字段">
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
import {
  EarlyWarnRegionApi,
  EarlyWarnRegionVO
} from '@/api/dataHub/warningAlarm/zoneWarnAlarm/adminDivDomainWarnAlarm'

/** 按行政区划预警告警统计 表单 */
defineOptions({ name: 'EarlyWarnRegionForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<EarlyWarnRegionVO>>({
  id: undefined,
  warnRegionStatId: undefined,
  statCycle: undefined,
  regionCode: undefined,
  regionName: undefined,
  regionLevel: undefined,
  totalWarnCount: undefined,
  normalLevelCount: undefined,
  heavyLevelCount: undefined,
  seriousLevelCount: undefined,
  criticalLevelCount: undefined,
  completedCount: undefined,
  pendCount: undefined,
  handleRate: undefined,
  statUserId: undefined,
  statTime: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  statCycle: [{ required: true, message: '统计周期不能为空', trigger: 'blur' }],
  regionCode: [{ required: true, message: '行政区划代码不能为空', trigger: 'blur' }],
  regionName: [{ required: true, message: '行政区划名称不能为空', trigger: 'blur' }],
  regionLevel: [{ required: true, message: '行政区划级别不能为空', trigger: 'blur' }]
})
const formRef = ref() // 表单 Ref
const activeNames = ref<string[]>([]) // 折叠面板状态

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
      formData.value = await EarlyWarnRegionApi.getEarlyWarnRegion(id)
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
    const data = formData.value as unknown as EarlyWarnRegionVO
    if (formType.value === 'create') {
      await EarlyWarnRegionApi.createEarlyWarnRegion(data)
      message.success(t('common.createSuccess'))
    } else {
      await EarlyWarnRegionApi.updateEarlyWarnRegion(data)
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
    warnRegionStatId: undefined,
    statCycle: undefined,
    regionCode: undefined,
    regionName: undefined,
    regionLevel: undefined,
    totalWarnCount: undefined,
    normalLevelCount: undefined,
    heavyLevelCount: undefined,
    seriousLevelCount: undefined,
    criticalLevelCount: undefined,
    completedCount: undefined,
    pendCount: undefined,
    handleRate: undefined,
    statUserId: undefined,
    statTime: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined
  }
  formRef.value?.resetFields()
  activeNames.value = []
}
</script>

<style scoped>
.custom-form {
  padding: 10px 0;
}

.el-form-item {
  margin-bottom: 15px;
}

.el-collapse {
  border: 1px solid #e5e7eb;
  border-radius: 4px;
  overflow: hidden;
}

.el-collapse-item__header {
  background-color: #f9fafb;
}
</style>
