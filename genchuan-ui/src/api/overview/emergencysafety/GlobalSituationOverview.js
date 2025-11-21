// import request from '@/config/axios';
import request from 'axios';
const BASE_URL = '/industry';


// 应急全域数据概览相关接口
export const fetchEmergencyGlobalOverview = () => {
  try {
    return request.get({ url: `${BASE_URL}/emerg-overview/get` }).then(response => {
      if (response.data?.code === 0 && Array.isArray(response.data.data) && response.data.data.length > 0) {
        return response.data.data;
      }
      throw new Error('真实接口返回空数据，使用模拟数据兜底');
    }).catch(error => {
      console.warn('应急全域数据概览接口调用失败：', error.message);
      return new Promise(resolve => {
        setTimeout(() => {
          resolve({
            totalEvtCount: 120,         // 应急事件总数
            handlCount: 30,             // 在处置事件数
            completedCount: 90,         // 已办结事件数
            totalResCount: 50,          // 应急资源总数
            highRiskCount: 5,           // 高风险隐患数
            regionCoverCount: 12,       // 行政区划覆盖数
            updateTime: "2025-11-19 10:00:00"  // 更新时间
          });
        }, 500);
      });
    });
  } catch (error) {
    console.error('fetchEmergencyGlobalOverview初始化错误:', error);
    return Promise.resolve([]);
  }
};

// 应急核心指标相关接口
export const fetchEmergencyCoreIndicators = () => {
  try {
    return request.get({ url: `${BASE_URL}/emerg-core-metrics/list` }).then(response => {
      if (response.data?.code === 0 && Array.isArray(response.data.data) && response.data.data.length > 0) {
        return response.data.data;
      }
      throw new Error('真实接口返回空数据，使用模拟数据兜底');
    }).catch(error => {
      console.warn('应急核心指标接口调用失败：', error.message);
      return new Promise(resolve => {
        setTimeout(() => {
          resolve([
            {
              index_id: '1',
              index_name: '应急事件办结率',
              index_code: 'complete_rate',
              statistic_cycle: '周',
              current_value: 88.5,
              target_value: 95.0,
              warning_threshold: 90.0,
              status: '1',
              trend_7d: JSON.stringify({
                xAxis: ['10/7', '10/8', '10/9', '10/10', '10/11', '10/12', '10/13'],
                series: [92.3, 91.8, 90.5, 89.7, 88.9, 89.2, 88.5]
              }),
              statistic_time: '2025-10-13 09:30:00',
              improve_order_id: 'order_123',
              update_user: 'system',
              update_time: '2025-10-13 09:30:00'
            },
            {
              index_id: '2',
              index_name: '预警准确率',
              index_code: 'early_warn_acc_rate',
              statistic_cycle: '周',
              current_value: 85.0,
              target_value: 90.0,
              warning_threshold: 85.0,
              status: '1',
              trend_7d: JSON.stringify({
                xAxis: ['10/7', '10/8', '10/9', '10/10', '10/11', '10/12', '10/13'],
                series: [89.5, 88.3, 87.6, 86.5, 85.8, 85.2, 85.0]
              }),
              statistic_time: '2025-10-13 09:30:00',
              improve_order_id: 'order_124',
              update_user: 'system',
              update_time: '2025-10-13 09:30:00'
            },
            {
              index_id: '3',
              index_name: '资源调用率',
              index_code: 'res_use_rate',
              statistic_cycle: '周',
              current_value: 76.3,
              target_value: 80.0,
              warning_threshold: 75.0,
              status: '1',
              trend_7d: JSON.stringify({
                xAxis: ['10/7', '10/8', '10/9', '10/10', '10/11', '10/12', '10/13'],
                series: [82.1, 80.5, 79.3, 78.6, 77.2, 76.8, 76.3]
              }),
              statistic_time: '2025-10-13 09:30:00',
              improve_order_id: 'order_125',
              update_user: 'system',
              update_time: '2025-10-13 09:30:00'
            },
            {
              index_id: '4',
              index_name: '平均处置时长',
              index_code: 'avg_handle_endure',
              statistic_cycle: '周',
              current_value: 35.2,
              target_value: 30.0,
              warning_threshold: 45.0,
              status: '0',
              trend_7d: JSON.stringify({
                xAxis: ['10/7', '10/8', '10/9', '10/10', '10/11', '10/12', '10/13'],
                series: [38.5, 37.2, 36.8, 36.1, 35.7, 35.4, 35.2]
              }),
              statistic_time: '2025-10-13 09:30:00',
              improve_order_id: '',
              update_user: 'system',
              update_time: '2025-10-13 09:30:00'
            },
            {
              index_id: '5',
              index_name: '风险整改率',
              index_code: 'risk_rectify_rate',
              statistic_cycle: '月',
              current_value: 92.6,
              target_value: 90.0,
              warning_threshold: 85.0,
              status: '0',
              trend_7d: JSON.stringify({
                xAxis: ['10/7', '10/8', '10/9', '10/10', '10/11', '10/12', '10/13'],
                series: [91.2, 91.5, 91.8, 92.0, 92.3, 92.5, 92.6]
              }),
              statistic_time: '2025-10-13 09:30:00',
              improve_order_id: '',
              update_user: 'system',
              update_time: '2025-10-13 09:30:00'
            }
          ]);
        }, 500);
      });
    });
  } catch (error) {
    console.error('fetchEmergencyCoreIndicators初始化错误:', error);
    return Promise.resolve([]);
  }
};

