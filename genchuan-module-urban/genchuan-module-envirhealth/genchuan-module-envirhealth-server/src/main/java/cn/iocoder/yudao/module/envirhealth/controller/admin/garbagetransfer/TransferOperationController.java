package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferoperation.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferOperationDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferOperationDetailDO;
import cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.transferoperation.TransferOperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "环境卫生管理 - 转运作业")
@RestController
@RequestMapping("/envirhealth/transfer-operation")
@Validated
public class TransferOperationController {

    @Resource
    private TransferOperationService transferOperationService;

    @PostMapping("/create")
    @Operation(summary = "创建转运作业")
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-operation:create')")
    public CommonResult<Long> createTransferOperation(@Valid @RequestBody TransferOperationSaveReqVO createReqVO) {
        return success(transferOperationService.createTransferOperation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新转运作业")
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-operation:update')")
    public CommonResult<Boolean> updateTransferOperation(@Valid @RequestBody TransferOperationSaveReqVO updateReqVO) {
        transferOperationService.updateTransferOperation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除转运作业")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-operation:delete')")
    public CommonResult<Boolean> deleteTransferOperation(@RequestParam("id") Long id) {
        transferOperationService.deleteTransferOperation(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得转运作业")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-operation:query')")
    public CommonResult<TransferOperationRespVO> getTransferOperation(@RequestParam("id") Long id) {
        TransferOperationDO transferOperation = transferOperationService.getTransferOperation(id);
        return success(BeanUtils.toBean(transferOperation, TransferOperationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得转运作业分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-operation:query')")
    public CommonResult<PageResult<TransferOperationRespVO>> getTransferOperationPage(@Valid TransferOperationPageReqVO pageReqVO) {
        PageResult<TransferOperationDO> pageResult = transferOperationService.getTransferOperationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TransferOperationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出转运作业 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-operation:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTransferOperationExcel(@Valid TransferOperationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TransferOperationDO> list = transferOperationService.getTransferOperationPage(pageReqVO).getList();

        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode("转运作业_" +
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".xls", "UTF-8"));
        response.setCharacterEncoding("UTF-8");

        // 导出 Excel
        ExcelUtils.write(response, "转运作业.xls", "数据", TransferOperationRespVO.class,
                        BeanUtils.toBean(list, TransferOperationRespVO.class));
    }

    @GetMapping("/detail-page")
    @Operation(summary = "获得转运作业详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-operation:query')")
    public CommonResult<PageResult<TransferOperationDetailDO>> getTransferOperationDetailPage(
            @Valid TransferOperationPageReqVO pageReqVO) {
        PageResult<TransferOperationDetailDO> pageResult =
                transferOperationService.getTransferOperationDetailPage(pageReqVO);

        return success(pageResult);
    }

    @GetMapping("/chart/dashboard-execute")
    @Operation(summary = "卡片/圆环图/柱状图/折线图统计(进行中)")
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-operation:query')")
    public CommonResult<TransferOperationDashboardVO> getDashboardStats() {
        TransferOperationDashboardVO dashboardStats = transferOperationService.getDashboardStats();
        return success(dashboardStats);
    }

    @GetMapping("/chart/dashboard-completed")
    @Operation(summary = "卡片/圆环图/柱状图/折线图统计(已完成)")
    @Parameter(name = "timeDimension", description = "进站量统计维度：day(日)/week(周)/month(月)，默认day", example = "day")
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-operation:query')")
    public CommonResult<TransferOperationCompletedDashboardVO> getTransferOperationDashboard(
            @RequestParam(required = false, defaultValue = "day") String timeDimension) {
        return success(transferOperationService.getTransferOperationDashboard(timeDimension));
    }

    @PutMapping("/pause")
    @Operation(summary = "暂停转运作业")
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-operation:update')")
    public CommonResult<Boolean> pauseTransferOperation(
            @RequestParam("id") Long operationId,
            @RequestParam(value = "pauseStatusId", defaultValue = "uuid-plan-status-004") String pauseStatusId) {
        transferOperationService.pauseTransferOperation(operationId, pauseStatusId);
        return success(true);
    }

    @PutMapping("/start")
    @Operation(summary = "启动转运作业")
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-operation:update')")
    public CommonResult<Boolean> startTransferOperation(
            @RequestParam("id") Long operationId,
            @RequestParam(value = "startStatusId", defaultValue = "uuid-plan-status-002") String startStatusId) {
        transferOperationService.startTransferOperation(operationId, startStatusId);
        return success(true);
    }

    @PutMapping("/complete")
    @Operation(summary = "归档转运作业")
    @Parameter(name = "operationId", description = "作业ID", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-operation:update')")
    public CommonResult<Boolean> completeTransferOperation(@RequestParam("operationId") Long operationId) {
        transferOperationService.completeTransferOperation(operationId);
        return success(true);
    }
}