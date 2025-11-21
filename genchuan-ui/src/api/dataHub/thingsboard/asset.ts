// asset.ts
import request from '@/config/axios'

// 资产 VO
export interface AssetVO {
  id: string // 资产ID
  tenantId: string // 租户ID
  customerId: string // 客户ID
  name: string // 资产名称
  type: string // 资产类型
  label: string // 标签
  assetProfileId: string // 资产实体ID
  additionalInfo: string // 附加信息
  externalId: string // 外部ID
  version: number // 版本
  createTime: Date // 创建时间
  createdTime: Date // 创建时间
}

// 资产 API
export const AssetApi = {
  // 查询资产分页
  getAssetPage: async (params: any) => {
    return await request.get({ url: `/datacenter/thingsboard/asset/page`, params })
  },

  // 查询资产详情
  getAsset: async (id: string) => {
    return await request.get({ url: `/datacenter/thingsboard/asset/get?id=` + id })
  },

  // 新增资产
  createAsset: async (data: AssetVO) => {
    return await request.post({ url: `/datacenter/thingsboard/asset/create`, data })
  },

  // 修改资产
  updateAsset: async (data: AssetVO) => {
    return await request.put({ url: `/datacenter/thingsboard/asset/update`, data })
  },

  // 删除资产
  deleteAsset: async (id: string) => {
    return await request.delete({ url: `/datacenter/thingsboard/asset/delete?id=` + id })
  },

  // 批量删除资产
  deleteAssetList: async (ids: string[]) => {
    return await request.delete({ 
      url: `/datacenter/thingsboard/asset/delete-list`, 
      params: { ids: ids.join(',') } 
    })
  },

  // 导出资产 Excel
  exportAsset: async (params: any) => {
    return await request.download({ url: `/datacenter/thingsboard/asset/export-excel`, params })
  },
  // 获取资产列表
  getAssetList: async () => {
    return await request.get({ url: `/datacenter/thingsboard/asset/list` })
  },
}

/* ===================== 双向映射 & 下拉选项 ===================== */
export function buildDataMaps(list: AssetVO[]) {
  const name2Id: Record<string, string> = {}   // 名称 → ID
  const id2Name: Record<string, string> = {}   // ID → 名称

  list.forEach((item) => {
    const { name, id } = item
    if (name && id) {
      name2Id[name] = id
      id2Name[id] = name
    }
  })

  // 下拉选项（el-option 直接循环）
  const options = list.map((item) => ({
    label: item.name,
    value: item.id,
  }))

  return { name2Id, id2Name, options }
}

/**
 * 快捷函数：先调接口，再拿到映射结果
 * 用法：
 *   const { name2Id, id2Name, options } = await getDataMaps()
 */
export async function getDataMaps() {
  const list = await AssetApi.getAssetList()
  return buildDataMaps(list)
}