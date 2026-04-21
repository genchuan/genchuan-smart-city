package cn.iocoder.yudao.module.ordertrade.controller.admin.merchantreconcile;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.merchantreconcile.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.merchantreconcile.ReconcileBillDO;
import cn.iocoder.yudao.module.ordertrade.service.merchantreconcile.ReconcileBillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.UPDATE;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "订单交易 - 商户对账 - 对账账单")
@RestController
@RequestMapping("/ordertrade/reconcile-bill")
@Validated
public class ReconcileBillController {

    @Resource
    private ReconcileBillService reconcileBillService;

    @PostMapping("/create")
    @Operation(summary = "创建对账账单")
    public CommonResult<Long> createReconcileBill(@Valid @RequestBody ReconcileBillSaveReqVO createReqVO) {
        return success(reconcileBillService.createReconcileBill(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新对账账单")
    public CommonResult<Boolean> updateReconcileBill(@Valid @RequestBody ReconcileBillSaveReqVO updateReqVO) {
        reconcileBillService.updateReconcileBill(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除对账账单")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> deleteReconcileBill(@RequestParam("id") Long id) {
        reconcileBillService.deleteReconcileBill(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得对账账单详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<ReconcileBillRespVO> getReconcileBill(@RequestParam("id") Long id) {
        ReconcileBillDO obj = reconcileBillService.getReconcileBill(id);
        return success(BeanUtils.toBean(obj, ReconcileBillRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得对账账单分页列表")
    public CommonResult<PageResult<ReconcileBillRespVO>> getReconcileBillPage(@Valid ReconcileBillPageReqVO pageReqVO) {
        PageResult<ReconcileBillDO> pageResult = reconcileBillService.getReconcileBillPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ReconcileBillRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出对账账单 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportReconcileBillExcel(@Valid ReconcileBillPageReqVO pageReqVO,
                                         HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ReconcileBillDO> list = reconcileBillService.getReconcileBillPage(pageReqVO).getList();
        ExcelUtils.write(response, "对账账单.xls", "数据", ReconcileBillRespVO.class,
                BeanUtils.toBean(list, ReconcileBillRespVO.class));
    }

    @PutMapping("/confirm")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "确认对账账单")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> confirmReconcileBill(@RequestParam("id") Long id) {
        reconcileBillService.confirmReconcileBill(id);
        return success(true);
    }

    @PutMapping("/dispute")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "提出对账异议")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> disputeReconcileBill(@RequestParam("id") Long id,
                                                      @RequestParam(value = "reason", required = false) String reason) {
        reconcileBillService.disputeReconcileBill(id, reason);
        return success(true);
    }

    @PutMapping("/resolve")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "解决对账异议")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> resolveReconcileBill(@RequestParam("id") Long id) {
        reconcileBillService.resolveReconcileBill(id);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获得对账账单统计图表数据")
    public CommonResult<ReconcileBillChartRespVO> getReconcileBillChart(@Valid ReconcileBillChartReqVO chartReqVO) {
        return success(reconcileBillService.getReconcileBillChart(chartReqVO));
    }
}
