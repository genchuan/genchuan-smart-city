package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer;

import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferreserve.TransferReservePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferreserve.TransferReserveRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferreserve.TransferReserveSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.detail.TransferReserveDetailDO;
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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferReserveDO;
import cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.transferreserve.TransferReserveService;

@Tag(name = "环境卫生管理 - 进站预约")
@RestController
@RequestMapping("/envirhealth/transfer-reserve")
@Validated
public class TransferReserveController {

    @Resource
    private TransferReserveService transferReserveService;

    @PostMapping("/create")
    @Operation(summary = "创建进站预约")
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-reserve:create')")
    public CommonResult<Long> createTransferReserve(@Valid @RequestBody TransferReserveSaveReqVO createReqVO) {
        return success(transferReserveService.createTransferReserve(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新进站预约")
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-reserve:update')")
    public CommonResult<Boolean> updateTransferReserve(@Valid @RequestBody TransferReserveSaveReqVO updateReqVO) {
        transferReserveService.updateTransferReserve(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除进站预约")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-reserve:delete')")
    public CommonResult<Boolean> deleteTransferReserve(@RequestParam("id") Long id) {
        transferReserveService.deleteTransferReserve(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得进站预约")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-reserve:query')")
    public CommonResult<TransferReserveRespVO> getTransferReserve(@RequestParam("id") Long id) {
        TransferReserveDO transferReserve = transferReserveService.getTransferReserve(id);
        return success(BeanUtils.toBean(transferReserve, TransferReserveRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得进站预约分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-reserve:query')")
    public CommonResult<PageResult<TransferReserveRespVO>> getTransferReservePage(@Valid TransferReservePageReqVO pageReqVO) {
        PageResult<TransferReserveDO> pageResult = transferReserveService.getTransferReservePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TransferReserveRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出进站预约 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-reserve:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTransferReserveExcel(@Valid TransferReservePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TransferReserveDO> list = transferReserveService.getTransferReservePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "进站预约.xls", "数据", TransferReserveRespVO.class,
                        BeanUtils.toBean(list, TransferReserveRespVO.class));
    }

    @GetMapping("/detail-page")
    @Operation(summary = "获得进站预约详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-reserve:query')")
    public CommonResult<PageResult<TransferReserveDetailDO>> getTransferReserveDetailPage(
            @Valid TransferReservePageReqVO pageReqVO) {
        PageResult<TransferReserveDetailDO> pageResult =
                transferReserveService.getTransferReserveDetailPage(pageReqVO);

        return success(pageResult);
    }
}