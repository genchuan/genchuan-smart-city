<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="700px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="130px"
      v-loading="formLoading"
      class="form-container"
    >
      <!-- 栅格布局：分两列优化布局 -->
      <el-row :gutter="24">
        <el-col :span="12">
          <el-form-item label="配置ID" prop="customStatCfgId">
            <el-input v-model="formData.customStatCfgId" placeholder="请输入配置ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="配置名称" prop="cfgName">
            <el-input v-model="formData.cfgName" placeholder="请输入配置名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="统计周期" prop="statCycle">
            <el-input v-model="formData.statCycle" placeholder="请输入统计周期" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="统计维度" prop="statDimensions">
            <el-input v-model="formData.statDimensions" placeholder="请输入统计维度" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="筛选条件" prop="filterConditions">
            <el-input v-model="formData.filterConditions" placeholder="请输入筛选条件" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分组维度" prop="groupDimensions">
            <el-input v-model="formData.groupDimensions" placeholder="请输入分组维度" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="统计指标" prop="statIndicators">
            <el-input v-model="formData.statIndicators" placeholder="请输入统计指标" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="创建人ID" prop="createUserId">
            <el-input v-model="formData.createUserId" placeholder="请输入创建人ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="创建人姓名" prop="createUserName">
            <el-input v-model="formData.createUserName" placeholder="请输入创建人姓名" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="是否常用" prop="isFrequentlyUsed">
            <el-select v-model="formData.isFrequentlyUsed" placeholder="请选择是否常用">
              <el-option label="是" value="1" />
              <el-option label="否" value="0" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 扩展字段：折叠面板隐藏 -->
      <el-collapse v-model="activeCollapse" class="mt-4">
        <el-collapse-item name="extFields" title="扩展字段（可选）">
          <el-row :gutter="24">
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
  EarlyWarnCustomCfgApi,
  EarlyWarnCustomCfgVO
} from '@/api/dataHub/warningAlarm/warnAlarmStatAnalysis/earlywarncustomcfg'

/** 预警告警自定义统计配置 表单 */
defineOptions({ name: 'EarlyWarnCustomCfgForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const activeCollapse = ref<string[]>([]) // 折叠面板状态
const formData = ref<Partial<EarlyWarnCustomCfgVO>>({
  id: undefined,
  customStatCfgId: undefined,
  cfgName: undefined,
  statCycle: undefined,
  statDimensions: undefined,
  filterConditions: undefined,
  groupDimensions: undefined,
  statIndicators: undefined,
  createUserId: undefined,
  createUserName: undefined,
  isFrequentlyUsed: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})

// 表单校验规则
const formRules = reactive({
  customStatCfgId: [{ required: true, message: '配置ID不能为空', trigger: 'blur' }],
  cfgName: [{ required: true, message: '配置名称不能为空', trigger: 'blur' }],
  statCycle: [{ required: true, message: '统计周期不能为空', trigger: 'blur' }],
  statDimensions: [{ required: true, message: '统计维度不能为空', trigger: 'blur' }],
  statIndicators: [{ required: true, message: '统计指标不能为空', trigger: 'blur' }],
  createUserId: [{ required: true, message: '创建人ID不能为空', trigger: 'blur' }],
  createUserName: [{ required: true, message: '创建人姓名不能为空', trigger: 'blur' }],
  isFrequentlyUsed: [{ required: true, message: '请选择是否常用', trigger: 'change' }]
})

const formRef = ref() // 表单 Ref

/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = t('action.' + type)
  formType.value = type
  resetForm()

  // 修改时加载数据
  if (id) {
    formLoading.value = true
    try {
      const res = await EarlyWarnCustomCfgApi.getEarlyWarnCustomCfg(id)
      formData.value = { ...res }
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
    const submitData = formData.value as EarlyWarnCustomCfgVO
    if (formType.value === 'create') {
      await EarlyWarnCustomCfgApi.createEarlyWarnCustomCfg(submitData)
      message.success(t('common.createSuccess'))
    } else {
      await EarlyWarnCustomCfgApi.updateEarlyWarnCustomCfg(submitData)
      message.success(t('common.updateSuccess'))
    }
    dialogVisible.value = false
    emit('success') // 通知父组件刷新列表
  } finally {
    formLoading.value = false
  }
}

/** 重置表单 */
const resetForm = () => {
  formData.value = {
    id: undefined,
    customStatCfgId: undefined,
    cfgName: undefined,
    statCycle: undefined,
    statDimensions: undefined,
    filterConditions: undefined,
    groupDimensions: undefined,
    statIndicators: undefined,
    createUserId: undefined,
    createUserName: undefined,
    isFrequentlyUsed: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined
  }
  formRef.value?.resetFields()
  activeCollapse.value = []
}
</script>

<style scoped>
.form-container {
  padding: 8px 0;
}

::v-deep .el-form-item {
  margin-bottom: 16px;
}

::v-deep .el-collapse {
  border: 1px solid #f0f2f5;
  border-radius: 6px;
  overflow: hidden;
}

::v-deep .el-collapse-item__header {
  background-color: #f9fafb;
}
</style>
