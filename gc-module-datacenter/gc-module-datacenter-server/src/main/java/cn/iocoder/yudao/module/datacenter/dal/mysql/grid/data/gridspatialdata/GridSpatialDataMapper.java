package cn.iocoder.yudao.module.datacenter.dal.mysql.grid.data.gridspatialdata;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.gridspatialdata.vo.GridSpatialDataPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.data.gridspatialdata.GridSpatialDataDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 网格空间数据 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface GridSpatialDataMapper extends BaseMapperX<GridSpatialDataDO> {

    default PageResult<GridSpatialDataDO> selectPage(GridSpatialDataPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GridSpatialDataDO>()
                .eqIfPresent(GridSpatialDataDO::getSpatialId, reqVO.getSpatialId())
                .eqIfPresent(GridSpatialDataDO::getGridId, reqVO.getGridId())
                .eqIfPresent(GridSpatialDataDO::getGridType, reqVO.getGridType())
                .eqIfPresent(GridSpatialDataDO::getBoundaryCoords, reqVO.getBoundaryCoords())
                .eqIfPresent(GridSpatialDataDO::getTopoRelation, reqVO.getTopoRelation())
                .eqIfPresent(GridSpatialDataDO::getCoordSystem, reqVO.getCoordSystem())
                .eqIfPresent(GridSpatialDataDO::getDataFormat, reqVO.getDataFormat())
                .eqIfPresent(GridSpatialDataDO::getRemark, reqVO.getRemark())
                .eqIfPresent(GridSpatialDataDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(GridSpatialDataDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(GridSpatialDataDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(GridSpatialDataDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(GridSpatialDataDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(GridSpatialDataDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(GridSpatialDataDO::getId));
    }

}