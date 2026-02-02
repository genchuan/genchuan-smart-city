package cn.iocoder.yudao.module.park.dal.mysql.park.resource.roadsideberthmanage;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.resource.roadsideberthmanage.vo.RoadsideBerthManagePageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.resource.roadsideberthmanage.RoadsideBerthManageDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 路测泊位管理 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface RoadsideBerthManageMapper extends BaseMapperX<RoadsideBerthManageDO> {

    default PageResult<RoadsideBerthManageDO> selectPage(RoadsideBerthManagePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RoadsideBerthManageDO>()
                .eqIfPresent(RoadsideBerthManageDO::getBerthCode, reqVO.getBerthCode())
                .likeIfPresent(RoadsideBerthManageDO::getRoadName, reqVO.getRoadName())
                .eqIfPresent(RoadsideBerthManageDO::getLocationDesc, reqVO.getLocationDesc())
                .eqIfPresent(RoadsideBerthManageDO::getBerthType, reqVO.getBerthType())
                .eqIfPresent(RoadsideBerthManageDO::getCoordinateX, reqVO.getCoordinateX())
                .eqIfPresent(RoadsideBerthManageDO::getCoordinateY, reqVO.getCoordinateY())
                .eqIfPresent(RoadsideBerthManageDO::getCurrentCar, reqVO.getCurrentCar())
                .eqIfPresent(RoadsideBerthManageDO::getBerthStatus, reqVO.getBerthStatus())
                .eqIfPresent(RoadsideBerthManageDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(RoadsideBerthManageDO::getRoadsideInfo, reqVO.getRoadsideInfo())
                .eqIfPresent(RoadsideBerthManageDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(RoadsideBerthManageDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(RoadsideBerthManageDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(RoadsideBerthManageDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(RoadsideBerthManageDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RoadsideBerthManageDO::getId));
    }

}