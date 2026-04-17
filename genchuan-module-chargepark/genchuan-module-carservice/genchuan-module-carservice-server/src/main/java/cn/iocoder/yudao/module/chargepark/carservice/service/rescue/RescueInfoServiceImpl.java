package cn.iocoder.yudao.module.chargepark.carservice.service.rescue;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo.RescueInfoBatchDispatchReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo.RescueInfoDispatchReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo.RescueInfoEvaluateReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo.RescueInfoPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo.RescueInfoSaveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo.RescueInfoTransferReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo.RescueInfoUpdateProgressReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.rescue.RescueInfoDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.rescue.RescueInfoMapper;
import cn.iocoder.yudao.module.chargepark.carservice.enums.rescue.RescueArchiveStatusEnum;
import cn.iocoder.yudao.module.chargepark.carservice.enums.rescue.RescueStatusEnum;
import cn.iocoder.yudao.module.chargepark.carservice.framework.notify.CarServiceNotifyHelper;
import cn.iocoder.yudao.module.chargepark.carservice.framework.statemachine.StatusTransition;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.carservice.enums.ErrorCodeConstants.RESCUE_INFO_NOT_EXISTS;
import static cn.iocoder.yudao.module.chargepark.carservice.enums.ErrorCodeConstants.RESCUE_INFO_STATUS_INVALID;

/**
 * 救援信息 Service 实现类
 *
 * @author carservice
 */
@Service
@Validated
public class RescueInfoServiceImpl implements RescueInfoService {

    /**
     * 状态流转表
     * 待派发 → 待认领（dispatch / batchDispatch）
     * 待认领 → 处理中（claim）
     * 处理中 → 处理中（updateProgress / transfer）
     * 处理中 → 已完成（complete）
     */
    private static final StatusTransition TRANSITIONS = StatusTransition.builder()
            .allow(RescueStatusEnum.WAITING_DISPATCH.getLabel(), RescueStatusEnum.WAITING_CLAIM.getLabel())
            .allow(RescueStatusEnum.WAITING_CLAIM.getLabel(), RescueStatusEnum.PROCESSING.getLabel())
            .allow(RescueStatusEnum.PROCESSING.getLabel(), RescueStatusEnum.PROCESSING.getLabel())
            .allow(RescueStatusEnum.PROCESSING.getLabel(), RescueStatusEnum.COMPLETED.getLabel())
            .build();

    @Resource
    private RescueInfoMapper rescueInfoMapper;

    @Resource
    private CarServiceNotifyHelper notifyHelper;

    @Override
    public Long createRescueInfo(RescueInfoSaveReqVO createReqVO) {
        RescueInfoDO rescueInfo = BeanUtils.toBean(createReqVO, RescueInfoDO.class);
        if (rescueInfo.getStatus() == null || rescueInfo.getStatus().isEmpty()) {
            rescueInfo.setStatus(RescueStatusEnum.WAITING_DISPATCH.getLabel());
        }
        if (rescueInfo.getArchiveStatus() == null || rescueInfo.getArchiveStatus().isEmpty()) {
            rescueInfo.setArchiveStatus(RescueArchiveStatusEnum.UNARCHIVED.getLabel());
        }
        if (!RescueStatusEnum.isValid(rescueInfo.getStatus())) {
            throw exception(RESCUE_INFO_STATUS_INVALID);
        }
        rescueInfoMapper.insert(rescueInfo);
        return rescueInfo.getId();
    }

