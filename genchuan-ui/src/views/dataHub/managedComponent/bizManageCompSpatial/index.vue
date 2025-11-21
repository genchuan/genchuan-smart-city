<template>
  <div class="app-container">
    <el-card>
      <el-form :inline="true" :model="queryParams" class="filter-form">
        <el-form-item label="经度范围">
          <el-input v-model="queryParams.longitudeMin" placeholder="最小经度" style="width: 120px" />
          <span style="margin: 0 5px">-</span>
          <el-input v-model="queryParams.longitudeMax" placeholder="最大经度" style="width: 120px" />
        </el-form-item>
        <el-form-item label="纬度范围">
          <el-input v-model="queryParams.latitudeMin" placeholder="最小纬度" style="width: 120px" />
          <span style="margin: 0 5px">-</span>
          <el-input v-model="queryParams.latitudeMax" placeholder="最大纬度" style="width: 120px" />
        </el-form-item>
        <el-form-item label="高程范围">
          <el-input v-model="queryParams.elevationMin" placeholder="最低高程" style="width: 120px" />
          <span style="margin: 0 5px">-</span>
          <el-input v-model="queryParams.elevationMax" placeholder="最高高程" style="width: 120px" />
        </el-form-item>
        <el-form-item label="精度等级">
          <el-select v-model="queryParams.precision" placeholder="请选择精度" clearable style="width: 150px">
            <el-option label="高" value="高" />
            <el-option label="中" value="中" />
            <el-option label="低" value="低" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleQuery">查询</el-button>
          <el-button @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <el-card class="content-card">
      <div class="toolbar">
        <el-button type="primary" @click="handleAdd">新增空间数据</el-button>
        <!-- 导入按钮与上传组件 -->
        <el-upload
          class="upload-demo"
          action="#"
          :auto-upload="false"
          :on-change="handleFileChange"
          :show-file-list="false"
          accept=".csv"
        >
          <template #trigger>
            <el-button type="success">导入</el-button>
          </template>
        </el-upload>
        <el-button type="warning" @click="handleExport">导出</el-button>
        <el-button type="info" @click="showAnalysisDialog = true">空间分析</el-button>
      </div>

      <!-- 腾讯地图真实交互区域 -->
      <div class="map-container">
        <div id="tencentMap" style="width:100%; height:100%; border-radius:8px;"></div>
      </div>

      <el-table :data="filteredTableData" border style="margin-top: 15px" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" />
        <el-table-column prop="name" label="名称" />
        <el-table-column prop="longitude" label="经度" />
        <el-table-column prop="latitude" label="纬度" />
        <el-table-column prop="elevation" label="高程(m)" />
        <el-table-column prop="precision" label="精度等级" />
        <el-table-column label="操作" width="220">
          <template #default="scope">
            <el-button size="small" type="primary" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button size="small" type="danger" @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="pagination-container">
        <el-pagination
          v-model:current-page="pagination.currentPage"
          v-model:page-size="pagination.pageSize"
          :total="pagination.total"
          layout="total, prev, pager, next, jumper"
          @current-change="handlePageChange"
        />
      </div>
    </el-card>

    <!-- 弹窗表单 -->
    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="500px">
      <el-form :model="form" label-width="100px">
        <el-form-item label="名称">
          <el-input v-model="form.name" />
        </el-form-item>
        <el-form-item label="经度">
          <el-input v-model="form.longitude" />
        </el-form-item>
        <el-form-item label="纬度">
          <el-input v-model="form.latitude" />
        </el-form-item>
        <el-form-item label="高程(m)">
          <el-input v-model="form.elevation" />
        </el-form-item>
        <el-form-item label="精度等级">
          <el-select v-model="form.precision" placeholder="请选择精度">
            <el-option label="高" value="高" />
            <el-option label="中" value="中" />
            <el-option label="低" value="低" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSave">保存</el-button>
      </template>
    </el-dialog>

    <!-- 空间分析对话框 -->
    <el-dialog v-model="showAnalysisDialog" title="空间分析" width="600px">
      <el-form :model="analysisForm" label-width="120px">
        <el-form-item label="分析类型">
          <el-select v-model="analysisForm.type" placeholder="请选择分析类型" @change="handleAnalysisTypeChange">
            <el-option label="两点距离计算" value="distance" />
            <el-option label="中心点计算" value="center" />
            <el-option label="高程分析" value="elevation" />
            <el-option label="坡度分析" value="slope" />
          </el-select>
        </el-form-item>

        <!-- 高程分析选项 -->
        <el-form-item v-if="analysisForm.type === 'elevation'" label="分析内容">
          <el-select v-model="analysisForm.elevationType" placeholder="请选择分析内容">
            <el-option label="高程统计" value="stats" />
            <el-option label="最高/最低点" value="extremes" />
            <el-option label="等高线模拟" value="contour" />
          </el-select>
        </el-form-item>

        <!-- 坡度分析选项 -->
        <el-form-item v-if="analysisForm.type === 'slope'" label="分析内容">
          <el-select v-model="analysisForm.slopeType" placeholder="请选择分析内容">
            <el-option label="坡度统计" value="stats" />
            <el-option label="坡度分布" value="distribution" />
          </el-select>
        </el-form-item>

        <el-form-item v-if="analysisResult" label="分析结果">
          <el-input v-model="analysisResult" readonly type="textarea" :rows="6" />
        </el-form-item>

        <!-- 可视化图表 -->
        <div v-if="analysisChartVisible" class="analysis-chart">
          <div id="analysisChart" style="width: 100%; height: 300px;"></div>
        </div>
      </el-form>
      <template #footer>
        <el-button @click="showAnalysisDialog = false">关闭</el-button>
        <el-button type="primary" @click="performAnalysis">执行分析</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, computed } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import * as echarts from 'echarts'

