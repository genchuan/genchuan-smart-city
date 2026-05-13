package cn.iocoder.yudao.module.chargepark.carservice.service.complaint;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.UserAppealAuditReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.UserAppealBatchAuditReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.UserAppealExecuteReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.UserAppealFeedbackReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.UserAppealPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.UserAppealSaveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.complaint.UserAppealDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.complaint.UserAppealMapper;
import cn.iocoder.yudao.module.chargepark.carservice.enums.complaint.UserAppealStatusEnum;
import cn.iocoder.yudao.module.chargepark.carservice.framework.notify.CarServiceNotifyHelper;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.carservice.enums.ErrorCodeConstants.USER_APPEAL_NOT_EXISTS;
import static cn.iocoder.yudao.module.chargepark.carservice.enums.ErrorCodeConstants.USER_APPEAL_STATUS_INVALID;
import static cn.iocoder.yudao.module.chargepark.carservice.enums.LogRecordConstants.*;

/**
 * 用户申诉 Service 实现类
 *
 * @author carservice
 */
@Service
@Validated
public class UserAppealServiceImpl implements UserAppealService {

    @Resource
    private UserAppealMapper userAppealMapper;

    @Resource
    private CarServiceNotifyHelper notifyHelper;

    @Override
    public Long createUserAppeal(UserAppealSaveReqVO createReqVO) {
        UserAppealDO userAppeal = BeanUtils.toBean(createReqVO, UserAppealDO.class);
        if (userAppeal.getSubmitTime() == null) {
            userAppeal.setSubmitTime(LocalDateTime.now());
        }
        if (userAppeal.getStatus() == null || userAppeal.getStatus().isEmpty()) {
            userAppeal.setStatus(UserAppealStatusEnum.WAITING_AUDIT.getLabel());
        }
        userAppealMapper.insert(userAppeal);
        return userAppeal.getId();
    }

    @Override
    public void updateUserAppeal(UserAppealSaveReqVO updateReqVO) {
        UserAppealDO existing = validateUserAppealExists(updateReqVO.getId());
        UserAppealDO updateObj = BeanUtils.toBean(updateReqVO, UserAppealDO.class);
        // update 接口不允许修改 status
        updateObj.setStatus(existing.getStatus());
        userAppealMapper.updateById(updateObj);
    }

    @Override
    public void deleteUserAppeal(Long id) {
        validateUserAppealExists(id);
        userAppealMapper.deleteById(id);
    }

    @Override
    public void deleteUserAppealListByIds(List<Long> ids) {
        userAppealMapper.deleteByIds(ids);
    }

    private UserAppealDO validateUserAppealExists(Long id) {
        UserAppealDO existing = userAppealMapper.selectById(id);
        if (existing == null) {
            throw exception(USER_APPEAL_NOT_EXISTS);
        }
        return existing;
    }

    private void validateStatus(UserAppealDO appeal, UserAppealStatusEnum expected) {
        if (!expected.getLabel().equals(appeal.getStatus())) {
            throw exception(USER_APPEAL_STATUS_INVALID);
        }
    }

    @Override
    public UserAppealDO getUserAppeal(Long id) {
        return userAppealMapper.selectById(id);
    }

    @Override
    public PageResult<UserAppealDO> getUserAppealPage(UserAppealPageReqVO pageReqVO) {
        return userAppealMapper.selectPage(pageReqVO);
    }

    // ========== 业务操作 ==========

