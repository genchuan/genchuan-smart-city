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
          <el-form-item label="整改任务ID" prop="rectifyTaskId">
            <el-input v-model="formData.rectifyTaskId" placeholder="请输入整改任务ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="整改任务编码" prop="rectifyTaskCode">
            <el-input v-model="formData.rectifyTaskCode" placeholder="请输入整改任务编码" />
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
          <el-form-item label="关联问题编码" prop="problemCode">
            <el-input v-model="formData.problemCode" placeholder="请输入关联问题编码" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="关联问题名称" prop="problemName">
        <el-input v-model="formData.problemName" placeholder="请输入关联问题名称" />
      </el-form-item>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="责任部门代码" prop="deptCode">
            <el-input v-model="formData.deptCode" placeholder="请输入责任部门代码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="责任部门名称" prop="deptName">
            <el-input v-model="formData.deptName" placeholder="请输入责任部门名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="整改负责人ID" prop="leaderUserId">
            <el-input v-model="formData.leaderUserId" placeholder="请输入整改负责人ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="整改负责人姓名" prop="leaderUserName">
            <el-input v-model="formData.leaderUserName" placeholder="请输入整改负责人姓名" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="整改时限（小时）" prop="rectifyDeadlineHour">
            <el-input
              v-model.number="formData.rectifyDeadlineHour"
              placeholder="请输入整改时限（小时）"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="整改截止时间" prop="rectifyDeadlineTime">
            <el-date-picker
              v-model="formData.rectifyDeadlineTime"
              type="datetime"
              value-format="x"
              placeholder="选择整改截止时间"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="派发说明" prop="dispatchDesc">
        <el-input
          v-model="formData.dispatchDesc"
          placeholder="请输入派发说明"
          type="textarea"
          rows="3"
        />
      </el-form-item>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="整改任务状态" prop="rectifyStatus">
            <el-radio-group v-model="formData.rectifyStatus">
              <el-radio value="已完成">已完成</el-radio>
              <el-radio value="进行中">进行中</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="派发人" prop="dispatchUser">
            <el-input v-model="formData.dispatchUser" placeholder="请输入派发人" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="派发时间" prop="dispatchTime">
            <el-date-picker
              v-model="formData.dispatchTime"
              type="datetime"
              value-format="x"
              placeholder="选择派发时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="创建时间" prop="bizCreateTime">
            <el-date-picker
              v-model="formData.bizCreateTime"
              type="datetime"
              value-format="x"
              placeholder="选择创建时间"
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
  InspectRectifyTaskApi,
  InspectRectifyTaskVO
} from '@/api/dataHub/supervisionInspection/issueCorrectionDisposal/correctionTaskAssignment'

/** 巡查巡检整改任务派发 表单 */
defineOptions({ name: 'InspectRectifyTaskForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const activeNames = ref(['1']) // 折叠面板默认状态
const formData = ref({
  id: undefined,
  rectifyTaskId: undefined,
  rectifyTaskCode: undefined,
  problemId: undefined,
  problemCode: undefined,
  problemName: undefined,
  deptCode: undefined,
  deptName: undefined,
  leaderUserId: undefined,
  leaderUserName: undefined,
  rectifyDeadlineHour: undefined,
  rectifyDeadlineTime: undefined,
  dispatchDesc: undefined,
  rectifyStatus: undefined,
  dispatchUser: undefined,
  dispatchTime: undefined,
  bizCreateTime: undefined,
  bizUpdateTime: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  rectifyTaskId: [{ required: true, message: '整改任务ID不能为空', trigger: 'blur' }],
  rectifyTaskCode: [{ required: true, message: '整改任务编码不能为空', trigger: 'blur' }],
  problemId: [{ required: true, message: '关联问题ID不能为空', trigger: 'blur' }],
  problemCode: [{ required: true, message: '关联问题编码不能为空', trigger: 'blur' }],
  problemName: [{ required: true, message: '关联问题名称不能为空', trigger: 'blur' }],
  deptCode: [{ required: true, message: '责任部门代码不能为空', trigger: 'blur' }],
  deptName: [{ required: true, message: '责任部门名称不能为空', trigger: 'blur' }],
  leaderUserId: [{ required: true, message: '整改负责人ID不能为空', trigger: 'blur' }],
  leaderUserName: [{ required: true, message: '整改负责人姓名不能为空', trigger: 'blur' }],
  rectifyDeadlineHour: [{ required: true, message: '整改时限（小时）不能为空', trigger: 'blur' }],
  rectifyDeadlineTime: [{ required: true, message: '整改截止时间不能为空', trigger: 'blur' }],
  rectifyStatus: [{ required: true, message: '整改任务状态不能为空', trigger: 'blur' }],
  dispatchUser: [{ required: true, message: '派发人不能为空', trigger: 'blur' }],
  dispatchTime: [{ required: true, message: '派发时间不能为空', trigger: 'blur' }],
  bizCreateTime: [{ required: true, message: '创建时间不能为空', trigger: 'blur' }]
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
      formData.value = await InspectRectifyTaskApi.getInspectRectifyTask(id)
      // 转换时间格式为时间戳
      if (formData.value.rectifyDeadlineTime) {
        formData.value.rectifyDeadlineTime = new Date(formData.value.rectifyDeadlineTime).getTime()
      }
      if (formData.value.dispatchTime) {
        formData.value.dispatchTime = new Date(formData.value.dispatchTime).getTime()
      }
      if (formData.value.bizCreateTime) {
        formData.value.bizCreateTime = new Date(formData.value.bizCreateTime).getTime()
      }
      if (formData.value.bizUpdateTime) {
        formData.value.bizUpdateTime = new Date(formData.value.bizUpdateTime).getTime()
      }
    } finally {
      formLoading.value = false
    }
  } else {
    // 新增时默认当前时间
    const now = Date.now()
    formData.value.dispatchTime = now
    formData.value.bizCreateTime = now
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
    const data = { ...formData.value } as unknown as InspectRectifyTaskVO
    // 转换时间格式为Date
    if (data.rectifyDeadlineTime) {
      data.rectifyDeadlineTime = new Date(Number(data.rectifyDeadlineTime))
    }
    if (data.dispatchTime) {
      data.dispatchTime = new Date(Number(data.dispatchTime))
    }
    if (data.bizCreateTime) {
      data.bizCreateTime = new Date(Number(data.bizCreateTime))
    }
    if (data.bizUpdateTime) {
      data.bizUpdateTime = new Date(Number(data.bizUpdateTime))
    }

    if (formType.value === 'create') {
      await InspectRectifyTaskApi.createInspectRectifyTask(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectRectifyTaskApi.updateInspectRectifyTask(data)
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
    rectifyTaskId: undefined,
    rectifyTaskCode: undefined,
    problemId: undefined,
    problemCode: undefined,
    problemName: undefined,
    deptCode: undefined,
    deptName: undefined,
    leaderUserId: undefined,
    leaderUserName: undefined,
    rectifyDeadlineHour: undefined,
    rectifyDeadlineTime: undefined,
    dispatchDesc: undefined,
    rectifyStatus: undefined,
    dispatchUser: undefined,
    dispatchTime: undefined,
    bizCreateTime: undefined,
    bizUpdateTime: undefined,
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

::v-deep .el-collapse {
  border: 1px solid #e5e7eb;
  border-radius: 4px;
}

::v-deep .el-collapse-item__header {
  background-color: #f9fafb;
}
</style>
