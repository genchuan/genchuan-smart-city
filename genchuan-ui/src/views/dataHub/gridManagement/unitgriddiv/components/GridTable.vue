<template>
  <el-table
    :data="data"
    :loading="loading"
    stripe
    border
    style="width: 100%; table-layout: fixed"
    height="calc(100vh - 350px)"
  >
    <el-table-column prop="unitGridName" label="网格名称" min-width="160" />
    <el-table-column prop="commId" label="社区ID" width="120" />
    <el-table-column prop="area" label="面积(㎡)" width="120" sortable />
    <el-table-column prop="scale" label="比例尺" width="100" />
    <el-table-column prop="divTime" label="划分时间" :formatter="dateFormatter" width="180" />
    <el-table-column prop="remark" label="备注" min-width="150" />

    <el-table-column fixed="right" label="操作" width="280">
      <template #default="{ row }">
        <el-button link type="primary" @click="$emit('view', row)">详情</el-button>
        <el-button link type="primary" @click="$emit('edit', row)">编辑</el-button>
        <el-button link type="danger" @click="$emit('delete', row)">删除</el-button>
        <el-button link type="warning" @click="validateArea(row)">校验面积</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script setup lang="ts">
import { defineProps } from 'vue'
import { ElMessage } from 'element-plus'
import { UnitGridDivApi } from '@/api/dataHub/gridManagement/divideConfig/unitgriddiv'
import { dateFormatter } from '@/utils/formatTime'

defineProps<{
  data: any[]
  loading?: boolean
}>()

const validateArea = async (row: any) => {
  try {
    const res = await UnitGridDivApi.validateArea({ area: row.area, commId: row.commId })
    if (res) ElMessage.success('面积校验通过')
    else ElMessage.warning('面积校验未通过')
  } catch (e: any) {
    ElMessage.error('校验失败: ' + (e.message || ''))
  }
}
</script>

<style scoped>
.el-table {
  width: 100%;
  font-size: 14px;
  word-break: break-all;
  table-layout: fixed !important;
}

.el-table th,
.el-table td {
  text-align: center;
  white-space: normal !important;
  word-wrap: break-word;
  padding: 10px 6px;
}
</style>
