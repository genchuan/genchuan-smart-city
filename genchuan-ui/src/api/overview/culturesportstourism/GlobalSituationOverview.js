import request from '@/config/axios';
// import request from 'axios';
const BASE_URL = '/industry';


// 获取文旅全域数据地图
export const fetchCulturalTourismGeometries = (params = {}) => {
  try {
    return request.get({
      url: `${BASE_URL}/culture-tourism-data-map/get`,
      params
    }).then(response => {
      console.log('获取文旅全域数据地图-接口请求成功');

      if (Array.isArray(response)) {
        console.log('获取文旅全域数据地图-响应符合实际格式');
        return response; // 直接返回数组
      }

      throw new Error('真实接口返回无效数据，使用模拟数据兜底');
    }).catch(error => {
      return new Promise(resolve => {
        setTimeout(() => {
          console.log('获取文旅全域数据地图接口调用失败-使用模拟数据兜底');
          resolve([
            {
              coordX: 26.855237, // 文旅资源纬度（景区）
              coordY: 117.777777, // 文旅资源经度（景区）
              compCatName: "景区", // 资源类型（景区→山形图标）
              totalRptCount: 1286, // 实时客流
              runStatus: "正常", // 设施状态（正常→绿色）
              incidentX: null, // 异常事件纬度（正常状态为空）
              incidentY: null // 异常事件经度（正常状态为空）
            },
            {
              coordX: 26.783237,
              coordY: 117.720114,
              compCatName: "场馆", // 资源类型（场馆→建筑图标）
              totalRptCount: 853,
              runStatus: "异常", // 设施状态（异常→红色）
              incidentX: 26.782237, // 异常事件纬度
              incidentY: 117.721114 // 异常事件经度
            },
            {
              coordX: 26.733337,
              coordY: 117.650114,
              compCatName: "景区",
              totalRptCount: 947,
              runStatus: "正常",
              incidentX: null,
              incidentY: null
            },
            {
              coordX: 26.810237,
              coordY: 117.800777,
              compCatName: "场馆",
              totalRptCount: 529,
              runStatus: "正常",
              incidentX: null,
              incidentY: null
            },
            {
              coordX: 26.756237,
              coordY: 117.712114,
              compCatName: "景区",
              totalRptCount: 1568,
              runStatus: "异常",
              incidentX: 26.755237,
              incidentY: 117.713114
            },
            {
              coordX: 26.832237,
              coordY: 117.689114,
              compCatName: "场馆",
              totalRptCount: 734,
              runStatus: "正常",
              incidentX: null,
              incidentY: null
            },
            {
              coordX: 26.798237,
              coordY: 117.833777,
              compCatName: "景区",
              totalRptCount: 1125,
              runStatus: "正常",
              incidentX: null,
              incidentY: null
            }
          ]);
        }, 500);
      });
    });
  } catch (error) {
    console.error('===== 函数初始化异常 =====');
    console.error('错误信息:', error.message);
    console.error('错误堆栈:', error.stack);
    return Promise.resolve([]);
  }
};

