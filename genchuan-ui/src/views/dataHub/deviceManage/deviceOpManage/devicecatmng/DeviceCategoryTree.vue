<template>
  <div class="category-tree-container">
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
      node-key="id"
      default-expand-all
      @node-click="handleNodeClick"
      highlight-current
    />
  </div>
</template>

<script setup lang="ts">
import { ref, watch } from 'vue'
import { DeviceCatMngApi } from '@/api/dataHub/deviceManage/deviceOpManage/devicecatmng'

// 设备分类节点接口
interface CategoryNode {
  id: string
  name: string
  level: 'major' | 'mid' | 'minor'
  parentId: string | null
  children?: CategoryNode[]
}

const props = defineProps<{
  // 选中的节点ID
  selectedId?: string
}>()

const emit = defineEmits<{
  // 节点选中事件，传递分类ID和层级
  (e: 'select', categoryId: string | null, level: 'major' | 'mid' | 'minor' | null): void
}>()

// 树形配置
const treeProps = {
  id: 'id',
  label: 'name',
  children: 'children',
  parentId: 'parentId'
}

// 搜索关键词
const searchKeyword = ref('')
// 树形数据
const treeData = ref<CategoryNode[]>([])
// 原始数据
const originData = ref<CategoryNode[]>([])
// 树实例
const treeRef = ref()

// 加载分类数据
const loadCategoryData = async () => {
  try {
    // 获取所有设备分类数据（使用分页接口获取足够多的数据）
    const response = await DeviceCatMngApi.getDeviceCatMngPage({
      pageNo: 1,
      pageSize: 100
    })

    // 提取并转换为分类节点数据
    const nodes = transformToCategoryNodes(response.list || [])
    originData.value = nodes
    // 构建树形结构
    buildTreeData()
  } catch (error) {
    console.error('加载分类数据失败', error)
  }
}

// 将设备数据转换为分类节点
const transformToCategoryNodes = (deviceList: any[]): CategoryNode[] => {
  const majorMap: Record<string, CategoryNode> = {}
  const midMap: Record<string, CategoryNode> = {}
  const minorMap: Record<string, CategoryNode> = {}
  const allNodes: CategoryNode[] = []

  // 提取所有唯一的大类、中类、小类
  deviceList.forEach((device) => {
    // 处理大类
    if (device.deviceMajorId && device.deviceMajorName && !majorMap[device.deviceMajorId]) {
      const majorNode: CategoryNode = {
        id: device.deviceMajorId,
        name: device.deviceMajorName,
        level: 'major',
        parentId: null,
        children: []
      }
      majorMap[device.deviceMajorId] = majorNode
      allNodes.push(majorNode)
    }

    // 处理中类
    if (device.deviceMidId && device.deviceMidName && !midMap[device.deviceMidId]) {
      const midNode: CategoryNode = {
        id: device.deviceMidId,
        name: device.deviceMidName,
        level: 'mid',
        parentId: device.deviceMajorId || null,
        children: []
      }
      midMap[device.deviceMidId] = midNode
      allNodes.push(midNode)
    }

    // 处理小类
    if (device.deviceMinorId && device.deviceMinorName && !minorMap[device.deviceMinorId]) {
      const minorNode: CategoryNode = {
        id: device.deviceMinorId,
        name: device.deviceMinorName,
        level: 'minor',
        parentId: device.deviceMidId || null
      }
      minorMap[device.deviceMinorId] = minorNode
      allNodes.push(minorNode)
    }
  })

  return allNodes
}

// 构建树形结构
const buildTreeData = () => {
  const tree: CategoryNode[] = []
  const map: Record<string, CategoryNode & { children?: CategoryNode[] }> = {}

  // 初始化节点映射
  originData.value.forEach((item) => {
    map[item.id] = { ...item, children: item.children || [] }
  })

  // 构建树形结构
  originData.value.forEach((item) => {
    const node = map[item.id]
    if (item.parentId && map[item.parentId]) {
      // 有父节点，添加到父节点的children中
      map[item.parentId].children?.push(node)
    } else if (item.level === 'major') {
      // 没有父节点的大类，作为根节点
      tree.push(node)
    }
  })

  treeData.value = tree
}

// 节点过滤方法
const filterNode = (value: string, data: CategoryNode) => {
  if (!value) return true
  return data.name?.includes(value)
}

// 处理搜索
const handleSearch = (value: string) => {
  treeRef.value?.filter(value)
}

// 处理节点点击
const handleNodeClick = (data: CategoryNode) => {
  emit('select', data.id, data.level)
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
loadCategoryData()
</script>

<style scoped>
.category-tree-container {
  padding: 16px;
  height: 100%;
  box-sizing: border-box;
  border-right: 1px solid #e5e7eb;
  overflow-y: auto;
}
</style>