// 查询参数
const queryParams = reactive({
  longitudeMin: '',
  longitudeMax: '',
  latitudeMin: '',
  latitudeMax: '',
  elevationMin: '',
  elevationMax: '',
  precision: ''
})

// 原始表格数据 - 添加高程字段
const tableData = ref([
  { id: 1, name: '部件A', longitude: 117.810114, latitude: 26.793227, elevation: 156.8, precision: '高' },
  { id: 2, name: '部件B', longitude: 117.817, latitude: 26.798, elevation: 243.2, precision: '中' },
  { id: 3, name: '部件C', longitude: 117.804, latitude: 26.787, elevation: 89.5, precision: '低' },
  { id: 4, name: '部件D', longitude: 117.823, latitude: 26.802, elevation: 312.7, precision: '高' },
  { id: 5, name: '部件E', longitude: 117.791, latitude: 26.775, elevation: 178.3, precision: '中' },
  { id: 6, name: '部件F', longitude: 117.815, latitude: 26.785, elevation: 124.6, precision: '高' }
])

// 过滤后的表格数据
const filteredTableData = computed(() => {
  return tableData.value.filter(item => {
    // 经度范围过滤
    if (queryParams.longitudeMin && Number(item.longitude) < Number(queryParams.longitudeMin)) {
      return false
    }
    if (queryParams.longitudeMax && Number(item.longitude) > Number(queryParams.longitudeMax)) {
      return false
    }

    // 纬度范围过滤
    if (queryParams.latitudeMin && Number(item.latitude) < Number(queryParams.latitudeMin)) {
      return false
    }
    if (queryParams.latitudeMax && Number(item.latitude) > Number(queryParams.latitudeMax)) {
      return false
    }

    // 高程范围过滤
    if (queryParams.elevationMin && Number(item.elevation) < Number(queryParams.elevationMin)) {
      return false
    }
    if (queryParams.elevationMax && Number(item.elevation) > Number(queryParams.elevationMax)) {
      return false
    }

    // 精度等级过滤
    if (queryParams.precision && item.precision !== queryParams.precision) {
      return false
    }

    return true
  })
})

