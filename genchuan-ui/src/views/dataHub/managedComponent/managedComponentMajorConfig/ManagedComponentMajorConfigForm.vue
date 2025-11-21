<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <!-- 大类代码改为下拉框 -->
      <el-form-item label="大类代码" prop="majorCode">
        <el-select v-model="formData.majorCode" placeholder="请选择大类代码">
          <el-option
            v-for="code in majorCodeOptions"
            :key="code.value"
            :label="code.label"
            :value="code.value"
          />
        </el-select>
      </el-form-item>

      <!-- 大类名称改为下拉框 -->
      <el-form-item label="大类名称" prop="majorName">
        <el-select v-model="formData.majorName" placeholder="请选择大类名称">
          <el-option
            v-for="name in majorNameOptions"
            :key="name.value"
            :label="name.label"
            :value="name.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="大类说明" prop="majorDesc">
        <el-input v-model="formData.majorDesc" placeholder="请输入大类说明" />
      </el-form-item>

      <!-- 排序序号改为下拉框 -->
      <el-form-item label="排序序号" prop="sortNum">
        <el-select v-model="formData.sortNum" placeholder="请选择排序序号">
          <el-option
            v-for="num in sortNumOptions"
            :key="num"
            :label="num.toString()"
            :value="num"
          />
        </el-select>
      </el-form-item>

      <!-- 创建人改为下拉框 -->
      <el-form-item label="创建人" prop="createUser">
        <el-select v-model="formData.createUser" placeholder="请选择创建人">
          <el-option
            v-for="user in userOptions"
            :key="user.value"
            :label="user.label"
            :value="user.value"
          />
        </el-select>
      </el-form-item>

      <!-- 更新人改为下拉框 -->
      <el-form-item label="更新人" prop="updateUser">
        <el-select v-model="formData.updateUser" placeholder="请选择更新人">
          <el-option
            v-for="user in userOptions"
            :key="user.value"
            :label="user.label"
            :value="user.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="扩展字段1" prop="extField1">
        <el-input v-model="formData.extField1" placeholder="请输入扩展字段1" />
      </el-form-item>
      <el-form-item label="扩展字段2" prop="extField2">
        <el-input v-model="formData.extField2" placeholder="请输入扩展字段2" />
      </el-form-item>
      <el-form-item label="扩展字段3" prop="extField3">
        <el-input v-model="formData.extField3" placeholder="请输入扩展字段3" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'

// 定义类型
interface ManagedComponentMajorConfigVO {
  majorId?: number
  majorCode?: string
  majorName?: string
  majorDesc?: string
  sortNum?: number
  createUser?: string
  updateUser?: string
  extField1?: string
  extField2?: string
  extField3?: string
}

/** 部件大类配置表 表单 */
defineOptions({ name: 'ManagedComponentMajorConfigForm' })

// 模拟消息和国际化函数
const message = {
  success: (msg: string) => {
    console.log('Success:', msg)
    alert(msg) // 可以用实际的消息组件替换
  }
}

const t = (key: string) => {
  const translations: Record<string, string> = {
    'action.create': '新增',
    'action.update': '修改',
    'common.createSuccess': '创建成功',
    'common.updateSuccess': '更新成功'
  }
  return translations[key] || key
}

// 下拉框选项数据
const majorCodeOptions = [
  { value: 'MC-001', label: 'MC-001 (机械类)' },
  { value: 'MC-002', label: 'MC-002 (电气类)' },
  { value: 'MC-003', label: 'MC-003 (液压类)' },
  { value: 'MC-004', label: 'MC-004 (气动类)' },
  { value: 'MC-005', label: 'MC-005 (控制类)' }
]

const majorNameOptions = [
  { value: '机械部件', label: '机械部件' },
  { value: '电气部件', label: '电气部件' },
  { value: '液压系统', label: '液压系统' },
  { value: '气动系统', label: '气动系统' },
  { value: '控制系统', label: '控制系统' }
]

const sortNumOptions = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

const userOptions = [
  { value: 'admin', label: '管理员' },
  { value: 'engineer', label: '工程师' },
  { value: 'operator', label: '操作员' },
  { value: 'designer', label: '设计师' }
]

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<ManagedComponentMajorConfigVO>({
  majorId: undefined,
  majorCode: undefined,
  majorName: undefined,
  majorDesc: undefined,
  sortNum: undefined,
  createUser: undefined,
  updateUser: undefined,
  extField1: undefined,
  extField2: undefined,
  extField3: undefined,
})
const formRules = reactive({
  majorCode: [{ required: true, message: '大类代码不能为空', trigger: 'change' }],
  majorName: [{ required: true, message: '大类名称不能为空', trigger: 'change' }],
  createUser: [{ required: true, message: '创建人不能为空', trigger: 'change' }],
})
const formRef = ref() // 表单 Ref

/** 模拟数据 - 用于编辑时的数据 */
const mockData: Record<number, ManagedComponentMajorConfigVO> = {
  1: {
    majorId: 1,
    majorCode: 'MC-001',
    majorName: '机械部件',
    majorDesc: '机械类部件',
    sortNum: 1,
    createUser: 'admin',
    updateUser: 'admin',
    extField1: '扩展1',
    extField2: '扩展2',
    extField3: '扩展3'
  },
  2: {
    majorId: 2,
    majorCode: 'MC-002',
    majorName: '电气部件',
    majorDesc: '电气类部件',
    sortNum: 2,
    createUser: 'admin',
    updateUser: 'admin',
    extField1: '',
    extField2: '',
    extField3: ''
  }
}

/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = t('action.' + type)
  formType.value = type
  resetForm()
  // 修改时，设置模拟数据
  if (id && mockData[id]) {
    formLoading.value = true
    try {
      // 模拟异步加载
      await new Promise(resolve => setTimeout(resolve, 500))
      formData.value = { ...mockData[id] }
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
  if (!formRef.value) return
  await formRef.value.validate()

  // 提交请求（模拟）
  formLoading.value = true
  try {
    // 模拟异步提交
    await new Promise(resolve => setTimeout(resolve, 1000))

    const data = formData.value
    if (formType.value === 'create') {
      // 模拟创建
      console.log('创建数据:', data)
      message.success(t('common.createSuccess'))
    } else {
      // 模拟更新
      console.log('更新数据:', data)
      message.success(t('common.updateSuccess'))
    }
    dialogVisible.value = false
    // 发送操作成功的事件
    emit('success')
  } catch (error) {
    console.error('提交失败:', error)
  } finally {
    formLoading.value = false
  }
}

/** 重置表单 */
const resetForm = () => {
  formData.value = {
    majorId: undefined,
    majorCode: undefined,
    majorName: undefined,
    majorDesc: undefined,
    sortNum: undefined,
    createUser: undefined,
    updateUser: undefined,
    extField1: undefined,
    extField2: undefined,
    extField3: undefined,
  }
  formRef.value?.resetFields()
}
</script>