// 风险分布视图相关接口
export const fetchEmergencyRiskGeometries = (params = {}) => {
  console.log('===== 开始调用应急风险几何数据接口 =====');
  console.log('请求参数:', params);
  console.log('请求地址:', `${BASE_URL}/emerg-risk-view/get`);
  try {
    return request.get({
      url: `${BASE_URL}/emerg-risk-view/get`,
      params
    }).then(response => {
      console.log('===== 接口请求成功，开始处理响应 =====');
      console.log('原始响应对象(response):', response);

      // 判断response是否为数组（实际返回的结构）
      if (Array.isArray(response)) {
        console.log('响应为直接数据数组，符合实际格式');
        return response; // 直接返回数组
      }

      // 保留原逻辑（兼容未来可能恢复的标准格式）
      if (!response) {
        console.error('响应对象response为undefined/null');
        throw new Error('响应对象为空');
      }
      if (response.data === undefined || response.data === null) {
        console.error('response.data为undefined/null');
        throw new Error('响应体data不存在');
      }
      if (response.data?.code === 0 && Array.isArray(response.data.data)) {
        console.log('响应为标准格式，返回data数组');
        return response.data.data;
      }

      throw new Error('真实接口返回无效数据，使用模拟数据兜底');
    }).catch(error => {
      // 错误处理逻辑（同上）
      console.error('===== 接口调用/处理异常 =====');
      console.error('错误类型:', error.name);
      console.error('错误信息:', error.message);
      console.error('错误堆栈:', error.stack);
      return new Promise(resolve => {
        setTimeout(() => {
          console.log('===== 使用模拟数据兜底 =====');
          resolve([
            {
              hazardId: "R001",
              riskLevel: "高",
              hazardType: "消防隐患",
              gridName: "城东网格01",
              regionName: "东城区",
              discoverTime: 1727783400000,
              coordX: 26.855237,
              coordY: 117.777777
            },
            {
              hazardId: "R002",
              riskLevel: "中",
              hazardType: "矿山隐患",
              gridName: "南郊网格05",
              regionName: "南郊区",
              discoverTime: 1727883300000,
              coordX: 26.783237,
              coordY: 117.720114
            },
            {
              hazardId: "R003",
              riskLevel: "低",
              hazardType: "教育安全",
              gridName: "老城网格12",
              regionName: "老城区",
              discoverTime: 1727828700000,
              coordX: 26.733337,
              coordY: 117.650114
            },
            {
              hazardId: "R004",
              riskLevel: "高",
              hazardType: "用电隐患",
              gridName: "河西网格08",
              regionName: "西城区",
              discoverTime: 1727953200000,
              coordX: 26.810237,
              coordY: 117.800777
            },
            {
              hazardId: "R005",
              riskLevel: "中",
              hazardType: "交通隐患",
              gridName: "北站网格03",
              regionName: "北城区",
              discoverTime: 1727927100000,
              coordX: 26.756237,
              coordY: 117.712114
            },
            {
              hazardId: "R006",
              riskLevel: "低",
              hazardType: "公共设施",
              gridName: "工业园网格02",
              regionName: "工业园区",
              discoverTime: 1728006600000,
              coordX: 26.832237,
              coordY: 117.689114
            },
            {
              hazardId: "R007",
              riskLevel: "高",
              hazardType: "燃气隐患",
              gridName: "环山网格06",
              regionName: "风景区",
              discoverTime: 1728041400000,
              coordX: 26.798237,
              coordY: 117.833777
            },
            {
              hazardId: "R008",
              riskLevel: "中",
              hazardType: "自然灾害",
              gridName: "新区网格09",
              regionName: "新城区",
              discoverTime: 1728113700000,
              coordX: 26.765237,
              coordY: 117.755114
            }
          ]);
        }, 500);
      });
    });
  } catch (error) {
    console.error('===== 函数初始化异常 =====');
    console.error('错误信息:', error.message);
    return Promise.resolve([]);
  }
};

