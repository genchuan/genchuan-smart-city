<!-- 资产分类管理树形组件 -->
<template>
  <div class="asset-cat-tree">
    <div class="tree-header">
      <div class="tree-actions">
        <el-button size="small" @click="expandAll" :disabled="loading">展开全部</el-button>
        <el-button size="small" @click="collapseAll" :disabled="loading">折叠全部</el-button>
        <el-button size="small" @click="loadAssetCatData" :loading="loading">
          <el-icon><Refresh /></el-icon>
          刷新
        </el-button>
        <el-input
          v-model="filterText"
          size="small"
          placeholder="输入关键字过滤"
          clearable
          style="width: 200px; margin-left: 10px;"
        >
          <template #prefix>
            <el-icon><Search /></el-icon>
          </template>
        </el-input>
      </div>
    </div>
    
    <!-- 加载状态 -->
    <div v-if="loading" class="loading-state">
      <el-icon class="is-loading"><Loading /></el-icon>
      加载中...
    </div>
    
    <!-- 错误状态 -->
    <div v-else-if="error" class="error-state">
      <el-alert
        :title="error"
        type="error"
        show-icon
        :closable="false"
      />
      <el-button @click="loadAssetCatData" type="primary" style="margin-top: 10px;">
        重试
      </el-button>
    </div>
    
    <!-- 空状态 -->
    <div v-else-if="!treeData || treeData.length === 0" class="empty-state">
      <el-empty description="暂无资产分类数据" />
    </div>
    
    <!-- 正常显示 -->
    <div v-else class="tree-container">
      <el-tree
        ref="treeRef"
        :data="treeData"
        :props="defaultProps"
        :default-expand-all="false"
        :expand-on-click-node="false"
        :filter-node-method="filterNode"
        node-key="assetCatId"
        highlight-current
        @node-click="handleNodeClick"
      >
        <template #default="{ node, data }">
          <span class="custom-tree-node">
            <span class="node-label">{{ data.assetCatName }}</span>
            <span class="node-id">({{ data.assetCatId }})</span>
          </span>
        </template>
      </el-tree>
    </div>
    
    <!-- 节点详情展示 -->
    <div v-if="selectedNode" class="node-detail">
      <h4>选中节点详情</h4>
      <div class="detail-content">
        <p><strong>分类ID:</strong> {{ selectedNode.assetCatId }}</p>
        <p><strong>分类名称:</strong> {{ selectedNode.assetCatName }}</p>
        <p><strong>父级分类ID:</strong> {{ selectedNode.parentCatId || '根节点' }}</p>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, watch } from 'vue'
import { ElTree, ElMessage } from 'element-plus'
import { Search, Refresh, Loading } from '@element-plus/icons-vue'
import { AssetCatMngApi } from '@/api/dataHub/assetManagement/assetOperationManagement/assetCatMng'

// 定义树节点类型
interface TreeNode {
  assetCatId: string
  assetCatName: string
  parentCatId: string
  children?: TreeNode[]
}

// 响应式数据
const treeData = ref<TreeNode[]>([])
const selectedNode = ref<TreeNode | null>(null)
const filterText = ref('')
const treeRef = ref<InstanceType<typeof ElTree>>()
const loading = ref(false)
const error = ref('')

// 树形配置
const defaultProps = {
  children: 'children',
  label: 'assetCatName'
}

// 加载资产分类数据
const loadAssetCatData = async () => {
  loading.value = true
  error.value = ''
  try {
    const response = await AssetCatMngApi.getAssetCatMngList()
    
    // 直接处理数组响应
    if (Array.isArray(response)) {
      if (response.length > 0) {
        treeData.value = buildTree(response)
        ElMessage.success(`成功加载 ${response.length} 个分类节点`)
      } else {
        ElMessage.warning('暂无资产分类数据')
      }
    } else {
      const errorMsg = `获取资产分类数据失败: ${response?.msg || '响应格式不正确'}`
      error.value = errorMsg
      ElMessage.error(errorMsg)
    }
  } catch (error: any) {
    const errorMsg = `加载资产分类数据出错: ${error.message || '未知错误'}`
    error.value = errorMsg
    ElMessage.error(errorMsg)
  } finally {
    loading.value = false
  }
}

