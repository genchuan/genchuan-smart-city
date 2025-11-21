import request from '@/config/axios'

// 资产数据管理 VO
export interface AssetDataMngVO {
  id: number // 主键ID
  assetDataId: string // 资产数据ID
  relAssetCatId: string // 关联资产分类ID
  relAssetCatName: string // 关联资产分类名称
  assetCode: string // 资产编码
  assetName: string // 资产名称
  assetStatus: string // 资产状态
  installTime: Date // 安装时间
  serviceLife: number // 使用年限
  assetManager: string // 资产负责人
  assetManagerTel: string // 资产负责人电话
  assetDesc: string // 资产描述
  createUser: string // 创建人
  createdTime: Date // 创建时间
  updateUser: string // 更新人
  updatedTime: Date // 更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 资产数据管理 API
export const AssetDataMngApi = {
  // 查询资产数据管理分页
  getAssetDataMngPage: async (params: any) => {
    return await request.get({ url: `/datacenter/asset-data-mng/page`, params })
  },

  // 查询资产数据管理详情
  getAssetDataMng: async (id: number) => {
    return await request.get({ url: `/datacenter/asset-data-mng/get?id=` + id })
  },

  // 新增资产数据管理
  createAssetDataMng: async (data: AssetDataMngVO) => {
    return await request.post({ url: `/datacenter/asset-data-mng/create`, data })
  },

  // 修改资产数据管理
  updateAssetDataMng: async (data: AssetDataMngVO) => {
    return await request.put({ url: `/datacenter/asset-data-mng/update`, data })
  },

  // 删除资产数据管理
  deleteAssetDataMng: async (id: number) => {
    return await request.delete({ url: `/datacenter/asset-data-mng/delete?id=` + id })
  },

  // 导出资产数据管理 Excel
  exportAssetDataMng: async (params) => {
    return await request.download({ url: `/datacenter/asset-data-mng/export-excel`, params })
  },
  // 获取启用的资产数据列表
  getEnableAssetDataList: async () => {
    return await request.get({ url: `/datacenter/asset-data-mng/enabled-list` })
  },
  // 获取
}

/* ===================== 双向映射 & 下拉选项 ===================== */
export function buildDataMaps(list: AssetDataMngVO[]) {
  const name2Id: Record<string, string> = {}   // 名称 → ID
  const id2Name: Record<string, string> = {}   // ID → 名称

  list.forEach((item) => {
    const { assetName, assetDataId } = item
    if (assetName && assetDataId) {
      name2Id[assetName] = assetDataId
      id2Name[assetDataId] = assetName
    }
  })

  // 下拉选项（el-option 直接循环）
  const options = list.map((item) => ({
    label: item.assetName,
    value: item.assetDataId,
  }))

  return { name2Id, id2Name, options }
}

/**
 * 快捷函数：先调接口，再拿到映射结果
 * 用法：
 *   const { name2Id, id2Name, options } = await getDataMaps()
 */
export async function getDataMaps() {
  const list = await AssetDataMngApi.getEnableAssetDataList()
  return buildDataMaps(list)
}

