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
          <el-button type="primary" plain :icon="Plus" @click="addClick(titleConfig.addButName)" v-hasPermi="['smarturban:constructionsitedatamgmt:create']"> {{ titleConfig.addButName }}</el-button>
        </div>
      </div>
<!--搜索内容字段-->
      <div class="cc-search-css" ref="searchRef">
        <el-form class="-mb-15px" :model="searchForm" ref="searchFormRef" :inline="true" label-width="68px">
          <!--@keyup.enter="handleQuery"-->
          <el-form-item label="建筑工地编号" prop="name">
            <el-input v-model="searchForm.name" placeholder="请输入建筑工地编号" clearable class="!w-240px"/>
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
<el-table-column label="建筑工地编号" min-width="150" align="center" prop="name" :show-overflow-tooltip="true" v-if="tableColumnShow('建筑工地编号')"/>
<el-table-column label="建筑工地名称" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('建筑工地名称')">
            <template #default="scope">
              {{ tableFieldShow('建筑工地名称', scope.row.field) }}
            </template>
          </el-table-column>
<el-table-column label="建筑工地类型" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('建筑工地类型')">
            <template #default="scope">
              {{ handleTypeOptions(tableFieldShow('建筑工地类型', scope.row.field), optionsLisi.建筑工地类型Options) }}
            </template>
          </el-table-column>
<el-table-column label="地址" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('地址')">
            <template #default="scope">
              {{ tableFieldShow('地址', scope.row.field) }}
            </template>
          </el-table-column>
<el-table-column label="项目结束时间" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('项目结束时间')">
            <template #default="scope">
              {{ tableFieldShow('项目结束时间', scope.row.field) }}
            </template>
          </el-table-column>
<el-table-column label="项目开始时间" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('项目开始时间')">
            <template #default="scope">
              {{ tableFieldShow('项目开始时间', scope.row.field) }}
            </template>
          </el-table-column>
<el-table-column label="施工单位" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('施工单位')">
            <template #default="scope">
              {{ tableFieldShow('施工单位', scope.row.field) }}
            </template>
          </el-table-column>
<el-table-column label="施工单位责任人" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('施工单位责任人')">
            <template #default="scope">
              {{ tableFieldShow('施工单位责任人', scope.row.field) }}
            </template>
          </el-table-column>
<el-table-column label="施工单位责任人电话" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('施工单位责任人电话')">
            <template #default="scope">
              {{ tableFieldShow('施工单位责任人电话', scope.row.field) }}
            </template>
          </el-table-column>
<el-table-column label="设计建筑面积" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('设计建筑面积')">
            <template #default="scope">
              {{ tableFieldShow('设计建筑面积', scope.row.field) }} ㎡
            </template>
          </el-table-column>
<el-table-column label="地基挖方量" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('地基挖方量')">
            <template #default="scope">
              {{ tableFieldShow('地基挖方量', scope.row.field) }} m³
            </template>
          </el-table-column>
<el-table-column label="挖槽土运出量" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('挖槽土运出量')">
            <template #default="scope">
              {{ tableFieldShow('挖槽土运出量', scope.row.field) }} m³
            </template>
          </el-table-column>
<el-table-column label="施工建筑垃圾产生量" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('施工建筑垃圾产生量')">
            <template #default="scope">
              {{ tableFieldShow('施工建筑垃圾产生量', scope.row.field) }} t
            </template>
          </el-table-column>
<el-table-column label="施工可回收废物产生量" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('施工可回收废物产生量')">
            <template #default="scope">
              {{ tableFieldShow('施工可回收废物产生量', scope.row.field) }} t
            </template>
          </el-table-column>
<el-table-column label="拆除建筑物面积" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('拆除建筑物面积')">
            <template #default="scope">
              {{ tableFieldShow('拆除建筑物面积', scope.row.field) }} ㎡
            </template>
          </el-table-column>
<el-table-column label="拆除建筑垃圾产生量" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('拆除建筑垃圾产生量')">
            <template #default="scope">
              {{ tableFieldShow('拆除建筑垃圾产生量', scope.row.field) }} t
            </template>
          </el-table-column>
