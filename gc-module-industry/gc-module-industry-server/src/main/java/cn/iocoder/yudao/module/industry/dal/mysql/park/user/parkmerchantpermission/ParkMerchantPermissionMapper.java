package cn.iocoder.yudao.module.industry.dal.mysql.park.user.parkmerchantpermission;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmerchantpermission.vo.ParkMerchantPermissionPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkmerchantpermission.ParkMerchantPermissionDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商户权限 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkMerchantPermissionMapper extends BaseMapperX<ParkMerchantPermissionDO> {

    default PageResult<ParkMerchantPermissionDO> selectPage(ParkMerchantPermissionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkMerchantPermissionDO>()
                .eqIfPresent(ParkMerchantPermissionDO::getMerchantId, reqVO.getMerchantId())
                .eqIfPresent(ParkMerchantPermissionDO::getPermCode, reqVO.getPermCode())
                .eqIfPresent(ParkMerchantPermissionDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ParkMerchantPermissionDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkMerchantPermissionDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkMerchantPermissionDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkMerchantPermissionDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkMerchantPermissionDO::getExtCommon4, reqVO.getExtCommon4())
                .eqIfPresent(ParkMerchantPermissionDO::getRemark, reqVO.getRemark())
                .orderByDesc(ParkMerchantPermissionDO::getId));
    }

}
