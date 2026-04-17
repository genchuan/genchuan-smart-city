package cn.iocoder.yudao.module.chargepark.carservice.service.reserve;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.reserve.vo.ReserveListAuditReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.reserve.vo.ReserveListBatchAuditReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.reserve.vo.ReserveListEvaluateReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.reserve.vo.ReserveListPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.reserve.vo.ReserveListSaveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.reserve.ReserveListDO;

import java.util.List;

/**
 * 预约列表 Service 接口
 *
 * @author carservice
 */
public interface ReserveListService {

    Long createReserveList(ReserveListSaveReqVO createReqVO);

    void updateReserveList(ReserveListSaveReqVO updateReqVO);

    void deleteReserveList(Long id);

    void deleteReserveListListByIds(List<Long> ids);

    ReserveListDO getReserveList(Long id);

    PageResult<ReserveListDO> getReserveListPage(ReserveListPageReqVO pageReqVO);

    // ========== 业务操作（状态机） ==========

    /** 审核：待审核 → 已生效 / 已取消 */
    void auditReserveList(ReserveListAuditReqVO reqVO);

    /** 批量审核 */
    void batchAuditReserveList(ReserveListBatchAuditReqVO reqVO);

    /** 取消：已生效 → 已取消 */
    void cancelReserveList(Long id);

    /** 完成：已生效 → 已完成 */
    void completeReserveList(Long id);

    /** 评价：已完成状态下评价 */
    void evaluateReserveList(ReserveListEvaluateReqVO reqVO);

}
