<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="800px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="140px"
      v-loading="formLoading"
      class="p-4 bg-gray-50 rounded-lg"
    >
      <!-- 纵向布局：确保输入控件对齐，符合用户使用习惯 -->
      <el-form-item label="分类ID" prop="monEvtCatId">
        <el-input
          v-model="formData.monEvtCatId"
          placeholder="请输入分类ID"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="父类ID" prop="parentEvtId">
        <el-input
          v-model="formData.parentEvtId"
          placeholder="请输入父类ID（无父类填0）"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="分类层级" prop="evtCatLevel">
        <el-input
          v-model="formData.evtCatLevel"
          placeholder="请输入分类层级（例：1/2/3）"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="分类代码" prop="evtCatCode">
        <el-input
          v-model="formData.evtCatCode"
          placeholder="请输入分类代码"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="分类名称" prop="evtCatName">
        <el-input
          v-model="formData.evtCatName"
          placeholder="请输入分类名称"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="分类说明" prop="evtCatDesc">
        <el-input
          v-model="formData.evtCatDesc"
          placeholder="请输入分类说明"
          type="textarea"
          :rows="3"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="启用状态" prop="enableStatus">
        <el-select
          v-model="formData.enableStatus"
          placeholder="请选择启用状态"
          class="w-full rounded-md"
        >
          <el-option label="启用" value="1" />
          <el-option label="禁用" value="0" />
        </el-select>
      </el-form-item>
      <el-form-item label="创建人" prop="createUser">
        <el-input
          v-model="formData.createUser"
          placeholder="请输入创建人"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="更新人" prop="updateUser">
        <el-input
          v-model="formData.updateUser"
          placeholder="请输入更新人"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="系统创建时间" prop="createTimeSys">
        <el-date-picker
          v-model="formData.createTimeSys"
          type="date"
          value-format="x"
          placeholder="选择系统创建时间"
          class="w-full rounded-md"
        />
      </el-form-item>
      <el-form-item label="系统更新时间" prop="updateTimeSys">
        <el-date-picker
          v-model="formData.updateTimeSys"
          type="date"
          value-format="x"
          placeholder="选择系统更新时间"
          class="w-full rounded-md"
        />
      </el-form-item>
    </el-form>

    <template #footer>
      <el-button @click="dialogVisible = false" class="mr-2">取 消</el-button>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
import {
  MonEvtCatApi,
  MonEvtCatVO
} from '@/api/dataHub/monitorCompEventMgr/monitorEvtConfigMgr/monevtcat'

/** 监测事件分类配置 表单 */
defineOptions({ name: 'MonEvtCatForm' })
const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改

// 简化表单数据，隐藏不必要字段
const formData = ref({
  id: undefined,
  monEvtCatId: undefined,
  parentEvtId: undefined,
  evtCatLevel: undefined,
  evtCatCode: undefined,
  evtCatName: undefined,
  evtCatDesc: undefined,
  enableStatus: undefined,
  createUser: undefined,
  updateUser: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined
})

// 保留核心必填项校验
const formRules = reactive({
  monEvtCatId: [{ required: true, message: '分类ID不能为空', trigger: 'blur' }],
  parentEvtId: [{ required: true, message: '父类ID不能为空', trigger: 'blur' }],
  evtCatLevel: [{ required: true, message: '分类层级不能为空', trigger: 'blur' }],
  evtCatCode: [{ required: true, message: '分类代码不能为空', trigger: 'blur' }],
  evtCatName: [{ required: true, message: '分类名称不能为空', trigger: 'blur' }],
  enableStatus: [{ required: true, message: '启用状态不能为空', trigger: 'change' }],
  createUser: [{ required: true, message: '创建人不能为空', trigger: 'blur' }]
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
      const res = await MonEvtCatApi.getMonEvtCat(id)
      formData.value = {
        ...res,
        // 只保留需要展示的字段
        evtCatDesc: res.evtCatDesc || '',
        updateUser: res.updateUser || ''
      }
    } finally {
      formLoading.value = false
    }
  }
}

defineExpose({ open }) // 提供 open 方法

/** 提交表单 */
const emit = defineEmits(['success'])
const submitForm = async () => {
  // 校验表单
  await formRef.value.validate()
  // 提交请求
  formLoading.value = true
  try {
    const data = formData.value as unknown as MonEvtCatVO
    if (formType.value === 'create') {
      await MonEvtCatApi.createMonEvtCat(data)
      message.success(t('common.createSuccess'))
    } else {
      await MonEvtCatApi.updateMonEvtCat(data)
      message.success(t('common.updateSuccess'))
    }
    dialogVisible.value = false
    emit('success')
  } finally {
    formLoading.value = false
  }
}

/** 重置表单 */
const resetForm = () => {
  formData.value = {
    id: undefined,
    monEvtCatId: undefined,
    parentEvtId: undefined,
    evtCatLevel: undefined,
    evtCatCode: undefined,
    evtCatName: undefined,
    evtCatDesc: undefined,
    enableStatus: undefined,
    createUser: undefined,
    updateUser: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined
  }
  formRef.value?.resetFields()
}
</script>

<style scoped>
.el-form {
  background-color: #fafafa;
}

.el-input,
.el-date-picker,
.el-select {
  --el-input-bg-color: #fff;
  --el-input-border-color: #e4e7ed;
  --el-input-hover-border-color: #409eff;
  width: 100%;
}

.el-dialog__body {
  padding: 16px 24px;
}

.el-form-item {
  margin-bottom: 20px;
}

.el-textarea {
  resize: none;
}

/* 确保输入控件高度一致 */
:deep(.el-input__wrapper),
:deep(.el-date-editor) {
  height: 32px;
  line-height: 32px;
}

:deep(.el-date-editor .el-input__wrapper) {
  height: auto;
}

/* 日期选择器宽度适配 */
:deep(.el-date-editor--date) {
  width: 100% !important;
}
</style>
