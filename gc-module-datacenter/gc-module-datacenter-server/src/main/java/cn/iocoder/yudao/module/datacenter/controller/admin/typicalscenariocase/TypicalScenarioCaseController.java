package cn.iocoder.yudao.module.datacenter.controller.admin.typicalscenariocase;

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

import cn.iocoder.yudao.module.datacenter.controller.admin.typicalscenariocase.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.typicalscenariocase.TypicalScenarioCaseDO;
import cn.iocoder.yudao.module.datacenter.service.typicalscenariocase.TypicalScenarioCaseService;

@Tag(name = "管理后台 - 典型场景案例库")
@RestController
@RequestMapping("/datacenter/typical-scenario-case")
@Validated
public class TypicalScenarioCaseController {

    @Resource
    private TypicalScenarioCaseService typicalScenarioCaseService;

    @PostMapping("/create")
    @Operation(summary = "创建典型场景案例库")
    @PreAuthorize("@ss.hasPermission('datacenter:typical-scenario-case:create')")
    public CommonResult<Long> createTypicalScenarioCase(@Valid @RequestBody TypicalScenarioCaseSaveReqVO createReqVO) {
        return success(typicalScenarioCaseService.createTypicalScenarioCase(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新典型场景案例库")
    @PreAuthorize("@ss.hasPermission('datacenter:typical-scenario-case:update')")
    public CommonResult<Boolean> updateTypicalScenarioCase(@Valid @RequestBody TypicalScenarioCaseSaveReqVO updateReqVO) {
        typicalScenarioCaseService.updateTypicalScenarioCase(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除典型场景案例库")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:typical-scenario-case:delete')")
    public CommonResult<Boolean> deleteTypicalScenarioCase(@RequestParam("id") Long id) {
        typicalScenarioCaseService.deleteTypicalScenarioCase(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得典型场景案例库")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:typical-scenario-case:query')")
    public CommonResult<TypicalScenarioCaseRespVO> getTypicalScenarioCase(@RequestParam("id") Long id) {
        TypicalScenarioCaseDO typicalScenarioCase = typicalScenarioCaseService.getTypicalScenarioCase(id);
        return success(BeanUtils.toBean(typicalScenarioCase, TypicalScenarioCaseRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得典型场景案例库分页")
    @PreAuthorize("@ss.hasPermission('datacenter:typical-scenario-case:query')")
    public CommonResult<PageResult<TypicalScenarioCaseRespVO>> getTypicalScenarioCasePage(@Valid TypicalScenarioCasePageReqVO pageReqVO) {
        PageResult<TypicalScenarioCaseDO> pageResult = typicalScenarioCaseService.getTypicalScenarioCasePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TypicalScenarioCaseRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出典型场景案例库 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:typical-scenario-case:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTypicalScenarioCaseExcel(@Valid TypicalScenarioCasePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TypicalScenarioCaseDO> list = typicalScenarioCaseService.getTypicalScenarioCasePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "典型场景案例库.xls", "数据", TypicalScenarioCaseRespVO.class,
                        BeanUtils.toBean(list, TypicalScenarioCaseRespVO.class));
    }

}