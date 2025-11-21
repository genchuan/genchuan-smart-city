<template>
  <el-drawer
    v-model="visible"
    :size="isFullscreen ? '100%' : '75%'"
    title="管理网格属性详情"
    :destroy-on-close="true"
    class="grid-detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">管理网格属性详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="p-4 grid grid-cols-2 gap-5">
      <!-- 基础信息 -->
      <el-card shadow="hover" class="info-card">
        <div class="section-header bg-blue-100 text-blue-700">基础信息</div>
        <div class="mt-3 space-y-2">
          <p><b>管理网格编码：</b><span class="text-gray-800">{{ record.mgGridCode }}</span></p>
          <p><b>面积：</b><span class="text-blue-700 font-semibold">{{ record.area }} m²</span></p>
          <p><b>初始时间：</b><span>{{ formatDate(record.initTime) }}</span></p>
          <p><b>终止时间：</b>
            <span :class="record.endTime ? 'text-red-600' : 'text-green-600'">
              {{ record.endTime || '在用中' }}
            </span>
          </p>
        </div>
      </el-card>

      <!-- 网格员信息 -->
      <el-card shadow="hover" class="info-card">
        <div class="section-header bg-green-100 text-green-700">网格员信息</div>
        <div class="mt-3 space-y-2">
          <p><b>姓名：</b><span class="text-gray-800">{{ record.gridUserName }}</span></p>
          <p><b>联系电话：</b><span class="text-gray-700">{{ mockContact(record.gridUserName) }}</span></p>
        </div>
      </el-card>

      <!-- 单元网格列表 -->
      <el-card shadow="hover" class="col-span-2 info-card">
        <div class="section-header bg-purple-100 text-purple-700">所含单元网格</div>
        <el-table :data="mockUnitList" stripe class="mt-3 rounded-md shadow-sm">
          <el-table-column label="单元网格编码" prop="code" align="center" />
          <el-table-column label="面积(m²)" prop="area" align="center" />
        </el-table>
      </el-card>

      <!-- 备注 -->
      <el-card shadow="hover" class="col-span-2 remark-card">
        <div class="section-header bg-yellow-100 text-yellow-700">备注</div>
        <p class="mt-3 text-gray-700 whitespace-pre-line leading-relaxed">
          {{ record.remark || '暂无备注' }}
        </p>
      </el-card>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'


const props = defineProps({
  modelValue: Boolean,
  record: { type: Object, required: true },
})
const emit = defineEmits(['update:modelValue'])
const visible = computed({
  get: () => props.modelValue,
  set: (v) => emit('update:modelValue', v),
})
const isFullscreen = ref(false)

const mockUnitList = [
  { code: 'UG001', area: 120.5 },
  { code: 'UG002', area: 98.3 },
  { code: 'UG003', area: 76.8 },
]

const mockContact = (name: string) => {
  const phone = {
    张三: '138-1111-2222',
    李四: '139-3333-4444',
    王五: '137-5555-6666',
  }
  return phone[name] || '未知'
}
const formatDate = (val: any) => (val ? new Date(val).toLocaleDateString() : '—')
</script>

<style scoped>
.grid-detail-drawer {
  background-color: #f9fafb;
}

.info-card {
  border-radius: 14px;
  transition: all 0.3s;
}

.info-card:hover {
  transform: translateY(-2px);
  box-shadow: var(--el-box-shadow-light);
}

.section-header {
  font-weight: 600;
  padding: 6px 10px;
  border-radius: 6px;
  display: inline-block;
}

.remark-card {
  background-color: #fffbea;
  border: 1px solid #fde68a;
}
</style>
