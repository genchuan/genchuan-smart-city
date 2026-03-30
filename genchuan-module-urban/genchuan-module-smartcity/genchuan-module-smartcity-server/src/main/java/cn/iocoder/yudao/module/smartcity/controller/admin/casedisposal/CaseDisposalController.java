package cn.iocoder.yudao.module.smartcity.controller.admin.casedisposal;

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

import cn.iocoder.yudao.module.smartcity.controller.admin.casedisposal.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.casedisposal.CaseDisposalDO;
import cn.iocoder.yudao.module.smartcity.service.casedisposal.CaseDisposalService;

@Tag(name = "管理后台 - 案件处理")
@RestController
@RequestMapping("/smartcity/case-disposal")
@Validated
public class CaseDisposalController {

    @Resource
    private CaseDisposalService caseDisposalService;

    @PostMapping("/create")
    @Operation(summary = "创建案件处理")
    @PreAuthorize("@ss.hasPermission('smartcity:case-disposal:create')")
    public CommonResult<Long> createCaseDisposal(@Valid @RequestBody CaseDisposalSaveReqVO createReqVO) {
        return success(caseDisposalService.createCaseDisposal(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新案件处理")
    @PreAuthorize("@ss.hasPermission('smartcity:case-disposal:update')")
    public CommonResult<Boolean> updateCaseDisposal(@Valid @RequestBody CaseDisposalSaveReqVO updateReqVO) {
        caseDisposalService.updateCaseDisposal(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除案件处理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:case-disposal:delete')")
    public CommonResult<Boolean> deleteCaseDisposal(@RequestParam("id") Long id) {
        caseDisposalService.deleteCaseDisposal(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得案件处理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:case-disposal:query')")
    public CommonResult<CaseDisposalRespVO> getCaseDisposal(@RequestParam("id") Long id) {
        CaseDisposalDO caseDisposal = caseDisposalService.getCaseDisposal(id);
        return success(BeanUtils.toBean(caseDisposal, CaseDisposalRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得案件处理分页")
    @PreAuthorize("@ss.hasPermission('smartcity:case-disposal:query')")
    public CommonResult<PageResult<CaseDisposalRespVO>> getCaseDisposalPage(@Valid CaseDisposalPageReqVO pageReqVO) {
        PageResult<CaseDisposalDO> pageResult = caseDisposalService.getCaseDisposalPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CaseDisposalRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出案件处理 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:case-disposal:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCaseDisposalExcel(@Valid CaseDisposalPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CaseDisposalDO> list = caseDisposalService.getCaseDisposalPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "案件处理.xls", "数据", CaseDisposalRespVO.class,
                        BeanUtils.toBean(list, CaseDisposalRespVO.class));
    }

}