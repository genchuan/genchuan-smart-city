package cn.iocoder.yudao.module.evaluate.service.statreport;

import cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.statreport.vo.StatReportPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.statreport.vo.StatReportSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.statreport.StatReportDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 统计分析报 Service 接口
 *
 * @author 亘川智城
 */
public interface StatReportService {

    /**
     * 创建统计分析报
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createStatReport(@Valid StatReportSaveReqVO createReqVO);

    /**
     * 更新统计分析报
     *
     * @param updateReqVO 更新信息
     */
    void updateStatReport(@Valid StatReportSaveReqVO updateReqVO);

    /**
     * 删除统计分析报
     *
     * @param id 编号
     */
    void deleteStatReport(Long id);

    /**
     * 获得统计分析报
     *
     * @param id 编号
     * @return 统计分析报
     */
    StatReportDO getStatReport(Long id);

    /**
     * 获得统计分析报分页
     *
     * @param pageReqVO 分页查询
     * @return 统计分析报分页
     */
    PageResult<StatReportDO> getStatReportPage(StatReportPageReqVO pageReqVO);

}