<el-table-column label="装修垃圾产生量" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('装修垃圾产生量')">
            <template #default="scope">
              {{ tableFieldShow('装修垃圾产生量', scope.row.field) }} t
            </template>
          </el-table-column>
<el-table-column label="拆除可回收废物量" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('拆除可回收废物量')">
            <template #default="scope">
              {{ tableFieldShow('拆除可回收废物量', scope.row.field) }} t
            </template>
          </el-table-column>
<el-table-column label="总覆盖面积" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('总覆盖面积')">
            <template #default="scope">
              {{ tableFieldShow('总覆盖面积', scope.row.field) }} ㎡
            </template>
          </el-table-column>
<el-table-column label="总挖掘体积" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('总挖掘体积')">
            <template #default="scope">
              {{ tableFieldShow('总挖掘体积', scope.row.field) }} m³
            </template>
          </el-table-column>
<el-table-column label="残留体积" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('残留体积')">
            <template #default="scope">
              {{ tableFieldShow('残留体积', scope.row.field) }} m³
            </template>
          </el-table-column>
<el-table-column label="准运证编号" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('准运证编号')">
            <template #default="scope">
              {{ tableFieldShow('准运证编号', scope.row.field) }}
            </template>
          </el-table-column>
<el-table-column label="核准有效期截止日期" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('核准有效期截止日期')">
            <template #default="scope">
              {{ tableFieldShow('核准有效期截止日期', scope.row.field) }}
            </template>
          </el-table-column>
<el-table-column label="核准有效期起始日期" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('核准有效期起始日期')">
            <template #default="scope">
              {{ tableFieldShow('核准有效期起始日期', scope.row.field) }}
            </template>
          </el-table-column>
