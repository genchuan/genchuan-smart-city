import axios from 'axios';

const BASE_URL = '/api/water';

// 水务全域数据地图数据
export const fetchGlobalDataMap = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/globalDataMap`);
    if (Array.isArray(response.data) && response.data.length > 0) {
      return response.data; // 优先返回真实接口数据
    }
    throw new Error('真实接口返回空数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('水务全域数据地图接口调用失败，使用模拟数据:', error.message);
    // 替换为文件2的geometriesArray模拟数据
    return [
      {
        coord_x: 26.855237, // 纬度
        coord_y: 117.777777, // 经度
        water_fac_id: "W001", // 设施ID
        water_fac_name: "城东加压泵站", // 设施名称
        fac_type: "泵站", // 类型
        run_status: "正常" // 状态
      },
      {
        coord_x: 26.783237,
        coord_y: 117.720114,
        water_fac_id: "W002",
        water_fac_name: "南区自来水厂",
        fac_type: "水厂",
        run_status: "异常"
      },
      {
        coord_x: 26.733337,
        coord_y: 117.650114,
        water_fac_id: "W003",
        water_fac_name: "老城区供水管网",
        fac_type: "管网",
        run_status: "正常"
      },
      {
        coord_x: 26.810237,
        coord_y: 117.800777,
        water_fac_id: "W004",
        water_fac_name: "河西污水泵站",
        fac_type: "泵站",
        run_status: "维护"
      },
      {
        coord_x: 26.756237,
        coord_y: 117.712114,
        water_fac_id: "W005",
        water_fac_name: "北郊净水厂",
        fac_type: "水厂",
        run_status: "正常"
      },
      {
        coord_x: 26.832237,
        coord_y: 117.689114,
        water_fac_id: "W006",
        water_fac_name: "工业园区供水管网",
        fac_type: "管网",
        run_status: "异常"
      },
      {
        coord_x: 26.798237,
        coord_y: 117.833777,
        water_fac_id: "W007",
        water_fac_name: "南郊雨水泵站",
        fac_type: "泵站",
        run_status: "正常"
      },
      {
        coord_x: 26.765237,
        coord_y: 117.755114,
        water_fac_id: "W008",
        water_fac_name: "新城区二次供水管网",
        fac_type: "管网",
        run_status: "维护"
      }
    ];
  }
};

// 水务核心资源分布数据
export const fetchCoreResources = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/coreResources`);
    if (Array.isArray(response.data) && response.data.length > 0) {
      return response.data;
    }
    throw new Error('真实接口返回空数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('水务核心资源分布接口调用失败，使用模拟数据:', error.message);
    return [
      // 水厂
      { resource_dist_id: 'res1002', resource_name: '城南水厂', resource_type: '水厂', area: '南城区', remind_status: '2', total_capacity: 200.00, current_capacity: 195.00, usage_rate: 97.50, latest_update_time: '2025-06-10 14:30:00' },
      { resource_dist_id: 'res1010', resource_name: '城北水厂', resource_type: '水厂', area: '南城区', remind_status: '0', total_capacity: 150.00, current_capacity: 60.00, usage_rate: 40.00, latest_update_time: '2025-06-10 11:50:00' },
      { resource_dist_id: 'res1011', resource_name: '东城水厂', resource_type: '水厂', area: '南城区', remind_status: '1', total_capacity: 180.00, current_capacity: 160.00, usage_rate: 88.90, latest_update_time: '2025-06-10 13:20:00' },
      { resource_dist_id: 'res1012', resource_name: '西城水厂', resource_type: '水厂', area: '南城区', remind_status: '0', total_capacity: 220.00, current_capacity: 150.00, usage_rate: 68.20, latest_update_time: '2025-06-10 10:15:00' },
      { resource_dist_id: 'res1013', resource_name: '高新水厂', resource_type: '水厂', area: '高新区', remind_status: '0', total_capacity: 120.00, current_capacity: 80.00, usage_rate: 66.70, latest_update_time: '2025-06-10 09:40:00' },
      { resource_dist_id: 'res1014', resource_name: '滨海水厂', resource_type: '水厂', area: '高新区', remind_status: '1', total_capacity: 250.00, current_capacity: 210.00, usage_rate: 84.00, latest_update_time: '2025-06-10 15:20:00' },
      // 泵站
      { resource_dist_id: 'res1006', resource_name: '高新区加压泵站', resource_type: '泵站', area: '东城区', remind_status: '2', total_capacity: 50.00, current_capacity: 48.00, usage_rate: 96.00, latest_update_time: '2025-06-10 15:45:00' },
      { resource_dist_id: 'res1015', resource_name: '东河泵站', resource_type: '泵站', area: '东城区', remind_status: '0', total_capacity: 40.00, current_capacity: 25.00, usage_rate: 62.50, latest_update_time: '2025-06-10 09:30:00' },
      { resource_dist_id: 'res1016', resource_name: '西河泵站', resource_type: '泵站', area: '西城区', remind_status: '1', total_capacity: 60.00, current_capacity: 52.00, usage_rate: 86.70, latest_update_time: '2025-06-10 14:20:00' },
      { resource_dist_id: 'res1017', resource_name: '北郊泵站', resource_type: '泵站', area: '东城区', remind_status: '0', total_capacity: 35.00, current_capacity: 20.00, usage_rate: 57.10, latest_update_time: '2025-06-10 11:10:00' },
      { resource_dist_id: 'res1018', resource_name: '南郊泵站', resource_type: '泵站', area: '西城区', remind_status: '2', total_capacity: 55.00, current_capacity: 54.00, usage_rate: 98.20, latest_update_time: '2025-06-10 16:30:00' },
      // 水库
      { resource_dist_id: 'res1019', resource_name: '西湖水库', resource_type: '水库', area: '北郊区', remind_status: '0', total_capacity: 1200.00, current_capacity: 850.00, usage_rate: 70.80, latest_update_time: '2025-06-10 08:50:00' },
      { resource_dist_id: 'res1020', resource_name: '南郊水库', resource_type: '水库', area: '南郊区', remind_status: '1', total_capacity: 800.00, current_capacity: 720.00, usage_rate: 90.00, latest_update_time: '2025-06-10 10:20:00' },
      { resource_dist_id: 'res1021', resource_name: '东湖水库', resource_type: '水库', area: '北郊区', remind_status: '0', total_capacity: 950.00, current_capacity: 600.00, usage_rate: 63.20, latest_update_time: '2025-06-10 13:10:00' },
      { resource_dist_id: 'res1022', resource_name: '北郊水库', resource_type: '水库', area: '北郊区', remind_status: '1', total_capacity: 750.00, current_capacity: 680.00, usage_rate: 90.70, latest_update_time: '2025-06-10 14:50:00' },
      // 管网
      { resource_dist_id: 'res1003', resource_name: '主城区供水管网', resource_type: '管网', area: '主城区', remind_status: '0', total_capacity: 300.00, current_capacity: 210.00, usage_rate: 70.00, latest_update_time: '2025-06-10 14:30:00' },
      { resource_dist_id: 'res1007', resource_name: '滨海新区管网', resource_type: '管网', area: '滨海新区', remind_status: '1', total_capacity: 180.00, current_capacity: 153.00, usage_rate: 85.00, latest_update_time: '2025-06-10 16:20:00' },
      { resource_dist_id: 'res1023', resource_name: '东城区管网', resource_type: '管网', area: '东城区', remind_status: '0', total_capacity: 250.00, current_capacity: 160.00, usage_rate: 64.00, latest_update_time: '2025-06-10 11:40:00' },
      { resource_dist_id: 'res1024', resource_name: '南城区管网', resource_type: '管网', area: '滨海新区', remind_status: '1', total_capacity: 200.00, current_capacity: 170.00, usage_rate: 85.00, latest_update_time: '2025-06-10 13:10:00' },
      { resource_dist_id: 'res1025', resource_name: '高新区管网', resource_type: '管网', area: '主城区', remind_status: '2', total_capacity: 150.00, current_capacity: 145.00, usage_rate: 96.70, latest_update_time: '2025-06-10 15:30:00' }
    ];
  }
};

