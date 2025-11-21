// 引入axios（假设项目中已配置axios）
import axios from 'axios';

// 基础URL，可根据实际项目配置
const BASE_URL = '/api/eco';

// 环保全域数据概览相关接口
export const fetchEcoGlobalOverview = async () => {
  try {
    // 1. 优先调用真实接口
    const response = await axios.get(`${BASE_URL}/globalOverview`);
    // 验证接口返回数据有效性
    if (Array.isArray(response.data) && response.data.length > 0) {
      return response.data; // 返回真实数据
    }
    throw new Error('真实接口返回空数据，使用模拟数据兜底');
  } catch (error) {
    // 2. 接口调用失败，返回模拟数据
    console.warn('环保全域数据概览接口调用失败，使用模拟数据:', error.message);
    return new Promise(resolve => {
      setTimeout(() => {
        resolve([
          {
            overview_id: '1',
            stat_time: '2025-10-13 08:00:00',
            air_quality_days: 25,       // 达标天数
            air_total_days: 30,         // 总天数
            drinking_water_normal: 28,  // 达标水源地数量
            drinking_water_total: 30,   // 总水源地数量
            pollutant_online_count: 42, // 在线污染源数量
            pollutant_total_count: 45,  // 总重点污染源数量
            solid_dispose_completed: 343, // 已处置固废量
            solid_dispose_total: 356.8,   // 总固废量
            warn_status: '0',
            report_id: 'report_123',
            create_user: 'system',
            create_time: '2025-10-13 08:00:00',
            ext1: '每小时更新',
            ext2: ''
          },
          {
            overview_id: '2',
            stat_time: '2025-10-13 09:00:00',
            air_quality_days: 23,
            air_total_days: 30,
            drinking_water_normal: 27,
            drinking_water_total: 30,
            pollutant_online_count: 40,
            pollutant_total_count: 45,
            solid_dispose_completed: 348,
            solid_dispose_total: 362.1,
            warn_status: '1',
            report_id: 'report_124',
            create_user: 'system',
            create_time: '2025-10-13 09:00:00',
            ext1: '每小时更新',
            ext2: '空气'
          }
        ]);
      }, 500);
    });
  }
};

// 区域生态格局视图相关接口
export const fetchEcoGeometries = async () => {
  try {
    // 1. 优先调用真实接口
    const response = await axios.get(`${BASE_URL}/geometries`);
    // 验证接口返回数据有效性（需为数组）
    if (Array.isArray(response.data) && response.data.length > 0) {
      return response.data; // 返回真实数据
    }
    throw new Error('真实接口返回空数据，使用模拟数据兜底');
  } catch (error) {
    // 2. 接口调用失败，返回文件2迁移过来的模拟数据
    console.warn('区域生态格局接口调用失败，使用模拟数据:', error.message);
    return new Promise(resolve => {
      setTimeout(() => {
        resolve([
          // 生态功能区 - 自然保护区
          {
            type: "ecoFunction", // 图层类型
            subType: "natureReserve", // 子类型（用于区分生态功能区的具体类型）
            name: "城东自然保护区",
            area: "245.6 公顷",
            level: "国家级",
            species: "樟树、白鹭、野兔",
            establishYear: "1992年",
            paths: [ // 多边形坐标路径
              { coord_x: 26.85, coord_y: 117.75 },
              { coord_x: 26.82, coord_y: 117.78 },
              { coord_x: 26.79, coord_y: 117.76 },
              { coord_x: 26.82, coord_y: 117.73 }
            ]
          },
          // 生态功能区 - 湿地
          {
            type: "ecoFunction",
            subType: "wetland",
            name: "南湖湿地",
            area: "128.3 公顷",
            level: "市级",
            species: "芦苇、野鸭、鲤鱼",
            establishYear: "2005年",
            paths: [
              { coord_x: 26.75, coord_y: 117.80 },
              { coord_x: 26.72, coord_y: 117.83 },
              { coord_x: 26.69, coord_y: 117.81 },
              { coord_x: 26.72, coord_y: 117.78 }
            ]
          },
          // 生态功能区 - 林地
          {
            type: "ecoFunction",
            subType: "woodland",
            name: "西郊防护林",
            area: "312.7 公顷",
            level: "区级",
            species: "松树、杨树、喜鹊",
            establishYear: "1998年",
            paths: [
              { coord_x: 26.80, coord_y: 117.65 },
              { coord_x: 26.77, coord_y: 117.68 },
              { coord_x: 26.74, coord_y: 117.66 },
              { coord_x: 26.77, coord_y: 117.63 }
            ]
          },
          // 生态红线
          {
            type: "ecoRedLine",
            name: "生态保护红线",
            paths: [
              { coord_x: 26.82, coord_y: 117.72 },
              { coord_x: 26.79, coord_y: 117.75 },
              { coord_x: 26.76, coord_y: 117.73 },
              { coord_x: 26.79, coord_y: 117.70 }
            ]
          },
          // 行政区划
          {
            type: "adminDivision",
            name: "市区行政区划",
            paths: [
              { coord_x: 26.88, coord_y: 117.65 },
              { coord_x: 26.72, coord_y: 117.70 },
              { coord_x: 26.68, coord_y: 117.85 },
              { coord_x: 26.84, coord_y: 117.90 }
            ]
          },
          // 污染源 - 低污染
          {
            type: "pollutionSource",
            subType: "low", // 污染级别
            name: "轻型工业区",
            pollutants: "少量粉尘、噪声",
            emission: "日均5吨",
            area: "0.8平方公里",
            pollutionType: "工业污染",
            paths: [
              { coord_x: 26.85, coord_y: 117.82 },
              { coord_x: 26.83, coord_y: 117.85 },
              { coord_x: 26.81, coord_y: 117.83 },
              { coord_x: 26.83, coord_y: 117.80 }
            ]
          },
          // 污染源 - 中污染
          {
            type: "pollutionSource",
            subType: "medium",
            name: "化工集中区",
            pollutants: "有机废气、废水",
            emission: "日均25吨",
            area: "1.2平方公里",
            pollutionType: "化工污染",
            paths: [
              { coord_x: 26.72, coord_y: 117.82 },
              { coord_x: 26.70, coord_y: 117.85 },
              { coord_x: 26.68, coord_y: 117.83 },
              { coord_x: 26.70, coord_y: 117.80 }
            ]
          },
          // 污染源 - 高污染
          {
            type: "pollutionSource",
            subType: "high",
            name: "重工业区",
            pollutants: "重金属、有毒气体",
            emission: "日均80吨",
            area: "1.5平方公里",
            pollutionType: "重工业污染",
            paths: [
              { coord_x: 26.80, coord_y: 117.62 },
              { coord_x: 26.78, coord_y: 117.65 },
              { coord_x: 26.76, coord_y: 117.63 },
              { coord_x: 26.78, coord_y: 117.60 }
            ]
          }
        ]);
      }, 500);
    });
  }
};

