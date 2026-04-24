package cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.resulthandle;

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

import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.resulthandle.vo.ResultHandlePageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.resulthandle.vo.ResultHandleRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.resulthandle.vo.ResultHandleSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.resulthandle.vo.ResultHandleBatchHandleReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.inspectmgmt.resulthandle.ResultHandleDO;
import cn.iocoder.yudao.module.vehiclepass.service.inspectmgmt.resulthandle.ResultHandleService;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;


@Tag(name = "管理后台 - 结果处置")
@RestController
@RequestMapping("/result/handle")
@Validated
public class ResultHandleController {

    @Resource
    private ResultHandleService handleService;

    @PostMapping("/create")
    @Operation(summary = "创建结果处置")
    @PreAuthorize("@ss.hasPermission('result:handle:create')")
    public CommonResult<Long> createHandle(@Valid @RequestBody ResultHandleSaveReqVO createReqVO) {
        return success(handleService.createHandle(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新结果处置")
    @PreAuthorize("@ss.hasPermission('result:handle:update')")
    public CommonResult<Boolean> updateHandle(@Valid @RequestBody ResultHandleSaveReqVO updateReqVO) {
        handleService.updateHandle(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除结果处置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('result:handle:delete')")
    public CommonResult<Boolean> deleteHandle(@RequestParam("id") Long id) {
        handleService.deleteHandle(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除结果处置")
    @PreAuthorize("@ss.hasPermission('result:handle:delete')")
    public CommonResult<Boolean> deleteHandleList(@RequestParam("ids") List<Long> ids) {
        handleService.deleteHandleListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得结果处置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('result:handle:query')")
    public CommonResult<ResultHandleRespVO> getHandle(@RequestParam("id") Long id) {
        ResultHandleDO handle = handleService.getHandle(id);
        return success(BeanUtils.toBean(handle, ResultHandleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得结果处置分页")
    @PreAuthorize("@ss.hasPermission('vehiclepass:result-handle:query')")
    public CommonResult<PageResult<ResultHandleRespVO>> getHandlePage(@Valid ResultHandlePageReqVO pageReqVO) {
        return success(handleService.getHandlePageWithJoin(pageReqVO));
    }

    @PostMapping("/batch-handle")
    @Operation(summary = "批量处置结果")
    @PreAuthorize("@ss.hasPermission('vehiclepass:result-handle:batch-handle')")
    public CommonResult<Boolean> batchHandle(@Valid @RequestBody ResultHandleBatchHandleReqVO reqVO) {
        handleService.batchHandle(reqVO);
        return success(true);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出结果处置 Excel")
    @PreAuthorize("@ss.hasPermission('result:handle:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportHandleExcel(@Valid ResultHandlePageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ResultHandleDO> list = handleService.getHandlePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "结果处置.xls", "数据", ResultHandleRespVO.class,
                BeanUtils.toBean(list, ResultHandleRespVO.class));
    }

}