// 企业分布数据
export const fetchEnterpriseDistribution = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/enterpriseDistribution`);
    if (response.data && (Array.isArray(response.data) || typeof response.data === 'object')) {
      return response.data;
    }
    throw new Error('企业分布接口返回数据无效，使用模拟数据兜底');
  } catch (error) {
    console.warn('企业分布接口调用失败，使用模拟数据:', error.message);
    return [
      { dist_id: 'ent_dist1001', area: '东城区', enterprise_type: '工业企业', count: 128, water_consumption: 52000, qualified_rate: 96.8, warning_count: 3, latest_update: '2025-06-10 10:30:00' },
      { dist_id: 'ent_dist1002', area: '西城区', enterprise_type: '商业企业', count: 256, water_consumption: 38000, qualified_rate: 98.2, warning_count: 1, latest_update: '2025-06-10 11:15:00' },
      { dist_id: 'ent_dist1003', area: '南城区', enterprise_type: '工业企业', count: 95, water_consumption: 68000, qualified_rate: 94.5, warning_count: 5, latest_update: '2025-06-10 09:45:00' },
      { dist_id: 'ent_dist1004', area: '北城区', enterprise_type: '混合企业', count: 187, water_consumption: 42000, qualified_rate: 97.3, warning_count: 2, latest_update: '2025-06-10 14:20:00' },
      { dist_id: 'ent_dist1005', area: '高新区', enterprise_type: '高新技术企业', count: 156, water_consumption: 29000, qualified_rate: 99.1, warning_count: 0, latest_update: '2025-06-10 16:05:00' }
    ];
  }
};

// 水务核心指标数据
export const fetchCoreIndicators = async (timeRange = 'today') => {
  try {
    const response = await axios.get(`${BASE_URL}/coreIndicators`, { params: { timeRange } });
    if (Array.isArray(response.data) && response.data.length > 0) {
      return response.data;
    }
    throw new Error('真实接口返回空数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('水务核心指标接口调用失败，使用模拟数据:', error.message);
    return [
      // 1. 供水量（新增实时值、日累计、月累计字段）
      {
        indicator_id: 'ind1001',
        indicator_name: '供水量',
        indicator_code: 'water_supply',
        stat_cycle: '实时',
        // 核心新增字段
        real_time_value: 5.2, // 实时值（万m³/h）
        daily_total: 52.5,    // 日累计（万m³）
        monthly_total: 1580.3, // 月累计（万m³）
        // 原有字段调整
        threshold_min: 40,    // 日累计阈值下限
        threshold_max: 80,    // 日累计阈值上限
        indicator_status: '0', // 0-达标，1-不达标（基于日累计判断）
        stat_time: new Date().toLocaleString(),
        ext1: '实时流量计+累计统计',
        ext2: 'stat_water_quantity', // 数据来源：水量统计表
        unit: '万m³'
      },
      // 2. 水质达标率（新增实时/日/月达标率）
      {
        indicator_id: 'ind1002',
        indicator_name: '水质达标率',
        indicator_code: 'water_quality_rate',
        stat_cycle: '实时',
        // 核心新增字段
        real_time_rate: 96.2, // 实时达标率（%）
        daily_rate: 93.0,     // 日达标率（%）
        monthly_rate: 94.5,   // 月达标率（%）
        // 原有字段调整
        threshold_min: 95,    // 达标阈值（≥95%为达标）
        threshold_max: 100,
        indicator_status: '1', // 不达标（基于日达标率93% < 95%）
        stat_time: new Date().toLocaleString(),
        ext1: '监测点水质合格次数/总次数',
        ext2: 'stat_water_qual', // 数据来源：水质统计表
        unit: '%'
      },
      // 3. 新增漏损率指标
      {
        indicator_id: 'ind1005',
        indicator_name: '漏损率',
        indicator_code: 'water_leak_rate',
        stat_cycle: '实时',
        // 核心字段
        real_time_leak_rate: 12.5, // 实时漏损率（%）
        daily_leak_rate: 11.8,     // 日漏损率（%）
        // 阈值（≤15%为达标）
        threshold_min: 0,
        threshold_max: 15,
        indicator_status: '0', // 达标（11.8% ≤15%）
        stat_time: new Date().toLocaleString(),
        ext1: '（供水量-用水量）/供水量',
        ext2: 'stat_water_leak', // 数据来源：漏损统计表
        unit: '%'
      },
      // 4. 新增投诉响应率指标
      {
        indicator_id: 'ind1006',
        indicator_name: '投诉响应率',
        indicator_code: 'water_complain_resp_rate',
        stat_cycle: '实时',
        // 核心字段
        real_time_resp_rate: 92.3, // 实时响应率（%）
        unresp_count: 3,           // 未响应数（件）
        // 阈值（≥90%为达标）
        threshold_min: 90,
        threshold_max: 100,
        indicator_status: '0', // 达标（92.3% ≥90%）
        stat_time: new Date().toLocaleString(),
        ext1: '已响应投诉数/总投诉数',
        ext2: 'stat_water_complain', // 数据来源：投诉统计表
        unit: '%'
      }
    ];
  }
};

// 水务核心指标近7天趋势数据
export const fetchIndicatorTrend = async (indicatorId) => {
  try {
    const response = await axios.get(`${BASE_URL}/indicatorTrend`, {
      params: { indicatorId, days: 7 }
    });
    if (response.data && response.data.xAxis && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回数据无效，使用模拟数据兜底');
  } catch (error) {
    console.warn('指标7天趋势接口调用失败，使用模拟数据:', error.message);

    // 模拟不同指标的基础值、目标值、单位（区分比率和非比率）
    const indicatorConfig = {
      'ind1001': { base: 52.5, target: 60, unit: '万m³', name: '日供水量', isRatio: false },
      'ind1002': { base: 93.0, target: 95, unit: '%', name: '水质达标率', isRatio: true },
      'ind1003': { base: 98.2, target: 95, unit: '%', name: '设施完好率', isRatio: true },
      'ind1004': { base: 88.5, target: 90, unit: '%', name: '工单响应率', isRatio: true }
    };
    const config = indicatorConfig[indicatorId] || { base: 90, target: 95, unit: '%', name: '指标', isRatio: true };

    // 生成近7天日期和模拟数据
    const xAxis = [];
    const actualData = [];
    const targetData = [];
    const today = new Date();

    for (let i = 6; i >= 0; i--) {
      const date = new Date(today);
      date.setDate(today.getDate() - i);
      xAxis.push(`${date.getMonth() + 1}/${date.getDate()}`);

      // 生成±5%范围内的波动数据（比率指标额外限制0-100%）
      const fluctuationRange = config.base * 0.1; // 波动幅度：基础值的±10%
      const fluctuation = (Math.random() - 0.5) * fluctuationRange;
      let actualValue = config.base + fluctuation;

      // 若为比率指标，限制在0-100%之间
      if (config.isRatio) {
        actualValue = Math.max(0, Math.min(100, actualValue)); // 边界处理
      }

      actualData.push(parseFloat(actualValue.toFixed(2)));
      targetData.push(config.target); // 目标值固定（若目标值为比率，需确保≤100）
    }

    return {
      xAxis,
      series: [
        { name: `实际${config.name}`, data: actualData, type: 'line', lineStyle: { width: 2 } },
        { name: `目标${config.name}`, data: targetData, type: 'line', lineStyle: { type: 'dashed', width: 2 } }
      ],
      unit: config.unit // 返回单位用于Y轴标注
    };
  }
};

// 水务设施运行状态数据
export const fetchFacilityRunStatus = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/facilityRunStatus`);
    if (Array.isArray(response.data) && response.data.length > 0) {
      return response.data;
    }
    throw new Error('真实接口返回空数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('水务设施运行状态接口调用失败，使用模拟数据:', error.message);
    return [
      {
        // 基础信息
        water_fac_id: 'fac1002',
        water_fac_name: '南郊泵站',
        fac_type: '泵站',
        location: '南郊路与环城南街交叉口',

        // 运行状态
        run_status: '异常',
        online_status: '在线',

        // 关键参数（泵站特有）
        flow: 480, // 流量(m³/h)
        flow_thr_min: 500,
        flow_thr_max: 1000,

        // 故障信息
        fault_type: '流量过低',
        fault_time: '2025-06-10 09:20:00',

        // 其他信息
        maintain_user_id: 'maintain01',
        feedback_content: '',
        feedback_time: '',
        create_user: 'system',
        create_time: '2025-01-15 08:00:00',
        ext1: 'SENSOR-2001',
        ext2: '每月一次'
      },
      {
        // 基础信息
        water_fac_id: 'fac1001',
        water_fac_name: '城东水厂',
        fac_type: '水厂',
        location: '城东路与和平路交叉口东北侧',

        // 运行状态
        run_status: '正常',
        online_status: '在线',

        // 关键参数（水厂特有）
        pressure: 0.32, // 压力(MPa)
        pressure_thr_min: 0.25,
        pressure_thr_max: 0.4,

        // 故障信息（无故障）
        fault_type: '',
        fault_time: '',

        // 其他信息
        maintain_user_id: 'maintain02',
        feedback_content: '',
        feedback_time: '',
        create_user: 'system',
        create_time: '2025-01-15 08:00:00',
        ext1: 'SENSOR-3001',
        ext2: '每季度一次'
      },
      {
        // 基础信息
        water_fac_id: 'fac1005',
        water_fac_name: '北区水厂',
        fac_type: '水厂',
        location: '北环路与工业路交叉口西北侧',

        // 运行状态
        run_status: '异常',
        online_status: '在线',

        // 关键参数（水厂特有）
        pressure: 0.30, // 压力(MPa)
        pressure_thr_min: 0.25,
        pressure_thr_max: 0.4,

        // 故障信息
        fault_type: '压力波动',
        fault_time: '2025-06-10 10:15:00',

        // 其他信息
        maintain_user_id: 'maintain02',
        feedback_content: '正在处理，预计1小时内恢复正常',
        feedback_time: '2025-06-10 10:15:00',
        create_user: 'system',
        create_time: '2025-01-15 08:00:00',
        ext1: 'SENSOR-3002',
        ext2: '每季度一次'
      },
      {
        // 基础信息
        water_fac_id: 'fac1006',
        water_fac_name: '河东泵站',
        fac_type: '泵站',
        location: '河东路与滨河路交叉口',

        // 运行状态
        run_status: '维护',
        online_status: '离线',

        // 关键参数（泵站特有）
        flow: 650, // 流量(m³/h)
        flow_thr_min: 500,
        flow_thr_max: 1000,

        // 故障信息
        fault_type: '设备检修',
        fault_time: '2025-06-10 08:00:00',

        // 其他信息
        maintain_user_id: 'maintain01',
        feedback_content: '计划维护中，预计18:00完成',
        feedback_time: '2025-06-10 07:30:00',
        create_user: 'system',
        create_time: '2025-01-15 08:00:00',
        ext1: 'SENSOR-2002',
        ext2: '每月一次'
      },
      {
        // 基础信息
        water_fac_id: 'fac2001',
        water_fac_name: '主城区供水管网',
        fac_type: '管网',
        location: '主城区范围内',

        // 运行状态
        run_status: '正常',
        online_status: '在线',

        // 关键参数（管网特有）
        speed: 1.2, // 流速(m/s)
        speed_thr_min: 0.8,
        speed_thr_max: 1.5,

        // 故障信息（无故障）
        fault_type: '',
        fault_time: '',

        // 其他信息
        maintain_user_id: 'maintain03',
        feedback_content: '',
        feedback_time: '',
        create_user: 'system',
        create_time: '2025-01-15 08:00:00',
        ext1: 'SENSOR-4001',
        ext2: '每半年一次'
      }
    ];
  }
};

