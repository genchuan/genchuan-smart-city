<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <!-- 1. 大类ID：下拉选择（预设已有大类数据） -->
      <el-form-item label="大类ID" prop="majorId">
        <el-select v-model="formData.majorId" placeholder="请选择大类ID">
          <el-option
            v-for="item in majorOptions"
            :key="item.id"
            :label="`${item.id}（${item.name}）`"
            :value="item.id"
          />
        </el-select>
      </el-form-item>

      <!-- 2. 小类代码：下拉选择（与小类名称联动） -->
      <el-form-item label="小类代码" prop="minorCode">
        <el-select
          v-model="formData.minorCode"
          placeholder="请选择小类代码"
          @change="handleMinorCodeChange"
        >
          <el-option
            v-for="item in minorCodeOptions"
            :key="item.code"
            :label="`${item.code}（${item.name}）`"
            :value="item.code"
          />
        </el-select>
      </el-form-item>

      <!-- 3. 小类名称：联动填充（下拉框禁用，确保与代码一致） -->
      <el-form-item label="小类名称" prop="minorName">
        <el-select
          v-model="formData.minorName"
          placeholder="请先选择小类代码"
          disabled
        >
          <el-option
            v-for="item in minorCodeOptions"
            :key="item.name"
            :label="item.name"
            :value="item.name"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="小类说明" prop="minorDesc">
        <el-input v-model="formData.minorDesc" placeholder="请输入小类说明" />
      </el-form-item>

      <!-- 4. 主管部门代码：下拉选择（与部门名称联动） -->
      <el-form-item label="主管部门代码" prop="deptCode">
        <el-select
          v-model="formData.deptCode"
          placeholder="请选择主管部门代码"
          @change="handleDeptCodeChange"
        >
          <el-option
            v-for="item in deptOptions"
            :key="item.code"
            :label="`${item.code}（${item.name}）`"
            :value="item.code"
          />
        </el-select>
      </el-form-item>

      <!-- 5. 主管部门名称：联动填充（下拉框禁用，避免手动修改） -->
      <el-form-item label="主管部门名称" prop="deptName">
        <el-select
          v-model="formData.deptName"
          placeholder="请先选择主管部门代码"
          disabled
        >
          <el-option
            v-for="item in deptOptions"
            :key="item.name"
            :label="item.name"
            :value="item.name"
          />
        </el-select>
      </el-form-item>

      <!-- 6. 是否扩展类：保留下拉框，优化选项显示 -->
      <el-form-item label="是否扩展类" prop="isExtend">
        <el-select v-model="formData.isExtend" placeholder="请选择是否扩展类">
          <el-option label="是（支持扩展字段）" value="1" />
          <el-option label="否（固定字段结构）" value="0" />
        </el-select>
      </el-form-item>

      <el-form-item label="创建人" prop="createUser">
        <el-input v-model="formData.createUser" placeholder="请输入创建人" />
      </el-form-item>

      <el-form-item label="更新人" prop="updateUser">
        <el-input v-model="formData.updateUser" placeholder="请输入更新人" />
      </el-form-item>

      <el-form-item label="扩展字段1" prop="extField1">
        <el-input v-model="formData.extField1" placeholder="请输入扩展字段1" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
/** 管理部件小类配置 表单 */
defineOptions({ name: 'ManagedComponentMinorConfigForm' })

const { t } = useI18n()
const message = useMessage()

const dialogVisible = ref(false)
const dialogTitle = ref('')
const formLoading = ref(false)
const formType = ref('')
const formData = ref({
  id: undefined,
  minorId: undefined,
  majorId: undefined,
  minorCode: undefined,
  minorName: undefined,
  minorDesc: undefined,
  deptCode: undefined,
  deptName: undefined,
  isExtend: undefined,
  createUser: undefined,
  updateUser: undefined,
  extField1: undefined,
})

// ---------------------- 下拉框选项配置 ----------------------
// 1. 大类ID选项（模拟实际业务中已存在的大类）
const majorOptions = ref([
  { id: 'MAJOR001', name: '机械部件' },
  { id: 'MAJOR002', name: '电气部件' },
  { id: 'MAJOR003', name: '液压系统' },
  { id: 'MAJOR004', name: '气动系统' }
])

// 2. 小类代码选项（与小类名称一一对应，用于联动）
const minorCodeOptions = ref([
  { code: 'ELEC_001', name: '电气设备' },
  { code: 'ELEC_002', name: '电气配件' },
  { code: 'MECH_001', name: '机械设备' },
  { code: 'MECH_002', name: '机械配件' },
  { code: 'HYD_001', name: '液压设备' },
  { code: 'PNE_001', name: '气动设备' }
])

// 3. 主管部门选项（代码+名称映射，用于联动）
const deptOptions = ref([
  { code: 'DEPT_ENG', name: '工程部' },
  { code: 'DEPT_MAINT', name: '维护部' },
  { code: 'DEPT_PUR', name: '采购部' },
  { code: 'DEPT_QUAL', name: '质检部' }
])

