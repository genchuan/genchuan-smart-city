package cn.iocoder.yudao.module.ordertrade.service.debtcollect;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.debtcollect.CollectTrackDO;

import java.util.List;

/**
 * CollectTrack Service 接口
 * @author genchuan
 */
public interface CollectTrackService {

    Long createCollectTrack(CollectTrackSaveReqVO createReqVO);

    void updateCollectTrack(CollectTrackSaveReqVO updateReqVO);

    void deleteCollectTrack(Long id);

    void deleteCollectTrackListByIds(List<Long> ids);

    CollectTrackDO getCollectTrack(Long id);

    PageResult<CollectTrackDO> getCollectTrackPage(CollectTrackPageReqVO pageReqVO);

    CollectTrackChartRespVO getCollectTrackChart(CollectTrackChartReqVO chartReqVO);

    /** Push - 单条操作（IdReqVO） */

    void pushCollectTrack(IdReqVO reqVO);

    /** UpdateProgress - 单条操作（IdReqVO） */

    void updateProgressCollectTrack(IdReqVO reqVO);

    /** Transfer - 单条操作（CollectTrackTransferReqVO） */

    void transferCollectTrack(CollectTrackTransferReqVO reqVO);

    /** Archive - 单条操作（IdReqVO） */

    void archiveCollectTrack(IdReqVO reqVO);

    /** BatchPush - 批量操作（IdsReqVO） */

    void batchPushCollectTrack(IdsReqVO reqVO);
}
