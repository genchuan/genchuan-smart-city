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
          <el-form-item label="验收ID" prop="acceptId">
            <el-input v-model="formData.acceptId" placeholder="请输入验收ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="验收编码" prop="acceptCode">
            <el-input v-model="formData.acceptCode" placeholder="请输入验收编码" />
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
          <el-form-item label="验收结果" prop="acceptResult">
            <el-select v-model="formData.acceptResult" placeholder="请选择验收结果">
              <el-option label="合格" value="合格" />
              <el-option label="待改进" value="待改进" />
              <el-option label="待定" value="待定" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="整改结果描述" prop="rectifyResultDesc">
        <el-input
          v-model="formData.rectifyResultDesc"
          placeholder="请输入整改结果描述"
          type="textarea"
          rows="3"
        />
      </el-form-item>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="整改前照片URLs" prop="rectifyBeforePhotoUrls">
            <el-input
              v-model="formData.rectifyBeforePhotoUrls"
              placeholder="请输入整改前照片URLs（多个用逗号分隔）"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="整改后照片URLs" prop="rectifyAfterPhotoUrls">
            <el-input
              v-model="formData.rectifyAfterPhotoUrls"
              placeholder="请输入整改后照片URLs（多个用逗号分隔）"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="验收意见" prop="acceptOpinion">
        <el-input
          v-model="formData.acceptOpinion"
          placeholder="请输入验收意见"
          type="textarea"
          rows="3"
        />
      </el-form-item>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="验收人" prop="acceptUser">
            <el-input v-model="formData.acceptUser" placeholder="请输入验收人" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="验收时间" prop="acceptTime">
            <el-date-picker
              v-model="formData.acceptTime"
              type="datetime"
              value-format="x"
              placeholder="选择验收时间"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="整改提交人" prop="rectifySubmitUser">
            <el-input v-model="formData.rectifySubmitUser" placeholder="请输入整改提交人" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="整改提交时间" prop="rectifySubmitTime">
            <el-date-picker
              v-model="formData.rectifySubmitTime"
              type="datetime"
              value-format="x"
              placeholder="选择整改提交时间"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="任务状态变更" prop="taskStatusChange">
        <el-input
          v-model="formData.taskStatusChange"
          placeholder="请输入任务状态变更（例：待验收->通过）"
        />
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
  InspectRectifyAcceptApi,
  InspectRectifyAcceptVO
} from '@/api/dataHub/supervisionInspection/issueCorrectionDisposal/correctionResultAcceptance'

/** 巡查巡检整改结果验收 表单 */
defineOptions({ name: 'InspectRectifyAcceptForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const activeNames = ref(['1']) // 折叠面板默认状态
const formData = ref<Partial<InspectRectifyAcceptVO>>({
  id: undefined,
  acceptId: undefined,
  acceptCode: undefined,
  rectifyTaskId: undefined,
  rectifyTaskCode: undefined,
  problemId: undefined,
  rectifyResultDesc: undefined,
  rectifyAfterPhotoUrls: undefined,
  rectifyBeforePhotoUrls: undefined,
  acceptResult: undefined,
  acceptOpinion: undefined,
  acceptUser: undefined,
  acceptTime: undefined,
  rectifySubmitUser: undefined,
  rectifySubmitTime: undefined,
  taskStatusChange: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  acceptId: [{ required: true, message: '验收ID不能为空', trigger: 'blur' }],
  acceptCode: [{ required: true, message: '验收编码不能为空', trigger: 'blur' }],
  rectifyTaskId: [{ required: true, message: '关联整改任务ID不能为空', trigger: 'blur' }],
  rectifyTaskCode: [{ required: true, message: '关联整改任务编码不能为空', trigger: 'blur' }],
  problemId: [{ required: true, message: '关联问题ID不能为空', trigger: 'blur' }],
  rectifyResultDesc: [{ required: true, message: '整改结果描述不能为空', trigger: 'blur' }],
  rectifyAfterPhotoUrls: [{ required: true, message: '整改后照片URLs不能为空', trigger: 'blur' }],
  rectifyBeforePhotoUrls: [{ required: true, message: '整改前照片URLs不能为空', trigger: 'blur' }],
  acceptResult: [{ required: true, message: '验收结果不能为空', trigger: 'change' }],
  acceptUser: [{ required: true, message: '验收人不能为空', trigger: 'blur' }],
  acceptTime: [{ required: true, message: '验收时间不能为空', trigger: 'change' }],
  rectifySubmitUser: [{ required: true, message: '整改提交人不能为空', trigger: 'blur' }],
  rectifySubmitTime: [{ required: true, message: '整改提交时间不能为空', trigger: 'change' }],
  taskStatusChange: [{ required: true, message: '任务状态变更不能为空', trigger: 'blur' }]
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
      const data = await InspectRectifyAcceptApi.getInspectRectifyAccept(id)
      formData.value = { ...data }
      // 转换时间格式为时间戳
      if (formData.value.acceptTime) {
        formData.value.acceptTime = new Date(formData.value.acceptTime).getTime()
      }
      if (formData.value.rectifySubmitTime) {
        formData.value.rectifySubmitTime = new Date(formData.value.rectifySubmitTime).getTime()
      }
    } finally {
      formLoading.value = false
    }
  } else {
    // 新增时默认值
    formData.value.acceptResult = '待验收'
    formData.value.rectifySubmitTime = Date.now()
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
    const data = { ...formData.value } as unknown as InspectRectifyAcceptVO
    // 转换时间格式为Date
    if (data.acceptTime) {
      data.acceptTime = new Date(Number(data.acceptTime))
    }
    if (data.rectifySubmitTime) {
      data.rectifySubmitTime = new Date(Number(data.rectifySubmitTime))
    }

    if (formType.value === 'create') {
      await InspectRectifyAcceptApi.createInspectRectifyAccept(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectRectifyAcceptApi.updateInspectRectifyAccept(data)
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
    acceptId: undefined,
    acceptCode: undefined,
    rectifyTaskId: undefined,
    rectifyTaskCode: undefined,
    problemId: undefined,
    rectifyResultDesc: undefined,
    rectifyAfterPhotoUrls: undefined,
    rectifyBeforePhotoUrls: undefined,
    acceptResult: '待验收',
    acceptOpinion: undefined,
    acceptUser: undefined,
    acceptTime: undefined,
    rectifySubmitUser: undefined,
    rectifySubmitTime: Date.now(),
    taskStatusChange: undefined,
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
