import request from '@/config/axios'

// 违建基本信息 VO
export interface BasicIllegalVO {
  id: number // 主键
  buildingNumber: string // 违建编号
  buildingAddress: string // 违建地址
  constructionArea: string // 违建面积
  typesStructures: string // 违建结构类型
  numberFloors: string // 违建层数
  unauthorizedBuildings: string // 违建用途
}

// 违建基本信息 API
export const BasicIllegalApi = {
  // 查询违建基本信息分页
  getBasicIllegalPage: async (params: any) => {
    return await request.get({ url: `/smartcity/basic-illegal/page`, params })
  },

  // 查询违建基本信息详情
  getBasicIllegal: async (id: number) => {
    return await request.get({ url: `/smartcity/basic-illegal/get?id=` + id })
  },

  // 新增违建基本信息
  createBasicIllegal: async (data: BasicIllegalVO) => {
    return await request.post({ url: `/smartcity/basic-illegal/create`, data })
  },

  // 修改违建基本信息
  updateBasicIllegal: async (data: BasicIllegalVO) => {
    return await request.put({ url: `/smartcity/basic-illegal/update`, data })
  },

  // 删除违建基本信息
  deleteBasicIllegal: async (id: number) => {
    return await request.delete({ url: `/smartcity/basic-illegal/delete?id=` + id })
  },

  // 导出违建基本信息 Excel
  exportBasicIllegal: async (params) => {
    return await request.download({ url: `/smartcity/basic-illegal/export-excel`, params })
  }
}