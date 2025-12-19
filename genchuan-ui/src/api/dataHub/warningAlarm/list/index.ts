import request from '@/config/axios'

// 预警告警列表 VO
export interface WarningAlertListTableVO {
  id: number // 预警ID
  alertCode: string // 告警编号
  relatedObjectType: string // 关联对象类型
  relatedObjectId: string // 关联对象ID
  relatedObjectName: string // 关联对象名称
  warningField: string // 预警领域
  warningType: string // 预警类型
  warningTypeId: number
  warningLevel: string // 预警等级
  warningStatus: string // 预警状态
  triggerReason: string // 触发原因
  relatedEventCode: string // 关联事件编号
  dispatchDepartment: string // 派发部门
  responsiblePerson: string // 责任人
  responsiblePersonPhone: string // 责任人电话
  triggerTime: Date // 触发时间
  requiredCompleteTime: Date // 要求完成时间
  disposalProgressDesc: string // 处置进展描述
  disposalAttachmentPath: string // 处置附件路径
  reviewOpinion: string // 审核意见
  reviewer: string // 审核人
  reviewTime: Date // 审核时间
  deviceId: string // 设备ID
  status: number //增加流程事件状态字段 同步流程事件状态
  processInstanceId: string //增加流程实例id字段 关联流程实例id
  regionCode: string //行政区划编码
  regionName: string //所在行政区划名称
  gridId: string //所在网格ID
  gridName: string //所在网格名称
  address: string //事件发生地址
  longitude: string //经度
  latitude: string //纬度
}

// 预警告警列表 API
export const WarningAlertListTableApi = {
  // 查询预警告警列表分页
  getWarningAlertListTablePage: async (params: any) => {
    return await request.get({ url: `/datacenter/warning-alert-list-table/page`, params })
  },

  // 查询预警告警列表详情
  getWarningAlertListTable: async (id: number) => {
    return await request.get({ url: `/datacenter/warning-alert-list-table/get?id=` + id })
  },

  // 新增预警告警列表
  createWarningAlertListTable: async (data: WarningAlertListTableVO) => {
    return await request.post({ url: `/datacenter/warning-alert-list-table/create`, data })
  },

  // 修改预警告警列表
  updateWarningAlertListTable: async (data: WarningAlertListTableVO) => {
    return await request.put({ url: `/datacenter/warning-alert-list-table/update`, data })
  },

  // 删除预警告警列表
  deleteWarningAlertListTable: async (id: number) => {
    return await request.delete({ url: `/datacenter/warning-alert-list-table/delete?id=` + id })
  },

  // 导出预警告警列表 Excel
  exportWarningAlertListTable: async (params) => {
    return await request.download({
      url: `/datacenter/warning-alert-list-table/export-excel`,
      params
    })
  },
  //   importWarningAlertListTable: async (params) => {
  //   return await request.post({ url: `/datacenter/warning-alert-list-table/import`, params })
  // },
  //
  // 下载用户导入模板
  importAlarmWarningTemplate: async () => {
    return await request.download({
      url: `/datacenter/warning-alert-list-table/get-import-template`
    })
  },
  //发起流程
  dispatchWarningAlertListTable: async (id: number) => {
    return await request.post({ url: `/datacenter/warning-alert-list-table/created?id=` + id })
  },
  //预警类型管理部件树形级联选择接口调用封装
  getWarningTypeTree: async () => {
    return await request.get({ url: `/datacenter/managed-matter-major/list` })
  },

  getDeviceList: async (params: any) => {
    return await request.get({ url: `/datacenter/thingsboard/device/page`, params })
  },
  //
  getGridTree: async (params: any) => {
    return await request.get({ url: `/datacenter/grid-county/page`, params })
  },
  //todo 获取部件列表接口
  getPartList: async (params: any) => {
    return await request.get({ url: `/admin-api/component/page`, params })
  },
  //获取部门树形下拉列表
  getDeptTree: async () => {
    return await request.get({ url: `/system/dept/list-all-simple` })
  },
  //获取用户下拉列表
  getSimpleUserList: async () => {
    return await request.get({ url: `/system/user/list-all-simple` })
  }
}
