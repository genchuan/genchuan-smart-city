package cn.iocoder.yudao.module.industry.dal.mysql.park.asset.roadside;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.roadside.vo.ParkRoadsidePageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.roadside.ParkRoadsideDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 路侧泊位 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface ParkRoadsideMapper extends BaseMapperX<ParkRoadsideDO> {

    default PageResult<ParkRoadsideDO> selectPage(ParkRoadsidePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkRoadsideDO>()
                .eqIfPresent(ParkRoadsideDO::getAssetExtendId, reqVO.getAssetExtendId())
                .likeIfPresent(ParkRoadsideDO::getRoadName, reqVO.getRoadName())
                .eqIfPresent(ParkRoadsideDO::getBerthNumber, reqVO.getBerthNumber())
                .eqIfPresent(ParkRoadsideDO::getBerthType, reqVO.getBerthType())
                .eqIfPresent(ParkRoadsideDO::getFeePileId, reqVO.getFeePileId())
                .eqIfPresent(ParkRoadsideDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ParkRoadsideDO::getLastOccupyTime, reqVO.getLastOccupyTime())
                .betweenIfPresent(ParkRoadsideDO::getLastReleaseTime, reqVO.getLastReleaseTime())
                .betweenIfPresent(ParkRoadsideDO::getRoadsideCreateTime, reqVO.getRoadsideCreateTime())
                .betweenIfPresent(ParkRoadsideDO::getRoadsideUpdateTime, reqVO.getRoadsideUpdateTime())
                .eqIfPresent(ParkRoadsideDO::getRoadsideRemark, reqVO.getRoadsideRemark())
                .betweenIfPresent(ParkRoadsideDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ParkRoadsideDO::getId));
    }

}