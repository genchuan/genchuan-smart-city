package cn.iocoder.yudao.module.park.dal.mysql.park.order.orderescape;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.order.orderescape.vo.OrderEscapePageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.order.orderescape.OrderEscapeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 逃费订单 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface OrderEscapeMapper extends BaseMapperX<OrderEscapeDO> {

    default PageResult<OrderEscapeDO> selectPage(OrderEscapePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderEscapeDO>()
                .eqIfPresent(OrderEscapeDO::getOriginalOrderId, reqVO.getOriginalOrderId())
                .eqIfPresent(OrderEscapeDO::getCarNumber, reqVO.getCarNumber())
                .eqIfPresent(OrderEscapeDO::getLotId, reqVO.getLotId())
                .eqIfPresent(OrderEscapeDO::getEscapeAmount, reqVO.getEscapeAmount())
                .betweenIfPresent(OrderEscapeDO::getEscapeTime, reqVO.getEscapeTime())
                .eqIfPresent(OrderEscapeDO::getEscapeType, reqVO.getEscapeType())
                .eqIfPresent(OrderEscapeDO::getEscapeLevel, reqVO.getEscapeLevel())
                .eqIfPresent(OrderEscapeDO::getBlacklistStatus, reqVO.getBlacklistStatus())
                .eqIfPresent(OrderEscapeDO::getTraceStatus, reqVO.getTraceStatus())
                .eqIfPresent(OrderEscapeDO::getTraceCount, reqVO.getTraceCount())
                .betweenIfPresent(OrderEscapeDO::getLastTraceTime, reqVO.getLastTraceTime())
                .betweenIfPresent(OrderEscapeDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(OrderEscapeDO::getRemark, reqVO.getRemark())
                .eqIfPresent(OrderEscapeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(OrderEscapeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(OrderEscapeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(OrderEscapeDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(OrderEscapeDO::getId));
    }

}
