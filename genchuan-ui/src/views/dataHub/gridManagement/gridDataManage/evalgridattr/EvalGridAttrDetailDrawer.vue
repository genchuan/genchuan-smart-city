<template>
  <el-drawer
    v-model="visible"
    :size="isFullscreen ? '100%' : '75%'"
    title="评价网格属性详情"
    :destroy-on-close="true"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">评价网格详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="p-4 grid grid-cols-2 gap-5">
      <el-card shadow="hover">
        <div class="section-header bg-blue-100 text-blue-700">基础信息</div>
        <div class="mt-3 space-y-2">
          <p><b>编码：</b>{{ record.evalGridCode }}</p>
          <p><b>面积：</b>{{ record.area }} m²</p>
          <p><b>网格类型：</b>{{ record.gridType }}</p>
          <p><b>初始时间：</b>{{ formatDate(record.initTime) || '—' }}</p>
          <p><b>终止时间：</b>
            <span :class="record.endTime ? 'text-red-600' : 'text-green-600'">
              {{ record.endTime || '-' }}
            </span>
          </p>
        </div>
      </el-card>

      <el-card shadow="hover">
        <div class="section-header bg-purple-100 text-purple-700">关联指标</div>
        <div class="mt-3 space-y-2">
          <p><b>指标ID：</b>{{ record.idxId || '-' }}</p>
          <p><b>指标名称：</b>{{ record.idxName || '-' }}</p>
        </div>
      </el-card>

      <el-card shadow="hover" class="col-span-2">
        <div class="section-header bg-yellow-100 text-yellow-700">备注</div>
        <p class="mt-2">{{ record.remark || '暂无备注' }}</p>
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
const formatDate = (val: any) => (val ? new Date(val).toLocaleDateString() : '—')
const emit = defineEmits(['update:modelValue'])
const visible = computed({
  get: () => props.modelValue,
  set: (v) => emit('update:modelValue', v),
})
const isFullscreen = ref(false)
</script>

<style scoped>
.section-header {
  font-weight: 600;
  padding: 6px 10px;
  border-radius: 6px;
  display: inline-block;
}
</style>
