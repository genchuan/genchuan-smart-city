package cn.iocoder.yudao.module.industry.dal.mysql.park.discount.parkrechargepackage;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkrechargepackage.vo.ParkRechargePackagePageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkrechargepackage.ParkRechargePackageDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 充值套餐 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkRechargePackageMapper extends BaseMapperX<ParkRechargePackageDO> {

    default PageResult<ParkRechargePackageDO> selectPage(ParkRechargePackagePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkRechargePackageDO>()
                .likeIfPresent(ParkRechargePackageDO::getPackageName, reqVO.getPackageName())
                .eqIfPresent(ParkRechargePackageDO::getRechargeAmount, reqVO.getRechargeAmount())
                .eqIfPresent(ParkRechargePackageDO::getGiveAmount, reqVO.getGiveAmount())
                .betweenIfPresent(ParkRechargePackageDO::getGiveTime, reqVO.getGiveTime())
                .eqIfPresent(ParkRechargePackageDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ParkRechargePackageDO::getSalesCount, reqVO.getSalesCount())
                .betweenIfPresent(ParkRechargePackageDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkRechargePackageDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ParkRechargePackageDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkRechargePackageDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkRechargePackageDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkRechargePackageDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(ParkRechargePackageDO::getId));
    }

}
