package cn.iocoder.yudao.module.park.dal.mysql.park.marketing.smoothstopcard;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.marketing.smoothstopcard.vo.SmoothStopCardPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.marketing.smoothstopcard.SmoothStopCardDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 畅停卡 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface SmoothStopCardMapper extends BaseMapperX<SmoothStopCardDO> {

    default PageResult<SmoothStopCardDO> selectPage(SmoothStopCardPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SmoothStopCardDO>()
                .likeIfPresent(SmoothStopCardDO::getCardName, reqVO.getCardName())
                .eqIfPresent(SmoothStopCardDO::getCardType, reqVO.getCardType())
                .eqIfPresent(SmoothStopCardDO::getValidDays, reqVO.getValidDays())
                .betweenIfPresent(SmoothStopCardDO::getEffectiveTime, reqVO.getEffectiveTime())
                .betweenIfPresent(SmoothStopCardDO::getExpireTime, reqVO.getExpireTime())
                .eqIfPresent(SmoothStopCardDO::getHolderId, reqVO.getHolderId())
                .eqIfPresent(SmoothStopCardDO::getBindCarLimit, reqVO.getBindCarLimit())
                .eqIfPresent(SmoothStopCardDO::getBoundCarNumbers, reqVO.getBoundCarNumbers())
                .eqIfPresent(SmoothStopCardDO::getApplyLotIds, reqVO.getApplyLotIds())
                .eqIfPresent(SmoothStopCardDO::getDescription, reqVO.getDescription())
                .betweenIfPresent(SmoothStopCardDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(SmoothStopCardDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(SmoothStopCardDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(SmoothStopCardDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(SmoothStopCardDO::getExtCommon4, reqVO.getExtCommon4())
                .eqIfPresent(SmoothStopCardDO::getRemark, reqVO.getRemark())
                .orderByDesc(SmoothStopCardDO::getId));
    }

}
