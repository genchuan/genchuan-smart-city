<template>
  <div class="tree-container">
    <el-input
      v-model="searchKeyword"
      placeholder="请输入分类名称搜索"
      clearable
      prefix-icon="ep:search"
      @input="handleSearch"
      class="mb-4"
    />
    <el-tree
      ref="treeRef"
      :data="treeData"
      :props="treeProps"
      :filter-node-method="filterNode"
      node-key="catRuleId"
      default-expand-all
      @node-click="handleNodeClick"
      highlight-current
    />
  </div>
</template>

<script setup lang="ts">
import { ref, watch } from 'vue'
import {
  DeviceCatRuleApi,
  DeviceCatRuleVO
} from '@/api/dataHub/deviceManage/deviceRule/devicecatrule'

const props = defineProps<{
  // 选中的节点ID
  selectedId?: string
}>()

const emit = defineEmits<{
  // 节点选中事件
  (e: 'select', catRuleId: string | null): void
}>()

// 树形配置
const treeProps = {
  id: 'catRuleId',
  label: 'catName',
  children: 'children',
  parentId: 'parentCatRuleId'
}

// 搜索关键词
const searchKeyword = ref('')
// 树形数据
const treeData = ref<DeviceCatRuleVO[]>([])
// 原始数据
const originData = ref<DeviceCatRuleVO[]>([])
// 树实例
const treeRef = ref()

// 加载树形数据
const loadTreeData = async () => {
  try {
    // 获取所有分类规则数据
    const response = await DeviceCatRuleApi.getDeviceCatRulePage({ pageNo: 1, pageSize: 100 })
    originData.value = response.list || []
    // 构建树形结构
    buildTreeData()
  } catch (error) {
    console.error('加载树形数据失败', error)
  }
}

// 构建树形结构
const buildTreeData = () => {
  const tree: DeviceCatRuleVO[] = []
  const map: Record<string, DeviceCatRuleVO & { children?: DeviceCatRuleVO[] }> = {}

  // 初始化节点映射
  originData.value.forEach((item) => {
    map[item.catRuleId] = { ...item, children: [] }
  })

  // 构建树形结构
  originData.value.forEach((item) => {
    const node = map[item.catRuleId]
    if (item.parentCatRuleId && map[item.parentCatRuleId]) {
      // 有父节点，添加到父节点的children中
      map[item.parentCatRuleId].children?.push(node)
    } else {
      // 没有父节点，作为根节点
      tree.push(node)
    }
  })

  treeData.value = tree
}

// 节点过滤方法
const filterNode = (value: string, data: DeviceCatRuleVO) => {
  if (!value) return true
  return data.catName?.includes(value)
}

// 处理搜索
const handleSearch = (value: string) => {
  treeRef.value?.filter(value)
}

// 处理节点点击
const handleNodeClick = (data: DeviceCatRuleVO) => {
  emit('select', data.catRuleId)
}

// 监听选中ID变化
watch(
  () => props.selectedId,
  (newVal) => {
    if (newVal && treeRef.value) {
      treeRef.value.setCurrentKey(newVal)
    }
  }
)

// 初始化加载数据
loadTreeData()
</script>

<style scoped>
.tree-container {
  padding: 16px;
  height: 100%;
  box-sizing: border-box;
  border-right: 1px solid #e5e7eb;
}
</style>
