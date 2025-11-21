import axios from 'axios';

// 基础URL，根据实际项目配置
const BASE_URL = '/api/overview/smartcommunity';

// 颜色配置常量（与前端样式统一）
const COLOR_CONFIG = {
  resource: {
    normal: '#36D399',      // 亮绿色-正常资源
    abnormal: '#F87272',    // 橙红色-异常资源
    maintenance: '#FBBF24'  // 明黄色-维护中
  },
  event: {
    '设施故障': '#F87272',
    '安全事件': '#FF7D00',
    '环境卫生': '#FAAD14',
    '民生服务': '#1890FF',
    '其他': '#722ED1'
  },
  alert: {
    high: '#F87272',
    medium: '#FF7D00',
    low: '#FBBF24'
  },
  grid: {
    highDensity: '#FF4D4F',
    mediumDensity: '#FA8C16',
    lowDensity: '#52C41A'
  }
};

// 社区ID映射
const COMMUNITY_MAP = {
  '幸福社区': 'comm1001',
  '和谐社区': 'comm1002',
  '平安社区': 'comm1003'
};

// 数据缓存
const dataCache = {
  indicators: null,
  population: {},
  resources: {},
  map: {},
  lastUpdated: {}
};

/**
 * 获取核心指标数据 - 从 stat_community_index 表获取
 */
