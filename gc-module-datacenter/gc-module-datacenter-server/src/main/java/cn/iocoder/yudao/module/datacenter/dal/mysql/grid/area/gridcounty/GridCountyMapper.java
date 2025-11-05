package cn.iocoder.yudao.module.datacenter.dal.mysql.grid.area.gridcounty;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridcounty.vo.GridCountyPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.area.gridcounty.GridCountyDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 县级及以上行政区划配置 Mapper
 *
 * @author zcq
 */
@Mapper
public interface GridCountyMapper extends BaseMapperX<GridCountyDO> {

    default PageResult<GridCountyDO> selectPage(GridCountyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GridCountyDO>()
                .eqIfPresent(GridCountyDO::getAdminDivisionId, reqVO.getAdminDivisionId())
                .eqIfPresent(GridCountyDO::getAdminCode, reqVO.getAdminCode())
                .likeIfPresent(GridCountyDO::getAdminName, reqVO.getAdminName())
                .eqIfPresent(GridCountyDO::getAdminLevel, reqVO.getAdminLevel())
                .eqIfPresent(GridCountyDO::getParentAdminId, reqVO.getParentAdminId())
                .betweenIfPresent(GridCountyDO::getEffectiveTime, reqVO.getEffectiveTime())
                .betweenIfPresent(GridCountyDO::getInvalidTime, reqVO.getInvalidTime())
                .eqIfPresent(GridCountyDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(GridCountyDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GridCountyDO::getId));
    }

    default List<GridCountyDO> selectList() {
        return selectList(new LambdaQueryWrapperX<GridCountyDO>().orderByDesc(GridCountyDO::getId));
    }

    default List<GridCountyDO> selectListByParentId(String parentAdminId) {
        return selectList(new LambdaQueryWrapperX<GridCountyDO>()
                .eq(GridCountyDO::getParentAdminId, parentAdminId)
                .orderByDesc(GridCountyDO::getId));
    }

    default List<GridCountyDO> selectListByLevel(Integer adminLevel) {
        return selectList(new LambdaQueryWrapperX<GridCountyDO>()
                .eq(GridCountyDO::getAdminLevel, adminLevel)
                .orderByDesc(GridCountyDO::getId));
    }

}