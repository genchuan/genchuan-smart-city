package cn.iocoder.yudao.module.chargepark.marketop.service.decisionanalysis.cyclereport;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.cyclereport.vo.CycleReportChartReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.cyclereport.vo.CycleReportChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.cyclereport.vo.CycleReportCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.cyclereport.vo.CycleReportPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.decisionanalysis.CycleReportDO;
import jakarta.validation.Valid;

import java.util.List;

public interface CycleReportService {

    PageResult<CycleReportDO> getPage(CycleReportPageReqVO reqVO);

    CycleReportDO get(Long id);

    Long create(@Valid CycleReportCreateReqVO reqVO);

    CycleReportChartRespVO getChart(CycleReportChartReqVO reqVO);

    List<CycleReportDO> getList(CycleReportPageReqVO reqVO);

    List<CycleReportDO> getListByIds(List<Long> ids);

    void incrementExportCount(List<Long> ids);

}
