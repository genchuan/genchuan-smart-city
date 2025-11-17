package cn.iocoder.yudao.module.datacenter.controller.admin.businessstandard.processexecstd;

import cn.iocoder.yudao.module.datacenter.controller.admin.businessstandard.processexecstd.vo.ProcessExecStdPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.businessstandard.processexecstd.vo.ProcessExecStdRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.businessstandard.processexecstd.vo.ProcessExecStdSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.businessstandard.processexecstd.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.businessstandard.processexecstd.ProcessExecStdDO;
import cn.iocoder.yudao.module.datacenter.service.businessstandard.processexecstd.ProcessExecStdService;

@Tag(name = "管理后台 - 流程执行规范")
@RestController
@RequestMapping("/datacenter/process-exec-std")
@Validated
public class ProcessExecStdController {

    @Resource
    private ProcessExecStdService processExecStdService;

    @PostMapping("/create")
    @Operation(summary = "创建流程执行规范")
    @PreAuthorize("@ss.hasPermission('datacenter:process-exec-std:create')")
    public CommonResult<Long> createProcessExecStd(@Valid @RequestBody ProcessExecStdSaveReqVO createReqVO) {
        return success(processExecStdService.createProcessExecStd(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新流程执行规范")
    @PreAuthorize("@ss.hasPermission('datacenter:process-exec-std:update')")
    public CommonResult<Boolean> updateProcessExecStd(@Valid @RequestBody ProcessExecStdSaveReqVO updateReqVO) {
        processExecStdService.updateProcessExecStd(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除流程执行规范")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:process-exec-std:delete')")
    public CommonResult<Boolean> deleteProcessExecStd(@RequestParam("id") Long id) {
        processExecStdService.deleteProcessExecStd(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得流程执行规范")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:process-exec-std:query')")
    public CommonResult<ProcessExecStdRespVO> getProcessExecStd(@RequestParam("id") Long id) {
        ProcessExecStdDO processExecStd = processExecStdService.getProcessExecStd(id);
        return success(BeanUtils.toBean(processExecStd, ProcessExecStdRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得流程执行规范分页")
    @PreAuthorize("@ss.hasPermission('datacenter:process-exec-std:query')")
    public CommonResult<PageResult<ProcessExecStdRespVO>> getProcessExecStdPage(@Valid ProcessExecStdPageReqVO pageReqVO) {
        PageResult<ProcessExecStdDO> pageResult = processExecStdService.getProcessExecStdPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ProcessExecStdRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出流程执行规范 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:process-exec-std:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportProcessExecStdExcel(@Valid ProcessExecStdPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ProcessExecStdDO> list = processExecStdService.getProcessExecStdPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "流程执行规范.xls", "数据", ProcessExecStdRespVO.class,
                        BeanUtils.toBean(list, ProcessExecStdRespVO.class));
    }

}