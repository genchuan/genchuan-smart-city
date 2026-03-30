package cn.iocoder.yudao.module.evaluate.dal.mysql.question;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.question.vo.QuestionPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.question.QuestionDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 题目 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface QuestionMapper extends BaseMapperX<QuestionDO> {

    default PageResult<QuestionDO> selectPage(QuestionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<QuestionDO>()
                .eqIfPresent(QuestionDO::getQuestionId, reqVO.getQuestionId())
                .eqIfPresent(QuestionDO::getQuestionnaireId, reqVO.getQuestionnaireId())
                .likeIfPresent(QuestionDO::getTitle, reqVO.getTitle())
                .eqIfPresent(QuestionDO::getQuestionType, reqVO.getQuestionType())
                .eqIfPresent(QuestionDO::getScore, reqVO.getScore())
                .eqIfPresent(QuestionDO::getScoreRange, reqVO.getScoreRange())
                .eqIfPresent(QuestionDO::getSortNo, reqVO.getSortNo())
                .eqIfPresent(QuestionDO::getCreateBy, reqVO.getCreateBy())
                .eqIfPresent(QuestionDO::getUpdateBy, reqVO.getUpdateBy())
                .betweenIfPresent(QuestionDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(QuestionDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(QuestionDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(QuestionDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(QuestionDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(QuestionDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(QuestionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(QuestionDO::getId));
    }

}