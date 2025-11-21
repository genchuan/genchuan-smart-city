import request from '@/config/axios'

// 救援队伍信息 VO
export interface EmerTeamInfoVO {
  id: number // 自增主键ID
  teamId: string // 队伍ID
  teamCode: string // 队伍编码
  teamName: string // 队伍名称
  catId: string // 分类ID
  catName: string // 分类名称
  applyRegionCode: string // 适用区域代码
  applyRegionName: string // 适用区域名称
  teamMemberCount: number // 队伍人数
  availableMemberCount: number // 可用人数
  teamStatus: string // 队伍状态，1（正常）/0（异常），默认1
  createUser: string // 创建人
  updateUser: string // 更新人
}

// 救援队伍信息 API
export const EmerTeamInfoApi = {
  // 查询救援队伍信息分页
  getEmerTeamInfoPage: async (params: any) => {
    return await request.get({ url: `/datacenter/emer-team-info/page`, params })
  },

  // 查询救援队伍信息详情
  getEmerTeamInfo: async (id: number) => {
    return await request.get({ url: `/datacenter/emer-team-info/get?id=` + id })
  },

  // 新增救援队伍信息
  createEmerTeamInfo: async (data: EmerTeamInfoVO) => {
    return await request.post({ url: `/datacenter/emer-team-info/create`, data })
  },

  // 修改救援队伍信息
  updateEmerTeamInfo: async (data: EmerTeamInfoVO) => {
    return await request.put({ url: `/datacenter/emer-team-info/update`, data })
  },

  // 删除救援队伍信息
  deleteEmerTeamInfo: async (id: number) => {
    return await request.delete({ url: `/datacenter/emer-team-info/delete?id=` + id })
  },

  // 导出救援队伍信息 Excel
  exportEmerTeamInfo: async (params) => {
    return await request.download({ url: `/datacenter/emer-team-info/export-excel`, params })
  },
}