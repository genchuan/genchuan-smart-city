import request from '@/config/axios'

// 部件信息 VO
export interface ComponentInformationVO {
  id: number // 主键
  partNumber: string // 部件编号
  componentName: string // 部件名称
  partType: string // 部件类型
  belongingRegion: string // 所属区域
  installationPosition: string // 安装位置
  latitudeLongitude: string // 经纬度坐标
  constructionDate: string // 建设日期
  administrativeDepartment: string // 管理部门
  maintenanceUnit: string // 维护单位
  contactNumber: string // 联系电话
  componentStatus: string // 部件状态
  specificationAndModel: string // 设备图片
  serviceLife: string // 使用寿命
  relatedEventRecords: string // 关联事件记录
}

// 部件信息 API
export const ComponentInformationApi = {
  // 查询部件信息分页
  getComponentInformationPage: async (params: any) => {
    return await request.get({ url: `/smartcity/component-information/page`, params })
  },

  // 查询部件信息详情
  getComponentInformation: async (id: number) => {
    return await request.get({ url: `/smartcity/component-information/get?id=` + id })
  },

  // 新增部件信息
  createComponentInformation: async (data: ComponentInformationVO) => {
    return await request.post({ url: `/smartcity/component-information/create`, data })
  },

  // 修改部件信息
  updateComponentInformation: async (data: ComponentInformationVO) => {
    return await request.put({ url: `/smartcity/component-information/update`, data })
  },

  // 删除部件信息
  deleteComponentInformation: async (id: number) => {
    return await request.delete({ url: `/smartcity/component-information/delete?id=` + id })
  },

  // 导出部件信息 Excel
  exportComponentInformation: async (params) => {
    return await request.download({ url: `/smartcity/component-information/export-excel`, params })
  }
}