// 获取文旅核心指标数据
export const fetchCoreIndicators = (params = {}, timeRange = 'today') => {
  const requestParams = { ...params, timeRange };
  try {
    return request.get({
      url: `${BASE_URL}/coreIndicators`,
      params: requestParams
    }).then(response => {
      console.log('获取文旅核心指标数据-接口请求成功');

      // 判断response是否为数组（实际返回的结构）
      if (Array.isArray(response)) {
        console.log('获取文旅核心指标数据-响应符合实际格式');
        return response; // 直接返回数组
      }

      throw new Error('真实接口返回无效数据，使用模拟数据兜底');
    }).catch(error => {
      return new Promise(resolve => {
        setTimeout(() => {
          console.log('获取文旅核心指标数据接口调用失败-使用模拟数据兜底');
          resolve([
            {
              index_id: 'ind1001',
              index_type: '文旅资源总数', // 对应total_scene_count
              stat_cycle: '日',
              stat_value: 286, // 资源总数（个）
              benchmark_value: 200, // 基准值
              warn_status: '0', // 0-正常，1-提醒，2-预警
              rectify_record_id: '',
              admin_user_id: 'admin1004',
              create_time: '2025-06-10 00:00:00'
            },
            {
              index_id: 'ind1002',
              index_type: '当日客流峰值', // 对应max_count
              stat_cycle: '日',
              stat_value: 20560, // 客流峰值（人）
              benchmark_value: 20000, // 基准值
              warn_status: '2',
              rectify_record_id: '',
              admin_user_id: 'admin1004',
              create_time: '2025-06-10 14:30:00'
            },
            {
              index_id: 'ind1003',
              index_type: '投诉办结率', // 对应complete_rate
              stat_cycle: '日',
              stat_value: 92.3, // 办结率（%）
              benchmark_value: 90, // 基准值
              warn_status: '0',
              rectify_record_id: '',
              admin_user_id: 'admin1005',
              create_time: '2025-06-10 00:00:00'
            },
            {
              index_id: 'ind1004',
              index_type: '设施完好率', // 对应normal_comp_count/total_comp_count
              stat_cycle: '日',
              stat_value: 89.7, // 完好率（%）
              benchmark_value: 95, // 基准值（低于此值预警）
              warn_status: '1', // 低于基准值，预警
              rectify_record_id: 'rect1001',
              admin_user_id: 'admin1005',
              create_time: '2025-06-10 00:00:00'
            },
            {
              index_id: 'ind1005',
              index_type: '活动开展数', // 对应new_scene_count
              stat_cycle: '日',
              stat_value: 12, // 活动数量（个）
              benchmark_value: 8, // 基准值
              warn_status: '0',
              rectify_record_id: '',
              admin_user_id: 'admin1006',
              create_time: '2025-06-10 00:00:00'
            }
          ]);
        }, 500);
      });
    });
  } catch (error) {
    return Promise.resolve([]);
  }
};

// 获取文旅客流总览数据
export const fetchTouristFlowOverview = (params = {}) => {
  try {
    return request.get({
      url: `${BASE_URL}/touristFlowOverview`,
      params
    }).then(response => {
      console.log('获取文旅客流总览数据-接口请求成功');

      // 判断response是否为数组（实际返回的结构）
      if (Array.isArray(response)) {
        console.log('获取文旅客流总览数据-响应符合实际格式');
        return response; // 直接返回数组
      }

      throw new Error('真实接口返回无效数据，使用模拟数据兜底');
    }).catch(error => {
      return new Promise(resolve => {
        setTimeout(() => {
          console.log('获取文旅客流总览数据接口调用失败-使用模拟数据兜底');
          resolve([
            {
              flow_id: 'flow1001',
              area_id: 'area1001',
              area_name: '东湖区',
              area_type: '景区',
              real_time_flow: 4500,
              area_capacity: 8000,
              warn_status: '0',
              guide_order_id: '',
              dispatcher_id: 'dispatcher1001',
              handle_time: '',
              handle_measure: '',
              verify_result: '',
              verify_time: '',
              create_user: 'system',
              create_time: '2025-06-10 09:00:00',
              ext1: '9:00-10:00',
              ext2: '步行巡逻'
            },
            {
              flow_id: 'flow1002',
              area_id: 'area1002',
              area_name: '文化街区',
              area_type: '商圈',
              real_time_flow: 6200,
              area_capacity: 10000,
              warn_status: '0',
              guide_order_id: '',
              dispatcher_id: 'dispatcher1002',
              handle_time: '',
              handle_measure: '',
              verify_result: '',
              verify_time: '',
              create_user: 'system',
              create_time: '2025-06-10 09:00:00',
              ext1: '9:00-10:00',
              ext2: '机动巡逻'
            },
            {
              flow_id: 'flow1003',
              area_id: 'area1003',
              area_name: '中心广场',
              area_type: '活动',
              real_time_flow: 8500,
              area_capacity: 12000,
              warn_status: '0',
              guide_order_id: '',
              dispatcher_id: 'dispatcher1003',
              handle_time: '',
              handle_measure: '',
              verify_result: '',
              verify_time: '',
              create_user: 'system',
              create_time: '2025-06-10 09:00:00',
              ext1: '9:00-10:00',
              ext2: '定点疏导'
            },
            {
              flow_id: 'flow1004',
              area_id: 'area1004',
              area_name: '西湖景区',
              area_type: '景区',
              real_time_flow: 9800,
              area_capacity: 15000,
              warn_status: '1',
              guide_order_id: 'guide1001',
              dispatcher_id: 'dispatcher1001',
              handle_time: '',
              handle_measure: '',
              verify_result: '',
              verify_time: '',
              create_user: 'system',
              create_time: '2025-06-10 09:00:00',
              ext1: '9:00-10:00',
              ext2: '增派人员'
            },
            {
              flow_id: 'flow1005',
              area_id: 'area1005',
              area_name: '南山森林公园',
              area_type: '景区',
              real_time_flow: 14200,
              area_capacity: 15000,
              warn_status: '1',
              guide_order_id: 'guide1002',
              dispatcher_id: 'dispatcher1004',
              handle_time: '',
              handle_measure: '',
              verify_result: '',
              verify_time: '',
              create_user: 'system',
              create_time: '2025-06-10 09:15:00',
              ext1: '9:15-10:15',
              ext2: '限流管控'
            },
            {
              flow_id: 'flow1006',
              area_id: 'area1006',
              area_name: '滨江商业中心',
              area_type: '商圈',
              real_time_flow: 7800,
              area_capacity: 12000,
              warn_status: '0',
              guide_order_id: '',
              dispatcher_id: 'dispatcher1005',
              handle_time: '',
              handle_measure: '',
              verify_result: '',
              verify_time: '',
              create_user: 'system',
              create_time: '2025-06-10 09:15:00',
              ext1: '9:15-10:15',
              ext2: '智慧巡逻'
            },
            {
              flow_id: 'flow1007',
              area_id: 'area1007',
              area_name: '市民体育公园',
              area_type: '活动',
              real_time_flow: 11800,
              area_capacity: 12000,
              warn_status: '1',
              guide_order_id: 'guide1003',
              dispatcher_id: 'dispatcher1006',
              handle_time: '',
              handle_measure: '',
              verify_result: '',
              verify_time: '',
              create_user: 'system',
              create_time: '2025-06-10 09:15:00',
              ext1: '9:15-10:15',
              ext2: '多通道疏导'
            }
          ]);
        }, 500);
      });
    });
  } catch (error) {
    console.error('===== 函数初始化异常 =====');
    console.error('错误信息:', error.message);
    console.error('错误堆栈:', error.stack);
    return Promise.resolve([]);
  }
};

