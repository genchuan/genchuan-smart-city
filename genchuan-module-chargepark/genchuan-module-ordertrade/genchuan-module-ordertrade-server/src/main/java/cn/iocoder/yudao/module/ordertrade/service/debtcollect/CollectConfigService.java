package cn.iocoder.yudao.module.ordertrade.service.debtcollect;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.debtcollect.CollectConfigDO;

import java.util.List;

/**
 * CollectConfig Service 接口
 * @author genchuan
 */
public interface CollectConfigService {

    Long createCollectConfig(CollectConfigSaveReqVO createReqVO);

    void updateCollectConfig(CollectConfigSaveReqVO updateReqVO);

    void deleteCollectConfig(Long id);

    void deleteCollectConfigListByIds(List<Long> ids);

    CollectConfigDO getCollectConfig(Long id);

    PageResult<CollectConfigDO> getCollectConfigPage(CollectConfigPageReqVO pageReqVO);

    CollectConfigChartRespVO getCollectConfigChart(CollectConfigChartReqVO chartReqVO);

    /** Enable - 单条操作（IdReqVO） */

    void enableCollectConfig(IdReqVO reqVO);

    /** Disable - 单条操作（IdReqVO） */

    void disableCollectConfig(IdReqVO reqVO);
}
