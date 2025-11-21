
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
          <el-button type="primary" plain :icon="Plus" @click="addClick(titleConfig.addButName)" v-hasPermi="['smarturban:constructionwastedisposallicensemgmt:create']"> {{ titleConfig.addButName }}</el-button>
        </div>
      </div>
<!--搜索内容字段-->
      <div class="cc-search-css" ref="searchRef">
        <el-form class="-mb-15px" :model="searchForm" ref="searchFormRef" :inline="true" label-width="68px">
          <!--@keyup.enter="handleQuery"-->
          <el-form-item label="处置证编号" prop="name">
            <el-input v-model="searchForm.name" placeholder="请输入处置证编号" clearable class="!w-240px"/>
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
          <el-table-column label="行政区划代码" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('行政区划代码')">
            <template #default="scope">
              {{ tableFieldShow('行政区划代码', scope.row.field) }}
            </template>
          </el-table-column>
<el-table-column label="处置证编号" min-width="150" align="center" prop="name" :show-overflow-tooltip="true" v-if="tableColumnShow('处置证编号')"/>
<el-table-column label="建筑工地名称" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('建筑工地名称')">
            <template #default="scope">
              {{ tableFieldShow('建筑工地名称', scope.row.field) }}
            </template>
          </el-table-column>
<el-table-column label="建筑单位" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('建筑单位')">
            <template #default="scope">
              {{ tableFieldShow('建筑单位', scope.row.field) }}
            </template>
          </el-table-column>
<el-table-column label="工程建筑垃圾量" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('工程建筑垃圾量')">
            <template #default="scope">
              {{ tableFieldShow('工程建筑垃圾量', scope.row.field) }} t
            </template>
          </el-table-column>
<el-table-column label="废物体积" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('废物体积')">
            <template #default="scope">
              {{ tableFieldShow('废物体积', scope.row.field) }} m³
            </template>
          </el-table-column>
<el-table-column label="残留体积" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('残留体积')">
            <template #default="scope">
              {{ tableFieldShow('残留体积', scope.row.field) }} m³
            </template>
          </el-table-column>
<el-table-column label="交易场所名称" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('交易场所名称')">
            <template #default="scope">
              {{ tableFieldShow('交易场所名称', scope.row.field) }}
            </template>
          </el-table-column>
<el-table-column label="交易场所类型" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('交易场所类型')">
            <template #default="scope">
              {{ tableFieldShow('交易场所类型', scope.row.field) }}
            </template>
          </el-table-column>
<el-table-column label="预估费用" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('预估费用')">
            <template #default="scope">
              {{ tableFieldShow('预估费用', scope.row.field) }} 元
            </template>
          </el-table-column>
<el-table-column label="运输企业名称" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('运输企业名称')">
            <template #default="scope">
              {{ tableFieldShow('运输企业名称', scope.row.field) }}
            </template>
          </el-table-column>
<el-table-column label="运输车数量" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('运输车数量')">
            <template #default="scope">
              {{ tableFieldShow('运输车数量', scope.row.field) }} 辆
            </template>
          </el-table-column>
<el-table-column label="车辆代码" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('车辆代码')">
            <template #default="scope">
              {{ tableFieldShow('车辆代码', scope.row.field) }}
            </template>
          </el-table-column>
<el-table-column label="运输结束时间" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('运输结束时间')">
            <template #default="scope">
              {{ tableFieldShow('运输结束时间', scope.row.field) }}
            </template>
          </el-table-column>
<el-table-column label="运输开始时间" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('运输开始时间')">
            <template #default="scope">
              {{ tableFieldShow('运输开始时间', scope.row.field) }}
            </template>
          </el-table-column>

          <el-table-column width="300" fixed="right" label="操作" align="center">
            <template #default="scope">
              <el-button type="success" :icon="View" :plain="true" @click="queryClick(scope.row)" v-hasPermi="['smarturban:constructionwastedisposallicensemgmt:query']">详细</el-button>
              <el-button type="primary" :icon="EditPen" :plain="true" @click="editClick(scope.row)" v-hasPermi="['smarturban:constructionwastedisposallicensemgmt:update']">编辑</el-button>
              <el-button type="danger" :icon="Delete" :plain="true" @click="delClick(scope.row)" v-hasPermi="['smarturban:constructionwastedisposallicensemgmt:delete']">删除</el-button>
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
                 <li class="width50"><span>行政区划代码：</span>{{ tableFieldShow('行政区划代码', queryData.field) }}</li>
