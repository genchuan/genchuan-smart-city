package cn.iocoder.yudao.module.datacenter.service.analysis.analysisasset;

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisasset.vo.AnalysisAssetPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisasset.vo.AnalysisAssetSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysisasset.AnalysisAssetDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 按资产分域分析研判统计 Service 接口
 *
 * @author 亘川智城
 */
public interface AnalysisAssetService {

    /**
     * 创建按资产分域分析研判统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAnalysisAsset(@Valid AnalysisAssetSaveReqVO createReqVO);

    /**
     * 更新按资产分域分析研判统计
     *
     * @param updateReqVO 更新信息
     */
    void updateAnalysisAsset(@Valid AnalysisAssetSaveReqVO updateReqVO);

    /**
     * 删除按资产分域分析研判统计
     *
     * @param id 编号
     */
    void deleteAnalysisAsset(Long id);

    /**
     * 获得按资产分域分析研判统计
     *
     * @param id 编号
     * @return 按资产分域分析研判统计
     */
    AnalysisAssetDO getAnalysisAsset(Long id);

    /**
     * 获得按资产分域分析研判统计分页
     *
     * @param pageReqVO 分页查询
     * @return 按资产分域分析研判统计分页
     */
    PageResult<AnalysisAssetDO> getAnalysisAssetPage(AnalysisAssetPageReqVO pageReqVO);

}