// 构建树形结构
const buildTree = (data: any[]): TreeNode[] => {
  if (!data || !Array.isArray(data) || data.length === 0) {
    return []
  }

  const nodeMap = new Map<string, TreeNode>()
  const tree: TreeNode[] = []
  
  // 创建所有节点的映射
  data.forEach(item => {
    if (item.assetCatId && item.assetCatName !== undefined) {
      const treeNode: TreeNode = {
        assetCatId: item.assetCatId,
        assetCatName: item.assetCatName,
        parentCatId: item.parentCatId || '0',
        children: []
      }
      nodeMap.set(item.assetCatId, treeNode)
    }
  })
  
  // 构建树结构
  nodeMap.forEach((node, assetCatId) => {
    const parentId = node.parentCatId
    
    if (!parentId || parentId === '0') {
      // 根节点
      tree.push(node)
    } else {
      // 子节点 - 查找父节点
      const parent = nodeMap.get(parentId)
      if (parent) {
        if (!parent.children) {
          parent.children = []
        }
        parent.children.push(node)
      } else {
        // 找不到父节点，作为根节点
        tree.push(node)
      }
    }
  })
  
  return tree
}

// 节点点击事件
const handleNodeClick = (data: TreeNode) => {
  selectedNode.value = data
}

// 展开所有节点
const expandAll = () => {
  if (treeRef.value) {
    const nodes = (treeRef.value as any).store.nodesMap
    Object.keys(nodes).forEach(key => {
      nodes[key].expanded = true
    })
  }
}

// 折叠所有节点
const collapseAll = () => {
  if (treeRef.value) {
    const nodes = (treeRef.value as any).store.nodesMap
    Object.keys(nodes).forEach(key => {
      nodes[key].expanded = false
    })
  }
}

// 节点过滤
const filterNode = (value: string, data: TreeNode) => {
  if (!value) return true
  return data.assetCatName.includes(value) || data.assetCatId.includes(value)
}

// 监听过滤文本变化
watch(filterText, (val) => {
  treeRef.value?.filter(val)
})

// 组件挂载时加载数据
onMounted(() => {
  loadAssetCatData()
})

// 暴露方法给父组件
defineExpose({
  refresh: loadAssetCatData
})
</script>

<style scoped>
.asset-cat-tree {
  border: 1px solid #e4e7ed;
  border-radius: 4px;
  padding: 16px;
  background-color: #fff;
  height: 100%;
  display: flex;
  flex-direction: column;
}

.tree-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 16px;
  padding-bottom: 12px;
  border-bottom: 1px solid #e4e7ed;
  flex-shrink: 0;
}

.tree-header h3 {
  margin: 0;
  color: #303133;
  font-size: 16px;
}

.tree-actions {
  display: flex;
  align-items: center;
}

.tree-container {
  flex: 1;
  overflow-y: auto;
  border: 1px solid #e4e7ed;
  border-radius: 4px;
  padding: 8px;
  min-height: 400px;
}

.loading-state,
.error-state,
.empty-state {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 200px;
  padding: 20px;
  flex: 1;
}

.custom-tree-node {
  display: flex;
  align-items: center;
  font-size: 14px;
  padding: 2px 0;
}

.node-label {
  font-weight: 500;
  color: #303133;
}

.node-id {
  margin-left: 8px;
  color: #909399;
  font-size: 12px;
}

.node-detail {
  margin-top: 16px;
  padding: 12px;
  border: 1px solid #e4e7ed;
  border-radius: 4px;
  background-color: #f5f7fa;
  flex-shrink: 0;
}

.node-detail h4 {
  margin: 0 0 12px 0;
  color: #303133;
  font-size: 14px;
}

.detail-content p {
  margin: 6px 0;
  color: #606266;
  font-size: 13px;
}

/* 树节点样式优化 */
:deep(.el-tree) {
  background: transparent;
}

:deep(.el-tree-node__content) {
  height: 32px;
  margin: 2px 0;
}

:deep(.el-tree-node:focus > .el-tree-node__content) {
  background-color: #f0f7ff;
}

:deep(.el-tree--highlight-current .el-tree-node.is-current > .el-tree-node__content) {
  background-color: #f0f7ff;
  color: #409eff;
}
</style>