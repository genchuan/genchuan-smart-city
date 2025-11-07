package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetStatReport.assetcatrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetStatReport.assetcatrpt.vo.AssetCatRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetStatReport.assetcatrpt.vo.AssetCatRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetStatReport.assetcatrpt.AssetCatRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 资产分类统计 Service 接口
 *
 * @author 亘川智城
 */
public interface AssetCatRptService {

    /**
     * 创建资产分类统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssetCatRpt(@Valid AssetCatRptSaveReqVO createReqVO);

    /**
     * 更新资产分类统计
     *
     * @param updateReqVO 更新信息
     */
    void updateAssetCatRpt(@Valid AssetCatRptSaveReqVO updateReqVO);

    /**
     * 删除资产分类统计
     *
     * @param id 编号
     */
    void deleteAssetCatRpt(Long id);

    /**
     * 获得资产分类统计
     *
     * @param id 编号
     * @return 资产分类统计
     */
    AssetCatRptDO getAssetCatRpt(Long id);

    /**
     * 获得资产分类统计分页
     *
     * @param pageReqVO 分页查询
     * @return 资产分类统计分页
     */
    PageResult<AssetCatRptDO> getAssetCatRptPage(AssetCatRptPageReqVO pageReqVO);

}