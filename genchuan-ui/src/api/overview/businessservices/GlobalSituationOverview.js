// 引入axios（假设项目中已配置axios）
import axios from 'axios';

// 基础URL，可根据实际项目配置
const BASE_URL = '/api/business';

// 营商全域数据概览相关接口
export const fetchBusinessGlobalOverview = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/globalOverview`);
    if (response.data && typeof response.data === 'object') {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('营商全域数据概览接口调用失败，使用模拟数据:', error.message);
    return {
      total_ent_count: 1256,
      daily_handle_count: 89,
      total_pol_amount: 28500000,
      appeal_resp_rate: 88,
      region_ent_distribution: [
        { region_name: '高新区', ent_count: 320 },
        { region_name: '经开区', ent_count: 280 },
        { region_name: '城东区', ent_count: 210 },
        { region_name: '城西区', ent_count: 180 },
        { region_name: '城南区', ent_count: 150 },
        { region_name: '城北区', ent_count: 116 }
      ],
      recent_7days_handle_trend: [
        { date: '10-28', handle_count: 75 },
        { date: '10-29', handle_count: 82 },
        { date: '10-30', handle_count: 68 },
        { date: '10-31', handle_count: 90 },
        { date: '11-01', handle_count: 79 },
        { date: '11-02', handle_count: 85 },
        { date: '11-03', handle_count: 89 }
      ],
      industry_distribution: [
        { industry_name: '制造业', count: 420 },
        { industry_name: '服务业', count: 310 },
        { industry_name: '信息技术', count: 240 },
        { industry_name: '金融业', count: 150 },
        { industry_name: '其他', count: 136 }
      ]
    };
  }
};

// 营商核心指标相关接口
export const fetchBusinessCoreIndicators = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/coreIndicators`);
    if (response.data && Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('营商核心指标接口调用失败，使用模拟数据:', error.message);
    return [
      {
        indicator_id: '1',
        indicator_name: '审批办结率',
        real_value: 82,
        unit: '%',
        compliance_rate: 82,
        year_on_year: -3,
        threshold_min: 85,
        threshold_max: 100,
        warn_status: '1',
        update_time: '2025-11-03 15:30',
        ext1: '政务服务中心',
        trend_7days: JSON.stringify({
          xAxis: ['10-30', '10-31', '11-01', '11-02', '11-03', '11-04', '11-05'],
          series: [85, 84, 83, 83, 82, 82, 82]
        })
      },
      {
        indicator_id: '2',
        indicator_name: '政策兑现率',
        real_value: 91,
        unit: '%',
        compliance_rate: 91,
        year_on_year: 2,
        threshold_min: 85,
        threshold_max: 100,
        warn_status: '0',
        update_time: '2025-11-03 15:30',
        ext1: '发改委',
        trend_7days: JSON.stringify({
          xAxis: ['10-30', '10-31', '11-01', '11-02', '11-03', '11-04', '11-05'],
          series: [89, 89, 90, 90, 91, 91, 91]
        })
      },
      {
        indicator_id: '3',
        indicator_name: '企业满意度',
        real_value: 95,
        unit: '分',
        compliance_rate: 95,
        year_on_year: 4,
        threshold_min: 80,
        threshold_max: 100,
        warn_status: '0',
        update_time: '2025-11-03 15:30',
        ext1: '市场监管局',
        trend_7days: JSON.stringify({
          xAxis: ['10-30', '10-31', '11-01', '11-02', '11-03', '11-04', '11-05'],
          series: [92, 93, 94, 94, 95, 95, 95]
        })
      },
      {
        indicator_id: '4',
        indicator_name: '诉求超期率',
        real_value: 3,
        unit: '%',
        compliance_rate: 97,
        year_on_year: -1,
        threshold_min: 0,
        threshold_max: 5,
        warn_status: '0',
        update_time: '2025-11-03 15:30',
        ext1: '信访局',
        trend_7days: JSON.stringify({
          xAxis: ['10-30', '10-31', '11-01', '11-02', '11-03', '11-04', '11-05'],
          series: [4, 4, 4, 3, 3, 3, 3]
        })
      }
    ];
  }
};

