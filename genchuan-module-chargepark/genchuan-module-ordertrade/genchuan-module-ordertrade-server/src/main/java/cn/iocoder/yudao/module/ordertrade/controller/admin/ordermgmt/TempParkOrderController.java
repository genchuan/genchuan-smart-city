package cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt.TempParkOrderDO;
import cn.iocoder.yudao.module.ordertrade.service.ordermgmt.TempParkOrderService;
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
 * 管理后台 - 临时停车订单
 *
 * @author genchuan
 */
@Tag(name = "管理后台  - 订单管理  - 临时停车订单")
@RestController
@RequestMapping("/ordertrade/temp-park-order")
@Validated
public class TempParkOrderController {

    @Resource
    private TempParkOrderService tempParkOrderService;

    // ==================== ① 标准CRUD ====================
/*

    @PostMapping("/create")
    @Operation(summary = "创建临时停车订单")
    public CommonResult<Long> createTempParkOrder(@Valid @RequestBody TempParkOrderSaveReqVO createReqVO) {
        return success(tempParkOrderService.createTempParkOrder(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新临时停车订单")
    public CommonResult<Boolean> updateTempParkOrder(@Valid @RequestBody TempParkOrderSaveReqVO updateReqVO) {
        tempParkOrderService.updateTempParkOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除临时停车订单")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> deleteTempParkOrder(@RequestParam("id") Long id) {
        tempParkOrderService.deleteTempParkOrder(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除临时停车订单")
    @Parameter(name = "ids", description = "主键列表", required = true)
    public CommonResult<Boolean> deleteTempParkOrderList(@RequestParam("ids") List<Long> ids) {
        tempParkOrderService.deleteTempParkOrderListByIds(ids);
        return success(true);
    }
*/

    @GetMapping("/get")
    @Operation(summary = "获得临时停车订单详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<TempParkOrderRespVO> getTempParkOrder(@RequestParam("id") Long id) {
        TempParkOrderDO obj = tempParkOrderService.getTempParkOrder(id);
        return success(BeanUtils.toBean(obj, TempParkOrderRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得临时停车订单分页列表")
    public CommonResult<PageResult<TempParkOrderRespVO>> getTempParkOrderPage(@Valid TempParkOrderPageReqVO pageReqVO) {
        PageResult<TempParkOrderDO> pageResult = tempParkOrderService.getTempParkOrderPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TempParkOrderRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出临时停车订单 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTempParkOrderExcel(@Valid TempParkOrderPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TempParkOrderDO> list = tempParkOrderService.getTempParkOrderPage(pageReqVO).getList();
        ExcelUtils.write(response, "临时停车订单.xls", "数据", TempParkOrderRespVO.class,
                BeanUtils.toBean(list, TempParkOrderRespVO.class));
    }

    @GetMapping("/batch-export")
    @Operation(summary = "批量导出临时停车订单 Excel（芋道原生导出风格）")
    @ApiAccessLog(operateType = EXPORT)
    public void batchExportTempParkOrderExcel(@Valid TempParkOrderPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TempParkOrderDO> list = tempParkOrderService.getTempParkOrderPage(pageReqVO).getList();
        ExcelUtils.write(response, "临时停车订单批量导出.xls", "数据", TempParkOrderRespVO.class,
                BeanUtils.toBean(list, TempParkOrderRespVO.class));
    }

    // ==================== ② 业务操作接口 ====================

    @PutMapping("/pay")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "支付临时停车订单")
    public CommonResult<Boolean> payTempParkOrder(@Valid @RequestBody IdReqVO reqVO) {
        tempParkOrderService.payTempParkOrder(reqVO);
        return success(true);
    }
    @PutMapping("/refund")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "发起退款")
    public CommonResult<Boolean> refundTempParkOrder(@Valid @RequestBody IdReqVO reqVO) {
        tempParkOrderService.refundTempParkOrder(reqVO);
        return success(true);
    }
    @PutMapping("/invoice")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "申请开票")
    public CommonResult<Boolean> invoiceTempParkOrder(@Valid @RequestBody IdReqVO reqVO) {
        tempParkOrderService.invoiceTempParkOrder(reqVO);
        return success(true);
    }
    @PutMapping("/cancel")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "取消订单")
    public CommonResult<Boolean> cancelTempParkOrder(@Valid @RequestBody IdReqVO reqVO) {
        tempParkOrderService.cancelTempParkOrder(reqVO);
        return success(true);
    }

    // ==================== ③ 数据可视化接口 ====================

    @GetMapping("/chart")
    @Operation(summary = "获得临时停车订单统计图表数据（折线图+柱状图/饼图+卡片）")
    public CommonResult<TempParkOrderChartRespVO> getTempParkOrderChart(@Valid TempParkOrderChartReqVO chartReqVO) {
        return success(tempParkOrderService.getTempParkOrderChart(chartReqVO));
    }
}
