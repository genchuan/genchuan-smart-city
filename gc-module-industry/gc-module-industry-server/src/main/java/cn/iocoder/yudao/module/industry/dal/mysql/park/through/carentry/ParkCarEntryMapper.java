package cn.iocoder.yudao.module.industry.dal.mysql.park.through.carentry;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.carentry.vo.ParkCarEntryPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.through.carentry.ParkCarEntryDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 入场记录 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface ParkCarEntryMapper extends BaseMapperX<ParkCarEntryDO> {

    default PageResult<ParkCarEntryDO> selectPage(ParkCarEntryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkCarEntryDO>()
                .eqIfPresent(ParkCarEntryDO::getEntryId, reqVO.getEntryId())
                .eqIfPresent(ParkCarEntryDO::getCarNumber, reqVO.getCarNumber())
                .eqIfPresent(ParkCarEntryDO::getCarType, reqVO.getCarType())
                .betweenIfPresent(ParkCarEntryDO::getEntryTime, reqVO.getEntryTime())
                .eqIfPresent(ParkCarEntryDO::getEntryExitId, reqVO.getEntryExitId())
                .eqIfPresent(ParkCarEntryDO::getLotId, reqVO.getLotId())
                .eqIfPresent(ParkCarEntryDO::getSpaceId, reqVO.getSpaceId())
                .eqIfPresent(ParkCarEntryDO::getDeviceCode, reqVO.getDeviceCode())
                .eqIfPresent(ParkCarEntryDO::getEntryType, reqVO.getEntryType())
                .eqIfPresent(ParkCarEntryDO::getUserId, reqVO.getUserId())
                .eqIfPresent(ParkCarEntryDO::getReservationId, reqVO.getReservationId())
                .betweenIfPresent(ParkCarEntryDO::getEntryCreateTime, reqVO.getEntryCreateTime())
                .betweenIfPresent(ParkCarEntryDO::getEntryUpdateTime, reqVO.getEntryUpdateTime())
                .eqIfPresent(ParkCarEntryDO::getEntryRemark, reqVO.getEntryRemark())
                .betweenIfPresent(ParkCarEntryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ParkCarEntryDO::getId));
    }

}