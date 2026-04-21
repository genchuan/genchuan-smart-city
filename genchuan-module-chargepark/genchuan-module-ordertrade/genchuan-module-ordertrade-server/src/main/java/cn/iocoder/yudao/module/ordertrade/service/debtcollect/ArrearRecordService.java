package cn.iocoder.yudao.module.ordertrade.service.debtcollect;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.debtcollect.ArrearRecordDO;

import java.util.List;

/**
 * ArrearRecord Service 接口
 * @author genchuan
 */
public interface ArrearRecordService {

    Long createArrearRecord(ArrearRecordSaveReqVO createReqVO);

    void updateArrearRecord(ArrearRecordSaveReqVO updateReqVO);

    void deleteArrearRecord(Long id);

    void deleteArrearRecordListByIds(List<Long> ids);

    ArrearRecordDO getArrearRecord(Long id);

    PageResult<ArrearRecordDO> getArrearRecordPage(ArrearRecordPageReqVO pageReqVO);

    ArrearRecordChartRespVO getArrearRecordChart(ArrearRecordChartReqVO chartReqVO);

    /** Remind - 单条操作（IdReqVO） */

    void remindArrearRecord(IdReqVO reqVO);
}
