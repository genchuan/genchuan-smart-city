import axios from 'axios';
const BASE_URL = '/api/municipal/facility';

// 市政设施概览数据（核心指标）
export const fetchFacilityOverview = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/overview`);
    if (response.data && typeof response.data === 'object') {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('市政设施概览接口调用失败，使用模拟数据:', error.message);
    return {
      emergencyCount: 8,          // 紧急故障数
      normalCount: 15,            // 一般故障数
      totalFault: 23,             // 总故障数
      handledCount: 20,           // 已处置数
      handlingRate: 87.0,         // 处置率（%）
      timeoutCount: 3             // 超时未处置数
    };
  }
};

// 故障设施空间数据（地图标注用）
export const fetchFaultFacilitySpatial = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/faultSpatial`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('故障设施空间接口调用失败，使用模拟数据:', error.message);
    // 模拟坐标（以福州为例）
    const baseLng = 119.3062;
    const baseLat = 26.0753;
    return [
      {
        mng_comp_id: 'MF-001',
        comp_name: '五四北路井盖破损',
        comp_status: '紧急故障',
        fault_reason: '重型车辆碾压导致井盖断裂，影响行人安全',
        coord_x: baseLat + 0.001,
        coord_y: baseLng + 0.002,
        comp_type: '井盖',
        report_time: '2025-12-03 08:15',
        handler: '',
        handle_status: '未处置',
        update_time: '2025-12-03 08:15',
        is_timeout: true // 超时（超过24小时）
      },
      {
        mng_comp_id: 'MF-002',
        comp_name: '八一七中路路灯故障',
        comp_status: '紧急故障',
        fault_reason: '线路短路导致路灯熄灭，影响夜间交通',
        coord_x: baseLat - 0.003,
        coord_y: baseLng + 0.005,
        comp_type: '路灯',
        report_time: '2025-12-03 09:30',
        handler: '张三',
        handle_status: '处置中',
        update_time: '2025-12-03 09:35',
        is_timeout: false
      },
      {
        mng_comp_id: 'MF-006',
        comp_name: '五一广场消防栓漏水',
        comp_status: '紧急故障',
        fault_reason: '阀门损坏导致消防栓持续漏水，水压不足',
        coord_x: baseLat + 0.004,
        coord_y: baseLng + 0.006,
        comp_type: '消防栓',
        report_time: '2025-12-03 11:20',
        handler: '赵六',
        handle_status: '处置中',
        update_time: '2025-12-03 11:25',
        is_timeout: false
      },
      {
        mng_comp_id: 'MF-008',
        comp_name: '东街口垃圾桶破损溢漏',
        comp_status: '紧急故障',
        fault_reason: '垃圾桶箱体破裂，垃圾溢漏污染环境',
        coord_x: baseLat + 0.002,
        coord_y: baseLng - 0.006,
        comp_type: '垃圾桶',
        report_time: '2025-12-03 13:45',
        handler: '',
        handle_status: '未处置',
        update_time: '2025-12-03 13:45',
        is_timeout: false
      },
      {
        mng_comp_id: 'MF-003',
        comp_name: '江滨西大道供水管网渗漏',
        comp_status: '一般故障',
        fault_reason: '管道老化导致轻微渗漏，暂不影响供水',
        coord_x: baseLat + 0.005,
        coord_y: baseLng - 0.003,
        comp_type: '管网', // 无专属图标，降级为基础橙标
        report_time: '2025-12-02 14:20',
        handler: '',
        handle_status: '未处置',
        update_time: '2025-12-02 14:20',
        is_timeout: true
      },
      {
        mng_comp_id: 'MF-004',
        comp_name: '台江路井盖松动',
        comp_status: '一般故障',
        fault_reason: '井盖固定螺丝脱落，无安全风险',
        coord_x: baseLat - 0.002,
        coord_y: baseLng - 0.001,
        comp_type: '井盖',
        report_time: '2025-12-03 10:10',
        handler: '李四',
        handle_status: '处置中',
        update_time: '2025-12-03 10:15',
        is_timeout: false
      },
      {
        mng_comp_id: 'MF-009',
        comp_name: '闽江公园垃圾桶满溢',
        comp_status: '一般故障',
        fault_reason: '未及时清运导致垃圾桶满溢，暂未污染',
        coord_x: baseLat - 0.001,
        coord_y: baseLng + 0.008,
        comp_type: '垃圾桶',
        report_time: '2025-12-02 18:30',
        handler: '孙八',
        handle_status: '处置中',
        update_time: '2025-12-02 18:40',
        is_timeout: true
      },
      {
        mng_comp_id: 'MF-005',
        comp_name: '杨桥中路路灯不亮',
        comp_status: '完好',
        fault_reason: '灯泡损坏，已更换',
        coord_x: baseLat + 0.003,
        coord_y: baseLng + 0.001,
        comp_type: '路灯',
        report_time: '2025-12-01 16:40',
        handler: '王五',
        handle_status: '已完成',
        update_time: '2025-12-01 17:20',
        is_timeout: false
      },
      {
        mng_comp_id: 'MF-007',
        comp_name: '仓山万达消防栓堵塞',
        comp_status: '完好',
        fault_reason: '杂物堵塞接口，已清理疏通',
        coord_x: baseLat - 0.005,
        coord_y: baseLng - 0.004,
        comp_type: '消防栓',
        report_time: '2025-12-01 09:10',
        handler: '钱七',
        handle_status: '已完成',
        update_time: '2025-12-01 10:30',
        is_timeout: false
      },
      {
        mng_comp_id: 'MF-010',
        comp_name: '万象城垃圾桶变形',
        comp_status: '完好',
        fault_reason: '外力撞击导致变形，已更换新桶',
        coord_x: baseLat - 0.004,
        coord_y: baseLng + 0.003,
        comp_type: '垃圾桶',
        report_time: '2025-12-01 14:00',
        handler: '吴十',
        handle_status: '已完成',
        update_time: '2025-12-01 15:20',
        is_timeout: false
      }
    ];
  }
};

