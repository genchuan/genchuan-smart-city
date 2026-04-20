package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.depositplan;

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


@Tag(name = "管理后台 - 押金方案")
@RestController
@RequestMapping("/stationresource/deposit-plan")
@Validated
public class DepositPlanController {

    @Resource
    private DepositPlanService depositPlanService;

    @PostMapping("/create")
    @Operation(summary = "创建押金方案")
    @PreAuthorize("@ss.hasPermission('stationresource:deposit-plan:create')")
    public CommonResult<Long> createDepositPlan(@Valid @RequestBody DepositPlanSaveReqVO createReqVO) {
        return success(depositPlanService.createDepositPlan(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新押金方案")
    @PreAuthorize("@ss.hasPermission('stationresource:deposit-plan:update')")
    public CommonResult<Boolean> updateDepositPlan(@Valid @RequestBody DepositPlanSaveReqVO updateReqVO) {
        depositPlanService.updateDepositPlan(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除押金方案")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('stationresource:deposit-plan:delete')")
    public CommonResult<Boolean> deleteDepositPlan(@RequestParam("id") Long id) {
        depositPlanService.deleteDepositPlan(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除押金方案")
                @PreAuthorize("@ss.hasPermission('stationresource:deposit-plan:delete')")
    public CommonResult<Boolean> deleteDepositPlanList(@RequestParam("ids") List<Long> ids) {
        depositPlanService.deleteDepositPlanListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得押金方案")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('stationresource:deposit-plan:query')")
    public CommonResult<DepositPlanRespVO> getDepositPlan(@RequestParam("id") Long id) {
        DepositPlanDO depositPlan = depositPlanService.getDepositPlan(id);
        return success(BeanUtils.toBean(depositPlan, DepositPlanRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得押金方案分页")
    @PreAuthorize("@ss.hasPermission('stationresource:deposit-plan:query')")
    public CommonResult<PageResult<DepositPlanRespVO>> getDepositPlanPage(@Valid DepositPlanPageReqVO pageReqVO) {
        PageResult<DepositPlanDO> pageResult = depositPlanService.getDepositPlanPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DepositPlanRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出押金方案 Excel")
    @PreAuthorize("@ss.hasPermission('stationresource:deposit-plan:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDepositPlanExcel(@Valid DepositPlanPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DepositPlanDO> list = depositPlanService.getDepositPlanPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "押金方案.xls", "数据", DepositPlanRespVO.class,
                        BeanUtils.toBean(list, DepositPlanRespVO.class));
    }

}
