<template>
  <el-drawer
    v-model="visible"
    :size="isFullscreen ? '100%' : '75%'"
    :destroy-on-close="true"
    class="grid-style-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">网格标识码注记详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="p-4">
      <!-- Grid布局：两列 -->
      <div class="grid grid-cols-2 gap-6">
        <!-- 左侧：基本信息卡片 -->
        <div class="flex flex-col gap-4">
          <!-- 基础信息卡片 -->
          <el-card
            class="hover:shadow-lg transition-shadow duration-300"
            header="基础信息"
            :header-style="{ background: '#e8f4f8', color: '#2d3748', fontWeight: '600' }"
          >
            <div class="space-y-3 text-gray-700">
              <div class="flex justify-between">
                <span>主键ID：</span>
                <span class="text-blue-600 font-bold">{{ formData.id || '-' }}</span>
              </div>
              <div class="flex justify-between">
                <span>注记ID：</span>
                <span class="text-blue-600 font-bold">{{ formData.annotateId || '-' }}</span>
              </div>
              <div class="flex justify-between">
                <span>比例尺：</span>
                <span class="text-blue-600 font-bold">{{ formData.scale || '-' }}</span>
              </div>
              <div class="flex justify-between">
                <span>网格类型：</span>
                <span class="text-blue-600 font-bold">
                  {{ gridTypeMap[formData.gridType] || formData.gridType || '-' }}
                </span>
              </div>
            </div>
          </el-card>

          <!-- 样式信息卡片 -->
          <el-card
            class="hover:shadow-lg transition-shadow duration-300"
            header="样式配置"
            :header-style="{ background: '#f0f8fb', color: '#2d3748', fontWeight: '600' }"
          >
            <div class="space-y-3 text-gray-700">
              <div class="flex justify-between">
                <span>字高：</span>
                <span class="text-blue-600 font-bold">{{ formData.fontHeight || '-' }} mm</span>
              </div>
              <div class="flex justify-between">
                <span>字型：</span>
                <span class="text-blue-600 font-bold">{{ formData.fontType || '正等线体' }}</span>
              </div>
              <div class="flex justify-between">
                <span>颜色(CMYK)：</span>
                <span class="text-pink-600 font-bold">
                  C{{ formData.colorC || 0 }}% M{{ formData.colorM || 100 }}% Y{{
                    formData.colorY || 0
                  }}% K{{ formData.colorK || 0 }}%
                </span>
              </div>
            </div>
          </el-card>

          <!-- 其他信息卡片 -->
          <el-card
            class="hover:shadow-lg transition-shadow duration-300"
            header="其他信息"
            :header-style="{ background: '#f5fafe', color: '#2d3748', fontWeight: '600' }"
          >
            <div class="space-y-3 text-gray-700">
              <div class="flex justify-between">
                <span>创建人：</span>
                <span>{{ formData.createUserId || '-' }}</span>
              </div>
              <div class="flex justify-between">
                <span>创建时间：</span>
                <span>{{ formatDate(formData.createTime) }}</span>
              </div>
              <div class="flex flex-col">
                <span>备注：</span>
                <span class="mt-1 text-gray-600">{{ formData.remark || '-' }}</span>
              </div>
            </div>
          </el-card>
        </div>

        <!-- 右侧：地图预览 -->
        <el-card
          class="hover:shadow-lg transition-shadow duration-300 h-full"
          header="注记效果预览"
          :header-style="{ background: '#fef7fb', color: '#2d3748', fontWeight: '600' }"
        >
          <AnnotateMapPreview
            :style-data="{
              scale: formData.scale,
              fontHeight: formData.fontHeight,
              fontType: formData.fontType || '正等线体',
              color: `rgb(255,0,255)`, // 品红色RGB
              colorCMYK: `C${formData.colorC || 0}% M${formData.colorM || 100}% Y${formData.colorY || 0}% K${formData.colorK || 0}%`
            }"
          />
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { ref, watch } from 'vue'
import AnnotateMapPreview from './AnnotateMapPreview.vue'
import { GridCodeAnnotateVO } from '@/api/dataHub/gridManagement/gridDiagramManage/gridcodeannotate'

const visible = ref(false)
const isFullscreen = ref(false)
const formData = ref<GridCodeAnnotateVO>({})

// 网格类型映射
const gridTypeMap = {
  UNIT_GRID: '单元网格',
  MANAGE_GRID: '管理网格',
  EVAL_GRID: '评价网格'
}

// 时间格式化
const formatDate = (val: any) => (val ? new Date(val).toLocaleDateString() : '-')

// 打开抽屉并传递数据
const open = (row: GridCodeAnnotateVO) => {
  formData.value = { ...row }
  visible.value = true
}
defineExpose({ open })

watch(
  () => formData.value,
  () => {
    // 实时更新预览数据
  },
  { deep: true }
)
</script>

<style scoped>
.grid-style-drawer {
  overflow: auto;
}

/* 马克龙浅色配色 + 卡片hover效果 */
.el-card {
  border: none;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
}

.grid-cols-2 {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 24px;
}

.hover\:shadow-lg:hover {
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
}

.space-y-3 > div {
  padding: 4px 0;
}
</style>
