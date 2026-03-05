package cn.iocoder.yudao.module.industry.dal.mysql.park.discount.parkchargefee;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkchargefee.vo.ParkChargeFeePageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkchargefee.ParkChargeFeeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 充电收费 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkChargeFeeMapper extends BaseMapperX<ParkChargeFeeDO> {

    default PageResult<ParkChargeFeeDO> selectPage(ParkChargeFeePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkChargeFeeDO>()
                .eqIfPresent(ParkChargeFeeDO::getFeeStrategyId, reqVO.getFeeStrategyId())
                .eqIfPresent(ParkChargeFeeDO::getChargePileId, reqVO.getChargePileId())
                .eqIfPresent(ParkChargeFeeDO::getBaseRate, reqVO.getBaseRate())
                .eqIfPresent(ParkChargeFeeDO::getPeakRate, reqVO.getPeakRate())
                .eqIfPresent(ParkChargeFeeDO::getOffPeakRate, reqVO.getOffPeakRate())
                .eqIfPresent(ParkChargeFeeDO::getMergeParkingFee, reqVO.getMergeParkingFee())
                .eqIfPresent(ParkChargeFeeDO::getActivityId, reqVO.getActivityId())
                .eqIfPresent(ParkChargeFeeDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ParkChargeFeeDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkChargeFeeDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ParkChargeFeeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkChargeFeeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkChargeFeeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkChargeFeeDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(ParkChargeFeeDO::getId));
    }

}