// 设施类型字典
export const fetchFacilityTypeDict = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/typeDict`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('设施类型字典接口调用失败，使用模拟数据:', error.message);
    return [
      { label: '井盖', value: '井盖' },
      { label: '路灯', value: '路灯' },
      { label: '管网', value: '管网' },
      { label: '消防栓', value: '消防栓' },
      { label: '垃圾桶', value: '垃圾桶' }
    ];
  }
};

// 生成抢修工单（派单接口）
export const createRepairWorkOrder = async (orderData) => {
  try {
    const response = await axios.post(`${BASE_URL}/createWorkOrder`, orderData);
    if (response.data && response.data.code === 200) {
      return response.data;
    }
    throw new Error('工单创建失败');
  } catch (error) {
    console.warn('派单接口调用失败:', error.message);
    return {
      code: 200,
      msg: '派单成功',
      data: { wo_id: `WO-${Date.now()}` }
    };
  }
};

// 标记故障处置完成
export const markFaultHandled = async (mngCompId) => {
  try {
    const response = await axios.post(`${BASE_URL}/markHandled`, { mng_comp_id: mngCompId });
    if (response.data && response.data.code === 200) {
      return response.data;
    }
    throw new Error('标记处置完成失败');
  } catch (error) {
    console.warn('标记处置完成接口调用失败:', error.message);
    return { code: 200, msg: '处置完成' };
  }
};

// 故障趋势数据（近7天）
export const fetchFaultTrend = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/faultTrend`);
    if (response.data && response.data.xAxis && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('故障趋势接口调用失败，使用模拟数据:', error.message);
    const days = ['12-01', '12-02', '12-03', '12-04', '12-05', '12-06', '12-07'];
    return {
      xAxis: days,
      series: [
        { name: '紧急故障', data: [3, 2, 2, 0, 1, 0, 0] },
        { name: '一般故障', data: [5, 4, 3, 2, 1, 4, 2] }
      ]
    };
  }
};

// 故障类型分布饼图数据
export const fetchFaultTypeDistribution = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/faultTypeDistribution`);
    if (response.data && response.data.legend && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('故障类型分布接口调用失败，使用模拟数据:', error.message);
    return {
      legend: ['井盖', '路灯', '管网', '消防栓', '垃圾桶'],
      series: [{
        name: '故障数',
        data: [8, 6, 5, 2, 1]
      }]
    };
  }
};


/**
 * 市容秩序概览数据（核心指标：总数、办结率、高发区域TOP3）
 * 来源：管理事项统计表（stat_mng_matter_rpt）
 */
export const fetchOrderOverview = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/overview`);
    if (response.data && typeof response.data === 'object') {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('市容秩序概览接口调用失败，使用模拟数据:', error.message);
    return {
      totalRptCount: 89,          // 事件总数
      completeRate: 78.7,         // 办结率（%）
      timeoutCount: 12,           // 超时未办结数（>12小时）
      highIncidenceAreas: [       // 高发区域TOP3（满足≥5件+环比≥20%）
        {
          areaName: '台江步行街',
          incidentCount: 18,
          ringGrowthRate: 25,     // 环比增长25%
          coord: { x: 26.0582, y: 119.3125 }, // 区域中心坐标
          mainType: '占道经营'    // 主要事件类型
        },
        {
          areaName: '仓山万达商圈',
          incidentCount: 15,
          ringGrowthRate: 22,
          coord: { x: 26.0458, y: 119.3417 },
          mainType: '违规广告'
        },
        {
          areaName: '鼓楼东街口',
          incidentCount: 12,
          ringGrowthRate: 20,
          coord: { x: 26.0819, y: 119.3008 },
          mainType: '私搭乱建'
        }
      ]
    };
  }
};

/**
 * 市容秩序事件空间数据（地图标注用）
 * 来源：管理事项表（biz_mng_matter）
 */
