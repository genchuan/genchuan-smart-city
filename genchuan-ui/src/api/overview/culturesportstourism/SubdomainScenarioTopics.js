// import request from '@/config/axios';
import request from 'axios';
const BASE_URL = '/industry';


// 旅游景区接口
// 旅游景区概览数据（核心指标）
export const fetchScenicSpotOverview = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/overview`);
    if (response.data && typeof response.data === 'object') {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('旅游景区概览接口调用失败，使用模拟数据:', error.message);
    return {
      total_scene_count: 32,          // 景区总数（个）
      current_count: 85600,           // 实时客流（人）
      capacity: 120000,              // 总承载量（人）
      flow_saturation: 71.3,         // 客流饱和度（%）
      level1_count: 18,               // 安全事件数（起）
      normal_comp_count: 285,         // 正常设施数（个）
      total_comp_count: 300,          // 总设施数（个）
      facility_rate: 95.0,            // 设施完好率（%）
      facility_rate_yoy: 2.5,         // 设施完好率同比（%）
      facility_rate_mom: 1.8,         // 设施完好率环比（%）
      highRiskScene: '南山景区、滨海浴场、古城核心区', // 高风险景区
      lastMonitorTime: '2025-11-25 14:30'              // 最近监测时间
    };
  }
};

// 景区客流趋势数据（30分钟粒度，近24小时）
export const fetchScenicSpotFlowTrend = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/flowTrend`);
    if (response.data && response.data.xAxis && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('景区客流趋势接口调用失败，使用模拟数据:', error.message);
    // 生成近24小时30分钟粒度的时间轴（如 12:00、12:30、13:00...）
    const get30MinTimeAxis = () => {
      const axis = [];
      const now = new Date();
      for (let i = 47; i >= 0; i--) { // 48个30分钟片段
        const time = new Date(now.getTime() - i * 30 * 60 * 1000);
        const hour = String(time.getHours()).padStart(2, '0');
        const minute = time.getMinutes() === 0 ? '00' : '30';
        axis.push(`${hour}:${minute}`);
      }
      return axis;
    };
    const timeAxis = get30MinTimeAxis();
    return {
      xAxis: timeAxis,
      series: [
        { name: '实时客流（千人）', data: timeAxis.map(() => Math.floor(60 + Math.random() * 30)) }
      ]
    };
  }
};

// 安全事件列表数据
export const fetchSafetyEventList = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/safetyEventList`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('安全事件列表接口调用失败，使用模拟数据:', error.message);
    return Array(8).fill(0).map((_, index) => ({
      event_id: `SE-${String(index + 100).slice(1)}`,
      matter_name: `${['南山景区', '滨海浴场', '古城核心区', '温泉度假区', '森林公园'][index % 5]}${['游客走失', '设施损坏', '人流拥挤', '滑倒摔伤', '突发疾病'][index % 5]}事件`,
      scene_name: ['南山景区', '滨海浴场', '古城核心区', '温泉度假区', '森林公园'][index % 5],
      level: index % 3 === 0 ? 'high' : index % 3 === 1 ? 'medium' : 'low',
      occur_time: `2025-11-25 ${10 + (index % 4)}:${index % 2 === 0 ? '00' : '30'}`,
      handle_status: index % 3 === 0 ? 'handled' : index % 3 === 1 ? 'processing' : 'pending'
    }));
  }
};

// 投诉列表数据
export const fetchComplaintList = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/complaintList`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('投诉列表接口调用失败，使用模拟数据:', error.message);
    return Array(8).fill(0).map((_, index) => ({
      complaint_id: `CO-${String(index + 100).slice(1)}`,
      matter_name: `${['门票价格', '设施服务', '环境卫生', '导览指引', '餐饮质量'][index % 5]}投诉`,
      scene_name: ['南山景区', '滨海浴场', '古城核心区', '温泉度假区', '森林公园'][index % 5],
      complaint_type: ['服务类', '设施类', '环境类', '价格类'][index % 4],
      create_time: `2025-11-25 ${9 + (index % 5)}:${index % 2 === 0 ? '00' : '30'}`,
      handle_status: index % 3 === 0 ? 'handled' : index % 3 === 1 ? 'processing' : 'pending',
      handler: index % 3 === 0 ? ['张管理员', '李客服', '王主管'][index % 3] : ''
    }));
  }
};

