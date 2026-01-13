package cn.iocoder.yudao.module.industry.dal.mysql.park.discount.parkpromotion;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkpromotion.vo.ParkPromotionPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkpromotion.ParkPromotionDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠活动 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkPromotionMapper extends BaseMapperX<ParkPromotionDO> {

    default PageResult<ParkPromotionDO> selectPage(ParkPromotionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkPromotionDO>()
                .likeIfPresent(ParkPromotionDO::getActivityName, reqVO.getActivityName())
                .eqIfPresent(ParkPromotionDO::getActivityType, reqVO.getActivityType())
                .eqIfPresent(ParkPromotionDO::getApplyScope, reqVO.getApplyScope())
                .betweenIfPresent(ParkPromotionDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(ParkPromotionDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(ParkPromotionDO::getQuota, reqVO.getQuota())
                .eqIfPresent(ParkPromotionDO::getUsedQuota, reqVO.getUsedQuota())
                .eqIfPresent(ParkPromotionDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ParkPromotionDO::getRuleConfig, reqVO.getRuleConfig())
                .eqIfPresent(ParkPromotionDO::getDataStatistics, reqVO.getDataStatistics())
                .eqIfPresent(ParkPromotionDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(ParkPromotionDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkPromotionDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkPromotionDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkPromotionDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkPromotionDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(ParkPromotionDO::getId));
    }

}
