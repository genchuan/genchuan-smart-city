<template>
  <ContentWrap>
    <!-- 搜索栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="68px"
    >
      <el-form-item label="大类代码" prop="majorCode">
        <el-input
          v-model="queryParams.majorCode"
          placeholder="请输入大类代码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>

      <el-form-item label="大类名称" prop="majorName">
        <el-input
          v-model="queryParams.majorName"
          placeholder="请输入大类名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>

      <el-form-item>
        <el-button @click="handleQuery"><Icon icon="ep:search" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" /> 重置</el-button>
        <el-button type="primary" plain @click="openForm('create')">
          <Icon icon="ep:plus" /> 新增
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" stripe>
      <el-table-column label="大类ID" align="center" prop="majorId">
        <template #default="{ row }">
          <span
            class="text-blue-600 cursor-pointer hover:underline"
            @click="handleIdClick(row)"
          >
            {{ row.majorId }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="大类名称" align="center" prop="majorName" />
      <el-table-column label="大类说明" align="center" prop="majorDesc" />
      <el-table-column label="排序序号" align="center" prop="sortNum" />
      <el-table-column label="创建人" align="center" prop="createUser" />
      <el-table-column label="创建时间" align="center" prop="createTime" />
      <el-table-column label="操作" align="center" width="180">
        <template #default="{ row }">
          <el-button link type="primary" @click="openForm('update', row.id)">编辑</el-button>
          <el-button link type="danger" @click="handleDelete(row.id)">删除</el-button>
          <el-button link type="info" @click="handleDetailClick(row)">详情</el-button>
        </template>
      </el-table-column>
    </el-table>
  </ContentWrap>

  <!-- ✅ 伪抽屉（同层右侧显示） -->
  <transition name="slide">
    <div v-if="drawerVisible" class="drawer-panel" :key="drawerKey">
      <div class="drawer-header">
        <h3 class="text-lg font-semibold">大类详情</h3>
        <el-button link type="danger" @click="drawerVisible = false">关闭</el-button>
      </div>

      <div class="drawer-body">
        <el-descriptions v-if="currentDetail" :column="2" border>
          <el-descriptions-item label="大类ID">{{ currentDetail.majorId }}</el-descriptions-item>
          <el-descriptions-item label="大类代码">{{ currentDetail.majorCode }}</el-descriptions-item>
          <el-descriptions-item label="大类名称">{{ currentDetail.majorName }}</el-descriptions-item>
          <el-descriptions-item label="大类说明">{{ currentDetail.majorDesc }}</el-descriptions-item>
          <el-descriptions-item label="排序序号">{{ currentDetail.sortNum }}</el-descriptions-item>
          <el-descriptions-item label="创建人">{{ currentDetail.createUser }}</el-descriptions-item>
          <el-descriptions-item label="创建时间">{{ currentDetail.createTime }}</el-descriptions-item>
        </el-descriptions>
      </div>
    </div>
  </transition>

  <ManagedComponentMajorConfigForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import ManagedComponentMajorConfigForm from './ManagedComponentMajorConfigForm.vue'

interface ManagedComponentMajorConfigVO {
  majorId: number
  majorCode: string
  majorName: string
  majorDesc: string
  sortNum: number
  createUser: string
  createTime: string
  updateUser: string
  id?: number
}

const loading = ref(true)
const list = ref<ManagedComponentMajorConfigVO[]>([])
const total = ref(0)
const formRef = ref()

// ✅ 抽屉逻辑
const drawerVisible = ref(false)
const currentDetail = ref<ManagedComponentMajorConfigVO | null>(null)
const drawerKey = ref(0)

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  majorCode: '',
  majorName: ''
})

// 模拟数据加载
const getList = async () => {
  loading.value = true
  list.value = [
    { majorId: 1001, majorCode: 'MC-001', majorName: '机械部件', majorDesc: '机械类部件', sortNum: 1, createUser: 'admin', createTime: '2024-01-01', updateUser: 'admin', id: 1 },
    { majorId: 1002, majorCode: 'MC-002', majorName: '电气部件', majorDesc: '电气类部件', sortNum: 2, createUser: 'admin', createTime: '2024-01-02', updateUser: 'admin', id: 2 },
    { majorId: 1003, majorCode: 'MC-003', majorName: '液压系统', majorDesc: '液压类系统', sortNum: 3, createUser: 'admin', createTime: '2024-01-03', updateUser: 'admin', id: 3 }
  ]
  total.value = list.value.length
  loading.value = false
}

// ✅ 点击蓝色大类ID / 详情按钮 都执行此函数
function openDetail(row: ManagedComponentMajorConfigVO) {
  currentDetail.value = row
  drawerKey.value++ // 强制刷新
  drawerVisible.value = true
}

function handleIdClick(row: ManagedComponentMajorConfigVO) {
  openDetail(row)
}
function handleDetailClick(row: ManagedComponentMajorConfigVO) {
  openDetail(row)
}
// ✅ ESC 键关闭抽屉
function handleEsc(event: KeyboardEvent) {
  if (event.key === 'Escape' && drawerVisible.value) {
    drawerVisible.value = false
  }
}

onMounted(() => {
  getList()
  window.addEventListener('keydown', handleEsc)
})

onBeforeUnmount(() => {
  window.removeEventListener('keydown', handleEsc)
})


const handleQuery = () => getList()
const resetQuery = () => {
  Object.assign(queryParams, { pageNo: 1, pageSize: 10, majorCode: '', majorName: '' })
  getList()
}
const openForm = (type: string, id?: number) => formRef.value?.open(type, id)
const handleDelete = (id: number) => console.log('删除', id)

onMounted(getList)
</script>

<style scoped>
/* ✅ 同层抽屉样式 */
.drawer-panel {
  position: fixed;
  top: 0;
  right: 0;
  height: 100%;
  width: 75%;
  background: #fff;
  border-left: 1px solid #dcdfe6;
  box-shadow: -2px 0 8px rgba(0, 0, 0, 0.1);
  z-index: 10;
  display: flex;
  flex-direction: column;
}
.drawer-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px;
  border-bottom: 1px solid #ebeef5;
}
.drawer-body {
  padding: 20px;
  overflow-y: auto;
  flex: 1;
}

/* ✅ 滑入动画 */
.slide-enter-active,
.slide-leave-active {
  transition: all 0.35s ease;
}
.slide-enter-from,
.slide-leave-to {
  transform: translateX(100%);
}

</style>


