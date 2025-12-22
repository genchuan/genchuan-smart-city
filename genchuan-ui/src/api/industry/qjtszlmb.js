import request from '@/config/axios';
const BASE_URL = '/industry';

/**
 * 获取行政区域字典
 * @returns {Promise<Array>} 区域选项数组 [{ label: '洛江区', value: '350504' }, ...]
 */
export const fetchRegionDict = () => {
  try {
    // ========== 完全注释真实接口请求 ==========
    // return request.get({
    //   url: `${BASE_URL}/region/dict`,
    // }).then(response => {
    //   console.log('行政区域字典-接口请求成功:', response);
    //   if (Array.isArray(response) && response.length > 0) {
    //     return response.map(item => ({
    //       label: item.region_name || item.label,
    //       value: item.region_code || item.value
    //     }));
    //   }
    //   throw new Error('行政区域接口返回无效数据，使用模拟数据兜底');
    // }).catch(error => {
    console.log('【模拟数据】获取行政区域字典');
    return Promise.resolve([
      { label: '丰泽区', value: '350503' },
      { label: '洛江区', value: '350504' },
      { label: '鲤城区', value: '350502' }
    ]);
    // });
  } catch (error) {
    console.error('fetchRegionDict初始化错误:', error);
    return Promise.resolve([
      { label: '丰泽区', value: '350503' },
      { label: '洛江区', value: '350504' },
      { label: '鲤城区', value: '350502' }
    ]);
  }
};

/**
 * 获取城管住建核心指标数据
 * @param {Object} params 筛选参数
 * @param {string} params.stat_cycle 统计周期（DAY/WEEK/MONTH/QUARTER/YEAR）
 * @param {string} params.region_code 行政区域编码
 */
export const fetchUrbanManagementCoreIndicators = (params = {}) => {
  try {
    // ========== 完全注释真实接口请求 ==========
    // return request.get({
    //   url: `${BASE_URL}/urban-core-metrics/list`,
    //   params: {
    //     stat_cycle: params.stat_cycle || '',
    //     region_code: params.region_code || ''
    //   }
    // }).then(response => {
    //   console.log('城管住建核心指标-请求成功（筛选参数）:', params);
    //   if (typeof response === 'object' && response !== null && Object.keys(response).length > 0) {
    //     console.log('城管住建核心指标-响应格式正常:', response);
    //     return response;
    //   }
    //   throw new Error('真实接口返回无核心数据，使用模拟数据兜底');
    // }).catch(error => {
    console.log('【模拟数据】获取城管住建核心指标', params);
    return new Promise(resolve => {
      setTimeout(() => {
        const fullMockMap = {
          '350504': {
            DAY: {
              total_count: 450,
              abnormal_count: 20,
              qualify_rate: 0.94,
              close_rate: 0.95,
              yoy_growth: 0.082,
              mom_growth: 0.030
            },
            WEEK: {
              total_count: 3150,
              abnormal_count: 140,
              qualify_rate: 0.925,
              close_rate: 0.945,
              yoy_growth: 0.070,
              mom_growth: 0.027
            },
            MONTH: {
              total_count: 6300,
              abnormal_count: 168,
              qualify_rate: 0.915,
              close_rate: 0.94,
              yoy_growth: 0.055,
              mom_growth: 0.022
            },
            QUARTER: {
              total_count: 18900,
              abnormal_count: 504,
              qualify_rate: 0.885,
              close_rate: 0.925,
              yoy_growth: 0.042,
              mom_growth: 0.017
            },
            YEAR: {
              total_count: 75600,
              abnormal_count: 2016,
              qualify_rate: 0.935,
              close_rate: 0.93,
              yoy_growth: 0.060,
              mom_growth: 0.013
            }
          },
          '350503': {
            DAY: {
              total_count: 320,
              abnormal_count: 16,
              qualify_rate: 0.985,
              close_rate: 0.93,
              yoy_growth: 0.105,
              mom_growth: 0.042
            },
            WEEK: {
              total_count: 2240,
              abnormal_count: 42,
              qualify_rate: 0.98,
              close_rate: 0.925,
              yoy_growth: 0.090,
              mom_growth: 0.038
            },
            MONTH: {
              total_count: 4480,
              abnormal_count: 108,
              qualify_rate: 0.975,
              close_rate: 0.93,
              yoy_growth: 0.075,
              mom_growth: 0.033
            },
            QUARTER: {
              total_count: 13440,
              abnormal_count: 324,
              qualify_rate: 0.955,
              close_rate: 0.91,
              yoy_growth: 0.062,
              mom_growth: 0.023
            },
            YEAR: {
              total_count: 53760,
              abnormal_count: 1296,
              qualify_rate: 0.965,
              close_rate: 0.92,
              yoy_growth: 0.080,
              mom_growth: 0.022
            }
          },
          '350502': {
            DAY: {
              total_count: 480,
              abnormal_count: 22,
              qualify_rate: 0.915,
              close_rate: 0.86,
              yoy_growth: 0.078,
              mom_growth: 0.029
            },
            WEEK: {
              total_count: 3360,
              abnormal_count: 154,
              qualify_rate: 0.91,
              close_rate: 0.855,
              yoy_growth: 0.065,
              mom_growth: 0.025
            },
            MONTH: {
              total_count: 6720,
              abnormal_count: 182,
              qualify_rate: 0.90,
              close_rate: 0.86,
              yoy_growth: 0.050,
              mom_growth: 0.020
            },
            QUARTER: {
              total_count: 20160,
              abnormal_count: 546,
              qualify_rate: 0.87,
              close_rate: 0.84,
              yoy_growth: 0.038,
              mom_growth: 0.015
            },
            YEAR: {
              total_count: 80640,
              abnormal_count: 2184,
              qualify_rate: 0.905,
              close_rate: 0.85,
              yoy_growth: 0.055,
              mom_growth: 0.009
            }
          }
        };

        const targetCycle = params.stat_cycle || 'MONTH';
        const targetRegion = params.region_code || '350504';
        const matchedData = fullMockMap[targetRegion]?.[targetCycle] || fullMockMap['350504']['MONTH'];

        const finalMock = {
          ...matchedData,
          qualify_warn_threshold: 0.9,
          stat_cycle: targetCycle,
          region_code: targetRegion,
          updateTime: 1764322488000
        };

        resolve(finalMock);
      }, 500);
    });
    // });
  } catch (error) {
    console.error('fetchUrbanManagementCoreIndicators初始化错误:', {
      message: error.message,
      stack: error.stack
    });
    return Promise.resolve({
      total_count: 0,
      abnormal_count: 0,
      qualify_rate: 0,
      close_rate: 0,
      yoy_growth: 0,
      mom_growth: 0,
      qualify_warn_threshold: 0.9,
      stat_cycle: params.stat_cycle || 'MONTH',
      region_code: params.region_code || '350504',
      updateTime: 1764322488000
    });
  }
};