// 导出环保全域数据
export const exportEcoGlobalOverview = async (params) => {
  try {
    // 1. 优先调用真实接口
    const response = await axios.post(`${BASE_URL}/exportGlobalOverview`, params);
    // 验证接口返回数据有效性
    if (response.data && response.data.success) {
      return response.data; // 返回真实结果
    }
    throw new Error('真实接口返回无效结果，使用模拟结果兜底');
  } catch (error) {
    // 2. 接口调用失败，返回模拟结果
    console.warn('导出环保全域数据接口调用失败，使用模拟结果:', error.message);
    return new Promise(resolve => {
      setTimeout(() => {
        resolve({ success: true, message: '数据导出成功' });
      }, 800);
    });
  }
};

// 查看环保分析报告
export const viewEcoAnalysisReport = async (reportId) => {
  try {
    // 1. 优先调用真实接口
    const response = await axios.get(`${BASE_URL}/analysisReport/${reportId}`);
    // 验证接口返回数据有效性
    if (response.data && response.data.report_id) {
      return response.data; // 返回真实结果
    }
    throw new Error('真实接口返回无效结果，使用模拟结果兜底');
  } catch (error) {
    // 2. 接口调用失败，返回模拟结果
    console.warn('查看环保分析报告接口调用失败，使用模拟结果:', error.message);
    return new Promise(resolve => {
      setTimeout(() => {
        resolve({
          report_id: reportId,
          title: `环保全域分析报告 ${new Date().toLocaleDateString()}`,
          content: '本报告包含空气、水、噪声、固废等环保领域的监测数据汇总分析...',
          stat_time: new Date().toISOString().slice(0, 19).replace('T', ' '),
          create_user: 'system'
        });
      }, 500);
    });
  }
};