export const fetchOrderSpatial = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/orderSpatial`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('市容秩序事件空间接口调用失败，使用模拟数据:', error.message);
    // 模拟坐标（以福州为基准）
    const baseLng = 119.3062;
    const baseLat = 26.0753;
    return [
      {
        mng_matter_id: 'CO-001',
        matter_name: '台江步行街占道摆摊（小吃）',
        incident_location: '台江区步行街中段',
        incident_x: baseLat - 0.012,
        incident_y: baseLng + 0.008,
        matter_type: '占道经营', // 事件类型：占道经营/违规广告/私搭乱建
        matter_status: '未处置', // 未处置/处置中/已办结
        report_time: '2025-12-04 08:00',
        responsible_person: '', // 责任人
        update_time: '2025-12-04 08:00',
        is_timeout: true, // 超时（>12小时未办结）
        violation_desc: '在步行街主干道占道售卖小吃，占用行人通道约2米，影响通行',
        scene_img: 'https://picsum.photos/800/400', // 现场图片
        related_parts: '步行街公共步道、市政垃圾桶', // 关联部件
        grid_code: 'FJ-FZ-TJ-001' // 网格编码（用于高发区域计算）
      },
      {
        mng_matter_id: 'CO-002',
        matter_name: '仓山万达违规悬挂广告牌',
        incident_location: '仓山区万达1号门外侧',
        incident_x: baseLat - 0.021,
        incident_y: baseLng + 0.015,
        matter_type: '违规广告',
        matter_status: '处置中',
        report_time: '2025-12-04 09:30',
        responsible_person: '李执法',
        update_time: '2025-12-04 09:35',
        is_timeout: false,
        violation_desc: '未经审批悬挂大型广告牌，尺寸3m*2m，遮挡消防通道标识',
        scene_img: 'https://picsum.photos/800/401',
        related_parts: '万达外立面墙体、消防通道标识牌',
        grid_code: 'FJ-FZ-CS-002'
      },
      {
        mng_matter_id: 'CO-003',
        matter_name: '鼓楼东街口私搭临时棚屋',
        incident_location: '鼓楼区东街口天桥下',
        incident_x: baseLat + 0.008,
        incident_y: baseLng - 0.005,
        matter_type: '私搭乱建',
        matter_status: '处置中',
        report_time: '2025-12-04 10:15',
        responsible_person: '王执法',
        update_time: '2025-12-04 10:20',
        is_timeout: false,
        violation_desc: '在天桥下私搭10㎡临时棚屋，用于售卖小商品，占用公共空间',
        scene_img: 'https://picsum.photos/800/402',
        related_parts: '人行天桥、公共照明设施',
        grid_code: 'FJ-FZ-GL-003'
      },
      {
        mng_matter_id: 'CO-004',
        matter_name: '台江步行街占道售卖玩具',
        incident_location: '台江区步行街北段',
        incident_x: baseLat - 0.015,
        incident_y: baseLng + 0.006,
        matter_type: '占道经营',
        matter_status: '未处置',
        report_time: '2025-12-04 07:45',
        responsible_person: '',
        update_time: '2025-12-04 07:45',
        is_timeout: true,
        violation_desc: '占道售卖儿童玩具，占用盲道约1.5米，影响视障人士通行',
        scene_img: 'https://picsum.photos/800/403',
        related_parts: '盲道、市政地砖',
        grid_code: 'FJ-FZ-TJ-001'
      },
      {
        mng_matter_id: 'CO-005',
        matter_name: '仓山万达违规张贴小广告',
        incident_location: '仓山区万达地下停车场入口',
        incident_x: baseLat - 0.023,
        incident_y: baseLng + 0.012,
        matter_type: '违规广告',
        matter_status: '未处置',
        report_time: '2025-12-04 08:40',
        responsible_person: '',
        update_time: '2025-12-04 08:40',
        is_timeout: true,
        violation_desc: '在停车场入口墙面张贴数十张小广告，覆盖面积约5㎡，影响市容',
        scene_img: 'https://picsum.photos/800/404',
        related_parts: '停车场入口墙面、监控摄像头',
        grid_code: 'FJ-FZ-CS-002'
      },
      {
        mng_matter_id: 'CO-006',
        matter_name: '鼓楼东街口占道经营早餐摊',
        incident_location: '鼓楼区东街口地铁站出口',
        incident_x: baseLat + 0.006,
        incident_y: baseLng - 0.008,
        matter_type: '占道经营',
        matter_status: '处置中',
        report_time: '2025-12-04 06:30',
        responsible_person: '张执法',
        update_time: '2025-12-04 06:35',
        is_timeout: true,
        violation_desc: '在地铁站出口占道经营早餐，占用行人通道，造成早高峰拥堵',
        scene_img: 'https://picsum.photos/800/405',
        related_parts: '地铁站出口标识、行人引导牌',
        grid_code: 'FJ-FZ-GL-003'
      },
      {
        mng_matter_id: 'CO-007',
        matter_name: '台江宝龙广场私搭遮阳棚',
        incident_location: '台江区宝龙广场外侧广场',
        incident_x: baseLat - 0.018,
        incident_y: baseLng + 0.010,
        matter_type: '私搭乱建',
        matter_status: '未处置',
        report_time: '2025-12-04 11:00',
        responsible_person: '',
        update_time: '2025-12-04 11:00',
        is_timeout: false,
        violation_desc: '未经审批私搭遮阳棚，面积约8㎡，占用广场公共活动空间',
        scene_img: 'https://picsum.photos/800/406',
        related_parts: '广场地面、公共座椅',
        grid_code: 'FJ-FZ-TJ-004'
      },
      {
        mng_matter_id: 'CO-008',
        matter_name: '仓山爱琴海违规电子屏广告',
        incident_location: '仓山区爱琴海购物公园外墙',
        incident_x: baseLat - 0.025,
        incident_y: baseLng + 0.018,
        matter_type: '违规广告',
        matter_status: '已办结',
        report_time: '2025-12-03 14:20',
        responsible_person: '赵执法',
        update_time: '2025-12-03 16:40',
        is_timeout: false,
        violation_desc: '电子屏广告超时播放（晚10点后仍播放），违反市容管理规定',
        scene_img: 'https://picsum.photos/800/407',
        related_parts: '外墙电子屏、电力供应设施',
        grid_code: 'FJ-FZ-CS-005'
      },
      {
        mng_matter_id: 'CO-009',
        matter_name: '鼓楼五四路私搭围栏占用人行道',
        incident_location: '鼓楼区五四路128号外侧',
        incident_x: baseLat + 0.012,
        incident_y: baseLng + 0.002,
        matter_type: '私搭乱建',
        matter_status: '已办结',
        report_time: '2025-12-03 10:00',
        responsible_person: '陈执法',
        update_time: '2025-12-03 11:30',
        is_timeout: false,
        violation_desc: '私搭金属围栏占用人行道约3米，影响行人通行，已拆除',
        scene_img: 'https://picsum.photos/800/408',
        related_parts: '人行道地砖、市政护栏',
        grid_code: 'FJ-FZ-GL-006'
      },
      {
        mng_matter_id: 'CO-010',
        matter_name: '台江中亭街占道经营水果摊',
        incident_location: '台江区中亭街西段',
        incident_x: baseLat - 0.010,
        incident_y: baseLng + 0.003,
        matter_type: '占道经营',
        matter_status: '已办结',
        report_time: '2025-12-03 09:15',
        responsible_person: '刘执法',
        update_time: '2025-12-03 10:00',
        is_timeout: false,
        violation_desc: '占道经营水果摊，已劝导撤离，清理现场垃圾',
        scene_img: 'https://picsum.photos/800/409',
        related_parts: '人行道、市政垃圾桶',
        grid_code: 'FJ-FZ-TJ-001'
      }
    ];
  }
};

/**
 * 市容秩序事件类型字典
 */
export const fetchOrderTypeDict = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/typeDict`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('市容秩序事件类型字典接口调用失败，使用模拟数据:', error.message);
    return [
      { label: '占道经营', value: '占道经营', color: '#FACC15' }, // 黄色
      { label: '违规广告', value: '违规广告', color: '#9333EA' }, // 紫色
      { label: '私搭乱建', value: '私搭乱建', color: '#EF4444' }  // 红色
    ];
  }
};

/**
 * 区域字典（街道/社区）
 */
