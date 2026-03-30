package cn.iocoder.yudao.module.waterdetection.dal.mysql.waterbalance;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.waterbalance.WaterBalanceDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.waterbalance.vo.*;

/**
 * 水量平衡与漏损分析 Mapper
 *
 * @author zcq
 */
@Mapper
public interface WaterBalanceMapper extends BaseMapperX<WaterBalanceDO> {

    default PageResult<WaterBalanceDO> selectPage(WaterBalancePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WaterBalanceDO>()
                .eqIfPresent(WaterBalanceDO::getPartitionId, reqVO.getPartitionId())
                .eqIfPresent(WaterBalanceDO::getStatisticsPeriod, reqVO.getStatisticsPeriod())
                .betweenIfPresent(WaterBalanceDO::getStatisticsDate, reqVO.getStatisticsDate())
                .eqIfPresent(WaterBalanceDO::getSupplyVolume, reqVO.getSupplyVolume())
                .eqIfPresent(WaterBalanceDO::getSalesVolume, reqVO.getSalesVolume())
                .eqIfPresent(WaterBalanceDO::getReasonableLoss, reqVO.getReasonableLoss())
                .eqIfPresent(WaterBalanceDO::getLeakageVolume, reqVO.getLeakageVolume())
                .eqIfPresent(WaterBalanceDO::getLeakageRate, reqVO.getLeakageRate())
                .eqIfPresent(WaterBalanceDO::getIsExceeded, reqVO.getIsExceeded())
                .betweenIfPresent(WaterBalanceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(WaterBalanceDO::getId));
    }

}