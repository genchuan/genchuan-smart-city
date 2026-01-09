package cn.iocoder.yudao.module.smartcity.service.questionclassification;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.questionclassification.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.questionclassification.QuestionClassificationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.questionclassification.QuestionClassificationMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 问题录入 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class QuestionClassificationServiceImpl implements QuestionClassificationService {

    @Resource
    private QuestionClassificationMapper questionClassificationMapper;

    @Override
    public Long createQuestionClassification(QuestionClassificationSaveReqVO createReqVO) {
        // 插入
        QuestionClassificationDO questionClassification = BeanUtils.toBean(createReqVO, QuestionClassificationDO.class);
        questionClassificationMapper.insert(questionClassification);
        // 返回
        return questionClassification.getId();
    }

    @Override
    public void updateQuestionClassification(QuestionClassificationSaveReqVO updateReqVO) {
        // 校验存在
        validateQuestionClassificationExists(updateReqVO.getId());
        // 更新
        QuestionClassificationDO updateObj = BeanUtils.toBean(updateReqVO, QuestionClassificationDO.class);
        questionClassificationMapper.updateById(updateObj);
    }

    @Override
    public void deleteQuestionClassification(Long id) {
        // 校验存在
        validateQuestionClassificationExists(id);
        // 删除
        questionClassificationMapper.deleteById(id);
    }

    private void validateQuestionClassificationExists(Long id) {
        if (questionClassificationMapper.selectById(id) == null) {
            throw exception(QUESTION_CLASSIFICATION_NOT_EXISTS);
        }
    }

    @Override
    public QuestionClassificationDO getQuestionClassification(Long id) {
        return questionClassificationMapper.selectById(id);
    }

    @Override
    public PageResult<QuestionClassificationDO> getQuestionClassificationPage(QuestionClassificationPageReqVO pageReqVO) {
        return questionClassificationMapper.selectPage(pageReqVO);
    }

}