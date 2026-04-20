package cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.rulecontrol.chargeparklink;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

/**
 * 充停联动 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ChargeParkLinkMapper extends BaseMapperX<ChargeParkLinkDO> {

    default PageResult<ChargeParkLinkDO> selectPage(ChargeParkLinkPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ChargeParkLinkDO>()
                .eqIfPresent(ChargeParkLinkDO::getStationId, reqVO.getStationId())
                .eqIfPresent(ChargeParkLinkDO::getDiscountType, reqVO.getDiscountType())
                .eqIfPresent(ChargeParkLinkDO::getDiscount, reqVO.getDiscount())
                .eqIfPresent(ChargeParkLinkDO::getCarType, reqVO.getCarType())
                .eqIfPresent(ChargeParkLinkDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ChargeParkLinkDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(ChargeParkLinkDO::getAuditUserId, reqVO.getAuditUserId())
                .eqIfPresent(ChargeParkLinkDO::getTodayOrderCount, reqVO.getTodayOrderCount())
                .eqIfPresent(ChargeParkLinkDO::getTodayIncome, reqVO.getTodayIncome())
                .eqIfPresent(ChargeParkLinkDO::getPayRate, reqVO.getPayRate())
                .eqIfPresent(ChargeParkLinkDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ChargeParkLinkDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(ChargeParkLinkDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(ChargeParkLinkDO::getCreator, reqVO.getCreator())
                .eqIfPresent(ChargeParkLinkDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(ChargeParkLinkDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(ChargeParkLinkDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(ChargeParkLinkDO::getId));
    }

}
