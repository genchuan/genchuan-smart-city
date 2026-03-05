package cn.iocoder.yudao.module.industry.dal.mysql.park.marketing.parkpoints;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpoints.vo.ParkPointsPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.marketing.parkpoints.ParkPointsDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户积分 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkPointsMapper extends BaseMapperX<ParkPointsDO> {

    default PageResult<ParkPointsDO> selectPage(ParkPointsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkPointsDO>()
                .eqIfPresent(ParkPointsDO::getUserId, reqVO.getUserId())
                .eqIfPresent(ParkPointsDO::getTotalPoints, reqVO.getTotalPoints())
                .eqIfPresent(ParkPointsDO::getAvailablePoints, reqVO.getAvailablePoints())
                .eqIfPresent(ParkPointsDO::getUsedPoints, reqVO.getUsedPoints())
                .eqIfPresent(ParkPointsDO::getExpiredPoints, reqVO.getExpiredPoints())
                .betweenIfPresent(ParkPointsDO::getLastUpdateTime, reqVO.getLastUpdateTime())
                .betweenIfPresent(ParkPointsDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkPointsDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ParkPointsDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkPointsDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkPointsDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkPointsDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(ParkPointsDO::getId));
    }

}
