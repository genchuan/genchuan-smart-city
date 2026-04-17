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
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.carservice.enums.ErrorCodeConstants.USER_APPEAL_NOT_EXISTS;
import static cn.iocoder.yudao.module.chargepark.carservice.enums.ErrorCodeConstants.USER_APPEAL_STATUS_INVALID;

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
    public void auditUserAppeal(UserAppealAuditReqVO reqVO) {
        UserAppealDO appeal = validateUserAppealExists(reqVO.getId());
        validateStatus(appeal, UserAppealStatusEnum.WAITING_AUDIT);
        UserAppealDO update = new UserAppealDO();
        update.setId(reqVO.getId());
        // 通过 → 待处置；驳回 → 已完成（终态）
        update.setStatus(Boolean.TRUE.equals(reqVO.getApproved())
                ? UserAppealStatusEnum.WAITING_HANDLE.getLabel()
                : UserAppealStatusEnum.COMPLETED.getLabel());
        update.setAuditUserId(SecurityFrameworkUtils.getLoginUserId());
        update.setAuditTime(LocalDateTime.now());
        update.setAuditRemark(reqVO.getAuditRemark());
        if (Boolean.FALSE.equals(reqVO.getApproved())) {
            update.setRejectReason(reqVO.getRejectReason());
        }
        userAppealMapper.updateById(update);
        // 审核通过后通知申诉人
        if (Boolean.TRUE.equals(reqVO.getApproved())) {
            notifyHelper.sendToUser(appeal.getUserId(), "carservice_appeal_pass", null, null);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchAuditUserAppeal(UserAppealBatchAuditReqVO reqVO) {
        if (reqVO.getIds() == null || reqVO.getIds().isEmpty()) {
            return;
        }
        boolean approved = UserAppealBatchAuditReqVO.AUDIT_RESULT_PASS.equals(reqVO.getAuditResult());
        LocalDateTime now = LocalDateTime.now();
        Long auditorId = SecurityFrameworkUtils.getLoginUserId();
        String targetStatus = approved
                ? UserAppealStatusEnum.WAITING_HANDLE.getLabel()
                : UserAppealStatusEnum.COMPLETED.getLabel();
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
            }
        }
    }

    @Override
    public void executeUserAppeal(UserAppealExecuteReqVO reqVO) {
        UserAppealDO appeal = validateUserAppealExists(reqVO.getId());
        validateStatus(appeal, UserAppealStatusEnum.WAITING_HANDLE);
        // execute 仅标记处置人为当前登录用户,状态保持"待处置",后续由 feedback 完成
        UserAppealDO update = new UserAppealDO();
        update.setId(reqVO.getId());
        update.setHandleUserId(SecurityFrameworkUtils.getLoginUserId());
        userAppealMapper.updateById(update);
    }

    @Override
    public void feedbackUserAppeal(UserAppealFeedbackReqVO reqVO) {
        UserAppealDO appeal = validateUserAppealExists(reqVO.getId());
        validateStatus(appeal, UserAppealStatusEnum.WAITING_HANDLE);
        UserAppealDO update = new UserAppealDO();
        update.setId(reqVO.getId());
        update.setStatus(UserAppealStatusEnum.COMPLETED.getLabel());
        update.setFeedbackContent(reqVO.getFeedbackContent());
        update.setFeedbackTime(LocalDateTime.now());
        userAppealMapper.updateById(update);
        // 处置完成后通知申诉人
        notifyHelper.sendToUser(appeal.getUserId(), "carservice_appeal_done", "feedbackContent", reqVO.getFeedbackContent());
    }

}