// 环保核心指标相关接口
export const fetchEcoCoreIndicators = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/coreIndicators`);
    if (Array.isArray(response.data) && response.data.length > 0) {
      return response.data;
    }
    throw new Error('真实接口返回空数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('环保核心指标接口调用失败，使用模拟数据:', error.message);
    return new Promise(resolve => {
      setTimeout(() => {
        resolve([
          // 1. AQI优良率
          {
            indicator_id: '1',
            indicator_name: 'AQI优良率',
            indicator_code: 'air_aqi_good_rate',
            real_value: 88.5,
            unit: '%',
            threshold_min: 85,
            threshold_max: 100,
            warn_status: '1',
            compliance_rate: 88.5,
            year_on_year: -1.2,
            // 保留7天趋势
            trend_7d: JSON.stringify({
              xAxis: ['10/7', '10/8', '10/9', '10/10', '10/11', '10/12', '10/13'],
              series: [86.2, 87.5, 88.1, 87.8, 88.3, 88.4, 88.5]
            }),
            // 新增24小时趋势数据
            trend_24h: JSON.stringify({
              xAxis: ['00:00', '01:00', '02:00', '03:00', '04:00', '05:00', '06:00', '07:00',
                '08:00', '09:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00',
                '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00', '23:00'],
              series: [87.2, 86.8, 86.5, 86.3, 86.1, 86.0, 86.2, 86.7,
                87.3, 87.8, 88.1, 88.3, 88.5, 88.6, 88.7, 88.6,
                88.5, 88.4, 88.3, 88.2, 88.1, 88.0, 87.9, 88.5]
            }),
            abnormal_reason: '',
            update_time: '2025-10-13 09:30:00',
            create_user: 'system',
            create_time: '2025-10-13 00:00:00',
            ext1: 'stat_mon_evt_rpt',
            ext2: '空气质量事件'
          },

          // 2. 饮用水水源地达标率
          {
            indicator_id: '2',
            indicator_name: '饮用水水源地达标率',
            indicator_code: 'water_source_qualified_rate',
            real_value: 100,
            unit: '%',
            threshold_min: 100,
            threshold_max: 100,
            warn_status: '0',
            compliance_rate: 100,
            year_on_year: 0,
            trend_7d: JSON.stringify({
              xAxis: ['10/7', '10/8', '10/9', '10/10', '10/11', '10/12', '10/13'],
              series: [100, 100, 100, 100, 100, 100, 100]
            }),
            trend_24h: JSON.stringify({
              xAxis: ['00:00', '01:00', '02:00', '03:00', '04:00', '05:00', '06:00', '07:00',
                '08:00', '09:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00',
                '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00', '23:00'],
              series: Array(24).fill(100) // 24小时均为100%
            }),
            abnormal_reason: '',
            update_time: '2025-10-13 09:30:00',
            create_user: 'system',
            create_time: '2025-10-13 00:00:00',
            ext1: 'stat_mon_comp_rpt',
            ext2: '饮用水监测'
          },

          // 3. 重点污染源超标次数
          {
            indicator_id: '3',
            indicator_name: '重点污染源超标次数',
            indicator_code: 'key_pollutant_overstandard_count',
            real_value: 12,
            unit: '次',
            threshold_min: 0,
            threshold_max: 5,
            warn_status: '1',
            compliance_rate: 41.7,
            year_on_year: 33.3,
            trend_7d: JSON.stringify({
              xAxis: ['10/7', '10/8', '10/9', '10/10', '10/11', '10/12', '10/13'],
              series: [3, 5, 7, 9, 10, 11, 12]
            }),
            trend_24h: JSON.stringify({
              xAxis: ['00:00', '01:00', '02:00', '03:00', '04:00', '05:00', '06:00', '07:00',
                '08:00', '09:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00',
                '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00', '23:00'],
              series: [1, 0, 0, 0, 1, 0, 0, 2, 1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1]
            }),
            abnormal_reason: '化工园区3家企业废气排放超标，污水处理厂2次COD超标',
            update_time: '2025-10-13 09:30:00',
            create_user: 'system',
            create_time: '2025-10-13 00:00:00',
            ext1: 'stat_mon_evt_rpt',
            ext2: '污染源超标事件'
          },

          // 4. 固废无害化处置率
          {
            indicator_id: '4',
            indicator_name: '固废无害化处置率',
            indicator_code: 'solid_waste_harmless_rate',
            real_value: 92.3,
            unit: '%',
            threshold_min: 85,
            threshold_max: 100,
            warn_status: '0',
            compliance_rate: 92.3,
            year_on_year: 2.1,
            trend_7d: JSON.stringify({
              xAxis: ['10/7', '10/8', '10/9', '10/10', '10/11', '10/12', '10/13'],
              series: [90.1, 90.5, 91.2, 91.8, 92.0, 92.2, 92.3]
            }),
            trend_24h: JSON.stringify({
              xAxis: ['00:00', '01:00', '02:00', '03:00', '04:00', '05:00', '06:00', '07:00',
                '08:00', '09:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00',
                '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00', '23:00'],
              series: [91.5, 91.6, 91.7, 91.8, 91.8, 91.9, 92.0, 92.0,
                92.1, 92.1, 92.2, 92.2, 92.2, 92.3, 92.3, 92.3,
                92.3, 92.3, 92.3, 92.3, 92.3, 92.3, 92.3, 92.3]
            }),
            abnormal_reason: '',
            update_time: '2025-10-13 09:30:00',
            create_user: 'system',
            create_time: '2025-10-13 00:00:00',
            ext1: 'stat_app_scene_rpt',
            ext2: '固废处置'
          }
        ]);
      }, 500);
    });
  }
};

// 更新环保核心指标异常原因
export const updateIndicatorReason = async (params) => {
  try {
    // 1. 优先调用真实接口
    const response = await axios.post(`${BASE_URL}/updateIndicatorReason`, params);
    // 验证接口返回数据有效性
    if (response.data && response.data.success) {
      return response.data; // 返回真实结果
    }
    throw new Error('真实接口返回无效结果，使用模拟结果兜底');
  } catch (error) {
    // 2. 接口调用失败，返回模拟结果
    console.warn('更新环保指标异常原因接口调用失败，使用模拟结果:', error.message);
    return new Promise(resolve => {
      setTimeout(() => {
        resolve({ success: true, message: '异常原因更新成功' });
      }, 500);
    });
  }
};

// 更新环保核心指标阈值
export const updateIndicatorThreshold = async (params) => {
  try {
    // 1. 优先调用真实接口
    const response = await axios.post(`${BASE_URL}/updateIndicatorThreshold`, params);
    // 验证接口返回数据有效性
    if (response.data && response.data.success) {
      return response.data; // 返回真实结果
    }
    throw new Error('真实接口返回无效结果，使用模拟结果兜底');
  } catch (error) {
    // 2. 接口调用失败，返回模拟结果
    console.warn('更新环保指标阈值接口调用失败，使用模拟结果:', error.message);
    return new Promise(resolve => {
      setTimeout(() => {
        resolve({ success: true, message: '阈值更新成功' });
      }, 500);
    });
  }
};

// 区域生态格局视图相关接口
export const fetchEcoRegionPatterns = async () => {
  try {
    // 1. 优先调用真实接口
    const response = await axios.get(`${BASE_URL}/regionPatterns`);
    // 验证接口返回数据有效性
    if (Array.isArray(response.data) && response.data.length > 0) {
      return response.data; // 返回真实数据
    }
    throw new Error('真实接口返回空数据，使用模拟数据兜底');
  } catch (error) {
    // 2. 接口调用失败，返回模拟数据
    console.warn('区域生态格局接口调用失败，使用模拟数据:', error.message);
    return new Promise(resolve => {
      setTimeout(() => {
        resolve([
          {
            pattern_id: '1',
            region_id: 'region_1',
            region_name: '东部生态红线区',
            eco_region_type: '生态红线区',
            region_boundary: JSON.stringify([
              { lng: 116.3, lat: 39.9 },
              { lng: 116.4, lat: 39.9 },
              { lng: 116.4, lat: 40.0 },
              { lng: 116.3, lat: 40.0 }
            ]),
            core_eco_indicator: '植被覆盖率:85%,物种数量:120种',
            monitor_point_count: 8,
            warn_status: '0',
            warn_reason: '',
            update_time: '2025-10-13 00:00:00',
            create_user: 'system',
            create_time: '2025-10-01 00:00:00',
            ext1: '国家级',
            ext2: 'layer_101'
          },
          {
            pattern_id: '2',
            region_id: 'region_2',
            region_name: '南部湿地保护区',
            eco_region_type: '湿地保护区',
            region_boundary: JSON.stringify([
              { lng: 116.5, lat: 39.7 },
              { lng: 116.6, lat: 39.7 },
              { lng: 116.6, lat: 39.8 },
              { lng: 116.5, lat: 39.8 }
            ]),
            core_eco_indicator: '水位:1.2m,水质等级:Ⅱ类',
            monitor_point_count: 5,
            warn_status: '1',
            warn_reason: '发现周边企业违规排放污水',
            update_time: '2025-10-13 00:00:00',
            create_user: 'system',
            create_time: '2025-10-01 00:00:00',
            ext1: '省级',
            ext2: 'layer_102'
          },
          {
            pattern_id: '3',
            region_id: 'region_3',
            region_name: '西部植被覆盖区',
            eco_region_type: '植被覆盖区',
            region_boundary: JSON.stringify([
              { lng: 116.1, lat: 39.8 },
              { lng: 116.2, lat: 39.8 },
              { lng: 116.2, lat: 39.9 },
              { lng: 116.1, lat: 39.9 }
            ]),
            core_eco_indicator: '覆盖率:72%,生长状况:良好',
            monitor_point_count: 6,
            warn_status: '0',
            warn_reason: '',
            update_time: '2025-10-13 00:00:00',
            create_user: 'system',
            create_time: '2025-10-01 00:00:00',
            ext1: '市级',
            ext2: 'layer_103'
          },
          {
            pattern_id: '4',
            region_id: 'region_4',
            region_name: '北部水源涵养区',
            eco_region_type: '水源涵养区',
            region_boundary: JSON.stringify([
              { lng: 116.2, lat: 40.1 },
              { lng: 116.3, lat: 40.1 },
              { lng: 116.3, lat: 40.2 },
              { lng: 116.2, lat: 40.2 }
            ]),
            core_eco_indicator: '蓄水量:1200万m³,水质:Ⅰ类',
            monitor_point_count: 7,
            warn_status: '0',
            warn_reason: '',
            update_time: '2025-10-13 00:00:00',
            create_user: 'system',
            create_time: '2025-10-01 00:00:00',
            ext1: '国家级',
            ext2: 'layer_104'
          }
        ]);
      }, 500);
    });
  }
};

// 处理区域生态预警
export const handleRegionWarning = async (params) => {
  try {
    // 1. 优先调用真实接口
    const response = await axios.post(`${BASE_URL}/handleRegionWarning`, params);
    // 验证接口返回数据有效性
    if (response.data && response.data.success) {
      return response.data; // 返回真实结果
    }
    throw new Error('真实接口返回无效结果，使用模拟结果兜底');
  } catch (error) {
    // 2. 接口调用失败，返回模拟结果
    console.warn('处理区域生态预警接口调用失败，使用模拟结果:', error.message);
    return new Promise(resolve => {
      setTimeout(() => {
        resolve({ success: true, message: '预警处理成功' });
      }, 500);
    });
  }
};

// 污染源分布视图相关接口
export const fetchPollutantDistributions = async () => {
  try {
    // 1. 优先调用真实接口
    const response = await axios.get(`${BASE_URL}/pollutantDistributions`);
    // 验证接口返回数据有效性
    if (Array.isArray(response.data) && response.data.length > 0) {
      return response.data; // 返回真实数据
    }
    throw new Error('真实接口返回空数据，使用模拟数据兜底');
  } catch (error) {
    // 2. 接口调用失败，返回模拟数据
    console.warn('污染源分布接口调用失败，使用模拟数据:', error.message);
    return new Promise(resolve => {
      setTimeout(() => {
        resolve([
          {
            distribution_id: '1',
            pollutant_id: 'pollutant_1',
            pollutant_name: '东方化工厂',
            pollutant_type: '工业源', // 对应sys_mon_comp_cat表的cat_name（工业源）
            coordinate: '116.35,39.92', // 对应biz_mon_comp_info表的coord_x,coord_y
            real_emission_index: 'SO₂:45mg/m³,NOx:32mg/m³,PM2.5:15μg/m³', // TB时序数据
            overstandard_status: '0', // 对应stat_mon_evt_rpt表的abn_comp_count（0未超标/1超标）
            belong_region: '东区',
            ext_cat1: '张三', // 对应biz_mon_comp_info表的ext_cat1（联系人）
            last_supervise_time: '2025-10-12 14:30:00',
            update_time: '2025-10-13 09:00:00',
            create_user: 'system',
            create_time: '2025-10-01 00:00:00',
            ext1: 'A级',
            ext2: '每日'
          },
          {
            distribution_id: '2',
            pollutant_id: 'pollutant_2',
            pollutant_name: '南方养殖场',
            pollutant_type: '农业源', // 农业源
            coordinate: '116.42,39.85',
            real_emission_index: 'COD:85mg/L,氨氮:15mg/L', // TB时序数据（COD、氨氮）
            overstandard_status: '1', // 超标
            belong_region: '南区',
            ext_cat1: '李四', // 联系人
            last_supervise_time: '2025-10-12 10:15:00',
            update_time: '2025-10-13 09:00:00',
            create_user: 'system',
            create_time: '2025-10-01 00:00:00',
            ext1: 'B级',
            ext2: '每周'
          },
          {
            distribution_id: '3',
            pollutant_id: 'pollutant_3',
            pollutant_name: '城西污水处理厂',
            pollutant_type: '生活源', // 生活源
            coordinate: '116.25,39.88',
            real_emission_index: 'COD:45mg/L,SS:12mg/L',
            overstandard_status: '0',
            belong_region: '西区',
            ext_cat1: '王五', // 联系人
            last_supervise_time: '2025-10-13 08:00:00',
            update_time: '2025-10-13 09:00:00',
            create_user: 'system',
            create_time: '2025-10-01 00:00:00',
            ext1: 'A级',
            ext2: '每日'
          },
          {
            distribution_id: '4',
            pollutant_id: 'pollutant_4',
            pollutant_name: '城北交通枢纽',
            pollutant_type: '移动源', // 移动源（原机动车调整为移动源）
            coordinate: '116.32,40.05',
            real_emission_index: 'CO:3.2mg/m³,HC:0.8mg/m³',
            overstandard_status: '1', // 超标
            belong_region: '北区',
            ext_cat1: '赵六', // 联系人
            last_supervise_time: '2025-10-12 16:45:00',
            update_time: '2025-10-13 09:00:00',
            create_user: 'system',
            create_time: '2025-10-01 00:00:00',
            ext1: 'B级',
            ext2: '每周'
          },
          {
            distribution_id: '5',
            pollutant_id: 'pollutant_5',
            pollutant_name: '中南钢铁厂',
            pollutant_type: '工业源',
            coordinate: '116.38,39.90',
            real_emission_index: 'SO₂:58mg/m³,粉尘:22mg/m³',
            overstandard_status: '1',
            belong_region: '中区',
            ext_cat1: '孙七', // 联系人
            last_supervise_time: '2025-10-12 09:20:00',
            update_time: '2025-10-13 09:00:00',
            create_user: 'system',
            create_time: '2025-10-01 00:00:00',
            ext1: 'C级',
            ext2: '每日'
          },
          {
            distribution_id: '6',
            pollutant_id: 'pollutant_6',
            pollutant_name: '东南汽车制造厂',
            pollutant_type: '工业源',
            coordinate: '116.45,39.89',
            real_emission_index: 'VOCs:32mg/m³,苯系物:0.6mg/m³',
            overstandard_status: '0',
            belong_region: '东南区',
            ext_cat1: '周八',
            last_supervise_time: '2025-10-13 10:00:00',
            update_time: '2025-10-13 11:30:00',
            create_user: 'system',
            create_time: '2025-10-02 00:00:00',
            ext1: 'B级',
            ext2: '每日'
          },
          {
            distribution_id: '7',
            pollutant_id: 'pollutant_7',
            pollutant_name: '西郊蔬菜种植基地',
            pollutant_type: '农业源',
            coordinate: '116.20,39.83',
            real_emission_index: '化肥残留:12mg/kg,农药残留:0.08mg/kg',
            overstandard_status: '1',
            belong_region: '西区',
            ext_cat1: '吴九',
            last_supervise_time: '2025-10-12 11:20:00',
            update_time: '2025-10-13 08:45:00',
            create_user: 'system',
            create_time: '2025-10-02 00:00:00',
            ext1: 'C级',
            ext2: '每周'
          },
          {
            distribution_id: '8',
            pollutant_name: '城中生活垃圾处理厂',
            pollutant_id: 'pollutant_8',
            pollutant_type: '生活源',
            coordinate: '116.33,39.93',
            real_emission_index: '硫化氢:10mg/m³,甲烷:0.5%',
            overstandard_status: '0',
            belong_region: '中区',
            ext_cat1: '郑十',
            last_supervise_time: '2025-10-13 09:15:00',
            update_time: '2025-10-13 10:30:00',
            create_user: 'system',
            create_time: '2025-10-03 00:00:00',
            ext1: 'A级',
            ext2: '每日'
          },
          {
            distribution_id: '9',
            pollutant_id: 'pollutant_9',
            pollutant_name: '滨海物流中心',
            pollutant_type: '移动源',
            coordinate: '116.48,39.96',
            real_emission_index: 'NOx:2.8g/km,颗粒物:0.3g/km',
            overstandard_status: '1',
            belong_region: '东区',
            ext_cat1: '王十一',
            last_supervise_time: '2025-10-12 15:30:00',
            update_time: '2025-10-13 09:45:00',
            create_user: 'system',
            create_time: '2025-10-03 00:00:00',
            ext1: 'B级',
            ext2: '每周'
          },
          {
            distribution_id: '10',
            pollutant_id: 'pollutant_10',
            pollutant_name: '西北建材厂',
            pollutant_type: '工业源',
            coordinate: '116.28,40.02',
            real_emission_index: '颗粒物:42mg/m³,二氧化硅:9mg/m³',
            overstandard_status: '1',
            belong_region: '北区',
            ext_cat1: '杨十二',
            last_supervise_time: '2025-10-13 07:50:00',
            update_time: '2025-10-13 08:20:00',
            create_user: 'system',
            create_time: '2025-10-04 00:00:00',
            ext1: 'C级',
            ext2: '每日'
          }
        ]);
      }, 500);
    });
  }
};

// 获取预警信息滚动数据
export const fetchWarningScrollData = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/warningScrollData`);
    if (Array.isArray(response.data) && response.data.length > 0) {
      return response.data;
    }
    throw new Error('真实接口返回空数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('预警滚动数据接口调用失败，使用模拟数据:', error.message);
    return [
      ['东区', '空气质量不达标', '09:20', '未处理'],
      ['南区', '农业污染源超标', '10:15', '处理中'],
      ['西区', '噪声超标', '11:30', '未处理'],
      ['北区', '机动车尾气超标', '13:45', '已解决'],
      ['中区', '工业排放超标', '14:20', '处理中']
    ];
  }
};