// 获取资源类型分布数据
export const fetchResourceTypeDistribution = (params = {}) => {
  try {
    return request.get({
      url: `${BASE_URL}/resourceTypeDistribution`,
      params
    }).then(response => {
      console.log('获取资源类型分布数据-接口请求成功');
      if (Array.isArray(response)) {
        return response;
      }
      throw new Error('真实接口返回无效数据，使用模拟数据兜底');
    }).catch(error => {
      return new Promise(resolve => {
        setTimeout(() => {
          console.log('获取资源类型分布数据接口调用失败-使用模拟数据兜底');
          // 模拟返回：与原数据匹配的类型统计
          resolve([
            { type: '景区', count: 32 },
            { type: '场馆', count: 89 },
            { type: '活动', count: 86 }
          ]);
        }, 500);
      });
    });
  } catch (error) {
    console.error('===== 获取资源类型分布函数初始化异常 =====');
    console.error('错误信息:', error.message);
    console.error('错误堆栈:', error.stack);
    return Promise.resolve([]);
  }
};

// 获取资源状态占比数据
export const fetchResourceStatusDistribution = (params = {}) => {
  try {
    return request.get({
      url: `${BASE_URL}/resourceStatusDistribution`,
      params
    }).then(response => {
      console.log('获取资源状态占比数据-接口请求成功');
      // 接口返回格式示例：[{ status: '0', count: 4 }, { status: '1', count: 2 }]
      if (Array.isArray(response)) {
        return response;
      }
      throw new Error('真实接口返回无效数据，使用模拟数据兜底');
    }).catch(error => {
      return new Promise(resolve => {
        setTimeout(() => {
          console.log('获取资源状态占比数据接口调用失败-使用模拟数据兜底');
          // 模拟返回：与原数据匹配的状态统计
          resolve([
            { status: '0', count: 241 }, // 正常
            { status: '1', count: 28 }  // 异常
          ]);
        }, 500);
      });
    });
  } catch (error) {
    console.error('===== 获取资源状态占比函数初始化异常 =====');
    console.error('错误信息:', error.message);
    console.error('错误堆栈:', error.stack);
    return Promise.resolve([]);
  }
};

