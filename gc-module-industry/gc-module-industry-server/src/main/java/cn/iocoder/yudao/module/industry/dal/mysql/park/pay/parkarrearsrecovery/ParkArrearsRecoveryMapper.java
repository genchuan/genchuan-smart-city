package cn.iocoder.yudao.module.industry.dal.mysql.park.pay.parkarrearsrecovery;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkarrearsrecovery.vo.ParkArrearsRecoveryPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkarrearsrecovery.ParkArrearsRecoveryDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 欠费追缴 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkArrearsRecoveryMapper extends BaseMapperX<ParkArrearsRecoveryDO> {

    default PageResult<ParkArrearsRecoveryDO> selectPage(ParkArrearsRecoveryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkArrearsRecoveryDO>()
                .eqIfPresent(ParkArrearsRecoveryDO::getRecoveryCode, reqVO.getRecoveryCode())
                .eqIfPresent(ParkArrearsRecoveryDO::getWoId, reqVO.getWoId())
                .eqIfPresent(ParkArrearsRecoveryDO::getWoNo, reqVO.getWoNo())
                .eqIfPresent(ParkArrearsRecoveryDO::getUserId, reqVO.getUserId())
                .likeIfPresent(ParkArrearsRecoveryDO::getUserName, reqVO.getUserName())
                .eqIfPresent(ParkArrearsRecoveryDO::getUserPhone, reqVO.getUserPhone())
                .betweenIfPresent(ParkArrearsRecoveryDO::getArrearsTime, reqVO.getArrearsTime())
                .betweenIfPresent(ParkArrearsRecoveryDO::getSettleTime, reqVO.getSettleTime())
                .eqIfPresent(ParkArrearsRecoveryDO::getArrearsAmount, reqVO.getArrearsAmount())
                .eqIfPresent(ParkArrearsRecoveryDO::getRecoveryWoId, reqVO.getRecoveryWoId())
                .eqIfPresent(ParkArrearsRecoveryDO::getRecoveryUserId, reqVO.getRecoveryUserId())
                .eqIfPresent(ParkArrearsRecoveryDO::getRecoveryStatus, reqVO.getRecoveryStatus())
                .eqIfPresent(ParkArrearsRecoveryDO::getRecoveryMethod, reqVO.getRecoveryMethod())
                .eqIfPresent(ParkArrearsRecoveryDO::getLimitMeasure, reqVO.getLimitMeasure())
                .likeIfPresent(ParkArrearsRecoveryDO::getRegionName, reqVO.getRegionName())
                .likeIfPresent(ParkArrearsRecoveryDO::getGridName, reqVO.getGridName())
                .eqIfPresent(ParkArrearsRecoveryDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(ParkArrearsRecoveryDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkArrearsRecoveryDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(ParkArrearsRecoveryDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(ParkArrearsRecoveryDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkArrearsRecoveryDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(ParkArrearsRecoveryDO::getId));
    }

}
