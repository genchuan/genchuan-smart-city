<template>
  <div class="cc-main">
    <div class="cc-main-content">
      <!--title-->
      <div class="cc-title-css">
        <div class="left">
          <el-icon class="left-icon">
            <Tickets/>
          </el-icon>
          <!--标题名称-->
          <span>场景分类设置</span>
        </div>
        <!--右边的按钮-->
        <div class="right">
          <el-button type="primary" plain :icon="Plus" @click="addClick()">新增场景分类</el-button>
        </div>
      </div>

      <!--搜索内容字段-->
      <div class="cc-search-css" ref="searchRef">
        <el-form class="-mb-15px" :model="searchForm" ref="searchFormRef" :inline="true" label-width="68px">
          <!--@keyup.enter="handleQuery"-->
          <el-form-item label="场景名称" prop="name">
            <el-input v-model="searchForm.name" placeholder="请输入场景名称" clearable class="!w-240px"/>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="searchClick">
              <Icon icon="ep:search" class="mr-5px"/>
              搜索
            </el-button>
            <el-button @click="searchResetClick">
              <Icon icon="ep:refresh" class="mr-5px"/>
              重置
            </el-button>
          </el-form-item>
        </el-form>
      </div>

      <div class="cc-table-css">
        <el-table
            ref="tableRef"
            :data="tableData"
            style="width: 100%;"
            row-key="id"
            border
            default-expand-all
            :header-cell-style="getHeaderCellStyle"
            v-loading="tableConfig.loading"
            :max-height="tableConfig.height"
        >
          <el-table-column prop="name" label="场景名称" align="left" sortable/>
          <el-table-column prop="deviceConfigName" label="设备配置名称" align="center" sortable width="150"/>
          <el-table-column prop="assetConfigName" label="资产配置名称" align="center" sortable width="150"/>
          <el-table-column prop="flowConfigName" label="流程配置名称" align="center" sortable width="150"/>
          <el-table-column width="400" fixed="right" label="操作" align="center">
            <template #default="scope">
              <el-button type="success" :icon="View" :plain="true" @click="queryClick(scope.row)">详细</el-button>
              <el-button type="warning" :icon="EditPen" :plain="true" @click="editClick(scope.row)">编辑</el-button>
              <el-button type="primary" :icon="Plus" :plain="true" @click="addClick(scope.row)">新增</el-button>
              <el-button type="danger" :icon="Delete" :plain="true" @click="delClick(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>

        <!--分页-->
<!--        <div class="cc-table-css-page">
          <el-pagination
              @size-change="pageSizeChange"
              @current-change="pageCurrentChange"
              :page-size="tableConfig.pageSize"
              :page-sizes="[10, 20, 50, 100,500,1000]"
              layout="total,sizes, prev, pager, next, jumper"
              :current-page="tableConfig.currentPage"
              :total="tableConfig.total"
          />
        </div>-->
      </div>

      <!--抽屉-->
      <el-drawer
          :title="drawerConfig.title"
          v-model="drawerConfig.isDrawer"
          direction="rtl"
          :append-to-body="true"
          class="cc-drawer-css"
          size="1200px"
      >
        <!--头部配置-->
        <template header>
          <span>{{ drawerConfig.title }}</span>
        </template>

        <div class="drawer-content">
          <!--查看 li添加class width100 width50 width33 就可以一列 二列 三列-->
          <div class="see-content" v-loading="drawerConfig.loading" v-if="drawerConfig.type==='query'">
            <ul>
              <li class="width100"><span>场景名称：</span> {{ formData.name }}</li>
              <li class="width100"><span>设备配置名称：</span>{{ formData.deviceConfigName }}</li>
              <li class="width100"><span>资产配置名称：</span> {{ formData.assetConfigName }}</li>
              <li class="width100"><span>流程配置名称：</span> {{ formData.flowConfigName }}</li>
            </ul>
          </div>

          <!--编辑 edit-content-form-li 里面添加 width100 width50 width33 就可以一列 二列 三列-->
          <div class="edit-content" v-loading="drawerConfig.loading" v-else>
            <el-form :model="formData" ref="drawerFromRef" class="edit-content-form" label-width="140px">
              <el-form-item label="场景名称：" prop="name" class="edit-content-form-li width100" :rules="[{ required: true, message: '请输入场景名称', trigger: 'blur' }]">
                <el-input placeholder="请输入场景名称" v-model="formData.name" clearable/>
              </el-form-item>
              <el-form-item label="设备配置名称：" prop="deviceConfigName" class="edit-content-form-li width100" :rules="[]">
                <el-input placeholder="请输入配置名称" v-model="formData.deviceConfigName" clearable/>
              </el-form-item>
              <el-form-item label="资产配置名称：" prop="assetConfigName" class="edit-content-form-li width100" :rules="[]">
                <el-input placeholder="请输入资产配置名称" v-model="formData.assetConfigName" clearable/>
              </el-form-item>
              <el-form-item label="流程配置名称：" prop="flowConfigName" class="edit-content-form-li width100" :rules="[]">
                <el-input placeholder="请输入流程配置名称" v-model="formData.flowConfigName" clearable/>
              </el-form-item>
            </el-form>
          </div>
        </div>

        <!--新增的时候-->
        <div class="action-button" v-if="['add', 'edit'].includes(drawerConfig.type) && !drawerConfig.loading">
          <el-button :icon="Close" @click="drawerConfig.isDrawer=false">取 消</el-button>
          <el-button type="primary" :icon="Check" @click="drawerSubmitForm(drawerFromRef)">保 存</el-button>
        </div>

      </el-drawer>


    </div>
  </div>
