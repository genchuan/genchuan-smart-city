package cn.iocoder.yudao.module.industry.dal.mysql.park.vas.parkspaceshareorder;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkspaceshareorder.vo.ParkSpaceShareOrderPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkspaceshareorder.ParkSpaceShareOrderDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车位共享订单 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkSpaceShareOrderMapper extends BaseMapperX<ParkSpaceShareOrderDO> {

    default PageResult<ParkSpaceShareOrderDO> selectPage(ParkSpaceShareOrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkSpaceShareOrderDO>()
                .eqIfPresent(ParkSpaceShareOrderDO::getOrderNo, reqVO.getOrderNo())
                .eqIfPresent(ParkSpaceShareOrderDO::getShareId, reqVO.getShareId())
                .eqIfPresent(ParkSpaceShareOrderDO::getUserId, reqVO.getUserId())
                .eqIfPresent(ParkSpaceShareOrderDO::getCarNumber, reqVO.getCarNumber())
                .betweenIfPresent(ParkSpaceShareOrderDO::getUseDate, reqVO.getUseDate())
                .betweenIfPresent(ParkSpaceShareOrderDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(ParkSpaceShareOrderDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(ParkSpaceShareOrderDO::getUseDuration, reqVO.getUseDuration())
                .eqIfPresent(ParkSpaceShareOrderDO::getFeeAmount, reqVO.getFeeAmount())
                .eqIfPresent(ParkSpaceShareOrderDO::getPayStatus, reqVO.getPayStatus())
                .eqIfPresent(ParkSpaceShareOrderDO::getPaymentId, reqVO.getPaymentId())
                .eqIfPresent(ParkSpaceShareOrderDO::getSettlementStatus, reqVO.getSettlementStatus())
                .betweenIfPresent(ParkSpaceShareOrderDO::getSettlementTime, reqVO.getSettlementTime())
                .betweenIfPresent(ParkSpaceShareOrderDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkSpaceShareOrderDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ParkSpaceShareOrderDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkSpaceShareOrderDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkSpaceShareOrderDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkSpaceShareOrderDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(ParkSpaceShareOrderDO::getId));
    }

}
