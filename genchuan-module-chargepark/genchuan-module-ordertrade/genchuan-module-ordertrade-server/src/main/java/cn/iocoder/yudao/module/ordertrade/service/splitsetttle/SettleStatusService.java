package cn.iocoder.yudao.module.ordertrade.service.splitsetttle;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.splitsetttle.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.splitsetttle.SettleStatusDO;

public interface SettleStatusService {

    Long createSettleStatus(SettleStatusSaveReqVO createReqVO);

    void updateSettleStatus(SettleStatusSaveReqVO updateReqVO);

    void deleteSettleStatus(Long id);

    SettleStatusDO getSettleStatus(Long id);

    PageResult<SettleStatusDO> getSettleStatusPage(SettleStatusPageReqVO pageReqVO);

    void checkSettleStatus(IdReqVO reqVO);

    SettleStatusChartRespVO getSettleStatusChart(SettleStatusChartReqVO chartReqVO);
}