<el-table-column label="称重设备代码" min-width="150" align="center" :show-overflow-tooltip="true" v-if="tableColumnShow('称重设备代码')">
            <template #default="scope">
              {{ tableFieldShow('称重设备代码', scope.row.field) }}
            </template>
          </el-table-column>

          <el-table-column width="300" fixed="right" label="操作" align="center">
            <template #default="scope">
              <el-button type="success" :icon="View" :plain="true" @click="queryClick(scope.row)" v-hasPermi="['smarturban:constructionsitedatamgmt:query']">详细</el-button>
              <el-button type="primary" :icon="EditPen" :plain="true" @click="editClick(scope.row)" v-hasPermi="['smarturban:constructionsitedatamgmt:update']">编辑</el-button>
              <el-button type="danger" :icon="Delete" :plain="true" @click="delClick(scope.row)" v-hasPermi="['smarturban:constructionsitedatamgmt:delete']">删除</el-button>
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
<li class="width50"><span>建筑工地编号：</span>{{ queryData.name }}</li>
 <li class="width50"><span>建筑工地名称：</span>{{ tableFieldShow('建筑工地名称', queryData.field) }}</li>
 <li class="width50"><span>建筑工地类型：</span>{{ handleTypeOptions(tableFieldShow('建筑工地类型', queryData.field), optionsLisi.建筑工地类型Options) }}</li>
 <li class="width50"><span>地址：</span>{{ tableFieldShow('地址', queryData.field) }}</li>
 <li class="width50"><span>项目结束时间：</span>{{ tableFieldShow('项目结束时间', queryData.field) }}</li>
 <li class="width50"><span>项目开始时间：</span>{{ tableFieldShow('项目开始时间', queryData.field) }}</li>
 <li class="width50"><span>施工单位：</span>{{ tableFieldShow('施工单位', queryData.field) }}</li>
 <li class="width50"><span>施工单位责任人：</span>{{ tableFieldShow('施工单位责任人', queryData.field) }}</li>
 <li class="width50"><span>施工单位责任人电话：</span>{{ tableFieldShow('施工单位责任人电话', queryData.field) }}</li>
 <li class="width50"><span>设计建筑面积：</span>{{ tableFieldShow('设计建筑面积', queryData.field) }}</li>
 <li class="width50"><span>地基挖方量：</span>{{ tableFieldShow('地基挖方量', queryData.field) }}</li>
 <li class="width50"><span>挖槽土运出量：</span>{{ tableFieldShow('挖槽土运出量', queryData.field) }}</li>
 <li class="width50"><span>施工建筑垃圾产生量：</span>{{ tableFieldShow('施工建筑垃圾产生量', queryData.field) }}</li>
 <li class="width50"><span>施工可回收废物产生量：</span>{{ tableFieldShow('施工可回收废物产生量', queryData.field) }}</li>
 <li class="width50"><span>拆除建筑物面积：</span>{{ tableFieldShow('拆除建筑物面积', queryData.field) }}</li>
 <li class="width50"><span>拆除建筑垃圾产生量：</span>{{ tableFieldShow('拆除建筑垃圾产生量', queryData.field) }}</li>
 <li class="width50"><span>装修垃圾产生量：</span>{{ tableFieldShow('装修垃圾产生量', queryData.field) }}</li>
 <li class="width50"><span>拆除可回收废物量：</span>{{ tableFieldShow('拆除可回收废物量', queryData.field) }}</li>
 <li class="width50"><span>总覆盖面积：</span>{{ tableFieldShow('总覆盖面积', queryData.field) }}</li>
 <li class="width50"><span>总挖掘体积：</span>{{ tableFieldShow('总挖掘体积', queryData.field) }}</li>
 <li class="width50"><span>残留体积：</span>{{ tableFieldShow('残留体积', queryData.field) }}</li>
 <li class="width50"><span>准运证编号：</span>{{ tableFieldShow('准运证编号', queryData.field) }}</li>
 <li class="width50"><span>核准有效期截止日期：</span>{{ tableFieldShow('核准有效期截止日期', queryData.field) }}</li>
 <li class="width50"><span>核准有效期起始日期：</span>{{ tableFieldShow('核准有效期起始日期', queryData.field) }}</li>
 <li class="width50"><span>称重设备代码：</span>{{ tableFieldShow('称重设备代码', queryData.field) }}</li>

            </ul>
          </div>

          <!--编辑 edit-content-form-li 里面添加 width100 width50 width33 就可以一列 二列 三列-->
          <div class="edit-content" v-loading="drawerConfig.loading" v-else>
            <el-form :model="formData" ref="drawerFromRef" class="edit-content-form" label-width="140px">
              <el-form-item label="行政区划代码：" prop="field.行政区划代码" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入行政区划代码', trigger: 'blur' }]">
                <el-input placeholder="请输入行政区划代码" v-model="formData.field.行政区划代码" clearable/>
              </el-form-item>
 <el-form-item label="建筑工地编号：" prop="name" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入建筑工地编号', trigger: 'blur' }]">
                <el-input placeholder="请输入建筑工地编号" v-model="formData.name" clearable/>
              </el-form-item>
<el-form-item label="建筑工地名称：" prop="field.建筑工地名称" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入建筑工地名称', trigger: 'blur' }]">
                <el-input placeholder="请输入建筑工地名称" v-model="formData.field.建筑工地名称" clearable/>
              </el-form-item>
<el-form-item label="建筑工地类型：" prop="field.建筑工地类型" class="edit-content-form-li width50" :rules="[{ required: true, message: '请选择建筑工地类型', trigger: 'blur' }]">
                   <el-select placeholder="请选择建筑工地类型" v-model="formData.field.建筑工地类型" filterable clearable>
                  <el-option v-for="item in optionsLisi.建筑工地类型Options" :key="item.value" :label="item.label" :value="item.value"/>
                </el-select>
              </el-form-item>
<el-form-item label="地址：" prop="field.地址" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入地址', trigger: 'blur' }]">
                <el-input placeholder="请输入地址" v-model="formData.field.地址" clearable/>
              </el-form-item>
<el-form-item label="项目结束时间：" prop="field.项目结束时间" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入项目结束时间', trigger: 'blur' }]">
                <el-input placeholder="请输入项目结束时间" v-model="formData.field.项目结束时间" clearable/>
              </el-form-item>
<el-form-item label="项目开始时间：" prop="field.项目开始时间" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入项目开始时间', trigger: 'blur' }]">
                <el-input placeholder="请输入项目开始时间" v-model="formData.field.项目开始时间" clearable/>
              </el-form-item>
