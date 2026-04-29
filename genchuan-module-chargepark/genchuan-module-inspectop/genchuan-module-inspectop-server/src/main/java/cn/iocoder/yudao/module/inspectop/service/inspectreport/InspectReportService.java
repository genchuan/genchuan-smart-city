package cn.iocoder.yudao.module.inspectop.service.inspectreport;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.inspectreport.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspectreport.InspectReportDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡检上报 Service 接口
 *
 * @author zhucongquan
 */
public interface InspectReportService {

    /**
     * 创建巡检上报
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectReport(@Valid InspectReportSaveReqVO createReqVO);

    /**
     * 更新巡检上报
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectReport(@Valid InspectReportSaveReqVO updateReqVO);

    /**
     * 删除巡检上报
     *
     * @param id 编号
     */
    void deleteInspectReport(Long id);

    /**
    * 批量删除巡检上报
    *
    * @param ids 编号
    */
    void deleteInspectReportListByIds(List<Long> ids);

    /**
     * 获得巡检上报
     *
     * @param id 编号
     * @return 巡检上报
     */
    InspectReportDO getInspectReport(Long id);

    /**
     * 获得巡检上报分页
     *
     * @param pageReqVO 分页查询
     * @return 巡检上报分页
     */
    PageResult<InspectReportRespVO> getInspectReportPage(InspectReportPageReqVO pageReqVO);

    /**
     * 批量审核巡检上报
     *
     * @param batchAuditReqVO 批量审核信息
     */
    void batchAuditInspectReport(@Valid InspectReportBatchAuditReqVO batchAuditReqVO);

    /**
     * 通过巡检上报
     * 将状态改为待处置(2)，设置审核人ID和审核时间
     *
     * @param approveReqVO 通过审核信息
     */
    void approveInspectReport(@Valid InspectReportApproveReqVO approveReqVO);

    /**
     * 驳回巡检上报
     * 将状态改为已驳回(5)，设置审核人ID、审核时间和驳回理由
     *
     * @param rejectReqVO 驳回信息
     */
    void rejectInspectReport(@Valid InspectReportRejectReqVO rejectReqVO);

    /**
     * 执行巡检上报
     * 将状态改为处理中(4)，设置处置人ID和处置时间
     *
     * @param processReqVO 执行信息
     */
    void processInspectReport(@Valid InspectReportProcessReqVO processReqVO);

    /**
     * 获取巡检上报图表数据
     * 包含上报量趋势、类型分布和卡片统计数据
     *
     * @param timeRange 时间范围
     * @return 图表数据
     */
    InspectReportChartRespVO getInspectReportChartData(String[] timeRange);

}