// 全域企业分布地图相关接口
export const fetchEnterpriseGeometries = async () => {
  try {
    // 调用真实接口（假设接口路径为/enterpriseGeometries）
    const response = await axios.get(`${BASE_URL}/enterpriseGeometries`);
    if (response.data && typeof response.data === 'object') {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('全域企业分布地图接口调用失败，使用模拟数据:', error.message);
    return [
      {
        coord_x: 26.855237, // 纬度
        coord_y: 117.777777, // 经度
        ent_id: "ENT001",
        ent_name: "东南重工集团",
        scale: "大型",
        industry: "装备制造",
        establish_time: "2005-03-12",
        address: "东城区工业园区A路8号"
      },
      {
        coord_x: 26.783237,
        coord_y: 117.720114,
        ent_id: "ENT002",
        ent_name: "恒信电子科技有限公司",
        scale: "中型",
        industry: "信息技术",
        establish_time: "2010-07-25",
        address: "南郊区科技园区B街15号"
      },
      {
        coord_x: 26.733337,
        coord_y: 117.650114,
        ent_id: "ENT003",
        ent_name: "惠民超市连锁",
        scale: "小型",
        industry: "零售业",
        establish_time: "2018-11-08",
        address: "老城区解放路22号"
      },
      {
        coord_x: 26.810237,
        coord_y: 117.800777,
        ent_id: "ENT004",
        ent_name: "江河物流集团",
        scale: "大型",
        industry: "交通运输",
        establish_time: "2008-05-19",
        address: "西城区物流园区C路36号"
      },
      {
        coord_x: 26.756237,
        coord_y: 117.712114,
        ent_id: "ENT005",
        ent_name: "绿源农产品加工场",
        scale: "中型",
        industry: "农产品加工",
        establish_time: "2015-09-03",
        address: "北城区农业园区D街7号"
      },
      {
        coord_x: 26.832237,
        coord_y: 117.689114,
        ent_id: "ENT006",
        ent_name: "启航广告工作室",
        scale: "小型",
        industry: "文化传媒",
        establish_time: "2020-02-28",
        address: "工业园区创意大厦1205室"
      }
    ];
  }
};

// 企业资源分布视图相关接口
export const fetchEnterpriseResourceDistribution = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/enterpriseDistribution`);
    if (response.data && typeof response.data === 'object') {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('企业资源分布接口调用失败，使用模拟数据:', error.message);
    return {
      key_enterprises: [
        {
          ent_id: '1001',
          ent_name: '科创信息技术有限公司',
          scale: '大型',
          location: '高新区',
          industry: '信息技术',
          register_time: '2018-05-12',
          recent_items: ['高新技术企业认定', '系统集成资质申请'],
          service_records: [
            {date: '2025-10-15', item: '软件著作权登记', status: '已办结'},
            {date: '2025-09-20', item: '企业名称变更', status: '已办结'}
          ]
        },
        {
          ent_id: '1002',
          ent_name: '恒通制造集团',
          scale: '大型',
          location: '经开区',
          industry: '制造业',
          register_time: '2015-03-20',
          recent_items: ['企业经营范围变更', '生产许可证续期'],
          service_records: [
            {date: '2025-10-10', item: '环保评估验收', status: '已办结'},
            {date: '2025-08-15', item: '特种设备登记', status: '已办结'}
          ]
        },
        {
          ent_id: '1003',
          ent_name: '鑫源金融服务公司',
          scale: '中型',
          location: '城东区',
          industry: '金融业',
          register_time: '2020-07-08',
          recent_items: ['金融许可证年检', '经营范围变更'],
          service_records: [
            {date: '2025-10-05', item: '税务登记证更新', status: '已办结'},
            {date: '2025-09-01', item: '开户许可证变更', status: '已办结'}
          ]
        },
        {
          ent_id: '1004',
          ent_name: '绿源环保科技公司',
          scale: '中型',
          location: '城西区',
          industry: '制造业',
          register_time: '2019-11-15',
          recent_items: ['环保资质认证', '排污许可证申请'],
          service_records: [
            {date: '2025-09-25', item: '环保设备备案', status: '已办结'},
            {date: '2025-08-20', item: '应急预案备案', status: '已办结'}
          ]
        },
        {
          ent_id: '1005',
          ent_name: '智慧城市服务集团',
          scale: '大型',
          location: '高新区',
          industry: '服务业',
          register_time: '2017-09-30',
          recent_items: ['服务资质升级', '项目备案'],
          service_records: [
            {date: '2025-10-18', item: '企业资质年审', status: '已办结'},
            {date: '2025-09-10', item: '从业人员认证', status: '已办结'}
          ]
        },
        {
          ent_id: '1006',
          ent_name: '瑞达物流有限公司',
          scale: '中型',
          location: '城南区',
          industry: '服务业',
          register_time: '2021-02-18',
          recent_items: ['进出口经营权办理', '道路运输许可证续期'],
          service_records: [
            {date: '2025-10-08', item: '车辆运营备案', status: '已办结'},
            {date: '2025-08-05', item: '税务减免申请', status: '已办结'}
          ]
        },
        {
          ent_id: '1007',
          ent_name: '蓝天能源科技公司',
          scale: '小型',
          location: '城北区',
          industry: '制造业',
          register_time: '2022-06-05',
          recent_items: ['小微企业扶持资金申请', '专利申请'],
          service_records: [
            {date: '2025-09-30', item: '营业执照更新', status: '已办结'},
            {date: '2025-07-15', item: '社保开户', status: '已办结'}
          ]
        },
        {
          ent_id: '1008',
          ent_name: '先锋软件股份公司',
          scale: '中型',
          location: '高新区',
          industry: '信息技术',
          register_time: '2016-12-10',
          recent_items: ['软件企业认定', '高新技术产品申报'],
          service_records: [
            {date: '2025-10-12', item: '著作权登记', status: '已办结'},
            {date: '2025-09-05', item: '研发费用加计扣除', status: '已办结'}
          ]
        }
      ],
      scale_distribution: [
        { scale: '大型', count: 156 },
        { scale: '中型', count: 420 },
        { scale: '小型', count: 680 }
      ]
    };
  }
};

// 服务事项总览相关接口
export const fetchServiceItemsOverview = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/serviceItems`);
    if (response.data && typeof response.data === 'object') {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('服务事项总览接口调用失败，使用模拟数据:', error.message);
    return {
      total_svc_count: 286,
      daily_pend_count: 24,
      svc_complete_rate: 92,
      overdue_svc_count: 7,
      // 区域审批率数据
      region_rate_data: [
        { region_name: '高新区', rate: 82 },
        { region_name: '经开区', rate: 88 },
        { region_name: '城东区', rate: 90 },
        { region_name: '城西区', rate: 87 },
        { region_name: '城南区', rate: 92 },
        { region_name: '城北区', rate: 89 }
      ],
      type_distribution: [
        { type_name: '企业注册', count: 86 },
        { type_name: '资质审批', count: 65 },
        { type_name: '政策申报', count: 58 },
        { type_name: '变更登记', count: 42 },
        { type_name: '其他事项', count: 35 }
      ],
      recent_24h_handle_trend: [
        { hour: '00时', handle_count: 5 },
        { hour: '03时', handle_count: 2 },
        { hour: '06时', handle_count: 1 },
        { hour: '09时', handle_count: 12 },
        { hour: '12时', handle_count: 8 },
        { hour: '15时', handle_count: 15 },
        { hour: '18时', handle_count: 9 },
        { hour: '21时', handle_count: 4 }
      ],
      pending_items: [
        { item_id: '2001', item_name: '高新技术企业认定', apply_ent: '科创信息技术有限公司', apply_time: '2025-11-02 09:15', due_time: '2025-11-05 17:00', status: 'pending' },
        { item_id: '2002', item_name: '企业经营范围变更', apply_ent: '恒通制造集团', apply_time: '2025-11-03 10:30', due_time: '2025-11-06 17:00', status: 'pending' },
        { item_id: '2003', item_name: '小微企业扶持资金申请', apply_ent: '蓝天能源科技公司', apply_time: '2025-11-01 14:20', due_time: '2025-11-04 17:00', status: 'overdue' },
        { item_id: '2004', item_name: '进出口经营权办理', apply_ent: '瑞达物流有限公司', apply_time: '2025-11-03 09:45', due_time: '2025-11-07 17:00', status: 'pending' },
        { item_id: '2005', item_name: '环保资质认证', apply_ent: '绿源环保科技公司', apply_time: '2025-10-30 11:20', due_time: '2025-11-02 17:00', status: 'overdue' },
        { item_id: '2006', item_name: '税务登记证办理', apply_ent: '先锋软件股份公司', apply_time: '2025-11-04 13:50', due_time: '2025-11-08 17:00', status: 'pending' },
        { item_id: '2007', item_name: '营业执照年检', apply_ent: '智慧城市服务集团', apply_time: '2025-11-01 09:20', due_time: '2025-11-03 17:00', status: 'overdue' },
        { item_id: '2008', item_name: '研发费用加计扣除申报', apply_ent: '科创信息技术有限公司', apply_time: '2025-11-05 10:10', due_time: '2025-11-10 17:00', status: 'pending' },
        { item_id: '2009', item_name: '特种设备使用登记', apply_ent: '恒通制造集团', apply_time: '2025-10-28 14:30', due_time: '2025-11-01 17:00', status: 'overdue' },
        { item_id: '2010', item_name: '社保开户办理', apply_ent: '蓝天能源科技公司', apply_time: '2025-11-04 16:40', due_time: '2025-11-09 17:00', status: 'pending' }
      ]
    };
  }
};

