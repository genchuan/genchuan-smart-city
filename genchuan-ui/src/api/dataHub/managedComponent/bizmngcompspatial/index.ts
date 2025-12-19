import request from '@/config/axios'

// 管理部件空间数据 VO
export interface BizMngCompSpatialVO {
  id: number // 主键ID
  mngCompSpatialId: string // 空间数据ID
  mngCompId: string // 关联管理部件ID
  compName: string // 关联部件名称
  coordSystem: string // 坐标系类型
  coordX: number // 坐标X，经度
  coordY: number // 坐标Y，纬度
  elevation: number // 高程
  accuracy: number // 定位精度
  accuracyLevel: string // 定位精度等级
  surveyUnit: string // 测绘单位
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 管理部件空间数据 API
export const BizMngCompSpatialApi = {
  // 查询管理部件空间数据分页
  getBizMngCompSpatialPage: async (params: any) => {
    return await request.get({ url: `/datacenter/biz-mng-comp-spatial/page`, params })
  },

  // 查询管理部件空间数据详情
  getBizMngCompSpatial: async (id: number) => {
    return await request.get({ url: `/datacenter/biz-mng-comp-spatial/get?id=` + id })
  },

  // 新增管理部件空间数据
  createBizMngCompSpatial: async (data: BizMngCompSpatialVO) => {
    return await request.post({ url: `/datacenter/biz-mng-comp-spatial/create`, data })
  },

  // 修改管理部件空间数据
  updateBizMngCompSpatial: async (data: BizMngCompSpatialVO) => {
    return await request.put({ url: `/datacenter/biz-mng-comp-spatial/update`, data })
  },

  // 删除管理部件空间数据
  deleteBizMngCompSpatial: async (id: number) => {
    return await request.delete({ url: `/datacenter/biz-mng-comp-spatial/delete?id=` + id })
  },

  // 导出管理部件空间数据 Excel
  exportBizMngCompSpatial: async (params) => {
    return await request.download({ url: `/datacenter/biz-mng-comp-spatial/export-excel`, params })
  }
}