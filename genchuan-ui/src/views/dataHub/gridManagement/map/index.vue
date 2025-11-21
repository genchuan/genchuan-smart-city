<template>
  <div class="p-4">
    <!-- 顶部操作按钮 -->
    <el-button type="primary" @click="handleAdd">新增管理网格</el-button>

    <!-- 其他内容，比如表格、列表等 -->

    <!-- 引入选择弹窗 -->
    <SelectUnitGridDialog ref="selectGridDialogRef" />
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import SelectUnitGridDialog from './UnitGridSelectDialog.vue'

const selectGridDialogRef = ref<any>(null)

/** 点击“新增”按钮，打开弹窗 */
const handleAdd = () => {
  selectGridDialogRef.value?.open()
}
</script>
