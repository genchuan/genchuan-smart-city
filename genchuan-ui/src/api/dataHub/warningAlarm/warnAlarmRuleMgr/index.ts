import request from '@/config/axios'


// 预警告警规则配置 VO
export interface AlarmRuleVO {
  id: number // 规则ID
  ruleName: string // 规则名称
  relatedObjectType: string // 关联对象类型
  relatedObjectScope: string // 关联对象范围
  relatedObjectCategoryId: string // 关联对象分类ID
  relatedObjectIdList: string // 关联对象ID列表
  triggerCondition: string // 预警触发条件
  warningIndicatorCode: string // 预警指标编码
  warningThresholdUpper: number // 预警阈值上限
  warningThresholdLower: number // 预警阈值下限
  statusDetermineValue: string // 状态判定值
  triggerDuration: number // 触发时长(分钟)
  earlyWarningLevel: number // 预警等级(1-5)
  triggerFrequency: number // 触发频率(次/小时)
  repeatTriggerInterval: number // 重复触发间隔(分钟)
  enableStatus: number // 启用状态(0-禁用,1-启用)
  ruleDescription: string // 规则描述
  createUser: string // 创建人
  updateUser: string // 更新人
  extendCategory1: string // 扩展分类字段1
  extendCategory2: string // 扩展分类字段2
  extendCategory3: string // 扩展分类字段3
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 预警告警规则配置 API
export const AlarmRuleApi = {
  // 查询预警告警规则配置分页
  getAlarmRulePage: async (params: any) => {
    return await request.get({ url: `/datacenter/alarm-rule/page`, params })
  },

  // 查询预警告警规则配置详情
  getAlarmRule: async (id: number) => {
    return await request.get({ url: `/datacenter/alarm-rule/get?id=` + id })
  },

  // 新增预警告警规则配置
  createAlarmRule: async (data: AlarmRuleVO) => {
    return await request.post({ url: `/datacenter/alarm-rule/create`, data })
  },

  // 修改预警告警规则配置
  updateAlarmRule: async (data: AlarmRuleVO) => {
    return await request.put({ url: `/datacenter/alarm-rule/update`, data })
  },

  // 删除预警告警规则配置
  deleteAlarmRule: async (id: number) => {
    return await request.delete({ url: `/datacenter/alarm-rule/delete?id=` + id })
  },

  // 导出预警告警规则配置 Excel
  exportAlarmRule: async (params) => {
    return await request.download({ url: `/datacenter/alarm-rule/export-excel`, params })
  },

  //禁用告警规则
  disableRule: async (id: number) => {
    return await request.put({ url: `/datacenter/alarm-rule/disable?id=`+ id })
  },

  //启用告警规则
  enableRule: async (id: number) => {
    return await request.put({ url: `/datacenter/alarm-rule/enable?id=`+ id })
  },

  //获取所有已启用告警规则
  getAllEnableRules: async () => {
    return await request.get({ url: `/datacenter/alarm-rule/enabled-list`})
  },

  //根据状态获得预警告警规则配置列表
  getRulesByStatus: async (enableStatus:number) => {
    return await request.get({ url: `/datacenter/alarm-rule/list-by-status/get?enableStatus=` + enableStatus})
  },

  //校验规则唯一性
  verifyRuleUniqueness:async(data:AlarmRuleVO) => {
    return await request.post({ url: `/datacenter/alarm-rule/validate-unique` ,data})
  },


}
