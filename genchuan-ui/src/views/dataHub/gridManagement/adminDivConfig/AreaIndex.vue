<template>
  <el-row :gutter="20">
    <!-- 左侧树 -->
    <el-col :span="5" :xs="24">
      <ContentWrap class="h-1/1">
        <AreaTree
          ref="areaTreeRef"
          v-model:filterText="treeFilterText"
          @node-selected="handleTreeNodeSelect"
        />
      </ContentWrap>
    </el-col>

    <!-- 右侧表 -->
    <el-col :span="18" :xs="24">
      <ContentWrap>
        <!-- 查询区 -->
        <el-form
          :model="queryParams"
          ref="queryFormRef"
          :inline="true"
          label-width="120px"
          class="-mb-15px"
        >
          <el-form-item label="上级行政区划ID">
            <el-input
              v-model="queryParams.parentId"
              placeholder="输入上级ID"
              clearable
              style="width: 160px"
              @keyup.enter="handleQuery"
            />
          </el-form-item>

          <el-form-item label="行政区划代码">
            <el-input
              v-model="queryParams.fullCode"
              placeholder="输入完整代码"
              clearable
              style="width: 180px"
              @keyup.enter="handleQuery"
            />
          </el-form-item>

          <el-form-item label="短代码">
            <el-input
              v-model="queryParams.shortCode"
              placeholder="输入短代码"
              clearable
              style="width: 180px"
              @keyup.enter="handleQuery"
            />
          </el-form-item>

          <el-form-item label="行政区划名称">
            <el-input
              v-model="queryParams.name"
              placeholder="输入名称"
              clearable
              style="width: 200px"
              @keyup.enter="handleQuery"
            />
          </el-form-item>

          <el-form-item label="行政级别">
            <el-select
              v-model="queryParams.level"
              placeholder="请选择级别"
              clearable
              style="width: 160px"
            >
              <el-option label="省级 (1)" :value="1" />
              <el-option label="市级 (2)" :value="2" />
              <el-option label="县级 (3)" :value="3" />
              <el-option label="乡镇 (4)" :value="4" />
              <el-option label="社区 (5)" :value="5" />
            </el-select>
          </el-form-item>

          <el-form-item label="排序">
            <el-select
              v-model="sortBy"
              placeholder="请选择排序方式"
              clearable
              style="width: 180px"
              @change="handleQuery"
            >
              <el-option label="生效时间倒序" value="effective_desc" />
              <el-option label="生效时间正序" value="effective_asc" />
            </el-select>
          </el-form-item>

          <el-form-item>
            <el-button @click="handleQuery">
              <Icon icon="ep:search" class="mr-5px" /> 搜索
            </el-button>
            <el-button @click="resetQuery">
              <Icon icon="ep:refresh" class="mr-5px" /> 重置
            </el-button>
            <el-button type="primary" plain @click="openForm('create')">
              <Icon icon="ep:plus" class="mr-5px" /> 新增
            </el-button>
            <el-button
              type="success"
              plain
              class="ml-8px"
              :loading="exportLoading"
              @click="handleExport"
            >
              <Icon icon="ep:download" class="mr-5px" /> 导出
            </el-button>
          </el-form-item>
        </el-form>
      </ContentWrap>

      <!-- 表格展示 -->
      <ContentWrap>
        <el-table v-loading="loading" :data="list" stripe style="width: 100%">
          <el-table-column prop="id" label="主键ID" align="center" width="90" />
          <el-table-column prop="parentId" label="上级ID" align="center" width="100" />
          <el-table-column prop="fullCode" label="完整代码" align="center" width="150" />
          <el-table-column prop="shortCode" label="短代码" align="center" width="140" />
          <el-table-column prop="name" label="行政区划名称" align="center" />
          <el-table-column prop="level" label="层级" align="center" width="120" />
          <el-table-column prop="areaType" label="类型" align="center" width="140" />
          <el-table-column
            label="生效时间"
            align="center"
            prop="effectiveTime"
            :formatter="dateFormatter"
            width="180px"
          />
          <el-table-column
            label="失效时间"
            align="center"
            prop="invalidTime"
            :formatter="dateFormatter"
            width="180px"
          />
          <el-table-column prop="remark" label="备注" align="center" />
          <el-table-column label="是否生效" align="center" width="110">
            <template #default="{ row }">
              <el-tag v-if="isEffective(row)" type="success">生效</el-tag>
              <el-tag v-else type="info">已失效</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" width="220">
            <template #default="{ row }">
              <el-button type="primary" link @click="openForm('update', row.id)">编辑</el-button>
              <el-button
                v-if="canDelete(row) && !row.hasChildren"
                link
                type="danger"
                @click="handleDelete(row)"
              >
                删除
              </el-button>
              <el-button link type="primary" @click="openDetail(row)">详情</el-button>
            </template>
          </el-table-column>
        </el-table>

        <!-- 分页 -->
        <Pagination
          :total="total"
          v-model:page="queryParams.pageNo"
          v-model:limit="queryParams.pageSize"
          @pagination="getList"
        />
      </ContentWrap>
    </el-col>
  </el-row>

  <AreaForm ref="formRef" @success="getList" />
  <AreaDetailDrawer v-model="detailVisible" :id="currentId" />
