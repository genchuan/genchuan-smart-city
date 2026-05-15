package cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.parkingpayment;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.parkingpayment.vo.*;
import cn.iocoder.yudao.module.accessmgmt.service.parkingmgmt.parkingpayment.ParkingPaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 停车缴费")
@RestController
@RequestMapping("/accessmgmt/parking-payment")
@Validated
public class ParkingPaymentController {

    @Resource
    private ParkingPaymentService parkingPaymentService;

    @GetMapping("/page")
    @Operation(summary = "获得停车缴费分页")
    @PreAuthorize("@ss.hasPermission('parking-payment:query')")
    public CommonResult<PageResult<ParkingPaymentRespVO>> getParkingPaymentPage(@Valid ParkingPaymentPageReqVO pageReqVO) {
        PageResult<ParkingPaymentRespVO> pageResult = parkingPaymentService.getParkingPaymentPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/get")
    @Operation(summary = "获得停车缴费详情")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('parking-payment:query')")
    public CommonResult<ParkingPaymentRespVO> getParkingPayment(@RequestParam("id") Long id) {
        ParkingPaymentRespVO respVO = parkingPaymentService.getParkingPayment(id);
        return success(respVO);
    }

    @PostMapping("/generate")
    @Operation(summary = "生成停车账单")
    @PreAuthorize("@ss.hasPermission('parking-payment:generate')")
    public CommonResult<ParkingPaymentGenerateRespVO> generateParkingPayment(@Valid @RequestBody ParkingPaymentGenerateReqVO reqVO) {
        ParkingPaymentGenerateRespVO respVO = parkingPaymentService.generateParkingPayment(reqVO);
        return success(respVO);
    }

    @PostMapping("/calculate")
    @Operation(summary = "停车费用计算")
    @PreAuthorize("@ss.hasPermission('parking-payment:calculate')")
    public CommonResult<ParkingPaymentCalculateRespVO> calculateParkingPayment(@Valid @RequestBody ParkingPaymentCalculateReqVO reqVO) {
        ParkingPaymentCalculateRespVO respVO = parkingPaymentService.calculateParkingPayment(reqVO);
        return success(respVO);
    }

    @PostMapping("/pay")
    @Operation(summary = "停车缴费支付")
    @PreAuthorize("@ss.hasPermission('parking-payment:pay')")
    public CommonResult<ParkingPaymentPayRespVO> payParkingPayment(@Valid @RequestBody ParkingPaymentPayReqVO reqVO) {
        ParkingPaymentPayRespVO respVO = parkingPaymentService.payParkingPayment(reqVO);
        return success(respVO);
    }

    @PutMapping("/invoice")
    @Operation(summary = "开具发票")
    @PreAuthorize("@ss.hasPermission('parking-payment:invoice')")
    public CommonResult<ParkingPaymentInvoiceRespVO> invoiceParkingPayment(@Valid @RequestBody ParkingPaymentInvoiceReqVO reqVO) {
        ParkingPaymentInvoiceRespVO respVO = parkingPaymentService.invoiceParkingPayment(reqVO);
        return success(respVO);
    }

    @PostMapping("/remind")
    @Operation(summary = "催缴")
    @PreAuthorize("@ss.hasPermission('parking-payment:remind')")
    public CommonResult<Boolean> remindParkingPayment(@Valid @RequestBody ParkingPaymentRemindReqVO reqVO) {
        Boolean result = parkingPaymentService.remindParkingPayment(reqVO);
        return success(result);
    }

    @PutMapping("/discount")
    @Operation(summary = "优惠减免")
    @PreAuthorize("@ss.hasPermission('parking-payment:discount')")
    public CommonResult<Boolean> discountParkingPayment(@Valid @RequestBody ParkingPaymentDiscountReqVO reqVO) {
        Boolean result = parkingPaymentService.discountParkingPayment(reqVO);
        return success(result);
    }

    @PostMapping("/repair")
    @Operation(summary = "补缴")
    @PreAuthorize("@ss.hasPermission('parking-payment:repair')")
    public CommonResult<ParkingPaymentRepairRespVO> repairParkingPayment(@Valid @RequestBody ParkingPaymentRepairReqVO reqVO) {
        ParkingPaymentRepairRespVO respVO = parkingPaymentService.repairParkingPayment(reqVO);
        return success(respVO);
    }

    @GetMapping("/export")
    @Operation(summary = "导出停车缴费 Excel")
    @PreAuthorize("@ss.hasPermission('parking-payment:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkingPaymentExcel(@Valid ParkingPaymentPageReqVO pageReqVO,
                                           HttpServletResponse response) throws IOException {
        String inputFileName = "停车缴费_";

        List<ParkingPaymentRespVO> list = parkingPaymentService.getParkingPaymentList(pageReqVO);

        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        String dateStr = java.time.LocalDate.now().toString();
        String fileOriginName = inputFileName + dateStr + ".xls";
        String fileName = URLEncoder.encode(fileOriginName, StandardCharsets.UTF_8.toString())
                .replaceAll("\\+", "%20").replace("UTF-8", "");
        response.setHeader("Content-Disposition", "attachment; filename*=" + fileName);

        ExcelUtils.write(response, "停车缴费.xls", "数据", ParkingPaymentRespVO.class,
                BeanUtils.toBean(list, ParkingPaymentRespVO.class));
    }

    @GetMapping("/chart")
    @Operation(summary = "停车缴费态势")
    @PreAuthorize("@ss.hasPermission('parking-payment:query')")
    public CommonResult<ParkingPaymentChartRespVO> getParkingPaymentChart(
            @Parameter(name = "startTime", description = "统计开始时间，格式时间戳") @RequestParam(value = "startTime", required = false) Long startTime,
            @Parameter(name = "endTime", description = "统计结束时间，格式时间戳") @RequestParam(value = "endTime", required = false) Long endTime) {
        ParkingPaymentChartRespVO chartVO = parkingPaymentService.getParkingPaymentChart(startTime, endTime);
        return success(chartVO);
    }

}
