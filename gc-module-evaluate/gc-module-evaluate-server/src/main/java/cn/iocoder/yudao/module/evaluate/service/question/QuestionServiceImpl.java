package cn.iocoder.yudao.module.evaluate.service.question;

import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.question.vo.QuestionPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.question.vo.QuestionSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import cn.iocoder.yudao.module.evaluate.dal.dataobject.question.QuestionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.evaluate.dal.mysql.question.QuestionMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.*;

/**
 * 题目 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class QuestionServiceImpl implements QuestionService {

    @Resource
    private QuestionMapper questionMapper;

    @Override
    public Long createQuestion(QuestionSaveReqVO createReqVO) {
        // 插入
        QuestionDO question = BeanUtils.toBean(createReqVO, QuestionDO.class);
        questionMapper.insert(question);

        // 返回
        return question.getId();
    }

    @Override
    public void updateQuestion(QuestionSaveReqVO updateReqVO) {
        // 校验存在
        validateQuestionExists(updateReqVO.getId());
        // 更新
        QuestionDO updateObj = BeanUtils.toBean(updateReqVO, QuestionDO.class);
        questionMapper.updateById(updateObj);
    }

    @Override
    public void deleteQuestion(Long id) {
        // 校验存在
        validateQuestionExists(id);
        // 删除
        questionMapper.deleteById(id);
    }

    @Override
        public void deleteQuestionListByIds(List<Long> ids) {
        // 删除
        questionMapper.deleteByIds(ids);
        }


    private void validateQuestionExists(Long id) {
        if (questionMapper.selectById(id) == null) {
            throw exception(QUESTION_NOT_EXISTS);
        }
    }

    @Override
    public QuestionDO getQuestion(Long id) {
        return questionMapper.selectById(id);
    }

    @Override
    public PageResult<QuestionDO> getQuestionPage(QuestionPageReqVO pageReqVO) {
        return questionMapper.selectPage(pageReqVO);
    }

}