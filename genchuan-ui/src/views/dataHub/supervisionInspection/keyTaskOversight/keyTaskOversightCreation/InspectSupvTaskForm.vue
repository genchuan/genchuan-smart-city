<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="700px">
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
          <el-form-item label="关联整改任务ID" prop="rectifyTaskId">
            <el-input v-model="formData.rectifyTaskId" placeholder="请输入关联整改任务ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联整改任务编码" prop="rectifyTaskCode">
            <el-input v-model="formData.rectifyTaskCode" placeholder="请输入关联整改任务编码" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="关联问题ID" prop="problemId">
            <el-input v-model="formData.problemId" placeholder="请输入关联问题ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="关联问题名称" prop="problemName">
            <el-input v-model="formData.problemName" placeholder="请输入关联问题名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="督办人ID" prop="supvUserId">
            <el-input v-model="formData.supvUserId" placeholder="请输入督办人ID" />
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
          <el-form-item label="督办频次" prop="supvFrequency">
            <el-input
              v-model="formData.supvFrequency"
              placeholder="请输入督办频次（例：每日/每周/每月）"
            />
          </el-form-item>
        </el-col>
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
      </el-row>

      <el-form-item label="督办要求" prop="supvRequirement">
        <el-input
          v-model="formData.supvRequirement"
          placeholder="请输入督办要求"
          type="textarea"
          rows="3"
        />
      </el-form-item>

      <el-form-item label="督办任务状态" prop="supvStatus">
        <el-select v-model="formData.supvStatus" placeholder="请选择督办任务状态">
          <!--          <el-option label="未开始" value="1" />-->
          <!--          <el-option label="进行中" value="2" />-->
          <!--          <el-option label="已完成" value="3" />-->
          <!--          <el-option label="已逾期" value="4" />-->
          <el-option label="督办中" value="督办中" />
          <el-option label="已完成" value="已完成" />
        </el-select>
      </el-form-item>

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
  InspectSupvTaskApi,
  InspectSupvTaskVO
} from '@/api/dataHub/supervisionInspection/keyTaskOversight/keyTaskOversightCreation'

/** 巡查巡检重点督办任务 表单 */
defineOptions({ name: 'InspectSupvTaskForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const activeNames = ref(['1']) // 折叠面板默认状态
const formData = ref<Partial<InspectSupvTaskVO>>({
  id: undefined,
  supvTaskId: undefined,
  supvTaskCode: undefined,
  rectifyTaskId: undefined,
  rectifyTaskCode: undefined,
  problemId: undefined,
  problemName: undefined,
  supvUserId: undefined,
  supvUserName: undefined,
  supvFrequency: undefined,
  supvDeadlineTime: undefined,
  supvRequirement: undefined,
  supvStatus: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  supvTaskId: [{ required: true, message: '督办任务ID不能为空', trigger: 'blur' }],
  supvTaskCode: [{ required: true, message: '督办任务编码不能为空', trigger: 'blur' }],
  rectifyTaskId: [{ required: true, message: '关联整改任务ID不能为空', trigger: 'blur' }],
  rectifyTaskCode: [{ required: true, message: '关联整改任务编码不能为空', trigger: 'blur' }],
  problemId: [{ required: true, message: '关联问题ID不能为空', trigger: 'blur' }],
  problemName: [{ required: true, message: '关联问题名称不能为空', trigger: 'blur' }],
  supvUserId: [{ required: true, message: '督办人ID不能为空', trigger: 'blur' }],
  supvUserName: [{ required: true, message: '督办人姓名不能为空', trigger: 'blur' }],
  supvFrequency: [{ required: true, message: '督办频次不能为空', trigger: 'blur' }],
  supvDeadlineTime: [{ required: true, message: '督办截止时间不能为空', trigger: 'change' }],
  supvRequirement: [{ required: true, message: '督办要求不能为空', trigger: 'blur' }],
  supvStatus: [{ required: true, message: '督办任务状态不能为空', trigger: 'change' }]
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
      const data = await InspectSupvTaskApi.getInspectSupvTask(id)
      formData.value = { ...data }
      // 转换时间格式为时间戳
      if (formData.value.supvDeadlineTime) {
        formData.value.supvDeadlineTime = new Date(formData.value.supvDeadlineTime).getTime()
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
    const data = { ...formData.value } as unknown as InspectSupvTaskVO
    // 转换时间格式为Date
    if (data.supvDeadlineTime) {
      data.supvDeadlineTime = new Date(Number(data.supvDeadlineTime))
    }

    if (formType.value === 'create') {
      await InspectSupvTaskApi.createInspectSupvTask(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectSupvTaskApi.updateInspectSupvTask(data)
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
    rectifyTaskId: undefined,
    rectifyTaskCode: undefined,
    problemId: undefined,
    problemName: undefined,
    supvUserId: undefined,
    supvUserName: undefined,
    supvFrequency: undefined,
    supvDeadlineTime: undefined,
    supvRequirement: undefined,
    supvStatus: undefined,
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

::v-deep .el-form-item {
  margin-bottom: 15px;
}

::v-deep .el-collapse {
  border: 1px solid #e5e7eb;
  border-radius: 4px;
}

::v-deep .el-collapse-item__header {
  background-color: #f9fafb;
}

::v-deep .el-textarea__wrapper {
  resize: none;
}
</style>