export const fetchAreaDict = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/areaDict`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('区域字典接口调用失败，使用模拟数据:', error.message);
    return [
      { label: '台江区', value: '台江区', children: [
          { label: '步行街社区', value: '步行街社区' },
          { label: '宝龙社区', value: '宝龙社区' },
          { label: '中亭街社区', value: '中亭街社区' }
        ]},
      { label: '仓山区', value: '仓山区', children: [
          { label: '万达社区', value: '万达社区' },
          { label: '爱琴海社区', value: '爱琴海社区' },
          { label: '金山社区', value: '金山社区' }
        ]},
      { label: '鼓楼区', value: '鼓楼区', children: [
          { label: '东街口社区', value: '东街口社区' },
          { label: '五四路社区', value: '五四路社区' },
          { label: '温泉社区', value: '温泉社区' }
        ]}
    ];
  }
};

/**
 * 生成执法工单（派单接口）
 */
export const createEnforcementWorkOrder = async (orderData) => {
  try {
    const response = await axios.post(`${BASE_URL}/createEnforcementOrder`, orderData);
    if (response.data && response.data.code === 200) {
      return response.data;
    }
    throw new Error('执法工单创建失败');
  } catch (error) {
    console.warn('派单执法接口调用失败:', error.message);
    return {
      code: 200,
      msg: '执法工单创建成功',
      data: { eo_id: `EO-${Date.now()}` }
    };
  }
};

/**
 * 标记事件办结
 */
export const markOrderCompleted = async (mngMatterId) => {
  try {
    const response = await axios.post(`${BASE_URL}/markCompleted`, { mng_matter_id: mngMatterId });
    if (response.data && response.data.code === 200) {
      return response.data;
    }
    throw new Error('标记事件办结失败');
  } catch (error) {
    console.warn('标记事件办结接口调用失败:', error.message);
    return { code: 200, msg: '事件已办结' };
  }
};

/**
 * 近7天办结率趋势数据
 */
export const fetchCompleteRateTrend = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/completeRateTrend`);
    if (response.data && response.data.xAxis && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('办结率趋势接口调用失败，使用模拟数据:', error.message);
    const days = ['12-01', '12-02', '12-03', '12-04', '12-05', '12-06', '12-07'];
    return {
      xAxis: days,
      series: [
        { name: '办结率(%)', data: [72.5, 75.0, 73.8, 78.7, 0, 0, 0] }, // 后续日期为0，模拟实时更新
        { name: '事件总数', data: [75, 80, 85, 89, 0, 0, 0] }
      ]
    };
  }
};

/**
 * 高发区域分析数据（原因+建议）
 */
export const fetchHighIncidenceAnalysis = async (areaName) => {
  try {
    const response = await axios.get(`${BASE_URL}/highIncidenceAnalysis`, { params: { area_name: areaName } });
    if (response.data && response.data.reason) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('高发区域分析接口调用失败，使用模拟数据:', error.message);
    const analysisMap = {
      '台江步行街': {
        reason: '1. 步行街人流量大，占道经营收益高，违规者屡教不改；2. 执法巡逻频次不足（仅每2小时1次）；3. 缺乏长效管控机制，违规成本低。',
        suggestion: '1. 增加执法巡逻频次至每1小时1次；2. 联合市场监管部门取缔无证摊贩；3. 划定规范经营区域，引导合规经营；4. 安装高清监控，远程取证执法。'
      },
      '仓山万达商圈': {
        reason: '1. 商圈商户广告投放需求大，违规设置成本低；2. 广告审批流程繁琐，部分商户铤而走险；3. 外立面巡查覆盖不全，违规广告发现不及时。',
        suggestion: '1. 简化广告审批流程，压缩办理时限至1个工作日；2. 建立商圈广告台账，定期核查；3. 联合物业开展日常巡查，及时拆除违规广告；4. 对多次违规商户进行公示警告。'
      },
      '鼓楼东街口': {
        reason: '1. 天桥下等公共空间管理权责不清；2. 临时棚屋搭建速度快，拆除后易反弹；3. 周边小商品需求大，违规搭建有市场。',
        suggestion: '1. 明确公共空间管理责任主体，落实网格化管理；2. 加装防违监控，实时预警违规搭建；3. 联合城管、街道开展专项整治行动；4. 引导违规者至正规经营场所。'
      },
      '默认': {
        reason: '高发区域核心原因：1. 违规收益高于违规成本；2. 执法覆盖存在盲区；3. 长效管控机制缺失。',
        suggestion: '通用整改建议：1. 增加重点区域执法频次；2. 建立违规台账，实施分级管控；3. 联合多部门开展专项整治；4. 加强宣传教育，提升商户合规意识。'
      }
    };
    return analysisMap[areaName] || analysisMap['默认'];
  }
};


