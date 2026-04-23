package cn.iocoder.yudao.module.ordertrade.controller.admin.merchantreconcile;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.merchantreconcile.vo.*;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.merchantreconcile.ReconcileRecordDO;
import cn.iocoder.yudao.module.ordertrade.service.merchantreconcile.ReconcileRecordService;
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

@Tag(name = "订单交易 - 商户对账 - 对账记录")
@RestController
@RequestMapping("/ordertrade/reconcile-record")
@Validated
public class ReconcileRecordController {

    @Resource
    private ReconcileRecordService reconcileRecordService;

    @PostMapping("/create")
    @Operation(summary = "创建对账记录")
    public CommonResult<Long> createReconcileRecord(@Valid @RequestBody ReconcileRecordSaveReqVO createReqVO) {
        return success(reconcileRecordService.createReconcileRecord(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新对账记录")
    public CommonResult<Boolean> updateReconcileRecord(@Valid @RequestBody ReconcileRecordSaveReqVO updateReqVO) {
        reconcileRecordService.updateReconcileRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除对账记录")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> deleteReconcileRecord(@RequestParam("id") Long id) {
        reconcileRecordService.deleteReconcileRecord(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得对账记录详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<ReconcileRecordRespVO> getReconcileRecord(@RequestParam("id") Long id) {
        ReconcileRecordDO obj = reconcileRecordService.getReconcileRecord(id);
        return success(BeanUtils.toBean(obj, ReconcileRecordRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得对账记录分页列表")
    public CommonResult<PageResult<ReconcileRecordRespVO>> getReconcileRecordPage(@Valid ReconcileRecordPageReqVO pageReqVO) {
        PageResult<ReconcileRecordDO> pageResult = reconcileRecordService.getReconcileRecordPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ReconcileRecordRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出对账记录 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportReconcileRecordExcel(@Valid ReconcileRecordPageReqVO pageReqVO,
                                           HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ReconcileRecordDO> list = reconcileRecordService.getReconcileRecordPage(pageReqVO).getList();
        ExcelUtils.write(response, "对账记录.xls", "数据", ReconcileRecordRespVO.class,
                BeanUtils.toBean(list, ReconcileRecordRespVO.class));
    }

    @PostMapping("/check")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "核查对账记录")
    public CommonResult<Boolean> checkReconcileRecord(@Valid @RequestBody IdReqVO reqVO) {
        reconcileRecordService.checkReconcileRecord(reqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获得对账记录统计图表数据")
    public CommonResult<ReconcileRecordChartRespVO> getReconcileRecordChart(@Valid ReconcileRecordChartReqVO chartReqVO) {
        return success(reconcileRecordService.getReconcileRecordChart(chartReqVO));
    }
}
