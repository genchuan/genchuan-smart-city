<!--<template>-->
<!--  <div class="cc-main">-->
<!--    <div class="cc-main-content">-->
<!--      &lt;!&ndash;title&ndash;&gt;-->
<!--      <div class="cc-title-css">-->
<!--        <div class="left">-->
<!--          <el-icon class="left-icon">-->
<!--            <Tickets/>-->
<!--          </el-icon>-->
<!--          &lt;!&ndash;标题名称&ndash;&gt;-->
<!--          <span>{{ titleConfig.name }}</span></div>-->
<!--        &lt;!&ndash;右边的按钮&ndash;&gt;-->
<!--        <div class="right">-->
<!--          <el-button type="primary" plain :icon="Plus" @click="addClick(titleConfig.addButName)" v-hasPermi="['smarturban:constructionwastetransportlogmgmt:create']"> {{ titleConfig.addButName }}</el-button>-->
<!--        </div>-->
<!--      </div>-->
<!--&lt;!&ndash;搜索内容字段&ndash;&gt;-->
<!--      <div class="cc-search-css" ref="searchRef">-->
<!--        <el-form class="-mb-15px" :model="searchForm" ref="searchFormRef" :inline="true" label-width="68px">-->
<!--          &lt;!&ndash;@keyup.enter="handleQuery"&ndash;&gt;-->
<!--          <el-form-item label="运输记录编号" prop="name">-->
<!--            <el-input v-model="searchForm.name" placeholder="请输入运输记录编号" clearable class="!w-240px"/>-->
<!--          </el-form-item>-->
<!--          <el-form-item>-->
<!--            <el-button type="primary" @click="searchClick">-->
<!--              <Icon icon="ep:search" class="mr-5px"/>-->
<!--              搜索-->
<!--            </el-button>-->
<!--            <el-button @click="searchResetClick">-->
<!--              <Icon icon="ep:refresh" class="mr-5px"/>-->
<!--              重置-->
<!--            </el-button>-->
<!--            &lt;!&ndash;表格字段是否显示&ndash;&gt;-->
<!--            <el-dropdown trigger="click" :hide-on-click="false" style="padding-left: 12px">-->
<!--              <el-button circle :icon="Menu"/>-->
<!--              <template #dropdown>-->
<!--                <el-dropdown-menu>-->
<!--                  <template v-for="item in tableColumns" :key="item.visible">-->
<!--                    <el-dropdown-item>-->
<!--                      <el-checkbox v-model="item.visible" :label="item.label"/>-->
<!--                    </el-dropdown-item>-->
<!--                  </template>-->
<!--                </el-dropdown-menu>-->
<!--              </template>-->
<!--            </el-dropdown>-->
<!--          </el-form-item>-->
<!--        </el-form>-->
<!--      </div>-->
<!--      &lt;!&ndash;table&ndash;&gt;-->
<!--      <div class="cc-table-css">-->
<!--        <el-table-->
<!--            ref="tableRef"-->
<!--            :data="tableData"-->
<!--            border-->
<!--            style="width: 100%"-->
<!--            :header-cell-style="getHeaderCellStyle"-->
<!--            v-loading="tableConfig.loading"-->
<!--            :max-height="tableConfig.height"-->
<!--        >-->
<!--          <el-table-column label="序号" type="index" width="55" align="center" fixed="left"/>-->
<!--          <el-table-column label="运输记录编号" min-width="150" align="center" prop="name" :show-overflow-tooltip="true" v-if="tableColumnShow('运输记录编号')"/>-->
<!--<el-table-column label="行政区划代码" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('行政区划代码')">-->
<!--            <template #default="scope">-->
<!--              {{ tableFieldShow('行政区划代码', scope.row.field) }} -->
<!--            </template>-->
<!--          </el-table-column>-->
<!--<el-table-column label="车牌号" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('车牌号')">-->
<!--            <template #default="scope">-->
<!--              {{ tableFieldShow('车牌号', scope.row.field) }} -->
<!--            </template>-->
<!--          </el-table-column>-->
<!--<el-table-column label="运输司机驾驶证号" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('运输司机驾驶证号')">-->
<!--            <template #default="scope">-->
<!--              {{ tableFieldShow('运输司机驾驶证号', scope.row.field) }} -->
<!--            </template>-->
<!--          </el-table-column>-->
<!--<el-table-column label="处置证编号" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('处置证编号')">-->
<!--            <template #default="scope">-->
<!--              {{ tableFieldShow('处置证编号', scope.row.field) }} -->
<!--            </template>-->
<!--          </el-table-column>-->
<!--<el-table-column label="出场建筑工地编号" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('出场建筑工地编号')">-->
<!--            <template #default="scope">-->
<!--              {{ tableFieldShow('出场建筑工地编号', scope.row.field) }} -->
<!--            </template>-->
<!--          </el-table-column>-->
<!--<el-table-column label="入场建筑垃圾处理厂标识码" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('入场建筑垃圾处理厂标识码')">-->
<!--            <template #default="scope">-->
<!--              {{ tableFieldShow('入场建筑垃圾处理厂标识码', scope.row.field) }} -->
<!--            </template>-->
<!--          </el-table-column>-->
<!--<el-table-column label="出场时间" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('出场时间')">-->
<!--            <template #default="scope">-->
<!--              {{ tableFieldShow('出场时间', scope.row.field) }} -->
<!--            </template>-->
<!--          </el-table-column>-->
<!--<el-table-column label="入场时间" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('入场时间')">-->
<!--            <template #default="scope">-->
<!--              {{ tableFieldShow('入场时间', scope.row.field) }} -->
<!--            </template>-->
<!--          </el-table-column>-->
<!--<el-table-column label="运输量" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('运输量')">-->
<!--            <template #default="scope">-->
<!--              {{ tableFieldShow('运输量', scope.row.field) }} -->
<!--            </template>-->
<!--          </el-table-column>-->

