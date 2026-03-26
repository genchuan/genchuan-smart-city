package cn.iocoder.yudao.module.smartcity.controller.admin.caseinvestigation;

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

import cn.iocoder.yudao.module.smartcity.controller.admin.caseinvestigation.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.caseinvestigation.CaseInvestigationDO;
import cn.iocoder.yudao.module.smartcity.service.caseinvestigation.CaseInvestigationService;

@Tag(name = "管理后台 - 案件调查")
@RestController
@RequestMapping("/smartcity/case-investigation")
@Validated
public class CaseInvestigationController {

    @Resource
    private CaseInvestigationService caseInvestigationService;

    @PostMapping("/create")
    @Operation(summary = "创建案件调查")
    @PreAuthorize("@ss.hasPermission('smartcity:case-investigation:create')")
    public CommonResult<Long> createCaseInvestigation(@Valid @RequestBody CaseInvestigationSaveReqVO createReqVO) {
        return success(caseInvestigationService.createCaseInvestigation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新案件调查")
    @PreAuthorize("@ss.hasPermission('smartcity:case-investigation:update')")
    public CommonResult<Boolean> updateCaseInvestigation(@Valid @RequestBody CaseInvestigationSaveReqVO updateReqVO) {
        caseInvestigationService.updateCaseInvestigation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除案件调查")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:case-investigation:delete')")
    public CommonResult<Boolean> deleteCaseInvestigation(@RequestParam("id") Long id) {
        caseInvestigationService.deleteCaseInvestigation(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得案件调查")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:case-investigation:query')")
    public CommonResult<CaseInvestigationRespVO> getCaseInvestigation(@RequestParam("id") Long id) {
        CaseInvestigationDO caseInvestigation = caseInvestigationService.getCaseInvestigation(id);
        return success(BeanUtils.toBean(caseInvestigation, CaseInvestigationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得案件调查分页")
    @PreAuthorize("@ss.hasPermission('smartcity:case-investigation:query')")
    public CommonResult<PageResult<CaseInvestigationRespVO>> getCaseInvestigationPage(@Valid CaseInvestigationPageReqVO pageReqVO) {
        PageResult<CaseInvestigationDO> pageResult = caseInvestigationService.getCaseInvestigationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CaseInvestigationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出案件调查 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:case-investigation:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCaseInvestigationExcel(@Valid CaseInvestigationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CaseInvestigationDO> list = caseInvestigationService.getCaseInvestigationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "案件调查.xls", "数据", CaseInvestigationRespVO.class,
                        BeanUtils.toBean(list, CaseInvestigationRespVO.class));
    }

}