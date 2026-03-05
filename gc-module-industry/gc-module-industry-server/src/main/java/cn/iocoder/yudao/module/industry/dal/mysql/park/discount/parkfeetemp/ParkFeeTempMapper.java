package cn.iocoder.yudao.module.industry.dal.mysql.park.discount.parkfeetemp;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkfeetemp.vo.ParkFeeTempPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkfeetemp.ParkFeeTempDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 临停收费规则 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkFeeTempMapper extends BaseMapperX<ParkFeeTempDO> {

    default PageResult<ParkFeeTempDO> selectPage(ParkFeeTempPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkFeeTempDO>()
                .likeIfPresent(ParkFeeTempDO::getRuleName, reqVO.getRuleName())
                .eqIfPresent(ParkFeeTempDO::getLotIds, reqVO.getLotIds())
                .eqIfPresent(ParkFeeTempDO::getFeeStrategyId, reqVO.getFeeStrategyId())
                .betweenIfPresent(ParkFeeTempDO::getFreeParkingTime, reqVO.getFreeParkingTime())
                .eqIfPresent(ParkFeeTempDO::getMaxDailyFee, reqVO.getMaxDailyFee())
                .eqIfPresent(ParkFeeTempDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ParkFeeTempDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkFeeTempDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ParkFeeTempDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkFeeTempDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkFeeTempDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkFeeTempDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(ParkFeeTempDO::getId));
    }

}
