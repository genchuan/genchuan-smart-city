package cn.iocoder.yudao.module.park.dal.mysql.park.pricing.feestrategy;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.feestrategy.vo.FeeStrategyPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.feestrategy.FeeStrategyDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 费率策略 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface FeeStrategyMapper extends BaseMapperX<FeeStrategyDO> {

    default PageResult<FeeStrategyDO> selectPage(FeeStrategyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FeeStrategyDO>()
                .likeIfPresent(FeeStrategyDO::getStrategyName, reqVO.getStrategyName())
                .eqIfPresent(FeeStrategyDO::getBaseRate, reqVO.getBaseRate())
                .eqIfPresent(FeeStrategyDO::getMaxDailyFee, reqVO.getMaxDailyFee())
                .eqIfPresent(FeeStrategyDO::getApplyScope, reqVO.getApplyScope())
                .eqIfPresent(FeeStrategyDO::getScopeIds, reqVO.getScopeIds())
                .betweenIfPresent(FeeStrategyDO::getPeakTime, reqVO.getPeakTime())
                .eqIfPresent(FeeStrategyDO::getPeakRate, reqVO.getPeakRate())
                .betweenIfPresent(FeeStrategyDO::getOffPeakTime, reqVO.getOffPeakTime())
                .eqIfPresent(FeeStrategyDO::getOffPeakRate, reqVO.getOffPeakRate())
                .betweenIfPresent(FeeStrategyDO::getEffectTime, reqVO.getEffectTime())
                .betweenIfPresent(FeeStrategyDO::getExpireTime, reqVO.getExpireTime())
                .eqIfPresent(FeeStrategyDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(FeeStrategyDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(FeeStrategyDO::getRemark, reqVO.getRemark())
                .eqIfPresent(FeeStrategyDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(FeeStrategyDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(FeeStrategyDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(FeeStrategyDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(FeeStrategyDO::getId));
    }

}
