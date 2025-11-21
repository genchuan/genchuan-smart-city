import request from '@/config/axios'

// 应急物资信息 VO
export interface EmerMatInfoVO {
  id: number // 自增主键ID
  matId: string // 物资ID，唯一编码，UUID
  matCode: string // 物资编码，系统自动生成
  matName: string // 物资名称，标准名称
  catId: string // 分类ID，关联应急物资分类配置表
  catName: string // 分类名称，与分类ID同步
  applyRegionCode: string // 适用区域代码，关联行政区划表
  applyRegionName: string // 适用区域名称，与区域代码同步
  matSpec: string // 物资规格，如“5kg装”
  matUnit: string // 物资单位，如“个”“箱”
  totalQuantity: number // 总数量，物资的总库存数量
  availableQuantity: number // 可用数量，正常状态可使用的物资数量
  matStatus: string // 物资状态：1（正常）/0（异常）
  createUser: string // 创建人
  updateUser: string // 更新人
}

// 应急物资信息 API
export const EmerMatInfoApi = {
  // 查询应急物资信息分页
  getEmerMatInfoPage: async (params: any) => {
    return await request.get({ url: `/datacenter/emer-mat-info/page`, params })
  },

  // 查询应急物资信息详情
  getEmerMatInfo: async (id: number) => {
    return await request.get({ url: `/datacenter/emer-mat-info/get?id=` + id })
  },

  // 新增应急物资信息
  createEmerMatInfo: async (data: EmerMatInfoVO) => {
    return await request.post({ url: `/datacenter/emer-mat-info/create`, data })
  },

  // 修改应急物资信息
  updateEmerMatInfo: async (data: EmerMatInfoVO) => {
    return await request.put({ url: `/datacenter/emer-mat-info/update`, data })
  },

  // 删除应急物资信息
  deleteEmerMatInfo: async (id: number) => {
    return await request.delete({ url: `/datacenter/emer-mat-info/delete?id=` + id })
  },

  // 导出应急物资信息 Excel
  exportEmerMatInfo: async (params) => {
    return await request.download({ url: `/datacenter/emer-mat-info/export-excel`, params })
  },
}