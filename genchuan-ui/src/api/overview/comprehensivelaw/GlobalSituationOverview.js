import request from '@/config/axios'
// 基础URL，根据执法主题调整
const BASE_URL = '/industry';

// 1.1.1 执法全域数据概览相关接口
export const fetchLawGlobalOverview = async (params = {}) => {
  let baseStats = {
    total_case_count: 3256,
    total_staff_count: 356,
    online_device_count: 342,
    new_case_today: 323,
    case_complete_rate: 38
  };
  // try {
  //   const response = await request.get({ url: `${BASE_URL}/law-overview/get`, params });
  //   if (response && typeof response === 'object') {
  //     console.log("执法全域数据概览接口调用成功:", response);
  //     baseStats = {
  //       total_case_count: response.totalCaseCount,
  //       total_staff_count: response.totalStaffCount,
  //       online_device_count: response.onlineDeviceCount,
  //       new_case_today: response.newCaseToday,
  //       case_complete_rate: response.caseCompleteRate
  //     };
  //   }
  // } catch (error) {
  //   console.warn('执法全域数据概览接口调用失败，使用模拟数据:', error.message);
  //   const { timeRange = 'today', area = [] } = params;
  //   baseStats = {
  //     total_case_count: 1256,
  //     total_staff_count: 156,
  //     online_device_count: 142,
  //     new_case_today: 23,
  //     case_complete_rate: 78
  //   };
  //
  //   // 根据时间范围调整数据
  //   if (timeRange === 'week') {
  //     baseStats = {
  //       total_case_count: 2890,
  //       total_staff_count: 156,
  //       online_device_count: 145,
  //       new_case_today: 156,
  //       case_complete_rate: 82
  //     };
  //   } else if (timeRange === 'month') {
  //     baseStats = {
  //       total_case_count: 8923,
  //       total_staff_count: 156,
  //       online_device_count: 148,
  //       new_case_today: 523,
  //       case_complete_rate: 85
  //     };
  //   }
  //
  //   // 根据行政区划调整数据
  //   if (area.length > 0) {
  //     const areaCode = area[area.length - 1];
  //     if (areaCode === 'gulou') {
  //       baseStats = {
  //         total_case_count: 320,
  //         total_staff_count: 45,
  //         online_device_count: 38,
  //         new_case_today: 8,
  //         case_complete_rate: 75
  //       };
  //     }
  //   }
  // }

  return {
    stats: [
      {
        id: 1,
        title: '执法案件总数',
        value: baseStats.total_case_count,
        unit: '件',
        rate: 8,
        warning: false,
        hasPulse: true,
        calculation: '案件总数 = 已受理案件数量总和'
      },
      {
        id: 2,
        title: '执法人员总数',
        value: baseStats.total_staff_count,
        unit: '人',
        rate: 2,
        warning: false,
        hasPulse: false,
        calculation: '执法人员总数 = 在编执法人员数量'
      },
      {
        id: 3,
        title: '执法设备在线数',
        value: baseStats.online_device_count,
        unit: '台',
        rate: -3,
        warning: false,
        hasPulse: false,
        calculation: '在线设备数 = 当前在线状态的执法设备数量'
      },
      {
        id: 4,
        title: '今日新增案件数',
        value: baseStats.new_case_today,
        unit: '件',
        rate: 15,
        warning: false,
        hasPulse: true,
        calculation: '今日新增 = 今日0点至当前时间受理的案件数'
      },
      {
        id: 5,
        title: '案件办结率',
        value: baseStats.case_complete_rate,
        unit: '%',
        rate: -2,
        warning: baseStats.case_complete_rate < 80,
        hasPulse: true,
        calculation: '办结率 = 已办结案件数 / 总案件数 × 100%'
      }
    ]
  };
};

