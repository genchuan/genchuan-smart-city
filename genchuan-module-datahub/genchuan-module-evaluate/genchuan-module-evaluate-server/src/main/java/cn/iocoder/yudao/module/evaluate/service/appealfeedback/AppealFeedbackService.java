package cn.iocoder.yudao.module.evaluate.service.appealfeedback;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.appealfeedback.vo.AppealFeedbackPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.appealfeedback.vo.AppealFeedbackSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.appealfeedback.AppealFeedbackDO;
import jakarta.validation.Valid;

/**
 * 申诉反馈 Service 接口
 *
 * @author 亘川智城
 */
public interface AppealFeedbackService {

    /**
     * 创建申诉反馈
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAppealFeedback(@Valid AppealFeedbackSaveReqVO createReqVO);

    /**
     * 更新申诉反馈
     *
     * @param updateReqVO 更新信息
     */
    void updateAppealFeedback(@Valid AppealFeedbackSaveReqVO updateReqVO);

    /**
     * 删除申诉反馈
     *
     * @param id 编号
     */
    void deleteAppealFeedback(Long id);

    /**
     * 获得申诉反馈
     *
     * @param id 编号
     * @return 申诉反馈
     */
    AppealFeedbackDO getAppealFeedback(Long id);

    /**
     * 获得申诉反馈分页
     *
     * @param pageReqVO 分页查询
     * @return 申诉反馈分页
     */
    PageResult<AppealFeedbackDO> getAppealFeedbackPage(AppealFeedbackPageReqVO pageReqVO);

}