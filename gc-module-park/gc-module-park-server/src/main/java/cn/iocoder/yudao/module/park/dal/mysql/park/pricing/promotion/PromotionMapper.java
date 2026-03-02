package cn.iocoder.yudao.module.park.dal.mysql.park.pricing.promotion;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.promotion.vo.PromotionPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.promotion.PromotionDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠活动 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PromotionMapper extends BaseMapperX<PromotionDO> {

    default PageResult<PromotionDO> selectPage(PromotionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PromotionDO>()
                .likeIfPresent(PromotionDO::getActivityName, reqVO.getActivityName())
                .eqIfPresent(PromotionDO::getActivityType, reqVO.getActivityType())
                .betweenIfPresent(PromotionDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(PromotionDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(PromotionDO::getQuota, reqVO.getQuota())
                .eqIfPresent(PromotionDO::getUsedQuota, reqVO.getUsedQuota())
                .eqIfPresent(PromotionDO::getApplyScope, reqVO.getApplyScope())
                .eqIfPresent(PromotionDO::getScopeIds, reqVO.getScopeIds())
                .eqIfPresent(PromotionDO::getRuleConfig, reqVO.getRuleConfig())
                .eqIfPresent(PromotionDO::getStatus, reqVO.getStatus())
                .eqIfPresent(PromotionDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(PromotionDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(PromotionDO::getRemark, reqVO.getRemark())
                .eqIfPresent(PromotionDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(PromotionDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(PromotionDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(PromotionDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(PromotionDO::getId));
    }

}
