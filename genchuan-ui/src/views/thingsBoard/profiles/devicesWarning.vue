<template>
  <div class="cc-main" v-if="!selectProcessDefinition">
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
        <!--        <div class="right">
                  <el-button type="primary" plain :icon="Plus" @click="addClick(titleConfig.addButName)" v-hasPermi="['profiles:assetProfile:create']"> {{ titleConfig.addButName }}</el-button>
                </div>-->
      </div>

      <!--搜索内容字段  这个等后面配置 1111-->
      <div class="cc-search-css" ref="searchRef">
        <el-form class="-mb-15px" :model="searchForm" ref="searchFormRef" :inline="true" label-width="68px">
          <!--@keyup.enter="handleQuery"-->
          <el-form-item label="状态" prop="name">
            <el-select-v2
                v-model="searchForm.statusList"
                :options="statusListOptions"
                placeholder="请选择状态"
                style="width: 240px"
                multiple
            />
          </el-form-item>
          <el-form-item label="状态" prop="name">
            <el-select-v2
                v-model="searchForm.severityList"
                :options="severityListOptions"
                placeholder="请选择严重程度"
                style="width: 240px"
                multiple
                clearable
            />
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
          <el-table-column label="id" align="center" prop="id" :show-overflow-tooltip="true">
            <template #default="scope">
              <div @click="idCopy(scope.row.id.id)" class="copy-css">{{ scope.row.id.id }}</div>
            </template>
          </el-table-column>

          <el-table-column label="实体类型" align="center" prop="entityType" :show-overflow-tooltip="true">
            <template #default="scope">
              {{ scope.row.id.entityType }}
            </template>
          </el-table-column>

          <el-table-column label="发起者" align="center" prop="entityType" :show-overflow-tooltip="true">
            <template #default="scope">
              {{ scope.row.originatorName }}
            </template>
          </el-table-column>

          <el-table-column label="类型" align="center" prop="entityType" :show-overflow-tooltip="true">
            <template #default="scope">
              {{ scope.row.type }}
            </template>
          </el-table-column>

          <el-table-column label="严重程度" align="center" prop="entityType" :show-overflow-tooltip="true">
            <template #default="scope">
              {{ severityMethod(scope.row.severity) }}
            </template>
          </el-table-column>


          <el-table-column label="创建时间" align="center" prop="createdTime" :formatter="dateFormatter" width="200px"/>

          <el-table-column label="流程id" align="center" prop="entityType" :show-overflow-tooltip="true">
            <template #default="scope">
              {{ scope.row.comments }}
            </template>
          </el-table-column>


          <el-table-column width="200" fixed="right" label="操作" align="center">
            <template #default="scope">
              <!---->
              <el-button type="primary" v-if="!scope.row.comments && scope.row.status.includes('ACTIVE_UNACK')" :plain="true" @click="eventButClick(scope.row)">事件发起</el-button>
              <el-button type="primary" v-else @click="eventDetailClick(scope.row.comments,scope.row.id.id)" :plain="true">事件详细</el-button>
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
    </div>
  </div>

  <!-- 第二步，填写表单，进行流程的提交 -->
  <ProcessDefinitionDetail3
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
import {dateFormatter, formatDate} from '@/utils/formatTime'


import {alarmsQueryList, alarmQueryListRemarks, remarksInfo, remarksClear} from "@/api/genchuan/thingsBoard/profiles";
import {tableFieldShow} from "@/utils/thingsBoardUtils";

//流程表单开始
import {getProcessDefinitionList} from "@/api/bpm/definition";
import ProcessDefinitionDetail3 from "@/views/bpm/processInstance/create/ProcessDefinitionDetail3.vue";
import * as ProcessInstanceApi from "@/api/bpm/processInstance";

const router = useRouter() // 路由
const route = useRoute() // 路由
// ========== 流程表单相关 ==========
const processDefinitionList = ref([]);//所有流程列表
const processDefinitionBut = ref(false);//发起请假流程按钮是否显示
const processDefinitionKey = 'alarm_event';//流程id会变 key不会变
const processDefinitionForm = ref({});//流程按钮点击，需要提交的表单数据
const selectProcessDefinition = ref() // 选择的流程定义
const processDefinitionDetailRef = ref()
const processInstanceId: any = route.query.processInstanceId // 流程实例编号。场景：重新发起时
defineOptions({name: 'devicesWarning'});//和路由那边的相同