// 1.1.2 执法核心指标相关接口
// export const fetchLawCoreIndicators = async (params = {}) => {
//   // 基础指标数据
//   let baseIndicators = [
//     {
//       indicator_id: '1',
//       indicator_name: '案件平均办结时长',
//       real_value: '8.5',
//       unit: '天',
//       compliance_rate: 95,
//       year_on_year: -5,
//       threshold_min: 1,
//       threshold_max: 5,
//       warn_status: '0',
//       update_time: '2025-11-03 15:30',
//       ext1: '执法案件统计表',
//       trend_7days: JSON.stringify({
//         xAxis: ['10-28', '10-29', '10-30', '10-31', '11-01', '11-02', '11-03'],
//         series: [2.8, 2.6, 2.5, 2.4, 2.5, 2.6, 2.5]
//       }),
//       calculation: '平均办结时长 = 总办结时长 / 已办结案件数'
//     },
//     {
//       indicator_id: '2',
//       indicator_name: '执法合规率',
//       real_value: '98',
//       unit: '%',
//       compliance_rate: 92,
//       year_on_year: 3,
//       threshold_min: 90,
//       threshold_max: 100,
//       warn_status: '0',
//       update_time: '2025-11-03 15:30',
//       ext1: '执法规范统计表',
//       trend_7days: JSON.stringify({
//         xAxis: ['10-28', '10-29', '10-30', '10-31', '11-01', '11-02', '11-03'],
//         series: [89, 90, 91, 92, 92, 91, 92]
//       }),
//       calculation: '合规率 = 合规案件数 / 总案件数 × 100%'},
//     {
//       indicator_id: '3',
//       indicator_name: '重复举报率',
//       real_value: '8',
//       unit: '%',
//       compliance_rate: 92,
//       year_on_year: 2,
//       threshold_min: 0,
//       threshold_max: 5,
//       warn_status: '1',
//       update_time: '2025-11-03 15:30',
//       ext1: '执法案件统计表',
//       trend_7days: JSON.stringify({
//         xAxis: ['10-28', '10-29', '10-30', '10-31', '11-01', '11-02', '11-03'],
//         series: [6, 7, 7, 8, 8, 7, 8]
//       }),
//       calculation: '重复举报率 = 重复举报案件数 / 总举报数 × 100%'},
//     {
//       indicator_id: '4',
//       indicator_name: '跨部门协同率',
//       real_value: '75',
//       unit: '%',
//       compliance_rate: 75,
//       year_on_year: 8,
//       threshold_min: 70,
//       threshold_max: 100,
//       warn_status: '0',
//       update_time: '2025-11-03 15:30',
//       ext1: '协同执法统计表',
//       trend_7days: JSON.stringify({
//         xAxis: ['10-28', '10-29', '10-30', '10-31', '11-01', '11-02', '11-03'],
//         series: [70, 72, 73, 74, 75, 74, 75]
//       }),
//       calculation: '协同率 = 跨部门协作案件数 / 总案件数 × 100%'}
//   ];
//
//   try {
//     const response = await request.get({ url: `${BASE_URL}/law-core-metrics/get`, params });
//     if (response ) {
//       console.log("执法核心指标接口调用成功:", response);
//       baseIndicators = [
//         {
//           indicator_id: '1',
//           indicator_name: '案件平均办结时长',
//           real_value: response.avgCaseHandleTime,
//           unit: '小时',
//           compliance_rate: 95,
//           year_on_year: -5,
//           threshold_min: 1,
//           threshold_max: 48,
//           warn_status: response.avgCaseHandleTimeCoreMetricVO.warnStatus,
//           update_time: response.avgCaseHandleTimeCoreMetricVO.updateTime,
//           ext1: '执法案件统计表',
//           trend_7days: JSON.stringify(convertTrendData(response.avgCaseHandleTimeCoreMetricVO.treatTrendVOList)),
//           calculation: '平均办结时长 = 总办结时长 / 已办结案件数'
//         },
//         {
//           indicator_id: '2',
//           indicator_name: '执法合规率',
//           real_value: response.lawComplianceRate,
//           unit: '%',
//           compliance_rate: 92,
//           year_on_year: 3,
//           threshold_min: 90,
//           threshold_max: 100,
//           warn_status: response.lawComplianceRateCoreMetricVO.warnStatus,
//           update_time: response.lawComplianceRateCoreMetricVO.updateTime,
//           ext1: '执法规范统计表',
//           trend_7days: JSON.stringify(convertTrendData(response.lawComplianceRateCoreMetricVO.treatTrendVOList)),
//           calculation: '合规率 = 合规案件数 / 总案件数 × 100%'},
//         {
//           indicator_id: '3',
//           indicator_name: '重复举报率',
//           real_value: response.repeatRptRate,
//           unit: '%',
//           compliance_rate: 92,
//           year_on_year: 2,
//           threshold_min: 0,
//           threshold_max: 5,
//           warn_status: response.repeatRptRateCoreMetricVO.warnStatus,
//           update_time: response.repeatRptRateCoreMetricVO.updateTime,
//           ext1: '执法案件统计表',
//           trend_7days: JSON.stringify(convertTrendData(response.repeatRptRateCoreMetricVO.treatTrendVOList)),
//           calculation: '重复举报率 = 重复举报案件数 / 总举报数 × 100%'},
//         {
//           indicator_id: '4',
//           indicator_name: '跨部门协同率',
//           real_value: response.crossDeptCoopRate,
//           unit: '%',
//           compliance_rate: 75,
//           year_on_year: 8,
//           threshold_min: 70,
//           threshold_max: 100,
//           warn_status: response.crossDeptCoopRateCoreMetricVO.warnStatus,
//           update_time: response.crossDeptCoopRateCoreMetricVO.updateTime,
//           ext1: '协同执法统计表',
//           trend_7days: JSON.stringify(convertTrendData(response.crossDeptCoopRateCoreMetricVO.treatTrendVOList)),
//           calculation: '协同率 = 跨部门协作案件数 / 总案件数 × 100%'}
//       ];
//     }
//   } catch (error) {
//     console.warn('执法核心指标接口调用失败，使用模拟数据:', error.message);
//     const { lawDomain = '' } = params;
//
//     // 根据执法领域筛选
//     if (lawDomain) {
//       baseIndicators = baseIndicators.filter(indicator => {
//         if (lawDomain === '市容执法') {
//           return indicator.indicator_name.includes('案件') || indicator.indicator_name.includes('合规');
//         } else if (lawDomain === '市场监管') {
//           return indicator.indicator_name.includes('举报') || indicator.indicator_name.includes('合规');
//         } else if (lawDomain === '环境保护') {
//           return indicator.indicator_name.includes('协同') || indicator.indicator_name.includes('案件');
//         }
//         return true;
//       });
//     }
//   }
//   return baseIndicators;
// };
// // 辅助函数：转换趋势数据
// const convertTrendData = (trendList) => {
//   if (!trendList || !Array.isArray(trendList)) {
//     return {
//       xAxis: ['2025-10-28', '2025-10-29', '2025-10-30', '2025-10-31', '2025-11-01', '2025-11-02', '2025-11-03'],
//       series: [0, 0, 0, 0, 0, 0, 0]
//     };
//   }
//
//   // 从日期字符串中提取 YY-MM-DD 格式
//   const xAxis = trendList.map(item => {
//     if (item.date) {
//       const date = new Date(item.date);
//       const year = date.getFullYear().toString().slice(-2); // 取后两位年份
//       const month = (date.getMonth() + 1).toString().padStart(2, '0');
//       const day = date.getDate().toString().padStart(2, '0');
//       return `${year}-${month}-${day}`;
//     }
//     return '';
//   });
//   const series = trendList.map(item => item.value || 0);
//   return { xAxis, series };
// };