// TOP5景区数据（客流TOP5）
export const fetchTop5ScenicSpot = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/top5ScenicSpot`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('TOP5景区接口调用失败，使用模拟数据:', error.message);
    return [
      { rank: 1, scene_name: '南山景区', current_count: 21500, saturation: 78.2 },
      { rank: 2, scene_name: '滨海浴场', current_count: 18600, saturation: 72.3 },
      { rank: 3, scene_name: '古城核心区', current_count: 15800, saturation: 85.6 },
      { rank: 4, scene_name: '温泉度假区', current_count: 14200, saturation: 65.8 },
      { rank: 5, scene_name: '森林公园', current_count: 10500, saturation: 58.9 }
    ];
  }
};


// 文旅活动接口
// 文旅活动概览数据（核心指标）
export const fetchCulturalTourismOverview = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/overview`);
    if (response.data && typeof response.data === 'object') {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('文旅活动概览接口调用失败，使用模拟数据:', error.message);
    return {
      total_scene_count: 86,          // 活动总数（场）
      total_rpt_count: 125800,        // 参与总人数（人）
      current_count: 15680,           // 实时参与人数（人）
      abn_comp_count: 12,             // 安全事件数（起）
      good_count: 9260,               // 好评数
      total_count: 10500,             // 总舆情数
      praise_rate: 88.2,              // 舆情好评率（%）
      praise_rate_yoy: 15.6,          // 好评率同比（%）
      praise_rate_mom: 8.3,           // 好评率环比（%）
      high_risk_scene: '古城核心展区、滨海演艺广场、高山赛事区', // 高风险场景
      last_monitor_time: '2025-11-25 15:30' // 最近监测时间
    };
  }
};

// 文旅活动趋势数据（近14天：活动数+参与人数）
export const fetchCulturalTourismTrend = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/trend`);
    if (response.data && response.data.xAxis && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('文旅活动趋势接口调用失败，使用模拟数据:', error.message);
    // 生成近14天日期（MM-DD格式）
    const getRecent14Days = () => {
      const days = [];
      const today = new Date();
      for (let i = 13; i >= 0; i--) {
        const date = new Date(today);
        date.setDate(today.getDate() - i);
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        days.push(`${month}-${day}`);
      }
      return days;
    };
    const recent14Days = getRecent14Days();
    return {
      xAxis: recent14Days,
      series: [
        { name: '活动数（场）', data: [5, 6, 4, 7, 8, 6, 9, 7, 8, 10, 9, 8, 11, 10] },
        { name: '参与人数（千人）', data: [8.2, 9.5, 7.8, 10.1, 11.5, 9.8, 12.0, 10.5, 11.8, 13.2, 12.5, 11.9, 14.0, 13.5] }
      ]
    };
  }
};

// 活动类型分布数据（展览/演出/赛事）
export const fetchActivityTypeDistribution = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/typeDistribution`);
    if (response.data && response.data.legend && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('活动类型分布接口调用失败，使用模拟数据:', error.message);
    return {
      legend: ['展览', '演出', '赛事'],
      series: [{
        name: '活动数（场）',
        data: [32, 40, 14]
      }]
    };
  }
};

