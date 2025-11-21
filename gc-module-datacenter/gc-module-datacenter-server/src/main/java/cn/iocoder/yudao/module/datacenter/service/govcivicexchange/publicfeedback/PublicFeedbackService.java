package cn.iocoder.yudao.module.datacenter.service.govcivicexchange.publicfeedback;

import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicfeedback.vo.PublicFeedbackPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicfeedback.vo.PublicFeedbackSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.govcivicexchange.publicfeedback.PublicFeedbackDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 意见反馈 Service 接口
 *
 * @author 亘川智城
 */
public interface PublicFeedbackService {

    /**
     * 创建意见反馈
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPublicFeedback(@Valid PublicFeedbackSaveReqVO createReqVO);

    /**
     * 更新意见反馈
     *
     * @param updateReqVO 更新信息
     */
    void updatePublicFeedback(@Valid PublicFeedbackSaveReqVO updateReqVO);

    /**
     * 删除意见反馈
     *
     * @param id 编号
     */
    void deletePublicFeedback(Long id);

    /**
     * 获得意见反馈
     *
     * @param id 编号
     * @return 意见反馈
     */
    PublicFeedbackDO getPublicFeedback(Long id);

    /**
     * 获得意见反馈分页
     *
     * @param pageReqVO 分页查询
     * @return 意见反馈分页
     */
    PageResult<PublicFeedbackDO> getPublicFeedbackPage(PublicFeedbackPageReqVO pageReqVO);

}