package cn.iocoder.yudao.module.chargepark.marketop.service.cardmgmt.cardorder;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardorder.vo.CardOrderChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardorder.vo.CardOrderPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.CardOrderDO;

import java.util.List;

public interface CardOrderService {

    PageResult<CardOrderDO> getPage(CardOrderPageReqVO reqVO);

    CardOrderDO get(Long id);

    void pay(Long id);

    void activate(Long id);

    void invoice(Long id);

    void cancel(Long id);

    CardOrderChartRespVO getChart();

    List<CardOrderDO> getListByIds(List<Long> ids);

}
