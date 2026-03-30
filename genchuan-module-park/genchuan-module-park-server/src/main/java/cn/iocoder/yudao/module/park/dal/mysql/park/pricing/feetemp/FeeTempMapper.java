package cn.iocoder.yudao.module.park.dal.mysql.park.pricing.feetemp;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.feetemp.vo.FeeTempPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.feetemp.FeeTempDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 临停收费规则 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface FeeTempMapper extends BaseMapperX<FeeTempDO> {

    default PageResult<FeeTempDO> selectPage(FeeTempPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FeeTempDO>()
                .likeIfPresent(FeeTempDO::getRuleName, reqVO.getRuleName())
                .eqIfPresent(FeeTempDO::getFeeStrategyId, reqVO.getFeeStrategyId())
                .betweenIfPresent(FeeTempDO::getFreeParkingTime, reqVO.getFreeParkingTime())
                .eqIfPresent(FeeTempDO::getMaxDailyFee, reqVO.getMaxDailyFee())
                .eqIfPresent(FeeTempDO::getApplyLotIds, reqVO.getApplyLotIds())
                .eqIfPresent(FeeTempDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(FeeTempDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(FeeTempDO::getRemark, reqVO.getRemark())
                .eqIfPresent(FeeTempDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(FeeTempDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(FeeTempDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(FeeTempDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(FeeTempDO::getId));
    }

}
