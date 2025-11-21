// 引入axios（假设项目中已配置axios）
import axios from 'axios';

// 基础URL，根据执法主题调整
const BASE_URL = '/api/comprehensivelaw';

// 1.1.1 执法全域数据概览相关接口
export const fetchLawGlobalOverview = async (params = {}) => {
  try {
    const response = await axios.get(`${BASE_URL}/globalOverview`, { params });
    if (response.data && typeof response.data === 'object') {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('执法全域数据概览接口调用失败，使用模拟数据:', error.message);

    const { timeRange = 'today', area = [] } = params;

    let baseStats = {
      total_case_count: 1256,
      total_staff_count: 156,
      online_device_count: 142,
      new_case_today: 23,
      case_complete_rate: 78
    };

    // 根据时间范围调整数据
    if (timeRange === 'week') {
      baseStats = {
        total_case_count: 2890,
        total_staff_count: 156,
        online_device_count: 145,
        new_case_today: 156,
        case_complete_rate: 82
      };
    } else if (timeRange === 'month') {
      baseStats = {
        total_case_count: 8923,
        total_staff_count: 156,
        online_device_count: 148,
        new_case_today: 523,
        case_complete_rate: 85
      };
    }

    // 根据行政区划调整数据
    if (area.length > 0) {
      const areaCode = area[area.length - 1];
      if (areaCode === 'gulou') {
        baseStats = {
          total_case_count: 320,
          total_staff_count: 45,
          online_device_count: 38,
          new_case_today: 8,
          case_complete_rate: 75
        };
      }
    }

    return {
      stats: [
        {
          id: 1,
          title: '执法案件总数',
          value: baseStats.total_case_count,
          unit: '件',
          rate: 8,
          rateText: '较上月 +8%',
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
          rateText: '较上月 +2%',
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
          rateText: '较上月 -3%',
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
          rateText: '较昨日 +15%',
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
          rateText: '较上月 -2%',
          warning: baseStats.case_complete_rate < 80,
          hasPulse: true,
          calculation: '办结率 = 已办结案件数 / 总案件数 × 100%'
        }
      ],
      // 区域案件分布
      region_case_distribution: [
        { region_name: '高新区', case_count: 320 },
        { region_name: '经开区', case_count: 280 },
        { region_name: '城东区', case_count: 210 },
        { region_name: '城西区', case_count: 180 },
        { region_name: '城南区', case_count: 150 },
        { region_name: '城北区', case_count: 116 }
      ],
      // 案件类型分布
      case_type_distribution: [
        { type_name: '市容执法', count: 420 },
        { type_name: '市场监管', count: 310 },
        { type_name: '环境保护', count: 240 },
        { type_name: '安全生产', count: 150 },
        { type_name: '其他', count: 136 }
      ]
    };
  }
};

// 1.1.2 执法核心指标相关接口
export const fetchLawCoreIndicators = async (params = {}) => {
  try {
    const response = await axios.get(`${BASE_URL}/coreIndicators`, { params });
    if (response.data && Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('执法核心指标接口调用失败，使用模拟数据:', error.message);

    const { lawDomain = '', timeRange = '7days' } = params;

    // 基础指标数据
    let baseIndicators = [
      {
        indicator_id: '1',
        indicator_name: '案件平均办结时长',
        real_value: '2.5',
        unit: '天',
        compliance_rate: 95,
        year_on_year: -5,
        threshold_min: 1,
        threshold_max: 5,
        warn_status: '0',
        update_time: '2025-11-03 15:30',
        ext1: '执法案件统计表',
        trend_7days: JSON.stringify({
          xAxis: ['10-28', '10-29', '10-30', '10-31', '11-01', '11-02', '11-03'],
          series: [2.8, 2.6, 2.5, 2.4, 2.5, 2.6, 2.5]
        }),
        calculation: '平均办结时长 = 总办结时长 / 已办结案件数'
      },
      {
        indicator_id: '2',
        indicator_name: '执法合规率',
        real_value: '92',
        unit: '%',
        compliance_rate: 92,
        year_on_year: 3,
        threshold_min: 90,
        threshold_max: 100,
        warn_status: '0',
        update_time: '2025-11-03 15:30',
        ext1: '执法规范统计表',
        trend_7days: JSON.stringify({
          xAxis: ['10-28', '10-29', '10-30', '10-31', '11-01', '11-02', '11-03'],
          series: [89, 90, 91, 92, 92, 91, 92]
        }),
        calculation: '合规率 = 合规案件数 / 总案件数 × 100%'
      },
      {
        indicator_id: '3',
        indicator_name: '重复举报率',
        real_value: '8',
        unit: '%',
        compliance_rate: 92,
        year_on_year: 2,
        threshold_min: 0,
        threshold_max: 5,
        warn_status: '1',
        update_time: '2025-11-03 15:30',
        ext1: '执法案件统计表',
        trend_7days: JSON.stringify({
          xAxis: ['10-28', '10-29', '10-30', '10-31', '11-01', '11-02', '11-03'],
          series: [6, 7, 7, 8, 8, 7, 8]
        }),
        calculation: '重复举报率 = 重复举报案件数 / 总举报数 × 100%'
      },
      {
        indicator_id: '4',
        indicator_name: '跨部门协同率',
        real_value: '75',
        unit: '%',
        compliance_rate: 75,
        year_on_year: 8,
        threshold_min: 70,
        threshold_max: 100,
        warn_status: '0',
        update_time: '2025-11-03 15:30',
        ext1: '协同执法统计表',
        trend_7days: JSON.stringify({
          xAxis: ['10-28', '10-29', '10-30', '10-31', '11-01', '11-02', '11-03'],
          series: [70, 72, 73, 74, 75, 74, 75]
        }),
        calculation: '协同率 = 跨部门协作案件数 / 总案件数 × 100%'
      }
    ];

    // 根据执法领域筛选
    if (lawDomain) {
      baseIndicators = baseIndicators.filter(indicator => {
        if (lawDomain === '市容执法') {
          return indicator.indicator_name.includes('案件') || indicator.indicator_name.includes('合规');
        } else if (lawDomain === '市场监管') {
          return indicator.indicator_name.includes('举报') || indicator.indicator_name.includes('合规');
        } else if (lawDomain === '环境保护') {
          return indicator.indicator_name.includes('协同') || indicator.indicator_name.includes('案件');
        }
        return true;
      });
    }

    return baseIndicators;
  }
};

// 1.1.3 执法力量分布视图相关接口
export const fetchLawForceDistribution = async (params = {}) => {
  try {
    const response = await axios.get(`${BASE_URL}/forceDistribution`, { params });
    if (response.data && typeof response.data === 'object') {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('执法力量分布接口调用失败，使用模拟数据:', error.message);

    const { forceType = '', lawTeam = '' } = params;

    // 基础力量数据
    let baseGeometries = [
      {x:26.855227, y:117.650114, dataType: 'staff', status: 'online', name: '张执法', team: '一中队'},
      {x:26.783227, y:117.720114, dataType: 'vehicle', status: 'moving', name: '巡逻车001', type: '巡逻车'},
      {x:26.733227, y:117.650114, dataType: 'station', status: 'normal', name: '高新区执法站', type: '固定站'},
      {x:26.823227, y:117.8220114, dataType: 'staff', status: 'online', name: '李监管', team: '二中队'},
      {x:26.845227, y:117.680114, dataType: 'vehicle', status: 'parked', name: '执法车002', type: '执法车'},
      {x:26.815227, y:117.750114, dataType: 'staff', status: 'busy', name: '王巡查', team: '一中队'},
      {x:26.795227, y:117.710114, dataType: 'station', status: 'normal', name: '经开区执法站', type: '固定站'},
    ];

    // 根据力量类型筛选
    if (forceType) {
      baseGeometries = baseGeometries.filter(item => item.dataType === forceType);
    }

    // 根据执法中队筛选
    if (lawTeam) {
      baseGeometries = baseGeometries.filter(item => {
        if (lawTeam === 'team1') return item.team === '一中队';
        if (lawTeam === 'team2') return item.team === '二中队';
        if (lawTeam === 'team3') return item.team === '三中队';
        return true;
      });
    }

    // 统计数据
    const stats = {
      total_staff_count: 156,
      total_vehicle_count: 42,
      total_station_count: 18,
      online_staff_count: 142,
      active_vehicle_count: 38
    };

    // 区域力量分布
    const regionForceDistribution = [
      { region_name: '高新区', staff_count: 45, vehicle_count: 12, station_count: 4 },
      { region_name: '经开区', staff_count: 38, vehicle_count: 10, station_count: 3 },
      { region_name: '城东区', staff_count: 28, vehicle_count: 8, station_count: 3 },
      { region_name: '城西区', staff_count: 25, vehicle_count: 6, station_count: 3 },
      { region_name: '城南区', staff_count: 12, vehicle_count: 4, station_count: 3 },
      { region_name: '城北区', staff_count: 8, vehicle_count: 2, station_count: 2 }
    ];

    return {
      geometries: baseGeometries,
      stats: stats,
      region_force_distribution: regionForceDistribution
    };
  }
};

// 1.1.4 执法案件总览相关接口
export const fetchCaseOverview = async (params = {}) => {
  try {
    const response = await axios.get(`${BASE_URL}/caseOverview`, { params });
    if (response.data && typeof response.data === 'object') {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('执法案件总览接口调用失败，使用模拟数据:', error.message);

    const { caseType = '', handleDept = '', timeRange = 'today' } = params;

    // 基础案件数据
    let baseCaseData = {
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
      // 区域案件分布 - 新增字段
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

    // 根据案件类型筛选
    if (caseType) {
      const typeMap = {
        '市容类': '市容执法',
        '市场类': '市场监管',
        '环保类': '环境保护'
      };

      const targetType = typeMap[caseType];
      if (targetType) {
        baseCaseData.type_distribution = baseCaseData.type_distribution.filter(
          item => item.case_type === targetType
        );

        // 调整总数
        const filteredCount = baseCaseData.type_distribution.reduce(
          (sum, item) => sum + item.case_count, 0
        );
        baseCaseData.total_case_count = filteredCount;
        baseCaseData.new_case_today = Math.floor(filteredCount * 0.02);
      }
    }

    // 根据办理部门筛选
    if (handleDept) {
      baseCaseData.overdue_cases = baseCaseData.overdue_cases.filter(
        item => item.handle_dept === handleDept
      );
      baseCaseData.overdue_case_count = baseCaseData.overdue_cases.length;
    }

    // 根据时间周期调整数据
    if (timeRange === 'week') {
      baseCaseData = {
        ...baseCaseData,
        total_case_count: 2890,
        new_case_today: 156,
        case_complete_rate: 82,
        overdue_case_count: 32
      };
    } else if (timeRange === 'month') {
      baseCaseData = {
        ...baseCaseData,
        total_case_count: 8923,
        new_case_today: 523,
        case_complete_rate: 85,
        overdue_case_count: 28
      };
    }

    return baseCaseData;
  }
};

// 新增方法：获取实时位置数据
export const fetchRealTimeLocations = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/realTimeLocations`);
    return response.data;
  } catch (error) {
    console.warn('获取实时位置数据失败，使用模拟数据:', error.message);
    return {
      staff_locations: [],
      vehicle_locations: []
    };
  }
};

// 新增方法：获取区域热力图数据
export const fetchRegionHeatmapData = async (regionCode) => {
  try {
    const response = await axios.get(`${BASE_URL}/regionHeatmap/${regionCode}`);
    return response.data;
  } catch (error) {
    console.warn('获取区域热力图数据失败，使用模拟数据:', error.message);
    return {
      region_code: regionCode,
      heatmap_data: []
    };
  }
};

// 案件筛选接口
export const filterCases = async (filters) => {
  try {
    const response = await axios.post(`${BASE_URL}/cases/filter`, filters);
    return response.data;
  } catch (error) {
    console.warn('案件筛选接口调用失败，使用模拟数据:', error.message);

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

    let filteredCases = mockCases;
    if (filters.caseType) {
      filteredCases = filteredCases.filter(c => c.case_type === filters.caseType);
    }
    if (filters.handleDept) {
      filteredCases = filteredCases.filter(c => c.handle_dept === filters.handleDept);
    }

    return {
      total: filteredCases.length,
      cases: filteredCases
    };
  }
};

// 获取案件详情 - 修复返回HTML问题
export const fetchCaseDetail = async (caseId) => {
  try {
    const response = await axios.get(`${BASE_URL}/case/${caseId}`);

    // 检查返回的是否是HTML
    if (typeof response.data === 'string' && response.data.includes('<!DOCTYPE html>')) {
      console.warn('接口返回HTML页面，使用模拟数据');
      return getMockCaseDetail(caseId);
    }

    // 检查返回数据格式
    if (response.data && typeof response.data === 'object' && response.data.case_id) {
      return response.data;
    } else {
      console.warn('接口返回数据格式不正确，使用模拟数据');
      return getMockCaseDetail(caseId);
    }
  } catch (error) {
    console.warn('获取案件详情失败，使用模拟数据:', error.message);
    return getMockCaseDetail(caseId);
  }
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
  try {
    const response = await axios.get(`${BASE_URL}/report/${reportId}`);
    return response.data;
  } catch (error) {
    console.warn('查看报告失败，使用模拟数据:', error.message);
    return {
      title: '执法工作分析报告',
      stat_time: '2025-11-03',
      create_user: '执法指挥中心',
      content: '本周执法工作整体平稳，案件总数1256件，较上周增长8%；案件办结率78%，较上周下降2个百分点；执法合规率92%，保持较好水平；重复举报率8%，超过5%阈值需要重点关注；跨部门协同率75%，较上周提升8个百分点。需重点关注高新区案件办结效率问题和城西区超期案件过多问题。'
    };
  }
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
    const response = await axios({
      method: 'post',
      url: `${BASE_URL}/export/${dataType}`,
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
  console.log('模拟更新指标异常原因，参数:', params);
  return { success: true };
};

// 更新指标阈值
export const updateIndicatorThreshold = async (params) => {
  console.log('模拟更新指标阈值，参数:', params);
  return { success: true };
};

// 处理预警
export const handleWarning = async (params) => {
  console.log('模拟处理预警，参数:', params);
  return { success: true };
};

// 提交服务事项处理结果
export const submitServiceHandle = async (params) => {
  console.log('模拟提交服务事项处理结果，参数:', params);
  return { success: true };
};

// WebSocket 连接管理
let socket = null;

// 初始化 WebSocket 连接
export const initWebSocket = (onMessage) => {
  if (socket) {
    socket.close();
  }

  // 模拟 WebSocket 连接（实际项目中替换为真实 WebSocket 地址）
  try {
    socket = new WebSocket('ws://localhost:8080/ws/law-enforcement');

    socket.onopen = () => {
      console.log('WebSocket 连接已建立');
    };

    socket.onmessage = (event) => {
      const data = JSON.parse(event.data);
      if (onMessage && typeof onMessage === 'function') {
        onMessage(data);
      }
    };

    socket.onclose = () => {
      console.log('WebSocket 连接已关闭');
    };

    socket.onerror = (error) => {
      console.error('WebSocket 连接错误:', error);
    };
  } catch (error) {
    console.error('WebSocket 初始化失败:', error);
  }

  return socket;
};

// 关闭 WebSocket 连接
export const closeWebSocket = () => {
  if (socket) {
    socket.close();
    socket = null;
  }
};
