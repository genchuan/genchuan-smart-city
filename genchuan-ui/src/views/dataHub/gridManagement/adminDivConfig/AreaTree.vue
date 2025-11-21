<template>
  <div class="area-tree-wrap">
    <el-input
      v-model="filterTextLocal"
      placeholder="搜索行政区划（模糊）"
      clearable
      class="mb-10px"
      @input="onFilterChange"
    >
      <template #prefix><Icon icon="ep:search" /></template>
    </el-input>

    <el-tree
      ref="treeRef"
      :data="displayTree"
      node-key="id"
      :props="treeProps"
      highlight-current
      :expand-on-click-node="false"
      :default-expand-all="false"
      :load="lazyLoad"
      lazy
      @node-click="handleNodeClick"
    >
      <template #default="{ node, data }">
        <span>{{ data.name }} <small v-if="data.level">（{{ data.level }}）</small></span>
      </template>
    </el-tree>
  </div>
</template>

<script lang="ts" setup>
import { AreaApi } from '@/api/dataHub/gridManagement/adminDivConfig'
import { ElTree } from 'element-plus'

const emits = defineEmits(['node-selected'])
const treeRef = ref<InstanceType<typeof ElTree> | null>(null)

const treeData = ref<any[]>([])
const filterTextLocal = ref('')

const treeProps = reactive({
  children: 'children',
  label: 'name',
  isLeaf: (data: any) => !data.hasChildren
})

/** 加载根树（完整树或按需懒加载） */
const loadRoot = async () => {
  try {
    const resp = await AreaApi.getTree()
    // resp.data is expected as array
    treeData.value = resp || []
  } catch (e) {
    console.error('加载行政区划树失败', e)
    treeData.value = []
  }
}

/** 懒加载函数（当节点展开时调用后端获取子节点） */
const lazyLoad = async (node: any, resolve: Function) => {
  // 如果为根（level undefined）则 loadRoot 为主入口
  const nodeData = node?.data
  if (!nodeData || nodeData.id === 0) {
    // 加载根子节点（如果后端按 root 提供）
    try {
      const children = await AreaApi.getTreeByParent(nodeData?.id ?? 0)
      resolve(children || [])
    } catch (e) {
      resolve([])
    }
    return
  }
  // 普通节点：调用 /datacenter/area/tree/{parentId}
  try {
    const children = await AreaApi.getTreeByParent(nodeData.id)
    resolve(children || [])
  } catch (e) {
    resolve([])
  }
}

/** 点击节点 */
const handleNodeClick = (data: any) => {
  emits('node-selected', data)
}

/** 本地模糊过滤：如果输入为空，显示完整树；否则筛选并展开匹配路径 */
const displayTree = computed(() => {
  if (!filterTextLocal.value) return treeData.value
  // 简单递归过滤：保留匹配节点及其祖先
  const res: any[] = []
  const match = (node: any) => {
    let matched = (node.name || '').includes(filterTextLocal.value)
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

/** 外部调用：reload */
const reload = async () => {
  await loadRoot()
}

defineExpose({ reload })

/** 监听 filterText 父子传递（v-model:filterText） */
const props = defineProps({
  filterText: { type: String, default: '' }
})
watch(
  () => props.filterText,
  (v) => {
    filterTextLocal.value = v
  }
)
watch(filterTextLocal, (v) => {
  // 如果为空，reload 数据（避免已展开树阻塞）
  if (!v) {
    // reload root tree to ensure children available
    loadRoot()
  }
})

/** 本地输入变化通知父组件（debounce 可选） */
const onFilterChange = () => {
  // 通过 emit 也可直接修改父级 v-model (we used v-model.sync)
}

/** init */
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
