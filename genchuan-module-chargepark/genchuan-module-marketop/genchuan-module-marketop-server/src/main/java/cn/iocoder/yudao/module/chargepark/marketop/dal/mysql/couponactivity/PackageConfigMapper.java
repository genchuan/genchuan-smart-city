package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.couponactivity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.packageconfig.vo.PackageConfigPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.PackageConfigDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PackageConfigMapper extends BaseMapperX<PackageConfigDO> {

    default PageResult<PackageConfigDO> selectPage(PackageConfigPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PackageConfigDO>()
                .likeIfPresent(PackageConfigDO::getName, reqVO.getName())
                .eqIfPresent(PackageConfigDO::getType, reqVO.getType())
                .eqIfPresent(PackageConfigDO::getStatus, reqVO.getStatus())
                .eqIfPresent(PackageConfigDO::getScope, reqVO.getScope())
                .orderByDesc(PackageConfigDO::getId));
    }

}
