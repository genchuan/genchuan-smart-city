package cn.iocoder.yudao.module.chargepark.carservice.service.complaint;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.SuggestionFeedbackReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.SuggestionHandleReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.SuggestionPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.SuggestionSaveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.SuggestionUpdateProgressReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.complaint.SuggestionDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 意见建议 Service 接口
 *
 * @author carservice
 */
public interface SuggestionService {

    Long createSuggestion(@Valid SuggestionSaveReqVO createReqVO);

    void updateSuggestion(@Valid SuggestionSaveReqVO updateReqVO);

    void deleteSuggestion(Long id);

    void deleteSuggestionListByIds(List<Long> ids);

    SuggestionDO getSuggestion(Long id);

    PageResult<SuggestionDO> getSuggestionPage(SuggestionPageReqVO pageReqVO);

    // ========== 业务操作（状态机） ==========

    /** 处理：待处理 → 处理中（只设处理人为当前用户,不更新进度） */
    void handleSuggestion(SuggestionHandleReqVO reqVO);

    /** 更新进度：处理中 → 处理中（仅更新 progress 字段） */
    void updateSuggestionProgress(SuggestionUpdateProgressReqVO reqVO);

    /** 反馈：处理中 → 已完成（设反馈内容、反馈时间） */
    void feedbackSuggestion(SuggestionFeedbackReqVO reqVO);

}
