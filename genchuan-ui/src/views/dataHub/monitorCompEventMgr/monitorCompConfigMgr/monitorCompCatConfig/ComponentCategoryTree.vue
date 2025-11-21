<template>
  <div>
    <!-- 搜索框 -->
    <el-input v-model="filterText" placeholder="请输入分类名称" clearable class="mb-10px">
      <template #prefix><Icon icon="ep:search" /></template>
    </el-input>

    <!-- 分类树 -->
    <el-tree
      ref="treeRef"
      :data="treeData"
      node-key="category_id"
      default-expand-all
      :expand-on-click-node="false"
      highlight-current
      :filter-node-method="filterNode"
      @node-click="handleNodeClick"
    >
      <template #default="{ data }">
        <span>{{ data.category_name }}</span>
      </template>
    </el-tree>
  </div>
</template>

<script lang="ts" setup>
import { ElTree } from 'element-plus'
const emits = defineEmits(['node-click'])
const treeRef = ref<InstanceType<typeof ElTree>>()
const filterText = ref('')

/** 模拟静态分类树 */
const treeData = ref([
  {
    category_id: 1,
    category_name: '市政设施',
    children: [
      {
        category_id: 2,
        category_name: '燃气',
        children: [
          { category_id: 3, category_name: '燃气管道' },
          { category_id: 4, category_name: '燃气调压设施' }
        ]
      }
    ]
  }
])

/** 过滤逻辑 */
const filterNode = (value: string, data: any) => {
  if (!value) return true
  return data.category_name.includes(value)
}

/** 监听输入变化执行过滤 */
watch(filterText, (val) => {
  treeRef.value!.filter(val)
})

/** 节点点击事件 */
const handleNodeClick = (data: any) => {
  emits('node-click', data)
}
</script>
