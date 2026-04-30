package cn.iocoder.yudao.module.vehiclepass.service.passreport.cyclereport;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo.CycleReportChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo.CycleReportChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo.CycleReportCreateReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo.CycleReportCreateRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo.CycleReportPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo.CycleReportRespVO;
import jakarta.validation.Valid;
import java.util.List;

public interface CycleReportService {

    /**
     * 手动生成周期报表
     */
    CycleReportCreateRespVO createCycleReport(@Valid CycleReportCreateReqVO createReqVO);

    /**
     * 分页查询周期报表
     */
    PageResult<CycleReportRespVO> getCycleReportPage(CycleReportPageReqVO pageReqVO);

    /**
     * 获取周期报表列表（用于导出）
     */
    List<CycleReportRespVO> getCycleReportList(CycleReportPageReqVO pageReqVO);

    /**
     * 获取周期报表图表数据
     */
    CycleReportChartRespVO getChart(CycleReportChartReqVO reqVO);

}