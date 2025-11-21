<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="600px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="110px"
      v-loading="formLoading"
      class="form-container"
    >
      <el-form-item label="分级ID" prop="problemLevelId" required>
        <el-input v-model="formData.problemLevelId" placeholder="请输入分级ID" />
      </el-form-item>
      <el-form-item label="关联问题ID" prop="problemId" required>
        <el-input v-model="formData.problemId" placeholder="请输入关联问题ID" />
      </el-form-item>
      <el-form-item label="关联问题编码" prop="problemCode">
        <el-input v-model="formData.problemCode" placeholder="请输入关联问题编码" />
      </el-form-item>
      <el-form-item label="关联问题名称" prop="problemName" required>
        <el-input v-model="formData.problemName" placeholder="请输入关联问题名称" />
      </el-form-item>
      <el-form-item label="问题级别" prop="problemLevel" required>
        <el-input v-model="formData.problemLevel" placeholder="请输入问题级别" />
      </el-form-item>
      <el-form-item label="级别描述" prop="levelDesc">
        <el-input
          v-model="formData.levelDesc"
          placeholder="请输入级别描述"
          type="textarea"
          rows="3"
        />
      </el-form-item>
      <el-form-item label="处置时限" prop="handleDeadline">
        <el-input v-model="formData.handleDeadline" placeholder="请输入处置时限" />
      </el-form-item>
      <el-form-item label="分级依据" prop="levelBasis">
        <el-input v-model="formData.levelBasis" placeholder="请输入分级依据" />
      </el-form-item>
      <el-form-item label="分级人" prop="levelUser">
        <el-input v-model="formData.levelUser" placeholder="请输入分级人" />
      </el-form-item>
      <el-form-item label="分级时间" prop="levelTime">
        <el-date-picker
          v-model="formData.levelTime"
          type="datetime"
          value-format="x"
          placeholder="选择分级时间"
        />
      </el-form-item>
      <el-form-item label="是否推送提醒" prop="isPushRemind">
        <el-radio-group v-model="formData.isPushRemind">
          <el-radio :label="true">是</el-radio>
          <el-radio :label="false">否</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="提醒推送时间" prop="pushRemindTime" v-if="formData.isPushRemind">
        <el-date-picker
          v-model="formData.pushRemindTime"
          type="datetime"
          value-format="x"
          placeholder="选择提醒推送时间"
        />
      </el-form-item>
      <el-form-item label="问题状态变更" prop="problemStatusChange">
        <el-input v-model="formData.problemStatusChange" placeholder="请输入问题状态变更" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  InspectProblemLevelRecApi,
  InspectProblemLevelRecVO
} from '@/api/dataHub/supervisionInspection/patrolIssueReporting/issueClassificationManagement'

/** 巡查巡检问题分级记录 表单 */
defineOptions({ name: 'InspectProblemLevelRecForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<InspectProblemLevelRecVO>>({
  id: undefined,
  problemLevelId: undefined,
  problemId: undefined,
  problemCode: undefined,
  problemName: undefined,
  problemLevel: undefined,
  levelDesc: undefined,
  handleDeadline: undefined,
  levelBasis: undefined,
  levelUser: undefined,
  levelTime: undefined,
  isPushRemind: false,
  pushRemindTime: undefined,
  problemStatusChange: undefined,
  bizCreateUser: undefined,
  bizCreateTime: undefined,
  bizUpdateUser: undefined,
  bizUpdateTime: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  problemLevelId: [{ required: true, message: '请输入分级ID', trigger: 'blur' }],
  problemId: [{ required: true, message: '请输入关联问题ID', trigger: 'blur' }],
  problemName: [{ required: true, message: '请输入关联问题名称', trigger: 'blur' }],
  problemLevel: [{ required: true, message: '请输入问题级别', trigger: 'blur' }]
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
      const data = await InspectProblemLevelRecApi.getInspectProblemLevelRec(id)
      formData.value = {
        ...data,
        // 转换时间格式为时间戳
        levelTime: data.levelTime ? new Date(data.levelTime).getTime() : undefined,
        pushRemindTime: data.pushRemindTime ? new Date(data.pushRemindTime).getTime() : undefined,
        bizCreateTime: data.bizCreateTime ? new Date(data.bizCreateTime).getTime() : undefined,
        bizUpdateTime: data.bizUpdateTime ? new Date(data.bizUpdateTime).getTime() : undefined
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
      levelTime: formData.value.levelTime ? new Date(formData.value.levelTime) : undefined,
      pushRemindTime: formData.value.pushRemindTime
        ? new Date(formData.value.pushRemindTime)
        : undefined
    } as InspectProblemLevelRecVO

    if (formType.value === 'create') {
      await InspectProblemLevelRecApi.createInspectProblemLevelRec(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectProblemLevelRecApi.updateInspectProblemLevelRec(data)
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
    problemLevelId: undefined,
    problemId: undefined,
    problemCode: undefined,
    problemName: undefined,
    problemLevel: undefined,
    levelDesc: undefined,
    handleDeadline: undefined,
    levelBasis: undefined,
    levelUser: undefined,
    levelTime: undefined,
    isPushRemind: false,
    pushRemindTime: undefined,
    problemStatusChange: undefined,
    bizCreateUser: undefined,
    bizCreateTime: undefined,
    bizUpdateUser: undefined,
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
  margin-bottom: 18px;
}

::v-deep .el-form-item__label {
  font-weight: 500;
  color: #4e5969;
}

::v-deep .el-input__wrapper {
  border-radius: 6px;
}
</style>