// 资源利用率趋势数据
export const fetchResourceTrend = async (resourceId) => {
  try {
    // 模拟真实接口调用（实际项目中替换为真实接口）
    const response = await axios.get(`${BASE_URL}/resourceTrend`, {
      params: { resourceId }
    });
    // 验证接口返回格式
    if (response.data && Array.isArray(response.data.xAxis) && Array.isArray(response.data.series)) {
      return response.data;
    }
    throw new Error('趋势数据格式无效，使用模拟数据兜底');
  } catch (error) {
    console.warn('资源利用率趋势接口调用失败，使用模拟数据:', error.message);

    // 从核心资源数据中匹配当前资源（模拟逻辑，与文件2保持一致）
    // 注意：实际项目中可通过接口单独获取资源详情，这里简化处理
    const coreResources = await fetchCoreResources(); // 复用现有核心资源接口
    const resource = coreResources.find(r => r.resource_dist_id === resourceId);
    const baseRate = resource ? resource.usage_rate : 70; // 默认基础利用率

    // 生成7天模拟趋势数据（迁移自文件2的changeTrendResource逻辑）
    const xAxis = [];
    const data = [];
    const today = new Date();
    for (let i = 6; i >= 0; i--) {
      const date = new Date(today);
      date.setDate(today.getDate() - i);
      xAxis.push(`${date.getMonth() + 1}/${date.getDate()}`);

      // 基于基础利用率添加随机波动
      const fluctuation = (Math.random() - 0.5) * 10;
      data.push(parseFloat((baseRate + fluctuation).toFixed(2)));
    }

    return {
      xAxis,
      series: [{
        name: resource ? `${resource.resource_name}利用率` : '资源利用率',
        data
      }]
    };
  }
};

