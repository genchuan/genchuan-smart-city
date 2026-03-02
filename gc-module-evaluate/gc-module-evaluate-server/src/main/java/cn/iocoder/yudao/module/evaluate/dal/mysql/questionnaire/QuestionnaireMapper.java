package cn.iocoder.yudao.module.evaluate.dal.mysql.questionnaire;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.questionnaire.vo.QuestionnairePageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.questionnaire.QuestionnaireDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 问卷 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface QuestionnaireMapper extends BaseMapperX<QuestionnaireDO> {

    default PageResult<QuestionnaireDO> selectPage(QuestionnairePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<QuestionnaireDO>()
                .eqIfPresent(QuestionnaireDO::getQuestionnaireId, reqVO.getQuestionnaireId())
                .likeIfPresent(QuestionnaireDO::getName, reqVO.getName())
                .eqIfPresent(QuestionnaireDO::getCode, reqVO.getCode())
                .eqIfPresent(QuestionnaireDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(QuestionnaireDO::getObjectScope, reqVO.getObjectScope())
                .eqIfPresent(QuestionnaireDO::getScopeId, reqVO.getScopeId())
                .eqIfPresent(QuestionnaireDO::getIssueTypeId, reqVO.getIssueTypeId())
                .betweenIfPresent(QuestionnaireDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(QuestionnaireDO::getEndTime, reqVO.getEndTime())
                .betweenIfPresent(QuestionnaireDO::getOriginalStartTime, reqVO.getOriginalStartTime())
                .betweenIfPresent(QuestionnaireDO::getOriginalEndTime, reqVO.getOriginalEndTime())
                .eqIfPresent(QuestionnaireDO::getFillCount, reqVO.getFillCount())
                .eqIfPresent(QuestionnaireDO::getFillRate, reqVO.getFillRate())
                .eqIfPresent(QuestionnaireDO::getAverageScore, reqVO.getAverageScore())
                .eqIfPresent(QuestionnaireDO::getFinalFillRate, reqVO.getFinalFillRate())
                .eqIfPresent(QuestionnaireDO::getFinalAverageScore, reqVO.getFinalAverageScore())
                .eqIfPresent(QuestionnaireDO::getIndexValue, reqVO.getIndexValue())
                .eqIfPresent(QuestionnaireDO::getDataRelationStatus, reqVO.getDataRelationStatus())
                .eqIfPresent(QuestionnaireDO::getLink, reqVO.getLink())
                .eqIfPresent(QuestionnaireDO::getQrcode, reqVO.getQrcode())
                .eqIfPresent(QuestionnaireDO::getStatusId, reqVO.getStatusId())
                .eqIfPresent(QuestionnaireDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(QuestionnaireDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(QuestionnaireDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(QuestionnaireDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(QuestionnaireDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(QuestionnaireDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(QuestionnaireDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(QuestionnaireDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(QuestionnaireDO::getId));
    }

}