<!--          <el-table-column width="300" fixed="right" label="操作" align="center">-->
<!--            <template #default="scope">-->
<!--              <el-button type="success" :icon="View" :plain="true" @click="queryClick(scope.row)" v-hasPermi="['smarturban:constructionwastetransportlogmgmt:query']">详细</el-button>-->
<!--              <el-button type="primary" :icon="EditPen" :plain="true" @click="editClick(scope.row)" v-hasPermi="['smarturban:constructionwastetransportlogmgmt:update']">编辑</el-button>-->
<!--              <el-button type="danger" :icon="Delete" :plain="true" @click="delClick(scope.row)" v-hasPermi="['smarturban:constructionwastetransportlogmgmt:delete']">删除</el-button>-->
<!--            </template>-->
<!--          </el-table-column>-->
<!--        </el-table>-->
<!--        &lt;!&ndash;分页&ndash;&gt;-->
<!--        <div class="cc-table-css-page">-->
<!--          -->
<!--          <el-pagination-->
<!--              @size-change="pageSizeChange"-->
<!--              @current-change="pageCurrentChange"-->
<!--              :page-size="tableConfig.pageSize"-->
<!--              :page-sizes="[10, 20, 50, 100,500,1000]"-->
<!--              layout="total,sizes, prev, pager, next, jumper"-->
<!--              :current-page="tableConfig.currentPage"-->
<!--              :total="tableConfig.total"-->
<!--          />-->
<!--        </div>-->
<!--      </div>-->
<!--       &lt;!&ndash;抽屉&ndash;&gt;-->
<!--      <el-drawer-->
<!--          :title="drawerConfig.title"-->
<!--          v-model="drawerConfig.isDrawer"-->
<!--          direction="rtl"-->
<!--          :append-to-body="true"-->
<!--          class="cc-drawer-css"-->
<!--          size="1200px"-->
<!--      >-->
<!--        &lt;!&ndash;头部配置&ndash;&gt;-->
<!--        <template header>-->
<!--          <span>{{ drawerConfig.title }}</span>-->
<!--        </template>-->

<!--        <div class="drawer-content">-->

<!--          &lt;!&ndash;tabs选项&ndash;&gt;-->
<!--          <el-tabs v-model="drawerConfig.active" class="content-top-tabs" @tab-click="tabsClick">-->
<!--            <el-tab-pane label="基础信息" name="1" v-if="drawerConfig.type=='add'"/>-->
<!--            <el-tab-pane v-else v-for="item in drawerConfig.tabsList" :key="item.value" :label="item.label" :name="item.value"/>-->
<!--          </el-tabs>-->

<!--          &lt;!&ndash;查看 li添加class width100 width50 width33 就可以一列 二列 三列&ndash;&gt;-->
<!--          <div class="see-content" v-loading="drawerConfig.loading" v-if="drawerConfig.type==='query'">-->
<!--            <ul>-->
<!--                <li class="width50"><span>运输记录编号：</span>{{ queryData.name }}</li>-->
<!-- <li class="width50"><span>行政区划代码：</span>{{ tableFieldShow('行政区划代码', queryData.field) }}</li>-->
<!-- <li class="width50"><span>车牌号：</span>{{ tableFieldShow('车牌号', queryData.field) }}</li>-->
<!-- <li class="width50"><span>运输司机驾驶证号：</span>{{ tableFieldShow('运输司机驾驶证号', queryData.field) }}</li>-->
<!-- <li class="width50"><span>处置证编号：</span>{{ tableFieldShow('处置证编号', queryData.field) }}</li>-->
<!-- <li class="width50"><span>出场建筑工地编号：</span>{{ tableFieldShow('出场建筑工地编号', queryData.field) }}</li>-->
<!-- <li class="width50"><span>入场建筑垃圾处理厂标识码：</span>{{ tableFieldShow('入场建筑垃圾处理厂标识码', queryData.field) }}</li>-->
<!-- <li class="width50"><span>出场时间：</span>{{ tableFieldShow('出场时间', queryData.field) }}</li>-->
<!-- <li class="width50"><span>入场时间：</span>{{ tableFieldShow('入场时间', queryData.field) }}</li>-->
<!-- <li class="width50"><span>运输量：</span>{{ tableFieldShow('运输量', queryData.field) }}</li>-->

<!--            </ul>-->
<!--          </div>-->

