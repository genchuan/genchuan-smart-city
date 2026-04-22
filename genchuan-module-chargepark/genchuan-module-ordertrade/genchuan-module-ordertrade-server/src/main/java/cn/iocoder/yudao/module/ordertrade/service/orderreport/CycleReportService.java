package cn.iocoder.yudao.module.ordertrade.service.orderreport;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.orderreport.vo.CycleReportChartReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.orderreport.vo.CycleReportChartRespVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.orderreport.vo.CycleReportCreateReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.orderreport.vo.CycleReportPageReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.orderreport.vo.CycleReportRespVO;

public interface CycleReportService {

    /**
     * 手动生成周期报表：实时聚合统计数据并持久化到 cycle_report 表。
     * 返回生成的报表记录 ID。
     */
    Long createCycleReport(CycleReportCreateReqVO createReqVO);

    /**
     * 分页查询已生成的周期报表记录（查 cycle_report 表）。
     */
    PageResult<CycleReportRespVO> getCycleReportPage(CycleReportPageReqVO pageReqVO);

    /**
     * 查看单条周期报表详情。
     */
    CycleReportRespVO getCycleReport(Long id);

    /**
     * 图表数据（基于已存储报表的统计时段，实时聚合 ECharts 数据）。
     */
    CycleReportChartRespVO getCycleReportChart(CycleReportChartReqVO chartReqVO);
}
