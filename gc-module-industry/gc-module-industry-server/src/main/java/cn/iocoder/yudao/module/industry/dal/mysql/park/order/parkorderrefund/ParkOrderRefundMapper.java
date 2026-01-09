package cn.iocoder.yudao.module.industry.dal.mysql.park.order.parkorderrefund;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;

import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderrefund.vo.ParkOrderRefundPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkorderrefund.ParkOrderRefundDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 退款订单 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkOrderRefundMapper extends BaseMapperX<ParkOrderRefundDO> {

    default PageResult<ParkOrderRefundDO> selectPage(ParkOrderRefundPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkOrderRefundDO>()
                .eqIfPresent(ParkOrderRefundDO::getRefundNo, reqVO.getRefundNo())
                .eqIfPresent(ParkOrderRefundDO::getOriginalOrderId, reqVO.getOriginalOrderId())
                .eqIfPresent(ParkOrderRefundDO::getOriginalOrderNo, reqVO.getOriginalOrderNo())
                .eqIfPresent(ParkOrderRefundDO::getUserId, reqVO.getUserId())
                .eqIfPresent(ParkOrderRefundDO::getRefundAmount, reqVO.getRefundAmount())
                .eqIfPresent(ParkOrderRefundDO::getRefundReason, reqVO.getRefundReason())
                .eqIfPresent(ParkOrderRefundDO::getRefundStatus, reqVO.getRefundStatus())
                .betweenIfPresent(ParkOrderRefundDO::getApplyTime, reqVO.getApplyTime())
                .betweenIfPresent(ParkOrderRefundDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(ParkOrderRefundDO::getAuditBy, reqVO.getAuditBy())
                .betweenIfPresent(ParkOrderRefundDO::getTransferTime, reqVO.getTransferTime())
                .betweenIfPresent(ParkOrderRefundDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkOrderRefundDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ParkOrderRefundDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkOrderRefundDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkOrderRefundDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkOrderRefundDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(ParkOrderRefundDO::getId));
    }

}
