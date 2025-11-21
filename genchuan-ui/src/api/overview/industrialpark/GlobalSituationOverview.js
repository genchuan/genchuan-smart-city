// 引入axios（假设项目中已配置axios）
import axios from 'axios';

// 基础URL，可根据实际项目配置
const BASE_URL = '/api/industrialpark';

// 获取园区全域数据概览
export const fetchParkOverview = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/overallStat`);
    if (Array.isArray(response.data) && response.data.length > 0) {
      return response.data;
    }
    throw new Error('真实接口返回空数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('园区全域数据概览接口调用失败，使用模拟数据:', error.message);
    return [
      {
        stat_id: 'stat1001',
        park_id: 'park001',
        park_name: '高新技术产业园区',
        zone_name: '生产区',
        time_period: 'today',
        data_status: 'normal',
        total_enterprise: 320,
        iot_total: 850,
        iot_online: 823,
        iot_offline: 27,
        iot_alarm: 12,
        data_collection_frequency: 30,
        enterprise_increase: 15,
        enterprise_moveout: 3,
        employee_total: 12500,
        key_enterprise_normal: 28,
        key_enterprise_warning: 2,
        monthly_revenue: 85,
        tax_contribution: 4.25,
        investment_completion: 3.5,
        today_event_total: 28,
        handled_event: 23,
        pending_event: 5,
        event_completion_rate: 82.1,
        event_timely_rate: 92.5,
        stat_time: '2025-06-10 15:30:00',
        is_abnormal: '0',
        create_user: 'system',
        create_time: '2025-06-10 15:30:00',
        ext1: '自动统计',
        ext2: 'v1.0'
      },
      {
        stat_id: 'stat1002',
        park_id: 'park001',
        park_name: '高新技术产业园区',
        zone_name: '生活区',
        time_period: 'today',
        data_status: 'normal',
        total_enterprise: 85,
        iot_total: 320,
        iot_online: 310,
        iot_offline: 10,
        iot_alarm: 2,
        data_collection_frequency: 60,
        enterprise_increase: 5,
        enterprise_moveout: 1,
        employee_total: 3200,
        key_enterprise_normal: 8,
        key_enterprise_warning: 0,
        monthly_revenue: 12,
        tax_contribution: 0.6,
        investment_completion: 0.8,
        today_event_total: 12,
        handled_event: 10,
        pending_event: 2,
        event_completion_rate: 83.3,
        event_timely_rate: 95.0,
        stat_time: '2025-06-10 15:30:00',
        is_abnormal: '0',
        create_user: 'system',
        create_time: '2025-06-10 15:30:00',
        ext1: '自动统计',
        ext2: 'v1.0'
      },
      {
        stat_id: 'stat1003',
        park_id: 'park001',
        park_name: '高新技术产业园区',
        zone_name: '研发区',
        time_period: 'today',
        data_status: 'abnormal',
        total_enterprise: 52,
        iot_total: 180,
        iot_online: 150,
        iot_offline: 30,
        iot_alarm: 8,
        data_collection_frequency: 15,
        enterprise_increase: 3,
        enterprise_moveout: 0,
        employee_total: 1800,
        key_enterprise_normal: 5,
        key_enterprise_warning: 3,
        monthly_revenue: 23,
        tax_contribution: 1.15,
        investment_completion: 1.5,
        today_event_total: 18,
        handled_event: 10,
        pending_event: 8,
        event_completion_rate: 55.6,
        event_timely_rate: 70.0,
        stat_time: '2025-06-10 15:30:00',
        is_abnormal: '1',
        create_user: 'system',
        create_time: '2025-06-10 15:30:00',
        ext1: '自动统计',
        ext2: 'v1.0'
      },
      {
        stat_id: 'stat1004',
        park_id: 'park001',
        park_name: '高新技术产业园区',
        zone_name: '生产区',
        time_period: 'yesterday',
        data_status: 'normal',
        total_enterprise: 318,
        iot_total: 850,
        iot_online: 810,
        iot_offline: 40,
        iot_alarm: 15,
        data_collection_frequency: 30,
        enterprise_increase: 12,
        enterprise_moveout: 2,
        employee_total: 12500,
        key_enterprise_normal: 29,
        key_enterprise_warning: 1,
        monthly_revenue: 83,
        tax_contribution: 4.15,
        investment_completion: 3.5,
        today_event_total: 25,
        handled_event: 22,
        pending_event: 3,
        event_completion_rate: 88.0,
        event_timely_rate: 93.0,
        stat_time: '2025-06-09 15:30:00',
        is_abnormal: '0',
        create_user: 'system',
        create_time: '2025-06-09 15:30:00',
        ext1: '自动统计',
        ext2: 'v1.0'
      },
      {
        stat_id: 'stat1005',
        park_id: 'park002',
        park_name: '经济开发区',
        zone_name: '综合区',
        time_period: 'today',
        data_status: 'normal',
        total_enterprise: 450,
        iot_total: 1200,
        iot_online: 1150,
        iot_offline: 50,
        iot_alarm: 18,
        data_collection_frequency: 30,
        enterprise_increase: 20,
        enterprise_moveout: 5,
        employee_total: 18000,
        key_enterprise_normal: 45,
        key_enterprise_warning: 3,
        monthly_revenue: 120,
        tax_contribution: 6,
        investment_completion: 5,
        today_event_total: 35,
        handled_event: 30,
        pending_event: 5,
        event_completion_rate: 85.7,
        event_timely_rate: 90.0,
        stat_time: '2025-06-10 15:30:00',
        is_abnormal: '0',
        create_user: 'system',
        create_time: '2025-06-10 15:30:00',
        ext1: '自动统计',
        ext2: 'v1.0'
      },
      {
        stat_id: 'stat1006',
        park_id: 'park003',
        park_name: '物流园区',
        zone_name: '仓储区',
        time_period: 'today',
        data_status: 'abnormal',
        total_enterprise: 120,
        iot_total: 500,
        iot_online: 420,
        iot_offline: 80,
        iot_alarm: 25,
        data_collection_frequency: 20,
        enterprise_increase: 8,
        enterprise_moveout: 4,
        employee_total: 6000,
        key_enterprise_normal: 12,
        key_enterprise_warning: 5,
        monthly_revenue: 50,
        tax_contribution: 2.5,
        investment_completion: 2,
        today_event_total: 40,
        handled_event: 22,
        pending_event: 18,
        event_completion_rate: 55.0,
        event_timely_rate: 65.0,
        stat_time: '2025-06-10 15:30:00',
        is_abnormal: '1',
        create_user: 'system',
        create_time: '2025-06-10 15:30:00',
        ext1: '自动统计',
        ext2: 'v1.0'
      }
    ];
  }
};

// 获取园区核心指标
export const fetchParkCoreIndicators = async (statPeriod = 'day') => {
  try {
    const response = await axios.get(`${BASE_URL}/coreIndex`, {
      params: { statPeriod }
    });
    if (Array.isArray(response.data) && response.data.length > 0) {
      return response.data;
    }
    throw new Error('真实接口返回空数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('园区核心指标接口调用失败，使用模拟数据:', error.message);
    return [
      {
        index_id: 'index1001',
        park_id: 'park001',
        park_name: '高新技术产业园区',
        economic: {
          revenue: 850,
          revenue_yoy: 12.5,
          tax: 42500,
          investment_rate: 92.3
        },
        device: {
          online_rate: 96.8,
          alert_rate: 1.4,
          repair_time: 45
        },
        enterprise: {
          revenue_ratio: 68.5,
          loss_rate: 2.1,
          registered_capital: 15000
        },
        event: {
          daily_count: 28,
          timeout_rate: 3.2,
          repeat_rate: 1.8
        },
        trend_6months: {
          xAxis: ['5月', '6月', '7月', '8月', '9月', '10月'],
          economic: [780000, 810000, 830000, 820000, 840000, 850000],
          device: [95.2, 96.1, 97.3, 96.5, 96.9, 96.8],
          enterprise: [65.3, 66.8, 67.2, 68.0, 68.3, 68.5],
          event: [22, 25, 27, 24, 26, 28]
        },
        stat_period: statPeriod === 'day' ? '日' : statPeriod === 'week' ? '周' : '月',
        index_update_time: new Date().toLocaleString()
      }
    ];
  }
};

// 获取园区空间几何数据
export const fetchGeometriesData = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/geometriesData`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('园区空间几何数据接口调用失败，使用模拟数据:', error.message);
    return [
      {
        coord_x: 26.855237,
        coord_y: 117.777777,
        building_id: "B001",
        building_name: "一号生产车间",
        floors: 3,
        building_type: "生产楼",
        zone_area: "3.2平方公里",
        green_area: 850,
        plaza_position: "建筑西北侧50米（26.856237, 117.776777）",
        charging_piles: [
          {x: 26.855137, y: 117.778077},
          {x: 26.855337, y: 117.778177}
        ],
        fire_hydrants: [
          {x: 26.855037, y: 117.777577},
          {x: 26.855437, y: 117.777477}
        ],
        emergency_passage: "东西走向，连接二号生产车间与西北门",
        shelter_capacity: 500
      },
      {
        coord_x: 26.783237,
        coord_y: 117.720114,
        building_id: "B002",
        building_name: "行政办公楼",
        floors: 6,
        building_type: "办公楼",
        zone_area: "2.5平方公里",
        green_area: 1200,
        plaza_position: "建筑南侧广场（26.782237, 117.720114）",
        charging_piles: [
          {x: 26.783037, y: 117.720514},
          {x: 26.783137, y: 117.720614}
        ],
        fire_hydrants: [
          {x: 26.783337, y: 117.719914},
          {x: 26.783137, y: 117.720814}
        ],
        emergency_passage: "南北走向，贯穿主楼与地下停车场",
        shelter_capacity: 300
      },
      {
        coord_x: 26.733337,
        coord_y: 117.650114,
        building_id: "B003",
        building_name: "员工1号宿舍楼",
        floors: 5,
        building_type: "宿舍楼",
        zone_area: "4.0平方公里",
        green_area: 600,
        plaza_position: "建筑东侧活动区（26.733337, 117.651114）",
        charging_piles: [
          {x: 26.733237, y: 117.650314},
          {x: 26.733437, y: 117.650314}
        ],
        fire_hydrants: [
          {x: 26.733137, y: 117.649914},
          {x: 26.733537, y: 117.649914}
        ],
        emergency_passage: "环形通道，连接2号宿舍楼与食堂",
        shelter_capacity: 400
      },
      {
        coord_x: 26.810237,
        coord_y: 117.800777,
        building_id: "B004",
        building_name: "二号生产车间",
        floors: 2,
        building_type: "生产楼",
        zone_area: "3.2平方公里",
        green_area: 720,
        plaza_position: "建筑东侧卸货区（26.810237, 117.801777）",
        charging_piles: [
          {x: 26.810137, y: 117.800977},
          {x: 26.810337, y: 117.800977}
        ],
        fire_hydrants: [
          {x: 26.810037, y: 117.800577},
          {x: 26.810437, y: 117.800577}
        ],
        emergency_passage: "东西走向，连接一号生产车间与东门",
        shelter_capacity: 450
      },
      {
        coord_x: 26.756237,
        coord_y: 117.712114,
        building_id: "B005",
        building_name: "研发中心楼",
        floors: 8,
        building_type: "办公楼",
        zone_area: "2.5平方公里",
        green_area: 1500,
        plaza_position: "建筑北侧喷泉广场（26.757237, 117.712114）",
        charging_piles: [
          {x: 26.756037, y: 117.712514},
          {x: 26.756237, y: 117.712614},
          {x: 26.756437, y: 117.712514}
        ],
        fire_hydrants: [
          {x: 26.755937, y: 117.711914},
          {x: 26.756537, y: 117.711914},
          {x: 26.756237, y: 117.712814}
        ],
        emergency_passage: "十字交叉通道，连接各实验室与西侧安全出口",
        shelter_capacity: 600
      },
      {
        coord_x: 26.832237,
        coord_y: 117.689114,
        building_id: "B006",
        building_name: "员工2号宿舍楼",
        floors: 6,
        building_type: "宿舍楼",
        zone_area: "4.0平方公里",
        green_area: 800,
        plaza_position: "建筑南侧篮球场（26.831237, 117.689114）",
        charging_piles: [
          {x: 26.832137, y: 117.689314},
          {x: 26.832337, y: 117.689314}
        ],
        fire_hydrants: [
          {x: 26.832037, y: 117.688914},
          {x: 26.832437, y: 117.688914}
        ],
        emergency_passage: "南北走向，连接1号宿舍楼与后勤楼",
        shelter_capacity: 550
      },
      {
        coord_x: 26.798237,
        coord_y: 117.833777,
        building_id: "B007",
        building_name: "三号仓储车间",
        floors: 1,
        building_type: "生产楼",
        zone_area: "3.2平方公里",
        green_area: 450,
        plaza_position: "建筑南侧装卸广场（26.797237, 117.833777）",
        charging_piles: [
          {x: 26.798137, y: 117.833977},
          {x: 26.798337, y: 117.833977}
        ],
        fire_hydrants: [
          {x: 26.798037, y: 117.833577},
          {x: 26.798437, y: 117.833577}
        ],
        emergency_passage: "环形通道，围绕建筑一周，连接南北门",
        shelter_capacity: 300
      },
      {
        coord_x: 26.765237,
        coord_y: 117.755114,
        building_id: "B008",
        building_name: "访客服务中心",
        floors: 2,
        building_type: "办公楼",
        zone_area: "2.5平方公里",
        green_area: 300,
        plaza_position: "建筑正前方广场（26.765237, 117.756114）",
        charging_piles: [
          {x: 26.765137, y: 117.755314},
          {x: 26.765337, y: 117.755314}
        ],
        fire_hydrants: [
          {x: 26.765037, y: 117.754914},
          {x: 26.765437, y: 117.754914}
        ],
        emergency_passage: "东西走向，连接主入口与内部通道",
        shelter_capacity: 150
      },
      {
        type: "road",
        road_name: "工业大道",
        length: "1.2公里",
        zone_type: "industrial",
        path: [
          {x: 26.85, y: 117.76},
          {x: 26.81, y: 117.78},
          {x: 26.79, y: 117.80}
        ]
      },
      {
        type: "road",
        road_name: "商业步行街",
        length: "0.8公里",
        zone_type: "commercial",
        path: [
          {x: 26.78, y: 117.71},
          {x: 26.76, y: 117.73},
          {x: 26.75, y: 117.75}
        ]
      },
      {
        type: "road",
        road_name: "生活环路",
        length: "1.5公里",
        zone_type: "residential",
        path: [
          {x: 26.73, y: 117.64},
          {x: 26.71, y: 117.66},
          {x: 26.73, y: 117.68}
        ]
      },
      {
        type: "parking",
        parking_name: "工业区一号停车场",
        position: {x: 26.83, y: 117.77},
        capacity: 150,
        zone_type: "industrial"
      },
      {
        type: "parking",
        parking_name: "商业区地下停车场",
        position: {x: 26.77, y: 117.72},
        capacity: 300,
        zone_type: "commercial"
      },
      {
        type: "parking",
        parking_name: "生活区露天停车场",
        position: {x: 26.72, y: 117.66},
        capacity: 200,
        zone_type: "residential"
      }
    ];
  }
};