// 获取预警类型数据
export const fetchWarningTypes = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/warningTypes`);
    if (Array.isArray(response.data) && response.data.length > 0) {
      return response.data;
    }
    throw new Error('真实接口返回空数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('预警类型数据接口调用失败，使用模拟数据:', error.message);
    return [
      {
        title: '空气质量预警',
        content: '东区空气质量达标率低于85%，请及时处理'
      },
      {
        title: '污染源超标预警',
        content: '中南钢铁厂SO₂排放超标，已持续2小时'
      },
      {
        title: '生态区域预警',
        content: '南部湿地保护区发现违规排污'
      }
    ];
  }
};

// 提交污染源监管记录
export const submitPollutantSupervise = async (params) => {
  try {
    // 1. 优先调用真实接口
    const response = await axios.post(`${BASE_URL}/submitPollutantSupervise`, params);
    // 验证接口返回数据有效性
    if (response.data && response.data.success) {
      return response.data; // 返回真实结果
    }
    throw new Error('真实接口返回无效结果，使用模拟结果兜底');
  } catch (error) {
    // 2. 接口调用失败，返回模拟结果
    console.warn('提交污染源监管记录接口调用失败，使用模拟结果:', error.message);
    return new Promise(resolve => {
      setTimeout(() => {
        resolve({ success: true, message: '监管记录提交成功' });
      }, 500);
    });
  }
};


// 空气质量专题视图相关接口
export const fetchAirQualityTrend = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/airQualityTrend`);
    if (response.data && response.data.xAxis && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('空气质量趋势接口调用失败，使用模拟数据:', error.message);
    // 生成30天日期（用于趋势图）
    const today = new Date();
    const dates = [];
    for (let i = 29; i >= 0; i--) {
      const date = new Date(today);
      date.setDate(today.getDate() - i);
      dates.push(`${date.getMonth() + 1}/${date.getDate()}`);
    }
    return {
      xAxis: dates,
      series: [
        { name: 'PM2.5', data: Array(30).fill(0).map(() => Math.floor(Math.random() * 50) + 10) },
        { name: 'PM10', data: Array(30).fill(0).map(() => Math.floor(Math.random() * 80) + 30) },
        { name: 'AQI', data: Array(30).fill(0).map(() => Math.floor(Math.random() * 100) + 30) }
      ]
    };
  }
};

