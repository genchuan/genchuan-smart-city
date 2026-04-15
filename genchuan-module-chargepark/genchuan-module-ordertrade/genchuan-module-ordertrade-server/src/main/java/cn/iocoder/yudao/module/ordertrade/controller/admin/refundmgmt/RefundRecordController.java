package cn.iocoder.yudao.module.ordertrade.controller.admin.refundmgmt;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.refundmgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.refundmgmt.RefundRecordDO;
import cn.iocoder.yudao.module.ordertrade.service.refundmgmt.RefundRecordService;
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
 * 管理后台 - 退款记录
 *
 * @author genchuan
 */
@Tag(name = "管理后台 -退款管理- 退款记录")
@RestController
@RequestMapping("/ordertrade/refund-record")
@Validated
public class RefundRecordController {

    @Resource
    private RefundRecordService refundRecordService;

    // ==================== ① 标准CRUD ====================
/*
    @PostMapping("/create")
    @Operation(summary = "创建退款记录")
    public CommonResult<Long> createRefundRecord(@Valid @RequestBody RefundRecordSaveReqVO createReqVO) {
        return success(refundRecordService.createRefundRecord(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新退款记录")
    public CommonResult<Boolean> updateRefundRecord(@Valid @RequestBody RefundRecordSaveReqVO updateReqVO) {
        refundRecordService.updateRefundRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除退款记录")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> deleteRefundRecord(@RequestParam("id") Long id) {
        refundRecordService.deleteRefundRecord(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除退款记录")
    @Parameter(name = "ids", description = "主键列表", required = true)
    public CommonResult<Boolean> deleteRefundRecordList(@RequestParam("ids") List<Long> ids) {
        refundRecordService.deleteRefundRecordListByIds(ids);
        return success(true);
    }*/

    @GetMapping("/get")
    @Operation(summary = "获得退款记录详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<RefundRecordRespVO> getRefundRecord(@RequestParam("id") Long id) {
        RefundRecordDO obj = refundRecordService.getRefundRecord(id);
        return success(BeanUtils.toBean(obj, RefundRecordRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得退款记录分页列表")
    public CommonResult<PageResult<RefundRecordRespVO>> getRefundRecordPage(@Valid RefundRecordPageReqVO pageReqVO) {
        PageResult<RefundRecordDO> pageResult = refundRecordService.getRefundRecordPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RefundRecordRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出退款记录 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRefundRecordExcel(@Valid RefundRecordPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RefundRecordDO> list = refundRecordService.getRefundRecordPage(pageReqVO).getList();
        ExcelUtils.write(response, "退款记录.xls", "数据", RefundRecordRespVO.class,
                BeanUtils.toBean(list, RefundRecordRespVO.class));
    }

  /*  @GetMapping("/batch-export")
    @Operation(summary = "批量导出退款记录 Excel（芋道原生导出风格）")
    @ApiAccessLog(operateType = EXPORT)
    public void batchExportRefundRecordExcel(@Valid RefundRecordPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RefundRecordDO> list = refundRecordService.getRefundRecordPage(pageReqVO).getList();
        ExcelUtils.write(response, "退款记录批量导出.xls", "数据", RefundRecordRespVO.class,
                BeanUtils.toBean(list, RefundRecordRespVO.class));
    }*/

    // ==================== ② 业务操作接口 ====================

    @PutMapping("/check")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "核查异常退款记录")
    public CommonResult<Boolean> checkRefundRecord(@Valid @RequestBody IdReqVO reqVO) {
        refundRecordService.checkRefundRecord(reqVO);
        return success(true);
    }

    // ==================== ③ 数据可视化接口 ====================

    @GetMapping("/chart")
    @Operation(summary = "获得退款记录统计图表数据（折线图+柱状图/饼图+卡片）")
    public CommonResult<RefundRecordChartRespVO> getRefundRecordChart(@Valid RefundRecordChartReqVO chartReqVO) {
        return success(refundRecordService.getRefundRecordChart(chartReqVO));
    }
}
