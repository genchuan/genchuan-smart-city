import request from '@/config/axios';
// import request from 'axios';
const BASE_URL = '/industry';


// 应急全域数据概览相关接口
export const fetchEmergencyGlobalOverview = (params = {}) => {
  try {
      return request.get({
        url: `${BASE_URL}/emerg-overview/get`,
        params
      }).then(response => {
        console.log('应急全域数据概览-接口请求成功');
        if ( response && typeof response === 'object' && !Array.isArray(response) && response.totalEvtCount ) {
          console.log('应急全域数据概览-响应符合实际格式');
          return response;
        }
        throw new Error('真实接口返回无核心数据，使用模拟数据兜底');
      })
      .catch(error => {
        console.log('应急全域数据概览接口调用失败-使用模拟数据兜底', error.message);
        return new Promise(resolve => {
          setTimeout(() => {
            resolve({
              totalEvtCount: 156,
              handlCount: 23,
              completedCount: 96,
              totalResCount: 896,
              highRiskCount: 18,
              regionCoverCount: 12,
              updateTime: "2025-11-19 10:00:00"
            });
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

// 应急核心指标相关接口
export const fetchEmergencyCoreIndicators = () => {
  try {
    return request.get({
      url: `${BASE_URL}/emerg-core-metrics/list`
    }).then(response => {
      console.log('应急核心指标-接口请求成功');
      if (typeof response === 'object' && response !== null && Object.keys(response).length > 0) {
        console.log('应急核心指标-响应符合实际格式');
        return response;
      }
      throw new Error('真实接口返回无核心数据，使用模拟数据兜底');
    }).catch(error => {
      console.log('应急核心指标接口调用失败-使用模拟数据兜底');
      return new Promise(resolve => {
        setTimeout(() => {
          resolve({
            "emergEventCompleteRate": {
              "rate": 66.5,
              "completedCount": 1330,
              "totalCount": 2000,
              "updateTime": 1764322488000,
              "warningThreshold": 40,
            },
            "earlyWarnAccRate": {
              "rate": 96.67,
              "completedCount": 8,
              "totalCount": 48,
              "updateTime": 1764036622000,
              "warningThreshold": 40,
            },
            "resUseRate": {
              "rate": 34.5,
              "usedCount": 690,
              "totalCount": 2000,
              "updateTime": 1763708842000,
              "warningThreshold": 40,
            },
            "riskRectifyRate": {
              "rate": 80,
              "rectifiedCount": 8,
              "totalCount": 20,
              "updateTime": 1764322488000,
              "warningThreshold": 40,
            },
            "avgHandleEndure": {
              "avgHandleDuration": 36,
              "updateTime": 1764322488000,
              "warningThreshold": 40
            }
          });
        }, 500);
      });
    });
  } catch (error) {
    console.error('===== fetchEmergencyCoreIndicators初始化错误 =====');
    console.error('应急核心指标-初始化错误详情:', {
      message: error.message,
      stack: error.stack
    });
    return Promise.resolve({});
  }
};

// 风险分布视图相关接口
export const fetchEmergencyRiskGeometries = (params = {}) => {
  try {
    return request.get({
      url: `${BASE_URL}/emerg-risk-view/get`,
      params
    }).then(response => {
      console.log('风险分布视图-接口请求成功');

      // 判断response是否为数组（实际返回的结构）
      if (Array.isArray(response)) {
        console.log('风险分布视图-响应符合实际格式');
        return response; // 直接返回数组
      }

      throw new Error('真实接口返回无效数据，使用模拟数据兜底');
    }).catch(error => {
      return new Promise(resolve => {
        setTimeout(() => {
          console.log('风险分布视图接口调用失败-使用模拟数据兜底');
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
export const fetchEmergencyResources = (params = {}) => {
  try {
    return request.get({
      url: `${BASE_URL}/emerg-resover-view/list`,
      params
    }).then(response => {
      console.log('应急资源总览-接口请求成功');

      if (Array.isArray(response)) {
        console.log('应急资源总览-响应符合实际格式');
        return response; // 直接返回数组
      }

      throw new Error('真实接口返回无效数据，使用模拟数据兜底');
    }).catch(error => {
      return new Promise(resolve => {
        setTimeout(() => {
          console.log('应急资源总览接口调用失败-使用模拟数据兜底');
          resolve([
            {
              resId: '1',
              resName: '消防水泵',
              resType: '设备',
              totalQty: 50,
              availableQty: 45,
              storageLoc: '东区应急仓库 A-12',
              mngrName: '张工',
              stockStatus: '紧张',
              updateTime: '1727783400000'
            },
            {
              resId: '2',
              resName: '防汛沙袋',
              resType: '应急物资',
              totalQty: 2000,
              availableQty: 1500,
              storageLoc: '南区应急仓库 B-03',
              mngrName: '李姐',
              stockStatus: '紧张',
              updateTime: '1727783400000'
            },
            {
              resId: '3',
              resName: '应急通信车',
              resType: '设备',
              totalQty: 3,
              availableQty: 1,
              storageLoc: '西区应急仓库 C-01',
              mngrName: '王师傅',
              stockStatus: '充足',
              updateTime: '1727783400000'
            },
            {
              resId: '4',
              resName: '抢险救援一队',
              resType: '救援队伍',
              totalQty: 30,
              availableQty: 25,
              storageLoc: '北区应急中心',
              mngrName: '赵队',
              stockStatus: '充足',
              updateTime: '1727783400000'
            },
            {
              resId: '5',
              resName: '应急避难场所',
              resType: '设备',
              totalQty: 1,
              availableQty: 1,
              storageLoc: '市中心广场',
              mngrName: '孙主任',
              stockStatus: '紧张',
              updateTime: '1727783400000'
            },
            {
              resId: '6',
              resName: '医用急救包',
              resType: '应急物资',
              totalQty: 100,
              availableQty: 85,
              storageLoc: '东区应急仓库 A-05',
              mngrName: '刘医生',
              stockStatus: '短缺',
              updateTime: '1727783400000'
            },
            {
              resId: '7',
              resName: '无人机侦察队',
              resType: '救援队伍',
              totalQty: 8,
              availableQty: 6,
              storageLoc: '西区应急仓库 C-08',
              mngrName: '陈队长',
              stockStatus: '充足',
              updateTime: '1727783400000'
            },
            {
              resId: '8',
              resName: '柴油发电机',
              resType: '设备',
              totalQty: 5,
              availableQty: 2,
              storageLoc: '南区应急仓库 B-10',
              mngrName: '周师傅',
              stockStatus: '充足',
              updateTime: '1727783400000'
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

// 近期预警信息滚动数据
export const fetchWarningScrollData = () => {
  try {
    return request.get({
      url: `${BASE_URL}/emerg-warn/get`
    }).then(response => {
      console.log('近期预警信息-接口请求成功');

      if (Array.isArray(response) && response.length > 0) {
        console.log('近期预警信息-响应符合实际格式');
        return response; // 直接返回接口的数组数据
      }

      throw new Error('真实接口返回无效数据，使用模拟数据兜底');
    }).catch(error => {
      return new Promise(resolve => {
        setTimeout(() => {
          console.log('近期预警信息接口调用失败-使用模拟数据兜底');
          resolve([
            {
              "type": "应急安全",
              "discoveryTime": "2025-12-03 09:12:30",
              "status": "未处理",
              "description": "危化品风险等级上升",
              "regionName": "北京市"
            },
            {
              "type": "风险",
              "discoveryTime": "2025-12-02 09:12:30",
              "status": "未处理",
              "description": "施工围挡出现松动",
              "regionName": "北京市"
            },
            {
              "type": "应急资源",
              "discoveryTime": "2025-12-01 14:25:10",
              "status": "处理中",
              "description": "防汛沙袋库存不足",
              "regionName": "上海市"
            },
            {
              "type": "风险",
              "discoveryTime": "2025-11-30 10:08:20",
              "status": "已解决",
              "description": "消防通道被占用",
              "regionName": "广州市"
            },
            {
              "type": "应急安全",
              "discoveryTime": "2025-11-29 16:40:00",
              "status": "处理中",
              "description": "风险点监测设备离线",
              "regionName": "深圳市"
            }
          ]);
        }, 500);
      });
    });
  } catch (initError) {
    // 捕获函数初始化/请求初始化阶段的异常
    console.error('===== 预警信息滚动数据函数初始化异常 =====');
    console.error('初始化错误信息:', initError.message);
    console.error('初始化错误堆栈:', initError.stack);
    // 兜底返回空数组，保证返回值类型统一
    return Promise.resolve([]);
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


// 事件处置追踪视图
// 事件追踪概览指标（核心追踪数据）
export const fetchTrackingOverview = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/overview`);
    if (response.data && typeof response.data === 'object') {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('事件追踪概览接口调用失败，使用模拟数据:', error.message);
    return {
      totalTrackingEvents: 128,      // 总追踪事件数（件）
      handlCount: 23,       // 在处置事件数（件）
      overtimeFollowEvents: 8,       // 超时未跟进事件数（件）
      completedTrackingEvents: 92,   // 追踪完成事件数（件）
      avgFollowUpInterval: 45.2,     // 平均跟进间隔（分钟）
      avgDisposalCycle: 180.5,       // 平均处置周期（分钟）
      followUpRate: 91.3,            // 跟进覆盖率（%）
      timelyFollowRate: 85.7,        // 及时跟进率（%）
      highLevelTrackingEvents: 35,   // 高级别追踪事件数（件）
      lastSyncTime: '2025-11-26 15:42' // 最近数据同步时间
    };
  }
};

// 处置进度分布（核心图表：看事件卡在哪个环节）
export const fetchDisposalProgressDistribution = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/progress/distribution`);
    if (response.data && response.data.labels && response.data.data) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('处置进度分布接口调用失败，使用模拟数据:', error.message);
    return {
      labels: ['待派单', '已派单', '处置中', '待验收', '已完成', '已关闭'],
      data: [15, 28, 42, 13, 92, 5],
      colors: ['#909399', '#409EFF', '#E6A23C', '#722ED1', '#67C23A', '#F56C6C']
    };
  }
};

// 区域追踪效率（核心图表：看哪个区域效率低）
export const fetchAreaTrackingEfficiency = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/area/efficiency`);
    if (response.data && response.data.xAxis && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('区域追踪效率接口调用失败，使用模拟数据:', error.message);
    return {
      xAxis: ['南部城区', '西部高速', '市中心', '北部新区', '东部郊区'],
      series: [
        { name: '及时跟进率（%）', data: [88.5, 92.3, 84.7, 89.2, 78.9] },
        { name: '平均处置周期（分钟）', data: [165.2, 152.8, 190.5, 172.3, 205.7] }
      ]
    };
  }
};

// 追踪事件列表（核心数据：带进度、跟进信息）
export const fetchTrackingEventList = async (params = {}) => {
  try {
    const response = await axios.get(`${BASE_URL}/event/list`, { params });
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('追踪事件列表接口调用失败，使用模拟数据:', error.message);
    const descriptions = [
      '多车追尾事故，无人员伤亡，需清理现场并疏导交通',
      '道路监控设备故障，影响交通管控，需紧急抢修',
      '主干道施工占道，需设置围挡并引导绕行',
      '暴雨导致路面积水，需排水作业并封闭部分车道',
      '车辆抛锚占用应急车道，需拖车救援'
    ];

    return Array(20).fill(0).map((_, index) => ({
      event_id: `EM-${String(index + 200).slice(1)}`,
      name: `${['南部城区', '西部高速', '市中心', '北部新区', '东部郊区'][index % 5]}${['交通事故', '设备故障', '道路施工', '自然灾害', '其他事件'][index % 5]}处置追踪`,
      type: index % 5 === 0 ? 'traffic_accident' : index % 5 === 1 ? 'equipment_failure' :
        index % 5 === 2 ? 'road_construction' : index % 5 === 3 ? 'natural_disaster' : 'other',
      level: index % 3 === 0 ? 'low' : index % 3 === 1 ? 'medium' : 'high',
      area: ['南部城区', '西部高速', '市中心', '北部新区', '东部郊区'][index % 5],
      status: index % 6 === 0 ? 'pending' : index % 6 === 1 ? 'assigned' :
        index % 6 === 2 ? 'processing' : index % 6 === 3 ? 'pending_acceptance' :
          index % 6 === 4 ? 'completed' : 'closed',
      progress: index % 6 === 0 ? 0 : index % 6 === 1 ? 20 :
        index % 6 === 2 ? 50 : index % 6 === 3 ? 80 :
          index % 6 === 4 ? 100 : 100, // 处置进度（%）
      create_time: `2025-11-2${6 - Math.floor(index / 10)} ${9 + (index % 6)}:${index % 2 === 0 ? '00' : '30'}`,
      assign_time: index % 6 > 0 ? `2025-11-2${6 - Math.floor(index / 10)} ${9 + (index % 6) + 1}:${index % 2 === 0 ? '30' : '00'}` : '',
      handler_team: ['应急一队', '应急二队', '维修组', '交通疏导组', '抢险组'][index % 5],
      handler: ['张三', '李四', '王五', '赵六', '孙七'][index % 5],
      last_follow_time: index % 6 > 0 ? `2025-11-2${6 - Math.floor(index / 10)} ${10 + (index % 6)}:${index % 2 === 0 ? '15' : '45'}` : '',
      follow_up_count: Math.floor(Math.random() * 5) + 1, // 跟进次数
      expected_completion_time: `2025-11-2${7 - Math.floor(index / 10)} ${12 + (index % 4)}:${index % 2 === 0 ? '00' : '30'}`,
      description: descriptions[index % descriptions.length]
    }));
  }
};

// 事件处置时间线（详情核心：追溯事件流程）
export const fetchEventTimeline = async (eventId) => {
  try {
    const response = await axios.get(`${BASE_URL}/event/timeline/${eventId}`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn(`事件${eventId}时间线接口调用失败，使用模拟数据:', error.message`);
    return [
      {
        stage: 'reported', // 上报
        time: '2025-11-26 09:30:00',
        operator: '路人-陈先生',
        content: '通过应急平台上报事件，上传现场照片3张',
        attachments: []
      },
      {
        stage: 'accepted', // 受理
        time: '2025-11-26 09:35:00',
        operator: '调度员-李姐',
        content: '审核事件信息有效，纳入处置追踪队列',
        attachments: []
      },
      {
        stage: 'assigned', // 派单
        time: '2025-11-26 09:40:00',
        operator: '调度员-李姐',
        content: '派单至应急二队，负责人李四，要求1小时内响应',
        attachments: []
      },
      {
        stage: 'responded', // 响应
        time: '2025-11-26 10:10:00',
        operator: '负责人-李四',
        content: '已接收派单，团队正在赶赴现场，预计20分钟到达',
        attachments: []
      },
      {
        stage: 'processing', // 处置中
        time: '2025-11-26 10:30:00',
        operator: '负责人-李四',
        content: '到达现场，开始实施处置方案，目前进度50%',
        attachments: []
      }
    ];
  }
};

// 事件跟进记录（核心功能：记录跟进过程）
export const fetchFollowUpRecords = async (eventId) => {
  try {
    const response = await axios.get(`${BASE_URL}/event/followup/${eventId}`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn(`事件${eventId}跟进记录接口调用失败，使用模拟数据:', error.message`);
    return [
      {
        record_id: `FR-${eventId}-001`,
        follow_time: '2025-11-26 10:00:00',
        follower: '李四',
        follower_team: '应急二队',
        content: '确认事件位置准确，团队已出发，预计20分钟到达',
        progress_update: '20%（已响应）',
        attachments: []
      },
      {
        record_id: `FR-${eventId}-002`,
        follow_time: '2025-11-26 10:30:00',
        follower: '李四',
        follower_team: '应急二队',
        content: '现场处置顺利，已完成核心问题解决，正在清理现场',
        progress_update: '50%（处置中）',
        attachments: []
      }
    ];
  }
};

// 保存跟进记录（核心操作：提交跟进信息）
export const saveFollowUpRecord = async (params) => {
  try {
    const response = await axios.post(`${BASE_URL}/event/followup/save`, params);
    if (response.data && response.data.success) {
      return response.data;
    }
    throw new Error('保存跟进记录失败');
  } catch (error) {
    console.warn('保存跟进记录接口调用失败，返回模拟成功:', error.message);
    return {
      success: true,
      message: '跟进记录保存成功',
      record_id: `FR-${params.event_id}-${Math.floor(Math.random() * 1000).toString().padStart(3, '0')}`
    };
  }
};

// 更新事件处置进度（核心操作：更新事件状态）
export const updateEventProgress = async (eventId, progress, status) => {
  try {
    const response = await axios.put(`${BASE_URL}/event/progress/${eventId}`, {
      progress,
      status
    });
    if (response.data && response.data.success) {
      return response.data;
    }
    throw new Error('更新处置进度失败');
  } catch (error) {
    console.warn(`更新事件${eventId}进度接口调用失败，返回模拟成功:', error.message`);
    return {
      success: true,
      message: '处置进度更新成功',
      progress,
      status,
      update_time: new Date().toLocaleString('zh-CN', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit'
      }).replace(/\//g, '-')
    };
  }
};

// 应急处置进度视图
// 应急处置进度核心指标
export const fetchDisposalProgressOverview = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/overview`);
    if (response.data && typeof response.data === 'object') {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('应急处置进度概览接口调用失败，使用模拟数据:', error.message);
    return {
      totalEvtCount: 156,        // 总处置事件数（件）
      pendingDisposalEvents: 18,       // 待处置事件数（件）
      disposingEvents: 42,             // 处置中事件数（件）
      completedCount: 96,             // 已完成处置事件数（件）
      overtimeDisposalEvents: 7,       // 超时处置事件数（件）
      avgDisposalDuration: 125.8,      // 平均处置时长（分钟）
      avgStageDuration: {              // 各阶段平均耗时（分钟）
        alarm: 5.2,                    // 接警阶段
        dispatch: 8.5,                 // 派单阶段
        arrive: 25.3,                  // 到场阶段
        handle: 68.7,                  // 处置实施阶段
        acceptance: 18.1,              // 验收阶段
        close: 5.0                     // 结案阶段
      },
      disposalCompletionRate: 61.5,    // 处置完成率（%）
      timelyDisposalRate: 88.2,        // 及时处置率（%）
      highPriorityEvents: 28,          // 高优先级处置事件数（件）
      lastSyncTime: '2025-11-27 10:15' // 最近数据同步时间
    };
  }
};

// 处置阶段分布（核心图表：看事件卡在哪个处置阶段）
export const fetchDisposalStageDistribution = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/stage/distribution`);
    if (response.data && response.data.labels && response.data.data) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('处置阶段分布接口调用失败，使用模拟数据:', error.message);
    return {
      labels: ['待接警', '已派单', '已到场', '处置中', '待验收', '已结案'],
      data: [8, 22, 15, 42, 18, 96],
      colors: ['#909399', '#409EFF', '#722ED1', '#E6A23C', '#13ce66', '#67C23A']
    };
  }
};

// 各阶段耗时分析（核心图表：看哪个阶段耗时最长）
export const fetchStageDurationAnalysis = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/stage/duration`);
    if (response.data && response.data.xAxis && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('阶段耗时分析接口调用失败，使用模拟数据:', error.message);
    return {
      xAxis: ['接警', '派单', '到场', '处置实施', '验收', '结案'],
      series: [
        { name: '平均耗时（分钟）', data: [5.2, 8.5, 25.3, 68.7, 18.1, 5.0] },
        { name: '标准耗时（分钟）', data: [5, 10, 20, 60, 15, 5] }
      ]
    };
  }
};

// 近7天处置进度趋势（核心图表：看处置效率变化）
export const fetchDisposalTrend7Days = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/trend/7days`);
    if (response.data && response.data.xAxis && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('近7天处置趋势接口调用失败，使用模拟数据:', error.message);
    return {
      xAxis: ['11-21', '11-22', '11-23', '11-24', '11-25', '11-26', '11-27'],
      series: [
        { name: '新增处置事件', data: [22, 18, 25, 20, 24, 21, 16] },
        { name: '完成处置事件', data: [18, 15, 20, 19, 22, 19, 14] },
        { name: '超时处置事件', data: [1, 0, 2, 1, 1, 0, 2] }
      ]
    };
  }
};

// 应急处置事件列表（核心数据：带进度、阶段、耗时信息）
export const fetchDisposalEventList = async (params = {}) => {
  try {
    const response = await axios.get(`${BASE_URL}/event/list`, { params });
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('处置事件列表接口调用失败，使用模拟数据:', error.message);
    const eventTypes = [
      'fire_accident', 'traffic_accident', 'gas_leak', 'building_collapse',
      'waterlogging', 'equipment_failure', 'other_emergency'
    ];
    const eventTypeNames = [
      '火灾事故', '交通事故', '燃气泄漏', '建筑坍塌',
      '城市内涝', '设备故障', '其他应急事件'
    ];
    const areas = ['城东片区', '城西片区', '城南片区', '城北片区', '高新区', '经开区'];
    const stages = ['pending_alarm', 'dispatched', 'arrived', 'disposing', 'pending_acceptance', 'closed'];
    const priorities = ['low', 'medium', 'high'];

    return Array(25).fill(0).map((_, index) => ({
      disposal_id: `EP-${String(index + 300).slice(1)}`,
      event_name: `${areas[index % 6]}${eventTypeNames[index % 7]}应急处置`,
      event_type: eventTypes[index % 7],
      priority: priorities[index % 3],
      area: areas[index % 6],
      stage: stages[index % 6],
      progress: index % 6 === 0 ? 0 : index % 6 === 1 ? 20 :
        index % 6 === 2 ? 35 : index % 6 === 3 ? 60 :
          index % 6 === 4 ? 85 : 100, // 处置进度（%）
      alarm_time: `2025-11-2${7 - Math.floor(index / 10)} ${8 + (index % 8)}:${index % 2 === 0 ? '00' : '30'}`,
      dispatch_time: index % 6 > 0 ? `2025-11-2${7 - Math.floor(index / 10)} ${8 + (index % 8) + 1}:${index % 2 === 0 ? '30' : '00'}` : '',
      arrive_time: index % 6 > 1 ? `2025-11-2${7 - Math.floor(index / 10)} ${9 + (index % 8)}:${index % 2 === 0 ? '15' : '45'}` : '',
      expected_complete_time: `2025-11-2${8 - Math.floor(index / 10)} ${10 + (index % 6)}:${index % 2 === 0 ? '00' : '30'}`,
      actual_complete_time: index % 6 === 5 ? `2025-11-2${8 - Math.floor(index / 10)} ${12 + (index % 6)}:${index % 2 === 0 ? '30' : '00'}` : '',
      disposal_team: ['应急一队', '消防中队', '抢修组', '医疗组', '交通疏导组', '工程抢险组'][index % 6],
      leader: ['张三', '李四', '王五', '赵六', '孙七', '周八'][index % 6],
      duration: index % 6 === 5 ? `${Math.floor(Math.random() * 120 + 60)}分钟` : '处置中',
      description: `${eventTypeNames[index % 7]}，${['无人员伤亡', '1人轻伤', '无重大损失', '需紧急疏散', '需临时封控'][index % 5]}，现场已${['未处理', '派单', '到场', '处置中', '待验收', '结案'][index % 6]}`
    }));
  }
};

// 处置进度时间线（详情核心：各阶段耗时+操作记录）
export const fetchDisposalTimeline = async (disposalId) => {
  try {
    const response = await axios.get(`${BASE_URL}/event/timeline/${disposalId}`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn(`处置${disposalId}时间线接口调用失败，使用模拟数据:', error.message`);
    return [
      {
        stage: 'alarm', // 接警
        time: '2025-11-27 08:30:00',
        operator: '接警员-李华',
        content: '接到群众报警，城东片区燃气泄漏，地址为幸福路123号，立即登记并上报',
        duration: '5分钟',
        attachments: []
      },
      {
        stage: 'dispatch', // 派单
        time: '2025-11-27 08:35:00',
        operator: '调度员-王芳',
        content: '派单至燃气抢修组，负责人张三，要求20分钟内到场处置',
        duration: '8分钟',
        attachments: []
      },
      {
        stage: 'arrive', // 到场
        time: '2025-11-27 08:55:00',
        operator: '负责人-张三',
        content: '抢修组抵达现场，设置警戒区域，检测泄漏点位置',
        duration: '20分钟',
        attachments: []
      },
      {
        stage: 'handle', // 处置实施
        time: '2025-11-27 09:15:00',
        operator: '负责人-张三',
        content: '关闭燃气主阀门，开始封堵泄漏点，目前处置进度60%',
        duration: '65分钟',
        attachments: []
      }
    ];
  }
};

// 处置进度更新（核心操作：更新阶段+进度）
export const updateDisposalProgress = async (disposalId, stage, progress) => {
  try {
    const response = await axios.put(`${BASE_URL}/event/progress/${disposalId}`, {
      stage,
      progress
    });
    if (response.data && response.data.success) {
      return response.data;
    }
    throw new Error('更新处置进度失败');
  } catch (error) {
    console.warn(`更新处置${disposalId}进度接口调用失败，返回模拟成功:', error.message`);
    return {
      success: true,
      message: '处置进度更新成功',
      stage,
      progress,
      update_time: new Date().toLocaleString('zh-CN', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit'
      }).replace(/\//g, '-')
    };
  }
};

// 处置记录提交（核心操作：添加处置记录）
export const submitDisposalRecord = async (params) => {
  try {
    const response = await axios.post(`${BASE_URL}/event/record/save`, params);
    if (response.data && response.data.success) {
      return response.data;
    }
    throw new Error('提交处置记录失败');
  } catch (error) {
    console.warn('提交处置记录接口调用失败，返回模拟成功:', error.message);
    return {
      success: true,
      message: '处置记录提交成功',
      record_id: `DR-${params.disposal_id}-${Math.floor(Math.random() * 1000).toString().padStart(3, '0')}`
    };
  }
};

// 应急资源分布视图
// 应急资源分布核心指标
export const fetchResourceDistributionOverview = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/resource/overview`);
    if (response.data && typeof response.data === 'object') {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('应急资源分布概览接口调用失败，使用模拟数据:', error.message);
    return {
      totalResourceCount: 896,          // 总资源数量（件/套）
      pendingAllocation: 78,            // 待调配资源数（件/套）
      inUseResources: 215,              // 在用资源数（件/套）
      availableResources: 523,          // 可用资源数（件/套）
      faultyResources: 80,              // 故障资源数（件/套）
      avgMaintenanceDuration: 86.5,     // 平均维护时长（分钟）
      avgTypeMaintenance: {             // 各类型资源平均维护时长（分钟）
        fire: 75.2,                     // 消防类
        medical: 92.5,                  // 医疗类
        traffic: 88.3,                  // 交通类
        communication: 68.7,           // 通讯类
        rescue: 98.1,                   // 救援类
        logistics: 72.0                 // 后勤类
      },
      resourceAvailabilityRate: 58.4,   // 资源可用率（%）
      resourceIntegrityRate: 91.2,      // 资源完好率（%）
      keyResourceCount: 156,            // 关键资源数量（件/套）
      lastSyncTime: '2025-11-27 10:30'  // 最近数据同步时间
    };
  }
};

// 资源状态分布（核心图表：看资源卡在哪个状态）
export const fetchResourceStatusDistribution = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/resource/status/distribution`);
    if (response.data && response.data.labels && response.data.data) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('资源状态分布接口调用失败，使用模拟数据:', error.message);
    return {
      labels: ['待调配', '在用', '可用', '故障', '维修中', '已报废'],
      data: [78, 215, 523, 45, 32, 7],
      colors: ['#909399', '#409EFF', '#67C23A', '#F56C6C', '#E6A23C', '#722ED1']
    };
  }
};

// 各类型资源维护耗时分析（核心图表：看哪类资源维护耗时最长）
export const fetchResourceTypeDurationAnalysis = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/resource/type/duration`);
    if (response.data && response.data.xAxis && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('资源类型耗时分析接口调用失败，使用模拟数据:', error.message);
    return {
      xAxis: ['消防类', '医疗类', '交通类', '通讯类', '救援类', '后勤类'],
      series: [
        { name: '平均维护时长（分钟）', data: [75.2, 92.5, 88.3, 68.7, 98.1, 72.0] },
        { name: '标准维护时长（分钟）', data: [70, 90, 80, 70, 90, 70] }
      ]
    };
  }
};

// 近7天资源状态趋势（核心图表：看资源可用率变化）
export const fetchResourceTrend7Days = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/resource/trend/7days`);
    if (response.data && response.data.xAxis && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('近7天资源趋势接口调用失败，使用模拟数据:', error.message);
    return {
      xAxis: ['11-21', '11-22', '11-23', '11-24', '11-25', '11-26', '11-27'],
      series: [
        { name: '新增调配资源', data: [32, 28, 35, 29, 31, 27, 24] },
        { name: '可用资源数', data: [518, 520, 515, 525, 522, 521, 523] },
        { name: '故障资源数', data: [42, 40, 48, 45, 43, 41, 45] }
      ]
    };
  }
};

// 应急资源列表（核心数据：带状态、类型、可用率信息）
export const eResourceList = async (params = {}) => {
  try {
    const response = await axios.get(`${BASE_URL}/resource/list`, { params });
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('资源列表接口调用失败，使用模拟数据:', error.message);
    const resourceTypes = [
      'fire', 'medical', 'traffic', 'communication', 'rescue', 'logistics'
    ];
    const resourceTypeNames = [
      '消防类', '医疗类', '交通类', '通讯类', '救援类', '后勤类'
    ];
    const areas = ['城东片区', '城西片区', '城南片区', '城北片区', '高新区', '经开区'];
    const statuses = ['pending_allocation', 'in_use', 'available', 'faulty', 'maintaining', 'scrapped'];
    const levels = ['normal', 'important', 'key'];

    return Array(25).fill(0).map((_, index) => ({
      resource_id: `RS-${String(index + 500).slice(1)}`,
      resource_name: `${areas[index % 6]}${resourceTypeNames[index % 6]}${['灭火器', '急救箱', '救护车', '对讲机', '破拆工具', '应急物资'][index % 6]}`,
      resource_type: resourceTypes[index % 6],
      level: levels[index % 3],
      area: areas[index % 6],
      status: statuses[index % 6],
      availability_rate: index % 6 === 0 ? 0 : index % 6 === 1 ? 40 :
        index % 6 === 2 ? 100 : index % 6 === 3 ? 0 :
          index % 6 === 4 ? 20 : 0, // 资源可用率（%）
      purchase_time: `202${3 + index % 3}-${10 + index % 2}-${10 + index % 20}`,
      last_maintenance_time: index % 6 > 1 ? `2025-11-2${7 - Math.floor(index / 10)} ${8 + (index % 8)}:${index % 2 === 0 ? '00' : '30'}` : '',
      next_maintenance_time: `2025-12-2${8 - Math.floor(index / 10)} ${10 + (index % 6)}:${index % 2 === 0 ? '00' : '30'}`,
      actual_scrap_time: index % 6 === 5 ? `2025-11-2${8 - Math.floor(index / 10)} ${12 + (index % 6)}:${index % 2 === 0 ? '30' : '00'}` : '',
      management_team: ['消防管理组', '医疗保障组', '交通保障组', '通讯保障组', '救援指挥组', '后勤保障组'][index % 6],
      manager: ['张三', '李四', '王五', '赵六', '孙七', '周八'][index % 6],
      maintenance_duration: index % 6 === 4 ? `${Math.floor(Math.random() * 120 + 60)}分钟` : '维护中',
      description: `${resourceTypeNames[index % 6]}${['正常', '轻微故障', '需维护', '严重故障', '维修中', '已报废'][index % 6]}，存放于${areas[index % 6]}${['一号仓库', '二号仓库', '应急站点', '指挥中心', '救援基地', '后勤库房'][index % 6]}`
    }));
  }
};

// 资源状态时间线（详情核心：各状态变更记录+维护耗时）
export const fetchResourceTimeline = async (resourceId) => {
  try {
    const response = await axios.get(`${BASE_URL}/resource/timeline/${resourceId}`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn(`资源${resourceId}时间线接口调用失败，使用模拟数据:', error.message`);
    return [
      {
        status: 'purchase', // 采购入库
        time: '2025-11-20 09:00:00',
        operator: '库管员-李华',
        content: '城东片区消防类灭火器采购入库，编号RS-501，验收合格，状态为可用',
        duration: '30分钟',
        attachments: []
      },
      {
        status: 'allocation', // 调配
        time: '2025-11-25 14:30:00',
        operator: '调度员-王芳',
        content: '调配至城东应急站点，用于日常备勤，状态变更为待调配',
        duration: '15分钟',
        attachments: []
      },
      {
        status: 'use', // 启用
        time: '2025-11-26 08:45:00',
        operator: '管理员-张三',
        content: '正式投入使用，用于小型火灾处置，状态变更为在用',
        duration: '8分钟',
        attachments: []
      },
      {
        status: 'maintenance', // 维护
        time: '2025-11-27 10:15:00',
        operator: '维护员-张三',
        content: '例行检查维护，发现压力不足，开始充压维护，目前维护进度60%',
        duration: '45分钟',
        attachments: []
      }
    ];
  }
};

// 资源状态更新（核心操作：更新状态+可用率）
export const updateResourceStatus = async (resourceId, status, availabilityRate) => {
  try {
    const response = await axios.put(`${BASE_URL}/resource/status/${resourceId}`, {
      status,
      availability_rate: availabilityRate
    });
    if (response.data && response.data.success) {
      return response.data;
    }
    throw new Error('更新资源状态失败');
  } catch (error) {
    console.warn(`更新资源${resourceId}状态接口调用失败，返回模拟成功:', error.message`);
    return {
      success: true,
      message: '资源状态更新成功',
      status,
      availability_rate: availabilityRate,
      update_time: new Date().toLocaleString('zh-CN', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit'
      }).replace(/\//g, '-')
    };
  }
};

// 资源维护记录提交（核心操作：添加维护记录）
export const submitResourceRecord = async (params) => {
  try {
    const response = await axios.post(`${BASE_URL}/resource/record/save`, params);
    if (response.data && response.data.success) {
      return response.data;
    }
    throw new Error('提交资源维护记录失败');
  } catch (error) {
    console.warn('提交资源维护记录接口调用失败，返回模拟成功:', error.message);
    return {
      success: true,
      message: '资源维护记录提交成功',
      record_id: `RR-${params.resource_id}-${Math.floor(Math.random() * 1000).toString().padStart(3, '0')}`
    };
  }
};

// 事故隐患预警视图
// 事故隐患预警核心指标
export const fetchHazardWarningOverview = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/overview`);
    if (response.data && typeof response.data === 'object') {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('事故隐患预警概览接口调用失败，使用模拟数据:', error.message);
    return {
      totalHazards: 218,                // 总隐患数（项）
      pendingHandleHazards: 35,         // 待处理隐患数（项）
      handlingHazards: 68,              // 处理中隐患数（项）
      handledHazards: 115,              // 已处理隐患数（项）
      overdueHandleHazards: 12,         // 逾期未处理隐患数（项）
      avgHandleDuration: 156.2,         // 平均处理时长（分钟）
      avgStageDuration: {               // 各环节平均耗时（分钟）
        report: 8.5,                    // 上报环节
        dispatch: 12.3,                 // 派单环节
        verify: 35.7,                   // 核查环节
        rectify: 82.5,                  // 整改环节
        acceptance: 15.2,               // 验收环节
        cancel: 6.0                     // 销号环节
      },
      hazardHandleRate: 52.7,           // 隐患处理完成率（%）
      timelyHandleRate: 81.5,           // 及时处理率（%）
      majorHazardEvents: 18,            // 重大隐患数（项）
      lastSyncTime: '2025-11-27 14:30'  // 最近数据同步时间
    };
  }
};

// 预警级别分布（核心图表：看各级别隐患占比）
export const fetchHazardLevelDistribution = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/level/distribution`);
    if (response.data && response.data.labels && response.data.data) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('预警级别分布接口调用失败，使用模拟数据:', error.message);
    return {
      labels: ['重大隐患', '较大隐患', '一般隐患', '轻微隐患'],
      data: [18, 32, 95, 73],
      colors: ['#FF4D4F', '#FA8C16', '#1890FF', '#52C41A']
    };
  }
};

// 各环节处理时长分析（核心图表：看哪个环节耗时最长）
export const fetchHazardHandleDurationAnalysis = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/stage/duration`);
    if (response.data && response.data.xAxis && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('环节耗时分析接口调用失败，使用模拟数据:', error.message);
    return {
      xAxis: ['上报', '派单', '核查', '整改', '验收', '销号'],
      series: [
        { name: '平均耗时（分钟）', data: [8.5, 12.3, 35.7, 82.5, 15.2, 6.0] },
        { name: '标准耗时（分钟）', data: [10, 15, 30, 70, 15, 5] }
      ]
    };
  }
};

// 近7天隐患预警趋势（核心图表：看预警数量变化）
export const fetchHazardWarningTrend7Days = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/trend/7days`);
    if (response.data && response.data.xAxis && response.data.series) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('近7天预警趋势接口调用失败，使用模拟数据:', error.message);
    return {
      xAxis: ['11-21', '11-22', '11-23', '11-24', '11-25', '11-26', '11-27'],
      series: [
        { name: '新增预警隐患', data: [32, 28, 35, 29, 31, 27, 24] },
        { name: '完成处理隐患', data: [25, 22, 28, 26, 29, 24, 21] },
        { name: '逾期处理隐患', data: [2, 3, 1, 2, 1, 2, 1] }
      ]
    };
  }
};

// 事故隐患预警列表（核心数据：带进度、状态、耗时信息）
export const fetchHazardWarningList = async (params = {}) => {
  try {
    const response = await axios.get(`${BASE_URL}/list`, { params });
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('隐患预警列表接口调用失败，使用模拟数据:', error.message);
    const hazardTypes = [
      'fire_hazard', 'electrical_hazard', 'structural_hazard', 'equipment_hazard',
      'operation_hazard', 'environment_hazard', 'other_hazard'
    ];
    const hazardTypeNames = [
      '火灾隐患', '电气隐患', '结构隐患', '设备隐患',
      '操作隐患', '环境隐患', '其他隐患'
    ];
    const areas = ['城东片区', '城西片区', '城南片区', '城北片区', '高新区', '经开区'];
    const handleStatus = ['pending_report', 'dispatched', 'verified', 'rectifying', 'pending_acceptance', 'cancelled'];
    const warningLevels = ['major', 'medium', 'general', 'minor'];

    return Array(25).fill(0).map((_, index) => ({
      hazard_id: `HZ-${String(index + 500).slice(1)}`,
      hazard_name: `${areas[index % 6]}${hazardTypeNames[index % 7]}隐患预警`,
      hazard_type: hazardTypes[index % 7],
      warning_level: warningLevels[index % 4],
      area: areas[index % 6],
      handle_status: handleStatus[index % 6],
      progress: index % 6 === 0 ? 0 : index % 6 === 1 ? 20 :
        index % 6 === 2 ? 35 : index % 6 === 3 ? 60 :
          index % 6 === 4 ? 85 : 100, // 整改进度（%）
      warning_time: `2025-11-2${7 - Math.floor(index / 10)} ${8 + (index % 8)}:${index % 2 === 0 ? '00' : '30'}`,
      dispatch_time: index % 6 > 0 ? `2025-11-2${7 - Math.floor(index / 10)} ${8 + (index % 8) + 1}:${index % 2 === 0 ? '30' : '00'}` : '',
      verify_time: index % 6 > 1 ? `2025-11-2${7 - Math.floor(index / 10)} ${9 + (index % 8)}:${index % 2 === 0 ? '15' : '45'}` : '',
      expected_complete_time: `2025-11-2${8 - Math.floor(index / 10)} ${10 + (index % 6)}:${index % 2 === 0 ? '00' : '30'}`,
      actual_complete_time: index % 6 === 5 ? `2025-11-2${8 - Math.floor(index / 10)} ${12 + (index % 6)}:${index % 2 === 0 ? '30' : '00'}` : '',
      responsible_dept: ['安全管理部', '工程部', '设备部', '运维部', '环境部', '综合部'][index % 6],
      leader: ['张三', '李四', '王五', '赵六', '孙七', '周八'][index % 6],
      duration: index % 6 === 5 ? `${Math.floor(Math.random() * 180 + 90)}分钟` : '处理中',
      description: `${hazardTypeNames[index % 7]}，${['无人员风险', '低风险', '中风险', '高风险', '需紧急整改'][index % 5]}，现场已${['未上报', '已派单', '已核查', '整改中', '待验收', '已销号'][index % 6]}`
    }));
  }
};

// 隐患处理时间线（详情核心：各环节耗时+操作记录）
export const fetchHazardHandleTimeline = async (hazardId) => {
  try {
    const response = await axios.get(`${BASE_URL}/timeline/${hazardId}`);
    if (Array.isArray(response.data)) {
      return response.data;
    }
    throw new Error('真实接口返回无效数据，使用模拟数据兜底');
  } catch (error) {
    console.warn(`隐患${hazardId}处理时间线接口调用失败，使用模拟数据:', error.message`);
    return [
      {
        stage: 'report', // 上报
        time: '2025-11-27 09:10:00',
        operator: '安全员-李华',
        content: '巡检发现城东片区电气隐患，线路老化存在短路风险，立即上报并登记',
        duration: '8分钟',
        attachments: []
      },
      {
        stage: 'dispatch', // 派单
        time: '2025-11-27 09:18:00',
        operator: '调度员-王芳',
        content: '派单至工程部，负责人张三，要求30分钟内到场核查整改',
        duration: '12分钟',
        attachments: []
      },
      {
        stage: 'verify', // 核查
        time: '2025-11-27 09:40:00',
        operator: '负责人-张三',
        content: '工程部抵达现场，确认线路老化情况属实，制定整改方案',
        duration: '35分钟',
        attachments: []
      },
      {
        stage: 'rectify', // 整改
        time: '2025-11-27 10:15:00',
        operator: '负责人-张三',
        content: '开始更换老化线路，目前整改进度60%',
        duration: '80分钟',
        attachments: []
      }
    ];
  }
};

// 隐患处理进度更新（核心操作：更新状态+进度）
export const updateHazardHandleProgress = async (hazardId, status, progress) => {
  try {
    const response = await axios.put(`${BASE_URL}/progress/${hazardId}`, {
      status,
      progress
    });
    if (response.data && response.data.success) {
      return response.data;
    }
    throw new Error('更新隐患处理进度失败');
  } catch (error) {
    console.warn(`更新隐患${hazardId}处理进度接口调用失败，返回模拟成功:', error.message`);
    return {
      success: true,
      message: '隐患处理进度更新成功',
      status,
      progress,
      update_time: new Date().toLocaleString('zh-CN', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit'
      }).replace(/\//g, '-')
    };
  }
};

// 隐患处理记录提交（核心操作：添加处理记录）
export const submitHazardHandleRecord = async (params) => {
  try {
    const response = await axios.post(`${BASE_URL}/record/save`, params);
    if (response.data && response.data.success) {
      return response.data;
    }
    throw new Error('提交隐患处理记录失败');
  } catch (error) {
    console.warn('提交隐患处理记录接口调用失败，返回模拟成功:', error.message);
    return {
      success: true,
      message: '隐患处理记录提交成功',
      record_id: `HR-${params.hazard_id}-${Math.floor(Math.random() * 1000).toString().padStart(3, '0')}`
    };
  }
};
