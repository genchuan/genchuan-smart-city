package cn.iocoder.yudao.module.datacenter.dal.mysql.operationguide.generaloperguide;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.generaloperguide.vo.GeneralOperGuidePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.operationguide.generaloperguide.GeneralOperGuideDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 通用操作指南 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface GeneralOperGuideMapper extends BaseMapperX<GeneralOperGuideDO> {

    default PageResult<GeneralOperGuideDO> selectPage(GeneralOperGuidePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GeneralOperGuideDO>()
                .eqIfPresent(GeneralOperGuideDO::getGeneralOperGuideId, reqVO.getGeneralOperGuideId())
                .eqIfPresent(GeneralOperGuideDO::getGuideTitle, reqVO.getGuideTitle())
                .eqIfPresent(GeneralOperGuideDO::getOperScenario, reqVO.getOperScenario())
                .eqIfPresent(GeneralOperGuideDO::getOperSteps, reqVO.getOperSteps())
                .eqIfPresent(GeneralOperGuideDO::getApplicableRole, reqVO.getApplicableRole())
                .eqIfPresent(GeneralOperGuideDO::getVersion, reqVO.getVersion())
                .betweenIfPresent(GeneralOperGuideDO::getEffectiveTime, reqVO.getEffectiveTime())
                .eqIfPresent(GeneralOperGuideDO::getGuideAttachId, reqVO.getGuideAttachId())
                .eqIfPresent(GeneralOperGuideDO::getGuideAttachPath, reqVO.getGuideAttachPath())
                .eqIfPresent(GeneralOperGuideDO::getGuideStatus, reqVO.getGuideStatus())
                .eqIfPresent(GeneralOperGuideDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(GeneralOperGuideDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(GeneralOperGuideDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(GeneralOperGuideDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(GeneralOperGuideDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(GeneralOperGuideDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(GeneralOperGuideDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(GeneralOperGuideDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(GeneralOperGuideDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(GeneralOperGuideDO::getId));
    }

}