<template>
  <div class="data-menu-page">
    <!-- 一级菜单容器：纵向单列 + 滚动（保留滚动功能） -->
    <div class="menu-list">
      <!-- 每个一级菜单单独占一行 -->
      <div v-for="menu in firstLevelMenus" :key="menu.id" class="menu-item-wrapper">
        <!-- 一级菜单卡片 -->
        <el-card class="menu-card" :style="`background: ${getCardBgColor(menu.id)}`">
          <!-- 一级菜单标题栏：居中展示，强化视觉层级 -->
          <div class="menu-header">
            <!-- 图标+文字统一容器 -->
            <div class="title-group">
              <Icon v-if="menu.icon" :icon="menu.icon" class="menu-icon" />
              <span class="menu-title">{{ menu.name }}</span>
            </div>
          </div>
          <!-- 子菜单区域：按区块横向排列，保留层级 -->
          <div class="sub-menu-container" v-if="menu.children?.length">
            <!-- 二级菜单区块（横向排列） -->
            <div v-for="child in menu.children" :key="child.id" class="sub-menu-block">
              <!-- 二级菜单标题（加粗区分区块） -->
              <div
                class="sub-menu-title"
                @click="!child.children?.length && handleMenuClick(child)"
                :style="{ cursor: child.children?.length ? 'default' : 'pointer' }"
              >
                {{ child.name }}
              </div>
              <!-- 三级菜单：横向紧凑排列 -->
              <div class="third-menu-list" v-if="child.children?.length">
                <div
                  v-for="third in child.children"
                  :key="third.id"
                  class="third-menu-item"
                  @click.stop="handleMenuClick(third)"
                >
                  {{ third.name }}
                </div>
              </div>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import { ref, onMounted, computed } from 'vue'
import * as PermissionApi from '@/api/system/permission'
import { getMenuTreeByIds } from '@/api/system/menu'
import { AppSceneCategoryApi } from '@/api/dataHub/sceneCategory/appCateInfo'
import { useRouter, useRoute } from 'vue-router'
import { getInfo } from '@/api/login'

const router = useRouter()
const route = useRoute()

interface MenuItem {
  id: number
  name: string
  parentId: number | string
  icon?: any
  path: string
  children?: MenuItem[] | null
}

const allMenus = ref<MenuItem[]>([])
const firstLevelMenus = ref<MenuItem[]>([])
const menuMap = ref<Record<number, MenuItem>>({})

const flattenMenus = (menus: MenuItem[]) => {
  menus.forEach((menu) => {
    menuMap.value[menu.id] = menu
    if (menu.children && menu.children.length) {
      flattenMenus(menu.children)
    }
  })
}

const getRoleKeyFromRoute = (): number | null => {
  const currentPath = route.fullPath
  const lastSlashIndex = currentPath.lastIndexOf('/')
  const codeStr = currentPath.slice(lastSlashIndex + 1)
  return /^\d+$/.test(codeStr) ? codeStr : null
}

// 核心新增：递归筛选所有层级菜单（仅保留id在menuIds或包含有效子菜单的节点）
const filterMenus = (menus: MenuItem[], menuIds: number[]): MenuItem[] => {
  return menus
    .map((menu) => {
      // 递归筛选当前菜单的子菜单
      const filteredChildren = menu.children
        ? filterMenus(menu.children, menuIds)
        : []
      // 保留条件：1.自身id在menuIds中；2.有筛选后的有效子菜单（避免空父菜单）
      if (menuIds.includes(menu.id) || filteredChildren.length > 0) {
        return { ...menu, children: filteredChildren }
      }
      return null // 不满足条件则剔除
    })
    .filter((menu) => menu !== null) as MenuItem[]
}

const loadMenus = async () => {
  const code = getRoleKeyFromRoute()
  if (!code) {
    console.error('路由中未提取到有效Code，当前路由路径：', route.fullPath)
    return
  }

  const sceneOfCode = await AppSceneCategoryApi.getAppSceneCategoryPage({
    pageNo: 1,
    pageSize: 10,
    sceneCatCode: code
  })

  const permissionInfo = await getInfo()
  const menus = permissionInfo?.menus || []
  const dataHubMenu = menus.find(menu => menu.name === '数据中枢' && menu.id === 5200)

  // 处理menuIds：字符串转数字数组（过滤非数字）
  const menuIds = sceneOfCode.list[0]?.menuIds
    ? sceneOfCode.list[0].menuIds.split(',').map(id => Number(id)).filter(id => !isNaN(id))
    : []

  // 关键修改：调用递归筛选函数，过滤所有层级菜单
  const filteredChildren = dataHubMenu?.children
    ? filterMenus(dataHubMenu.children, menuIds)
    : []

  allMenus.value = filteredChildren
  firstLevelMenus.value = allMenus.value.filter((m) => String(m.parentId) === '5200')
  flattenMenus(allMenus.value)
}

const getFullRoutePath = (currentMenu: MenuItem): string => {
  const pathParts: string[] = []
  let current: MenuItem | undefined = currentMenu
  while (current && String(current.parentId) !== '5200') {
    if (current.path && current.path.trim()) {
      pathParts.push(current.path.trim())
    }
    current = menuMap.value[Number(current.parentId)]
  }
  if (current && current.path && current.path.trim()) {
    pathParts.push(current.path.trim())
  }
  const rootPath = '/dataHub'
  const fullPath = `${rootPath}/${pathParts.reverse().join('/')}`
  return fullPath.replace(/\/+/g, '/')
}

