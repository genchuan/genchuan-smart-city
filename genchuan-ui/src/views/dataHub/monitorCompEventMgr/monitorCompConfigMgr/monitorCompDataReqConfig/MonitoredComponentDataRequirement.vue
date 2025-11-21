<template>
  <div class="app-container">
    <el-card>
      <template #header>
        <div class="flex justify-between items-center">
          <span>监测部件数据要求配置</span>
          <el-button type="primary" @click="openAddDialog">新增配置</el-button>
        </div>
      </template>

      <el-form :inline="true" class="mb-2">
        <el-form-item label="部件分类">
          <el-select v-model="query.category" placeholder="请选择分类" clearable>
            <el-option
              v-for="c in categoryList"
              :key="c.id"
              :label="c.name"
              :value="c.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="数据类型">
          <el-select v-model="query.dataType" placeholder="请选择类型" clearable>
            <el-option label="空间数据" value="spatial" />
            <el-option label="属性数据" value="attribute" />
          </el-select>
        </el-form-item>
        <el-button type="primary" @click="handleQuery">查询</el-button>
      </el-form>

      <el-table :data="tableData" border stripe v-loading="loading">
        <el-table-column prop="componentCategoryName" label="部件分类" />
        <el-table-column prop="dataType" label="数据类型">
          <template #default="{ row }">
            {{ row.dataType === 'spatial' ? '空间数据' : '属性数据' }}
          </template>
        </el-table-column>
        <el-table-column prop="summary" label="说明" />
        <el-table-column prop="createUser" label="创建人" width="100" />
        <el-table-column prop="createTime" label="创建时间" width="160" />
        <el-table-column label="操作" width="180">
          <template #default="{ row }">
            <el-button size="small" @click="openEditDialog(row)">编辑</el-button>
            <el-button size="small" type="danger" @click="handleDelete(row.dataReqId)">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
        class="mt-3 text-center"
        background
        layout="prev, pager, next"
        :total="total"
        :page-size="10"
        @current-change="handlePageChange"
      />
    </el-card>

    <DataReqForm
      v-if="dialogVisible"
      :visible="dialogVisible"
      :editData="editRow"
      @close="dialogVisible = false; loadData()"
    />
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import {
  fetchCategoryList,
  fetchRequirementList,
  deleteRequirementById
} from '@/api/dataHub/monitorCompEventMgr/monitorCompConfigMgr/monitorCompDataReqConfig';
import DataReqForm from './DataReqForm.vue';

const categoryList = ref<any[]>([]);
const tableData = ref<any[]>([]);
const query = ref({ category: '', dataType: '' });
const total = ref(0);
const page = ref(1);
const loading = ref(false);
const dialogVisible = ref(false);
const editRow = ref<any>(null);

onMounted(async () => {
  categoryList.value = await fetchCategoryList();
  await loadData();
});

async function loadData() {
  loading.value = true;
  const { records, total: t } = await fetchRequirementList({
    componentCategoryId: query.value.category,
    dataType: query.value.dataType
  });
  tableData.value = records;
  total.value = t;
  loading.value = false;
}

function handleQuery() {
  page.value = 1;
  loadData();
}

function handlePageChange(p: number) {
  page.value = p;
  loadData();
}

function openAddDialog() {
  editRow.value = null;
  dialogVisible.value = true;
}

function openEditDialog(row: any) {
  editRow.value = row;
  dialogVisible.value = true;
}

async function handleDelete(id: string) {
  await deleteRequirementById(id);
  await loadData();
}
</script>
