package cn.iocoder.yudao.module.industry.dal.mysql.park.user.parkmerchant;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmerchant.vo.ParkMerchantPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkmerchant.ParkMerchantDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商户 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkMerchantMapper extends BaseMapperX<ParkMerchantDO> {

    default PageResult<ParkMerchantDO> selectPage(ParkMerchantPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkMerchantDO>()
                .likeIfPresent(ParkMerchantDO::getMerchantName, reqVO.getMerchantName())
                .eqIfPresent(ParkMerchantDO::getMerchantCode, reqVO.getMerchantCode())
                .eqIfPresent(ParkMerchantDO::getContactPerson, reqVO.getContactPerson())
                .eqIfPresent(ParkMerchantDO::getContactPhone, reqVO.getContactPhone())
                .eqIfPresent(ParkMerchantDO::getAddress, reqVO.getAddress())
                .eqIfPresent(ParkMerchantDO::getBusinessScope, reqVO.getBusinessScope())
                .eqIfPresent(ParkMerchantDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ParkMerchantDO::getSettlementRatio, reqVO.getSettlementRatio())
                .betweenIfPresent(ParkMerchantDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkMerchantDO::getCreateBy, reqVO.getCreateBy())
                .eqIfPresent(ParkMerchantDO::getUpdateBy, reqVO.getUpdateBy())
                .eqIfPresent(ParkMerchantDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ParkMerchantDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkMerchantDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkMerchantDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkMerchantDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(ParkMerchantDO::getId));
    }

}
