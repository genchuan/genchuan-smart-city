package cn.iocoder.yudao.module.industry.dal.mysql.park.pay.parkdiscountactivity;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkdiscountactivity.vo.ParkDiscountActivityPageReqVO;

import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkdiscountactivity.ParkDiscountActivityDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 优惠活动 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkDiscountActivityMapper extends BaseMapperX<ParkDiscountActivityDO> {

    default PageResult<ParkDiscountActivityDO> selectPage(ParkDiscountActivityPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkDiscountActivityDO>()
                .eqIfPresent(ParkDiscountActivityDO::getDiscountActivityNo, reqVO.getDiscountActivityNo())
                .likeIfPresent(ParkDiscountActivityDO::getActivityName, reqVO.getActivityName())
                .eqIfPresent(ParkDiscountActivityDO::getDiscountType, reqVO.getDiscountType())
                .eqIfPresent(ParkDiscountActivityDO::getDiscountRule, reqVO.getDiscountRule())
                .eqIfPresent(ParkDiscountActivityDO::getDiscountAmount, reqVO.getDiscountAmount())
                .eqIfPresent(ParkDiscountActivityDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ParkDiscountActivityDO::getTotalDiscountAmount, reqVO.getTotalDiscountAmount())
                .eqIfPresent(ParkDiscountActivityDO::getParticipateOrderCount, reqVO.getParticipateOrderCount())
                .betweenIfPresent(ParkDiscountActivityDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkDiscountActivityDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(ParkDiscountActivityDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(ParkDiscountActivityDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkDiscountActivityDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(ParkDiscountActivityDO::getId));
    }

}