</template>

<!-- vue3写法 -->
<script lang="ts" setup>
import {ref, onMounted} from 'vue'
import {Check, Close, Delete, EditPen, Menu, Plus, Tickets, View} from "@element-plus/icons-vue";
import {formatDate} from "@/utils/formatTime";
import { SceneCategoryApi } from '@/api/genchuan/dataHub/sceneCategory'

// table表格数据
const tableData = ref([
/*  {
    id: 1,
    pid: 0,
    name: '场景1',
    deviceConfigName: '设备配置名称1',
    assetConfigName: '资产配置名称1',
    flowConfigName: '流程配置名称1',
    children: []
  },
  {
    id: 2,
    pid: 0,
    name: '场景2',
    deviceConfigName: '设备配置名称2',
    assetConfigName: '资产配置名称2',
    flowConfigName: '流程配置名称2',
    children: []
  },
  {
    id: 3,
    pid: 0,
    name: '场景3',
    deviceConfigName: '设备配置名称3',
    assetConfigName: '资产配置名称3',
    flowConfigName: '流程配置名称3',
    children: [
      {
        id: 31,
        pid: 3,
        name: '场景31',
        deviceConfigName: '设备配置名称31',
        assetConfigName: '资产配置名称31',
        flowConfigName: '流程配置名称31',
        children: [
          {
            pid: 31,
            id: 33,
            name: '场景31',
            deviceConfigName: '设备配置名称31',
            assetConfigName: '资产配置名称31',
            flowConfigName: '流程配置名称31',
          },]
      },

      {
        id: 32,
        pid: 0,
        name: '场景32',
        deviceConfigName: '设备配置名称32',
        assetConfigName: '资产配置名称32',
        flowConfigName: '流程配置名称32',
      },
    ],
  },
  {
    id: 4,
    pid: 0,
    name: '场景4',
    deviceConfigName: '设备配置名称4',
    assetConfigName: '资产配置名称4',
    flowConfigName: '流程配置名称4',
  },*/
])

// 数据初始化
const tableInitData = async () => {
  tableConfig.value.loading = true
  const res=await  SceneCategoryApi.getSceneCategoryTree();
  tableData.value=res;
  tableConfig.value.loading = false
}

// 表单字段
const initFormData = {
  id: '',
  pid: 0,
  name: '',//场景名称
  deviceConfigName: '',//设备配置名称
  assetConfigName: '',//资产配置名称
  flowConfigName: '',//流程配置名称
}
// 表单初始化数据
const formData = ref({...initFormData});

//drawer 配置
const drawerConfig = ref({
  isDrawer: false,//是否打开抽屉 true 打开 false 关闭
  loading: true,//还没加载完成 true 在加载 false加载完成
  title: '抽屉标题',//标题名称
  type: 'add',//add新增 edit编辑 query详细
})

//表单保存
const drawerFromRef = ref<FormInstance>()
const drawerSubmitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  const valid = await new Promise<boolean>((resolve) => {
    formEl.validate((valid) => {
      resolve(valid);
    });
  });
  if (valid) {
    try {
      // await assetProfileAddEdit(formData.value);
      if (drawerConfig.value.type === 'add') {
        tableConfig.value.currentPage = 1;
        await SceneCategoryApi.createSceneCategory(formData.value)
      }else{
        await SceneCategoryApi.updateSceneCategory(formData.value)
      }
      drawerConfig.value.isDrawer = false;
      ElMessage.success('保存成功');
      await tableInitData();
    } catch (error) {
      // if (drawerConfig.value.type === 'add') {
        ElMessage.error('保存失败');
      // }
      console.log('新增出错!', error);
    }
  } else {
    console.log('表单验证出错!');
    // ElMessage.warning('请完善表单信息');
  }
}

