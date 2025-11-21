<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="事项ID" prop="mngMatterId">
        <el-input v-model="formData.mngMatterId" placeholder="请输入事项ID" />
      </el-form-item>
      <el-form-item label="事项标识码" prop="matterCode">
        <el-input v-model="formData.matterCode" placeholder="请输入事项标识码" />
      </el-form-item>
      <el-form-item label="事项名称" prop="matterName">
        <el-input v-model="formData.matterName" placeholder="请输入事项名称" />
      </el-form-item>
      <el-form-item label="关联管理事项小类ID" prop="minorId">
        <el-input v-model="formData.minorId" placeholder="请输入关联管理事项小类ID" />
      </el-form-item>
      <el-form-item label="关联管理事项小类名称" prop="minorName">
        <el-input v-model="formData.minorName" placeholder="请输入关联管理事项小类名称" />
      </el-form-item>
      <el-form-item label="关联单元网格ID" prop="gridId">
        <el-input v-model="formData.gridId" placeholder="请输入关联单元网格ID" />
      </el-form-item>
      <el-form-item label="关联单元网格名称" prop="gridName">
        <el-input v-model="formData.gridName" placeholder="请输入关联单元网格名称" />
      </el-form-item>
      <el-form-item label="事项状态" prop="matterStatus">
        <el-radio-group v-model="formData.matterStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="事项等级" prop="matterLevel">
        <el-input v-model="formData.matterLevel" placeholder="请输入事项等级" />
      </el-form-item>
      <el-form-item label="主管部门代码" prop="deptCode">
        <el-input v-model="formData.deptCode" placeholder="请输入主管部门代码" />
      </el-form-item>
      <el-form-item label="主管部门名称" prop="deptName">
        <el-input v-model="formData.deptName" placeholder="请输入主管部门名称" />
      </el-form-item>
      <el-form-item label="事发位置" prop="incidentLocation">
        <el-input v-model="formData.incidentLocation" placeholder="请输入事发位置" />
      </el-form-item>
      <el-form-item label="录入人账号" prop="createUser">
        <el-input v-model="formData.createUser" placeholder="请输入录入人账号" />
      </el-form-item>
      <el-form-item label="修改人账号" prop="updateUser">
        <el-input v-model="formData.updateUser" placeholder="请输入修改人账号" />
      </el-form-item>
      <el-form-item label="系统创建时间" prop="createTimeSys">
        <el-date-picker
          v-model="formData.createTimeSys"
          type="date"
          value-format="x"
          placeholder="选择系统创建时间"
        />
      </el-form-item>
      <el-form-item label="系统更新时间" prop="updateTimeSys">
        <el-date-picker
          v-model="formData.updateTimeSys"
          type="date"
          value-format="x"
          placeholder="选择系统更新时间"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { BizMngMatterApi, BizMngMatterVO } from '@/api/dataHub/managedComponent/bizmngmatter'

/** 管理事项信息 表单 */
defineOptions({ name: 'BizMngMatterForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  mngMatterId: undefined,
  matterCode: undefined,
  matterName: undefined,
  minorId: undefined,
  minorName: undefined,
  gridId: undefined,
  gridName: undefined,
  matterStatus: undefined,
  matterLevel: undefined,
  deptCode: undefined,
  deptName: undefined,
  incidentLocation: undefined,
  createUser: undefined,
  updateUser: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  mngMatterId: [{ required: true, message: '事项ID不能为空', trigger: 'blur' }],
  matterCode: [{ required: true, message: '事项标识码不能为空', trigger: 'blur' }],
  matterName: [{ required: true, message: '事项名称不能为空', trigger: 'blur' }],
  minorId: [{ required: true, message: '关联管理事项小类ID', trigger: 'blur' }],
  minorName: [{ required: true, message: '关联管理事项小类名称', trigger: 'blur' }],
  gridId: [{ required: true, message: '关联单元网格ID', trigger: 'blur' }],
  gridName: [{ required: true, message: '关联单元网格名称', trigger: 'blur' }],
  matterStatus: [{ required: true, message: '事项状态不能为空', trigger: 'blur' }],
  deptCode: [{ required: true, message: '主管部门代码不能为空', trigger: 'blur' }],
  deptName: [{ required: true, message: '主管部门名称不能为空', trigger: 'blur' }],
  createUser: [{ required: true, message: '录入人账号不能为空', trigger: 'blur' }],
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
      formData.value = await BizMngMatterApi.getBizMngMatter(id)
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
    const data = formData.value as unknown as BizMngMatterVO
    if (formType.value === 'create') {
      await BizMngMatterApi.createBizMngMatter(data)
      message.success(t('common.createSuccess'))
    } else {
      await BizMngMatterApi.updateBizMngMatter(data)
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
    mngMatterId: undefined,
    matterCode: undefined,
    matterName: undefined,
    minorId: undefined,
    minorName: undefined,
    gridId: undefined,
    gridName: undefined,
    matterStatus: undefined,
    matterLevel: undefined,
    deptCode: undefined,
    deptName: undefined,
    incidentLocation: undefined,
    createUser: undefined,
    updateUser: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined,
  }
  formRef.value?.resetFields()
}
</script>