// 分页
const pagination = reactive({
  currentPage: 1,
  pageSize: 10,
  total: computed(() => filteredTableData.value.length)
})

// 弹窗相关
const dialogVisible = ref(false)
const dialogTitle = ref('')
const form = reactive({
  id: null,
  name: '',
  longitude: '',
  latitude: '',
  elevation: '',
  precision: ''
})

// 空间分析相关
const showAnalysisDialog = ref(false)
const analysisForm = reactive({
  type: 'distance',
  elevationType: 'stats',
  slopeType: 'stats'
})
const analysisResult = ref('')
const selectedRows = ref([])
const analysisChartVisible = ref(false)
let analysisChart = null

// 交互函数
const handleQuery = () => {
  // 过滤数据已经通过computed实现，这里只需要更新地图标记
  updateMapMarkers()
  ElMessage.success('查询完成')
}

const resetQuery = () => {
  Object.assign(queryParams, {
    longitudeMin: '',
    longitudeMax: '',
    latitudeMin: '',
    latitudeMax: '',
    elevationMin: '',
    elevationMax: '',
    precision: ''
  })
  updateMapMarkers()
}

const handlePageChange = (page) => (pagination.currentPage = page)

const handleAdd = () => {
  Object.assign(form, {
    id: null,
    name: '',
    longitude: '',
    latitude: '',
    elevation: '',
    precision: ''
  })
  dialogTitle.value = '新增空间数据'
  dialogVisible.value = true
}

const handleEdit = (row) => {
  Object.assign(form, row)
  dialogTitle.value = '编辑空间数据'
  dialogVisible.value = true
}

const handleDelete = (row) => {
  ElMessageBox.confirm(`确定删除 ${row.name} 吗？`, '提示', { type: 'warning' }).then(() => {
    tableData.value = tableData.value.filter((r) => r.id !== row.id)
    updateMapMarkers()
    ElMessage.success('删除成功')
  })
}

const handleSave = () => {
  if (!form.name || !form.longitude || !form.latitude || !form.elevation || !form.precision) {
    ElMessage.error('请填写完整信息')
    return
  }

  if (isNaN(Number(form.longitude)) || isNaN(Number(form.latitude)) || isNaN(Number(form.elevation))) {
    ElMessage.error('经纬度和高程必须为数字')
    return
  }

  if (form.id) {
    const index = tableData.value.findIndex((r) => r.id === form.id)
    if (index !== -1) tableData.value[index] = { ...form }
  } else {
    form.id = Date.now()
    tableData.value.push({ ...form })
  }
  dialogVisible.value = false
  updateMapMarkers()
  ElMessage.success('保存成功')
}

// 导入功能实现
const handleFileChange = (file) => {
  const reader = new FileReader()
  reader.onload = (e) => {
    try {
      const content = e.target.result
      const lines = content.split('\n').filter(line => line.trim())

      // 简单验证CSV格式 (名称,经度,纬度,高程,精度)
      if (lines[0] !== '名称,经度,纬度,高程,精度') {
        ElMessage.error('CSV格式不正确，请使用"名称,经度,纬度,高程,精度"格式')
        return
      }

      // 解析数据
      const newData = []
      for (let i = 1; i < lines.length; i++) {
        const [name, longitude, latitude, elevation, precision] = lines[i].split(',')
        if (name && longitude && latitude && elevation && precision) {
          newData.push({
            id: Date.now() + i,
            name,
            longitude: Number(longitude),
            latitude: Number(latitude),
            elevation: Number(elevation),
            precision
          })
        }
      }

      if (newData.length > 0) {
        tableData.value.push(...newData)
        updateMapMarkers()
        ElMessage.success(`成功导入 ${newData.length} 条数据`)
      } else {
        ElMessage.warning('未找到有效数据')
      }
    } catch (error) {
      ElMessage.error('导入失败：' + error.message)
    }
  }
  reader.readAsText(file.raw)
}

