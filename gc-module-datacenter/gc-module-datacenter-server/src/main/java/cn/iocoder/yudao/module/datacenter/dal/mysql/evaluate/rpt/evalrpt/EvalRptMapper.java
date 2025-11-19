package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.rpt.evalrpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrpt.vo.EvalRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rpt.evalrpt.EvalRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评价报告 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalRptMapper extends BaseMapperX<EvalRptDO> {

    default PageResult<EvalRptDO> selectPage(EvalRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalRptDO>()
                .eqIfPresent(EvalRptDO::getEvalRptId, reqVO.getEvalRptId())
                .likeIfPresent(EvalRptDO::getEvalRptName, reqVO.getEvalRptName())
                .eqIfPresent(EvalRptDO::getEvalRptCode, reqVO.getEvalRptCode())
                .eqIfPresent(EvalRptDO::getRptTemplateId, reqVO.getRptTemplateId())
                .likeIfPresent(EvalRptDO::getRptTemplateName, reqVO.getRptTemplateName())
                .eqIfPresent(EvalRptDO::getResultArchId, reqVO.getResultArchId())
                .eqIfPresent(EvalRptDO::getEvalObjectId, reqVO.getEvalObjectId())
                .likeIfPresent(EvalRptDO::getEvalObjectName, reqVO.getEvalObjectName())
                .eqIfPresent(EvalRptDO::getRptFilePath, reqVO.getRptFilePath())
                .eqIfPresent(EvalRptDO::getRptStatus, reqVO.getRptStatus())
                .betweenIfPresent(EvalRptDO::getGenerateTime, reqVO.getGenerateTime())
                .eqIfPresent(EvalRptDO::getSuppleContent, reqVO.getSuppleContent())
                .eqIfPresent(EvalRptDO::getGenerateUser, reqVO.getGenerateUser())
                .eqIfPresent(EvalRptDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(EvalRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalRptDO::getId));
    }

}