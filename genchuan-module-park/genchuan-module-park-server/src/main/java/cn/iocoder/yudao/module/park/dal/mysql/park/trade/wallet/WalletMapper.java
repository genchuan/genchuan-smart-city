package cn.iocoder.yudao.module.park.dal.mysql.park.trade.wallet;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.wallet.vo.WalletPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.trade.wallet.WalletDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户钱包 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface WalletMapper extends BaseMapperX<WalletDO> {

    default PageResult<WalletDO> selectPage(WalletPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WalletDO>()
                .eqIfPresent(WalletDO::getUserId, reqVO.getUserId())
                .eqIfPresent(WalletDO::getBalance, reqVO.getBalance())
                .eqIfPresent(WalletDO::getStatus, reqVO.getStatus())
                .eqIfPresent(WalletDO::getVersion, reqVO.getVersion())
                .betweenIfPresent(WalletDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(WalletDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(WalletDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(WalletDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(WalletDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(WalletDO::getId));
    }

}
