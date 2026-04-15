package cn.iocoder.yudao.module.ordertrade.service.ordermgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt.AbnormalOrderDO;

import java.util.List;

/**
 * AbnormalOrder Service 接口
 * @author genchuan
 */
public interface AbnormalOrderService {

    Long createAbnormalOrder(AbnormalOrderSaveReqVO createReqVO);

    void updateAbnormalOrder(AbnormalOrderSaveReqVO updateReqVO);

    void deleteAbnormalOrder(Long id);

    void deleteAbnormalOrderListByIds(List<Long> ids);

    AbnormalOrderDO getAbnormalOrder(Long id);

    PageResult<AbnormalOrderDO> getAbnormalOrderPage(AbnormalOrderPageReqVO pageReqVO);

    AbnormalOrderChartRespVO getAbnormalOrderChart(AbnormalOrderChartReqVO chartReqVO);

    /** Check - 单条操作（IdReqVO） */

    void checkAbnormalOrder(IdReqVO reqVO);

    /** Ignore - 单条操作（IdReqVO） */

    void ignoreAbnormalOrder(IdReqVO reqVO);

    /** UpdateProgress - 单条操作（IdReqVO） */

    void updateProgressAbnormalOrder(IdReqVO reqVO);

    /** BatchProcess - 批量操作（IdsReqVO） */

    void batchProcessAbnormalOrder(IdsReqVO reqVO);
}
