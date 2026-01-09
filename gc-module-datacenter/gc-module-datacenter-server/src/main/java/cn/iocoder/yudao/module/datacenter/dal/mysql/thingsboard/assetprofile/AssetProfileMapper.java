package cn.iocoder.yudao.module.datacenter.dal.mysql.thingsboard.assetprofile;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.assetprofile.vo.AssetProfilePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.thingsboard.assetprofile.AssetProfileDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资产配置信息 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface AssetProfileMapper extends BaseMapperX<AssetProfileDO> {

    default PageResult<AssetProfileDO> selectPage(AssetProfilePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AssetProfileDO>()
                .eqIfPresent(AssetProfileDO::getProfileId, reqVO.getProfileId())
                .eqIfPresent(AssetProfileDO::getEntityType, reqVO.getEntityType())
                .betweenIfPresent(AssetProfileDO::getCreatedTime, reqVO.getCreatedTime())
                .eqIfPresent(AssetProfileDO::getTenantIdTb, reqVO.getTenantIdTb())
                .eqIfPresent(AssetProfileDO::getTenantEntityType, reqVO.getTenantEntityType())
                .likeIfPresent(AssetProfileDO::getProfileName, reqVO.getProfileName())
                .eqIfPresent(AssetProfileDO::getProfileDescription, reqVO.getProfileDescription())
                .eqIfPresent(AssetProfileDO::getProfileImage, reqVO.getProfileImage())
                .eqIfPresent(AssetProfileDO::getDefaultRuleChainId, reqVO.getDefaultRuleChainId())
                .eqIfPresent(AssetProfileDO::getDefaultDashboardId, reqVO.getDefaultDashboardId())
                .likeIfPresent(AssetProfileDO::getDefaultQueueName, reqVO.getDefaultQueueName())
                .eqIfPresent(AssetProfileDO::getDefaultEdgeRuleChainId, reqVO.getDefaultEdgeRuleChainId())
                .eqIfPresent(AssetProfileDO::getExternalId, reqVO.getExternalId())
                .eqIfPresent(AssetProfileDO::getExternalEntityType, reqVO.getExternalEntityType())
                .eqIfPresent(AssetProfileDO::getVersion, reqVO.getVersion())
                .eqIfPresent(AssetProfileDO::getIsDefault, reqVO.getIsDefault())
                .betweenIfPresent(AssetProfileDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(AssetProfileDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AssetProfileDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(AssetProfileDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(AssetProfileDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(AssetProfileDO::getId));
    }

//    default AssetProfileDO selectByProfileId(String profileId) {
//        return selectOne(AssetProfileDO::getProfileId, profileId);
//    }

    /**
     * 根据资产配置ID查询
     */
    AssetProfileDO selectByProfileId(String profileId);

}