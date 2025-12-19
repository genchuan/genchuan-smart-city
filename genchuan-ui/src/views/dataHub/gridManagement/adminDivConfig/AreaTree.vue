<template>
  <div class="area-tree-wrap">
    <el-input
      v-model="filterTextLocal"
      placeholder="搜索行政区划（模糊）"
      clearable
      class="mb-10px"
      @input="onFilterChange"
    >
      <template #prefix>
        <Icon icon="ep:search" />
      </template>
    </el-input>
    <el-tree
      ref="treeRef"
      :data="displayTree"
      node-key="id"
      :props="treeProps"
      highlight-current
      :expand-on-click-node="false"
      :default-expand-all="false"
      @node-click="handleNodeClick"
    >
      <template #default="{ node, data }">
        <span
          >{{ data.name }} <small v-if="data.level">（{{ data.level }}）</small></span
        >
      </template>
    </el-tree>
  </div>
</template>

<script lang="ts" setup>
import { AreaApi } from '@/api/dataHub/gridManagement/adminDivConfig'
import { ElTree } from 'element-plus'
import { ref, reactive, computed, watch, onMounted, defineProps, defineEmits } from 'vue'

const emits = defineEmits(['node-selected'])
const treeRef = ref<InstanceType<typeof ElTree> | null>(null)

// 树形数据存储正确的接口返回data（树形数组）
const treeData = ref<any[]>([])
const filterTextLocal = ref('')

// 树形节点配置：保持不变
const treeProps = reactive({
  children: 'children',
  label: 'name',
  isLeaf: (data: any) => !data.hasChildren
})

/** 修正：加载根树 - 正确解析接口返回的data字段 */
const loadRoot = async () => {
  try {
    const resp = await AreaApi.getTree()
    treeData.value = resp || []
  } catch (e) {
    console.error('加载行政区划树失败', e)
    treeData.value = []
  }
}

/** 点击节点 - 逻辑不变 */
const handleNodeClick = (data: any) => {
  emits('node-selected', data)
}

/** 本地模糊过滤：修正数据源后，过滤逻辑保持不变 */
const displayTree = computed(() => {
  if (!filterTextLocal.value) return treeData.value

  const res: any[] = []
  const match = (node: any) => {
    let matched = (node.name || '').toLowerCase().includes(filterTextLocal.value.toLowerCase())
    const children = node.children || []
    const keptChildren: any[] = []

    for (const c of children) {
      const m = match(c)
      if (m) keptChildren.push(c)
      matched = matched || m
    }

    if (matched) {
      const copy = { ...node }
      if (keptChildren.length) copy.children = keptChildren
      res.push(copy)
      return true
    }
    return false
  }

  for (const n of treeData.value) {
    match(n)
  }
  return res
})

/** 外部调用：reload - 逻辑不变 */
const reload = async () => {
  await loadRoot()
}
defineExpose({ reload })

/** 监听 filterText 父子传递 - 逻辑不变 */
const props = defineProps({
  filterText: { type: String, default: '' }
})
watch(
  () => props.filterText,
  (v) => {
    filterTextLocal.value = v
  }
)

/** 本地输入变化通知父组件 - 逻辑不变 */
const onFilterChange = () => {
  // 空实现（如需同步父组件，可添加emit：emits('update:filterText', filterTextLocal.value)）
}

/** 初始化：仅加载一次完整树，关闭懒加载避免重复 */
onMounted(() => {
  loadRoot()
})
</script>

<style scoped>
.area-tree-wrap {
  font-size: 15px; /* 调大字体 */
  line-height: 1.8;
  padding: 8px;
}

.area-tree-wrap .el-tree-node__content {
  height: 32px; /* 节点高度适度增大 */
}
</style>
