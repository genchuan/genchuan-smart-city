<template>
  <!--记得路由要配个name-->
  <div class="cc-main"  v-if="!selectProcessDefinition">
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
          <el-button type="primary" plain :icon="Plus" @click="addClick(titleConfig.addButName)" v-hasPermi="['profiles:test66:create']"> {{ titleConfig.addButName }}</el-button>
        </div>
      </div>
      <!--搜索内容字段-->
      <div class="cc-search-css" ref="searchRef">
        <el-form class="-mb-15px" :model="searchForm" ref="searchFormRef" :inline="true" label-width="68px">
          <!--@keyup.enter="handleQuery"-->
          <el-form-item label="名字" prop="name">
            <el-input v-model="searchForm.name" placeholder="请输入名字" clearable class="!w-240px"/>
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
          <el-table-column label="名字" min-width="150" align="center" prop="name" :show-overflow-tooltip="true" v-if="tableColumnShow('名字')"/>
          <el-table-column label="年龄" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('年龄')">
            <template #default="scope">
              {{ tableFieldShow('年龄', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column label="流程id" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('流程id')">
            <template #default="scope">
              {{ tableFieldShow('流程id', scope.row.field) }}
            </template>
          </el-table-column>

          <el-table-column width="450" fixed="right" label="操作" align="center">
            <template #default="scope">
              <el-button type="success" :icon="View" :plain="true" @click="queryClick(scope.row)" v-hasPermi="['profiles:test66:query']">详细</el-button>
              <el-button type="primary" :icon="EditPen" :plain="true" @click="editClick(scope.row)" v-hasPermi="['profiles:test66:update']">编辑</el-button>
              <el-button type="primary" v-if="processDefinitionBut && !tableFieldShow('流程id', scope.row.field)" @click="eventButClick(scope.row)"  :plain="true">事件发起</el-button>
              <el-button type="primary" v-if="tableFieldShow('流程id', scope.row.field)" @click="eventDetailClick(tableFieldShow('流程id', scope.row.field),scope.row.id.id)"  :plain="true">事件详细</el-button>
              <el-button type="danger" :icon="Delete" :plain="true" @click="delClick(scope.row)" v-hasPermi="['profiles:test66:delete']">删除</el-button>
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
              <li class="width50"><span>名字：</span>{{ queryData.name }}</li>
              <li class="width50"><span>年龄：</span>{{ tableFieldShow('年龄', queryData.field) }}</li>
              <li class="width50"><span>流程id：</span>{{ tableFieldShow('流程id', queryData.field) }}</li>

            </ul>
          </div>

          <!--编辑 edit-content-form-li 里面添加 width100 width50 width33 就可以一列 二列 三列-->
          <div class="edit-content" v-loading="drawerConfig.loading" v-else>
            <el-form :model="formData" ref="drawerFromRef" class="edit-content-form" label-width="140px">
              <el-form-item label="名字：" prop="name" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入名字', trigger: 'blur' }]">
                <el-input placeholder="请输入名字" v-model="formData.name" clearable/>
              </el-form-item>
              <el-form-item label="年龄：" prop="field.年龄" class="edit-content-form-li width50" :rules="[]">
                <el-input placeholder="请输入年龄" v-model="formData.field.年龄" clearable/>
              </el-form-item>
              <el-form-item label="流程id：" prop="field.流程id" class="edit-content-form-li width50" :rules="[]">
                <el-input placeholder="请输入流程id" v-model="formData.field.流程id" clearable/>
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

  <!-- 第二步，填写表单，进行流程的提交 -->
  <ProcessDefinitionDetail2
      v-else
      ref="processDefinitionDetailRef"
      :selectProcessDefinition="selectProcessDefinition"
      @cancel="selectProcessDefinition = undefined"
      @tableInitData="tableInitData"
  />

</template>

<script lang="ts" setup>
import {ref, reactive, onMounted} from 'vue'
import {Tickets, Plus, EditPen, Delete, View, Menu, Close, Check} from '@element-plus/icons-vue'
import {handleTypeOptions, tableFieldShow} from '@/utils/thingsBoardUtils'
import {assetsDel, assetsEdit, assetsEditField, assetsList, assetsListField} from "@/api/genchuan/thingsBoard/assets";

//流程表单开始
import {getProcessDefinitionList} from "@/api/bpm/definition";
import ProcessDefinitionDetail2 from "@/views/bpm/processInstance/create/ProcessDefinitionDetail2.vue";
import * as ProcessInstanceApi from "@/api/bpm/processInstance";
import ProcessDefinitionDetail from "@/views/bpm/processInstance/create/ProcessDefinitionDetail.vue";
const router = useRouter() // 路由
const route = useRoute() // 路由
const processDefinitionList = ref([]);//所有流程列表
const processDefinitionBut = ref(false);//发起请假流程按钮是否显示
const processDefinitionKey = 'test_1';//流程id会变 key不会变
const processDefinitionForm=ref({});//流程按钮点击，需要提交的表单数据
const processInstanceId: any = route.query.processInstanceId // 流程实例编号。场景：重新发起时
defineOptions({ name: 'test66name' });//和路由那边的相同

/** 获取所有流程 并判断按钮是否显示 */
const getProcessDefinitionMethod = async () => {
  try {
    processDefinitionList.value = await getProcessDefinitionList({suspensionState: 1});
    for (let i = 0; i < processDefinitionList.value.length; i++) {
      if (processDefinitionKey == processDefinitionList.value[i].key) {
        processDefinitionBut.value = true;
        processDefinitionForm.value=processDefinitionList.value[i];
        return false
      }
    }
  } catch (error) {
    console.log('获取流程列表出错')
  }
}

// ========== 流程表单相关 ==========
const selectProcessDefinition = ref() // 选择的流程定义
const processDefinitionDetailRef = ref()

/** 点击事件流程发起按钮 **/
const eventButClick = async (row, formVariables?) => {
  // 设置选择的流程
  processDefinitionForm.value.tableRow=row;//把表格的数据也给子组件
  selectProcessDefinition.value = processDefinitionForm.value;
  // 初始化流程定义详情
  await nextTick()
  processDefinitionDetailRef.value?.initProcessInfo(processDefinitionForm.value, formVariables)
}

/** 事件按钮详情详情 */
const eventDetailClick = (id,thingsBoardId) => {
  router.push({
    name: 'BpmProcessInstanceDetail1',
    query: {
      id: id,
      thingsBoardId:thingsBoardId,
      routerName:router.currentRoute.value.name
    }
  })
}


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
      assetProfileId: 'dacff810-7981-11f0-bd39-2dc5ccdb39ef',//资产配置id
    }
    let res = await assetsList(params);
    tableData.value = res.data;
    console.log(tableData.value,111)
    for (let i = 0; i < tableData.value.length; i++) {
      let params = {id: tableData.value[i].id.id}
      let resField = await assetsListField(params);
      tableData.value[i].field = resField
    }
    tableConfig.value.total = res.totalElements;
    tableConfig.value.loading = false;

    // 如果 processInstanceId 非空，说明是重新发起
    if (processInstanceId?.length > 0) {
      const processInstance = await ProcessInstanceApi.getProcessInstance(processInstanceId)
      if (!processInstance) {
        message.error('重新发起流程失败，原因：流程实例不存在')
        return
      }
      const processDefinition = processDefinitionList.value.find(
          (item: any) => item.key == processInstance.processDefinition?.key
      )
      if (!processDefinition) {
        message.error('重新发起流程失败，原因：流程定义不存在')
        return
      }
      await eventButClick(processDefinition, processInstance.formVariables)
    }



  } catch (error) {
    tableConfig.value.loading = false;
    // 处理错误
    console.log("初始化出错了")
  }
}