// 导出功能实现
const handleExport = () => {
  if (filteredTableData.value.length === 0) {
    ElMessage.warning('没有数据可导出')
    return
  }

  // 构建CSV内容
  let csvContent = '名称,经度,纬度,高程,精度\n'
  filteredTableData.value.forEach(item => {
    csvContent += `${item.name},${item.longitude},${item.latitude},${item.elevation},${item.precision}\n`
  })

  // 创建下载链接
  const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' })
  const url = URL.createObjectURL(blob)
  const link = document.createElement('a')
  link.setAttribute('href', url)
  link.setAttribute('download', `空间数据_${new Date().toLocaleDateString()}.csv`)
  link.style.visibility = 'hidden'
  document.body.appendChild(link)
  link.click()
  document.body.removeChild(link)
}

// 空间分析功能实现
const performAnalysis = () => {
  // 重置图表
  analysisChartVisible.value = false
  if (analysisChart) {
    analysisChart.dispose()
    analysisChart = null
  }

  if (analysisForm.type === 'distance') {
    if (selectedRows.value.length !== 2) {
      ElMessage.warning('请选择两个点进行距离计算')
      return
    }

    // 计算两点之间的距离(简单球面距离公式)
    const point1 = selectedRows.value[0]
    const point2 = selectedRows.value[1]

    const R = 6371 // 地球半径(公里)
    const lat1 = point1.latitude * Math.PI / 180
    const lon1 = point1.longitude * Math.PI / 180
    const lat2 = point2.latitude * Math.PI / 180
    const lon2 = point2.longitude * Math.PI / 180

    const dLat = lat2 - lat1
    const dLon = lon2 - lon1

    const a = Math.sin(dLat/2) * Math.sin(dLat/2) +
      Math.cos(lat1) * Math.cos(lat2) *
      Math.sin(dLon/2) * Math.sin(dLon/2)
    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a))
    const distance = R * c // 公里

    // 计算高程差
    const elevationDiff = Math.abs(point1.elevation - point2.elevation)

    analysisResult.value = `${point1.name} 与 ${point2.name} 之间的直线距离约为 ${distance.toFixed(2)} 公里\n高程差: ${elevationDiff.toFixed(2)} 米\n坡度: ${(elevationDiff / (distance * 1000) * 100).toFixed(2)}%`
  }
  else if (analysisForm.type === 'center') {
    if (selectedRows.value.length < 2) {
      ElMessage.warning('请选择至少两个点进行中心点计算')
      return
    }

    // 计算中心点
    let sumLon = 0
    let sumLat = 0
    selectedRows.value.forEach(point => {
      sumLon += Number(point.longitude)
      sumLat += Number(point.latitude)
    })

    const centerLon = sumLon / selectedRows.value.length
    const centerLat = sumLat / selectedRows.value.length

    analysisResult.value = `选中 ${selectedRows.value.length} 个点的中心点坐标为：\n经度: ${centerLon.toFixed(6)}, 纬度: ${centerLat.toFixed(6)}`

    // 在地图上标记中心点
    if (centerMarker.value) {
      centerMarker.value.setMap(null)
    }
    centerMarker.value = new TMap.Marker({
      map: map.value,
      position: new TMap.LatLng(centerLat, centerLon),
      style: new TMap.MarkerStyle({
        width: 30,
        height: 30,
        src: 'https://mapapi.qq.com/web/lbs/javascriptGL/demo/img/center.png',
        anchor: { x: 15, y: 15 }
      })
    })
  }
  else if (analysisForm.type === 'elevation') {
    if (selectedRows.value.length === 0) {
      ElMessage.warning('请选择至少一个点进行高程分析')
      return
    }

    if (analysisForm.elevationType === 'stats') {
      // 高程统计分析
      const elevations = selectedRows.value.map(p => Number(p.elevation))
      const maxElevation = Math.max(...elevations)
      const minElevation = Math.min(...elevations)
      const avgElevation = elevations.reduce((sum, e) => sum + e, 0) / elevations.length

      analysisResult.value = `高程统计分析结果:\n
最高点: ${maxElevation.toFixed(2)} 米\n
最低点: ${minElevation.toFixed(2)} 米\n
平均高程: ${avgElevation.toFixed(2)} 米\n
高程极差: ${(maxElevation - minElevation).toFixed(2)} 米\n
数据点数量: ${elevations.length}`

      // 显示高程分布图表
      analysisChartVisible.value = true
      setTimeout(() => {
        initElevationChart(elevations)
      }, 100)
    }
    else if (analysisForm.elevationType === 'extremes') {
      // 最高/最低点分析
      const maxPoint = selectedRows.value.reduce((max, p) =>
        Number(p.elevation) > Number(max.elevation) ? p : max
      )
      const minPoint = selectedRows.value.reduce((min, p) =>
        Number(p.elevation) < Number(min.elevation) ? p : min
      )

      analysisResult.value = `高程极值点分析:\n
最高点: ${maxPoint.name} (${maxPoint.elevation}米)\n
最低点: ${minPoint.name} (${minPoint.elevation}米)\n
高程差: ${(maxPoint.elevation - minPoint.elevation).toFixed(2)} 米`

      // 在地图上高亮显示最高点和最低点
      updateExtremeMarkers(maxPoint, minPoint)
    }
    else if (analysisForm.elevationType === 'contour') {
      // 等高线模拟分析
      const elevations = selectedRows.value.map(p => Number(p.elevation))
      const elevationRanges = [
        { min: 0, max: 100, count: 0 },
        { min: 100, max: 200, count: 0 },
        { min: 200, max: 300, count: 0 },
        { min: 300, max: 400, count: 0 }
      ]

      elevations.forEach(e => {
        const range = elevationRanges.find(r => e >= r.min && e < r.max)
        if (range) range.count++
      })

      analysisResult.value = `等高线分布模拟:\n
0-100米: ${elevationRanges[0].count} 个点\n
100-200米: ${elevationRanges[1].count} 个点\n
200-300米: ${elevationRanges[2].count} 个点\n
300-400米: ${elevationRanges[3].count} 个点`

      // 显示等高线分布图表
      analysisChartVisible.value = true
      setTimeout(() => {
        initContourChart(elevationRanges)
      }, 100)
    }
  }
  else if (analysisForm.type === 'slope') {
    if (selectedRows.value.length < 2) {
      ElMessage.warning('请选择至少两个点进行坡度分析')
      return
    }

    if (analysisForm.slopeType === 'stats') {
      // 坡度统计分析
      const slopes = []
      for (let i = 0; i < selectedRows.value.length - 1; i++) {
        for (let j = i + 1; j < selectedRows.value.length; j++) {
          const p1 = selectedRows.value[i]
          const p2 = selectedRows.value[j]

          // 计算两点间的坡度
          const R = 6371 // 地球半径(公里)
          const lat1 = p1.latitude * Math.PI / 180
          const lon1 = p1.longitude * Math.PI / 180
          const lat2 = p2.latitude * Math.PI / 180
          const lon2 = p2.longitude * Math.PI / 180

          const dLat = lat2 - lat1
          const dLon = lon2 - lon1

          const a = Math.sin(dLat/2) * Math.sin(dLat/2) +
            Math.cos(lat1) * Math.cos(lat2) *
            Math.sin(dLon/2) * Math.sin(dLon/2)
          const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a))
          const distance = R * c * 1000 // 转换为米

          const elevationDiff = Math.abs(p1.elevation - p2.elevation)
          const slope = distance > 0 ? (elevationDiff / distance) * 100 : 0

          slopes.push(slope)
        }
      }

      const maxSlope = Math.max(...slopes)
      const minSlope = Math.min(...slopes)
      const avgSlope = slopes.reduce((sum, s) => sum + s, 0) / slopes.length

      analysisResult.value = `坡度统计分析结果:\n
最大坡度: ${maxSlope.toFixed(2)}%\n
最小坡度: ${minSlope.toFixed(2)}%\n
平均坡度: ${avgSlope.toFixed(2)}%\n
坡度计算次数: ${slopes.length}`

      // 显示坡度分布图表
      analysisChartVisible.value = true
      setTimeout(() => {
        initSlopeChart(slopes)
      }, 100)
    }
    else if (analysisForm.slopeType === 'distribution') {
      // 坡度分布分析
      const slopes = []
      for (let i = 0; i < selectedRows.value.length - 1; i++) {
        for (let j = i + 1; j < selectedRows.value.length; j++) {
          const p1 = selectedRows.value[i]
          const p2 = selectedRows.value[j]

          // 计算两点间的坡度
          const R = 6371 // 地球半径(公里)
          const lat1 = p1.latitude * Math.PI / 180
          const lon1 = p1.longitude * Math.PI / 180
          const lat2 = p2.latitude * Math.PI / 180
          const lon2 = p2.longitude * Math.PI / 180

          const dLat = lat2 - lat1
          const dLon = lon2 - lon1

          const a = Math.sin(dLat/2) * Math.sin(dLat/2) +
            Math.cos(lat1) * Math.cos(lat2) *
            Math.sin(dLon/2) * Math.sin(dLon/2)
          const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a))
          const distance = R * c * 1000 // 转换为米

          const elevationDiff = Math.abs(p1.elevation - p2.elevation)
          const slope = distance > 0 ? (elevationDiff / distance) * 100 : 0

          slopes.push(slope)
        }
      }

      // 坡度分级统计
      const slopeRanges = [
        { min: 0, max: 5, label: '平缓(0-5%)', count: 0 },
        { min: 5, max: 15, label: '缓坡(5-15%)', count: 0 },
        { min: 15, max: 30, label: '中坡(15-30%)', count: 0 },
        { min: 30, max: 100, label: '陡坡(>30%)', count: 0 }
      ]

      slopes.forEach(s => {
        const range = slopeRanges.find(r => s >= r.min && s < r.max)
        if (range) range.count++
      })

      analysisResult.value = `坡度分布分析:\n
平缓(0-5%): ${slopeRanges[0].count} 处\n
缓坡(5-15%): ${slopeRanges[1].count} 处\n
中坡(15-30%): ${slopeRanges[2].count} 处\n
陡坡(>30%): ${slopeRanges[3].count} 处`

      // 显示坡度分布图表
      analysisChartVisible.value = true
      setTimeout(() => {
        initSlopeDistributionChart(slopeRanges)
      }, 100)
    }
  }
}