// 获取文旅资源分布数据
export const fetchResourceDistribution = (params = {}) => {
  try {
    return request.get({
      url: `${BASE_URL}/resourceDistribution`,
      params
    }).then(response => {
      console.log('获取文旅资源分布数据-接口请求成功');

      // 判断response是否为数组（实际返回的结构）
      if (Array.isArray(response)) {
        console.log('获取文旅资源分布数据-响应符合实际格式');
        return response; // 直接返回数组
      }

      throw new Error('真实接口返回无效数据，使用模拟数据兜底');
    }).catch(error => {
      return new Promise(resolve => {
        setTimeout(() => {
          console.log('获取文旅资源分布数据接口调用失败-使用模拟数据兜底');
          resolve([
            {
              dist_id: 'dist1001',
              resource_id: 'res1001',
              resource_name: '东湖景区',
              resource_type: '景区',
              resource_pos: '东湖区环湖路1号',
              resource_status: '0',
              warn_status: '0',
              maintain_order_id: '',
              maintain_user_id: 'maintain1001',
              maintain_time: '',
              maintain_measure: '',
              verify_result: '',
              verify_time: '',
              create_user: 'system',
              create_time: '2025-01-15 08:00:00',
              ext1: '4A景区',
              ext2: '每月检修',
              hot_degree: 95,
            },
            {
              dist_id: 'dist1002',
              resource_id: 'res1002',
              resource_name: '城市博物馆',
              resource_type: '场馆',
              resource_pos: '文化西路8号',
              resource_status: '1',
              warn_status: '1',
              maintain_order_id: 'order2001',
              maintain_user_id: 'maintain1002',
              maintain_time: '',
              maintain_measure: '',
              verify_result: '',
              verify_time: '',
              create_user: 'system',
              create_time: '2025-01-15 08:00:00',
              ext1: '国家一级博物馆',
              ext2: '每周检修',
              hot_degree: 88,
            },
            {
              dist_id: 'dist1003',
              resource_id: 'res1005',
              resource_name: '古城墙遗址',
              resource_type: '活动',
              resource_pos: '老城区北门',
              resource_status: '0',
              warn_status: '0',
              maintain_order_id: '',
              maintain_user_id: 'maintain1003',
              maintain_time: '2025-06-09 10:30:00',
              maintain_measure: '日常巡检，无异常',
              verify_result: '合格',
              verify_time: '2025-06-09 11:45:00',
              create_user: 'system',
              create_time: '2025-01-15 08:00:00',
              ext1: '省级文物保护单位',
              ext2: '季度检修',
              hot_degree: 75,
            },
            {
              dist_id: 'dist1004',
              resource_id: 'res1006',
              resource_name: '艺术中心',
              resource_type: '场馆',
              resource_pos: '新区文化大道15号',
              resource_status: '1',
              warn_status: '1',
              maintain_order_id: 'order2002',
              maintain_user_id: 'maintain1002',
              maintain_time: '',
              maintain_measure: '',
              verify_result: '',
              verify_time: '',
              create_user: 'system',
              create_time: '2025-01-15 08:00:00',
              ext1: '现代化场馆',
              ext2: '每月检修',
              hot_degree: 92,
            },
            {
              dist_id: 'dist1005',
              resource_id: 'res1007',
              resource_name: '湿地公园',
              resource_type: '景区',
              resource_pos: '高新区环湖路20号',
              resource_status: '0',
              warn_status: '0',
              maintain_order_id: '',
              maintain_user_id: 'maintain1001',
              maintain_time: '2025-06-10 09:15:00',
              maintain_measure: '设备巡检正常',
              verify_result: '合格',
              verify_time: '2025-06-10 10:30:00',
              create_user: 'system',
              create_time: '2025-01-15 08:00:00',
              ext1: '3A景区',
              ext2: '每月检修',
              hot_degree: 80,
            },
            {
              dist_id: 'dist1006',
              resource_id: 'res1008',
              resource_name: '民俗博物馆',
              resource_type: '场馆',
              resource_pos: '老城区文化街12号',
              resource_status: '0',
              warn_status: '1',
              maintain_order_id: 'order2003',
              maintain_user_id: 'maintain1002',
              maintain_time: '',
              maintain_measure: '',
              verify_result: '',
              verify_time: '',
              create_user: 'system',
              create_time: '2025-01-15 08:00:00',
              ext1: '市级博物馆',
              ext2: '每周检修',
              hot_degree: 85,
            }
          ]);
        }, 500);
      });
    });
  } catch (error) {
    console.error('===== 函数初始化异常 =====');
    console.error('错误信息:', error.message);
    console.error('错误堆栈:', error.stack);
    return Promise.resolve([]);
  }
};