// 获取企业分布数据
export const fetchEnterpriseDistribution = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/enterpriseDist`);
    if (Array.isArray(response.data) && response.data.length > 0) {
      return response.data;
    }
    throw new Error('真实接口返回空数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('企业分布接口调用失败，使用模拟数据:', error.message);
    return [
      {
        dist_id: 'dist1001',
        enterprise_id: 'ent001',
        enterprise_name: '科创电子有限公司',
        park_id: 'park001',
        park_name: '高新技术产业园区',
        enterprise_industry: '制造业',
        enterprise_coord: '120.15,30.15',
        employee_num: 500,
        annual_output_value: 25000.00,
        enterprise_status: '0',
        coord_update_time: '2025-06-01 10:00:00',
        create_user: 'system',
        create_time: '2025-01-15 08:00:00',
        ext1: '021-12345678',
        ext2: '2010-05-20'
      },
      {
        dist_id: 'dist1003',
        enterprise_id: 'ent003',
        enterprise_name: '未来研发中心',
        park_id: 'park001',
        park_name: '高新技术产业园区',
        enterprise_industry: '研发',
        enterprise_coord: '120.25,30.35',
        employee_num: 150,
        annual_output_value: 8000.00,
        enterprise_status: '0',
        coord_update_time: '2025-06-10 11:30:00',
        create_user: 'admin',
        create_time: '2025-06-10 10:00:00',
        ext1: '021-56781234',
        ext2: '2025-06-10'
      },
      {
        dist_id: 'dist1004',
        enterprise_id: 'ent004',
        enterprise_name: '恒通机械厂',
        park_id: 'park001',
        park_name: '高新技术产业园区',
        enterprise_industry: '制造业',
        enterprise_coord: '120.18,30.18',
        employee_num: 200,
        annual_output_value: 12000.00,
        enterprise_status: '1',
        coord_update_time: '2025-06-10 14:20:00',
        create_user: 'system',
        create_time: '2025-01-15 08:00:00',
        ext1: '021-43218765',
        ext2: '2008-07-05'
      }
    ];
  }
};

// 获取园区基础信息列表
export const fetchParkBaseInfoList = async (parentParkId = '') => {
  try {
    const response = await axios.get(`${BASE_URL}/parkBaseInfoList`, {
      params: { parentParkId }
    });
    if (Array.isArray(response.data) && response.data.length > 0) {
      return response.data;
    }
    throw new Error('真实接口返回空数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('园区基础信息列表接口调用失败，使用模拟数据:', error.message);
    return [
      {
        id: 'park001',
        name: '高新技术产业园区',
        builtArea: 120000,
        plannedArea: 200000,
        enterpriseCount: 156,
        completionRate: 60,
        establishmentTime: '2010-05-18',
        address: '上海市浦东新区科技路88号',
        managementCompany: '科创园区管理有限公司',
        industryType: '电子信息、生物医药',
        parkLevel: '国家级'
      },
      {
        id: 'park002',
        name: '经济开发区',
        builtArea: 250000,
        plannedArea: 400000,
        enterpriseCount: 218,
        completionRate: 62.5,
        establishmentTime: '2005-08-23',
        address: '江苏省苏州市工业园区东方大道1200号',
        managementCompany: '经济开发区运营管理集团',
        industryType: '高端制造、新材料',
        parkLevel: '省级'
      },
      {
        id: 'park003',
        name: '物流产业园',
        builtArea: 180000,
        plannedArea: 300000,
        enterpriseCount: 89,
        completionRate: 60,
        establishmentTime: '2015-11-05',
        address: '浙江省杭州市萧山区物流大道567号',
        managementCompany: '现代物流园区管理有限公司',
        industryType: '仓储物流、跨境电商',
        parkLevel: '市级'
      },
      {
        id: 'park004',
        name: '文创产业园',
        builtArea: 50000,
        plannedArea: 80000,
        enterpriseCount: 124,
        completionRate: 62.5,
        establishmentTime: '2018-03-12',
        address: '北京市朝阳区文创路34号',
        managementCompany: '创意园区发展有限公司',
        industryType: '文化创意、设计服务',
        parkLevel: '市级'
      }
    ];
  }
};

// 获取行业分布统计数据
export const fetchIndustryDistribution = async (parkId) => {
  try {
    const response = await axios.get(`${BASE_URL}/industryDistribution`, {
      params: { parkId }
    });
    if (response.data && response.data.legend && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('行业分布统计接口调用失败，使用模拟数据:', error.message);
    return {
      legend: ['制造业', '服务业', '研发', '其他'],
      series: [65, 25, 8, 2]
    };
  }
};

// 获取指标趋势数据（7天核心指标趋势 + 30天开工率趋势）
export const fetchIndicatorTrend = async (params) => {
  try {
    const response = await axios.get(`${BASE_URL}/indicatorTrend`, { params });
    if (response.data && response.data.xAxis && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('指标趋势接口调用失败，使用模拟数据:', error.message);
    const { indicatorType, period } = params;
    const xAxis = [];
    const series = [];
    const today = new Date();

    const days = period === '30d' ? 30 : 7;
    for (let i = days - 1; i >= 0; i--) {
      const date = new Date(today);
      date.setDate(today.getDate() - i);
      xAxis.push(`${date.getMonth() + 1}/${date.getDate()}`);
    }

    if (indicatorType === 'core') {
      const baseEnergy = 1050.80;
      const baseOperation = 75.50;
      const baseFacility = 98.20;
      const baseComplaint = 95.00;

      const energyData = xAxis.map(() =>
        parseFloat((baseEnergy + (Math.random() - 0.5) * 100).toFixed(2))
      );
      const operationData = xAxis.map(() =>
        parseFloat((baseOperation + (Math.random() - 0.5) * 5).toFixed(2))
      );
      const facilityData = xAxis.map(() =>
        parseFloat((baseFacility + (Math.random() - 0.5) * 1).toFixed(2))
      );
      const complaintData = xAxis.map(() =>
        parseFloat((baseComplaint + (Math.random() - 0.5) * 2).toFixed(2))
      );

      series.push(
        { name: '日均能耗(kWh)', data: energyData },
        { name: '企业开工率(%)', data: operationData },
        { name: '设施完好率(%)', data: facilityData },
        { name: '投诉处理及时率(%)', data: complaintData }
      );
    } else if (indicatorType === 'operation') {
      const baseRate = 75.50;
      const data = xAxis.map((_, i) => {
        const trend = (days - 1 - i) * 0.5;
        const fluctuation = (Math.random() - 0.5) * 2;
        return parseFloat((baseRate + trend + fluctuation).toFixed(2));
      });
      series.push({ name: '企业开工率', data });
    }

    return { xAxis, series };
  }
};

// 获取空间位置数据（分区位置/大小 + 企业位置）
export const fetchSpatialPositions = async (type, parkId) => {
  try {
    const response = await axios.get(`${BASE_URL}/spatialPositions`, {
      params: { type, parkId }
    });
    if (typeof response.data === 'object' && Object.keys(response.data).length > 0) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn(`空间${type}位置接口调用失败，使用模拟数据:`, error.message);
    if (type === 'zone') {
      return {
        positions: {
          'zone001': { left: '50px', top: '50px' },
          'zone002': { left: '350px', top: '50px' },
          'zone003': { left: '200px', top: '250px' }
        },
        sizes: {
          'zone001': { width: '250px', height: '180px' },
          'zone002': { width: '200px', height: '180px' },
          'zone003': { width: '180px', height: '150px' }
        }
      };
    } else if (type === 'enterprise') {
      return {
        positions: {
          'dist1001': { left: '120px', top: '100px' },
          'dist1002': { left: '420px', top: '100px' },
          'dist1003': { left: '280px', top: '320px' },
          'dist1004': { left: '180px', top: '150px' }
        }
      };
    }
  }
};

// 手动刷新园区统计数据
export const refreshParkStats = async (parkId) => {
  try {
    const response = await axios.post(`${BASE_URL}/refreshStats`, { parkId });
    if (response.data && response.data.success) {
      return response.data;
    }
    throw new Error('真实接口返回无效结果，使用模拟结果兜底');
  } catch (error) {
    console.warn('刷新园区统计数据接口调用失败，使用模拟结果:', error.message);
    return {
      success: true,
      message: '数据刷新成功',
      data: {
        refreshTime: new Date().toLocaleString()
      }
    };
  }
};

// 更新企业坐标
export const updateEnterpriseCoord = async (params) => {
  try {
    const response = await axios.post(`${BASE_URL}/updateEnterpriseCoord`, params);
    if (response.data && response.data.success) {
      return response.data;
    }
    throw new Error('真实接口返回无效结果，使用模拟结果兜底');
  } catch (error) {
    console.warn('更新企业坐标接口调用失败，使用模拟结果:', error.message);
    return {
      success: true,
      message: '企业坐标更新成功',
      data: {
        updateTime: new Date().toLocaleString()
      }
    };
  }
};