// 在GlobalSituationOverview.js中添加API调用函数
// 获取考核目标及分数表数据
export const fetchAssessmentTargets = async (params = {}) => {
  let result = [
    { area: '广东省河源市源城区上城街道', score: 100, id: 't1' },
    { area: '广东省河源市源城区下城街道', score: 80, id: 't2' },
    { area: '广东省河源市源城区新江街道', score: 70, id: 't3' },
    { area: '广东省河源市源城区源西街道', score: 60, id: 't4' },
    { area: '广东省河源市源城区城东街道', score: 50, id: 't5' },
    { area: '广东省河源市源城区高埔岗街道', score: 40, id: 't6' }
  ];

  // try {
  //   const response = await request.get({
  //     url: `${BASE_URL}/assessment/targets`,
  //     params
  //   });
  //   console.log("考核目标及分数表接口调用成功:", response);
  //
  //   // 假设接口返回格式为 { data: [...] }
  //   if (response && Array.isArray(response.data)) {
  //     result = response.data.map(item => ({
  //       id: item.id,
  //       area: item.targetName,
  //       score: item.score
  //     }));
  //   }
  // } catch (error) {
  //   console.warn('获取考核目标数据失败，使用模拟数据:', error.message);
  // }

  return result;
};

// 获取场所类型评分表数据
export const fetchPlaceTypeScores = async (targetId, params = {}) => {
  let result = [
    { type: '市政道路', score: 70, weight: 20, id: 'p1' },
    { type: '公共厕所', score: 80, weight: 15, id: 'p2' },
    { type: '公共服务机构周边', score: 90, weight: 15, id: 'p3' },
    { type: '商业街', score: 60, weight: '-', id: 'p4' }
  ];

  // try {
  //   const response = await request.get({
  //     url: `${BASE_URL}/assessment/targets/${targetId}/places`,
  //     params
  //   });
  //   console.log(`目标${targetId}的场所类型评分表接口调用成功:`, response);
  //
  //   if (response && Array.isArray(response.data)) {
  //     result = response.data.map(item => ({
  //       id: item.id,
  //       type: item.placeType,
  //       score: item.score,
  //       weight: item.weight || '-'
  //     }));
  //   }
  // } catch (error) {
  //   console.warn(`获取目标${targetId}的场所类型数据失败，使用模拟数据:`, error.message);
  // }

  return result;
};

// 获取指标类型评分表数据
export const fetchIndicatorScores = async (placeId, params = {}) => {
  let result = [
    { indicatorType: '环境卫生类', orientation: '无明显垃圾', score: 70, weight: 2, id: 'i1' },
    { indicatorType: '环境卫生类', orientation: '无吊挂或积存垃圾', score: 80, weight: 0.5, id: 'i2' },
    { indicatorType: '设施容貌类', orientation: '建（构）筑物外立面整洁', score: 90, weight: 1, id: 'i3' },
    { indicatorType: '园林绿化类', orientation: '无花草枯死、泥土裸露', score: 60, weight: '-', id: 'i4' }
  ];

  // try {
  //   const response = await request.get({
  //     url: `${BASE_URL}/assessment/places/${placeId}/indicators`,
  //     params
  //   });
  //   console.log(`场所${placeId}的指标类型评分表接口调用成功:`, response);
  //
  //   if (response && Array.isArray(response.data)) {
  //     result = response.data.map(item => ({
  //       id: item.id,
  //       indicatorType: item.indicatorType,
  //       orientation: item.indicatorOrientation,
  //       score: item.score,
  //       weight: item.weight || '-'
  //     }));
  //   }
  // } catch (error) {
  //   console.warn(`获取场所${placeId}的指标类型数据失败，使用模拟数据:`, error.message);
  // }

  return result;
};

