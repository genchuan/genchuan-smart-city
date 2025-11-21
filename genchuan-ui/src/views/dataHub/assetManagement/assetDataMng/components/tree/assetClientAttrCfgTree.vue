<template>
  <div class="asset-tree-container">
    <!-- 资产树形列表 -->
    <div class="asset-tree-header">
      <span class="header-title">资产列表</span>
    </div>
    <div class="asset-tree-content">
      <el-input
        v-model="filterText"
        placeholder="输入关键字过滤"
        clearable
        class="tree-filter-input"
      >
        <template #prefix>
          <el-icon><Search /></el-icon>
        </template>
      </el-input>
      
      <el-tree
        ref="treeRef"
        :data="assetTreeData"
        :props="treeProps"
        :filter-node-method="filterNode"
        :highlight-current="true"
        node-key="id"
        default-expand-all
        @node-click="handleNodeClick"
        class="asset-tree"
      >
        <template #default="{ node, data }">
          <span class="tree-node-label">
            {{ data.name }}（{{ data.id }}）
          </span>
        </template>
      </el-tree>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, watch, onMounted } from 'vue'
import { Search } from '@element-plus/icons-vue'
import { getDataMaps } from '@/api/dataHub/assetManagement/assetDataMng/assetDataMngList'

// 定义组件事件
const emit = defineEmits(['asset-select'])

// 过滤文本
const filterText = ref('')
// 树形组件引用
const treeRef = ref()
// 资产树形数据
const assetTreeData = ref<any[]>([])
// 树形配置
const treeProps = {
  children: 'children',
  label: 'name'
}

// 监听过滤文本变化
watch(filterText, (val) => {
  treeRef.value?.filter(val)
})

/**
 * 过滤树节点
 */
const filterNode = (value: string, data: any) => {
  if (!value) return true
  return data.name?.includes(value) || data.id?.includes(value)
}

/**
 * 处理节点点击事件
 */
const handleNodeClick = (data: any) => {
  // 只处理叶子节点（没有子节点的节点）
  if (!data.children || data.children.length === 0) {
    emit('asset-select', {
      assetId: data.id,
      assetName: data.name
    })
  }
}

/**
 * 加载资产数据并构建树形结构
 */
const loadAssetData = async () => {
  try {
    const maps = await getDataMaps()
    
    // 将映射数据转换为树形结构
    assetTreeData.value = Object.entries(maps.name2Id).map(([name, id]) => ({
      id: id,
      name: name,
      // 这里可以根据需要添加子节点
      children: []
    }))
  } catch (error) {
    console.error('加载资产数据失败:', error)
  }
}

/**
 * 清空选中状态
 */
const clearSelection = () => {
  treeRef.value?.setCurrentKey(null)
}

// 暴露方法给父组件
defineExpose({
  clearSelection
})

// 初始化加载数据
onMounted(() => {
  loadAssetData()
})
</script>

<style lang="scss" scoped>
.asset-tree-container {
  height: 100%;
  border: 1px solid #e4e7ed;
  border-radius: 4px;
  background: #fff;
  
  .asset-tree-header {
    padding: 12px 16px;
    border-bottom: 1px solid #e4e7ed;
    background: #f5f7fa;
    
    .header-title {
      font-weight: 600;
      color: #303133;
    }
  }
  
  .asset-tree-content {
    padding: 16px;
    
    .tree-filter-input {
      margin-bottom: 12px;
    }
    
    .asset-tree {
      :deep(.el-tree-node) {
        .el-tree-node__content {
          height: 36px;
          margin: 2px 0;
          
          &:hover {
            background-color: #f5f7fa;
          }
        }
        
        &.is-current {
          .el-tree-node__content {
            background-color: #ecf5ff;
            
            .tree-node-label {
              color: #409eff;
              font-weight: 500;
            }
          }
        }
        
        .tree-node-label {
          font-size: 14px;
          color: #606266;
        }
      }
    }
  }
}
</style>