import request from '@/config/axios'

// 联动指令 VO
export interface CoopCommandVO {
  id: number // 自增主键ID
  commandId: string // 指令ID
  commandNo: string // 指令编号
  commandName: string // 指令名称
  flowId: string // 关联流程ID
  evtCode: string // 关联事件编码
  unitId: string // 联动单位ID
}

// 联动指令 API
export const CoopCommandApi = {
  // 查询联动指令分页
  getCoopCommandPage: async (params: any) => {
    return await request.get({ url: `/datacenter/coop-command/page`, params })
  },

  // 查询联动指令详情
  getCoopCommand: async (id: number) => {
    return await request.get({ url: `/datacenter/coop-command/get?id=` + id })
  },

  // 新增联动指令
  createCoopCommand: async (data: CoopCommandVO) => {
    return await request.post({ url: `/datacenter/coop-command/create`, data })
  },

  // 修改联动指令
  updateCoopCommand: async (data: CoopCommandVO) => {
    return await request.put({ url: `/datacenter/coop-command/update`, data })
  },

  // 删除联动指令
  deleteCoopCommand: async (id: number) => {
    return await request.delete({ url: `/datacenter/coop-command/delete?id=` + id })
  },

  // 导出联动指令 Excel
  exportCoopCommand: async (params) => {
    return await request.download({ url: `/datacenter/coop-command/export-excel`, params })
  },
}