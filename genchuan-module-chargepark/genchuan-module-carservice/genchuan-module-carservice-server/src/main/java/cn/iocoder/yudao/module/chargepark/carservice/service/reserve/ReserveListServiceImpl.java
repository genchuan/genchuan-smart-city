package cn.iocoder.yudao.module.chargepark.carservice.service.reserve;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.reserve.vo.ReserveListAuditReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.reserve.vo.ReserveListBatchAuditReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.reserve.vo.ReserveListEvaluateReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.reserve.vo.ReserveListPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.reserve.vo.ReserveListSaveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.reserve.ReserveListDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.reserve.ReserveListMapper;
import cn.iocoder.yudao.module.chargepark.carservice.enums.reserve.ReserveStatusEnum;
import cn.iocoder.yudao.module.chargepark.carservice.framework.notify.CarServiceNotifyHelper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.carservice.enums.ErrorCodeConstants.RESERVE_LIST_NOT_EXISTS;
import static cn.iocoder.yudao.module.chargepark.carservice.enums.ErrorCodeConstants.RESERVE_LIST_STATUS_INVALID;

/**
 * 预约列表 Service 实现类
 *
 * @author carservice
 */
@Service
@Validated
public class ReserveListServiceImpl implements ReserveListService {

    private static final DateTimeFormatter DT_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Resource
    private ReserveListMapper reserveListMapper;

    @Resource
    private CarServiceNotifyHelper notifyHelper;

    /** 给用户发送审核结果通知 */
    private void notifyAuditResult(ReserveListDO reserve, boolean approved, String rejectReason) {
        if (approved) {
            Map<String, Object> params = new HashMap<>();
            params.put("reserveType", reserve.getReserveType());
            params.put("reserveTime", reserve.getReserveTime() == null ? "" : reserve.getReserveTime().format(DT_FMT));
            notifyHelper.sendToUser(reserve.getUserId(), "carservice_reserve_pass", params);
        } else {
            Map<String, Object> params = new HashMap<>();
            params.put("reserveType", reserve.getReserveType());
            params.put("rejectReason", rejectReason == null ? "" : rejectReason);
            notifyHelper.sendToUser(reserve.getUserId(), "carservice_reserve_reject", params);
        }
    }

    @Override
    public Long createReserveList(ReserveListSaveReqVO createReqVO) {
        ReserveListDO reserveList = BeanUtils.toBean(createReqVO, ReserveListDO.class);
        if (reserveList.getStatus() == null || reserveList.getStatus().isEmpty()) {
            reserveList.setStatus(ReserveStatusEnum.WAITING_AUDIT.getLabel());
        }
        if (!ReserveStatusEnum.isValid(reserveList.getStatus())) {
            throw exception(RESERVE_LIST_STATUS_INVALID);
        }
        reserveListMapper.insert(reserveList);
        return reserveList.getId();
    }

    @Override
    public void updateReserveList(ReserveListSaveReqVO updateReqVO) {
        ReserveListDO existing = validateReserveListExists(updateReqVO.getId());
        // update 接口不允许修改 status，必须走专属接口
        ReserveListDO updateObj = BeanUtils.toBean(updateReqVO, ReserveListDO.class);
        updateObj.setStatus(existing.getStatus());
        reserveListMapper.updateById(updateObj);
    }

    @Override
    public void deleteReserveList(Long id) {
        validateReserveListExists(id);
        reserveListMapper.deleteById(id);
    }

    @Override
    public void deleteReserveListListByIds(List<Long> ids) {
        reserveListMapper.deleteByIds(ids);
    }

    private ReserveListDO validateReserveListExists(Long id) {
        ReserveListDO existing = reserveListMapper.selectById(id);
        if (existing == null) {
            throw exception(RESERVE_LIST_NOT_EXISTS);
        }
        return existing;
    }

    private void validateStatus(ReserveListDO reserve, ReserveStatusEnum expected) {
        if (!expected.getLabel().equals(reserve.getStatus())) {
            throw exception(RESERVE_LIST_STATUS_INVALID);
        }
    }

