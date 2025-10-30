package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetDataMng.assetspatialdata;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetspatialdata.vo.AssetSpatialDataImportExcelVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetspatialdata.vo.AssetSpatialDataImportRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetspatialdata.vo.AssetSpatialDataPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetspatialdata.vo.AssetSpatialDataSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetspatialdata.AssetSpatialDataDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import java.util.List;

/**
 * 资产空间数据 Service 接口
 *
 * @author 亘川智城
 */
public interface AssetSpatialDataService {

    /**
     * 创建资产空间数据
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssetSpatialData(@Valid AssetSpatialDataSaveReqVO createReqVO);

    /**
     * 更新资产空间数据
     *
     * @param updateReqVO 更新信息
     */
    void updateAssetSpatialData(@Valid AssetSpatialDataSaveReqVO updateReqVO);

    /**
     * 删除资产空间数据
     *
     * @param id 编号
     */
    void deleteAssetSpatialData(Long id);

    /**
     * 获得资产空间数据
     *
     * @param id 编号
     * @return 资产空间数据
     */
    AssetSpatialDataDO getAssetSpatialData(Long id);

    /**
     * 获得资产空间数据分页
     *
     * @param pageReqVO 分页查询
     * @return 资产空间数据分页
     */
    PageResult<AssetSpatialDataDO> getAssetSpatialDataPage(AssetSpatialDataPageReqVO pageReqVO);

    /**
     * 导入资产空间数据信息
     * @param importAssetSpatialData
     * @param updateSupport
     * @return
     */
    AssetSpatialDataImportRespVO importAssetSpatialDataList(List<AssetSpatialDataImportExcelVO> importAssetSpatialData, boolean updateSupport);
}