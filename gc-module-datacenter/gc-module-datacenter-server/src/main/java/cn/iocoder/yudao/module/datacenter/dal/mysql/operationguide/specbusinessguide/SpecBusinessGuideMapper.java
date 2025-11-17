package cn.iocoder.yudao.module.datacenter.dal.mysql.operationguide.specbusinessguide;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.specbusinessguide.vo.SpecBusinessGuidePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.operationguide.specbusinessguide.SpecBusinessGuideDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 专项业务指南 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface SpecBusinessGuideMapper extends BaseMapperX<SpecBusinessGuideDO> {

    default PageResult<SpecBusinessGuideDO> selectPage(SpecBusinessGuidePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SpecBusinessGuideDO>()
                .eqIfPresent(SpecBusinessGuideDO::getSpecBusinessGuideId, reqVO.getSpecBusinessGuideId())
                .eqIfPresent(SpecBusinessGuideDO::getGuideTitle, reqVO.getGuideTitle())
                .eqIfPresent(SpecBusinessGuideDO::getBusinessFieldCode, reqVO.getBusinessFieldCode())
                .likeIfPresent(SpecBusinessGuideDO::getBusinessFieldName, reqVO.getBusinessFieldName())
                .likeIfPresent(SpecBusinessGuideDO::getRelatedTableName, reqVO.getRelatedTableName())
                .eqIfPresent(SpecBusinessGuideDO::getRelatedTableCode, reqVO.getRelatedTableCode())
                .eqIfPresent(SpecBusinessGuideDO::getBusinessProcessDesc, reqVO.getBusinessProcessDesc())
                .eqIfPresent(SpecBusinessGuideDO::getOperSteps, reqVO.getOperSteps())
                .eqIfPresent(SpecBusinessGuideDO::getDataSpec, reqVO.getDataSpec())
                .eqIfPresent(SpecBusinessGuideDO::getApplicableRole, reqVO.getApplicableRole())
                .eqIfPresent(SpecBusinessGuideDO::getGuideAttachId, reqVO.getGuideAttachId())
                .eqIfPresent(SpecBusinessGuideDO::getGuideAttachPath, reqVO.getGuideAttachPath())
                .eqIfPresent(SpecBusinessGuideDO::getGuideStatus, reqVO.getGuideStatus())
                .eqIfPresent(SpecBusinessGuideDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(SpecBusinessGuideDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(SpecBusinessGuideDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(SpecBusinessGuideDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(SpecBusinessGuideDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(SpecBusinessGuideDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(SpecBusinessGuideDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(SpecBusinessGuideDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(SpecBusinessGuideDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(SpecBusinessGuideDO::getId));
    }

}