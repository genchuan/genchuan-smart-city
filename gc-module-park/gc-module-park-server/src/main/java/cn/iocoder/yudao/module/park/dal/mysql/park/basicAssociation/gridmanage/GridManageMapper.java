package cn.iocoder.yudao.module.park.dal.mysql.park.basicAssociation.gridmanage;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.gridmanage.vo.GridManagePageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.gridmanage.GridManageDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 网格管理 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface GridManageMapper extends BaseMapperX<GridManageDO> {

    default PageResult<GridManageDO> selectPage(GridManagePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GridManageDO>()
                .eqIfPresent(GridManageDO::getTreeNodeId, reqVO.getTreeNodeId())
                .eqIfPresent(GridManageDO::getNodeCode, reqVO.getNodeCode())
                .likeIfPresent(GridManageDO::getNodeName, reqVO.getNodeName())
                .eqIfPresent(GridManageDO::getGridType, reqVO.getGridType())
                .likeIfPresent(GridManageDO::getGridName, reqVO.getGridName())
                .eqIfPresent(GridManageDO::getGridCode, reqVO.getGridCode())
                .eqIfPresent(GridManageDO::getBoundaryCoords, reqVO.getBoundaryCoords())
                .eqIfPresent(GridManageDO::getGridArea, reqVO.getGridArea())
                .eqIfPresent(GridManageDO::getGridStatus, reqVO.getGridStatus())
                .eqIfPresent(GridManageDO::getGridUserId, reqVO.getGridUserId())
                .likeIfPresent(GridManageDO::getGridUserName, reqVO.getGridUserName())
                .betweenIfPresent(GridManageDO::getDivTime, reqVO.getDivTime())
                .eqIfPresent(GridManageDO::getGridRemark, reqVO.getGridRemark())
                .betweenIfPresent(GridManageDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GridManageDO::getId));
    }

}
