package cn.iocoder.yudao.module.datacenter.service.monitor.comprehensiveReport.trendanalysisrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.trendanalysisrpt.vo.TrendAnalysisRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.trendanalysisrpt.vo.TrendAnalysisRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.comprehensiveReport.trendanalysisrpt.TrendAnalysisRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 综合趋势分析报表 Service 接口
 *
 * @author zcq
 */
public interface TrendAnalysisRptService {

    /**
     * 创建综合趋势分析报表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTrendAnalysisRpt(@Valid TrendAnalysisRptSaveReqVO createReqVO);

    /**
     * 更新综合趋势分析报表
     *
     * @param updateReqVO 更新信息
     */
    void updateTrendAnalysisRpt(@Valid TrendAnalysisRptSaveReqVO updateReqVO);

    /**
     * 删除综合趋势分析报表
     *
     * @param id 编号
     */
    void deleteTrendAnalysisRpt(Long id);

    /**
     * 获得综合趋势分析报表
     *
     * @param id 编号
     * @return 综合趋势分析报表
     */
    TrendAnalysisRptDO getTrendAnalysisRpt(Long id);

    /**
     * 获得综合趋势分析报表分页
     *
     * @param pageReqVO 分页查询
     * @return 综合趋势分析报表分页
     */
    PageResult<TrendAnalysisRptDO> getTrendAnalysisRptPage(TrendAnalysisRptPageReqVO pageReqVO);

}