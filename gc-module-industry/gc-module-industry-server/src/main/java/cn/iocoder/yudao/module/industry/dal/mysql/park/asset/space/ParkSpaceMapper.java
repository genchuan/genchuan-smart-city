package cn.iocoder.yudao.module.industry.dal.mysql.park.asset.space;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.space.vo.ParkSpacePageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.space.ParkSpaceDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车位信息 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface ParkSpaceMapper extends BaseMapperX<ParkSpaceDO> {

    default PageResult<ParkSpaceDO> selectPage(ParkSpacePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkSpaceDO>()
                .eqIfPresent(ParkSpaceDO::getAssetExtendId, reqVO.getAssetExtendId())
                .eqIfPresent(ParkSpaceDO::getLotId, reqVO.getLotId())
                .eqIfPresent(ParkSpaceDO::getGarageId, reqVO.getGarageId())
                .eqIfPresent(ParkSpaceDO::getSpaceNumber, reqVO.getSpaceNumber())
                .eqIfPresent(ParkSpaceDO::getSpaceType, reqVO.getSpaceType())
                .eqIfPresent(ParkSpaceDO::getBindCarList, reqVO.getBindCarList())
                .eqIfPresent(ParkSpaceDO::getIsReservable, reqVO.getIsReservable())
                .eqIfPresent(ParkSpaceDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ParkSpaceDO::getSpaceCreateTime, reqVO.getSpaceCreateTime())
                .betweenIfPresent(ParkSpaceDO::getSpaceUpdateTime, reqVO.getSpaceUpdateTime())
                .eqIfPresent(ParkSpaceDO::getSpaceRemark, reqVO.getSpaceRemark())
                .betweenIfPresent(ParkSpaceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ParkSpaceDO::getId));
    }

}