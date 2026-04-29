package cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt.OfftimeParkOrderDO;
import cn.iocoder.yudao.module.ordertrade.rpc.stationresource.StationNameHelper;
import cn.iocoder.yudao.module.ordertrade.service.ordermgmt.OfftimeParkOrderService;
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
 * 管理后台 - 错时停车订单
 *
 * @author genchuan
 */
@Tag(name = "订单交易  - 订单管理  - 错时停车订单")
@RestController
@RequestMapping("/ordertrade/offtime-park-order")
@Validated
public class OfftimeParkOrderController {

    @Resource
    private OfftimeParkOrderService offtimeParkOrderService;
    @Resource
    private StationNameHelper stationNameHelper;

    // ==================== ① 标准CRUD ====================
/*
    @PostMapping("/create")
    @Operation(summary = "创建错时停车订单")
    public CommonResult<Long> createOfftimeParkOrder(@Valid @RequestBody OfftimeParkOrderSaveReqVO createReqVO) {
        return success(offtimeParkOrderService.createOfftimeParkOrder(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新错时停车订单")
    public CommonResult<Boolean> updateOfftimeParkOrder(@Valid @RequestBody OfftimeParkOrderSaveReqVO updateReqVO) {
        offtimeParkOrderService.updateOfftimeParkOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除错时停车订单")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> deleteOfftimeParkOrder(@RequestParam("id") Long id) {
        offtimeParkOrderService.deleteOfftimeParkOrder(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除错时停车订单")
    @Parameter(name = "ids", description = "主键列表", required = true)
    public CommonResult<Boolean> deleteOfftimeParkOrderList(@RequestParam("ids") List<Long> ids) {
        offtimeParkOrderService.deleteOfftimeParkOrderListByIds(ids);
        return success(true);
    }*/

    @GetMapping("/get")
    @Operation(summary = "获得错时停车订单详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<OfftimeParkOrderRespVO> getOfftimeParkOrder(@RequestParam("id") Long id) {
        OfftimeParkOrderDO obj = offtimeParkOrderService.getOfftimeParkOrder(id);
        OfftimeParkOrderRespVO vo = BeanUtils.toBean(obj, OfftimeParkOrderRespVO.class);
        stationNameHelper.fillStationName(vo, OfftimeParkOrderRespVO::getStationId, OfftimeParkOrderRespVO::setStationName);
        return success(vo);
    }

    @GetMapping("/page")
    @Operation(summary = "获得错时停车订单分页列表")
    public CommonResult<PageResult<OfftimeParkOrderRespVO>> getOfftimeParkOrderPage(@Valid OfftimeParkOrderPageReqVO pageReqVO) {
        PageResult<OfftimeParkOrderDO> pageResult = offtimeParkOrderService.getOfftimeParkOrderPage(pageReqVO);
        PageResult<OfftimeParkOrderRespVO> voPage = BeanUtils.toBean(pageResult, OfftimeParkOrderRespVO.class);
        stationNameHelper.fillStationNames(voPage.getList(), OfftimeParkOrderRespVO::getStationId, OfftimeParkOrderRespVO::setStationName);
        return success(voPage);
    }

    @GetMapping("/export")
    @Operation(summary = "导出错时停车订单 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOfftimeParkOrderExcel(@Valid OfftimeParkOrderPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<OfftimeParkOrderDO> list = offtimeParkOrderService.getOfftimeParkOrderPage(pageReqVO).getList();
        List<OfftimeParkOrderRespVO> voList = BeanUtils.toBean(list, OfftimeParkOrderRespVO.class);
        stationNameHelper.fillStationNames(voList, OfftimeParkOrderRespVO::getStationId, OfftimeParkOrderRespVO::setStationName);
        ExcelUtils.write(response, "错时停车订单.xls", "数据", OfftimeParkOrderRespVO.class, voList);
    }

    @GetMapping("/batch-export")
    @Operation(summary = "批量导出错时停车订单 Excel（芋道原生导出风格）")
    @ApiAccessLog(operateType = EXPORT)
    public void batchExportOfftimeParkOrderExcel(@Valid OfftimeParkOrderPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<OfftimeParkOrderDO> list = offtimeParkOrderService.getOfftimeParkOrderPage(pageReqVO).getList();
        List<OfftimeParkOrderRespVO> voList = BeanUtils.toBean(list, OfftimeParkOrderRespVO.class);
        stationNameHelper.fillStationNames(voList, OfftimeParkOrderRespVO::getStationId, OfftimeParkOrderRespVO::setStationName);
        ExcelUtils.write(response, "错时停车订单批量导出.xls", "数据", OfftimeParkOrderRespVO.class, voList);
    }

    // ==================== ② 业务操作接口 ====================

    @PutMapping("/pay")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "支付错时停车订单")
    public CommonResult<Boolean> payOfftimeParkOrder(@Valid @RequestBody IdReqVO reqVO) {
        offtimeParkOrderService.payOfftimeParkOrder(reqVO);
        return success(true);
    }
    @PutMapping("/refund")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "发起退款")
    public CommonResult<Boolean> refundOfftimeParkOrder(@Valid @RequestBody IdReqVO reqVO) {
        offtimeParkOrderService.refundOfftimeParkOrder(reqVO);
        return success(true);
    }
    @PutMapping("/invoice")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "申请开票")
    public CommonResult<Boolean> invoiceOfftimeParkOrder(@Valid @RequestBody InvoiceOrderReqVO reqVO) {
        offtimeParkOrderService.invoiceOfftimeParkOrder(reqVO);
        return success(true);
    }
    @PutMapping("/cancel")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "取消订单")
    public CommonResult<Boolean> cancelOfftimeParkOrder(@Valid @RequestBody IdReqVO reqVO) {
        offtimeParkOrderService.cancelOfftimeParkOrder(reqVO);
        return success(true);
    }

    // ==================== ③ 数据可视化接口 ====================

    @GetMapping("/chart")
    @Operation(summary = "获得错时停车订单统计图表数据（折线图+柱状图/饼图+卡片）")
    public CommonResult<OfftimeParkOrderChartRespVO> getOfftimeParkOrderChart(@Valid OfftimeParkOrderChartReqVO chartReqVO) {
        return success(offtimeParkOrderService.getOfftimeParkOrderChart(chartReqVO));
    }
}
