package cn.iocoder.yudao.module.stationresource.service.stationresource.stationreport;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo.*;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo.extraops.StationOpHistoryReportCreateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo.extraops.StationOpReportBatchBackReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo.ops.*;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationreport.StationReportDO;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

/**
 * 场站资源报表 Service 接口
 *
 * @author 亘川智城
 */
public interface StationReportService {



    /**
     * 获得场站资源报表
     *
     * @param id 编号
     * @return 报表
     */
    StationReportDO getReport(Long id);


    PageResult<StationReportDO> getPage(StationOpReportPageReqVO pageReqVO);

    Long addReport(StationOpReportCreateReqVO reqVO);

    StationOpReportChartRespVO getReportChartData(StationOpReportChartReqVO reqVO);

    Long addHistoryReport(StationOpHistoryReportCreateReqVO reqVO);

    List<Long> addBatchBackReport(StationOpReportBatchBackReqVO reqVO);

    // ====================== 钻取方法（卡片指标 → 明细数据） ======================
    /** 总片区数 → 钻取片区列表 */
    DrillDownRespVO drillDownArea(DrillDownReqVO reqVO);

    /** 总站场数 → 钻取场站列表 */
    DrillDownRespVO drillDownStation(DrillDownReqVO reqVO);

    /** 正常运营数 → 钻取运营中场站列表 */
    DrillDownRespVO drillDownNormalStation(DrillDownReqVO reqVO);

    /** 总车位数 → 钻取车位列表 */
    DrillDownRespVO drillDownSpace(DrillDownReqVO reqVO);

    /** 可用车位数 → 钻取空闲车位列表 */
    DrillDownRespVO drillDownAvailableSpace(DrillDownReqVO reqVO);

    /** 生效规则数 → 钻取规则列表 */
    DrillDownRespVO drillDownEffectiveRule(DrillDownReqVO reqVO);

    /** 订单量/营收 → 钻取充停联动订单列表 */
    DrillDownRespVO drillDownOrder(DrillDownReqVO reqVO);

    /** 追缴完成率 → 钻取追缴记录列表 */
    DrillDownRespVO drillDownDebtExpand(DrillDownReqVO reqVO);

    /** 押金订单量 → 钻取押金计划列表 */
    DrillDownRespVO drillDownDepositPlan(DrillDownReqVO reqVO);
}