// 1.1.3 执法力量分布视图相关接口
export const fetchLawForceDistribution = async (params = {}) => {
  // try {
  //   const resp = await request.get({
  //     url: `${BASE_URL}/law-force-view/get`,
  //     params
  //   });
  //   // resp 直接就是后端 data
  //   if (resp && typeof resp === "object") {
  //     console.log("执法力量分布视图相关接口-成功获取到数据resp:", resp);
  //     const data = resp;
  //     const geometries = [];
  //
  //     // 1. 人员列表 staffLocList
  //     (data.staffLocList || []).forEach(item => {
  //       if (!item.coordX || !item.coordY) return; // 坐标为空直接跳过
  //       geometries.push({
  //         x: item.coordY,
  //         y: item.coordX,
  //         dataType: "staff",
  //         status: item.status || "online",
  //         name: item.userName || "未知人员",
  //         team: item.team || "",
  //         regionFullCode: item.regionFullCode
  //       });
  //     });
  //
  //     // 2. 车辆列表 vehicleLocList
  //     (data.vehicleLocList || []).forEach(item => {
  //       if (!item.vehicleCoordX || !item.vehicleCoordY) return;
  //       geometries.push({
  //         x: item.vehicleCoordY,
  //         y: item.vehicleCoordX,
  //         dataType: "vehicle",
  //         status: item.status || "parked",
  //         name: item.licensePlate || "未知车辆",
  //         type: item.vehicleType || "",
  //         regionFullCode: item.regionFullCode
  //       });
  //     });
  //
  //     // 3. 站点列表 stationLocList
  //     (data.stationLocList || []).forEach(item => {
  //       if (!item.stationCoordX || !item.stationCoordY) return;
  //       geometries.push({
  //         x: item.stationCoordY,
  //         y: item.stationCoordX,
  //         dataType: "station",
  //         status: item.status || "normal",
  //         name: item.stationName || "站点",
  //         type: item.stationType || "",
  //         regionFullCode: item.regionFullCode
  //       });
  //     });
  //
  //     // 统计数据（stats）
  //     const stats = {
  //       total_staff_count:
  //         data.staffLocListCount || (data.staffLocList || []).length,
  //       total_vehicle_count:
  //         data.vehicleLocListCount || (data.vehicleLocList || []).length,
  //       total_station_count:
  //         data.stationLocListCount || (data.stationLocList || []).length,
  //       online_staff_count: (data.staffLocList || []).filter(
  //         i => i.status === "online"
  //       ).length,
  //       active_vehicle_count: (data.vehicleLocList || []).filter(
  //         i => i.status === "moving"
  //       ).length
  //     };
  //
      // 区域力量分布（后端优先）
    //   const result = {
    //     geometries,
    //     stats
    //   };
    //   console.log("执法力量分布视图result:", result);
    //   return result;
    // }
  //   throw new Error("接口返回的数据不是对象，走模拟数据兜底");
  // } catch (error) {
  //   console.warn("执法力量分布接口调用失败，使用模拟数据:", error.message);
  //   const { forceType = "", lawTeam = "" } = params;
    let baseGeometries = [
      { x: 26.855227, y: 117.650114, dataType: "staff", status: "online", name: "张执法", team: "一中队" },
      { x: 26.823227, y: 117.8220114, dataType: "staff", status: "online", name: "李监管", team: "二中队" },
      { x: 26.815227, y: 117.750114, dataType: "staff", status: "busy", name: "王巡查", team: "一中队" },
      { x: 26.783227, y: 117.720114, dataType: "vehicle", status: "moving", name: "巡逻车001", type: "巡逻车" },
      { x: 26.845227, y: 117.680114, dataType: "vehicle", status: "parked", name: "执法车002", type: "执法车" },
      { x: 26.733227, y: 117.650114, dataType: "station", status: "normal", name: "高新区执法站", type: "固定站" },
      { x: 26.795227, y: 117.710114, dataType: "station", status: "normal", name: "经开区执法站", type: "固定站" }
    ];

    // if (forceType) {
    //   baseGeometries = baseGeometries.filter(item => item.dataType === forceType);
    // }
    const stats = {
      total_staff_count: 156,
      total_vehicle_count: 42,
      total_station_count: 18,
      online_staff_count: 142,
      active_vehicle_count: 38
    };
    const result = {
      geometries: baseGeometries,
      stats
    };
    console.log("分布模拟数据:", result);
    return result;
  // }
};

// 1.1.4 执法案件总览相关接口
export const fetchCaseOverview = async (params = {}) => {
  const { caseType = '', handleDept = '', timeRange = 'today' } = params;

  // 模拟数据（作为兜底）
  const mockData = {
    // 案件总量统计
    total_case_count: 1256,
    new_case_today: 23,
    case_complete_rate: 78,
    overdue_case_count: 45,

    // 案件类型分布
    type_distribution: [
      { case_type: '市容执法', case_count: 456, color: '#3B82F6' },
      { case_type: '市场监管', case_count: 342, color: '#10B981' },
      { case_type: '环境保护', case_count: 240, color: '#F59E0B' },
      { case_type: '安全生产', case_count: 150, color: '#EF4444' },
      { case_type: '其他', case_count: 68, color: '#8B5CF6' }
    ],

    // 案件来源分布
    source_distribution: [
      { case_source: '群众举报', case_count: 623, color: '#3B82F6' },
      { case_source: '巡查发现', case_count: 432, color: '#10B981' },
      { case_source: '上级交办', case_count: 201, color: '#F59E0B' }
    ],

    // 区域案件分布
    region_distribution: [
      { region_name: '高新区', case_count: 320 },
      { region_name: '经开区', case_count: 280 },
      { region_name: '城东区', case_count: 210 },
      { region_name: '城西区', case_count: 180 },
      { region_name: '城南区', case_count: 150 },
      { region_name: '城北区', case_count: 116 }
    ],

    // 案件办理进度
    progress_distribution: [
      { progress_stage: '受理', case_count: 156, color: '#3B82F6' },
      { progress_stage: '调查', case_count: 234, color: '#60A5FA' },
      { progress_stage: '处理', case_count: 345, color: '#F59E0B' },
      { progress_stage: '办结', case_count: 521, color: '#10B981' }
    ],

    // 超期案件列表
    overdue_cases: [
      {
        case_id: 'C202411001',
        case_type: '市容执法',
        case_title: '占道经营整治',
        apply_time: '2025-10-25 09:15',
        due_time: '2025-10-30 17:00',
        overdue_days: 5,
        handle_dept: '市容执法局',
        current_progress: '调查'
      },
      {
        case_id: 'C202411023',
        case_type: '市场监管',
        case_title: '无证经营查处',
        apply_time: '2025-10-28 14:30',
        due_time: '2025-11-02 17:00',
        overdue_days: 3,
        handle_dept: '市场监管局',
        current_progress: '受理'
      },
      {
        case_id: 'C202411045',
        case_type: '环境保护',
        case_title: '噪音污染投诉',
        apply_time: '2025-10-29 10:20',
        due_time: '2025-11-03 17:00',
        overdue_days: 2,
        handle_dept: '环保局',
        current_progress: '调查'
      }
    ],

    // 近期案件趋势
    recent_case_trend: [
      { date: '10-28', new_cases: 18, complete_cases: 15 },
      { date: '10-29', new_cases: 22, complete_cases: 18 },
      { date: '10-30', new_cases: 15, complete_cases: 20 },
      { date: '10-31', new_cases: 25, complete_cases: 22 },
      { date: '11-01', new_cases: 20, complete_cases: 19 },
      { date: '11-02', new_cases: 24, complete_cases: 21 },
      { date: '11-03', new_cases: 23, complete_cases: 20 }
    ]
  };

  // try {
  //   const response = await request.get({
  //     url: `${BASE_URL}/law-case-view/get`,
  //     params
  //   });
  //
  //   console.log("执法案件总览接口原始响应:", response);
  //
  //   // 检查响应格式：应该有 code 和 data 字段
  //   if (response && typeof response === 'object') {
  //     // 如果响应有 code 字段，说明是标准格式
  //     if (response.code !== undefined) {
  //       if (response.code === 0 && response.data) {
  //         console.log("执法案件总览接口调用成功:", response.data);
  //
  //         // 转换接口数据格式为前端所需格式
  //         const result = convertApiDataToFrontendFormat(response.data, mockData);
  //
  //         // 应用筛选参数
  //         return applyFiltersToResult(result, params);
  //       } else {
  //         console.warn('执法案件总览接口返回code不为0:', response.code, response.msg);
  //         return applyFiltersToMockData(mockData, params);
  //       }
  //     }
  //     // 如果没有code字段，直接是data对象
  //     else if (response.data) {
  //       console.log("执法案件总览接口调用成功（非标准格式）:", response.data);
  //       const result = convertApiDataToFrontendFormat(response.data, mockData);
  //       return applyFiltersToResult(result, params);
  //     }
  //     // 如果响应就是数据本身
  //     else {
  //       console.log("执法案件总览接口调用成功（直接数据）:", response);
  //       const result = convertApiDataToFrontendFormat(response, mockData);
  //       return applyFiltersToResult(result, params);
  //     }
  //   } else {
  //     console.warn('执法案件总览接口返回格式不符合要求，使用模拟数据');
  //     return applyFiltersToMockData(mockData, params);
  //   }
  // } catch (error) {
  //   console.warn('执法案件总览接口调用失败，使用模拟数据:', error.message);
    return applyFiltersToMockData(mockData, params);
  // }
};