/** 获取所有流程 并判断按钮是否显示 */
const getProcessDefinitionMethod = async () => {
  try {
    processDefinitionList.value = await getProcessDefinitionList({suspensionState: 1});
    for (let i = 0; i < processDefinitionList.value.length; i++) {
      if (processDefinitionKey == processDefinitionList.value[i].key) {
        processDefinitionBut.value = true;
        processDefinitionForm.value = processDefinitionList.value[i];
        return false
      }
    }
  } catch (error) {
    console.log('获取流程列表出错')
  }
}


/** 点击事件流程发起按钮 **/
const eventButClick = async (row, formVariables?) => {
  // 设置选择的流程
  processDefinitionForm.value.tableRow = row;//把表格的数据也给子组件
  selectProcessDefinition.value = processDefinitionForm.value;
  // 初始化流程定义详情
  await nextTick()
  processDefinitionDetailRef.value?.initProcessInfo(processDefinitionForm.value, formVariables)
}

/** 事件按钮详情详情 */
const eventDetailClick = async (id, thingsBoardId) => {
  router.push({
    name: 'BpmProcessInstanceDetail3',
    query: {
      id: id,
      thingsBoardId: thingsBoardId,
      routerName: router.currentRoute.value.name
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
      sortProperty: 'createdTime',
      textSearch: searchForm.value.name,
      sortOrder: 'DESC',
      statusList: searchForm.value.statusList.toString(),//告警状态列表
      severityList: searchForm.value.severityList.toString(),//警报严重程度列表

    }
    let res = await alarmsQueryList(params);
    // 获取警告里面的评论
    for (let i = 0; i < res.data.length; i++) {
      let params2 = {
        pageSize: 100000,
        page: 0,
        sortProperty: 'createdTime',
        sortOrder: 'DESC',
        id: res.data[i].id.id
      }
      let remarksRes = await alarmQueryListRemarks(params2);
      if (remarksRes.data.length) {
        for (let j = 0; j < remarksRes.data.length; j++) {
          let commentsJson = JSON.parse(remarksRes.data[j].name);
          let comments = commentsJson.text;
          if (comments !== null && comments !== undefined && comments !== '') {
            // 统计短横线 '-' 的数量
            const hyphenCount = (comments.match(/-/g) || []).length;
            // 判断是否包含3个短横线
            if (hyphenCount >= 3) {
              res.data[i]['comments'] = comments;
              break
            } else {
              res.data[i]['comments'] = '';
            }
          } else {
            res.data[i]['comments'] = '';
          }
        }
      }
    }

    tableData.value = res.data;
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


// title配置
const titleConfig = ref({
  name: '警告列表',//标题名称
})
// 搜索 字段
const initSearchForm = {
  statusList: '',//状态
  severityList: '',//告警严重程度
}
const statusListOptions = ref([
  {value: 'ACTIVE', label: '激活'},
  {value: 'CLEARED', label: '已清除'},
  {value: 'ACK', label: '已确定'},
  {value: 'UNACK', label: '未确定'},
]);

// 告警严重程度
const severityListOptions = ref([
  {value: 'CRITICAL', label: '危险'},
  {value: 'MAJOR', label: '重要'},
  {value: 'MINOR', label: '次要'},
  {value: 'WARNING', label: '警告'},
  {value: 'INDETERMINATE', label: '不确定'},
]);

// 告警严重程度 过滤器
const severityMethod = (val) => {
  switch (val) {
    case 'CRITICAL':
      return '危险'
    case 'MAJOR':
      return '重要'
    case 'MINOR':
      return '次要'
    case 'WARNING':
      return '警告'
    case 'INDETERMINATE':
      return '不确定'
  }
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
  {label: `id`, visible: true},
  {label: `配置名称`, visible: true},
  {label: `说明`, visible: true},
  {label: `创建时间`, visible: true},
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