// 安全事件分布数据（饼图）
export const fetchSafetyEventDistribution = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/safetyEventDistribution`);
    if (response.data && Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('安全事件分布接口调用失败，使用模拟数据:', error.message);
    return [
      { name: '人流拥挤', value: 5, color: '#ffd100' },
      { name: '设施故障', value: 3, color: '#ff7d00' },
      { name: '游客走失', value: 2, color: '#13ce66' },
      { name: '突发疾病', value: 1, color: '#ff4949' },
      { name: '其他', value: 1, color: '#999999' }
    ];
  }
};

// 文旅活动列表数据（基础信息）
export const fetchActivityList = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/activityList`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('文旅活动列表接口调用失败，使用模拟数据:', error.message);
    return [
      {
        scene_id: 'CA-001',
        app_scene_name: '古城非遗文化展演', // 活动名称
        minor_name: '演出',                 // 活动类型（展览/演出/赛事）
        scene_status: 'ongoing',            // 活动状态（筹备/进行中/结束）
        stat_cycle: '今日',                 // 时间周期（今日/本周）
        create_time: '2025-11-25 09:00',
        location: '古城核心景区',
        expected_people: 2000,
        actual_people: 2580,
        safety_event: '0'
      },
      {
        scene_id: 'CA-002',
        app_scene_name: '滨海艺术展览',
        minor_name: '展览',
        scene_status: 'pending',
        stat_cycle: '本周',
        create_time: '2025-11-24 10:00',
        location: '滨海文化中心',
        expected_people: 1500,
        actual_people: 0,
        safety_event: '0'
      },
      {
        scene_id: 'CA-003',
        app_scene_name: '高山户外挑战赛',
        minor_name: '赛事',
        scene_status: 'completed',
        stat_cycle: '今日',
        create_time: '2025-11-25 09:00',
        location: '高山体育公园',
        expected_people: 800,
        actual_people: 920,
        safety_event: '1'
      }
    ];
  }
};

// 文旅活动详细监测数据（表格用）
export const fetchActivityDetailData = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/activityDetailData`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('文旅活动详细数据接口调用失败，使用模拟数据:', error.message);
    return Array(15).fill(0).map((_, index) => ({
      scene_id: `CA-${String(index + 100).slice(1)}`,
      app_scene_name: `${['古城', '滨海', '高山', '温泉', '花海'][index % 5]}${['文化展', '演艺秀', '挑战赛', '民俗展', '音乐节'][index % 5]}活动`,
      minor_name: ['展览', '演出', '赛事'][index % 3], // 活动类型
      scene_status: index % 3 === 0 ? 'ongoing' : index % 3 === 1 ? 'completed' : 'pending', // 活动状态
      stat_cycle: index % 2 === 0 ? '今日' : '本周', // 时间周期
      location: `${['古城核心景区', '滨海文化中心', '高山体育公园', '温泉度假区', '花海艺术区'][index % 5]}`,
      expected_people: 500 + Math.floor(Math.random() * 2000),
      actual_people: index % 3 === 1 ? 0 : (500 + Math.floor(Math.random() * 2000)) * (0.8 + Math.random() * 0.5),
      safety_event: index % 4 === 0 ? '1' : '0', // 安全事件数
      praise_rate: (85 + Math.random() * 10).toFixed(1), // 好评率
      create_time: `2025-11-25 ${8 + (index % 6)}:${index % 2 === 0 ? '00' : '30'}`,
      start_time: `2025-11-25 ${9 + (index % 6)}:${index % 2 === 0 ? '30' : '00'}`,
      end_time: index % 3 === 1 ? `2025-11-25 ${18 + (index % 4)}:${index % 2 === 0 ? '00' : '30'}` : '',
      manager: ['张主管', '李运营', '王策划'][index % 3],
      safety_measures: index % 3 === 0 ? '配备安保人员、设置应急通道' : index % 3 === 1 ? '活动未开始' : '已完成安全巡检'
    }));
  }
};

// 单条活动趋势数据（详情弹窗用）
export const fetchActivityDetailTrend = async (sceneId) => {
  try {
    const response = await axios.get(`${BASE_URL}/activityDetailTrend/${sceneId}`);
    if (response.data && response.data.xAxis && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('活动详情趋势接口调用失败，使用模拟数据:', error.message);
    return {
      xAxis: ['11-12', '11-13', '11-14', '11-15', '11-16', '11-17', '11-18'],
      series: [
        { name: '参与人数（人）', data: [350, 420, 510, 480, 550, 620, 580] },
        { name: '好评数（条）', data: [320, 380, 450, 420, 490, 550, 520] }
      ]
    };
  }
};


// 文体场馆专题接口
// 文体场馆概览数据（核心指标）
export const fetchVenueOverview = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/overview`);
    if (response.data && typeof response.data === 'object') {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('文体场馆概览接口调用失败，使用模拟数据:', error.message);
    return {
      total_venue_count: 89,          // 字段名替换为 venue
      reserve_count: 12580,
      current_count: 7850,
      abn_venue_count: 7,             // 字段名替换为 venue
      complete_rate: 89.2,
      reserve_rate_avg: 78.5,
      fault_rate: 7.9,
      last_update_time: '2025-11-25 10:30'
    };
  }
};

