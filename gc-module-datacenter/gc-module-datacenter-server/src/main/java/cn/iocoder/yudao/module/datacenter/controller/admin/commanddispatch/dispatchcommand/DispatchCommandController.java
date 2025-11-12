package cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatchcommand;

import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatchcommand.vo.DispatchCommandPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatchcommand.vo.DispatchCommandRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatchcommand.vo.DispatchCommandSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.dispatchcommand.DispatchCommandDO;
import cn.iocoder.yudao.module.datacenter.service.commanddispatch.dispatchcommand.DispatchCommandService;

@Tag(name = "管理后台 - 调度指令")
@RestController
@RequestMapping("/datacenter/dispatch-command")
@Validated
public class DispatchCommandController {

    @Resource
    private DispatchCommandService dispatchCommandService;

    @PostMapping("/create")
    @Operation(summary = "创建调度指令")
    @PreAuthorize("@ss.hasPermission('datacenter:dispatch-command:create')")
    public CommonResult<Long> createDispatchCommand(@Valid @RequestBody DispatchCommandSaveReqVO createReqVO) {
        return success(dispatchCommandService.createDispatchCommand(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新调度指令")
    @PreAuthorize("@ss.hasPermission('datacenter:dispatch-command:update')")
    public CommonResult<Boolean> updateDispatchCommand(@Valid @RequestBody DispatchCommandSaveReqVO updateReqVO) {
        dispatchCommandService.updateDispatchCommand(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除调度指令")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:dispatch-command:delete')")
    public CommonResult<Boolean> deleteDispatchCommand(@RequestParam("id") Long id) {
        dispatchCommandService.deleteDispatchCommand(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得调度指令")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:dispatch-command:query')")
    public CommonResult<DispatchCommandRespVO> getDispatchCommand(@RequestParam("id") Long id) {
        DispatchCommandDO dispatchCommand = dispatchCommandService.getDispatchCommand(id);
        return success(BeanUtils.toBean(dispatchCommand, DispatchCommandRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得调度指令分页")
    @PreAuthorize("@ss.hasPermission('datacenter:dispatch-command:query')")
    public CommonResult<PageResult<DispatchCommandRespVO>> getDispatchCommandPage(@Valid DispatchCommandPageReqVO pageReqVO) {
        PageResult<DispatchCommandDO> pageResult = dispatchCommandService.getDispatchCommandPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DispatchCommandRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出调度指令 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:dispatch-command:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDispatchCommandExcel(@Valid DispatchCommandPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DispatchCommandDO> list = dispatchCommandService.getDispatchCommandPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "调度指令.xls", "数据", DispatchCommandRespVO.class,
                        BeanUtils.toBean(list, DispatchCommandRespVO.class));
    }

}