<el-form-item label="施工单位：" prop="field.施工单位" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入施工单位', trigger: 'blur' }]">
                <el-input placeholder="请输入施工单位" v-model="formData.field.施工单位" clearable/>
              </el-form-item>
<el-form-item label="施工单位责任人：" prop="field.施工单位责任人" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入施工单位责任人', trigger: 'blur' }]">
                <el-input placeholder="请输入施工单位责任人" v-model="formData.field.施工单位责任人" clearable/>
              </el-form-item>
<el-form-item label="施工单位责任人电话：" prop="field.施工单位责任人电话" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入施工单位责任人电话', trigger: 'blur' }]">
                <el-input placeholder="请输入施工单位责任人电话" v-model="formData.field.施工单位责任人电话" clearable/>
              </el-form-item>
<el-form-item label="设计建筑面积：" prop="field.设计建筑面积" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入设计建筑面积', trigger: 'blur' }]">
                <el-input placeholder="请输入设计建筑面积" v-model="formData.field.设计建筑面积" clearable>
                  <template #append>㎡</template>
                </el-input>
              </el-form-item>
<el-form-item label="地基挖方量：" prop="field.地基挖方量" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入地基挖方量', trigger: 'blur' }]">
                <el-input placeholder="请输入地基挖方量" v-model="formData.field.地基挖方量" clearable>
                  <template #append>m³</template>
                </el-input>
              </el-form-item>
<el-form-item label="挖槽土运出量：" prop="field.挖槽土运出量" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入挖槽土运出量', trigger: 'blur' }]">
                <el-input placeholder="请输入挖槽土运出量" v-model="formData.field.挖槽土运出量" clearable>
                  <template #append>m³</template>
                </el-input>
              </el-form-item>
<el-form-item label="施工建筑垃圾产生量：" prop="field.施工建筑垃圾产生量" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入施工建筑垃圾产生量', trigger: 'blur' }]">
                <el-input placeholder="请输入施工建筑垃圾产生量" v-model="formData.field.施工建筑垃圾产生量" clearable>
                  <template #append>t</template>
                </el-input>
              </el-form-item>
<el-form-item label="施工可回收废物产生量：" prop="field.施工可回收废物产生量" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入施工可回收废物产生量', trigger: 'blur' }]">
                <el-input placeholder="请输入施工可回收废物产生量" v-model="formData.field.施工可回收废物产生量" clearable>
                  <template #append>t</template>
                </el-input>
              </el-form-item>
<el-form-item label="拆除建筑物面积：" prop="field.拆除建筑物面积" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入拆除建筑物面积', trigger: 'blur' }]">
                <el-input placeholder="请输入拆除建筑物面积" v-model="formData.field.拆除建筑物面积" clearable>
                  <template #append>㎡</template>
                </el-input>
              </el-form-item>
<el-form-item label="拆除建筑垃圾产生量：" prop="field.拆除建筑垃圾产生量" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入拆除建筑垃圾产生量', trigger: 'blur' }]">
                <el-input placeholder="请输入拆除建筑垃圾产生量" v-model="formData.field.拆除建筑垃圾产生量" clearable>
                  <template #append>t</template>
                </el-input>
              </el-form-item>
<el-form-item label="装修垃圾产生量：" prop="field.装修垃圾产生量" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入装修垃圾产生量', trigger: 'blur' }]">
                <el-input placeholder="请输入装修垃圾产生量" v-model="formData.field.装修垃圾产生量" clearable>
                  <template #append>t</template>
                </el-input>
              </el-form-item>
<el-form-item label="拆除可回收废物量：" prop="field.拆除可回收废物量" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入拆除可回收废物量', trigger: 'blur' }]">
                <el-input placeholder="请输入拆除可回收废物量" v-model="formData.field.拆除可回收废物量" clearable>
                  <template #append>t</template>
                </el-input>
              </el-form-item>
<el-form-item label="总覆盖面积：" prop="field.总覆盖面积" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入总覆盖面积', trigger: 'blur' }]">
                <el-input placeholder="请输入总覆盖面积" v-model="formData.field.总覆盖面积" clearable>
                  <template #append>㎡</template>
                </el-input>
              </el-form-item>
