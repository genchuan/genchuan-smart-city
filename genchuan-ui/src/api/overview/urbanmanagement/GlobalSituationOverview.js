// 引入axios（假设项目中已配置axios）
import axios from 'axios';

// 基础URL，可根据实际项目配置
const BASE_URL = '/api/urban';

// 获取城管全域数据地图数据
export const fetchUrbanDataMap = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/urbanDataMap`);
    if (Array.isArray(response.data) && response.data.length > 0) {
      return response.data;
    }
    throw new Error('真实接口返回空数据，使用模拟数据兜底');
  } catch (error) {
    console.warn('城管全域数据地图接口调用失败，使用模拟数据:', error.message);
    // 替换为文件2的geometriesArray模拟数据
    return [
      {
        coord_x: 26.855237, // 纬度
        coord_y: 118.000000, // 经度
        mng_comp_id: "C001", // 部件ID
        comp_name: "温度传感器-广场A", // 部件名称
        comp_status: "破损" // 状态
      },
      {
        coord_x: 26.783237,
        coord_y: 117.720114,
        mng_comp_id: "C002",
        comp_name: "湿度传感器-街道B",
        comp_status: "维护"
      },
      {
        coord_x: 26.9333327,
        coord_y: 117.650114,
        mng_comp_id: "C003",
        comp_name: "压力传感器-路口C",
        comp_status: "完好"
      },
      {
        coord_x: 26.823237,
        coord_y: 117.8220114,
        mng_comp_id: "C004",
        comp_name: "振动传感器-桥梁D",
        comp_status: "完好"
      }
    ];
  }
};

// 部件列表接口
export const fetchPartsList = () => {
  try {
    return axios.get(`${BASE_URL}/urbanParts`).then(response => {
      if (Array.isArray(response.data) && response.data.length > 0) {
        return response.data;
      }
      throw new Error('真实接口返回空数据，使用模拟数据兜底');
    }).catch(error => {
      console.warn('部件列表接口调用失败，使用模拟数据:', error.message);
      return new Promise(resolve => {
        setTimeout(() => {
          resolve([
            {
              part_id: 'P001', // 部件ID
              part_name: '人行道地砖', // 部件名称
              part_type: '市政', // 部件类型（市政/市容/环卫）
              status: '完好', // 状态（完好/破损/维护）
              manager: '张三', // 负责人
              related_event: '无', // 关联事件
              location: '解放路东段', // 位置
              install_time: '2023-05-10', // 安装时间
              last_check: '2025-10-12' // 最后检查时间
            },
            {
              part_id: 'P002',
              part_name: '果皮箱',
              part_type: '环卫',
              status: '破损',
              manager: '李四',
              related_event: 'EVT20251013001', // 关联事件ID
              location: '中山路中段',
              install_time: '2024-01-20',
              last_check: '2025-10-10'
            },
            {
              part_id: 'P003',
              part_name: '交通隔离栏',
              part_type: '市政',
              status: '维护',
              manager: '王五',
              related_event: 'EVT20251012005',
              location: '人民路西段',
              install_time: '2023-11-05',
              last_check: '2025-10-13'
            },
            {
              part_id: 'P004',
              part_name: '路灯',
              part_type: '市政',
              status: '完好',
              manager: '赵六',
              related_event: '无',
              location: '公园路全段',
              install_time: '2022-08-15',
              last_check: '2025-10-09'
            },
            {
              part_id: 'P005',
              part_name: '广告牌',
              part_type: '市容',
              status: '破损',
              manager: '孙七',
              related_event: 'EVT20251011003',
              location: '商业广场外墙',
              install_time: '2024-03-30',
              last_check: '2025-10-11'
            },
            {
              part_id: 'P006',
              part_name: '垃圾桶',
              part_type: '环卫',
              status: '维护',
              manager: '周八',
              related_event: '无',
              location: '菜市场入口',
              install_time: '2024-05-20',
              last_check: '2025-10-13'
            }
          ]);
        }, 500);
      });
    });
  } catch (error) {
    console.error('fetchPartsList初始化错误:', error);
    return new Promise(resolve => {
      setTimeout(() => {
        resolve([/* 同上模拟数据 */]);
      }, 500);
    });
  }
};

// 获取城管设施分布数据
export const fetchUrbanFacilityDistribution = async () => {
  try {
    // 1. 优先调用真实接口
    const response = await axios.get(`${BASE_URL}/facilityDistribution`);
    // 验证接口返回数据有效性
    if (Array.isArray(response.data) && response.data.length > 0) {
      return response.data; // 返回真实数据
    }
    throw new Error('真实接口返回空数据，使用模拟数据兜底');
  } catch (error) {
    // 2. 接口调用失败，返回模拟数据
    console.warn('城管设施分布接口调用失败，使用模拟数据:', error.message);
    return [
      {
        dist_id: 'dist1001',
        mng_comp_id: 'comp1001', // 设施ID（对应mng_comp_id）
        comp_name: '城东路路灯', // 设施名称（对应comp_name）
        facility_type: '1', // 类型编码（用于统计）
        minor_name: '照明设施', // 类型名称（对应minor_name）
        region: '城东区和平街道',
        install_time: '2020-05-10 00:00:00',
        map_coord: '1.2,1.5',
        facility_status: '0',
        repair_order_id: '',
        maintain_user_id: 'user2001',
        repair_time: '',
        accept_result: '',
        create_user: 'system',
        create_time: '2025-01-15 08:00:00',
        ext1: 'LED-200W',
        ext2: '每季度维护' // 去掉表来源
      },
      {
        dist_id: 'dist1002',
        mng_comp_id: 'comp1002',
        comp_name: '西湖路垃圾桶',
        facility_type: '2',
        minor_name: '环卫设施',
        region: '西湖区科技街道',
        install_time: '2021-03-15 00:00:00',
        map_coord: '1.3,1.8',
        facility_status: '1',
        repair_order_id: 'repair1001',
        maintain_user_id: 'user2002',
        repair_time: '',
        accept_result: '',
        create_user: 'system',
        create_time: '2025-01-15 08:00:00',
        ext1: '分类垃圾桶',
        ext2: '每月维护'
      },
      {
        dist_id: 'dist1003',
        mng_comp_id: 'comp1003',
        comp_name: '河东路公厕',
        facility_type: '2',
        minor_name: '环卫设施',
        region: '河东区滨河街道',
        install_time: '2019-08-20 00:00:00',
        map_coord: '1.9,1.6',
        facility_status: '0',
        repair_order_id: '',
        maintain_user_id: 'user2002',
        repair_time: '2025-01-10 14:30:00',
        accept_result: '合格',
        create_user: 'system',
        create_time: '2025-01-15 08:00:00',
        ext1: '三星级',
        ext2: '每周维护'
      },
      {
        dist_id: 'dist1004',
        mng_comp_id: 'comp1004',
        comp_name: '北环路交通信号灯',
        facility_type: '3',
        minor_name: '市政设施',
        region: '北城区工业街道',
        install_time: '2018-11-05 00:00:00',
        map_coord: '1.4,1.0',
        facility_status: '2',
        repair_order_id: 'repair1002',
        maintain_user_id: 'user2003',
        repair_time: '',
        accept_result: '',
        create_user: 'system',
        create_time: '2025-01-15 08:00:00',
        ext1: '智能交通灯',
        ext2: '每半年维护'
      },
      {
        dist_id: 'dist1005',
        mng_comp_id: 'comp1005',
        comp_name: '南郊路果皮箱',
        facility_type: '2',
        minor_name: '环卫设施',
        region: '南郊区环城街道',
        install_time: '2022-04-12 00:00:00',
        map_coord: '1.7,1.3',
        facility_status: '0',
        repair_order_id: '',
        maintain_user_id: 'user2002',
        repair_time: '',
        accept_result: '',
        create_user: 'system',
        create_time: '2025-01-15 08:00:00',
        ext1: '不锈钢',
        ext2: '每月维护'
      },
      {
        dist_id: 'dist1006',
        mng_comp_id: 'comp1006',
        comp_name: '科技路路灯',
        facility_type: '1',
        minor_name: '照明设施',
        region: '高新区创新街道',
        install_time: '2021-09-30 00:00:00',
        map_coord: '1.6,1.7',
        facility_status: '1',
        repair_order_id: 'repair1003',
        maintain_user_id: 'user2001',
        repair_time: '',
        accept_result: '',
        create_user: 'system',
        create_time: '2025-01-15 08:00:00',
        ext1: 'LED-300W',
        ext2: '每季度维护'
      },
      // 新增“市容设施”类型
      {
        dist_id: 'dist1007',
        mng_comp_id: 'comp1007',
        comp_name: '步行街广告牌',
        facility_type: '4',
        minor_name: '市容设施',
        region: '主城区商业街道',
        install_time: '2021-06-20 00:00:00',
        map_coord: '1.6,1.4',
        facility_status: '0',
        repair_order_id: '',
        maintain_user_id: 'user2004',
        repair_time: '',
        accept_result: '',
        create_user: 'system',
        create_time: '2025-01-15 08:00:00',
        ext1: '户外广告',
        ext2: '每季度维护'
      }
    ];
  }
};

// 获取城管核心指标数据
export const fetchUrbanCoreIndicators = async (timeRange = 'day') => {
  try {
    // 1. 优先调用真实接口
    const response = await axios.get(`${BASE_URL}/coreIndicators`, {
      params: { timeRange }
    });
    // 验证接口返回数据有效性
    if (Array.isArray(response.data) && response.data.length > 0) {
      return response.data; // 返回真实数据
    }
    throw new Error('真实接口返回空数据，使用模拟数据兜底');
  } catch (error) {
    // 2. 接口调用失败，返回模拟数据（新增指标类别、同比、环比数据）
    console.warn('城管核心指标接口调用失败，使用模拟数据:', error.message);
    return [
      {
        index_id: 'ind1001',
        index_name: '部件总数',
        index_category: '部件指标',
        index_code: 'total_comp_count',
        stat_cycle: '1', // 每小时统计
        current_value: 586, // 直接显示总数（无百分比）
        target_value: '', // 无目标值
        warn_threshold: '',
        remind_threshold: '',
        index_status: '0', // 正常（总数无异常状态）
        yoy: 3.2, // 同比增长数
        mom: 1.5, // 环比增长数
        total_count: 586, // 部件总数
        intact_count: 566, // 关联完好数（用于辅助展示）
        supervise_order_id: '',
        leader_id: 'user3001',
        ext1: '直接统计总数',
        ext2: 'stat_mng_comp_rpt' // 来源管理部件统计表
      },
      {
        index_id: 'ind1002',
        index_name: '完好率',
        index_category: '部件指标',
        index_code: 'facility_intact_rate',
        stat_cycle: '1',
        current_value: 96.5, // 百分比
        target_value: 98.0,
        warn_threshold: 90.0,
        remind_threshold: 98.0,
        index_status: '1', // 提醒
        yoy: 1.2,
        mom: -0.3,
        total_count: 586, // 部件总数（关联）
        intact_count: 566, // 完好数
        ext1: '完好部件数/部件总数',
        ext2: 'stat_mng_comp_rpt'
      },
      {
        index_id: 'ind1003',
        index_name: '事件总数',
        index_category: '事件指标',
        index_code: 'total_rpt_count',
        stat_cycle: '2', // 每日统计
        current_value: 126, // 直接显示总数
        target_value: '',
        warn_threshold: '',
        remind_threshold: '',
        index_status: '0',
        yoy: 8.5, // 同比增长数
        mom: 2.3, // 环比增长数
        total_count: 126, // 事件总数
        rejected_count: 8, // 关联驳回数（辅助展示）
        ext1: '直接统计总数',
        ext2: 'stat_mng_matter_rpt' // 来源管理事项统计表
      },
      {
        index_id: 'ind1004',
        index_name: '办结率',
        index_category: '事件指标',
        index_code: 'event_completion_rate',
        stat_cycle: '2',
        current_value: 78.0, // 百分比（<80%标红）
        target_value: 95.0,
        warn_threshold: 80.0,
        remind_threshold: 95.0,
        index_status: '2', // 预警
        yoy: -3.5,
        mom: -2.1,
        total_count: 126,
        completed_count: 98,
        rejected_count: 8,
        ext1: '已办结数/(总数-驳回数)',
        ext2: 'stat_mng_matter_rpt'
      },
      {
        index_id: 'ind1005',
        index_name: '平均处置时长',
        index_category: '处置指标',
        index_code: 'avg_handle_duration',
        stat_cycle: '2',
        current_value: 125, // 单位：分钟（无百分比）
        target_value: 90,
        warn_threshold: 120,
        remind_threshold: 60,
        index_status: '2', // 预警
        yoy: 15.3,
        mom: 8.7,
        total_disposed_count: 98,
        total_handle_duration: 12250,
        ext1: '总时长/已处置数',
        ext2: 'stat_mng_matter_rpt'
      },
      {
        index_id: 'ind1006',
        index_name: '未处置数',
        index_category: '事件指标',
        index_code: 'pend_count',
        stat_cycle: '2',
        current_value: 20, // 直接显示数量
        target_value: 10, // 目标：≤10
        warn_threshold: 15, // 超过15标红
        remind_threshold: 10,
        index_status: '2', // 预警（20>15）
        yoy: 5.0, // 同比增长数
        mom: 3.0, // 环比增长数
        total_count: 126, // 事件总数（关联）
        pend_count: 20, // 未处置数
        ext1: '事件总数-已办结数-驳回数',
        ext2: 'stat_mng_matter_rpt'
      }
    ];
  }
};

// 获取指标近7天趋势数据
export const fetchIndicatorTrend = async (indexId) => {
  try {
    // 优先调用真实接口
    const response = await axios.get(`${BASE_URL}/indicatorTrend`, {
      params: { indexId, days: 7 } // 明确传入7天参数
    });
    if (response.data && response.data.xAxis && response.data.series) {
      return response.data; // 真实接口返回格式：{xAxis: [], series: [{name, data}, ...]}
    }
    throw new Error('真实接口返回数据无效，使用模拟数据');
  } catch (error) {
    console.warn('指标7天趋势接口调用失败，使用模拟数据:', error.message);
    // 生成近7天模拟数据
    const xAxis = [];
    const data = [];
    const targetData = [];
    const today = new Date();
    // 匹配现有指标的基础值
    const baseValues = {
      'ind1001': 586, // 部件总数
      'ind1002': 96.5, // 完好率
      'ind1003': 126, // 事件总数
      'ind1004': 78.0, // 办结率
      'ind1005': 20, // 未处置数
      'ind1006': 125  // 平均时长
    };
    const baseValue = baseValues[indexId] || 90; // 默认基础值
    // 根据指标类型设置目标值
    const targetValue = indexId === 'ind1005' ? 90 : 95; // 平均处置时长目标值为90分钟

    // 生成近7天日期（从6天前到今天）
    for (let i = 6; i >= 0; i--) {
      const date = new Date(today);
      date.setDate(today.getDate() - i);
      xAxis.push(`${date.getMonth() + 1}/${date.getDate()}`); // 格式：月/日

      // 模拟波动数据
      let fluctuation;
      if (indexId === 'ind1005') {
        // 平均处置时长波动范围±15分钟
        fluctuation = (Math.random() - 0.5) * 30;
      } else {
        // 百分比指标波动范围±2%
        fluctuation = (Math.random() - 0.5) * 4;
      }

      data.push(parseFloat((baseValue + fluctuation).toFixed(2)));
      targetData.push(targetValue);
    }

    return {
      xAxis,
      series: [
        { name: indexId === 'ind1005' ? '平均时长(分钟)' : '实际值(%)',
          data,
          type: 'line',
          lineStyle: { width: 2 }
        },
        { name: indexId === 'ind1005' ? '目标时长(分钟)' : '目标值(%)',
          data: targetData,
          type: 'line',
          lineStyle: { type: 'dashed', width: 2 }
        }
      ]
    };
  }
};

// 获取城管事件处置状态数据
export const fetchEventDisposalStatus = async () => {
  try {
    // 1. 优先调用真实接口
    const response = await axios.get(`${BASE_URL}/eventDisposalStatus`);
    // 验证接口返回数据有效性
    if (Array.isArray(response.data) && response.data.length > 0) {
      return response.data; // 返回真实数据
    }
    throw new Error('真实接口返回空数据，使用模拟数据兜底');
  } catch (error) {
    // 2. 接口调用失败，返回模拟数据
    console.warn('城管事件处置状态接口调用失败，使用模拟数据:', error.message);
    return [
      {
        event_id: 'event1001',
        event_name: '南郊路占道经营',
        event_type: '流动摊贩',
        region: '南郊区环城街道',
        occur_time: '2025-01-15 09:30:00',
        status: '0', // 0（待处置）
        level: '一般',
        handle_user_id: '',
        handle_user_name: '',
        expect_time: '2025-01-15 10:00:00', // 30分钟内响应
        actual_handle_time: '',
        finish_time: '',
        create_user: 'system',
        create_time: '2025-01-15 09:30:00',
        ext1: '2名摊贩',
        ext2: '无冲突'
      },
      {
        event_id: 'event1002',
        event_name: '北区违建',
        event_type: '违章搭建',
        region: '北城区工业街道',
        occur_time: '2025-01-15 07:30:00',
        status: '1', // 1（处置中）
        level: '严重',
        handle_user_id: 'user1002',
        handle_user_name: '李四',
        expect_time: '2025-01-15 15:30:00', // 8小时内处置
        actual_handle_time: '2025-01-15 08:15:00',
        finish_time: '',
        create_user: 'system',
        create_time: '2025-01-15 07:30:00',
        ext1: '约50平米',
        ext2: '正在调查'
      },
      {
        event_id: 'event1003',
        event_name: '主城区垃圾堆积',
        event_type: '环境卫生',
        region: '主城区中心街道',
        occur_time: '2025-01-14 16:45:00',
        status: '2', // 2（已完成）
        level: '一般',
        handle_user_id: 'user1003',
        handle_user_name: '王五',
        expect_time: '2025-01-15 08:45:00',
        actual_handle_time: '2025-01-14 17:05:00',
        finish_time: '2025-01-14 18:30:00',
        create_user: 'system',
        create_time: '2025-01-14 16:45:00',
        ext1: '居民区',
        ext2: '已清理'
      },
      {
        event_id: 'event1004',
        event_name: '科技路占道施工',
        event_type: '违规施工',
        region: '高新区创新街道',
        occur_time: '2025-01-15 10:20:00',
        status: '0', // 0（待处置）
        level: '较严重',
        handle_user_id: '',
        handle_user_name: '',
        expect_time: '2025-01-15 10:50:00',
        actual_handle_time: '',
        finish_time: '',
        create_user: 'system',
        create_time: '2025-01-15 10:20:00',
        ext1: '无施工许可',
        ext2: '占用半幅路面'
      },
      {
        event_id: 'event1005',
        event_name: '东城菜市场违规停车',
        event_type: '违规停车',
        region: '东城区农贸街道',
        occur_time: '2025-01-15 08:10:00',
        status: '1', // 1（处置中）
        level: '一般',
        handle_user_id: 'user1005',
        handle_user_name: '赵六',
        expect_time: '2025-01-15 09:10:00', // 1小时内处置
        actual_handle_time: '2025-01-15 08:25:00',
        finish_time: '',
        create_user: 'system',
        create_time: '2025-01-15 08:10:00',
        ext1: '3辆货车',
        ext2: '堵塞消防通道'
      },
      {
        event_id: 'event1006',
        event_name: '西湖景区非法小广告',
        event_type: '户外广告违规',
        region: '西城区文旅街道',
        occur_time: '2025-01-14 14:20:00',
        status: '2', // 2（已完成）
        level: '轻微',
        handle_user_id: 'user1006',
        handle_user_name: '孙七',
        expect_time: '2025-01-14 17:20:00',
        actual_handle_time: '2025-01-14 15:00:00',
        finish_time: '2025-01-14 16:10:00',
        create_user: 'system',
        create_time: '2025-01-14 14:20:00',
        ext1: '墙面张贴',
        ext2: '已清理20余处'
      },
      {
        event_id: 'event1007',
        event_name: '夜间工地噪音污染',
        event_type: '噪音污染',
        region: '新城区建设街道',
        occur_time: '2025-01-15 22:15:00',
        status: '0', // 0（待处置）
        level: '较严重',
        handle_user_id: '',
        handle_user_name: '',
        expect_time: '2025-01-15 23:15:00', // 1小时内响应
        actual_handle_time: '',
        finish_time: '',
        create_user: 'system',
        create_time: '2025-01-15 22:15:00',
        ext1: '居民区旁工地',
        ext2: '多次投诉'
      },
      {
        event_id: 'event1008',
        event_name: '步行街无证游商',
        event_type: '流动摊贩',
        region: '主城区商业街道',
        occur_time: '2025-01-15 15:40:00',
        status: '1', // 1（处置中）
        level: '一般',
        handle_user_id: 'user1008',
        handle_user_name: '周八',
        expect_time: '2025-01-15 16:40:00',
        actual_handle_time: '2025-01-15 15:55:00',
        finish_time: '',
        create_user: 'system',
        create_time: '2025-01-15 15:40:00',
        ext1: '售卖小商品',
        ext2: '人流量大'
      },
      {
        event_id: 'event1009',
        event_name: '违章广告牌',
        event_type: '户外广告违规',
        region: '城东区和平街道',
        occur_time: '2025-01-14 10:00:00',
        status: '3', // 3（已驳回）
        level: '一般',
        handle_user_id: 'user1003',
        handle_user_name: '王五',
        expect_time: '',
        actual_handle_time: '2025-01-14 10:30:00',
        finish_time: '2025-01-14 10:30:00',
        create_user: 'system',
        create_time: '2025-01-14 10:00:00',
        ext1: '违规设置',
        ext2: '不属于城管管辖范围'
      }
    ];
  }
};

// 获取近期预警信息数据
export const fetchWarningData = async () => {
  try {
    // 1. 优先调用真实接口
    const response = await axios.get(`${BASE_URL}/warningData`);
    // 验证接口返回数据有效性
    if (Array.isArray(response.data) && response.data.length > 0) {
      return response.data; // 返回真实数据
    }
    throw new Error('真实接口返回空数据，使用模拟数据兜底');
  } catch (error) {
    // 2. 接口调用失败，返回模拟数据
    console.warn('近期预警信息接口调用失败，使用模拟数据:', error.message);
    return [
      ['南郊区', '占道经营事件', '09:20', '待处置'],
      ['北城区', '违章搭建事件', '07:30', '处置中'],
      ['西湖区', '垃圾桶故障', '08:45', '待维修'],
      ['高新区', '路灯故障', '10:15', '待维修'],
      ['城东区', '设施完好率低', '09:30', '已督办'],
      ['主城区', '事件办结率低', '10:00', '已督办'],
      ['全区域', '平均处置时长长', '11:00', '预警中']
    ];
  }
};

// 派单处理事件
export const dispatchEvent = async (params) => {
  try {
    // 1. 优先调用真实接口
    const response = await axios.post(`${BASE_URL}/dispatchEvent`, params);
    // 验证接口返回数据有效性
    if (response.data && response.data.success) {
      return response.data; // 返回真实结果
    }
    throw new Error('真实接口返回无效结果，使用模拟结果兜底');
  } catch (error) {
    // 2. 接口调用失败，返回模拟结果
    console.warn('派单处理事件接口调用失败，使用模拟结果:', error.message);
    return {
      success: true,
      message: '派单成功',
      data: {
        dispatchOrderId: 'order' + Math.random().toString(36).substr(2, 9),
        dispatchTime: new Date().toLocaleString()
      }
    };
  }
};

// 处理事件状态更新
export const handleEvent = async (params) => {
  try {
    // 1. 优先调用真实接口
    const response = await axios.post(`${BASE_URL}/handleEvent`, params);
    // 验证接口返回数据有效性
    if (response.data && response.data.success) {
      return response.data; // 返回真实结果
    }
    throw new Error('真实接口返回无效结果，使用模拟结果兜底');
  } catch (error) {
    // 2. 接口调用失败，返回模拟结果
    console.warn('处理事件状态更新接口调用失败，使用模拟结果:', error.message);
    return {
      success: true,
      message: '事件处理成功',
      data: {
        handleTime: new Date().toLocaleString()
      }
    };
  }
};

// 生成督办工单
export const createSuperviseOrder = async (params) => {
  try {
    // 1. 优先调用真实接口
    const response = await axios.post(`${BASE_URL}/createSuperviseOrder`, params);
    // 验证接口返回数据有效性
    if (response.data && response.data.success) {
      return response.data; // 返回真实结果
    }
    throw new Error('真实接口返回无效结果，使用模拟结果兜底');
  } catch (error) {
    // 2. 接口调用失败，返回模拟结果
    console.warn('生成督办工单接口调用失败，使用模拟结果:', error.message);
    return {
      success: true,
      message: '督办工单生成成功',
      data: {
        superviseOrderId: 'supervise' + Math.random().toString(36).substr(2, 9),
        createTime: new Date().toLocaleString()
      }
    };
  }
};

// 生成维修工单
export const createRepairOrder = async (params) => {
  try {
    // 1. 优先调用真实接口
    const response = await axios.post(`${BASE_URL}/createRepairOrder`, params);
    // 验证接口返回数据有效性
    if (response.data && response.data.success) {
      return response.data; // 返回真实结果
    }
    throw new Error('真实接口返回无效结果，使用模拟结果兜底');
  } catch (error) {
    // 2. 接口调用失败，返回模拟结果
    console.warn('生成维修工单接口调用失败，使用模拟结果:', error.message);
    return {
      success: true,
      message: '维修工单生成成功',
      data: {
        repairOrderId: 'repair' + Math.random().toString(36).substr(2, 9),
        createTime: new Date().toLocaleString()
      }
    };
  }
};