export const fetchAirQualityStations = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/airQualityStations`);
    if (Array.isArray(response.data) && response.data.length > 0) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('空气质量监测站点接口调用失败，使用模拟数据:', error.message);
    return [
      {
        station_id: 'AQ-001',
        station_name: '城区监测点',
        lng: 116.4,
        lat: 39.9,
        region: '市中心',
        region_type: 'urban',
        station_type: 'standard',
        establish_date: '2020-05-15',
        real_time_aqi: 58,
        pm25: 32,
        pm10: 65,
        so2: 15,
        no2: 45,
        o3: 85,
        main_pollutant: 'PM2.5',
        status: '正常',
        device_status: '运行中',
        update_time: '2025-10-30 14:30:00',
        calibration_time: '2025-10-15',
        address: '人民广场东侧'
      },
      {
        station_id: 'AQ-002',
        station_name: '工业区监测点',
        lng: 116.5,
        lat: 39.8,
        region: '东部工业区',
        region_type: 'industrial',
        station_type: 'standard',
        establish_date: '2019-11-20',
        real_time_aqi: 120,
        pm25: 68,
        pm10: 110,
        so2: 35,
        no2: 75,
        o3: 92,
        main_pollutant: 'PM10',
        status: '轻度污染',
        device_status: '运行中',
        update_time: '2025-10-30 14:30:00',
        calibration_time: '2025-10-10',
        address: '化工园区北门'
      },
      {
        station_id: 'AQ-003',
        station_name: '郊区监测点',
        lng: 116.3,
        lat: 40.0,
        region: '西部郊区',
        region_type: 'suburban',
        station_type: 'background',
        establish_date: '2021-03-05',
        real_time_aqi: 45,
        pm25: 18,
        pm10: 42,
        so2: 8,
        no2: 22,
        o3: 75,
        main_pollutant: '',
        status: '正常',
        device_status: '运行中',
        update_time: '2025-10-30 14:30:00',
        calibration_time: '2025-10-05',
        address: '生态公园内'
      }
    ];
  }
};

export const fetchAirQualityCoreIndicators = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/airQualityCoreIndicators`);
    if (response.data && typeof response.data === 'object') {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('空气质量核心指标接口调用失败，使用模拟数据:', error.message);
    return {
      currentAqi: 65,
      pm25Value: 32,
      pm25Change: -5,
      pm10Value: 78,
      pm10Change: 3,
      qualifiedDays: 22,
      totalDays: 30,
      qualifiedRate: Math.round((22 / 30) * 100)
    };
  }
};

