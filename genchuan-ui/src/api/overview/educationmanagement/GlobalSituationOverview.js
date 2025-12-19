// GlobalSituationOverview.js
// 引入axios（假设项目中已配置axios）
import axios from 'axios';

// ==================== 基础配置 ====================
const BASE_URL = '/api/educationmanagement';

// ==================== 接口路径配置 ====================
const API = {
  // 1.1.1 教育全域数据概览
  GLOBAL_OVERVIEW: `${BASE_URL}/globalOverview`,
  // 1.1.2 教育核心指标
  CORE_INDICATORS: `${BASE_URL}/coreIndicators`,
  // 1.1.3 教育力量分布
  FORCE_DISTRIBUTION: `${BASE_URL}/forceDistribution`,
  // 1.1.4 教育服务总览
  SERVICE_OVERVIEW: `${BASE_URL}/serviceOverview`,
  // 实时位置数据
  REAL_TIME_LOCATIONS: `${BASE_URL}/realTimeLocations`,
  // 区域热力图数据
  REGION_HEATMAP: `${BASE_URL}/regionHeatmap`,
  // 服务筛选
  SERVICE_FILTER: `${BASE_URL}/services/filter`,
  // 服务详情
  SERVICE_DETAIL: `${BASE_URL}/service`,
  // 查看报告
  REPORT: `${BASE_URL}/report`,
  // 导出数据
  EXPORT: `${BASE_URL}/export`,
  // 更新指标异常原因
  UPDATE_INDICATOR_REASON: `${BASE_URL}/updateIndicatorReason`,
  // 更新指标阈值
  UPDATE_INDICATOR_THRESHOLD: `${BASE_URL}/updateIndicatorThreshold`,
  // 处理预警
  HANDLE_WARNING: `${BASE_URL}/handleWarning`,
  // 提交服务事项处理结果
  SUBMIT_SERVICE_HANDLE: `${BASE_URL}/submitServiceHandle`
};

// ==================== 数据接口 ====================

