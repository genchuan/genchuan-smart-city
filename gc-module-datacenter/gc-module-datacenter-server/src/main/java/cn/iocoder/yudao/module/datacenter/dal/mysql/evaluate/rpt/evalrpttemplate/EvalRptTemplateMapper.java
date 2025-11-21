package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.rpt.evalrpttemplate;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrpttemplate.vo.EvalRptTemplatePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rpt.evalrpttemplate.EvalRptTemplateDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 报告模板配置 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalRptTemplateMapper extends BaseMapperX<EvalRptTemplateDO> {

    default PageResult<EvalRptTemplateDO> selectPage(EvalRptTemplatePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalRptTemplateDO>()
                .eqIfPresent(EvalRptTemplateDO::getRptTemplateId, reqVO.getRptTemplateId())
                .likeIfPresent(EvalRptTemplateDO::getRptTemplateName, reqVO.getRptTemplateName())
                .eqIfPresent(EvalRptTemplateDO::getRptTemplateCode, reqVO.getRptTemplateCode())
                .eqIfPresent(EvalRptTemplateDO::getApplyTaskType, reqVO.getApplyTaskType())
                .eqIfPresent(EvalRptTemplateDO::getTemplateFilePath, reqVO.getTemplateFilePath())
                .eqIfPresent(EvalRptTemplateDO::getRptStructure, reqVO.getRptStructure())
                .eqIfPresent(EvalRptTemplateDO::getDynamicFieldMap, reqVO.getDynamicFieldMap())
                .eqIfPresent(EvalRptTemplateDO::getFixedContent, reqVO.getFixedContent())
                .eqIfPresent(EvalRptTemplateDO::getTemplateVersion, reqVO.getTemplateVersion())
                .eqIfPresent(EvalRptTemplateDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(EvalRptTemplateDO::getCreateUserBiz, reqVO.getCreateUserBiz())
                .eqIfPresent(EvalRptTemplateDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(EvalRptTemplateDO::getUpdateUserBiz, reqVO.getUpdateUserBiz())
                .eqIfPresent(EvalRptTemplateDO::getUpdateTimeBiz, reqVO.getUpdateTimeBiz())
                .eqIfPresent(EvalRptTemplateDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalRptTemplateDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalRptTemplateDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalRptTemplateDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalRptTemplateDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalRptTemplateDO::getId));
    }

}