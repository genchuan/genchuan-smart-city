<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="200px"
      v-loading="formLoading"
    >
      <el-form-item label="单位ID" prop="unitId">
        <el-input v-model="formData.unitId" placeholder="请输入单位ID" />
      </el-form-item>
      <el-form-item label="单位编码" prop="unitCode">
        <el-input v-model="formData.unitCode" placeholder="请输入单位编码" />
      </el-form-item>
      <el-form-item label="单位名称" prop="unitName">
        <el-input v-model="formData.unitName" placeholder="请输入单位名称" />
      </el-form-item>
      <el-form-item label="单位类型" prop="unitType">
        <el-select v-model="formData.unitType" placeholder="请选择单位类型">
          <el-option label="政府部门" value="1" />
          <el-option label="企业" value="2" />
          <el-option label="社会组织" value="3" />
        </el-select>
      </el-form-item>
      <el-form-item label="行政区划代码" prop="regionCode">
        <el-input v-model="formData.regionCode" placeholder="请输入行政区划代码" />
      </el-form-item>
      <el-form-item label="行政区划名称" prop="regionName">
        <el-input v-model="formData.regionName" placeholder="请输入行政区划名称" />
      </el-form-item>
      <el-form-item label="负责人姓名" prop="leaderName">
        <el-input v-model="formData.leaderName" placeholder="请输入负责人姓名" />
      </el-form-item>
      <el-form-item label="负责人联系方式" prop="leaderContact">
        <el-input v-model="formData.leaderContact" placeholder="请输入负责人联系方式" />
      </el-form-item>
      <el-form-item label="单位详细地址" prop="contactAddress">
        <el-input v-model="formData.contactAddress" placeholder="请输入单位详细地址" />
      </el-form-item>
      <el-form-item label="联动范围" prop="coopScope">
        <el-input v-model="formData.coopScope" placeholder="请输入联动范围" />
      </el-form-item>
      <el-form-item label="单位状态" prop="unitStatus">
        <el-radio-group v-model="formData.unitStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="创建人账号" prop="createUser">
        <el-input v-model="formData.createUser" placeholder="请输入创建人账号" />
      </el-form-item>
      <el-form-item label="更新人账号" prop="updateUser">
        <el-input v-model="formData.updateUser" placeholder="请输入更新人账号" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { CoopUnitInfoApi, CoopUnitInfoVO } from '@/api/dataHub/commandAndCoordination/coopunitinfo'

/** 联动单位信息 表单 */
defineOptions({ name: 'CoopUnitInfoForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  unitId: undefined,
  unitCode: undefined,
  unitName: undefined,
  unitType: undefined,
  regionCode: undefined,
  regionName: undefined,
  leaderName: undefined,
  leaderContact: undefined,
  contactAddress: undefined,
  coopScope: undefined,
  unitStatus: undefined,
  createUser: undefined,
  updateUser: undefined,
})
const formRules = reactive({
  unitId: [{ required: true, message: '单位ID不能为空', trigger: 'blur' }],
  unitCode: [{ required: true, message: '单位编码不能为空', trigger: 'blur' }],
  unitName: [{ required: true, message: '单位名称不能为空', trigger: 'blur' }],
  unitType: [{ required: true, message: '单位类型不能为空', trigger: 'change' }],
  regionCode: [{ required: true, message: '行政区划代码不能为空', trigger: 'blur' }],
  regionName: [{ required: true, message: '行政区划名称不能为空', trigger: 'blur' }],
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
      formData.value = await CoopUnitInfoApi.getCoopUnitInfo(id)
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
    const data = formData.value as unknown as CoopUnitInfoVO
    if (formType.value === 'create') {
      await CoopUnitInfoApi.createCoopUnitInfo(data)
      message.success(t('common.createSuccess'))
    } else {
      await CoopUnitInfoApi.updateCoopUnitInfo(data)
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
    unitId: undefined,
    unitCode: undefined,
    unitName: undefined,
    unitType: undefined,
    regionCode: undefined,
    regionName: undefined,
    leaderName: undefined,
    leaderContact: undefined,
    contactAddress: undefined,
    coopScope: undefined,
    unitStatus: undefined,
    createUser: undefined,
    updateUser: undefined,
  }
  formRef.value?.resetFields()
}
</script>