<li class="width50"><span>处置证编号：</span>{{ queryData.name }}</li>
 <li class="width50"><span>建筑工地名称：</span>{{ tableFieldShow('建筑工地名称', queryData.field) }}</li>
 <li class="width50"><span>建筑单位：</span>{{ tableFieldShow('建筑单位', queryData.field) }}</li>
 <li class="width50"><span>工程建筑垃圾量：</span>{{ tableFieldShow('工程建筑垃圾量', queryData.field) }}</li>
 <li class="width50"><span>废物体积：</span>{{ tableFieldShow('废物体积', queryData.field) }}</li>
 <li class="width50"><span>残留体积：</span>{{ tableFieldShow('残留体积', queryData.field) }}</li>
 <li class="width50"><span>交易场所名称：</span>{{ tableFieldShow('交易场所名称', queryData.field) }}</li>
 <li class="width50"><span>交易场所类型：</span>{{ tableFieldShow('交易场所类型', queryData.field) }}</li>
 <li class="width50"><span>预估费用：</span>{{ tableFieldShow('预估费用', queryData.field) }}</li>
 <li class="width50"><span>运输企业名称：</span>{{ tableFieldShow('运输企业名称', queryData.field) }}</li>
 <li class="width50"><span>运输车数量：</span>{{ tableFieldShow('运输车数量', queryData.field) }}</li>
 <li class="width50"><span>车辆代码：</span>{{ tableFieldShow('车辆代码', queryData.field) }}</li>
 <li class="width50"><span>运输结束时间：</span>{{ tableFieldShow('运输结束时间', queryData.field) }}</li>
 <li class="width50"><span>运输开始时间：</span>{{ tableFieldShow('运输开始时间', queryData.field) }}</li>

            </ul>
          </div>

          <!--编辑 edit-content-form-li 里面添加 width100 width50 width33 就可以一列 二列 三列-->
          <div class="edit-content" v-loading="drawerConfig.loading" v-else>
            <el-form :model="formData" ref="drawerFromRef" class="edit-content-form" label-width="140px">
              <el-form-item label="行政区划代码：" prop="field.行政区划代码" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入行政区划代码', trigger: 'blur' }]">
                <el-input placeholder="请输入行政区划代码" v-model="formData.field.行政区划代码" clearable/>
              </el-form-item>
 <el-form-item label="处置证编号：" prop="name" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入处置证编号', trigger: 'blur' }]">
                <el-input placeholder="请输入处置证编号" v-model="formData.name" clearable/>
              </el-form-item>
<el-form-item label="建筑工地名称：" prop="field.建筑工地名称" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入建筑工地名称', trigger: 'blur' }]">
                <el-input placeholder="请输入建筑工地名称" v-model="formData.field.建筑工地名称" clearable/>
              </el-form-item>
<el-form-item label="建筑单位：" prop="field.建筑单位" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入建筑单位', trigger: 'blur' }]">
                <el-input placeholder="请输入建筑单位" v-model="formData.field.建筑单位" clearable/>
              </el-form-item>
<el-form-item label="工程建筑垃圾量：" prop="field.工程建筑垃圾量" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入工程建筑垃圾量', trigger: 'blur' }]">
                <el-input placeholder="请输入工程建筑垃圾量" v-model="formData.field.工程建筑垃圾量" clearable>
                  <template #append>t</template>
                </el-input>
              </el-form-item>
<el-form-item label="废物体积：" prop="field.废物体积" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入废物体积', trigger: 'blur' }]">
                <el-input placeholder="请输入废物体积" v-model="formData.field.废物体积" clearable>
                  <template #append>m³</template>
                </el-input>
              </el-form-item>
<el-form-item label="残留体积：" prop="field.残留体积" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入残留体积', trigger: 'blur' }]">
                <el-input placeholder="请输入残留体积" v-model="formData.field.残留体积" clearable>
                  <template #append>m³</template>
                </el-input>
              </el-form-item>
<el-form-item label="交易场所名称：" prop="field.交易场所名称" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入交易场所名称', trigger: 'blur' }]">
                <el-input placeholder="请输入交易场所名称" v-model="formData.field.交易场所名称" clearable/>
              </el-form-item>
<el-form-item label="交易场所类型：" prop="field.交易场所类型" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入交易场所类型', trigger: 'blur' }]">
                <el-input placeholder="请输入交易场所类型" v-model="formData.field.交易场所类型" clearable/>
              </el-form-item>
<el-form-item label="预估费用：" prop="field.预估费用" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入预估费用', trigger: 'blur' }]">
                <el-input placeholder="请输入预估费用" v-model="formData.field.预估费用" clearable>
                  <template #append>元</template>
                </el-input>
              </el-form-item>
<el-form-item label="运输企业名称：" prop="field.运输企业名称" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入运输企业名称', trigger: 'blur' }]">
                <el-input placeholder="请输入运输企业名称" v-model="formData.field.运输企业名称" clearable/>
              </el-form-item>
