package cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt.ShareChargeOrderDO;
import cn.iocoder.yudao.module.ordertrade.rpc.stationresource.StationNameHelper;
import cn.iocoder.yudao.module.ordertrade.service.ordermgmt.ShareChargeOrderService;
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
 * 管理后台 - 共享充电订单
 *
 * @author genchuan
 */
@Tag(name = "订单交易 -订单管理- 共享充电订单")
@RestController
@RequestMapping("/ordertrade/share-charge-order")
@Validated
public class ShareChargeOrderController {

    @Resource
    private ShareChargeOrderService shareChargeOrderService;
    @Resource
    private StationNameHelper stationNameHelper;

    // ==================== ① 标准CRUD ====================

   /* @PostMapping("/create")
    @Operation(summary = "创建共享充电订单")
    public CommonResult<Long> createShareChargeOrder(@Valid @RequestBody ShareChargeOrderSaveReqVO createReqVO) {
        return success(shareChargeOrderService.createShareChargeOrder(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新共享充电订单")
    public CommonResult<Boolean> updateShareChargeOrder(@Valid @RequestBody ShareChargeOrderSaveReqVO updateReqVO) {
        shareChargeOrderService.updateShareChargeOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除共享充电订单")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> deleteShareChargeOrder(@RequestParam("id") Long id) {
        shareChargeOrderService.deleteShareChargeOrder(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除共享充电订单")
    @Parameter(name = "ids", description = "主键列表", required = true)
    public CommonResult<Boolean> deleteShareChargeOrderList(@RequestParam("ids") List<Long> ids) {
        shareChargeOrderService.deleteShareChargeOrderListByIds(ids);
        return success(true);
    }*/

    @GetMapping("/get")
    @Operation(summary = "获得共享充电订单详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<ShareChargeOrderRespVO> getShareChargeOrder(@RequestParam("id") Long id) {
        ShareChargeOrderDO obj = shareChargeOrderService.getShareChargeOrder(id);
        ShareChargeOrderRespVO vo = BeanUtils.toBean(obj, ShareChargeOrderRespVO.class);
        stationNameHelper.fillStationName(vo, ShareChargeOrderRespVO::getStationId, ShareChargeOrderRespVO::setStationName);
        return success(vo);
    }

    @GetMapping("/page")
    @Operation(summary = "获得共享充电订单分页列表")
    public CommonResult<PageResult<ShareChargeOrderRespVO>> getShareChargeOrderPage(@Valid ShareChargeOrderPageReqVO pageReqVO) {
        PageResult<ShareChargeOrderDO> pageResult = shareChargeOrderService.getShareChargeOrderPage(pageReqVO);
        PageResult<ShareChargeOrderRespVO> voPage = BeanUtils.toBean(pageResult, ShareChargeOrderRespVO.class);
        stationNameHelper.fillStationNames(voPage.getList(), ShareChargeOrderRespVO::getStationId, ShareChargeOrderRespVO::setStationName);
        return success(voPage);
    }

    @GetMapping("/export")
    @Operation(summary = "导出共享充电订单 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportShareChargeOrderExcel(@Valid ShareChargeOrderPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ShareChargeOrderDO> list = shareChargeOrderService.getShareChargeOrderPage(pageReqVO).getList();
        List<ShareChargeOrderRespVO> voList = BeanUtils.toBean(list, ShareChargeOrderRespVO.class);
        stationNameHelper.fillStationNames(voList, ShareChargeOrderRespVO::getStationId, ShareChargeOrderRespVO::setStationName);
        ExcelUtils.write(response, "共享充电订单.xls", "数据", ShareChargeOrderRespVO.class, voList);
    }

    @GetMapping("/batch-export")
    @Operation(summary = "批量导出共享充电订单 Excel（芋道原生导出风格）")
    @ApiAccessLog(operateType = EXPORT)
    public void batchExportShareChargeOrderExcel(@Valid ShareChargeOrderPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ShareChargeOrderDO> list = shareChargeOrderService.getShareChargeOrderPage(pageReqVO).getList();
        List<ShareChargeOrderRespVO> voList = BeanUtils.toBean(list, ShareChargeOrderRespVO.class);
        stationNameHelper.fillStationNames(voList, ShareChargeOrderRespVO::getStationId, ShareChargeOrderRespVO::setStationName);
        ExcelUtils.write(response, "共享充电订单批量导出.xls", "数据", ShareChargeOrderRespVO.class, voList);
    }

    // ==================== ② 业务操作接口 ====================

    @PutMapping("/return")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "归还共享充电")
    public CommonResult<Boolean> returnOrderShareChargeOrder(@Valid @RequestBody IdReqVO reqVO) {
        shareChargeOrderService.returnOrderShareChargeOrder(reqVO);
        return success(true);
    }
    @PutMapping("/pay")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "支付共享充电订单")
    public CommonResult<Boolean> payShareChargeOrder(@Valid @RequestBody IdReqVO reqVO) {
        shareChargeOrderService.payShareChargeOrder(reqVO);
        return success(true);
    }
    @PutMapping("/refund")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "发起退款")
    public CommonResult<Boolean> refundShareChargeOrder(@Valid @RequestBody IdReqVO reqVO) {
        shareChargeOrderService.refundShareChargeOrder(reqVO);
        return success(true);
    }
    @PutMapping("/invoice")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "申请开票")
    public CommonResult<Boolean> invoiceShareChargeOrder(@Valid @RequestBody InvoiceOrderReqVO reqVO) {
        shareChargeOrderService.invoiceShareChargeOrder(reqVO);
        return success(true);
    }
    @PutMapping("/cancel")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "取消订单")
    public CommonResult<Boolean> cancelShareChargeOrder(@Valid @RequestBody IdReqVO reqVO) {
        shareChargeOrderService.cancelShareChargeOrder(reqVO);
        return success(true);
    }

    // ==================== ③ 数据可视化接口 ====================

    @GetMapping("/chart")
    @Operation(summary = "获得共享充电订单统计图表数据（折线图+柱状图/饼图+卡片）")
    public CommonResult<ShareChargeOrderChartRespVO> getShareChargeOrderChart(@Valid ShareChargeOrderChartReqVO chartReqVO) {
        return success(shareChargeOrderService.getShareChargeOrderChart(chartReqVO));
    }
}
