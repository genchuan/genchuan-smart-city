package cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill;

import cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo.SettlementBillPageReqVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo.SettlementBillRespVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo.SettlementBillSaveReqVO;

import cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo.chart.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo.ops.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.settlementbill.SettlementBillDO;
import cn.iocoder.yudao.module.vehiclecharging.service.settlementbill.SettlementBillService;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;


@Tag(name = "管理后台 - 结算单")
@RestController
@RequestMapping("/vehiclecharging/settlement-bill")
@Validated
public class SettlementBillController {

    @Resource
    private SettlementBillService settlementBillService;
    // ==================== 图表统计接口 ====================
    @GetMapping("/chart")
    @Operation(summary = "结算单处理统计图表（总览）")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:settlement_bill:query')")
    public CommonResult<SettlementBillSummaryRespVO> settlementBillChart(SettlementBillChartReqVO reqVO) {
        return success(settlementBillService.getSettlementBillChart(reqVO));
    }

    @GetMapping("/chart/dailyTrend")
    @Operation(summary = "每日结算单数量及完成数量趋势")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:settlement_bill:query')")
    public CommonResult<SettlementBillDailyTrendRespVO> settlementBillDailyTrend(SettlementBillDailyTrendReqVO reqVO) {
        return success(settlementBillService.getDailyTrend(reqVO));
    }

    @GetMapping("/chart/cooperatorAmount")
    @Operation(summary = "各合作方结算金额统计")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:settlement_bill:query')")
    public CommonResult<SettlementBillCooperatorAmountRespVO> settlementBillCooperatorAmount(SettlementBillCooperatorAmountReqVO reqVO) {
        return success(settlementBillService.getCooperatorAmount(reqVO));
    }

    @GetMapping("/chart/billCount")
    @Operation(summary = "结算单状态统计（卡片钻取）")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:settlement_bill:query')")
    public CommonResult<SettlementBillCountRespVO> settlementBillCount(SettlementBillCountReqVO reqVO) {
        return success(settlementBillService.getBillCount(reqVO));
    }

    // ==================== 5、重新审核接口 ====================
    @PutMapping("/reaudit")
    @Operation(summary = "重新审核结算单", description = "仅允许对【已驳回】状态执行，重置为【待审核】")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:settlement_bill:reaudit')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> reauditSettlementBill(@Valid @RequestBody SettlementBillReauditReqVO reqVO) {
        settlementBillService.reauditSettlementBill(reqVO);
        return success(true);
    }

    // ==================== 6、修改备注接口 ====================
    @PutMapping("/remark")
    @Operation(summary = "修改结算单备注", description = "修改结算单备注信息，自动更新时间")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:settlement_bill:remark')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> updateSettlementBillRemark(@Valid @RequestBody SettlementBillRemarkReqVO reqVO) {
        settlementBillService.updateSettlementBillRemark(reqVO);
        return success(true);
    }
    @PutMapping("/reject")
    @Operation(summary = "驳回结算单", description = "仅允许对待审核状态执行，状态改为已驳回，自动记录驳回原因")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:settlement_bill:reject')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> rejectSettlementBill(@Valid @RequestBody SettlementBillRejectReqVO reqVO) {
        settlementBillService.rejectSettlementBill(reqVO);
        return success(true);
    }
    @PostMapping("/createBatch")
    @Operation(summary = "批量生成结算单")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:settlement_bill:create')")
    public CommonResult<Integer> createBatchSettlementBill(@Valid @RequestBody SettlementBillCreateBatchReqVO reqVO) {
        Integer count = settlementBillService.createBatchSettlementBill(reqVO);
        return success(count);
    }
    // ==================== 新增：审核接口 ====================
    @PutMapping("/audit")
    @Operation(summary = "审核结算单", description = "仅允许对待审核状态执行，自动填充审核人、审核时间、审核备注")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:settlement_bill:audit')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> auditSettlementBill(@Valid @RequestBody SettlementBillAuditReqVO reqVO) {
        settlementBillService.auditSettlementBill(reqVO);
        return success(true);
    }

    // ==================== 新增：结算接口 ====================
    @PutMapping("/settle")
    @Operation(summary = "结算结算单", description = "仅允许对审核通过状态执行，自动填充结算时间、结算渠道，状态改为已完成")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:settlement_bill:settle')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> settleSettlementBill(@Valid @RequestBody SettlementBillSettleReqVO reqVO) {
        settlementBillService.settleSettlementBill(reqVO);
        return success(true);
    }

    @PostMapping("/create")
    @Operation(summary = "(勿用)创建结算单")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:settlement-bill:create')")
    public CommonResult<Long> createSettlementBill(@Valid @RequestBody SettlementBillSaveReqVO createReqVO) {
        return success(settlementBillService.createSettlementBill(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新结算单")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:settlement-bill:update')")
    public CommonResult<Boolean> updateSettlementBill(@Valid @RequestBody SettlementBillSaveReqVO updateReqVO) {
        settlementBillService.updateSettlementBill(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除结算单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('vehiclecharging:settlement-bill:delete')")
    public CommonResult<Boolean> deleteSettlementBill(@RequestParam("id") Long id) {
        settlementBillService.deleteSettlementBill(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除结算单")
                @PreAuthorize("@ss.hasPermission('vehiclecharging:settlement-bill:delete')")
    public CommonResult<Boolean> deleteSettlementBillList(@RequestParam("ids") List<Long> ids) {
        settlementBillService.deleteSettlementBillListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得结算单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:settlement-bill:query')")
    public CommonResult<SettlementBillRespVO> getSettlementBill(@RequestParam("id") Long id) {
        SettlementBillDO settlementBill = settlementBillService.getSettlementBill(id);
        return success(BeanUtils.toBean(settlementBill, SettlementBillRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得结算单分页")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:settlement-bill:query')")
    public CommonResult<PageResult<SettlementBillRespVO>> getSettlementBillPage(@Valid SettlementBillPageReqVO pageReqVO) {
        PageResult<SettlementBillDO> pageResult = settlementBillService.getSettlementBillPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SettlementBillRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出结算单 Excel")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:settlement-bill:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSettlementBillExcel(@Valid SettlementBillPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SettlementBillDO> list = settlementBillService.getSettlementBillPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "结算单.xls", "数据", SettlementBillRespVO.class,
                        BeanUtils.toBean(list, SettlementBillRespVO.class));
    }

}
