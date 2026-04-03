package cn.iocoder.yudao.module.vehiclecharging.service.settlementbill;

import java.util.*;

import cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo.SettlementBillPageReqVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo.SettlementBillSaveReqVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo.SettlementBillSummaryRespVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo.chart.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo.ops.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.settlementbill.SettlementBillDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 结算单 Service 接口
 *
 * @author 亘川智城
 */
public interface SettlementBillService {

    // 图表统计
    SettlementBillSummaryRespVO getSettlementBillChart(SettlementBillChartReqVO reqVO);
    SettlementBillDailyTrendRespVO getDailyTrend(SettlementBillDailyTrendReqVO reqVO);
    SettlementBillCooperatorAmountRespVO getCooperatorAmount(SettlementBillCooperatorAmountReqVO reqVO);
    SettlementBillCountRespVO getBillCount(SettlementBillCountReqVO reqVO);
    /**
     * 创建结算单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSettlementBill(@Valid SettlementBillSaveReqVO createReqVO);

    /**
     * 更新结算单
     *
     * @param updateReqVO 更新信息
     */
    void updateSettlementBill(@Valid SettlementBillSaveReqVO updateReqVO);

    /**
     * 删除结算单
     *
     * @param id 编号
     */
    void deleteSettlementBill(Long id);

    /**
    * 批量删除结算单
    *
    * @param ids 编号
    */
    void deleteSettlementBillListByIds(List<Long> ids);

    /**
     * 获得结算单
     *
     * @param id 编号
     * @return 结算单
     */
    SettlementBillDO getSettlementBill(Long id);

    /**
     * 获得结算单分页
     *
     * @param pageReqVO 分页查询
     * @return 结算单分页
     */
    PageResult<SettlementBillDO> getSettlementBillPage(SettlementBillPageReqVO pageReqVO);

    void auditSettlementBill(SettlementBillAuditReqVO reqVO);

    void settleSettlementBill(SettlementBillSettleReqVO reqVO);

    Integer createBatchSettlementBill(SettlementBillCreateBatchReqVO reqVO);

    void rejectSettlementBill(SettlementBillRejectReqVO reqVO);

    void reauditSettlementBill(SettlementBillReauditReqVO reqVO);

    void updateSettlementBillRemark(SettlementBillRemarkReqVO reqVO);
}