// 环境卫生概览数据（核心指标）
export const fetchEnvironOverview = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/overview`);
    if (response.data && typeof response.data === 'object') {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('环境卫生概览接口调用失败，使用模拟数据:', error.message);
    return {
      clearCompletionRate: 82.5,    // 清运完成率（%）
      cleanCoverageRate: 78.0,      // 保洁覆盖率（%）
      overflowHandleRate: 90.0,     // 满溢事件处置率（%）
      totalClearPoint: 120,         // 总清运点数量
      completedClearPoint: 99,      // 已完成清运点数量
      totalCleanArea: 500000,       // 总保洁面积（㎡）
      cleanedArea: 390000,          // 已保洁面积（㎡）
      overflowEventCount: 15,       // 满溢事件总数
      handledOverflowCount: 13,     // 已处置满溢事件数
      timeoutOverflowCount: 2       // 超时未处置满溢事件数
    };
  }
};

// 清运点空间数据（地图标注用）
export const fetchClearPointSpatial = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/clearPointSpatial`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('清运点空间接口调用失败，使用模拟数据:', error.message);
    // 模拟坐标（以福州为例）
    const baseLng = 119.3062;
    const baseLat = 26.0753;
    return [
      {
        clear_point_id: 'CP-001',
        point_name: '五四北路永辉超市清运点',
        clear_status: '满溢',        // 满溢/待清运/已清运
        bucket_total: 20,            // 总桶数
        bucket_remaining: 20,        // 剩余桶数
        bucket_cleaned: 0,           // 已清运桶数
        coord_x: baseLat + 0.001,
        coord_y: baseLng + 0.002,
        responsible_person: '张三', // 负责人
        plan_clear_time: '2025-12-05 08:00', // 计划清运时间
        actual_clear_time: '',       // 实际清运时间
        is_timeout: true,            // 是否超时
        overflow_event_id: 'OE-001'  // 关联满溢事件ID
      },
      {
        clear_point_id: 'CP-002',
        point_name: '八一七中路文化宫清运点',
        clear_status: '满溢',
        bucket_total: 15,
        bucket_remaining: 15,
        bucket_cleaned: 0,
        coord_x: baseLat - 0.003,
        coord_y: baseLng + 0.005,
        responsible_person: '李四',
        plan_clear_time: '2025-12-05 09:00',
        actual_clear_time: '',
        is_timeout: true,
        overflow_event_id: 'OE-002'
      },
      {
        clear_point_id: 'CP-003',
        point_name: '江滨西大道闽江公园清运点',
        clear_status: '待清运',
        bucket_total: 18,
        bucket_remaining: 18,
        bucket_cleaned: 0,
        coord_x: baseLat + 0.005,
        coord_y: baseLng - 0.003,
        responsible_person: '王五',
        plan_clear_time: '2025-12-05 10:30',
        actual_clear_time: '',
        is_timeout: false,
        overflow_event_id: ''
      },
      {
        clear_point_id: 'CP-004',
        point_name: '台江路万达广场清运点',
        clear_status: '待清运',
        bucket_total: 25,
        bucket_remaining: 25,
        bucket_cleaned: 0,
        coord_x: baseLat - 0.002,
        coord_y: baseLng - 0.001,
        responsible_person: '赵六',
        plan_clear_time: '2025-12-05 11:00',
        actual_clear_time: '',
        is_timeout: false,
        overflow_event_id: ''
      },
      {
        clear_point_id: 'CP-005',
        point_name: '东街口商圈清运点',
        clear_status: '已清运',
        bucket_total: 30,
        bucket_remaining: 0,
        bucket_cleaned: 30,
        coord_x: baseLat + 0.002,
        coord_y: baseLng - 0.006,
        responsible_person: '钱七',
        plan_clear_time: '2025-12-05 07:00',
        actual_clear_time: '2025-12-05 07:30',
        is_timeout: false,
        overflow_event_id: ''
      },
      {
        clear_point_id: 'CP-006',
        clear_status: '已清运',
        point_name: '仓山万达清运点',
        bucket_total: 16,
        bucket_remaining: 0,
        bucket_cleaned: 16,
        coord_x: baseLat - 0.005,
        coord_y: baseLng - 0.004,
        responsible_person: '孙八',
        plan_clear_time: '2025-12-05 06:30',
        actual_clear_time: '2025-12-05 07:00',
        is_timeout: false,
        overflow_event_id: ''
      },
      {
        clear_point_id: 'CP-007',
        point_name: '闽江世纪城清运点',
        clear_status: '满溢',
        bucket_total: 22,
        bucket_remaining: 22,
        bucket_cleaned: 0,
        coord_x: baseLat + 0.004,
        coord_y: baseLng + 0.006,
        responsible_person: '周九',
        plan_clear_time: '2025-12-04 18:00',
        actual_clear_time: '',
        is_timeout: true,
        overflow_event_id: 'OE-003'
      },
      {
        clear_point_id: 'CP-008',
        point_name: '杨桥中路山姆会员店清运点',
        clear_status: '待清运',
        bucket_total: 19,
        bucket_remaining: 19,
        bucket_cleaned: 0,
        coord_x: baseLat + 0.003,
        coord_y: baseLng + 0.001,
        responsible_person: '吴十',
        plan_clear_time: '2025-12-05 14:00',
        actual_clear_time: '',
        is_timeout: false,
        overflow_event_id: ''
      }
    ];
  }
};

// 保洁区域空间数据（地图面数据）
export const fetchCleanAreaSpatial = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/cleanAreaSpatial`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('保洁区域空间接口调用失败，使用模拟数据:', error.message);
    // 模拟福州各区域面坐标（简化为多边形顶点）
    const baseLng = 119.3062;
    const baseLat = 26.0753;
    return [
      {
        clean_area_id: 'CA-001',
        area_name: '鼓东街道保洁区域',
        clean_status: '未保洁',      // 未保洁/保洁中/已保洁
        coverage_rate: 55.0,         // 保洁覆盖率（%）
        total_area: 80000,           // 总面积（㎡）
        cleaned_area: 44000,         // 已保洁面积（㎡）
        responsible_team: '鼓楼区第一保洁队',
        responsible_person: '张三',
        plan_clean_time: '2025-12-05 08:00',
        actual_clean_time: '',
        // 多边形坐标（[lng, lat] 数组）
        polygon_coords: [
          [baseLng + 0.000, baseLat + 0.000],
          [baseLng + 0.003, baseLat + 0.000],
          [baseLng + 0.003, baseLat + 0.003],
          [baseLng + 0.000, baseLat + 0.003],
          [baseLng + 0.000, baseLat + 0.000]
        ]
      },
      {
        clean_area_id: 'CA-002',
        area_name: '台江街道保洁区域',
        clean_status: '保洁中',
        coverage_rate: 75.0,
        total_area: 95000,
        cleaned_area: 71250,
        responsible_team: '台江区第一保洁队',
        responsible_person: '李四',
        plan_clean_time: '2025-12-05 07:30',
        actual_clean_time: '2025-12-05 08:00',
        polygon_coords: [
          [baseLng - 0.002, baseLat - 0.001],
          [baseLng + 0.001, baseLat - 0.001],
          [baseLng + 0.001, baseLat + 0.002],
          [baseLng - 0.002, baseLat + 0.002],
          [baseLng - 0.002, baseLat - 0.001]
        ]
      },
      {
        clean_area_id: 'CA-003',
        area_name: '临江街道保洁区域',
        clean_status: '已保洁',
        coverage_rate: 92.0,
        total_area: 70000,
        cleaned_area: 64400,
        responsible_team: '台江区第二保洁队',
        responsible_person: '王五',
        plan_clean_time: '2025-12-05 06:00',
        actual_clean_time: '2025-12-05 07:15',
        polygon_coords: [
          [baseLng + 0.001, baseLat - 0.003],
          [baseLng + 0.004, baseLat - 0.003],
          [baseLng + 0.004, baseLat + 0.000],
          [baseLng + 0.001, baseLat + 0.000],
          [baseLng + 0.001, baseLat - 0.003]
        ]
      },
      {
        clean_area_id: 'CA-004',
        area_name: '东街街道保洁区域',
        clean_status: '未保洁',
        coverage_rate: 58.0,
        total_area: 65000,
        cleaned_area: 37700,
        responsible_team: '鼓楼区第二保洁队',
        responsible_person: '赵六',
        plan_clean_time: '2025-12-05 09:00',
        actual_clean_time: '',
        polygon_coords: [
          [baseLng - 0.001, baseLat + 0.002],
          [baseLng + 0.002, baseLat + 0.002],
          [baseLng + 0.002, baseLat + 0.005],
          [baseLng - 0.001, baseLat + 0.005],
          [baseLng - 0.001, baseLat + 0.002]
        ]
      },
      {
        clean_area_id: 'CA-005',
        area_name: '仓山万达保洁区域',
        clean_status: '保洁中',
        coverage_rate: 85.0,
        total_area: 100000,
        cleaned_area: 85000,
        responsible_team: '仓山区第一保洁队',
        responsible_person: '钱七',
        plan_clean_time: '2025-12-05 08:30',
        actual_clean_time: '2025-12-05 09:00',
        polygon_coords: [
          [baseLng - 0.004, baseLat - 0.002],
          [baseLng - 0.001, baseLat - 0.002],
          [baseLng - 0.001, baseLat + 0.001],
          [baseLng - 0.004, baseLat + 0.001],
          [baseLng - 0.004, baseLat - 0.002]
        ]
      }
    ];
  }
};

// 垃圾满溢事件数据
export const fetchOverflowEvent = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/overflowEvent`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('垃圾满溢事件接口调用失败，使用模拟数据:', error.message);
    return [
      {
        overflow_event_id: 'OE-001',
        relate_clear_point_id: 'CP-001',
        event_name: '五四北路永辉超市清运点垃圾满溢',
        report_time: '2025-12-04 18:30',
        handle_status: '未处置',    // 未处置/处置中/已处置
        handle_person: '',
        handle_time: '',
        handle_measures: '',
        is_timeout: true,
        complaint_count: 3          // 投诉次数
      },
      {
        overflow_event_id: 'OE-002',
        relate_clear_point_id: 'CP-002',
        event_name: '八一七中路文化宫清运点垃圾满溢',
        report_time: '2025-12-04 19:15',
        handle_status: '处置中',
        handle_person: '张三',
        handle_time: '2025-12-05 08:15',
        handle_measures: '已安排清运车辆前往',
        is_timeout: true,
        complaint_count: 2
      },
      {
        overflow_event_id: 'OE-003',
        relate_clear_point_id: 'CP-007',
        event_name: '闽江世纪城清运点垃圾满溢',
        report_time: '2025-12-04 20:00',
        handle_status: '未处置',
        handle_person: '',
        handle_time: '',
        handle_measures: '',
        is_timeout: true,
        complaint_count: 5
      },
      {
        overflow_event_id: 'OE-004',
        relate_clear_point_id: 'CP-009',
        event_name: '万象城清运点垃圾满溢',
        report_time: '2025-12-05 07:45',
        handle_status: '已处置',
        handle_person: '李四',
        handle_time: '2025-12-05 08:30',
        handle_measures: '已完成清运，清理溢漏垃圾',
        is_timeout: false,
        complaint_count: 1
      }
    ];
  }
};

