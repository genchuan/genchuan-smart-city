package cn.iocoder.yudao.module.park.dal.mysql.park.user.merchantpermission;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.user.merchantpermission.vo.MerchantPermissionPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.merchantpermission.MerchantPermissionDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商户权限 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface MerchantPermissionMapper extends BaseMapperX<MerchantPermissionDO> {

    default PageResult<MerchantPermissionDO> selectPage(MerchantPermissionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MerchantPermissionDO>()
                .eqIfPresent(MerchantPermissionDO::getMerchantId, reqVO.getMerchantId())
                .eqIfPresent(MerchantPermissionDO::getPermId, reqVO.getPermId())
                .eqIfPresent(MerchantPermissionDO::getPermCode, reqVO.getPermCode())
                .likeIfPresent(MerchantPermissionDO::getPermName, reqVO.getPermName())
                .betweenIfPresent(MerchantPermissionDO::getEffectTime, reqVO.getEffectTime())
                .betweenIfPresent(MerchantPermissionDO::getExpireTime, reqVO.getExpireTime())
                .eqIfPresent(MerchantPermissionDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(MerchantPermissionDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(MerchantPermissionDO::getRemark, reqVO.getRemark())
                .eqIfPresent(MerchantPermissionDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(MerchantPermissionDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(MerchantPermissionDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(MerchantPermissionDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(MerchantPermissionDO::getId));
    }

}
