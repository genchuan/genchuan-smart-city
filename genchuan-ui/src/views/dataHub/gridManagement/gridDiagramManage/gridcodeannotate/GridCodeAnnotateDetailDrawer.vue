<template>
  <el-drawer
    v-model="visible"
    :size="isFullscreen ? '100%' : '75%'"
    :destroy-on-close="true"
    class="grid-style-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">网格边界样式配置</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="flex gap-4">
      <div class="w-1/2">
        <el-form ref="formRef" :model="formData" :rules="formRules" label-width="100px" class="p-4">
          <el-form-item label="比例尺" prop="scale">
            <el-select v-model="formData.scale" placeholder="选择比例尺">
              <el-option v-for="item in scaleList" :key="item.value" :label="item.label" :value="item.value" />
            </el-select>
          </el-form-item>

          <el-form-item label="网格类型" prop="gridType">
            <el-select v-model="formData.gridType" placeholder="选择网格类型">
              <el-option v-for="item in gridTypeList" :key="item.value" :label="item.label" :value="item.value" />
            </el-select>
          </el-form-item>

          <el-form-item label="线宽" prop="lineWidth">
            <el-slider v-model="formData.lineWidth" :min="1" :max="10" show-input />
          </el-form-item>

          <el-form-item label="颜色" prop="color">
            <el-color-picker v-model="formData.color" show-alpha />
          </el-form-item>

          <div class="text-center mt-6">
            <el-button type="primary" @click="handleSubmit">保存</el-button>
            <el-button @click="visible = false">取消</el-button>
          </div>
        </el-form>
      </div>

      <div class="w-1/2 h-[70vh]">
        <AnnotateMapPreview :styleData="formData" />
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

const formRef = ref()
const formData = ref({
  scale: '',
  gridType: '',
  lineWidth: 2,  // 默认线宽
  color: 'rgba(0,150,255,0.6)'  // 默认颜色
})

const formRules = {
  scale: [{ required: true, message: '请选择比例尺', trigger: 'change' }],
  gridType: [{ required: true, message: '请选择网格类型', trigger: 'change' }]
}

const scaleList = [
  { label: '1:500', value: '1:500' },
  { label: '1:1000', value: '1:1000' },
  { label: '1:5000', value: '1:5000' }
]

const gridTypeList = [
  { label: '管理网格', value: 'mng' },
  { label: '评价网格', value: 'eval' }
]

// 打开抽屉并传递数据
const open = (row: GridCodeAnnotateVO) => {
  // 转换数据，确保数据符合 formData 结构
  formData.value = {
    scale: row.scale || '',
    gridType: row.gridType || '',
    lineWidth: 2,  // 如果没有该字段，给定默认值
    color: row.colorC && row.colorM && row.colorY && row.colorK
      ? `cmyk(${row.colorC}, ${row.colorM}, ${row.colorY}, ${row.colorK})`
      : 'rgba(0,150,255,0.6)' // 默认颜色
  }
  visible.value = true  // 打开抽屉
}

defineExpose({ open })

const handleSubmit = () => {
  formRef.value.validate((valid: boolean) => {
    if (valid) {
      console.log('保存样式:', formData.value)
      visible.value = false
    }
  })
}

watch(formData, () => {
  // 实时更新地图样式
}, { deep: true })
</script>

<style scoped>
.grid-style-drawer {
  overflow: hidden;
}
</style>
