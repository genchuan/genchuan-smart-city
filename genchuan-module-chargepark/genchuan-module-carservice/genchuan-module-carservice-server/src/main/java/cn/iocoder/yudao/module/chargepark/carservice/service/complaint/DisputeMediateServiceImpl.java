package cn.iocoder.yudao.module.chargepark.carservice.service.complaint;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.DisputeMediateMediateReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.DisputeMediatePageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.DisputeMediateSaveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.DisputeMediateUpdateProgressReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.complaint.DisputeMediateDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.complaint.DisputeMediateMapper;
import cn.iocoder.yudao.module.chargepark.carservice.enums.complaint.DisputeMediateStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.carservice.enums.ErrorCodeConstants.DISPUTE_MEDIATE_NOT_EXISTS;
import static cn.iocoder.yudao.module.chargepark.carservice.enums.ErrorCodeConstants.DISPUTE_MEDIATE_STATUS_INVALID;

/**
 * 纠纷调解 Service 实现类
 *
 * @author carservice
 */
@Service
@Validated
public class DisputeMediateServiceImpl implements DisputeMediateService {

    @Resource
    private DisputeMediateMapper disputeMediateMapper;

    @Override
    public Long createDisputeMediate(DisputeMediateSaveReqVO createReqVO) {
        DisputeMediateDO disputeMediate = BeanUtils.toBean(createReqVO, DisputeMediateDO.class);
        if (disputeMediate.getSubmitTime() == null) {
            disputeMediate.setSubmitTime(LocalDateTime.now());
        }
        if (disputeMediate.getStatus() == null || disputeMediate.getStatus().isEmpty()) {
            disputeMediate.setStatus(DisputeMediateStatusEnum.WAITING_MEDIATE.getLabel());
        }
        disputeMediateMapper.insert(disputeMediate);
        return disputeMediate.getId();
    }

    @Override
    public void updateDisputeMediate(DisputeMediateSaveReqVO updateReqVO) {
        DisputeMediateDO existing = validateDisputeMediateExists(updateReqVO.getId());
        DisputeMediateDO updateObj = BeanUtils.toBean(updateReqVO, DisputeMediateDO.class);
        // update 接口不允许修改 status
        updateObj.setStatus(existing.getStatus());
        disputeMediateMapper.updateById(updateObj);
    }

    @Override
    public void deleteDisputeMediate(Long id) {
        validateDisputeMediateExists(id);
        disputeMediateMapper.deleteById(id);
    }

    @Override
    public void deleteDisputeMediateListByIds(List<Long> ids) {
        disputeMediateMapper.deleteByIds(ids);
    }

    private DisputeMediateDO validateDisputeMediateExists(Long id) {
        DisputeMediateDO existing = disputeMediateMapper.selectById(id);
        if (existing == null) {
            throw exception(DISPUTE_MEDIATE_NOT_EXISTS);
        }
        return existing;
    }

    @Override
    public DisputeMediateDO getDisputeMediate(Long id) {
        return disputeMediateMapper.selectById(id);
    }

    @Override
    public PageResult<DisputeMediateDO> getDisputeMediatePage(DisputeMediatePageReqVO pageReqVO) {
        return disputeMediateMapper.selectPage(pageReqVO);
    }

    // ========== 业务操作 ==========

    @Override
    public void mediateDisputeMediate(DisputeMediateMediateReqVO reqVO) {
        DisputeMediateDO dispute = validateDisputeMediateExists(reqVO.getId());
        // mediate 仅支持「待调解 → 调解中」,设调解人为当前用户,不更新 progress
        if (!DisputeMediateStatusEnum.WAITING_MEDIATE.getLabel().equals(dispute.getStatus())) {
            throw exception(DISPUTE_MEDIATE_STATUS_INVALID);
        }
        DisputeMediateDO update = new DisputeMediateDO();
        update.setId(reqVO.getId());
        update.setStatus(DisputeMediateStatusEnum.MEDIATING.getLabel());
        update.setMediateUserId(SecurityFrameworkUtils.getLoginUserId());
        disputeMediateMapper.updateById(update);
    }

    @Override
    public void updateDisputeMediateProgress(DisputeMediateUpdateProgressReqVO reqVO) {
        DisputeMediateDO dispute = validateDisputeMediateExists(reqVO.getId());
        // update-progress 仅支持「调解中 → 调解中」,仅更新 progress
        if (!DisputeMediateStatusEnum.MEDIATING.getLabel().equals(dispute.getStatus())) {
            throw exception(DISPUTE_MEDIATE_STATUS_INVALID);
        }
        DisputeMediateDO update = new DisputeMediateDO();
        update.setId(reqVO.getId());
        update.setProgress(reqVO.getProgress());
        disputeMediateMapper.updateById(update);
    }

    @Override
    public void confirmDisputeMediate(Long id) {
        DisputeMediateDO dispute = validateDisputeMediateExists(id);
        if (!DisputeMediateStatusEnum.MEDIATING.getLabel().equals(dispute.getStatus())) {
            throw exception(DISPUTE_MEDIATE_STATUS_INVALID);
        }
        DisputeMediateDO update = new DisputeMediateDO();
        update.setId(id);
        update.setStatus(DisputeMediateStatusEnum.COMPLETED.getLabel());
        update.setConfirmTime(LocalDateTime.now());
        disputeMediateMapper.updateById(update);
    }

}
