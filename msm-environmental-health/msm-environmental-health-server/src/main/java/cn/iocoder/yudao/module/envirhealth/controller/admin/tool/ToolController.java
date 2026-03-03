/*
package cn.iocoder.yudao.module.envirhealth.controller.admin.tool;

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

import cn.iocoder.yudao.module.envirhealth.controller.admin.tool.vo.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.tool.ToolDO;
import cn.iocoder.yudao.module.envirhealth.service.tool.ToolService;

@Tag(name = "环境卫生管理 - 工具字典")
@RestController
@RequestMapping("/envirhealth/tool")
@Validated
public class ToolController {

    @Resource
    private ToolService toolService;

    @PostMapping("/create")
    @Operation(summary = "创建工具字典")
    @PreAuthorize("@ss.hasPermission('envirhealth:tool:create')")
    public CommonResult<Long> createTool(@Valid @RequestBody ToolSaveReqVO createReqVO) {
        return success(toolService.createTool(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新工具字典")
    @PreAuthorize("@ss.hasPermission('envirhealth:tool:update')")
    public CommonResult<Boolean> updateTool(@Valid @RequestBody ToolSaveReqVO updateReqVO) {
        toolService.updateTool(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除工具字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:tool:delete')")
    public CommonResult<Boolean> deleteTool(@RequestParam("id") Long id) {
        toolService.deleteTool(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得工具字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:tool:query')")
    public CommonResult<ToolRespVO> getTool(@RequestParam("id") Long id) {
        ToolDO tool = toolService.getTool(id);
        return success(BeanUtils.toBean(tool, ToolRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得工具字典分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:tool:query')")
    public CommonResult<PageResult<ToolRespVO>> getToolPage(@Valid ToolPageReqVO pageReqVO) {
        PageResult<ToolDO> pageResult = toolService.getToolPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ToolRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出工具字典 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:tool:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportToolExcel(@Valid ToolPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ToolDO> list = toolService.getToolPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "工具字典.xls", "数据", ToolRespVO.class,
                        BeanUtils.toBean(list, ToolRespVO.class));
    }

}*/
