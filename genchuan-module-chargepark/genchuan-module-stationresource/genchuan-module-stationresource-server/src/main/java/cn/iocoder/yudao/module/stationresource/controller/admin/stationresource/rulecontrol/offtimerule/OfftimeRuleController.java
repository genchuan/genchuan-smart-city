package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.offtimerule;

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


@Tag(name = "管理后台 - 错时规则")
@RestController
@RequestMapping("/stationresource/offtime-rule")
@Validated
public class OfftimeRuleController {

    @Resource
    private OfftimeRuleService offtimeRuleService;

    @PostMapping("/create")
    @Operation(summary = "创建错时规则")
    @PreAuthorize("@ss.hasPermission('stationresource:offtime-rule:create')")
    public CommonResult<Long> createOfftimeRule(@Valid @RequestBody OfftimeRuleSaveReqVO createReqVO) {
        return success(offtimeRuleService.createOfftimeRule(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新错时规则")
    @PreAuthorize("@ss.hasPermission('stationresource:offtime-rule:update')")
    public CommonResult<Boolean> updateOfftimeRule(@Valid @RequestBody OfftimeRuleSaveReqVO updateReqVO) {
        offtimeRuleService.updateOfftimeRule(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除错时规则")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('stationresource:offtime-rule:delete')")
    public CommonResult<Boolean> deleteOfftimeRule(@RequestParam("id") Long id) {
        offtimeRuleService.deleteOfftimeRule(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除错时规则")
                @PreAuthorize("@ss.hasPermission('stationresource:offtime-rule:delete')")
    public CommonResult<Boolean> deleteOfftimeRuleList(@RequestParam("ids") List<Long> ids) {
        offtimeRuleService.deleteOfftimeRuleListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得错时规则")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('stationresource:offtime-rule:query')")
    public CommonResult<OfftimeRuleRespVO> getOfftimeRule(@RequestParam("id") Long id) {
        OfftimeRuleDO offtimeRule = offtimeRuleService.getOfftimeRule(id);
        return success(BeanUtils.toBean(offtimeRule, OfftimeRuleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得错时规则分页")
    @PreAuthorize("@ss.hasPermission('stationresource:offtime-rule:query')")
    public CommonResult<PageResult<OfftimeRuleRespVO>> getOfftimeRulePage(@Valid OfftimeRulePageReqVO pageReqVO) {
        PageResult<OfftimeRuleDO> pageResult = offtimeRuleService.getOfftimeRulePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, OfftimeRuleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出错时规则 Excel")
    @PreAuthorize("@ss.hasPermission('stationresource:offtime-rule:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOfftimeRuleExcel(@Valid OfftimeRulePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<OfftimeRuleDO> list = offtimeRuleService.getOfftimeRulePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "错时规则.xls", "数据", OfftimeRuleRespVO.class,
                        BeanUtils.toBean(list, OfftimeRuleRespVO.class));
    }

}
