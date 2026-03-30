package cn.iocoder.yudao.module.smartcity.controller.admin.lawdocument;

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

import cn.iocoder.yudao.module.smartcity.controller.admin.lawdocument.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.lawdocument.LawDocumentDO;
import cn.iocoder.yudao.module.smartcity.service.lawdocument.LawDocumentService;

@Tag(name = "管理后台 - 执法文书")
@RestController
@RequestMapping("/smartcity/law-document")
@Validated
public class LawDocumentController {

    @Resource
    private LawDocumentService lawDocumentService;

    @PostMapping("/create")
    @Operation(summary = "创建执法文书")
    @PreAuthorize("@ss.hasPermission('smartcity:law-document:create')")
    public CommonResult<Long> createLawDocument(@Valid @RequestBody LawDocumentSaveReqVO createReqVO) {
        return success(lawDocumentService.createLawDocument(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新执法文书")
    @PreAuthorize("@ss.hasPermission('smartcity:law-document:update')")
    public CommonResult<Boolean> updateLawDocument(@Valid @RequestBody LawDocumentSaveReqVO updateReqVO) {
        lawDocumentService.updateLawDocument(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除执法文书")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:law-document:delete')")
    public CommonResult<Boolean> deleteLawDocument(@RequestParam("id") Long id) {
        lawDocumentService.deleteLawDocument(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得执法文书")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:law-document:query')")
    public CommonResult<LawDocumentRespVO> getLawDocument(@RequestParam("id") Long id) {
        LawDocumentDO lawDocument = lawDocumentService.getLawDocument(id);
        return success(BeanUtils.toBean(lawDocument, LawDocumentRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得执法文书分页")
    @PreAuthorize("@ss.hasPermission('smartcity:law-document:query')")
    public CommonResult<PageResult<LawDocumentRespVO>> getLawDocumentPage(@Valid LawDocumentPageReqVO pageReqVO) {
        PageResult<LawDocumentDO> pageResult = lawDocumentService.getLawDocumentPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, LawDocumentRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出执法文书 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:law-document:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportLawDocumentExcel(@Valid LawDocumentPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<LawDocumentDO> list = lawDocumentService.getLawDocumentPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "执法文书.xls", "数据", LawDocumentRespVO.class,
                        BeanUtils.toBean(list, LawDocumentRespVO.class));
    }

}