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
          <span>{{ titleConfig.name }}</span></div>
        <!--右边的按钮-->
        <div class="right">
          <el-button type="primary" plain :icon="Plus" @click="addClick(titleConfig.addButName)" v-hasPermi="['profiles:assets:create']"> {{ titleConfig.addButName }}</el-button>
        </div>
      </div>

      <!--搜索内容字段-->
      <div class="cc-search-css" ref="searchRef">
        <el-form class="-mb-15px" :model="searchForm" ref="searchFormRef" :inline="true" label-width="68px">
          <!--@keyup.enter="handleQuery"-->
          <el-form-item label="资产名称" prop="name">
            <el-input v-model="searchForm.name" placeholder="请输入资产名称" clearable class="!w-240px"/>
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
            <!--表格字段是否显示-->
            <el-dropdown trigger="click" :hide-on-click="false" style="padding-left: 12px">
              <el-button circle :icon="Menu"/>
              <template #dropdown>
                <el-dropdown-menu>
                  <template v-for="item in tableColumns" :key="item.visible">
                    <el-dropdown-item>
                      <el-checkbox v-model="item.visible" :label="item.label"/>
                    </el-dropdown-item>
                  </template>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </el-form-item>
        </el-form>
      </div>

      <!--table-->
      <div class="cc-table-css">
        <el-table
            ref="tableRef"
            :data="tableData"
            border
            style="width: 100%"
            :header-cell-style="getHeaderCellStyle"
            v-loading="tableConfig.loading"
            :max-height="tableConfig.height"
        >
          <el-table-column label="序号" type="index" width="55" align="center" fixed="left"/>
          <el-table-column label="资产名称" min-width="150" align="center" prop="name" :show-overflow-tooltip="true" v-if="tableColumnShow('资产名称')"/>
          <el-table-column label="输入框" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('输入框')">
            <template #default="scope">
              {{ tableFieldShow('a1', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column label="输入框有单位" min-width="150" align="center" :show-overflow-tooltip="true" width="200px" v-if="tableColumnShow('输入框有单位')">
            <template #default="scope">
              {{ tableFieldShow('a6', scope.row.field) }} 天
            </template>
          </el-table-column>
          <el-table-column label="文本框" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('文本框')">
            <template #default="scope">
              {{ tableFieldShow('a2', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column label="下拉框" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('下拉框')">
            <template #default="scope">
              {{ handleTypeOptions(tableFieldShow('a3', scope.row.field), optionsLisi.a3Options) }}
            </template>
          </el-table-column>
          <el-table-column label="单选框" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('单选框')">
            <template #default="scope">
              {{ handleTypeOptions(tableFieldShow('a4', scope.row.field), optionsLisi.a4Options) }}
            </template>
          </el-table-column>
          <el-table-column label="日期选择器" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('日期选择器')">
            <template #default="scope">
              {{ tableFieldShow('a5', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column width="300" fixed="right" label="操作" align="center">
            <template #default="scope">
              <el-button type="success" :icon="View" :plain="true" @click="queryClick(scope.row)" v-hasPermi="['profiles:assets:query']">详细</el-button>
              <el-button type="primary" :icon="EditPen" :plain="true" @click="editClick(scope.row)" v-hasPermi="['profiles:assets:update']">编辑</el-button>
              <el-button type="danger" :icon="Delete" :plain="true" @click="delClick(scope.row)" v-hasPermi="['profiles:assets:delete']">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!--分页-->
        <div class="cc-table-css-page">
          <el-pagination
              @size-change="pageSizeChange"
              @current-change="pageCurrentChange"
              :page-size="tableConfig.pageSize"
              :page-sizes="[10, 20, 50, 100,500,1000]"
              layout="total,sizes, prev, pager, next, jumper"
              :current-page="tableConfig.currentPage"
              :total="tableConfig.total"
          />
        </div>
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

          <!--tabs选项-->
          <el-tabs v-model="drawerConfig.active" class="content-top-tabs" @tab-click="tabsClick">
            <el-tab-pane label="基础信息" name="1" v-if="drawerConfig.type=='add'"/>
            <el-tab-pane v-else v-for="item in drawerConfig.tabsList" :key="item.value" :label="item.label" :name="item.value"/>
          </el-tabs>

          <!--查看 li添加class width100 width50 width33 就可以一列 二列 三列-->
          <div class="see-content" v-loading="drawerConfig.loading" v-if="drawerConfig.type==='query'">
            <ul>
              <li class="width100"><span>资产名称：</span>{{ queryData.name }}</li>
              <li class="width100"><span>输入框：</span>{{ tableFieldShow('a1', queryData.field) }}</li>
              <li class="width100"><span>输入框有单位：</span>{{ tableFieldShow('a6', queryData.field) }} 天</li>
              <li class="width100"><span>文本框：</span>{{ tableFieldShow('a2', queryData.field) }}</li>
              <li class="width100"><span>下拉框：</span>{{ handleTypeOptions(tableFieldShow('a3', queryData.field), optionsLisi.a3Options) }}</li>
              <li class="width100"><span>单选框：</span>{{ handleTypeOptions(tableFieldShow('a4', queryData.field), optionsLisi.a4Options) }}</li>
              <li class="width100"><span>日期选择器：</span>{{ tableFieldShow('a5', queryData.field) }}</li>
            </ul>
          </div>

          <!--编辑 edit-content-form-li 里面添加 width100 width50 width33 就可以一列 二列 三列-->
          <div class="edit-content" v-loading="drawerConfig.loading" v-else>
            <el-form :model="formData" ref="drawerFromRef" class="edit-content-form" label-width="140px">
              <el-form-item label="资产名称：" prop="name" class="edit-content-form-li width100" :rules="[{ required: true, message: '请输入资产名称', trigger: 'blur' }]">
                <el-input placeholder="请输入资产名称" v-model="formData.name" clearable/>
              </el-form-item>
              <el-form-item label="我是输入框：" prop="field.a1" class="edit-content-form-li width100" :rules="[{ required: true, message: '请输入我是输入框', trigger: 'blur' }]">
                <el-input placeholder="请输入我是输入框" v-model="formData.field.a1" clearable/>
              </el-form-item>
              <el-form-item label="我是输入框有单位：" prop="field.a6" class="edit-content-form-li width100" :rules="[{ required: true, message: '请输入我是输入框有单位', trigger: 'blur' }]">
                <el-input placeholder="请输入我是输入框有单位" v-model="formData.field.a6" clearable>
                  <template #append>天</template>
                </el-input>
              </el-form-item>
              <el-form-item label="我是文本框：" prop="field.a2" class="edit-content-form-li width100" :rules="[{ required: true, message: '请输入我是文本框', trigger: 'blur' }]">
                <el-input placeholder="请输入我是文本框" v-model="formData.field.a2" :autosize="{ minRows: 4, maxRows: 10 }" type="textarea" clearable/>
              </el-form-item>
              <el-form-item label="我是下拉：" prop="field.a3" class="edit-content-form-li width100" :rules="[{ required: true, message: '请选择我是下拉', trigger: 'blur' }]">
                <el-select placeholder="请选择我是下拉" v-model="formData.field.a3" filterable clearable>
                  <el-option v-for="item in optionsLisi.a3Options" :key="item.value" :label="item.label" :value="item.value"/>
                </el-select>
              </el-form-item>
              <el-form-item label="我是单选：" prop="field.a4" class="edit-content-form-li width100" :rules="[{ required: true, message: '请选择我是单选', trigger: 'blur' }]">
                <el-radio-group v-model="formData.field.a4">
                  <el-radio v-for="item in optionsLisi.a4Options" :key="item.value" :label="item.label" :value="item.value">Option A</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="我是日期选择器：" prop="field.a5" class="edit-content-form-li width100" :rules="[{ required: true, message: '请选择我是日期选择器', trigger: 'blur' }]">
                <el-date-picker class="width100" v-model="formData.field.a5" type="date" placeholder="请选择我是日期选择器" format="YYYY-MM-DD" value-format="YYYY-MM-DD"/>
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

<script lang="ts" setup>
import {ref, reactive, onMounted} from 'vue'
import {Tickets, Plus, EditPen, Delete, View, Menu, Close, Check} from '@element-plus/icons-vue'
import {handleTypeOptions, tableFieldShow} from '@/utils/thingsBoardUtils'
import {assetsDel, assetsEdit, assetsEditField, assetsList, assetsListField} from "@/api/genchuan/thingsBoard/assets";


//table 初始化
const tableInitData = async () => {
  tableConfig.value.loading = true;
  try {
    let params = {
      pageSize: tableConfig.value.pageSize,
      page: tableConfig.value.currentPage - 1,
      textSearch: searchForm.value.name,
      sortProperty: 'createdTime',
      sortOrder: 'DESC',
      assetProfileId: '3da341c0-7268-11f0-bd39-2dc5ccdb39ef',//资产配置id
    }
    let res = await assetsList(params);
    tableData.value = res.data;
    for (let i = 0; i < tableData.value.length; i++) {
      let params = {id: tableData.value[i].id.id}
      let resField = await assetsListField(params);
      tableData.value[i].field = resField
    }
    tableConfig.value.total = res.totalElements;
    tableConfig.value.loading = false;
  } catch (error) {
    tableConfig.value.loading = false;
    // 处理错误
    console.log("初始化出错了")
  }
}

// 表单字段
const initFormData = {
  name: '',//资产名称 这个用一个字段区代表就行了
  type: '菜菜资产配置',//资产配置，都是写死固定的资产配置
  field: {
    a1: '',//输入框
    a6: '',//输入框有单位
    a2: '',//文本框
    a3: '1',//下拉框
    a4: '3',//单选框
    a5: '',//日期选择器
  }
}
// 所有选项集合
const optionsLisi = ref({
  a3Options: [
    {value: '1', label: '下拉框选项1'},
    {value: '2', label: '下拉框选项2'},
  ],
  a4Options: [
    {value: '3', label: '单选框选项3'},
    {value: '4', label: '单选框选项3'},
  ]
})
// 表单初始化数据
const formData = ref({...initFormData});

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
      let res = await assetsEdit(formData.value);
      await assetsEditField(res.id.id, formData.value.field)
      if (drawerConfig.value.type === 'add') {
        tableConfig.value.currentPage = 1;
      }
      drawerConfig.value.isDrawer = false;
      ElMessage.success('保存成功');
      await tableInitData();
    } catch (error) {
      if (drawerConfig.value.type === 'add') {
        ElMessage.error('保存失败，数据重复');
      }
      console.log('新增出错!', error);
    }
  } else {
    console.log('表单验证出错!');
    // ElMessage.warning('请完善表单信息');
  }
}

// 点击详细按钮
const queryData = ref({})
const queryClick = async (row) => {
  queryData.value = row;
  console.log(row)
  drawerConfig.value.isDrawer = true;
  drawerConfig.value.loading = false;
  drawerConfig.value.type = 'query';
  drawerConfig.value.title = '资产配置-详细信息';
}
// 点击编辑按钮
const editClick = async (row) => {
  let copyRow = JSON.parse(JSON.stringify(row))
  formData.value = copyRow;
  formData.value.field = {};
  formData.value.field.a1 = tableFieldShow('a1', row.field);//输入框
  formData.value.field.a6 = tableFieldShow('a6', row.field);//输入框有单位
  formData.value.field.a2 = tableFieldShow('a2', row.field);//文本框
  formData.value.field.a3 = tableFieldShow('a3', row.field);//下拉框
  formData.value.field.a4 = tableFieldShow('a4', row.field);//单选框
  formData.value.field.a5 = tableFieldShow('a5', row.field);//日期选择器
  drawerConfig.value.isDrawer = true;
  drawerConfig.value.loading = false;
  drawerConfig.value.type = 'edit';
  drawerConfig.value.title = '编辑资产配置';
}
// 点击删除按钮
const delClick = (row) => {
  ElMessageBox.confirm(
      '请注意：确认后，设备配置和所有相关数据将不可恢复。',
      `确定要删除 '${row.name}' 吗？`,
      {
        confirmButtonText: '确 定',
        cancelButtonText: '取 消',
        type: 'warning',
      }
  )
      .then(async () => {
        try {
          await assetsDel(row.id.id)
          await tableInitData();
          ElMessage({
            type: 'success',
            message: '删除成功',
          })
        } catch (error) {
          // 处理错误
          console.log(error)
        }
      })
      .catch(() => {
        console.log('取消删除')
      })
}
// title点击新增
const addClick = async () => {
  formData.value = {
    ...initFormData,
    field: {...initFormData.field}
  };
  drawerConfig.value.isDrawer = true;
  drawerConfig.value.loading = false;
  drawerConfig.value.type = 'add';
  drawerConfig.value.title = '新增资产';
}

//drawer 配置
const drawerConfig = ref({
  isDrawer: false,//是否打开抽屉 true 打开 false 关闭
  loading: true,//还没加载完成 true 在加载 false加载完成
  title: '抽屉标题',//标题名称
  type: 'add',//add新增 edit编辑 query详细
  active: '1',//当前选择了哪个
  // tabs轮动
  tabsList: [
    {label: '基础信息', name: '1'},
  ]
})
//drawer tabs 点击切换
const tabsClick = (tab) => {
  tableConfig.value.currentPage = 1;//当前第几页
  tableInitData();
  console.log('tabs点击切换', tab.props.name)
}

// title配置
const titleConfig = ref({
  name: '资产',//标题名称
  addButName: '新增资产',//新增按钮名称
})
// 搜索 字段
const initSearchForm = {
  name: '',//配置名称
}
// 搜索初始化数据
const searchForm = ref({...initSearchForm});
// 搜索 点击
const searchClick = () => {
  tableConfig.value.currentPage = 1;
  tableInitData();
}
//搜索 重置
const searchResetClick = () => {
  searchForm.value = {...initSearchForm};
  tableConfig.value.currentPage = 1;
  tableInitData();
}

//table 列显隐信息
const tableColumns = ref([
  {label: `资产名称`, visible: true},
  {label: `输入框`, visible: true},
  {label: `输入框有单位`, visible: true},
  {label: `文本框`, visible: true},
  {label: `下拉框`, visible: true},
  {label: `单选框`, visible: true},
  {label: `日期选择器`, visible: true},
])
// table表格数据
const tableData = ref([])
//table 配置
const tableConfig = ref({
  height: 0,//表格高度
  loading: true,//是否加载完成
  currentPage: 1,//当前第几页
  pageSize: 10,//每页几条
  total: 0,//总数
})
//table 列是否显示
const tableColumnShow = (label) => {
  for (let i = 0; i < tableColumns.value.length; i++) {
    if (tableColumns.value[i].label === label) {
      return tableColumns.value[i].visible
    }
  }
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
  console.log('挂载完毕2')
  addClassToBody();//给body加class控制样式
  getTableHeight();//给table获取高度
  tableInitData();//数据初始化
})
// 在组件卸载时移除 class
onUnmounted(() => {
  removeClassFromBody();
});
</script>
<style lang="scss" scoped></style>
