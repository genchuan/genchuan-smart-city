<template>
  <el-dialog
    v-model="visible"
    title="批量导入单元网格"
    width="600px"
    destroy-on-close
  >
    <el-form :model="form" label-width="120px" :rules="rules" ref="formRef">
      <el-form-item label="管理网格名称" prop="mngGridName">
        <el-input v-model="form.mngGridName" placeholder="请输入管理网格名称" />
      </el-form-item>

      <el-form-item label="所属乡镇ID" prop="townStreetId">
        <el-input v-model="form.townStreetId" placeholder="请输入所属乡镇ID" />
      </el-form-item>

      <el-form-item label="单元网格ID列表" prop="unitGridIds">
        <el-select
          v-model="form.unitGridIds"
          multiple
          filterable
          placeholder="请选择单元网格"
          class="w-full"
        >
          <el-option
            v-for="unit in mockUnitList"
            :key="unit.id"
            :label="unit.name"
            :value="unit.id"
          />
        </el-select>
        <!-- 实际中你可以改成接口加载单元网格列表 -->
      </el-form-item>

      <el-form-item label="网格员ID">
        <el-input v-model="form.staffId" placeholder="请输入网格员ID（可选）" />
      </el-form-item>

      <el-form-item label="备注">
        <el-input
          type="textarea"
          v-model="form.remark"
          placeholder="可填写备注"
        />
      </el-form-item>

      <el-divider />

      <!-- 校验结果展示 -->
      <div v-if="validateResult" class="p-2 bg-gray-50 rounded text-sm">
        <div>校验结果：{{ validateResult.message }}</div>
        <div>单元数：{{ validateResult.unitGridCount }}</div>
        <div>面积：{{ validateResult.calculatedArea }} m²</div>
        <div>跨社区：{{ validateResult.crossCommunity ? '是' : '否' }}</div>
        <div>相邻性：{{ validateResult.adjacent ? '是' : '否' }}</div>
      </div>

      <template #footer>
        <div class="flex justify-end space-x-2">
          <el-button @click="visible = false">取消</el-button>
          <el-button
            type="primary"
            :loading="validating"
            @click="handleValidate"
          >
            校验
          </el-button>
          <el-button
            type="success"
            :disabled="!validateResult || !validateResult.valid"
            :loading="importing"
            @click="handleImport"
          >
            导入
          </el-button>
        </div>
      </template>
    </el-form>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import { MngGridDivApi } from '@/api/dataHub/gridManagement/divideConfig/mnggriddiv'

const visible = ref(false)
const formRef = ref()
const form = ref({
  mngGridName: '',
  townStreetId: '',
  unitGridIds: [] as string[],
  staffId: '',
  remark: ''
})

const rules = {
  mngGridName: [{ required: true, message: '请输入网格名称', trigger: 'blur' }],
  townStreetId: [{ required: true, message: '请输入所属乡镇ID', trigger: 'blur' }],
  unitGridIds: [{ required: true, message: '请选择单元网格', trigger: 'change' }]
}

const validateResult = ref<any>(null)
const validating = ref(false)
const importing = ref(false)

// 模拟单元网格下拉数据（实际应调用接口加载）
const mockUnitList = [
  { id: 'unit_001', name: '单元网格001' },
  { id: 'unit_002', name: '单元网格002' },
  { id: 'unit_003', name: '单元网格003' }
]

// 打开弹窗方法
const open = () => {
  resetForm()
  visible.value = true
}

// 重置表单
const resetForm = () => {
  form.value = {
    mngGridName: '',
    townStreetId: '',
    unitGridIds: [],
    staffId: '',
    remark: ''
  }
  validateResult.value = null
}

// 校验单元网格是否可合并
const handleValidate = async () => {
  await formRef.value.validate()
  validating.value = true
  try {
    const res = await MngGridDivApi.validateUnitGrids({
      unitGridIds: form.value.unitGridIds,
      townStreetId: form.value.townStreetId
    })
    if (res.code === 0) {
      validateResult.value = res.data
      if (res.data.valid) {
        ElMessage.success('校验通过，可进行导入')
      } else {
        ElMessage.warning(res.data.message || '校验未通过')
      }
    } else {
      ElMessage.error('校验失败')
    }
  } finally {
    validating.value = false
  }
}

// 导入接口调用
const handleImport = async () => {
  importing.value = true
  try {
    const res = await MngGridDivApi.importUnitGrids(form.value)
    if (res.code === 0) {
      ElMessage.success('批量导入成功')
      visible.value = false
      emit('refresh')
    } else {
      ElMessage.error(res.msg || '导入失败')
    }
  } finally {
    importing.value = false
  }
}

// 向父组件发事件
const emit = defineEmits(['refresh'])

defineExpose({ open })
</script>

<style scoped>
.bg-gray-50 {
  background-color: #f9fafb;
}
</style>
