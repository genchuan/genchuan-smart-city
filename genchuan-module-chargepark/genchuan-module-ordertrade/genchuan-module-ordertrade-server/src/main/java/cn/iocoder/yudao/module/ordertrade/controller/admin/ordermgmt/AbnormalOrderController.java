package cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt.AbnormalOrderDO;
import cn.iocoder.yudao.module.ordertrade.service.ordermgmt.AbnormalOrderService;
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
 * 管理后台 - 异常订单
 *
 * @author genchuan
 */
@Tag(name = "订单交易 -订单管理- 异常订单")
@RestController
@RequestMapping("/ordertrade/abnormal-order")
@Validated
public class AbnormalOrderController {

    @Resource
    private AbnormalOrderService abnormalOrderService;

    // ==================== ① 标准CRUD ====================

/*
    @PostMapping("/create")
    @Operation(summary = "创建异常订单")
    public CommonResult<Long> createAbnormalOrder(@Valid @RequestBody AbnormalOrderSaveReqVO createReqVO) {
        return success(abnormalOrderService.createAbnormalOrder(createReqVO));
    }
*/

    /*@PutMapping("/update")
    @Operation(summary = "更新异常订单")
    public CommonResult<Boolean> updateAbnormalOrder(@Valid @RequestBody AbnormalOrderSaveReqVO updateReqVO) {
        abnormalOrderService.updateAbnormalOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除异常订单")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> deleteAbnormalOrder(@RequestParam("id") Long id) {
        abnormalOrderService.deleteAbnormalOrder(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除异常订单")
    @Parameter(name = "ids", description = "主键列表", required = true)
    public CommonResult<Boolean> deleteAbnormalOrderList(@RequestParam("ids") List<Long> ids) {
        abnormalOrderService.deleteAbnormalOrderListByIds(ids);
        return success(true);
    }*/

    @GetMapping("/get")
    @Operation(summary = "获得异常订单详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<AbnormalOrderRespVO> getAbnormalOrder(@RequestParam("id") Long id) {
        AbnormalOrderDO obj = abnormalOrderService.getAbnormalOrder(id);
        return success(BeanUtils.toBean(obj, AbnormalOrderRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得异常订单分页列表")
    public CommonResult<PageResult<AbnormalOrderRespVO>> getAbnormalOrderPage(@Valid AbnormalOrderPageReqVO pageReqVO) {
        PageResult<AbnormalOrderDO> pageResult = abnormalOrderService.getAbnormalOrderPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AbnormalOrderRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出异常订单 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAbnormalOrderExcel(@Valid AbnormalOrderPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AbnormalOrderDO> list = abnormalOrderService.getAbnormalOrderPage(pageReqVO).getList();
        ExcelUtils.write(response, "异常订单.xls", "数据", AbnormalOrderRespVO.class,
                BeanUtils.toBean(list, AbnormalOrderRespVO.class));
    }

 /*   @GetMapping("/batch-export")
    @Operation(summary = "批量导出异常订单 Excel（芋道原生导出风格）")
    @ApiAccessLog(operateType = EXPORT)
    public void batchExportAbnormalOrderExcel(@Valid AbnormalOrderPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AbnormalOrderDO> list = abnormalOrderService.getAbnormalOrderPage(pageReqVO).getList();
        ExcelUtils.write(response, "异常订单批量导出.xls", "数据", AbnormalOrderRespVO.class,
                BeanUtils.toBean(list, AbnormalOrderRespVO.class));
    }
*/
    // ==================== ② 业务操作接口 ====================

    @PutMapping("/check")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "核查异常订单")
    public CommonResult<Boolean> checkAbnormalOrder(@Valid @RequestBody IdReqVO reqVO) {
        abnormalOrderService.checkAbnormalOrder(reqVO);
        return success(true);
    }
    @PutMapping("/ignore")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "忽略异常订单")
    public CommonResult<Boolean> ignoreAbnormalOrder(@Valid @RequestBody IdReqVO reqVO) {
        abnormalOrderService.ignoreAbnormalOrder(reqVO);
        return success(true);
    }
    @PutMapping("/update-progress")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "更新处理进度")
    public CommonResult<Boolean> updateProgressAbnormalOrder(@Valid @RequestBody IdReqVO reqVO) {
        abnormalOrderService.updateProgressAbnormalOrder(reqVO);
        return success(true);
    }
    @PostMapping("/batch-handle")
    @Operation(summary = "批量处置异常订单")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> batchProcessAbnormalOrder(@Valid @RequestBody IdsReqVO reqVO) {
        abnormalOrderService.batchProcessAbnormalOrder(reqVO);
        return success(true);
    }

    // ==================== ③ 数据可视化接口 ====================

    @GetMapping("/chart")
    @Operation(summary = "获得异常订单统计图表数据（折线图+柱状图/饼图+卡片）")
    public CommonResult<AbnormalOrderChartRespVO> getAbnormalOrderChart(@Valid AbnormalOrderChartReqVO chartReqVO) {
        return success(abnormalOrderService.getAbnormalOrderChart(chartReqVO));
    }
}
