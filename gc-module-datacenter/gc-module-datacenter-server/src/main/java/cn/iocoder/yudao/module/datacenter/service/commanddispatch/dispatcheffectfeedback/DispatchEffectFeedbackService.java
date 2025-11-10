package cn.iocoder.yudao.module.datacenter.service.commanddispatch.dispatcheffectfeedback;

import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatcheffectfeedback.vo.DispatchEffectFeedbackPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatcheffectfeedback.vo.DispatchEffectFeedbackSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.dispatcheffectfeedback.DispatchEffectFeedbackDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 调度效果反馈 Service 接口
 *
 * @author 亘川智城
 */
public interface DispatchEffectFeedbackService {

    /**
     * 创建调度效果反馈
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDispatchEffectFeedback(@Valid DispatchEffectFeedbackSaveReqVO createReqVO);

    /**
     * 更新调度效果反馈
     *
     * @param updateReqVO 更新信息
     */
    void updateDispatchEffectFeedback(@Valid DispatchEffectFeedbackSaveReqVO updateReqVO);

    /**
     * 删除调度效果反馈
     *
     * @param id 编号
     */
    void deleteDispatchEffectFeedback(Long id);

    /**
     * 获得调度效果反馈
     *
     * @param id 编号
     * @return 调度效果反馈
     */
    DispatchEffectFeedbackDO getDispatchEffectFeedback(Long id);

    /**
     * 获得调度效果反馈分页
     *
     * @param pageReqVO 分页查询
     * @return 调度效果反馈分页
     */
    PageResult<DispatchEffectFeedbackDO> getDispatchEffectFeedbackPage(DispatchEffectFeedbackPageReqVO pageReqVO);

}