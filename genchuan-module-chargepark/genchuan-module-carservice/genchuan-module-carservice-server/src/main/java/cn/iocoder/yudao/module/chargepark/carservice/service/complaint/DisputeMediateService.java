package cn.iocoder.yudao.module.chargepark.carservice.service.complaint;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.DisputeMediateMediateReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.DisputeMediatePageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.DisputeMediateSaveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.DisputeMediateUpdateProgressReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.complaint.DisputeMediateDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 纠纷调解 Service 接口
 *
 * @author carservice
 */
public interface DisputeMediateService {

    Long createDisputeMediate(@Valid DisputeMediateSaveReqVO createReqVO);

    void updateDisputeMediate(@Valid DisputeMediateSaveReqVO updateReqVO);

    void deleteDisputeMediate(Long id);

    void deleteDisputeMediateListByIds(List<Long> ids);

    DisputeMediateDO getDisputeMediate(Long id);

    PageResult<DisputeMediateDO> getDisputeMediatePage(DisputeMediatePageReqVO pageReqVO);

    // ========== 业务操作（状态机） ==========

    /** 调解：待调解 → 调解中（只设调解人为当前用户,不更新进度） */
    void mediateDisputeMediate(DisputeMediateMediateReqVO reqVO);

    /** 更新进度:调解中 → 调解中（仅更新 progress 字段） */
    void updateDisputeMediateProgress(DisputeMediateUpdateProgressReqVO reqVO);

    /** 确认：调解中 → 已完成 */
    void confirmDisputeMediate(Long id);

}
