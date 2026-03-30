package cn.iocoder.yudao.module.park.dal.mysql.park.trade.walletflow;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.walletflow.vo.WalletFlowPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.trade.walletflow.WalletFlowDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 钱包流水 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface WalletFlowMapper extends BaseMapperX<WalletFlowDO> {

    default PageResult<WalletFlowDO> selectPage(WalletFlowPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WalletFlowDO>()
                .eqIfPresent(WalletFlowDO::getWalletId, reqVO.getWalletId())
                .eqIfPresent(WalletFlowDO::getUserId, reqVO.getUserId())
                .eqIfPresent(WalletFlowDO::getTradeCode, reqVO.getTradeCode())
                .betweenIfPresent(WalletFlowDO::getTradeFinishTime, reqVO.getTradeFinishTime())
                .eqIfPresent(WalletFlowDO::getAmount, reqVO.getAmount())
                .eqIfPresent(WalletFlowDO::getBalanceAfter, reqVO.getBalanceAfter())
                .eqIfPresent(WalletFlowDO::getFlowDesc, reqVO.getFlowDesc())
                .betweenIfPresent(WalletFlowDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(WalletFlowDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(WalletFlowDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(WalletFlowDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(WalletFlowDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(WalletFlowDO::getId));
    }

}