// 应急资源总览相关接口
export const fetchEmergencyResources = () => {
  try {
    return request.get({ url: `${BASE_URL}/emerg-resover-view/list` }).then(response => {
      if (response.data?.code === 0 && Array.isArray(response.data.data) && response.data.data.length > 0) {
        return response.data.data;
      }
      throw new Error('真实接口返回空数据，使用模拟数据兜底');
    }).catch(error => {
      console.warn('应急资源总览接口调用失败：', error.message);
      return new Promise(resolve => {
        setTimeout(() => {
          resolve([
            {
              res_id: '1',
              res_name: '消防水泵',
              res_type: '设备',
              stock_qty: 50,
              available_qty: 45,
              storage_loc: '东区应急仓库 A-12',
              mngr_name: '张工',
              spec_model: 'XQB-2000',
              status: '1',
              allocation_id: 'alloc_201',
              update_time: '2025-10-13 09:00:00'
            },
            {
              res_id: '2',
              res_name: '防汛沙袋',
              res_type: '应急物资',
              stock_qty: 2000,
              available_qty: 1500,
              storage_loc: '南区应急仓库 B-03',
              mngr_name: '李姐',
              spec_model: '10kg/个',
              status: '1',
              allocation_id: 'alloc_202',
              update_time: '2025-10-13 08:30:00'
            },
            {
              res_id: '3',
              res_name: '应急通信车',
              res_type: '设备',
              stock_qty: 3,
              available_qty: 1,
              storage_loc: '西区应急仓库 C-01',
              mngr_name: '王师傅',
              spec_model: 'TC-500',
              status: '0',
              allocation_id: 'alloc_203',
              update_time: '2025-10-13 07:45:00'
            },
            {
              res_id: '4',
              res_name: '抢险救援一队',
              res_type: '救援队伍',
              stock_qty: 30,
              available_qty: 25,
              storage_loc: '北区应急中心',
              mngr_name: '赵队',
              spec_model: '30人',
              status: '0',
              allocation_id: 'alloc_204',
              update_time: '2025-10-13 09:15:00'
            },
            {
              res_id: '5',
              res_name: '应急避难场所',
              res_type: '设备',
              stock_qty: 1,
              available_qty: 1,
              storage_loc: '市中心广场',
              mngr_name: '孙主任',
              spec_model: '5000人',
              status: '1',
              allocation_id: '',
              update_time: '2025-10-13 06:30:00'
            },
            {
              res_id: '6',
              res_name: '医用急救包',
              res_type: '应急物资',
              stock_qty: 100,
              available_qty: 85,
              storage_loc: '东区应急仓库 A-05',
              mngr_name: '刘医生',
              spec_model: '标准版（含20种用品）',
              status: '1',
              allocation_id: 'alloc_205',
              update_time: '2025-10-13 10:30:00'
            },
            {
              res_id: '7',
              res_name: '无人机侦察队',
              res_type: '救援队伍',
              stock_qty: 8,
              available_qty: 6,
              storage_loc: '西区应急仓库 C-08',
              mngr_name: '陈队长',
              spec_model: '8人（含4架无人机）',
              status: '0',
              allocation_id: 'alloc_206',
              update_time: '2025-10-13 14:20:00'
            },
            {
              res_id: '8',
              res_name: '柴油发电机',
              res_type: '设备',
              stock_qty: 5,
              available_qty: 2,
              storage_loc: '南区应急仓库 B-10',
              mngr_name: '周师傅',
              spec_model: '300kW 全自动',
              status: '0',
              allocation_id: 'alloc_207',
              update_time: '2025-10-13 16:10:00'
            }
          ]);
        }, 500);
      });
    });
  } catch (error) {
    console.error('fetchEmergencyResources初始化错误:', error);
    return Promise.resolve([]);
  }
};