// 区域字典（街道/社区，筛选用）
export const fetchEnvironRegionDict = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/regionDict`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('区域字典接口调用失败，使用模拟数据:', error.message);
    return [
      { label: '全部区域', value: '' },
      { label: '鼓楼区', value: 'gulou', children: [
          { label: '鼓东街道', value: 'gudong' },
          { label: '东街街道', value: 'dongjie' },
          { label: '华大街道', value: 'huada' }
        ]},
      { label: '台江区', value: 'taijiang', children: [
          { label: '台江街道', value: 'taijiang' },
          { label: '临江街道', value: 'linjiang' },
          { label: '瀛洲街道', value: 'yingzhou' }
        ]},
      { label: '仓山区', value: 'cangshan', children: [
          { label: '仓山街道', value: 'cangshan' },
          { label: '三叉街街道', value: 'sanchajie' },
          { label: '上渡街道', value: 'shangdu' }
        ]}
    ];
  }
};

// 导出作业计划
export const exportWorkPlan = async (planParams) => {
  try {
    const response = await axios.post(`${BASE_URL}/exportWorkPlan`, planParams, {
      responseType: 'blob' // 导出文件需指定blob类型
    });
    if (response.data) {
      return response.data;
    }
    throw new Error('作业计划导出失败');
  } catch (error) {
    console.warn('作业计划导出接口调用失败:', error.message);
    // 模拟导出成功返回
    return {
      code: 200,
      msg: '作业计划导出成功',
      data: {
        file_name: `环卫作业计划_${new Date().getTime()}.xlsx`,
        file_url: `https://example.com/download/plan_${new Date().getTime()}.xlsx`
      }
,    };
  }
};

// 标记清运点完成清运
export const markClearPointCompleted = async (clearPointId) => {
  try {
    const response = await axios.post(`${BASE_URL}/markClearCompleted`, { clear_point_id: clearPointId });
    if (response.data && response.data.code === 200) {
      return response.data;
    }
    throw new Error('标记清运完成失败');
  } catch (error) {
    console.warn('标记清运完成接口调用失败:', error.message);
    return {
      code: 200,
      msg: '清运完成标记成功',
      data: { actual_clear_time: new Date().toLocaleString() }
    };
  }
};

// 标记保洁区域完成保洁
export const markCleanAreaCompleted = async (cleanAreaId) => {
  try {
    const response = await axios.post(`${BASE_URL}/markCleanCompleted`, { clean_area_id: cleanAreaId });
    if (response.data && response.data.code === 200) {
      return response.data;
    }
    throw new Error('标记保洁完成失败');
  } catch (error) {
    console.warn('标记保洁完成接口调用失败:', error.message);
    return {
      code: 200,
      msg: '保洁完成标记成功',
      data: {
        actual_clean_time: new Date().toLocaleString(),
        coverage_rate: 100.0 // 标记完成后覆盖率设为100%
      }
    };
  }
};

// 处置垃圾满溢事件
export const handleOverflowEvent = async (eventId, handleData) => {
  try {
    const response = await axios.post(`${BASE_URL}/handleOverflowEvent`, {
      overflow_event_id: eventId,
      ...handleData
    });
    if (response.data && response.data.code === 200) {
      return response.data;
    }
    throw new Error('满溢事件处置失败');
  } catch (error) {
    console.warn('满溢事件处置接口调用失败:', error.message);
    return {
      code: 200,
      msg: '满溢事件处置成功',
      data: {
        handle_time: new Date().toLocaleString(),
        handle_status: '已处置'
      }
    };
  }
};