/**
 * 城管住建全域数据-获取设施点位数据（支持筛选：行政区域/设施类型/状态）
 * @param {Object} params - 筛选参数
 * @param {string} params.region_code - 行政区域编码
 * @param {string} params.facility_type - 设施类型（road/bridge/drainage等）
 * @param {string} params.status - 设施状态（正常/异常/维护）
 * @returns {Promise<Array>} 筛选后的设施点位数组
 */
export const fetchUrbanConstructionGeometries = (params = {}) => {
  try {
    // ========== 完全注释真实接口请求 ==========
    // return request.get({
    //   url: `${BASE_URL}/urban-construction/facility/get`,
    //   params: {
    //     region_code: params.region_code || '',
    //     facility_type: params.facility_type || '',
    //     status: params.status || ''
    //   }
    // }).then(response => {
    //   console.log('城管住建设施数据-接口请求成功');

    //   if (Array.isArray(response)) {
    //     console.log('城管住建设施数据-响应符合数组格式');
    //     return response.map(item => ({
    //       road_fac_id: item.road_fac_id || item.roadFacId,
    //       road_fac_name: item.road_fac_name || item.roadFacName,
    //       region_name: item.region_name || item.regionName,
    //       region_code: item.region_code || item.regionCode,
    //       facility_type: item.facility_type || item.facilityType,
    //       status: item.status,
    //       coord_x: item.coord_x || item.coordX,
    //       coord_y: item.coord_y || item.coordY,
    //       wo_id: item.wo_id || item.woId || '无'
    //     }));
    //   }

    //   throw new Error('城管住建接口返回无效数据，使用模拟数据兜底');
    // }).catch(error => {
    console.log('【模拟数据】获取城管住建设施点位数据', params);
    return new Promise(resolve => {
      setTimeout(() => {
        const mockData = [
          {
            road_fac_id: "QZ001",
            road_fac_name: "中山路路灯01",
            region_name: "鲤城区",
            region_code: "350502",
            facility_type: "lighting",
            status: "正常",
            coord_x: 24.895868,
            coord_y: 118.587654,
            wo_id: "WO2025001"
          },
          {
            road_fac_id: "QZ002",
            road_fac_name: "涂门街井盖05",
            region_name: "鲤城区",
            region_code: "350502",
            facility_type: "drainage",
            status: "异常",
            coord_x: 24.883237,
            coord_y: 118.590114,
            wo_id: "WO2025002"
          },
          {
            road_fac_id: "QZ003",
            road_fac_name: "丰泽街公交站03",
            region_name: "丰泽区",
            region_code: "350503",
            facility_type: "road",
            status: "维护",
            coord_x: 24.903337,
            coord_y: 118.600114,
            wo_id: "无"
          },
          {
            road_fac_id: "QZ004",
            road_fac_name: "东海大街消防栓08",
            region_name: "丰泽区",
            region_code: "350503",
            facility_type: "road",
            status: "正常",
            coord_x: 24.910237,
            coord_y: 118.610777,
            wo_id: "无"
          },
          {
            road_fac_id: "QZ005",
            road_fac_name: "安吉路垃圾桶12",
            region_name: "洛江区",
            region_code: "350504",
            facility_type: "sanitation",
            status: "维护",
            coord_x: 24.923672,
            coord_y: 118.628765,
            wo_id: "WO2025005"
          },
          {
            road_fac_id: "QZ006",
            road_fac_name: "万虹路辅道路井盖02",
            region_name: "洛江区",
            region_code: "350504",
            facility_type: "bridge",
            status: "异常",
            coord_x: 24.942237,
            coord_y: 118.649114,
            wo_id: "WO2025006"
          }
        ];

        let filteredData = [...mockData];
        if (params.region_code) {
          filteredData = filteredData.filter(item => item.region_code === params.region_code);
        }
        if (params.facility_type) {
          filteredData = filteredData.filter(item => item.facility_type === params.facility_type);
        }
        if (params.status) {
          filteredData = filteredData.filter(item => item.status === params.status);
        }

        resolve(filteredData);
      }, 500);
    });
    // });
  } catch (error) {
    console.error('===== 城管住建接口初始化异常 =====');
    console.error('错误信息:', error.message);
    return Promise.resolve([]);
  }
};

