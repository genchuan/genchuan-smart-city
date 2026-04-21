package cn.iocoder.yudao.module.ordertrade.service.splitsetttle;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.splitsetttle.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.splitsetttle.SplitRateDO;

public interface SplitRateService {

    Long createSplitRate(SplitRateSaveReqVO createReqVO);

    void updateSplitRate(SplitRateSaveReqVO updateReqVO);

    void deleteSplitRate(Long id);

    SplitRateDO getSplitRate(Long id);

    PageResult<SplitRateDO> getSplitRatePage(SplitRatePageReqVO pageReqVO);

    void enableSplitRate(Long id);

    void disableSplitRate(Long id);

    SplitRateChartRespVO getSplitRateChart(SplitRateChartReqVO chartReqVO);
}