<!--          &lt;!&ndash;编辑 edit-content-form-li 里面添加 width100 width50 width33 就可以一列 二列 三列&ndash;&gt;-->
<!--          <div class="edit-content" v-loading="drawerConfig.loading" v-else>-->
<!--            <el-form :model="formData" ref="drawerFromRef" class="edit-content-form" label-width="140px">-->
<!--               <el-form-item label="运输记录编号：" prop="name" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入运输记录编号', trigger: 'blur' }]">-->
<!--                <el-input placeholder="请输入运输记录编号" v-model="formData.name" clearable/>-->
<!--              </el-form-item>-->
<!--<el-form-item label="行政区划代码：" prop="field.行政区划代码" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入行政区划代码', trigger: 'blur' }]">-->
<!--                <el-input placeholder="请输入行政区划代码" v-model="formData.field.行政区划代码" clearable/>-->
<!--              </el-form-item>-->
<!--<el-form-item label="车牌号：" prop="field.车牌号" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入车牌号', trigger: 'blur' }]">-->
<!--                <el-input placeholder="请输入车牌号" v-model="formData.field.车牌号" clearable/>-->
<!--              </el-form-item>-->
<!--<el-form-item label="运输司机驾驶证号：" prop="field.运输司机驾驶证号" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入运输司机驾驶证号', trigger: 'blur' }]">-->
<!--                <el-input placeholder="请输入运输司机驾驶证号" v-model="formData.field.运输司机驾驶证号" clearable/>-->
<!--              </el-form-item>-->
<!--<el-form-item label="处置证编号：" prop="field.处置证编号" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入处置证编号', trigger: 'blur' }]">-->
<!--                <el-input placeholder="请输入处置证编号" v-model="formData.field.处置证编号" clearable/>-->
<!--              </el-form-item>-->
<!--<el-form-item label="出场建筑工地编号：" prop="field.出场建筑工地编号" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入出场建筑工地编号', trigger: 'blur' }]">-->
<!--                <el-input placeholder="请输入出场建筑工地编号" v-model="formData.field.出场建筑工地编号" clearable/>-->
<!--              </el-form-item>-->
<!--<el-form-item label="入场建筑垃圾处理厂标识码：" prop="field.入场建筑垃圾处理厂标识码" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入入场建筑垃圾处理厂标识码', trigger: 'blur' }]">-->
<!--                <el-input placeholder="请输入入场建筑垃圾处理厂标识码" v-model="formData.field.入场建筑垃圾处理厂标识码" clearable/>-->
<!--              </el-form-item>-->
<!--<el-form-item label="出场时间：" prop="field.出场时间" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入出场时间', trigger: 'blur' }]">-->
<!--                <el-input placeholder="请输入出场时间" v-model="formData.field.出场时间" clearable/>-->
<!--              </el-form-item>-->
<!--<el-form-item label="入场时间：" prop="field.入场时间" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入入场时间', trigger: 'blur' }]">-->
<!--                <el-input placeholder="请输入入场时间" v-model="formData.field.入场时间" clearable/>-->
<!--              </el-form-item>-->
<!--<el-form-item label="运输量：" prop="field.运输量" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入运输量', trigger: 'blur' }]">-->
<!--                <el-input placeholder="请输入运输量" v-model="formData.field.运输量" clearable/>-->
<!--              </el-form-item>-->

<!--            </el-form>-->
<!--          </div>-->
<!--        </div>-->

<!--        &lt;!&ndash;新增的时候&ndash;&gt;-->
<!--        <div class="action-button" v-if="['add', 'edit'].includes(drawerConfig.type) && !drawerConfig.loading">-->
<!--          <el-button :icon="Close" @click="drawerConfig.isDrawer=false">取 消</el-button>-->
<!--          <el-button type="primary" :icon="Check" @click="drawerSubmitForm(drawerFromRef)">保 存</el-button>-->
<!--        </div>-->
<!--      </el-drawer>-->

<!--    </div>-->
<!--  </div>-->
<!--</template>-->

<!--            <script lang="ts" setup>-->
<!--            import {ref, reactive, onMounted} from 'vue'-->
<!--            import {Tickets, Plus, EditPen, Delete, View, Menu, Close, Check} from '@element-plus/icons-vue'-->
<!--            import {handleTypeOptions, tableFieldShow} from '@/utils/thingsBoardUtils'-->
<!--            import {assetsDel, assetsEdit, assetsEditField, assetsList, assetsListField} from "@/api/genchuan/thingsBoard/assets";-->
<!--            //table 初始化-->
<!--            const tableInitData = async () => {-->
<!--              tableConfig.value.loading = true;-->
<!--              try {-->
<!--                let params = {-->
<!--                  pageSize: tableConfig.value.pageSize,-->
<!--                  page: tableConfig.value.currentPage - 1,-->
<!--                  textSearch: searchForm.value.name,-->
<!--                  sortProperty: 'createdTime',-->
<!--                  sortOrder: 'DESC',-->
<!--                  assetProfileId: '42695520-7725-11f0-bd39-2dc5ccdb39ef',//资产配置id-->
<!--                }-->
<!--                let res = await assetsList(params);-->
<!--                tableData.value = res.data;-->
<!--                for (let i = 0; i < tableData.value.length; i++) {-->
<!--                  let params = {id: tableData.value[i].id.id}-->
<!--                  let resField = await assetsListField(params);-->
<!--                  tableData.value[i].field = resField-->
<!--                }-->
<!--                tableConfig.value.total = res.totalElements;-->
<!--                tableConfig.value.loading = false;-->
<!--              } catch (error) {-->
<!--                tableConfig.value.loading = false;-->
<!--                // 处理错误-->
<!--                console.log("初始化出错了")-->
<!--              }-->
<!--            }-->

<!--            // 表单字段-->
<!--              const initFormData = {-->
<!--                name: '',//资产名称 这个用一个字段区代表就行了-->
<!--                type: '建筑垃圾运输记录管理',//资产配置，都是写死固定的资产配置-->
<!--                field: {-->
<!--                   行政区划代码: '',//行政区划代码-->
<!--            车牌号: '',//车牌号-->
<!--            运输司机驾驶证号: '',//运输司机驾驶证号-->
<!--            处置证编号: '',//处置证编号-->
<!--            出场建筑工地编号: '',//出场建筑工地编号-->
<!--            入场建筑垃圾处理厂标识码: '',//入场建筑垃圾处理厂标识码-->
<!--            出场时间: '',//出场时间-->
<!--            入场时间: '',//入场时间-->
<!--            运输量: '',//运输量-->
<!--            -->
<!--                }-->
<!--              }-->

<!--              // 所有选项集合-->
<!--            const optionsLisi = ref({-->
<!--              -->
<!--            })-->

<!--            // 表单初始化数据-->
<!--            const formData = ref({...initFormData});-->

