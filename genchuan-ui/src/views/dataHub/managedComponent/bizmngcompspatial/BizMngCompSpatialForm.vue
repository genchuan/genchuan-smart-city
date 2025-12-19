<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="空间数据ID" prop="mngCompSpatialId">
        <el-input v-model="formData.mngCompSpatialId" placeholder="请输入空间数据ID" />
      </el-form-item>
      <el-form-item label="关联管理部件ID" prop="mngCompId">
        <el-input v-model="formData.mngCompId" placeholder="请输入关联管理部件ID" />
      </el-form-item>
      <el-form-item label="关联部件名称" prop="compName">
        <el-input v-model="formData.compName" placeholder="请输入关联部件名称" />
      </el-form-item>
      <el-form-item label="坐标系类型" prop="coordSystem">
        <el-input v-model="formData.coordSystem" placeholder="请输入坐标系类型" />
      </el-form-item>
      <el-form-item label="坐标X，经度" prop="coordX">
        <el-input v-model="formData.coordX" placeholder="请输入坐标X，经度" />
      </el-form-item>
      <el-form-item label="坐标Y，纬度" prop="coordY">
        <el-input v-model="formData.coordY" placeholder="请输入坐标Y，纬度" />
      </el-form-item>
      <el-form-item label="高程" prop="elevation">
        <el-input v-model="formData.elevation" placeholder="请输入高程" />
      </el-form-item>
      <el-form-item label="定位精度" prop="accuracy">
        <el-input v-model="formData.accuracy" placeholder="请输入定位精度" />
      </el-form-item>
      <el-form-item label="定位精度等级" prop="accuracyLevel">
        <el-input v-model="formData.accuracyLevel" placeholder="请输入定位精度等级" />
      </el-form-item>
      <el-form-item label="测绘单位" prop="surveyUnit">
        <el-input v-model="formData.surveyUnit" placeholder="请输入测绘单位" />
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
import { BizMngCompSpatialApi, BizMngCompSpatialVO } from '@/api/dataHub/managedComponent/bizmngcompspatial'

/** 管理部件空间数据 表单 */
defineOptions({ name: 'BizMngCompSpatialForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  mngCompSpatialId: undefined,
  mngCompId: undefined,
  compName: undefined,
  coordSystem: undefined,
  coordX: undefined,
  coordY: undefined,
  elevation: undefined,
  accuracy: undefined,
  accuracyLevel: undefined,
  surveyUnit: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined
})
const formRules = reactive({
  mngCompSpatialId: [{ required: true, message: '空间数据ID不能为空', trigger: 'blur' }],
  mngCompId: [{ required: true, message: '关联管理部件ID不能为空', trigger: 'blur' }],
  compName: [{ required: true, message: '关联部件名称不能为空', trigger: 'blur' }],
  coordSystem: [{ required: true, message: '坐标系类型不能为空', trigger: 'blur' }],
  coordX: [{ required: true, message: '坐标X，经度不能为空', trigger: 'blur' }],
  coordY: [{ required: true, message: '坐标Y，纬度不能为空', trigger: 'blur' }],
  accuracy: [{ required: true, message: '定位精度不能为空', trigger: 'blur' }],
  accuracyLevel: [{ required: true, message: '定位精度等级不能为空', trigger: 'blur' }]
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
      formData.value = await BizMngCompSpatialApi.getBizMngCompSpatial(id)
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
    const data = formData.value as unknown as BizMngCompSpatialVO
    if (formType.value === 'create') {
      await BizMngCompSpatialApi.createBizMngCompSpatial(data)
      message.success(t('common.createSuccess'))
    } else {
      await BizMngCompSpatialApi.updateBizMngCompSpatial(data)
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
    mngCompSpatialId: undefined,
    mngCompId: undefined,
    compName: undefined,
    coordSystem: undefined,
    coordX: undefined,
    coordY: undefined,
    elevation: undefined,
    accuracy: undefined,
    accuracyLevel: undefined,
    surveyUnit: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined
  }
  formRef.value?.resetFields()
}
</script>
