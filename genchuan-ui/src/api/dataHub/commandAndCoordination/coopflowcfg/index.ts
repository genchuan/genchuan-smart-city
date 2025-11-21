import request from '@/config/axios'

// 联动流程配置 VO
export interface CoopFlowCfgVO {
  id: number // 自增主键ID
  flowCfgId: string // 配置ID
  flowCfgName: string // 配置名称
  flowSteps: string // 流程步骤
  createUser: string // 创建人账号
  updateUser: string // 更新人账号
}

// 联动流程配置 API
export const CoopFlowCfgApi = {
  // 查询联动流程配置分页
  getCoopFlowCfgPage: async (params: any) => {
    return await request.get({ url: `/datacenter/coop-flow-cfg/page`, params })
  },

  // 查询联动流程配置详情
  getCoopFlowCfg: async (id: number) => {
    return await request.get({ url: `/datacenter/coop-flow-cfg/get?id=` + id })
  },

  // 新增联动流程配置
  createCoopFlowCfg: async (data: CoopFlowCfgVO) => {
    return await request.post({ url: `/datacenter/coop-flow-cfg/create`, data })
  },

  // 修改联动流程配置
  updateCoopFlowCfg: async (data: CoopFlowCfgVO) => {
    return await request.put({ url: `/datacenter/coop-flow-cfg/update`, data })
  },

  // 删除联动流程配置
  deleteCoopFlowCfg: async (id: number) => {
    return await request.delete({ url: `/datacenter/coop-flow-cfg/delete?id=` + id })
  },

  // 导出联动流程配置 Excel
  exportCoopFlowCfg: async (params) => {
    return await request.download({ url: `/datacenter/coop-flow-cfg/export-excel`, params })
  },
}