export const fetchAirQualityStationTrend = async (stationId) => {
  try {
    const response = await axios.get(`${BASE_URL}/airQualityStationTrend/${stationId}`);
    if (response.data && response.data.xAxis && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('空气质量站点详情趋势接口调用失败，使用模拟数据:', error.message);
    return {
      xAxis: ['00:00', '04:00', '08:00', '12:00', '16:00', '20:00'],
      series: [
        { name: 'PM2.5', data: [32, 28, 35, 42, 38, 32] },
        { name: 'PM10', data: [65, 60, 72, 85, 78, 70] },
        { name: 'AQI', data: [58, 62, 75, 82, 70, 65] }
      ]
    };
  }
};

export const fetchAirQualityDetails = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/airQualityDetails`);
    if (Array.isArray(response.data) && response.data.length > 0) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('空气质量详细数据接口调用失败，使用模拟数据:', error.message);
    // 生成表格数据
    const today = new Date();
    const stationIds = ['AQ-001', 'AQ-002', 'AQ-003'];
    const data = [];

    // 生成过去30天的数据（每天8-17点）
    for (let day = 29; day >= 0; day--) {
      for (let hour = 8; hour <= 17; hour++) {
        const date = new Date(today);
        date.setDate(today.getDate() - day);
        const regionTypeIndex = Math.floor(Math.random() * 3);
        const region = regionTypeIndex === 0 ? '城区' : regionTypeIndex === 1 ? '郊区' : '工业区';
        const stationId = stationIds[Math.floor(Math.random() * stationIds.length)];

        data.push({
          monitor_time: `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, '0')}-${String(date.getDate()).padStart(2, '0')} ${hour}:00`,
          station_id: stationId,
          region,
          aqi: Math.floor(Math.random() * 150) + 30,
          pm25: Math.floor(Math.random() * 80) + 10,
          pm10: Math.floor(Math.random() * 100) + 20,
          so2: Math.floor(Math.random() * 50) + 5,
          no2: Math.floor(Math.random() * 60) + 10,
          status: Math.random() > 0.2 ? '正常' : '轻度污染'
        });
      }
    }
    return data;
  }
};

// 饮用水水源地专题视图相关接口
export const fetchWaterSourceOverview = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/waterSourceOverview`);
    if (response.data && typeof response.data === 'object') {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('饮用水水源地概览接口调用失败，使用模拟数据:', error.message);
    return {
      totalSources: 12,
      surfaceCount: 8,
      groundCount: 4,
      qualifiedRate: 92,
      rateChange: -3,
      unqualifiedCount: 1,
      mainPollutant: '总大肠菌群',
      monitorFrequency: '每周2次',
      lastMonitorTime: '2025-10-29 09:30'
    };
  }
};

export const fetchWaterSourceTrend = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/waterSourceTrend`);
    if (response.data && response.data.xAxis && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('饮用水水源地趋势接口调用失败，使用模拟数据:', error.message);
    const months = ['5月', '6月', '7月', '8月', '9月', '10月'];
    return {
      xAxis: months,
      series: [{
        name: '达标率',
        data: [98, 96, 95, 94, 95, 92]
      }]
    };
  }
};

export const fetchWaterSourceDistribution = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/waterSourceDistribution`);
    if (response.data && response.data.legend && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('饮用水水源地分布接口调用失败，使用模拟数据:', error.message);
    return {
      legend: ['地表水', '地下水'],
      series: [{
        name: '数量（个）',
        data: [8, 4]
      }]
    };
  }
};

export const fetchWaterSources = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/waterSources`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('饮用水水源地列表接口调用失败，使用模拟数据:', error.message);
    return [
      {
        source_id: 'WS-001',
        name: '青山水库',
        type: 'surface',
        lng: 116.45,
        lat: 39.92,
        region: '东部城区',
        status: 'qualified',
        last_check: '2025-10-29 08:15'
      },
      {
        source_id: 'WS-002',
        name: '地下水源地A',
        type: 'ground',
        lng: 116.38,
        lat: 39.88,
        region: '南部郊区',
        status: 'qualified',
        last_check: '2025-10-29 09:30'
      },
      {
        source_id: 'WS-003',
        name: '东湖水源地',
        type: 'surface',
        lng: 116.52,
        lat: 40.01,
        region: '北部新区',
        status: 'unqualified',
        last_check: '2025-10-29 10:45'
      }
    ];
  }
};

export const fetchWaterSourceData = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/waterSourceData`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('饮用水水源地详细数据接口调用失败，使用模拟数据:', error.message);
    return Array(12).fill(0).map((_, index) => ({
      source_id: `WS-${String(index + 100).slice(1)}`,
      name: `${['青山', '东湖', '西湖', '龙潭', '地下'][index % 5]}水源地${index + 1}`,
      type: index % 3 === 0 ? 'ground' : 'surface',
      region: ['东部城区', '南部郊区', '西部县区', '北部新区'][index % 4],
      ph: (7 + Math.random() * 0.8).toFixed(1),
      dissolved_oxygen: (6 + Math.random() * 1.5).toFixed(1),
      total_coliform: index === 2 ? 15 : Math.floor(Math.random() * 5),
      turbidity: (0.5 + Math.random() * 0.3).toFixed(2),
      cod_mn: (2 + Math.random() * 1).toFixed(1),
      ammonia_nitrogen: (0.5 + Math.random() * 0.3).toFixed(2),
      status: index === 2 ? 'unqualified' : 'qualified',
      last_check: `2025-10-${29 - (index % 3)} ${8 + (index % 4)}:${index % 2 === 0 ? '15' : '45'}`,
      population_served: (5 + Math.random() * 15).toFixed(1),
      area: (10 + Math.random() * 30).toFixed(1),
      build_time: `20${10 + (index % 10)}-${String(index % 12 + 1).padStart(2, '0')}`,
      unqualified_item: index === 2 ? '总大肠菌群' : '',
      actual_value: index === 2 ? '15' : '',
      standard_limit: index === 2 ? '10' : '',
      unit: index === 2 ? 'MPN/100mL' : '',
      treatment_measures: index === 2 ? '已启动应急处理，增加消毒频次' : ''
    }));
  }
};

