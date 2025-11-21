<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="600px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
      class="form-container"
    >
      <el-form-item label="人员ID" prop="personId" required>
        <el-input v-model="formData.personId" placeholder="请输入人员ID" />
      </el-form-item>
      <el-form-item label="人员姓名" prop="personName" required>
        <el-input v-model="formData.personName" placeholder="请输入人员姓名" />
      </el-form-item>
      <el-form-item label="人员账号" prop="personAccount" required>
        <el-input v-model="formData.personAccount" placeholder="请输入人员账号" />
      </el-form-item>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="所属部门代码" prop="deptCode" required>
            <el-input v-model="formData.deptCode" placeholder="请输入部门代码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属部门名称" prop="deptName" required>
            <el-input v-model="formData.deptName" placeholder="请输入部门名称" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="联系电话" prop="personPhone" required>
        <el-input v-model="formData.personPhone" placeholder="请输入联系电话" />
      </el-form-item>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="负责网格ID" prop="gridId">
            <el-input v-model="formData.gridId" placeholder="请输入网格ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="负责网格名称" prop="gridName">
            <el-input v-model="formData.gridName" placeholder="请输入网格名称" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="人员角色" prop="personRole">
        <el-input v-model="formData.personRole" placeholder="请输入人员角色" />
      </el-form-item>
      <el-form-item label="关联设备编码" prop="deviceCode">
        <el-input v-model="formData.deviceCode" placeholder="请输入设备编码" />
      </el-form-item>
      <el-form-item label="人员状态" prop="personStatus" required>
        <el-radio-group v-model="formData.personStatus">
          <el-radio :label="true" border>启用</el-radio>
          <el-radio :label="false" border>禁用</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="入职日期" prop="entryDate">
        <el-date-picker
          v-model="formData.entryDate"
          type="date"
          value-format="x"
          placeholder="选择入职日期"
        />
      </el-form-item>

      <!-- 折叠面板展示扩展字段 -->
      <el-collapse v-model="activeNames">
        <el-collapse-item name="1" title="扩展信息">
          <el-form-item label="分类扩展字段1" prop="extCat1">
            <el-input v-model="formData.extCat1" placeholder="请输入分类扩展字段1" />
          </el-form-item>
          <el-form-item label="分类扩展字段2" prop="extCat2">
            <el-input v-model="formData.extCat2" placeholder="请输入分类扩展字段2" />
          </el-form-item>
          <el-form-item label="通用扩展字段1" prop="extCommon1">
            <el-input v-model="formData.extCommon1" placeholder="请输入通用扩展字段1" />
          </el-form-item>
          <el-form-item label="通用扩展字段2" prop="extCommon2">
            <el-input v-model="formData.extCommon2" placeholder="请输入通用扩展字段2" />
          </el-form-item>
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
  InspectPersonInfoApi,
  InspectPersonInfoVO
} from '@/api/dataHub/supervisionInspection/patrolResource/staffManage'

/** 巡查巡检人员信息 表单 */
defineOptions({ name: 'InspectPersonInfoForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const activeNames = ref(['1']) // 折叠面板状态
const formData = ref<Partial<InspectPersonInfoVO>>({
  id: undefined,
  personId: undefined,
  personName: undefined,
  personAccount: undefined,
  deptCode: undefined,
  deptName: undefined,
  personPhone: undefined,
  gridId: undefined,
  gridName: undefined,
  personRole: undefined,
  deviceCode: undefined,
  personStatus: true,
  entryDate: undefined,
  createUser: undefined,
  bizCreateTime: undefined,
  updateUser: undefined,
  bizUpdateTime: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const formRules = reactive({
  personId: [{ required: true, message: '请输入人员ID', trigger: 'blur' }],
  personName: [{ required: true, message: '请输入人员姓名', trigger: 'blur' }],
  personAccount: [{ required: true, message: '请输入人员账号', trigger: 'blur' }],
  deptCode: [{ required: true, message: '请输入部门代码', trigger: 'blur' }],
  deptName: [{ required: true, message: '请输入部门名称', trigger: 'blur' }],
  personPhone: [{ required: true, message: '请输入联系电话', trigger: 'blur' }],
  personStatus: [{ required: true, message: '请选择人员状态', trigger: 'change' }]
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
      const data = await InspectPersonInfoApi.getInspectPersonInfo(id)
      // 转换时间格式
      if (data.entryDate) data.entryDate = new Date(data.entryDate).getTime()
      if (data.bizCreateTime) data.bizCreateTime = new Date(data.bizCreateTime).getTime()
      if (data.bizUpdateTime) data.bizUpdateTime = new Date(data.bizUpdateTime).getTime()
      formData.value = data
    } finally {
      formLoading.value = false
    }
  }
}
defineExpose({ open }) // 提供 open 方法，用于打开弹窗

/** 提交表单 */
const emit = defineEmits(['success']) // 定义 success 事件
const submitForm = async () => {
  // 校验表单
  await formRef.value.validate()
  // 提交请求
  formLoading.value = true
  try {
    const data = formData.value as InspectPersonInfoVO
    if (formType.value === 'create') {
      await InspectPersonInfoApi.createInspectPersonInfo(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectPersonInfoApi.updateInspectPersonInfo(data)
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
    personId: undefined,
    personName: undefined,
    personAccount: undefined,
    deptCode: undefined,
    deptName: undefined,
    personPhone: undefined,
    gridId: undefined,
    gridName: undefined,
    personRole: undefined,
    deviceCode: undefined,
    personStatus: true,
    entryDate: undefined,
    createUser: undefined,
    bizCreateTime: undefined,
    updateUser: undefined,
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

::v-deep .el-collapse-item__content {
  padding-bottom: 5px !important;
}

::v-deep .el-radio-group {
  display: flex;
  gap: 15px;
}
</style>
