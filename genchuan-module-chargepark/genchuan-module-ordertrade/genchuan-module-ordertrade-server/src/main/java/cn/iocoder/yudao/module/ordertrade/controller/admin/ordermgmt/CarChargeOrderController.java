package cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt.CarChargeOrderDO;
import cn.iocoder.yudao.module.ordertrade.service.ordermgmt.CarChargeOrderService;
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
 * 管理后台 - 汽车充电订单
 *
 * @author genchuan
 */
@Tag(name = "订单交易 - 订单管理 - 汽车充电订单")
@RestController
@RequestMapping("/ordertrade/car-charge-order")
@Validated
public class CarChargeOrderController {

    @Resource
    private CarChargeOrderService carChargeOrderService;

    // ==================== ① 标准CRUD ====================

   /* @PostMapping("/create")
    @Operation(summary = "创建汽车充电订单")
    public CommonResult<Long> createCarChargeOrder(@Valid @RequestBody CarChargeOrderSaveReqVO createReqVO) {
        return success(carChargeOrderService.createCarChargeOrder(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新汽车充电订单")
    public CommonResult<Boolean> updateCarChargeOrder(@Valid @RequestBody CarChargeOrderSaveReqVO updateReqVO) {
        carChargeOrderService.updateCarChargeOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除汽车充电订单")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> deleteCarChargeOrder(@RequestParam("id") Long id) {
        carChargeOrderService.deleteCarChargeOrder(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除汽车充电订单")
    @Parameter(name = "ids", description = "主键列表", required = true)
    public CommonResult<Boolean> deleteCarChargeOrderList(@RequestParam("ids") List<Long> ids) {
        carChargeOrderService.deleteCarChargeOrderListByIds(ids);
        return success(true);
    }
*/
    @GetMapping("/get")
    @Operation(summary = "获得汽车充电订单详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<CarChargeOrderRespVO> getCarChargeOrder(@RequestParam("id") Long id) {
        CarChargeOrderDO obj = carChargeOrderService.getCarChargeOrder(id);
        return success(BeanUtils.toBean(obj, CarChargeOrderRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得汽车充电订单分页列表")
    public CommonResult<PageResult<CarChargeOrderRespVO>> getCarChargeOrderPage(@Valid CarChargeOrderPageReqVO pageReqVO) {
        PageResult<CarChargeOrderDO> pageResult = carChargeOrderService.getCarChargeOrderPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CarChargeOrderRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出汽车充电订单 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCarChargeOrderExcel(@Valid CarChargeOrderPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CarChargeOrderDO> list = carChargeOrderService.getCarChargeOrderPage(pageReqVO).getList();
        ExcelUtils.write(response, "汽车充电订单.xls", "数据", CarChargeOrderRespVO.class,
                BeanUtils.toBean(list, CarChargeOrderRespVO.class));
    }

    @GetMapping("/batch-export")
    @Operation(summary = "批量导出汽车充电订单 Excel（芋道原生导出风格）")
    @ApiAccessLog(operateType = EXPORT)
    public void batchExportCarChargeOrderExcel(@Valid CarChargeOrderPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CarChargeOrderDO> list = carChargeOrderService.getCarChargeOrderPage(pageReqVO).getList();
        ExcelUtils.write(response, "汽车充电订单批量导出.xls", "数据", CarChargeOrderRespVO.class,
                BeanUtils.toBean(list, CarChargeOrderRespVO.class));
    }

    // ==================== ② 业务操作接口 ====================

    @PutMapping("/stop")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "停止充电")
    public CommonResult<Boolean> stopCarChargeOrder(@Valid @RequestBody IdReqVO reqVO) {
        carChargeOrderService.stopCarChargeOrder(reqVO);
        return success(true);
    }
    @PutMapping("/pay")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "支付汽车充电订单")
    public CommonResult<Boolean> payCarChargeOrder(@Valid @RequestBody IdReqVO reqVO) {
        carChargeOrderService.payCarChargeOrder(reqVO);
        return success(true);
    }
    @PutMapping("/refund")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "发起退款")
    public CommonResult<Boolean> refundCarChargeOrder(@Valid @RequestBody IdReqVO reqVO) {
        carChargeOrderService.refundCarChargeOrder(reqVO);
        return success(true);
    }
    @PutMapping("/invoice")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "申请开票")
    public CommonResult<Boolean> invoiceCarChargeOrder(@Valid @RequestBody InvoiceOrderReqVO reqVO) {
        carChargeOrderService.invoiceCarChargeOrder(reqVO);
        return success(true);
    }
    @PutMapping("/cancel")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "取消订单")
    public CommonResult<Boolean> cancelCarChargeOrder(@Valid @RequestBody IdReqVO reqVO) {
        carChargeOrderService.cancelCarChargeOrder(reqVO);
        return success(true);
    }

    // ==================== ③ 数据可视化接口 ====================

    @GetMapping("/chart")
    @Operation(summary = "获得汽车充电订单统计图表数据（折线图+柱状图/饼图+卡片）")
    public CommonResult<CarChargeOrderChartRespVO> getCarChargeOrderChart(@Valid CarChargeOrderChartReqVO chartReqVO) {
        return success(carChargeOrderService.getCarChargeOrderChart(chartReqVO));
    }
}
