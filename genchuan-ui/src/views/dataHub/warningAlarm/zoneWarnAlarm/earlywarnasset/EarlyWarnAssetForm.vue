<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="700px">
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
          <el-form-item label="统计ID" prop="warnAssetStatId">
            <el-input v-model="formData.warnAssetStatId" placeholder="请输入统计ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="统计周期" prop="statCycle">
            <el-input v-model="formData.statCycle" placeholder="请输入统计周期" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="资产大类ID" prop="assetMajorId">
            <el-input v-model="formData.assetMajorId" placeholder="请输入资产大类ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="资产大类名称" prop="assetMajorName">
            <el-input v-model="formData.assetMajorName" placeholder="请输入资产大类名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="资产小类ID" prop="assetMinorId">
            <el-input v-model="formData.assetMinorId" placeholder="请输入资产小类ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="资产小类名称" prop="assetMinorName">
            <el-input v-model="formData.assetMinorName" placeholder="请输入资产小类名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="预警总数" prop="totalWarnCount">
            <el-input v-model.number="formData.totalWarnCount" placeholder="请输入预警总数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="主要故障类型" prop="mainFaultType">
            <el-select v-model="formData.mainFaultType" placeholder="请选择主要故障类型">
              <el-option label="请选择字典生成" value="" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="涉及资产数" prop="assetCount">
            <el-input v-model.number="formData.assetCount" placeholder="请输入涉及资产数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="维修总成本" prop="mntCost">
            <el-input v-model.number="formData.mntCost" placeholder="请输入维修总成本" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="已修复资产数" prop="repairedAssetCount">
            <el-input
              v-model.number="formData.repairedAssetCount"
              placeholder="请输入已修复资产数"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="修复率" prop="repairRate">
            <el-input v-model.number="formData.repairRate" placeholder="请输入修复率" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="统计人" prop="statUserId">
            <el-input v-model="formData.statUserId" placeholder="请输入统计人" />
          </el-form-item>
        </el-col>
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

      <el-collapse v-model="activeNames" class="mt-2">
        <el-collapse-item title="扩展字段" name="1">
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
  EarlyWarnAssetApi,
  EarlyWarnAssetVO
} from '@/api/dataHub/warningAlarm/zoneWarnAlarm/earlywarnasset'

/** 按资产分域预警告警统计 表单 */
defineOptions({ name: 'EarlyWarnAssetForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const activeNames = ref(['1']) // 折叠面板默认状态
const formData = ref({
  id: undefined,
  warnAssetStatId: undefined,
  statCycle: undefined,
  assetMajorId: undefined,
  assetMajorName: undefined,
  assetMinorId: undefined,
  assetMinorName: undefined,
  totalWarnCount: undefined,
  mainFaultType: undefined,
  assetCount: undefined,
  mntCost: undefined,
  repairedAssetCount: undefined,
  repairRate: undefined,
  statUserId: undefined,
  statTime: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  warnAssetStatId: [{ required: true, message: '统计ID不能为空', trigger: 'blur' }],
  statCycle: [{ required: true, message: '统计周期不能为空', trigger: 'blur' }],
  assetMajorId: [{ required: true, message: '资产大类ID不能为空', trigger: 'blur' }],
  assetMajorName: [{ required: true, message: '资产大类名称不能为空', trigger: 'blur' }],
  assetMinorId: [{ required: true, message: '资产小类ID不能为空', trigger: 'blur' }],
  assetMinorName: [{ required: true, message: '资产小类名称不能为空', trigger: 'blur' }],
  totalWarnCount: [{ required: true, message: '预警总数不能为空', trigger: 'blur' }],
  repairedAssetCount: [{ required: true, message: '已修复资产数不能为空', trigger: 'blur' }],
  repairRate: [{ required: true, message: '修复率不能为空', trigger: 'blur' }],
  statUserId: [{ required: true, message: '统计人不能为空', trigger: 'blur' }],
  statTime: [{ required: true, message: '统计时间不能为空', trigger: 'blur' }]
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
      formData.value = await EarlyWarnAssetApi.getEarlyWarnAsset(id)
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
    const data = formData.value as unknown as EarlyWarnAssetVO
    if (formType.value === 'create') {
      await EarlyWarnAssetApi.createEarlyWarnAsset(data)
      message.success(t('common.createSuccess'))
    } else {
      await EarlyWarnAssetApi.updateEarlyWarnAsset(data)
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
    warnAssetStatId: undefined,
    statCycle: undefined,
    assetMajorId: undefined,
    assetMajorName: undefined,
    assetMinorId: undefined,
    assetMinorName: undefined,
    totalWarnCount: undefined,
    mainFaultType: undefined,
    assetCount: undefined,
    mntCost: undefined,
    repairedAssetCount: undefined,
    repairRate: undefined,
    statUserId: undefined,
    statTime: undefined,
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

::v-deep .el-form-item__label {
  color: #4e5969;
  font-weight: 500;
}

::v-deep .el-collapse-item__header {
  background-color: #f5f7fa;
  border-radius: 4px;
  margin-bottom: 10px;
}

::v-deep .el-collapse-item__content {
  padding-bottom: 5px !important;
}
</style>
