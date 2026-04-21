package cn.iocoder.yudao.module.ordertrade.dal.mysql.paymgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo.PayWalletPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt.PayWalletDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PayWalletMapper extends BaseMapperX<PayWalletDO> {

    default PageResult<PayWalletDO> selectPage(PayWalletPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PayWalletDO>()
                .eqIfPresent(PayWalletDO::getUserId, reqVO.getUserId())
                .eqIfPresent(PayWalletDO::getUserType, reqVO.getUserType())
                .eqIfPresent(PayWalletDO::getStatus, reqVO.getStatus())
                .orderByDesc(PayWalletDO::getId));
    }

    @Select("SELECT IFNULL(SUM(balance), 0) FROM pay_wallet WHERE status = 0")
    Long selectTotalBalance();

    @Select("SELECT IFNULL(SUM(balance), 0) FROM pay_wallet WHERE status = 0 AND create_time BETWEEN #{startTime} AND #{endTime}")
    Long selectRechargeAmount(@org.apache.ibatis.annotations.Param("startTime") java.time.LocalDateTime startTime,
                              @org.apache.ibatis.annotations.Param("endTime") java.time.LocalDateTime endTime);
}
