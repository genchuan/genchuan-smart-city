<template>
  <div>
    <el-input v-model="assetAllocationName" style="width: 240px" placeholder="资产配置名称"/>
    <el-button @click="addClick">添加资产配置</el-button>
  </div>
  <div>
    资产配置列表
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="name" label="名称" width="180"/>
      <el-table-column prop="createdTime" label="创建日期"/>
      <el-table-column>
        <template #default="scope">

          <el-button
            size="small"
            type="danger"
            @click="editClick(scope.row)"
          >
            edit
          </el-button>

          <el-button
            size="small"
            type="danger"
            @click="delClick(scope.row)"
          >
            Delete
          </el-button>
        </template>
      </el-table-column>
    </el-table>


  </div>
</template>

<!-- vue3写法 -->
<script lang="ts" setup>
import {ref, reactive, onMounted} from 'vue'
import {
  assetProfileAddEdit,
  assetProfilesDel,
  assetProfilesQueryList
} from "@/api/genchuan/thingsBoard/profiles";

// 挂载完毕
onMounted(() => {
  console.log('挂载完毕')
  initData();
})

// 项目初始化都在这
const initData = () => {
  tableQueryList();
}

// 添加资产配置
let assetAllocationName = ref('');//资产配置名称
const addClick = async () => {
  let data = {
    name:'',//资产配置名称
    "image": null,
    "defaultRuleChainId": null,
    "defaultDashboardId": null,
    "defaultQueueName": null,
    "defaultEdgeRuleChainId": null,
    description: null,// 说明
  }
  try {
    await assetProfileAddEdit(data);
    await tableQueryList();
  } catch (error) {
    console.log(123123)
  }
}

const editClick=async (row)=>{
  const plainRow = JSON.parse(JSON.stringify(row));
  plainRow.name=555555;
  console.log(plainRow.name)
  await assetProfileAddEdit(plainRow);
  await tableQueryList();
}

//资产配置列表
let tableData = ref([]);
const tableQueryList = async () => {
  try {
    let params = {
      pageSize: 10,
      page: 0,
      sortProperty: 'createdTime',
      sortOrder: 'DESC'
    }
    let res = await assetProfilesQueryList(params);
    tableData.value = res.data
  } catch (error) {
    // 处理错误
    console.log(error)
  }
}

//资产配置删除
const delClick = async (row) => {
  try {
    await assetProfilesDel(row.id.id)
    await tableQueryList();
  } catch (error) {
    // 处理错误
    console.log(error)
  }
}


</script>

<style lang="scss" scoped></style>
