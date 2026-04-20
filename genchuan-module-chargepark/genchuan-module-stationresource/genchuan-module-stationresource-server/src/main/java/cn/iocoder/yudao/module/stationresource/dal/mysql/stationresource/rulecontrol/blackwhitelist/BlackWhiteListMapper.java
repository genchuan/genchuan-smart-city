package cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.rulecontrol.blackwhitelist;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

/**
 * 黑白名单 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface BlackWhiteListMapper extends BaseMapperX<BlackWhiteListDO> {

    default PageResult<BlackWhiteListDO> selectPage(BlackWhiteListPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BlackWhiteListDO>()
                .eqIfPresent(BlackWhiteListDO::getPlateNo, reqVO.getPlateNo())
                .eqIfPresent(BlackWhiteListDO::getType, reqVO.getType())
                .eqIfPresent(BlackWhiteListDO::getSubType, reqVO.getSubType())
                .betweenIfPresent(BlackWhiteListDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(BlackWhiteListDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(BlackWhiteListDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(BlackWhiteListDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(BlackWhiteListDO::getAuditUserId, reqVO.getAuditUserId())
                .eqIfPresent(BlackWhiteListDO::getInterceptCount, reqVO.getInterceptCount())
                .eqIfPresent(BlackWhiteListDO::getCertInfo, reqVO.getCertInfo())
                .eqIfPresent(BlackWhiteListDO::getRemark, reqVO.getRemark())
                .eqIfPresent(BlackWhiteListDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(BlackWhiteListDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(BlackWhiteListDO::getCreator, reqVO.getCreator())
                .eqIfPresent(BlackWhiteListDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(BlackWhiteListDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(BlackWhiteListDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(BlackWhiteListDO::getId));
    }

}
