package cn.iocoder.yudao.module.smartcity.controller.admin.caseclosure;

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

import cn.iocoder.yudao.module.smartcity.controller.admin.caseclosure.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.caseclosure.CaseClosureDO;
import cn.iocoder.yudao.module.smartcity.service.caseclosure.CaseClosureService;

@Tag(name = "管理后台 - 案件结案")
@RestController
@RequestMapping("/smartcity/case-closure")
@Validated
public class CaseClosureController {

    @Resource
    private CaseClosureService caseClosureService;

    @PostMapping("/create")
    @Operation(summary = "创建案件结案")
    @PreAuthorize("@ss.hasPermission('smartcity:case-closure:create')")
    public CommonResult<Long> createCaseClosure(@Valid @RequestBody CaseClosureSaveReqVO createReqVO) {
        return success(caseClosureService.createCaseClosure(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新案件结案")
    @PreAuthorize("@ss.hasPermission('smartcity:case-closure:update')")
    public CommonResult<Boolean> updateCaseClosure(@Valid @RequestBody CaseClosureSaveReqVO updateReqVO) {
        caseClosureService.updateCaseClosure(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除案件结案")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:case-closure:delete')")
    public CommonResult<Boolean> deleteCaseClosure(@RequestParam("id") Long id) {
        caseClosureService.deleteCaseClosure(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得案件结案")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:case-closure:query')")
    public CommonResult<CaseClosureRespVO> getCaseClosure(@RequestParam("id") Long id) {
        CaseClosureDO caseClosure = caseClosureService.getCaseClosure(id);
        return success(BeanUtils.toBean(caseClosure, CaseClosureRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得案件结案分页")
    @PreAuthorize("@ss.hasPermission('smartcity:case-closure:query')")
    public CommonResult<PageResult<CaseClosureRespVO>> getCaseClosurePage(@Valid CaseClosurePageReqVO pageReqVO) {
        PageResult<CaseClosureDO> pageResult = caseClosureService.getCaseClosurePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CaseClosureRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出案件结案 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:case-closure:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCaseClosureExcel(@Valid CaseClosurePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CaseClosureDO> list = caseClosureService.getCaseClosurePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "案件结案.xls", "数据", CaseClosureRespVO.class,
                        BeanUtils.toBean(list, CaseClosureRespVO.class));
    }

}