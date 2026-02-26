package cn.iocoder.yudao.module.envir.controller.admin.operationstatus;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
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

import cn.iocoder.yudao.module.envir.controller.admin.operationstatus.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.operationstatus.OperationStatusDO;
import cn.iocoder.yudao.module.envir.service.operationstatus.OperationStatusService;

@Tag(name = "管理后台 - 运营状态字典")
@RestController
@RequestMapping("/envir/operation-status")
@Validated
public class OperationStatusController {

    @Resource
    private OperationStatusService operationStatusService;

    @PostMapping("/create")
    @Operation(summary = "创建运营状态字典")
    @PreAuthorize("@ss.hasPermission('envir:operation-status:create')")
    public CommonResult<Long> createOperationStatus(@Valid @RequestBody OperationStatusSaveReqVO createReqVO) {
        return success(operationStatusService.createOperationStatus(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新运营状态字典")
    @PreAuthorize("@ss.hasPermission('envir:operation-status:update')")
    public CommonResult<Boolean> updateOperationStatus(@Valid @RequestBody OperationStatusSaveReqVO updateReqVO) {
        operationStatusService.updateOperationStatus(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除运营状态字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envir:operation-status:delete')")
    public CommonResult<Boolean> deleteOperationStatus(@RequestParam("id") Long id) {
        operationStatusService.deleteOperationStatus(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得运营状态字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envir:operation-status:query')")
    public CommonResult<OperationStatusRespVO> getOperationStatus(@RequestParam("id") Long id) {
        OperationStatusDO operationStatus = operationStatusService.getOperationStatus(id);
        return success(BeanUtils.toBean(operationStatus, OperationStatusRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得运营状态字典分页")
    @PreAuthorize("@ss.hasPermission('envir:operation-status:query')")
    public CommonResult<PageResult<OperationStatusRespVO>> getOperationStatusPage(@Valid OperationStatusPageReqVO pageReqVO) {
        PageResult<OperationStatusDO> pageResult = operationStatusService.getOperationStatusPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, OperationStatusRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出运营状态字典 Excel")
    @PreAuthorize("@ss.hasPermission('envir:operation-status:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOperationStatusExcel(@Valid OperationStatusPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<OperationStatusDO> list = operationStatusService.getOperationStatusPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "运营状态字典.xls", "数据", OperationStatusRespVO.class,
                        BeanUtils.toBean(list, OperationStatusRespVO.class));
    }

}