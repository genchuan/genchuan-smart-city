<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="600px">
    <el-form ref="formRef" :model="formData" label-width="100px" v-loading="formLoading">
      <!-- 基础信息 -->
      <el-form-item label="评价网格编码" prop="evalGridCode">
        <el-input v-model="formData.evalGridCode" disabled />
      </el-form-item>

      <el-form-item label="面积(m²)" prop="area">
        <el-input-number v-model="formData.area" disabled :min="0" />
      </el-form-item>

      <el-form-item label="网格类型" prop="gridType">
        <el-input v-model="formData.gridType" disabled />
      </el-form-item>

      <el-form-item label="所含管理网格列表" prop="mgGridList">
        <el-input v-model="formData.mgGridList" disabled />
      </el-form-item>

      <!-- 指标选择 -->
      <el-form-item label="关联评价指标" prop="idxId">
        <el-select v-model="formData.idxId" placeholder="请选择指标" filterable @change="onIdxChange">
          <el-option
            v-for="item in filteredIdxList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="指标名称">
        <el-input v-model="formData.idxName" disabled />
      </el-form-item>

      <!-- 终止时间 -->
      <el-form-item label="终止时间" prop="endTime">
        <el-date-picker
          v-model="formData.endTime"
          type="date"
          placeholder="选择终止时间"
          clearable
        />
      </el-form-item>

      <!-- 备注 -->
      <el-form-item label="备注" prop="remark">
        <el-input v-model="formData.remark" placeholder="请输入备注" />
      </el-form-item>
    </el-form>

    <template #footer>
      <el-button type="primary" @click="submitForm" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
import { ref, reactive, computed } from 'vue'
import { EvalGridAttrApi, EvalGridAttrVO } from '@/api/dataHub/gridManagement/gridDataManage/evalgridattr'

// 模拟指标数据
const idxList = [
  { id: 'A1', name: '指标A1', type: 'A' },
  { id: 'A2', name: '指标A2', type: 'A' },
  { id: 'B1', name: '指标B1', type: 'B' },
  { id: 'C1', name: '指标C1', type: 'C' },
]

const dialogVisible = ref(false)
const dialogTitle = ref('评价网格属性编辑')
const formLoading = ref(false)

// 编辑表单数据
const formData = reactive<EvalGridAttrVO>({
  id: 0,
  evalAttrId: '',
  evalGridId: '',
  evalGridCode: '',
  area: 0,
  gridType: '',
  initTime: new Date(),
  endTime: new Date(),
  mgGridList: '',
  idxId: '',
  idxName: '',
  remark: '',
  extCat1: '',
  extCat2: '',
  extCommon1: '',
  extCommon2: '',
})

const formRef = ref<any>()

// 根据网格类型过滤指标
const filteredIdxList = computed(() => idxList.filter((i) => i.type === formData.gridType))

// 打开编辑表单，只加载已有数据
const open = async (id: number) => {
  dialogVisible.value = true
  formLoading.value = true
  try {
    const data = await EvalGridAttrApi.getEvalGridAttr(id)
    Object.assign(formData, {
      ...data,
      endTime: data.endTime ? new Date(data.endTime) : new Date(),
    })
  } finally {
    formLoading.value = false
  }
}

// 指标选择变化
const onIdxChange = (val: string) => {
  const selected = idxList.find((i) => i.id === val)
  formData.idxName = selected?.name ?? ''
}

// 提交表单
const submitForm = async () => {
  await formRef.value.validate()
  formLoading.value = true
  try {
    await EvalGridAttrApi.updateEvalGridAttr(formData)
    dialogVisible.value = false
  } finally {
    formLoading.value = false
  }
}

defineExpose({ open })
</script>
