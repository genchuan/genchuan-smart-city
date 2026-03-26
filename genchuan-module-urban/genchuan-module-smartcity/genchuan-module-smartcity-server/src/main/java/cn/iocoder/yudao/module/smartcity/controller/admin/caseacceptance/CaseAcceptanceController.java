package cn.iocoder.yudao.module.smartcity.controller.admin.caseacceptance;

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

import cn.iocoder.yudao.module.smartcity.controller.admin.caseacceptance.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.caseacceptance.CaseAcceptanceDO;
import cn.iocoder.yudao.module.smartcity.service.caseacceptance.CaseAcceptanceService;

@Tag(name = "管理后台 - 案件受理")
@RestController
@RequestMapping("/smartcity/case-acceptance")
@Validated
public class CaseAcceptanceController {

    @Resource
    private CaseAcceptanceService caseAcceptanceService;

    @PostMapping("/create")
    @Operation(summary = "创建案件受理")
    @PreAuthorize("@ss.hasPermission('smartcity:case-acceptance:create')")
    public CommonResult<Long> createCaseAcceptance(@Valid @RequestBody CaseAcceptanceSaveReqVO createReqVO) {
        return success(caseAcceptanceService.createCaseAcceptance(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新案件受理")
    @PreAuthorize("@ss.hasPermission('smartcity:case-acceptance:update')")
    public CommonResult<Boolean> updateCaseAcceptance(@Valid @RequestBody CaseAcceptanceSaveReqVO updateReqVO) {
        caseAcceptanceService.updateCaseAcceptance(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除案件受理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:case-acceptance:delete')")
    public CommonResult<Boolean> deleteCaseAcceptance(@RequestParam("id") Long id) {
        caseAcceptanceService.deleteCaseAcceptance(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得案件受理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:case-acceptance:query')")
    public CommonResult<CaseAcceptanceRespVO> getCaseAcceptance(@RequestParam("id") Long id) {
        CaseAcceptanceDO caseAcceptance = caseAcceptanceService.getCaseAcceptance(id);
        return success(BeanUtils.toBean(caseAcceptance, CaseAcceptanceRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得案件受理分页")
    @PreAuthorize("@ss.hasPermission('smartcity:case-acceptance:query')")
    public CommonResult<PageResult<CaseAcceptanceRespVO>> getCaseAcceptancePage(@Valid CaseAcceptancePageReqVO pageReqVO) {
        PageResult<CaseAcceptanceDO> pageResult = caseAcceptanceService.getCaseAcceptancePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CaseAcceptanceRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出案件受理 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:case-acceptance:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCaseAcceptanceExcel(@Valid CaseAcceptancePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CaseAcceptanceDO> list = caseAcceptanceService.getCaseAcceptancePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "案件受理.xls", "数据", CaseAcceptanceRespVO.class,
                        BeanUtils.toBean(list, CaseAcceptanceRespVO.class));
    }

}