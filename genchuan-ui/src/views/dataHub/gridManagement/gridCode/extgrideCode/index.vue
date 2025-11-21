<template>
  <ContentWrap>
    <!-- 查询区 -->
    <el-form :model="queryParams" ref="queryFormRef" :inline="true" label-width="120px">
      <el-form-item label="扩展网格标识码">
        <el-input
          v-model="queryParams.extGridCode"
          placeholder="请输入扩展网格标识码"
          clearable
          class="!w-220px"
        />
      </el-form-item>

      <el-form-item label="类型码">
        <el-select
          v-model="queryParams.typeCode"
          placeholder="请选择类型"
          clearable
          class="!w-160px"
        >
          <el-option label="工业园区(G)" value="G" />
          <el-option label="自贸区(Z)" value="Z" />
          <el-option label="商圈(S)" value="S" />
          <el-option label="其他(Q)" value="Q" />
        </el-select>
      </el-form-item>

      <el-form-item label="编码状态">
        <el-select
          v-model="queryParams.codeStatus"
          placeholder="请选择状态"
          clearable
          class="!w-160px"
        >
          <el-option label="有效" value="有效" />
          <el-option label="激活" value="ACTIVE" />
          <el-option label="待审核" value="待审核" />
<!--          <el-option label="作废" value="作废" />-->
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="handleQuery"><Icon icon="ep:search" /> 查询</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" /> 重置</el-button>
        <el-button type="success" plain @click="handleExport" :loading="exportLoading">
          <Icon icon="ep:download" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表区 -->
  <ContentWrap>
    <el-table
      v-loading="loading"
      :data="list"
      :default-sort="{ prop: 'generateTime', order: 'descending' }"
      border
      stripe
    >
      <el-table-column prop="extCodeId" label="编码ID" align="center" width="180" />
      <el-table-column prop="extGridCode" label="扩展网格标识码" align="center" width="200" />
      <el-table-column prop="extGridId" label="所属扩展网格ID" align="center" />
      <el-table-column prop="areaFullCode" label="行政区划完整代码" align="center" />
      <el-table-column prop="seqCode" label="顺序码" align="center" width="100" />

      <!-- 类型码带颜色 -->
      <el-table-column label="类型码" align="center" width="120">
        <template #default="scope">
          <el-tag :type="getTypeTag(scope.row.typeCode)" round>
            {{ scope.row.typeCode }}
          </el-tag>
        </template>
      </el-table-column>

      <!-- 生成时间 -->
      <el-table-column
        prop="generateTime"
        label="生成时间"
        align="center"
        :formatter="dateFormatter"
        sortable
        width="180"
      />

      <!-- 状态：绿色/红色 -->
      <el-table-column label="状态" align="center" width="100">
        <template #default="scope">
          <el-tag :type="scope.row.codeStatus === '有效' ? 'success' : 'danger'">
            {{ scope.row.codeStatus }}
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column prop="remark" label="备注" align="center" />

      <!-- 操作 -->
      <el-table-column label="操作" align="center" width="120">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row)">详情</el-button>
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

  <!-- 详情抽屉 -->
  <ExtGridCodeDetail ref="detailRef" />
</template>

<script setup lang="ts">
import { ref, reactive, nextTick, onMounted } from 'vue'
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { ExtGridCodeApi, ExtGridCodeVO } from '@/api/dataHub/gridManagement/gridCode/extgridcode'
import ExtGridCodeDetail from './ExtGridCodeDetail.vue'

const loading = ref(false)
const list = ref<ExtGridCodeVO[]>([])
const total = ref(0)
const exportLoading = ref(false)
const queryFormRef = ref()
const detailRef = ref()

// 初始化为“空字符串”，保证 el-select/el-input 在 reset 后能正确显示为空
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  extGridCode: '' as string,
  typeCode: '' as string,
  codeStatus: '' as string,
})

// 类型码颜色映射（返回 Element Plus 支持的 tag type）
const getTypeTag = (type?: string) => {
  switch (type) {
    case 'G': return 'success'   // 工业园区 -> 绿色
    case 'Z': return 'primary'   // 自贸区 -> 蓝色
    case 'S': return 'danger'    // 商圈 -> 红色
    case 'Q': return 'info'      // 其他 -> 灰色
    default: return undefined           // undefined 让 el-tag 使用默认样式（不会报类型错误）
  }
}

/** 获取列表 */
const getList = async () => {
  loading.value = true
  try {
    // 如果后端不喜欢空串参数，可以在这里转换（例如去掉空串字段）
    const params = {
      ...queryParams,
      // optional: strip empty strings to undefined for backend
      extGridCode: queryParams.extGridCode || undefined,
      typeCode: queryParams.typeCode || undefined,
      codeStatus: queryParams.codeStatus || undefined,
    }
    const data = await ExtGridCodeApi.getExtGridCodePage(params)
    list.value = data.list || []
    total.value = data.total || 0
  } finally {
    loading.value = false
  }
}

/** 查询、重置、导出逻辑 */
const handleQuery = () => {
  queryParams.pageNo = 1
  getList()
}

/** 稳妥的重置：
 * 1. 先把所有查询字段手动清空（兼容 el-select）
 * 2. 清除校验状态并等待 nextTick 更新
 * 3. 刷新列表
 */
const resetQuery = async () => {
  // 手动清空绑定字段（确保组件内部 v-model 真实更新）
  queryParams.extGridCode = ''
  queryParams.typeCode = ''
  queryParams.codeStatus = ''
  queryParams.pageNo = 1

  // 清除验证信息（若有）
  queryFormRef.value?.clearValidate?.()

  // 等 DOM/响应式稳定后刷新
  await nextTick()
  getList()
}

const handleExport = async () => {
  exportLoading.value = true
  try {
    const params = {
      ...queryParams,
      extGridCode: queryParams.extGridCode || undefined,
      typeCode: queryParams.typeCode || undefined,
      codeStatus: queryParams.codeStatus || undefined,
    }
    const data = await ExtGridCodeApi.exportExtGridCode(params)
    download.excel(data, '扩展网格编码.xls')
  } finally {
    exportLoading.value = false
  }
}

/** 打开详情 */
const openDetail = (row: ExtGridCodeVO) => detailRef.value.open(row)

onMounted(() => getList())
</script>