// 预警数据相关接口
export const fetchWarningScrollData = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/warningScrollData`);
    if (response.data && Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('预警滚动数据接口调用失败，使用模拟数据:', error.message);
    return [
      { region: '高新区', warning_type: '审批超期', time: '2025-11-03 10:20', status: '未处理' },
      { region: '城东区', warning_type: '诉求响应率低', time: '2025-11-03 09:15', status: '处理中' },
      { region: '经开区', warning_type: '政策兑现延迟', time: '2025-11-02 16:40', status: '已处理' },
      { region: '城西区', warning_type: '超期事项过多', time: '2025-11-02 14:25', status: '处理中' },
      { region: '城南区', warning_type: '企业投诉增加', time: '2025-11-01 11:30', status: '已处理' }
    ];
  }
};

export const fetchWarningTypes = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/warningTypes`);
    if (response.data && Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('预警类型数据接口调用失败，使用模拟数据:', error.message);
    return [
      { title: '审批办结率预警', content: '高新区审批办结率低于85%阈值，当前为82%' },
      { title: '超期事项预警', content: '城西区超期事项已达7项，超过阈值5项' },
      { title: '诉求响应预警', content: '城东区诉求响应率持续偏低，当前为87%' },
      { title: '政策兑现预警', content: '经开区有3项政策兑现超期未处理' }
    ];
  }
};

