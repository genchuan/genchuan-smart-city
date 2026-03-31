package cn.iocoder.yudao.module.vehiclecharging.dal.mysql.interconnection;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.interconnection.InterconnectionDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.interconnection.vo.*;

/**
 * 互联互通表 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface InterconnectionMapper extends BaseMapperX<InterconnectionDO> {

    default PageResult<InterconnectionDO> selectPage(InterconnectionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InterconnectionDO>()
                .eqIfPresent(InterconnectionDO::getConnectCode, reqVO.getConnectCode())
                .eqIfPresent(InterconnectionDO::getThirdPlatform, reqVO.getThirdPlatform())
                .eqIfPresent(InterconnectionDO::getConnectType, reqVO.getConnectType())
                .eqIfPresent(InterconnectionDO::getApiParam, reqVO.getApiParam())
                .eqIfPresent(InterconnectionDO::getSyncFreq, reqVO.getSyncFreq())
                .eqIfPresent(InterconnectionDO::getSyncSuccessRate, reqVO.getSyncSuccessRate())
                .eqIfPresent(InterconnectionDO::getConnectStatus, reqVO.getConnectStatus())
                .eqIfPresent(InterconnectionDO::getAuditUser, reqVO.getAuditUser())
                .betweenIfPresent(InterconnectionDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(InterconnectionDO::getAuditRemark, reqVO.getAuditRemark())
                .eqIfPresent(InterconnectionDO::getCloseReason, reqVO.getCloseReason())
                .eqIfPresent(InterconnectionDO::getRemark, reqVO.getRemark())
                .eqIfPresent(InterconnectionDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(InterconnectionDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(InterconnectionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InterconnectionDO::getId));
    }

    Integer selectTotalCount();
}