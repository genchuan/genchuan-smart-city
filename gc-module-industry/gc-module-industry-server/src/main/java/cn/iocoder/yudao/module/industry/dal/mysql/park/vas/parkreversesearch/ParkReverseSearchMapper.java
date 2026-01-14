package cn.iocoder.yudao.module.industry.dal.mysql.park.vas.parkreversesearch;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkreversesearch.vo.ParkReverseSearchPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkreversesearch.ParkReverseSearchDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 反向寻车记录 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkReverseSearchMapper extends BaseMapperX<ParkReverseSearchDO> {

    default PageResult<ParkReverseSearchDO> selectPage(ParkReverseSearchPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkReverseSearchDO>()
                .eqIfPresent(ParkReverseSearchDO::getUserId, reqVO.getUserId())
                .eqIfPresent(ParkReverseSearchDO::getCarNumber, reqVO.getCarNumber())
                .eqIfPresent(ParkReverseSearchDO::getLotId, reqVO.getLotId())
                .eqIfPresent(ParkReverseSearchDO::getSpaceId, reqVO.getSpaceId())
                .betweenIfPresent(ParkReverseSearchDO::getSearchTime, reqVO.getSearchTime())
                .eqIfPresent(ParkReverseSearchDO::getLocationX, reqVO.getLocationX())
                .eqIfPresent(ParkReverseSearchDO::getLocationY, reqVO.getLocationY())
                .eqIfPresent(ParkReverseSearchDO::getLocationArea, reqVO.getLocationArea())
                .eqIfPresent(ParkReverseSearchDO::getRegionFullCode, reqVO.getRegionFullCode())
                .eqIfPresent(ParkReverseSearchDO::getRouteInfo, reqVO.getRouteInfo())
                .betweenIfPresent(ParkReverseSearchDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkReverseSearchDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkReverseSearchDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkReverseSearchDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkReverseSearchDO::getExtCommon4, reqVO.getExtCommon4())
                .eqIfPresent(ParkReverseSearchDO::getRemark, reqVO.getRemark())
                .orderByDesc(ParkReverseSearchDO::getId));
    }

}
