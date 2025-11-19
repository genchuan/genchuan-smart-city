package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.data.evalquestionnaire;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalquestionnaire.vo.EvalQuestionnairePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.data.evalquestionnaire.EvalQuestionnaireDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 问卷调查管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalQuestionnaireMapper extends BaseMapperX<EvalQuestionnaireDO> {

    default PageResult<EvalQuestionnaireDO> selectPage(EvalQuestionnairePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalQuestionnaireDO>()
                .eqIfPresent(EvalQuestionnaireDO::getQuestionnaireId, reqVO.getQuestionnaireId())
                .likeIfPresent(EvalQuestionnaireDO::getQuestionnaireName, reqVO.getQuestionnaireName())
                .eqIfPresent(EvalQuestionnaireDO::getQuestionnaireCode, reqVO.getQuestionnaireCode())
                .eqIfPresent(EvalQuestionnaireDO::getEvalTaskId, reqVO.getEvalTaskId())
                .likeIfPresent(EvalQuestionnaireDO::getEvalTaskName, reqVO.getEvalTaskName())
                .eqIfPresent(EvalQuestionnaireDO::getIdxItemId, reqVO.getIdxItemId())
                .likeIfPresent(EvalQuestionnaireDO::getIdxItemName, reqVO.getIdxItemName())
                .eqIfPresent(EvalQuestionnaireDO::getTargetScope, reqVO.getTargetScope())
                .eqIfPresent(EvalQuestionnaireDO::getReleaseMethod, reqVO.getReleaseMethod())
                .eqIfPresent(EvalQuestionnaireDO::getQuestionnaireUrl, reqVO.getQuestionnaireUrl())
                .eqIfPresent(EvalQuestionnaireDO::getQuestionnaireQrcode, reqVO.getQuestionnaireQrcode())
                .betweenIfPresent(EvalQuestionnaireDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(EvalQuestionnaireDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(EvalQuestionnaireDO::getQuestionnaireStatus, reqVO.getQuestionnaireStatus())
                .eqIfPresent(EvalQuestionnaireDO::getFillCount, reqVO.getFillCount())
                .eqIfPresent(EvalQuestionnaireDO::getQuestionnaireContent, reqVO.getQuestionnaireContent())
                .eqIfPresent(EvalQuestionnaireDO::getCreateUserBiz, reqVO.getCreateUserBiz())
                .eqIfPresent(EvalQuestionnaireDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(EvalQuestionnaireDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalQuestionnaireDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalQuestionnaireDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalQuestionnaireDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalQuestionnaireDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalQuestionnaireDO::getId));
    }

}