// 1.1.1 教育全域数据概览相关接口
export const fetchEduGlobalOverview = async (params = {}) => {
  try {
    const response = await axios.get(API.GLOBAL_OVERVIEW, { params });
    if (response.data && typeof response.data === 'object') {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('教育全域数据概览接口调用失败，使用模拟数据:', error.message);
    return getMockGlobalOverview(params);
  }
};

// 1.1.2 教育核心指标相关接口
export const fetchEduCoreIndicators = async (params = {}) => {
  try {
    const response = await axios.get(API.CORE_INDICATORS, { params });
    if (response.data && Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('教育核心指标接口调用失败，使用模拟数据:', error.message);
    return getMockCoreIndicators(params);
  }
};

// 1.1.3 教育力量分布视图相关接口
export const fetchEduForceDistribution = async (params = {}) => {
  try {
    const response = await axios.get(API.FORCE_DISTRIBUTION, { params });
    if (response.data && typeof response.data === 'object') {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('教育力量分布接口调用失败，使用模拟数据:', error.message);
    return getMockForceDistribution(params);
  }
};

// 1.1.4 教育服务总览相关接口
export const fetchServiceOverview = async (params = {}) => {
  try {
    const response = await axios.get(API.SERVICE_OVERVIEW, { params });
    if (response.data && typeof response.data === 'object') {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('教育服务总览接口调用失败，使用模拟数据:', error.message);
    return getMockServiceOverview(params);
  }
};

// 新增方法：获取实时位置数据
export const fetchRealTimeLocations = async () => {
  try {
    const response = await axios.get(API.REAL_TIME_LOCATIONS);
    return response.data;
  } catch (error) {
    console.warn('获取实时位置数据失败，使用模拟数据:', error.message);
    return {
      teacherLocations: [],
      schoolbusLocations: []
    };
  }
};

// 新增方法：获取区域热力图数据
export const fetchRegionHeatmapData = async (regionCode) => {
  try {
    const response = await axios.get(`${API.REGION_HEATMAP}/${regionCode}`);
    return response.data;
  } catch (error) {
    console.warn('获取区域热力图数据失败，使用模拟数据:', error.message);
    return {
      regionCode: regionCode,
      heatmapData: []
    };
  }
};

// 服务筛选接口
export const filterServices = async (filters) => {
  try {
    const response = await axios.post(API.SERVICE_FILTER, filters);
    return response.data;
  } catch (error) {
    console.warn('服务筛选接口调用失败，使用模拟数据:', error.message);
    return getMockFilteredServices(filters);
  }
};

// 获取服务详情
export const fetchServiceDetail = async (serviceId) => {
  try {
    const response = await axios.get(`${API.SERVICE_DETAIL}/${serviceId}`);

    // 检查返回的是否是HTML
    if (typeof response.data === 'string' && response.data.includes('<!DOCTYPE html>')) {
      throw new Error('接口返回HTML页面，使用模拟数据');
    }

    // 检查返回数据格式
    if (response.data && typeof response.data === 'object' && response.data.serviceId) {
      return response.data;
    }

    throw new Error('接口返回数据格式不正确，使用模拟数据');
  } catch (error) {
    console.warn('获取服务详情失败，使用模拟数据:', error.message);
    return getMockServiceDetail(serviceId);
  }
};

// 查看报告
export const viewReport = async (reportId) => {
  try {
    const response = await axios.get(`${API.REPORT}/${reportId}`);
    return response.data;
  } catch (error) {
    console.warn('查看报告失败，使用模拟数据:', error.message);
    return getMockReport(reportId);
  }
};

// 导出数据
export const exportData = async (dataType, params) => {
  try {
    console.log(`导出${dataType}数据，参数:`, params);

    // 实际项目中调用后端导出接口
    const response = await axios({
      method: 'post',
      url: `${API.EXPORT}/${dataType}`,
      data: params,
      responseType: 'blob' // 重要：接收二进制数据
    });

    // 创建下载链接
    const blob = new Blob([response.data], {
      type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
    });
    const url = window.URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;

    // 生成文件名
    const timestamp = new Date().toISOString().split('T')[0];
    const fileName = `教育${getExportFileName(dataType)}_${timestamp}.xlsx`;
    link.download = fileName;

    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
    window.URL.revokeObjectURL(url);

    return { success: true, message: '导出成功', fileName };
  } catch (error) {
    console.error('导出失败:', error);
    // 如果后端导出失败，使用前端导出作为兜底方案
    return await exportWithFrontend(dataType, params);
  }
};

// 更新指标异常原因
export const updateIndicatorReason = async (params) => {
  try {
    const response = await axios.post(API.UPDATE_INDICATOR_REASON, params);
    return response.data;
  } catch (error) {
    console.warn('更新指标异常原因失败，使用模拟数据:', error.message);
    return { success: true };
  }
};

// 更新指标阈值
export const updateIndicatorThreshold = async (params) => {
  try {
    const response = await axios.post(API.UPDATE_INDICATOR_THRESHOLD, params);
    return response.data;
  } catch (error) {
    console.warn('更新指标阈值失败，使用模拟数据:', error.message);
    return { success: true };
  }
};

// 处理预警
export const handleWarning = async (params) => {
  try {
    const response = await axios.post(API.HANDLE_WARNING, params);
    return response.data;
  } catch (error) {
    console.warn('处理预警失败，使用模拟数据:', error.message);
    return { success: true };
  }
};

// 提交服务事项处理结果
export const submitServiceHandle = async (params) => {
  try {
    const response = await axios.post(API.SUBMIT_SERVICE_HANDLE, params);
    return response.data;
  } catch (error) {
    console.warn('提交服务事项处理结果失败，使用模拟数据:', error.message);
    return { success: true };
  }
};

// ==================== 模拟数据函数 ====================

// 模拟全局概览数据
const getMockGlobalOverview = (params = {}) => {
  const { timeRange = 'today', area = [] } = params;

  let baseStats = {
    totalSchoolCount: 1256,
    totalStudentCount: 85600,
    totalTeacherCount: 5600,
    resourceAllocationRate: 85,
    serviceCompleteRate: 92
  };

  // 根据时间范围调整数据
  if (timeRange === 'week') {
    baseStats = {
      totalSchoolCount: 1256,
      totalStudentCount: 85600,
      totalTeacherCount: 5600,
      resourceAllocationRate: 86,
      serviceCompleteRate: 93
    };
  } else if (timeRange === 'month') {
    baseStats = {
      totalSchoolCount: 1256,
      totalStudentCount: 85600,
      totalTeacherCount: 5600,
      resourceAllocationRate: 87,
      serviceCompleteRate: 94
    };
  }

  // 根据行政区划调整数据
  if (area.length > 0) {
    const areaCode = area[area.length - 1];
    if (areaCode === 'gulou') {
      baseStats = {
        totalSchoolCount: 45,
        totalStudentCount: 3200,
        totalTeacherCount: 280,
        resourceAllocationRate: 82,
        serviceCompleteRate: 88
      };
    }
  }

  return {
    stats: [
      {
        id: 1,
        title: '学校总数',
        value: baseStats.totalSchoolCount,
        unit: '所',
        rate: 2,
        rateText: '较上年 +2%',
        warning: false,
        hasPulse: true,
        calculation: '学校总数 = 幼儿园 + 小学 + 中学数量总和'
      },
      {
        id: 2,
        title: '在园在校学生数',
        value: baseStats.totalStudentCount,
        unit: '人',
        rate: 3,
        rateText: '较上年 +3%',
        warning: false,
        hasPulse: true,
        calculation: '学生总数 = 在园幼儿数 + 在校学生数'
      },
      {
        id: 3,
        title: '专任教师数',
        value: baseStats.totalTeacherCount,
        unit: '人',
        rate: 4,
        rateText: '较上年 +4%',
        warning: false,
        hasPulse: false,
        calculation: '专任教师数 = 在编专任教师数量'
      },
      {
        id: 4,
        title: '教育资源配置率',
        value: baseStats.resourceAllocationRate,
        unit: '%',
        rate: 2,
        rateText: '较上年 +2%',
        warning: baseStats.resourceAllocationRate < 85,
        hasPulse: true,
        calculation: '资源配置率 = 已配置资源学校数 / 总学校数 × 100%'
      },
      {
        id: 5,
        title: '教育服务办结率',
        value: baseStats.serviceCompleteRate,
        unit: '%',
        rate: 3,
        rateText: '较上年 +3%',
        warning: baseStats.serviceCompleteRate < 90,
        hasPulse: true,
        calculation: '服务办结率 = 已办结服务工单数 / 总服务工单数 × 100%'
      }
    ],
    // 区域学校分布
    regionSchoolDistribution: [
      { regionName: '高新区', schoolCount: 45 },
      { regionName: '经开区', schoolCount: 38 },
      { regionName: '城东区', schoolCount: 28 },
      { regionName: '城西区', schoolCount: 25 },
      { regionName: '城南区', schoolCount: 12 },
      { regionName: '城北区', schoolCount: 8 }
    ],
    // 学校类型分布
    schoolTypeDistribution: [
      { typeName: '幼儿园', count: 320 },
      { typeName: '小学', count: 280 },
      { typeName: '初中', count: 210 },
      { typeName: '高中', count: 180 },
      { typeName: '其他', count: 116 }
    ]
  };
};

// 模拟核心指标数据
const getMockCoreIndicators = (params = {}) => {
  const { eduDomain = '', timeRange = '7days' } = params;

  // 基础指标数据
  let baseIndicators = [
    {
      indicatorId: '1',
      indicatorName: '学前三年毛入园率',
      realValue: '95.2',
      unit: '%',
      complianceRate: 95,
      yearOnYear: 2.5,
      thresholdMin: 90,
      thresholdMax: 100,
      warnStatus: '0',
      updateTime: '2025-11-03 15:30',
      ext1: '教育统计报表',
      trend7days: JSON.stringify({
        xAxis: ['10-28', '10-29', '10-30', '10-31', '11-01', '11-02', '11-03'],
        series: [94.8, 95.0, 95.1, 95.2, 95.2, 95.1, 95.2]
      }),
      calculation: '毛入园率 = 在园幼儿数 / 适龄幼儿总数 × 100%'
    },
    {
      indicatorId: '2',
      indicatorName: '九年义务教育巩固率',
      realValue: '98.5',
      unit: '%',
      complianceRate: 98,
      yearOnYear: 1.2,
      thresholdMin: 95,
      thresholdMax: 100,
      warnStatus: '0',
      updateTime: '2025-11-03 15:30',
      ext1: '教育统计报表',
      trend7days: JSON.stringify({
        xAxis: ['10-28', '10-29', '10-30', '10-31', '11-01', '11-02', '11-03'],
        series: [98.2, 98.3, 98.4, 98.5, 98.5, 98.4, 98.5]
      }),
      calculation: '巩固率 = 毕业学生数 / 入学学生数 × 100%'
    },
    {
      indicatorId: '3',
      indicatorName: '师生比',
      realValue: '15.3',
      unit: '',
      complianceRate: 92,
      yearOnYear: -0.5,
      thresholdMin: 12,
      thresholdMax: 18,
      warnStatus: '0',
      updateTime: '2025-11-03 15:30',
      ext1: '师生信息表',
      trend7days: JSON.stringify({
        xAxis: ['10-28', '10-29', '10-30', '10-31', '11-01', '11-02', '11-03'],
        series: [15.8, 15.6, 15.5, 15.4, 15.3, 15.4, 15.3]
      }),
      calculation: '师生比 = 学生总数 / 专任教师数'
    },
    {
      indicatorId: '4',
      indicatorName: '教师本科以上学历比',
      realValue: '88.6',
      unit: '%',
      complianceRate: 88,
      yearOnYear: 3.2,
      thresholdMin: 85,
      thresholdMax: 100,
      warnStatus: '0',
      updateTime: '2025-11-03 15:30',
      ext1: '师资信息表',
      trend7days: JSON.stringify({
        xAxis: ['10-28', '10-29', '10-30', '10-31', '11-01', '11-02', '11-03'],
        series: [87.8, 88.0, 88.2, 88.4, 88.5, 88.6, 88.6]
      }),
      calculation: '本科以上占比 = 本科以上学历教师数 / 专任教师总数 × 100%'
    },
    {
      indicatorId: '5',
      indicatorName: '辍学率',
      realValue: '0.45',
      unit: '%',
      complianceRate: 95,
      yearOnYear: -0.1,
      thresholdMin: 0,
      thresholdMax: 0.5,
      warnStatus: '1',
      updateTime: '2025-11-03 15:30',
      ext1: '学籍管理表',
      trend7days: JSON.stringify({
        xAxis: ['10-28', '10-29', '10-30', '10-31', '11-01', '11-02', '11-03'],
        series: [0.48, 0.47, 0.46, 0.45, 0.45, 0.46, 0.45]
      }),
      calculation: '辍学率 = 辍学学生数 / 在校学生总数 × 100%'
    }
  ];

  // 根据教育领域筛选
  if (eduDomain) {
    baseIndicators = baseIndicators.filter(indicator => {
      if (eduDomain === '入学类') {
        return indicator.indicatorName.includes('入园率') || indicator.indicatorName.includes('巩固率');
      } else if (eduDomain === '师资类') {
        return indicator.indicatorName.includes('师生比') || indicator.indicatorName.includes('学历比');
      } else if (eduDomain === '学业类') {
        return indicator.indicatorName.includes('辍学率');
      }
      return true;
    });
  }

  return baseIndicators;
};

// 模拟力量分布数据
const getMockForceDistribution = (params = {}) => {
  const { forceType = '', eduTeam = '' } = params;

  // 基础力量数据
  let baseGeometries = [
    {x:26.855227, y:117.650114, dataType: 'teacher', status: 'online', name: '张老师', team: '语文组', school: '第一中学'},
    {x:26.783227, y:117.720114, dataType: 'schoolbus', status: 'moving', name: '校车001', type: '校车', route: '1号线'},
    {x:26.733227, y:117.650114, dataType: 'school', status: 'normal', name: '第一中学', type: '高中'},
    {x:26.823227, y:117.8220114, dataType: 'teacher', status: 'online', name: '李老师', team: '数学组', school: '实验小学'},
    {x:26.845227, y:117.680114, dataType: 'schoolbus', status: 'parked', name: '校车002', type: '校车', route: '2号线'},
    {x:26.815227, y:117.750114, dataType: 'teacher', status: 'busy', name: '王老师', team: '英语组', school: '第三中学'},
    {x:26.795227, y:117.710114, dataType: 'school', status: 'normal', name: '实验小学', type: '小学'},
  ];

  // 根据力量类型筛选
  if (forceType) {
    baseGeometries = baseGeometries.filter(item => item.dataType === forceType);
  }

  // 根据教研组筛选
  if (eduTeam) {
    baseGeometries = baseGeometries.filter(item => {
      if (eduTeam === '语文组') return item.team === '语文组';
      if (eduTeam === '数学组') return item.team === '数学组';
      if (eduTeam === '英语组') return item.team === '英语组';
      return true;
    });
  }

  // 统计数据
  const stats = {
    totalTeacherCount: 5600,
    totalSchoolbusCount: 120,
    totalSchoolCount: 1256,
    onlineTeacherCount: 5200,
    activeSchoolbusCount: 98
  };

  // 区域力量分布
  const regionForceDistribution = [
    { regionName: '高新区', teacherCount: 850, schoolbusCount: 25, schoolCount: 45 },
    { regionName: '经开区', teacherCount: 720, schoolbusCount: 22, schoolCount: 38 },
    { regionName: '城东区', teacherCount: 680, schoolbusCount: 20, schoolCount: 28 },
    { regionName: '城西区', teacherCount: 550, schoolbusCount: 18, schoolCount: 25 },
    { regionName: '城南区', teacherCount: 480, schoolbusCount: 15, schoolCount: 12 },
    { regionName: '城北区', teacherCount: 320, schoolbusCount: 10, schoolCount: 8 }
  ];

  return {
    geometries: baseGeometries,
    stats: stats,
    regionForceDistribution: regionForceDistribution
  };
};

// 模拟服务总览数据
const getMockServiceOverview = (params = {}) => {
  const { serviceType = '', handleDept = '', timeRange = 'today' } = params;

  // 基础服务数据
  let baseServiceData = {
    // 服务总量统计
    totalServiceCount: 1256,
    newServiceToday: 23,
    serviceCompleteRate: 92,
    overdueServiceCount: 15,

    // 服务类型分布
    typeDistribution: [
      { serviceType: '教学服务', serviceCount: 456, color: '#3B82F6' },
      { serviceType: '后勤服务', serviceCount: 342, color: '#10B981' },
      { serviceType: '安全服务', serviceCount: 240, color: '#F59E0B' },
      { serviceType: '信息化服务', serviceCount: 150, color: '#EF4444' },
      { serviceType: '其他', serviceCount: 68, color: '#8B5CF6' }
    ],

    // 服务来源分布
    sourceDistribution: [
      { serviceSource: '学校上报', serviceCount: 623, color: '#3B82F6' },
      { serviceSource: '家长反馈', serviceCount: 432, color: '#10B981' },
      { serviceSource: '上级交办', serviceCount: 201, color: '#F59E0B' }
    ],
    // 区域服务分布 - 新增字段
    regionDistribution: [
      { regionName: '高新区', serviceCount: 320 },
      { regionName: '经开区', serviceCount: 280 },
      { regionName: '城东区', serviceCount: 210 },
      { regionName: '城西区', serviceCount: 180 },
      { regionName: '城南区', serviceCount: 150 },
      { regionName: '城北区', serviceCount: 116 }
    ],
    // 服务办理进度
    progressDistribution: [
      { progressStage: '受理', serviceCount: 156, color: '#3B82F6' },
      { progressStage: '处理', serviceCount: 234, color: '#60A5FA' },
      { progressStage: '审核', serviceCount: 345, color: '#F59E0B' },
      { progressStage: '办结', serviceCount: 521, color: '#10B981' }
    ],

    // 超期服务列表
    overdueServices: [
      {
        serviceId: 'S202411001',
        serviceType: '教学服务',
        serviceTitle: '教学设备维修申请',
        applyTime: '2025-10-25 09:15',
        dueTime: '2025-10-30 17:00',
        overdueDays: 5,
        handleDept: '教学设备科',
        currentProgress: '处理'
      },
      {
        serviceId: 'S202411023',
        serviceType: '后勤服务',
        serviceTitle: '食堂卫生整改',
        applyTime: '2025-10-28 14:30',
        dueTime: '2025-11-02 17:00',
        overdueDays: 3,
        handleDept: '后勤保障科',
        currentProgress: '受理'
      },
      {
        serviceId: 'S202411045',
        serviceType: '安全服务',
        serviceTitle: '校园安全隐患排查',
        applyTime: '2025-10-29 10:20',
        dueTime: '2025-11-03 17:00',
        overdueDays: 2,
        handleDept: '安全管理科',
        currentProgress: '处理'
      }
    ],

    // 近期服务趋势
    recentServiceTrend: [
      { date: '10-28', newServices: 18, completeServices: 15 },
      { date: '10-29', newServices: 22, completeServices: 18 },
      { date: '10-30', newServices: 15, completeServices: 20 },
      { date: '10-31', newServices: 25, completeServices: 22 },
      { date: '11-01', newServices: 20, completeServices: 19 },
      { date: '11-02', newServices: 24, completeServices: 21 },
      { date: '11-03', newServices: 23, completeServices: 20 }
    ]
  };

  // 根据服务类型筛选
  if (serviceType) {
    const typeMap = {
      '教学类': '教学服务',
      '后勤类': '后勤服务',
      '安全类': '安全服务'
    };

    const targetType = typeMap[serviceType];
    if (targetType) {
      baseServiceData.typeDistribution = baseServiceData.typeDistribution.filter(
        item => item.serviceType === targetType
      );

      // 调整总数
      const filteredCount = baseServiceData.typeDistribution.reduce(
        (sum, item) => sum + item.serviceCount, 0
      );
      baseServiceData.totalServiceCount = filteredCount;
      baseServiceData.newServiceToday = Math.floor(filteredCount * 0.02);
    }
  }

  // 根据办理部门筛选
  if (handleDept) {
    baseServiceData.overdueServices = baseServiceData.overdueServices.filter(
      item => item.handleDept === handleDept
    );
    baseServiceData.overdueServiceCount = baseServiceData.overdueServices.length;
  }

  // 根据时间周期调整数据
  if (timeRange === 'week') {
    baseServiceData = {
      ...baseServiceData,
      totalServiceCount: 2890,
      newServiceToday: 156,
      serviceCompleteRate: 93,
      overdueServiceCount: 32
    };
  } else if (timeRange === 'month') {
    baseServiceData = {
      ...baseServiceData,
      totalServiceCount: 8923,
      newServiceToday: 523,
      serviceCompleteRate: 94,
      overdueServiceCount: 28
    };
  }

  return baseServiceData;
};

// 模拟筛选服务数据
const getMockFilteredServices = (filters = {}) => {
  const mockServices = [
    {
      serviceId: 'S202411001',
      serviceType: '教学服务',
      serviceTitle: '教学设备维修申请',
      applyTime: '2025-10-25 09:15',
      overdueDays: 5,
      currentProgress: '处理',
      handleDept: '教学设备科'
    },
    {
      serviceId: 'S202411023',
      serviceType: '后勤服务',
      serviceTitle: '食堂卫生整改',
      applyTime: '2025-10-28 14:30',
      overdueDays: 3,
      currentProgress: '受理',
      handleDept: '后勤保障科'
    },
    {
      serviceId: 'S202411045',
      serviceType: '安全服务',
      serviceTitle: '校园安全隐患排查',
      applyTime: '2025-10-29 10:20',
      overdueDays: 2,
      currentProgress: '处理',
      handleDept: '安全管理科'
    }
  ];

  let filteredServices = mockServices;
  if (filters.serviceType) {
    filteredServices = filteredServices.filter(s => s.serviceType === filters.serviceType);
  }
  if (filters.handleDept) {
    filteredServices = filteredServices.filter(s => s.handleDept === filters.handleDept);
  }

  return {
    total: filteredServices.length,
    services: filteredServices
  };
};

// 模拟服务详情数据
const getMockServiceDetail = (serviceId) => {
  const mockDetails = {
    S202411001: {
      serviceId: 'S202411001',
      serviceTitle: '教学设备维修申请',
      serviceType: '教学服务',
      serviceSource: '学校上报',
      priority: '高',
      status: '处理中',
      applyTime: '2025-10-25 09:15:00',
      dueTime: '2025-10-30 17:00:00',
      currentProgress: '处理',
      handleDept: '教学设备科',
      handleStaff: '张技术员',
      serviceDescription: '第一中学多媒体教室投影设备故障，影响正常教学秩序，急需维修。',
      location: {
        address: '第一中学教学楼3楼多媒体教室',
        coord: '26.855227, 117.650114',
        district: '高新区'
      },
      involvedParties: [
        { name: '李老师', type: '申请人', contact: '138****1234', identity: '任课教师' }
      ],
      evidenceMaterials: [
        { type: '照片', name: '设备故障照片1.jpg', time: '2025-10-25 09:20', uploader: '李老师' },
        { type: '照片', name: '设备故障照片2.jpg', time: '2025-10-25 09:25', uploader: '李老师' }
      ],
      progressRecords: [
        {
          time: '2025-10-25 09:30:00',
          action: '服务受理',
          operator: '系统自动',
          description: '服务已成功受理，分配至教学设备科处理',
          attachments: []
        },
        {
          time: '2025-10-26 14:20:00',
          action: '现场检查',
          operator: '张技术员',
          description: '对故障设备进行现场检查，确认投影仪灯泡损坏',
          attachments: ['检查报告.pdf']
        }
      ],
      nextActions: [
        { action: '更换投影仪灯泡', deadline: '2025-10-28', responsible: '张技术员', status: '进行中' }
      ],
      statistics: {
        handleDays: 3,
        overdueDays: 0,
        visitTimes: 1,
        evidenceCount: 2
      }
    },
    S202411023: {
      serviceId: 'S202411023',
      serviceTitle: '食堂卫生整改',
      serviceType: '后勤服务',
      serviceSource: '家长反馈',
      priority: '中',
      status: '处理中',
      applyTime: '2025-10-28 14:30:00',
      dueTime: '2025-11-02 17:00:00',
      currentProgress: '受理',
      handleDept: '后勤保障科',
      handleStaff: '李管理员',
      serviceDescription: '家长反映实验小学校园食堂存在卫生问题，需要整改。',
      location: {
        address: '实验小学校园食堂',
        coord: '26.783227, 117.720114',
        district: '高新区'
      },
      involvedParties: [
        { name: '王家长', type: '反馈人', contact: '137****9012', identity: '学生家长' }
      ],
      evidenceMaterials: [
        { type: '照片', name: '食堂卫生照片.jpg', time: '2025-10-28 15:00', uploader: '王家长' }
      ],
      progressRecords: [
        {
          time: '2025-10-28 14:45:00',
          action: '服务受理',
          operator: '系统自动',
          description: '服务已受理，分配至后勤保障科处理',
          attachments: []
        }
      ],
      nextActions: [
        { action: '现场核查卫生情况', deadline: '2025-10-30', responsible: '李管理员', status: '待完成' }
      ],
      statistics: {
        handleDays: 1,
        overdueDays: 0,
        visitTimes: 0,
        evidenceCount: 1
      }
    }
  };

  return mockDetails[serviceId] || {
    serviceId: serviceId,
    serviceTitle: `服务 ${serviceId}`,
    serviceType: '其他',
    serviceSource: '未知',
    priority: '中',
    status: '处理中',
    applyTime: '2025-10-01 00:00:00',
    dueTime: '2025-10-07 17:00:00',
    currentProgress: '受理',
    handleDept: '相关部门',
    handleStaff: '待分配',
    serviceDescription: '服务详情正在整理中...',
    location: {
      address: '待确认',
      coord: '',
      district: '待确认'
    },
    involvedParties: [],
    evidenceMaterials: [],
    progressRecords: [
      {
        time: '2025-10-01 00:00:00',
        action: '服务创建',
        operator: '系统',
        description: '服务已创建，等待分配处理',
        attachments: []
      }
    ],
    nextActions: [
      { action: '分配承办人员', deadline: '2025-10-02', responsible: '系统', status: '待完成' }
    ],
    statistics: {
      handleDays: 0,
      overdueDays: 0,
      visitTimes: 0,
      evidenceCount: 0
    }
  };
};

// 模拟报告数据
const getMockReport = (reportId) => {
  return {
    title: '教育工作分析报告',
    statTime: '2025-11-03',
    createUser: '教育指挥中心',
    content: '本周教育工作整体平稳，学校总数1256所，较上年增长2%；在园在校学生数85600人，较上年增长3%；专任教师数5600人，较上年增长4%；教育资源配置率85%，较上年提升2个百分点；教育服务办结率92%，较上年提升3个百分点。需重点关注高新区资源配置率问题和城西区超期服务过多问题。'
  };
};

// ==================== 工具函数 ====================

// 获取导出文件名
const getExportFileName = (dataType) => {
  const nameMap = {
    'service': '服务数据',
    'type': '类型统计',
    'region': '区域统计',
    'source': '来源统计',
    'progress': '进度统计'
  };
  return nameMap[dataType] || '数据';
};

// 生成服务导出数据
const generateServiceExportData = async (params) => {
  // 模拟服务数据
  return [
    {
      '服务编号': 'S202411001',
      '服务标题': '教学设备维修申请',
      '服务类型': '教学服务',
      '服务来源': '学校上报',
      '受理时间': '2025-10-25 09:15',
      '办理时限': '2025-10-30 17:00',
      '当前进度': '处理',
      '办理部门': '教学设备科',
      '承办人员': '张技术员',
      '超期天数': 0,
      '优先级': '高'
    },
    {
      '服务编号': 'S202411023',
      '服务标题': '食堂卫生整改',
      '服务类型': '后勤服务',
      '服务来源': '家长反馈',
      '受理时间': '2025-10-28 14:30',
      '办理时限': '2025-11-02 17:00',
      '当前进度': '受理',
      '办理部门': '后勤保障科',
      '承办人员': '李管理员',
      '超期天数': 0,
      '优先级': '中'
    }
  ];
};

// 前端导出兜底方案
const exportWithFrontend = async (dataType, params) => {
  try {
    // 使用xlsx库进行前端导出
    const XLSX = await import('xlsx');

    let data = [];
    let fileName = '';

    switch (dataType) {
      case 'service':
        data = await generateServiceExportData(params);
        fileName = `服务数据_${new Date().toISOString().split('T')[0]}.xlsx`;
        break;
      case 'type':
        data = params.data || [];
        fileName = `服务类型统计_${new Date().toISOString().split('T')[0]}.xlsx`;
        break;
      case 'region':
        data = params.data || [];
        fileName = `区域服务统计_${new Date().toISOString().split('T')[0]}.xlsx`;
        break;
      default:
        data = params.data || [];
        fileName = `教育数据_${new Date().toISOString().split('T')[0]}.xlsx`;
    }

    // 创建工作簿
    const wb = XLSX.utils.book_new();
    const ws = XLSX.utils.json_to_sheet(data);
    XLSX.utils.book_append_sheet(wb, ws, '数据');

    // 导出文件
    XLSX.writeFile(wb, fileName);

    return { success: true, message: '导出成功', fileName };
  } catch (error) {
    console.error('前端导出失败:', error);
    throw new Error('导出功能暂不可用');
  }
};
