import request from '@/config/axios'

// 资产分类管理 VO
export interface AssetCatMngVO {
  id: number // 主键ID
  assetCatId: string // 资产分类ID
  relCatRuleId: string // 关联分类规则ID
  assetCatCode: string // 资产分类编码
  assetCatName: string // 资产分类名称
  catLevel: string // 分类层级
  parentCatId: string // 上级分类ID
  parentCatName: string // 上级分类名称
  catDesc: string // 分类说明
  enableStatus: string // 启用状态
  createUser: string // 创建人
  createdTime: Date // 创建时间
  updateUser: string // 更新人
  updatedTime: Date // 更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 资产分类管理 API
export const AssetCatMngApi = {
  // 查询资产分类管理分页
  getAssetCatMngPage: async (params: any) => {
    return await request.get({ url: `/datacenter/asset-cat-mng/page`, params })
  },

  // 查询资产分类管理详情
  getAssetCatMng: async (id: number) => {
    return await request.get({ url: `/datacenter/asset-cat-mng/get?id=` + id })
  },

  // 新增资产分类管理
  createAssetCatMng: async (data: AssetCatMngVO) => {
    return await request.post({ url: `/datacenter/asset-cat-mng/create`, data })
  },

  // 修改资产分类管理
  updateAssetCatMng: async (data: AssetCatMngVO) => {
    return await request.put({ url: `/datacenter/asset-cat-mng/update`, data })
  },

  // 删除资产分类管理
  deleteAssetCatMng: async (id: number) => {
    return await request.delete({ url: `/datacenter/asset-cat-mng/delete?id=` + id })
  },

  // 导出资产分类管理 Excel
  exportAssetCatMng: async (params) => {
    return await request.download({ url: `/datacenter/asset-cat-mng/export-excel`, params })
  },
  // 导入资产分类管理 Excel
  importAssetCatMng: async (data) => {
    return await request.upload({ url: `/datacenter/asset-cat-mng/import`, data })
  },
  // 下载资产分类管理 Excel 模板
  importAssetCatMngTemplate: async () => {
    return await request.download({ url: `/datacenter/asset-cat-mng/import-template` })
  },
 // 获取启用的资产分类管理列表（用于字典）
  getEnabledAssetCatMngList: async () => {
    return await request.get({ url: `/datacenter/asset-cat-mng/enabled-list` })
  },
  // 获取资产分类管理列表数据
  getAssetCatMngList: async () => {
    return await request.get({ url: `/datacenter/asset-cat-mng/list` })
  },
}


/* ===================== 双向映射 & 下拉选项 ===================== */
export function buildCatMaps(list: AssetCatMngVO[]) {
  const name2Id: Record<string, string> = {}   // 名称 → ID
  const id2Name: Record<string, string> = {}   // ID → 名称

  list.forEach((item) => {
    const { assetCatName, assetCatId } = item
    if (assetCatName && assetCatId) {
      name2Id[assetCatName] = assetCatId
      id2Name[assetCatId] = assetCatName
    }
  })

  // 下拉选项（el-option 直接循环）
  const options = list.map((item) => ({
    label: item.assetCatName,
    value: item.assetCatId,
  }))

  return { name2Id, id2Name, options }
}

/**
 * 快捷函数：先调接口，再拿到映射结果
 * 用法：
 *   const { name2Id, id2Name, options } = await getCatMaps()
 */
export async function getCatMaps() {
  const list = await AssetCatMngApi.getEnabledAssetCatMngList()
  return buildCatMaps(list)
}

// 新增：根据层级获取上级分类选项的函数
export async function getParentOptionsByLevel(currentLevel: string) {
  const allCategories = await AssetCatMngApi.getEnabledAssetCatMngList()
  
  const parentLevelMap: Record<string, string> = {
    '2': '1', // 层级2的上级是层级1
    '3': '2'  // 层级3的上级是层级2
  }
  
  const parentLevel = parentLevelMap[currentLevel]
  
  if (parentLevel) {
    // 修正：筛选对应层级的分类，而不是排除
    const parentCategories = allCategories.filter((cat: AssetCatMngVO) => 
      String(cat.catLevel) === String(parentLevel)
    )
    return buildCatMaps(parentCategories)
  }
  
  return { name2Id: {}, id2Name: {}, options: [] }
}