// 场馆预约趋势数据（1小时粒度，近24小时）
export const fetchVenueReserveTrend = async (venueName = '') => {
  try {
    const url = venueName
      ? `${BASE_URL}/reserveTrend?venueName=${encodeURIComponent(venueName)}`
      : `${BASE_URL}/reserveTrend`;
    const response = await axios.get(url);
    if (response.data && response.data.xAxis && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('场馆预约趋势接口调用失败，使用模拟数据:', error.message);
    const hours = Array.from({length: 24}, (_, i) => `${i}时`);
    const baseData = venueName
      ? [120, 98, 85, 70, 65, 80, 150, 280, 350, 420, 480, 520, 500, 450, 380, 320, 290, 310, 350, 280, 200, 150, 110, 90]
      : [850, 720, 650, 580, 520, 600, 950, 1280, 1560, 1820, 2050, 2180, 2050, 1920, 1750, 1600, 1480, 1520, 1650, 1420, 1150, 980, 820, 750];
    return {
      xAxis: hours,
      series: [{
        name: venueName || '全场馆预约量',
        data: baseData,
        type: 'line'
      }]
    };
  }
};

// 场馆运营率TOP5数据
export const fetchVenueOperationTop5 = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/operationTop5`);
    if (response.data && response.data.xAxis && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('场馆运营率TOP5接口调用失败，使用模拟数据:', error.message);
    return {
      xAxis: ['市体育中心', '文化中心体育馆', '奥体游泳馆', '全民健身中心', '城东文体馆'],
      series: [{
        name: '运营率（%）',
        data: [92.5, 88.7, 85.3, 82.1, 79.8]
      }]
    };
  }
};

// 设施故障列表数据
export const fetchVenueFaultList = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/faultList`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('场馆故障列表接口调用失败，使用模拟数据:', error.message);
    return [
      {
        fault_id: 'FF-001',
        venue_name: '市体育中心', // 字段名替换为 venue
        facility_name: '主馆照明系统',
        fault_type: '电气故障',
        fault_level: 'high',
        occur_time: '2025-11-25 08:15',
        handle_status: 'processing',
        handler: '王工',
        handle_measures: '已安排电工现场检修'
      },
      {
        fault_id: 'FF-002',
        venue_name: '奥体游泳馆', // 字段名替换为 venue
        facility_name: '泳池水循环设备',
        fault_type: '机械故障',
        fault_level: 'medium',
        occur_time: '2025-11-25 09:20',
        handle_status: 'pending',
        handler: '',
        handle_measures: ''
      },
      {
        fault_id: 'FF-003',
        venue_name: '文化中心体育馆', // 字段名替换为 venue
        facility_name: '观众席座椅',
        fault_type: '结构故障',
        fault_level: 'low',
        occur_time: '2025-11-25 10:00',
        handle_status: 'handled',
        handler: '李工',
        handle_measures: '已更换损坏座椅'
      },
      {
        fault_id: 'FF-004',
        venue_name: '全民健身中心', // 字段名替换为 venue
        facility_name: '健身器材（跑步机）',
        fault_type: '机械故障',
        fault_level: 'high',
        occur_time: '2025-11-25 07:40',
        handle_status: 'pending',
        handler: '',
        handle_measures: ''
      },
      {
        fault_id: 'FF-005',
        venue_name: '城东文体馆', // 字段名替换为 venue
        facility_name: '空调系统',
        fault_type: '制冷故障',
        fault_level: 'medium',
        occur_time: '2025-11-25 09:50',
        handle_status: 'processing',
        handler: '张工',
        handle_measures: '已联系维保单位上门'
      }
    ];
  }
};