const handleMenuClick = (menu: MenuItem) => {
  const fullRoutePath = getFullRoutePath(menu)
  const code = getRoleKeyFromRoute()
  if (code) {
    router
      .push({
        path: fullRoutePath,
        query: { code: code }
      })
      .catch((err) => {
        if (err.name !== 'NavigationDuplicated') {
          console.error('路由跳转失败：', err)
        }
      })
  }
}

const getCardBgColor = computed(() => {
  const colors = [
    'linear-gradient(135deg, #fce4ec, #ffebee)',
    'linear-gradient(135deg, #e1f5fe, #e0f7fa)',
    'linear-gradient(135deg, #e8f5e9, #f1f8e9)',
    'linear-gradient(135deg, #fff3e0, #fff8e1)',
    'linear-gradient(135deg, #f3e5f5, #f5f5f5)'
  ]
  return (id: number) => colors[id % colors.length]
})

onMounted(() => loadMenus())
</script>
<style scoped>
/* 页面容器：基础布局，适配全屏 */
.data-menu-page {
  width: 100%;
  padding: 16px;
  box-sizing: border-box;
  background: #fafbfc;
  min-height: calc(100vh - 100px);
}
/* 一级菜单列表：纵向排列，自适应数量 */
.menu-list {
  display: flex;
  flex-direction: column;
  gap: 16px; /* 一级菜单间距，视觉更舒适 */
}
/* 一级菜单卡片：适配背景色，强化区块感 */
.menu-card {
  width: 100%;
  border: none;
  border-radius: 12px;
  padding: 16px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
  transition: box-shadow 0.2s;
}
.menu-card:hover {
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.09);
}
/* 一级菜单标题栏：居中+加粗，突出层级 */
.menu-header {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  margin-bottom: 12px;
  padding-bottom: 8px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.5); /* 分隔线强化卡片内层级 */
}
/* 图标+文字容器：紧凑排列，适配背景色 */
.title-group {
  display: flex;
  align-items: center;
  gap: 8px; /* 图标与文字间距，避免紧贴 */
}
/* 菜单图标：统一颜色，适配马卡龙背景 */
.menu-icon {
  font-size: 18px;
  color: #d32f2f; /* 深色图标，与浅背景形成对比 */
}
/* 一级菜单标题：加粗，增强视觉焦点 */
.menu-title {
  font-size: 16px;
  font-weight: 700;
  color: #2d3436; /* 深色文字，确保在浅背景上清晰可见 */
}
/* 子菜单容器：横向排列，按区块区分 */
.sub-menu-container {
  display: flex;
  flex-wrap: wrap; /* 自动换行，适配菜单数量变化 */
  gap: 12px; /* 区块之间的横向/纵向间距，区分不同二级菜单 */
  row-gap: 16px;
  padding: 4px 0;
}
/* 二级菜单区块：独立背景，强化区块感 */
.sub-menu-block {
  display: flex;
  flex-direction: column;
  gap: 6px; /* 二级标题与三级菜单间距 */
  padding: 8px 12px;
  background: rgba(255, 255, 255, 0.7); /* 半透明白色背景，适配一级卡片渐变 */
  border-radius: 8px;
  min-width: 180px; /* 最小宽度，避免区块过窄 */
  flex: 1; /* 自适应宽度，最大宽度不超过父容器 */
  max-width: calc(50% - 6px); /* 最多一行2个区块，适配大屏 */
}
/* 二级菜单标题：加粗+深色，区分三级菜单 */
.sub-menu-title {
  font-size: 14px;
  font-weight: 600;
  color: #37474f;
  transition: color 0.2s;
}
.sub-menu-title:hover {
  color: #d32f2f; /* hover变色，增强交互反馈（仅无子菜单时生效，因有子菜单时cursor为default） */
}
/* 三级菜单列表：横向紧凑排列，适配数量 */
.third-menu-list {
  display: flex;
  flex-wrap: wrap;
  gap: 6px; /* 三级菜单之间间距，紧凑不拥挤 */
  row-gap: 4px;
}
/* 三级菜单项：轻量样式，适配区块背景 */
.third-menu-item {
  padding: 4px 8px;
  background: rgba(255, 255, 255, 0.9);
  border-radius: 4px;
  font-size: 13px;
  color: #546e7a;
  cursor: pointer;
  transition: all 0.2s;
  white-space: nowrap; /* 防止文字换行，保持横向排列 */
}
.third-menu-item:hover {
  background: #fff;
  color: #d32f2f;
  transform: translateY(-1px); /* 轻微上浮，增强交互感 */
}
/* 响应式适配：小屏幕优化区块布局 */
@media (max-width: 768px) {
  .data-menu-page {
    padding: 12px 8px;
  }
  .menu-header {
    margin-bottom: 8px;
  }
  .menu-title {
    font-size: 15px;
  }
  .sub-menu-container {
    gap: 8px;
    row-gap: 12px;
  }
  .sub-menu-block {
    min-width: 140px;
    max-width: 100%; /* 小屏幕一行1个区块，避免挤压 */
    padding: 6px 10px;
  }
  .third-menu-item {
    font-size: 12px;
    padding: 3px 6px;
  }
}
/* 超小屏幕适配（手机） */
@media (max-width: 375px) {
  .sub-menu-block {
    min-width: 100%; /* 强制一行1个区块 */
  }
  .third-menu-list {
    gap: 4px;
  }
}
</style>
