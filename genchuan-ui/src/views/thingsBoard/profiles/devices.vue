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
          <!--<el-button type="primary" plain :icon="Plus" @click="addClick(titleConfig.addButName)" v-hasPermi="['profiles:deviceProfile:create']"> {{ titleConfig.addButName }}</el-button>-->
        </div>
      </div>
      <!--搜索内容字段-->
      <div class="cc-search-css" ref="searchRef">
        <el-form class="-mb-15px" :model="searchForm" ref="searchFormRef" :inline="true" label-width="68px">
          <!--@keyup.enter="handleQuery"-->
          <el-form-item label="搜索内容" prop="name">
            <el-input v-model="searchQuery" placeholder="请输入配置名称" clearable class="!w-240px" @input="handleSearch"/>
          </el-form-item>
          <el-form-item>
            <!--表格字段是否显示-->
            <el-dropdown trigger="click" :hide-on-click="false">
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
          <el-table-column label="id" width="350" align="center" prop="id" :show-overflow-tooltip="true" v-if="tableColumnShow('id')"/>
          <el-table-column label="设备名称" min-width="150" align="center" prop="设备名称" :show-overflow-tooltip="true" v-if="tableColumnShow('设备名称')"/>
          <el-table-column label="流量" min-width="150" align="center" prop="流量" :show-overflow-tooltip="true" v-if="tableColumnShow('流量')"/>
          <el-table-column label="湿度" min-width="150" align="center" prop="湿度" :show-overflow-tooltip="true" v-if="tableColumnShow('湿度')"/>
          <el-table-column label="液位" min-width="150" align="center" prop="液位" :show-overflow-tooltip="true" v-if="tableColumnShow('液位')"/>
          <el-table-column label="雨量" min-width="150" align="center" prop="雨量" :show-overflow-tooltip="true" v-if="tableColumnShow('雨量')"/>
          <el-table-column label="遥测时间" align="center" prop="遥测时间" width="200px" v-if="tableColumnShow('遥测时间')"/>
          <el-table-column width="120" fixed="right" label="操作" align="center">
            <template #default="scope">
              <el-button type="success" :icon="View" :plain="true" @click="queryClick(scope.row)" v-hasPermi="['profiles:deviceProfile:query']">详细</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!--分页-->
        <div class="cc-table-css-page">
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
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {ref, reactive, onMounted, computed} from 'vue'
import {Tickets, Plus, EditPen, Delete, View, Menu, Close, Check} from '@element-plus/icons-vue'
import {useThingsBoardWs} from './thingsboardWsService';
import {delay, formatDate} from '@/utils/formatTime'
import {deviceProfileQueryList} from "@/api/genchuan/thingsBoard/profiles";

const router = useRouter() // 路由
// 引入WebSocket服务
const {
  isConnected,
  connectionError,
  receivedTelemetry,
  deviceData,
  connect,
  disconnect,
  sendTelemetry,
  subscribeToDeviceTelemetry
} = useThingsBoardWs();


// 分页相关变量
const currentPage = ref(1);
const pageSize = ref(10);
// 原始数据和过滤后的数据
const rawData = ref([]);
const filteredData = ref([]);




// 获取初始化设备
watch(deviceData, (newVal, oldVal) => {
  try {
    let resData = newVal.data.data;
    for (let i = 0; i < resData.length; i++) {
      let jsonData = {
        id: resData[i].entityId.id,
        设备名称: ensureString(resData[i].latest.ENTITY_FIELD.name.value),
        流量: ensureString(resData[i].latest.TIME_SERIES.流量.value),
        湿度: ensureString(resData[i].latest.TIME_SERIES.湿度.value),
        液位: ensureString(resData[i].latest.TIME_SERIES.液位.value),
        雨量: ensureString(resData[i].latest.TIME_SERIES.雨量.value),
        遥测时间: formatDate(resData[i].latest.TIME_SERIES.流量.ts)
      }
      rawData.value.push(jsonData)
    }
    filteredData.value = [...rawData.value];
  } catch (error) {
    // 处理错误
    console.log('监听出问题')
  }

})

// 设备的遥测变了的值  获取最新的值
watch(receivedTelemetry, (newVal, oldVal) => {
  let resData = newVal.update;
  for (let i = 0; i < resData.length; i++) {
    for (let j = 0; j < tableData.value.length; j++) {
      if (resData[i].entityId.id == tableData.value[j].id) {
        tableData.value[j].流量 = ensureString(resData[i].latest.TIME_SERIES.流量.value);
        tableData.value[j].湿度 = ensureString(resData[i].latest.TIME_SERIES.湿度.value);
        tableData.value[j].液位 = ensureString(resData[i].latest.TIME_SERIES.液位.value);
        tableData.value[j].雨量 = ensureString(resData[i].latest.TIME_SERIES.雨量.value);
        tableData.value[j].遥测时间 = formatDate(resData[i].latest.TIME_SERIES.流量.ts)
      }
    }
  }
})