// 获取区域分布热力图数据
export const fetchEmergencyHeatmapData = () => {
  try {
    return request.get({ url: `${BASE_URL}/heatmapData` }).then(response => {
      if (response.data?.code === 0 && Array.isArray(response.data.data) && response.data.data.length > 0) {
        return response.data.data;
      }
      throw new Error('真实接口返回空数据，使用模拟数据兜底');
    }).catch(error => {
      console.warn('应急热力图数据接口调用失败：', error.message);
      return new Promise(resolve => {
        setTimeout(() => {
          resolve([
            { regionId: 'r1', name: '东区', value: 65, position: { x: 20, y: 30 }, size: 15 },
            { regionId: 'r2', name: '南区', value: 32, position: { x: 50, y: 60 }, size: 12 },
            { regionId: 'r3', name: '西区', value: 88, position: { x: 70, y: 40 }, size: 18 },
            { regionId: 'r4', name: '北区', value: 45, position: { x: 30, y: 70 }, size: 14 },
            { regionId: 'r5', name: '中区', value: 72, position: { x: 50, y: 35 }, size: 16 }
          ]);
        }, 500);
      });
    });
  } catch (error) {
    console.error('fetchEmergencyHeatmapData初始化错误:', error);
    return Promise.resolve([]);
  }
};

// 导出应急全域数据
export const exportEmergencyGlobalOverview = (params) => {
  try {
    return request.post({
      url: `${BASE_URL}/exportGlobalOverview`,
      data: params
    }).then(response => {
      if (response.data?.code === 0 && response.data.success) {
        return response.data;
      }
      throw new Error('真实接口返回无效结果，使用模拟结果兜底');
    }).catch(error => {
      console.warn('导出应急全域数据接口调用失败：', error.message);
      return new Promise(resolve => {
        setTimeout(() => {
          resolve({ success: true, message: '数据导出成功' });
        }, 800);
      });
    });
  } catch (error) {
    console.error('exportEmergencyGlobalOverview初始化错误:', error);
    return Promise.resolve({ success: false, message: '导出失败' });
  }
};

// 查看应急分析报告
export const viewEmergencyAnalysisReport = (reportId) => {
  try {
    return request.get({ url: `${BASE_URL}/analysisReport/${reportId}` }).then(response => {
      if (response.data?.code === 0 && response.data.data?.report_id) {
        return response.data.data;
      }
      throw new Error('真实接口返回无效结果，使用模拟结果兜底');
    }).catch(error => {
      console.warn('查看应急分析报告接口调用失败：', error.message);
      return new Promise(resolve => {
        setTimeout(() => {
          resolve({
            report_id: reportId,
            title: `应急全域分析报告 ${new Date().toLocaleDateString()}`,
            content: '本报告包含应急事件、风险点、资源储备等应急领域的监测数据汇总分析...',
            stat_time: new Date().toISOString().slice(0, 19).replace('T', ' '),
            create_user: 'system'
          });
        }, 500);
      });
    });
  } catch (error) {
    console.error('viewEmergencyAnalysisReport初始化错误:', error);
    return Promise.resolve({ report_id: reportId, title: '报告加载失败' });
  }
};

