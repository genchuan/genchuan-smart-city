import request from '@/config/axios'

// 联动单位信息 VO
export interface CoopUnitInfoVO {
  id: number // 自增主键ID
  unitId: string // 单位ID
  unitCode: string // 单位编码
  unitName: string // 单位名称
  unitType: string // 单位类型
  regionCode: string // 行政区划代码
  regionName: string // 行政区划名称
  leaderName: string // 负责人姓名
  leaderContact: string // 负责人联系方式
  contactAddress: string // 单位详细地址
  coopScope: string // 联动范围
  unitStatus: string // 单位状态
  createUser: string // 创建人账号
  updateUser: string // 更新人账号
}

// 联动单位信息 API
export const CoopUnitInfoApi = {
  // 查询联动单位信息分页
  getCoopUnitInfoPage: async (params: any) => {
    return await request.get({ url: `/datacenter/coop-unit-info/page`, params })
  },

  // 查询联动单位信息详情
  getCoopUnitInfo: async (id: number) => {
    return await request.get({ url: `/datacenter/coop-unit-info/get?id=` + id })
  },

  // 新增联动单位信息
  createCoopUnitInfo: async (data: CoopUnitInfoVO) => {
    return await request.post({ url: `/datacenter/coop-unit-info/create`, data })
  },

  // 修改联动单位信息
  updateCoopUnitInfo: async (data: CoopUnitInfoVO) => {
    return await request.put({ url: `/datacenter/coop-unit-info/update`, data })
  },

  // 删除联动单位信息
  deleteCoopUnitInfo: async (id: number) => {
    return await request.delete({ url: `/datacenter/coop-unit-info/delete?id=` + id })
  },

  // 导出联动单位信息 Excel
  exportCoopUnitInfo: async (params) => {
    return await request.download({ url: `/datacenter/coop-unit-info/export-excel`, params })
  },
}