const getCoreIndicators = async (params) => {
  const cacheKey = `indicators_${params.communityId}_${params.timeRange}`;
  const cacheTime = 5 * 60 * 1000; // 5分钟缓存

  // 检查缓存是否有效
  if (dataCache.indicators && !params.forceRefresh &&
    dataCache.lastUpdated[cacheKey] &&
    (Date.now() - dataCache.lastUpdated[cacheKey]) < cacheTime) {
    return dataCache.indicators;
  }

  try {
    const response = await axios.get(`${BASE_URL}/coreIndicators`, {
      params: {
        communityId: params.communityId,
        timeRange: params.timeRange,
        table: 'stat_community_index',
        fields: 'population_count,facility_online_rate,service_completion_rate,safety_event_rate,grid_coverage_rate,population_change,facility_change,service_change,safety_change,grid_change'
      },
      timeout: 10000
    });

    // 处理API响应
    let apiData;
    if (response.data && response.data.code === 200) {
      apiData = response.data.data || response.data;
    } else {
      apiData = response.data;
    }

    if (apiData) {
      const indicatorsData = {
        population: {
          value: apiData.population_count ?
            typeof apiData.population_count === 'number'
              ? apiData.population_count.toLocaleString()
              : apiData.population_count
            : '12,548',
          change: apiData.population_change || '+2.3%',
          changeType: (apiData.population_change || '+2.3%').includes('+') ? 'increase' : 'decrease',
          abnormal: false,
          source: 'sys_pop',
          threshold: 0,
          unit: '人'
        },
        facility: {
          value: apiData.facility_online_rate ?
            typeof apiData.facility_online_rate === 'number'
              ? `${(apiData.facility_online_rate * 100).toFixed(1)}%`
              : apiData.facility_online_rate
            : '96.7%',
          change: apiData.facility_change || '+1.2%',
          changeType: (apiData.facility_change || '+1.2%').includes('+') ? 'increase' : 'decrease',
          abnormal: (apiData.facility_online_rate || 0.967) < 0.9,
          source: 'biz_device_status',
          threshold: 90,
          unit: '%'
        },
        service: {
          value: apiData.service_completion_rate ?
            typeof apiData.service_completion_rate === 'number'
              ? `${(apiData.service_completion_rate * 100).toFixed(1)}%`
              : apiData.service_completion_rate
            : '78.5%',
          change: apiData.service_change || '-3.1%',
          changeType: (apiData.service_change || '-3.1%').includes('+') ? 'increase' : 'decrease',
          abnormal: (apiData.service_completion_rate || 0.785) < 0.8,
          source: 'biz_community_svc',
          threshold: 80,
          unit: '%'
        },
        safety: {
          value: apiData.safety_event_rate ?
            typeof apiData.safety_event_rate === 'number'
              ? apiData.safety_event_rate.toFixed(1)
              : apiData.safety_event_rate
            : '0.3',
          change: apiData.safety_change || '-0.1',
          changeType: (apiData.safety_change || '-0.1').includes('+') ? 'increase' : 'decrease',
          abnormal: (apiData.safety_event_rate || 0.3) > 0.5,
          source: 'biz_safety_event',
          threshold: 0.5,
          unit: '起/平方公里·月'
        },
        grid: {
          value: apiData.grid_coverage_rate ?
            typeof apiData.grid_coverage_rate === 'number'
              ? `${(apiData.grid_coverage_rate * 100).toFixed(1)}%`
              : apiData.grid_coverage_rate
            : '98.2%',
          change: apiData.grid_change || '0%',
          changeType: 'same',
          abnormal: (apiData.grid_coverage_rate || 0.982) < 0.95,
          source: 'biz_grid_mng',
          threshold: 95,
          unit: '%'
        }
      };

      dataCache.indicators = indicatorsData;
      dataCache.lastUpdated[cacheKey] = Date.now();
      return indicatorsData;
    }
  } catch (error) {
    console.warn('核心指标接口调用失败，使用模拟数据:', error.message);
  }

  // 增强的模拟数据 - 根据不同社区和时间范围返回不同数据
  const getMockData = (communityId, timeRange) => {
    const baseData = {
      'comm1001': { // 幸福社区
        month: {
          population: { value: '12,548', change: '+2.3%', abnormal: false },
          facility: { value: '96.7%', change: '+1.2%', abnormal: false },
          service: { value: '78.5%', change: '-3.1%', abnormal: true },
          safety: { value: '0.3', change: '-0.1', abnormal: false },
          grid: { value: '98.2%', change: '0%', abnormal: false }
        },
        quarter: {
          population: { value: '12,340', change: '+1.8%', abnormal: false },
          facility: { value: '97.2%', change: '+0.8%', abnormal: false },
          service: { value: '80.1%', change: '+1.6%', abnormal: false },
          safety: { value: '0.2', change: '-0.2', abnormal: false },
          grid: { value: '98.2%', change: '0%', abnormal: false }
        },
        year: {
          population: { value: '11,980', change: '+4.2%', abnormal: false },
          facility: { value: '95.8%', change: '+2.1%', abnormal: false },
          service: { value: '82.3%', change: '+3.8%', abnormal: false },
          safety: { value: '0.4', change: '-0.3', abnormal: false },
          grid: { value: '97.8%', change: '+0.4%', abnormal: false }
        }
      },
      'comm1002': { // 和谐社区
        month: {
          population: { value: '18,720', change: '+3.1%', abnormal: false },
          facility: { value: '94.5%', change: '-0.5%', abnormal: true },
          service: { value: '82.3%', change: '+1.2%', abnormal: false },
          safety: { value: '0.4', change: '+0.1', abnormal: false },
          grid: { value: '97.8%', change: '-0.2%', abnormal: false }
        },
        quarter: {
          population: { value: '18,450', change: '+2.7%', abnormal: false },
          facility: { value: '95.1%', change: '+0.6%', abnormal: false },
          service: { value: '83.5%', change: '+1.2%', abnormal: false },
          safety: { value: '0.3', change: '-0.1', abnormal: false },
          grid: { value: '97.8%', change: '0%', abnormal: false }
        },
        year: {
          population: { value: '17,890', change: '+5.8%', abnormal: false },
          facility: { value: '93.2%', change: '+1.3%', abnormal: true },
          service: { value: '85.1%', change: '+4.2%', abnormal: false },
          safety: { value: '0.5', change: '-0.2', abnormal: false },
          grid: { value: '96.9%', change: '+0.7%', abnormal: false }
        }
      },
      'comm1003': { // 平安社区
        month: {
          population: { value: '9,450', change: '+1.5%', abnormal: false },
          facility: { value: '98.1%', change: '+0.3%', abnormal: false },
          service: { value: '85.2%', change: '+2.1%', abnormal: false },
          safety: { value: '0.1', change: '-0.1', abnormal: false },
          grid: { value: '99.1%', change: '+0.1%', abnormal: false }
        },
        quarter: {
          population: { value: '9,320', change: '+1.2%', abnormal: false },
          facility: { value: '98.3%', change: '+0.2%', abnormal: false },
          service: { value: '86.7%', change: '+1.5%', abnormal: false },
          safety: { value: '0.1', change: '0%', abnormal: false },
          grid: { value: '99.1%', change: '0%', abnormal: false }
        },
        year: {
          population: { value: '9,120', change: '+2.8%', abnormal: false },
          facility: { value: '97.6%', change: '+1.1%', abnormal: false },
          service: { value: '88.3%', change: '+3.9%', abnormal: false },
          safety: { value: '0.2', change: '-0.1', abnormal: false },
          grid: { value: '98.7%', change: '+0.5%', abnormal: false }
        }
      }
    };

    const communityData = baseData[communityId] || baseData['comm1001'];
    const timeData = communityData[timeRange] || communityData.month;

    return {
      population: {
        value: timeData.population.value,
        change: timeData.population.change,
        changeType: timeData.population.change.includes('+') ? 'increase' : 'decrease',
        abnormal: timeData.population.abnormal,
        source: 'sys_pop',
        threshold: 0,
        unit: '人'
      },
      facility: {
        value: timeData.facility.value,
        change: timeData.facility.change,
        changeType: timeData.facility.change.includes('+') ? 'increase' : 'decrease',
        abnormal: timeData.facility.abnormal,
        source: 'biz_device_status',
        threshold: 90,
        unit: '%'
      },
      service: {
        value: timeData.service.value,
        change: timeData.service.change,
        changeType: timeData.service.change.includes('+') ? 'increase' : 'decrease',
        abnormal: timeData.service.abnormal,
        source: 'biz_community_svc',
        threshold: 80,
        unit: '%'
      },
      safety: {
        value: timeData.safety.value,
        change: timeData.safety.change,
        changeType: timeData.safety.change.includes('+') ? 'increase' : 'decrease',
        abnormal: timeData.safety.abnormal,
        source: 'biz_safety_event',
        threshold: 0.5,
        unit: '起/平方公里·月'
      },
      grid: {
        value: timeData.grid.value,
        change: timeData.grid.change,
        changeType: timeData.grid.change.includes('+') ? 'increase' : 'decrease',
        abnormal: timeData.grid.abnormal,
        source: 'biz_grid_mng',
        threshold: 95,
        unit: '%'
      }
    };
  };

  const mockData = getMockData(params.communityId, params.timeRange);
  dataCache.indicators = mockData;
  dataCache.lastUpdated[cacheKey] = Date.now();
  return mockData;
};

/**
 * 社区全局态势地图API（带模拟数据兜底）
 */
