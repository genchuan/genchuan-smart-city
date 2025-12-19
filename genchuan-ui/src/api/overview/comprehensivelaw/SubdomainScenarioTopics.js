import request from '@/config/axios'

const BASE_URL = '/law/enforcement';

// ========== 公共API ==========

// 获取区域选项
export const getAreaOptions = () => {
  return request({
    url: `${BASE_URL}/area/options`,
    method: 'get'
  })
}

// 获取部门选项
export const getDepartmentOptions = () => {
  return request({
    url: `${BASE_URL}/department/options`,
    method: 'get'
  })
}

// 获取案件类型选项
export const getCaseTypeOptions = () => {
  return request({
    url: `${BASE_URL}/case-type/options`,
    method: 'get'
  })
}

// ========== 执法资源专题API ==========

// 获取执法资源数据
export const getResourceData = (params) => {
  return request({
    url: `${BASE_URL}/resource/stats`,
    method: 'get',
    params: {
      timePeriod: params.timePeriod,
      areaId: params.area,
      deptId: params.department,
      status: params.resourceStatus,
      // 计算使用率的逻辑参数
      calculateUsage: true
    }
  })
}

// 获取资源详情
export const getResourceDetail = (type, params) => {
  return request({
    url: `${BASE_URL}/resource/${type}/detail`,
    method: 'get',
    params
  })
}

// 获取闲置资源TOP3
export const getIdleResources = (type, params) => {
  return request({
    url: `${BASE_URL}/resource/${type}/idle-top3`,
    method: 'get',
    params: {
      areaId: params.area,
      deptId: params.department,
      timePeriod: params.timePeriod
    }
  })
}

// 获取资源使用趋势
export const getResourceTrend = (type, params) => {
  return request({
    url: `${BASE_URL}/resource/${type}/trend`,
    method: 'get',
    params
  })
}

// 资源使用率计算
export const calculateResourceUsage = (data) => {
  return request({
    url: `${BASE_URL}/resource/calculate-usage`,
    method: 'post',
    data
  })
}

// 检查资源预警
export const checkResourceWarnings = (params) => {
  return request({
    url: `${BASE_URL}/resource/check-warnings`,
    method: 'get',
    params: {
      deviceIntactThreshold: 90, // 设备完好率阈值
      materialSafeStock: true, // 检查安全库存
      staffOnDutyThreshold: 95, // 在岗率阈值
      vehicleUsageThreshold: 85 // 车辆使用率阈值
    }
  })
}

// ========== 执法检查专题API ==========

// 获取执法检查数据
export const getCheckData = (params) => {
  return request({
    url: `${BASE_URL}/check/stats`,
    method: 'get',
    params: {
      checkType: params.type,
      areaId: params.area,
      deptId: params.department,
      trendPeriod: params.trendPeriod,
      timePeriod: params.timePeriod
    }
  })
}

// 获取检查计划执行进度
export const getCheckProgress = (params) => {
  return request({
    url: `${BASE_URL}/check/progress`,
    method: 'get',
    params: {
      areaId: params.area,
      deptId: params.department,
      checkType: params.type
    }
  })
}

// 获取问题整改趋势
export const getRectifyTrend = (params) => {
  return request({
    url: `${BASE_URL}/check/rectify-trend`,
    method: 'get',
    params: {
      trendPeriod: params.trendPeriod,
      areaId: params.area,
      deptId: params.department
    }
  })
}

// 获取未完成检查计划
export const getUncompletedPlans = (params) => {
  return request({
    url: `${BASE_URL}/check/uncompleted-plans`,
    method: 'get',
    params
  })
}

// 获取检查类型统计
export const getCheckTypeStats = (params) => {
  return request({
    url: `${BASE_URL}/check/type-stats`,
    method: 'get',
    params
  })
}

// 计算检查完成率
export const calculateCheckCompletion = (data) => {
  return request({
    url: `${BASE_URL}/check/calculate-completion`,
    method: 'post',
    data: {
      completedChecks: data.completedCount,
      totalPlans: data.totalCount,
      checkStatus: 2 // 完成状态
    }
  })
}

