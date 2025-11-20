package cn.iocoder.yudao.module.datacenter.dal.mysql.thingsboard.asset;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.thingsboard.asset.AssetDO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo.AssetPageReqVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资产 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface AssetMapper extends BaseMapperX<AssetDO> {

    default PageResult<AssetDO> selectPage(AssetPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AssetDO>()
                .eqIfPresent(AssetDO::getTenantId, reqVO.getTenantId())
                .eqIfPresent(AssetDO::getCustomerId, reqVO.getCustomerId())
                .likeIfPresent(AssetDO::getName, reqVO.getName())
                .eqIfPresent(AssetDO::getType, reqVO.getType())
                .eqIfPresent(AssetDO::getLabel, reqVO.getLabel())
                .eqIfPresent(AssetDO::getAssetProfileId, reqVO.getAssetProfileId())
                .eqIfPresent(AssetDO::getAdditionalInfo, reqVO.getAdditionalInfo())
                .eqIfPresent(AssetDO::getExternalId, reqVO.getExternalId())
                .eqIfPresent(AssetDO::getVersion, reqVO.getVersion())
                .betweenIfPresent(AssetDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AssetDO::getId));
    }

}