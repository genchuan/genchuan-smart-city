package cn.iocoder.yudao.module.park.dal.mysql.park.pricing.rechargepackage;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.rechargepackage.vo.RechargePackagePageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.rechargepackage.RechargePackageDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 充值套餐 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface RechargePackageMapper extends BaseMapperX<RechargePackageDO> {

    default PageResult<RechargePackageDO> selectPage(RechargePackagePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RechargePackageDO>()
                .likeIfPresent(RechargePackageDO::getPackageName, reqVO.getPackageName())
                .eqIfPresent(RechargePackageDO::getRechargeAmount, reqVO.getRechargeAmount())
                .eqIfPresent(RechargePackageDO::getGiveAmount, reqVO.getGiveAmount())
                .eqIfPresent(RechargePackageDO::getValidDays, reqVO.getValidDays())
                .eqIfPresent(RechargePackageDO::getPackageType, reqVO.getPackageType())
                .eqIfPresent(RechargePackageDO::getStatus, reqVO.getStatus())
                .eqIfPresent(RechargePackageDO::getSalesCount, reqVO.getSalesCount())
                .betweenIfPresent(RechargePackageDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(RechargePackageDO::getRemark, reqVO.getRemark())
                .eqIfPresent(RechargePackageDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(RechargePackageDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(RechargePackageDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(RechargePackageDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(RechargePackageDO::getId));
    }

}
