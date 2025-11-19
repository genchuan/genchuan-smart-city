package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.task.evaltasktemplate;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.task.evaltasktemplate.vo.EvalTaskTemplatePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.task.evaltasktemplate.EvalTaskTemplateDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 任务模板管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalTaskTemplateMapper extends BaseMapperX<EvalTaskTemplateDO> {

    default PageResult<EvalTaskTemplateDO> selectPage(EvalTaskTemplatePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalTaskTemplateDO>()
                .eqIfPresent(EvalTaskTemplateDO::getTaskTemplateId, reqVO.getTaskTemplateId())
                .likeIfPresent(EvalTaskTemplateDO::getTaskTemplateName, reqVO.getTaskTemplateName())
                .eqIfPresent(EvalTaskTemplateDO::getTaskTemplateCode, reqVO.getTaskTemplateCode())
                .eqIfPresent(EvalTaskTemplateDO::getApplyObjectType, reqVO.getApplyObjectType())
                .eqIfPresent(EvalTaskTemplateDO::getIdxSystemId, reqVO.getIdxSystemId())
                .likeIfPresent(EvalTaskTemplateDO::getIdxSystemName, reqVO.getIdxSystemName())
                .eqIfPresent(EvalTaskTemplateDO::getEvalSubjectId, reqVO.getEvalSubjectId())
                .likeIfPresent(EvalTaskTemplateDO::getEvalSubjectName, reqVO.getEvalSubjectName())
                .eqIfPresent(EvalTaskTemplateDO::getTaskCycle, reqVO.getTaskCycle())
                .eqIfPresent(EvalTaskTemplateDO::getCustomCycle, reqVO.getCustomCycle())
                .eqIfPresent(EvalTaskTemplateDO::getTemplateDesc, reqVO.getTemplateDesc())
                .eqIfPresent(EvalTaskTemplateDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(EvalTaskTemplateDO::getCreateUserBiz, reqVO.getCreateUserBiz())
                .eqIfPresent(EvalTaskTemplateDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(EvalTaskTemplateDO::getUpdateUserBiz, reqVO.getUpdateUserBiz())
                .eqIfPresent(EvalTaskTemplateDO::getUpdateTimeBiz, reqVO.getUpdateTimeBiz())
                .eqIfPresent(EvalTaskTemplateDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalTaskTemplateDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalTaskTemplateDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalTaskTemplateDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalTaskTemplateDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalTaskTemplateDO::getId));
    }

}