// title点击新增
const addClick = async (row) => {
  formData.value = {...initFormData};
  if(row){
    formData.value.pid =row.id;
  }

  drawerConfig.value.isDrawer = true;
  drawerConfig.value.loading = false;
  drawerConfig.value.type = 'add';
  drawerConfig.value.title = '新增场景名称';
}

// 点击详细按钮
const queryClick = async (row) => {
  formData.value = row;
  console.log(row)
  drawerConfig.value.isDrawer = true;
  drawerConfig.value.loading = false;
  drawerConfig.value.type = 'query';
  drawerConfig.value.title = '场景名称-详细信息';
}
// 点击编辑按钮
const editClick = async (row) => {
  let copyRow = JSON.parse(JSON.stringify(row))
  formData.value = copyRow;
  drawerConfig.value.isDrawer = true;
  drawerConfig.value.loading = false;
  drawerConfig.value.type = 'edit';
  drawerConfig.value.title = '编辑场景名称';
}
// 点击删除按钮
const delClick = (row) => {
  ElMessageBox.confirm(
      '请注意：确认后，场景名称和所有相关数据将不可恢复。',
      `确定要删除 '${row.name}' 场景名称吗？`,
      {
        confirmButtonText: '确 定',
        cancelButtonText: '取 消',
        type: 'warning',
      }
  )
      .then(async () => {
        try {
          await SceneCategoryApi.deleteSceneCategory(row.id)
          await tableInitData();
          ElMessage.success('删除成功')
        } catch (error) {
          // 处理错误
          ElMessage.error('删除失败');
          console.log(error)
        }
      })
      .catch(() => {
        console.log('取消删除')
      })
}


//table 配置
const tableConfig = ref({
  height: 0,//表格高度
  loading: true,//是否加载完成
  currentPage: 1,//当前第几页
  pageSize: 10,//每页几条
  total: 0,//总数
})


// 搜索 字段
const initSearchForm = {
  name: '',//场景名称
}
// 搜索初始化数据
const searchForm = ref({...initSearchForm});
// 搜索 点击
const searchClick =async () => {
  tableConfig.value.currentPage = 1;
  if(searchForm.value.name){
    const res=await SceneCategoryApi.getSceneCategoryPage(searchForm.value);
    tableData.value=res;
  }else{
   await tableInitData('search');
  }


}
//搜索 重置
const searchResetClick = () => {
  searchForm.value = {...initSearchForm};
  tableConfig.value.currentPage = 1;
  tableInitData();
}


//table 设置高度
const searchRef = ref(null);
const getTableHeight = () => {
  setTimeout(() => {
    if (searchRef.value) {
      const searchHeight = searchRef.value.clientHeight;
      let height = document.documentElement.clientHeight - searchHeight - 52 - 107 - 70
      tableConfig.value.height = height
    }
  }, 300)
}

//table 给表头加样式
const getHeaderCellStyle = () => {
  return {
    background: '#f5f7fa',
    color: '#081333',
    fontWeight: 'normal'
  };
};
//table 分页 选择了每页几条
const pageSizeChange = (val) => {
  tableConfig.value.currentPage = 1;//当前第几页
  tableConfig.value.pageSize = val;//每页几条
  tableInitData();
};
//table 分页 点击了跳转到第几页
const pageCurrentChange = (val) => {
  tableConfig.value.currentPage = val;//当前第几页
  tableInitData();
};

// 定义要添加到 body 的 class 名称
const customClass = 'thingsBoard-body-class';
const addClassToBody = () => {
  document.body.classList.add(customClass);
};
const removeClassFromBody = () => {
  document.body.classList.remove(customClass);
};

// 挂载完毕
onMounted(() => {
  addClassToBody();//给body加class控制样式
  getTableHeight();//给table获取高度
  tableInitData();//数据初始化
})
// 在组件卸载时移除 class
onUnmounted(() => {
  removeClassFromBody();
});
</script>

<style lang="scss">
.el-table__row--level-1{
  .el-table_1_column_1{
    .el-table__expand-icon{
      margin-left: 20px !important;
    }

  }
}
.el-table__row--level-2{
  .el-table_1_column_1{
    .el-table__expand-icon{
      margin-left: 20px !important;
    }
  }
}
</style>