// 高程分布图表初始化
const initElevationChart = (elevations) => {
  const chartDom = document.getElementById('analysisChart')
  if (!chartDom) return

  analysisChart = echarts.init(chartDom)

  const option = {
    title: {
      text: '高程分布直方图',
      left: 'center'
    },
    tooltip: {
      trigger: 'axis'
    },
    xAxis: {
      type: 'category',
      name: '高程区间(米)',
      data: ['<100', '100-200', '200-300', '300-400', '>400']
    },
    yAxis: {
      type: 'value',
      name: '数量'
    },
    series: [
      {
        name: '高程分布',
        type: 'bar',
        data: [
          elevations.filter(e => e < 100).length,
          elevations.filter(e => e >= 100 && e < 200).length,
          elevations.filter(e => e >= 200 && e < 300).length,
          elevations.filter(e => e >= 300 && e < 400).length,
          elevations.filter(e => e >= 400).length
        ],
        itemStyle: {
          color: '#5470c6'
        }
      }
    ]
  }

  analysisChart.setOption(option)
}

// 等高线分布图表初始化
const initContourChart = (elevationRanges) => {
  const chartDom = document.getElementById('analysisChart')
  if (!chartDom) return

  analysisChart = echarts.init(chartDom)

  const option = {
    title: {
      text: '等高线分布模拟',
      left: 'center'
    },
    tooltip: {
      trigger: 'item'
    },
    series: [
      {
        name: '高程分布',
        type: 'pie',
        radius: '60%',
        data: elevationRanges.map((range, index) => ({
          value: range.count,
          name: `${range.min}-${range.max}米`
        })),
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        }
      }
    ]
  }

  analysisChart.setOption(option)
}

