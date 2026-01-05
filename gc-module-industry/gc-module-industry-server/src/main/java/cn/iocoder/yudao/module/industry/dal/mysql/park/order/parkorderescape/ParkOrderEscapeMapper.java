package cn.iocoder.yudao.module.industry.dal.mysql.park.order.parkorderescape;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;

import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderescape.vo.ParkOrderEscapePageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkorderescape.ParkOrderEscapeDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 逃费订单 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkOrderEscapeMapper extends BaseMapperX<ParkOrderEscapeDO> {

    default PageResult<ParkOrderEscapeDO> selectPage(ParkOrderEscapePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkOrderEscapeDO>()
                .eqIfPresent(ParkOrderEscapeDO::getOrderNo, reqVO.getOrderNo())
                .eqIfPresent(ParkOrderEscapeDO::getEscapeOrderId, reqVO.getEscapeOrderId())
                .eqIfPresent(ParkOrderEscapeDO::getCarNumber, reqVO.getCarNumber())
                .eqIfPresent(ParkOrderEscapeDO::getUserId, reqVO.getUserId())
                .eqIfPresent(ParkOrderEscapeDO::getLotId, reqVO.getLotId())
                .betweenIfPresent(ParkOrderEscapeDO::getEscapeTime, reqVO.getEscapeTime())
                .eqIfPresent(ParkOrderEscapeDO::getEscapeAmount, reqVO.getEscapeAmount())
                .eqIfPresent(ParkOrderEscapeDO::getEscapeReason, reqVO.getEscapeReason())
                .eqIfPresent(ParkOrderEscapeDO::getTraceStatus, reqVO.getTraceStatus())
                .eqIfPresent(ParkOrderEscapeDO::getTraceTimes, reqVO.getTraceTimes())
                .betweenIfPresent(ParkOrderEscapeDO::getLastTraceTime, reqVO.getLastTraceTime())
                .eqIfPresent(ParkOrderEscapeDO::getTraceWay, reqVO.getTraceWay())
                .betweenIfPresent(ParkOrderEscapeDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkOrderEscapeDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ParkOrderEscapeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkOrderEscapeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkOrderEscapeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkOrderEscapeDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(ParkOrderEscapeDO::getId));
    }

}
