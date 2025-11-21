import request from '@/config/axios'

// 培训资料 VO
export interface TrainMatVO {
  id: number // 主键ID
  trainMatId: string // 资料ID
  matName: string // 资料名称
  trainTypeCode: string // 培训类型代码
  trainTypeName: string // 培训类型名称
  trainObject: string // 培训对象
  trainEndure: string // 培训时长
  matFormat: string // 资料格式
  matSize: string // 资料大小
  matAbstract: string // 资料摘要
  matFileId: string // 资料文件ID
  matFilePath: string // 资料文件路径
  matStatus: string // 资料状态
  downloadCount: number // 下载次数
  createUser: string // 创建人
  updateUser: string // 更新人
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 培训资料 API
export const TrainMatApi = {
  // 查询培训资料分页
  getTrainMatPage: async (params: any) => {
    return await request.get({ url: `/datacenter/train-mat/page`, params })
  },

  // 查询培训资料详情
  getTrainMat: async (id: number) => {
    return await request.get({ url: `/datacenter/train-mat/get?id=` + id })
  },

  // 新增培训资料
  createTrainMat: async (data: TrainMatVO) => {
    return await request.post({ url: `/datacenter/train-mat/create`, data })
  },

  // 修改培训资料
  updateTrainMat: async (data: TrainMatVO) => {
    return await request.put({ url: `/datacenter/train-mat/update`, data })
  },

  // 删除培训资料
  deleteTrainMat: async (id: number) => {
    return await request.delete({ url: `/datacenter/train-mat/delete?id=` + id })
  },

  // 导出培训资料 Excel
  exportTrainMat: async (params) => {
    return await request.download({ url: `/datacenter/train-mat/export-excel`, params })
  },
}