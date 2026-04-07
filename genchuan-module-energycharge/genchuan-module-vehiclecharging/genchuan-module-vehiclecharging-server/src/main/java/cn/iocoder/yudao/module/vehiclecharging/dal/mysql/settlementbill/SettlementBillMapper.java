package cn.iocoder.yudao.module.vehiclecharging.dal.mysql.settlementbill;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo.SettlementBillPageReqVO;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.settlementbill.SettlementBillDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 结算单 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface SettlementBillMapper extends BaseMapperX<SettlementBillDO> {

    default PageResult<SettlementBillDO> selectPage(SettlementBillPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SettlementBillDO>()
                .eqIfPresent(SettlementBillDO::getBillCode, reqVO.getBillCode())
                .eqIfPresent(SettlementBillDO::getCooperator, reqVO.getCooperator())
                .eqIfPresent(SettlementBillDO::getSettlementCycle, reqVO.getSettlementCycle())
                .eqIfPresent(SettlementBillDO::getSettlementAmount, reqVO.getSettlementAmount())
                .eqIfPresent(SettlementBillDO::getSharingAmount, reqVO.getSharingAmount())
                .eqIfPresent(SettlementBillDO::getBillStatus, reqVO.getBillStatus())
                .eqIfPresent(SettlementBillDO::getAuditUser, reqVO.getAuditUser())
                .betweenIfPresent(SettlementBillDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(SettlementBillDO::getAuditRemark, reqVO.getAuditRemark())
                .betweenIfPresent(SettlementBillDO::getSettlementTime, reqVO.getSettlementTime())
                .eqIfPresent(SettlementBillDO::getSettlementChannel, reqVO.getSettlementChannel())
                .eqIfPresent(SettlementBillDO::getRemark, reqVO.getRemark())
                .eqIfPresent(SettlementBillDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(SettlementBillDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(SettlementBillDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SettlementBillDO::getId));
    }

}