// 转换API数据为前端所需格式
const convertApiDataToFrontendFormat = (apiData, mockData) => {
  console.log("转换前的API数据:", apiData);

  // 如果apiData已经是完整的格式，直接返回
  if (apiData.total_case_count !== undefined) {
    console.log("API数据已是前端格式，直接返回");
    return apiData;
  }

  // 否则，合并API数据和模拟数据，API数据优先
  const result = { ...mockData };

  // 更新超期案件数量
  if (apiData.overdueCaseCount !== undefined) {
    result.overdue_case_count = apiData.overdueCaseCount;
  }

  // 转换案件类型分布
  if (apiData.typeDistribution && Array.isArray(apiData.typeDistribution)) {
    result.type_distribution = apiData.typeDistribution.map((item, index) => {
      // 从模拟数据中获取对应的颜色
      const mockItem = mockData.type_distribution.find(mock =>
        mock.case_type === item.caseType
      ) || mockData.type_distribution[index] || mockData.type_distribution[0];

      return {
        case_type: item.caseType || item.case_type || '未知类型',
        case_count: item.caseCount || item.case_count || 0,
        color: mockItem.color || '#3B82F6'
      };
    });
  }

  // 转换案件来源分布
  if (apiData.sourceDistribution && Array.isArray(apiData.sourceDistribution)) {
    result.source_distribution = apiData.sourceDistribution.map((item, index) => {
      const mockItem = mockData.source_distribution.find(mock =>
        mock.case_source === item.caseSource
      ) || mockData.source_distribution[index] || mockData.source_distribution[0];

      return {
        case_source: item.caseSource || item.case_source || '未知来源',
        case_count: item.caseCount || item.case_count || 0,
        color: mockItem.color || '#3B82F6'
      };
    });
  }

  // 转换案件进度分布
  if (apiData.progressDistribution && Array.isArray(apiData.progressDistribution)) {
    result.progress_distribution = apiData.progressDistribution.map((item, index) => {
      const mockItem = mockData.progress_distribution.find(mock =>
        mock.progress_stage === item.progressStage
      ) || mockData.progress_distribution[index] || mockData.progress_distribution[0];

      return {
        progress_stage: item.progressStage || item.progress_stage || '未知阶段',
        case_count: item.caseCount || item.case_count || 0,
        color: mockItem.color || '#3B82F6'
      };
    });
  }

  console.log("转换后的数据:", result);
  return result;
};

