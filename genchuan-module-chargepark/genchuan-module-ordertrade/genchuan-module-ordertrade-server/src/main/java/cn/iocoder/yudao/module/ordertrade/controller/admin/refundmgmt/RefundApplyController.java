package cn.iocoder.yudao.module.ordertrade.controller.admin.refundmgmt;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.refundmgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.refundmgmt.RefundApplyDO;
import cn.iocoder.yudao.module.ordertrade.service.refundmgmt.RefundApplyService;
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
 * 管理后台 退款管理- 退款申请
 *
 * @author genchuan
 */
@Tag(name = "订单交易 -退款管理- 退款申请")
@RestController
@RequestMapping("/ordertrade/refund-apply")
@Validated
public class RefundApplyController {

    @Resource
    private RefundApplyService refundApplyService;

    // ==================== ① 标准CRUD ====================

   /* @PostMapping("/create")
    @Operation(summary = "创建退款申请")
    public CommonResult<Long> createRefundApply(@Valid @RequestBody RefundApplySaveReqVO createReqVO) {
        return success(refundApplyService.createRefundApply(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新退款申请")
    public CommonResult<Boolean> updateRefundApply(@Valid @RequestBody RefundApplySaveReqVO updateReqVO) {
        refundApplyService.updateRefundApply(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除退款申请")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> deleteRefundApply(@RequestParam("id") Long id) {
        refundApplyService.deleteRefundApply(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除退款申请")
    @Parameter(name = "ids", description = "主键列表", required = true)
    public CommonResult<Boolean> deleteRefundApplyList(@RequestParam("ids") List<Long> ids) {
        refundApplyService.deleteRefundApplyListByIds(ids);
        return success(true);
    }*/

    @GetMapping("/get")
    @Operation(summary = "获得退款申请详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<RefundApplyRespVO> getRefundApply(@RequestParam("id") Long id) {
        RefundApplyDO obj = refundApplyService.getRefundApply(id);
        return success(BeanUtils.toBean(obj, RefundApplyRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得退款申请分页列表")
    public CommonResult<PageResult<RefundApplyRespVO>> getRefundApplyPage(@Valid RefundApplyPageReqVO pageReqVO) {
        PageResult<RefundApplyDO> pageResult = refundApplyService.getRefundApplyPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RefundApplyRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出退款申请 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRefundApplyExcel(@Valid RefundApplyPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RefundApplyDO> list = refundApplyService.getRefundApplyPage(pageReqVO).getList();
        ExcelUtils.write(response, "退款申请.xls", "数据", RefundApplyRespVO.class,
                BeanUtils.toBean(list, RefundApplyRespVO.class));
    }

   /* @GetMapping("/batch-export")
    @Operation(summary = "批量导出退款申请 Excel（芋道原生导出风格）")
    @ApiAccessLog(operateType = EXPORT)
    public void batchExportRefundApplyExcel(@Valid RefundApplyPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RefundApplyDO> list = refundApplyService.getRefundApplyPage(pageReqVO).getList();
        ExcelUtils.write(response, "退款申请批量导出.xls", "数据", RefundApplyRespVO.class,
                BeanUtils.toBean(list, RefundApplyRespVO.class));
    }*/

    // ==================== ② 业务操作接口 ====================

    @PutMapping("/approve")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "审核通过")
    public CommonResult<Boolean> approveRefundApply(@Valid @RequestBody IdReqVO reqVO) {
        refundApplyService.approveRefundApply(reqVO);
        return success(true);
    }
    @PutMapping("/reject")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "审核驳回")
    public CommonResult<Boolean> rejectRefundApply(@Valid @RequestBody IdReqVO reqVO) {
        refundApplyService.rejectRefundApply(reqVO);
        return success(true);
    }
    @PutMapping("/execute")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "执行退款")
    public CommonResult<Boolean> executeRefundApply(@Valid @RequestBody IdReqVO reqVO) {
        refundApplyService.executeRefundApply(reqVO);
        return success(true);
    }
    @PutMapping("/re-apply")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "重新申请")
    public CommonResult<Boolean> reapplyRefundApply(@Valid @RequestBody IdReqVO reqVO) {
        refundApplyService.reapplyRefundApply(reqVO);
        return success(true);
    }
    @PostMapping("/batch-audit")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "批量审核通过")
    public CommonResult<Boolean> batchAuditRefundApply(@Valid @RequestBody IdsReqVO reqVO) {
        refundApplyService.batchAuditRefundApply(reqVO);
        return success(true);
    }

    // ==================== ③ 数据可视化接口 ====================

    @GetMapping("/chart")
    @Operation(summary = "获得退款申请统计图表数据（折线图+柱状图/饼图+卡片）")
    public CommonResult<RefundApplyChartRespVO> getRefundApplyChart(@Valid RefundApplyChartReqVO chartReqVO) {
        return success(refundApplyService.getRefundApplyChart(chartReqVO));
    }
}
