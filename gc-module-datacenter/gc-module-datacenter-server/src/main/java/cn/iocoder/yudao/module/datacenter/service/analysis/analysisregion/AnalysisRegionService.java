package cn.iocoder.yudao.module.datacenter.service.analysis.analysisregion;

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisregion.vo.AnalysisRegionPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisregion.vo.AnalysisRegionSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysisregion.AnalysisRegionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 按行政区划分析研判统计 Service 接口
 *
 * @author 亘川智城
 */
public interface AnalysisRegionService {

    /**
     * 创建按行政区划分析研判统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAnalysisRegion(@Valid AnalysisRegionSaveReqVO createReqVO);

    /**
     * 更新按行政区划分析研判统计
     *
     * @param updateReqVO 更新信息
     */
    void updateAnalysisRegion(@Valid AnalysisRegionSaveReqVO updateReqVO);

    /**
     * 删除按行政区划分析研判统计
     *
     * @param id 编号
     */
    void deleteAnalysisRegion(Long id);

    /**
     * 获得按行政区划分析研判统计
     *
     * @param id 编号
     * @return 按行政区划分析研判统计
     */
    AnalysisRegionDO getAnalysisRegion(Long id);

    /**
     * 获得按行政区划分析研判统计分页
     *
     * @param pageReqVO 分页查询
     * @return 按行政区划分析研判统计分页
     */
    PageResult<AnalysisRegionDO> getAnalysisRegionPage(AnalysisRegionPageReqVO pageReqVO);

}