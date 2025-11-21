<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="部件ID" prop="monCompId">
        <el-input v-model="formData.monCompId" placeholder="请输入部件ID" />
      </el-form-item>
      <el-form-item label="部件标识码" prop="compCode">
        <el-input v-model="formData.compCode" placeholder="请输入部件标识码" />
      </el-form-item>
      <el-form-item label="部件名称" prop="compName">
        <el-input v-model="formData.compName" placeholder="请输入部件名称" />
      </el-form-item>
      <el-form-item label="部件分类ID" prop="compCatId">
        <el-input v-model="formData.compCatId" placeholder="请输入部件分类ID" />
      </el-form-item>
      <el-form-item label="主管部门代码" prop="deptCode">
        <el-input v-model="formData.deptCode" placeholder="请输入主管部门代码" />
      </el-form-item>
      <el-form-item label="主管部门名称" prop="deptName">
        <el-input v-model="formData.deptName" placeholder="请输入主管部门名称" />
      </el-form-item>
      <el-form-item label="所在行政区域代码" prop="regionCode">
        <el-input v-model="formData.regionCode" placeholder="请输入所在行政区域代码" />
      </el-form-item>
      <el-form-item label="所在行政区域名称" prop="regionName">
        <el-input v-model="formData.regionName" placeholder="请输入所在行政区域名称" />
      </el-form-item>
      <el-form-item label="所在网格ID" prop="gridId">
        <el-input v-model="formData.gridId" placeholder="请输入所在网格ID" />
      </el-form-item>
      <el-form-item label="所在网格名称" prop="gridName">
        <el-input v-model="formData.gridName" placeholder="请输入所在网格名称" />
      </el-form-item>
      <el-form-item label="坐标X，经度" prop="coordX">
        <el-input v-model="formData.coordX" placeholder="请输入坐标X，经度" />
      </el-form-item>
      <el-form-item label="坐标Y，纬度" prop="coordY">
        <el-input v-model="formData.coordY" placeholder="请输入坐标Y，纬度" />
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
import { MonCompInfoApi, MonCompInfoVO } from '@/api/dataHub/managedComponent/moncompinfo'

/** 监测部件信息 表单 */
defineOptions({ name: 'MonCompInfoForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  monCompId: undefined,
  compCode: undefined,
  compName: undefined,
  compCatId: undefined,
  deptCode: undefined,
  deptName: undefined,
  regionCode: undefined,
  regionName: undefined,
  gridId: undefined,
  gridName: undefined,
  coordX: undefined,
  coordY: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const formRules = reactive({
  monCompId: [{ required: true, message: '部件ID不能为空', trigger: 'blur' }],
  compCode: [{ required: true, message: '部件标识码不能为空', trigger: 'blur' }],
  compName: [{ required: true, message: '部件名称不能为空', trigger: 'blur' }],
  compCatId: [{ required: true, message: '部件分类ID不能为空', trigger: 'blur' }],
  deptCode: [{ required: true, message: '主管部门代码不能为空', trigger: 'blur' }],
  deptName: [{ required: true, message: '主管部门名称不能为空', trigger: 'blur' }],
  regionCode: [{ required: true, message: '所在行政区域代码不能为空', trigger: 'blur' }],
  regionName: [{ required: true, message: '所在行政区域名称不能为空', trigger: 'blur' }],
  gridId: [{ required: true, message: '所在网格ID不能为空', trigger: 'blur' }],
  gridName: [{ required: true, message: '所在网格名称不能为空', trigger: 'blur' }],
  coordX: [{ required: true, message: '坐标X，经度不能为空', trigger: 'blur' }],
  coordY: [{ required: true, message: '坐标Y，纬度不能为空', trigger: 'blur' }],
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
      formData.value = await MonCompInfoApi.getMonCompInfo(id)
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
    const data = formData.value as unknown as MonCompInfoVO
    if (formType.value === 'create') {
      await MonCompInfoApi.createMonCompInfo(data)
      message.success(t('common.createSuccess'))
    } else {
      await MonCompInfoApi.updateMonCompInfo(data)
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
    monCompId: undefined,
    compCode: undefined,
    compName: undefined,
    compCatId: undefined,
    deptCode: undefined,
    deptName: undefined,
    regionCode: undefined,
    regionName: undefined,
    gridId: undefined,
    gridName: undefined,
    coordX: undefined,
    coordY: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined,
  }
  formRef.value?.resetFields()
}
</script>
