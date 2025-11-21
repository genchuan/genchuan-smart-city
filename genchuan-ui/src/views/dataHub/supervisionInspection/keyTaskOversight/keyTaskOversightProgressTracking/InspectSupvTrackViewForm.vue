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
          <el-form-item label="督办任务ID" prop="supvTaskId">
            <el-input v-model="formData.supvTaskId" placeholder="请输入督办任务ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="督办任务编码" prop="supvTaskCode">
            <el-input v-model="formData.supvTaskCode" placeholder="请输入督办任务编码" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="关联问题名称" prop="problemName">
            <el-input v-model="formData.problemName" placeholder="请输入关联问题名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="督办人姓名" prop="supvUserName">
            <el-input v-model="formData.supvUserName" placeholder="请输入督办人姓名" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="整改负责人姓名" prop="leaderUserName">
            <el-input v-model="formData.leaderUserName" placeholder="请输入整改负责人姓名" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="督办任务状态" prop="supvStatus">
            <el-select v-model="formData.supvStatus" placeholder="请选择督办任务状态">
              <el-option label="督办中" value="督办中" />
              <el-option label="已完成" value="已完成" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="督办截止时间" prop="supvDeadlineTime">
            <el-date-picker
              v-model="formData.supvDeadlineTime"
              type="datetime"
              value-format="x"
              placeholder="选择督办截止时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="是否超期" prop="isOverdue">
            <el-radio-group v-model="formData.isOverdue">
              <el-radio :value="true">是</el-radio>
              <el-radio :value="false">否</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="最新督办进展时间" prop="latestSupvTime">
            <el-date-picker
              v-model="formData.latestSupvTime"
              type="datetime"
              value-format="x"
              placeholder="选择最新督办进展时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="最新督办进展类型" prop="latestSupvType">
            <el-select v-model="formData.latestSupvType" placeholder="请选择最新督办进展类型">
              <el-option label="进度检查" value="进度检查" />
              <el-option label="现场督办" value="现场督办" />
              <el-option label="验收检查" value="验收检查" />
              <el-option label="完工检查" value="完工检查" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="最新督办进展内容" prop="latestSupvContent">
        <Editor v-model="formData.latestSupvContent" height="150px" />
      </el-form-item>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="督办操作次数" prop="supvOperCount">
            <el-input v-model.number="formData.supvOperCount" placeholder="请输入督办操作次数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联整改任务状态" prop="rectifyStatus">
            <el-select v-model="formData.rectifyStatus" placeholder="请选择关联整改任务状态">
              <!--              <el-option label="未开始" value="1" />-->
              <el-option label="进行中" value="进行中" />
              <el-option label="已完成" value="已完成" />
              <!--              <el-option label="已逾期" value="4" />-->
            </el-select>
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
  InspectSupvTrackViewApi,
  InspectSupvTrackViewVO
} from '@/api/dataHub/supervisionInspection/keyTaskOversight/keyTaskOversightProgressTracking'

/** 巡查巡检督办进度跟踪视图 表单 */
defineOptions({ name: 'InspectSupvTrackViewForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<InspectSupvTrackViewVO>>({
  id: undefined,
  supvTaskId: undefined,
  supvTaskCode: undefined,
  problemName: undefined,
  supvUserName: undefined,
  leaderUserName: undefined,
  supvStatus: undefined,
  supvDeadlineTime: undefined,
  isOverdue: false,
  latestSupvTime: undefined,
  latestSupvType: undefined,
  latestSupvContent: undefined,
  supvOperCount: 0,
  rectifyStatus: undefined
})
const formRules = reactive({
  supvTaskId: [{ required: true, message: '督办任务ID不能为空', trigger: 'blur' }],
  supvTaskCode: [{ required: true, message: '督办任务编码不能为空', trigger: 'blur' }],
  problemName: [{ required: true, message: '关联问题名称不能为空', trigger: 'blur' }],
  supvUserName: [{ required: true, message: '督办人姓名不能为空', trigger: 'blur' }],
  leaderUserName: [{ required: true, message: '整改负责人姓名不能为空', trigger: 'blur' }],
  supvStatus: [{ required: true, message: '督办任务状态不能为空', trigger: 'change' }],
  supvDeadlineTime: [{ required: true, message: '督办截止时间不能为空', trigger: 'change' }],
  isOverdue: [{ required: true, message: '是否超期不能为空', trigger: 'change' }],
  latestSupvTime: [{ required: true, message: '最新督办进展时间不能为空', trigger: 'change' }],
  latestSupvType: [{ required: true, message: '最新督办进展类型不能为空', trigger: 'change' }],
  supvOperCount: [{ required: true, message: '督办操作次数不能为空', trigger: 'blur' }],
  rectifyStatus: [{ required: true, message: '关联整改任务状态不能为空', trigger: 'change' }]
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
      const data = await InspectSupvTrackViewApi.getInspectSupvTrackView(id)
      formData.value = { ...data }
    } finally {
      formLoading.value = false
    }
  } else {
    // 新增时默认值
    formData.value.isOverdue = false
    formData.value.supvOperCount = 0
    formData.value.latestSupvTime = Date.now()
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
    const data = { ...formData.value } as unknown as InspectSupvTrackViewVO
    // 转换时间格式为Date
    // if (data.supvDeadlineTime) {
    //   data.supvDeadlineTime = new Date(Number(data.supvDeadlineTime))
    // }
    // if (data.latestSupvTime) {
    //   data.latestSupvTime = new Date(Number(data.latestSupvTime))
    // }

    if (formType.value === 'create') {
      await InspectSupvTrackViewApi.createInspectSupvTrackView(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectSupvTrackViewApi.updateInspectSupvTrackView(data)
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
    supvTaskId: undefined,
    supvTaskCode: undefined,
    problemName: undefined,
    supvUserName: undefined,
    leaderUserName: undefined,
    supvStatus: undefined,
    supvDeadlineTime: undefined,
    isOverdue: false,
    latestSupvTime: Date.now(),
    latestSupvType: undefined,
    latestSupvContent: undefined,
    supvOperCount: 0,
    rectifyStatus: undefined
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

::v-deep .el-form-item {
  margin-bottom: 15px;
}

::v-deep .el-input__wrapper,
::v-deep .el-select__wrapper,
::v-deep .el-date-editor .el-input__wrapper {
  border-radius: 4px;
}
</style>
