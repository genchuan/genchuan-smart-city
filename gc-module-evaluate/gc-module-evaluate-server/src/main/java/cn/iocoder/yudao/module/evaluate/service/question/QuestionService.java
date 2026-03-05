package cn.iocoder.yudao.module.evaluate.service.question;

import java.util.*;

import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.question.vo.QuestionPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.question.vo.QuestionSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.question.QuestionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 题目 Service 接口
 *
 * @author 芋道源码
 */
public interface QuestionService {

    /**
     * 创建题目
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createQuestion(@Valid QuestionSaveReqVO createReqVO);

    /**
     * 更新题目
     *
     * @param updateReqVO 更新信息
     */
    void updateQuestion(@Valid QuestionSaveReqVO updateReqVO);

    /**
     * 删除题目
     *
     * @param id 编号
     */
    void deleteQuestion(Long id);

    /**
    * 批量删除题目
    *
    * @param ids 编号
    */
    void deleteQuestionListByIds(List<Long> ids);

    /**
     * 获得题目
     *
     * @param id 编号
     * @return 题目
     */
    QuestionDO getQuestion(Long id);

    /**
     * 获得题目分页
     *
     * @param pageReqVO 分页查询
     * @return 题目分页
     */
    PageResult<QuestionDO> getQuestionPage(QuestionPageReqVO pageReqVO);

}