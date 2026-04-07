package cn.iocoder.yudao.module.vehiclecharging.dal.mysql.sharingratio;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.sharingratio.SharingRatioDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingratio.vo.*;

/**
 * 分账比例 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface SharingRatioMapper extends BaseMapperX<SharingRatioDO> {

    default PageResult<SharingRatioDO> selectPage(SharingRatioPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SharingRatioDO>()
                .eqIfPresent(SharingRatioDO::getSharingCode, reqVO.getSharingCode())
                .likeIfPresent(SharingRatioDO::getSharingName, reqVO.getSharingName())
                .eqIfPresent(SharingRatioDO::getCooperator, reqVO.getCooperator())
                .eqIfPresent(SharingRatioDO::getSharingType, reqVO.getSharingType())
                .eqIfPresent(SharingRatioDO::getSharingRatio, reqVO.getSharingRatio())
                .eqIfPresent(SharingRatioDO::getApplyStation, reqVO.getApplyStation())
                .eqIfPresent(SharingRatioDO::getApplyChannel, reqVO.getApplyChannel())
                .betweenIfPresent(SharingRatioDO::getEffectTime, reqVO.getEffectTime())
                .betweenIfPresent(SharingRatioDO::getExpireTime, reqVO.getExpireTime())
                .eqIfPresent(SharingRatioDO::getSharingStatus, reqVO.getSharingStatus())
                .eqIfPresent(SharingRatioDO::getRemark, reqVO.getRemark())
                .eqIfPresent(SharingRatioDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(SharingRatioDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(SharingRatioDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SharingRatioDO::getId));
    }

}