<!--            //表单保存-->
<!--            const drawerFromRef = ref<FormInstance>()-->
<!--            const drawerSubmitForm = async (formEl: FormInstance | undefined) => {-->
<!--              if (!formEl) return-->
<!--              const valid = await new Promise<boolean>((resolve) => {-->
<!--                formEl.validate((valid) => {-->
<!--                  resolve(valid);-->
<!--                });-->
<!--              });-->
<!--              if (valid) {-->
<!--                try {-->
<!--                  let res = await assetsEdit(formData.value);-->
<!--                  await assetsEditField(res.id.id, formData.value.field)-->
<!--                  if (drawerConfig.value.type === 'add') {-->
<!--                    tableConfig.value.currentPage = 1;-->
<!--                  }-->
<!--                  drawerConfig.value.isDrawer = false;-->
<!--                  ElMessage.success('保存成功');-->
<!--                  await tableInitData();-->
<!--                } catch (error) {-->
<!--                  if (drawerConfig.value.type === 'add') {-->
<!--                    ElMessage.error('保存失败，数据重复');-->
<!--                  }-->
<!--                  console.log('新增出错!', error);-->
<!--                }-->
<!--              } else {-->
<!--                console.log('表单验证出错!');-->
<!--                // ElMessage.warning('请完善表单信息');-->
<!--              }-->
<!--            }-->

<!--            // 点击详细按钮-->
<!--            const queryData = ref({})-->
<!--            const queryClick = async (row) => {-->
<!--              queryData.value = row;-->
<!--              console.log(row)-->
<!--              drawerConfig.value.isDrawer = true;-->
<!--              drawerConfig.value.loading = false;-->
<!--              drawerConfig.value.type = 'query';-->
<!--              drawerConfig.value.title = '建筑垃圾运输记录管理-详细信息';-->
<!--            }-->
<!--            // 点击编辑按钮-->
<!--            const editClick = async (row) => {-->
<!--              let copyRow = JSON.parse(JSON.stringify(row))-->
<!--              formData.value = copyRow;-->
<!--              formData.value.field = {};-->
<!--              formData.value.field.行政区划代码 = tableFieldShow('行政区划代码', row.field);//行政区划代码-->
<!--            formData.value.field.车牌号 = tableFieldShow('车牌号', row.field);//车牌号-->
<!--            formData.value.field.运输司机驾驶证号 = tableFieldShow('运输司机驾驶证号', row.field);//运输司机驾驶证号-->
<!--            formData.value.field.处置证编号 = tableFieldShow('处置证编号', row.field);//处置证编号-->
<!--            formData.value.field.出场建筑工地编号 = tableFieldShow('出场建筑工地编号', row.field);//出场建筑工地编号-->
<!--            formData.value.field.入场建筑垃圾处理厂标识码 = tableFieldShow('入场建筑垃圾处理厂标识码', row.field);//入场建筑垃圾处理厂标识码-->
<!--            formData.value.field.出场时间 = tableFieldShow('出场时间', row.field);//出场时间-->
<!--            formData.value.field.入场时间 = tableFieldShow('入场时间', row.field);//入场时间-->
<!--            formData.value.field.运输量 = tableFieldShow('运输量', row.field);//运输量-->
<!--            -->
<!--              drawerConfig.value.isDrawer = true;-->
<!--              drawerConfig.value.loading = false;-->
<!--              drawerConfig.value.type = 'edit';-->
<!--              drawerConfig.value.title = '编辑建筑垃圾运输记录管理';-->
<!--            }-->
<!--            // 点击删除按钮-->
<!--            const delClick = (row) => {-->
<!--              ElMessageBox.confirm(-->
<!--                  '请注意：确认后，所有相关数据将不可恢复。',-->
<!--                   `确定要删除 '${row.name}' 吗？`,-->
<!--                  {-->
<!--                    confirmButtonText: '确 定',-->
<!--                    cancelButtonText: '取 消',-->
<!--                    type: 'warning',-->
<!--                  }-->
<!--              )-->
<!--                  .then(async () => {-->
<!--                    try {-->
<!--                      await assetsDel(row.id.id)-->
<!--                      await tableInitData();-->
<!--                      ElMessage({-->
<!--                        type: 'success',-->
<!--                        message: '删除成功',-->
<!--                      })-->
<!--                    } catch (error) {-->
<!--                      // 处理错误-->
<!--                      console.log(error)-->
<!--                    }-->
<!--                  })-->
<!--                  .catch(() => {-->
<!--                    console.log('取消删除')-->
<!--                  })-->
<!--            }-->
<!--            // title点击新增-->
<!--            const addClick = async () => {-->
<!--              formData.value = {-->
<!--                ...initFormData,-->
<!--                field: {...initFormData.field}-->
<!--              };-->
<!--              drawerConfig.value.isDrawer = true;-->
<!--              drawerConfig.value.loading = false;-->
<!--              drawerConfig.value.type = 'add';-->
<!--              drawerConfig.value.title = '新增建筑垃圾运输记录管理';-->
<!--            }-->