// 对结果数据应用筛选条件
const applyFiltersToResult = (result, params) => {
  const { caseType = '', handleDept = '', timeRange = 'today' } = params;

  let filteredResult = { ...result };

  // 根据案件类型筛选
  if (caseType) {
    const typeMap = {
      '市容类': '市容执法',
      '市场类': '市场监管',
      '环保类': '环境保护'
    };

    const targetType = typeMap[caseType];
    if (targetType) {
      // 筛选类型分布
      filteredResult.type_distribution = filteredResult.type_distribution.filter(
        item => item.case_type === targetType
      );

      // 筛选超期案件
      if (filteredResult.overdue_cases) {
        filteredResult.overdue_cases = filteredResult.overdue_cases.filter(
          item => item.case_type === targetType
        );
      }

      // 调整总数
      const filteredCount = filteredResult.type_distribution.reduce(
        (sum, item) => sum + item.case_count, 0
      );
      filteredResult.total_case_count = filteredCount;
      filteredResult.new_case_today = Math.floor(filteredCount * 0.02);
      if (filteredResult.overdue_cases) {
        filteredResult.overdue_case_count = filteredResult.overdue_cases.length;
      }
    }
  }

  // 根据办理部门筛选
  if (handleDept && filteredResult.overdue_cases) {
    filteredResult.overdue_cases = filteredResult.overdue_cases.filter(
      item => item.handle_dept === handleDept
    );
    filteredResult.overdue_case_count = filteredResult.overdue_cases.length;
  }

  // 根据时间周期调整数据
  if (timeRange === 'week') {
    filteredResult = {
      ...filteredResult,
      total_case_count: 2890,
      new_case_today: 156,
      case_complete_rate: 82,
      overdue_case_count: 32
    };
  } else if (timeRange === 'month') {
    filteredResult = {
      ...filteredResult,
      total_case_count: 8923,
      new_case_today: 523,
      case_complete_rate: 85,
      overdue_case_count: 28
    };
  }

  return filteredResult;
};

// 对模拟数据应用筛选条件（兜底情况）
const applyFiltersToMockData = (mockData, params) => {
  const { caseType = '', handleDept = '', timeRange = 'today' } = params;
  let filteredData = { ...mockData };

  // 根据案件类型筛选
  if (caseType) {
    const typeMap = {
      '市容类': '市容执法',
      '市场类': '市场监管',
      '环保类': '环境保护'
    };

    const targetType = typeMap[caseType];
    if (targetType) {
      filteredData.type_distribution = filteredData.type_distribution.filter(
        item => item.case_type === targetType
      );

      filteredData.overdue_cases = filteredData.overdue_cases.filter(
        item => item.case_type === targetType
      );

      const filteredCount = filteredData.type_distribution.reduce(
        (sum, item) => sum + item.case_count, 0
      );
      filteredData.total_case_count = filteredCount;
      filteredData.new_case_today = Math.floor(filteredCount * 0.02);
      filteredData.overdue_case_count = filteredData.overdue_cases.length;
    }
  }

  // 根据办理部门筛选
  if (handleDept) {
    filteredData.overdue_cases = filteredData.overdue_cases.filter(
      item => item.handle_dept === handleDept
    );
    filteredData.overdue_case_count = filteredData.overdue_cases.length;
  }

  // 根据时间周期调整数据
  if (timeRange === 'week') {
    filteredData = {
      ...filteredData,
      total_case_count: 2890,
      new_case_today: 156,
      case_complete_rate: 82,
      overdue_case_count: 32
    };
  } else if (timeRange === 'month') {
    filteredData = {
      ...filteredData,
      total_case_count: 8923,
      new_case_today: 523,
      case_complete_rate: 85,
      overdue_case_count: 28
    };
  }

  return filteredData;
};

// 新增方法：获取实时位置数据
export const fetchRealTimeLocations = async () => {
  let result = {
    staff_locations: [],
    vehicle_locations: []
  };

  // try {
  //   const response = await request.get({ url: `${BASE_URL}/realTimeLocations` });
  //   console.log("实时位置数据接口调用成功:", response);
  //   result = response;
  // } catch (error) {
  //   console.warn('获取实时位置数据失败，使用模拟数据:', error.message);
  // }

  return result;
};

// 案件筛选接口
export const filterCases = async (filters) => {
  let result = {
    total: 0,
    cases: []
  };
  const mockCases = [
    {
      case_id: 'C202411001',
      case_type: '市容执法',
      case_title: '科技路占道经营整治',
      apply_time: '2025-10-25 09:15',
      overdue_days: 5,
      current_progress: '调查',
      handle_dept: '市容执法局'
    },
    {
      case_id: 'C202411023',
      case_type: '市场监管',
      case_title: '无证经营餐饮店查处',
      apply_time: '2025-10-28 14:30',
      overdue_days: 3,
      current_progress: '受理',
      handle_dept: '市场监管局'
    },
    {
      case_id: 'C202411045',
      case_type: '环境保护',
      case_title: '噪音污染投诉处理',
      apply_time: '2025-10-29 10:20',
      overdue_days: 2,
      current_progress: '调查',
      handle_dept: '环保局'
    }
  ];

  // try {
  //   const response = await request.post({ url: `${BASE_URL}/cases/filter`, data: filters });
  //   console.log("案件筛选接口调用成功:", response);
  //   result = response;
  // } catch (error) {
  //   console.warn('案件筛选接口调用失败，使用模拟数据:', error.message);
  //
  //   let filteredCases = mockCases;
  //   if (filters.caseType) {
  //     filteredCases = filteredCases.filter(c => c.case_type === filters.caseType);
  //   }
  //   if (filters.handleDept) {
  //     filteredCases = filteredCases.filter(c => c.handle_dept === filters.handleDept);
  //   }
  //
  //   result = {
  //     total: filteredCases.length,
  //     cases: filteredCases
  //   };
  // }

  return result;
};

// 获取案件详情
export const fetchCaseDetail = async (caseId) => {
  let result = getMockCaseDetail(caseId);

  // try {
  //   const response = await request.get({ url: `${BASE_URL}/case/${caseId}` });
  //   console.log("案件详情接口调用成功:", response);
  //
  //   // 检查返回的是否是HTML
  //   if (typeof response === 'string' && response.includes('<!DOCTYPE html>')) {
  //     console.warn('接口返回HTML页面，使用模拟数据');
  //   } else if (response && typeof response === 'object' && response.case_id) {
  //     result = response;
  //   } else {
  //     console.warn('接口返回数据格式不正确，使用模拟数据');
  //   }
  // } catch (error) {
  //   console.warn('获取案件详情失败，使用模拟数据:', error.message);
  // }

  return result;
};

