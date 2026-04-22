package cn.iocoder.yudao.module.chargepark.carservice.service.carguide;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.SpacePushBatchPushReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.SpacePushPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.SpacePushSaveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide.SpacePushDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 空位推送 Service 接口
 *
 * @author carservice
 */
public interface SpacePushService {

    Long createSpacePush(@Valid SpacePushSaveReqVO createReqVO);

    void updateSpacePush(@Valid SpacePushSaveReqVO updateReqVO);

    void deleteSpacePush(Long id);

    void deleteSpacePushListByIds(List<Long> ids);

    SpacePushDO getSpacePush(Long id);

    PageResult<SpacePushDO> getSpacePushPage(SpacePushPageReqVO pageReqVO);

    /** 推送：待推送 → 已推送（更新 push_time、push_result） */
    void pushSpacePush(Long id);

    /** 批量推送 */
    void batchPushSpacePush(SpacePushBatchPushReqVO reqVO);

}