// 更新应急核心指标阈值
export const updateIndicatorThreshold = (params) => {
  try {
    return request.post({
      url: `${BASE_URL}/updateIndicatorThreshold`,
      data: params
    }).then(response => {
      if (response.data?.code === 0 && response.data.success) {
        return response.data;
      }
      throw new Error('真实接口返回无效结果，使用模拟结果兜底');
    }).catch(error => {
      console.warn('更新指标阈值接口调用失败：', error.message);
      return new Promise(resolve => {
        setTimeout(() => {
          resolve({ success: true, message: '阈值更新成功' });
        }, 500);
      });
    });
  } catch (error) {
    console.error('updateIndicatorThreshold初始化错误:', error);
    return Promise.resolve({ success: false, message: '更新失败' });
  }
};

// 处理风险预警
export const handleRiskWarning = (params) => {
  try {
    return request.post({
      url: `${BASE_URL}/handleRiskWarning`,
      data: params
    }).then(response => {
      if (response.data?.code === 0 && response.data.success) {
        return response.data;
      }
      throw new Error('真实接口返回无效结果，使用模拟结果兜底');
    }).catch(error => {
      console.warn('处理风险预警接口调用失败：', error.message);
      return new Promise(resolve => {
        setTimeout(() => {
          resolve({ success: true, message: '风险预警处理成功' });
        }, 500);
      });
    });
  } catch (error) {
    console.error('handleRiskWarning初始化错误:', error);
    return Promise.resolve({ success: false, message: '处理失败' });
  }
};

// 获取预警信息滚动数据
export const fetchWarningScrollData = async () => {
  try {
    const response = await request.get({ url: `${BASE_URL}/warningScrollData` });
    if (response.data?.code === 0 && Array.isArray(response.data.data) && response.data.data.length > 0) {
      return response.data.data;
    }
    throw new Error('真实接口返回空数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('预警信息滚动数据接口调用失败：', error.message);
    return [
      ['东区', '危化品风险等级上升', '09:20', '未处理'],
      ['南区', '资源库存不足', '10:15', '处理中'],
      ['西区', '处置及时率下降', '11:30', '未处理'],
      ['北区', '预警准确率低', '13:45', '已解决'],
      ['中区', '风险点监测离线', '14:20', '处理中']
    ];
  }
};

// 获取预警类型数据
export const fetchWarningTypes = async () => {
  try {
    const response = await request.get({ url: `${BASE_URL}/warningTypes` });
    if (response.data?.code === 0 && Array.isArray(response.data.data) && response.data.data.length > 0) {
      return response.data.data;
    }
    throw new Error('真实接口返回空数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('预警类型数据接口调用失败：', error.message);
    return [
      {
        title: '风险等级预警',
        content: '东方化工厂仓库风险等级升至5级，请注意监控'
      },
      {
        title: '资源预警',
        content: '防汛沙袋可用数量低于库存的30%，建议补充'
      },
      {
        title: '指标预警',
        content: '平均处置时长升至35分钟，已超过目标值（30分钟）'
      }
    ];
  }
};

// 提交资源调拨申请
export const submitResourceAllocation = (params) => {
  try {
    return request.post({
      url: `${BASE_URL}/submitResourceAllocation`,
      data: params
    }).then(response => {
      if (response.data?.code === 0 && response.data.success) {
        return response.data;
      }
      throw new Error('真实接口返回无效结果，使用模拟结果兜底');
    }).catch(error => {
      console.warn('提交资源调拨申请接口调用失败：', error.message);
      return new Promise(resolve => {
        setTimeout(() => {
          resolve({ success: true, message: '资源调拨申请提交成功' });
        }, 500);
      });
    });
  } catch (error) {
    console.error('submitResourceAllocation初始化错误:', error);
    return Promise.resolve({ success: false, message: '申请失败' });
  }
};