// 坡度分布图表初始化
const initSlopeChart = (slopes) => {
  const chartDom = document.getElementById('analysisChart')
  if (!chartDom) return

  analysisChart = echarts.init(chartDom)

  const option = {
    title: {
      text: '坡度分布直方图',
      left: 'center'
    },
    tooltip: {
      trigger: 'axis'
    },
    xAxis: {
      type: 'category',
      name: '坡度区间(%)',
      data: ['<5', '5-10', '10-15', '15-20', '20-25', '25-30', '>30']
    },
    yAxis: {
      type: 'value',
      name: '数量'
    },
    series: [
      {
        name: '坡度分布',
        type: 'bar',
        data: [
          slopes.filter(s => s < 5).length,
          slopes.filter(s => s >= 5 && s < 10).length,
          slopes.filter(s => s >= 10 && s < 15).length,
          slopes.filter(s => s >= 15 && s < 20).length,
          slopes.filter(s => s >= 20 && s < 25).length,
          slopes.filter(s => s >= 25 && s < 30).length,
          slopes.filter(s => s >= 30).length
        ],
        itemStyle: {
          color: '#91cc75'
        }
      }
    ]
  }

  analysisChart.setOption(option)
}

// 坡度分布图表初始化
const initSlopeDistributionChart = (slopeRanges) => {
  const chartDom = document.getElementById('analysisChart')
  if (!chartDom) return

  analysisChart = echarts.init(chartDom)

  const option = {
    title: {
      text: '坡度等级分布',
      left: 'center'
    },
    tooltip: {
      trigger: 'item'
    },
    series: [
      {
        name: '坡度分布',
        type: 'pie',
        radius: '60%',
        data: slopeRanges.map(range => ({
          value: range.count,
          name: range.label
        })),
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        }
      }
    ]
  }

  analysisChart.setOption(option)
}

