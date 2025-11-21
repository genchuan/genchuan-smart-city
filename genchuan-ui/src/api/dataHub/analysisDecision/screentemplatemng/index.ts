import request from '@/config/axios'

// 大屏模板管理 VO
export interface ScreenTemplateMngVO {
  id: number // 主键ID
  screenTemplateId: string // 模板ID
  templateName: string // 模板名称
  screenLayout: string // 大屏布局
  dataSourceCfg: string // 数据来源配置
  componentCfg: string // 组件配置
  refreshFreq: number // 刷新频率
  templateStatus: string // 模板状态
  createUser: string // 创建人
  updateUser: string // 更新人
  templatePreview: string // 模板预览图
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2挥中心/展厅）
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 大屏模板管理 API
export const ScreenTemplateMngApi = {
  // 查询大屏模板管理分页
  getScreenTemplateMngPage: async (params: any) => {
    return await request.get({ url: `/datacenter/screen-template-mng/page`, params })
  },

  // 查询大屏模板管理详情
  getScreenTemplateMng: async (id: number) => {
    return await request.get({ url: `/datacenter/screen-template-mng/get?id=` + id })
  },

  // 新增大屏模板管理
  createScreenTemplateMng: async (data: ScreenTemplateMngVO) => {
    return await request.post({ url: `/datacenter/screen-template-mng/create`, data })
  },

  // 修改大屏模板管理
  updateScreenTemplateMng: async (data: ScreenTemplateMngVO) => {
    return await request.put({ url: `/datacenter/screen-template-mng/update`, data })
  },

  // 删除大屏模板管理
  deleteScreenTemplateMng: async (id: number) => {
    return await request.delete({ url: `/datacenter/screen-template-mng/delete?id=` + id })
  },

  // 导出大屏模板管理 Excel
  exportScreenTemplateMng: async (params) => {
    return await request.download({ url: `/datacenter/screen-template-mng/export-excel`, params })
  },
}