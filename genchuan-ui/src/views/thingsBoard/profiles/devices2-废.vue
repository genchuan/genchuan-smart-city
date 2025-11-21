<template>
  <div class="pagination-container">
    <el-card>
      <div class="card-header">
        <h2>前端分页示例 (1000条数据)</h2>
        <el-input
            v-model="searchQuery"
            placeholder="搜索内容"
            clearable
            class="search-input"
            @input="handleSearch"
        />
      </div>

      <el-table
          :data="currentPageData"
          border
          stripe
          style="width: 100%; margin-bottom: 20px;"
          v-loading="loading"
      >
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="name" label="姓名" />
        <el-table-column prop="email" label="邮箱" />
        <el-table-column prop="age" label="年龄" width="80" />
        <el-table-column prop="address" label="地址" />
        <el-table-column prop="registerDate" label="注册日期" />
      </el-table>

      <div class="pagination-footer">
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[10, 20, 50, 100]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="filteredData.length"
        />
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import { ElTable, ElTableColumn, ElPagination, ElInput, ElCard, ElLoading } from 'element-plus';

// 分页相关变量
const currentPage = ref(1);
const pageSize = ref(10);
const searchQuery = ref('');
const loading = ref(true);

// 原始数据和过滤后的数据
const rawData = ref([]);
const filteredData = ref([]);

// 生成1000条测试数据
const generateMockData = () => {
  const data = [];
  for (let i = 1; i <= 1000; i++) {
    data.push({
      id: i,
      name: `用户${i}`,
      email: `user${i}@example.com`,
      age: Math.floor(Math.random() * 50) + 18,
      address: `地址${i}号，测试街道，测试城市`,
      registerDate: new Date(Date.now() - Math.random() * 31536000000).toLocaleDateString()
    });
  }
  return data;
};

// 处理搜索
const handleSearch = () => {
  if (!searchQuery.value) {
    filteredData.value = [...rawData.value];
    currentPage.value = 1; // 重置到第一页
    return;
  }

  const query = searchQuery.value.toLowerCase();
  filteredData.value = rawData.value.filter(item =>
      item.name.toLowerCase().includes(query) ||
      item.email.toLowerCase().includes(query) ||
      item.address.toLowerCase().includes(query)
  );
  currentPage.value = 1; // 重置到第一页
};

// 计算当前页的数据
const currentPageData = computed(() => {
  const startIndex = (currentPage.value - 1) * pageSize.value;
  return filteredData.value.slice(startIndex, startIndex + pageSize.value);
});

// 处理每页条数变化
const handleSizeChange = (val) => {
  pageSize.value = val;
  currentPage.value = 1; // 重置到第一页
};

// 处理页码变化
const handleCurrentChange = (val) => {
  currentPage.value = val;
  // 滚动到表格顶部
  document.querySelector('.el-table').scrollTop = 0;
};

// 初始化数据
onMounted(() => {
  // 模拟加载延迟
  setTimeout(() => {
    rawData.value = generateMockData();
    filteredData.value = [...rawData.value];
    loading.value = false;
  }, 500);
});
</script>

<style scoped>
.pagination-container {
  max-width: 1200px;
  margin: 20px auto;
  padding: 0 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.search-input {
  width: 300px;
}

.pagination-footer {
  display: flex;
  justify-content: flex-end;
  margin-top: 10px;
}

h2 {
  margin: 0;
  font-size: 1.5rem;
}
</style>