// 高亮显示最高点和最低点
const updateExtremeMarkers = (maxPoint, minPoint) => {
  // 清除之前的极值点标记
  if (maxMarker.value) {
    maxMarker.value.setMap(null)
  }
  if (minMarker.value) {
    minMarker.value.setMap(null)
  }

  // 添加最高点标记
  maxMarker.value = new TMap.Marker({
    map: map.value,
    position: new TMap.LatLng(maxPoint.latitude, maxPoint.longitude),
    style: new TMap.MarkerStyle({
      width: 30,
      height: 30,
      src: 'https://mapapi.qq.com/web/lbs/javascriptGL/demo/img/marker.png',
      anchor: { x: 15, y: 15 }
    })
  })

  // 添加最低点标记
  minMarker.value = new TMap.Marker({
    map: map.value,
    position: new TMap.LatLng(minPoint.latitude, minPoint.longitude),
    style: new TMap.MarkerStyle({
      width: 30,
      height: 30,
      src: 'https://mapapi.qq.com/web/lbs/javascriptGL/demo/img/marker.png',
      anchor: { x: 15, y: 15 }
    })
  })
}

const handleAnalysisTypeChange = () => {
  // 重置分析结果和图表
  analysisResult.value = ''
  analysisChartVisible.value = false
  if (analysisChart) {
    analysisChart.dispose()
    analysisChart = null
  }
}

const handleSelectionChange = (rows) => (selectedRows.value = rows)


// 腾讯地图逻辑
const map = ref(null)
const markerLayer = ref(null)
const centerMarker = ref(null) // 用于显示分析的中心点
const maxMarker = ref(null) // 用于显示最高点
const minMarker = ref(null) // 用于显示最低点

