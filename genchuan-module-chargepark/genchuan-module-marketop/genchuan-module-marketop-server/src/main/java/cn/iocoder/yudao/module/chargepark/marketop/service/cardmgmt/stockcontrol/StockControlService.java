package cn.iocoder.yudao.module.chargepark.marketop.service.cardmgmt.stockcontrol;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo.StockControlAllocateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo.StockControlChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo.StockControlPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo.StockControlRestockReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.StockControlDO;
import jakarta.validation.Valid;

public interface StockControlService {

    PageResult<StockControlDO> getPage(StockControlPageReqVO reqVO);

    StockControlDO get(Long id);

    void restock(@Valid StockControlRestockReqVO reqVO);

    void warn(Long id);

    void allocate(@Valid StockControlAllocateReqVO reqVO);

    StockControlChartRespVO getChart(String timeRange);

}