// 提取模拟数据到单独函数
const getMockCaseDetail = (caseId) => {
  const mockDetails = {
    C202411001: {
      case_id: 'C202411001',
      case_title: '科技路占道经营整治',
      case_type: '市容执法',
      case_source: '巡查发现',
      priority: '高',
      status: '处理中',
      apply_time: '2025-10-25 09:15:00',
      due_time: '2025-10-30 17:00:00',
      current_progress: '调查',
      handle_dept: '市容执法局',
      handle_staff: '张执法',
      case_description: '科技路与创新大道交叉口存在严重占道经营现象，多家商户在行人通道摆放货架和商品，严重影响交通秩序和市容环境，周边居民多次投诉。',
      location: {
        address: '科技路与创新大道交叉口',
        coord: '26.855227, 117.650114',
        district: '高新区'
      },
      involved_parties: [
        { name: '李某', type: '当事人', contact: '138****1234', identity: '个体工商户' }
      ],
      evidence_materials: [
        { type: '照片', name: '现场照片1.jpg', time: '2025-10-25 09:20', uploader: '张执法' },
        { type: '照片', name: '现场照片2.jpg', time: '2025-10-25 09:25', uploader: '张执法' }
      ],
      progress_records: [
        {
          time: '2025-10-25 09:30:00',
          action: '案件受理',
          operator: '系统自动',
          description: '案件已成功受理，分配至市容执法局处理',
          attachments: []
        },
        {
          time: '2025-10-26 14:20:00',
          action: '现场调查',
          operator: '张执法',
          description: '对占道经营商户进行拍照取证，并下达整改通知书',
          attachments: ['整改通知书.pdf']
        }
      ],
      next_actions: [
        { action: '复查整改情况', deadline: '2025-10-28', responsible: '张执法', status: '进行中' }
      ],
      statistics: {
        handle_days: 3,
        overdue_days: 0,
        visit_times: 2,
        evidence_count: 3
      }
    },
    C202411023: {
      case_id: 'C202411023',
      case_title: '无证经营餐饮店查处',
      case_type: '市场监管',
      case_source: '群众举报',
      priority: '中',
      status: '处理中',
      apply_time: '2025-10-28 14:30:00',
      due_time: '2025-11-02 17:00:00',
      current_progress: '受理',
      handle_dept: '市场监管局',
      handle_staff: '李监管',
      case_description: '群众举报高新区创新园内存在无证经营餐饮店，存在食品安全隐患。',
      location: {
        address: '创新园A区3号楼101',
        coord: '26.783227, 117.720114',
        district: '高新区'
      },
      involved_parties: [
        { name: '赵某', type: '当事人', contact: '137****9012', identity: '餐饮店主' }
      ],
      evidence_materials: [
        { type: '照片', name: '店铺照片.jpg', time: '2025-10-28 15:00', uploader: '李监管' }
      ],
      progress_records: [
        {
          time: '2025-10-28 14:45:00',
          action: '案件受理',
          operator: '系统自动',
          description: '案件已受理，分配至市场监管局处理',
          attachments: []
        }
      ],
      next_actions: [
        { action: '现场核查许可证', deadline: '2025-10-30', responsible: '李监管', status: '待完成' }
      ],
      statistics: {
        handle_days: 1,
        overdue_days: 0,
        visit_times: 0,
        evidence_count: 1
      }
    },
    C202411045: {
      case_id: 'C202411045',
      case_title: '噪音污染投诉处理',
      case_type: '环境保护',
      case_source: '群众举报',
      priority: '中',
      status: '处理中',
      apply_time: '2025-10-29 10:20:00',
      due_time: '2025-11-03 17:00:00',
      current_progress: '调查',
      handle_dept: '环保局',
      handle_staff: '王环保',
      case_description: '居民投诉高新区某工地夜间施工噪音超标，影响休息。',
      location: {
        address: '高新区创新大道与科技路交叉口工地',
        coord: '26.815227, 117.750114',
        district: '高新区'
      },
      involved_parties: [
        { name: '刘某', type: '投诉人', contact: '139****5678', identity: '居民' },
        { name: '某建筑公司', type: '被投诉方', contact: '工地负责人', identity: '施工单位' }
      ],
      evidence_materials: [
        { type: '录音', name: '噪音录音.mp3', time: '2025-10-29 22:00', uploader: '系统自动' },
        { type: '检测报告', name: '噪音检测报告.pdf', time: '2025-10-30 09:30', uploader: '王环保' }
      ],
      progress_records: [
        {
          time: '2025-10-29 10:30:00',
          action: '案件受理',
          operator: '系统自动',
          description: '案件已受理，分配至环保局处理',
          attachments: []
        },
        {
          time: '2025-10-30 09:15:00',
          action: '现场调查',
          operator: '王环保',
          description: '已前往现场进行噪音检测，确认存在夜间施工噪音超标问题',
          attachments: ['检测报告.pdf', '现场照片.jpg']
        }
      ],
      next_actions: [
        { action: '出具整改通知书', deadline: '2025-11-05', responsible: '王环保', status: '待完成' },
        { action: '复查整改效果', deadline: '2025-11-07', responsible: '王环保', status: '待完成' }
      ],
      statistics: {
        handle_days: 2,
        overdue_days: 2,
        visit_times: 1,
        evidence_count: 2
      }
    }
  };

  return mockDetails[caseId] || {
    case_id: caseId,
    case_title: `案件 ${caseId}`,
    case_type: '其他',
    case_source: '未知',
    priority: '中',
    status: '处理中',
    apply_time: '2025-10-01 00:00:00',
    due_time: '2025-10-07 17:00:00',
    current_progress: '受理',
    handle_dept: '相关部门',
    handle_staff: '待分配',
    case_description: '案件详情正在整理中...',
    location: {
      address: '待确认',
      coord: '',
      district: '待确认'
    },
    involved_parties: [],
    evidence_materials: [],
    progress_records: [
      {
        time: '2025-10-01 00:00:00',
        action: '案件创建',
        operator: '系统',
        description: '案件已创建，等待分配处理',
        attachments: []
      }
    ],
    next_actions: [
      { action: '分配承办人员', deadline: '2025-10-02', responsible: '系统', status: '待完成' }
    ],
    statistics: {
      handle_days: 0,
      overdue_days: 0,
      visit_times: 0,
      evidence_count: 0
    }
  };
};

