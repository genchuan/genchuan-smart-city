package cn.iocoder.yudao.module.usermerchant.dal.mysql.merchantmgmt.merchantinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.merchantmgmt.merchantinfo.MerchantInfoDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantinfo.vo.*;

/**
 * 商户信息 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface MerchantInfoMapper extends BaseMapperX<MerchantInfoDO> {

    default PageResult<MerchantInfoDO> selectPage(MerchantInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MerchantInfoDO>()
                .likeIfPresent(MerchantInfoDO::getName, reqVO.getName())
                .eqIfPresent(MerchantInfoDO::getContact, reqVO.getContact())
                .eqIfPresent(MerchantInfoDO::getPhone, reqVO.getPhone())
                .eqIfPresent(MerchantInfoDO::getMerchantType, reqVO.getMerchantType())
                .eqIfPresent(MerchantInfoDO::getAddress, reqVO.getAddress())
                .betweenIfPresent(MerchantInfoDO::getRegisterTime, reqVO.getRegisterTime())
                .eqIfPresent(MerchantInfoDO::getStatus, reqVO.getStatus())
                .eqIfPresent(MerchantInfoDO::getWalletBalance, reqVO.getWalletBalance())
                .eqIfPresent(MerchantInfoDO::getAuditorId, reqVO.getAuditorId())
                .betweenIfPresent(MerchantInfoDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(MerchantInfoDO::getRemark, reqVO.getRemark())
                .orderByDesc(MerchantInfoDO::getId));
    }

}