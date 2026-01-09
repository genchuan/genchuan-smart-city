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
                .eqIfPresent(AssetDO::getAssetId, reqVO.getAssetId())
                .eqIfPresent(AssetDO::getEntityType, reqVO.getEntityType())
                .betweenIfPresent(AssetDO::getCreatedTime, reqVO.getCreatedTime())
                .eqIfPresent(AssetDO::getTenantEntityType, reqVO.getTenantEntityType())
                .eqIfPresent(AssetDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(AssetDO::getCustomerEntityType, reqVO.getCustomerEntityType())
                .eqIfPresent(AssetDO::getAssetProfileId, reqVO.getAssetProfileId())
                .eqIfPresent(AssetDO::getAssetProfileEntityType, reqVO.getAssetProfileEntityType())
                .likeIfPresent(AssetDO::getAssetName, reqVO.getAssetName())
                .eqIfPresent(AssetDO::getAssetType, reqVO.getAssetType())
                .eqIfPresent(AssetDO::getAssetLabel, reqVO.getAssetLabel())
                .eqIfPresent(AssetDO::getExternalId, reqVO.getExternalId())
                .eqIfPresent(AssetDO::getVersion, reqVO.getVersion())
                .eqIfPresent(AssetDO::getCustomerTitle, reqVO.getCustomerTitle())
                .eqIfPresent(AssetDO::getCustomerIsPublic, reqVO.getCustomerIsPublic())
                .likeIfPresent(AssetDO::getAssetProfileName, reqVO.getAssetProfileName())
                .eqIfPresent(AssetDO::getAdditionalInfo, reqVO.getAdditionalInfo())
                .eqIfPresent(AssetDO::getAttributes, reqVO.getAttributes())
                .eqIfPresent(AssetDO::getContextDevices, reqVO.getContextDevices())
                .eqIfPresent(AssetDO::getContextAsset, reqVO.getContextAsset())
                .eqIfPresent(AssetDO::getTenantIdSys, reqVO.getTenantIdSys())
                .betweenIfPresent(AssetDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(AssetDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AssetDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(AssetDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(AssetDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(AssetDO::getId));
    }

    /**
     * 根据资产ID查询
     */
    AssetDO selectByAssetId(String assetId);

}