// 清运趋势数据（近7天）
export const fetchClearTrend = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/clearTrend`);
    if (response.data && response.data.xAxis && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('清运趋势接口调用失败，使用模拟数据:', error.message);
    const days = ['12-01', '12-02', '12-03', '12-04', '12-05', '12-06', '12-07'];
    return {
      xAxis: days,
      series: [
        { name: '已清运桶数', data: [280, 320, 290, 310, 295, 0, 0] },
        { name: '满溢事件数', data: [3, 5, 2, 4, 3, 0, 0] }
      ]
    };
  }
};

// 保洁覆盖率分布（按街道）
export const fetchCleanCoverageDistribution = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/cleanCoverageDistribution`);
    if (response.data && response.data.legend && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('保洁覆盖率分布接口调用失败，使用模拟数据:', error.message);
    return {
      legend: ['鼓东街道', '台江街道', '临江街道', '东街街道', '仓山街道'],
      series: [{
        name: '保洁覆盖率(%)',
        data: [55, 75, 92, 58, 85]
      }]
    };
  }
};


// 园林绿化概览数据（核心指标）
export const fetchGardenOverview = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/overview`);
    if (response.data && typeof response.data === 'object') {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('园林绿化概览接口调用失败，使用模拟数据:', error.message);
    return {
      coverArea: 850000,        // 绿化覆盖面积(㎡)
      coverRate: 88.5,           // 覆盖率(%)
      diseaseCount: 12,          // 病害总数
      handledDiseaseCount: 9,    // 已处置病害数
      diseaseHandlingRate: 75.0, // 病害处置率(%)
      survivalRate: 92.3,        // 绿化存活率(%)
      toMaintainCount: 18        // 待养护数量
    };
  }
};

// 绿化空间分布数据（地图标注用）
export const fetchGardenSpatial = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/spatial`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('绿化空间接口调用失败，使用模拟数据:', error.message);
    // 模拟坐标（以福州为例）
    const baseLng = 119.3062;
    const baseLat = 26.0753;
    return [
      // 树木（带病害）
      {
        mng_comp_id: 'GD-001',
        comp_name: '五四北路香樟树',
        minor_name: '树木',
        coord_x: baseLat + 0.001,
        coord_y: baseLng + 0.002,
        maintain_status: '待养护',
        disease_status: '未处置',
        disease_reason: '叶斑病，枝叶发黄枯萎',
        responsible_person: '张三',
        maintain_count: 0,
        report_time: '2025-12-03 08:15',
        is_new_disease: true, // 新增病害
        area: '鼓楼区华大街道'
      },
      {
        mng_comp_id: 'GD-002',
        comp_name: '八一七中路榕树',
        minor_name: '树木',
        coord_x: baseLat - 0.003,
        coord_y: baseLng + 0.005,
        maintain_status: '养护中',
        disease_status: '处置中',
        disease_reason: '白蚁侵蚀树干，需除虫',
        responsible_person: '李四',
        maintain_count: 2,
        report_time: '2025-12-03 09:30',
        is_new_disease: false,
        area: '台江区茶亭街道'
      },
      // 绿地（带病害）
      {
        mng_comp_id: 'GD-003',
        comp_name: '江滨西大道公共绿地',
        minor_name: '绿地',
        coord_x: baseLat + 0.005,
        coord_y: baseLng - 0.003,
        maintain_status: '待养护',
        disease_status: '未处置',
        disease_reason: '杂草丛生，需除草施肥',
        responsible_person: '',
        maintain_count: 1,
        report_time: '2025-12-02 14:20',
        is_new_disease: true,
        area: '仓山区临江街道'
      },
      // 花坛（无病害）
      {
        mng_comp_id: 'GD-004',
        comp_name: '五一广场月季花坛',
        minor_name: '花坛',
        coord_x: baseLat + 0.004,
        coord_y: baseLng + 0.006,
        maintain_status: '已养护',
        disease_status: '无病害',
        disease_reason: '',
        responsible_person: '王五',
        maintain_count: 5,
        report_time: '2025-12-01 11:20',
        is_new_disease: false,
        area: '鼓楼区鼓东街道'
      },
      // 更多模拟数据
      {
        mng_comp_id: 'GD-005',
        comp_name: '东街口银杏树木',
        minor_name: '树木',
        coord_x: baseLat + 0.002,
        coord_y: baseLng - 0.006,
        maintain_status: '待养护',
        disease_status: '未处置',
        disease_reason: '根部积水，需排水修剪',
        responsible_person: '',
        maintain_count: 0,
        report_time: '2025-12-03 13:45',
        is_new_disease: false,
        area: '鼓楼区东街街道'
      },
      {
        mng_comp_id: 'GD-006',
        comp_name: '台江路麦冬绿地',
        minor_name: '绿地',
        coord_x: baseLat - 0.002,
        coord_y: baseLng - 0.001,
        maintain_status: '养护中',
        disease_status: '处置中',
        disease_reason: '褐斑病，部分草坪枯死',
        responsible_person: '赵六',
        maintain_count: 3,
        report_time: '2025-12-03 10:10',
        is_new_disease: false,
        area: '台江区后洲街道'
      },
      {
        mng_comp_id: 'GD-007',
        comp_name: '闽江公园牡丹花坛',
        minor_name: '花坛',
        coord_x: baseLat - 0.001,
        coord_y: baseLng + 0.008,
        maintain_status: '待养护',
        disease_status: '未处置',
        disease_reason: '花期过后未修剪，需施肥',
        responsible_person: '',
        maintain_count: 2,
        report_time: '2025-12-02 18:30',
        is_new_disease: false,
        area: '仓山区金山街道'
      },
      {
        mng_comp_id: 'GD-008',
        comp_name: '杨桥中路悬铃木',
        minor_name: '树木',
        coord_x: baseLat + 0.003,
        coord_y: baseLng + 0.001,
        maintain_status: '已养护',
        disease_status: '已处置',
        disease_reason: '天牛虫害，已除虫完成',
        responsible_person: '孙七',
        maintain_count: 4,
        report_time: '2025-12-01 16:40',
        is_new_disease: false,
        area: '鼓楼区鼓西街道'
      },
      {
        mng_comp_id: 'GD-009',
        comp_name: '塔头路玉兰树',
        minor_name: '树木',
        coord_x: baseLat + 0.007,
        coord_y: baseLng - 0.008,
        maintain_status: '待养护',
        disease_status: '未处置',
        disease_reason: '介壳虫侵害，枝叶黏腻发黄',
        responsible_person: '',
        maintain_count: 1,
        report_time: '2025-12-04 09:20',
        is_new_disease: true,
        area: '晋安区岳峰街道'
      },
      {
        mng_comp_id: 'GD-010',
        comp_name: '罗星西路马尼拉草坪',
        minor_name: '绿地',
        coord_x: baseLat - 0.005,
        coord_y: baseLng - 0.004,
        maintain_status: '已养护',
        disease_status: '无病害',
        disease_reason: '',
        responsible_person: '周八',
        maintain_count: 6,
        report_time: '2025-12-01 14:10',
        is_new_disease: false,
        area: '马尾区罗星街道'
      },
      {
        mng_comp_id: 'GD-011',
        comp_name: '郑和中路杜鹃花坛',
        minor_name: '花坛',
        coord_x: baseLat + 0.008,
        coord_y: baseLng + 0.009,
        maintain_status: '养护中',
        disease_status: '处置中',
        disease_reason: '枯萎病，部分花苗倒伏',
        responsible_person: '吴九',
        maintain_count: 2,
        report_time: '2025-12-04 11:30',
        is_new_disease: true,
        area: '长乐区吴航街道'
      },
      {
        mng_comp_id: 'GD-012',
        comp_name: '福马路水杉树',
        minor_name: '树木',
        coord_x: baseLat - 0.004,
        coord_y: baseLng - 0.007,
        maintain_status: '养护中',
        disease_status: '处置中',
        disease_reason: '叶斑病扩散，需喷杀菌剂',
        responsible_person: '郑十',
        maintain_count: 3,
        report_time: '2025-12-03 15:45',
        is_new_disease: false,
        area: '晋安区鼓山街道'
      },
      {
        mng_comp_id: 'GD-013',
        comp_name: '君竹路高羊茅绿地',
        minor_name: '绿地',
        coord_x: baseLat - 0.006,
        coord_y: baseLng + 0.004,
        maintain_status: '待养护',
        disease_status: '未处置',
        disease_reason: '杂草丛生覆盖草坪，需清理',
        responsible_person: '',
        maintain_count: 0,
        report_time: '2025-12-04 08:50',
        is_new_disease: true,
        area: '马尾区马尾镇'
      },
      {
        mng_comp_id: 'GD-014',
        comp_name: '郑和公园菊花花坛',
        minor_name: '花坛',
        coord_x: baseLat + 0.006,
        coord_y: baseLng - 0.002,
        maintain_status: '已养护',
        disease_status: '已处置',
        disease_reason: '花期病害，已喷药防治',
        responsible_person: '钱十一',
        maintain_count: 4,
        report_time: '2025-12-02 16:20',
        is_new_disease: false,
        area: '长乐区航城街道'
      }
    ];
  }
};

