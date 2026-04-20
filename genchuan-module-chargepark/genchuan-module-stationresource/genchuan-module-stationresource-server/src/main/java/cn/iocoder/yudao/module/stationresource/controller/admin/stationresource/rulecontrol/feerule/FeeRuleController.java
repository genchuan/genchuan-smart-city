package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule;

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


@Tag(name = "管理后台 - 收费规则")
@RestController
@RequestMapping("/stationresource/fee-rule")
@Validated
public class FeeRuleController {

    @Resource
    private FeeRuleService feeRuleService;

    @PostMapping("/create")
    @Operation(summary = "创建收费规则")
    @PreAuthorize("@ss.hasPermission('stationresource:fee-rule:create')")
    public CommonResult<Long> createFeeRule(@Valid @RequestBody FeeRuleSaveReqVO createReqVO) {
        return success(feeRuleService.createFeeRule(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新收费规则")
    @PreAuthorize("@ss.hasPermission('stationresource:fee-rule:update')")
    public CommonResult<Boolean> updateFeeRule(@Valid @RequestBody FeeRuleSaveReqVO updateReqVO) {
        feeRuleService.updateFeeRule(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除收费规则")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('stationresource:fee-rule:delete')")
    public CommonResult<Boolean> deleteFeeRule(@RequestParam("id") Long id) {
        feeRuleService.deleteFeeRule(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除收费规则")
                @PreAuthorize("@ss.hasPermission('stationresource:fee-rule:delete')")
    public CommonResult<Boolean> deleteFeeRuleList(@RequestParam("ids") List<Long> ids) {
        feeRuleService.deleteFeeRuleListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得收费规则")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('stationresource:fee-rule:query')")
    public CommonResult<FeeRuleRespVO> getFeeRule(@RequestParam("id") Long id) {
        FeeRuleDO feeRule = feeRuleService.getFeeRule(id);
        return success(BeanUtils.toBean(feeRule, FeeRuleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得收费规则分页")
    @PreAuthorize("@ss.hasPermission('stationresource:fee-rule:query')")
    public CommonResult<PageResult<FeeRuleRespVO>> getFeeRulePage(@Valid FeeRulePageReqVO pageReqVO) {
        PageResult<FeeRuleDO> pageResult = feeRuleService.getFeeRulePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, FeeRuleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出收费规则 Excel")
    @PreAuthorize("@ss.hasPermission('stationresource:fee-rule:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportFeeRuleExcel(@Valid FeeRulePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<FeeRuleDO> list = feeRuleService.getFeeRulePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "收费规则.xls", "数据", FeeRuleRespVO.class,
                        BeanUtils.toBean(list, FeeRuleRespVO.class));
    }

}
