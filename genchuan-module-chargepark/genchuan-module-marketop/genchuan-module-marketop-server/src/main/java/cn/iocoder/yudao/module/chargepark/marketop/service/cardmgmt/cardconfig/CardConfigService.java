package cn.iocoder.yudao.module.chargepark.marketop.service.cardmgmt.cardconfig;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardconfig.vo.CardConfigChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardconfig.vo.CardConfigCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardconfig.vo.CardConfigPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardconfig.vo.CardConfigUpdateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.CardConfigDO;
import jakarta.validation.Valid;

public interface CardConfigService {

    PageResult<CardConfigDO> getPage(CardConfigPageReqVO reqVO);

    CardConfigDO get(Long id);

    Long create(@Valid CardConfigCreateReqVO reqVO);

    void update(@Valid CardConfigUpdateReqVO reqVO);

    void enable(Long id);

    void disable(Long id);

    CardConfigChartRespVO getChart(String timeRange);

}
