package cn.iocoder.yudao.module.industry.dal.mysql.park.marketing.parksmoothparkingcard;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parksmoothparkingcard.vo.ParkSmoothParkingCardPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.marketing.parksmoothparkingcard.ParkSmoothParkingCardDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 畅停卡 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkSmoothParkingCardMapper extends BaseMapperX<ParkSmoothParkingCardDO> {

    default PageResult<ParkSmoothParkingCardDO> selectPage(ParkSmoothParkingCardPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkSmoothParkingCardDO>()
                .eqIfPresent(ParkSmoothParkingCardDO::getCardCode, reqVO.getCardCode())
                .eqIfPresent(ParkSmoothParkingCardDO::getCardType, reqVO.getCardType())
                .likeIfPresent(ParkSmoothParkingCardDO::getCardName, reqVO.getCardName())
                .eqIfPresent(ParkSmoothParkingCardDO::getApplyScopeType, reqVO.getApplyScopeType())
                .eqIfPresent(ParkSmoothParkingCardDO::getApplyScopeValue, reqVO.getApplyScopeValue())
                .eqIfPresent(ParkSmoothParkingCardDO::getValidDays, reqVO.getValidDays())
                .eqIfPresent(ParkSmoothParkingCardDO::getOriginalPrice, reqVO.getOriginalPrice())
                .eqIfPresent(ParkSmoothParkingCardDO::getSalePrice, reqVO.getSalePrice())
                .eqIfPresent(ParkSmoothParkingCardDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ParkSmoothParkingCardDO::getUserId, reqVO.getUserId())
                .betweenIfPresent(ParkSmoothParkingCardDO::getActivateTime, reqVO.getActivateTime())
                .betweenIfPresent(ParkSmoothParkingCardDO::getExpireTime, reqVO.getExpireTime())
                .betweenIfPresent(ParkSmoothParkingCardDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkSmoothParkingCardDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ParkSmoothParkingCardDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkSmoothParkingCardDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkSmoothParkingCardDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkSmoothParkingCardDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(ParkSmoothParkingCardDO::getId));
    }

}
