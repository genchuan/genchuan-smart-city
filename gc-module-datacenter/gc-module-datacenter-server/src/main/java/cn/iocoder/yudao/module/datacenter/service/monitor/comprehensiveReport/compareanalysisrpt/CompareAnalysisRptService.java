package cn.iocoder.yudao.module.datacenter.service.monitor.comprehensiveReport.compareanalysisrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.compareanalysisrpt.vo.CompareAnalysisRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.compareanalysisrpt.vo.CompareAnalysisRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.comprehensiveReport.compareanalysisrpt.CompareAnalysisRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 综合对比分析报表 Service 接口
 *
 * @author zcq
 */
public interface CompareAnalysisRptService {

    /**
     * 创建综合对比分析报表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCompareAnalysisRpt(@Valid CompareAnalysisRptSaveReqVO createReqVO);

    /**
     * 更新综合对比分析报表
     *
     * @param updateReqVO 更新信息
     */
    void updateCompareAnalysisRpt(@Valid CompareAnalysisRptSaveReqVO updateReqVO);

    /**
     * 删除综合对比分析报表
     *
     * @param id 编号
     */
    void deleteCompareAnalysisRpt(Long id);

    /**
     * 获得综合对比分析报表
     *
     * @param id 编号
     * @return 综合对比分析报表
     */
    CompareAnalysisRptDO getCompareAnalysisRpt(Long id);

    /**
     * 获得综合对比分析报表分页
     *
     * @param pageReqVO 分页查询
     * @return 综合对比分析报表分页
     */
    PageResult<CompareAnalysisRptDO> getCompareAnalysisRptPage(CompareAnalysisRptPageReqVO pageReqVO);

}