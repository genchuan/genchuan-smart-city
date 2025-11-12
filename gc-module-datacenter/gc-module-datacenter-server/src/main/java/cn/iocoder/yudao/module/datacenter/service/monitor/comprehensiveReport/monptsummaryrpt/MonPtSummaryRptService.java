package cn.iocoder.yudao.module.datacenter.service.monitor.comprehensiveReport.monptsummaryrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.monptsummaryrpt.vo.MonPtSummaryRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.monptsummaryrpt.vo.MonPtSummaryRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.comprehensiveReport.monptsummaryrpt.MonPtSummaryRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 监测点位汇总报表 Service 接口
 *
 * @author zcq
 */
public interface MonPtSummaryRptService {

    /**
     * 创建监测点位汇总报表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMonPtSummaryRpt(@Valid MonPtSummaryRptSaveReqVO createReqVO);

    /**
     * 更新监测点位汇总报表
     *
     * @param updateReqVO 更新信息
     */
    void updateMonPtSummaryRpt(@Valid MonPtSummaryRptSaveReqVO updateReqVO);

    /**
     * 删除监测点位汇总报表
     *
     * @param id 编号
     */
    void deleteMonPtSummaryRpt(Long id);

    /**
     * 获得监测点位汇总报表
     *
     * @param id 编号
     * @return 监测点位汇总报表
     */
    MonPtSummaryRptDO getMonPtSummaryRpt(Long id);

    /**
     * 获得监测点位汇总报表分页
     *
     * @param pageReqVO 分页查询
     * @return 监测点位汇总报表分页
     */
    PageResult<MonPtSummaryRptDO> getMonPtSummaryRptPage(MonPtSummaryRptPageReqVO pageReqVO);

}