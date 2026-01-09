package cn.iocoder.yudao.module.industry.dal.mysql.park.order.parksettlement;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;

import cn.iocoder.yudao.module.industry.controller.admin.park.order.parksettlement.vo.ParkSettlementPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parksettlement.ParkSettlementDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 分账结算表 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkSettlementMapper extends BaseMapperX<ParkSettlementDO> {

    default PageResult<ParkSettlementDO> selectPage(ParkSettlementPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkSettlementDO>()
                .eqIfPresent(ParkSettlementDO::getMerchantId, reqVO.getMerchantId())
                .eqIfPresent(ParkSettlementDO::getTotalAmount, reqVO.getTotalAmount())
                .eqIfPresent(ParkSettlementDO::getSplitRatio, reqVO.getSplitRatio())
                .eqIfPresent(ParkSettlementDO::getPlatformAmount, reqVO.getPlatformAmount())
                .eqIfPresent(ParkSettlementDO::getMerchantAmount, reqVO.getMerchantAmount())
                .eqIfPresent(ParkSettlementDO::getTaxAmount, reqVO.getTaxAmount())
                .eqIfPresent(ParkSettlementDO::getSettlementStatus, reqVO.getSettlementStatus())
                .betweenIfPresent(ParkSettlementDO::getGenerateTime, reqVO.getGenerateTime())
                .betweenIfPresent(ParkSettlementDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(ParkSettlementDO::getAuditBy, reqVO.getAuditBy())
                .betweenIfPresent(ParkSettlementDO::getTransferTime, reqVO.getTransferTime())
                .eqIfPresent(ParkSettlementDO::getStatCode, reqVO.getStatCode())
                .eqIfPresent(ParkSettlementDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(ParkSettlementDO::getStatCycleName, reqVO.getStatCycleName())
                .betweenIfPresent(ParkSettlementDO::getStatStartTime, reqVO.getStatStartTime())
                .betweenIfPresent(ParkSettlementDO::getStatEndTime, reqVO.getStatEndTime())
                .betweenIfPresent(ParkSettlementDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(ParkSettlementDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ParkSettlementDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkSettlementDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkSettlementDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkSettlementDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(ParkSettlementDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ParkSettlementDO::getId));
    }

}
