import request from '@/config/axios'

// 救援队伍分类配置 VO
export interface EmerTeamCatVO {
  id: number // 自增主键ID
  teamCatId: string // 分类ID
  parentCatId: string // 父分类ID
  catLevel: string // 分类层级
  catCode: string // 分类编码
  catName: string // 分类名称
  catDesc: string // 分类说明
  applyRegionCode: string // 适用区域代码
  applyRegionName: string // 适用区域名称
}

// 救援队伍分类配置 API
export const EmerTeamCatApi = {
  // 查询救援队伍分类配置分页
  getEmerTeamCatPage: async (params: any) => {
    return await request.get({ url: `/datacenter/emer-team-cat/page`, params })
  },

  // 查询救援队伍分类配置详情
  getEmerTeamCat: async (id: number) => {
    return await request.get({ url: `/datacenter/emer-team-cat/get?id=` + id })
  },

  // 新增救援队伍分类配置
  createEmerTeamCat: async (data: EmerTeamCatVO) => {
    return await request.post({ url: `/datacenter/emer-team-cat/create`, data })
  },

  // 修改救援队伍分类配置
  updateEmerTeamCat: async (data: EmerTeamCatVO) => {
    return await request.put({ url: `/datacenter/emer-team-cat/update`, data })
  },

  // 删除救援队伍分类配置
  deleteEmerTeamCat: async (id: number) => {
    return await request.delete({ url: `/datacenter/emer-team-cat/delete?id=` + id })
  },

  // 导出救援队伍分类配置 Excel
  exportEmerTeamCat: async (params) => {
    return await request.download({ url: `/datacenter/emer-team-cat/export-excel`, params })
  },
}