// 通用方法（所有修改/提交类接口均使用模拟数据返回成功）
export const exportData = async (dataType, params) => {
  // 模拟导出成功，不调用真实接口
  console.log(`模拟导出${dataType}数据，参数:`, params);
  return { success: true, message: '导出成功' };
};

export const updateIndicatorReason = async (params) => {
  // 模拟更新指标异常原因成功，不调用真实接口
  console.log('模拟更新指标异常原因，参数:', params);
  return { success: true };
};

export const updateIndicatorThreshold = async (params) => {
  // 模拟更新指标阈值成功，不调用真实接口
  console.log('模拟更新指标阈值，参数:', params);
  return { success: true };
};

export const handleWarning = async (params) => {
  // 模拟处理预警成功，不调用真实接口
  console.log('模拟处理预警，参数:', params);
  return { success: true };
};

export const submitServiceHandle = async (params) => {
  // 模拟提交服务事项处理结果成功，不调用真实接口
  console.log('模拟提交服务事项处理结果，参数:', params);
  return { success: true };
};

export const viewReport = async (reportId) => {
  try {
    const response = await axios.get(`${BASE_URL}/report/${reportId}`);
    return response.data;
  } catch (error) {
    console.warn('查看报告失败，使用模拟数据:', error.message);
    return {
      title: '营商环境分析报告',
      stat_time: '2025-11-03',
      create_user: '系统管理员',
      content: '本周营商环境整体良好，审批办结率82%，较上周下降3个百分点；政策兑现率91%，较上周上升2个百分点；企业满意度95分，保持较高水平；诉求超期率3%，在合理范围内。需重点关注高新区审批效率问题和城西区超期事项过多问题。'
    };
  }
};