<el-form-item label="总挖掘体积：" prop="field.总挖掘体积" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入总挖掘体积', trigger: 'blur' }]">
                <el-input placeholder="请输入总挖掘体积" v-model="formData.field.总挖掘体积" clearable>
                  <template #append>m³</template>
                </el-input>
              </el-form-item>
<el-form-item label="残留体积：" prop="field.残留体积" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入残留体积', trigger: 'blur' }]">
                <el-input placeholder="请输入残留体积" v-model="formData.field.残留体积" clearable>
                  <template #append>m³</template>
                </el-input>
              </el-form-item>
<el-form-item label="准运证编号：" prop="field.准运证编号" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入准运证编号', trigger: 'blur' }]">
                <el-input placeholder="请输入准运证编号" v-model="formData.field.准运证编号" clearable/>
              </el-form-item>
<el-form-item label="核准有效期截止日期：" prop="field.核准有效期截止日期" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入核准有效期截止日期', trigger: 'blur' }]">
                <el-input placeholder="请输入核准有效期截止日期" v-model="formData.field.核准有效期截止日期" clearable/>
              </el-form-item>
<el-form-item label="核准有效期起始日期：" prop="field.核准有效期起始日期" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入核准有效期起始日期', trigger: 'blur' }]">
                <el-input placeholder="请输入核准有效期起始日期" v-model="formData.field.核准有效期起始日期" clearable/>
              </el-form-item>
