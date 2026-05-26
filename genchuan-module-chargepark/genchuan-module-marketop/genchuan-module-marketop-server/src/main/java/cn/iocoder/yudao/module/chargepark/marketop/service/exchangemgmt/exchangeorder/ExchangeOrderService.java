package cn.iocoder.yudao.module.chargepark.marketop.service.exchangemgmt.exchangeorder;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder.vo.ExchangeOrderChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder.vo.ExchangeOrderDeliverReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder.vo.ExchangeOrderPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder.vo.ExchangeOrderRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.exchangemgmt.ExchangeOrderDO;
import jakarta.validation.Valid;

import java.util.List;

public interface ExchangeOrderService {

    PageResult<ExchangeOrderDO> getPage(ExchangeOrderPageReqVO reqVO);

    ExchangeOrderDO get(Long id);

    void pay(Long id);

    void deliver(@Valid ExchangeOrderDeliverReqVO reqVO);

    void cancel(Long id);

    ExchangeOrderChartRespVO getChart();

    List<ExchangeOrderDO> getListByIds(List<Long> ids);

    PageResult<ExchangeOrderRespVO> getPageWithJoin(ExchangeOrderPageReqVO reqVO);

    ExchangeOrderRespVO getWithJoin(Long id);

    List<ExchangeOrderRespVO> getListByIdsWithJoin(List<Long> ids);

}
