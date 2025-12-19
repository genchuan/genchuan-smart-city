<template>
  <el-drawer
    v-model="visible"
    :size="isFullscreen ? '100%' : size"
    title="图层详情"
    :destroy-on-close="true"
    class="grid-detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">图层详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="p-4 grid grid-cols-3 gap-4">
      <div
        class="col-span-1 p-4 rounded-lg shadow-sm"
        style="background: linear-gradient(135deg, #fef3f3, #fff)"
      >
        <h3 class="font-semibold mb-3">基础信息</h3>
        <div class="text-sm"><strong>图层ID：</strong>{{ data.layerId }}</div>
        <div class="text-sm"><strong>图层名称：</strong>{{ data.layerName }}</div>
        <div class="text-sm"><strong>网格类型：</strong>{{ data.gridType }}</div>
        <div class="text-sm"><strong>比例尺：</strong>{{ data.scale }}</div>
      </div>

      <div
        class="col-span-1 p-4 rounded-lg shadow-sm"
        style="background: linear-gradient(135deg, #f3fbff, #fff)"
      >
        <h3 class="font-semibold mb-3">样式配置</h3>
        <div class="text-sm"><strong>边界样式：</strong>{{ data.boundaryStyleId || '—' }}</div>
        <div class="text-sm"><strong>注记样式：</strong>{{ data.annotateStyleId || '—' }}</div>
        <div class="text-sm"><strong>透明度：</strong>{{ data.extCommon1 || '默认' }}</div>
      </div>

      <div
        class="col-span-1 p-4 rounded-lg shadow-sm"
        style="background: linear-gradient(135deg, #f7fff3, #fff)"
      >
        <h3 class="font-semibold mb-3">显示 & 顺序</h3>
        <div class="text-sm">
          <strong>显示状态：</strong>
          <el-tag type="success" v-if="data.displayStatus === '1'">显示</el-tag>
          <el-tag v-else type="info">隐藏</el-tag>
        </div>
        <div class="text-sm"><strong>图层顺序：</strong>{{ data.layerWo }}</div>
        <div class="text-sm"><strong>创建人：</strong>{{ data.createUserId }}</div>
      </div>

      <div class="col-span-3 mt-2 p-4 rounded-lg shadow-sm" style="background: #fff">
        <h3 class="font-semibold mb-3">备注</h3>
        <div class="text-sm text-gray-600">{{ data.remark || '暂无备注' }}</div>
      </div>
    </div>

    <template #footer>
      <div class="flex justify-between items-center w-full px-4">
        <div>
          <el-button type="primary" @click="openEdit">编辑</el-button>
          <el-button @click="emitSync">同步到 GIS（模拟）</el-button>
        </div>
        <div>
          <el-button @click="visible = false">关闭</el-button>
        </div>
      </div>
    </template>
  </el-drawer>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import { GridTopicLayerVO } from '@/api/dataHub/gridManagement/gridDiagramManage/gridtopiclayer'

defineOptions({ name: 'GridTopicLayerDrawer' })

const visible = ref(false)
const isFullscreen = ref(false)
const size = ref('75%') // ✅ 修复：定义默认尺寸

const data = reactive<Partial<GridTopicLayerVO>>({
  id: 0,
  layerWo: 10,
  displayStatus: '0'
})

const emit = defineEmits(['edit']) // ✅ 修复：用于父组件监听编辑事件

const open = (row: GridTopicLayerVO) => {
  Object.assign(data, row)
  visible.value = true
}
defineExpose({ open })

const openEdit = () => {
  emit('edit', data) // ✅ 修复：触发编辑事件
}

const emitSync = () => {
  console.log('同步至 GIS（模拟）：', data)
  ElMessage.success('已发送同步到 GIS（模拟）')
}
</script>