//流程表单到这结束

// 表单字段
const initFormData = {
  name: '',//资产名称 这个用一个字段区代表就行了
  type: '测试流程与资产',//资产配置，都是写死固定的资产配置
  field: {
    年龄: '',//年龄
    流程id: '',//流程id
  }
}

// 所有选项集合
const optionsLisi = ref({})

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
  drawerConfig.value.title = '测试流程与资产-详细信息';
}
// 点击编辑按钮
const editClick = async (row) => {
  let copyRow = JSON.parse(JSON.stringify(row))
  formData.value = copyRow;
  formData.value.field = {};
  formData.value.field.年龄 = tableFieldShow('年龄', row.field);//年龄
  formData.value.field.流程id = tableFieldShow('流程id', row.field);//流程id

  drawerConfig.value.isDrawer = true;
  drawerConfig.value.loading = false;
  drawerConfig.value.type = 'edit';
  drawerConfig.value.title = '编辑测试流程与资产';
}
// 点击删除按钮
const delClick = (row) => {
  ElMessageBox.confirm(
      '请注意：确认后，所有相关数据将不可恢复。',
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
  drawerConfig.value.title = '新增测试流程与资产';
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
  name: '测试流程与资产',//标题名称
  addButName: '新增测试流程与资产',//新增按钮名称
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
  {label: '名字', visible: true},
  {label: '年龄', visible: true},
  {label: '流程id', visible: true},

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
  getProcessDefinitionMethod();//获取事件列表
})
// 在组件卸载时移除 class
onUnmounted(() => {
  removeClassFromBody();
});
</script>
<style lang="scss" scoped></style>