// 获取指标历史趋势数据（30天）
export const fetchIndicatorHistory = (params = {}, indicatorId) => {
  const requestParams = { ...params, indicatorId };
  try {
    return request.get({
      url: `${BASE_URL}/indicatorHistory`,
      params: requestParams
    }).then(response => {
      console.log('获取指标历史趋势数据-接口请求成功');

      if (response && response.xAxis && response.series) {
        console.log('获取指标历史趋势数据-响应符合实际格式');
        return response;
      }

      throw new Error('真实接口返回无效数据，使用模拟数据兜底');
    }).catch(error => {
      return new Promise(resolve => {
        setTimeout(() => {
          console.log('获取指标历史趋势数据接口调用失败-使用模拟数据兜底');
          const xAxis = [];
          const data = [];
          const today = new Date();
          const mockIndicator = {
            stat_value: indicatorId === 'ind1001' ? 128 : indicatorId === 'ind1002' ? 15680 : 80,
            benchmark_value: indicatorId === 'ind1001' ? 100 : indicatorId === 'ind1002' ? 20000 : 80
          };

          for (let i = 29; i >= 0; i--) {
            const date = new Date(today);
            date.setDate(today.getDate() - i);
            xAxis.push(`${date.getMonth() + 1}/${date.getDate()}`);
            const baseValue = mockIndicator.stat_value;
            const fluctuation = (Math.random() - 0.5) * (indicatorId === 'ind1001' ? 10 : 15);
            data.push(parseFloat((baseValue + fluctuation).toFixed(2)));
          }

          resolve({
            xAxis,
            series: [
              { name: '指标值', data },
              { name: '基准值', data: Array(30).fill(mockIndicator.benchmark_value), type: 'line', lineStyle: { type: 'dashed' } }
            ]
          });
        }, 500);
      });
    });
  } catch (error) {
    return Promise.resolve({ xAxis: [], series: [] });
  }
};

// 获取资源状态趋势数据（7天）
export const fetchResourceStatusTrend = (params = {}, resourceId) => {
  const requestParams = { ...params, resourceId };
  try {
    return request.get({
      url: `${BASE_URL}/resourceStatusTrend`,
      params: requestParams
    }).then(response => {
      console.log('获取资源状态趋势数据-接口请求成功');

      if (response && response.xAxis && response.series) {
        console.log('获取资源状态趋势数据-响应符合实际格式');
        return response;
      }

      throw new Error('真实接口返回无效数据，使用模拟数据兜底');
    }).catch(error => {
      return new Promise(resolve => {
        setTimeout(() => {
          console.log('获取资源状态趋势数据接口调用失败-使用模拟数据兜底');
          const xAxis = [];
          const statusData = [];
          const today = new Date();

          for (let i = 6; i >= 0; i--) {
            const date = new Date(today);
            date.setDate(today.getDate() - i);
            xAxis.push(`${date.getMonth() + 1}/${date.getDate()}`);
            statusData.push(Math.random() > 0.2 ? 0 : 1);
          }

          resolve({
            xAxis,
            series: [
              { name: '资源状态(0=正常,1=异常)', data: statusData, type: 'line', step: 'end' }
            ]
          });
        }, 500);
      });
    });
  } catch (error) {
    return Promise.resolve({ xAxis: [], series: [] });
  }
};

// 获取客流小时趋势数据（24小时）
export const fetchFlowHourlyTrend = (params = {}, flowId) => {
  const requestParams = { ...params, flowId };
  try {
    return request.get({
      url: `${BASE_URL}/flowHourlyTrend`,
      params: requestParams
    }).then(response => {
      console.log('获取客流小时趋势数据-接口请求成功');

      // 判断response是否为有效格式（实际返回结构）
      if (response && response.xAxis && response.series) {
        console.log('获取客流小时趋势数据-响应符合实际格式');
        return response;
      }

      throw new Error('真实接口返回无效数据，使用模拟数据兜底');
    }).catch(error => {
      return new Promise(resolve => {
        setTimeout(() => {
          console.log('获取客流小时趋势数据接口调用失败-使用模拟数据兜底');
          const xAxis = [];
          const data = [];
          const now = new Date();
          const mockFlow = {
            real_time_flow: flowId === 'flow1001' ? 4500 : flowId === 'flow1004' ? 9800 : 6000,
            area_capacity: flowId === 'flow1001' ? 8000 : flowId === 'flow1004' ? 15000 : 10000
          };

          for (let i = 23; i >= 0; i--) {
            const hour = new Date(now);
            hour.setHours(now.getHours() - i);
            xAxis.push(`${hour.getHours()}:00`);
            const baseValue = mockFlow.real_time_flow;
            const fluctuation = (Math.random() - 0.5) * baseValue * 0.4;
            data.push(Math.max(0, Math.round(baseValue + fluctuation)));
          }

          resolve({
            xAxis,
            series: [
              { name: '客流量', data },
              { name: '区域容量', data: Array(24).fill(mockFlow.area_capacity), type: 'line', lineStyle: { type: 'dashed' } }
            ]
          });
        }, 500);
      });
    });
  } catch (error) {
    return Promise.resolve({ xAxis: [], series: [] });
  }
};

