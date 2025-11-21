<template>
  <el-dialog
    v-model="visible"
    title="地图拾取"
    width="800px"
    destroy-on-close
  >
    <div class="map-container">
      <div class="fake-map" @click="pickLocation">
        <span class="tip">（静态地图占位，点击拾取示例位置）</span>
      </div>
    </div>

    <template #footer>
      <el-button @click="cancel">取消</el-button>
      <el-button type="primary" @click="confirm">确认</el-button>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref } from 'vue'
import { ElMessage } from 'element-plus'

const props = defineProps({
  modelValue: Boolean
})
const emit = defineEmits(['update:modelValue', 'picked'])

const visible = ref(false)
const pickedLocation = ref('')

// 双向绑定 visible
watch(
  () => props.modelValue,
  (val) => (visible.value = val)
)
watch(visible, (val) => emit('update:modelValue', val))

// 点击地图拾取
const pickLocation = () => {
  pickedLocation.value = 'XX大道与XX路交叉口东南侧（模拟）'
  ElMessage.success('已拾取位置：' + pickedLocation.value)
}

// 取消
const cancel = () => {
  visible.value = false
}

// 确认
const confirm = () => {
  if (!pickedLocation.value) {
    ElMessage.warning('请先点击地图拾取位置')
    return
  }
  emit('picked', pickedLocation.value)
  visible.value = false
}
</script>

<style scoped>
.map-container {
  height: 400px;
  border: 1px solid #dcdfe6;
  border-radius: 8px;
  overflow: hidden;
  position: relative;
}

.fake-map {
  width: 100%;
  height: 100%;
  background: linear-gradient(45deg, #eef2f7, #cfd8dc);
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}

.tip {
  color: #666;
  font-size: 14px;
}
</style>
