package cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismngmatter;

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismngmatter.vo.AnalysisMngMatterPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismngmatter.vo.AnalysisMngMatterRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismngmatter.vo.AnalysisMngMatterSaveReqVO;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismngmatter.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysismngmatter.AnalysisMngMatterDO;
import cn.iocoder.yudao.module.datacenter.service.analysis.analysismngmatter.AnalysisMngMatterService;

@Tag(name = "管理后台 - 按管理事项分析研判统计")
@RestController
@RequestMapping("/datacenter/analysis-mng-matter")
@Validated
public class AnalysisMngMatterController {

    @Resource
    private AnalysisMngMatterService analysisMngMatterService;

    @PostMapping("/create")
    @Operation(summary = "创建按管理事项分析研判统计")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-mng-matter:create')")
    public CommonResult<Long> createAnalysisMngMatter(@Valid @RequestBody AnalysisMngMatterSaveReqVO createReqVO) {
        return success(analysisMngMatterService.createAnalysisMngMatter(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新按管理事项分析研判统计")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-mng-matter:update')")
    public CommonResult<Boolean> updateAnalysisMngMatter(@Valid @RequestBody AnalysisMngMatterSaveReqVO updateReqVO) {
        analysisMngMatterService.updateAnalysisMngMatter(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除按管理事项分析研判统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-mng-matter:delete')")
    public CommonResult<Boolean> deleteAnalysisMngMatter(@RequestParam("id") Long id) {
        analysisMngMatterService.deleteAnalysisMngMatter(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得按管理事项分析研判统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-mng-matter:query')")
    public CommonResult<AnalysisMngMatterRespVO> getAnalysisMngMatter(@RequestParam("id") Long id) {
        AnalysisMngMatterDO analysisMngMatter = analysisMngMatterService.getAnalysisMngMatter(id);
        return success(BeanUtils.toBean(analysisMngMatter, AnalysisMngMatterRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得按管理事项分析研判统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-mng-matter:query')")
    public CommonResult<PageResult<AnalysisMngMatterRespVO>> getAnalysisMngMatterPage(@Valid AnalysisMngMatterPageReqVO pageReqVO) {
        PageResult<AnalysisMngMatterDO> pageResult = analysisMngMatterService.getAnalysisMngMatterPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AnalysisMngMatterRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出按管理事项分析研判统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-mng-matter:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAnalysisMngMatterExcel(@Valid AnalysisMngMatterPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AnalysisMngMatterDO> list = analysisMngMatterService.getAnalysisMngMatterPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "按管理事项分析研判统计.xls", "数据", AnalysisMngMatterRespVO.class,
                        BeanUtils.toBean(list, AnalysisMngMatterRespVO.class));
    }

}