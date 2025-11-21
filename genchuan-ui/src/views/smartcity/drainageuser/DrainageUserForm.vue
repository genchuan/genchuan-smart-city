<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="150px"
      v-loading="formLoading"
    >
      <el-form-item label="统一社会信用代码" prop="creditCode">
        <el-input v-model="formData.creditCode" placeholder="请输入统一社会信用代码" />
      </el-form-item>
      <el-form-item label="排水户名称" prop="userName">
        <el-input v-model="formData.userName" placeholder="请输入排水户名称" />
      </el-form-item>
      <el-form-item label="行业类别" prop="industryType">
        <el-select v-model="formData.industryType" placeholder="请选择行业类别">
          <el-option
            v-for="dict in getStrDictOptions(DICT_TYPE.SM_INDUSTRY_CATEGORY)"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="排水户分类" prop="userType">
        <el-select v-model="formData.userType" placeholder="请选择排水户分类">
          <el-option
            v-for="dict in getStrDictOptions(DICT_TYPE.SM_DRAINAGE_USER)"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="月均用水量（吨）" prop="waterUsage">
        <el-input v-model="formData.waterUsage" placeholder="请输入月均用水量（吨）" />
      </el-form-item>
      <el-form-item label="排水管网接入点坐标" prop="drainagePoint">
        <el-input v-model="formData.drainagePoint" placeholder="请输入排水管网接入点坐标" />
      </el-form-item>
      <el-form-item label="预处理设施清单" prop="preTreatment">
        <el-input v-model="formData.preTreatment" type="textarea" placeholder="请输入预处理设施清单" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { getStrDictOptions, DICT_TYPE } from '@/utils/dict'
import { DrainageUserApi, DrainageUserVO } from '@/api/smartcity/drainageuser'

/** 排水户信息 表单 */
defineOptions({ name: 'DrainageUserForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  creditCode: undefined,
  userName: undefined,
  industryType: undefined,
  userType: undefined,
  waterUsage: undefined,
  drainagePoint: undefined,
  preTreatment: undefined,
})
const formRules = reactive({
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
      formData.value = await DrainageUserApi.getDrainageUser(id)
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
    const data = formData.value as unknown as DrainageUserVO
    if (formType.value === 'create') {
      await DrainageUserApi.createDrainageUser(data)
      message.success(t('common.createSuccess'))
    } else {
      await DrainageUserApi.updateDrainageUser(data)
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
    creditCode: undefined,
    userName: undefined,
    industryType: undefined,
    userType: undefined,
    waterUsage: undefined,
    drainagePoint: undefined,
    preTreatment: undefined,
  }
  formRef.value?.resetFields()
}
</script>
