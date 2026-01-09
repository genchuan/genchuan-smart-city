package cn.iocoder.yudao.module.smartcity.dal.mysql.questionclassification;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.questionclassification.QuestionClassificationDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.questionclassification.vo.*;

/**
 * 问题录入 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface QuestionClassificationMapper extends BaseMapperX<QuestionClassificationDO> {

    default PageResult<QuestionClassificationDO> selectPage(QuestionClassificationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<QuestionClassificationDO>()
                .eqIfPresent(QuestionClassificationDO::getIsArea, reqVO.getIsArea())
                .eqIfPresent(QuestionClassificationDO::getQuestionType, reqVO.getQuestionType())
                .orderByDesc(QuestionClassificationDO::getId));
    }

}