// 绿化类型字典
export const fetchGardenTypeDict = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/typeDict`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('绿化类型字典接口调用失败，使用模拟数据:', error.message);
    return [
      { label: '树木', value: '树木' },
      { label: '绿地', value: '绿地' },
      { label: '花坛', value: '花坛' }
    ];
  }
};

// 区域字典
export const fetchGardenAreaDict = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/areaDict`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('区域字典接口调用失败，使用模拟数据:', error.message);
    return [
      { label: '鼓楼区华大街道', value: '鼓楼区华大街道' },
      { label: '台江区茶亭街道', value: '台江区茶亭街道' },
      { label: '仓山区临江街道', value: '仓山区临江街道' },
      { label: '鼓楼区鼓东街道', value: '鼓楼区鼓东街道' },
      { label: '鼓楼区东街街道', value: '鼓楼区东街街道' },
      { label: '台江区后洲街道', value: '台江区后洲街道' },
      { label: '仓山区金山街道', value: '仓山区金山街道' },
      { label: '鼓楼区鼓西街道', value: '鼓楼区鼓西街道' }
    ];
  }
};

// 生成养护工单（派单接口）
export const createGardenWorkOrder = async (orderData) => {
  try {
    const response = await axios.post(`${BASE_URL}/createWorkOrder`, orderData);
    if (response.data && response.data.code === 200) {
      return response.data;
    }
    throw new Error('工单创建失败');
  } catch (error) {
    console.warn('养护派单接口调用失败:', error.message);
    return {
      code: 200,
      msg: '养护派单成功',
      data: { wo_id: `WO-GD-${Date.now()}` }
    };
  }
};

// 标记养护/病害处置完成
export const markGardenHandled = async (mngCompId) => {
  try {
    const response = await axios.post(`${BASE_URL}/markHandled`, { mng_comp_id: mngCompId });
    if (response.data && response.data.code === 200) {
      return response.data;
    }
    throw new Error('标记处置完成失败');
  } catch (error) {
    console.warn('标记处置完成接口调用失败:', error.message);
    return { code: 200, msg: '处置完成' };
  }
};

// 病害上报接口
export const reportGardenDisease = async (diseaseData) => {
  try {
    const response = await axios.post(`${BASE_URL}/reportDisease`, diseaseData);
    if (response.data && response.data.code === 200) {
      return response.data;
    }
    throw new Error('病害上报失败');
  } catch (error) {
    console.warn('病害上报接口调用失败:', error.message);
    return {
      code: 200,
      msg: '病害上报成功',
      data: { disease_id: `DISEASE-${Date.now()}` }
    };
  }
};

// 养护趋势数据（近7天）
export const fetchGardenTrend = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/maintainTrend`);
    if (response.data && response.data.xAxis && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('养护趋势接口调用失败，使用模拟数据:', error.message);
    const days = ['12-01', '12-02', '12-03', '12-04', '12-05', '12-06', '12-07'];
    return {
      xAxis: days,
      series: [
        { name: '养护完成数', data: [5, 8, 6, 9, 7, 10, 8] },
        { name: '病害上报数', data: [3, 2, 4, 1, 3, 2, 1] }
      ]
    };
  }
};

// 病害类型分布饼图数据
export const fetchGardenDiseaseDistribution = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/diseaseDistribution`);
    if (response.data && response.data.legend && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('病害类型分布接口调用失败，使用模拟数据:', error.message);
    return {
      legend: ['叶斑病', '虫害', '枯萎病', '杂草丛生', '其他'],
      series: [{
        name: '病害数',
        data: [4, 3, 2, 2, 1]
      }]
    };
  }
};
