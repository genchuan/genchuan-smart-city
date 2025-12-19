import request from '@/config/axios'

// 统一行政区划配置表 VO
export interface AreaVO {
  id: number // 主键ID
  parentId: number // 上级行政区划ID（0表示根节点）
  fullCode: string // 完整行政区划代码（12位）
  shortCode: string // 短代码（省/市/县6位，乡镇/社区3位）
  name: string // 行政区划名称
  level: number // 层级：1-省级 2-市级 3-县级 4-乡镇 5-社区
  areaType: string // 类型（街道/镇/乡/社区/村）
  effectiveTime: Date // 生效时间
  invalidTime: Date // 失效时间
  boundary: string // 社区边界坐标（2000坐标系）
  remark: string // 备注
  extCat1: string // 分类扩展字段1 存放应用场景分类代码sceneCatCode
  extCat2: string // 分类扩展字段2 存放应用场景分类名称sceneCatName
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 统一行政区划配置表（树形结构） API
export const AreaApi = {
  // 查询统一行政区划配置表（树形结构）分页
  getAreaPage: async (params: any) => {
    return await request.get({ url: `/datacenter/area/page`, params })
  },

  // 查询统一行政区划配置表（树形结构）详情
  getArea: async (id: number) => {
    return await request.get({ url: `/datacenter/area/get?id=` + id })
  },

  // 新增统一行政区划配置表（树形结构）
  createArea: async (data: AreaVO) => {
    return await request.post({ url: `/datacenter/area/create`, data })
  },

  // 修改统一行政区划配置表（树形结构）
  updateArea: async (data: AreaVO) => {
    return await request.put({ url: `/datacenter/area/update`, data })
  },

  // 删除统一行政区划配置表（树形结构）
  deleteArea: async (id: number) => {
    return await request.delete({ url: `/datacenter/area/delete?id=` + id })
  },

  // 导出统一行政区划配置表（树形结构） Excel
  exportArea: async (params) => {
    return await request.download({ url: `/datacenter/area/export-excel`, params })
  },

  // 获取指定父节点的子树
  getTreeByParent: async (parentId: number = 0) => {
    return await request.get({ url: `/datacenter/area/tree/${parentId}` })
  },

  //获得完整的行政区划树
  getTree: async () => {
    return await request.get({ url: `/datacenter/area/tree` })
  },

  //导入
  importArea: async (params) => {
    return await request.post({ url: `/datacenter/area/import`, params })
  },

  getTakeEffect: async () => {
    return await request.get({ url: `/datacenter/area/dropdown` })
  }
}