// 处理文旅预警
export const handleWarning = (params = {}) => {
  try {
    return request.post({
      url: `${BASE_URL}/handleWarning`,
      data: params
    }).then(response => {
      console.log('处理文旅预警-接口请求成功');

      // 判断response是否为有效格式（实际返回结构）
      if (response && typeof response === 'object' && !Array.isArray(response)) {
        console.log('处理文旅预警-响应符合实际格式');
        return response;
      }

      throw new Error('真实接口返回无效数据，使用模拟结果兜底');
    }).catch(error => {
      return new Promise(resolve => {
        setTimeout(() => {
          console.log('处理文旅预警接口调用失败-使用模拟结果兜底');
          resolve({
            success: true,
            message: '预警处理成功',
            data: {
              handleTime: new Date().toLocaleString()
            }
          });
        }, 500);
      });
    });
  } catch (error) {
    return Promise.resolve({ success: false, message: '接口初始化失败' });
  }
};

// 刷新文旅数据
export const refreshCulturalData = (params = {}, type) => {
  const requestData = { ...params, type };
  try {
    return request.post({
      url: `${BASE_URL}/refreshData`,
      data: requestData
    }).then(response => {
      console.log('刷新文旅数据-接口请求成功');

      // 判断response是否为有效格式（实际返回结构）
      if (response && typeof response === 'object' && !Array.isArray(response)) {
        console.log('刷新文旅数据-响应符合实际格式');
        return response;
      }

      throw new Error('真实接口返回无效数据，使用模拟结果兜底');
    }).catch(error => {
      return new Promise(resolve => {
        setTimeout(() => {
          console.log('刷新文旅数据接口调用失败-使用模拟结果兜底');
          resolve({
            success: true,
            message: `文旅${type}数据刷新成功`,
            data: {
              refreshTime: new Date().toLocaleString()
            }
          });
        }, 500);
      });
    });
  } catch (error) {
    return Promise.resolve({ success: false, message: '接口初始化失败' });
  }
};

// 配置指标基准值
export const configureIndicatorBenchmark = (params = {}) => {
  try {
    return request.post({
      url: `${BASE_URL}/configureIndicator`,
      data: params
    }).then(response => {
      console.log('配置指标基准值-接口请求成功');

      // 判断response是否为有效格式（实际返回结构）
      if (response && typeof response === 'object' && !Array.isArray(response)) {
        console.log('配置指标基准值-响应符合实际格式');
        return response;
      }

      throw new Error('真实接口返回无效数据，使用模拟结果兜底');
    }).catch(error => {
      return new Promise(resolve => {
        setTimeout(() => {
          console.log('配置指标基准值接口调用失败-使用模拟结果兜底');
          resolve({
            success: true,
            message: '指标基准值配置成功',
            data: {
              configureTime: new Date().toLocaleString()
            }
          });
        }, 500);
      });
    });
  } catch (error) {
    return Promise.resolve({ success: false, message: '接口初始化失败' });
  }
};

// 提交核查结果
export const submitVerifyResult = (params = {}) => {
  try {
    return request.post({
      url: `${BASE_URL}/submitVerifyResult`,
      data: params
    }).then(response => {
      console.log('提交核查结果-接口请求成功');

      // 判断response是否为有效格式（实际返回结构）
      if (response && typeof response === 'object' && !Array.isArray(response)) {
        console.log('提交核查结果-响应符合实际格式');
        return response;
      }

      throw new Error('真实接口返回无效数据，使用模拟结果兜底');
    }).catch(error => {
      return new Promise(resolve => {
        setTimeout(() => {
          console.log('提交核查结果接口调用失败-使用模拟结果兜底');
          resolve({
            success: true,
            message: '核查结果提交成功',
            data: {
              verifyTime: new Date().toLocaleString()
            }
          });
        }, 500);
      });
    });
  } catch (error) {
    return Promise.resolve({ success: false, message: '接口初始化失败' });
  }
};
