package cn.iocoder.yudao.module.chargepark.carservice.service.complaint;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.SuggestionFeedbackReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.SuggestionHandleReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.SuggestionPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.SuggestionSaveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.SuggestionUpdateProgressReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.complaint.SuggestionDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.complaint.SuggestionMapper;
import cn.iocoder.yudao.module.chargepark.carservice.enums.complaint.SuggestionStatusEnum;
import cn.iocoder.yudao.module.chargepark.carservice.framework.notify.CarServiceNotifyHelper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.carservice.enums.ErrorCodeConstants.SUGGESTION_NOT_EXISTS;
import static cn.iocoder.yudao.module.chargepark.carservice.enums.ErrorCodeConstants.SUGGESTION_STATUS_INVALID;

/**
 * 意见建议 Service 实现类
 *
 * @author carservice
 */
@Service
@Validated
public class SuggestionServiceImpl implements SuggestionService {

    @Resource
    private SuggestionMapper suggestionMapper;

    @Resource
    private CarServiceNotifyHelper notifyHelper;

    @Override
    public Long createSuggestion(SuggestionSaveReqVO createReqVO) {
        SuggestionDO suggestion = BeanUtils.toBean(createReqVO, SuggestionDO.class);
        if (suggestion.getSubmitTime() == null) {
            suggestion.setSubmitTime(LocalDateTime.now());
        }
        if (suggestion.getStatus() == null || suggestion.getStatus().isEmpty()) {
            suggestion.setStatus(SuggestionStatusEnum.PENDING.getLabel());
        }
        suggestionMapper.insert(suggestion);
        return suggestion.getId();
    }

    @Override
    public void updateSuggestion(SuggestionSaveReqVO updateReqVO) {
        SuggestionDO existing = validateSuggestionExists(updateReqVO.getId());
        SuggestionDO updateObj = BeanUtils.toBean(updateReqVO, SuggestionDO.class);
        // update 接口不允许修改 status
        updateObj.setStatus(existing.getStatus());
        suggestionMapper.updateById(updateObj);
    }

    @Override
    public void deleteSuggestion(Long id) {
        validateSuggestionExists(id);
        suggestionMapper.deleteById(id);
    }

    @Override
    public void deleteSuggestionListByIds(List<Long> ids) {
        suggestionMapper.deleteByIds(ids);
    }

    private SuggestionDO validateSuggestionExists(Long id) {
        SuggestionDO existing = suggestionMapper.selectById(id);
        if (existing == null) {
            throw exception(SUGGESTION_NOT_EXISTS);
        }
        return existing;
    }

    @Override
    public SuggestionDO getSuggestion(Long id) {
        return suggestionMapper.selectById(id);
    }

    @Override
    public PageResult<SuggestionDO> getSuggestionPage(SuggestionPageReqVO pageReqVO) {
        return suggestionMapper.selectPage(pageReqVO);
    }

    // ========== 业务操作 ==========

    @Override
    public void handleSuggestion(SuggestionHandleReqVO reqVO) {
        SuggestionDO suggestion = validateSuggestionExists(reqVO.getId());
        // handle 仅支持「待处理 → 处理中」,设处理人为当前用户,不更新 progress
        if (!SuggestionStatusEnum.PENDING.getLabel().equals(suggestion.getStatus())) {
            throw exception(SUGGESTION_STATUS_INVALID);
        }
        SuggestionDO update = new SuggestionDO();
        update.setId(reqVO.getId());
        update.setStatus(SuggestionStatusEnum.PROCESSING.getLabel());
        update.setHandleUserId(SecurityFrameworkUtils.getLoginUserId());
        update.setHandleTime(LocalDateTime.now());
        suggestionMapper.updateById(update);
    }

    @Override
    public void updateSuggestionProgress(SuggestionUpdateProgressReqVO reqVO) {
        SuggestionDO suggestion = validateSuggestionExists(reqVO.getId());
        // update-progress 仅支持「处理中 → 处理中」,仅更新 progress
        if (!SuggestionStatusEnum.PROCESSING.getLabel().equals(suggestion.getStatus())) {
            throw exception(SUGGESTION_STATUS_INVALID);
        }
        SuggestionDO update = new SuggestionDO();
        update.setId(reqVO.getId());
        update.setProgress(reqVO.getProgress());
        suggestionMapper.updateById(update);
    }

    @Override
    public void feedbackSuggestion(SuggestionFeedbackReqVO reqVO) {
        SuggestionDO suggestion = validateSuggestionExists(reqVO.getId());
        if (!SuggestionStatusEnum.PROCESSING.getLabel().equals(suggestion.getStatus())) {
            throw exception(SUGGESTION_STATUS_INVALID);
        }
        SuggestionDO update = new SuggestionDO();
        update.setId(reqVO.getId());
        update.setStatus(SuggestionStatusEnum.CLOSED.getLabel());
        update.setFeedbackContent(reqVO.getFeedbackContent());
        update.setFeedbackTime(LocalDateTime.now());
        suggestionMapper.updateById(update);
        // 反馈完成后通知意见提交人
        notifyHelper.sendToUser(suggestion.getUserId(), "carservice_suggestion_closed",
                "feedbackContent", reqVO.getFeedbackContent());
    }

}
