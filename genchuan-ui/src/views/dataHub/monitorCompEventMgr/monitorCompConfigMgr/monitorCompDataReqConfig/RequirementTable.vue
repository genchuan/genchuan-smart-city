<template>
  <div>
    <el-table
      :data="list"
      stripe
      border
      style="width:100%"
    >
      <el-table-column prop="dataReqId" label="配置ID" width="220" />
      <el-table-column prop="componentCategoryName" label="部件分类" width="180" />
      <el-table-column prop="dataType" label="数据类型" width="120">
        <template #default="{ row }">
          <el-tag size="small" type="info">{{ row.dataType === 'spatial' ? '空间数据' : '属性数据' }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column prop="summary" label="说明/摘要" />
      <el-table-column prop="createUser" label="创建人" width="140" />
      <el-table-column prop="createTime" label="创建时间" width="180" sortable />

      <el-table-column label="操作" width="220" fixed="right">
        <template #default="{ row }">
          <el-button size="mini" type="primary" @click="$emit('edit', row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="onDelete(row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="footer-bar">
      <el-pagination
        background
        :current-page="pagination.page"
        :page-size="pagination.pageSize"
        :total="pagination.total"
        layout="total, prev, pager, next, sizes, jumper"
        :page-sizes="[10, 20, 50]"
        @size-change="onPageSizeChange"
        @current-change="onPageChange"
      />
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from 'vue';
import { fetchRequirementList, deleteRequirementById } from '@/api/dataHub/monitorCompEventMgr/monitorCompConfigMgr/monitorCompDataReqConfig';;
import { ElMessageBox, ElMessage } from 'element-plus';
// import dayjs from 'dayjs'; // 如果项目没装 dayjs，可把 createTime 展示改为原始字符串

const props = defineProps({
  filters: {
    type: Object,
    default: () => ({})
  }
});

const emit = defineEmits(['edit', 'delete', 'refresh']);

const list = ref([]);
const pagination = ref({
  page: 1,
  pageSize: 10,
  total: 0
});

// 当父组件 filters 变化时自动刷新
watch(() => ({ ...props.filters }), () => {
  loadList(1);
});

async function loadList(page = pagination.value.page) {
  const params = {
    componentCategoryId: props.filters.componentCategoryId || '',
    dataType: props.filters.dataType || '',
    page,
    pageSize: pagination.value.pageSize,
    sort: 'createTime',
    order: 'desc'
  };

  // TODO: 调用真实后端接口 fetchRequirementList(params)
  const res = await fetchRequirementList(params);
  list.value = res.records;
  pagination.value.total = res.total;
  pagination.value.page = page;
}

function onPageChange(page) {
  loadList(page);
}

function onPageSizeChange(size) {
  pagination.value.pageSize = size;
  loadList(1);
}

function onDelete(row) {
  ElMessageBox.confirm(`确认删除配置 "${row.dataReqId}" 吗？（仅在无关联部件实例时允许删除）`, '删除确认', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    // TODO: 后端删除校验（检查是否存在关联部件实例）
    const ok = await deleteRequirementById(row.dataReqId);
    if (ok) {
      ElMessage.success('删除成功');
      emit('delete', { id: row.dataReqId, componentCategoryId: row.componentCategoryId });
      loadList();
    } else {
      ElMessage.error('删除失败：存在关联部件实例或服务器错误');
    }
  }).catch(() => { /* cancel */ });
}

function exportExcel() {
  // TODO: 请求后端导出接口；这里调用 mock 导出函数（直接下载 mock 文件或生成 CSV）
  exportRequirementsExcel({ componentCategoryId: props.filters.componentCategoryId, dataType: props.filters.dataType })
    .then(() => {
      ElMessage.success('导出已触发（模拟）');
    })
    .catch(() => {
      ElMessage.error('导出失败（模拟）');
    });
}

loadList(1);

const exposed = {
  loadList,
  exportExcel
};
defineExpose(exposed);
</script>

<style scoped>
.footer-bar {
  margin-top: 12px;
  display:flex;
  justify-content:flex-end;
}
</style>