// 查看报告
export const viewReport = async (reportId) => {
  let result = {
    title: '执法工作分析报告',
    stat_time: '2025-11-03',
    create_user: '执法指挥中心',
    content: '本周执法工作整体平稳，案件总数1256件，较上周增长8%；案件办结率78%，较上周下降2个百分点；执法合规率92%，保持较好水平；重复举报率8%，超过5%阈值需要重点关注；跨部门协同率75%，较上周提升8个百分点。需重点关注高新区案件办结效率问题和城西区超期案件过多问题。'
  };

  // try {
  //   const response = await request.get({ url: `${BASE_URL}/report/${reportId}` });
  //   console.log("查看报告接口调用成功:", response);
  //   result = response;
  // } catch (error) {
  //   console.warn('查看报告失败，使用模拟数据:', error.message);
  // }

  return result;
};

// 获取导出文件名
const getExportFileName = (dataType) => {
  const nameMap = {
    'case': '案件数据',
    'type': '类型统计',
    'region': '区域统计',
    'source': '来源统计',
    'progress': '进度统计'
  };
  return nameMap[dataType] || '数据';
};

// 生成案件导出数据
const generateCaseExportData = async (params) => {
  // 模拟案件数据
  return [
    {
      '案件编号': 'C202411001',
      '案件标题': '科技路占道经营整治',
      '案件类型': '市容执法',
      '案件来源': '巡查发现',
      '受理时间': '2025-10-25 09:15',
      '办理时限': '2025-10-30 17:00',
      '当前进度': '调查',
      '办理部门': '市容执法局',
      '承办人员': '张执法',
      '超期天数': 0,
      '优先级': '高'
    },
    {
      '案件编号': 'C202411023',
      '案件标题': '无证经营餐饮店查处',
      '案件类型': '市场监管',
      '案件来源': '群众举报',
      '受理时间': '2025-10-28 14:30',
      '办理时限': '2025-11-02 17:00',
      '当前进度': '受理',
      '办理部门': '市场监管局',
      '承办人员': '李监管',
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
      case 'case':
        data = await generateCaseExportData(params);
        fileName = `案件数据_${new Date().toISOString().split('T')[0]}.xlsx`;
        break;
      case 'type':
        data = params.data || [];
        fileName = `案件类型统计_${new Date().toISOString().split('T')[0]}.xlsx`;
        break;
      case 'region':
        data = params.data || [];
        fileName = `区域案件统计_${new Date().toISOString().split('T')[0]}.xlsx`;
        break;
      default:
        data = params.data || [];
        fileName = `执法数据_${new Date().toISOString().split('T')[0]}.xlsx`;
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

// 导出数据
export const exportData = async (dataType, params) => {
  try {
    console.log(`导出${dataType}数据，参数:`, params);

    // 实际项目中调用后端导出接口
    const response = await request({
      method: 'post',
      url: `${BASE_URL}/export/${dataType}`,
      data: params,
      responseType: 'blob' // 重要：接收二进制数据
    });

    // 创建下载链接
    const blob = new Blob([response], {
      type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
    });
    const url = window.URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;

    // 生成文件名
    const timestamp = new Date().toISOString().split('T')[0];
    const fileName = `执法${getExportFileName(dataType)}_${timestamp}.xlsx`;
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
  let result = { success: true };

  try {
    const response = await request.post({ url: `${BASE_URL}/indicator/reason`, data: params });
    console.log("更新指标异常原因接口调用成功:", response);
    result = response;
  } catch (error) {
    console.warn('更新指标异常原因失败，使用模拟成功:', error.message);
  }

  return result;
};

// 更新指标阈值
export const updateIndicatorThreshold = async (params) => {
  let result = { success: true };

  try {
    const response = await request.post({ url: `${BASE_URL}/indicator/threshold`, data: params });
    console.log("更新指标阈值接口调用成功:", response);
    result = response;
  } catch (error) {
    console.warn('更新指标阈值失败，使用模拟成功:', error.message);
  }

  return result;
};

// 处理预警
export const handleWarning = async (params) => {
  let result = { success: true };

  try {
    const response = await request.post({ url: `${BASE_URL}/warning/handle`, data: params });
    console.log("处理预警接口调用成功:", response);
    result = response;
  } catch (error) {
    console.warn('处理预警失败，使用模拟成功:', error.message);
  }

  return result;
};

// 提交服务事项处理结果
export const submitServiceHandle = async (params) => {
  let result = { success: true };

  try {
    const response = await request.post({ url: `${BASE_URL}/service/handle`, data: params });
    console.log("提交服务事项处理结果接口调用成功:", response);
    result = response;
  } catch (error) {
    console.warn('提交服务事项处理结果失败，使用模拟成功:', error.message);
  }
  return result;
};
