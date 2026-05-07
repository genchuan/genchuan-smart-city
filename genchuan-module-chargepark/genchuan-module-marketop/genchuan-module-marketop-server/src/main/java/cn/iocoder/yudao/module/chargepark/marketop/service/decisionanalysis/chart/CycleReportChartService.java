package cn.iocoder.yudao.module.chargepark.marketop.service.decisionanalysis.chart;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.chart.vo.CycleReportChartBarDrillReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.chart.vo.CycleReportChartLineDrillReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.chart.vo.CycleReportChartPieDrillReqVO;

import java.util.Map;

public interface CycleReportChartService {

    PageResult<Map<String, Object>> lineDrill(CycleReportChartLineDrillReqVO reqVO);

    PageResult<Map<String, Object>> barDrill(CycleReportChartBarDrillReqVO reqVO);

    PageResult<Map<String, Object>> pieDrill(CycleReportChartPieDrillReqVO reqVO);

}