<!--            //drawer 配置-->
<!--            const drawerConfig = ref({-->
<!--              isDrawer: false,//是否打开抽屉 true 打开 false 关闭-->
<!--              loading: true,//还没加载完成 true 在加载 false加载完成-->
<!--              title: '抽屉标题',//标题名称-->
<!--              type: 'add',//add新增 edit编辑 query详细-->
<!--              active: '1',//当前选择了哪个-->
<!--              // tabs轮动-->
<!--              tabsList: [-->
<!--                {label: '基础信息', name: '1'},-->
<!--              ]-->
<!--            })-->
<!--            //drawer tabs 点击切换-->
<!--            const tabsClick = (tab) => {-->
<!--              tableConfig.value.currentPage = 1;//当前第几页-->
<!--              tableInitData();-->
<!--              console.log('tabs点击切换', tab.props.name)-->
<!--            }-->
<!--            // title配置-->
<!--            const titleConfig = ref({-->
<!--              name: '建筑垃圾运输记录管理',//标题名称-->
<!--              addButName: '新增建筑垃圾运输记录管理',//新增按钮名称-->
<!--            })-->
<!--            // 搜索 字段-->
<!--            const initSearchForm = {-->
<!--              name: '',//配置名称-->
<!--            }-->
<!--            // 搜索初始化数据-->
<!--            const searchForm = ref({...initSearchForm});-->
<!--            // 搜索 点击-->
<!--            const searchClick = () => {-->
<!--              tableConfig.value.currentPage = 1;-->
<!--              tableInitData();-->
<!--            }-->
<!--            //搜索 重置-->
<!--            const searchResetClick = () => {-->
<!--              searchForm.value = {...initSearchForm};-->
<!--              tableConfig.value.currentPage = 1;-->
<!--              tableInitData();-->
<!--            }-->
<!--            //table 列显隐信息-->
<!--          const tableColumns = ref([-->
<!--            {label: '运输记录编号', visible: true},-->
<!--            {label: '行政区划代码', visible: true},-->
<!--            {label: '车牌号', visible: true},-->
<!--            {label: '运输司机驾驶证号', visible: true},-->
<!--            {label: '处置证编号', visible: true},-->
<!--            {label: '出场建筑工地编号', visible: true},-->
<!--            {label: '入场建筑垃圾处理厂标识码', visible: true},-->
<!--            {label: '出场时间', visible: true},-->
<!--            {label: '入场时间', visible: true},-->
<!--            {label: '运输量', visible: true},-->
<!--            -->
<!--          ])-->
<!--          // table表格数据-->
<!--          const tableData = ref([])-->
<!--          //table 配置-->
<!--          const tableConfig = ref({-->
<!--            height: 0,//表格高度-->
<!--            loading: true,//是否加载完成-->
<!--            currentPage: 1,//当前第几页-->
<!--            pageSize: 10,//每页几条-->
<!--            total: 0,//总数-->
<!--          })-->
<!--          //table 列是否显示-->
<!--          const tableColumnShow = (label) => {-->
<!--            for (let i = 0; i < tableColumns.value.length; i++) {-->
<!--              if (tableColumns.value[i].label === label) {-->
<!--                return tableColumns.value[i].visible-->
<!--              }-->
<!--            }-->
<!--          }-->
<!--          //table 设置高度-->
<!--          const searchRef = ref(null);-->
<!--          const getTableHeight = () => {-->
<!--            setTimeout(() => {-->
<!--              if (searchRef.value) {-->
<!--                const searchHeight = searchRef.value.clientHeight;-->
<!--                let height = document.documentElement.clientHeight - searchHeight - 52 - 107 - 70-->
<!--                tableConfig.value.height = height-->
<!--              }-->
<!--            }, 300)-->
<!--          }-->
<!--          //table 给表头加样式-->
<!--          const getHeaderCellStyle = () => {-->
<!--            return {-->
<!--              background: '#f5f7fa',-->
<!--              color: '#081333',-->
<!--              fontWeight: 'normal'-->
<!--            };-->
<!--          };-->
<!--          //table 分页 选择了每页几条-->
<!--          const pageSizeChange = (val) => {-->
<!--            tableConfig.value.currentPage = 1;//当前第几页-->
<!--            tableConfig.value.pageSize = val;//每页几条-->
<!--            tableInitData();-->
<!--          };-->
<!--          //table 分页 点击了跳转到第几页-->
<!--          const pageCurrentChange = (val) => {-->
<!--            tableConfig.value.currentPage = val;//当前第几页-->
<!--            tableInitData();-->
<!--          };-->

<!--          // 定义要添加到 body 的 class 名称-->
<!--          const customClass = 'thingsBoard-body-class';-->
<!--          const addClassToBody = () => {-->
<!--            document.body.classList.add(customClass);-->
<!--          };-->
<!--          const removeClassFromBody = () => {-->
<!--            document.body.classList.remove(customClass);-->
<!--          };-->

