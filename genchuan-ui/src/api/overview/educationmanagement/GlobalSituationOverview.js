// 引入axios（假设项目中已配置axios）
import axios from 'axios';

// 基础URL，根据教育主题调整
const BASE_URL = '/api/educationmanagement';

// 1.1.1 教育全域数据概览相关接口
export const fetchEduGlobalOverview = async (params = {}) => {
  try {
    const response = await axios.get(`${BASE_URL}/globalOverview`, { params });
    if (response.data && typeof response.data === 'object') {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('教育全域数据概览接口调用失败，使用模拟数据:', error.message);

    const { timeRange = 'today', area = [] } = params;

    let baseStats = {
      total_school_count: 1256,
      total_student_count: 85600,
      total_teacher_count: 5600,
      resource_allocation_rate: 85,
      service_complete_rate: 92
    };

    // 根据时间范围调整数据
    if (timeRange === 'week') {
      baseStats = {
        total_school_count: 1256,
        total_student_count: 85600,
        total_teacher_count: 5600,
        resource_allocation_rate: 86,
        service_complete_rate: 93
      };
    } else if (timeRange === 'month') {
      baseStats = {
        total_school_count: 1256,
        total_student_count: 85600,
        total_teacher_count: 5600,
        resource_allocation_rate: 87,
        service_complete_rate: 94
      };
    }

    // 根据行政区划调整数据
    if (area.length > 0) {
      const areaCode = area[area.length - 1];
      if (areaCode === 'gulou') {
        baseStats = {
          total_school_count: 45,
          total_student_count: 3200,
          total_teacher_count: 280,
          resource_allocation_rate: 82,
          service_complete_rate: 88
        };
      }
    }

    return {
      stats: [
        {
          id: 1,
          title: '学校总数',
          value: baseStats.total_school_count,
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
          value: baseStats.total_student_count,
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
          value: baseStats.total_teacher_count,
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
          value: baseStats.resource_allocation_rate,
          unit: '%',
          rate: 2,
          rateText: '较上年 +2%',
          warning: baseStats.resource_allocation_rate < 85,
          hasPulse: true,
          calculation: '资源配置率 = 已配置资源学校数 / 总学校数 × 100%'
        },
        {
          id: 5,
          title: '教育服务办结率',
          value: baseStats.service_complete_rate,
          unit: '%',
          rate: 3,
          rateText: '较上年 +3%',
          warning: baseStats.service_complete_rate < 90,
          hasPulse: true,
          calculation: '服务办结率 = 已办结服务工单数 / 总服务工单数 × 100%'
        }
      ],
      // 区域学校分布
      region_school_distribution: [
        { region_name: '高新区', school_count: 45 },
        { region_name: '经开区', school_count: 38 },
        { region_name: '城东区', school_count: 28 },
        { region_name: '城西区', school_count: 25 },
        { region_name: '城南区', school_count: 12 },
        { region_name: '城北区', school_count: 8 }
      ],
      // 学校类型分布
      school_type_distribution: [
        { type_name: '幼儿园', count: 320 },
        { type_name: '小学', count: 280 },
        { type_name: '初中', count: 210 },
        { type_name: '高中', count: 180 },
        { type_name: '其他', count: 116 }
      ]
    };
  }
};

// 1.1.2 教育核心指标相关接口
export const fetchEduCoreIndicators = async (params = {}) => {
  try {
    const response = await axios.get(`${BASE_URL}/coreIndicators`, { params });
    if (response.data && Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('教育核心指标接口调用失败，使用模拟数据:', error.message);

    const { eduDomain = '', timeRange = '7days' } = params;

    // 基础指标数据
    let baseIndicators = [
      {
        indicator_id: '1',
        indicator_name: '学前三年毛入园率',
        real_value: '95.2',
        unit: '%',
        compliance_rate: 95,
        year_on_year: 2.5,
        threshold_min: 90,
        threshold_max: 100,
        warn_status: '0',
        update_time: '2025-11-03 15:30',
        ext1: '教育统计报表',
        trend_7days: JSON.stringify({
          xAxis: ['10-28', '10-29', '10-30', '10-31', '11-01', '11-02', '11-03'],
          series: [94.8, 95.0, 95.1, 95.2, 95.2, 95.1, 95.2]
        }),
        calculation: '毛入园率 = 在园幼儿数 / 适龄幼儿总数 × 100%'
      },
      {
        indicator_id: '2',
        indicator_name: '九年义务教育巩固率',
        real_value: '98.5',
        unit: '%',
        compliance_rate: 98,
        year_on_year: 1.2,
        threshold_min: 95,
        threshold_max: 100,
        warn_status: '0',
        update_time: '2025-11-03 15:30',
        ext1: '教育统计报表',
        trend_7days: JSON.stringify({
          xAxis: ['10-28', '10-29', '10-30', '10-31', '11-01', '11-02', '11-03'],
          series: [98.2, 98.3, 98.4, 98.5, 98.5, 98.4, 98.5]
        }),
        calculation: '巩固率 = 毕业学生数 / 入学学生数 × 100%'
      },
      {
        indicator_id: '3',
        indicator_name: '师生比',
        real_value: '15.3',
        unit: '',
        compliance_rate: 92,
        year_on_year: -0.5,
        threshold_min: 12,
        threshold_max: 18,
        warn_status: '0',
        update_time: '2025-11-03 15:30',
        ext1: '师生信息表',
        trend_7days: JSON.stringify({
          xAxis: ['10-28', '10-29', '10-30', '10-31', '11-01', '11-02', '11-03'],
          series: [15.8, 15.6, 15.5, 15.4, 15.3, 15.4, 15.3]
        }),
        calculation: '师生比 = 学生总数 / 专任教师数'
      },
      {
        indicator_id: '4',
        indicator_name: '专任教师本科以上学历占比',
        real_value: '88.6',
        unit: '%',
        compliance_rate: 88,
        year_on_year: 3.2,
        threshold_min: 85,
        threshold_max: 100,
        warn_status: '0',
        update_time: '2025-11-03 15:30',
        ext1: '师资信息表',
        trend_7days: JSON.stringify({
          xAxis: ['10-28', '10-29', '10-30', '10-31', '11-01', '11-02', '11-03'],
          series: [87.8, 88.0, 88.2, 88.4, 88.5, 88.6, 88.6]
        }),
        calculation: '本科以上占比 = 本科以上学历教师数 / 专任教师总数 × 100%'
      },
      {
        indicator_id: '5',
        indicator_name: '辍学率',
        real_value: '0.45',
        unit: '%',
        compliance_rate: 95,
        year_on_year: -0.1,
        threshold_min: 0,
        threshold_max: 0.5,
        warn_status: '1',
        update_time: '2025-11-03 15:30',
        ext1: '学籍管理表',
        trend_7days: JSON.stringify({
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
          return indicator.indicator_name.includes('入园率') || indicator.indicator_name.includes('巩固率');
        } else if (eduDomain === '师资类') {
          return indicator.indicator_name.includes('师生比') || indicator.indicator_name.includes('学历占比');
        } else if (eduDomain === '学业类') {
          return indicator.indicator_name.includes('辍学率');
        }
        return true;
      });
    }

    return baseIndicators;
  }
};

// 1.1.3 教育力量分布视图相关接口
export const fetchEduForceDistribution = async (params = {}) => {
  try {
    const response = await axios.get(`${BASE_URL}/forceDistribution`, { params });
    if (response.data && typeof response.data === 'object') {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('教育力量分布接口调用失败，使用模拟数据:', error.message);

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
      total_teacher_count: 5600,
      total_schoolbus_count: 120,
      total_school_count: 1256,
      online_teacher_count: 5200,
      active_schoolbus_count: 98
    };

    // 区域力量分布
    const regionForceDistribution = [
      { region_name: '高新区', teacher_count: 850, schoolbus_count: 25, school_count: 45 },
      { region_name: '经开区', teacher_count: 720, schoolbus_count: 22, school_count: 38 },
      { region_name: '城东区', teacher_count: 680, schoolbus_count: 20, school_count: 28 },
      { region_name: '城西区', teacher_count: 550, schoolbus_count: 18, school_count: 25 },
      { region_name: '城南区', teacher_count: 480, schoolbus_count: 15, school_count: 12 },
      { region_name: '城北区', teacher_count: 320, schoolbus_count: 10, school_count: 8 }
    ];

    return {
      geometries: baseGeometries,
      stats: stats,
      region_force_distribution: regionForceDistribution
    };
  }
};

// 1.1.4 教育服务总览相关接口
export const fetchServiceOverview = async (params = {}) => {
  try {
    const response = await axios.get(`${BASE_URL}/serviceOverview`, { params });
    if (response.data && typeof response.data === 'object') {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('教育服务总览接口调用失败，使用模拟数据:', error.message);

    const { serviceType = '', handleDept = '', timeRange = 'today' } = params;

    // 基础服务数据
    let baseServiceData = {
      // 服务总量统计
      total_service_count: 1256,
      new_service_today: 23,
      service_complete_rate: 92,
      overdue_service_count: 15,

      // 服务类型分布
      type_distribution: [
        { service_type: '教学服务', service_count: 456, color: '#3B82F6' },
        { service_type: '后勤服务', service_count: 342, color: '#10B981' },
        { service_type: '安全服务', service_count: 240, color: '#F59E0B' },
        { service_type: '信息化服务', service_count: 150, color: '#EF4444' },
        { service_type: '其他', service_count: 68, color: '#8B5CF6' }
      ],

      // 服务来源分布
      source_distribution: [
        { service_source: '学校上报', service_count: 623, color: '#3B82F6' },
        { service_source: '家长反馈', service_count: 432, color: '#10B981' },
        { service_source: '上级交办', service_count: 201, color: '#F59E0B' }
      ],
      // 区域服务分布 - 新增字段
      region_distribution: [
        { region_name: '高新区', service_count: 320 },
        { region_name: '经开区', service_count: 280 },
        { region_name: '城东区', service_count: 210 },
        { region_name: '城西区', service_count: 180 },
        { region_name: '城南区', service_count: 150 },
        { region_name: '城北区', service_count: 116 }
      ],
      // 服务办理进度
      progress_distribution: [
        { progress_stage: '受理', service_count: 156, color: '#3B82F6' },
        { progress_stage: '处理', service_count: 234, color: '#60A5FA' },
        { progress_stage: '审核', service_count: 345, color: '#F59E0B' },
        { progress_stage: '办结', service_count: 521, color: '#10B981' }
      ],

      // 超期服务列表
      overdue_services: [
        {
          service_id: 'S202411001',
          service_type: '教学服务',
          service_title: '教学设备维修申请',
          apply_time: '2025-10-25 09:15',
          due_time: '2025-10-30 17:00',
          overdue_days: 5,
          handle_dept: '教学设备科',
          current_progress: '处理'
        },
        {
          service_id: 'S202411023',
          service_type: '后勤服务',
          service_title: '食堂卫生整改',
          apply_time: '2025-10-28 14:30',
          due_time: '2025-11-02 17:00',
          overdue_days: 3,
          handle_dept: '后勤保障科',
          current_progress: '受理'
        },
        {
          service_id: 'S202411045',
          service_type: '安全服务',
          service_title: '校园安全隐患排查',
          apply_time: '2025-10-29 10:20',
          due_time: '2025-11-03 17:00',
          overdue_days: 2,
          handle_dept: '安全管理科',
          current_progress: '处理'
        }
      ],

      // 近期服务趋势
      recent_service_trend: [
        { date: '10-28', new_services: 18, complete_services: 15 },
        { date: '10-29', new_services: 22, complete_services: 18 },
        { date: '10-30', new_services: 15, complete_services: 20 },
        { date: '10-31', new_services: 25, complete_services: 22 },
        { date: '11-01', new_services: 20, complete_services: 19 },
        { date: '11-02', new_services: 24, complete_services: 21 },
        { date: '11-03', new_services: 23, complete_services: 20 }
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
        baseServiceData.type_distribution = baseServiceData.type_distribution.filter(
          item => item.service_type === targetType
        );

        // 调整总数
        const filteredCount = baseServiceData.type_distribution.reduce(
          (sum, item) => sum + item.service_count, 0
        );
        baseServiceData.total_service_count = filteredCount;
        baseServiceData.new_service_today = Math.floor(filteredCount * 0.02);
      }
    }

    // 根据办理部门筛选
    if (handleDept) {
      baseServiceData.overdue_services = baseServiceData.overdue_services.filter(
        item => item.handle_dept === handleDept
      );
      baseServiceData.overdue_service_count = baseServiceData.overdue_services.length;
    }

    // 根据时间周期调整数据
    if (timeRange === 'week') {
      baseServiceData = {
        ...baseServiceData,
        total_service_count: 2890,
        new_service_today: 156,
        service_complete_rate: 93,
        overdue_service_count: 32
      };
    } else if (timeRange === 'month') {
      baseServiceData = {
        ...baseServiceData,
        total_service_count: 8923,
        new_service_today: 523,
        service_complete_rate: 94,
        overdue_service_count: 28
      };
    }

    return baseServiceData;
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
      teacher_locations: [],
      schoolbus_locations: []
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

// 服务筛选接口
export const filterServices = async (filters) => {
  try {
    const response = await axios.post(`${BASE_URL}/services/filter`, filters);
    return response.data;
  } catch (error) {
    console.warn('服务筛选接口调用失败，使用模拟数据:', error.message);

    const mockServices = [
      {
        service_id: 'S202411001',
        service_type: '教学服务',
        service_title: '教学设备维修申请',
        apply_time: '2025-10-25 09:15',
        overdue_days: 5,
        current_progress: '处理',
        handle_dept: '教学设备科'
      },
      {
        service_id: 'S202411023',
        service_type: '后勤服务',
        service_title: '食堂卫生整改',
        apply_time: '2025-10-28 14:30',
        overdue_days: 3,
        current_progress: '受理',
        handle_dept: '后勤保障科'
      },
      {
        service_id: 'S202411045',
        service_type: '安全服务',
        service_title: '校园安全隐患排查',
        apply_time: '2025-10-29 10:20',
        overdue_days: 2,
        current_progress: '处理',
        handle_dept: '安全管理科'
      }
    ];

    let filteredServices = mockServices;
    if (filters.serviceType) {
      filteredServices = filteredServices.filter(s => s.service_type === filters.serviceType);
    }
    if (filters.handleDept) {
      filteredServices = filteredServices.filter(s => s.handle_dept === filters.handleDept);
    }

    return {
      total: filteredServices.length,
      services: filteredServices
    };
  }
};

// 获取服务详情 - 修复返回HTML问题
export const fetchServiceDetail = async (serviceId) => {
  try {
    const response = await axios.get(`${BASE_URL}/service/${serviceId}`);

    // 检查返回的是否是HTML
    if (typeof response.data === 'string' && response.data.includes('<!DOCTYPE html>')) {
      throw new Error('接口返回HTML页面，使用模拟数据');
    }

    // 检查返回数据格式
    if (response.data && typeof response.data === 'object' && response.data.service_id) {
      return response.data;
    }

    throw new Error('接口返回数据格式不正确，使用模拟数据');
  } catch (error) {
    console.warn('获取服务详情失败，使用模拟数据:', error.message);
    return getMockServiceDetail(serviceId);
  }
};

// 提取模拟数据到单独函数
const getMockServiceDetail = (serviceId) => {
  const mockDetails = {
    S202411001: {
      service_id: 'S202411001',
      service_title: '教学设备维修申请',
      service_type: '教学服务',
      service_source: '学校上报',
      priority: '高',
      status: '处理中',
      apply_time: '2025-10-25 09:15:00',
      due_time: '2025-10-30 17:00:00',
      current_progress: '处理',
      handle_dept: '教学设备科',
      handle_staff: '张技术员',
      service_description: '第一中学多媒体教室投影设备故障，影响正常教学秩序，急需维修。',
      location: {
        address: '第一中学教学楼3楼多媒体教室',
        coord: '26.855227, 117.650114',
        district: '高新区'
      },
      involved_parties: [
        { name: '李老师', type: '申请人', contact: '138****1234', identity: '任课教师' }
      ],
      evidence_materials: [
        { type: '照片', name: '设备故障照片1.jpg', time: '2025-10-25 09:20', uploader: '李老师' },
        { type: '照片', name: '设备故障照片2.jpg', time: '2025-10-25 09:25', uploader: '李老师' }
      ],
      progress_records: [
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
      next_actions: [
        { action: '更换投影仪灯泡', deadline: '2025-10-28', responsible: '张技术员', status: '进行中' }
      ],
      statistics: {
        handle_days: 3,
        overdue_days: 0,
        visit_times: 1,
        evidence_count: 2
      }
    },
    S202411023: {
      service_id: 'S202411023',
      service_title: '食堂卫生整改',
      service_type: '后勤服务',
      service_source: '家长反馈',
      priority: '中',
      status: '处理中',
      apply_time: '2025-10-28 14:30:00',
      due_time: '2025-11-02 17:00:00',
      current_progress: '受理',
      handle_dept: '后勤保障科',
      handle_staff: '李管理员',
      service_description: '家长反映实验小学校园食堂存在卫生问题，需要整改。',
      location: {
        address: '实验小学校园食堂',
        coord: '26.783227, 117.720114',
        district: '高新区'
      },
      involved_parties: [
        { name: '王家长', type: '反馈人', contact: '137****9012', identity: '学生家长' }
      ],
      evidence_materials: [
        { type: '照片', name: '食堂卫生照片.jpg', time: '2025-10-28 15:00', uploader: '王家长' }
      ],
      progress_records: [
        {
          time: '2025-10-28 14:45:00',
          action: '服务受理',
          operator: '系统自动',
          description: '服务已受理，分配至后勤保障科处理',
          attachments: []
        }
      ],
      next_actions: [
        { action: '现场核查卫生情况', deadline: '2025-10-30', responsible: '李管理员', status: '待完成' }
      ],
      statistics: {
        handle_days: 1,
        overdue_days: 0,
        visit_times: 0,
        evidence_count: 1
      }
    }
  };

  return mockDetails[serviceId] || {
    service_id: serviceId,
    service_title: `服务 ${serviceId}`,
    service_type: '其他',
    service_source: '未知',
    priority: '中',
    status: '处理中',
    apply_time: '2025-10-01 00:00:00',
    due_time: '2025-10-07 17:00:00',
    current_progress: '受理',
    handle_dept: '相关部门',
    handle_staff: '待分配',
    service_description: '服务详情正在整理中...',
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
        action: '服务创建',
        operator: '系统',
        description: '服务已创建，等待分配处理',
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
      title: '教育工作分析报告',
      stat_time: '2025-11-03',
      create_user: '教育指挥中心',
      content: '本周教育工作整体平稳，学校总数1256所，较上年增长2%；在园在校学生数85600人，较上年增长3%；专任教师数5600人，较上年增长4%；教育资源配置率85%，较上年提升2个百分点；教育服务办结率92%，较上年提升3个百分点。需重点关注高新区资源配置率问题和城西区超期服务过多问题。'
    };
  }
};

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
    socket = new WebSocket('ws://localhost:8080/ws/education-management');

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
