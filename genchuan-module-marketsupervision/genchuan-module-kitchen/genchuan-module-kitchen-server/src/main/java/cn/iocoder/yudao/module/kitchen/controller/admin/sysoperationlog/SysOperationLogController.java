package cn.iocoder.yudao.module.kitchen.controller.admin.sysoperationlog;

import cn.iocoder.yudao.module.kitchen.controller.admin.sysoperationlog.vo.SysOperationLogPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.sysoperationlog.vo.SysOperationLogRespVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.sysoperationlog.vo.SysOperationLogSaveReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.sysoperationlog.vo.add.SysOperationLogAddReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.sysoperationlog.SysOperationLogDO;
import cn.iocoder.yudao.module.kitchen.service.sysoperationlog.SysOperationLogService;
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


@Tag(name = "管理后台 - 系统操作审计日志表，存储平台全模块所有操作的审计日志信息")
@RestController
@RequestMapping("/kitchen/sys-operation-log")
@Validated
public class SysOperationLogController {

    @Resource
    private SysOperationLogService sysOperationLogService;

    @PostMapping("/add")
    @Operation(summary = "新增系统操作审计日志表")
//    @PreAuthorize("@ss.hasPermission('kitchen:sys-operation-log:add')")
    public CommonResult<Long> addSysOperationLog(@Valid @RequestBody SysOperationLogAddReqVO addReqVO) {
        Long id = sysOperationLogService.addSysOperationLog(addReqVO);
        return success(id);
    }
    @PostMapping("/create")
    @Operation(summary = "（勿用）创建系统操作审计日志表，存储平台全模块所有操作的审计日志信息")
//    @PreAuthorize("@ss.hasPermission('kitchen:sys-operation-log:create')")
    public CommonResult<Long> createSysOperationLog(@Valid @RequestBody SysOperationLogSaveReqVO createReqVO) {
        return success(sysOperationLogService.createSysOperationLog(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新系统操作审计日志表，存储平台全模块所有操作的审计日志信息")
//    @PreAuthorize("@ss.hasPermission('kitchen:sys-operation-log:update')")
    public CommonResult<Boolean> updateSysOperationLog(@Valid @RequestBody SysOperationLogSaveReqVO updateReqVO) {
        sysOperationLogService.updateSysOperationLog(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除系统操作审计日志表，存储平台全模块所有操作的审计日志信息")
    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('kitchen:sys-operation-log:delete')")
    public CommonResult<Boolean> deleteSysOperationLog(@RequestParam("id") Long id) {
        sysOperationLogService.deleteSysOperationLog(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得系统操作审计日志表，存储平台全模块所有操作的审计日志信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('kitchen:sys-operation-log:query')")
    public CommonResult<SysOperationLogRespVO> getSysOperationLog(@RequestParam("id") Long id) {
        SysOperationLogDO sysOperationLog = sysOperationLogService.getSysOperationLog(id);
        return success(BeanUtils.toBean(sysOperationLog, SysOperationLogRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得系统操作审计日志表，存储平台全模块所有操作的审计日志信息分页")
//    @PreAuthorize("@ss.hasPermission('kitchen:sys-operation-log:query')")
    public CommonResult<PageResult<SysOperationLogRespVO>> getSysOperationLogPage(@Valid SysOperationLogPageReqVO pageReqVO) {
        PageResult<SysOperationLogDO> pageResult = sysOperationLogService.getSysOperationLogPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SysOperationLogRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出系统操作审计日志表，存储平台全模块所有操作的审计日志信息 Excel")
//    @PreAuthorize("@ss.hasPermission('kitchen:sys-operation-log:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSysOperationLogExcel(@Valid SysOperationLogPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SysOperationLogDO> list = sysOperationLogService.getSysOperationLogPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "系统操作审计日志表，存储平台全模块所有操作的审计日志信息.xls", "数据", SysOperationLogRespVO.class,
                        BeanUtils.toBean(list, SysOperationLogRespVO.class));
    }

}
