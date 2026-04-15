package cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt.BikeChargeOrderDO;
import cn.iocoder.yudao.module.ordertrade.service.ordermgmt.BikeChargeOrderService;
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
 * 管理后台 - 两轮充电订单
 *
 * @author genchuan
 */
@Tag(name = "管理后台 -订单管理- 两轮充电订单")
@RestController
@RequestMapping("/ordertrade/bike-charge-order")
@Validated
public class BikeChargeOrderController {

    @Resource
    private BikeChargeOrderService bikeChargeOrderService;

    // ==================== ① 标准CRUD ====================
/*
    @PostMapping("/create")
    @Operation(summary = "创建两轮充电订单")
    public CommonResult<Long> createBikeChargeOrder(@Valid @RequestBody BikeChargeOrderSaveReqVO createReqVO) {
        return success(bikeChargeOrderService.createBikeChargeOrder(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新两轮充电订单")
    public CommonResult<Boolean> updateBikeChargeOrder(@Valid @RequestBody BikeChargeOrderSaveReqVO updateReqVO) {
        bikeChargeOrderService.updateBikeChargeOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除两轮充电订单")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> deleteBikeChargeOrder(@RequestParam("id") Long id) {
        bikeChargeOrderService.deleteBikeChargeOrder(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除两轮充电订单")
    @Parameter(name = "ids", description = "主键列表", required = true)
    public CommonResult<Boolean> deleteBikeChargeOrderList(@RequestParam("ids") List<Long> ids) {
        bikeChargeOrderService.deleteBikeChargeOrderListByIds(ids);
        return success(true);
    }*/

    @GetMapping("/get")
    @Operation(summary = "获得两轮充电订单详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<BikeChargeOrderRespVO> getBikeChargeOrder(@RequestParam("id") Long id) {
        BikeChargeOrderDO obj = bikeChargeOrderService.getBikeChargeOrder(id);
        return success(BeanUtils.toBean(obj, BikeChargeOrderRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得两轮充电订单分页列表")
    public CommonResult<PageResult<BikeChargeOrderRespVO>> getBikeChargeOrderPage(@Valid BikeChargeOrderPageReqVO pageReqVO) {
        PageResult<BikeChargeOrderDO> pageResult = bikeChargeOrderService.getBikeChargeOrderPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BikeChargeOrderRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出两轮充电订单 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBikeChargeOrderExcel(@Valid BikeChargeOrderPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BikeChargeOrderDO> list = bikeChargeOrderService.getBikeChargeOrderPage(pageReqVO).getList();
        ExcelUtils.write(response, "两轮充电订单.xls", "数据", BikeChargeOrderRespVO.class,
                BeanUtils.toBean(list, BikeChargeOrderRespVO.class));
    }

    @GetMapping("/batch-export")
    @Operation(summary = "批量导出两轮充电订单 Excel（芋道原生导出风格）")
    @ApiAccessLog(operateType = EXPORT)
    public void batchExportBikeChargeOrderExcel(@Valid BikeChargeOrderPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BikeChargeOrderDO> list = bikeChargeOrderService.getBikeChargeOrderPage(pageReqVO).getList();
        ExcelUtils.write(response, "两轮充电订单批量导出.xls", "数据", BikeChargeOrderRespVO.class,
                BeanUtils.toBean(list, BikeChargeOrderRespVO.class));
    }

    // ==================== ② 业务操作接口 ====================

    @PutMapping("/stop")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "停止充电")
    public CommonResult<Boolean> stopBikeChargeOrder(@Valid @RequestBody IdReqVO reqVO) {
        bikeChargeOrderService.stopBikeChargeOrder(reqVO);
        return success(true);
    }
    @PutMapping("/pay")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "支付两轮充电订单")
    public CommonResult<Boolean> payBikeChargeOrder(@Valid @RequestBody IdReqVO reqVO) {
        bikeChargeOrderService.payBikeChargeOrder(reqVO);
        return success(true);
    }
    @PutMapping("/refund")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "发起退款")
    public CommonResult<Boolean> refundBikeChargeOrder(@Valid @RequestBody IdReqVO reqVO) {
        bikeChargeOrderService.refundBikeChargeOrder(reqVO);
        return success(true);
    }
    @PutMapping("/invoice")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "申请开票")
    public CommonResult<Boolean> invoiceBikeChargeOrder(@Valid @RequestBody IdReqVO reqVO) {
        bikeChargeOrderService.invoiceBikeChargeOrder(reqVO);
        return success(true);
    }
    @PutMapping("/cancel")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "取消订单")
    public CommonResult<Boolean> cancelBikeChargeOrder(@Valid @RequestBody IdReqVO reqVO) {
        bikeChargeOrderService.cancelBikeChargeOrder(reqVO);
        return success(true);
    }

    // ==================== ③ 数据可视化接口 ====================

    @GetMapping("/chart")
    @Operation(summary = "获得两轮充电订单统计图表数据（折线图+柱状图/饼图+卡片）")
    public CommonResult<BikeChargeOrderChartRespVO> getBikeChargeOrderChart(@Valid BikeChargeOrderChartReqVO chartReqVO) {
        return success(bikeChargeOrderService.getBikeChargeOrderChart(chartReqVO));
    }
}