<!--          // 挂载完毕-->
<!--          onMounted(() => {-->
<!--            console.log('挂载完毕2')-->
<!--            addClassToBody();//给body加class控制样式-->
<!--            getTableHeight();//给table获取高度-->
<!--            tableInitData();//数据初始化-->
<!--          })-->
<!--          // 在组件卸载时移除 class-->
<!--          onUnmounted(() => {-->
<!--            removeClassFromBody();-->
<!--          });-->
<!--          </script>-->
<!--            <style lang="scss" scoped></style>-->
<template>
  <div class="cc-main">
    <div class="cc-main-content">
      <!--title-->
      <div class="cc-title-css">
        <div class="left">
          <el-icon class="left-icon">
            <Tickets/>
          </el-icon>
          <span>{{ titleConfig.name }}</span>
        </div>
        <div class="right">
          <el-button type="primary" plain :icon="Plus" @click="addClick(titleConfig.addButName)" v-hasPermi="['smarturban:constructionwastetransportlogmgmt:create']">
            {{ titleConfig.addButName }}
          </el-button>
        </div>
      </div>

      <!--搜索内容字段-->
      <div class="cc-search-css" ref="searchRef">
        <el-form class="-mb-15px" :model="searchForm" ref="searchFormRef" :inline="true" label-width="68px">
          <el-form-item label="运输记录编号" prop="name">
            <el-input v-model="searchForm.name" placeholder="请输入运输记录编号" clearable class="!w-240px"/>
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
          <el-table-column label="运输记录编号" min-width="180" align="center" prop="name" :show-overflow-tooltip="true" v-if="tableColumnShow('运输记录编号')"/>
          <el-table-column label="车牌号" min-width="120" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('车牌号')">
            <template #default="scope">
              {{ tableFieldShow('车牌号', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column label="入场建筑垃圾处理厂标识码" min-width="180" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('入场建筑垃圾处理厂标识码')">
            <template #default="scope">
              {{ tableFieldShow('入场建筑垃圾处理厂标识码', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column label="出场时间" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('出场时间')">
            <template #default="scope">
              {{ tableFieldShow('出场时间', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column label="行政区划代码" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('行政区划代码')">
            <template #default="scope">
              {{ tableFieldShow('行政区划代码', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column label="运输司机驾驶证号" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('运输司机驾驶证号')">
            <template #default="scope">
              {{ tableFieldShow('运输司机驾驶证号', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column label="处置证编号" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('处置证编号')">
            <template #default="scope">
              {{ tableFieldShow('处置证编号', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column label="出场建筑工地编号" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('出场建筑工地编号')">
            <template #default="scope">
              {{ tableFieldShow('出场建筑工地编号', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column label="入场时间" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('入场时间')">
            <template #default="scope">
              {{ tableFieldShow('入场时间', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column label="运输量" min-width="100" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('运输量')">
            <template #default="scope">
              {{ tableFieldShow('运输量', scope.row.field) }}
            </template>
          </el-table-column>

          <el-table-column width="300" fixed="right" label="操作" align="center">
            <template #default="scope">
              <el-button type="success" :icon="View" :plain="true" @click="queryClick(scope.row)" v-hasPermi="['smarturban:constructionwastetransportlogmgmt:query']">详细</el-button>
              <el-button type="primary" :icon="EditPen" :plain="true" @click="editClick(scope.row)" v-hasPermi="['smarturban:constructionwastetransportlogmgmt:update']">编辑</el-button>
              <el-button type="danger" :icon="Delete" :plain="true" @click="delClick(scope.row)" v-hasPermi="['smarturban:constructionwastetransportlogmgmt:delete']">删除</el-button>
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
        <template header>
          <span>{{ drawerConfig.title }}</span>
        </template>

        <div class="drawer-content">
          <el-tabs v-model="drawerConfig.active" class="content-top-tabs" @tab-click="tabsClick">
            <el-tab-pane label="基础信息" name="1" v-if="drawerConfig.type=='add'"/>
            <el-tab-pane v-else v-for="item in drawerConfig.tabsList" :key="item.value" :label="item.label" :name="item.value"/>
          </el-tabs>

          <!--查看-->
          <div class="see-content" v-loading="drawerConfig.loading" v-if="drawerConfig.type==='query'">
            <ul>
              <li class="width50"><span>运输记录编号：</span>{{ queryData.name }}</li>
              <li class="width50"><span>车牌号：</span>{{ tableFieldShow('车牌号', queryData.field) }}</li>
              <li class="width50"><span>入场建筑垃圾处理厂标识码：</span>{{ tableFieldShow('入场建筑垃圾处理厂标识码', queryData.field) }}</li>
              <li class="width50"><span>出场时间：</span>{{ tableFieldShow('出场时间', queryData.field) }}</li>
              <li class="width50"><span>行政区划代码：</span>{{ tableFieldShow('行政区划代码', queryData.field) }}</li>
              <li class="width50"><span>运输司机驾驶证号：</span>{{ tableFieldShow('运输司机驾驶证号', queryData.field) }}</li>
              <li class="width50"><span>处置证编号：</span>{{ tableFieldShow('处置证编号', queryData.field) }}</li>
              <li class="width50"><span>出场建筑工地编号：</span>{{ tableFieldShow('出场建筑工地编号', queryData.field) }}</li>
              <li class="width50"><span>入场时间：</span>{{ tableFieldShow('入场时间', queryData.field) }}</li>
              <li class="width50"><span>运输量：</span>{{ tableFieldShow('运输量', queryData.field) }}</li>
            </ul>
          </div>

          <!--编辑-->
          <div class="edit-content" v-loading="drawerConfig.loading" v-else>
            <el-form :model="formData" ref="drawerFromRef" class="edit-content-form" label-width="180px">
              <el-form-item label="运输记录编号：" prop="name" class="edit-content-form-li width50">
                <el-input
                  placeholder="自动生成"
                  v-model="formData.name"
                  readonly
                  :title="'自动生成的运输记录编号: ' + formData.name"
                />
              </el-form-item>
              <el-form-item label="车牌号：" prop="field.车牌号" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入车牌号', trigger: 'blur' }]">
                <el-input placeholder="请输入车牌号" v-model="formData.field.车牌号" clearable @change="updateTransportCode"/>
              </el-form-item>
              <el-form-item label="入场建筑垃圾处理厂标识码：" prop="field.入场建筑垃圾处理厂标识码" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入入场建筑垃圾处理厂标识码', trigger: 'blur' }]">
                <el-input placeholder="请输入入场建筑垃圾处理厂标识码" v-model="formData.field.入场建筑垃圾处理厂标识码" clearable @change="updateTransportCode"/>
              </el-form-item>
<!--              <el-form-item label="出场时间：" prop="field.出场时间" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入出场时间', trigger: 'blur' }]">-->
<!--                <el-date-picker-->
<!--                  v-model="formData.field.出场时间"-->
<!--                  type="datetime"-->
<!--                  placeholder="选择出场时间"-->
<!--                  value-format="YYYY-MM-DD HH:mm:ss"-->
<!--                  @change="updateTransportCode"-->
<!--                  style="width: 100%"-->
<!--                />-->
<!--              </el-form-item>-->
              <el-form-item label="出场时间：" prop="field.出场时间" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入出场时间', trigger: 'blur' }]">
                              <el-input placeholder="请输入出场时间" v-model="formData.field.出场时间" clearable/>
                            </el-form-item>
              <el-form-item label="行政区划代码：" prop="field.行政区划代码" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入行政区划代码', trigger: 'blur' }]">
                <el-input placeholder="请输入行政区划代码" v-model="formData.field.行政区划代码" clearable/>
              </el-form-item>
              <el-form-item label="运输司机驾驶证号：" prop="field.运输司机驾驶证号" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入运输司机驾驶证号', trigger: 'blur' }]">
                <el-input placeholder="请输入运输司机驾驶证号" v-model="formData.field.运输司机驾驶证号" clearable/>
              </el-form-item>
              <el-form-item label="处置证编号：" prop="field.处置证编号" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入处置证编号', trigger: 'blur' }]">
                <el-input placeholder="请输入处置证编号" v-model="formData.field.处置证编号" clearable/>
              </el-form-item>
              <el-form-item label="出场建筑工地编号：" prop="field.出场建筑工地编号" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入出场建筑工地编号', trigger: 'blur' }]">
                <el-input placeholder="请输入出场建筑工地编号" v-model="formData.field.出场建筑工地编号" clearable/>
              </el-form-item>
              <el-form-item label="入场时间：" prop="field.入场时间" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入入场时间', trigger: 'blur' }]">
                              <el-input placeholder="请输入入场时间" v-model="formData.field.入场时间" clearable/>
                            </el-form-item>
              <el-form-item label="运输量：" prop="field.运输量" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入运输量', trigger: 'blur' }]">
                <el-input placeholder="请输入运输量" v-model="formData.field.运输量" clearable/>
              </el-form-item>
            </el-form>
          </div>
        </div>

        <!--新增/编辑时的操作按钮-->
        <div class="action-button" v-if="['add', 'edit'].includes(drawerConfig.type) && !drawerConfig.loading">
          <el-button :icon="Close" @click="drawerConfig.isDrawer=false">取 消</el-button>
          <el-button type="primary" :icon="Check" @click="drawerSubmitForm(drawerFromRef)">保 存</el-button>
        </div>
      </el-drawer>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive, onMounted, watch } from 'vue'
import { Tickets, Plus, EditPen, Delete, View, Menu, Close, Check } from '@element-plus/icons-vue'
import { handleTypeOptions, tableFieldShow } from '@/utils/thingsBoardUtils'
import { assetsDel, assetsEdit, assetsEditField, assetsList, assetsListField } from "@/api/genchuan/thingsBoard/assets";
import { ElMessage, ElMessageBox } from 'element-plus'
import type { FormInstance } from 'element-plus'

// 自动生成运输记录编号 - 格式：车牌号-厂标识码-出场时间(日期部分)
const generateTransportCode = (row) => {
  if (!row.field) return '';
  const { 车牌号, 入场建筑垃圾处理厂标识码, 出场时间 } = row.field;
  // 格式化出场时间，只保留日期部分
  const formattedDate = 出场时间 ? 出场时间.split(' ')[0] : '';
  return `${车牌号 || ''}-${入场建筑垃圾处理厂标识码 || ''}-${formattedDate || ''}`;
}

// 手动触发更新运输记录编号
const updateTransportCode = () => {
  formData.value.name = generateTransportCode(formData.value);
}

// 表单初始化数据
const initFormData = {
  name: '', // 运输记录编号将自动生成
  type: '建筑垃圾运输记录管理',
  field: {
    行政区划代码: '',
    车牌号: '',
    入场建筑垃圾处理厂标识码: '',
    运输司机驾驶证号: '',
    处置证编号: '',
    出场建筑工地编号: '',
    出场时间: '',
    入场时间: '',
    运输量: ''
  }
}

// 表单数据
const formData = ref({...initFormData});

// 监听关键字段变化，自动更新运输记录编号
watch(() => [
  formData.value.field.车牌号,
  formData.value.field.入场建筑垃圾处理厂标识码,
  formData.value.field.出场时间
], () => {
  formData.value.name = generateTransportCode(formData.value);
}, { deep: true });

// 搜索初始化数据
const initSearchForm = {
  name: '',
}

// 搜索表单数据
const searchForm = ref({...initSearchForm});

// 查询数据
const queryData = ref({})

// 表格数据
const tableData = ref([])

// 表格列配置
const tableColumns = ref([
  {label: '运输记录编号', visible: true},
  {label: '车牌号', visible: true},
  {label: '入场建筑垃圾处理厂标识码', visible: true},
  {label: '出场时间', visible: true},
  {label: '行政区划代码', visible: true},
  {label: '运输司机驾驶证号', visible: true},
  {label: '处置证编号', visible: true},
  {label: '出场建筑工地编号', visible: true},
  {label: '入场时间', visible: true},
  {label: '运输量', visible: true},
])

// 表格配置
const tableConfig = ref({
  height: 0,
  loading: true,
  currentPage: 1,
  pageSize: 10,
  total: 0,
})

// 标题配置
const titleConfig = ref({
  name: '建筑垃圾运输记录管理',
  addButName: '新增建筑垃圾运输记录管理',
})

// 抽屉配置
const drawerConfig = ref({
  isDrawer: false,
  loading: true,
  title: '抽屉标题',
  type: 'add',
  active: '1',
  tabsList: [
    {label: '基础信息', name: '1'},
  ]
})

// 表单引用
const drawerFromRef = ref<FormInstance>()
const searchFormRef = ref<FormInstance>()
const searchRef = ref(null)
const tableRef = ref(null)

// 表格初始化
const tableInitData = async () => {
  tableConfig.value.loading = true;
  try {
    let params = {
      pageSize: tableConfig.value.pageSize,
      page: tableConfig.value.currentPage - 1,
      textSearch: searchForm.value.name,
      sortProperty: 'createdTime',
      sortOrder: 'DESC',
      assetProfileId: '42695520-7725-11f0-bd39-2dc5ccdb39ef',
    }
    let res = await assetsList(params);
    tableData.value = res.data;
    for (let i = 0; i < tableData.value.length; i++) {
      let params = {id: tableData.value[i].id.id}
      let resField = await assetsListField(params);
      tableData.value[i].field = resField;
      // 确保显示正确的运输记录编号
      tableData.value[i].name = generateTransportCode(tableData.value[i]);
    }
    tableConfig.value.total = res.totalElements;
    tableConfig.value.loading = false;
  } catch (error) {
    tableConfig.value.loading = false;
    console.error("初始化出错", error);
    ElMessage.error('数据加载失败');
  }
}

// 表单保存
const drawerSubmitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  const valid = await new Promise<boolean>((resolve) => {
    formEl.validate((valid) => {
      resolve(valid);
    });
  });

  if (valid) {
    try {
      // 确保运输记录编号是最新的
      formData.value.name = generateTransportCode(formData.value);

      let res = await assetsEdit(formData.value);
      await assetsEditField(res.id.id, formData.value.field);

      if (drawerConfig.value.type === 'add') {
        tableConfig.value.currentPage = 1;
      }
      drawerConfig.value.isDrawer = false;
      ElMessage.success('保存成功');
      await tableInitData();
    } catch (error) {
      console.error('保存出错', error);
      ElMessage.error('保存失败，请检查数据');
    }
  } else {
    ElMessage.warning('请完善表单信息');
  }
}

// 点击详细按钮
const queryClick = async (row) => {
  queryData.value = row;
  drawerConfig.value.isDrawer = true;
  drawerConfig.value.loading = false;
  drawerConfig.value.type = 'query';
  drawerConfig.value.title = '建筑垃圾运输记录管理-详细信息';
}

// 点击编辑按钮
const editClick = async (row) => {
  let copyRow = JSON.parse(JSON.stringify(row));
  formData.value = copyRow;
  formData.value.field = {};
  formData.value.field.行政区划代码 = tableFieldShow('行政区划代码', row.field);
  formData.value.field.车牌号 = tableFieldShow('车牌号', row.field);
  formData.value.field.入场建筑垃圾处理厂标识码 = tableFieldShow('入场建筑垃圾处理厂标识码', row.field);
  formData.value.field.运输司机驾驶证号 = tableFieldShow('运输司机驾驶证号', row.field);
  formData.value.field.处置证编号 = tableFieldShow('处置证编号', row.field);
  formData.value.field.出场建筑工地编号 = tableFieldShow('出场建筑工地编号', row.field);
  formData.value.field.出场时间 = tableFieldShow('出场时间', row.field);
  formData.value.field.入场时间 = tableFieldShow('入场时间', row.field);
  formData.value.field.运输量 = tableFieldShow('运输量', row.field);

  // 自动生成运输记录编号
  formData.value.name = generateTransportCode(formData.value);

  drawerConfig.value.isDrawer = true;
  drawerConfig.value.loading = false;
  drawerConfig.value.type = 'edit';
  drawerConfig.value.title = '编辑建筑垃圾运输记录管理';
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
        await assetsDel(row.id.id);
        await tableInitData();
        ElMessage.success('删除成功');
      } catch (error) {
        console.error('删除出错', error);
        ElMessage.error('删除失败');
      }
    })
    .catch(() => {
      console.log('取消删除');
    })
}

// 点击新增按钮
const addClick = async () => {
  formData.value = {
    ...initFormData,
    field: {...initFormData.field}
  };
  drawerConfig.value.isDrawer = true;
  drawerConfig.value.loading = false;
  drawerConfig.value.type = 'add';
  drawerConfig.value.title = '新增建筑垃圾运输记录管理';
}

// 搜索点击
const searchClick = () => {
  tableConfig.value.currentPage = 1;
  tableInitData();
}

// 搜索重置
const searchResetClick = () => {
  searchForm.value = {...initSearchForm};
  tableConfig.value.currentPage = 1;
  tableInitData();
}

// 表格列是否显示
const tableColumnShow = (label) => {
  return tableColumns.value.find(item => item.label === label)?.visible;
}

// 设置表格高度
const getTableHeight = () => {
  setTimeout(() => {
    if (searchRef.value) {
      const searchHeight = searchRef.value.clientHeight;
      let height = document.documentElement.clientHeight - searchHeight - 52 - 107 - 70;
      tableConfig.value.height = height;
    }
  }, 300);
}

// 表头样式
const getHeaderCellStyle = () => {
  return {
    background: '#f5f7fa',
    color: '#081333',
    fontWeight: 'normal'
  };
};

// 分页大小变化
const pageSizeChange = (val) => {
  tableConfig.value.currentPage = 1;
  tableConfig.value.pageSize = val;
  tableInitData();
};

// 分页当前页变化
const pageCurrentChange = (val) => {
  tableConfig.value.currentPage = val;
  tableInitData();
};

// tabs点击
const tabsClick = (tab) => {
  tableConfig.value.currentPage = 1;
  tableInitData();
  console.log('tabs点击切换', tab.props.name);
}

// 挂载
onMounted(() => {
  console.log('组件挂载完毕');
  getTableHeight();
  tableInitData();
  window.addEventListener('resize', getTableHeight);
});

// 卸载
onUnmounted(() => {
  window.removeEventListener('resize', getTableHeight);
});
</script>

<style lang="scss" scoped>
.cc-main {
  height: 100%;
  .cc-main-content {
    height: 100%;
    padding: 15px;
    background-color: #fff;
    .cc-title-css {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding-bottom: 15px;
      .left {
        display: flex;
        align-items: center;
        .left-icon {
          font-size: 20px;
          margin-right: 10px;
          color: var(--el-color-primary);
        }
        span {
          font-size: 18px;
          font-weight: bold;
        }
      }
    }
    .cc-search-css {
      padding: 15px 15px 0;
      background-color: #f5f7fa;
      border-radius: 4px;
      margin-bottom: 15px;
    }
    .cc-table-css {
      background-color: #fff;
      border-radius: 4px;
      &-page {
        padding-top: 15px;
        display: flex;
        justify-content: flex-end;
      }
    }
  }
}

.drawer-content {
  padding: 20px;
  .see-content {
    ul {
      display: flex;
      flex-wrap: wrap;
      li {
        margin-bottom: 15px;
        span {
          color: #999;
          margin-right: 10px;
        }
        &.width100 {
          width: 100%;
        }
        &.width50 {
          width: 50%;
        }
        &.width33 {
          width: 33.33%;
        }
      }
    }
  }
  .edit-content {
    &-form {
      display: flex;
      flex-wrap: wrap;
      &-li {
        padding-right: 20px;
        &.width100 {
          width: 100%;
        }
        &.width50 {
          width: 50%;
        }
        &.width33 {
          width: 33.33%;
        }
      }
    }
  }
}

.action-button {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  padding: 15px;
  background-color: #fff;
  border-top: 1px solid #eee;
  display: flex;
  justify-content: flex-end;
}
</style>
