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
     * 生成账单
     */
    ParkingPaymentGenerateRespVO generateParkingPayment(ParkingPaymentGenerateReqVO reqVO);

    /**
     * 费用计算
     */
    ParkingPaymentCalculateRespVO calculateParkingPayment(ParkingPaymentCalculateReqVO reqVO);

    /**
     * 缴费支付
     */
    ParkingPaymentPayRespVO payParkingPayment(ParkingPaymentPayReqVO reqVO);

    /**
     * 开具发票
     */
    ParkingPaymentInvoiceRespVO invoiceParkingPayment(ParkingPaymentInvoiceReqVO reqVO);

    /**
     * 催缴
     */
    Boolean remindParkingPayment(ParkingPaymentRemindReqVO reqVO);

    /**
     * 优惠减免
     */
    Boolean discountParkingPayment(ParkingPaymentDiscountReqVO reqVO);

    /**
     * 补缴
     */
    ParkingPaymentRepairRespVO repairParkingPayment(ParkingPaymentRepairReqVO reqVO);

    /**
     * 获得停车缴费列表（导出用）
     */
    List<ParkingPaymentRespVO> getParkingPaymentList(ParkingPaymentPageReqVO pageReqVO);

    /**
     * 停车缴费态势
     */
    ParkingPaymentChartRespVO getParkingPaymentChart(String startTime, String endTime);

}
