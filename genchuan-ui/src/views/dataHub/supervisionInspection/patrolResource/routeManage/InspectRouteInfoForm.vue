<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="700px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="140px"
      v-loading="formLoading"
      class="form-container"
    >
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="路线ID" prop="routeId" required>
            <el-input v-model="formData.routeId" placeholder="请输入路线ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="路线名称" prop="routeName" required>
            <el-input v-model="formData.routeName" placeholder="请输入路线名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="路线编码" prop="routeCode" required>
            <el-input v-model="formData.routeCode" placeholder="请输入路线编码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属行政区划代码" prop="areaCode" required>
            <el-input v-model="formData.areaCode" placeholder="请输入所属行政区划代码" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="所属行政区划名称" prop="areaName" required>
            <el-input v-model="formData.areaName" placeholder="请输入所属行政区划名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="路线状态" prop="routeStatus" required>
            <el-radio-group v-model="formData.routeStatus">
              <el-radio :label="true">启用</el-radio>
              <el-radio :label="false">禁用</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>

      <el-divider content-position="left">起点信息</el-divider>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="起点名称" prop="startPtName" required>
            <el-input v-model="formData.startPtName" placeholder="请输入起点名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="起点坐标X" prop="startPtX" required>
            <el-input v-model="formData.startPtX" placeholder="请输入起点坐标X" type="number" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="起点坐标Y" prop="startPtY" required>
            <el-input v-model="formData.startPtY" placeholder="请输入起点坐标Y" type="number" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-divider content-position="left">终点信息</el-divider>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="终点名称" prop="endPtName" required>
            <el-input v-model="formData.endPtName" placeholder="请输入终点名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="终点坐标X" prop="endPtX" required>
            <el-input v-model="formData.endPtX" placeholder="请输入终点坐标X" type="number" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="终点坐标Y" prop="endPtY" required>
            <el-input v-model="formData.endPtY" placeholder="请输入终点坐标Y" type="number" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-divider content-position="left">其他信息</el-divider>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="途经网格IDs" prop="passGridIds">
            <el-input
              v-model="formData.passGridIds"
              placeholder="请输入途经网格IDs，多个用逗号分隔"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="途经网格名称" prop="passGridNames">
            <el-input
              v-model="formData.passGridNames"
              placeholder="请输入途经网格名称，多个用逗号分隔"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="预计时长" prop="estimateEndure">
            <el-input
              v-model="formData.estimateEndure"
              placeholder="请输入预计时长"
              type="number"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="巡查频次" prop="inspectFrequency">
            <el-input v-model="formData.inspectFrequency" placeholder="请输入巡查频次" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="负责人员ID" prop="personId">
            <el-input v-model="formData.personId" placeholder="请输入负责人员ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="负责人员姓名" prop="personName">
            <el-input v-model="formData.personName" placeholder="请输入负责人员姓名" />
          </el-form-item>
        </el-col>
      </el-row>

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
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import {
  InspectRouteInfoApi,
  InspectRouteInfoVO
} from '@/api/dataHub/supervisionInspection/patrolResource/routeManage'

/** 巡查巡检路线信息 表单 */
defineOptions({ name: 'InspectRouteInfoForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref<Partial<InspectRouteInfoVO>>({
  id: undefined,
  routeId: undefined,
  routeName: undefined,
  routeCode: undefined,
  areaCode: undefined,
  areaName: undefined,
  startPtName: undefined,
  startPtX: undefined,
  startPtY: undefined,
  endPtName: undefined,
  endPtX: undefined,
  endPtY: undefined,
  passGridIds: undefined,
  passGridNames: undefined,
  estimateEndure: undefined,
  inspectFrequency: undefined,
  personId: undefined,
  personName: undefined,
  routeStatus: true,
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
  routeId: [{ required: true, message: '请输入路线ID', trigger: 'blur' }],
  routeName: [{ required: true, message: '请输入路线名称', trigger: 'blur' }],
  routeCode: [{ required: true, message: '请输入路线编码', trigger: 'blur' }],
  areaCode: [{ required: true, message: '请输入所属行政区划代码', trigger: 'blur' }],
  areaName: [{ required: true, message: '请输入所属行政区划名称', trigger: 'blur' }],
  startPtName: [{ required: true, message: '请输入起点名称', trigger: 'blur' }],
  startPtX: [{ required: true, message: '请输入起点坐标X', trigger: 'blur' }],
  startPtY: [{ required: true, message: '请输入起点坐标Y', trigger: 'blur' }],
  endPtName: [{ required: true, message: '请输入终点名称', trigger: 'blur' }],
  endPtX: [{ required: true, message: '请输入终点坐标X', trigger: 'blur' }],
  endPtY: [{ required: true, message: '请输入终点坐标Y', trigger: 'blur' }]
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
      const data = await InspectRouteInfoApi.getInspectRouteInfo(id)
      formData.value = { ...data }
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
    const data = formData.value as unknown as InspectRouteInfoVO
    if (formType.value === 'create') {
      await InspectRouteInfoApi.createInspectRouteInfo(data)
      message.success(t('common.createSuccess'))
    } else {
      await InspectRouteInfoApi.updateInspectRouteInfo(data)
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
    routeId: undefined,
    routeName: undefined,
    routeCode: undefined,
    areaCode: undefined,
    areaName: undefined,
    startPtName: undefined,
    startPtX: undefined,
    startPtY: undefined,
    endPtName: undefined,
    endPtX: undefined,
    endPtY: undefined,
    passGridIds: undefined,
    passGridNames: undefined,
    estimateEndure: undefined,
    inspectFrequency: undefined,
    personId: undefined,
    personName: undefined,
    routeStatus: true,
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

::v-deep .el-divider--horizontal {
  margin: 15px 0;
}

::v-deep .el-form-item {
  margin-bottom: 15px;
}
</style>
