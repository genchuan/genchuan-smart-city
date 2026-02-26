package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer;

import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferoperation.TransferOperationPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferoperation.TransferOperationRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferoperation.TransferOperationSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.detail.TransferOperationDetailDO;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferOperationDO;
import cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.transferoperation.TransferOperationService;

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
}