export const fetchWaterSourceDetailTrend = async (sourceId) => {
  try {
    const response = await axios.get(`${BASE_URL}/waterSourceDetailTrend/${sourceId}`);
    if (response.data && response.data.xAxis && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('饮用水水源地详情趋势接口调用失败，使用模拟数据:', error.message);
    return {
      xAxis: ['8月', '9月', '10月'],
      series: [
        { name: 'pH值', data: [7.2, 7.3, 7.1] },
        { name: '溶解氧', data: [6.8, 7.0, 6.5] }
      ]
    };
  }
};

// 重点污染源专题视图相关接口
export const fetchPollutantStatistics = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/pollutantStatistics`);
    if (response.data && typeof response.data === 'object') {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('重点污染源统计数据接口调用失败，使用模拟数据:', error.message);
    return {
      totalPollutants: 45,
      onlineRate: 93,
      overstandardCount: 5,
      overstandardRate: 11,
      mainPollutant: '化学需氧量(COD)',
      maxConcentration: 185,
      concentrationUnit: 'mg/L',
      supervisionCount: 12,
      rectificationCount: 3
    };
  }
};

export const fetchPollutantDetailTrend = async (pollutantId) => {
  try {
    const response = await axios.get(`${BASE_URL}/pollutantDetailTrend/${pollutantId}`);
    if (response.data && response.data.xAxis && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('污染源详情趋势接口调用失败，使用模拟数据:', error.message);
    // 生成近7天日期
    const days = [];
    const today = new Date();
    for (let i = 6; i >= 0; i--) {
      const date = new Date(today);
      date.setDate(today.getDate() - i);
      days.push(`${date.getMonth() + 1}/${date.getDate()}`);
    }
    // 生成模拟数据（基于污染源ID的哈希值生成相对稳定的随机数）
    const baseValue = (parseInt(pollutantId.replace(/\D/g, '')) % 10) + 5;
    return {
      xAxis: days,
      series: [{
        name: '日排放量 (吨)',
        data: Array(7).fill(0).map(() => (Math.random() * 5 + baseValue).toFixed(2))
      }]
    };
  }
};

export const fetchPollutantTypeDistribution = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/pollutantTypeDistribution`);
    if (response.data && response.data.legend && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('污染源类型分布接口调用失败，使用模拟数据:', error.message);
    return {
      legend: ['工业', '农业', '生活', '机动车'],
      series: [{
        name: '数量',
        data: [22, 8, 10, 5]
      }]
    };
  }
};

export const fetchPollutantEmissionTrend = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/pollutantEmissionTrend`);
    if (response.data && response.data.xAxis && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('污染物排放趋势接口调用失败，使用模拟数据:', error.message);
    const days = [];
    const today = new Date();
    for (let i = 14; i >= 0; i--) {
      const date = new Date(today);
      date.setDate(today.getDate() - i);
      days.push(`${date.getMonth()+1}/${date.getDate()}`);
    }
    return {
      xAxis: days,
      series: [
        {
          name: 'COD',
          data: Array(15).fill(0).map(() => (Math.random() * 5 + 15).toFixed(2))
        },
        {
          name: '氨氮',
          data: Array(15).fill(0).map(() => (Math.random() * 2 + 3).toFixed(2))
        },
        {
          name: '二氧化硫',
          data: Array(15).fill(0).map(() => (Math.random() * 3 + 8).toFixed(2))
        }
      ]
    };
  }
};

export const fetchPollutantList = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/pollutantList`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('污染源列表接口调用失败，使用模拟数据:', error.message);
    return [
      {
        id: 'PS-001',
        name: '东方化工厂',
        type: 'industrial',
        lng: 116.55,
        lat: 39.85,
        region: '东部工业区',
        emission: 28.5,
        overstandard: '1',
        last_monitor: '2025-10-30 08:30'
      },
      {
        id: 'PS-002',
        name: '城南污水处理厂',
        type: 'domestic',
        lng: 116.42,
        lat: 39.82,
        region: '南部城区',
        emission: 12.3,
        overstandard: '0',
        last_monitor: '2025-10-30 09:15'
      },
      {
        id: 'PS-003',
        name: '北郊农田区',
        type: 'agricultural',
        lng: 116.38,
        lat: 40.05,
        region: '北部郊区',
        emission: 8.7,
        overstandard: '1',
        last_monitor: '2025-10-30 10:00'
      },
      {
        id: 'PS-004',
        name: '城西交通枢纽',
        type: 'vehicle',
        lng: 116.30,
        lat: 39.92,
        region: '西部城区',
        emission: 15.2,
        overstandard: '0',
        last_monitor: '2025-10-30 07:45'
      }
    ];
  }
};

export const fetchPollutantTableData = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/pollutantTableData`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('污染源表格数据接口调用失败，使用模拟数据:', error.message);
    return Array(45).fill(0).map((_, index) => {
      const typeList = ['industrial', 'agricultural', 'domestic', 'vehicle'];
      const type = typeList[index % 4];
      const emission = (Math.random() * 30 + 5).toFixed(1);
      const standard = (Math.random() * 20 + 10).toFixed(1);
      const overstandard = parseFloat(emission) > parseFloat(standard) ? '1' : '0';

      return {
        id: `PS-${String(index + 100).slice(1)}`,
        name: `${['东方', '南方', '西方', '北方', '中环'][index % 5]}${
          {
            industrial: '工业',
            agricultural: '农业',
            domestic: '生活',
            vehicle: '机动车'
          }[type]
        }污染源${index + 1}`,
        type,
        region: ['东部工业区', '南部城区', '西部县区', '北部郊区'][index % 4],
        emission,
        standard,
        overstandard,
        overstandard_multiple: overstandard === '1' ? ((parseFloat(emission) / parseFloat(standard)).toFixed(2)) : '0',
        main_pollutant: index % 3 === 0 ? 'COD' : index % 3 === 1 ? '氨氮' : '二氧化硫',
        monitor_status: index % 10 === 0 ? 'offline' : 'online',
        last_monitor: `2025-10-${30 - (index % 2)} ${7 + (index % 12)}:${index % 2 === 0 ? '00' : '30'}`,
        last_calibration: `2025-10-${15 - (index % 10)}`,
        address: `${['化工路', '环保路', '科技园', '产业园'][index % 4]}${index + 100}号`,
        manager: `负责人${index + 1}`,
        contact: `138${String(10000000 + index * 123456).slice(1)}`,
        treatment_records: overstandard === '1' ? [
          {
            time: `2025-10-${28 - (index % 2)} 14:${index % 60}`,
            content: '已下发整改通知，要求15日内完成治理',
            handler: `监管员${(index % 5) + 1}`
          }
        ] : []
      };
    });
  }
};

// 固废处置专题视图相关接口
export const fetchSolidWasteStats = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/solidWasteStats`);
    if (response.data && typeof response.data === 'object') {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('固废处置统计数据接口调用失败，使用模拟数据:', error.message);
    return {
      totalWaste: 12560,
      totalChange: 2.5,
      disposedWaste: 11980,
      disposalRate: Math.round((11980 / 12560) * 100),
      harmlessWaste: 11560,
      harmlessRate: Math.round((11560 / 11980) * 100),
      facilityCount: 18,
      runningFacility: 16
    };
  }
};