// 近期预警滚动数据
export const fetchWarningScrollData = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/warningScrollData`);
    if (Array.isArray(response.data) && response.data.length > 0) {
      return response.data;
    }
    throw new Error('真实接口返回空数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('近期预警滚动数据接口调用失败，使用模拟数据:', error.message);
    return [
      ['南郊区域', '泵站流量过低', '09:20', '未处理'],
      ['北区', '水厂浊度超标', '10:15', '处理中'],
      ['主城区', '管网压力不足', '11:30', '未处理'],
      ['东区', '水质异常', '13:45', '已解决'],
      ['西区', '泵站压力异常', '14:20', '处理中'],
      ['南区', '供水量突增', '15:10', '已解决']
    ];
  }
};

// 确认预警信息
export const confirmWarning = async (params) => {
  try {
    const response = await axios.post(`${BASE_URL}/confirmWarning`, params);
    if (response.data && response.data.success) {
      return response.data;
    }
    throw new Error('真实接口返回无效结果，使用模拟结果兜底');
  } catch (error) {
    console.warn('确认预警接口调用失败，使用模拟结果:', error.message);
    return {
      success: true,
      message: '预警确认成功',
      data: {
        confirmTime: new Date().toLocaleString()
      }
    };
  }
};

// 提交运维反馈
export const submitFeedback = async (params) => {
  try {
    const response = await axios.post(`${BASE_URL}/submitFeedback`, params);
    if (response.data && response.data.success) {
      return response.data;
    }
    throw new Error('真实接口返回无效结果，使用模拟结果兜底');
  } catch (error) {
    console.warn('提交运维反馈接口调用失败，使用模拟结果:', error.message);
    return {
      success: true,
      message: '反馈提交成功',
      data: {
        feedbackTime: new Date().toLocaleString()
      }
    };
  }
};
