package cn.iocoder.yudao.module.accessmgmt.service.accessreport.cyclereport;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.accessreport.cyclereport.vo.*;

import java.util.List;

/**
 * 通行周期报表 Service 接口
 *
 * @author 亘川智城
 */
public interface AccessCycleReportService {

    /**
     * 获得通行周期报表分页
     */
    PageResult<AccessCycleReportRespVO> getAccessCycleReportPage(AccessCycleReportPageReqVO pageReqVO);

    /**
     * 获得通行周期报表详情
     */
    AccessCycleReportRespVO getAccessCycleReport(Long id);

    /**
     * 生成报表 —— 根据周期类型自动计算时间范围，生成报表名称，模拟统计数据并入库
     */
    AccessCycleReportGenerateRespVO generateAccessCycleReport(AccessCycleReportGenerateReqVO reqVO);

    /**
     * 获得通行周期报表列表（导出用，全量不分页）
     */
    List<AccessCycleReportRespVO> getAccessCycleReportList(AccessCycleReportPageReqVO pageReqVO);

    /**
     * 通行周期报表态势 —— 卡片数据 + 多维度趋势与分布图表
     */
    AccessCycleReportChartRespVO getAccessCycleReportChart(Long id);

}