// ========== 案件管理专题API ==========

// 获取案件管理数据
export const getCaseData = (params) => {
  return request({
    url: `${BASE_URL}/case/stats`,
    method: 'get',
    params: {
      caseTypes: params.type?.join(','),
      deptId: params.department || params.department,
      areaId: params.area,
      timePeriod: params.timePeriod,
      includeOverdue: true
    }
  })
}

// 获取案件办理进度分布
export const getCaseStageDistribution = (params) => {
  return request({
    url: `${BASE_URL}/case/stage-distribution`,
    method: 'get',
    params: {
      deptId: params.department,
      areaId: params.area,
      caseTypes: params.type?.join(',')
    }
  })
}

// 获取案件来源分布
export const getCaseSourceDistribution = (params) => {
  return request({
    url: `${BASE_URL}/case/source-distribution`,
    method: 'get',
    params: {
      limit: 3, // 只取TOP3
      timePeriod: params.timePeriod,
      deptId: params.department,
      areaId: params.area
    }
  })
}

// 获取超期案件TOP5
export const getOverdueCases = (params) => {
  return request({
    url: `${BASE_URL}/case/overdue-top5`,
    method: 'get',
    params: {
      deptId: params.department,
      areaId: params.area,
      caseTypes: params.type?.join(','),
      timePeriod: params.timePeriod,
      handleLimit: true // 包含办理时限
    }
  })
}

// 获取处罚金额统计
export const getPunishmentAmount = (params) => {
  return request({
    url: `${BASE_URL}/case/punishment-amount`,
    method: 'get',
    params: {
      caseStatus: 3, // 已处罚状态
      deptId: params.department,
      areaId: params.area,
      timePeriod: params.timePeriod
    }
  })
}

// 获取案件趋势
export const getCaseTrend = (params) => {
  return request({
    url: `${BASE_URL}/case/trend`,
    method: 'get',
    params
  })
}

// 检查超期案件
export const checkOverdueCases = (params) => {
  return request({
    url: `${BASE_URL}/case/check-overdue`,
    method: 'get',
    params: {
      currentTime: new Date().toISOString(),
      acceptTimeField: 'accept_time',
      handleLimitField: 'handle_limit'
    }
  })
}

// ========== 执法监督专题API ==========

// 获取执法监督数据
export const getSupervisionData = (params) => {
  return request({
    url: `${BASE_URL}/supervision/stats`,
    method: 'get',
    params: {
      supervisionType: params.type,
      period: params.period,
      areaId: params.area,
      deptId: params.department,
      timePeriod: params.timePeriod
    }
  })
}

// 获取合规检查趋势
export const getComplianceTrend = (params) => {
  return request({
    url: `${BASE_URL}/supervision/compliance-trend`,
    method: 'get',
    params: {
      period: params.period,
      areaId: params.area,
      deptId: params.department,
      qualifiedStatus: 1 // 合格状态
    }
  })
}

// 获取投诉举报处理列表
export const getComplaintList = (params) => {
  return request({
    url: `${BASE_URL}/supervision/complaint-list`,
    method: 'get',
    params: {
      limit: 5,
      handleStatus: params.handleStatus,
      period: params.period,
      areaId: params.area,
      deptId: params.department
    }
  })
}

// 获取违规执法记录
export const getIllegalRecords = (params) => {
  return request({
    url: `${BASE_URL}/supervision/illegal-records`,
    method: 'get',
    params: {
      period: params.period,
      areaId: params.area,
      deptId: params.department,
      illegalThreshold: 3 // 违规次数阈值
    }
  })
}

// 获取执法人员信用评分
export const getStaffCreditScores = (params) => {
  return request({
    url: `${BASE_URL}/supervision/staff-credit`,
    method: 'get',
    params: {
      period: params.period,
      areaId: params.area,
      deptId: params.department,
      avgOnly: true // 只返回平均值
    }
  })
}