//table 初始化
const tableInitData = async () => {
  tableConfig.value.loading = true;

  try {
    let params = {
      pageSize: 10,
      page: 1,
      sortProperty: 'createdTime',
      textSearch: '为了方式token过期拉下接口',
      sortOrder: 'DESC'
    }
    await deviceProfileQueryList(params);
    await delay(1000);
    // 发送认证消息并开始获取数据
    const authMessageData = {
      "cmds": [
        {
          "type": "ENTITY_DATA",
          "query": {
            "entityFilter": {
              "type": "deviceType",
              "resolveMultiple": true,
              "deviceTypes": ["测试"],
              "deviceNameFilter": ""
            },
            "pageLink": {
              "page": 0,
              "pageSize": 1024,
              "textSearch": null,
              "dynamic": true,
              "sortOrder": {
                "key": {"key": "name", "type": "ENTITY_FIELD"},
                "direction": "ASC"
              }
            },
            "entityFields": [
              {"type": "ENTITY_FIELD", "key": "name"},
              {"type": "ENTITY_FIELD", "key": "label"},
              {"type": "ENTITY_FIELD", "key": "additionalInfo"}
            ],
            "latestValues": [
              {"type": "TIME_SERIES", "key": "流量"},
              {"type": "TIME_SERIES", "key": "湿度"},
              {"type": "TIME_SERIES", "key": "液位"},
              {"type": "TIME_SERIES", "key": "雨量"}
            ]
          },
          "latestCmd": {
            "keys": [
              {"type": "TIME_SERIES", "key": "流量"},
              {"type": "TIME_SERIES", "key": "湿度"},
              {"type": "TIME_SERIES", "key": "液位"},
              {"type": "TIME_SERIES", "key": "雨量"}
            ]
          },
          "cmdId": 1
        }
      ],
      "authCmd": {
        "cmdId": 0,
        "token": window.localStorage.getItem('thingsBoardJwt_token')
      }
    }
    connect(authMessageData);
  } catch (error) {
    // 处理错误
    console.log('token过期')
  }


  if (isConnected) {
    tableConfig.value.loading = false;
  } else {
    console.log('ws 连接没连接上')
    tableConfig.value.loading = false;
  }
}

// 点击详细按钮
const queryClick = async (row) => {
  await router.push({
    name: 'DeviceTelemetryDetail',
    query: {id: row.id, deviceName: row.设备名称}
  })
}


// title配置
const titleConfig = ref({
  name: '设备遥测',//标题名称
  addButName: '新增设备遥测',//新增按钮名称
})
// 搜索 字段
const searchForm = ref({});//只为了搜索不报错
const searchQuery = ref('');

// 处理搜索
const handleSearch = () => {
  if (!searchQuery.value) {
    filteredData.value = [...rawData.value];
    currentPage.value = 1; // 重置到第一页
    return;
  }
  const query = searchQuery.value.toLowerCase();
  filteredData.value = rawData.value.filter(item =>
      item.id.toLowerCase().includes(query) ||
      item.设备名称.toLowerCase().includes(query) ||
      item.流量.toLowerCase().includes(query) ||
      item.湿度.toLowerCase().includes(query) ||
      item.液位.toLowerCase().includes(query) ||
      item.雨量.toLowerCase().includes(query) ||
      item.遥测时间.toLowerCase().includes(query)
  );
  currentPage.value = 1; // 重置到第一页
};


//table 列显隐信息
const tableColumns = ref([
  {label: `id`, visible: true},
  {label: `设备名称`, visible: true},
  {label: `流量`, visible: true},
  {label: `湿度`, visible: true},
  {label: `液位`, visible: true},
  {label: `雨量`, visible: true},
  {label: `遥测时间`, visible: true},
])
//
// table表格数据 计算当前页的数据
const tableData = computed(() => {
  const startIndex = (currentPage.value - 1) * pageSize.value;
  return filteredData.value.slice(startIndex, startIndex + pageSize.value);
});
//table 配置
const tableConfig = ref({
  height: 0,//表格高度
  loading: true,//是否加载完成
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

// 值一定是字符串型，不然不能搜索
const ensureString = (value, defaultValue = '') => {
  // 检查是否已经是字符串类型
  if (typeof value === 'string') {
    return value;
  }
  // 处理null和undefined
  if (value === null || value === undefined) {
    return defaultValue;
  }

  // 尝试将其他类型转换为字符串
  try {
    // 对于对象类型，使用JSON.stringify
    if (typeof value === 'object') {
      return JSON.stringify(value);
    }
    // 其他类型直接转换
    return String(value);
  } catch (error) {
    // 转换失败时返回默认值
    console.warn('无法将值转换为字符串，使用默认值', error);
    return defaultValue;
  }
}


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
  disconnect();
});
</script>
<style lang="scss" scoped></style>