export const fetchSolidWasteTypeDistribution = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/solidWasteTypeDistribution`);
    if (response.data && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('固废类型分布接口调用失败，使用模拟数据:', error.message);
    return {
      legend: ['生活垃圾', '工业固废', '危险废物', '医疗废物'],
      series: [{
        name: '产生量 (吨)',
        data: [
          { name: '生活垃圾', value: 6500 },
          { name: '工业固废', value: 4200 },
          { name: '危险废物', value: 1200 },
          { name: '医疗废物', value: 660 }
        ]
      }]
    };
  }
};

export const fetchSolidWasteTrend = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/solidWasteTrend`);
    if (response.data && response.data.xAxis && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('固废处置趋势接口调用失败，使用模拟数据:', error.message);
    const months = ['5月', '6月', '7月', '8月', '9月', '10月'];
    return {
      xAxis: months,
      series: [
        {
          name: '产生量',
          data: [11200, 11800, 12100, 12400, 12300, 12560]
        },
        {
          name: '处置量',
          data: [10800, 11500, 11700, 12000, 11800, 11980]
        }
      ]
    };
  }
};

export const fetchSolidWasteFacilityTrend = async (facilityId) => {
  try {
    const response = await axios.get(`${BASE_URL}/solidWasteFacilityTrend/${facilityId}`);
    if (response.data && response.data.xAxis && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('固废处置设施趋势接口调用失败，使用模拟数据:', error.message);
    // 生成近7天日期
    const days = [];
    const today = new Date();
    for (let i = 6; i >= 0; i--) {
      const date = new Date(today);
      date.setDate(today.getDate() - i);
      days.push(`${date.getMonth() + 1}/${date.getDate()}`);
    }

    // 基于设施ID获取基础值
    const facilities = await fetchSolidWasteFacilities();
    const facility = facilities.find(f => f.id === facilityId) || { actual_daily: 100 };
    const baseValue = parseFloat(facility.actual_daily) * 0.8;

    return {
      xAxis: days,
      series: [{
        name: '日处理量 (吨)',
        data: Array(7).fill(0).map(() => (Math.random() * 50 + baseValue).toFixed(1))
      }]
    };
  }
};

export const fetchSolidWasteFacilities = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/solidWasteFacilities`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('固废处置设施接口调用失败，使用模拟数据:', error.message);
    return [
      {
        id: 'FW-001',
        name: '城市生活垃圾填埋场',
        type: 'domestic',
        lng: 116.58,
        lat: 39.98,
        region: '东北部',
        daily_capacity: 800,
        actual_daily: 750,
        status: 'running',
        address: '东郊环保产业园1号',
        commissioning_time: '2018-05-10',
        technology: '卫生填埋+沼气回收',
        discharge_standard: 'GB16889-2008',
        last_inspection: '2025-10-25',
        operator: '城市环境服务集团'
      },
      {
        id: 'FW-002',
        name: '工业固废综合处理厂',
        type: 'industrial',
        lng: 116.35,
        lat: 39.85,
        region: '西南部',
        daily_capacity: 500,
        actual_daily: 480,
        status: 'running',
        address: '西郊工业园8号',
        commissioning_time: '2019-03-15',
        technology: '焚烧发电+资源回收',
        discharge_standard: 'GB18484-2020',
        last_inspection: '2025-10-26',
        operator: '绿色工业处理有限公司'
      },
      {
        id: 'FW-003',
        name: '危险废物处置中心',
        type: 'hazardous',
        lng: 116.62,
        lat: 39.78,
        region: '东南部',
        daily_capacity: 150,
        actual_daily: 130,
        status: 'running',
        address: '南郊危险品处理区',
        commissioning_time: '2020-07-20',
        technology: '高温焚烧+安全填埋',
        discharge_standard: 'GB18484-2020',
        last_inspection: '2025-10-28',
        operator: '环安危废处理有限公司'
      },
      {
        id: 'FW-004',
        name: '医疗废物处理站',
        type: 'medical',
        lng: 116.30,
        lat: 40.02,
        region: '西北部',
        daily_capacity: 50,
        actual_daily: 45,
        status: 'running',
        address: '北郊医疗产业园',
        commissioning_time: '2021-01-10',
        technology: '高温蒸汽灭菌+焚烧',
        discharge_standard: 'GB18484-2020',
        last_inspection: '2025-10-27',
        operator: '健康环保科技有限公司'
      }
    ];
  }
};

export const fetchSolidWasteDetailData = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/solidWasteDetailData`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('固废处置详情数据接口调用失败，使用模拟数据:', error.message);
    const types = ['domestic', 'industrial', 'hazardous', 'medical'];
    const facilities = [
      '城市生活垃圾填埋场',
      '工业固废综合处理厂',
      '危险废物处置中心',
      '医疗废物处理站'
    ];

    return Array(20).fill(0).map((_, index) => {
      const type = types[index % 4];
      const generation = Math.round(Math.random() * 1000 + 500);
      const disposalRate = Math.round(Math.random() * 20 + 80);
      const disposal = Math.round(generation * disposalRate / 100);

      return {
        region: ['东部城区', '南部城区', '西部县区', '北部郊区', '市中心'][index % 5],
        type,
        generation,
        disposal,
        disposal_rate: disposalRate,
        disposal_method: index % 3 === 0 ? '填埋' : index % 3 === 1 ? '焚烧' : '综合利用',
        main_facility: facilities[index % facilities.length],
        update_time: `2025-10-${30 - (index % 3)} ${9 + (index % 10)}:${index % 2 === 0 ? '00' : '30'}`
      };
    });
  }
};

