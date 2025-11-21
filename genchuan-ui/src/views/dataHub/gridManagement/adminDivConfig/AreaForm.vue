<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form ref="formRef" :model="formData" :rules="formRules" label-width="150px" v-loading="formLoading">
      <!-- 新增时允许录入全部字段；编辑时只允许修改 invalidTime / remark（其它字段只读） -->
      <el-form-item label="上级行政区划ID" prop="parentId">
        <el-input v-model="formData.parentId" :disabled="isUpdate" placeholder="请输入上级行政区划ID（0表示根节点）" />
      </el-form-item>

      <el-form-item label="行政区划代码" prop="fullCode">
        <el-input v-model="formData.fullCode" :disabled="isUpdate" placeholder="请输入完整行政区划代码（12位）" />
      </el-form-item>

      <el-form-item label="短代码" prop="shortCode">
        <el-input v-model="formData.shortCode" :disabled="isUpdate" placeholder="请输入短代码（6/3位）" />
      </el-form-item>

      <el-form-item label="行政区划名称" prop="name">
        <el-input v-model="formData.name" :disabled="isUpdate" placeholder="请输入行政区划名称" />
      </el-form-item>

      <el-form-item label="层级" prop="level">
        <el-input v-model="formData.level" :disabled="isUpdate" placeholder="请输入层级（1-5）" />
      </el-form-item>

      <el-form-item label="类型" prop="areaType">
        <el-input v-model="formData.areaType" :disabled="isUpdate" placeholder="请输入类型（街道/镇/乡/社区/村）" />
      </el-form-item>

      <el-form-item label="生效时间" prop="effectiveTime">
        <el-date-picker v-model="formData.effectiveTime" type="datetime" value-format="YYYY-MM-DD HH:mm:ss" :disabled="isUpdate" />
      </el-form-item>

      <el-form-item label="失效时间" prop="invalidTime">
        <el-date-picker v-model="formData.invalidTime" type="datetime" value-format="YYYY-MM-DD HH:mm:ss" placeholder="选择失效时间" />
      </el-form-item>

      <el-form-item label="社区边界坐标" prop="boundary">
        <el-input v-model="formData.boundary" :disabled="isUpdate" placeholder="请输入社区边界坐标（2000坐标系）" />
      </el-form-item>

      <el-form-item label="备注" prop="remark">
        <el-input v-model="formData.remark" placeholder="请输入备注" />
      </el-form-item>
    </el-form>

    <template #footer>
      <el-button :disabled="formLoading" @click="submitForm" type="primary">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>

<script lang="ts" setup>
import { AreaApi,AreaVO } from '@/api/dataHub/gridManagement/adminDivConfig'

const emits = defineEmits(['success'])
const dialogVisible = ref(false)
const dialogTitle = ref('')
const formRef = ref()
const formLoading = ref(false)
const formType = ref<'create' | 'update'>('create')
const isUpdate = computed(() => formType.value === 'update')

const formData = reactive<any>({
  id: undefined,
  parentId: undefined,
  fullCode: undefined,
  shortCode: undefined,
  name: undefined,
  level: undefined,
  areaType: undefined,
  effectiveTime: undefined,
  invalidTime: undefined,
  boundary: undefined,
  remark: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
})

const formRules = reactive({
  parentId: [{ required: true, message: '上级行政区划ID不能为空', trigger: 'blur' }],
  fullCode: [{ required: true, message: '完整代码不能为空', trigger: 'blur' }],
  shortCode: [{ required: true, message: '短代码不能为空', trigger: 'blur' }],
  name: [{ required: true, message: '名称不能为空', trigger: 'blur' }],
  level: [{ required: true, message: '层级不能为空', trigger: 'blur' }],
})

/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  dialogVisible.value = true
  formType.value = type as any
  dialogTitle.value = type === 'create' ? '新增行政区划' : '修改行政区划（仅可修改失效时间/备注）'
  // reset
  Object.assign(formData, {
    id: undefined,
    parentId: undefined,
    fullCode: undefined,
    shortCode: undefined,
    name: undefined,
    level: undefined,
    areaType: undefined,
    effectiveTime: undefined,
    invalidTime: undefined,
    boundary: undefined,
    remark: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined,
  })
  if (id) {
    formLoading.value = true
    try {
      const data = await AreaApi.getArea(id)
      // 将后端结构赋值（注意后端可能返回时间戳或字符串）
      Object.assign(formData, data)
    } finally {
      formLoading.value = false
    }
  }
}
defineExpose({ open })

/** 提交表单 */
const submitForm = async () => {
  // 校验：create 模式需校验全部，update 模式只校验 invalidTime 可选、remark 可选
  try {
    if (formType.value === 'create') {
      await formRef.value.validate()
    } // update 不强制校验
  } catch (e) {
    return
  }
  formLoading.value = true
  try {
    if (formType.value === 'create') {
      await AreaApi.createArea(formData as AreaVO)
      ElMessage.success('新增成功')
    } else {
      // update: 仅提交 id, invalidTime, remark（后端应支持部分更新）
      await AreaApi.updateArea({
        id: formData.id,
        invalidTime: formData.invalidTime,
        remark: formData.remark,
      } as any)
      ElMessage.success('修改成功')
    }
    emits('success')
    dialogVisible.value = false
  } catch (e) {
    ElMessage.error('保存失败')
  } finally {
    formLoading.value = false
  }
}
</script>
