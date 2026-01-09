package cn.iocoder.yudao.module.industry.dal.mysql.park.asset.garage;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.garage.vo.ParkGaragePageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.garage.ParkGarageDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车库信息 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface ParkGarageMapper extends BaseMapperX<ParkGarageDO> {

    default PageResult<ParkGarageDO> selectPage(ParkGaragePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkGarageDO>()
                .eqIfPresent(ParkGarageDO::getAssetExtendId, reqVO.getAssetExtendId())
                .eqIfPresent(ParkGarageDO::getLotId, reqVO.getLotId())
                .eqIfPresent(ParkGarageDO::getFloorCount, reqVO.getFloorCount())
                .eqIfPresent(ParkGarageDO::getTotalSpace, reqVO.getTotalSpace())
                .eqIfPresent(ParkGarageDO::getAvailableSpace, reqVO.getAvailableSpace())
                .eqIfPresent(ParkGarageDO::getAccessControlType, reqVO.getAccessControlType())
                .betweenIfPresent(ParkGarageDO::getGarageCreateTime, reqVO.getGarageCreateTime())
                .betweenIfPresent(ParkGarageDO::getGarageUpdateTime, reqVO.getGarageUpdateTime())
                .eqIfPresent(ParkGarageDO::getGarageRemark, reqVO.getGarageRemark())
                .betweenIfPresent(ParkGarageDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ParkGarageDO::getId));
    }

}