/**
 * 获取近3个月核心指标趋势数据（日粒度）
 * @param {Object} params
 * @param {string} params.region_code 区域编码
 * @param {Array} params.indicators 需要的指标列表（abnormal_count/qualify_rate/close_rate等）
 */
export const fetchCoreIndicatorTrend = (params = {}) => {
  try {
    // ========== 完全注释真实接口请求 ==========
    // return request.get({
    //   url: `${BASE_URL}/urban-core-metrics/trend`,
    //   params: {
    //     region_code: params.region_code || '',
    //     indicators: params.indicators?.join(',') || ''
    //   }
    // }).then(response => {
    //   if (response && Object.keys(response).length > 0) {
    //     return response;
    //   }
    //   throw new Error('真实接口无趋势数据，使用模拟数据兜底');
    // }).catch(error => {
    console.log('【模拟数据】获取核心指标趋势数据', params);
    return new Promise(resolve => {
      setTimeout(() => {
        // 1. 生成近3个月的日期轴（约90天）
        const getLast90Days = () => {
          const days = [];
          for (let i = 89; i >= 0; i--) {
            const date = new Date();
            date.setDate(date.getDate() - i);
            days.push(date.toISOString().split('T')[0]); // 格式：2025-09-15
          }
          return days;
        };
        const xAxis = getLast90Days();
        const targetRegion = params.region_code || '350504';
        const targetIndicators = params.indicators || ['abnormal_count', 'qualify_rate', 'close_rate'];

        // 2. 模拟各指标的日粒度数据
        const baseDataMap = {
          '350504': {
            abnormal_count: xAxis.map((_, idx) => {
              // 基础值 + 随机波动，模拟异常数
              const base = 15 + Math.floor(Math.random() * 10);
              // 模拟异常时段：第30-35天、第60-65天异常突增
              if ((idx >= 30 && idx <= 35) || (idx >= 60 && idx <= 65)) {
                return base + 20;
              }
              return base;
            }),
            qualify_rate: xAxis.map((_, idx) => {
              const base = 0.88 + Math.random() * 0.08;
              // 异常时段达标率下降
              if ((idx >= 30 && idx <= 35) || (idx >= 60 && idx <= 65)) {
                return base - 0.1;
              }
              return parseFloat(base.toFixed(4));
            }),
            close_rate: xAxis.map((_, idx) => {
              const base = 0.85 + Math.random() * 0.1;
              return parseFloat(base.toFixed(4));
            })
          },
          '350503': {
            abnormal_count: xAxis.map((_, idx) => {
              const base = 5 + Math.floor(Math.random() * 5);
              if ((idx >= 30 && idx <= 35) || (idx >= 60 && idx <= 65)) {
                return base + 10;
              }
              return base;
            }),
            qualify_rate: xAxis.map((_, idx) => {
              const base = 0.95 + Math.random() * 0.03;
              if ((idx >= 30 && idx <= 35) || (idx >= 60 && idx <= 65)) {
                return base - 0.05;
              }
              return parseFloat(base.toFixed(4));
            }),
            close_rate: xAxis.map((_, idx) => {
              const base = 0.90 + Math.random() * 0.05;
              return parseFloat(base.toFixed(4));
            })
          },
          '350502': {
            abnormal_count: xAxis.map((_, idx) => {
              const base = 18 + Math.floor(Math.random() * 8);
              if ((idx >= 30 && idx <= 35) || (idx >= 60 && idx <= 65)) {
                return base + 25;
              }
              return base;
            }),
            qualify_rate: xAxis.map((_, idx) => {
              const base = 0.85 + Math.random() * 0.06;
              if ((idx >= 30 && idx <= 35) || (idx >= 60 && idx <= 65)) {
                return base - 0.12;
              }
              return parseFloat(base.toFixed(4));
            }),
            close_rate: xAxis.map((_, idx) => {
              const base = 0.80 + Math.random() * 0.08;
              return parseFloat(base.toFixed(4));
            })
          }
        };

        // 3. 标记异常时段（供图表标注）
        const abnormalPeriods = [
          { start: xAxis[30], end: xAxis[35], reason: '暴雨导致设施异常激增' },
          { start: xAxis[60], end: xAxis[65], reason: '设备维护不及时' }
        ];

        // 4. 趋势预判：极简随机数模拟预测数据
        const predictTrend = (indicatorData) => {
          const predictData = [];
          const lastVal = indicatorData[indicatorData.length - 1];
          // 生成7天随机数据（仅±5%波动，极简逻辑）
          for (let i = 1; i <= 7; i++) {
            // 随机比例：0.95~1.05（仅±5%波动，避免数值偏离）
            const newVal = lastVal * (0.95 + Math.random() * 0.1);
            // 极简判断：异常数取整，率值保留4位，且不小于0
            const finalVal = Math.max(0, Number.isInteger(lastVal) ? Math.floor(newVal) : parseFloat(newVal.toFixed(4)));
            predictData.push(finalVal);
          }
          return predictData;
        };

        // 5. 组装最终返回数据
        const series = targetIndicators.map(indicator => {
          const rawData = baseDataMap[targetRegion][indicator];
          const predictData = predictTrend(rawData);
          return {
            name: {
              abnormal_count: '异常数',
              qualify_rate: '达标率(%)',
              close_rate: '处置闭环率(%)'
            }[indicator],
            type: 'line',
            data: rawData.map(val => indicator.includes('rate') ? val * 100 : val), // 率转百分比
            // 预判数据单独标记（虚线）
            predictData: predictData.map(val => indicator.includes('rate') ? val * 100 : val),
            lineStyle: indicator.includes('rate') ? {} : { width: 3 }
          };
        });

        resolve({
          xAxis, // 近90天日期
          series, // 各指标数据
          abnormalPeriods, // 异常时段
          predictXAxis: Array.from({ length: 7 }, (_, i) => {
            const date = new Date(xAxis[xAxis.length - 1]);
            date.setDate(date.getDate() + i + 1);
            return date.toISOString().split('T')[0];
          }) // 未来7天日期
        });
      }, 500);
    });
    // });
  } catch (error) {
    console.error('fetchCoreIndicatorTrend初始化错误:', error);
    return Promise.resolve({
      xAxis: [],
      series: [],
      abnormalPeriods: [],
      predictXAxis: []
    });
  }
};

/**
 * 检查异常连续触发预警
 * @param {string} region_code 区域编码
 * @param {string} indicator 指标名
 * @param {number} threshold 阈值
 * @param {number} continuousDays 连续天数
 */
export const checkContinuousAbnormal = (region_code, indicator, threshold, continuousDays = 3) => {
  // 复用趋势数据逻辑，仅检查最后N天（本身无真实接口请求）
  const trendDataPromise = fetchCoreIndicatorTrend({ region_code, indicators: [indicator] }).then(res => {
    const indicatorData = res.series[0]?.data || [];
    if (indicatorData.length < continuousDays) return { isTrigger: false, lastNDays: [], threshold };

    // 取最后N天数据
    const lastNDays = indicatorData.slice(-continuousDays);
    // 判断是否连续超标（异常数：>阈值；达标率：<阈值）
    const isAbnormal = (val) => {
      if (indicator === 'abnormal_count') return val > threshold;
      if (indicator === 'qualify_rate') return val < threshold;
      return false;
    };

    const isContinuous = lastNDays.every(isAbnormal);
    return {
      isTrigger: isContinuous,
      lastNDays,
      threshold
    };
  });
  return trendDataPromise;
};