const loadTencentMap = () => {
  return new Promise((resolve) => {
    if (window.TMap) return resolve()
    const script = document.createElement('script')
    script.src =
      'https://map.qq.com/api/gljs?v=1.exp&key=OHCBZ-7BPC3-J7E3H-OA62K-Y3ZFZ-JQBPD&callback=initTencentMap'
    script.async = true
    window.initTencentMap = () => resolve()
    document.head.appendChild(script)
  })
}

const initMap = () => {
  map.value = new TMap.Map(document.getElementById('tencentMap'), {
    center: new TMap.LatLng(26.793227, 117.810114),
    zoom: 12
  })
  updateMapMarkers()

  // 点击地图拾取经纬度
  map.value.on('click', (evt) => {
    const lat = evt.latLng.getLat()
    const lng = evt.latLng.getLng()
    form.latitude = lat.toFixed(6)
    form.longitude = lng.toFixed(6)
    // 模拟高程数据 - 在实际应用中，这里可以调用高程API
    form.elevation = (Math.random() * 300 + 50).toFixed(1)
    ElMessage.info(`已拾取坐标：${lat.toFixed(6)}, ${lng.toFixed(6)}，模拟高程: ${form.elevation}米`)
  })
}

const updateMapMarkers = () => {
  if (!window.TMap || !map.value) return

  // 清除之前的中心点标记
  if (centerMarker.value) {
    centerMarker.value.setMap(null)
    centerMarker.value = null
  }

  // 清除极值点标记
  if (maxMarker.value) {
    maxMarker.value.setMap(null)
    maxMarker.value = null
  }
  if (minMarker.value) {
    minMarker.value.setMap(null)
    minMarker.value = null
  }

  const geometries = filteredTableData.value.map((item) => ({
    id: item.id,
    styleId: 'marker',
    position: new TMap.LatLng(item.latitude, item.longitude),
    properties: {
      title: item.name,
      elevation: item.elevation
    }
  }))

  if (markerLayer.value) {
    markerLayer.value.setGeometries(geometries)
  } else {
    markerLayer.value = new TMap.MultiMarker({
      map: map.value,
      styles: {
        marker: new TMap.MarkerStyle({
          width: 25,
          height: 35,
          src: 'https://mapapi.qq.com/web/lbs/javascriptGL/demo/img/markerDefault.png',
          anchor: { x: 16, y: 32 }
        })
      },
      geometries
    })

    // 添加点击事件显示高程信息
    markerLayer.value.on('click', (evt) => {
      const item = filteredTableData.value.find(d => d.id === evt.geometry.id)
      if (item) {
        ElMessage.info(`${item.name}: 经度${item.longitude}, 纬度${item.latitude}, 高程${item.elevation}米`)
      }
    })
  }
}

onMounted(async () => {
  await loadTencentMap()
  initMap()
})
</script>

<style scoped>
.filter-form {
  margin-bottom: 10px;
}

.content-card {
  margin-top: 10px;
}

/* 优化工具栏按钮排版 */
.toolbar {
  margin-bottom: 15px;
  display: flex; /* 使用Flex布局 */
  gap: 10px; /* 按钮之间的间距 */
  align-items: center; /* 垂直居中对齐 */
  padding: 8px 0; /* 增加上下内边距，让区域更饱满 */
}

/* 为按钮增加统一的小尺寸，避免过大 */
.toolbar .el-button {
  padding: 6px 15px; /* 调整按钮内边距 */
  font-size: 14px; /* 统一字体大小 */
}

.map-container {
  width: 100%;
  height: 400px;
  margin-top: 10px;
  border: 1px solid #ddd;
  border-radius: 8px;
  overflow: hidden;
}

.pagination-container {
  margin-top: 15px;
  text-align: right;
}

.analysis-chart {
  margin-top: 20px;
  border-top: 1px solid #e4e7ed;
  padding-top: 15px;
}
</style>
