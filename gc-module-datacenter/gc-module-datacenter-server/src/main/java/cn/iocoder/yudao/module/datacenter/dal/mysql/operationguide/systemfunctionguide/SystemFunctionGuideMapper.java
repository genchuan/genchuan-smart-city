package cn.iocoder.yudao.module.datacenter.dal.mysql.operationguide.systemfunctionguide;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.systemfunctionguide.vo.SystemFunctionGuidePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.operationguide.systemfunctionguide.SystemFunctionGuideDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统功能指南 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface SystemFunctionGuideMapper extends BaseMapperX<SystemFunctionGuideDO> {

    default PageResult<SystemFunctionGuideDO> selectPage(SystemFunctionGuidePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SystemFunctionGuideDO>()
                .eqIfPresent(SystemFunctionGuideDO::getSystemFunctionGuideId, reqVO.getSystemFunctionGuideId())
                .eqIfPresent(SystemFunctionGuideDO::getGuideTitle, reqVO.getGuideTitle())
                .eqIfPresent(SystemFunctionGuideDO::getRelatedFunctionId, reqVO.getRelatedFunctionId())
                .likeIfPresent(SystemFunctionGuideDO::getRelatedFunctionName, reqVO.getRelatedFunctionName())
                .eqIfPresent(SystemFunctionGuideDO::getFunctionPurpose, reqVO.getFunctionPurpose())
                .eqIfPresent(SystemFunctionGuideDO::getOperSteps, reqVO.getOperSteps())
                .eqIfPresent(SystemFunctionGuideDO::getCommonProblems, reqVO.getCommonProblems())
                .eqIfPresent(SystemFunctionGuideDO::getApplicableRole, reqVO.getApplicableRole())
                .eqIfPresent(SystemFunctionGuideDO::getGuideAttachId, reqVO.getGuideAttachId())
                .eqIfPresent(SystemFunctionGuideDO::getGuideAttachPath, reqVO.getGuideAttachPath())
                .eqIfPresent(SystemFunctionGuideDO::getGuideStatus, reqVO.getGuideStatus())
                .eqIfPresent(SystemFunctionGuideDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(SystemFunctionGuideDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(SystemFunctionGuideDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(SystemFunctionGuideDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(SystemFunctionGuideDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(SystemFunctionGuideDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(SystemFunctionGuideDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(SystemFunctionGuideDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(SystemFunctionGuideDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(SystemFunctionGuideDO::getId));
    }

}