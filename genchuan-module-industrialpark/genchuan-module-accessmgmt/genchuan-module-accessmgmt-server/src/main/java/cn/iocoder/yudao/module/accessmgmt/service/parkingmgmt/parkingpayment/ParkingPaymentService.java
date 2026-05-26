package cn.iocoder.yudao.module.accessmgmt.service.parkingmgmt.parkingpayment;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.parkingpayment.vo.*;

import java.util.List;

/**
 * 停车缴费 Service 接口
 *
 * @author 亘川智城
 */
public interface ParkingPaymentService {

    /**
     * 获得停车缴费分页
     */
    PageResult<ParkingPaymentRespVO> getParkingPaymentPage(ParkingPaymentPageReqVO pageReqVO);

    /**
     * 获得停车缴费详情
     */
    ParkingPaymentRespVO getParkingPayment(Long id);

    /**
     * 生成停车账单 —— 创建账单记录，初始状态"待缴费"，生成 PP+时间戳+随机数 格式的账单编号
     */
    ParkingPaymentGenerateRespVO generateParkingPayment(ParkingPaymentGenerateReqVO reqVO);

    /**
     * 费用计算 —— 计算原始费用、优惠费用、实付费用
     */
    ParkingPaymentCalculateRespVO calculateParkingPayment(ParkingPaymentCalculateReqVO reqVO);

    /**
     * 缴费支付 —— 记录支付方式和支付时间，状态变更为"已缴费"
     */
    ParkingPaymentPayRespVO payParkingPayment(ParkingPaymentPayReqVO reqVO);

    /**
     * 开具发票 —— 发票状态变更为"已开具"
     */
    ParkingPaymentInvoiceRespVO invoiceParkingPayment(ParkingPaymentInvoiceReqVO reqVO);

    /**
     * 催缴 —— 状态变更为"已欠费"
     */
    Boolean remindParkingPayment(ParkingPaymentRemindReqVO reqVO);

    /**
     * 优惠减免 —— 写入优惠金额和优惠原因
     */
    Boolean discountParkingPayment(ParkingPaymentDiscountReqVO reqVO);

    /**
     * 补缴 —— 重新支付，记录支付方式和支付时间，状态变更为"已缴费"
     */
    ParkingPaymentRepairRespVO repairParkingPayment(ParkingPaymentRepairReqVO reqVO);

    /**
     * 获得停车缴费列表（导出用，全量不分页）
     */
    List<ParkingPaymentRespVO> getParkingPaymentList(ParkingPaymentPageReqVO pageReqVO);

    /**
     * 停车缴费态势 —— 卡片统计 + 每日收入趋势 + 每日支付笔数趋势
     */
    ParkingPaymentChartRespVO getParkingPaymentChart(Long startTime, Long endTime);

}