    @Override
    public void updateRescueInfo(RescueInfoSaveReqVO updateReqVO) {
        RescueInfoDO existing = validateRescueInfoExists(updateReqVO.getId());
        // 如果前端传了 status，必须是合法状态机迁移
        if (updateReqVO.getStatus() != null && !updateReqVO.getStatus().equals(existing.getStatus())) {
            if (!TRANSITIONS.canTransition(existing.getStatus(), updateReqVO.getStatus())) {
                throw exception(RESCUE_INFO_STATUS_INVALID);
            }
        }
        RescueInfoDO updateObj = BeanUtils.toBean(updateReqVO, RescueInfoDO.class);
        rescueInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteRescueInfo(Long id) {
        validateRescueInfoExists(id);
        rescueInfoMapper.deleteById(id);
    }

    @Override
    public void deleteRescueInfoListByIds(List<Long> ids) {
        rescueInfoMapper.deleteByIds(ids);
    }

    private RescueInfoDO validateRescueInfoExists(Long id) {
        RescueInfoDO existing = rescueInfoMapper.selectById(id);
        if (existing == null) {
            throw exception(RESCUE_INFO_NOT_EXISTS);
        }
        return existing;
    }

    /** 校验当前状态等于期望状态，否则抛 STATUS_INVALID */
    private void validateStatus(RescueInfoDO rescue, RescueStatusEnum expected) {
        if (!expected.getLabel().equals(rescue.getStatus())) {
            throw exception(RESCUE_INFO_STATUS_INVALID);
        }
    }

    @Override
    public RescueInfoDO getRescueInfo(Long id) {
        return rescueInfoMapper.selectById(id);
    }

    @Override
    public PageResult<RescueInfoDO> getRescueInfoPage(RescueInfoPageReqVO pageReqVO) {
        return rescueInfoMapper.selectPage(pageReqVO);
    }

    // ========== 业务操作 ==========

    @Override
    public void dispatchRescueInfo(RescueInfoDispatchReqVO reqVO) {
        RescueInfoDO rescue = validateRescueInfoExists(reqVO.getId());
        validateStatus(rescue, RescueStatusEnum.WAITING_DISPATCH);
        RescueInfoDO update = new RescueInfoDO();
        update.setId(reqVO.getId());
        update.setStatus(RescueStatusEnum.WAITING_CLAIM.getLabel());
        update.setRescueUserId(reqVO.getRescueUserId());
        update.setDispatchTime(LocalDateTime.now());
        update.setDispatchRemark(reqVO.getDispatchRemark());
        rescueInfoMapper.updateById(update);
        notifyHelper.sendToUser(reqVO.getRescueUserId(), "carservice_rescue_dispatch", "location", rescue.getLocation());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDispatchRescueInfo(RescueInfoBatchDispatchReqVO reqVO) {
        if (reqVO.getIds() == null || reqVO.getIds().isEmpty()) {
            return;
        }
        LocalDateTime now = LocalDateTime.now();
        for (Long id : reqVO.getIds()) {
            RescueInfoDO rescue = validateRescueInfoExists(id);
            validateStatus(rescue, RescueStatusEnum.WAITING_DISPATCH);
            RescueInfoDO update = new RescueInfoDO();
            update.setId(id);
            update.setStatus(RescueStatusEnum.WAITING_CLAIM.getLabel());
            update.setRescueUserId(reqVO.getRescueUserId());
            update.setDispatchTime(now);
            update.setDispatchRemark(reqVO.getDispatchRemark());
            rescueInfoMapper.updateById(update);
            notifyHelper.sendToUser(reqVO.getRescueUserId(), "carservice_rescue_dispatch", "location", rescue.getLocation());
        }
    }

    @Override
    public void claimRescueInfo(Long id) {
        RescueInfoDO rescue = validateRescueInfoExists(id);
        validateStatus(rescue, RescueStatusEnum.WAITING_CLAIM);
        RescueInfoDO update = new RescueInfoDO();
        update.setId(id);
        update.setStatus(RescueStatusEnum.PROCESSING.getLabel());
        // 认领后自动把救援人员设为当前登录用户
        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();
        if (currentUserId != null) {
            update.setRescueUserId(currentUserId);
        }
        rescueInfoMapper.updateById(update);
    }

    @Override
    public void updateRescueInfoProgress(RescueInfoUpdateProgressReqVO reqVO) {
        RescueInfoDO rescue = validateRescueInfoExists(reqVO.getId());
        validateStatus(rescue, RescueStatusEnum.PROCESSING);
        RescueInfoDO update = new RescueInfoDO();
        update.setId(reqVO.getId());
        update.setProgress(reqVO.getProgress());
        if (reqVO.getPhoto() != null) {
            update.setPhoto(reqVO.getPhoto());
        }
        rescueInfoMapper.updateById(update);
    }

    @Override
    public void transferRescueInfo(RescueInfoTransferReqVO reqVO) {
        RescueInfoDO rescue = validateRescueInfoExists(reqVO.getId());
        validateStatus(rescue, RescueStatusEnum.PROCESSING);
        RescueInfoDO update = new RescueInfoDO();
        update.setId(reqVO.getId());
        update.setRescueUserId(reqVO.getNewRescueUserId());
        update.setTransferReason(reqVO.getTransferReason());
        rescueInfoMapper.updateById(update);
        notifyHelper.sendToUser(reqVO.getNewRescueUserId(), "carservice_rescue_transfer", "location", rescue.getLocation());
    }

    @Override
    public void completeRescueInfo(Long id) {
        RescueInfoDO rescue = validateRescueInfoExists(id);
        validateStatus(rescue, RescueStatusEnum.PROCESSING);
        LocalDateTime now = LocalDateTime.now();
        RescueInfoDO update = new RescueInfoDO();
        update.setId(id);
        update.setStatus(RescueStatusEnum.COMPLETED.getLabel());
        update.setFinishTime(now);
        // 处理时长：从创建时间到完成时间（秒）
        if (rescue.getCreateTime() != null) {
            long seconds = Duration.between(rescue.getCreateTime(), now).getSeconds();
            update.setHandleDuration((int) seconds);
        }
        rescueInfoMapper.updateById(update);
    }

    @Override
    public void evaluateRescueInfo(RescueInfoEvaluateReqVO reqVO) {
        RescueInfoDO rescue = validateRescueInfoExists(reqVO.getId());
        validateStatus(rescue, RescueStatusEnum.COMPLETED);
        RescueInfoDO update = new RescueInfoDO();
        update.setId(reqVO.getId());
        update.setScore(reqVO.getScore());
        update.setEvaluateContent(reqVO.getEvaluateContent());
        rescueInfoMapper.updateById(update);
    }

    @Override
    public void archiveRescueInfo(Long id) {
        RescueInfoDO rescue = validateRescueInfoExists(id);
        validateStatus(rescue, RescueStatusEnum.COMPLETED);
        RescueInfoDO update = new RescueInfoDO();
        update.setId(id);
        update.setArchiveStatus(RescueArchiveStatusEnum.ARCHIVED.getLabel());
        rescueInfoMapper.updateById(update);
    }

}
