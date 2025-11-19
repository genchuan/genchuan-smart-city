package cn.iocoder.yudao.module.datacenter.service.inspection.result.inspectresultfeedback;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresultfeedback.vo.InspectResultFeedbackPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresultfeedback.vo.InspectResultFeedbackSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.result.inspectresultfeedback.InspectResultFeedbackDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检结果反馈 Service 接口
 *
 * @author zcq
 */
public interface InspectResultFeedbackService {

    /**
     * 创建巡查巡检结果反馈
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectResultFeedback(@Valid InspectResultFeedbackSaveReqVO createReqVO);

    /**
     * 更新巡查巡检结果反馈
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectResultFeedback(@Valid InspectResultFeedbackSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检结果反馈
     *
     * @param id 编号
     */
    void deleteInspectResultFeedback(Long id);

    /**
     * 获得巡查巡检结果反馈
     *
     * @param id 编号
     * @return 巡查巡检结果反馈
     */
    InspectResultFeedbackDO getInspectResultFeedback(Long id);

    /**
     * 获得巡查巡检结果反馈分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检结果反馈分页
     */
    PageResult<InspectResultFeedbackDO> getInspectResultFeedbackPage(InspectResultFeedbackPageReqVO pageReqVO);

}