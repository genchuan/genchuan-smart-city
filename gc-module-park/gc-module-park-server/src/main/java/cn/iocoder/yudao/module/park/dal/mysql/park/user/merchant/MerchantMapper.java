package cn.iocoder.yudao.module.park.dal.mysql.park.user.merchant;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.user.merchant.vo.MerchantPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.merchant.MerchantDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商户 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface MerchantMapper extends BaseMapperX<MerchantDO> {

    default PageResult<MerchantDO> selectPage(MerchantPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MerchantDO>()
                .likeIfPresent(MerchantDO::getMerchantName, reqVO.getMerchantName())
                .eqIfPresent(MerchantDO::getMerchantCode, reqVO.getMerchantCode())
                .eqIfPresent(MerchantDO::getContactPerson, reqVO.getContactPerson())
                .eqIfPresent(MerchantDO::getContactPhone, reqVO.getContactPhone())
                .eqIfPresent(MerchantDO::getAddress, reqVO.getAddress())
                .eqIfPresent(MerchantDO::getBusinessScope, reqVO.getBusinessScope())
                .eqIfPresent(MerchantDO::getRegionCode, reqVO.getRegionCode())
                .eqIfPresent(MerchantDO::getCreditCode, reqVO.getCreditCode())
                .eqIfPresent(MerchantDO::getStatus, reqVO.getStatus())
                .eqIfPresent(MerchantDO::getSettlementAccount, reqVO.getSettlementAccount())
                .betweenIfPresent(MerchantDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(MerchantDO::getRemark, reqVO.getRemark())
                .eqIfPresent(MerchantDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(MerchantDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(MerchantDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(MerchantDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(MerchantDO::getId));
    }

}
