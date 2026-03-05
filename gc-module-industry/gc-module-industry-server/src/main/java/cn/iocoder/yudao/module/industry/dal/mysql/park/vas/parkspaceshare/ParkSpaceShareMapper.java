package cn.iocoder.yudao.module.industry.dal.mysql.park.vas.parkspaceshare;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkspaceshare.vo.ParkSpaceSharePageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkspaceshare.ParkSpaceShareDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车位共享配置 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkSpaceShareMapper extends BaseMapperX<ParkSpaceShareDO> {

    default PageResult<ParkSpaceShareDO> selectPage(ParkSpaceSharePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkSpaceShareDO>()
                .eqIfPresent(ParkSpaceShareDO::getShareNo, reqVO.getShareNo())
                .eqIfPresent(ParkSpaceShareDO::getSpaceId, reqVO.getSpaceId())
                .eqIfPresent(ParkSpaceShareDO::getUserId, reqVO.getUserId())
                .eqIfPresent(ParkSpaceShareDO::getPricePerHour, reqVO.getPricePerHour())
                .eqIfPresent(ParkSpaceShareDO::getMaxBookingDuration, reqVO.getMaxBookingDuration())
                .betweenIfPresent(ParkSpaceShareDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(ParkSpaceShareDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(ParkSpaceShareDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ParkSpaceShareDO::getOrderCount, reqVO.getOrderCount())
                .eqIfPresent(ParkSpaceShareDO::getIncomeAmount, reqVO.getIncomeAmount())
                .betweenIfPresent(ParkSpaceShareDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkSpaceShareDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ParkSpaceShareDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkSpaceShareDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkSpaceShareDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkSpaceShareDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(ParkSpaceShareDO::getId));
    }

}
