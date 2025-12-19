<template>
  <div class="app-scene-tree">
    <el-tree-select
      v-model="selectedValues"
      :data="sceneTreeData"
      :props="sceneTreeProps"
      :filterable="true"
      :filter-method="filterSceneTree"
      :multiple="true"
      :collapse-tags="false"
      :collapse-tags-tooltip="true"
      placeholder="请选择应用场景（支持多选、模糊搜索）"
      @change="handleTreeChange"
      @clear="handleClear"
      :disabled="props.disabled"
      class="w-full"
      clearable
    >
      <template #default="{ node, data }">
        <span class="tree-node-label">
          {{ data.sceneCatName }}
          <small class="tree-node-code ml-2 text-gray-400">({{ data.sceneCatCode }})</small>
        </span>
      </template>
    </el-tree-select>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, watch, onMounted, defineProps, defineEmits, computed } from 'vue'
import { AppSceneCategoryApi, AppSceneCategoryVO } from '@/api/dataHub/sceneCategory/appCateInfo'

// 组件Props：支持父组件传入默认值和禁用状态
const props = defineProps({
  modelValue: {
    type: Array as () => string[],
    default: () => []
  },
  disabled: {
    type: Boolean,
    default: false
  },
  maxVisibleTags: {
    type: Number,
    default: 5
  }
})

// 组件Events：向父组件传递选中结果
const emits = defineEmits(['change', 'update:modelValue'])

/* ================== 树形核心配置 ================== */
const rawSceneTreeData = ref<any[]>([])
const sceneTreeData = ref<any[]>([])
const sceneTreeProps = reactive({
  label: 'sceneCatName',
  value: 'sceneCatCode',
  children: 'children',
  isLeaf: (data: any) => !data.children || data.children.length === 0
})

/* ================== 选中状态管理 ================== */
const selectedValues = ref<string[]>([])
const flatSceneList = ref<AppSceneCategoryVO[]>([])
const selectedLabels = computed(() => {
  return selectedValues.value
    .map((code) => {
      const matchItem = flatSceneList.value.find((item) => item.sceneCatCode === code)
      return matchItem?.sceneCatName || ''
    })
    .filter(Boolean)
})

/* ================== 数据加载与处理 ================== */
const loadSceneTreeData = async () => {
  try {
    const treeRes = await AppSceneCategoryApi.getAppSceneCategoryTree()
    const validTreeData = treeRes || []
    rawSceneTreeData.value = validTreeData
    sceneTreeData.value = validTreeData
    flatSceneList.value = []
    flattenSceneTree(validTreeData, flatSceneList)
  } catch (error) {
    console.error('加载应用场景树形数据失败:', error)
    rawSceneTreeData.value = []
    sceneTreeData.value = []
    flatSceneList.value = []
  }
}

const flattenSceneTree = (tree: any[], result: Ref<AppSceneCategoryVO[]>) => {
  tree.forEach((item) => {
    if (item.sceneCatCode && item.sceneCatName) {
      result.value.push(item as AppSceneCategoryVO)
    }
    if (item.children && item.children.length > 0) {
      flattenSceneTree(item.children, result)
    }
  })
}

/* ================== 模糊搜索过滤 ================== */
const filterSceneTree = (query: string) => {
  if (!query) {
    sceneTreeData.value = rawSceneTreeData.value
    return
  }
  const lowerQuery = query.toLowerCase()
  const filterNode = (nodes: any[]): any[] => {
    const validNodes: any[] = []
    nodes.forEach((node) => {
      const isMatch = node.sceneCatName?.toLowerCase().includes(lowerQuery)
      const filteredChildren = node.children ? filterNode(node.children) : []
      if (isMatch || filteredChildren.length > 0) {
        validNodes.push({
          ...node,
          children: filteredChildren
        })
      }
    })
    return validNodes
  }
  sceneTreeData.value = filterNode(rawSceneTreeData.value)
}

/* ================== 选中与回显 ================== */
const handleTreeChange = () => {
  emits('update:modelValue', selectedValues.value)
  emits('change', {
    values: [...selectedValues.value],
    labels: [...selectedLabels.value]
  })
}

// 优化回显逻辑：移除immediate，仅在数据变化且就绪后执行
watch(
  () => props.modelValue,
  (newVal) => {
    if (Array.isArray(newVal) && flatSceneList.value.length) {
      // 确保数据就绪
      const validCodes = newVal.filter((code) =>
        flatSceneList.value.some((item) => item.sceneCatCode === code)
      )
      selectedValues.value = validCodes
    }
  },
  { deep: true } // 移除immediate，避免初始化时无效触发
)

// 清空处理方法
const handleClear = () => {
  selectedValues.value = []
  handleTreeChange()
}

/* ================== 初始化与暴露 ================== */
// 初始化加载数据
onMounted(() => {
  loadSceneTreeData()
})
// 暴露加载数据方法，供父组件调用（关键）
defineExpose({
  loadSceneTreeData,
  flatSceneList
})
</script>

<style scoped>
.app-scene-tree {
  width: 100%;
}

.tree-node-label {
  display: inline-flex;
  align-items: center;
  line-height: 1.5;
}

.tree-node-code {
  font-size: 12px;
  font-weight: normal;
}

/* 优化多选标签样式 */
::v-deep .el-tree-select__tags {
  flex-wrap: wrap;
  gap: 4px;
  padding: 2px 4px;
  max-height: 100px;
  overflow-y: auto;
}

::v-deep .el-tree-select__tag {
  margin-bottom: 4px;
  max-width: calc(100% - 20px);
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  padding: 2px 8px;
  height: 24px;
  line-height: 20px;
}

/* 优化输入框样式 */
::v-deep .el-tree-select .el-input__inner {
  padding: 6px 12px;
  min-height: 34px;
}

/* 树形下拉弹窗样式 */
::v-deep .el-tree-select-dropdown .el-tree {
  max-height: 400px;
  overflow-y: auto;
}

::v-deep .el-tree-node__content:hover {
  background-color: #f0f9ff;
}

::v-deep .el-tree--highlight-current .el-tree-node.is-current > .el-tree-node__content {
  background-color: #e6f7ff;
  color: #1890ff;
  font-weight: 500;
}
</style>