    @Override
    public ReserveListDO getReserveList(Long id) {
        return reserveListMapper.selectById(id);
    }

    @Override
    public PageResult<ReserveListDO> getReserveListPage(ReserveListPageReqVO pageReqVO) {
        return reserveListMapper.selectPage(pageReqVO);
    }

    // ========== 业务操作 ==========

    @Override
    public void auditReserveList(ReserveListAuditReqVO reqVO) {
        ReserveListDO reserve = validateReserveListExists(reqVO.getId());
        validateStatus(reserve, ReserveStatusEnum.WAITING_AUDIT);
        ReserveListDO update = new ReserveListDO();
        update.setId(reqVO.getId());
        update.setStatus(Boolean.TRUE.equals(reqVO.getApproved())
                ? ReserveStatusEnum.EFFECTIVE.getLabel()
                : ReserveStatusEnum.CANCELLED.getLabel());
        update.setAuditUserId(SecurityFrameworkUtils.getLoginUserId());
        update.setAuditTime(LocalDateTime.now());
        update.setAuditRemark(reqVO.getAuditRemark());
        if (Boolean.FALSE.equals(reqVO.getApproved())) {
            update.setRejectReason(reqVO.getRejectReason());
        }
        reserveListMapper.updateById(update);
        notifyAuditResult(reserve, Boolean.TRUE.equals(reqVO.getApproved()), reqVO.getRejectReason());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchAuditReserveList(ReserveListBatchAuditReqVO reqVO) {
        if (reqVO.getIds() == null || reqVO.getIds().isEmpty()) {
            return;
        }
        boolean approved = ReserveListBatchAuditReqVO.AUDIT_RESULT_PASS.equals(reqVO.getAuditResult());
        LocalDateTime now = LocalDateTime.now();
        Long auditorId = SecurityFrameworkUtils.getLoginUserId();
        String targetStatus = approved
                ? ReserveStatusEnum.EFFECTIVE.getLabel()
                : ReserveStatusEnum.CANCELLED.getLabel();
        for (Long id : reqVO.getIds()) {
            ReserveListDO reserve = validateReserveListExists(id);
            validateStatus(reserve, ReserveStatusEnum.WAITING_AUDIT);
            ReserveListDO update = new ReserveListDO();
            update.setId(id);
            update.setStatus(targetStatus);
            update.setAuditUserId(auditorId);
            update.setAuditTime(now);
            update.setAuditRemark(reqVO.getAuditRemark());
            if (!approved) {
                update.setRejectReason(reqVO.getRejectReason());
            }
            reserveListMapper.updateById(update);
            notifyAuditResult(reserve, approved, reqVO.getRejectReason());
        }
    }

    @Override
    public void cancelReserveList(Long id) {
        ReserveListDO reserve = validateReserveListExists(id);
        validateStatus(reserve, ReserveStatusEnum.EFFECTIVE);
        ReserveListDO update = new ReserveListDO();
        update.setId(id);
        update.setStatus(ReserveStatusEnum.CANCELLED.getLabel());
        reserveListMapper.updateById(update);
    }

    @Override
    public void completeReserveList(Long id) {
        ReserveListDO reserve = validateReserveListExists(id);
        validateStatus(reserve, ReserveStatusEnum.EFFECTIVE);
        ReserveListDO update = new ReserveListDO();
        update.setId(id);
        update.setStatus(ReserveStatusEnum.COMPLETED.getLabel());
        update.setFinishTime(LocalDateTime.now());
        reserveListMapper.updateById(update);
    }

    @Override
    public void evaluateReserveList(ReserveListEvaluateReqVO reqVO) {
        ReserveListDO reserve = validateReserveListExists(reqVO.getId());
        validateStatus(reserve, ReserveStatusEnum.COMPLETED);
        ReserveListDO update = new ReserveListDO();
        update.setId(reqVO.getId());
        update.setScore(reqVO.getScore());
        update.setEvaluateContent(reqVO.getEvaluateContent());
        reserveListMapper.updateById(update);
    }

}