// 计算合规率
export const calculateComplianceRate = (data) => {
  return request({
    url: `${BASE_URL}/supervision/calculate-compliance`,
    method: 'post',
    data: {
      qualifiedCount: data.qualifiedCount,
      totalCount: data.totalCount,
      threshold: 95 // 合规率阈值
    }
  })
}

// 计算投诉处理率
export const calculateComplaintHandleRate = (data) => {
  return request({
    url: `${BASE_URL}/supervision/calculate-complaint-rate`,
    method: 'post',
    data: {
      handledCount: data.handledCount,
      totalCount: data.totalCount,
      handleStatus: 2 // 已处理状态
    }
  })
}

// ========== 实时数据API ==========

// 订阅实时数据
export const subscribeRealTimeData = (theme) => {
  return request({
    url: `${BASE_URL}/realtime/subscribe/${theme}`,
    method: 'get'
  })
}

// 取消订阅
export const unsubscribeRealTimeData = (theme) => {
  return request({
    url: `${BASE_URL}/realtime/unsubscribe/${theme}`,
    method: 'get'
  })
}

// 获取实时更新
export const getRealTimeUpdates = (theme, params) => {
  return request({
    url: `${BASE_URL}/realtime/updates/${theme}`,
    method: 'get',
    params
  })
}

// ========== 预警API ==========

// 获取资源预警
export const getResourceWarnings = (params) => {
  return request({
    url: `${BASE_URL}/warning/resource`,
    method: 'get',
    params: {
      deviceIntactRate: { lt: 90 }, // 设备完好率<90%
      materialStock: { lt: 'safeStock' }, // 库存<安全库存
      staffOnDutyRate: { lt: 95 }, // 在岗率<95%
      vehicleUseRate: { lt: 85 } // 车辆使用率<85%
    }
  })
}

// 获取检查预警
export const getCheckWarnings = (params) => {
  return request({
    url: `${BASE_URL}/warning/check`,
    method: 'get',
    params: {
      rectifyRate: { lt: 80 }, // 整改率<80%
      completionRate: { lt: 90 }, // 完成率<90%
      overdueDays: { gt: 7 } // 超期>7天
    }
  })
}

// 获取案件预警
export const getCaseWarnings = (params) => {
  return request({
    url: `${BASE_URL}/warning/case`,
    method: 'get',
    params: {
      overdueCount: { gt: 5 }, // 超期案件>5
      handleTime: { gt: 'handleLimit' } // 办理时间>时限
    }
  })
}

// 获取监督预警
export const getSupervisionWarnings = (params) => {
  return request({
    url: `${BASE_URL}/warning/supervision`,
    method: 'get',
    params: {
      illegalCount: { gt: 3 }, // 违规次数>3
      complianceRate: { lt: 95 }, // 合规率<95%
      creditScore: { lt: 3 } // 信用评分<3
    }
  })
}

// ========== 数据导出API ==========

// 导出资源数据
export const exportResourceData = (params) => {
  return request({
    url: `${BASE_URL}/export/resource`,
    method: 'get',
    params,
    responseType: 'blob'
  })
}

// 导出检查数据
export const exportCheckData = (params) => {
  return request({
    url: `${BASE_URL}/export/check`,
    method: 'get',
    params,
    responseType: 'blob'
  })
}

// 导出案件数据
export const exportCaseData = (params) => {
  return request({
    url: `${BASE_URL}/export/case`,
    method: 'get',
    params,
    responseType: 'blob'
  })
}

// 导出监督数据
export const exportSupervisionData = (params) => {
  return request({
    url: `${BASE_URL}/export/supervision`,
    method: 'get',
    params,
    responseType: 'blob'
  })
}

export default {
  getAreaOptions,
  getDepartmentOptions,
  getCaseTypeOptions,
  getResourceData,
  getCheckData,
  getCaseData,
  getSupervisionData,
  getResourceWarnings,
  getCheckWarnings,
  getCaseWarnings,
  getSupervisionWarnings,
  calculateResourceUsage,
  calculateCheckCompletion,
  checkOverdueCases,
  calculateComplianceRate,
  calculateComplaintHandleRate
}