</template>

<script lang="ts" setup>
import AreaTree from './AreaTree.vue'
import AreaForm from './AreaForm.vue'
import { AreaApi, AreaVO } from '@/api/dataHub/gridManagement/adminDivConfig'
import download from '@/utils/download'
import { dateFormatter } from '@/utils/formatTime'
import AreaDetailDrawer from './AreaDetailDrawer.vue'
import { ElMessageBox } from 'element-plus'

const message = useMessage()
const areaTreeRef = ref()
const formRef = ref()
const queryFormRef = ref()
const loading = ref(false)
const exportLoading = ref(false)
const list = ref<AreaVO[]>([])
const total = ref(0)
const treeFilterText = ref('')
const sortBy = ref<string>('') // 默认不排序

// 查询参数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  parentId: undefined,
  fullCode: undefined,
  shortCode: undefined,
  name: undefined,
  level: undefined
})

/** 详情 */
const detailVisible = ref(false)
const currentId = ref<number>()
const openDetail = (row: any) => {
  currentId.value = row.id
  detailVisible.value = true
}

/** 判断是否生效 */
const isEffective = (row: AreaVO) => {
  const now = Date.now()
  const eff = row.effectiveTime ? new Date(row.effectiveTime).getTime() : null
  const inv = row.invalidTime ? new Date(row.invalidTime).getTime() : null
  if (eff && now < eff) return false
  if (!inv) return true
  return now <= inv
}

/** 是否可删除（仅失效数据） */
const canDelete = (row: AreaVO) => {
  if (!row.invalidTime) return false
  const inv = new Date(row.invalidTime).getTime()
  return Date.now() > inv
}

/** 获取分页数据 */
const getList = async () => {
  loading.value = true
  try {
    const params = {
      pageNo: queryParams.pageNo,
      pageSize: queryParams.pageSize,
      parentId: queryParams.parentId,
      fullCode: queryParams.fullCode,
      shortCode: queryParams.shortCode,
      name: queryParams.name,
      level: queryParams.level
    }

    const resp = await AreaApi.getAreaPage(params)
    let rows = resp.list || []

    // 排序逻辑（用户未选择则不排序）
    if (sortBy.value === 'effective_desc') {
      rows.sort((a: AreaVO, b: AreaVO) => new Date(b.effectiveTime).getTime() - new Date(a.effectiveTime).getTime())
    } else if (sortBy.value === 'effective_asc') {
      rows.sort((a: AreaVO, b: AreaVO) => new Date(a.effectiveTime).getTime() - new Date(b.effectiveTime).getTime())
    }

    // 标记父节点
    list.value = await Promise.all(
      rows.map(async (item: AreaVO) => {
        try {
          const children = await AreaApi.getTreeByParent(item.id)
          return { ...item, hasChildren: Array.isArray(children) && children.length > 0 }
        } catch {
          return { ...item, hasChildren: false }
        }
      })
    )
    total.value = resp.total ?? rows.length
  } catch (e) {
    console.error(e)
    message.error('加载失败')
  } finally {
    loading.value = false
  }
}

/** 查询 */
const handleQuery = () => {
  queryParams.pageNo = 1
  getList()
}

/** 重置 */
const resetQuery = () => {
  queryFormRef.value?.resetFields()
  treeFilterText.value = ''
  Object.assign(queryParams, {
    parentId: undefined,
    fullCode: undefined,
    shortCode: undefined,
    name: undefined,
    level: undefined
  })
  sortBy.value = ''
  handleQuery()
}

/** 树节点选择 */
const handleTreeNodeSelect = (node: any) => {
  queryParams.parentId = node?.id
  queryParams.pageNo = 1
  getList()
}

/** 打开表单 */
const openForm = (type: string, id?: number) => {
  formRef.value.open(type, id)
}

/** 删除 */
const handleDelete = async (row: AreaVO) => {
  try {
    if (!canDelete(row)) {
      message.warning('仅允许删除已失效的行政区划！')
      return
    }
    await ElMessageBox.confirm(
      `确认删除 ID=${row.id} 的行政区划？仅允许删除已失效的数据。`,
      '警告',
      { type: 'warning' }
    )
    await AreaApi.deleteArea(row.id)
    message.success('删除成功')
    await getList()
    areaTreeRef.value?.reload()
  } catch {}
}

/** 导出 */
const handleExport = async () => {
  try {
    await ElMessageBox.confirm('确认导出当前查询结果？', '导出确认', { type: 'info' })
    exportLoading.value = true
    const data = await AreaApi.exportArea({
      parentId: queryParams.parentId,
      fullCode: queryParams.fullCode,
      shortCode: queryParams.shortCode,
      name: queryParams.name,
      level: queryParams.level
    })
    download.excel(data, '统一行政区划导出.xls')
  } finally {
    exportLoading.value = false
  }
}

onMounted(() => {
  getList()
})
</script>
