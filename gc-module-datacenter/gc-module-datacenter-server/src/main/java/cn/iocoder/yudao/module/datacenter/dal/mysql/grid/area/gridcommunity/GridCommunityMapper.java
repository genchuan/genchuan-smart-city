package cn.iocoder.yudao.module.datacenter.dal.mysql.grid.area.gridcommunity;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridcommunity.vo.GridCommunityPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.area.gridcommunity.GridCommunityDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 社区（村）行政区划配置 Mapper
 *
 * @author zcq
 */
@Mapper
public interface GridCommunityMapper extends BaseMapperX<GridCommunityDO> {

    default PageResult<GridCommunityDO> selectPage(GridCommunityPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GridCommunityDO>()
                .eqIfPresent(GridCommunityDO::getCommunityId, reqVO.getCommunityId())
                .eqIfPresent(GridCommunityDO::getCommunityCode, reqVO.getCommunityCode())
                .likeIfPresent(GridCommunityDO::getCommunityName, reqVO.getCommunityName())
                .eqIfPresent(GridCommunityDO::getStreetId, reqVO.getStreetId())
                .betweenIfPresent(GridCommunityDO::getEffectiveTime, reqVO.getEffectiveTime())
                .betweenIfPresent(GridCommunityDO::getInvalidTime, reqVO.getInvalidTime())
                .eqIfPresent(GridCommunityDO::getCommunityBoundary, reqVO.getCommunityBoundary())
                .eqIfPresent(GridCommunityDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(GridCommunityDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GridCommunityDO::getId));
    }

    default List<GridCommunityDO> selectList() {
        return selectList(new LambdaQueryWrapperX<GridCommunityDO>()
                .orderByDesc(GridCommunityDO::getId));
    }

    default List<GridCommunityDO> selectListByStreetId(String streetId) {
        return selectList(new LambdaQueryWrapperX<GridCommunityDO>()
                .eq(GridCommunityDO::getStreetId, streetId)
                .orderByDesc(GridCommunityDO::getId));
    }

    default List<GridCommunityDO> selectListByStreetIds(List<String> streetIds) {
        return selectList(new LambdaQueryWrapperX<GridCommunityDO>()
                .in(GridCommunityDO::getStreetId, streetIds)
                .orderByDesc(GridCommunityDO::getId));
    }

    default List<GridCommunityDO> selectListByName(String communityName) {
        return selectList(new LambdaQueryWrapperX<GridCommunityDO>()
                .like(GridCommunityDO::getCommunityName, communityName)
                .orderByDesc(GridCommunityDO::getId));
    }
}