// 投诉列表数据
export const fetchVenueComplaintList = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/complaintList`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('场馆投诉列表接口调用失败，使用模拟数据:', error.message);
    return [
      {
        complaint_id: 'CL-001',
        venue_name: '市体育中心', // 字段名替换为 venue
        complaint_content: '预约系统卡顿，无法正常预约',
        complaint_type: '系统故障',
        create_time: '2025-11-25 08:30',
        complete_status: 'handled',
        complete_time: '2025-11-25 09:15',
        handler: '客服部-刘',
        handle_measures: '已修复预约系统，用户可正常使用'
      },
      {
        complaint_id: 'CL-002',
        venue_name: '奥体游泳馆', // 字段名替换为 venue
        complaint_content: '更衣室卫生条件差',
        complaint_type: '服务质量',
        create_time: '2025-11-25 09:10',
        complete_status: 'pending',
        complete_time: '',
        handler: '',
        handle_measures: ''
      },
      {
        complaint_id: 'CL-003',
        venue_name: '文化中心体育馆', // 字段名替换为 venue
        complaint_content: '停车费过高',
        complaint_type: '收费争议',
        create_time: '2025-11-25 09:45',
        complete_status: 'processing',
        complete_time: '',
        handler: '运营部-王',
        handle_measures: '已反馈至财务部门，待核实调整'
      },
      {
        complaint_id: 'CL-004',
        venue_name: '全民健身中心', // 字段名替换为 venue
        complaint_content: '器材维护不及时',
        complaint_type: '设施维护',
        create_time: '2025-11-25 10:10',
        complete_status: 'pending',
        complete_time: '',
        handler: '',
        handle_measures: ''
      },
      {
        complaint_id: 'CL-005',
        venue_name: '城东文体馆', // 字段名替换为 venue
        complaint_content: '工作人员态度恶劣',
        complaint_type: '服务态度',
        create_time: '2025-11-25 10:25',
        complete_status: 'handled',
        complete_time: '2025-11-25 10:40',
        handler: '人事部-赵',
        handle_measures: '已对涉事人员批评教育，向用户致歉'
      }
    ];
  }
};

// 场馆列表数据（用于筛选趋势图）
export const fetchVenueList = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/list`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('场馆列表接口调用失败，使用模拟数据:', error.message);
    return [
      { venue_name: '市体育中心', reserve_rate: 92.5 }, // 字段名替换为 venue
      { venue_name: '文化中心体育馆', reserve_rate: 88.7 },
      { venue_name: '奥体游泳馆', reserve_rate: 85.3 },
      { venue_name: '全民健身中心', reserve_rate: 82.1 },
      { venue_name: '城东文体馆', reserve_rate: 79.8 },
      { venue_name: '城西游泳馆', reserve_rate: 76.5 },
      { venue_name: '城南体育馆', reserve_rate: 74.2 }
    ];
  }
};

// 文旅资源专题
/**
 * 文旅资源概览数据（核心指标：总数、运营率）
 * 数据来源：stat_app_scene_rpt
 */
export const fetchTourismResourceOverview = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/overview`);
    if (response.data && typeof response.data === 'object') {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('文旅资源概览接口调用失败，使用模拟数据:', error.message);
    // 模拟 stat_app_scene_rpt 表核心数据
    return {
      total_scene_count: 286,          // 资源总数
      used_count: 241,                 // 已使用资源数
      operation_rate: 84.3,            // 运营率（used_count/total_scene_count）
      update_time: '2025-11-25 10:00'  // 统计时间
    };
  }
};

/**
 * 资源类型分布数据
 * 数据来源：sys_app_scene_minor 关联 stat_app_scene_rpt
 */
export const fetchResourceTypeDistribution = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/type-distribution`);
    if (response.data && response.data.legend && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('资源类型分布接口调用失败，使用模拟数据:', error.message);
    // 模拟 minor_name（类型名称）与 count（数量）
    return {
      legend: ['自然景区', '人文古迹', '文旅综合体', '乡村旅游点', '研学基地'],
      series: [{
        name: '资源数量',
        data: [85, 62, 58, 45, 36]
      }]
    };
  }
};

/**
 * 资源状态分布数据
 * 数据来源：biz_app_scene_info 的 scene_status 与 count
 */
