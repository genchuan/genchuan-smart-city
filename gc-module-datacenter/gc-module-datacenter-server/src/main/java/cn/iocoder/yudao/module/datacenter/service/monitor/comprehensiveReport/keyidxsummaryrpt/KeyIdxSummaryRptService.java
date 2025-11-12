package cn.iocoder.yudao.module.datacenter.service.monitor.comprehensiveReport.keyidxsummaryrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.keyidxsummaryrpt.vo.KeyIdxSummaryRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.keyidxsummaryrpt.vo.KeyIdxSummaryRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.comprehensiveReport.keyidxsummaryrpt.KeyIdxSummaryRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 关键指标汇总报表 Service 接口
 *
 * @author zcq
 */
public interface KeyIdxSummaryRptService {

    /**
     * 创建关键指标汇总报表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createKeyIdxSummaryRpt(@Valid KeyIdxSummaryRptSaveReqVO createReqVO);

    /**
     * 更新关键指标汇总报表
     *
     * @param updateReqVO 更新信息
     */
    void updateKeyIdxSummaryRpt(@Valid KeyIdxSummaryRptSaveReqVO updateReqVO);

    /**
     * 删除关键指标汇总报表
     *
     * @param id 编号
     */
    void deleteKeyIdxSummaryRpt(Long id);

    /**
     * 获得关键指标汇总报表
     *
     * @param id 编号
     * @return 关键指标汇总报表
     */
    KeyIdxSummaryRptDO getKeyIdxSummaryRpt(Long id);

    /**
     * 获得关键指标汇总报表分页
     *
     * @param pageReqVO 分页查询
     * @return 关键指标汇总报表分页
     */
    PageResult<KeyIdxSummaryRptDO> getKeyIdxSummaryRptPage(KeyIdxSummaryRptPageReqVO pageReqVO);

}