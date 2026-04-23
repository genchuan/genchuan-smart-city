package cn.iocoder.yudao.module.chargepark.carservice.service.complaint;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.UserAppealAuditReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.UserAppealBatchAuditReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.UserAppealExecuteReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.UserAppealFeedbackReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.UserAppealPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.UserAppealSaveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.complaint.UserAppealDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 用户申诉 Service 接口
 *
 * @author carservice
 */
public interface UserAppealService {

    Long createUserAppeal(@Valid UserAppealSaveReqVO createReqVO);

    void updateUserAppeal(@Valid UserAppealSaveReqVO updateReqVO);

    void deleteUserAppeal(Long id);

    void deleteUserAppealListByIds(List<Long> ids);

    UserAppealDO getUserAppeal(Long id);

    PageResult<UserAppealDO> getUserAppealPage(UserAppealPageReqVO pageReqVO);

    // ========== 业务操作（状态机） ==========

    /** 审核：待审核 → 待处置（通过）/ 已完成（驳回） */
    void auditUserAppeal(UserAppealAuditReqVO reqVO);

    /** 批量审核 */
    void batchAuditUserAppeal(UserAppealBatchAuditReqVO reqVO);

    /** 执行:待处置 → 待处置(标记处置人为当前登录用户,触发处置流程) */
    void executeUserAppeal(UserAppealExecuteReqVO reqVO);

    /** 反馈：待处置 → 已完成 */
    void feedbackUserAppeal(UserAppealFeedbackReqVO reqVO);

}
