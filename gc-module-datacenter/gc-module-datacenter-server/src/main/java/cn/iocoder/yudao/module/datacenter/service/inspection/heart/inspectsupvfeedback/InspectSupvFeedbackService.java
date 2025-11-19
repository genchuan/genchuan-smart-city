package cn.iocoder.yudao.module.datacenter.service.inspection.heart.inspectsupvfeedback;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvfeedback.vo.InspectSupvFeedbackPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvfeedback.vo.InspectSupvFeedbackSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.heart.inspectsupvfeedback.InspectSupvFeedbackDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检督办结果反馈 Service 接口
 *
 * @author zcq
 */
public interface InspectSupvFeedbackService {

    /**
     * 创建巡查巡检督办结果反馈
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectSupvFeedback(@Valid InspectSupvFeedbackSaveReqVO createReqVO);

    /**
     * 更新巡查巡检督办结果反馈
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectSupvFeedback(@Valid InspectSupvFeedbackSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检督办结果反馈
     *
     * @param id 编号
     */
    void deleteInspectSupvFeedback(Long id);

    /**
     * 获得巡查巡检督办结果反馈
     *
     * @param id 编号
     * @return 巡查巡检督办结果反馈
     */
    InspectSupvFeedbackDO getInspectSupvFeedback(Long id);

    /**
     * 获得巡查巡检督办结果反馈分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检督办结果反馈分页
     */
    PageResult<InspectSupvFeedbackDO> getInspectSupvFeedbackPage(InspectSupvFeedbackPageReqVO pageReqVO);

}