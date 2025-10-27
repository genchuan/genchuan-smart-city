package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetStatReport.assetstatusrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetStatReport.assetstatusrpt.vo.AssetStatusRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetStatReport.assetstatusrpt.vo.AssetStatusRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetStatReport.assetstatusrpt.AssetStatusRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 资产状态统计 Service 接口
 *
 * @author 亘川智城
 */
public interface AssetStatusRptService {

    /**
     * 创建资产状态统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssetStatusRpt(@Valid AssetStatusRptSaveReqVO createReqVO);

    /**
     * 更新资产状态统计
     *
     * @param updateReqVO 更新信息
     */
    void updateAssetStatusRpt(@Valid AssetStatusRptSaveReqVO updateReqVO);

    /**
     * 删除资产状态统计
     *
     * @param id 编号
     */
    void deleteAssetStatusRpt(Long id);

    /**
     * 获得资产状态统计
     *
     * @param id 编号
     * @return 资产状态统计
     */
    AssetStatusRptDO getAssetStatusRpt(Long id);

    /**
     * 获得资产状态统计分页
     *
     * @param pageReqVO 分页查询
     * @return 资产状态统计分页
     */
    PageResult<AssetStatusRptDO> getAssetStatusRptPage(AssetStatusRptPageReqVO pageReqVO);

}