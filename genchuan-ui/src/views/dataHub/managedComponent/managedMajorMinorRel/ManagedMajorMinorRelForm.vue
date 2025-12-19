<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="关联ID，唯一编码，采用UUID" prop="relId">
        <el-input v-model="formData.relId" placeholder="请输入关联ID，唯一编码，采用UUID" />
      </el-form-item>
      <el-form-item label="关联的大类ID" prop="majorId">
        <el-input v-model="formData.majorId" placeholder="请输入关联的大类ID" />
      </el-form-item>
      <el-form-item label="关联的小类ID" prop="minorId">
        <el-input v-model="formData.minorId" placeholder="请输入关联的小类ID" />
      </el-form-item>
      <el-form-item label="关联状态：0-无效/1-有效" prop="relStatus">
        <el-radio-group v-model="formData.relStatus">
          <el-radio value="1">请选择字典生成</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="关联时间，格式：yyyy-MM-dd HH:mm:ss" prop="relTime">
        <el-date-picker
          v-model="formData.relTime"
          type="date"
          value-format="x"
          placeholder="选择关联时间，格式：yyyy-MM-dd HH:mm:ss"
        />
      </el-form-item>
      <el-form-item label="解除关联时间，格式：yyyy-MM-dd HH:mm:ss" prop="unrelTime">
        <el-date-picker
          v-model="formData.unrelTime"
          type="date"
          value-format="x"
          placeholder="选择解除关联时间，格式：yyyy-MM-dd HH:mm:ss"
        />
      </el-form-item>
      <el-form-item label="操作人ID" prop="operateUser">
        <el-input v-model="formData.operateUser" placeholder="请输入操作人ID" />
      </el-form-item>
      <el-form-item label="备注信息" prop="remark">
        <el-input v-model="formData.remark" placeholder="请输入备注信息" />
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
import { ManagedMajorMinorRelApi, ManagedMajorMinorRelVO } from '@/api/dataHub/managedComponent/managedmajorminorrel'

/** 管理部件大类小类关联 表单 */
defineOptions({ name: 'ManagedMajorMinorRelForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  relId: undefined,
  majorId: undefined,
  minorId: undefined,
  relStatus: undefined,
  relTime: undefined,
  unrelTime: undefined,
  operateUser: undefined,
  remark: undefined,
  extField1: undefined,
  extField2: undefined,
  extField3: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined
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
      formData.value = await ManagedMajorMinorRelApi.getManagedMajorMinorRel(id)
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
    const data = formData.value as unknown as ManagedMajorMinorRelVO
    if (formType.value === 'create') {
      await ManagedMajorMinorRelApi.createManagedMajorMinorRel(data)
      message.success(t('common.createSuccess'))
    } else {
      await ManagedMajorMinorRelApi.updateManagedMajorMinorRel(data)
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
    relId: undefined,
    majorId: undefined,
    minorId: undefined,
    relStatus: undefined,
    relTime: undefined,
    unrelTime: undefined,
    operateUser: undefined,
    remark: undefined,
    extField1: undefined,
    extField2: undefined,
    extField3: undefined,
    createTimeSys: undefined,
    updateTimeSys: undefined
  }
  formRef.value?.resetFields()
}
</script>