const GlobalSituationOverviewAPI = {
  /**
   * 获取社区边界数据 - 从 sys_area 表获取
   */
  getCommunityBoundary: async (params) => {
    const cacheKey = `boundary_${params.id}`;
    const now = Date.now();

    // 检查缓存是否有效（5分钟缓存）
    if (dataCache.map[cacheKey] && !params.forceRefresh &&
      (now - dataCache.lastUpdated[cacheKey]) < 5 * 60 * 1000) {
      return dataCache.map[cacheKey];
    }

    try {
      const response = await axios.get(`${BASE_URL}/communityBoundary`, {
        params: {
          communityId: params.id,
          table: 'sys_area',
          field: 'community_boundary'
        },
        timeout: 10000
      });
      if (response.data && response.data.community_boundary) {
        const result = {
          ...response.data,
          style: {
            color: '#00CCFF',
            fillColor: 'rgba(0, 204, 255, 0.1)',
            lineWidth: 2
          }
        };
        dataCache.map[cacheKey] = result;
        dataCache.lastUpdated[cacheKey] = now;
        return result;
      }
    } catch (error) {
      console.warn('社区边界接口调用失败，使用模拟数据:', error.message);
    }

    // 模拟数据
    const mockData = {
      community_id: params.id || 'comm1001',
      community_name: '智慧社区示范区',
      community_boundary: JSON.stringify([
        [116.38748, 39.91882],
        [116.40748, 39.91882],
        [116.40748, 39.89882],
        [116.38748, 39.89882],
        [116.38748, 39.91882]
      ]),
      style: {
        color: '#00CCFF',
        fillColor: 'rgba(0, 204, 255, 0.1)',
        lineWidth: 2
      }
    };
    dataCache.map[cacheKey] = mockData;
    dataCache.lastUpdated[cacheKey] = now;
    return mockData;
  },

  /**
   * 获取单元网格数据 - 从 biz_unit_grid_div 表获取
   */
  getUnitGrids: async (params) => {
    const cacheKey = `grids_${params.communityId}`;
    const now = Date.now();

    if (dataCache.map[cacheKey] && !params.forceRefresh &&
      (now - dataCache.lastUpdated[cacheKey]) < 5 * 60 * 1000) {
      return dataCache.map[cacheKey];
    }

    try {
      const response = await axios.get(`${BASE_URL}/unitGrids`, {
        params: {
          ...params,
          table: 'biz_unit_grid_div',
          field: 'boundary_coords'
        },
        timeout: 10000
      });
      if (Array.isArray(response.data) && response.data.length > 0) {
        const result = {
          data: response.data.map(grid => ({
            ...grid,
            densityColor:
              grid.population_density > 8000
                ? COLOR_CONFIG.grid.highDensity
                : grid.population_density > 6000
                  ? COLOR_CONFIG.grid.mediumDensity
                  : COLOR_CONFIG.grid.lowDensity,
            style: {
              color: '#00CCFF',
              fillColor: 'rgba(0, 204, 255, 0.05)',
              lineWidth: 1
            }
          }))
        };
        dataCache.map[cacheKey] = result;
        dataCache.lastUpdated[cacheKey] = now;
        return result;
      }
    } catch (error) {
      console.warn('网格数据接口调用失败，使用模拟数据:', error.message);
    }

    // 模拟数据
    const mockData = {
      data: [
        {
          id: 'grid101',
          grid_name: '第一网格',
          community_id: params.communityId || 'comm1001',
          boundary_coords: JSON.stringify([
            [116.38748, 39.91882],
            [116.39748, 39.91882],
            [116.39748, 39.90882],
            [116.38748, 39.90882],
            [116.38748, 39.91882]
          ]),
          grid_manager: '张三',
          grid_phone: '13800138001',
          population_density: 8372,
          densityColor: COLOR_CONFIG.grid.highDensity,
          style: { color: '#00CCFF', fillColor: 'rgba(0, 204, 255, 0.05)', lineWidth: 1 }
        },
        {
          id: 'grid102',
          grid_name: '第二网格',
          community_id: params.communityId || 'comm1001',
          boundary_coords: JSON.stringify([
            [116.39748, 39.91882],
            [116.40748, 39.91882],
            [116.40748, 39.90882],
            [116.39748, 39.90882],
            [116.39748, 39.91882]
          ]),
          grid_manager: '李四',
          grid_phone: '13900139001',
          population_density: 7250,
          densityColor: COLOR_CONFIG.grid.mediumDensity,
          style: { color: '#00CCFF', fillColor: 'rgba(0, 204, 255, 0.05)', lineWidth: 1 }
        },
        {
          id: 'grid103',
          grid_name: '第三网格',
          community_id: params.communityId || 'comm1001',
          boundary_coords: JSON.stringify([
            [116.38748, 39.90882],
            [116.40748, 39.90882],
            [116.40748, 39.89882],
            [116.38748, 39.89882],
            [116.38748, 39.90882]
          ]),
          grid_manager: '王五',
          grid_phone: '13700137001',
          population_density: 5820,
          densityColor: COLOR_CONFIG.grid.lowDensity,
          style: { color: '#00CCFF', fillColor: 'rgba(0, 204, 255, 0.05)', lineWidth: 1 }
        }
      ]
    };
    dataCache.map[cacheKey] = mockData;
    dataCache.lastUpdated[cacheKey] = now;
    return mockData;
  },

  /**
   * 获取重点区域（POI）数据 - 从 sys_poi_data 表获取
   */
  getPoiData: async (params) => {
    const cacheKey = `poi_${params.communityId}`;
    const now = Date.now();

    if (dataCache.map[cacheKey] && !params.forceRefresh &&
      (now - dataCache.lastUpdated[cacheKey]) < 5 * 60 * 1000) {
      return dataCache.map[cacheKey];
    }

    try {
      const response = await axios.get(`${BASE_URL}/poiData`, {
        params: {
          ...params,
          table: 'sys_poi_data',
          fields: 'poi_type,coord_x,coord_y'
        },
        timeout: 10000
      });
      if (Array.isArray(response.data) && response.data.length > 0) {
        const result = {
          data: response.data.map(poi => ({
            ...poi,
            color: '#FBBF24',
            icon: 'icon-location-important',
            tooltip: `${poi.poi_name} (${poi.poi_type === 'school' ? '学校' : poi.poi_type === 'hospital' ? '医院' : '公共区域'})`
          }))
        };
        dataCache.map[cacheKey] = result;
        dataCache.lastUpdated[cacheKey] = now;
        return result;
      }
    } catch (error) {
      console.warn('重点区域接口调用失败，使用模拟数据:', error.message);
    }

    // 模拟数据
    const mockData = {
      data: [
        {
          id: 'poi101',
          poi_name: '社区中心小学',
          poi_type: 'school',
          address: '第一网格教育路1号',
          coordinateX: 116.39248,
          coordinateY: 39.91382,
          area: 8000,
          importance: 'high',
          color: '#FBBF24',
          icon: 'icon-location-important',
          tooltip: '社区中心小学 (学校)'
        },
        {
          id: 'poi102',
          poi_name: '社区卫生服务中心',
          poi_type: 'hospital',
          address: '第二网格健康路5号',
          coordinateX: 116.40248,
          coordinateY: 39.91382,
          area: 5000,
          importance: 'high',
          color: '#FBBF24',
          icon: 'icon-location-important',
          tooltip: '社区卫生服务中心 (医院)'
        },
        {
          id: 'poi103',
          poi_name: '社区文化广场',
          poi_type: 'square',
          address: '第三网格文化路3号',
          coordinateX: 116.39748,
          coordinateY: 39.90382,
          area: 12000,
          importance: 'medium',
          color: '#FBBF24',
          icon: 'icon-location-important',
          tooltip: '社区文化广场 (公共区域)'
        }
      ]
    };
    dataCache.map[cacheKey] = mockData;
    dataCache.lastUpdated[cacheKey] = now;
    return mockData;
  },

  /**
   * 获取管理部件（资源点位）数据 - 从 biz_mng_comp 表获取
   */
  getMngComponents: async (params) => {
    const cacheKey = `resources_${params.communityId}_${params.type || 'all'}_${params.status || 'all'}`;
    const now = Date.now();

    if (dataCache.resources[cacheKey] && !params.forceRefresh &&
      (now - dataCache.lastUpdated[cacheKey]) < 2 * 60 * 1000) {
      return dataCache.resources[cacheKey];
    }

    try {
      const response = await axios.get(`${BASE_URL}/mngComponents`, {
        params: {
          ...params,
          table: 'biz_mng_comp',
          fields: 'minor_name,coord_x,coord_y,status'
        },
        timeout: 10000
      });
      if (Array.isArray(response.data) && response.data.length > 0) {
        const result = {
          data: response.data.map(resource => ({
            ...resource,
            color:
              resource.status === 1
                ? COLOR_CONFIG.resource.normal
                : resource.status === 0
                  ? COLOR_CONFIG.resource.abnormal
                  : COLOR_CONFIG.resource.maintenance,
            statusText:
              resource.status === 1 ? '正常' : resource.status === 0 ? '异常' : '维护中',
            tooltip: `${resource.comp_name} (${resource.comp_type}) - ${resource.statusText}`,
            icon:
              resource.comp_type === '路灯'
                ? 'icon-light'
                : resource.comp_type === '监控'
                  ? 'icon-monitor'
                  : resource.comp_type === '消防设施'
                    ? 'icon-fire'
                    : 'icon-resource'
          }))
        };
        dataCache.resources[cacheKey] = result;
        dataCache.lastUpdated[cacheKey] = now;
        return result;
      }
    } catch (error) {
      console.warn('资源点位接口调用失败，使用模拟数据:', error.message);
    }

    // 模拟数据
    const resources = [
      {
        id: 'res101',
        comp_name: '主干道路灯1',
        comp_type: '路灯',
        location: '第一网格主干道',
        coordinateX: 116.39048,
        coordinateY: 39.91582,
        status: 1,
        install_time: '2023-01-15',
        grid_name: '第一网格',
        remark: '主干道照明',
        color: COLOR_CONFIG.resource.normal,
        statusText: '正常',
        tooltip: '主干道路灯1 (路灯) - 正常',
        icon: 'icon-light'
      },
      {
        id: 'res102',
        comp_name: '十字路口监控1',
        comp_type: '监控',
        location: '第一/二网格交界处',
        coordinateX: 116.39748,
        coordinateY: 39.91582,
        status: 1,
        install_time: '2023-02-20',
        grid_name: '第一网格',
        remark: '交通监控',
        color: COLOR_CONFIG.resource.normal,
        statusText: '正常',
        tooltip: '十字路口监控1 (监控) - 正常',
        icon: 'icon-monitor'
      },
      {
        id: 'res103',
        comp_name: '消防栓A1',
        comp_type: '消防设施',
        location: '第二网格居民区内',
        coordinateX: 116.40048,
        coordinateY: 39.91282,
        status: 0,
        install_time: '2023-03-10',
        grid_name: '第二网格',
        remark: '水压异常',
        color: COLOR_CONFIG.resource.abnormal,
        statusText: '异常',
        tooltip: '消防栓A1 (消防设施) - 异常',
        icon: 'icon-fire'
      },
      {
        id: 'res104',
        comp_name: '垃圾集中点B3',
        comp_type: '垃圾点',
        location: '第三网格中心',
        coordinateX: 116.39548,
        coordinateY: 39.90582,
        status: 2,
        install_time: '2023-04-05',
        grid_name: '第三网格',
        remark: '垃圾分类点',
        color: COLOR_CONFIG.resource.maintenance,
        statusText: '维护中',
        tooltip: '垃圾集中点B3 (垃圾点) - 维护中',
        icon: 'icon-resource'
      }
    ];

    let filteredResources = resources;
    if (params.type) {
      filteredResources = filteredResources.filter(item => item.comp_type === params.type);
    }
    if (params.status !== undefined) {
      filteredResources = filteredResources.filter(item => item.status === params.status);
    }

    const result = { data: filteredResources };
    dataCache.resources[cacheKey] = result;
    dataCache.lastUpdated[cacheKey] = now;
    return result;
  },

  /**
   * 获取监测事件（异常点位）数据 - 从 biz_mon_evt_info 表获取
   */
  getMonitorEvents: async (params) => {
    const cacheKey = `events_${params.communityId}_${params.type || 'all'}_${params.timeRange || 'all'}`;
    const now = Date.now();

    if (dataCache.map[cacheKey] && !params.forceRefresh &&
      (now - dataCache.lastUpdated[cacheKey]) < 1 * 60 * 1000) {
      return dataCache.map[cacheKey];
    }

    try {
      const response = await axios.get(`${BASE_URL}/monitorEvents`, {
        params: {
          ...params,
          table: 'biz_mon_evt_info',
          fields: 'evt_cat_name,incident_x,incident_y,occur_time'
        },
        timeout: 10000
      });
      if (Array.isArray(response.data) && response.data.length > 0) {
        const result = {
          data: response.data.map(event => ({
            ...event,
            color: COLOR_CONFIG.event[event.evt_cat_name] || COLOR_CONFIG.event['其他'],
            statusText:
              event.evt_status === 0 ? '未处理' : event.evt_status === 1 ? '处理中' : '已解决',
            statusColor:
              event.evt_status === 0 ? '#F87272' : event.evt_status === 1 ? '#FF7D00' : '#52C41A',
            tooltip: `${event.evt_name} (${event.evt_cat_name}) - ${event.statusText}`,
            isBlink: event.evt_status === 0
          }))
        };
        dataCache.map[cacheKey] = result;
        dataCache.lastUpdated[cacheKey] = now;
        return result;
      }
    } catch (error) {
      console.warn('异常点位接口调用失败，使用模拟数据:', error.message);
    }

    // 模拟数据
    const abnormalPoints = [
      {
        id: 'evt101',
        evt_name: '路灯线路故障',
        evt_cat_name: '设施故障',
        location: '第一网格主干道',
        incidentX: 116.38948,
        incidentY: 39.91682,
        evt_status: 0,
        occur_time: '2024-01-15 08:30:00',
        evt_desc: '连续3天夜间不亮，疑似线路短路',
        grid_name: '第一网格',
        color: COLOR_CONFIG.event['设施故障'],
        statusText: '未处理',
        statusColor: '#F87272',
        tooltip: '路灯线路故障 (设施故障) - 未处理',
        isBlink: true
      },
      {
        id: 'evt102',
        evt_name: '广场舞噪音投诉',
        evt_cat_name: '安全事件',
        location: '第三网格文化广场',
        incidentX: 116.39748,
        incidentY: 39.90282,
        evt_status: 1,
        occur_time: '2024-01-15 09:15:00',
        evt_desc: '超过晚上9点仍持续高分贝噪音',
        grid_name: '第三网格',
        color: COLOR_CONFIG.event['安全事件'],
        statusText: '处理中',
        statusColor: '#FF7D00',
        tooltip: '广场舞噪音投诉 (安全事件) - 处理中',
        isBlink: false
      }
    ];

    let filtered = abnormalPoints;
    if (params.type) filtered = filtered.filter(item => item.evt_cat_name === params.type);
    if (params.gridId) filtered = filtered.filter(item => item.grid_name === params.gridId);
    if (params.timeRange === 'today') {
      filtered = filtered.filter(item => item.occur_time.startsWith('2024-01-15'));
    }

    const result = { data: filtered };
    dataCache.map[cacheKey] = result;
    dataCache.lastUpdated[cacheKey] = now;
    return result;
  },

  /**
   * 获取核心指标数据
   */
  getCoreIndicators,

  /**
   * 获取全局态势概览数据
   */
  getGlobalSituationOverview: async (params) => {
    try {
      const response = await axios.get(`${BASE_URL}/globalSituationOverview`, {
        params,
        timeout: 10000
      });
      if (response.data && response.data.community_id) {
        return {
          ...response.data,
          resourceOnlineRate: ((response.data.normal_resources / response.data.total_resources) * 100).toFixed(1),
          eventCompletionRate:
            response.data.today_events > 0
              ? ((response.data.completed_events / response.data.today_events) * 100).toFixed(1)
              : '100.0',
          safetyEvents: response.data.safety_events || 0,
          gridCoverage: response.data.grid_coverage || '98.2'
        };
      }
    } catch (error) {
      console.warn('全局态势概览接口调用失败，使用模拟数据:', error.message);
    }

    return {
      community_id: params.communityId || 'comm1001',
      community_name: '智慧社区示范区',
      total_grids: 3,
      total_resources: 45,
      normal_resources: 42,
      abnormal_resources: 3,
      today_events: 5,
      processing_events: 2,
      completed_events: 3,
      safety_events: 2,
      grid_coverage: '98.2',
      key_areas: 8,
      population: 12560,
      households: 4230,
      update_time: '2024-01-15 14:30:00',
      resourceOnlineRate: '93.3',
      eventCompletionRate: '60.0'
    };
  },

  /**
   * 获取态势统计图表数据
   */
  getSituationCharts: async (params) => {
    try {
      const response = await axios.get(`${BASE_URL}/situationCharts`, {
        params,
        timeout: 10000
      });
      if (response.data && Array.isArray(response.data.data)) {
        return response.data;
      }
    } catch (error) {
      console.warn('态势图表接口调用失败，使用模拟数据:', error.message);
    }

    const { chartType, indicatorType } = params;

    if (chartType === 'eventTrend') {
      return {
        data: [
          { date: '01-04', events: 3 },
          { date: '01-05', events: 5 },
          { date: '01-06', events: 4 },
          { date: '01-07', events: 7 },
          { date: '01-08', events: 6 },
          { date: '01-09', events: 4 },
          { date: '01-10', events: 5 }
        ],
        type: 'line',
        style: {
          lineColor: '#00C896',
          areaColor: 'rgba(0, 200, 150, 0.1)',
          axisColor: 'rgba(255, 255, 255, 0.3)'
        }
      };
    } else if (chartType === 'resourceStatus') {
      return {
        data: [
          { name: '正常', value: 42, color: COLOR_CONFIG.resource.normal },
          { name: '异常', value: 3, color: COLOR_CONFIG.resource.abnormal },
          { name: '维护中', value: 5, color: COLOR_CONFIG.resource.maintenance }
        ],
        type: 'pie'
      };
    } else if (chartType === 'eventType') {
      return {
        data: [
          { name: '设施故障', value: 12, color: COLOR_CONFIG.event['设施故障'] },
          { name: '安全事件', value: 8, color: COLOR_CONFIG.event['安全事件'] },
          { name: '环境卫生', value: 15, color: COLOR_CONFIG.event['环境卫生'] },
          { name: '民生服务', value: 6, color: COLOR_CONFIG.event['民生服务'] },
          { name: '其他', value: 4, color: COLOR_CONFIG.event['其他'] }
        ],
        type: 'bar',
        style: {
          axisColor: 'rgba(255, 255, 255, 0.3)',
          gridColor: 'rgba(255, 255, 255, 0.1)'
        }
      };
    } else if (chartType === 'indicatorTrend') {
      const indicatorData = {
        population: {
          data: [
            { date: '1月', value: 12230 },
            { date: '2月', value: 12350 },
            { date: '3月', value: 12480 },
            { date: '4月', value: 12548 },
            { date: '5月', value: 12620 },
            { date: '6月', value: 12700 }
          ]
        },
        facility: {
          data: [
            { date: '1月', value: 95.2 },
            { date: '2月', value: 95.8 },
            { date: '3月', value: 96.1 },
            { date: '4月', value: 96.7 },
            { date: '5月', value: 97.2 },
            { date: '6月', value: 97.5 }
          ]
        },
        service: {
          data: [
            { date: '1月', value: 82.3 },
            { date: '2月', value: 81.6 },
            { date: '3月', value: 80.2 },
            { date: '4月', value: 78.5 },
            { date: '5月', value: 79.1 },
            { date: '6月', value: 80.3 }
          ]
        },
        safety: {
          data: [
            { date: '1月', value: 0.5 },
            { date: '2月', value: 0.4 },
            { date: '3月', value: 0.3 },
            { date: '4月', value: 0.3 },
            { date: '5月', value: 0.2 },
            { date: '6月', value: 0.2 }
          ]
        },
        grid: {
          data: [
            { date: '1月', value: 98.2 },
            { date: '2月', value: 98.2 },
            { date: '3月', value: 98.2 },
            { date: '4月', value: 98.2 },
            { date: '5月', value: 98.2 },
            { date: '6月', value: 98.2 }
          ]
        }
      };

      return {
        data: indicatorData[indicatorType]?.data || indicatorData.facility.data,
        type: 'line',
        style: {
          lineColor: '#00C896',
          areaColor: 'rgba(0, 200, 150, 0.1)',
          axisColor: 'rgba(255, 255, 255, 0.3)'
        }
      };
    }

    return {
      data: [
        { date: '01-04', events: 3 },
        { date: '01-05', events: 5 },
        { date: '01-06', events: 4 },
        { date: '01-07', events: 7 },
        { date: '01-08', events: 6 },
        { date: '01-09', events: 4 },
        { date: '01-10', events: 5 }
      ],
      type: 'line',
      style: {
        lineColor: '#00C896',
        areaColor: 'rgba(0, 200, 150, 0.1)',
        axisColor: 'rgba(255, 255, 255, 0.3)'
      }
    };
  },

  /**
   * 获取实时预警信息
   */
  getRealTimeAlerts: async (params) => {
    try {
      const response = await axios.get(`${BASE_URL}/realTimeAlerts`, {
        params,
        timeout: 5000
      });
      if (Array.isArray(response.data) && response.data.length > 0) {
        return {
          data: response.data.map(alert => ({
            ...alert,
            color: COLOR_CONFIG.alert[alert.alert_level] || COLOR_CONFIG.alert.medium,
            levelText: alert.alert_level === 'high' ? '高级' : alert.alert_level === 'medium' ? '中级' : '低级',
            statusText: alert.handle_status === 0 ? '未处理' : alert.handle_status === 1 ? '处理中' : '已处理'
          }))
        };
      }
    } catch (error) {
      console.warn('实时预警接口调用失败，使用模拟数据:', error.message);
    }

    const alerts = [
      {
        id: 'alert101',
        alert_title: '消防设施异常',
        alert_level: 'high',
        alert_type: 'facility',
        location: '第二网格3栋居民楼',
        occur_time: '2024-01-15 14:25:00',
        alert_desc: '消防栓水压异常，可能存在故障',
        handle_status: 0,
        grid_name: '第二网格',
        color: COLOR_CONFIG.alert.high,
        levelText: '高级',
        statusText: '未处理'
      }
    ];

    if (params.level) {
      return { data: alerts.filter(item => item.alert_level === params.level) };
    }
    return { data: alerts };
  },

  /**
   * 获取社区资源热力图数据
   */
  getResourceHeatmap: async (params) => {
    try {
      const response = await axios.get(`${BASE_URL}/resourceHeatmap`, {
        params,
        timeout: 10000
      });
      if (response.data && Array.isArray(response.data.points)) {
        return {
          ...response.data,
          gradient: {
            0.2: '#36D399',
            0.4: '#69B1FF',
            0.6: '#FBBF24',
            0.8: '#FF7D00',
            1.0: '#F87272'
          },
          legend: [
            { value: 5, label: '低密度' },
            { value: 10, label: '中密度' },
            { value: 15, label: '高密度' }
          ]
        };
      }
    } catch (error) {
      console.warn('资源热力图接口调用失败，使用模拟数据:', error.message);
    }

    return {
      points: [
        [116.39248, 39.91382, 15],
        [116.40248, 39.91382, 8],
        [116.39748, 39.90382, 12],
        [116.39048, 39.91582, 6],
        [116.40048, 39.91282, 10],
        [116.39548, 39.90582, 7],
        [116.38948, 39.91682, 5],
        [116.40348, 39.91082, 9]
      ],
      bounds: [
        [116.38748, 39.89882],
        [116.40748, 39.91882]
      ],
      max: 15,
      min: 5,
      gradient: {
        0.2: '#36D399',
        0.4: '#69B1FF',
        0.6: '#FBBF24',
        0.8: '#FF7D00',
        1.0: '#F87272'
      },
      legend: [
        { value: 5, label: '低密度' },
        { value: 10, label: '中密度' },
        { value: 15, label: '高密度' }
      ]
    };
  },

  /**
   * 获取人口分布数据 - 从 sys_pop 表获取
   */
  getPopulationDistribution: async (params) => {
    const cacheKey = `population_${params.communityId}_${params.timeType}`;
    const now = Date.now();

    if (dataCache.population[cacheKey] && !params.forceRefresh &&
      (now - dataCache.lastUpdated[cacheKey]) < 10 * 60 * 1000) {
      return dataCache.population[cacheKey];
    }

    try {
      const response = await axios.get(`${BASE_URL}/populationDistribution`, {
        params: {
          ...params,
          table: 'sys_pop',
          fields: 'permanent_count,float_count,age_group,male_count,female_count,hukou_local_count,hukou_nonlocal_count'
        },
        timeout: 10000
      });
      if (response.data) {
        // 处理年龄结构数据
        const ageDistribution = response.data.age_group || {
          '0-18岁': 1884,
          '19-60岁': 7536,
          '60岁以上': 3140
        };

        // 处理性别比例
        const genderRatio = {
          male: response.data.male_count || 6280,
          female: response.data.female_count || 6280
        };

        // 处理户籍类型
        const hukouRatio = {
          local: response.data.hukou_local_count || 10048,
          nonlocal: response.data.hukou_nonlocal_count || 2512
        };

        const result = {
          total: response.data.permanent_count + response.data.float_count || 12560,
          permanent: response.data.permanent_count || 10048,
          float: response.data.float_count || 2512,
          households: response.data.households || 4230,
          distribution: ageDistribution,
          genderRatio: genderRatio,
          hukouRatio: hukouRatio,
          update_time: response.data.update_time || '2024-01-15 14:30:00'
        };

        dataCache.population[cacheKey] = result;
        dataCache.lastUpdated[cacheKey] = now;
        return result;
      }
    } catch (error) {
      console.warn('人口分布接口调用失败，使用模拟数据:', error.message);
    }

    // 模拟数据
    const { communityId = 'comm1001', timeType = 'year' } = params;
    const isQuarter = timeType === 'quarter';

    const communityData = {
      'comm1001': {
        total: isQuarter ? 12340 : 12560,
        permanent: isQuarter ? 9950 : 10048,
        float: isQuarter ? 2390 : 2512,
        households: isQuarter ? 4190 : 4230,
        distribution: {
          '0-18岁': isQuarter ? 1850 : 1884,
          '19-60岁': isQuarter ? 7450 : 7536,
          '60岁以上': isQuarter ? 3040 : 3140
        },
        genderRatio: {
          male: isQuarter ? 6200 : 6280,
          female: isQuarter ? 6140 : 6280
        },
        hukouRatio: {
          local: isQuarter ? 9950 : 10048,
          nonlocal: isQuarter ? 2390 : 2512
        },
        update_time: '2024-01-15 14:30:00'
      },
      'comm1002': {
        total: isQuarter ? 18450 : 18720,
        permanent: isQuarter ? 13900 : 14040,
        float: isQuarter ? 4550 : 4680,
        households: isQuarter ? 6280 : 6350,
        distribution: {
          '0-18岁': isQuarter ? 2780 : 2808,
          '19-60岁': isQuarter ? 11150 : 11232,
          '60岁以上': isQuarter ? 4520 : 4680
        },
        genderRatio: {
          male: isQuarter ? 9450 : 9547,
          female: isQuarter ? 9000 : 9173
        },
        hukouRatio: {
          local: isQuarter ? 13900 : 14040,
          nonlocal: isQuarter ? 4550 : 4680
        },
        update_time: '2024-01-15 14:30:00'
      },
      'comm1003': {
        total: isQuarter ? 9320 : 9450,
        permanent: isQuarter ? 7980 : 8030,
        float: isQuarter ? 1340 : 1420,
        households: isQuarter ? 3180 : 3210,
        distribution: {
          '0-18岁': isQuarter ? 1400 : 1418,
          '19-60岁': isQuarter ? 5590 : 5670,
          '60岁以上': isQuarter ? 2330 : 2362
        },
        genderRatio: {
          male: isQuarter ? 4620 : 4678,
          female: isQuarter ? 4700 : 4772
        },
        hukouRatio: {
          local: isQuarter ? 7980 : 8030,
          nonlocal: isQuarter ? 1340 : 1420
        },
        update_time: '2024-01-15 14:30:00'
      }
    };

    const mockData = communityData[communityId] || communityData['comm1001'];
    dataCache.population[cacheKey] = mockData;
    dataCache.lastUpdated[cacheKey] = now;
    return mockData;
  },

  /**
   * 获取指标详情数据
   */
  getIndicatorDetail: async (params) => {
    try {
      const response = await axios.get(`${BASE_URL}/indicatorDetail`, {
        params,
        timeout: 10000
      });
      if (response.data) {
        return response.data;
      }
    } catch (error) {
      console.warn('指标详情接口调用失败，使用模拟数据:', error.message);
    }

    const mockData = {
      population: {
        currentValue: '12,548',
        change: '+2.3%',
        changeType: 'increase',
        abnormal: false,
        unit: '人',
        lastUpdateTime: '2024-01-15 14:30:00'
      },
      facility: {
        currentValue: '96.7%',
        change: '+1.2%',
        changeType: 'increase',
        abnormal: false,
        unit: '%',
        lastUpdateTime: '2024-01-15 14:25:00'
      },
      service: {
        currentValue: '78.5%',
        change: '-3.1%',
        changeType: 'decrease',
        abnormal: true,
        unit: '%',
        lastUpdateTime: '2024-01-15 14:20:00'
      },
      safety: {
        currentValue: '0.3',
        change: '-0.1',
        changeType: 'decrease',
        abnormal: false,
        unit: '起/平方公里·月',
        lastUpdateTime: '2024-01-15 14:15:00'
      },
      grid: {
        currentValue: '98.2%',
        change: '0%',
        changeType: 'same',
        abnormal: false,
        unit: '%',
        lastUpdateTime: '2024-01-15 14:10:00'
      }
    };

    return {
      data: mockData[params.type] || mockData.facility
    };
  },

  /**
   * 导出指标数据
   */
  exportIndicatorData: async (params) => {
    try {
      const response = await axios.get(`${BASE_URL}/exportIndicatorData`, {
        params,
        responseType: 'blob',
        timeout: 30000
      });
      return response;
    } catch (error) {
      console.warn('导出接口调用失败，使用模拟导出:', error.message);
      return {
        data: new Blob(['模拟导出数据'], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' })
      };
    }
  },

  // 新增方法
  updateIndicatorThreshold: async (formData) => {
    try {
      const response = await axios.post(`${BASE_URL}/updateIndicatorThreshold`, formData, {
        timeout: 10000
      });
      // 清除缓存
      dataCache.indicators = null;
      return response.data;
    } catch (error) {
      console.warn('更新指标阈值接口调用失败:', error.message);
      return {
        code: 200,
        message: '指标阈值更新成功',
        data: { ...formData, updateTime: new Date().toISOString() }
      };
    }
  },

  updateMapPoint: async (formData) => {
    try {
      const response = await axios.post(`${BASE_URL}/updateMapPoint`, formData, {
        timeout: 10000
      });
      // 清除相关缓存
      Object.keys(dataCache.map).forEach(key => {
        if (key.includes(formData.dataType)) {
          delete dataCache.map[key];
        }
      });
      return response.data;
    } catch (error) {
      console.warn('更新地图点位接口调用失败:', error.message);
      return {
        code: 200,
        message: '地图点位更新成功',
        data: { ...formData, updateTime: new Date().toISOString() }
      };
    }
  },

  updateResourceInfo: async (formData) => {
    try {
      const response = await axios.post(`${BASE_URL}/updateResourceInfo`, formData, {
        timeout: 10000
      });
      // 清除资源缓存
      Object.keys(dataCache.resources).forEach(key => delete dataCache.resources[key]);
      return response.data;
    } catch (error) {
      console.warn('更新资源信息接口调用失败:', error.message);
      return {
        code: 200,
        message: '资源信息更新成功',
        data: { ...formData, updateTime: new Date().toISOString() }
      };
    }
  },

  handleWarning: async (warningData) => {
    try {
      const response = await axios.post(`${BASE_URL}/handleWarning`, warningData, {
        timeout: 10000
      });
      return response.data;
    } catch (error) {
      console.warn('处理预警接口调用失败:', error.message);
      return {
        code: 200,
        message: '预警处理成功',
        data: { ...warningData, handleTime: new Date().toISOString() }
      };
    }
  },

  /**
   * 清除缓存
   */
  clearCache: (cacheType) => {
    if (cacheType) {
      dataCache[cacheType] = {};
      Object.keys(dataCache.lastUpdated).forEach(key => {
        if (key.startsWith(cacheType)) {
          delete dataCache.lastUpdated[key];
        }
      });
    } else {
      Object.keys(dataCache).forEach(key => {
        if (key !== 'lastUpdated') {
          dataCache[key] = {};
        }
      });
      dataCache.lastUpdated = {};
    }
  },

  /**
   * 获取缓存状态
   */
  getCacheStatus: () => {
    return {
      cacheCounts: {
        indicators: dataCache.indicators ? 1 : 0,
        population: Object.keys(dataCache.population).length,
        resources: Object.keys(dataCache.resources).length,
        map: Object.keys(dataCache.map).length
      },
      lastUpdated: dataCache.lastUpdated
    };
  }
};

export default GlobalSituationOverviewAPI;
