<template>
  <el-drawer
    v-model="drawerVisible"
    title="评价网格详情"
    :size="isFullscreen ? '100%' : '80%'"
    direction="rtl"
    destroy-on-close
    @close="emit('update:visible', false)"
  >
    <template #header>
      <div class="flex justify-between items-center w-full">
        <span class="font-bold text-lg">评价网格详情</span>
        <el-button
          type="text"
          @click="toggleFullscreen"
          class="text-gray-500 hover:text-blue-500"
        >
          <el-icon><FullScreen /></el-icon>
          <span class="ml-1">{{ isFullscreen ? '退出全屏' : '全屏' }}</span>
        </el-button>
      </div>
    </template>

    <div v-if="loading" class="flex justify-center py-10">
      <el-icon><Loading /></el-icon>
      <span class="ml-2">加载中...</span>
    </div>

    <div v-else>
      <el-descriptions :column="2" border>
        <el-descriptions-item label="评价网格ID">
          {{ detail.evalGridId ?? '-' }}
        </el-descriptions-item>
        <el-descriptions-item label="评价网格名称">
          {{ detail.evalGridName ?? '-' }}
        </el-descriptions-item>
        <el-descriptions-item label="网格类型">
          {{ detail.gridType ?? '-' }}
        </el-descriptions-item>
        <el-descriptions-item label="面积(㎡)">
          {{ detail.area ?? '-' }}
        </el-descriptions-item>
        <el-descriptions-item label="划分时间">
          {{ formatDate(detail.divTime) }}
        </el-descriptions-item>
        <el-descriptions-item label="更新时间">
          {{ formatDate(detail.updateTime) }}
        </el-descriptions-item>
        <el-descriptions-item label="备注">
          {{ detail.remark || '-' }}
        </el-descriptions-item>
      </el-descriptions>

      <el-divider />

      <el-card shadow="hover" class="mt-4">
        <template #header>
          <div class="font-bold">地图展示</div>
        </template>
        <EvalGridMap :includedMgIds="detail.includedMgIds ?? ''" />
      </el-card>
    </div>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="emit('update:visible', false)">关闭</el-button>
      </span>
    </template>
  </el-drawer>
</template>

<script lang="ts">
import { defineComponent, ref, watch } from 'vue'
import { EvalGridDivApi, EvalGridDivVO } from '@/api/dataHub/gridManagement/divideConfig/evalgriddiv'
import EvalGridMap from './EvalGridMap.vue'
import { ElMessage } from 'element-plus'
import { Loading, FullScreen } from '@element-plus/icons-vue'

export default defineComponent({
  name: 'EvalGridDetail',
  components: { EvalGridMap, Loading, FullScreen },
  props: {
    visible: { type: Boolean, required: true },
    id: { type: Number, required: false }
  },
  emits: ['update:visible'],
  setup(props, { emit }) {
    const detail = ref<Partial<EvalGridDivVO>>({})
    const loading = ref(false)
    const isFullscreen = ref(false)
    const drawerVisible = ref(props.visible)

    const toggleFullscreen = () => {
      isFullscreen.value = !isFullscreen.value
    }

    const loadDetail = async () => {
      if (props.id === undefined || props.id === null) {
        detail.value = {}
        return
      }
      loading.value = true
      try {
        const res = await EvalGridDivApi.getEvalGridDetail(props.id)
        detail.value = res
      } catch (err) {
        ElMessage.error('加载详情失败')
      } finally {
        loading.value = false
      }
    }

    const formatDate = (val?: string | Date | null): string => {
      if (val === undefined || val === null || val === '') return '-'
      const date = new Date(val as string | Date)
      if (isNaN(date.getTime())) return '-'
      return date.toLocaleString()
    }

    // 父 -> 子
    watch(() => props.visible, val => {
      drawerVisible.value = val
    })

    // 子 -> 父（确保 v-model 双向同步）
    watch(drawerVisible, (val) => {
      emit('update:visible', val)
    })

    // 当 id 修改或首次渲染时加载详情
    watch(() => props.id, loadDetail, { immediate: true })

    return {
      detail,
      loading,
      isFullscreen,
      drawerVisible,
      toggleFullscreen,
      formatDate,
      emit
    }
  }
})
</script>

<style scoped>
.dialog-footer {
  text-align: right;
}
</style>
