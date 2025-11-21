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
          <el-form-item label="归档ID" prop="archId">
            <el-input v-model="formData.archId" placeholder="请输入归档ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="归档编号" prop="archNo">
            <el-input v-model="formData.archNo" placeholder="请输入归档编号" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="预警ID" prop="earlyWarnId">
            <el-input v-model="formData.earlyWarnId" placeholder="请输入预警ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预警名称" prop="earlyWarnName">
            <el-input v-model="formData.earlyWarnName" placeholder="请输入预警名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="预警类型" prop="warnType">
            <el-select v-model="formData.warnType" placeholder="请选择预警类型">
              <el-option label="请选择字典生成" value="" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预警等级" prop="warnLevel">
            <el-input v-model="formData.warnLevel" placeholder="请输入预警等级" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="所在区域" prop="regionName">
            <el-input v-model="formData.regionName" placeholder="请输入所在区域" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="处置人" prop="handleUserName">
            <el-input v-model="formData.handleUserName" placeholder="请输入处置人" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="综合评估得分" prop="comprehensiveScore">
            <el-input
              v-model="formData.comprehensiveScore"
              placeholder="请输入综合评估得分"
              type="number"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="解除状态" prop="releaseStatus">
            <el-radio-group v-model="formData.releaseStatus">
              <el-radio value="RELEASED">已解除</el-radio>
              <el-radio value="PENDING">未解除</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="归档时间" prop="archTime">
            <el-date-picker
              v-model="formData.archTime"
              type="date"
              value-format="x"
              placeholder="选择归档时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="归档人ID" prop="archUserId">
            <el-input v-model="formData.archUserId" placeholder="请输入归档人ID" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="归档人姓名" prop="archUserName">
            <el-input v-model="formData.archUserName" placeholder="请输入归档人姓名" />
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
  EarlyWarnArchApi,
  EarlyWarnArchVO
} from '@/api/dataHub/warningAlarm/warnAlarmRespDisposal/earlywarnarch'

/** 预警告警处置归档 表单 */
defineOptions({ name: 'EarlyWarnArchForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const activeNames = ref(['1']) // 折叠面板默认状态
const formData = ref({
  id: undefined,
  archId: undefined,
  archNo: undefined,
  earlyWarnId: undefined,
  earlyWarnName: undefined,
  warnType: undefined,
  warnLevel: undefined,
  regionName: undefined,
  handleUserName: undefined,
  comprehensiveScore: undefined,
  releaseStatus: undefined,
  archTime: undefined,
  archUserId: undefined,
  archUserName: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  archId: [{ required: true, message: '归档ID不能为空', trigger: 'blur' }],
  archNo: [{ required: true, message: '归档编号不能为空', trigger: 'blur' }],
  earlyWarnId: [{ required: true, message: '预警ID不能为空', trigger: 'blur' }],
  earlyWarnName: [{ required: true, message: '预警名称不能为空', trigger: 'blur' }],
  warnType: [{ required: true, message: '预警类型不能为空', trigger: 'change' }],
  warnLevel: [{ required: true, message: '预警等级不能为空', trigger: 'blur' }],
  regionName: [{ required: true, message: '所在区域不能为空', trigger: 'blur' }],
  handleUserName: [{ required: true, message: '处置人不能为空', trigger: 'blur' }],
  comprehensiveScore: [{ required: true, message: '综合评估得分不能为空', trigger: 'blur' }],
  releaseStatus: [{ required: true, message: '解除状态不能为空', trigger: 'change' }],
  archTime: [{ required: true, message: '归档时间不能为空', trigger: 'change' }],
  archUserId: [{ required: true, message: '归档人ID不能为空', trigger: 'blur' }],
  archUserName: [{ required: true, message: '归档人姓名不能为空', trigger: 'blur' }]
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
      formData.value = await EarlyWarnArchApi.getEarlyWarnArch(id)
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
    const data = formData.value as unknown as EarlyWarnArchVO
    if (formType.value === 'create') {
      await EarlyWarnArchApi.createEarlyWarnArch(data)
      message.success(t('common.createSuccess'))
    } else {
      await EarlyWarnArchApi.updateEarlyWarnArch(data)
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
    archId: undefined,
    archNo: undefined,
    earlyWarnId: undefined,
    earlyWarnName: undefined,
    warnType: undefined,
    warnLevel: undefined,
    regionName: undefined,
    handleUserName: undefined,
    comprehensiveScore: undefined,
    releaseStatus: undefined,
    archTime: undefined,
    archUserId: undefined,
    archUserName: undefined,
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
  background-color: #f9fafb;
  border-radius: 8px;
}

::v-deep .el-collapse-item__content {
  padding-top: 15px !important;
}
</style>
