<template>
  <el-drawer
    v-model="visible"
    :size="isFullscreen ? '100%' : '75%'"
    title="单元网格属性详情"
    :destroy-on-close="true"
    class="unit-grid-detail-drawer"
    @closed="emit('closed')"
  >
    <!-- 自定义标题 -->
    <template #header>
      <div class="flex items-center justify-between w-full px-2">
        <span class="text-lg font-bold text-gray-800">单元网格属性详情</span>
        <el-button type="primary" link @click="toggleFullscreen">
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
          <p><b>网格编码：</b><span class="text-gray-800">{{ detail.unitGridCode }}</span></p>
          <p><b>面积：</b><span class="text-blue-700 font-semibold">{{ detail.area }} m²</span></p>
          <p><b>初始时间：</b><span>{{ formatDate(detail.initTime) }}</span></p>
          <p><b>终止时间：</b>
            <span :class="detail.endTime ? 'text-red-600' : 'text-green-600'">
              {{ detail.endTime ? formatDate(detail.endTime) : '-' }}
            </span>
          </p>
          <p>
            <b>状态：</b>
            <el-tag :type="detail.endTime ? 'danger' : 'success'">
              {{ detail.endTime ? '停用' : '在用' }}
            </el-tag>
          </p>
        </div>
      </el-card>

      <!-- 关联信息 -->
      <el-card shadow="hover" class="info-card">
        <div class="section-header bg-green-100 text-green-700">关联信息</div>
        <div class="mt-3 space-y-2">
          <p><b>关联网格数：</b><el-tag>{{ relatedGrids }}</el-tag></p>
          <p><b>关联部件数：</b><el-tag type="warning">{{ relatedParts }}</el-tag></p>
        </div>
      </el-card>

      <!-- 备注 -->
      <el-card shadow="hover" class="col-span-2 remark-card">
        <div class="section-header bg-yellow-100 text-yellow-700">备注（自动保存）</div>
        <el-input
          v-model="remark"
          type="textarea"
          :rows="3"
          placeholder="输入备注将自动保存"
          class="mt-3"
          @input="debouncedSave"
        />
        <p class="mt-2 text-gray-500 text-sm text-right">
          💡 修改内容后系统将自动保存
        </p>
      </el-card>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { debounce } from 'lodash-es'
import { UnitGridAttrApi, UnitGridAttrVO } from '@/api/dataHub/gridManagement/gridDataManage/unitgridattr'

defineOptions({ name: 'UnitGridAttrDetailDrawer' })
const message = useMessage()
const emit = defineEmits(['closed'])

const visible = ref(false)
const isFullscreen = ref(false)
const detail = ref<Partial<UnitGridAttrVO>>({})
const remark = ref('')
const relatedGrids = ref(3)
const relatedParts = ref(15)

const open = (row: UnitGridAttrVO) => {
  detail.value = row
  remark.value = row.remark || ''
  visible.value = true
}
defineExpose({ open })

const toggleFullscreen = () => {
  isFullscreen.value = !isFullscreen.value
}

const formatDate = (val: any) => (val ? new Date(val).toLocaleDateString() : '—')

// 防抖保存备注
const saveRemark = async () => {
  if (!detail.value.id) return
  try {
    await UnitGridAttrApi.updateUnitGridAttr({
      ...(detail.value as any),
      remark: remark.value
    })
    message.success('备注已保存')
  } catch (e) {
    message.error('保存失败')
  }
}
const debouncedSave = debounce(saveRemark, 1000)
</script>

<style scoped>
.unit-grid-detail-drawer {
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
