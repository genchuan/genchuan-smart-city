package cn.iocoder.yudao.module.industry.dal.mysql.park.discount.parkfeestrategy;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkfeestrategy.vo.ParkFeeStrategyPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkfeestrategy.ParkFeeStrategyDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 费率策略 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkFeeStrategyMapper extends BaseMapperX<ParkFeeStrategyDO> {

    default PageResult<ParkFeeStrategyDO> selectPage(ParkFeeStrategyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkFeeStrategyDO>()
                .likeIfPresent(ParkFeeStrategyDO::getStrategyName, reqVO.getStrategyName())
                .eqIfPresent(ParkFeeStrategyDO::getStrategyType, reqVO.getStrategyType())
                .eqIfPresent(ParkFeeStrategyDO::getApplyScope, reqVO.getApplyScope())
                .eqIfPresent(ParkFeeStrategyDO::getBaseRate, reqVO.getBaseRate())
                .eqIfPresent(ParkFeeStrategyDO::getPeakRate, reqVO.getPeakRate())
                .eqIfPresent(ParkFeeStrategyDO::getOffPeakRate, reqVO.getOffPeakRate())
                .eqIfPresent(ParkFeeStrategyDO::getRegionRate, reqVO.getRegionRate())
                .betweenIfPresent(ParkFeeStrategyDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(ParkFeeStrategyDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(ParkFeeStrategyDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ParkFeeStrategyDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkFeeStrategyDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ParkFeeStrategyDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkFeeStrategyDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkFeeStrategyDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkFeeStrategyDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(ParkFeeStrategyDO::getId));
    }

}
