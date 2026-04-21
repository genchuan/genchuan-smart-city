package cn.iocoder.yudao.module.ordertrade.service.splitsetttle;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.splitsetttle.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.splitsetttle.SettleBillDO;

import java.util.List;

public interface SettleBillService {

    Long createSettleBill(SettleBillSaveReqVO createReqVO);

    void updateSettleBill(SettleBillSaveReqVO updateReqVO);

    void deleteSettleBill(Long id);

    SettleBillDO getSettleBill(Long id);

    PageResult<SettleBillDO> getSettleBillPage(SettleBillPageReqVO pageReqVO);

    void approveSettleBill(IdReqVO reqVO);

    void rejectSettleBill(IdReqVO reqVO);

    void settleSettleBill(IdReqVO reqVO);

    void regenerateSettleBill(IdReqVO reqVO);

    void batchAuditSettleBill(List<Long> ids);

    SettleBillChartRespVO getSettleBillChart(SettleBillChartReqVO chartReqVO);
}
