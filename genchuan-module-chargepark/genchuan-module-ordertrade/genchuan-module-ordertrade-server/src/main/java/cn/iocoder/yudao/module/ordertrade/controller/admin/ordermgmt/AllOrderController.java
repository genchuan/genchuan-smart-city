package cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt.AllOrderDO;
import cn.iocoder.yudao.module.ordertrade.service.ordermgmt.AllOrderService;
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

/**
 * 管理后台 - 全部订单
 *
 * @author genchuan
 */
@Tag(name = "订单交易 - 订单管理  - 全部订单")
@RestController
@RequestMapping("/ordertrade/all-order")
@Validated
public class AllOrderController {

    @Resource
    private AllOrderService allOrderService;

    // ==================== ① 标准CRUD ====================
/*
    @PostMapping("/create")
    @Operation(summary = "创建全部订单")
    public CommonResult<Long> createAllOrder(@Valid @RequestBody AllOrderSaveReqVO createReqVO) {
        return success(allOrderService.createAllOrder(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新全部订单")
    public CommonResult<Boolean> updateAllOrder(@Valid @RequestBody AllOrderSaveReqVO updateReqVO) {
        allOrderService.updateAllOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除全部订单")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> deleteAllOrder(@RequestParam("id") Long id) {
        allOrderService.deleteAllOrder(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除全部订单")
    @Parameter(name = "ids", description = "主键列表", required = true)
    public CommonResult<Boolean> deleteAllOrderList(@RequestParam("ids") List<Long> ids) {
        allOrderService.deleteAllOrderListByIds(ids);
        return success(true);
    }
*/
    @GetMapping("/get")
    @Operation(summary = "获得全部订单详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<AllOrderRespVO> getAllOrder(@RequestParam("id") Long id) {
        AllOrderDO obj = allOrderService.getAllOrder(id);
        return success(BeanUtils.toBean(obj, AllOrderRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得全部订单分页列表")
    public CommonResult<PageResult<AllOrderRespVO>> getAllOrderPage(@Valid AllOrderPageReqVO pageReqVO) {
        PageResult<AllOrderDO> pageResult = allOrderService.getAllOrderPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AllOrderRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出订单 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAllOrderExcel(@Valid AllOrderPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AllOrderDO> list = allOrderService.getAllOrderPage(pageReqVO).getList();
        ExcelUtils.write(response, "全部订单.xls", "数据", AllOrderRespVO.class,
                BeanUtils.toBean(list, AllOrderRespVO.class));
    }

    @GetMapping("/batch-export")
    @Operation(summary = "批量导出全部订单 Excel（芋道原生导出风格）")
    @ApiAccessLog(operateType = EXPORT)
    public void batchExportAllOrderExcel(@Valid AllOrderPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AllOrderDO> list = allOrderService.getAllOrderPage(pageReqVO).getList();
        ExcelUtils.write(response, "全部订单批量导出.xls", "数据", AllOrderRespVO.class,
                BeanUtils.toBean(list, AllOrderRespVO.class));
    }

    // ==================== ② 业务操作接口 ====================

    @PutMapping("/pay")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "支付订单")
    public CommonResult<Boolean> payAllOrder(@Valid @RequestBody IdReqVO reqVO) {
        allOrderService.payAllOrder(reqVO);
        return success(true);
    }
    @PutMapping("/refund")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "发起退款")
    public CommonResult<Boolean> refundAllOrder(@Valid @RequestBody IdReqVO reqVO) {
        allOrderService.refundAllOrder(reqVO);
        return success(true);
    }
    @PutMapping("/invoice")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "申请开票")
    public CommonResult<Boolean> invoiceAllOrder(@Valid @RequestBody IdReqVO reqVO) {
        allOrderService.invoiceAllOrder(reqVO);
        return success(true);
    }
    @PutMapping("/cancel")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "取消订单")
    public CommonResult<Boolean> cancelAllOrder(@Valid @RequestBody IdReqVO reqVO) {
        allOrderService.cancelAllOrder(reqVO);
        return success(true);
    }

    // ==================== ③ 数据可视化接口 ====================

    @GetMapping("/chart")
    @Operation(summary = "获得全部订单统计图表数据（折线图+柱状图/饼图+卡片）")
    public CommonResult<AllOrderChartRespVO> getAllOrderChart(@Valid AllOrderChartReqVO chartReqVO) {
        return success(allOrderService.getAllOrderChart(chartReqVO));
    }
}
