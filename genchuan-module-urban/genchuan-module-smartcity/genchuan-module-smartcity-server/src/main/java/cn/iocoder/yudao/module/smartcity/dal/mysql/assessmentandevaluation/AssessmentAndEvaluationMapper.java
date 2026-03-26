package cn.iocoder.yudao.module.smartcity.dal.mysql.assessmentandevaluation;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.assessmentandevaluation.AssessmentAndEvaluationDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.assessmentandevaluation.vo.*;

/**
 * 养护考核评价 Mapper
 *
 * @author zcq
 */
@Mapper
public interface AssessmentAndEvaluationMapper extends BaseMapperX<AssessmentAndEvaluationDO> {

    default PageResult<AssessmentAndEvaluationDO> selectPage(AssessmentAndEvaluationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AssessmentAndEvaluationDO>()
                .eqIfPresent(AssessmentAndEvaluationDO::getMaintenancePersonnelNumber, reqVO.getMaintenancePersonnelNumber())
                .eqIfPresent(AssessmentAndEvaluationDO::getMaintenanceTaskNumber, reqVO.getMaintenanceTaskNumber())
                .eqIfPresent(AssessmentAndEvaluationDO::getAssessmentCycle, reqVO.getAssessmentCycle())
                .eqIfPresent(AssessmentAndEvaluationDO::getAssessmentScore, reqVO.getAssessmentScore())
                .eqIfPresent(AssessmentAndEvaluationDO::getAssessmentLevel, reqVO.getAssessmentLevel())
                .eqIfPresent(AssessmentAndEvaluationDO::getEvaluationOpinion, reqVO.getEvaluationOpinion())
                .eqIfPresent(AssessmentAndEvaluationDO::getImprovementSuggestions, reqVO.getImprovementSuggestions())
                .betweenIfPresent(AssessmentAndEvaluationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AssessmentAndEvaluationDO::getId));
    }

}