    @Override
    @LogRecord(type = USER_APPEAL_TYPE, subType = USER_APPEAL_AUDIT_SUB,
            bizNo = "{{#reqVO.id}}", success = USER_APPEAL_AUDIT_SUCCESS)
    public void auditUserAppeal(UserAppealAuditReqVO reqVO) {
        UserAppealDO appeal = validateUserAppealExists(reqVO.getId());
        validateStatus(appeal, UserAppealStatusEnum.WAITING_AUDIT);
        UserAppealDO update = new UserAppealDO();
        update.setId(reqVO.getId());
        // 通过 → 待处置；驳回 → 已关闭（终态）
        update.setStatus(Boolean.TRUE.equals(reqVO.getApproved())
                ? UserAppealStatusEnum.WAITING_HANDLE.getLabel()
                : UserAppealStatusEnum.CLOSED.getLabel());
        update.setAuditUserId(SecurityFrameworkUtils.getLoginUserId());
        update.setAuditTime(LocalDateTime.now());
        update.setAuditRemark(reqVO.getAuditRemark());
        if (Boolean.FALSE.equals(reqVO.getApproved())) {
            update.setRejectReason(reqVO.getRejectReason());
        }
        userAppealMapper.updateById(update);
        // 审核结果通知申诉人
        if (Boolean.TRUE.equals(reqVO.getApproved())) {
            notifyHelper.sendToUser(appeal.getUserId(), "carservice_appeal_pass", null, null);
            LogRecordContext.putVariable("verb", "审核通过");
            LogRecordContext.putVariable("reason", "");
        } else {
            notifyHelper.sendToUser(appeal.getUserId(), "carservice_appeal_reject",
                    "rejectReason", reqVO.getRejectReason());
            LogRecordContext.putVariable("verb", "审核驳回");
            LogRecordContext.putVariable("reason", ",原因:" + reqVO.getRejectReason());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogRecord(type = USER_APPEAL_TYPE, subType = USER_APPEAL_BATCH_AUDIT_SUB,
            bizNo = "{{#reqVO.ids[0]}}", success = USER_APPEAL_BATCH_AUDIT_SUCCESS)
    public void batchAuditUserAppeal(UserAppealBatchAuditReqVO reqVO) {
        if (reqVO.getIds() == null || reqVO.getIds().isEmpty()) {
            return;
        }
        boolean approved = UserAppealBatchAuditReqVO.AUDIT_RESULT_PASS.equals(reqVO.getAuditResult());
        LocalDateTime now = LocalDateTime.now();
        Long auditorId = SecurityFrameworkUtils.getLoginUserId();
        String targetStatus = approved
                ? UserAppealStatusEnum.WAITING_HANDLE.getLabel()
                : UserAppealStatusEnum.CLOSED.getLabel();
        for (Long id : reqVO.getIds()) {
            UserAppealDO appeal = validateUserAppealExists(id);
            validateStatus(appeal, UserAppealStatusEnum.WAITING_AUDIT);
            UserAppealDO update = new UserAppealDO();
            update.setId(id);
            update.setStatus(targetStatus);
            update.setAuditUserId(auditorId);
            update.setAuditTime(now);
            update.setAuditRemark(reqVO.getAuditRemark());
            if (!approved) {
                update.setRejectReason(reqVO.getRejectReason());
            }
            userAppealMapper.updateById(update);
            if (approved) {
                notifyHelper.sendToUser(appeal.getUserId(), "carservice_appeal_pass", null, null);
            } else {
                notifyHelper.sendToUser(appeal.getUserId(), "carservice_appeal_reject",
                        "rejectReason", reqVO.getRejectReason());
            }
        }
        LogRecordContext.putVariable("verb", approved ? "审核通过" : "审核驳回");
        LogRecordContext.putVariable("reason", approved ? "" : ",原因:" + reqVO.getRejectReason());
    }

    @Override
    @LogRecord(type = USER_APPEAL_TYPE, subType = USER_APPEAL_EXECUTE_SUB,
            bizNo = "{{#reqVO.id}}", success = USER_APPEAL_EXECUTE_SUCCESS)
    public void executeUserAppeal(UserAppealExecuteReqVO reqVO) {
        UserAppealDO appeal = validateUserAppealExists(reqVO.getId());
        validateStatus(appeal, UserAppealStatusEnum.WAITING_HANDLE);
        // 认领并完成处置：状态推进到"已完成"，记录处置人和处置时间，等待反馈关闭
        UserAppealDO update = new UserAppealDO();
        update.setId(reqVO.getId());
        update.setStatus(UserAppealStatusEnum.COMPLETED.getLabel());
        update.setHandleUserId(SecurityFrameworkUtils.getLoginUserId());
        update.setHandleTime(LocalDateTime.now());
        userAppealMapper.updateById(update);
    }

    @Override
    @LogRecord(type = USER_APPEAL_TYPE, subType = USER_APPEAL_FEEDBACK_SUB,
            bizNo = "{{#reqVO.id}}", success = USER_APPEAL_FEEDBACK_SUCCESS)
    public void feedbackUserAppeal(UserAppealFeedbackReqVO reqVO) {
        UserAppealDO appeal = validateUserAppealExists(reqVO.getId());
        // 兼容历史"处置中"也可以反馈
        if (!UserAppealStatusEnum.COMPLETED.getLabel().equals(appeal.getStatus())
                && !UserAppealStatusEnum.HANDLING.getLabel().equals(appeal.getStatus())) {
            throw exception(USER_APPEAL_STATUS_INVALID);
        }
        UserAppealDO update = new UserAppealDO();
        update.setId(reqVO.getId());
        update.setStatus(UserAppealStatusEnum.CLOSED.getLabel());
        update.setFeedbackContent(reqVO.getFeedbackContent());
        update.setFeedbackTime(LocalDateTime.now());
        userAppealMapper.updateById(update);
        // 处置完成后通知申诉人
        notifyHelper.sendToUser(appeal.getUserId(), "carservice_appeal_done", "feedbackContent", reqVO.getFeedbackContent());
    }

}