<el-form-item label="运输车数量：" prop="field.运输车数量" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入运输车数量', trigger: 'blur' }]">
                <el-input placeholder="请输入运输车数量" v-model="formData.field.运输车数量" clearable>
                  <template #append>辆</template>
                </el-input>
              </el-form-item>
<el-form-item label="车辆代码：" prop="field.车辆代码" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入车辆代码', trigger: 'blur' }]">
                <el-input placeholder="请输入车辆代码" v-model="formData.field.车辆代码" clearable/>
              </el-form-item>
<el-form-item label="运输结束时间：" prop="field.运输结束时间" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入运输结束时间', trigger: 'blur' }]">
                <el-input placeholder="请输入运输结束时间" v-model="formData.field.运输结束时间" clearable/>
              </el-form-item>
<el-form-item label="运输开始时间：" prop="field.运输开始时间" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入运输开始时间', trigger: 'blur' }]">
                <el-input placeholder="请输入运输开始时间" v-model="formData.field.运输开始时间" clearable/>
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
                  assetProfileId: '5681c030-7723-11f0-bd39-2dc5ccdb39ef',//资产配置id
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
                type: '建筑垃圾处置证管理',//资产配置，都是写死固定的资产配置
                field: {
                   行政区划代码: '',//行政区划代码
            建筑工地名称: '',//建筑工地名称
            建筑单位: '',//建筑单位
            工程建筑垃圾量: '',//工程建筑垃圾量
            废物体积: '',//废物体积
            残留体积: '',//残留体积
            交易场所名称: '',//交易场所名称
            交易场所类型: '',//交易场所类型
            预估费用: '',//预估费用
            运输企业名称: '',//运输企业名称
            运输车数量: '',//运输车数量
            车辆代码: '',//车辆代码
            运输结束时间: '',//运输结束时间
            运输开始时间: '',//运输开始时间

                }
              }

              // 所有选项集合
            const optionsLisi = ref({

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
              drawerConfig.value.title = '建筑垃圾处置证管理-详细信息';
            }
            // 点击编辑按钮
            const editClick = async (row) => {
              let copyRow = JSON.parse(JSON.stringify(row))
              formData.value = copyRow;
              formData.value.field = {};
              formData.value.field.行政区划代码 = tableFieldShow('行政区划代码', row.field);//行政区划代码
            formData.value.field.建筑工地名称 = tableFieldShow('建筑工地名称', row.field);//建筑工地名称
            formData.value.field.建筑单位 = tableFieldShow('建筑单位', row.field);//建筑单位
            formData.value.field.工程建筑垃圾量 = tableFieldShow('工程建筑垃圾量', row.field);//工程建筑垃圾量
            formData.value.field.废物体积 = tableFieldShow('废物体积', row.field);//废物体积
            formData.value.field.残留体积 = tableFieldShow('残留体积', row.field);//残留体积
            formData.value.field.交易场所名称 = tableFieldShow('交易场所名称', row.field);//交易场所名称
            formData.value.field.交易场所类型 = tableFieldShow('交易场所类型', row.field);//交易场所类型
            formData.value.field.预估费用 = tableFieldShow('预估费用', row.field);//预估费用
            formData.value.field.运输企业名称 = tableFieldShow('运输企业名称', row.field);//运输企业名称
            formData.value.field.运输车数量 = tableFieldShow('运输车数量', row.field);//运输车数量
            formData.value.field.车辆代码 = tableFieldShow('车辆代码', row.field);//车辆代码
            formData.value.field.运输结束时间 = tableFieldShow('运输结束时间', row.field);//运输结束时间
            formData.value.field.运输开始时间 = tableFieldShow('运输开始时间', row.field);//运输开始时间

              drawerConfig.value.isDrawer = true;
              drawerConfig.value.loading = false;
              drawerConfig.value.type = 'edit';
              drawerConfig.value.title = '编辑建筑垃圾处置证管理';
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
              drawerConfig.value.title = '新增建筑垃圾处置证管理';
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
              name: '建筑垃圾处置证管理',//标题名称
              addButName: '新增建筑垃圾处置证管理',//新增按钮名称
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
            {label: '行政区划代码', visible: true},
            {label: '处置证编号', visible: true},
            {label: '建筑工地名称', visible: true},
            {label: '建筑单位', visible: true},
            {label: '工程建筑垃圾量', visible: true},
            {label: '废物体积', visible: true},
            {label: '残留体积', visible: true},
            {label: '交易场所名称', visible: true},
            {label: '交易场所类型', visible: true},
            {label: '预估费用', visible: true},
            {label: '运输企业名称', visible: true},
            {label: '运输车数量', visible: true},
            {label: '车辆代码', visible: true},
            {label: '运输结束时间', visible: true},
            {label: '运输开始时间', visible: true},

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
