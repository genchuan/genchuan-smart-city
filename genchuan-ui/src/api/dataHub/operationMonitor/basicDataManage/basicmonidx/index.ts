import request from '@/config/axios'

// 基础监测指标 VO
export interface BasicMonIdxVO {
  id: number // 主键ID
  idxId: string // 指标ID
  idxName: string // 指标名称
  idxCode: string // 指标编码
  idxUnit: string // 指标单位
  dataType: string // 数据类型
  dataLength: string // 数据长度
  relPtType: string // 关联点位类型
  warnThresholdMax: string // 预警阈值上限
  warnThresholdMin: string // 预警阈值下限
  idxDesc: string // 指标说明
  idxStatus: boolean // 指标状态
  createTimeSys: Date // 创建时间
  updateTimeSys: Date // 更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 基础监测指标 API
export const BasicMonIdxApi = {
  // 查询基础监测指标分页
  getBasicMonIdxPage: async (params: any) => {
    return await request.get({ url: `/datacenter/basic-mon-idx/page`, params })
  },

  // 查询基础监测指标详情
  getBasicMonIdx: async (id: number) => {
    return await request.get({ url: `/datacenter/basic-mon-idx/get?id=` + id })
  },

  // 新增基础监测指标
  createBasicMonIdx: async (data: BasicMonIdxVO) => {
    return await request.post({ url: `/datacenter/basic-mon-idx/create`, data })
  },

  // 修改基础监测指标
  updateBasicMonIdx: async (data: BasicMonIdxVO) => {
    return await request.put({ url: `/datacenter/basic-mon-idx/update`, data })
  },

  // 删除基础监测指标
  deleteBasicMonIdx: async (id: number) => {
    return await request.delete({ url: `/datacenter/basic-mon-idx/delete?id=` + id })
  },

  // 导出基础监测指标 Excel
  exportBasicMonIdx: async (params) => {
    return await request.download({ url: `/datacenter/basic-mon-idx/export-excel`, params })
  }
}
