<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="部件ID" prop="mngCompId">
        <el-input v-model="formData.mngCompId" placeholder="请输入部件ID" />
      </el-form-item>
      <el-form-item label="部件标识码" prop="compCode">
        <el-input v-model="formData.compCode" placeholder="请输入部件标识码" />
      </el-form-item>
      <el-form-item label="部件名称" prop="compName">
        <el-input v-model="formData.compName" placeholder="请输入部件名称" />
      </el-form-item>
      <el-form-item label="关联管理部件小类ID" prop="minorId">
        <el-input v-model="formData.minorId" placeholder="请输入关联管理部件小类ID" />
      </el-form-item>
      <el-form-item label="关联管理部件小类名称" prop="minorName">
        <el-input v-model="formData.minorName" placeholder="请输入关联管理部件小类名称" />
      </el-form-item>
      <el-form-item label="主管部门代码" prop="deptCode">
        <el-input v-model="formData.deptCode" placeholder="请输入主管部门代码" />
      </el-form-item>
      <el-form-item label="主管部门名称" prop="deptName">
        <el-input v-model="formData.deptName" placeholder="请输入主管部门名称" />
      </el-form-item>
      <el-form-item label="关联单元网格ID" prop="gridId">
        <el-input v-model="formData.gridId" placeholder="请输入关联单元网格ID" />
      </el-form-item>
      <el-form-item label="关联单元网格名称" prop="gridName">
        <el-input v-model="formData.gridName" placeholder="请输入关联单元网格名称" />
      </el-form-item>
      <el-form-item label="部件状态" prop="compStatus">
        <el-radio-group v-model="formData.compStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="部件普查日期" prop="initDate">
        <el-date-picker
          v-model="formData.initDate"
          type="date"
          value-format="x"
          placeholder="选择部件普查日期"
        />
      </el-form-item>
      <el-form-item label="状态/权属变更时更新日期" prop="changeDate">
        <el-date-picker
          v-model="formData.changeDate"
          type="date"
          value-format="x"
          placeholder="选择状态/权属变更时更新日期"
        />
      </el-form-item>
      <el-form-item label="数据来源" prop="dataSource">
        <el-input v-model="formData.dataSource" placeholder="请输入数据来源" />
      </el-form-item>
      <el-form-item label="录入人账号" prop="createUser">
        <el-input v-model="formData.createUser" placeholder="请输入录入人账号" />
      </el-form-item>
      <el-form-item label="修改人账号" prop="updateUser">
        <el-input v-model="formData.updateUser" placeholder="请输入修改人账号" />
      </el-form-item>
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
import { BizMngCompApi, BizMngCompVO } from '@/api/dataHub/managedComponent/bizmngcomp'

/** 管理部件信息管理 表单 */
defineOptions({ name: 'BizMngCompForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  mngCompId: undefined,
  compCode: undefined,
  compName: undefined,
  minorId: undefined,
  minorName: undefined,
  deptCode: undefined,
  deptName: undefined,
  gridId: undefined,
  gridName: undefined,
  compStatus: undefined,
  initDate: undefined,
  changeDate: undefined,
  dataSource: undefined,
  createUser: undefined,
  updateUser: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined
})
const formRules = reactive({
  mngCompId: [{ required: true, message: '部件ID不能为空', trigger: 'blur' }],
  compCode: [{ required: true, message: '部件标识码不能为空', trigger: 'blur' }],
  compName: [{ required: true, message: '部件名称不能为空', trigger: 'blur' }],
  minorId: [{ required: true, message: '关联管理部件小类ID不能为空', trigger: 'blur' }],
  minorName: [{ required: true, message: '关联管理部件小类名称不能为空', trigger: 'blur' }],
  deptCode: [{ required: true, message: '主管部门代码不能为空', trigger: 'blur' }],
  deptName: [{ required: true, message: '主管部门名称不能为空', trigger: 'blur' }],
  gridId: [{ required: true, message: '关联单元网格ID不能为空', trigger: 'blur' }],
  gridName: [{ required: true, message: '关联单元网格名称不能为空', trigger: 'blur' }],
  compStatus: [{ required: true, message: '部件状态不能为空', trigger: 'blur' }],
  initDate: [{ required: true, message: '部件普查日期不能为空', trigger: 'blur' }],
  createUser: [{ required: true, message: '录入人账号不能为空', trigger: 'blur' }]
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
      formData.value = await BizMngCompApi.getBizMngComp(id)
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
    const data = formData.value as unknown as BizMngCompVO
    if (formType.value === 'create') {
      await BizMngCompApi.createBizMngComp(data)
      message.success(t('common.createSuccess'))
    } else {
      await BizMngCompApi.updateBizMngComp(data)
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
    mngCompId: undefined,
    compCode: undefined,
    compName: undefined,
    minorId: undefined,
    minorName: undefined,
    deptCode: undefined,
    deptName: undefined,
    gridId: undefined,
    gridName: undefined,
    compStatus: undefined,
    initDate: undefined,
    changeDate: undefined,
    dataSource: undefined,
    createUser: undefined,
    updateUser: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined
  }
  formRef.value?.resetFields()
}
</script>