export const fetchResourceStatusDistribution = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/status-distribution`);
    if (response.data && response.data.xAxis && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('资源状态分布接口调用失败，使用模拟数据:', error.message);
    // 模拟 scene_status（状态）与 count（数量）
    return {
      xAxis: ['正常运营', '闲置'],
      series: [{
        name: '资源数量',
        data: [241, 28]
      }]
    };
  }
};

/**
 * 闲置资源列表
 * 数据来源：biz_app_scene_info（app_scene_name、idle_days等）
 * @param {Object} params - 筛选参数（typeCode/regionCode/idleDays）
 */
export const fetchIdleResourceList = async (params = {}) => {
  try {
    const response = await axios.get(`${BASE_URL}/idle-resources`, { params });
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('闲置资源列表接口调用失败，使用模拟数据:', error.message);
    // 模拟闲置资源数据（闲置天数=当前日期-最后运营日期）
    const types = ['自然景区', '人文古迹', '文旅综合体', '乡村旅游点', '研学基地'];
    const regions = ['主城区', '东部新区', '西部文旅区', '南部乡村区'];
    return Array(18).fill(0).map((_, index) => ({
      scene_id: `TR-${String(index + 100).slice(1)}`,
      app_scene_name: `${types[index % 5]}${index + 1}号点位`,
      minor_name: types[index % 5],
      minor_code: `type_${index % 5 + 1}`, // 关联 sys_app_scene_minor.minor_code
      region_name: regions[index % 4],
      region_code: `00${index % 4 + 1}`,   // 关联 sys_area.region_code
      scene_status: 'idle',
      idle_days: 10 + Math.floor(Math.random() * 40), // 闲置天数（10-50天）
      last_operation_time: `2025-10-${15 + index % 15} 09:${index % 2 === 0 ? '00' : '30'}`,
      update_time: `2025-10-${15 + index % 15} 09:${index % 2 === 0 ? '00' : '30'}`,
      responsible_person: ['张三', '李四', '王五', '赵六', '钱七'][index % 5],
      contact: `13800${10000 + index * 100}`,
      total_used_count: Math.floor(Math.random() * 100),
      monthly_used_count: Math.floor(Math.random() * 10),
      scene_operation_rate: 60 + Math.floor(Math.random() * 30),
      exception_count: Math.floor(Math.random() * 5)
    }));
  }
};

/**
 * 单条资源运营记录（详情弹窗用）
 * @param {String} sceneId - 资源ID
 */
export const fetchResourceOperationRecord = async (sceneId) => {
  try {
    const response = await axios.get(`${BASE_URL}/operation-record/${sceneId}`);
    if (response.data && response.data.xAxis && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('资源运营记录接口调用失败，使用模拟数据:', error.message);
    const months = ['6月', '7月', '8月', '9月', '10月', '11月'];
    return {
      xAxis: months,
      series: [{
        name: '使用次数',
        data: [12, 15, 8, 6, 4, 2]
      }],
      recordList: Array(10).fill(0).map((_, index) => ({
        record_time: `2025-11-${5 + index} 10:${index % 60}`,
        used_count: Math.floor(Math.random() * 5),
        operation_type: ['日常运营', '活动承接', '维护检查', '应急处置'][index % 4],
        operator: ['张三', '李四', '王五'][index % 3],
        remark: index % 3 === 0 ? '正常运营' : index % 3 === 1 ? '设备检修' : '无'
      }))
    };
  }
};

/**
 * 更新资源状态（管理人员编辑状态）
 * @param {String} sceneId - 资源ID
 * @param {String} status - 新状态（normal/idle/maintenance等）
 */
export const updateResourceStatus = async (sceneId, status) => {
  try {
    const response = await axios.put(`${BASE_URL}/update-status/${sceneId}`, { status });
    if (response.data.code === 200) {
      ElMessage.success('资源状态更新成功');
      return response.data;
    }
    throw new Error(response.data.msg || '资源状态更新失败');
  } catch (error) {
    console.error('更新资源状态失败:', error.message);
    ElMessage.error(`资源状态更新失败：${error.message}`);
    throw error;
  }
};
