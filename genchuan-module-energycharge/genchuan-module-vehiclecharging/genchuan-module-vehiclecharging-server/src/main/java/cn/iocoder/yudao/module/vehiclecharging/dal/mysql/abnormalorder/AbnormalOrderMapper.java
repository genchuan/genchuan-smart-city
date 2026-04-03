package cn.iocoder.yudao.module.vehiclecharging.dal.mysql.abnormalorder;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.abnormalorder.AbnormalOrderDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.abnormalorder.vo.*;

/**
 * 异常订单 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AbnormalOrderMapper extends BaseMapperX<AbnormalOrderDO> {

    default PageResult<AbnormalOrderDO> selectPage(AbnormalOrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AbnormalOrderDO>()
                .eqIfPresent(AbnormalOrderDO::getOrderCode, reqVO.getOrderCode())
                .eqIfPresent(AbnormalOrderDO::getAbnormalType, reqVO.getAbnormalType())
                .eqIfPresent(AbnormalOrderDO::getAbnormalReason, reqVO.getAbnormalReason())
                .eqIfPresent(AbnormalOrderDO::getCheckUser, reqVO.getCheckUser())
                .betweenIfPresent(AbnormalOrderDO::getCheckTime, reqVO.getCheckTime())
                .eqIfPresent(AbnormalOrderDO::getHandleMeasure, reqVO.getHandleMeasure())
                .eqIfPresent(AbnormalOrderDO::getRefundAmount, reqVO.getRefundAmount())
                .eqIfPresent(AbnormalOrderDO::getAbnormalStatus, reqVO.getAbnormalStatus())
                .betweenIfPresent(AbnormalOrderDO::getHandleTime, reqVO.getHandleTime())
                .eqIfPresent(AbnormalOrderDO::getRemark, reqVO.getRemark())
                .eqIfPresent(AbnormalOrderDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(AbnormalOrderDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(AbnormalOrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AbnormalOrderDO::getId));
    }

}