package cn.iocoder.yudao.module.chargepark.marketop.service.exchangemgmt.exchangeorder;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder.vo.ExchangeOrderChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder.vo.ExchangeOrderDeliverReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder.vo.ExchangeOrderPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.exchangemgmt.ExchangeOrderDO;
import jakarta.validation.Valid;

public interface ExchangeOrderService {

    PageResult<ExchangeOrderDO> getPage(ExchangeOrderPageReqVO reqVO);

    ExchangeOrderDO get(Long id);

    void pay(Long id);

    void deliver(@Valid ExchangeOrderDeliverReqVO reqVO);

    void cancel(Long id);

    ExchangeOrderChartRespVO getChart();

}
