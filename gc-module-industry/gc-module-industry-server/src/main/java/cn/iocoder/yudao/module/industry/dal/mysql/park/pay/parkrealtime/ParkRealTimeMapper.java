package cn.iocoder.yudao.module.industry.dal.mysql.park.pay.parkrealtime;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkrealtime.vo.ParkRealTimePageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkrealtime.ParkRealTimeDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 停车泊位实时状态 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkRealTimeMapper extends BaseMapperX<ParkRealTimeDO> {

    default PageResult<ParkRealTimeDO> selectPage(ParkRealTimePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkRealTimeDO>()
                .eqIfPresent(ParkRealTimeDO::getRecommendParkId, reqVO.getRecommendParkId())
                .eqIfPresent(ParkRealTimeDO::getFreeSpace, reqVO.getFreeSpace())
                .eqIfPresent(ParkRealTimeDO::getOccupyRate, reqVO.getOccupyRate())
                .betweenIfPresent(ParkRealTimeDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkRealTimeDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(ParkRealTimeDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(ParkRealTimeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkRealTimeDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(ParkRealTimeDO::getId));
    }

}