<el-form-item label="称重设备代码：" prop="field.称重设备代码" class="edit-content-form-li width50" :rules="[{ required: true, message: '请输入称重设备代码', trigger: 'blur' }]">
                <el-input placeholder="请输入称重设备代码" v-model="formData.field.称重设备代码" clearable/>
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
                  assetProfileId: 'a9be4740-771a-11f0-bd39-2dc5ccdb39ef',//资产配置id
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
                type: '建筑工地数据管理',//资产配置，都是写死固定的资产配置
                field: {
                   行政区划代码: '',//行政区划代码
            建筑工地名称: '',//建筑工地名称
            建筑工地类型: '',//建筑工地类型
            地址: '',//地址
            项目结束时间: '',//项目结束时间
            项目开始时间: '',//项目开始时间
            施工单位: '',//施工单位
            施工单位责任人: '',//施工单位责任人
            施工单位责任人电话: '',//施工单位责任人电话
            设计建筑面积: '',//设计建筑面积
            地基挖方量: '',//地基挖方量
            挖槽土运出量: '',//挖槽土运出量
            施工建筑垃圾产生量: '',//施工建筑垃圾产生量
            施工可回收废物产生量: '',//施工可回收废物产生量
            拆除建筑物面积: '',//拆除建筑物面积
            拆除建筑垃圾产生量: '',//拆除建筑垃圾产生量
            装修垃圾产生量: '',//装修垃圾产生量
            拆除可回收废物量: '',//拆除可回收废物量
            总覆盖面积: '',//总覆盖面积
            总挖掘体积: '',//总挖掘体积
            残留体积: '',//残留体积
            准运证编号: '',//准运证编号
            核准有效期截止日期: '',//核准有效期截止日期
            核准有效期起始日期: '',//核准有效期起始日期
            称重设备代码: '',//称重设备代码

                }
              }

              // 所有选项集合
            const optionsLisi = ref({
              //建筑工地类型 选项
                                   建筑工地类型Options:[
                                {label:'建筑施工工地',value:'1'},{label:'建筑拆除工地',value:'2'},
                           ],

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
              drawerConfig.value.title = '建筑工地数据管理-详细信息';
            }
            // 点击编辑按钮
            const editClick = async (row) => {
              let copyRow = JSON.parse(JSON.stringify(row))
              formData.value = copyRow;
              formData.value.field = {};
              formData.value.field.行政区划代码 = tableFieldShow('行政区划代码', row.field);//行政区划代码
            formData.value.field.建筑工地名称 = tableFieldShow('建筑工地名称', row.field);//建筑工地名称
            formData.value.field.建筑工地类型 = tableFieldShow('建筑工地类型', row.field);//建筑工地类型
            formData.value.field.地址 = tableFieldShow('地址', row.field);//地址
            formData.value.field.项目结束时间 = tableFieldShow('项目结束时间', row.field);//项目结束时间
            formData.value.field.项目开始时间 = tableFieldShow('项目开始时间', row.field);//项目开始时间
            formData.value.field.施工单位 = tableFieldShow('施工单位', row.field);//施工单位
            formData.value.field.施工单位责任人 = tableFieldShow('施工单位责任人', row.field);//施工单位责任人
            formData.value.field.施工单位责任人电话 = tableFieldShow('施工单位责任人电话', row.field);//施工单位责任人电话
            formData.value.field.设计建筑面积 = tableFieldShow('设计建筑面积', row.field);//设计建筑面积
            formData.value.field.地基挖方量 = tableFieldShow('地基挖方量', row.field);//地基挖方量
            formData.value.field.挖槽土运出量 = tableFieldShow('挖槽土运出量', row.field);//挖槽土运出量
            formData.value.field.施工建筑垃圾产生量 = tableFieldShow('施工建筑垃圾产生量', row.field);//施工建筑垃圾产生量
            formData.value.field.施工可回收废物产生量 = tableFieldShow('施工可回收废物产生量', row.field);//施工可回收废物产生量
            formData.value.field.拆除建筑物面积 = tableFieldShow('拆除建筑物面积', row.field);//拆除建筑物面积
            formData.value.field.拆除建筑垃圾产生量 = tableFieldShow('拆除建筑垃圾产生量', row.field);//拆除建筑垃圾产生量
            formData.value.field.装修垃圾产生量 = tableFieldShow('装修垃圾产生量', row.field);//装修垃圾产生量
            formData.value.field.拆除可回收废物量 = tableFieldShow('拆除可回收废物量', row.field);//拆除可回收废物量
            formData.value.field.总覆盖面积 = tableFieldShow('总覆盖面积', row.field);//总覆盖面积
            formData.value.field.总挖掘体积 = tableFieldShow('总挖掘体积', row.field);//总挖掘体积
            formData.value.field.残留体积 = tableFieldShow('残留体积', row.field);//残留体积
            formData.value.field.准运证编号 = tableFieldShow('准运证编号', row.field);//准运证编号
            formData.value.field.核准有效期截止日期 = tableFieldShow('核准有效期截止日期', row.field);//核准有效期截止日期
            formData.value.field.核准有效期起始日期 = tableFieldShow('核准有效期起始日期', row.field);//核准有效期起始日期
            formData.value.field.称重设备代码 = tableFieldShow('称重设备代码', row.field);//称重设备代码

              drawerConfig.value.isDrawer = true;
              drawerConfig.value.loading = false;
              drawerConfig.value.type = 'edit';
              drawerConfig.value.title = '编辑建筑工地数据管理';
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
              drawerConfig.value.title = '新增建筑工地数据管理';
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
              name: '建筑工地数据管理',//标题名称
              addButName: '新增建筑工地数据管理',//新增按钮名称
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
            {label: '建筑工地编号', visible: true},
            {label: '建筑工地名称', visible: true},
            {label: '建筑工地类型', visible: true},
            {label: '地址', visible: true},
            {label: '项目结束时间', visible: true},
            {label: '项目开始时间', visible: true},
            {label: '施工单位', visible: true},
            {label: '施工单位责任人', visible: true},
            {label: '施工单位责任人电话', visible: true},
            {label: '设计建筑面积', visible: true},
            {label: '地基挖方量', visible: true},
            {label: '挖槽土运出量', visible: true},
            {label: '施工建筑垃圾产生量', visible: true},
            {label: '施工可回收废物产生量', visible: true},
            {label: '拆除建筑物面积', visible: true},
            {label: '拆除建筑垃圾产生量', visible: true},
            {label: '装修垃圾产生量', visible: true},
            {label: '拆除可回收废物量', visible: true},
            {label: '总覆盖面积', visible: true},
            {label: '总挖掘体积', visible: true},
            {label: '残留体积', visible: true},
            {label: '准运证编号', visible: true},
            {label: '核准有效期截止日期', visible: true},
            {label: '核准有效期起始日期', visible: true},
            {label: '称重设备代码', visible: true},

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
