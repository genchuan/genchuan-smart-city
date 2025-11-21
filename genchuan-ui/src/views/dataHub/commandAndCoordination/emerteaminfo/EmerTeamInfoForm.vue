<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="队伍ID" prop="teamId">
        <el-input v-model="formData.teamId" placeholder="请输入队伍ID" />
      </el-form-item>
      <el-form-item label="队伍编码" prop="teamCode">
        <el-input v-model="formData.teamCode" placeholder="请输入队伍编码" />
      </el-form-item>
      <el-form-item label="队伍名称" prop="teamName">
        <el-input v-model="formData.teamName" placeholder="请输入队伍名称" />
      </el-form-item>
      <el-form-item label="分类ID" prop="catId">
        <el-input v-model="formData.catId" placeholder="请输入分类ID" />
      </el-form-item>
      <el-form-item label="分类名称" prop="catName">
        <el-input v-model="formData.catName" placeholder="请输入分类名称" />
      </el-form-item>
      <el-form-item label="适用区域代码" prop="applyRegionCode">
        <el-input v-model="formData.applyRegionCode" placeholder="请输入适用区域代码" />
      </el-form-item>
      <el-form-item label="适用区域名称" prop="applyRegionName">
        <el-input v-model="formData.applyRegionName" placeholder="请输入适用区域名称" />
      </el-form-item>
      <el-form-item label="队伍人数" prop="teamMemberCount">
        <el-input v-model="formData.teamMemberCount" placeholder="请输入队伍人数" />
      </el-form-item>
      <el-form-item label="可用人数" prop="availableMemberCount">
        <el-input v-model="formData.availableMemberCount" placeholder="请输入可用人数" />
      </el-form-item>
      <el-form-item label="队伍状态" prop="teamStatus">
        <el-radio-group v-model="formData.teamStatus">
          <el-radio value="1">正常</el-radio>
          <el-radio value="0">异常</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="创建人" prop="createUser">
        <el-input v-model="formData.createUser" placeholder="请输入创建人" />
      </el-form-item>
      <el-form-item label="更新人" prop="updateUser">
        <el-input v-model="formData.updateUser" placeholder="请输入更新人" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { EmerTeamInfoApi, EmerTeamInfoVO } from '@/api/dataHub/commandAndCoordination/emerteaminfo'

/** 救援队伍信息 表单 */
defineOptions({ name: 'EmerTeamInfoForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  teamId: undefined,
  teamCode: undefined,
  teamName: undefined,
  catId: undefined,
  catName: undefined,
  applyRegionCode: undefined,
  applyRegionName: undefined,
  teamMemberCount: undefined,
  availableMemberCount: undefined,
  teamStatus: undefined,
  createUser: undefined,
  updateUser: undefined,
})
const formRules = reactive({
  teamId: [{ required: true, message: '队伍ID不能为空', trigger: 'blur' }],
  teamCode: [{ required: true, message: '队伍编码不能为空', trigger: 'blur' }],
  teamName: [{ required: true, message: '队伍名称不能为空', trigger: 'blur' }],
  catId: [{ required: true, message: '分类ID不能为空', trigger: 'blur' }],
  catName: [{ required: true, message: '分类名称不能为空', trigger: 'blur' }],
  applyRegionCode: [{ required: true, message: '适用区域代码不能为空', trigger: 'blur' }],
  applyRegionName: [{ required: true, message: '适用区域名称不能为空', trigger: 'blur' }],
  teamMemberCount: [{ required: true, message: '队伍人数不能为空', trigger: 'blur' }],
  availableMemberCount: [{ required: true, message: '可用人数不能为空', trigger: 'blur' }],
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
      formData.value = await EmerTeamInfoApi.getEmerTeamInfo(id)
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
    const data = formData.value as unknown as EmerTeamInfoVO
    if (formType.value === 'create') {
      await EmerTeamInfoApi.createEmerTeamInfo(data)
      message.success(t('common.createSuccess'))
    } else {
      await EmerTeamInfoApi.updateEmerTeamInfo(data)
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
    teamId: undefined,
    teamCode: undefined,
    teamName: undefined,
    catId: undefined,
    catName: undefined,
    applyRegionCode: undefined,
    applyRegionName: undefined,
    teamMemberCount: undefined,
    availableMemberCount: undefined,
    teamStatus: undefined,
    createUser: undefined,
    updateUser: undefined,
  }
  formRef.value?.resetFields()
}
</script>
