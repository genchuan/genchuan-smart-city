package cn.iocoder.yudao.module.datacenter.dal.mysql.grid.area.gridstreet;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridstreet.vo.GridStreetPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.area.gridstreet.GridStreetDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 街道（镇、乡）行政区划配置 Mapper
 *
 * @author zcq
 */
@Mapper
public interface GridStreetMapper extends BaseMapperX<GridStreetDO> {

    default PageResult<GridStreetDO> selectPage(GridStreetPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GridStreetDO>()
                .eqIfPresent(GridStreetDO::getStreetId, reqVO.getStreetId())
                .eqIfPresent(GridStreetDO::getStreetCode, reqVO.getStreetCode())
                .likeIfPresent(GridStreetDO::getStreetName, reqVO.getStreetName())
                .eqIfPresent(GridStreetDO::getCountyAdminId, reqVO.getCountyAdminId())
                .eqIfPresent(GridStreetDO::getStreetLevel, reqVO.getStreetLevel())
                .betweenIfPresent(GridStreetDO::getEffectiveTime, reqVO.getEffectiveTime())
                .betweenIfPresent(GridStreetDO::getInvalidTime, reqVO.getInvalidTime())
                .eqIfPresent(GridStreetDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(GridStreetDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GridStreetDO::getId));
    }

    default List<GridStreetDO> selectList() {
        return selectList(new LambdaQueryWrapperX<GridStreetDO>()
                .orderByDesc(GridStreetDO::getId));
    }

    default List<GridStreetDO> selectListByCountyId(String countyAdminId) {
        return selectList(new LambdaQueryWrapperX<GridStreetDO>()
                .eq(GridStreetDO::getCountyAdminId, countyAdminId)
                .orderByDesc(GridStreetDO::getId));
    }

    default List<GridStreetDO> selectListByLevel(Integer streetLevel) {
        return selectList(new LambdaQueryWrapperX<GridStreetDO>()
                .eq(GridStreetDO::getStreetLevel, streetLevel)
                .orderByDesc(GridStreetDO::getId));
    }
}