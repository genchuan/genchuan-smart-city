package cn.iocoder.yudao.module.industry.dal.mysql.park.pay.parkberthlock;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkberthlock.vo.ParkBerthLockPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkberthlock.ParkBerthLockDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 泊位锁定记录 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkBerthLockMapper extends BaseMapperX<ParkBerthLockDO> {

    default PageResult<ParkBerthLockDO> selectPage(ParkBerthLockPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkBerthLockDO>()
                .eqIfPresent(ParkBerthLockDO::getReservationId, reqVO.getReservationId())
                .eqIfPresent(ParkBerthLockDO::getBerthId, reqVO.getBerthId())
                .betweenIfPresent(ParkBerthLockDO::getLockStartTime, reqVO.getLockStartTime())
                .betweenIfPresent(ParkBerthLockDO::getLockEndTime, reqVO.getLockEndTime())
                .eqIfPresent(ParkBerthLockDO::getLockStatus, reqVO.getLockStatus())
                .eqIfPresent(ParkBerthLockDO::getUnlockReason, reqVO.getUnlockReason())
                .betweenIfPresent(ParkBerthLockDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkBerthLockDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(ParkBerthLockDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(ParkBerthLockDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkBerthLockDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(ParkBerthLockDO::getId));
    }

}