// ---------------------- 表单验证规则 ----------------------
const formRules = reactive({
  majorId: [{ required: true, message: '大类ID不能为空', trigger: 'change' }],
  minorCode: [{ required: true, message: '小类代码不能为空', trigger: 'change' }],
  minorName: [{ required: true, message: '小类名称不能为空', trigger: 'change' }],
  deptCode: [{ required: true, message: '主管部门代码不能为空', trigger: 'change' }],
  deptName: [{ required: true, message: '主管部门名称不能为空', trigger: 'change' }],
  isExtend: [{ required: true, message: '是否扩展类不能为空', trigger: 'change' }],
  createUser: [{ required: true, message: '创建人不能为空', trigger: 'blur' }],
})

const formRef = ref()

// ---------------------- 字段联动逻辑 ----------------------
// 1. 选择小类代码后，自动填充小类名称
const handleMinorCodeChange = (code: string) => {
  const matchedItem = minorCodeOptions.value.find(item => item.code === code)
  formData.value.minorName = matchedItem ? matchedItem.name : ''
}

// 2. 选择主管部门代码后，自动填充主管部门名称
const handleDeptCodeChange = (code: string) => {
  const matchedItem = deptOptions.value.find(item => item.code === code)
  formData.value.deptName = matchedItem ? matchedItem.name : ''
}

// ---------------------- 原有业务逻辑保留（优化回显联动） ----------------------
// 模拟数据存储
let mockDataStore = [
  {
    id: 1,
    minorId: 'MINOR001',
    majorId: 'MAJOR001',
    minorCode: 'ELEC_001',
    minorName: '电气设备',
    minorDesc: '包括变压器、开关柜等电气设备',
    deptCode: 'DEPT_ENG',
    deptName: '工程部',
    isExtend: '0',
    createUser: 'admin',
    createTime: '2024-01-15 10:30:00',
    updateUser: 'admin',
    extField1: '高压设备'
  },
  {
    id: 2,
    minorId: 'MINOR002',
    majorId: 'MAJOR001',
    minorCode: 'MECH_001',
    minorName: '机械设备',
    minorDesc: '包括泵、风机等机械设备',
    deptCode: 'DEPT_ENG',
    deptName: '工程部',
    isExtend: '0',
    createUser: 'admin',
    createTime: '2024-01-16 14:20:00',
    updateUser: 'user1',
    extField1: '旋转设备'
  }
]

// 打开弹窗（新增编辑回显时触发联动）
const open = async (type: string, id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = type === 'create' ? '新增管理部件小类配置' : '修改管理部件小类配置'
  formType.value = type
  resetForm()

  if (id) {
    formLoading.value = true
    try {
      await new Promise(resolve => setTimeout(resolve, 500))
      const record = mockDataStore.find(item => item.id === id)
      if (record) {
        formData.value = { ...record }
        // 回显时触发联动，确保名称与代码一致
        handleMinorCodeChange(record.minorCode)
        handleDeptCodeChange(record.deptCode)
      } else {
        message.error('未找到对应的数据')
        dialogVisible.value = false
      }
    } finally {
      formLoading.value = false
    }
  } else {
    // 新增时默认填充当前用户
    formData.value.createUser = 'current_user'
    formData.value.updateUser = 'current_user'
  }
}

// 获取模拟数据
const getMockData = () => mockDataStore

// 合并一次 defineExpose
defineExpose({
  open,
  getMockData
})

// 提交表单
const emit = defineEmits(['success'])
const submitForm = async () => {
  await formRef.value.validate()
  formLoading.value = true
  try {
    await new Promise(resolve => setTimeout(resolve, 800))
    if (formType.value === 'create') {
      const newId = Math.max(...mockDataStore.map(item => item.id), 0) + 1
      const newMinorId = `MINOR${newId.toString().padStart(3, '0')}`
      const newRecord = {
        ...formData.value,
        id: newId,
        minorId: newMinorId,
        createTime: new Date().toISOString().replace('T', ' ').substring(0, 19),
        updateTime: new Date().toISOString().replace('T', ' ').substring(0, 19)
      }
      mockDataStore.push(newRecord)
      message.success('新增成功')
    } else {
      const index = mockDataStore.findIndex(item => item.id === formData.value.id)
      if (index > -1) {
        mockDataStore[index] = {
          ...mockDataStore[index],
          ...formData.value,
          updateTime: new Date().toISOString().replace('T', ' ').substring(0, 19)
        }
        message.success('修改成功')
      } else {
        message.error('未找到要修改的数据')
      }
    }
    dialogVisible.value = false
    emit('success')
  } catch (error) {
    console.error('提交失败:', error)
    message.error('提交失败，请重试')
  } finally {
    formLoading.value = false
  }
}

// 重置表单
const resetForm = () => {
  formData.value = {
    id: undefined,
    minorId: undefined,
    majorId: undefined,
    minorCode: undefined,
    minorName: undefined,
    minorDesc: undefined,
    deptCode: undefined,
    deptName: undefined,
    isExtend: undefined,
    createUser: undefined,
    updateUser: undefined,
    extField1: undefined,
  }
  formRef.value?.resetFields()
}
</script>
