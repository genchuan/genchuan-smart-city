<template>
  <el-drawer
    v-model="visible"
    title="边界重叠检查"
    size="75%"
    :destroy-on-close="true"
  >
    <div class="p-4">
      <!-- 输入区域 -->
      <el-form :model="form" label-width="100px">
        <el-form-item label="边界坐标">
          <el-input
            v-model="form.boundaryCoords"
            type="textarea"
            placeholder="请输入坐标，格式：lng,lat;lng,lat;..."
          />
        </el-form-item>
        <el-form-item label="排除ID">
          <el-input
            v-model="form.excludeId"
            type="number"
            placeholder="可选，用于排除当前网格ID"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleCheckOverlap" :loading="loading">
            检查重叠
          </el-button>
          <el-button @click="handleReset">重置</el-button>
        </el-form-item>
      </el-form>

      <!-- 检查结果 -->
      <el-divider content-position="left">重叠网格列表</el-divider>

      <el-empty
        v-if="!loading && overlapList.length === 0 && hasChecked"
        description="未检测到重叠区域"
      />

      <el-table
        v-if="overlapList.length > 0"
        :data="overlapList"
        stripe
        border
        style="width: 100%"
        size="small"
      >
        <el-table-column prop="id" label="ID" width="100" />
        <el-table-column prop="unitGridId" label="单元网格ID" width="160" />
        <el-table-column prop="unitGridName" label="单元网格名称" min-width="180" />
        <el-table-column prop="commId" label="所属社区ID" width="120" />
        <el-table-column prop="area" label="面积(m²)" width="120" />
      </el-table>

      <el-alert
        v-if="errorMsg"
        type="error"
        show-icon
        :closable="false"
        class="mt-3"
        :title="errorMsg"
      />
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import { ElMessage } from 'element-plus'
import { UnitGridDivApi } from '@/api/dataHub/gridManagement/divideConfig/unitgriddiv'

// 控制抽屉显示
const visible = ref(false)

// 表单数据
const form = reactive({
  boundaryCoords: '',
  excludeId: undefined as number | undefined
})

// 检查结果列表
const overlapList = ref<any[]>([])

// 状态
const loading = ref(false)
const hasChecked = ref(false)
const errorMsg = ref('')

// 打开方法（供父组件调用）
const open = (coords?: string, excludeId?: number) => {
  visible.value = true
  if (coords) form.boundaryCoords = coords
  if (excludeId) form.excludeId = excludeId
}

// 重置表单
const handleReset = () => {
  form.boundaryCoords = ''
  form.excludeId = undefined
  overlapList.value = []
  hasChecked.value = false
  errorMsg.value = ''
}

// 执行重叠检查
const handleCheckOverlap = async () => {
  if (!form.boundaryCoords) {
    ElMessage.warning('请先输入边界坐标')
    return
  }

  try {
    loading.value = true
    errorMsg.value = ''
    const params = {
      boundaryCoords: form.boundaryCoords,
      excludeId: form.excludeId
    }

    const res = await UnitGridDivApi.checkOverlap(params)
    hasChecked.value = true

      overlapList.value = res || []
      if (res.length) {
        ElMessage.warning(`检测到 ${res.length} 个重叠网格`)
      } else {
        ElMessage.success('未检测到边界重叠')
      }

  } catch (e: any) {
    console.error('检查重叠失败：', e)
    errorMsg.value = e.message || '请求异常'
  } finally {
    loading.value = false
  }
}

// 暴露方法给父组件
defineExpose({ open })
</script>

<style scoped>
.p-4 {
  padding: 16px;
}
.mt-3 {
  margin-top: 12px;
}
</style>
